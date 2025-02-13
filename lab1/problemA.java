import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class problemA {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine().toLowerCase();
        String permutation = scanner.nextLine().toLowerCase();

        Set<Character> wordChars = new HashSet<>();
        for (char c : word.toCharArray()) {
            wordChars.add(c);
        }

        boolean won = true;
        for (char c : wordChars) {
            if (permutation.indexOf(c) == -1) {
                won = false;
                break;
            }
        }

        if (won) {
            System.out.println("Win!");
        } else {
            System.out.println("Lose!");
        }
    }
}
