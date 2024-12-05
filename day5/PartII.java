import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        Map<String, List<String>> rules = new HashMap<>();
        List<String[]> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day5/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                if (txt.equals("")) {
                    break;
                }
                String[] tmp = txt.split("\\|");
                if (rules.containsKey(tmp[0])) {
                    rules.get(tmp[0]).add(tmp[1]);
                } else {
                    rules.put(tmp[0], new ArrayList<>());
                    rules.get(tmp[0]).add(tmp[1]);
                }
            }
            while (in.hasNext()) {
                String txt = in.nextLine();
                list.add(txt.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            String[] update = list.get(i);
            int cnt = update.length;
            if (!isCorrect(update, rules, cnt)) {
                String[] newUpdate = correctUpdate(update, rules, cnt);
                if (newUpdate != null) {
                    sum += Integer.parseInt(newUpdate[cnt / 2]);
                }
            }
        }
        System.out.println(sum);
    }

    static boolean isCorrect(String[] update, Map<String, List<String>> rules, int n) {
        for (int i = 0; i < n - 1; i++) {
            String tmp = update[i];
            List<String> list = rules.get(tmp);
            for (int j = i + 1; j < n; j++) {
                if (list == null || !list.contains(update[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    static String[] correctUpdate(String[] update, Map<String, List<String>> rules, int n) {
        List<List<String>> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            q.add(new ArrayList<>());
            q.get(i).add(update[i]);
        }
        while (!q.isEmpty()) {
            List<String> p = q.remove(0);
            List<String> rule = rules.get(p.getLast());
            for (int i = 0; i < n && rule != null; i++) {
                if (rule.contains(update[i]) && !p.contains(update[i])) {
                    List<String> newP = new ArrayList<>(p);
                    newP.add(update[i]);
                    if (newP.size() == n) {
                        String[] tmpUpdate = new String[n];
                        newP.toArray(tmpUpdate);
                        return tmpUpdate;
                    }
                    q.add(0,newP);
                }
            }
        }

        return null;
    }
}