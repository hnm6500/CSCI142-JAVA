package rit.stu;
import rit.cs.Queue;
import rit.cs.Node;

/**
 * Created by moholkar.hrishikesh2 on 2/14/2016.
 * @hrishikesh Moholkar
 * hnm6500@rit.edu
 */
public class QueueNode<T> implements Queue<T> {
    /**
     * this method uses queue as an interface to
     * create queue using nodes.
     */
    public QueueNode(){
        /**
         * constructor which setst the
         * front node and back node to null
         */
        front=null;
        back1=null;
    }
    Node<T>front;
     Node<T>back1;
    public T back(){
        /**
         * this method returns the back element of the
         * queue
         */
        assert!empty();
        T data1= this.back1.getData();
        return data1;


    }

    public T dequeue(){
        /**
         * this method returns the front
         * element of the queue and sets the
         * queue to the next front element after
         * getting the front element data
         */
        assert!empty();
        T data=this.front.getData();
        this.front=this.front.getNext();
        if (this.front==null){
            this.back1=null;
        }
        return data;


    }

    public boolean empty() {
        /**
         * this method sees whether the
         * queue is empty or not
         */
        if ((front == null)&&(back1==null)) {

            return true;
        }
        return false;
    }

    public void enqueue(T element){
        /**
         * this method adds new element to
         * the back of the queue.
         */
        Node <T>new1=new Node<T>(element,null);
        if((this.front==null)&&(this.back1==null)){
            this.front=new1;
        }else{
            this.back1.setNext(new1);

        }
        this.back1=new1;
    }

    public T front(){
        /**
         * this method return only the
         * first element .doesnt discard the first
         * one.
         */
        assert !empty();
        return this.front.getData();


    }


}

