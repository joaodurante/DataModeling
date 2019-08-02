package com.joaodurante.springproject.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UrlUtils {

    public static String decodeParam(String s)  {
        try{
            return URLDecoder.decode(s, "UTF-8");
        }catch(UnsupportedEncodingException e){
            return "";
        }
    }

    public static List<Integer> decodeIntegerList(String s){
        String[] array = s.split(",");
        List<Integer> list = new ArrayList<>();

        for(String i : array){
            list.add(Integer.parseInt(i));
        }

        return list;
    }
}
