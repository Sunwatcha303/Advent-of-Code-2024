import java.nio.file.Paths;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        int safe = 0;
        try (Scanner in = new Scanner(Paths.get("./day2/input.txt"))) {
            while (in.hasNext()) {
                String[] level = in.nextLine().split(" ");
                Boolean isIncrease = null;
                boolean isSafe = true;
                for (int i = -1; i < level.length; i++) {
                    isSafe = true;
                    isIncrease = null;
                    loop: for (int j = 0; j < level.length - 1; j++) {
                        int cur = j;
                        int next = j + 1;
                        if (j == i) {
                            continue;
                        }
                        if (j + 1 == i) {
                            next++;
                            if (next == level.length)
                                break loop;
                        }

                        int tmp = Integer.parseInt(level[cur]);
                        int tmpNext = Integer.parseInt(level[next]);
                        if (isIncrease == null) {
                            if (tmp - tmpNext < 0) {
                                isIncrease = true;
                            } else {
                                isIncrease = false;
                            }
                        }
                        if (isIncrease && tmp - tmpNext < 0) {
                            if (Math.abs(tmp - tmpNext) > 3) {
                                isSafe = false;
                                break loop;
                            }
                        } else if (!isIncrease && tmp - tmpNext > 0) {
                            if (Math.abs(tmp - tmpNext) > 3) {
                                isSafe = false;
                                break loop;
                            }
                        } else {
                            isSafe = false;
                            break loop;
                        }
                    }
                    if (isSafe) {
                        break;
                    }
                }
                if (isSafe)
                    safe++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(safe);
    }
}