package com.gym.gym_system.controller;

import com.gym.gym_system.dto.MemberDTO;
import com.gym.gym_system.entity.Member;
import com.gym.gym_system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Optional<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public Member createMember(@RequestBody MemberDTO dto) {
        return memberService.createMember(dto);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody MemberDTO dto) {
        return memberService.updateMember(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
