import java.nio.file.Paths;
import java.util.*;

public class PartII {
    static Map<String, long[]> memo = new HashMap<>();

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day11/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                String[] line = txt.split(" ");
                list.addAll(Arrays.asList(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long sum = 0;
        for (String s : list) {
            String[] arr = { s };
            sum += afterBlink(arr, "", 1, 76);
        }
        System.out.println(sum);
    }

    private static long afterBlink(String[] arr, String pre, int curLevel, int maxLevel) {
        if (curLevel == maxLevel) {
            long size = arr.length;
            return size;
        }

        long sum = 0;
        for (String str : arr) {
            if (memo.containsKey(str) && memo.get(str)[curLevel] != 0) {
                sum += memo.get(str)[curLevel];
            } else {
                String[] tmp;
                long num = Long.parseLong(str);
                if (num == 0) {
                    tmp = new String[] { "1" };
                } else if (str.length() % 2 == 0) {
                    int mid = str.length() / 2;
                    tmp = new String[] {
                            String.valueOf(Long.parseLong(str.substring(0, mid))),
                            String.valueOf(Long.parseLong(str.substring(mid)))
                    };
                } else {
                    tmp = new String[] { String.valueOf(num * 2024) };
                }
                sum += afterBlink(tmp, str, curLevel + 1, maxLevel);

            }
        }

        if (!memo.containsKey(pre)) {
            memo.put(pre, new long[maxLevel + 1]);
        }
        memo.get(pre)[curLevel - 1] = sum;
        return sum;
    }
}
