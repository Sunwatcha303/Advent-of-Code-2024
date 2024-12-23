import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PartI {
    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        try (Scanner in = new Scanner(Paths.get("./day23/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                String[] line = txt.split("-");
                if (!map.containsKey(line[0])) {
                    map.put(line[0], new ArrayList<>());
                }
                if (!map.containsKey(line[1])) {
                    map.put(line[1], new ArrayList<>());
                }
                map.get(line[0]).add(line[1]);
                map.get(line[1]).add(line[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long sum = 0;
        List<Set<String>> set = new ArrayList<>();
        for (String key : map.keySet()) {
            for (String key1 : map.get(key)) {
                for (String key2 : map.get(key1)) {
                    Set<String> s = new HashSet<>();
                    if (!key.equals(key1) && !key.equals(key2) && !key1.equals(key2)) {
                        s.add(key);
                        s.add(key1);
                        s.add(key2);
                        if (map.get(key2).contains(key) && !set.contains(s)) {
                            if (key.startsWith("t") || key1.startsWith("t") || key2.startsWith("t")) {
                                sum++;
                            }
                            set.add(s);
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }
}