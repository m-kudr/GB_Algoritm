package ru.geekbrains.lesson3;

public class MyLinkedListV2 {

    private NodeV2 head;
    private NodeV2 tail;

    public void addFirst(int value){
        NodeV2 node = new NodeV2(value);
        if (head != null){

            node.setNext(head);
            head.setPrev(node);
           //head = node;
        }
        else {
            //head = node;
            tail = node;
        }
        head = node;
    }

    public void removeFirst(){
        if (head != null && head.getNext() != null){
            head.getNext().setPrev(null);
            head = head.getNext();
        }
        else {
            head = null;
            tail = null;
        }
    }

}
