package main;

import hilo.Server_Hilo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor_Main {

    public static void main(String[] args) {

        Socket s;   //Socket del cliente que mande la petición.
        final int puerto = 5000; //Puerto por el que va a escuchar el servidor.

        //Intenta crar un ServerSocket
        try (ServerSocket ss = new ServerSocket(puerto)){

            System.out.println("Servidor iniciado");

            //Bucle infinito para mantenerse escuchando peticiones de clientes.
            while (true) {

                s = ss.accept(); //Entra un nuevo cliente.

                Server_Hilo sh = new Server_Hilo(s); //Instancia un nuevo hilo.

                sh.start(); //Comienza el hilo.

            }

        } catch (IOException e) {
            System.err.println("Error de lectura/escritura: " + e);
        }
    }
}
