/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contadorLocalizaciones;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author venganzaalchocolate
 */
public class ContarLocalizaciones {

    // método para leer archivo llamando al método obtenerLista
    public static void leerArchivo(String archivo) {
        List<String> lineas = obtenerLista(archivo);
        for (String linea : lineas) {
            System.out.println(linea);
        }
    }

    // método que crea un List<String> con las lineas del archivo
    private static List<String> obtenerLista(String archivo) {
        List<String> lineas = new ArrayList<>();
        try {
            lineas = Files.readAllLines(Paths.get(archivo),
                    StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Error leyendo el fichero");
        }
        return lineas;
    }

    
   // método para obtener un List<String> con el texto que coincide con la expresion regular dada en el enunciado
    public static List<String> obtenerListaLocalizaciones(String archivo) {
        List<String> lineas = obtenerLista(archivo);
        List<String> listaLineas = new ArrayList<>();
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.matches("Localizacion: [NSOE]")) {
                listaLineas.add(linea);
            }
        }
        return listaLineas;
    }

    // método que busca la coincidencia de una expresion regular separada por grupos
    public static Map<String, Integer> obtenerMapLocalizaciones(List<String> listaLocalizaciones) {
        Map<String, Integer> mapLocalizaciones = new HashMap<String, Integer>();
        
        final String regex = "(N)|(S)|(O)|(E)";
        final String[] coordenada = {"N", "S", "O", "E"};
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(listaLocalizaciones.toString());

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
               // si el grupo coincide con coordenada
                if (matcher.group(i)!=null && matcher.group(i).equalsIgnoreCase(coordenada[i - 1])) {
                    // si existe en el map la corrdenada me añades uno al valor
                    if (mapLocalizaciones.containsKey(coordenada[i - 1])) {
                        mapLocalizaciones.replace(coordenada[i - 1], mapLocalizaciones.get(coordenada[i - 1]) + 1);
                    } else {
                        // sino me creas la coordenada
                        mapLocalizaciones.put(coordenada[i - 1], 1);
                    }
                }
            }
        }
        return mapLocalizaciones;
    }
    
     public static void crearFicheroConMap(Map<String, Integer> lista, String nombreFichero) {
         // creo un String del map
         String datos="";
         for (Map.Entry<String, Integer> entry : lista.entrySet()) {
             Object key = entry.getKey();
             Object val = entry.getValue();
             datos+="%s - %s".formatted(key, val);
             datos+="\n";
         }
         // creo el archivo con el String dle map
        try {
            Files.write(Paths.get(nombreFichero), datos.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.out.println("Error creando el fichero");
        }
    } 

}
