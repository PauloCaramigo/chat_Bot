package chat2;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ChatClient {
   
    static BufferedReader entrada;
    static PrintWriter saida;
    static String nome;
    

    public ChatClient() {
        new Janela();
    }

    public void iniciarChat() throws Exception{
        String enderecoIP =  InetAddress.getLocalHost().getHostAddress();
        Socket usuario = new Socket(enderecoIP,9086);
        
        entrada = new BufferedReader(new InputStreamReader(usuario.getInputStream()));
        saida = new PrintWriter(usuario.getOutputStream(),true);
        
        while(true){
            String msg = entrada.readLine();
            
            if (msg.equals("NOME_REQUERIDO")) {
                nome = JOptionPane.showInputDialog(Janela.chatJanela,"Nome de usuario: ","Insira um nome",JOptionPane.PLAIN_MESSAGE);
                saida.println(nome);             
            }else if (msg.equals("NOME_EXISTENTE")) {
                nome = JOptionPane.showInputDialog(Janela.chatJanela,"Nome de usuario: ","Nome duplicado",JOptionPane.WARNING_MESSAGE);
                saida.println(nome);             
            }else if (msg.startsWith("NOME_ACEITO")) {
                Janela.chatNovaMensagem.setEditable(true);
                Janela.chatUsuarioLogado.setText("Voc� esta logado como: " + msg.substring(11));
                break;
            }
        }
        
        
        while(true) {
        	String[] myMsg = entrada.readLine().split(":", 2);
        	
        	if(myMsg[0].contains(nome)) {
                Janela.chatMensagem.append("Eu: " + myMsg[1] + "\n");
        	} else {
            Janela.chatMensagem.append(myMsg[0] + ":"  + myMsg[1] + "\n");
        	}
        }
    }
  
    
    public static void main(String[] args) throws Exception {
        ChatClient chat = new ChatClient();
        chat.iniciarChat();
    }
}
