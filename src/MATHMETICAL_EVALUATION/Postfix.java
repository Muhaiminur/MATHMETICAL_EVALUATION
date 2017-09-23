/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MATHMETICAL_EVALUATION;

import java.util.Stack;

/**
 *
 * @author muhaiminur
 */
public class Postfix {
Stack operatorStack = new Stack();

public String toPrefix(String expression) {
    expression="("+expression+")";
    int i;      
    char token;
    String output = "";
    for (i = 0; i < expression.length(); i++) {
        token = expression.charAt(i);
        if (Character.isLetterOrDigit(token) == true)
            output += token;
        else if (token == '(')
            operatorStack.push(token);
        else if (token == ')') {
            char seeTop;
            while ((seeTop = seeTop()) != '(') {
                output += seeTop;
                popSeeTop();
            }

            operatorStack.pop();
        } else {
            while (priority(token) <= priority(seeTop())) {
                output += seeTop();
                popSeeTop();
            }
            operatorStack.push(token);
        }
    }
    return output;

}

private int priority(char operator) {
    if (operator == '^')
        return 3;
    if (operator == '/' || operator == '*')
        return 2;
    if (operator == '+' || operator == '-')
        return 1;
    return 0;
}

private Character seeTop() {
    if(!operatorStack.empty())
        return (Character) operatorStack.peek();
    else
        return '0';
}

private void popSeeTop() {
    if(!operatorStack.empty())
        operatorStack.pop();
}
}