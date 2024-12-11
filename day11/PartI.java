import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day11/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                String[] line = txt.split(" ");
                for (String s : line) {
                    list.add(s);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // long sum = 0;
        for (int i = 0; i < 25; i++) {
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                String str = list.get(j);
                // System.out.println(str);
                long num = Long.parseLong(str);
                if (num == 0) {
                    tmp.add("1");
                } else if (str.length() % 2 == 0) {
                    int mid = str.length() / 2;
                    tmp.add(String.valueOf(Long.parseLong(str.substring(0, mid))));
                    tmp.add(String.valueOf(Long.parseLong(str.substring(mid, str.length()))));
                } else {
                    tmp.add(String.valueOf(num * 2024));
                }
            }
            list = tmp;
            // System.out.println(list);
        }
        System.out.println(list.size());
    }

}