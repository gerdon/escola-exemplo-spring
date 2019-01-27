package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Aluno;
import com.example.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository repository;
	
	public List<Aluno> findAll(){
		return repository.findAll();
	}
	
	public Optional<Aluno> findOne(Integer id) {
		return repository.findById(id);
	}
	
	public Aluno save(Aluno entity) {
		return repository.save(entity);
	}
	
	public void delete(Aluno entity) {
		repository.delete(entity);
	}

}
