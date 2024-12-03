import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartII {
    public static void main(String[] args) {
        int sum = 0;
        String reg = "do\\(\\)|don\\'t\\(\\)|mul\\(\\d{1,3},\\d{1,3}\\)";
        Pattern pattern = Pattern.compile(reg);
        boolean isDo = true;
        try (Scanner in = new Scanner(Paths.get("./day3/input.txt"))) {
            while (in.hasNext()) {
                Matcher matcher = pattern.matcher(in.nextLine());
                while (matcher.find()) {
                    String txt = matcher.group();
                    if(txt.equals("do()")){
                        isDo = true;
                        continue;
                    }else if(txt.equals("don\'t()")){
                        isDo = false;
                        continue;
                    }
                    if(isDo){
                        String tmp = matcher.group().substring(4, matcher.group().length() - 1);
                        String[] num = tmp.split(",");
                        sum += Integer.parseInt(num[0]) * Integer.parseInt(num[1]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }
}