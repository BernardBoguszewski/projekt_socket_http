package pl.kul;

import java.net.*;
import java.io.*;

public class ChatClient {
    private static Socket socket = null;
    private static DataInputStream dataInputStream = null;
    private static DataOutputStream dataOutputStream = null;

    public static void run() {
        System.out.println("Czekanie na nawiązanie połączenia...");
        try {
            socket = new Socket("localhost", 8080);
            System.out.println("Połączono z socketem: " + socket);
            dataInputStream = new DataInputStream(System.in);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println();
        String text = "";
        System.out.println("Wpisuj linie i zatwierdzaj enterem, kończysz wpisując słowo: end");
        while (!text.equals("end")) {
            try {
                text = dataInputStream.readLine();
                dataOutputStream.writeUTF(text);
                dataOutputStream.flush();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        stopServer();
    }

    private static void stopServer() {
        try {
            if (dataInputStream != null) dataInputStream.close();
            if (dataOutputStream != null) dataOutputStream.close();
            if (socket != null) socket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ChatClient.run();
    }
}