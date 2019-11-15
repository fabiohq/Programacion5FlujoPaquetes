package co.com.camacho.proyectoprograamacion5.modelo;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jackeline Ramirez 20/06/2019
 * @Materia ProgramacionV
 * @Definición El proyecto consiste en dar solución al problema de estudio que
 * se enunciara mas adelante. El estudiante debe presentar dos soluciones
 * computacionales. Los detalles de las soluciones se presentan más adelante.
 * Nuestra empresa necesita enviar un ujo de paquetes a un servidor para que
 * estos sean procesados. Dentro del ujo de datos, se envían paquetes de
 * diferentes tipos como : Vídeo, Música, Imágenes, Texto, Binarios, etc. Sin
 * embargo, debido a ataques recientes realizados al servidor, donde los ujo de
 * datos conten ían muchos paquetes de un mismo tipo, se implemento una nueva
 * medida de seguridad. Esta medida de seguridad consiste en ignorar (no
 * procesar) los paquetes de tipo en un ujo de datos cuando la cantidad total de
 * ese tipo de paquete en el ujo supera un limite M. Por ejemplo, si M es 5, y
 * nuestro ujo de datos contiene 3 paquetes de Vídeos, 7 de Música, 4 de
 * Imágenes, 5 de texto y 8 Binarios, el servidor solo procesara los paquetes de
 * Vídeos, de imágenes y de texto. Los paquetes de Musida y binarios no serán
 * procesados debido a que superan el limite permitido.
 */
public class FlujoPaquete1 {
    private static int M = 2;//Conincidencias que de paquetes que se pueden procesar
    public void procesarPaquete(String strPaquetes) {        
        int totalPaquetes = strPaquetes.length();//Se saca el total de caracteres de la cadena recibida        
        int contador = 0;
        int total = 0;
        
        //Se itera la cadena recibida y se almacena cada caracter en una pocicion del arreglo
        //para acceder de forma facil a los datos que ésta contiene 
        StringBuilder sb = new StringBuilder();//El StringBuilder se usa para hacer mas eficiente la concatenacion
            sb.append("\n=================\nJackeline Ramirez\n=================\npaquetes a procesar => ");
        sb.append(strPaquetes);
        sb.append("\n");
        sb.append("Paquetes que puede procesar el servidor => ");
        sb.append(M);
        sb.append("\n\n\n");
        //CDCFB
        int conteoPaquetes = 1;
        while (conteoPaquetes <= totalPaquetes) {
            if (conteoPaquetes == 1) {//La primera iteracion se realiza en este blouque debido a que no hay un rango para extrar paquetes
                for (int i = 0; i < totalPaquetes; i++) {
                    char c = strPaquetes.charAt(i);
                    sb.append("Paquete; ");
                    sb.append(c);
                    sb.append(" => ");
                    sb.append(conteoPaquetes);
                    sb.append("\n");
                    //System.out.println(sb.toString());
                }
                conteoPaquetes++;
            } else {//La segunda en aadelante se realiza en este blouque debido a que si hay un rango para extrar paquetes                
                for (int i = 0; i < total; i++) {
                    String token = strPaquetes.substring(contador+i, conteoPaquetes+i);                   
                    Map<String, Integer> mapPaquetes = new HashMap<>();//hashmap para almacenar los pauqtes procesados encontradas en la cadena                                        
                    /*
                    Este proceso adiciona un paquete al hashmap, y si ya se encuentra en el hashmap aumenta la cantidad
                    la cuaal esta representada por el valor del hashmap
                    */
                    Integer key;
                    for(int j = 0;j<(conteoPaquetes);j++){
                        String str = Character.toString(token.charAt(j));
                        if(mapPaquetes.containsKey(str)){//Si existe
                            key =  mapPaquetes.get(str);//Se recuperaa la cantidad del paquete
                            mapPaquetes.replace(str, (key+1));//Se reemplaza incrementando el valo de los paquetes existentes para saber cuantos paquetes se han procesado
                           
                        }else{//Si no se adiciona la haashmap 
                            //La llave es el paquete y el vaalor la caantidad procesada
                            mapPaquetes.put(str, 1);                            
                        }
                    }                    
                    char c = strPaquetes.charAt(i);
                    sb.append("Paquete; ");
                    sb.append(token);
                    sb.append(" => ");
                    sb.append(conteoPaquetes-contarPaquetes(mapPaquetes));
                    sb.append("\n");
                }
                conteoPaquetes++;                
            }
            
            if (total ==0){
                total = (totalPaquetes-1); //Se disminuye total paquetes por que se van agrupando quedando menos por procesar
            }else{
                total--;
            }
            System.out.println(sb.toString());
            crearArchivoTexto(sb.toString());
            sb.setLength(0);
        }
    }

    /*
    Metodo encargado de validar y procesar paquetes que complan con el requerimiento M
    Recibe el hashmap con todos los paquetes, los cuales deben ser procesados dependiendo el valor del parametro M
    */
    public int contarPaquetes(Map<String, Integer> mapPaquetes){
        int contador = 0;
        for (Map.Entry<String, Integer> entry : mapPaquetes.entrySet()) {
            int valor = entry.getValue();            
            if(valor > M){//Se controla cuantos paquetes se procesaran
                if(contador ==0){//Si es cero se asignaa el valor al contador
                    contador  = valor;
                }else{//Si es mayor a cero se le suma al contaaador el valor que se recibe por paaraametro
                    contador  += valor;
                }                
            }
        }
        return contador;
    }
            
    public void crearArchivoTexto(String pStrCadena) {
        try {
            File archivo = new File("ArchiviPaquetes.txt");
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(pStrCadena);
            escribir.close();
        } catch (Exception e) {
            System.err.println("Error aal escribir");
        }
    }
    
    
    public static void main(String args[]) {
        FlujoPaquete1 fp = new FlujoPaquete1();
        fp.procesarPaquete("CCDFBBBAACAEA");
        //fp.procesarPaquete("CDCFB");
    }
}
