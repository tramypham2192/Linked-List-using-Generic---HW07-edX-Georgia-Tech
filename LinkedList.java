import java.lang.management.ThreadInfo;

public class LinkedList<T extends Comparable> implements List<T>
{
    private class Node<T>{
        T data;
        Node<T> next;

        private Node (T data, Node<T> next){
            this.data = data;
        }

        private Node (T data){
            this(data, null);
            this.next = null;
        }

        public T getData(){
            return this.data;
        }
        public void setData(T data){
            this.data = data;
        }

        public Node<T> getNext(){
            return this.next;
        }
        public void setNext(Node<T> next){
            this.next = next;
        }
    }

    Node<T> head = null;
    Node<T> tail = null;
    int size = 0;

    public LinkedList(){
        this.head = null;
        this.tail = null;
    }

    public Node<T> getHead(){
        return this.head;
    }

    public Node<T> getTail(){
        return this.tail;
    }

    public void addAtIndex(T data, int index){
        if (data == null){
            throw new IllegalArgumentException("You cannot add null data to the list");
        }
        if (index < 0 || index > this.size){
            throw new IllegalArgumentException("Your index is out of the list bounds");
        }
        //if linked list is empty, head node would be new node
        if (this.head == null){
            this.head = new Node(data);
            this.size++;
        } else {
            int counter = 0;
            Node<T> newNode = new Node(data);
            //add new node to the front of the head
            if (index == 0){
                newNode.next = this.head;
                this.head = newNode;
                this.size++;
            } 
            //add the node at the tail
            else if (index == this.size) {
                if (this.size == 1){
                    this.head.next = newNode;
                    this.size++;
                } else {
                    Node<T> currentNode = this.head;
                    while (counter < index ){
                        while (currentNode.next != null){
                            currentNode = currentNode.next;
                        }
                        counter++;
                    }
                    currentNode.next = newNode;
                    this.size++;
                }
                
            }
            //add the node at the Nth position
            else if (index > 0 && index < this.size){
                Node<T> currentNode = this.head;
                while (counter < index - 1){
                    currentNode = currentNode.next;
                    counter++;     
                }
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                this.size++;
                }
            }
        }

    public T getAtIndex(int index){
        Node<T> currentNode = this.head;
        if (index <= this.size){
            if (index == 0){
                return this.head.data;
            } else {
                int counter = 0;
                while (currentNode != null){
                    currentNode = currentNode.next;
                    counter++;
                    if (counter == index){
                        return (T) currentNode.data;
                    }
                }
            }
        }
        return currentNode.data;
    }

    public T removeAtIndex(int index){
        Node<T> currentNode = this.head;
        if (index >= 0 && index <= this.size){
            if (index == 0){
                this.head = this.head.next;
                this.size--;
            } else {
                int counter = 0;
                while (counter < index - 1){
                    currentNode = currentNode.next;
                    counter++;
                }
                currentNode.next = currentNode.next.next;
                this.size--;
                return currentNode.next.data;
            }
        }
        return currentNode.data;
    }

    public T remove(T dataToRemove){
        Node<T> currentNode = this.head;
        while (currentNode.next != null){
            currentNode = currentNode.next;
            if (currentNode.next.data.compareTo(dataToRemove) == 0){
                currentNode.next = currentNode.next.next;
                this.size--;
            }
        }
        return dataToRemove;
    }

    public int size(){
        return this.size;
    }

    public void printLinkedList(LinkedList<T> newLinkedList){
        Node<T> currentNode = this.head;
        if (this.size == 0){
            System.out.println("Linked list is empty");
        }
        else {
            while (currentNode != null){
                System.out.println(currentNode.data);
                System.out.println("size cua linked list hien tai dang la " + this.size); 
                currentNode = currentNode.next;
            }
        }
        }
    

    public void clear(){
        Node<T> currentNode = this.head;
        while (currentNode != null){
            currentNode = currentNode.next;
            removeAtIndex(0);
        }
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public static void main(String[] args) {
        LinkedList<String> newLL = new LinkedList<>();
        newLL.addAtIndex("tra my", 0);
        newLL.addAtIndex("my my", 1);
        newLL.addAtIndex("dan nguyen", 2);
        newLL.addAtIndex("dan bui", 2);
        newLL.addAtIndex("dai bui", 4);
        newLL.addAtIndex("ngoc mai", 5); 
        newLL.printLinkedList(newLL);
        System.out.println("element at index 2 is " + newLL.getAtIndex(2));
        newLL.removeAtIndex(0);
        System.out.println("After remove element at index 0: ");
        newLL.printLinkedList(newLL);
        newLL.removeAtIndex(0);
        System.out.println("After remove element at index 0 the 2nd time: ");
        newLL.printLinkedList(newLL);;
        newLL.remove("ngoc mai");
        System.out.println("After remove data of ngoc mai: ");
        newLL.printLinkedList(newLL);

        newLL.clear();
        System.out.println("After clear all: ");
        System.out.println(newLL.size);
        // newLL.printLinkedList(newLL);
    }
}