package com.ufabc.telemedicina.services.impl;

import com.ufabc.telemedicina.domains.HuffmanPaciente;
import com.ufabc.telemedicina.services.PostService;
import com.ufabc.telemedicina.services.hibernate.PacienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PacienteDAO pacienteDAO;

    @Autowired
    public PostServiceImpl(PacienteDAO thePacienteDAO) {pacienteDAO = thePacienteDAO;}


    @Override
    public void savePacient(HuffmanPaciente paciente) {
        pacienteDAO.savePaciente(paciente);
    }

    @Override
    public void deletePacientById(long cpf) {
        pacienteDAO.deleteClientByCpf(cpf);
    }
}
