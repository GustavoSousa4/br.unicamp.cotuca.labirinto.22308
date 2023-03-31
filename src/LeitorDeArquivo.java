import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LeitorDeArquivo {
    private BufferedReader bufferedReader;
    private char[] linha;
    public LeitorDeArquivo(File file) throws Exception{
        this.bufferedReader = new BufferedReader(new FileReader(file));
    }

    public char[] leiaLinha() throws Exception{

        this.linha = this.bufferedReader.readLine().toCharArray();
        return this.linha;
    }
    public char getCharNaColuna(int coluna) throws Exception{
        if(coluna < 0)
            throw new Exception("Valor invalido");
            
        return this.linha[coluna];
    }
    public int getLimites() throws Exception{
        return Integer.parseInt(bufferedReader.readLine());
    }

    @Override
    public String toString(){
        return "";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;
        LeitorDeArquivo leitorDeArquivo = (LeitorDeArquivo) obj;
        for(int i = 0; i < linha.length; i++){
            if(this.linha[i] != leitorDeArquivo.linha[i])
                return false;
        }
        if(this.bufferedReader != leitorDeArquivo.bufferedReader)
            return false;
        return true;
    }
    
    @Override
    public int hashCode(){
        int hash = 5;
        hash = hash * 7 + new BufferedReader(this.bufferedReader).hashCode();
        for(int i = 0; i < this.linha.length; i++){
            hash = hash * 11 + Character.valueOf(linha[i]).hashCode();
        }
        if(hash <0)
            hash= -hash;
        return hash;
    }
}
