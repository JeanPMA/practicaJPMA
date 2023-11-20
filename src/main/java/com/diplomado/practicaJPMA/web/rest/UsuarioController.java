package com.diplomado.practicaJPMA.web.rest;

import com.diplomado.practicaJPMA.domain.entities.Usuario;
import com.diplomado.practicaJPMA.dto.RolDTO;
import com.diplomado.practicaJPMA.dto.UsuarioDTO;
import com.diplomado.practicaJPMA.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);


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
        try{
            return ResponseEntity
                    .ok()
                    .body(usuarioService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + id)));
        } catch (ApplicationException e){
            log.error("Un error ocurrio tratando de obtener el usuario", e);
            throw e;
        }

    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody final UsuarioDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Usuario no puede tener ya un id ingresado.");
        }

       usuarioService.save(dto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO dto) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid usuario id, null value");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        usuarioService.editarUsuario(id, dto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

    public class ApplicationException extends RuntimeException {
        public ApplicationException(String message) {
            super(message);
        }

        public ApplicationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public class ResourceNotFoundException extends ApplicationException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
