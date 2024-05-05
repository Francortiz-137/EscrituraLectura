package desafio;

import java.io.*;
import java.util.ArrayList;

public class Main {

    private static final ArrayList<String> listaPalabras = new ArrayList<String>();
    public static void main(String[] args) {
        listaPalabras.add("Perro");
        listaPalabras.add("Gato");
        listaPalabras.add("Juan");
        listaPalabras.add("Daniel");
        listaPalabras.add("Juan");
        listaPalabras.add("Gato");
        listaPalabras.add("Perro");
        listaPalabras.add("Camila");
        listaPalabras.add("Daniel");
        listaPalabras.add("Camila");

        String directorio = "directorio";
        String fichero = "palabras.txt";
        File archivo = crearArchivo(directorio,fichero);
        escribirEnArchivo(archivo);
        buscarTexto("src/"+directorio+File.separator+fichero,"Juan");
    }


    public static File crearArchivo(String directorio, String fichero) {
        // 1. Crear método crearArchivo(directorio, fichero)
        File archivo = new File("src/" + directorio + File.separator + fichero);

        // 2. Validar que el nombre del fichero termine con ".txt"
        if (!fichero.endsWith(".txt")) {
            System.out.println("El nombre del fichero debe terminar con .txt");
            return null;
        }

        // 3. Validar que el directorio no exista dentro del programa
        File directorioFichero = new File("src/" + directorio);
        if (directorioFichero.exists()) {
            if (directorioFichero.isDirectory()) {
                // Crear el archivo si el directorio existe
                try {
                    archivo.createNewFile();
                    System.out.println("Archivo creado exitosamente");
                } catch (IOException e) {
                    System.out.println("Error al crear el archivo: " + e.getMessage());
                }
            } else {
                System.out.println("El directorio indicado no es un directorio válido");
            }
        } else {
            // Crear el directorio y el archivo si no existe
            try {
                directorioFichero.mkdirs();
                archivo.createNewFile();
                escribirEnArchivo(archivo);
                System.out.println("Directorio y archivo creados exitosamente");
            } catch (IOException e) {
                System.out.println("Error al crear directorio y archivo: " + e.getMessage());
            }
        }
        return archivo;
    }

    private static void escribirEnArchivo(File archivo) {
        // 4. Escribir en el archivo con un salto de línea
        try (FileWriter escritor = new FileWriter(archivo);
             PrintWriter impresora = new PrintWriter(escritor)) {
            for (String palabra : listaPalabras) {
                impresora.println(palabra);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void buscarTexto(String nombreFichero, String texto) {
        // 5. Crear método buscarTexto(nombreFichero, texto)
        File archivo = new File(nombreFichero);

        // 6. Validar que el fichero exista
        if (!archivo.exists()) {
            System.out.println("El fichero ingresado no existe");
            return;
        }

        // 7. Buscar el texto ingresado dentro del archivo
        int contador = 0;
        try (FileReader lector = new FileReader(archivo);
             BufferedReader lectorLineas = new BufferedReader(lector)) {
            String linea;
            while ((linea = lectorLineas.readLine()) != null) {
                if (linea.contains(texto)) {
                    contador++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // 8. Mostrar la cantidad de veces que la palabra se encuentra
        System.out.println("Cantidad de repeticiones del texto -> " + contador);
    }
}
