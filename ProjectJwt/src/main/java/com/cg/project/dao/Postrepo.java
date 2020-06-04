package com.cg.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.project.entity.Postnews;
import com.cg.project.entity.Request;

public interface Postrepo extends JpaRepository<Postnews, Integer>{

}
