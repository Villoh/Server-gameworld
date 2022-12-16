package hilo;

import java.io.*;
import java.net.Socket;

public class Metodos_Servidor {

    private final Socket s;
    public Metodos_Servidor(Socket s){
          this.s = s;
    }

    /**
     * Recibe una peticion del cliente como una variable int y el nombre del fichero que va a descargar/subir mediante el stream que comparten en el socket el cliente y el servidor.
     */
    public void reciboPeticion() throws IOException {

        try {
            DataInputStream dis = new DataInputStream(s.getInputStream());

            int decision = dis.readInt();

            String nombreFichero = dis.readUTF();

            System.out.println("El cliente tomo la decision Nº " + decision);

            switch (decision) {
                case 1 -> {
                    mandaFichero(nombreFichero);
                    System.out.println("Finalizo c:");
                }
                case 2 -> {
                    recibeFichero(nombreFichero);
                    System.out.println("Finalizo c:");
                }
                default -> System.err.println("La petición no existe o algo ha salido mal");
            }
        } catch (IOException e) {
            System.err.println("Error de lectura/escritura: " + e);
        }
    }

    /**
     * Obtiene el fichero del stream de la aplicación y lo escribe en el servidor.
     * @param nombreFichero Nombre del fichero que va a subir al servidor.
     */
    private void recibeFichero(String nombreFichero) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = s.getInputStream();
            out = new FileOutputStream("E:\\Programas\\gameworld\\games\\" + nombreFichero);
            byte[] bytes = new byte[1024];

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            out.close();
            in.close();
            s.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Fichero no encontrado" + fnfe);
        } catch (IOException ioe) {
            System.err.println("El archivo no se ha podido escribir: " + ioe);
        }
    }

    /**
     *  Escribe el fichero en el stream para que el cliente pueda descargar el archivo especificado.
     * @param nombreFichero Nombre del fichero que va a descargar del servidor.
     */
    private void mandaFichero(String nombreFichero) {
        InputStream is = null;
        File file = new File("E:\\Programas\\gameworld\\games\\" + nombreFichero);
        // Get the size of the file
        byte[] bytes = new byte[1024];

        try {
            is = new FileInputStream(file);
            OutputStream out = s.getOutputStream();
            int count;
            while ((count = is.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            is.close();
            out.close();
            s.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Fichero no encontrado" + fnfe);
        } catch (IOException ioe) {
            System.err.println("El archivo no se ha podido escribir: " + ioe);
        }
    }

}
