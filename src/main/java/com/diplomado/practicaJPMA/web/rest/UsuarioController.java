package com.diplomado.practicaJPMA.web.rest;

import com.diplomado.practicaJPMA.dto.RolDTO;
import com.diplomado.practicaJPMA.dto.UsuarioDTO;
import com.diplomado.practicaJPMA.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listUsuarios(@RequestParam(required = false, defaultValue = "false") boolean detailed) {
        if (detailed) {
            return ResponseEntity.ok().body(usuarioService.listUsersDetailed());
        } else {
            return ResponseEntity.ok().body(usuarioService.listUsers());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuariosById(@PathVariable final Integer id) {
        return ResponseEntity
                .ok()
                .body(usuarioService.getUserById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody final UsuarioDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Usuario no puede tener ya un id ingresado.");
        }

        UsuarioDTO usuarioDTO = usuarioService.save(dto);

        return ResponseEntity.created(new URI("/v1/usuarios/" + usuarioDTO.getId())).body(usuarioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editUsuario(@RequestBody final UsuarioDTO dto,
                                          @PathVariable final Integer id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid usuario id, null value");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.usuarioService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
