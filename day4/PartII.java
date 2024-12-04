import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartII {
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
                if (list.get(i)[j] == 'A') {
                    int tmp = countXMAS(list, i, j);
                    sum += tmp;
                }
            }
        }
        System.out.println(sum);
    }

    private static int countXMAS(List<char[]> list, int i, int j) {
        int sum = 0;
        if (j - 1 >= 0 && i - 1 >= 0 && j + 1 < list.get(i).length && i + 1 < list.size()
                && list.get(i - 1)[j - 1] == 'M' && list.get(i + 1)[j + 1] == 'S'
                && list.get(i - 1)[j + 1] == 'M' && list.get(i + 1)[j - 1] == 'S') {
            sum++;
        }
        else if (j - 1 >= 0 && i - 1 >= 0 && j + 1 < list.get(i).length && i + 1 < list.size()
                && list.get(i - 1)[j - 1] == 'M' && list.get(i + 1)[j + 1] == 'S'
                && list.get(i - 1)[j + 1] == 'S' && list.get(i + 1)[j - 1] == 'M') {
            sum++;
        }
        else if (j - 1 >= 0 && i - 1 >= 0 && j + 1 < list.get(i).length && i + 1 < list.size()
                && list.get(i - 1)[j - 1] == 'S' && list.get(i + 1)[j + 1] == 'M'
                && list.get(i - 1)[j + 1] == 'S' && list.get(i + 1)[j - 1] == 'M') {
            sum++;
        }
        else if (j - 1 >= 0 && i - 1 >= 0 && j + 1 < list.get(i).length && i + 1 < list.size()
                && list.get(i - 1)[j - 1] == 'S' && list.get(i + 1)[j + 1] == 'M'
                && list.get(i - 1)[j + 1] == 'M' && list.get(i + 1)[j - 1] == 'S') {
            sum++;
        }
        return sum;
    }

}