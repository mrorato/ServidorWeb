
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements Runnable {

    protected int serverPort;
    protected ServerSocket serverSocket = null;
    private Principal.Estado estado;
    int i = 0;
    private Object ok = new Object();

    public Servidor(int port, Principal.Estado estado) {
        this.serverPort = port;
        this.estado = estado;
    }

    @Override
    public void run() {
        abrirServerSocket();
        while (true) {
            Socket clientSocket = null;
            try {
                i++;
                clientSocket = this.serverSocket.accept();
                System.out.println("----------------------------");
                System.out.println("SERVIDOR: NOVA REQUISIÇÃO " + clientSocket);

                Thread log = new Thread(new GerarLog(clientSocket, ok), "CLIENTE " + i);
                log.start();

                new Thread(new ExibePagina(clientSocket, estado), "CLIENTE " + i).start();

            } catch (Exception ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void abrirServerSocket() {
        System.out.println("aBERTO SERVERSOCKET");
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            System.out.println("###--------> Porta 8080 aberta!" + serverSocket);
            System.out.println("###--------> Aguardando conexão do cliente...");
        } catch (IOException e) {
            throw new RuntimeException("###--------> Nao conseguiu abrir a porta 8080!", e);
        }
    }
}
