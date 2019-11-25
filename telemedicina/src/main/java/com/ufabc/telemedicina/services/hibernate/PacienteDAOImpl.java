package com.ufabc.telemedicina.services.hibernate;

import com.ufabc.telemedicina.domains.HuffmanPaciente;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PacienteDAOImpl implements PacienteDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public PacienteDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<HuffmanPaciente> findAllPacientes() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<HuffmanPaciente> theQuery = currentSession.createQuery("from pacientes", HuffmanPaciente.class);

        List<HuffmanPaciente> pacientes = theQuery.getResultList();

        return pacientes;
    }

    @Override
    public List<HuffmanPaciente> findByCpf(long cpf) {
        Session currentSession = entityManager.unwrap(Session.class);
        String cpfString = String.valueOf(cpf);

        Query<HuffmanPaciente> theQuery = currentSession.createQuery("from pacientes WHERE cpf=:cpf",HuffmanPaciente.class);
        theQuery.setParameter("cpf", cpf);
        List<HuffmanPaciente> pacientes = theQuery.getResultList();

        return pacientes;
    }

    @Override
    public void savePaciente(HuffmanPaciente paciente) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(paciente);
    }

    @Override
    public void deleteClientByCpf(long cpf) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from clients where id=:cpf");
        theQuery.setParameter("cpf", cpf);
        theQuery.executeUpdate();

    }
}
