package br.com.coderbank.portalcliente.services;

import br.com.coderbank.portalcliente.dtos.request.ClienteRequestDTO;
import br.com.coderbank.portalcliente.dtos.response.ClienteResponseDTO;
import br.com.coderbank.portalcliente.entities.Cliente;
import br.com.coderbank.portalcliente.entities.enums.Status;
import br.com.coderbank.portalcliente.exceptions.ClienteJaExistenteException;
import br.com.coderbank.portalcliente.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponseDTO salvar(final ClienteRequestDTO clienteRequestDTO) {

        verificarCpfDuplicado(clienteRequestDTO);

        var clienteEntity = new Cliente();

        BeanUtils.copyProperties(clienteRequestDTO, clienteEntity);

        clienteEntity.setStatus(Status.ATIVO);

        clienteRepository.save(clienteEntity);


        return new ClienteResponseDTO(clienteEntity.getId(),
                clienteEntity.getStatus(),
                clienteEntity.getCriadoPeloUsuario(),
                clienteEntity.getCriadoDataEHora(),
                clienteEntity.getEditadoPeloUsuario(),
                clienteEntity.getEditadoDataEHora()
                );
    }

    private void verificarCpfDuplicado(final ClienteRequestDTO clienteRequestDTO) {
        final var numeroCpf = clienteRequestDTO.cpf();

        if (clienteRepository.existsByCpf(numeroCpf)) {
            throw new ClienteJaExistenteException("Cliente com o cpf " +numeroCpf + " já existe.");
        }
    }
}
