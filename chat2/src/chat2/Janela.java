package chat2;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Janela {
    static JFrame chatJanela = new JFrame("Chat");
    static JTextArea chatMensagem= new JTextArea(22,40);
    static JTextField chatNovaMensagem = new JTextField(47);
    static JButton chatBotaoEnviar = new JButton("Enviar");
    static JLabel chatUsuarioLogado = new JLabel("");
    Color fundo = new Color(61, 61, 61);
    Color branco = Color.white;
    Font fonteLista= new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 20);
    Font fonteHistorico= new Font("Calibri", Font.LAYOUT_RIGHT_TO_LEFT, 18);
    
    public Janela(){
        
        chatJanela.setLayout(new FlowLayout());
        chatJanela.add(chatUsuarioLogado);
        chatJanela.add(new JScrollPane(chatMensagem));
        chatJanela.add(chatNovaMensagem);
        chatJanela.add(chatBotaoEnviar);
        
        
        chatJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatJanela.setSize(650,608);
        chatJanela.setVisible(true);
        chatJanela.setLocationRelativeTo(null);
        chatJanela.setResizable(false);
        chatJanela.getContentPane().setBackground(fundo);
        
        chatMensagem.setEditable(false);
        chatMensagem.setBackground(fundo);
        chatMensagem.setForeground(branco);
        chatMensagem.setFont(fonteHistorico);
        
        
        
        chatNovaMensagem.setEditable(false);
        chatNovaMensagem.setBackground(new Color(78, 78, 78));
        chatNovaMensagem.setForeground(branco);
        
        chatBotaoEnviar.setBackground(Color.BLUE);
        chatBotaoEnviar.setForeground(branco);
        
        chatBotaoEnviar.addActionListener(new Listener());
        chatNovaMensagem.addActionListener(new Listener());
        
        chatJanela.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
            		ChatClient.saida.println("DESCONNECTibjs8CEUrCQv-"+ChatClient.nome);
                    System.exit(0);
            }
        });
    }
    
    
}

class Listener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ChatClient.saida.println(Janela.chatNovaMensagem.getText());
        Janela.chatNovaMensagem.setText("");
    }
    
}