import java.lang.reflect.Method;

public class Fila <X> 	
{
    private Object[] elemento;
    private int ultimo= -1;
    private int capacidadeInicial;

    public Fila (int tamanho) throws Exception
    {
        if (tamanho<=0)
            throw new Exception ("Tamanho invalido");

        this.elemento=new Object [tamanho];
        this.capacidadeInicial=tamanho;
    }

    private void redimensioneSe(float porct)
    {
        Object[] novo = new Object[(int)Math.ceil(this.elemento.length*porct)];

        for(int i = 0 ; i<this.elemento.length ; i++ )
        {
            novo[i]= this.elemento[i] ;
        }

        this.elemento = novo;
    }

    public void guardeUmItem (X x) throws Exception
    {
        if (x==null)
            throw new Exception ("Falta o que guardar");

        if (this.isCheia())
            this.redimensioneSe (2.0F);

        this.ultimo++;

        if (x instanceof Cloneable)
            this.elemento[this.ultimo] = meuCloneDeX(x);
        else
            this.elemento[this.ultimo] = x;
    }

    public X recupereUmItem () throws Exception
    {
        if (this.ultimo==-1)
            throw new Exception ("Nada a recuperar");

        X ret=null;
        if (this.elemento[this.ultimo] instanceof Cloneable)
            ret = meuCloneDeX((X)this.elemento[this.ultimo]);
        else
            ret = (X)this.elemento[this.ultimo];

        return ret;
    }

    public void removaUmItem () throws Exception
    {
        if (this.ultimo==-1)
            throw new Exception ("Nada a remover");

        for(int i = 0; i <  this.elemento.length; i++){
            if(i == this.elemento.length-1)
                this.elemento[i] = null;
            else
                this.elemento[i] = this.elemento[i+1]; 
            System.out.println(this.elemento[i]);
        }
        this.ultimo--;

        if (this.elemento.length>this.capacidadeInicial && this.ultimo<=Math.round(this.elemento.length*0.25F))
            this.redimensioneSe (0.5F);
    }

    public boolean isCheia ()
    {
        if(this.ultimo+1==this.elemento.length)
            return true;

        return false;
    }

    public boolean isVazia ()
    {
        if(this.ultimo==-1)
            return true;

        return false;
    }

    private X meuCloneDeX(X x)
    {

        X ret  = null;

        try
        {
			Class<?> classe = x.getClass();
			Class<?>[] tipoDosParms = null;
			Method metodo = classe.getMethod("clone", tipoDosParms);
			Object[] parms = null;
			ret = (X) metodo.invoke(classe, parms);
        }
        catch(Exception erro)
        {}

        return ret;
    }

    public Fila(Fila<X> modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("modelo ausente");

        this.ultimo = modelo.ultimo;

        this.elemento = new Object[modelo.elemento.length];

        for(int i=0 ; i<modelo.elemento.length ; i++)
            this.elemento[i] = modelo.elemento[i];
    }

    @Override
    public Object clone()
    {
        Fila<X> ret = null;

        try
        {
            ret  = new Fila(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }

    @Override
    public String toString()
    {
        String ret = this.elemento.length + " positions";
        
        if (this.ultimo!=-1)
            ret += ", sendo o ultimo "+this.elemento[this.ultimo];
            
        return ret;
    }

    @Override
    public boolean equals (Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Fila<X> fil = (Fila<X>) obj;

        if(this.ultimo!=fil.ultimo)
            return false;

        for(int i=0 ; i<this.ultimo;i++)
            if(!this.elemento[i].equals (fil.elemento[i]))
                return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret=7;
        ret = ret * 11 + Integer.valueOf(this.ultimo).hashCode();

        for (int i=0; i<this.ultimo; i++)
            ret = ret * 11 + this.elemento[i].hashCode();

        if (ret<0)
            ret=-ret;

        return ret;
    }
}