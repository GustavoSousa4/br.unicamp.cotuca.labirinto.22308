import java.io.File;

public class App {
    public static void main(String[] args){
        try
        {
            File file = new File("src/Labirintos/LabirintosCorretosParaTeste/Teste6.txt");
            LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo(file);
            Labirinto lab = new Labirinto(leitorDeArquivo.getLimites(), leitorDeArquivo.getLimites());
    
            for (int linha = 0; linha < lab.getAltura(); linha++) {
                leitorDeArquivo.leiaLinha();
                for (int coluna = 0; coluna < lab.getLargura(); coluna++) {
                     // dentro da classe : this.matris[row][col]
                     lab.setChar(linha, coluna, leitorDeArquivo.getCharNaColuna(coluna));
                }
            }

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
            System.out.println(lab.toString());
            
        }
        catch(Exception erro){
            System.err.println(erro);
        }       
    }
}