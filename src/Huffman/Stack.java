package Huffman;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author CJ
 */
public class Stack<T extends Object> extends List<T> implements Cloneable, Serializable {

    public Stack() {
        this.list = new ArrayList();
    }

    public void push(T t) {
        list.add(t);
    }

    public T pull() {
        if (list.size() > 0) {
            T temp = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return temp;
        }
        return null;
    }

    public T pop() {
        if (list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = list.size() - 1; i >= 0; i--) {
            s += list.get(i);
        }
        clear();
        return s;
    }

    public String popString() {
        String s = "";
        for (int i = list.size() - 1; i >= 0; i--) {
            s += list.get(i);
        }
        return s;
    }

}
