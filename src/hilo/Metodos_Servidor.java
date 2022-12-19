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

        DataInputStream dis = new DataInputStream(s.getInputStream()); //Crea un DataInputStream mediante el InputStream del socket, para poder recibir las peticiones (datos) del cliente

        String pathGlobal = "";

        int decision = dis.readInt(); //Lee la 1a petición como entero

        String nombreFichero = dis.readUTF(); //Lee la 2a petición como un string

        System.out.println("El cliente tomo la decision Nº " + decision);

        //Basándonos en el entero recibido se realiza una acción u otra.
        switch (decision) {
            case 1 -> {
                pathGlobal = "/lib/gameworld/games/";
                mandaFichero(pathGlobal, nombreFichero);
                System.out.println("Fichero mandado!");
            }
            case 2 -> {
                pathGlobal = "/lib/gameworld/games/";
                recibeFichero(pathGlobal, nombreFichero);
                System.out.println("Fichero subido!");
            }
            case 3 -> { //Imágenes (Portadas)
                pathGlobal = "/lib/gameworld/portadas/";
                mandaFichero(pathGlobal, nombreFichero);
            }
            case 4 -> { //Imágenes (Portadas)
                pathGlobal = "/lib/gameworld/portadas/";
                recibeFichero(pathGlobal, nombreFichero);
            }
            default -> System.err.println("La petición no existe o algo ha salido mal");
        }

    }

    /**
     * Obtiene el fichero del stream de la aplicación y lo escribe en el servidor.
     * @param nombreFichero Nombre del fichero que va a subir al servidor.
     */
    private void recibeFichero(String pathGlobal, String nombreFichero) throws IOException{
        InputStream in;
        OutputStream out;
        try {
            in = s.getInputStream(); //InputStream del socket para poder recibir los datos del fichero.
            out = new FileOutputStream(pathGlobal + nombreFichero); //Crea un FileOutputStream con la ruta deseada y el nombre del fichero indicado.
            byte[] bytes = new byte[1024]; //Array de bytes que se va a utilizar para manipular el archivo.

            //Bucle y contador para la escritura de los bytes en el stream con el directorio y archivo indicado.
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            //Cierro flujos.

            out.close();
            in.close();
            s.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Fichero no encontrado" + fnfe);
        }
    }


    /**
     *  Escribe el fichero en el stream para que el cliente pueda descargar el archivo especificado.
     * @param nombreFichero Nombre del fichero que va a descargar del servidor.
     */
    private void mandaFichero(String pathGlobal, String nombreFichero) throws IOException{
        InputStream is;
        File file;
        OutputStream out;
        // Get the size of the file
        byte[] bytes = new byte[1024];

        try {
            file = new File(pathGlobal + nombreFichero); //Fichero que va a mandar al stream del socket.
            is = new FileInputStream(file); //Stream creado para leer los bytes del archivo.
            out = s.getOutputStream(); //Stream del socket para escribir los bytes y que el cliente los pueda manipular.

            //Bucle y contador para la escritura de los bytes en el stream del socket.
            int count;
            while ((count = is.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            //Cierro flujos.
            is.close();
            out.close();
            s.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Fichero no encontrado" + fnfe);
        }
    }

}
