package br.com.coderbank.portalcliente.dtos.request;

//Gera automaticamente (getters, equals, hashCode, toString)

//Imutáveis: Uma vez criado, o valor de um record não pode ser alterado

//Sintaxe enxuta

public record ClienteRequestDTO(
        String nome,
        String cpf,
        String email,
        String endereco,
        Integer idade
) {
}