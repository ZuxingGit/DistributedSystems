package A1;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class CalculatorImplementation implements Calculator {
    Stack<Integer> stack = new Stack<>();
    
    public void pushValue(int val) {
        stack.push(val);
        System.out.println("a value pushed in: " + val);
    }
    
    public int pushOperation(String operator) throws RemoteException {
        if (operator.isEmpty() || operator == null || operator.length() == 0) {
            System.err.println("Wrong operator type!");
        }
        if (operator.equals("min")) {
            return min(stack);
        } else if (operator.equals("max")) {
            return max(stack);
        } else if (operator.equals("lcm")) {
            return lcm(stack);
        } else if (operator.equals("gcd")) {
            return gcd(stack);
        } else {
            System.err.println("Wrong operator type!");
        }
        return -1;
    }
    
    public int pop() {
        int top = stack.pop();
        System.out.println("a value popped out: " + top);
        return top;
    }
    
    public boolean isEmpty() {
        if (stack.isEmpty())
            System.out.println("Stack is already empty");
        else
            System.out.println("Stack not empty");
        return stack.isEmpty();
    }
    
    public int delayPop(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
            System.out.print("after " + millis + " millis delay ");
            return pop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int min(Stack stack) throws RemoteException {
        if (stack.size() < 1)
            System.err.println("no enough value or no operator!");

        int minVal = (int) stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()) {
            int val = iterator.next();
            if (val < minVal)
                minVal = val;
        }
        System.out.println(stack);
        stack.clear();
        System.out.println("min:" + minVal);
        return minVal;
    }
    
    public int max(Stack stack) throws RemoteException {
        if (stack.size() < 1)
            System.err.println("no enough value or no operator!");

        int maxVal = (int) stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()) {
            int val = iterator.next();
            if (val > maxVal)
                maxVal = val;
        }
        System.out.println(stack);
        stack.clear();
        System.out.println("max:" + maxVal);
        return maxVal;
    }
    
    public int lcm(Stack stack) throws RemoteException {
        int lcm = (int) stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext())
            lcm = lcm(lcm, iterator.next());

        System.out.println(stack);
        stack.clear();
        System.out.println("lcm:" + lcm);
        return lcm;
    }
    
    public int gcd(Stack stack) throws RemoteException {
        int gcd = (int) stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext())
            gcd = gcd(gcd, iterator.next());

        System.out.println(stack);
        stack.clear();
        System.out.println("gcd:" + gcd);
        return gcd;
    }

    private static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}
