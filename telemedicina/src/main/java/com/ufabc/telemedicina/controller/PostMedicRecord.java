package com.ufabc.telemedicina.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.HashMap;



@RestController
@RequestMapping("/projetoaed")
public class PostMedicRecord {

    @Autowired
    private HuffmanDecoder huffmanDecoder;

    @Autowired
    GetService getService;

    @Autowired
    PostService postService;

    @PostMapping(value="/paciente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> requestPaciente(
            @PathVariable(value="id") String documento,
            @RequestBody Paciente payload) throws JsonProcessingException {

        Paciente paciente = new Paciente();
        paciente.setCpf(documento);
        paciente.setIdade(payload.getIdade());
        paciente.setTipoSangue(payload.getTipoSangue());
        paciente.setSexo(payload.getSexo());
        paciente.setNome(payload.getNome());

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
        System.out.println("Começo do decoder");
        System.out.println(huffmanDecoder.decompress(huffman.getCompressedString(), (HashMap<String, String>) huffman.getDictionary()));

        HuffmanPaciente pacienteDados = new HuffmanPaciente(0L,Long.parseLong(paciente.getCpf()),huffman.getCompressedString(),huffman.getDictionary().toString());
        postService.savePacient(pacienteDados);

        return new ResponseEntity<Paciente>(paciente, responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping(value="/paciente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> deletePaciente(
            @PathVariable(value="id") int documento
            ) throws JsonProcessingException {

        postService.deletePacientById(documento);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
