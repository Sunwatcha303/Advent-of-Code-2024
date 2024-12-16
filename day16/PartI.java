import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<char[]> map = new ArrayList<>();
        int[] posStart = new int[2];
        int[] posEnd = new int[2];
        try (Scanner in = new Scanner(Paths.get("./day16/input.txt"))) {
            int i = 0;
            while (in.hasNext()) {
                String txt = in.nextLine();
                char[] line = txt.toCharArray();
                map.add(line);
                for (int j = 0; j < line.length; j++) {
                    if (line[j] == 'S') {
                        posStart[0] = i;
                        posStart[1] = j;
                    } else if (line[j] == 'E') {
                        posEnd[0] = i;
                        posEnd[1] = j;
                    }
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long sum = 0;
        long tmp = shortestPath(map, posStart, posEnd);
        sum = tmp;
        System.out.println(new BigDecimal(sum));
    }

    private static long shortestPath(List<char[]> map, int[] posStart, int[] posEnd) {
        Queue<Tile> q = new PriorityQueue<>();
        boolean[][] visited = new boolean[map.size()][map.get(0).length];
        q.add(new Tile(posStart[0], posEnd[0], Direction.RIGHT, 0));
        while (!q.isEmpty()) {
            Tile p = q.poll();
            int r = p.row;
            int c = p.col;
            Direction d = p.dir;
            long s = p.score;
            if (r == posEnd[0] && c == posEnd[1]) {
                return s;
            }

            if (!visited[r - 1][c] && map.get(r - 1)[c] != '#') {
                if (d != Direction.UP) {
                    q.add(new Tile(r - 1, c, Direction.UP, s + 1001));
                } else {
                    q.add(new Tile(r - 1, c, d, s + 1));
                }
                visited[r - 1][c] = true;
            }
            if (!visited[r][c + 1] && map.get(r)[c + 1] != '#') {
                if (d != Direction.RIGHT) {
                    q.add(new Tile(r, c + 1, Direction.RIGHT, s + 1001));
                } else {
                    q.add(new Tile(r, c + 1, d, s + 1));
                }
                visited[r][c + 1] = true;
            }
            if (!visited[r + 1][c] && map.get(r + 1)[c] != '#') {
                if (d != Direction.DOWN) {
                    q.add(new Tile(r + 1, c, Direction.DOWN, s + 1001));
                } else {
                    q.add(new Tile(r + 1, c, d, s + 1));
                }
                visited[r + 1][c] = true;
            }
            if (!visited[r][c - 1] && map.get(r)[c - 1] != '#') {
                if (d != Direction.LEFT) {
                    q.add(new Tile(r, c - 1, Direction.LEFT, s + 1001));
                } else {
                    q.add(new Tile(r, c - 1, d, s + 1));
                }
                visited[r - 1][c] = true;
            }
        }
        return 0;
    }

}

enum Direction {
    UP, RIGHT, DOWN, LEFT
}

@SuppressWarnings("rawtypes")
class Tile implements Comparable {
    int row;
    int col;
    Direction dir;
    long score;

    Tile(int r, int c, Direction d, long s) {
        row = r;
        col = c;
        dir = d;
        score = s;
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(score, ((Tile) o).score);
    }
}