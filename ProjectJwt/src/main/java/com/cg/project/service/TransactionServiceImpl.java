package com.cg.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.project.dao.TransactiondetailsRepo;
import com.cg.project.entity.TransactionDetails;

@Service
public class TransactionServiceImpl implements TransactionService {

	private TransactiondetailsRepo transactiondetailsRepository;

	@Autowired
	public TransactionServiceImpl(TransactiondetailsRepo theTransactiondetailsRepository) {
		this.transactiondetailsRepository = theTransactiondetailsRepository;
	}

	@Override
	public List<TransactionDetails> findAllTransaction() {
		return transactiondetailsRepository.findAll();
	}

}
