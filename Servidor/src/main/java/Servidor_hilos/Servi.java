package Servidor_hilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servi {

    public static void main(String[] args) {

        ServerSocket ss = null;

        Socket s = null;

        final int puerto = 5000;

        try {

            ss = new ServerSocket(puerto);

            System.out.println("Servidor iniciado");

            while (true) {

                s = ss.accept(); //Entro un nuevo cliente

                Server_Hilo sh = new Server_Hilo(s);

                sh.start();

            }

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }
}
