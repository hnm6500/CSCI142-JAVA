/**
 * @Hrishikesh Moholkar
 * hnm6500@rit.edu
 */
package rit.stu;

import rit.cs.Stack;

import rit.stu.StackNode;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * A program that converts an infix expression to postfix.<br>
 * <br>
 *  Usage: java InToPost filename<br>
 * <br>
 * For example, prog1.in, containing infix expressions (one per line):<br>
 * <br>
 *  A + B<br>
 *  A + B * C<br>
 *  A + B * C + D<br>
 *  ( A + B ) * C<br>
 *  ( A + B ) / ( C - D )<br>
 *  ( ( A + B ) * C - ( D - E ) )<br>
 * <br>
 * When run: java InToPost prog1.in:<br>
 * <br>
 *  InToPost: converting expressions from infix to postfix...<br>
 *  [A, +, B]<br>
 *  [A, +, B, *, C]<br>
 *  [A, +, B, *, C, +, D]<br>
 *  [(, A, +, B, ), *, C]<br>
 *  [(, A, +, B, ), /, (, C, -, D, )]<br>
 *  [(, (, A, +, B, ), *, C, -, (, D, -, E, ), )]<br>
 *  InToPost: emitting postfix expressions...<br>
 *  A B +<br>
 *  A B C * +<br>
 *  A B C * + D +<br>
 *  A B + C *<br>
 *  A B + C D - /<br>
 *  A B + C * D E - -<br>
 * <br>
 * @author Sean Strout @ RIT CS
 * @author YOUR NAME HERE
 */
public class InToPost {
    /** The add operator */
    public final static String ADD = "+";
    /** The subtract operator */
    public final static String SUBTRACT = "-";
    /** The multiply operator */
    public final static String MULTIPLY = "*";
    /** The divide operator */
    public final static String DIVIDE = "/";
    /** The open parentheses operator */
    public final static String OPEN_PAREN = "(";
    /** The close parentheses operator */
    public final static String CLOSE_PAREN = ")";

    /** The list of converted postfix expressions */
    private List<List<String>> expressions;
    /** The name of the infix source file */
    private String srcFile;
    /** A dictionary that maps operators (strings) to their precedence level
     * (integer) from highest (3) to lowest (1):
     *      *, /: 3
     *      +, -: 2
     *      (: 1
     */
    private Map<String, Integer> precedence;

    /**
     * Create a new IntoPost object.
     * @param filename The name of the infix source file
     */
    public InToPost(String filename) {
        this.expressions = new LinkedList<>();
        this.srcFile = filename;

        /** populate the precedence map */
        this.precedence = new HashMap<>();
        this.precedence.put(MULTIPLY, 3);
        this.precedence.put(DIVIDE, 3);
        this.precedence.put(ADD, 2);
        this.precedence.put(SUBTRACT, 2);
        this.precedence.put(OPEN_PAREN, 1);
    }

    /**
     * A private utility method that can be used by the private convert
     * method when dealing with the precedence of math operators on
     * the stack.  Indicates whether the top token on stack has greater
     * than or equal precedence than the current token.  For example:
     *  greaterEqualPrecedence("+", "*") -> false
     *  greaterEqualPrecedence("*", "+") -> true
     *  greaterEqualPrecedence("+", "-") -> true
     *  greaterEqualPrecedence("(", "/") -> false
     *
     * @param token the current math token, e.g. "+"
     * @param top the top token to compare to, e.g. "*"
     * @return true if top has greater than or equal precedence than
     *  token, false otherwise
     */
    private boolean greaterEqualPrecedence(String top, String token) {
        return this.precedence.get(top) >= this.precedence.get(token);
    }

    /**,
     * A private helper conversion function that takes a list of tokens
     * (strings) in infix form and converts them to a list of tokens
     * (strings) in postfix form.
     *
     *  For example:
     *      tokens = ["A", "+", "B"]
     *      postfix = ["A", "B", "+"]
     *
     *  Note, this method must use either StackList<T> or StackNode<T>
     *  to represent the stack structure in the conversion algorithm.
     *
     * @param tokens the list of tokens (strings) in infix form
     * @return a new list of tokens (strings) in postfix form
     */
    private List<String> convert(List<String> tokens) {
        /**
         * this method returns a list which contains the expression in
         * postflix form.
         */
        StackNode<String>opstack=new StackNode<String>();
        ArrayList<String>list1=new ArrayList<String>();
        for (String i:tokens){
            if(i.equals(OPEN_PAREN)){
                opstack.push(i);
            }
            else if(i.equals(ADD)||(i.equals(SUBTRACT))||(i.equals(MULTIPLY))||(i.equals(DIVIDE))){
                if((!opstack.empty())) {
                    boolean f = opstack.empty();
                    while ((!opstack.empty())&&(greaterEqualPrecedence(opstack.top(), i))) {
                        f = opstack.empty();
                        String x = opstack.pop();
                        list1.add(x);
                        //System.out.println(list1);

                    }
                }
                opstack.push(i);
            }
            else if(i.equals(CLOSE_PAREN)){
                while(!opstack.top().equals(OPEN_PAREN)){
                    String x=opstack.pop();
                    list1.add(x);
                    //System.out.println(list1);
                }opstack.pop();
            }
            else{
                list1.add(i);
                //System.out.println(list1);
            }

            }

        while(!opstack.empty()){
            String x=opstack.pop();
            list1.add(x);
           // System.out.println(list1);
        }

        //System.out.println(list1+"---------------------");
        return list1;
    }

    /**
     * The public conversion function that converts all infix expressions
     * in the source file (one per line), into postfix expressions.
     * @throws FileNotFoundException if the file is not found
     */
    public void convert() throws FileNotFoundException {
        Scanner in = new Scanner(new File(this.srcFile));
        while (in.hasNext()) {
            // convert the line into a list of strings, e.g.
            //      line = "A B +"
            //      tokens = ["A", "B", "+"]
            List<String> tokens = Arrays.asList(in.nextLine().split(" "));
            // if the line isn't empty, convert it to postfix and add it to
            // the list of expressions
            if (tokens.size() > 0) {
                System.out.println(tokens);
                this.expressions.add(convert(tokens));
            }
        }
    }

    /**
     * Display the converted postfix expressions.
     */
    public void emit() {
        for (List<String> expression : this.expressions) {
            for (String token : expression) {
                System.out.print(token + " ");
            }
            System.out.println();
        }
    }

    /**
     * The main program takes the source file of infix expressions, converts
     * them to postfix, and displays them.
     * @param args command line arguments
     * @throws FileNotFoundException if the source file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Usage: java inToPost.py source-file.in");
            return;
        }

        InToPost inToPost = new InToPost(args[0]);
        System.out.println("InToPost: converting expressions from infix to postfix...");
        inToPost.convert();
        System.out.println("InToPost: emitting postfix expressions...");
        inToPost.emit();
    }
}
