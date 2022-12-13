package Servidor_hilos;

import Objetos.Regalo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server_Hilo extends Thread{

    public Socket s;

    private DataInputStream entrada;

    private ObjectOutputStream salidaObjeto;

    Hilo_metodos hiloMetodos;

    public Server_Hilo(Socket s) {
        this.s = s;
    }

    public void run () {

        try {

            entrada = new DataInputStream(s.getInputStream());

            //int cadena = entrada.readInt();

            /**if (cadena.equalsIgnoreCase("Jesu")) {

                System.out.println("Le tendre que devolver algo");

                //entrada.close();

                salidaObjeto = new ObjectOutputStream(s.getOutputStream());

                Regalo r = new Regalo();

                r.nombre = "192.168.33.34 esta es tu IP miguel C:";

                salidaObjeto.writeObject(r);

            }**/

            hiloMetodos = new Hilo_metodos();

            hiloMetodos.ReciboSocket(s);
            hiloMetodos.reciboPeticion();

        } catch (IOException e) {

            System.err.println(e.getMessage());
        }

    }
}
