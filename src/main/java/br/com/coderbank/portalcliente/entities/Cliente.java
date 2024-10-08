package br.com.coderbank.portalcliente.entities;

import br.com.coderbank.portalcliente.entities.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.UUID;


@Entity
@Table(name = "CB_CLIENTE")
public class Cliente {

    @Id
//    AUTO - gerado de forma automatica
    @GeneratedValue(strategy = GenerationType.AUTO)
//Identificador Único Universal

//UUId são identificadores únicos que podem ser gerados em qualquer lugar
// são próprios para arquiteturas distribuídas, evitando conflitos que gerariam
// se fossem sequenciais

// Ele possui alta probabilidade de ser único, mesmo se gerado em sistemas diferentes

//    Permite trabalhar com UUID no mysql e h2
//    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column
    private String email;

    @Column
    private Integer idade;

    @Column
    private Status status;

    @Column
    private String endereco;

    @Column
    private String criadoPeloUsuario;

    @Column
    @CreationTimestamp
    private String criadoDataEHora;

    @Column
    private String editadoPeloUsuario;

    @Column
    @UpdateTimestamp
    private  String editadoDataEHora;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCriadoPeloUsuario() {
        return criadoPeloUsuario;
    }

    public void setCriadoPeloUsuario(String criadoPeloUsuario) {
        this.criadoPeloUsuario = criadoPeloUsuario;
    }

    public String getCriadoDataEHora() {
        return criadoDataEHora;
    }

    public void setCriadoDataEHora(String criadoDataEHora) {
        this.criadoDataEHora = criadoDataEHora;
    }

    public String getEditadoPeloUsuario() {
        return editadoPeloUsuario;
    }

    public void setEditadoPeloUsuario(String editadoPeloUsuario) {
        this.editadoPeloUsuario = editadoPeloUsuario;
    }

    public String getEditadoDataEHora() {
        return editadoDataEHora;
    }

    public void setEditadoDataEHora(String editadoDataEHora) {
        this.editadoDataEHora = editadoDataEHora;
    }
}