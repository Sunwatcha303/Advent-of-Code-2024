import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<char[]> map = new ArrayList<>();
        int[] posStart = new int[2];
        try (Scanner in = new Scanner(Paths.get("./day15/input.txt"))) {
            int i = 0;
            while (in.hasNext()) {
                String txt = in.nextLine();
                if (txt.equals("")) {
                    break;
                }
                char[] line = txt.toCharArray();
                map.add(line);
                for (int j = 0; j < line.length; j++) {
                    if (line[j] == '@') {
                        posStart[0] = i;
                        posStart[1] = j;
                    }
                }
                i++;
            }
            while (in.hasNext()) {
                String txt = in.nextLine();
                for (char c : txt.toCharArray()) {
                    move(map, posStart, c, map.size(), map.get(0).length);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long sum = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).length; j++) {
                if (map.get(i)[j] == 'O') {
                    long tmp = 100 * i + j;
                    sum += tmp;
                }
            }
        }
        System.out.println(new BigDecimal(sum));
    }

    private static void move(List<char[]> map, int[] posStart, char c, int m, int n) {
        int curRow = posStart[0], curCol = posStart[1];
        if (c == '^') {
            while (map.get(curRow)[curCol] != '#' && map.get(curRow)[curCol] != '.') {
                curRow--;
            }
            if (map.get(curRow)[curCol] == '.') {
                while (map.get(curRow + 1)[curCol] == 'O') {
                    map.get(curRow)[curCol] = 'O';
                    map.get(curRow + 1)[curCol] = '.';
                    curRow++;
                }
                map.get(curRow)[curCol] = '@';
                map.get(curRow + 1)[curCol] = '.';
            } else {
                curRow = posStart[0];
            }
        }
        if (c == '>') {
            while (map.get(curRow)[curCol] != '#' && map.get(curRow)[curCol] != '.') {
                curCol++;
            }
            if (map.get(curRow)[curCol] == '.') {
                while (map.get(curRow)[curCol - 1] == 'O') {
                    map.get(curRow)[curCol] = 'O';
                    map.get(curRow)[curCol - 1] = '.';
                    curCol--;
                }
                map.get(curRow)[curCol] = '@';
                map.get(curRow)[curCol - 1] = '.';
            } else {
                curCol = posStart[1];
            }
        }
        if (c == 'v') {
            while (map.get(curRow)[curCol] != '#' && map.get(curRow)[curCol] != '.') {
                curRow++;
            }
            if (map.get(curRow)[curCol] == '.') {
                while (map.get(curRow - 1)[curCol] == 'O') {
                    map.get(curRow)[curCol] = 'O';
                    map.get(curRow - 1)[curCol] = '.';
                    curRow--;
                }
                map.get(curRow)[curCol] = '@';
                map.get(curRow - 1)[curCol] = '.';
            } else {
                curRow = posStart[0];
            }
        }
        if (c == '<') {
            while (map.get(curRow)[curCol] != '#' && map.get(curRow)[curCol] != '.') {
                curCol--;
            }
            if (map.get(curRow)[curCol] == '.') {
                while (map.get(curRow)[curCol + 1] == 'O') {
                    map.get(curRow)[curCol] = 'O';
                    map.get(curRow)[curCol + 1] = '.';
                    curCol++;
                }
                map.get(curRow)[curCol] = '@';
                map.get(curRow)[curCol + 1] = '.';
            } else {
                curCol = posStart[1];
            }
        }

        posStart[0] = curRow;
        posStart[1] = curCol;
    }
}