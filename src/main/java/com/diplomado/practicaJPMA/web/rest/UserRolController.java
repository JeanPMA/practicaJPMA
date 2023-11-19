package com.diplomado.practicaJPMA.web.rest;

import com.diplomado.practicaJPMA.domain.entities.UserRol;
import com.diplomado.practicaJPMA.domain.entities.Usuario;
import com.diplomado.practicaJPMA.dto.UserDetailDTO;
import com.diplomado.practicaJPMA.dto.UserRolDTO;
import com.diplomado.practicaJPMA.services.UserRolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/userwithrols")
public class UserRolController {
    private  final UserRolService userRolService;

    public UserRolController(UserRolService userRolService) {
        this.userRolService = userRolService;
    }
    @GetMapping
    public ResponseEntity<List<UserRolDTO>> listUsersRoles() {
        return ResponseEntity.ok().body(userRolService.listUserRoles());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserRolDTO> actualizarParcial(@RequestBody UserRolDTO dto, @PathVariable final Long id) {
        userRolService.parcial(dto, id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
