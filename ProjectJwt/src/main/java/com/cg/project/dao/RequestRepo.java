package com.cg.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.project.entity.Request;

@Repository
public interface RequestRepo extends JpaRepository<Request, Integer>{


	
	@Query(value = "select a from Request a where status = 'Updated'")
	List<Request> findAllApproved();
	
	@Query(value = "select a from Request a where status = 'Rejected'")
	List<Request> findAllRejected();

    

}
