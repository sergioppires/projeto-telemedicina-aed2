package com.ufabc.telemedicina.compress;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufabc.telemedicina.domains.HuffmanPaciente;
import com.ufabc.telemedicina.domains.Paciente;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;

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

        String raw = paciente.getDicionario().substring(1, paciente.getDicionario().length()-1);
        raw = raw.replaceAll(",,","virgula,");


        String[] keyValuePairs = raw.split(",");
        HashMap<String,String> dicionarioReconstruido = new HashMap<>();

        for(String pair : keyValuePairs)                        //iterate over the pairs
        {
            if(!StringUtils.isEmpty(pair)) {
                String[] entry = pair.split("=");
                if(entry[1].trim().equals("virgula")){
                    entry[1] = ",";
                } else if (StringUtils.isEmpty(entry[1].trim())){
                    entry[1] = "_";
                }
                dicionarioReconstruido.put(entry[0].trim(), entry[1].trim());
            }
        }

        String jsonRaw = this.decompress(paciente.getData(), dicionarioReconstruido);

    ObjectMapper mapper = new ObjectMapper();
    Paciente pojo = mapper.readValue(jsonRaw, Paciente.class);



    return pojo;
}

private Paciente validadorString(Paciente paciente){
        Paciente pacienteReturn = paciente;
        pacienteReturn.setHospitalDestinado(paciente.getHospitalDestinado().replaceAll("_"," "));
        pacienteReturn.setNome(paciente.getNome().replaceAll("_"," "));
        pacienteReturn.setDescricao(paciente.getDescricao().replaceAll("_"," "));
        return pacienteReturn;
}

}
