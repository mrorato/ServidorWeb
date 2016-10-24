import java.util.Random;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.net.URL;
import java.net.URLConnection;


public class WebDados implements Runnable{

    private ServidorWeb.Estado estado;
    private Random random = new Random(System.currentTimeMillis());
    static  String nome, dia, tempo, maxima, minima, sigla;
    
    public WebDados(ServidorWeb.Estado estado) {
        this.estado = estado;
    }
    public WebDados() {
        
    }
    


    @Override
    public void run() {
        while(true){
            synchronized(estado) {
              estado.bloqueado = true;
                System.out.println("Bloqueado");
            }
              try {
                  
                    int i;
                    String codigo[] = new String [5];
                    codigo[0] = "4599";
                    codigo[1] = "5070";
                    codigo[2] = "349";
                    codigo[3] = "4827";
                    codigo[4] = "5565";
                    Random gerador = new Random();
                    i = gerador.nextInt(5);
                    URL url = new URL("http://servicos.cptec.inpe.br/XML/cidade/"+ codigo[i] +"/previsao.xml");
                    URLConnection conn = url.openConnection();


                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(conn.getInputStream());


                    doc.getDocumentElement().normalize();

                    NodeList nList = doc.getElementsByTagName("cidade");

                    System.out.println("----------------------------");

                    for (int temp = 0; temp < nList.getLength(); temp++) {

                        Node nNode = nList.item(temp);


                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;

                            nome  = eElement.getElementsByTagName("nome").item(0).getTextContent();
                            dia   = eElement.getElementsByTagName("dia").item(0).getTextContent();
                            tempo = eElement.getElementsByTagName("tempo").item(0).getTextContent();
                            maxima = eElement.getElementsByTagName("maxima").item(0).getTextContent();
                            minima = eElement.getElementsByTagName("minima").item(0).getTextContent();


                            System.out.println("Nome : " + nome);
                            System.out.println("dia : " + dia);
                            sigla = tempo;
                            if(tempo.intern() == "ec"){
                              tempo = "Encoberto com Chuvas Isoladas";
                            }
                            if(tempo.intern() == "ci"){
                              tempo = "Chuvas Isoladas";
                            }
                            if(tempo.intern() == "c"){
                              tempo = "Chuvas";
                            }
                            if(tempo.intern() == "in"){
                              tempo = "Instável";
                            }
                            if(tempo.intern() == "pp"){
                              tempo = "Poss. de Pancadas de Chuva";
                            }
                            if(tempo.intern() == "cm"){
                              tempo = "Chuvas pela Manhã";
                            }
                            if(tempo.intern() == "cn"){
                              tempo = "Chuvas a Noite";
                            }
                            if(tempo.intern() == "pt"){
                              tempo = "Pancadas de Chuva a Tarde";
                            }
                            if(tempo.intern() == "pm"){
                              tempo = "Pancadas de Chuva pela Manha";
                            }
                            if(tempo.intern() == "np"){
                              tempo = "Nublado e Pancadas de Chuva";
                            }
                            if(tempo.intern() == "pc"){
                              tempo = "Pancadas de Chuva";
                            }
                            if(tempo.intern() == "pn"){
                              tempo = "Parcialmente Nublado";
                            }
                            if(tempo.intern() == "cv"){
                              tempo = "Chuvisco";
                            }
                            if(tempo.intern() == "ch"){
                              tempo = "Chuvoso";
                            }
                            if(tempo.intern() == "t"){
                              tempo = "Tempestade";
                            }
                            if(tempo.intern() == "ps"){
                              tempo = "Predomínio de Sol";
                            }
                            if(tempo.intern() == "e"){
                              tempo = "Encoberto";
                            }
                            if(tempo.intern() == "n"){
                              tempo = "Nublado";
                            }
                            if(tempo.intern() == "cl"){
                              tempo = "Céu Claro";
                            }
                            if(tempo.intern() == "g"){
                              tempo = "Geada";
                            }
                            if(tempo.intern() == "ne"){
                              tempo = "Neve";
                            }
                            if(tempo.intern() == "nd"){
                              tempo = "Não Definido";
                            }
                            if(tempo.intern() == "pnt"){
                              tempo = "Pancadas de Chuva a Noite";
                            }
                            if(tempo.intern() == "psc"){
                              tempo = "Possibilidade de Chuva";
                            }
                            if(tempo.intern() == "pcm"){
                              tempo = "Possibilidade de Chuva pela Manhã";
                            }
                            if(tempo.intern() == "pct"){
                              tempo = "Possibilidade de Chuva pela Tarde";
                            }
                            if(tempo.intern() == "pcn"){
                              tempo = "Possibilidade de Chuva a Noite";
                            }
                            if(tempo.intern() == "npt"){
                              tempo = "Nublado com Pancadas a Tarde";
                            }
                            if(tempo.intern() == "npn"){
                              tempo = "Nublado com Pancadas a Noite";
                            }
                            if(tempo.intern() == "ncn"){
                              tempo = "Nublado com Possibilidade de Chuva a Noite";
                            }
                            if(tempo.intern() == "nct"){
                              tempo = "Nublado com Possibilidade de Chuva a Tarde";
                            }
                            if(tempo.intern() == "ncm"){
                              tempo = "Nublado com Possibilidade de Chuva pela Manha";
                            }
                            if(tempo.intern() == "npm"){
                              tempo = "Nublado com Pancadas pela Manha";
                            }
                            if(tempo.intern() == "npp"){
                              tempo = "Nublado com Possibilidade de Chuva";
                            }
                            if(tempo.intern() == "vn"){
                              tempo = "Variacão de Nebulosidade";
                            }
                            if(tempo.intern() == "ct"){
                              tempo = "Chuva a Tarde";
                            }
                            if(tempo.intern() == "ppn"){
                              tempo = "Possibilidade de Pancadas de Chuva a Noite";
                            }
                            if(tempo.intern() == "ppt"){
                              tempo = "Possibilidade de Pancadas de Chuva a Tarde";
                            }
                            if(tempo.intern() == "ppm"){
                              tempo = "Possibilidade de Pancadas de Chuva pela Manha";
                            }
                            System.out.println("tempo : " + tempo);
                            System.out.println("maximo : " + maxima);
                            System.out.println("minima : " + minima);
                            System.out.println("----------------------------");
                        }
                    } 
                    
                    Thread.sleep(5*1000);
                    synchronized(estado) {
                        estado.bloqueado = false;
                        estado.notifyAll();
                        System.out.println("Desbloqueou");
                    }
                } catch (Exception e) {
                 e.printStackTrace();
                }
                     
            try {
                System.out.println("entrou no sleep");

                Thread.sleep(30*1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public  String getNome() {
        return nome;
    }

    public static String getDia() {
        return dia;
    }

    public static String getTempo() {
        return tempo;
    }

    public static String getMaxima() {
        return maxima;
    }

    public static String getMinima() {
        return minima;
    }

    public static String getSigla() {
        return sigla;
    }
    

   
}
    
    
   

