package A1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer {
    public CalculatorServer() {
    }

    public static void main(String[] args) {
        try {
            Calculator obj = new CalculatorImplementation();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            registry.bind("Calculator", stub);

            System.err.println("---CalculatorServer ready---");
        } catch (Exception e) {
            System.err.println("Server exception:" + e.toString());
            e.printStackTrace();
        }
    }
}
