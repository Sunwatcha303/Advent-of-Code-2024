import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PartII {
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
                char[] newLine = new char[line.length * 2];
                for (int j = 0; j < line.length; j++) {
                    if (line[j] == '#') {
                        newLine[2 * j] = '#';
                        newLine[2 * j + 1] = '#';
                    }
                    if (line[j] == '.') {
                        newLine[2 * j] = '.';
                        newLine[2 * j + 1] = '.';
                    }
                    if (line[j] == 'O') {
                        newLine[2 * j] = '[';
                        newLine[2 * j + 1] = ']';
                    }
                    if (line[j] == '@') {
                        posStart[0] = i;
                        posStart[1] = 2 * j;
                        newLine[2 * j] = '@';
                        newLine[2 * j + 1] = '.';
                    }
                }
                map.add(newLine);
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
                if (map.get(i)[j] == '[') {
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
            if (map.get(curRow - 1)[curCol] == '#') {
                return;
            }
            List<List<Integer>> posBoxs = new ArrayList<>();
            List<Integer[]> p = new ArrayList<>();
            p.add(new Integer[] { curRow, curCol });
            while (!p.isEmpty()) {
                Integer[] q = p.remove(0);
                int row = q[0];
                int col = q[1];
                if (map.get(row - 1)[col] == ']') {
                    Integer[] tmp1 = new Integer[] { row - 1, col };
                    Integer[] tmp2 = new Integer[] { row - 1, col - 1 };
                    p.add(tmp1);
                    p.add(tmp2);
                    List<Integer> tmp11 = Arrays.asList(tmp1[0], tmp1[1]);
                    List<Integer> tmp22 = Arrays.asList(tmp2[0], tmp2[1]);
                    if (!posBoxs.contains(tmp11)) {
                        posBoxs.add(tmp11);
                    }
                    if (!posBoxs.contains(tmp22)) {
                        posBoxs.add(tmp22);
                    }
                } else if (map.get(row - 1)[col] == '[') {
                    Integer[] tmp1 = new Integer[] { row - 1, col };
                    Integer[] tmp2 = new Integer[] { row - 1, col + 1 };
                    p.add(tmp1);
                    p.add(tmp2);
                    List<Integer> tmp11 = Arrays.asList(tmp1[0], tmp1[1]);
                    List<Integer> tmp22 = Arrays.asList(tmp2[0], tmp2[1]);
                    if (!posBoxs.contains(tmp11)) {
                        posBoxs.add(tmp11);
                    }
                    if (!posBoxs.contains(tmp22)) {
                        posBoxs.add(tmp22);
                    }
                }
            }
            
            for (int i = posBoxs.size() - 1; i > 0; i -= 2) {
                Integer[] pos1 = posBoxs.get(i).stream().toArray(Integer[]::new);
                Integer[] pos2 = posBoxs.get(i - 1).stream().toArray(Integer[]::new);
                if (map.get(pos1[0] - 1)[pos1[1]] == '#') {
                    return;
                }
                if (map.get(pos2[0] - 1)[pos2[1]] == '#') {
                    return;
                }
            }

            for (int i = posBoxs.size() - 1; i > 0; i -= 2) {
                Integer[] pos1 = posBoxs.get(i).stream().toArray(Integer[]::new);
                Integer[] pos2 = posBoxs.get(i - 1).stream().toArray(Integer[]::new);
                map.get(pos1[0] - 1)[pos1[1]] = map.get(pos1[0])[pos1[1]];
                map.get(pos2[0] - 1)[pos2[1]] = map.get(pos2[0])[pos2[1]];
                map.get(pos1[0])[pos1[1]] = '.';
                map.get(pos2[0])[pos2[1]] = '.';
            }
            map.get(curRow - 1)[curCol] = '@';
            map.get(curRow)[curCol] = '.';
            curRow--;
        }
        if (c == '>') {
            while (map.get(curRow)[curCol] != '#' && map.get(curRow)[curCol] != '.') {
                curCol++;
            }
            if (map.get(curRow)[curCol] == '.') {
                while (map.get(curRow)[curCol - 1] == ']' || map.get(curRow)[curCol - 1] == '[') {
                    map.get(curRow)[curCol] = map.get(curRow)[curCol - 1];
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
            if (map.get(curRow + 1)[curCol] == '#') {
                return;
            }
            List<List<Integer>> posBoxs = new ArrayList<>();
            List<Integer[]> p = new ArrayList<>();
            p.add(new Integer[] { curRow, curCol });
            while (!p.isEmpty()) {
                Integer[] q = p.remove(0);
                int row = q[0];
                int col = q[1];
                if (map.get(row + 1)[col] == ']') {
                    Integer[] tmp1 = new Integer[] { row + 1, col };
                    Integer[] tmp2 = new Integer[] { row + 1, col - 1 };
                    p.add(tmp1);
                    p.add(tmp2);
                    List<Integer> tmp11 = Arrays.asList(tmp1[0], tmp1[1]);
                    List<Integer> tmp22 = Arrays.asList(tmp2[0], tmp2[1]);
                    if (!posBoxs.contains(tmp11)) {
                        posBoxs.add(tmp11);
                    }
                    if (!posBoxs.contains(tmp22)) {
                        posBoxs.add(tmp22);
                    }
                } else if (map.get(row + 1)[col] == '[') {
                    Integer[] tmp1 = new Integer[] { row + 1, col };
                    Integer[] tmp2 = new Integer[] { row + 1, col + 1 };
                    p.add(tmp1);
                    p.add(tmp2);
                    List<Integer> tmp11 = Arrays.asList(tmp1[0], tmp1[1]);
                    List<Integer> tmp22 = Arrays.asList(tmp2[0], tmp2[1]);
                    if (!posBoxs.contains(tmp11)) {
                        posBoxs.add(tmp11);
                    }
                    if (!posBoxs.contains(tmp22)) {
                        posBoxs.add(tmp22);
                    }
                }
            }

            for (int i = posBoxs.size() - 1; i > 0; i -= 2) {
                Integer[] pos1 = posBoxs.get(i).stream().toArray(Integer[]::new);
                Integer[] pos2 = posBoxs.get(i - 1).stream().toArray(Integer[]::new);
                if (map.get(pos1[0] + 1)[pos1[1]] == '#') {
                    return;
                }
                if (map.get(pos2[0] + 1)[pos2[1]] == '#') {
                    return;
                }
            }

            for (int i = posBoxs.size() - 1; i > 0; i -= 2) {
                Integer[] pos1 = posBoxs.get(i).stream().toArray(Integer[]::new);
                Integer[] pos2 = posBoxs.get(i - 1).stream().toArray(Integer[]::new);
                map.get(pos1[0] + 1)[pos1[1]] = map.get(pos1[0])[pos1[1]];
                map.get(pos2[0] + 1)[pos2[1]] = map.get(pos2[0])[pos2[1]];
                map.get(pos1[0])[pos1[1]] = '.';
                map.get(pos2[0])[pos2[1]] = '.';
            }
            map.get(curRow + 1)[curCol] = '@';
            map.get(curRow)[curCol] = '.';
            curRow++;
        }
        if (c == '<') {
            while (map.get(curRow)[curCol] != '#' && map.get(curRow)[curCol] != '.') {
                curCol--;
            }
            if (map.get(curRow)[curCol] == '.') {
                while (map.get(curRow)[curCol + 1] == ']' || map.get(curRow)[curCol + 1] == '[') {
                    map.get(curRow)[curCol] = map.get(curRow)[curCol + 1];
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