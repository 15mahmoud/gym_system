package com.gym.gym_system.service;

import com.gym.gym_system.dto.MemberDTO;
import com.gym.gym_system.entity.Member;
import com.gym.gym_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Member createMember(MemberDTO dto) {
        Member member = new Member();
        member.setName(dto.getName());
        member.setPhoneNumber(dto.getPhoneNumber());
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, MemberDTO dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setName(dto.getName());
        member.setPhoneNumber(dto.getPhoneNumber());
        return memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
