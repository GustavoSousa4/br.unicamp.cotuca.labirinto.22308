public class Labirinto{
    private char[][] matriz; 
    private final int ALTURA;
    private final int LARGURA;
    public Pilha<Coordenada> caminho;
    private Pilha<Fila<Coordenada>> possibilidade;
    private Fila<Coordenada> fila;
    private Pilha<Coordenada> inverso;

    public Labirinto(int altura, int largura) throws Exception {
        if(altura < 0)
            throw new Exception("Valor de linha invalido");
        if(largura < 0)
            throw new Exception("Valor de coluna invalido");
        this.ALTURA = altura;
        this.LARGURA = largura;
        this.matriz = new char [this.getAltura()][this.getLargura()];
        this.caminho = new Pilha<Coordenada>(this.ALTURA*this.LARGURA);
        this.fila = new Fila<Coordenada>(3);
        this.possibilidade = new Pilha<Fila<Coordenada>>(this.ALTURA*this.LARGURA);
        this.inverso = new Pilha<Coordenada>(this.ALTURA*this.LARGURA);
    }
    
    public int getAltura() {
        return this.ALTURA;
    }

    public int getLargura() {
        return this.LARGURA;
    }
    public char getCharPosicao(int linha, int coluna) throws Exception{
        //método que retorna char na posição solicitada
        if(linha < 0)
            throw new Exception("Erro ao recuperar linha, valor invalido");
        if(coluna < 0)
            throw new Exception("Erro ao recuperar coluna, valor invalido");
        return this.matriz[linha][coluna];
    }
    public void setChar(int linha, int coluna, Character elemento) throws Exception{
        //método para colocar o elemento dentro da matriz
        if(linha < 0)
            throw new Exception("Valor de linha de setChar invalido");
        if(coluna < 0)
            throw new Exception("Valor de coluna de setChar invalido");
        if(linha > this.getAltura())
            throw new Exception("Número de linhas superior ao do labirinto");
        if(coluna > this.getLargura())
            throw new Exception("Número de colunas superior ao do labirinto");    
        this.matriz[linha][coluna] = elemento;
    }
    
    public void verificadorEntrada() throws Exception
    {
        /*Verificador para analisar se a entrada do labirinto não está nas pontas ou se o número de entradas é superior a "1" ou igual a "0".
          Analisa também se a entradas está inacessível*/
        byte e = 0; 
        if(this.getCharPosicao(0, 0) == 'E'||this.getCharPosicao(this.getAltura() - 1, 0) == 'E' ||
           this.getCharPosicao(0, this.getAltura()-1) == 'E'||this.getCharPosicao(this.getAltura() - 1, this.getLargura()-1 ) == 'E')
                throw new Exception("Entada localizada em alguma das pontas");

            for(int i = 0; i < this.getAltura(); i++){
                for(int j = 0; j < this.getLargura(); j++){
                    if(this.getCharPosicao(i, j) == 'E'){
                        e++;            
                        this.caminho.guardeItem(new Coordenada(i,j));
                    }
                }
            }
            for(int i = 1; i < this.getAltura()-2; i++){
                for(int j = 1; j < this.getLargura()-2; j++){
                    if(this.getCharPosicao(i, j) == 'E')
                    throw new Exception("Entrada localizada dentro do labirinto");            
                }
            }
            if(e==0)
                  throw new Exception("Não foi encontrado nenhuma entrada");
            if(e>1)
                throw new Exception("A quantidade de entradas é superior a 1");
    }
    public Coordenada verificadorSaidas()throws Exception{
        /*Verificador para analisar se a saída do labirinto não está nas pontas ou se o número de saídas é superior a "1" ou igual a "0".
          Analisa também se a saída está inacessível*/
        byte s = 0;
        Coordenada saida = new Coordenada(0,0);
        if(this.getCharPosicao(0, 0) == 'S'||this.getCharPosicao(this.getAltura() - 1, 0) == 'S' ||
           this.getCharPosicao(0, this.getAltura()-1) == 'S'||this.getCharPosicao(this.getAltura() - 1, this.getLargura()-1 ) == 'S')
            throw new Exception("Saida localizada em alguma das pontas");
        for(int i = 0; i < this.getAltura(); i++){
            for(int j = 0; j < this.getLargura(); j++){
                if(this.getCharPosicao(i, j) == 'S'){
                    s++;
                    saida = new Coordenada(i, j);
                }
            }
        }
        for(int i = 1; i < this.getAltura()-2; i++){
            for(int j = 1; j < this.getLargura()-2; j++){
                if(this.getCharPosicao(i, j) == 'S')
                    throw new Exception("Saida localizada dentro do labirinto");            
            }
        }
        if(s==0)
            throw new Exception("Não foi encontrado nenhuma saida");
        if(s>1)
            throw new Exception("A quantidade de saidas é superior a 1");
        return saida;
    }
    
    public void isVazioEmBordas()throws Exception{
        //verificar se há espaço vazio nas bordas
        for(int i = 0; i < this.getLargura(); i++){
            if(this.getCharPosicao(0, i) == ' ')
                throw new Exception("Há espaços vazios nas paredes");
            if(this.getCharPosicao(this.getAltura() - 1, i) == ' ')
                throw new Exception("Há espaços vazios nas paredes");
        }
        for(int i = 0; i < this.getAltura(); i++){
            if(this.getCharPosicao(i, 0) == ' ')
                throw new Exception("Há espaços vazios nas paredes");
            if(this.getCharPosicao(i, this.getLargura() -1) == ' ')
                throw new Exception("Há espaços vazios nas paredes");
        }
        if(this.getCharPosicao(0, 0) == ' ' 
                || this.getCharPosicao(this.getAltura() - 1, 0) == ' '
                || this.getCharPosicao(0, this.getAltura()-1) == ' '
                || this.getCharPosicao(this.getAltura() - 1, this.getLargura()-1 ) == ' ')
            throw new Exception("Há espaços vazios em alguma das pontas");
    }
    public void caracterEstranho()throws Exception{
        //Método utilizado para verificar se algum caracter é diferente dos pré definidos para completar o labirinto
        for(int i = 0; i < this.getAltura(); i++){
            for(int j = 0; j < this.getLargura(); j++){
                if(this.getCharPosicao(i, j) != 'E' && this.getCharPosicao(i, j) != 'S' &&
                   this.getCharPosicao(i, j) != '#' && this.getCharPosicao(i, j) != '*' &&
                   this.getCharPosicao(i, j) != ' ')
                   throw new Exception("Caracter inválido para o labirinto");
            }
        }
    }
    //verificar posições adjacentes(Coordenada atual): se o caractere é um espaço vazio,
    //se posição adjascente já foi percorrida e não foi o ultimo elemento de "caminho"
    public void adjacente (Coordenada atual)throws Exception{
        Coordenada w;
        this.fila = new Fila<Coordenada>(3);
        if (atual.getLinha()-1 >= 0 ) {
            w = new Coordenada(atual.getLinha()-1,atual.getColuna());
            if(this.matriz[w.getLinha()][w.getColuna()] == ' ' || this.matriz[w.getLinha()][w.getColuna()] == 'S' && !this.caminho.recupereUmItem().equals(w)){
    
                this.fila.guardeUmItem(w);
            }
        } 
        if (atual.getColuna()-1 >= 0 ){
            w = new Coordenada(atual.getLinha(), atual.getColuna() -1);
            if(this.matriz[w.getLinha()][w.getColuna()] == ' ' || this.matriz[w.getLinha()][w.getColuna()] == 'S' && !this.caminho.recupereUmItem().equals(w)){
                this.fila.guardeUmItem(w);            
            }
        }
        if(atual.getLinha() + 1 <= this.getAltura()-1){
            w = new Coordenada(atual.getLinha()+1, atual.getColuna());
            if(this.matriz[w.getLinha()][w.getColuna()] == ' '|| this.matriz[w.getLinha()][w.getColuna()] == 'S' && !this.caminho.recupereUmItem().equals(w)){
                this.fila.guardeUmItem(w); 
            }
        
        }
        if(atual.getColuna() + 1 <= this.getLargura()-1){
            w = new Coordenada(atual.getLinha(), atual.getColuna()+1);
            if(this.matriz[w.getLinha()][w.getColuna()] == ' '|| this.matriz[w.getLinha()][w.getColuna()] == 'S' && !this.caminho.recupereUmItem().equals(w)){
                this.fila.guardeUmItem(w);
            }
        }

    }
    public Coordenada getAtual()throws Exception{
        return this.caminho.recupereUmItem();
    }
    public void mover() throws Exception{
        //método mover(): adicionar ao caminho a 1a posição de fila, remover essa posição de fila,
        //adicionar fila em possibilidades, usar setChar para colocar * na posição passada
        if (!this.fila.isVazia()) {
            this.caminho.guardeItem(this.fila.recupereUmItem());
            this.fila.removaUmItem();
            this.possibilidade.guardeItem(fila);
            if (this.getCharPosicao(this.caminho.recupereUmItem().getLinha(), this.caminho.recupereUmItem().getColuna()) != 'S')
                this.setChar(this.caminho.recupereUmItem().getLinha(), this.caminho.recupereUmItem().getColuna(), '*');
            this.caminho.toString();
        }
        else this.voltar();
    }

    //roolback() verificar se o burro bate na parede
    public void voltar() throws Exception{
        this.setChar(this.caminho.recupereUmItem().getLinha(), this.caminho.recupereUmItem().getColuna(), '.');
        this.caminho.removaUmItem();
        this.semCaminhoEparaS();
        this.fila = this.possibilidade.recupereUmItem();
        this.possibilidade.removaUmItem();
    }
    public void semCaminhoEparaS()throws Exception{
        if(this.caminho.isVazia())
            throw new Exception("Sem caminho de E para S");
    }
    public String inverteCaminho()throws Exception{
        //inverte caminho e colocar em inverso
        StringBuilder mapa = new StringBuilder(); 
        while (!this.caminho.isVazia()) {
            this.inverso.guardeItem(this.caminho.recupereUmItem());
            mapa.append("("+this.inverso.recupereUmItem() + ") ");
            this.caminho.removaUmItem();
        }
        return mapa.toString();
    }
    @Override
    public String toString(){
        String ret = "";
        for(int i = 0; i < this.getAltura(); i++){
            for(int j = 0; j<this.getLargura(); j++){
                ret += "" + matriz[i][j];
            }
            ret += "\n";
        }
        return ret;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;
        Labirinto lab = (Labirinto) obj;
        if(this.ALTURA != lab.ALTURA)
            return false;
        if(this.LARGURA != lab.LARGURA)
            return false;
        if(this.caminho!=lab.caminho)
            return false;
        if(this.possibilidade!=lab.possibilidade)
            return false;
        for(int i = 0; i < this.getAltura(); i++){
            for(int j = 0; j<this.getLargura(); j++){
                if(this.matriz[i][j]!=lab.matriz[i][j])
                    return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode(){
        int hash = 7;
        hash = hash * 11 + Integer.valueOf(this.ALTURA).hashCode();
        hash = hash * 11 + Integer.valueOf(this.LARGURA).hashCode();
        for(int i = 0; i < this.getAltura(); i++){
            for(int j = 0; j<this.getLargura(); j++){
                hash = hash * 11 + Integer.valueOf((int)this.matriz[i][j]).hashCode();
            }
        }

        hash = hash * 11 + this.fila.hashCode();
        hash = hash * 11 + this.caminho.hashCode();
        hash = hash * 11 + this.possibilidade.hashCode();

        if(hash < 0)
            hash = -hash;
        return hash;
    }
}