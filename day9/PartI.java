import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        String diskMap = "";
        try (Scanner in = new Scanner(Paths.get("./day9/input.txt"))) {
            while (in.hasNext()) {
                String txt = in.nextLine();
                diskMap = txt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Integer> files = new ArrayList<>();
        // System.out.println(diskMap);

        int id = 0;
        for (int i = 0; i < diskMap.length(); i++) {
            if (i % 2 == 0) {
                int tmp = Integer.parseInt(String.valueOf(diskMap.charAt(i)));
                for (int j = 0; j < tmp; j++) {
                    files.add(id);
                }
                id++;
            } else {
                int tmp = Integer.parseInt(String.valueOf(diskMap.charAt(i)));
                for (int j = 0; j < tmp; j++) {
                    files.add(-1);
                }
            }
        }
        // System.out.println(files);
        compacingBlock(files);
        // System.out.println(files);

        long sum = 0;
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i) >= 0) {
                sum += i * files.get(i);
            }
        }
        System.out.println(sum);
    }

    private static void compacingBlock(List<Integer> files) {
        int left = 0;
        int right = files.size() - 1;
        while (left < right) {
            int tmpId = files.get(left);
            if (tmpId < 0) {
                while (files.get(right) < 0) {
                    right--;
                }
                int idTmp = files.get(right);
                files.set(left, idTmp);
                files.set(right, tmpId);
                right--;
            }
            left++;
        }
    }
}