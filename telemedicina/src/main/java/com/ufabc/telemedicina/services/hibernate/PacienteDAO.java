package com.ufabc.telemedicina.services.hibernate;

import com.ufabc.telemedicina.domains.HuffmanPaciente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PacienteDAO {

    public List<HuffmanPaciente> findAllPacientes();

    public List<HuffmanPaciente> findByHospital(String hospital);

    public void savePaciente(HuffmanPaciente paciente);

    public void deleteClientByCpf(long cpf);

}
