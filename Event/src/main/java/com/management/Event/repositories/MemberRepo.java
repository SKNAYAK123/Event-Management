package com.management.Event.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.management.Event.model.Member;

@Repository
public interface MemberRepo extends CrudRepository<Member, Integer> {

	Member findByEmailAndPassword(String username, String password);

	List<Member> findByRole(String string);

	Member findByEmail(String email);
}
