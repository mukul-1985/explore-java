package in.co.mukuljain.java8.stream;

import java.util.List;
import java.util.function.Predicate;

public class StreamFilterFeature {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        test(numbers, n -> n % 2 == 0);
        test(numbers, n -> n % 2 != 0);
    }

    public static void test(List<Integer> numbers, Predicate<Integer> p) {
        for (Integer num : numbers) {
            if (p.test(num)) {
                System.out.println(num);
            }
        }
    }
}
