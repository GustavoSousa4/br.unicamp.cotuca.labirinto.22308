import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args){        
        String arquivo = "", tipo = "";
        Scanner leia = new Scanner(System.in);;
        File file;
        Labirinto lab;
        LeitorDeArquivo leitorDeArquivo;
        String resposta = "";
        while(!resposta.toLowerCase().equals("n")){
            try
            {
                try{
                    try{
                        System.out.print("Labirintos: Certos ou Errados? ");
                        tipo = leia.nextLine();
                    }
                    catch(Exception er){
                        throw new Exception("Caminho não existente");
                    }
                    System.out.print("Digite o nome do Arquivo: ");
                    arquivo = leia.nextLine();
                    
                    file = new File("src/Labirintos/"+tipo+"/"+arquivo+".txt");
                }
                catch(Exception e){
                    throw new Exception("Nome de Arquivo não encontrado ou inexistente");
                }
                    leitorDeArquivo = new LeitorDeArquivo(file);
                    lab = new Labirinto(leitorDeArquivo.getLimites(), leitorDeArquivo.getLimites());
                try{
                    for (int linha = 0; linha < lab.getAltura(); linha++) {
                        leitorDeArquivo.leiaLinha();
                        for (int coluna = 0; coluna < lab.getLargura(); coluna++) {
                            lab.setChar(linha, coluna, leitorDeArquivo.getCharNaColuna(coluna));
                        }
                    }
                }
                catch(Exception e){throw new Exception("Número de linhas ou colunas informados incorretamente");}
                    
                lab.verificadorEntrada();
                lab.verificadorSaidas();
                lab.isVazioEmBordas();
                lab.caracterEstranho();
                    
                for(;;) {
                    if(!lab.getAtual().equals(lab.verificadorSaidas())) {
                        lab.adjacente(lab.getAtual());
                        lab.mover();
                    }
                        else break;
                }
                System.out.print(lab.toString());
                System.out.println(lab.inverteCaminho());
            }
            catch(Exception erro){
                System.err.println(erro.getMessage());
            }
            System.out.print("Deseja verificar mais labirintos? [S/N]");
            resposta = leia.nextLine();
        }       
        leia.close();
    }
}