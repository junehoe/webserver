package webserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketIO {
    public static BufferedReader createSocketReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static PrintWriter createSocketWriter(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream());
    }

    public static String readFromInputStream(BufferedReader input) throws IOException {
        return input.readLine();
    }

    public static void writeToOutputStream(PrintWriter output, String data) {
        output.println(data);
    }
}
