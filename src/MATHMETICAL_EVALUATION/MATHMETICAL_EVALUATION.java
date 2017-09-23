/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MATHMETICAL_EVALUATION;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author muhaiminur
 */
public class MATHMETICAL_EVALUATION {

    static String []op={"+","-","/","%","*"};
    public static int[] id;
    public static char[] er;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //(a+b-c)*d-(e+f)
        //a*b+a*c+b*c
        //a*c-b/c+c*c
//        g=2
//p=3
//t=1
//w=2
        
//        g+p*t-w*p
//t-g+t-w
//e+t*t-m
        Scanner abir=new Scanner(System.in);
        String[] s;
        String[] s2;
        String[]s3;
        Postfix p=new Postfix();
        System.out.println("ENTER YOUR FIRST INDENTIFIER VALUE");
        int i=abir.nextInt();
        s=new String[i+1];
        id=new int[i+1];
        er=new char[i+1];
        System.out.println("Start your indentifier value");
        for (int j = 0; j < s.length; j++) {
            s[j]=abir.nextLine();
            extractNumber(s[j], j);
            String trm=s[j];
            if(j>0){
                er[j-1]=s[j].charAt(0);
            }
            
        }
        System.out.println("ENTER YOUR DESIRE NUMBER OF EXPRESSION");
        int e=abir.nextInt();
        s2=new String[e+1];
        s3=new String[e+1];
        System.out.println("Start your EXPRESSION value");
        for (int j = 0; j < s2.length; j++) {
            s2[j]=abir.nextLine();
            
        }
        for (int j = 0; j < id.length; j++) {
            System.out.println(er[j]+" ===== "+id[j]);
        }
        for (int j = 1; j < s2.length; j++) {
            
            char[]c=s2[j].toCharArray();
            for (int k = 0; k < c.length; k++) {
                char d = c[k];
                if(isThereChar(er, d)==false){
                    //System.out.println("dekhi "+d);
                    if(Arrays.asList(op).contains(String.valueOf(d))==false){
                        System.out.println("COMPILER ERROR");
                        System.exit(0);
                    }
                }
                        
            }
            String rs=p.toPrefix(s2[j]);
            System.out.println("POSTFIX EXPRESSION of"+(j)+" === "+rs);
            String rs2=engtomath(rs);
            System.out.println("POSTFIX TO MATH of"+(j)+" === "+rs2);
            int re =eva(rs2);
            System.out.println("FINAL RESULT == "+re);
        }
        
        
    }
    public static boolean extractNumber(String str,int cd) {
        //System.out.println("Printing"+cd);
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
                id[cd-1]=Character.getNumericValue(c);
                found = true;
            }else if(found){
                // If we already found a digit before and this char is not a digit, stop looping
                break;
            }                
        }
        return found;
    }
    /*public static String postfix(String s){
        boolean du;
        String r="";
        Stack st = new Stack();
        char t;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            char d = c[i];
            du=false;
            if(d=='('){
                st.add(d);
            }
            
            if(Arrays.asList(op).contains(d)){
                if('('==(char)st.peek()){
                    st.add(d);
                    break;
                }
                t=(char)st.pop();
                st.add(d);
                r=r+t;
                du=true;
            }
            if(d==')'){
                t=(char)st.pop();
                r=r+t;
                du=true;
                st.pop();
            }
            if(du==false){
                r=r+d;
            }
            
        }
        for (int j = 0; j < c.length; j++) {
            //System.out.print("Printing result == "+c[j]);
            
        }
        System.out.print("Printing result == "+r+st);
        return r+st;
    }*/
    public static int eva(String s){
        //System.out.println("----"+s);
        int i;
        Stack st = new Stack();
        char[] chars = s.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            char aChar = chars[j];
            //System.out.println("loop"+aChar);
            if(Arrays.asList(op).contains(String.valueOf(aChar))){
                //System.out.println("ok=="+aChar);
                int num2 = (int)st.pop();
	        int num1 = (int)st.pop();
                if (aChar=='+'){
                    st.push(num1 + num2);
                    //System.out.println("plus"+st.peek());
                } else if (aChar=='-'){
                    st.push(num1 - num2);
                    //System.out.println("minus"+st.peek());
                }else if (aChar=='*'){
                    st.push(num1 * num2);
                    //System.out.println("peek = "+st.peek());
                } else{
                    st.push(num2 / num1);
                    //System.out.println("divide"+st.peek());
                }
            }
            if(Character.isDigit(aChar)){
                
                 st.push(Character.getNumericValue(aChar));
                 //System.out.println("vaule"+st.peek());
            }
        }
        i=(int)st.peek();
        return i;
    }
    public static String engtomath(String s){
        String r="";
        char[]c=s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            boolean t=true;
            if(Arrays.asList(op).contains(String.valueOf(c[i]))){
                //System.out.println("===="+c[i]);
                r=r+c[i];
                //System.out.println("------"+r);
                t=false;
            }
            if(t){
                r=r+id[getArrayIndex(c[i])];
            }
            
            
        }
        return r;
    }
    public static int postfixEvaluate(String exp) {
	 	Stack<Integer> s = new Stack<Integer> ();
		Scanner tokens = new Scanner(exp);
		
		while (tokens.hasNext()) {
			if (tokens.hasNextInt()) {
				s.push(tokens.nextInt());
			} else {
				int num2 = s.pop();
				int num1 = s.pop();
				String op = tokens.next();
				System.out.println("-------------------------------"+op);
				if (op.equals("+")) {
					s.push(num1 + num2);
                                        
				} else if (op.equals("-")) {
					s.push(num1 - num2);
				} else if (op.equals("*")) {
					s.push(num1 * num2);
				} else {
					s.push(num1 / num2);
				}
				
			//  "+", "-", "*", "/"
			}
		}
		return s.pop();
    }
    public static int getArrayIndex(char value) {
        int k=0;
        for(int i=0;i<er.length;i++){

            if(er[i]==value){
                k=i;
                break;
            }
        }
        return k;
    }
    public static Double evaluate(String postfix) {
        // Use a stack to track all the numbers and temporary results
        Stack<Double> s = new Stack<Double>();

        // Convert expression to char array
        char[] chars = postfix.toCharArray();

        // Cache the length of expression
        int N = chars.length;

        for (int i = 0; i < N; i++) {
            char ch = chars[i];

            if (isOperator(ch)) {
                // Operator, simply pop out two numbers from stack and perfom operation
                // Notice the order of operands
                switch (ch) {
                    case '+': s.push(s.pop() + s.pop());     break;
                    case '*': s.push(s.pop() * s.pop());     break;
                    case '-': s.push(-s.pop() + s.pop());    break;
                    case '/': s.push(1 / s.pop() * s.pop()); break;
                }
            } else if(Character.isDigit(ch)) {
                // Number, push to the stack
                s.push(0.0);
                while (Character.isDigit(chars[i]))
                    s.push(10.0 * s.pop() + (chars[i++] - '0'));
            }
        }

        // The final result should be located in the bottom of stack
        // Otherwise return 0.0
        if (!s.isEmpty()) 
            return s.pop();
        else
            return 0.0;
    }

    /**
     * Check if the character is an operator
     */
    private static boolean isOperator(char ch) {
        return ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }
    
    public static boolean isThereChar(char[] chaArray, char chr){
    boolean bool = false;
    for(int i=0; i < chaArray.length; i++) {
        if(chr==chaArray[i]){
             bool = true;
        }
    }
    return bool;
}
}
