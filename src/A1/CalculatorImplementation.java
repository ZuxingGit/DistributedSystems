package A1;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class CalculatorImplementation implements Calculator {
    Stack<Integer> stack = new Stack<>();

    @Override
    public void pushValue(int val) {
        stack.push(val);
        System.out.println("a value pushed in: " + val);
    }

    @Override
    public void pushOperation(String operator) throws RemoteException {
        if (operator.isEmpty() || operator == null || operator.length() == 0) {
            System.err.println("Wrong operator type!");
        }
        if (operator.equals("min")) {
            int min = min(stack);
        } else if (operator.equals("max")) {
            int max = max(stack);
        } else if (operator.equals("lcm")) {
            int lcm = lcm(stack);
        } else if (operator.equals("gcd")) {
            int gcd = gcd(stack);
        } else {
            System.err.println("Wrong operator type!");
        }
    }

    @Override
    public int pop() {
        int top = stack.pop();
        System.out.println("a value popped out: " + top);
        return top;
    }

    @Override
    public boolean isEmpty() {
        if (stack.isEmpty())
            System.out.println("Stack is empty.");
        else
            System.out.println("Stack not empty");
        return stack.isEmpty();
    }

    @Override
    public int delayPop(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
            System.out.print("after " + millis + " millis delay ");
            return pop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int min(Stack stack) throws RemoteException {
        if (stack.size() < 2)
            System.err.println("no enough value or no operator!");

        int minVal = (int) stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()) {
            int val = iterator.next();
            if (val < minVal)
                minVal = val;
        }
        System.out.println(stack.toString());
        System.out.println("min:" + minVal);
        return minVal;
    }

    @Override
    public int max(Stack stack) throws RemoteException {
        if (stack.size() < 2)
            System.err.println("no enough value or no operator!");

        int maxVal = (int) stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()) {
            int val = iterator.next();
            if (val > maxVal)
                maxVal = val;
        }
        System.out.println(stack.toString());
        System.out.println("max:" + maxVal);
        return maxVal;
    }

    @Override
    public int lcm(Stack stack) throws RemoteException {
        int lcm = (int) stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext())
            lcm = lcm(lcm, iterator.next());

        System.out.println(stack.toString());
        System.out.println("lcm:" + lcm);
        return lcm;
    }

    @Override
    public int gcd(Stack stack) throws RemoteException {
        int gcd = (int) stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext())
            gcd = gcd(gcd, iterator.next());

        System.out.println(stack.toString());
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
