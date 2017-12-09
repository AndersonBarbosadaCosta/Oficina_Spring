package com.projetos.vinhos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ModelAndView novo(){
		ModelAndView model = new ModelAndView("vinhos/cadastro-vinho");
		model.addObject("vinho",new Vinho());
		model.addObject("tipos",TipoVinho.values());
		return model;
	}
	
	@PostMapping("/novo")
	public String salvar(Vinho vinho){
		this.vinhos.save(vinho);
		return "redirect:/vinhos/novo";
	}
}
