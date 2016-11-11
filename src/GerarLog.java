
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.io.PrintWriter;
import java.net.Socket;

public class GerarLog implements Runnable {

    private Socket clientSocket;
    private Object ok;

    GerarLog(Socket clientSocket, Object ok) {
        this.clientSocket = clientSocket;
        this.ok = ok;
    }

    public void run() {
        String c = Thread.currentThread().getName();
        System.out.println("AGUARDANDO PARA GRAVAR LOG -> " + c);
        synchronized (ok) {
            File arquivoTxt = new File("./logs/" + "log_programa.txt");

            if (!arquivoTxt.exists()) {
                try {
                    //Cria o arquivo
                    arquivoTxt.createNewFile();
                    System.out.println("###--------> LOG ATUALIZADO: " + c);

                    //salva o arquivo
                    FileWriter writer = new FileWriter(arquivoTxt);
                    writer.write("\n\n");
                    writer.write("IP:" + clientSocket.getInetAddress() + "\n");
                    writer.write("  DATA:" + new Date() + "\n");
                    writer.write("\n---------------------\n");
                    writer.close();
                    System.out.println("LOG GRAVADO PARA -> " + c + " -> IP:" + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    System.out.println("INICIOU LOG -> " + c);
                    FileReader reader = new FileReader("./logs/" + "log_programa.txt");
                    BufferedReader br = new BufferedReader(reader);
                    System.out.println("LENDO LOG -> " + c);
                    String linha = br.readLine();
                    //PrintWriter writer = new PrintWriter (arquivoTxt);
                    FileWriter txt = new FileWriter(arquivoTxt, true);
                    PrintWriter writer = new PrintWriter(txt);

                    while (linha != null) {
                        //writer.printf(linha+"%n");
                        br.readLine();
                        linha = br.readLine();
                    }

                    br.close();
                    reader.close();
                    System.out.println("FINALIZADA LEITURA -> " + c);
                    writer.printf("%n%n");
                    writer.printf("IP:" + clientSocket.getInetAddress() + "   DATA:" + new Date() + "%n");
                    writer.printf("---------------------%n");

                    writer.close();
                    System.out.println("LOG GRAVADO PARA -> " + c + " -> IP:" + clientSocket.getInetAddress());
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        }
    }
}
