
import java.io.IOException;



public class ServidorWeb {
    
    public static Estado estado = new Estado();
 
    public static void main(String[] args) throws IOException {
        new Thread(new WebDados(estado), "Atualizador").start();
        new Thread(new Servidor(8080, estado), "Servidor").start();
        
    }
    
    public static class Estado {
        
        public Boolean bloqueado = false;
        
    }
    
}
