package Huffman;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author CJ
 */
public class List<T extends Object> implements Cloneable, Serializable {

    protected boolean sorted = false;
    protected ArrayList<T> list;
        private static final long serialVersionUID = 1L;

    public List(ArrayList<T> list) {
        this.list = list;
    }

    public List() {
        list = new ArrayList();
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    public T get(int i) {
        return list.get(i);
    }

    public T extract(int i) {
        T t = get(i);
        list.remove(i);
        return t;
    }

    public void setList(int i, T t) {
        this.list.set(i, t);
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void add(T t) {
        this.list.add(t);
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public void clear() {
        this.list = new ArrayList();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public int indexOf(T t) {
        return list.indexOf(t);
    }

    public int find(Object o) {
        for (int i = 0; i < list.size(); i++) {
            if (((T) list.get(i)).equals(o)) {;
                return i;
            }
        }
        return -1;
    }

    public boolean putAgain(int p) {
        T t = list.get(p);
        list.remove(p);
        return put(t);
    }

    public boolean put(T t) {
        if (sorted) {
            for (int i = list.size() - 1; i >= 0; i--) {
                if (t.hashCode() > list.get(i).hashCode()) {
                    list.add(i + 1, t);
                    return true;
                }
            }
        }
        list.add(0, t);
        return false;
    }

    @Override

    public String toString() {
        return list.toString();
    }
}
