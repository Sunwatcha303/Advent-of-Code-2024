import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        List<String> value = new ArrayList<>();
        List<String> eq = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day7/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                String[] line = txt.split(": ");
                value.add(line[0]);
                eq.add(line[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long sum = 0;
        for (int i = 0; i < value.size(); i++) {
            long val = Long.parseLong(value.get(i));
            if (isPossible(val, eq.get(i))) {
                sum += val;
            }
        }
        System.out.println(sum);
    }

    private static boolean isPossible(long value, String eq) {
        String[] tmp = eq.split(" ");
        List<Long> equaltion = new ArrayList<>();
        for (int i = 0; i < tmp.length; i++) {
            equaltion.add(Long.parseLong(tmp[i]));
        }

        List<long[]> q = new ArrayList<>();
        q.add(new long[] { equaltion.get(0), 0 });
        while (!q.isEmpty()) {
            long[] val = q.remove(0);
            long val1 = val[0];
            long val2 = val[1];
            if (val1 == value && val2 == equaltion.size() - 1) {
                return true;
            }
            if (val2 + 1 < equaltion.size()) {
                long num = equaltion.get((int) (val2 + 1));
                q.add(new long[] { (val1 + num), val2 + 1 });
                q.add(new long[] { (val1 * num), val2 + 1 });
                q.add(new long[] { Long.parseLong(val1 + "" + num), val2 + 1 });
            }
        }
        return false;
    }
}