package ru.geekbrains.lesson4;

public class HashMap<K, V> {

    private static final int INIT_BUCKET_COUNT = 16;
    private Bucket[] buckets;

    class Entity {
        K key;
        V value;
    }

    class Bucket<K, V> {
        Node head;

        class Node {
            Node next;
            Entity value;
        }

        /**
         * Получить значение элемента по ключу
         *
         * @param key Ключ
         * @return Значение элемента
         */
        public V get(K key) {
            Node node = head;
            while (node != null) {
                if (node.value.key.equals(key))
                    return (V) node.value.value;
                node = node.next;
            }
            return null;
        }

        /**
         * Добавление нового элемента
         *
         * @param entity Элемент
         * @return Результат добавления
         */
        public V add(Entity entity) {
            Node node = new Node();
            node.value = entity;

            if (head == null) {
                head = node;
                return (V) entity.value;
            }

            Node currentNode = head;
            while (true) {

                if (currentNode.value.key.equals(entity.key)) {
                    currentNode.value.value = entity.value;
                    return (V) entity.value;
                }

                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                } else {
                    currentNode.next = node;
                    return (V) entity.value;
                }
            }
        }

        /**
         * Удаление элемента
         *
         * @param key Ключ
         * @return Значение
         */
        public V remove(K key) {
            if (head == null)
                return null;

            if (head.value.key.equals(key)) {
                V value = (V) head.value.value;
                head = head.next;
                return value;
            } else {
                Node node = head;
                while (node.next != null) {
                    if (node.next.value.key.equals(key)) {
                        V value = (V) node.next.value.value;
                        node.next = node.next.next;
                        return value;
                    }
                    node = node.next;
                }
                return null;
            }
        }
    }

    private int calculateBucketIndex(K key) {
        int index = key.hashCode() % buckets.length;
        if (index < 0)
            index = index * -1;
        return index;
    }

    /**
     * Получение значения элемента по ключу
     *
     * @param key Ключ
     * @return Значение элемента (null - элемент не найден)
     */
    public V get(K key) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket != null) {
            return (V) bucket.get(key);
        }
        return null;
    }

    /**
     * Добавление нового элемента в хеш-таблицу
     *
     * @param key   Ключ
     * @param value Значение
     * @return Результат добавления
     */
    public V put(K key, V value) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            bucket = new Bucket();
            buckets[index] = bucket;
        }

        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        return (V) bucket.add(entity);
    }

    /**
     * Удаление элемента по ключу
     *
     * @param key Ключ
     * @return Значение
     */
    public V remove(K key) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null)
            return null;
        return (V) bucket.remove(key);
    }

    public HashMap() {
        this(INIT_BUCKET_COUNT);
    }

    public HashMap(int initCount) {
        buckets = new Bucket[initCount];
    }
}
