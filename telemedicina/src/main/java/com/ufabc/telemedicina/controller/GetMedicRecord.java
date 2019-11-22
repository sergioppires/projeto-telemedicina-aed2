package com.ufabc.telemedicina.controller;

import com.ufabc.telemedicina.domains.Paciente;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projetoaed")
public class GetMedicRecord {

    @GetMapping(value="/paciente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> requestPaciente(
            @PathVariable(value="id") String documento){
        Paciente paciente = new Paciente();
        paciente.setCpf(documento);
        paciente.setIdade("12");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Chave", "AED2");
        return new ResponseEntity<Paciente>(paciente, responseHeaders, HttpStatus.OK);
    };




}
