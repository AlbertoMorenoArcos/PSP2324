
package PSP1aEv;

import java.util.*;


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
