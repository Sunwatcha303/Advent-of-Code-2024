import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PartI {
    static Queue<Long> q = new PriorityQueue<>();

    public static void main(String[] args) {
        int[][] map = new int[71][71];
        try (Scanner in = new Scanner(Paths.get("./day18/input.txt"))) {
            int i = 1;
            while (in.hasNext()) {
                String txt = in.nextLine();
                String[] line = txt.split(",");

                map[Integer.parseInt(line[1])][Integer.parseInt(line[0])] = 1;
                if (i == 1024) {
                    break;
                }
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long dist = findPath(map, 0, 0, 70, 70);
        System.out.println(dist);
    }

    private static long findPath(int[][] map, int rStart, int cStart, int rEnd, int cEnd) {
        boolean[][] visited = new boolean[rEnd + 1][cEnd + 1];
        List<int[]> q = new ArrayList<>();
        q.add(new int[] { rStart, cStart, 0 });
        visited[rStart][cStart] = true;
        while (!q.isEmpty()) {
            int[] p = q.remove(0);
            int r = p[0];
            int c = p[1];
            int d = p[2];

            if (r == rEnd && c == cEnd) {
                return d;
            }

            if (r - 1 >= 0 && !visited[r - 1][c] && map[r - 1][c] != 1) {
                if (r - 1 == rEnd && c == cEnd) {
                    return d + 1;
                }
                q.add(new int[] { r - 1, c, d + 1 });
                visited[r - 1][c] = true;
            }
            if (c + 1 < map[0].length && !visited[r][c + 1] && map[r][c + 1] != 1) {
                if (r == rEnd && c + 1 == cEnd) {
                    return d + 1;
                }
                q.add(new int[] { r, c + 1, d + 1 });
                visited[r][c + 1] = true;
            }
            if (r + 1 < map.length && !visited[r + 1][c] && map[r + 1][c] != 1) {
                if (r + 1 == rEnd && c == cEnd) {
                    return d + 1;
                }
                q.add(new int[] { r + 1, c, d + 1 });
                visited[r + 1][c] = true;
            }
            if (c - 1 >= 0 && !visited[r][c - 1] && map[r][c - 1] != 1) {
                if (r == rEnd && c - 1 == cEnd) {
                    return d + 1;
                }
                q.add(new int[] { r, c - 1, d + 1 });
                visited[r][c - 1] = true;
            }
        }
        return -1;
    }

}