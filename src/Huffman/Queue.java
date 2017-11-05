package Huffman;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author CJ
 */
public class Queue<T extends Object> extends List<T> implements Cloneable, Serializable {

    private int pos;

    public Queue() {
        this.list = new ArrayList();
        pos = 0;
    }

    public T next() {
        if (list.isEmpty()) {
            return null;
        }
        T temp = list.get(0);
        list.remove(0);
        return temp;
    }

    public T getCurrent() {
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > pos) {
            T temp = list.get(pos);
            list.remove(pos);
            return temp;
        } else {
            return null;
        }
    }

    public T see() {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public T seeNext() {
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > pos) {
            return list.get(pos);
        } else {
            return null;
        }
    }

}
