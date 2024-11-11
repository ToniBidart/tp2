package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {
    private ArrayList<T> heapArr; 
    private Comparator<T> comparator;

    // Heap<Traslado> heap = new Heap<>(traslados, CustomComparator.BY_GANANCIA);  asi mando un parametro

    public Heap(ArrayList<T> arr, Comparator<T> comparator){
        this.comparator = comparator;
        this.heapArr = floydInsert(arr);
    }

    private ArrayList<T> floydInsert(ArrayList<T> arr){
        for(int i = arr.size()/2 - 1; i >= 0; i--){
            heapify(i, arr);
        }
        return arr;
    }

    private void heapify(int i, ArrayList<T> arr){
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < arr.size() && comparator.compare(arr.get(left), arr.get(largest)) >= 0){
            largest = left;
        }
        if (right < arr.size() && comparator.compare(arr.get(right), arr.get(largest)) >= 0){
            largest = right;
        }
        if (largest != i){
            swap(i, largest, arr);
            heapify(largest, arr);
        }
    }

    private void heapifyUp(int i){
        int padre = (i - 1) / 2;

        while(i > 0 && comparator.compare(this.heapArr.get(i), this.heapArr.get(padre)) > 0){
            swap(i, padre, this.heapArr);
            i = padre;
            padre = (i - 1) / 2;
        }
    }

    private void swap(int i, int j, ArrayList<T> arr) {
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);

        //chequear esto (casting)
        if(this.comparator == CustomComparator.BY_GANANCIA){
            Traslado elemento = (Traslado) arr.get(i);
            elemento.setPosicionGanancia(i);
            Traslado elemento2 = (Traslado) arr.get(j);
            elemento2.setPosicionGanancia(j);
        }
        if(this.comparator == CustomComparator.BY_TIMESTAMP){
            Traslado elemento = (Traslado) arr.get(i);
            elemento.setPosicionTimestamp(i);
            Traslado elemento2 = (Traslado) arr.get(j);
            elemento2.setPosicionTimestamp(j);
        }
        if(this.comparator == CustomComparator.BY_BALANCE){
            Ciudad elemento = (Ciudad) arr.get(i);
            elemento.setPosicionCiudad(i);
            Ciudad elemento2 = (Ciudad) arr.get(j);
            elemento2.setPosicionCiudad(j);
        }      
    }
}