import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<char[]> map = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day12/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                char[] line = txt.toCharArray();
                map.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean[][] visited = new boolean[map.size()][map.get(0).length];
        long sum = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length; j++) {
                char cur = map.get(i)[j];
                if (!visited[i][j]) {
                    long cnt = countPerimeter(map, cur, i, j, visited);
                    sum += cnt;
                }
            }
        }
        System.out.println(sum);
    }

    private static long countPerimeter(List<char[]> map, char cur, int i, int j, boolean[][] visited) {
        long count = 0;
        long sum = 0;
        List<int[]> q = new ArrayList<>();
        q.add(new int[] { i, j });

        sum++;
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int[] p = q.remove(0);
            int row = p[0];
            int col = p[1];

            if (row - 1 >= 0) {
                if (map.get(row - 1)[col] != cur) {
                    count++;
                } else if (map.get(row - 1)[col] == cur && !visited[row - 1][col]) {
                    q.add(new int[] { row - 1, col });
                    visited[row - 1][col] = true;
                    sum++;
                }
            } else {
                count++;
            }

            if (col - 1 >= 0) {
                if (map.get(row)[col - 1] != cur) {
                    count++;
                } else if (map.get(row)[col - 1] == cur && !visited[row][col - 1]) {
                    q.add(new int[] { row, col - 1 });
                    visited[row][col - 1] = true;
                    sum++;
                }
            } else {
                count++;
            }
            if (row + 1 < map.size()) {
                if (map.get(row + 1)[col] != cur) {
                    count++;
                } else if (map.get(row + 1)[col] == cur && !visited[row + 1][col]) {
                    q.add(new int[] { row + 1, col });
                    visited[row + 1][col] = true;
                    sum++;
                }
            } else {
                count++;
            }
            if (col + 1 < map.get(0).length) {
                if (map.get(row)[col + 1] != cur) {
                    count++;
                } else if (map.get(row)[col + 1] == cur && !visited[row][col + 1]) {
                    q.add(new int[] { row, col + 1 });
                    visited[row][col + 1] = true;
                    sum++;
                }
            } else {
                count++;
            }
        }
        return sum * count;
    }

}