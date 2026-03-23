package com.UPIIZ.E7.services;

import com.UPIIZ.E7.dto.MateriaDTO;
import com.UPIIZ.E7.models.Materia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MateriaService {


    private final List<Materia> materias = new ArrayList<>();

    private final AtomicLong counter = new AtomicLong(1);


    public List<MateriaDTO> obtenerTodas() {
        List<MateriaDTO> dtos = new ArrayList<>();
        for (Materia m : materias) {
            dtos.add(convertirADto(m));
        }
        return dtos;
    }


    public MateriaDTO obtenerPorId(Long id) {
        return materias.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .map(this::convertirADto)
                .orElse(null);
    }


    public void guardar(MateriaDTO dto) {
        Materia nuevaMateria = new Materia(counter.getAndIncrement(), dto.getNombre(), dto.getCreditos());
        materias.add(nuevaMateria);
    }


    public void actualizar(MateriaDTO dto) {
        Optional<Materia> materiaOpt = materias.stream()
                .filter(m -> m.getId().equals(dto.getId()))
                .findFirst();

        if (materiaOpt.isPresent()) {
            Materia m = materiaOpt.get();
            m.setNombre(dto.getNombre());
            m.setCreditos(dto.getCreditos());
        }
    }


    public void eliminar(Long id) {
        materias.removeIf(m -> m.getId().equals(id));
    }


    private MateriaDTO convertirADto(Materia materia) {
        MateriaDTO dto = new MateriaDTO();
        dto.setId(materia.getId());
        dto.setNombre(materia.getNombre());
        dto.setCreditos(materia.getCreditos());
        return dto;
    }
}