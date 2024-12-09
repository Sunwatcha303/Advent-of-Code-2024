import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartII {
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
        List<Block> files = new ArrayList<>();

        int id = 0;
        for (int i = 0; i < diskMap.length(); i++) {
            if (i % 2 == 0) {
                int tmp = Integer.parseInt(String.valueOf(diskMap.charAt(i)));
                Block block = new Block();
                for (int j = 0; j < tmp; j++) {
                    block.header.add(id);
                }
                files.add(block);
                id++;
            } else {
                int tmp = Integer.parseInt(String.valueOf(diskMap.charAt(i)));
                files.get(id - 1).freeSize = tmp;
            }
        }
        compacingBlock(files);

        long sum = 0;
        int idx = 0;
        for (int i = 0; i < files.size(); i++) {
            Block block = files.get(i);
            for (int j : block.header) {
                sum += j * (idx++);
            }
            for (int j : block.payload) {
                sum += j * (idx++);
            }
            idx += block.freeSize;
        }
        System.out.println(sum);
    }

    private static void compacingBlock(List<Block> files) {
        for (int i = files.size() - 1; i >= 0; i--) {
            Block srcBlock = files.get(i);
            for (int j = 0; j < files.size() && j != i; j++) {
                Block destBlock = files.get(j);
                if (srcBlock.header.size() <= destBlock.freeSize) {
                    for (int k = 0; k < srcBlock.header.size(); k++) {
                        destBlock.payload.add(srcBlock.header.get(k));
                        srcBlock.header.set(k, 0);
                        destBlock.freeSize--;
                    }
                    break;
                }
            }
        }
    }
}

class Block {
    List<Integer> header = new ArrayList<>();
    List<Integer> payload = new ArrayList<>();
    int freeSize;

    public String toString() {
        return header + " " + payload + " size: " + freeSize + "\n";
    }
}