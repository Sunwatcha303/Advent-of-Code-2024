import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        List<char[]> map = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day12/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                char[] line = txt.toCharArray();
                char[] newArr = new char[line.length + 2];
                Arrays.fill(newArr, '.');
                System.arraycopy(line, 0, newArr, 1, line.length);
                map.add(newArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        char[] tmp = new char[map.get(0).length];
        Arrays.fill(tmp, '.');
        map.addFirst(tmp);
        tmp = new char[map.get(0).length];
        Arrays.fill(tmp, '.');
        map.add(tmp);

        boolean[][] visited = new boolean[map.size() + 1][map.get(0).length + 1];
        long sum = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length; j++) {
                char cur = map.get(i)[j];
                if (cur != '.' && !visited[i][j]) {
                    long cnt = countPerimeter(map, cur, i, j, visited);
                    sum += cnt;
                }
            }
        }
        System.out.println(sum);
    }

    private static long countPerimeter(List<char[]> map, char cur, int i, int j, boolean[][] visited) {
        int count = 0;
        long sum = 0;
        List<int[]> q = new ArrayList<>();

        q.add(new int[] { i, j });
        visited[i][j] = true;
        sum++;

        while (!q.isEmpty()) {
            int[] p = q.remove(0);
            int row = p[0];
            int col = p[1];

            if (row - 1 >= 0 && col - 1 >= 0) {
                // +-
                // | A
                if ((map.get(row - 1)[col] != cur && map.get(row)[col - 1] != cur)) {
                    count++;
                }
                // |
                // -+
                // A
                if ((map.get(row - 1)[col] == cur && map.get(row)[col - 1] == cur
                        && map.get(row - 1)[col - 1] != cur)) {
                    count++;
                }
            }

            if (row - 1 >= 0 && col + 1 < map.get(0).length) {
                // -+
                // A|
                if ((map.get(row - 1)[col] != cur && map.get(row)[col + 1] != cur)) {
                    count++;
                }
                // |
                // +-
                // A
                if ((map.get(row - 1)[col] == cur && map.get(row)[col + 1] == cur
                        && map.get(row - 1)[col + 1] != cur)) {
                    count++;
                }
            }

            if (row + 1 < map.size() && col - 1 >= 0) {
                // |A
                // +-
                if ((map.get(row + 1)[col] != cur && map.get(row)[col - 1] != cur)) {
                    count++;
                }
                // A
                // -+
                // |
                if ((map.get(row + 1)[col] == cur && map.get(row)[col - 1] == cur
                        && map.get(row + 1)[col - 1] != cur)) {
                    count++;
                }
            }

            if (row + 1 < map.size() && col + 1 < map.get(0).length) {
                // A|
                // -+
                if ((map.get(row + 1)[col] != cur && map.get(row)[col + 1] != cur)) {
                    count++;
                }
                // A
                // +-
                // |
                if ((map.get(row + 1)[col] == cur && map.get(row)[col + 1] == cur
                        && map.get(row + 1)[col + 1] != cur)) {
                    count++;
                }
            }

            if (row - 1 >= 0) {
                if (map.get(row - 1)[col] == cur && !visited[row - 1][col]) {
                    q.add(new int[] { row - 1, col });
                    visited[row - 1][col] = true;
                    sum++;
                }
            }

            if (col - 1 >= 0) {
                if (map.get(row)[col - 1] == cur && !visited[row][col - 1]) {
                    q.add(new int[] { row, col - 1 });
                    visited[row][col - 1] = true;
                    sum++;
                }
            }
            if (row + 1 < map.size()) {
                if (map.get(row + 1)[col] == cur && !visited[row + 1][col]) {
                    q.add(new int[] { row + 1, col });
                    visited[row + 1][col] = true;
                    sum++;
                }
            }
            if (col + 1 < map.get(0).length) {
                if (map.get(row)[col + 1] == cur && !visited[row][col + 1]) {
                    q.add(new int[] { row, col + 1 });
                    visited[row][col + 1] = true;
                    sum++;
                }
            }
        }
        return sum * count;
    }

}