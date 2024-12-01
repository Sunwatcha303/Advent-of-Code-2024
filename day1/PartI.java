import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        try (Scanner in = new Scanner(Paths.get("./day1/input.txt"))) {
            while (in.hasNext()) {
                String[] pair = in.nextLine().split("   ");
                left.add(Integer.parseInt(pair[0]));
                right.add(Integer.parseInt(pair[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);
        // System.out.println(left);
        // System.out.println(right);
        int sum = 0;
        for(int i=0;i<left.size();i++){
            sum += Math.abs(left.get(i) - right.get(i));
        }
        System.out.println(sum);
    }
}