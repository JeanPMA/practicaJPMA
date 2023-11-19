package com.diplomado.practicaJPMA.web.rest;

import com.diplomado.practicaJPMA.dto.RolDTO;
import com.diplomado.practicaJPMA.dto.UserDetailDTO;
import com.diplomado.practicaJPMA.services.UserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/details")
public class UserDetailController {

    private final UserDetailService userDetailService;

    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping
    public ResponseEntity<List<UserDetailDTO>> listDetails() {
        return ResponseEntity.ok().body(userDetailService.listUserDetails());
    }
}
