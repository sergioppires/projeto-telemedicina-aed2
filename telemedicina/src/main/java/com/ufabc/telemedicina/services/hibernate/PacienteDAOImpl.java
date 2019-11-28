package com.ufabc.telemedicina.services.hibernate;

import com.ufabc.telemedicina.domains.HuffmanPaciente;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

        Query<HuffmanPaciente> theQuery = currentSession.createQuery("from pacientes");
        currentSession.close();
        return theQuery.list();
    }

    @Override
    @Transactional
    public List<HuffmanPaciente> findByHospital(String hospital) {
        TypedQuery<HuffmanPaciente> q = entityManager.createQuery("SELECT p FROM pacientes p WHERE p.hospital = :hospital", HuffmanPaciente.class);
        q.setParameter("hospital", hospital);
        List<HuffmanPaciente> pacientes =  q.getResultList();
        return pacientes;

    }

    @Override
    @Transactional
    public void savePaciente(HuffmanPaciente paciente) {
        if (paciente.getId() == null) {
            entityManager.persist(paciente);
        } else {
            paciente = entityManager.merge(paciente);
        }
    }

    @Override
    @Transactional
    public void deleteClientByCpf(long cpf) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from clients where id=:cpf");
        theQuery.setParameter("cpf", cpf);
        theQuery.executeUpdate();
        currentSession.close();

    }
}
