package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int mayor = 0;
        String aux = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (aux.contains(String.valueOf(c))) {
                aux = aux.substring(aux.indexOf(c) + 1);
            }
            aux += c;
            mayor = Math.max(mayor, aux.length());
        }

        return mayor;
    }
}

