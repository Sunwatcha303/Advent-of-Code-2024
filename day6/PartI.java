import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<char[]> map = new ArrayList<>();
        PositionGuard posGuard = null;
        try (Scanner in = new Scanner(Paths.get("./day6/input.txt"))) {
            int i = 0;
            while (in.hasNext()) {
                String txt = in.nextLine();
                char[] tmp = txt.toCharArray();
                map.add(tmp);
                for (int j = 0; j < tmp.length; j++) {
                    if (tmp[j] == '<') {
                        posGuard = new PositionGuard(i, j, 0);
                    }
                    if (tmp[j] == '^') {
                        posGuard = new PositionGuard(i, j, 1);
                    }
                    if (tmp[j] == '>') {
                        posGuard = new PositionGuard(i, j, 2);
                    }
                    if (tmp[j] == 'v') {
                        posGuard = new PositionGuard(i, j, 3);
                    }
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(posGuard);

        int dist = getDistance(posGuard, map);
        System.out.println(dist);
    }

    private static int getDistance(PositionGuard posGuard, List<char[]> map) {
        int cnt = 0;
        map.get(posGuard.row)[posGuard.col] = 'X';
        cnt++;
        while (posGuard.row >= 0 && posGuard.row < map.size() && posGuard.col >= 0
                && posGuard.col < map.get(0).length) {
            int row = posGuard.row;
            int col = posGuard.col;
            int cur = posGuard.curState;
            if (cur == 0) {
                if (col - 1 >= 0 && map.get(row)[col - 1] == '#') {
                    posGuard.curState = 1;
                } else {
                    posGuard.col = col - 1;
                }
            } else if (cur == 1) {
                if (row - 1 >= 0 && map.get(row - 1)[col] == '#') {
                    posGuard.curState = 2;
                } else {
                    posGuard.row = row - 1;
                }
            } else if (cur == 2) {
                if (col + 1 < map.get(0).length && map.get(row)[col + 1] == '#') {
                    posGuard.curState = 3;
                } else {
                    posGuard.col = col + 1;
                }
            } else if (cur == 3) {
                if (row + 1 < map.size() && map.get(row + 1)[col] == '#') {
                    posGuard.curState = 0;
                } else {
                    posGuard.row = row + 1;
                }
            }
            if (posGuard.row >= 0 && posGuard.row < map.size() && posGuard.col >= 0
                    && posGuard.col < map.get(0).length) {
                if (map.get(posGuard.row)[posGuard.col] != 'X') {
                    cnt++;
                    map.get(posGuard.row)[posGuard.col] = 'X';
                }
            }
        }
        return cnt;
    }
}

class PositionGuard {
    int row;
    int col;
    int curState;

    PositionGuard(int row, int col, int curState) {
        this.row = row;
        this.col = col;
        this.curState = curState;
    }

    public String toString() {
        return "row: " + row + ", col: " + col + ", cur: " + curState;
    }
}