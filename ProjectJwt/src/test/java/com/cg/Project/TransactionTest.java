package com.cg.Project;


import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.project.entity.TransactionDetails;
import com.cg.project.service.CustomerService;
import com.cg.project.service.TransactionService;


@SpringBootTest
class TransactionTest {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private CustomerService customerService;

	@Test
	void getAllTransactionsPositiveTest() {
		List<TransactionDetails> transList = transactionService.findAllTransaction();
		assertNotNull(transList);
	}

	@Test
	void getAllTransactionsNegativeTest() {
		List<TransactionDetails> transList = transactionService.findAllTransaction();
		assertNotEquals(null, transList);
	}

	@Test
	void getMyTransactionsPositiveTest() {
		int id = 1;
		List<TransactionDetails> transList = customerService.myTransaction(id);
		assertNotNull(transList);
	}

	@Test
	void getMyTransactionsNegativeTest() {
		int id = 1;
		List<TransactionDetails> transList = customerService.myTransaction(id);
		assertNotEquals(null, transList);
	}

}