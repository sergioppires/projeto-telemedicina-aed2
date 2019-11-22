package com.ufabc.telemedicina.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufabc.telemedicina.domains.Paciente;
import com.ufabc.telemedicina.huffman.HuffmanCoding;
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
            @PathVariable(value="id") String documento) throws JsonProcessingException {
        Paciente paciente = new Paciente();

        paciente.setCpf(documento);
        paciente.setIdade("12");
        paciente.setNome("Serjao");
        paciente.setSexo("M");
        paciente.setTipoSangue("O+");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Chave", "AED2");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(paciente);

        HuffmanCoding huffman = new HuffmanCoding(json);
        huffman.compress();
        System.out.println("Size before compression: " + huffman.getUncompressedSize());
        System.out.println("Size after compression: " + huffman.getCompressedSize());
        System.out.println("Compressed string: " + huffman.getCompressedString());
        System.out.println(huffman.getDictionary());



        return new ResponseEntity<Paciente>(paciente, responseHeaders, HttpStatus.OK);
    };




}
