package com.ufabc.telemedicina.huffman;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class HuffmanDecoder {


    public static String decompress(String s, HashMap<String, Character> codeToChar) {
        String temp = new String();
        String result = new String();
        for(int i = 0; i < s.length(); i++) {
            temp = temp + s.charAt(i);
            Character c = codeToChar.get(temp);
            if(c!=null && c!=0) {
                result = result + c;
                temp = "";
            }
        }
        return result;
    }

}
