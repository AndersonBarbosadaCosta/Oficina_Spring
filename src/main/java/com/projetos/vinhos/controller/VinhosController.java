package com.projetos.vinhos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetos.vinhos.model.TipoVinho;
import com.projetos.vinhos.model.Vinho;
import com.projetos.vinhos.repository.Vinhos;

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

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result) {

		if (result.hasErrors()) {
			return novo(vinho);
		}
		this.vinhos.save(vinho);
		return new ModelAndView("redirect:/vinhos/novo");
	}
}
