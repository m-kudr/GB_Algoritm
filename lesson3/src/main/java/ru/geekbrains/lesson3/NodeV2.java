package ru.geekbrains.lesson3;

public class NodeV2 {

    private int value;
    private NodeV2 next;
    private NodeV2 prev;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodeV2 getNext() {
        return next;
    }

    public void setNext(NodeV2 next) {
        this.next = next;
    }

    public NodeV2 getPrev() {
        return prev;
    }

    public void setPrev(NodeV2 prev) {
        this.prev = prev;
    }

    public NodeV2(int value) {
        this.value = value;
    }
}
