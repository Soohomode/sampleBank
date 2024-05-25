package com.dayone.persist;

import com.dayone.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUsername(String username); // id를 기준으로 회원정보를 찾는 메서드

    boolean existsByUsername(String username); // 회원가입을 할때 이미 존재하는 회원인지 확인하는 메서드

}
