package py.com.mabpg.ruidospeckle.models;

public class Nodo {

    // posicion en la imagen y el valor de la mediana a guardar.

    private CambiosFiltroMediana valor;
    // Variable para enlazar los nodos.
    private Nodo siguiente;

    /**
     * Constructor que inicializamos el valor de las variables.
     */
    public void Nodo() {
        this.valor = null;
        this.siguiente = null;
    }

    // Métodos get y set para los atributos.
    public CambiosFiltroMediana getValor() {
        return valor;
    }

    public void setValor(CambiosFiltroMediana valor) {
        this.valor = valor;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
