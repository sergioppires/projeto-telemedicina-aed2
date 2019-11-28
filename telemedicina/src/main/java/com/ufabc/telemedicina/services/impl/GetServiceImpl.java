package com.ufabc.telemedicina.services.impl;

import com.ufabc.telemedicina.domains.HuffmanPaciente;
import com.ufabc.telemedicina.services.GetService;
import com.ufabc.telemedicina.services.hibernate.PacienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetServiceImpl implements GetService {

    private PacienteDAO pacienteDAO;

    @Autowired
    public GetServiceImpl(PacienteDAO thePacienteDAO) {pacienteDAO = thePacienteDAO;}

    @Override
    @Transactional
    public List<HuffmanPaciente> findAllPacientes() {
        return pacienteDAO.findAllPacientes();
    }

    @Override
    @Transactional
    public List<HuffmanPaciente> findByHospital(String hospital) {
        return pacienteDAO.findByHospital(hospital);
    }



}
