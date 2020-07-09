package com.projetos.vinhos.controller;

import com.projetos.vinhos.model.TipoVinho;
import com.projetos.vinhos.model.Vinho;
import com.projetos.vinhos.repository.Vinhos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {

    @Autowired
    private Vinhos vinhos;

    @GetMapping("/novo")
    public ModelAndView novo(Vinho vinho) {
        ModelAndView model = new ModelAndView("vinhos/cadastro-vinho");
        model.addObject(vinho);
        model.addObject("tipos", TipoVinho.values());
        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        return novo(vinhos.findOne(id));
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView model = new ModelAndView("vinhos/lista-vinhos");
        model.addObject("vinhos", vinhos.findAll());
        return model;
    }

    @PostMapping("/novo")
    public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return novo(vinho);
        }
        this.vinhos.save(vinho);
        attributes.addFlashAttribute("mensagem", "Vinho Salvo com Sucesso!!");
        return new ModelAndView("redirect:/vinhos");
    }

    @DeleteMapping("/{id}")
    public String remover(@PathVariable Long id, RedirectAttributes attributes) {
        vinhos.delete(id);
        attributes.addFlashAttribute("mensagem", "Registro excluido com Sucesso!!");
        return "redirect:/vinhos";
    }
}
