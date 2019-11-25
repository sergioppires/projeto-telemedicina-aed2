package com.ufabc.telemedicina.huffman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufabc.telemedicina.domains.HuffmanPaciente;
import com.ufabc.telemedicina.domains.Paciente;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HuffmanDecoder {

    public static String decompress(String s, HashMap<String, String> codeToChar) {
        String temp = new String();
        String result = new String();
        for(int i = 0; i < s.length(); i++) {
            temp = temp + s.charAt(i);
            String c = codeToChar.get(temp);
            if(c!=null && c!="0") {
                result = result + c;
                temp = "";
            }
        }
        return result;
    }

    public Paciente desserializar(HuffmanPaciente paciente) throws JsonProcessingException {

        Map<String, String> dicionarioReconstruido = Arrays.stream(paciente.getDicionario().split(","))
            .map(s -> s.split("=", 2))
            .collect(Collectors.toMap(s -> s[0], s -> s[1]));

    String jsonRaw = this.decompress(paciente.getData(), (HashMap<String, String>) dicionarioReconstruido);

    ObjectMapper mapper = new ObjectMapper();
    Paciente pojo = mapper.readValue(jsonRaw, Paciente.class);
    return pojo;
}

}
