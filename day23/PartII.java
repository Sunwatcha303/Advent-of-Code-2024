
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class PartII {
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
        List<String> maxSet = new ArrayList<>();
        for (String key : map.keySet()) {
            List<Set<String>> memo = new ArrayList<>();
            Queue<List<String>> q = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.size(), p1.size()));
            q.add(List.of(key));
            while (!q.isEmpty()) {
                List<String> p = q.poll();
                if (p.size() > maxSet.size()) {
                    maxSet = new ArrayList<>(p);
                }
                Set<String> tmp = new HashSet<>(map.get(p.get(0)));
                for (int i = 1; i < p.size(); i++) {
                    tmp.retainAll(map.get(p.get(i)));
                }
                tmp.removeAll(p);
                for (String s : tmp) {
                    List<String> newTmp = new ArrayList<>(p);
                    newTmp.add(s);
                    Set<String> set = new HashSet<>(newTmp);
                    if (!memo.contains(set)) {
                        memo.add(set);
                        q.offer(newTmp);
                    }
                }
            }
        }
        Collections.sort(maxSet);
        System.out.println(String.join(",", maxSet));
    }
}