package Huffman;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author CJ
 */
public class Tree implements Serializable {

    /* Lista de hijos. */
    protected ArrayList<Tree> children;
    /* Arbol padre. */
    protected Tree father;
    /* Valor del arbol. */
    protected int value;
    /* Contenido del arbol. */
    protected Object content;
    /* Orden de recorrido. */
    private int order = 2;
    /* Orden prefijo. */
    public final int PRE = 0;
    /* Orden posfijo. */
    public final int POS = 1;
    /* Orden simetrico. */
    public final int SIMETRIC = 2;
    /* Visto. */
    private boolean seen = false;

    /**
     *
     * @param father
     * @param content
     * @param subtrees
     */
    public Tree(Tree father, Object content, int value, ArrayList<Tree> subtrees) {
        this.father = father;
        this.content = content;
        this.value = value;
        if (subtrees != null) {
            this.children = subtrees;
            for (int i = 0; i < children.size(); i++) {
                this.children.get(i).setFather(this);
            }
        } else {
            this.children = new ArrayList();
        }
    }

    /**
     *
     * @param nodes
     */
    public void create(Tree[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].setFather(this);
            children.add(nodes[i]);
        }
    }

    /**
     *
     * @param object
     */
    public void add(Object object, int value) {
        if (object instanceof Tree) {
            this.children.add((Tree) object);
        } else {
            this.children.add(new Tree(this, object, value, null));
        }
    }

    /**
     *
     * @param object
     * @return position
     */
    public int indexOf(Object object) {
        if (object instanceof Tree) {
            return children.indexOf((Tree) object);
        } else {
            for (int i = 0; i < children.size(); i++) {
                if (this.children.get(i).content == object) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     *
     * @param object
     */
    public void remove(Object object) {
        int i = indexOf(object);
        if (i > 0) {
            children.remove(i);
        }
    }

    /**
     *
     * @param tree
     */
    public void setFather(Tree tree) {
        this.father = tree;
    }

    /**
     *
     * @return father
     */
    public Tree getFather() {
        return father;
    }

    /**
     *
     * @param object
     */
    public void setContent(Object object) {
        this.content = object;
    }

    /**
     *
     * @return content
     */
    public Object getContent() {
        return this.content;
    }

    /**
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @param seen
     */
    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    /**
     *
     * @return seen
     */
    public boolean isSeen() {
        return this.seen;
    }

    /**
     *
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     *
     * @return nextTree
     */
    public Tree next() {
        if (!children.isEmpty()) {
            switch (order) {
                case PRE: {
                    for (int i = children.size() - 1; i >= 0; i--) {
                        if (!children.get(i).isSeen()) {
                            return children.get(i).next();
                        }
                    }
                }
                case POS: {
                    for (int i = children.size() - 1; i >= 0; i--) {
                        if (!children.get(i).isSeen()) {
                            return children.get(i).next();
                        }
                    }
                }
                case SIMETRIC: {
                    for (int i = children.size() - 1; i >= 0; i--) {
                        if (!children.get(i).isSeen()) {
                            return children.get(i).next();
                        }
                    }
                }
            }
        }
        setSeen(true);
        return this;
    }

    public void clear() {
        this.children = new ArrayList();
        this.content = null;
    }

    public boolean isEmpty() {
        return children.isEmpty() && (content == null);
    }

    @Override
    public String toString() {
        String data = "";
        data += content + " [" + value + "] { ";
        for (int i = 0; i < children.size(); i++) {
            data += children.get(i).toString() + ", ";
        }
        data += " } ";
        return data;
    }

    @Override
    public boolean equals(Object o) {
        return content.equals(o);
    }

    @Override
    public int hashCode() {
        return value;
    }

    public ArrayList<Tree> getChildren() {
        return children;
    }

}
