package rit.stu;
import rit.cs.Node;
import rit.cs.Stack;

/**
 * Created by moholkar.hrishikesh2 on 2/13/2016.
 * Hrishikesh Moholkar
 * hnm6500@rit.edu
 */
public class StackNode<T> implements Stack<T> {
    /**
     * this class uses the stack interface to construct
     * a stack using nodes as a building block.
     */
    Node<T> stackelement;
    public StackNode(){
        /**
         * constructor which initializes
         * the stack initially as empty
         */
        stackelement=null;
    }

    public boolean empty() {
        /**
         * verifys wether the stack is empty or not.
         */
        if (stackelement == null) {

            return true;
        }
        return false;
    }


    public T pop(){
        /**
         * removes the top element of stack
         */
        assert !empty();
        T data=this.stackelement.getData();
        stackelement=this.stackelement.getNext();
        return data;

    }

    public void push(T element){
        /**
         * pushes a new element on the top of
         * stack
         */

       Node<T> new1 = new Node(element,this.stackelement);
        this.stackelement=new1;

    }

    public T top(){
        /**
         * sees the top element of the stack without removing
         * it.
         */

        assert !empty();
        return this.stackelement.getData();

    }



}
