import java.io.File;

public class App {
    public static void main(String[] args){
        try
        {
            File file = new File("src/Labirintos/LabirintosErradosParaTeste/SemEntrada.txt");
            LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo(file);
            Labirinto lab = new Labirinto(leitorDeArquivo.getLimites(), leitorDeArquivo.getLimites());
    
            for (int linha = 0; linha < lab.getAltura(); linha++) {
                leitorDeArquivo.leiaLinha();
                for (int coluna = 0; coluna < lab.getLargura(); coluna++) {
                     // dentro da classe : this.matris[row][col]
                     lab.setChar(linha, coluna, leitorDeArquivo.getCharNaColuna(coluna));
                }
            }
            lab.isVazioEmBordas();
            lab.verificadorEntrada();
            lab.verificadorSaidas();
            lab.caracterEstranho();
            lab.toString();
            System.out.println("Retorno: " + lab.getCharPosicao(0,10));
            //lab.toString();
        }
        catch(Exception erro){
            System.err.println(erro);
        }       
    }
}