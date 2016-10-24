import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements Runnable {
    
    protected int          serverPort;
    protected ServerSocket serverSocket   = null;
    protected Thread       estaExecutando = null;
    private ServidorWeb.Estado estado;
    int i = 0;
    private Object ok = new Object();
    public Servidor(int port, ServidorWeb.Estado estado) {
        this.serverPort = port;
        this.estado = estado;
        
    }

   
    
     @Override
    public void run() {
        
            //abrirServerSocket();
            
            abrirServerSocket();
                while (true){
                Socket clientSocket = null;
                    try {
                        
                         i++;
                         clientSocket = this.serverSocket.accept();
                        
                         System.out.println("SERVIDOR: CLIENTE --->  " +clientSocket);
                         
                         Thread log = new Thread(new GerarLog(clientSocket, ok), "cliente"+i);
                         log.start();
                         
                         new Thread(new WebServer(clientSocket, estado), "Cliente"+i).start();
                      
                    } catch (Exception ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    } 
               }
    }
    private void dormir(){
            try {
                wait();
//                Thread.sleep((int) (rnd.nextDouble() * 100.0));
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
             

        private void abrirServerSocket() {
            System.out.println("###--------> Servidor abrirServerSocket!");
            try {
                this.serverSocket = new ServerSocket(this.serverPort);
                System.out.println("###--------> Porta 8080 aberta!" + serverSocket);
                // Aguarda alguém se conectar. A execução do servidor
                // fica bloqueada na chamada do método accept da classe
                // ServerSocket. Quando alguém se conectar ao servidor, o
                // método desbloqueia e retorna com um objeto da classe
                // Socket, que é uma porta da comunicação.
                System.out.println("###--------> Aguardando conexão do cliente...");
            } catch (IOException e) {
                throw new RuntimeException("###--------> Nao conseguiu abrir a porta 8080!", e);
            }
        }
    }


