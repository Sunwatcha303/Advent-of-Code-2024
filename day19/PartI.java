import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    static List<String> memo = new ArrayList<>();

    public static void main(String[] args) {
        long sum = 0;
        try (Scanner in = new Scanner(Paths.get("./day19/input.txt"))) {
            String[] pattern = in.nextLine().split(", ");
            in.nextLine();
            while (in.hasNext()) {
                String txt = in.nextLine();
                if (canFind(txt, pattern)) {
                    sum++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    private static boolean canFind(String txt, String[] pattern) {
        int n = txt.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i < n + 1; i++) {
            for (String s : pattern) {
                int ns = s.length();
                if (i >= ns && dp[i - ns] && txt.substring(i - ns, i).equals(s)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
}