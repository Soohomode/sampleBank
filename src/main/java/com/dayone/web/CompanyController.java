package com.dayone.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    // 배당금 조회 할때 자동완성 api
    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
        return null;
    }

    // 회사리스트 조회 api
    @GetMapping
    public ResponseEntity<?> searchCompany() {
        return null;
    }

    // 회사 저장 api
    @PostMapping
    public ResponseEntity<?> addCompany() {
        return null;
    }

    // 회사를 삭제하는 api
    @DeleteMapping
    public ResponseEntity<?> deleteCompany() {
        return null;
    }

}
