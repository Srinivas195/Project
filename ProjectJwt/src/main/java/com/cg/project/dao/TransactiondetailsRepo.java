package com.cg.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.project.entity.TransactionDetails;


public interface TransactiondetailsRepo extends JpaRepository<TransactionDetails, Integer> {

}
