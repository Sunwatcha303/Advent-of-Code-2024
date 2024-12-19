import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PartII {
    static List<String> memo = new ArrayList<>();

    public static void main(String[] args) {
        long sum = 0;
        try (Scanner in = new Scanner(Paths.get("./day19/input.txt"))) {
            String[] pattern = in.nextLine().split(", ");
            in.nextLine();
            while (in.hasNext()) {
                String txt = in.nextLine();
                sum += find(txt, pattern);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    private static long find(String txt, String[] pattern) {
        int n = txt.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (String s : pattern) {
                int ns = s.length();
                if (i >= ns && txt.substring(i - ns, i).equals(s)) {
                    dp[i] += dp[i - ns];
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}