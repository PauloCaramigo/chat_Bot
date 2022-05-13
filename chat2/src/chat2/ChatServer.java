package chat2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    static ArrayList<String> listaUsuarios = new ArrayList<String>();
    static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

    public static void main(String[] args) throws IOException {
        System.out.println("Aguardando novo usuario...");
        ServerSocket ss = new ServerSocket(9086);
        
        while(true){
            Socket usuario = ss.accept();
            System.out.println("Usuario conectado!");
            
            ManipuladorConversa conversa = new ManipuladorConversa(usuario);
            conversa.start();
        }
    }
    
}


