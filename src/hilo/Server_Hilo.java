package hilo;

import java.io.IOException;
import java.net.Socket;

public class Server_Hilo extends Thread{

    public Socket s;
    Metodos_Servidor hiloMetodos;

    public Server_Hilo(Socket s) {
        this.s = s;
    }

    public void run () {
        try {
            hiloMetodos = new Metodos_Servidor(s);
            hiloMetodos.reciboPeticion();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
