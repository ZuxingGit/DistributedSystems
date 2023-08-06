import A1.Calculator;
import A1.CalculatorImplementation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Calculator stub=new CalculatorImplementation();
            System.out.println("Please input numbers and an operator(min/max/lcm/gcd/pop/delayPop):");
            Scanner scan = new Scanner(System.in);
            while (scan.hasNext()){
                while (scan.hasNextInt()){
                    int in = scan.nextInt();
                    stub.pushValue(in);
                }
                String op=scan.next();
                if ("pop".equals(op)){
                    System.out.println(stub.pop());
                } else if ("delayPop".equalsIgnoreCase(op)) {
                    System.out.println("Please input the length of delay(milliseconds):");
                    int dl= scan.nextInt();
                    System.out.println(stub.delayPop(dl));
                } else {
                    System.out.println(stub.pushOperation(op));
                }
            }
            
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        System.out.println("Hello world!");
    }
}