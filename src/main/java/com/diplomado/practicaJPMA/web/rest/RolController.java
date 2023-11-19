package com.diplomado.practicaJPMA.web.rest;


import com.diplomado.practicaJPMA.domain.entities.Rol;
import com.diplomado.practicaJPMA.dto.RolDTO;
import com.diplomado.practicaJPMA.services.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/roles")
public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<RolDTO>> listRoles() {
            return ResponseEntity.ok().body(rolService.listRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> getRolById(@PathVariable final Integer id) {
        return ResponseEntity
                .ok()
                .body(rolService.getRolById(id).orElseThrow(() -> new IllegalArgumentException("Recurno no encontrado: " + id)));
    }
    @GetMapping("/total")
    public ResponseEntity<List<RolDTO>> rolesConUsuarios() {
        return ResponseEntity.ok().body(rolService.listRolesConUsuarios());
    }
    @PostMapping
    public ResponseEntity<RolDTO> create(@RequestBody final RolDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Rol no puede tener ya un id ingresado.");
        }

        RolDTO rolDTO = rolService.save(dto);

        return ResponseEntity.created(new URI("/v1/students/" + rolDTO.getId())).body(rolDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> editRol(@RequestBody final RolDTO dto,
                                          @PathVariable final Integer id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid rol id, null value");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.rolService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        rolService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
