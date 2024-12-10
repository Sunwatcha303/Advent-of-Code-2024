import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<char[]> map = new ArrayList<>();
        List<int[]> posStart = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day10/input.txt"))) {
            int i = 0;
            while (in.hasNext()) {
                String txt = in.nextLine();
                char[] line = txt.toCharArray();
                map.add(line);
                for (int j = 0; j < line.length; j++) {
                    if (line[j] == '0') {
                        posStart.add(new int[] { i, j });
                    }
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long sum = 0;
        for (int[] pos : posStart) {
            int cnt = countTailHead(pos[0], pos[1], map);
            sum += cnt;
        }
        System.out.println(sum);
    }

    private static int countTailHead(int rStart, int cStart, List<char[]> map) {
        int cnt = 0;
        List<int[]> q = new ArrayList<>();
        q.add(new int[] { rStart, cStart });
        List<Pair> histry = new ArrayList<>();

        while (!q.isEmpty()) {
            int[] p = q.remove(0);
            int r = p[0];
            int c = p[1];
            int cur = map.get(r)[c];
            if (map.get(r)[c] == '9' && !histry.contains(new Pair(r, c))) {
                cnt++;
                histry.add(new Pair(r, c));
                continue;
            }

            if (r - 1 >= 0 && map.get(r - 1)[c] == cur + 1) {
                q.add(new int[] { r - 1, c });
            }
            if (c + 1 < map.get(0).length && map.get(r)[c + 1] == cur + 1) {
                q.add(new int[] { r, c + 1 });
            }
            if (r + 1 < map.size() && map.get(r + 1)[c] == cur + 1) {
                q.add(new int[] { r + 1, c });
            }
            if (c - 1 >= 0 && map.get(r)[c - 1] == cur + 1) {
                q.add(new int[] { r, c - 1 });
            }
        }
        return cnt;
    }
}

class Pair {
    int r;
    int c;

    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + r;
        result = prime * result + c;
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
        Pair other = (Pair) obj;
        if (r != other.r)
            return false;
        if (c != other.c)
            return false;
        return true;
    }

}