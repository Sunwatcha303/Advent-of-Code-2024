import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PartII {
    public static void main(String[] args) {
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
        for (int sec = 1; sec <= 10000; sec++) {
            int[][] map = new int[103][101];
            for (int i = 0; i < p.size(); i++) {
                int[] pp = p.get(i).clone();
                int[] vv = v.get(i).clone();
                simulate100(map, pp, vv, map.length, map[0].length, sec);
            }
            saveArrayAsImage(map, sec);
        }
    }

    private static void simulate100(int[][] map, int[] p, int[] v, int m, int n, int sec) {
        for (int i = 0; i < sec; i++) {
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
        map[p[0]][p[1]] += 1;
    }

    public static void saveArrayAsImage(int[][] map, int i) {
        int width = map[0].length;
        int height = map.length;

        // Create a BufferedImage
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Map 2D array values to the image pixels
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int value = map[y][x];
                int color = (value >= 1) ? 0xFFFFFF : 0x000000;
                image.setRGB(x, y, color);
            }
        }

        // Save the image to a file
        try {
            File outputDir = new File("./day14/output/");
            outputDir.mkdirs(); // Ensure the output directory exists

            File outputFile = new File(outputDir, i + ".png");
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image saved: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}