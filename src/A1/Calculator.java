package A1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Stack;

public interface Calculator extends Remote {

    void pushValue(int val) throws RemoteException;

    int pushOperation(String operator) throws RemoteException;

    int pop() throws RemoteException;

    boolean isEmpty() throws RemoteException;

    int delayPop(int millis) throws RemoteException;
    int min(Stack stack) throws RemoteException;
    
    int max(Stack stack) throws RemoteException;
    
    int lcm(Stack stack) throws RemoteException;
    
    int gcd(Stack stack) throws RemoteException;
}
