package py.com.mabpg.ruidospeckle.models;

import py.com.mabpg.ruidospeckle.models.Nodo;

public class ListaEnlazada {

    //Atributos
    private Nodo primero;

    /**
     * Constructor por defecto
     */
    public ListaEnlazada() {
        listaVacia();
    }

    /**
     * Vacia la lista
     */
    private void listaVacia() {
        primero = null;
    }

    /**
     * Indica si la lista esta vacia o no
     *
     * @return True = esta vacia
     */
    public boolean estaVacia() {
        return primero == null;
    }

    /**
     * Inserta un objeto al principio de la lista
     *
     * @param t Dato insertado
     */
    public void insertarPrimero(int valor, int fila, int columna) {
        CambiosFiltroMediana elemento = new CambiosFiltroMediana(valor, fila, columna);

        Nodo nuevo = new Nodo();
        nuevo.setValor(elemento);

        if (!estaVacia()) {
            //Sino esta vacia, el primero actual pasa a ser
            // el siguiente de nuestro nuevo nodo
            nuevo.setSiguiente(primero);
        }

        //el primero apunta al nodo nuevo
        primero = nuevo;

    }

    /**
     * Quita el primer elemento de la lista
     */
    public Nodo quitarPrimero() {
        Nodo aux = null;
        if (!estaVacia()) {
            aux = primero;
            primero = primero.getSiguiente();
        }
        return aux;
    }

    /**
     * Devuelve el número de elementos de la lista
     *
     * @return Número de elementos
     */
    public int cuantosElementos() {
        Nodo aux;
        int numElementos = 0;
        aux = primero;

        //Recorremos
        while (aux != null) {
            numElementos++;
            aux = aux.getSiguiente();
        }
        return numElementos;

    }

}
