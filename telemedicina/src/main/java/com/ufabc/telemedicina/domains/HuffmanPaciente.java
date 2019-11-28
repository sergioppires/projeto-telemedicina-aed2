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

    @Column(name = "data", columnDefinition="TEXT")
    private String data;

    @Column(name = "dicionario", columnDefinition="TEXT")
    private String dicionario;

    @Column(name = "hospital", columnDefinition="TEXT")
    private String hospital;

    public HuffmanPaciente() {
    }

    public HuffmanPaciente(Long id, Long cpf, String data, String dicionario, String hospital) {
        this.id = id;
        this.cpf = cpf;
        this.data = data;
        this.dicionario = dicionario;
        this.hospital = hospital;
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

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
