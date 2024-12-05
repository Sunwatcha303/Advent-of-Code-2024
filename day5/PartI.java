import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PartI {
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
            if (isCorrect(update, rules, cnt)) {
                sum += Integer.parseInt(update[cnt / 2]);
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
}