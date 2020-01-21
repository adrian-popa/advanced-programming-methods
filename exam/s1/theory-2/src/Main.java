import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16);

        List<Integer> finalNumbers = Stream.of(
                numbers.stream()
                        .filter(n -> n % 4 == 0)
                        .map(n -> n + 1)
                        .reduce(0, (a, b) -> (a + b) % 2)
        ).collect(Collectors.toList());

        System.out.println(finalNumbers);
    }
}
