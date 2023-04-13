/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package contadorLocalizaciones;

import java.util.List;
import java.util.Map;

/**
 *
 * @author eli
 */

/*
Abre el fichero en un programa Java y realiza la lectura de su contenido. 
Una vez cargado el contenido del fichero en memoria (en una lista de String), 
se debe obtener una nueva lista con todas las localizaciones del mismo (N, S, E, O).
Para ello, usa expresiones regulares para obtener de cada línea de texto (Localizacion: [N,S,O,E]) la localización  (N, S, E, O) 
y almacenarla en una nueva lista. Imprime esta nueva lista por pantalla.
A partir de esta lista hay que obtener una estructura Map donde se almacene, para cada localización (N, S, E, O), 
el número de veces que aparece en la lista. Muestra por pantalla el resultado.
Guarda en un fichero de texto llamado contadorLocalizaciones.txt, en la raíz de tu proyecto, 
la información contenida en la estructura Map, para ello recorre la estructura y ve escribiendo una línea por cada entrada del Map, siguiendo este ejemplo:
------------ Fichero contadorLocalizaciones ------------
N - 7
S - 5
O - 9
E - 12
----------------------------------------------------------------------------
Comprueba la creación del fichero y que el resultado es correcto. Prueba a cambiar algunos datos en el fichero localizaciones.txt y vuelve a ejecutar el programa. Los resultados deben ser acordes a los datos que pusiste en el fichero origen.
Modulariza la aplicación, crea una clase ServiciosFicheros con métodos encargados de lectura y escritura de datos en los ficheros. En la clase del main() puedes incluir el resto de métodos que necesites para realizar el ejercicio.
Sube el proyecto en formato ZIP.
 */
public class Ej7Aelisabet {

    public static void main(String[] args) {
        // Lista de localizaciones imprimida por pantalla a partir de un archivo donde busco a través de una expresion regular las localizaciones.
        List<String> listaLocalizaciones = ContarLocalizaciones.obtenerListaLocalizaciones("localizaciones.txt");
        System.out.println("Lista de localizaciones con la expresion regular 'Localizacion: [NSOE]'");
        ContarLocalizaciones.obtenerListaLocalizaciones("localizaciones.txt").forEach(System.out::println);
        
        //  estructura Map donde se almacene, para cada localización (N, S, E, O), el número de veces que aparece en la lista.Se muestra por pantalla el resultado.
        Map <String,Integer> mapLocalizaciones = ContarLocalizaciones.obtenerMapLocalizaciones(listaLocalizaciones);
       System.out.println("Map de localizaciones");
        for (Map.Entry<String, Integer> entry : mapLocalizaciones.entrySet()) {
            String coordenada = entry.getKey();
            Integer cantidad = entry.getValue();
            System.out.println("""
                               %s:%s
                               """.formatted(coordenada,cantidad));
        }
        // creamos un fichero con el mapLocalizaciones
        ContarLocalizaciones.crearFicheroConMap(mapLocalizaciones, "mapLocalizaciones.txt");
        // mostramos el contenido del fichero que acabamos de crear
        System.out.println("Lectura del archivo creado a partir del Map de localizaciones'");
        ContarLocalizaciones.leerArchivo("mapLocalizaciones.txt");
        
    }
}
