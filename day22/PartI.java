import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day22/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                list.add(Long.parseLong(txt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long sum = 0;
        for (Long l : list) {
            long cur = l;
            for (int i = 0; i < 2000; i++) {
                cur = process(cur);
            }
            sum += cur;
        }
        System.out.println(sum);
    }

    private static long process(Long l) {
        l = ((l ^ (l * 64)) % 16777216);
        l = ((l ^ (l / 32)) % 16777216);
        l = ((l ^ (l * 2048)) % 16777216);
        return l;
    }
}