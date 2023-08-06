package A1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {
    private CalculatorClient() {}

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host,1099);
            Calculator stub = (Calculator) registry.lookup("Calculator");
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
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
