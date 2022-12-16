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
            hiloMetodos = new Metodos_Servidor(s); //Instancio un nuevo hilo
            hiloMetodos.reciboPeticion(); //Llamo a reciboPetición para comprobar la petición realizada por el cliente
        } catch (IOException e) {
            System.err.println("Error de lectura/escritura: " + e);
        }

    }
}
