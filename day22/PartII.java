import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day22/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                list.add(Long.parseLong(txt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        @SuppressWarnings("unchecked")
        Map<String, Integer>[] pattern = new HashMap[list.size()];
        for (int i = 0; i < list.size(); i++) {
            pattern[i] = new HashMap<>();
        }
        for (int j = 0; j < list.size(); j++) {
            long cur = list.get(j);
            String pre = "" + (cur % 10);
            List<String> seq = new ArrayList<>();
            for (int i = 0; i < 2000; i++) {
                cur = process(cur);
                int mod = (int) cur % 10;
                seq.add((pre.isBlank() ? "" : "" + (mod - Integer.parseInt(pre))));
                pre = "" + mod;
                if (seq.size() == 4) {
                    String s = "";
                    for (String str : seq) {
                        s += str;
                    }
                    if (!pattern[j].containsKey(s)) {
                        pattern[j].put(s, mod);
                    }
                    seq.remove(0);
                }
            }
        }
        int sum = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            for (String k : pattern[i].keySet()) {
                int tmp = pattern[i].get(k);
                for (int j = 0; j < list.size(); j++) {
                    if (i != j) {
                        if (pattern[j].containsKey(k)) {
                            tmp += pattern[j].get(k);
                        }
                    }
                }
                // System.out.println(tmp);
                if (tmp > sum) {
                    sum = tmp;
                }
            }
        }
        System.out.println(sum);
    }

    private static long process(Long l) {
        l = ((l ^ (l * 64)) % 16777216);
        l = ((l ^ (l / 32)) % 16777216);
        l = ((l ^ (l * 2048)) % 16777216);
        return l;
    }
}