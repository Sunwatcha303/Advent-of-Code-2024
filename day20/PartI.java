import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {

    public static void main(String[] args) {
        List<String[]> map = new ArrayList<>();
        int[] posStart = new int[2];
        int[] posEnd = new int[2];
        try (Scanner in = new Scanner(Paths.get("./day20/input.txt"))) {
            int i = 0;
            while (in.hasNext()) {
                String txt = in.nextLine();
                String[] line = txt.split("");
                map.add(line);
                for (int j = 0; j < line.length; j++) {
                    if (line[j].equals("S")) {
                        posStart[0] = i;
                        posStart[1] = j;
                    } else if (line[j].equals("E")) {
                        posEnd[0] = i;
                        posEnd[1] = j;
                    }
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<int[]> path = new ArrayList<>();
        long sum = 0;
        long dist = findPath(map, posStart, posEnd, path);
        for (int[] p : path) {
            sum += isSaveOver100(map, p, posEnd, dist);
        }
        System.out.println(sum);
    }

    private static long isSaveOver100(List<String[]> map, int[] p, int[] posEnd, long dist) {
        long save = 0;
        int r = p[0];
        int c = p[1];
        if (r - 2 >= 0 && map.get(r - 1)[c].equals("#") && !map.get(r - 2)[c].equals("#")) {
            long s = Long.parseLong(map.get(r - 2)[c]) - Long.parseLong(map.get(r)[c]) - 2;
            if (s >= 100) {
                save++;
            }
        }
        if (r + 2 < map.size() && map.get(r + 1)[c].equals("#") && !map.get(r + 2)[c].equals("#")) {
            long s = Long.parseLong(map.get(r + 2)[c]) - Long.parseLong(map.get(r)[c]) - 2;
            if (s >= 100) {
                save++;
            }
        }
        if (c - 2 >= 0 && map.get(r)[c - 1].equals("#") && !map.get(r)[c - 2].equals("#")) {
            long s = Long.parseLong(map.get(r)[c - 2]) - Long.parseLong(map.get(r)[c]) - 2;
            if (s >= 100) {
                save++;
            }
        }
        if (c + 2 < map.get(0).length && map.get(r)[c + 1].equals("#") && !map.get(r)[c + 2].equals("#")) {
            long s = Long.parseLong(map.get(r)[c + 2]) - Long.parseLong(map.get(r)[c]) - 2;
            if (s >= 100) {
                save++;
            }
        }
        return save;
    }

    private static long findPath(List<String[]> map, int[] posStart, int[] posEnd, List<int[]> path) {
        int r = posStart[0];
        int c = posStart[1];
        long dist = 0;
        boolean[][] visited = new boolean[map.size()][map.get(0).length];
        visited[r][c] = true;
        while (!map.get(r)[c].equals("E")) {
            map.get(r)[c] = String.valueOf(dist);
            if (!visited[r - 1][c] && !map.get(r - 1)[c].equals("#")) {
                r--;
                dist++;
            } else if (!visited[r][c + 1] && !map.get(r)[c + 1].equals("#")) {
                c++;
                dist++;
            } else if (!visited[r + 1][c] && !map.get(r + 1)[c].equals("#")) {
                r++;
                dist++;
            } else if (!visited[r][c - 1] && !map.get(r)[c - 1].equals("#")) {
                c--;
                dist++;
            }
            visited[r][c] = true;
            path.add(new int[] { r, c });
        }
        map.get(r)[c] = String.valueOf(dist);
        return dist;
    }
}