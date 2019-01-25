package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Aluno;
import com.example.service.AlunoService;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService service;
	
	@GetMapping
	public String index(Model model) {
		
		return "aluno/index";
	}
	
//	@GetMapping("/{id}")
//	public String showAluno(Model model, @PathVariable("id") Integer id) {
//		
//		return "aluno/show"; 
//	}
//	
	@GetMapping("/new")
	public String create(Model model, @ModelAttribute Aluno aluno) {
		
		return "aluno/create"; 
	}
	
	@PostMapping
	public String create(Model model, @ModelAttribute Aluno alunoEntity, RedirectAttributes redirectAttributes) {
		
		Aluno aluno = null;
		
		try {
			aluno = service.save(alunoEntity);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/alunos/"; 
	}
	
//	@GetMapping("/{id}/update")
//	public String update(@ModelAttribute Aluno aluno, @PathVariable("id") Integer id) {
//		
//		return "aluno/create";
//	}
//	
//	@PutMapping
//	public String update(@ModelAttribute Aluno aluno, RedirectAttributes redirectAttributes) {
//		
//		return "redirect:/alunos/";
//	}
//	
//	@RequestMapping("/{id}/delete")
//	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
//		
//		return "redirect:/alunos/";
//	}

}
