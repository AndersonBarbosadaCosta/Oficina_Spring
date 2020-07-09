package com.projetos.vinhos.repository;

import com.projetos.vinhos.model.Vinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Vinhos extends JpaRepository<Vinho, Long> {

}
