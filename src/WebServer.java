
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WebServer implements Runnable{

    protected Socket clientSocket = null;
    private byte[] b = new byte[2048];
    private ServidorWeb.Estado estado;
    
    
    public WebServer(Socket clientSocket, ServidorWeb.Estado estado) {
        this.clientSocket = clientSocket;
        this.estado = estado;
    }
//    String c = Thread.currentThread().getName();
//    System.out.prinln("Cliente webserver-> " + c);
    public void run(){
       synchronized(estado){
            try {
                 
                while(estado.bloqueado){
                    String c = Thread.currentThread().getName();
                    System.out.println("PARADO AGUARDANDO ATUALIZAÇÃO: " + c);
                    estado.wait();
                }
                

            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       String c = Thread.currentThread().getName();
        System.out.println("Liberado: " + c);
        try {
            int nbytes = clientSocket.getInputStream().read(b);
            if (nbytes <= 0) {
                try{
                    clientSocket.close();
                }catch (Exception e){
                }
            }
            String str = new String(b, 0, nbytes);
            //System.out.println(str);
            StringTokenizer st = new StringTokenizer(str);
            String metodo = st.nextToken();
            String documento = st.nextToken();
            String versao = st.nextToken();
            //System.out.println("METODO: " + metodo);
            //System.out.println("DOCUMENTO: " + documento);
            
            if (metodo.equals("GET")){
                documento.replace('\\', '/');
                if (documento.equals("/")){
                    documento = "index.html";
                }
                try{
                    File f = new File("./pagina/" + documento);
                    FileInputStream fin = new FileInputStream(f);
                    byte[] b_arquivo = new byte[(int)fin.getChannel().size()];
                    fin.read(b_arquivo);
                    fin.close();        
                    String testando = new String(b_arquivo, "UTF-8").replace("#$nome", new WebDados().getNome()).replace("#$dia", new WebDados().getDia()).replace("#$tempo", new WebDados().getTempo()).replace("#$sigla", new WebDados().getSigla()).replace("#$max", new WebDados().getMaxima()).replace("#$min", new WebDados().getMinima());
                    clientSocket.getOutputStream().write(testando.getBytes("UTF-8"));
                }catch (Exception e){
                    e.printStackTrace();
                }  
            }
            try{
                clientSocket.close();
            }catch(Exception e){    
            }
        }catch(Exception q){
            q.printStackTrace();
        }
    }
    
}

    