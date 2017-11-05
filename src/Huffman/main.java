package Huffman;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author CJ
 */
public class main {

    public static void main(String[] args) {
        String text;
        System.out.println("Ingrese texto:");
        text = new Scanner(System.in).nextLine();

        // Lista de arboles
        List<Tree> list = new List();
        list.setSorted(true);

        // Frecuencia
        int location;
        char current;
        for (int i = 0; i < text.length(); i++) {
            if ((location = list.find(current = text.charAt(i))) >= 0) {
                list.get(location).setValue(list.get(location).getValue() + 1);
                list.putAgain(location);
            } else {
                list.put(new Tree(null, current, 1, null));
            }
        }

        // Arbol binario
        while (list.size() > 1) {
            ArrayList<Tree> nodes = new ArrayList();
            nodes.add(list.extract(0));
            nodes.add(list.extract(0));
            int total = nodes.get(0).getValue() + nodes.get(1).getValue();
            list.put(new Tree(null, "NI", total, nodes));
        }

        // Obtener clave
        ArrayList<String> keys = key(list.extract(0), "", new ArrayList());

        // Formatear
        String formatted = format(text, keys);

        // Encriptar
        String encrypted = encrypt(formatted);

        // Desencriptar
        String decrypted = decrypt(encrypted);

        // Reformar
        String reformed = reform(decrypted, keys);

    }

    public static ArrayList<String> key(Tree tree, String track, ArrayList<String> keys) {
        if (tree.getChildren().isEmpty()) {
            keys.add(tree.getContent() + track);
        } else {
            key(tree.getChildren().get(0), track + "0", keys);
            key(tree.getChildren().get(1), track + "1", keys);
        }
        return keys;
    }

    public static String format(String text, ArrayList<String> keys) {
        String formatted = "";
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < keys.size(); j++) {
                if (text.charAt(i) == keys.get(j).charAt(0)) {
                    formatted += keys.get(j).substring(1, keys.get(j).length());
                }
            }
        }
        return formatted;
    }

    public static String reform(String decrypted, ArrayList<String> keys) {
        String text = "";
        while (decrypted.length() > 0) {
            for (int j = 0; j < keys.size(); j++) {
                String key = keys.get(j).substring(1, keys.get(j).length());
                int size = key.length();
                if (size > decrypted.length()) {
                    continue;
                }
                String code = decrypted.substring(0, size);
                if (key.equals(code)) {
                    text += keys.get(j).charAt(0);
                    decrypted = decrypted.substring(size, decrypted.length());
                }
            }
        }
        return text;
    }

    public static String encrypt(String formatted) {
        String encrypted = "";
        int radix = 16;
        while ((formatted.length() / radix) > 0) {
            encrypted += (char) Integer.parseInt(formatted.substring(0, radix), 2);
            formatted = formatted.substring(radix, formatted.length());
        }
        encrypted += (char) Integer.parseInt(formatted, 2);
        return encrypted;
    }

    public static String decrypt(String encrypted) {
        String decrypted = "";
        for (int i = 0; i < encrypted.length(); i++) {
            String temp = Integer.toBinaryString((int) encrypted.charAt(i));
            while ((temp.length() < 16) && (i < encrypted.length() - 1)) {
                temp = "0" + temp;
            }
            decrypted += temp;
        }
        return decrypted;
    }

}
