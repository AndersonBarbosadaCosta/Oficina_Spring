package com.projetos.vinhos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.vinhos.model.Vinho;

public interface Vinhos extends JpaRepository<Vinho, Long>{

}
