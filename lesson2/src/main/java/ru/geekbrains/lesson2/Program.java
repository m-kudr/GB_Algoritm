package ru.geekbrains.lesson2;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Program {


    public static void main(String[] args) {

        int[] arr = ArrayUtils.prepareArray();
        /*
        ArrayUtils.printArray(arr);
        SortUtils.directSort(arr);
        ArrayUtils.printArray(arr);
        */
        arr = ArrayUtils.prepareArray();
        System.out.println("Исходный несортированный массив:");
        ArrayUtils.printArray(arr);  // вывод исходного несортированного массива
        // SortUtils.quickSort(arr);
        System.out.println("Массив отсортирован методом пирамидальной сортировки:");
        SortUtils.pyramidSort(arr);  // сортитовка массива
        ArrayUtils.printArray(arr);  // вывод отсортированного массива

        /*
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

        testArray = new int[]{9, -10, 100, 22, -5, -5, 0, 9, 22, 13};
        ArrayUtils.printArray(testArray);
        SortUtils.quickSort(testArray);
        ArrayUtils.printArray(testArray);
        int res01 = SearchUtils.binarySearch(testArray, 13);
        System.out.printf("Элемент %d %s\n",
                13, res01 >= 0 ? String.format("найден в массиве по индексу %d", res01) :
                        "не найден в массиве");

        testArray = new int[]{9, -10, 100, 22, -5, -5, 0, 9, 22, 13};
        Arrays.sort(testArray);
        ArrayUtils.printArray(testArray);
        int res02 = Arrays.binarySearch(testArray, 13);
        System.out.printf("Элемент %d %s\n",
                13, res02 >= 0 ? String.format("найден в массиве по индексу %d", res02) :
                        "не найден в массиве");
        */
    }

    static class ArrayUtils {

        private static final Random random = new Random();

        static int[] prepareArray() {
            int[] array = new int[random.nextInt(16) + 10];
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

    static class SortUtils {
        //--------- алгоритм пирамидальной сортировки (сортировка кучей)
        static void pyramidSort(int arr[]) {
            int n = arr.length;

            // Построение кучи (перегруппируем массив)
            for (int i = n / 2 - 1; i >= 0; i--)
                heapify(arr, n, i);

            // Один за другим извлекаем элементы из кучи
            for (int i = n - 1; i >= 0; i--) {
                // Перемещаем текущий корень в конец
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                // Вызываем процедуру heapify на уменьшенной куче
                heapify(arr, i, 0);
            }
        }

        // Процедура для преобразования в двоичную кучу поддерева с корневым узлом i,
        // что является индексом в arr[]. n - размер кучи
        static void heapify(int arr[], int n, int i) {
            int largest = i; // Инициализируем наибольший элемент как корень
            int l = 2 * i + 1; // левый = 2*i + 1
            int r = 2 * i + 2; // правый = 2*i + 2

            // Если левый дочерний элемент больше корня
            if (l < n && arr[l] > arr[largest])
                largest = l;

            // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
            if (r < n && arr[r] > arr[largest])
                largest = r;
            // Если самый большой элемент не корень
            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
                heapify(arr, n, largest);
            }
        }

        //--------------------------------------------------------------------------
        static void directSort(int[] array) {
            for (int i = 0; i < array.length; i++) {
                int index = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < array[index]) {
                        index = j;
                    }
                }
                if (index != i) {
                    int buf = array[i];
                    array[i] = array[index];
                    array[index] = buf;
                }
            }
        }

        static void quickSort(int[] array) {
            quickSort(array, 0, array.length - 1);
        }

        static void quickSort(int[] array, int startPosition, int endPosition) {
            int left = startPosition;
            int right = endPosition;
            int middle = array[(left + right) / 2];

            do {
                while (array[left] < middle) {
                    left++;
                }
                while (array[right] > middle) {
                    right--;
                }
                if (left <= right) {
                    if (left < right) {
                        int buf = array[left];
                        array[left] = array[right];
                        array[right] = buf;
                    }
                    left++;
                    right--;
                }
            }
            while (left <= right);
            if (left < endPosition) {
                quickSort(array, left, endPosition);
            }
            if (startPosition < right) {
                quickSort(array, startPosition, right);
            }
        }

    }

    static class SearchUtils {

        static int binarySearch(int[] array, int value) {
            return binarySearch(array, value, 0, array.length - 1);
        }

        static int binarySearch(int[] array, int value, int min, int max) {
            if (max < min) return -1;
            int middle = (min + max) / 2;
            if (array[middle] == value) {
                return middle;
            } else if (array[middle] > value) {
                return binarySearch(array, value, min, middle - 1);
            } else {
                return binarySearch(array, value, middle + 1, max);
            }
        }
    }


}
