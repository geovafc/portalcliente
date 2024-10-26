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
//        Definir os parâmetros de paginação e habilita a paginação no repository
        var pageable = PageRequest.of(pagina, tamanho);
//        Retorna uma pagina de resultados
//        contendo informações sobre a página atual, o número total de páginas,
//        o número total de elementos e a lista de elementos da página atual.
//        Page<ClienteResumoResponseDTO>
        var paginaClientes = clienteService.obterClientes(pageable);

        return new PagedResponse<>(paginaClientes);
    }



}
