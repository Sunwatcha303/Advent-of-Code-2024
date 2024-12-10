import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PartII {
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
        List<PositionGuard> list = new ArrayList<>();
        List<char[]> newMap = new ArrayList<>();
        for (int j = 0; j < map.size(); j++) {
            char[] copyChar = Arrays.copyOf(map.get(j), map.get(j).length);
            newMap.add(copyChar);
        }
        PositionGuard newPosGuard = new PositionGuard(posGuard.row, posGuard.col, posGuard.curState);
        int dist = getDistance(newPosGuard, newMap, list);
        System.out.println(dist);

        int sum = 0;
        for (int i = 0; i < dist - 1; i++) {
            List<char[]> copyMap = new ArrayList<>();
            for (int j = 0; j < map.size(); j++) {
                char[] copyChar = Arrays.copyOf(map.get(j), map.get(j).length);
                copyMap.add(copyChar);
            }
            PositionGuard copyPosGuard = new PositionGuard(posGuard.row, posGuard.col, posGuard.curState);
            PositionGuard oldPath = list.get(i);
            copyMap.get(oldPath.row)[oldPath.col] = '#';
            if (isLoop(copyMap, copyPosGuard)) {
                sum++;
            }
        }
        System.out.println(sum);
    }

    private static boolean isLoop(List<char[]> map, PositionGuard posGuard) {
        long startTime = System.currentTimeMillis();
        long timeLimit = 100;
        map.get(posGuard.row)[posGuard.col] = 'X';
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
                    map.get(posGuard.row)[posGuard.col] = 'X';
                }
            }
            if (System.currentTimeMillis() - startTime > timeLimit) {
                // System.out.println("Loop exceeded time limit and was stopped.");
                return true;
            }
        }
        return false;
    }

    private static int getDistance(PositionGuard posGuard, List<char[]> map, List<PositionGuard> list) {
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
                    list.add(new PositionGuard(posGuard.row, posGuard.col, posGuard.curState));
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