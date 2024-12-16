import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class PartII {
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
        long tmp = shortestPath(map, posStart, posEnd, new HashSet<Point>());
        sum = tmp;
        System.out.println(new BigDecimal(sum));
    }

    private static long shortestPath(List<char[]> map, int[] posStart, int[] posEnd, Set<Point> set) {
        Queue<Tile> q = new PriorityQueue<>();
        Map<Tile, Long> bestScores = new HashMap<>();
        Tile tStart = new Tile(posStart[0], posEnd[0], Direction.RIGHT, 0);
        tStart.path.add(tStart);
        q.add(tStart);
        long bestScore = Long.MAX_VALUE;
        while (!q.isEmpty()) {
            Tile p = q.poll();
            int r = p.row;
            int c = p.col;
            Direction d = p.dir;
            long s = p.score;
            if (r == posEnd[0] && c == posEnd[1]) {
                if (s <= bestScore) {
                    bestScore = s;
                    p.path.stream().forEach(t -> set.add(new Point(t.row, t.col)));
                }
                continue;
            }
            if (bestScores.containsKey(p) && bestScores.get(p) < p.score) {
                continue;
            }
            bestScores.put(p, p.score);

            if (!p.path.contains(new Tile(r - 1, c, d, s)) && map.get(r - 1)[c] != '#') {
                if (d != Direction.UP) {
                    Tile tmp = new Tile(r - 1, c, Direction.UP, s + 1001);
                    tmp.path.addAll(p.path);
                    tmp.path.add(tmp);
                    q.add(tmp);
                } else {
                    Tile tmp = new Tile(r - 1, c, d, s + 1);
                    tmp.path.addAll(p.path);
                    tmp.path.add(tmp);
                    q.add(tmp);
                }
            }
            if (!p.path.contains(new Tile(r, c + 1, d, s)) && map.get(r)[c + 1] != '#') {
                if (d != Direction.RIGHT) {
                    Tile tmp = new Tile(r, c + 1, Direction.RIGHT, s + 1001);
                    tmp.path.addAll(p.path);
                    tmp.path.add(tmp);
                    q.add(tmp);
                } else {
                    Tile tmp = new Tile(r, c + 1, d, s + 1);
                    tmp.path.addAll(p.path);
                    tmp.path.add(tmp);
                    q.add(tmp);
                }
            }
            if (!p.path.contains(new Tile(r + 1, c, d, s)) && map.get(r + 1)[c] != '#') {
                if (d != Direction.DOWN) {
                    Tile tmp = new Tile(r + 1, c, Direction.DOWN, s + 1001);
                    tmp.path.addAll(p.path);
                    tmp.path.add(tmp);
                    q.add(tmp);
                } else {
                    Tile tmp = new Tile(r + 1, c, d, s + 1);
                    tmp.path.addAll(p.path);
                    tmp.path.add(tmp);
                    q.add(tmp);
                }
            }
            if (!p.path.contains(new Tile(r, c - 1, d, s)) && map.get(r)[c - 1] != '#') {
                if (d != Direction.LEFT) {
                    Tile tmp = new Tile(r, c - 1, Direction.LEFT, s + 1001);
                    tmp.path.addAll(p.path);
                    tmp.path.add(tmp);
                    q.add(tmp);
                } else {
                    Tile tmp = new Tile(r, c - 1, d, s + 1);
                    tmp.path.addAll(p.path);
                    tmp.path.add(tmp);
                    q.add(tmp);
                }
            }
        }
        return set.size();
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
    List<Tile> path = new ArrayList<>();

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + col;
        result = prime * result + ((dir == null) ? 0 : dir.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (row != other.row)
            return false;
        if (col != other.col)
            return false;
        if (dir != other.dir)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tile [row=" + row + ", col=" + col + ", dir=" + dir + ", score=" + score + "]";
    }
}

class Point {
    int row;
    int col;

    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + col;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (row != other.row)
            return false;
        if (col != other.col)
            return false;
        return true;
    }
}