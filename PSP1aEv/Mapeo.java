/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PSP1aEv;
import java.util.*;
/**
 *
 * @author M24Y
 */
public class Mapeo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Map<String, String> diccionario = new HashMap<>();
        diccionario.put("Hola", "Saludo coloquial.");
        diccionario.put("Adios", "despedida");
        diccionario.put("Hola", "Saludo informal.");
        System.out.println(diccionario.get("Hola"));
        
        System.out.println("Recorro el diccionario:");
        for(String clave : diccionario.keySet()){
            System.out.println(clave + ": " +diccionario.get(clave));
        }
        
        Map<String, Integer> censo = new HashMap<>();
        censo.put("Toledo", 83500);
        censo.put("Illescas", 32000);
        censo.put("Getafe", 234000);
        for(String municipio : censo.keySet()){
            System.out.println(municipio +": "+censo.get(municipio));
        }
        MapeoDeCodigo mapeo = new MapeoDeCodigo();
        System.out.println(mapeo.traduce("A"));
        System.out.println(mapeo.traduce("B"));
    }
    
}
