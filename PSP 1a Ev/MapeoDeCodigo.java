/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mapeo;

import java.util.*;

/**
 *
 * @author M24Y
 */
public class MapeoDeCodigo {
    
    private Map<String, Integer> tablaDeCodigos;
    
    public MapeoDeCodigo(){
        tablaDeCodigos = new HashMap<>();
        inicializaTabla();
    }
    
    private void inicializaTabla(){
        tablaDeCodigos.put("A",0);
        tablaDeCodigos.put("B",1);
    }
    
    public int traduce(String clave){
        if(tablaDeCodigos.containsKey(clave)){
        return tablaDeCodigos.get(clave);
        }else{
            return -1;
        }
    }
    
    
    public String traduceFrase(String palabra){
        String respuesta = "";
        for (int i = 0; i < palabra.length(); i++){
            String palabraParaTraducir ="";
            palabraParaTraducir += palabra.charAt(i);
        }
        return null;
    }
    
}
