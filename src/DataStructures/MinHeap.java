package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    List<Integer> items = new ArrayList<>();

    public int peek() {
        if (items.size() == 0) {
            throw new IllegalStateException();
        }

        return items.get(0);
    }

    public int poll() {
        if (items.size() == 0) {
            throw new IllegalStateException();
        }

        int item = items.get(0);
        items.set(0, items.get(items.size() - 1));
        items.remove(items.size() - 1);
        heapifyDown();

        return item;
    }

    public void add(int item) {
        items.add(item);
        heapifyUp();
    }

    public int size(){
        return items.size();
    }

    private void heapifyUp() {
        int index = items.size() - 1;
        while (hasParent(index) && parent(index) > items.get(index)) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)){
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)){
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items.get(index) < items.get(smallerChildIndex)){
                break;
            }

            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index){
        return 2 * index + 2;
    }

    private int getParentIndex(int index){
        return (index - 1) / 2;
    }

    private boolean hasLeftChild(int index){
        return getLeftChildIndex(index) < items.size();
    }

    private boolean hasRightChild(int index){
        return getRightChildIndex(index) < items.size();
    }

    private boolean hasParent(int index){
        return getParentIndex(index) < items.size();
    }

    private int rightChild(int index){
        return items.get(getRightChildIndex(index));
    }

    private int leftChild(int index){
        return items.get(getLeftChildIndex(index));
    }

    private int parent(int index){
        return items.get(getParentIndex(index));
    }

    private void swap(int a, int b){
        int temp = items.get(a);
        items.set(a, items.get(b));
        items.set(b, temp);
    }
}
