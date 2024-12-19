import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class PartII {
    public static void main(String[] args) {
        Map<String, Long> register = new HashMap<>();
        List<Integer> program = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day17/input.txt"))) {
            String aLine = in.nextLine();
            String bLine = in.nextLine();
            String cLine = in.nextLine();
            in.nextLine();
            String pLine = in.nextLine();

            String[] a = aLine.split(": ");
            register.put(a[0].substring(a[0].length() - 1), Long.parseLong(a[1]));
            String[] b = bLine.split(": ");
            register.put(b[0].substring(b[0].length() - 1), Long.parseLong(b[1]));
            String[] c = cLine.split(": ");
            register.put(c[0].substring(c[0].length() - 1), Long.parseLong(c[1]));

            String[] p = pLine.split(": ")[1].split(",");
            for (int i = 0; i < p.length; i++) {
                program.add(Integer.parseInt(p[i]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<Integer, Combo> combo = new HashMap<>();
        combo.put(0, () -> 0);
        combo.put(1, () -> 1);
        combo.put(2, () -> 2);
        combo.put(3, () -> 3);
        combo.put(4, () -> register.get("A"));
        combo.put(5, () -> register.get("B"));
        combo.put(6, () -> register.get("C"));

        StringBuilder output = new StringBuilder();
        // long tmp = 100000000000000L;
        long a = 1;
        // long a = tmp;
        long b = register.get("B");
        long c = register.get("C");
        System.out.println(register);
        System.out.println(program);
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(a, ""));
        while (!stack.isEmpty()) {
            Pair p = stack.remove(0);

            if (!p.s.isEmpty() && p.s.substring(0, output.length() - 1).split(",").length == program.size() && isEqual(p.s, program)) {
                break;
            }
            for (long A = p.a; A < p.a + 1000000000L; A++) {
                int i = 0;
                register.put("A", A);
                register.put("B", b);
                register.put("C", c);

                output = new StringBuilder();
                while (i < program.size()) {
                    int ops = program.get(i % program.size());
                    int com = program.get((i + 1) % program.size());
                    boolean jump = process(ops, com, register, combo, output);
                    i = (jump) ? com : (i + 2);
                }

                if (isEqual(output.toString(), program)) {
                    System.out.println("A: " + A + " " + Long.toBinaryString(A) + " " + output.toString());
                    stack.push(new Pair(A << 3, output.toString()));
                    break;
                }
            }
        }
    }

    private static boolean isEqual(String output, List<Integer> program) {
        if (output.isEmpty()) {
            return false;
        }
        List<Integer> reversedProgram = new ArrayList<>(program);
        Collections.reverse(reversedProgram);
        String[] str = output.substring(0, output.length() - 1).split(",");
        if (str.length > program.size()) {
            return false;
        }
        for (int i = str.length - 1; i >= 0; i--) {
            if (Integer.parseInt(str[i]) != reversedProgram.get(str.length - i - 1)) {
                return false;
            }
        }

        return true;
    }

    private static boolean process(int ops, int com, Map<String, Long> register, Map<Integer, Combo> combo,
            StringBuilder output) {
        if (ops == 0) {
            long val = register.get("A");
            val = val / (int) (Math.pow(2, combo.get(com).fuc()));
            register.put("A", val);
        } else if (ops == 1) {
            long val = register.get("B");
            val ^= com;
            register.put("B", val);
        } else if (ops == 2) {
            long val = combo.get(com).fuc();
            val %= 8;
            register.put("B", val);
        } else if (ops == 3) {
            long val = register.get("A");
            if (val != 0) {
                return true;
            }
        } else if (ops == 4) {
            long valB = register.get("B");
            long valC = register.get("C");
            long val = valB ^ valC;
            register.put("B", val);
        } else if (ops == 5) {
            long val = combo.get(com).fuc();
            val %= 8;
            output.append(val);
            output.append(",");
        } else if (ops == 6) {
            long val = register.get("A");
            val /= Math.pow(2, combo.get(com).fuc());
            register.put("B", val);
        } else if (ops == 7) {
            long val = register.get("A");
            val /= Math.pow(2, combo.get(com).fuc());
            register.put("C", val);
        }
        return false;
    }

}

class Pair {
    long a;
    String s;

    Pair(long a, String s) {
        this.a = a;
        this.s = s;
    }
}

interface Combo {
    long fuc();
}
