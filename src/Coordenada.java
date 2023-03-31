public class Coordenada {
    
    private final int LINHA;
    private final int COLUNA;
    
    public Coordenada(int linha, int coluna) throws Exception{
        if(linha < 0)
            throw new Exception("Valor de linha invalido");
        if(coluna < 0)
            throw new Exception("Valor de coluna invalido");
        
        this.LINHA = linha;
        this.COLUNA = coluna;
    }

    public int getLinha(){
        return this.LINHA;
    }

    public int getColuna(){
        return this.COLUNA;
    }

    @Override
    public String toString(){
        return "Linha(Y): " + this.LINHA + "\n" + "Colunha(X): " + this.COLUNA;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;
        Coordenada coordenada = (Coordenada) obj;
        if(this.LINHA != coordenada.LINHA)
            return false;
        if(this.COLUNA != coordenada.COLUNA)
            return false;
        
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 7;
        ret = ret * 11 + Integer.valueOf(this.LINHA).hashCode();
        ret = ret * 11 + Integer.valueOf(this.COLUNA).hashCode();
        if(ret < 0)
            ret = -ret;
        return ret;
    }
}