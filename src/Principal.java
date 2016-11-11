
import java.io.IOException;



public class Principal {
    
    public static Estado estado = new Estado();
 
    public static void main(String[] args) throws IOException {
        new Thread(new BuscaDados(estado), "Atualizador").start();
        new Thread(new Servidor(8080, estado), "SERVIDOR").start();     
    }
    
    public static class Estado {
        public Boolean bloqueado = false;
    } 
}
