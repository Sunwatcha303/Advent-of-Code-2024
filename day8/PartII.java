import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PartII {
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
            if(!antinodes.contains(tile1)){
                antinodes.add(tile1);
                cnt++;
            }
            for (int j = i + 1; j < list.size(); j++) {
                Tile tile2 = list.get(j);
                if(!antinodes.contains(tile2)){
                    antinodes.add(tile2);
                    cnt++;
                }
                int rowDiff = tile1.row - tile2.row;
                int colDiff = tile1.col - tile2.col;
                int rowDiffabs = Math.abs(tile1.row - tile2.row);
                int colDiffabs = Math.abs(tile1.col - tile2.col);
                int prevRowDiffabs = rowDiffabs;
                int prevColDiffabs = colDiffabs;
                if (rowDiff < 0 && colDiff <= 0) {
                    // left top
                    while (tile1.row - rowDiffabs >= 0 && tile1.col - colDiffabs >= 0) {
                        Tile tmp = new Tile(tile1.row - rowDiffabs, tile1.col - colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                        rowDiffabs += prevRowDiffabs;
                        colDiffabs += prevColDiffabs;
                    }
                    rowDiffabs = prevRowDiffabs;
                    colDiffabs = prevColDiffabs;
                    // right bottom
                    while (tile2.row + rowDiffabs < rMap && tile2.col + colDiffabs < cMap) {
                        Tile tmp = new Tile(tile2.row + rowDiffabs, tile2.col + colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                        rowDiffabs += prevRowDiffabs;
                        colDiffabs += prevColDiffabs;
                    }
                } else if (rowDiff <= 0 && colDiff > 0) {
                    // right top
                    while (tile1.row - rowDiffabs >= 0 && tile1.col + colDiffabs < cMap) {
                        Tile tmp = new Tile(tile1.row - rowDiffabs, tile1.col + colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                        rowDiffabs += prevRowDiffabs;
                        colDiffabs += prevColDiffabs;
                    }
                    rowDiffabs = prevRowDiffabs;
                    colDiffabs = prevColDiffabs;
                    // left bottom
                    while (tile2.row + rowDiffabs < rMap && tile2.col - colDiffabs >= 0) {
                        Tile tmp = new Tile(tile2.row + rowDiffabs, tile2.col - colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                        rowDiffabs += prevRowDiffabs;
                        colDiffabs += prevColDiffabs;
                    }
                } else if (rowDiff > 0 && colDiff >= 0) {
                    // right bottom
                    while (tile1.row + rowDiffabs < rMap && tile1.col + colDiffabs < cMap) {
                        Tile tmp = new Tile(tile1.row + rowDiffabs, tile1.col + colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                        rowDiffabs += prevRowDiffabs;
                        colDiffabs += prevColDiffabs;
                    }
                    rowDiffabs = prevRowDiffabs;
                    colDiffabs = prevColDiffabs;
                    // left up
                    while (tile2.row - rowDiffabs >= 0 && tile2.col - colDiffabs >= 0) {
                        Tile tmp = new Tile(tile2.row - rowDiffabs, tile2.col - colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                        rowDiffabs += prevRowDiffabs;
                        colDiffabs += prevColDiffabs;
                    }
                } else if (rowDiff >= 0 && colDiff < 0) {
                    // left bottom
                    while (tile1.row + rowDiffabs < rowDiffabs && tile1.col - colDiffabs >= 0) {
                        Tile tmp = new Tile(tile1.row + rowDiffabs, tile1.col - colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                        rowDiffabs += prevRowDiffabs;
                        colDiffabs += prevColDiffabs;
                    }
                    rowDiffabs = prevRowDiffabs;
                    colDiffabs = prevColDiffabs;
                    // right up
                    while (tile2.row + rowDiffabs < rMap && tile2.col + colDiffabs < cMap) {
                        Tile tmp = new Tile(tile2.row + rowDiffabs, tile2.col + colDiffabs);
                        if (!antinodes.contains(tmp)) {
                            antinodes.add(tmp);
                            cnt++;
                        }
                        rowDiffabs += prevRowDiffabs;
                        colDiffabs += prevColDiffabs;
                    }
                }
            }
        }
        return cnt;
    }
}