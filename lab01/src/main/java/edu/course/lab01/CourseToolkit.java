package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        int divisor = 2;

        while (divisor * divisor <= number) {
            if (number % divisor == 0) {
                return false;
            }

            divisor++;
    }

    return true;
    }

    public static boolean isPalindrome(String text) {
    int left = 0;
    int right = text.length() - 1;
    while (left < right) {
        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
    }

    public static double average(int[] numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException();
        }
        
        long sum = 0;
        
        for (int number : numbers) {
             sum += number;
        }

        return (double) sum / numbers.length;
    }

    
}
