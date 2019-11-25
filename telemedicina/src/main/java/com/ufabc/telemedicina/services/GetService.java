package com.ufabc.telemedicina.services;

import com.ufabc.telemedicina.domains.HuffmanPaciente;

import java.util.List;

public interface GetService {

    public List<HuffmanPaciente> findAllPacientes();

    public List<HuffmanPaciente> findByCpf(long cpf);


}
