package Huffman;

import java.io.*;
import java.util.*;

/**
 *
 * @author CJ
 */
public class Manager {

    protected File file;
    protected List list;

    public Manager(File file) {
        this.file = file;
        this.list = new List();
    }

    public boolean writeBin(List list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(list.get(i));
                out.close();
            }
        } catch (Exception e) {
        }

        return false;
    }

    public List readBin() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(file));
            Object o = entrada.readObject();
            list.add(o);
            entrada.close();
        } catch (Exception e) {
        }
        return list;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

}
