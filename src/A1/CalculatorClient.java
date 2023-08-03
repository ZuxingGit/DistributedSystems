package A1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    private CalculatorClient() {}

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",1099);
            Calculator stub = (Calculator) registry.lookup("Calculator");
            stub.pushValue(4);
            stub.pushValue(3);
            stub.pushValue(9);
            stub.pushValue(6);
            if (!stub.isEmpty()){
                stub.pushOperation("min");
                stub.pushOperation("max");
                stub.pushOperation("lcm");
                stub.pushOperation("gcd");
            }
            stub.pop();
            stub.delayPop(2000);
            
//            System.out.println("---response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
