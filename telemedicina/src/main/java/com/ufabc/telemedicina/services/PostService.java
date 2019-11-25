package com.ufabc.telemedicina.services;

import com.ufabc.telemedicina.domains.HuffmanPaciente;

public interface PostService {

    public void savePacient(HuffmanPaciente paciente);

    public void deletePacientById(long cpf);

}
