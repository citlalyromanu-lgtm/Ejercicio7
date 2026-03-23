package com.UPIIZ.E7.controllers;

import com.UPIIZ.E7.dto.MateriaDTO;
import com.UPIIZ.E7.services.MateriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "/materias"})
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("materias", materiaService.obtenerTodas());
        return "listado";
    }


    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("materia", new MateriaDTO());
        return "formulario_crear";
    }


    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("materia") MateriaDTO materia) {
        materiaService.guardar(materia);
        return "redirect:/materias";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        MateriaDTO materia = materiaService.obtenerPorId(id);
        if (materia == null) return "redirect:/materias";

        model.addAttribute("materia", materia);
        return "formulario_actualizar";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("materia") MateriaDTO materia) {
        materiaService.actualizar(materia);
        return "redirect:/materias";
    }


    @GetMapping("/eliminar/{id}")
    public String mostrarConfirmacionEliminar(@PathVariable Long id, Model model) {
        MateriaDTO materia = materiaService.obtenerPorId(id);
        if (materia == null) return "redirect:/materias";

        model.addAttribute("materia", materia);
        return "formulario_eliminar";
    }


    @PostMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id) {
        materiaService.eliminar(id);
        return "redirect:/materias";
    }
}