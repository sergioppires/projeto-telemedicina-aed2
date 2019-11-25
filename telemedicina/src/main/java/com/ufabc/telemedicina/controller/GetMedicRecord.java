package com.ufabc.telemedicina.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufabc.telemedicina.domains.HuffmanPaciente;
import com.ufabc.telemedicina.domains.Paciente;
import com.ufabc.telemedicina.huffman.HuffmanCoding;
import com.ufabc.telemedicina.huffman.HuffmanDecoder;
import com.ufabc.telemedicina.services.GetService;
import com.ufabc.telemedicina.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projetoaed")
public class GetMedicRecord {

    @Autowired
    private HuffmanDecoder huffmanDecoder;

    @Autowired
    GetService getService;

    @Autowired
    PostService postService;

    @GetMapping(value="/paciente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> requestPaciente(
            @PathVariable(value="id") long documento) throws JsonProcessingException {

        List<HuffmanPaciente> listaPrint = getService.findByCpf(documento);
        HuffmanPaciente pacientezero = listaPrint.get(0);
        System.out.println("Começo do decoder DEPOIS de bater na base de Dados");
        Paciente paciente = huffmanDecoder.desserializar(pacientezero);

        return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
    };

    @GetMapping(value="/paciente/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Paciente>> requestTodosPaciente(
            ) throws JsonProcessingException {

        List<HuffmanPaciente> listaPrint = getService.findAllPacientes();
        List<Paciente> listaRetorno = new ArrayList<>();

        listaPrint.forEach(lista -> {
            Paciente pacienteLista = new Paciente();
            try {
                pacienteLista = huffmanDecoder.desserializar(lista);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            listaRetorno.add(pacienteLista);
        });



        return new ResponseEntity<List<Paciente>>(listaRetorno, HttpStatus.OK);
    };




}
