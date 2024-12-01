import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        try (Scanner in = new Scanner(Paths.get("./day1/input.txt"))) {
            while (in.hasNext()) {
                String[] pair = in.nextLine().split("   ");
                left.add(Integer.parseInt(pair[0]));
                right.add(Integer.parseInt(pair[1]));
                if(!map.containsKey(right.getLast())){
                    map.put(right.getLast(), 1);
                }else{
                    int cnt = map.get(right.getLast());
                    cnt++;
                    map.put(right.getLast(), cnt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        

        int sum = 0;
        for (int i = 0; i < left.size(); i++) {
            int l = left.get(i);
            if (map.containsKey(l)) {
                sum += (l * map.get(l));
            }
        }
        System.out.println(sum);
    }
}