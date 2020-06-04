package com.cg.Project;


import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.project.entity.Postnews;
import com.cg.project.service.RequestService;


@SpringBootTest
class PostTest {

	@Autowired
	private RequestService postNewsService;

	@Test
	void getNewsPositiveTest() {
		Postnews postnews = null;
		List<Postnews> news = postNewsService.findAll1(postnews);
		assertNotNull(news);
	}

	@Test
	void getNewsNegativeTest() {
		Postnews postnews = null;
		List<Postnews> news = postNewsService.findAll1(postnews);
		assertNotEquals(null, news);
	}

}