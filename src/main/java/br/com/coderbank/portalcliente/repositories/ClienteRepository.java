package br.com.coderbank.portalcliente.repositories;

//JPA: Uma Ponte entre Objetos Java e Bancos de Dados
//JPA fornece um mecanismo para mapear objetos Java para tabelas em um banco de dados
// relacional e vice-versa.

//Vamos fazer o uso do Spring Data JPA
// Ele se integra com a JPA para fornecer uma forma fácil e eficiente de trabalhar com
// bancos de dados

//Repositórios: Interfaces que definem as operações de CRUD sobre as entidades.

//Consultas: Permite criar consultas personalizadas usando JPQL ou Query Methods,
// uma forma mais concisa e intuitiva de escrever consultas.

//Paginação e ordenação: Facilita a implementação de mecanismos de paginação e ordenação dos resultados.

//Transações: Gereia transações automaticamente para garantir a consistência dos dados.

//Suporte a diversos bancos de dados: Funciona com qualquer banco de dados que seja suportado pela JPA.



// já possui vários métodos prontos para transações com banco de dados
// Como estamos extendendo de JPaRepository, essa interface já gerá
// um bean gerenciada pelo spring boot, mas para quem quiser pode anotar aqui
// com @repository que é um steriotipo do spring para transacoes com banco de dados

import br.com.coderbank.portalcliente.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

//Query methods: para criar consultas sql automaticamente de maneira programática
// iniciamos com o nome da operação que queremos realizar seguido pelo nome do atributo da entidade

    boolean existsByCpf(String numeroCpf);
}