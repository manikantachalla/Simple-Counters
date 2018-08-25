import java.util.HashMap;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class SimpleCounters {
    public static void main(String args[]) {
        HashMap <String, Counter> map = new HashMap < > ();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Waiting for input.....");
            String inputLine;
            while (!(inputLine  = sc.nextLine() ).equals("exit")) {
                SimpleCounters.processInputLine(inputLine, map);
                if(!sc.hasNextLine()) {
                    System.out.println("Waiting for input.....");
                } else {
                    System.out.println(sc.nextLine());
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("System.in was closed; exiting");
        }
    }

    static void processInputLine(String inputLine, HashMap <String, Counter> map ) {
        if (inputLine.equals("ls")) {
            if (map.size() == 0) {
                System.out.println("No counters created yet");
            } else {
                System.out.println(map.size() + " counters found");
                map.forEach((key, counter) -> {
                    System.out.println("counter: " + key + " value: " + counter.getValue());
                });
            }
        } else {
            String[] parts = inputLine.split(" ");
            if (parts.length != 2) {
                System.out.println(inputLine + " is not recongnized command");
                return;
            }
            String cmd = parts[0];
            String counterName = parts[1];
            switch (cmd) {
                case "inc":
                    if (map.containsKey(counterName)) {
                        Counter c = map.get(counterName);
                        c.increment();
                        System.out.println("Counter '" + counterName + "' incremented");
                    } else {
                        System.out.println("counter '" + counterName + "' was never created. Create it before incrementing the value");
                    }
                    break;
                case "value":
                    if (map.containsKey(counterName)) {
                        Counter c = map.get(counterName);
                        System.out.println("Value of counter '" + counterName  + "' is" + c.getValue());
                    } else {
                        System.out.println("counter '" + counterName + "' was never created. Create it before incrementing the value");
                    }
                    break;
                case "create":
                    if (map.containsKey(counterName)) {
                        System.out.println("counter '" + counterName + "' aleady exists");
                    } else {
                        Counter c = new Counter();
                        map.put(counterName, c);
                        System.out.println("counter '" + counterName + "' created");
                    }
                    break;
                default:
                    System.out.println(inputLine + " is not a valid input");
            }
        }
    }
}

class Counter {
    public int count;

    void increment() {
        this.count = this.count + 1;
    }

    int getValue() {
        return this.count;
    }
}