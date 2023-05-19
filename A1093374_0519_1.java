import java.util.Random;

class A1093374_0519_1 {
    private static final int PORK_DUMPLINGS = 5000;
    private static final int BEEF_DUMPLINGS = 3000;
    private static final int VEGETABLE_DUMPLINGS = 1000;

    private static int porkStock = PORK_DUMPLINGS;
    private static int beefStock = BEEF_DUMPLINGS;
    private static int vegetableStock = VEGETABLE_DUMPLINGS;

    private static final Object lock = new Object();

    private static void serveCustomer(int customerNumber) {
        Random random = new Random();
        int dumplingCount = random.nextInt(41) + 10; // 10 to 50 dumplings
        String dumplingType = getRandomDumplingType();

        System.out.println("Customer " + customerNumber + " ordered " + dumplingCount + " " + dumplingType + " dumplings.");

        synchronized (lock) {
            switch (dumplingType) {
                case "pork":
                    if (porkStock >= dumplingCount) {
                        porkStock -= dumplingCount;
                        System.out.println("Customer " + customerNumber + " got " + dumplingCount + " " + dumplingType + " dumplings. Remaining stock: " + porkStock + " pork dumplings.");
                    } else {
                        System.out.println("Customer " + customerNumber + " cannot be served. Out of stock for " + dumplingType + " dumplings.");
                    }
                    break;
                case "beef":
                    if (beefStock >= dumplingCount) {
                        beefStock -= dumplingCount;
                        System.out.println("Customer " + customerNumber + " got " + dumplingCount + " " + dumplingType + " dumplings. Remaining stock: " + beefStock + " beef dumplings.");
                    } else {
                        System.out.println("Customer " + customerNumber + " cannot be served. Out of stock for " + dumplingType + " dumplings.");
                    }
                    break;
                case "vegetable":
                    if (vegetableStock >= dumplingCount) {
                        vegetableStock -= dumplingCount;
                        System.out.println("Customer " + customerNumber + " got " + dumplingCount + " " + dumplingType + " dumplings. Remaining stock: " + vegetableStock + " vegetable dumplings.");
                    } else {
                        System.out.println("Customer " + customerNumber + " cannot be served. Out of stock for " + dumplingType + " dumplings.");
                    }
                    break;
            }
        }

        try {
            Thread.sleep(3000); // Wait for the next customer
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getRandomDumplingType() {
        Random random = new Random();
        int randomNum = random.nextInt(3);
        switch (randomNum) {
            case 0:
                return "pork";
            case 1:
                return "beef";
            case 2:
                return "vegetable";
            default:
                return "unknown";
        }
    }

    public static void main(String[] args) {
        int numberOfCustomers = 10; // Default number of customers

        if (args.length > 0) {
            numberOfCustomers = Integer.parseInt(args[0]);
        }
	for (int i = 1; i <= numberOfCustomers; i++) {
   	 final int customerNumber = i; // 將 i 複製到一個 final 變數中
    		Thread customerThread = new Thread(() -> serveCustomer(customerNumber));
    			customerThread.start();
	}

    }
}
