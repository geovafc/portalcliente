package br.com.coderbank.portalcliente.controllers;

import br.com.coderbank.portalcliente.dtos.request.ClienteRequestDTO;
import br.com.coderbank.portalcliente.dtos.response.ClienteResumoResponseDTO;
import br.com.coderbank.portalcliente.dtos.response.ClienteResponseDTO;
import br.com.coderbank.portalcliente.dtos.response.PagedResponse;
import br.com.coderbank.portalcliente.services.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/clientes")
@Slf4j
public class ClienteControllerV2 {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        log.info("m=salvar, ClienteRequestDTO={}", clienteRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.salvar(clienteRequestDTO));
    }

    @GetMapping
    public PagedResponse<ClienteResumoResponseDTO> obterClientes(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {
        var pageable = PageRequest.of(pagina, tamanho);
        var paginaClientes = clienteService.obterClientes(pageable);
//        todo : Validar se está preenchendo corretamente o objeto da paginacao

        return new PagedResponse<>(paginaClientes);
    }



}
