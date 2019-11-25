package com.ufabc.telemedicina.domains;

import javax.persistence.*;
import java.util.Map;

@Entity(name = "pacientes")
@Table(name="pacientes", schema = "public")
public class HuffmanPaciente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "cpf")
    private Long cpf;

    @Column(name = "data")
    private String data;

    @Column(name = "dicionario")
    private String dicionario;

    public HuffmanPaciente(Long id, Long cpf, String data, String dicionario) {
        this.id = id;
        this.cpf = cpf;
        this.data = data;
        this.dicionario = dicionario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDicionario() {
        return dicionario;
    }

    public void setDicionario(String dicionario) {
        this.dicionario = dicionario;
    }
}
