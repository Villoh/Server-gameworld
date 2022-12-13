package Servidor_hilos;

import java.io.*;
import java.net.Socket;

public class Hilo_metodos {

    private Socket s;

    public void ReciboSocket(Socket s) {

        this.s = s;

    }

    public void reciboPeticion() throws IOException {

        DataInputStream dis = null;

        dis = new DataInputStream(s.getInputStream());

        int decision = dis.readInt();

        System.out.println("El cliente tomo la decision Nº " + decision);

        switch (decision) {

            case 1:

                PeliFavorita();

                break;

            case 2:

                ColorFav();

                break;

            case 3:

                Error();

                break;

            case 4:

                Fichero();

                System.out.println("Finalizo c:");

                break;

            default:

                Error();

                break;

        }

    }

    private void PeliFavorita() throws IOException {

        DataOutputStream dos = null;

        dos = new DataOutputStream(s.getOutputStream());

        dos.writeUTF("Paw Patrol en hielo");

    }

    private void ColorFav() throws IOException {

        DataOutputStream dos = null;

        dos = new DataOutputStream(s.getOutputStream());

        dos.writeUTF("Cocaina marron");

    }

    private void Error() throws IOException {

        DataOutputStream dos = null;

        dos = new DataOutputStream(s.getOutputStream());

        dos.writeUTF("Harry no deberia ser el protagonista :(");

    }

    private void Fichero() throws IOException {

        FileOutputStream in = null;
        InputStream is = null;
        File file = new File("C:\\Users\\jesuf\\OneDrive\\Escritorio\\Estudios_servidor_cliente\\Regalo.png");
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[1024];


        is = new FileInputStream(file);

        OutputStream out = s.getOutputStream();

        int count;
        while ((count = is.read(bytes)) > 0) {
            out.write(bytes, 0, count);
            System.out.println("Sigo en el metodo");
        }


    }

}
