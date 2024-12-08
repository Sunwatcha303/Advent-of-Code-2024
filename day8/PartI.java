import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<char[]> map = new ArrayList<>();
        Map<Character, List<Tile>> posAntenna = new HashMap<>();
        try (Scanner in = new Scanner(Paths.get("./day8/input.txt"))) {
            int i = 0;
            while (in.hasNext()) {
                String txt = in.nextLine();
                char[] line = txt.toCharArray();
                map.add(line);
                for (int j = 0; j < line.length; j++) {
                    if (line[j] != '.') {
                        if (!posAntenna.containsKey(line[j])) {
                            posAntenna.put(line[j], new ArrayList<>());
                        }
                        posAntenna.get(line[j]).add(new Tile(i, j));
                    }
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Tile> antinodes = new ArrayList<>();
        long sum = 0;
        for (char key : posAntenna.keySet()) {
            int cnt = countAntinode(posAntenna.get(key), map.size(), map.get(0).length, antinodes);
            sum += cnt;
        }
        System.out.println(sum);
    }

    private static int countAntinode(List<Tile> list, int rMap, int cMap, List<Tile> antinodes) {
        int cnt = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            Tile tile1 = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                Tile tile2 = list.get(j);
                int rowDiff = tile1.row - tile2.row;
                int colDiff = tile1.col - tile2.col;
                int rowDiffabs = Math.abs(tile1.row - tile2.row);
                int colDiffabs = Math.abs(tile1.col - tile2.col);
                if (rowDiff < 0 && colDiff <= 0) {
                    // left top
                    if (tile1.row - rowDiffabs >= 0 && tile1.col - colDiffabs >= 0) {
                        Tile tmp = new Tile(tile1.row - rowDiffabs, tile1.col - colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                    }
                    // right bottom
                    if (tile2.row + rowDiffabs < rMap && tile2.col + colDiffabs < cMap) {
                        Tile tmp = new Tile(tile2.row + rowDiffabs, tile2.col + colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                    }
                } else if (rowDiff <= 0 && colDiff > 0) {
                    // right top
                    if (tile1.row - rowDiffabs >= 0 && tile1.col + colDiffabs < cMap) {
                        Tile tmp = new Tile(tile1.row - rowDiffabs, tile1.col + colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                    }
                    // left bottom
                    if (tile2.row + rowDiffabs < rMap && tile2.col - colDiffabs >= 0) {
                        Tile tmp = new Tile(tile2.row + rowDiffabs, tile2.col - colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                    }
                } else if (rowDiff > 0 && colDiff >= 0) {
                    // right bottom
                    if (tile1.row + rowDiffabs < rMap && tile1.col + colDiffabs < cMap) {
                        Tile tmp = new Tile(tile1.row + rowDiffabs, tile1.col + colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                    }
                    // left up
                    if (tile2.row - rowDiffabs >= 0 && tile2.col - colDiffabs >= 0) {
                        Tile tmp = new Tile(tile2.row - rowDiffabs, tile2.col - colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                    }
                } else if (rowDiff >= 0 && colDiff < 0) {
                    // left bottom
                    if (tile1.row + rowDiffabs < rowDiffabs && tile1.col - colDiffabs >= 0) {
                        Tile tmp = new Tile(tile1.row + rowDiffabs, tile1.col - colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                    }
                    // right up
                    if (tile2.row + rowDiffabs < rMap && tile2.col + colDiffabs < cMap) {
                        Tile tmp = new Tile(tile2.row + rowDiffabs, tile2.col + colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}

class Tile {
    int row;
    int col;

    Tile(int i, int j) {
        row = i;
        col = j;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tile tile = (Tile) obj;
        return row == tile.row && col == tile.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public String toString() {
        return "row: " + row + ", col: " + col;
    }
}