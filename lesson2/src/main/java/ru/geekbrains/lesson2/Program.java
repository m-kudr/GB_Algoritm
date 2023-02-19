package ru.geekbrains.lesson2;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Program {


    public static void main(String[] args) {

        int[]  arr = ArrayUtils.prepareArray();

        ArrayUtils.printArray(arr);
        SortUtils.directSort(arr);
        ArrayUtils.printArray(arr);


        arr = ArrayUtils.prepareArray();
        ArrayUtils.printArray(arr);
        SortUtils.quickSort(arr);
        ArrayUtils.printArray(arr);

        int[] testArray = ArrayUtils.prepareArray(200000);
        long startTime = System.currentTimeMillis();
        //SortUtils.directSort(testArray.clone());
        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;
        System.out.printf("Время работы сортировки выбором: %d мс.\n", processingTime);

        startTime = System.currentTimeMillis();
        SortUtils.quickSort(testArray.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время работы быстрой сортировки: %d мс.\n", processingTime);

        testArray = new int[] {9, -10, 100, 22, -5, -5, 0, 9, 22, 13};
        ArrayUtils.printArray(testArray);
        SortUtils.quickSort(testArray);
        ArrayUtils.printArray(testArray);
        int res01 = SearchUtils.binarySearch(testArray, 13);
        System.out.printf("Элемент %d %s\n",
                13, res01 >= 0 ? String.format("найден в массиве по индексу %d", res01) :
                "не найден в массиве");

        testArray = new int[] {9, -10, 100, 22, -5, -5, 0, 9, 22, 13};
        Arrays.sort(testArray);
        ArrayUtils.printArray(testArray);
        int res02 =Arrays.binarySearch(testArray, 13);
        System.out.printf("Элемент %d %s\n",
                13, res02 >= 0 ? String.format("найден в массиве по индексу %d", res02) :
                        "не найден в массиве");

    }

    static class ArrayUtils {

        private static final Random random = new Random();

        static int[] prepareArray() {
            int[] array = new int[random.nextInt(16) + 5];
            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(100);
            }
            return array;
        }

        static int[] prepareArray(int length) {
            int[] array = new int[length];
            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(100);
            }
            return array;
        }

        static void printArray(int[] array) {
            for (int element : array) {
                System.out.printf("%d\t", element);
            }
            System.out.println();
        }

    }

    static class SortUtils{

        static void directSort(int[] array) {
            for (int i = 0; i < array.length; i++){
                int index = i;
                for (int j = i + 1; j < array.length; j++){
                    if (array[j] < array[index]){
                        index = j;
                    }
                }
                if (index != i){
                    int buf = array[i];
                    array[i] = array[index];
                    array[index] = buf;
                }
            }
        }

        static void quickSort(int[] array){
            quickSort(array, 0, array.length - 1);
        }

        static void quickSort(int[] array, int startPosition, int endPosition){
            int left = startPosition;
            int right = endPosition;
            int middle = array[(left + right) / 2];

            do {
                while (array[left] < middle){
                    left++;
                }
                while (array[right] > middle){
                    right--;
                }
                if (left <= right){
                    if (left < right){
                        int buf = array[left];
                        array[left] = array[right];
                        array[right] = buf;
                    }
                    left++;
                    right--;
                }
            }
            while (left <= right);
            if (left < endPosition){
                quickSort(array, left, endPosition);
            }
            if (startPosition < right){
                quickSort(array, startPosition, right);
            }
        }

    }
    static class SearchUtils{

        static int binarySearch(int[] array, int value) {
            return binarySearch(array, value, 0, array.length - 1);
        }

        static int binarySearch(int[] array, int value, int min, int max){
            if (max < min) return -1;
            int middle = (min + max) / 2;
            if (array[middle] == value){
                return middle;
            }
            else if (array[middle] > value){
                return binarySearch(array, value, min, middle - 1);
            }
            else{
                return  binarySearch(array, value, middle + 1, max);
            }
        }
    }


}
