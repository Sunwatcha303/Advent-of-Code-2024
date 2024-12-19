import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        Map<String, Integer> register = new HashMap<>();
        List<Integer> program = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day17/input.txt"))) {
            String aLine = in.nextLine();
            String bLine = in.nextLine();
            String cLine = in.nextLine();
            in.nextLine();
            String pLine = in.nextLine();

            String[] a = aLine.split(": ");
            register.put(a[0].substring(a[0].length() - 1), Integer.parseInt(a[1]));
            String[] b = bLine.split(": ");
            register.put(b[0].substring(b[0].length() - 1), Integer.parseInt(b[1]));
            String[] c = cLine.split(": ");
            register.put(c[0].substring(c[0].length() - 1), Integer.parseInt(c[1]));

            String[] p = pLine.split(": ")[1].split(",");
            for (int i = 0; i < p.length; i++) {
                program.add(Integer.parseInt(p[i]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(register);
        System.out.println(program);
        Map<Integer, Combo> combo = new HashMap<>();
        combo.put(0, () -> 0);
        combo.put(1, () -> 1);
        combo.put(2, () -> 2);
        combo.put(3, () -> 3);
        combo.put(4, () -> register.get("A"));
        combo.put(5, () -> register.get("B"));
        combo.put(6, () -> register.get("C"));

        StringBuilder output = new StringBuilder();
        int i = 0;
        while (i < program.size()) {
            int ops = program.get(i % program.size());
            int com = program.get((i + 1) % program.size());
            boolean jump = process(ops, com, register, combo, output);
            i = (jump) ? com : (i + 2);
        }
        System.out.println(register);
        System.out.println(output.substring(0, output.length() - 1));
    }

    private static boolean process(int ops, int com, Map<String, Integer> register, Map<Integer, Combo> combo,
            StringBuilder output) {
        if (ops == 0) {
            int val = register.get("A");
            val = val / (int) (Math.pow(2, combo.get(com).fuc()));
            register.put("A", val);
        } else if (ops == 1) {
            int val = register.get("B");
            val ^= com;
            register.put("B", val);
        } else if (ops == 2) {
            int val = combo.get(com).fuc();
            val %= 8;
            register.put("B", val);
        } else if (ops == 3) {
            int val = register.get("A");
            if (val != 0) {
                return true;
            }
        } else if (ops == 4) {
            int valB = register.get("B");
            int valC = register.get("C");
            int val = valB ^ valC;
            register.put("B", val);
        } else if (ops == 5) {
            int val = combo.get(com).fuc();
            val %= 8;
            output.append(val);
            output.append(",");
        } else if (ops == 6) {
            int val = register.get("A");
            val /= Math.pow(2, combo.get(com).fuc());
            register.put("B", val);
        } else if (ops == 7) {
            int val = register.get("A");
            val /= Math.pow(2, combo.get(com).fuc());
            register.put("C", val);
        }
        return false;
    }

}

interface Combo {
    int fuc();
}