package com.management.Event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.Event.exception.ResourceNotFoundException;
import com.management.Event.model.Member;
import com.management.Event.repositories.MemberRepo;


@org.springframework.stereotype.Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepo memberRepo;;

	// Add Member...
	@Override
	public Member addMember(Member member) {
		memberRepo.save(member);
		return member;
	}

	// Find by Username and Password
	@Override
	public Member Authenticate(String username, String password) throws ResourceNotFoundException {

		try {
			return memberRepo.findByEmailAndPassword(username, password);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Resource with given value is not found");
		}

	}

	// Get Member..
	@Override
	public Member getMember(int memberId) {
		return memberRepo.findById(memberId).get();
	}

	// Update Member
	@Override
	public int updateMember(Member member) {
		Member member2 = memberRepo.findById(member.getMemberId()).get();
		member2.setFirstName(member.getFirstName());
		member2.setLastName(member.getLastName());
		member2.setEmail(member.getEmail());
		member2.setPhoneNumber(member.getPhoneNumber());
		
		memberRepo.save(member2);
		
		return 1;
	}

	// Delete Member
	@Override
	public int deleteMember(int memberId) {
		memberRepo.deleteById(memberId);
		return 1;
	}

	// List of Users
	@Override
	public List<Member> getAllUsers() {
		return (List<Member>) memberRepo.findByRole("user");
	}

	// List of Organizers
	@Override
	public List<Member> getAllMembers() {
		return memberRepo.findByRole("organizer");
	}

}
