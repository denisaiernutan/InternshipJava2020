class FinTrans {
    public static String transName;
    public static double amount;

    synchronized void update(String transName, double amount) {
        this.transName = transName;
        this.amount = amount;
        System.out.println(this.transName + " " + this.amount);
    }
}