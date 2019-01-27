package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		List<Aluno> all = service.findAll();
		model.addAttribute("listAluno", all);
		model.addAttribute("");

		return "aluno/index";
	}
	
	@GetMapping("/new")
	public String create(Model model, @ModelAttribute Aluno aluno) {
		
		return "aluno/create"; 
	}
	
	@PostMapping
	public String create(Model model, @ModelAttribute Aluno alunoEntity, RedirectAttributes redirectAttributes) {
		
		Aluno aluno = null;
		
		try {
			aluno = service.save(alunoEntity);
			redirectAttributes.addFlashAttribute("success", "Aluno criado com sucesso");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Erro ao criar o Aluno");
			e.printStackTrace();
		}
		
		return "redirect:/alunos/"; 
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		
		try {
			if (id != null) {				
				Aluno entity = service.findOne(id).get();
				model.addAttribute("cliente", entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "aluno/create";
	}

	@PutMapping
	public String update(@ModelAttribute Aluno entity, BindingResult result, 
			             RedirectAttributes redirectAttributes) {
		Aluno aluno = null;
		
		try {
			aluno = service.save(entity);
			redirectAttributes.addFlashAttribute("success", "Aluno atualizado com sucesso");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error");
			e.printStackTrace();
		}
		
		return "redirect:/alunos/";
	}

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Aluno entity = service.findOne(id).get();
				service.delete(entity);
				redirectAttributes.addFlashAttribute("success", "Aluno deletado com sucesso");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error ao deletar o Aluno");
			e.printStackTrace();
		}

		return "redirect:/alunos/";
	}

}
