package com.dayone.model;

import lombok.Data;

import java.util.List;

public class Auth {

    @Data
    public static class SignIn { // 로그인 클래스
        private String username;
        private String password;
    }

    @Data
    public static class SignUp { // 회원가입 클래스
        private String username;
        private String password;
        private List<String> roles;

        public MemberEntity toEntity() {
            return MemberEntity.builder()
                    .username(this.username)
                    .password(this.password)
                    .roles(this.roles)
                    .build();
        }
    }

}
