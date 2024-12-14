import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        int[][] map = new int[103][101];
        // int[][] map = new int[7][11];
        List<int[]> p = new ArrayList<>();
        List<int[]> v = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day14/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                String[] line = txt.split(" ");
                String[] pStr = line[0].substring(2).split(",");
                String[] vStr = line[1].substring(2).split(",");
                int[] pp = new int[] { Integer.parseInt(pStr[1]), Integer.parseInt(pStr[0]) };
                int[] vv = new int[] { Integer.parseInt(vStr[0]), Integer.parseInt(vStr[1]) };
                p.add(pp);
                v.add(vv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < p.size(); i++) {
            int[] pp = p.get(i);
            int[] vv = v.get(i);
            simulate100(map, pp, vv, map.length, map[0].length);
        }
        int[] quad = new int[4];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(i < map.length / 2 && j < map[0].length / 2){
                    quad[0] += map[i][j];
                }
                if(i < map.length / 2 && j > map[0].length / 2){
                    quad[1] += map[i][j];
                }
                if(i > map.length / 2 && j < map[0].length / 2){
                    quad[2] += map[i][j];
                }
                if(i > map.length / 2 && j > map[0].length / 2){
                    quad[3] += map[i][j];
                }
            }
        }
        
        double sum = quad[0] * quad[1] * quad[2] * quad[3];

        System.out.println(new BigDecimal(sum));
    }

    private static void simulate100(int[][] map, int[] p, int[] v, int m, int n) {
        for (int i = 0; i < 100; i++) {
            p[0] = (p[0] + v[1]);
            p[1] = (p[1] + v[0]);
            if (p[0] < 0) {
                p[0] = m + p[0];
            } else if (p[0] >= m) {
                p[0] = p[0] % m;
            }
            if (p[1] < 0) {
                p[1] = n + p[1];
            } else if (p[1] >= n) {
                p[1] = p[1] % n;
            }

        }

        if (p[0] != m / 2 || p[1] != n / 2) {
            map[p[0]][p[1]] += 1;
        }
    }
}