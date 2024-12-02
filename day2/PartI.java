import java.nio.file.Paths;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        int safe = 0;
        try (Scanner in = new Scanner(Paths.get("./day2/input.txt"))) {
            while (in.hasNext()) {
                String[] level = in.nextLine().split(" ");
                int tmp = Integer.parseInt(level[0]);
                int tmpNext = Integer.parseInt(level[1]);
                boolean isIncrease = false;
                int cnt = 0;
                if (tmp - tmpNext < 0) {
                    isIncrease = true;
                }
                for (int i = 0; i < level.length - 1; i++) {
                    tmp = Integer.parseInt(level[i]);
                    tmpNext = Integer.parseInt(level[i + 1]);
                    // System.out.println(tmp + " " + tmpNext);
                    if (isIncrease && tmp - tmpNext < 0) {
                        if (Math.abs(tmp - tmpNext) > 0 && Math.abs(tmp - tmpNext) < 4) {
                            cnt++;
                        } else {
                            break;
                        }
                    } else if (!isIncrease && tmp - tmpNext > 0) {
                        if (Math.abs(tmp - tmpNext) > 0 && Math.abs(tmp - tmpNext) < 4) {
                            cnt++;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                    // System.out.println("cnt: "+cnt);
                }
                if (cnt == level.length - 1)
                    safe++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(safe);
    }
}