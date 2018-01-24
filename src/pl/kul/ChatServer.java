package pl.kul;

import java.net.*;
import java.io.*;

public class ChatServer {
    private static Socket socket = null;
    private static ServerSocket server = null;
    private static DataInputStream streamIn = null;

    public static void run() {
        try {
            server = new ServerSocket(8080);
            System.out.println("Serwer wzstartował " + server);
            System.out.println("Oczekiwanie na klienta");
            socket = server.accept();
            System.out.println("Klient akceptuje " + socket);
            open();
            boolean end = false;
            while (!end) {
                try {
                    String line = streamIn.readUTF();
                    System.out.println(line);
                    end = line.equals("end");
                } catch (IOException ioe) {
                    end = true;
                }
            }
            close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    private static void close() throws IOException {
        if (socket != null) socket.close();
        if (streamIn != null) streamIn.close();
    }

    public static void main(String args[]) {
        ChatServer.run();

    }
}