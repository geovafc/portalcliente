package br.com.coderbank.portalcliente.controllers;

import br.com.coderbank.portalcliente.dtos.request.ClienteRequestDTO;
import br.com.coderbank.portalcliente.dtos.response.ClienteResponseDTO;
import br.com.coderbank.portalcliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/clientes")
public class ClienteControllerV2 {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody ClienteRequestDTO clienteRequestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.salvar(clienteRequestDTO));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvarNovo(@RequestBody ClienteRequestDTO clienteRequestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.salvar(clienteRequestDTO));
    }

}
