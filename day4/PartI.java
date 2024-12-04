import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        int sum = 0;
        List<char[]> list = new ArrayList<>();

        try (Scanner in = new Scanner(Paths.get("./day4/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                list.add(txt.toCharArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                if (list.get(i)[j] == 'X') {
                    int tmp = countXMAS(list, i, j);
                    sum += tmp;
                }
            }
        }
        System.out.println(sum);
    }

    private static int countXMAS(List<char[]> list, int i, int j) {
        int sum = 0;
        if (j - 3 >= 0 && list.get(i)[j - 1] == 'M' && list.get(i)[j - 2] == 'A' && list.get(i)[j - 3] == 'S') {
            sum++;
        }
        if (j + 3 < list.get(i).length && list.get(i)[j + 1] == 'M' && list.get(i)[j + 2] == 'A'
                && list.get(i)[j + 3] == 'S') {
            sum++;
        }
        if (i - 3 >= 0 && list.get(i - 1)[j] == 'M' && list.get(i - 2)[j] == 'A' && list.get(i - 3)[j] == 'S') {
            sum++;
        }
        if (i + 3 < list.size() && list.get(i + 1)[j] == 'M' && list.get(i + 2)[j] == 'A'
                && list.get(i + 3)[j] == 'S') {
            sum++;
        }

        if (j - 3 >= 0 && i - 3 >= 0 && list.get(i - 1)[j - 1] == 'M' && list.get(i - 2)[j - 2] == 'A'
                && list.get(i - 3)[j - 3] == 'S') {
            sum++;
        }
        if (j + 3 < list.get(i).length && i - 3 >= 0 && list.get(i - 1)[j + 1] == 'M' && list.get(i - 2)[j + 2] == 'A'
                && list.get(i - 3)[j + 3] == 'S') {
            sum++;
        }
        if (j + 3 < list.get(i).length && i + 3 < list.size() && list.get(i + 1)[j + 1] == 'M'
                && list.get(i + 2)[j + 2] == 'A'
                && list.get(i + 3)[j + 3] == 'S') {
            sum++;
        }
        if (j - 3 >= 0 && i + 3 < list.size() && list.get(i + 1)[j - 1] == 'M' && list.get(i + 2)[j - 2] == 'A'
                && list.get(i + 3)[j - 3] == 'S') {
            sum++;
        }
        return sum;
    }

}