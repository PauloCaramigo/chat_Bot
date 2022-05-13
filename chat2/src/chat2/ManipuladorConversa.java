package chat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ManipuladorConversa extends Thread {
    Socket usuarioConversa;
    BufferedReader entrada;
    PrintWriter saida;
    String nome;
    String msg;
    
    public ManipuladorConversa(Socket usuarioConversa) throws IOException{
        this.usuarioConversa=usuarioConversa;
    }
    
    
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(usuarioConversa.getInputStream()));
            saida = new PrintWriter(usuarioConversa.getOutputStream(), true);
            
            saida.println("NOME_REQUERIDO");
            
            while (true) {
            	nome = entrada.readLine();
            	
            	if (nome.isEmpty()) {
                    saida.println("NOME_REQUERIDO");
                    System.out.println(nome.isEmpty());
                } else if (ChatServer.listaUsuarios.contains(nome)){
                    saida.println("NOME_EXISTENTE");
                } else {
                	saida.println("NOME_ACEITO" + nome);
                	ChatServer.listaUsuarios.add(nome);
                    ChatServer.printWriters.add(saida);
                    break;
                }
            }
            
            
            while (true) {                
                msg = entrada.readLine();
                
                if (msg == null || msg == "") {
                    return;
                } else if (msg.contains("DESCONNECTibjs8CEUrCQv-")) {
                	desconnect(msg.replace("DESCONNECTibjs8CEUrCQv-", ""));
                	
                	for (PrintWriter writer : ChatServer.printWriters){
	                    writer.println("\n" + nome + " se desconectou da conversa.\n");
	                }
                	
                } else {
	                for (PrintWriter writer : ChatServer.printWriters){
	                    writer.println(nome + ": " + msg);
	                }
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erro no servidor: "+ e.getMessage());
            e.printStackTrace();
        }
    }
    public void desconnect(String nome) {
    	ChatServer.listaUsuarios.remove(ChatServer.listaUsuarios.indexOf(nome));
    }
}
