import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        double sum = 0;

        try (Scanner in = new Scanner(Paths.get("./day13/input.txt"))) {
            while (in.hasNext()) {
                double[][] matrix = new double[2][3];
                String a = in.nextLine();
                String b = in.nextLine();
                String p = in.nextLine();

                String[] xStr = a.split(", ");
                String[] x1Str = xStr[0].split("\\+");
                String[] x2Str = xStr[1].split("\\+");
                double x1 = Double.parseDouble(x1Str[x1Str.length - 1]);
                double x2 = Double.parseDouble(x2Str[x2Str.length - 1]);

                matrix[0][0] = x1;
                matrix[1][0] = x2;

                String[] yStr = b.split(", ");
                String[] y1Str = yStr[0].split("\\+");
                String[] y2Str = yStr[1].split("\\+");
                double y1 = Double.parseDouble(y1Str[y1Str.length - 1]);
                double y2 = Double.parseDouble(y2Str[y2Str.length - 1]);

                matrix[0][1] = y1;
                matrix[1][1] = y2;

                String[] pStr = p.split(", ");
                String[] p1Str = pStr[0].split("=");
                String[] p2Str = pStr[1].split("=");
                double p1 = Double.parseDouble(p1Str[p1Str.length - 1]);
                double p2 = Double.parseDouble(p2Str[p2Str.length - 1]);

                p1 += 10000000000000.0;
                p2 += 10000000000000.0;
                matrix[0][2] = p1;
                matrix[1][2] = p2;

                double cost = solve(matrix, 2, 3);
                sum += cost;

                if (in.hasNext()) {
                    in.nextLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(new BigDecimal(sum));
    }

    private static double solve(double[][] matrix, int m, int n) {
        double tmp1 = matrix[1][0];
        double tmp2 = matrix[0][0];
        for (int i = 0; i < n; i++) {
            matrix[0][i] *= tmp1;
            matrix[1][i] *= tmp2;
            matrix[1][i] = matrix[0][i] - matrix[1][i];
        }
        double y = matrix[1][2] / matrix[1][1];

        matrix[0][1] *= y;
        double x = (matrix[0][2] - matrix[0][1]) / matrix[0][0];
        double sum = 0;
        if ((x - (long) x) == 0 && (y - (long) y) == 0) {
            sum = (x * 3) + y;
        }
        return sum;
    }
}