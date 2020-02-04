public class MyThread extends Thread {

    boolean negative = true;
    double pi;
    private FinTrans ft;
    private static String anotherSharedLock = "";

    MyThread(FinTrans ft, String name) {
        super(name); // Save thread's name
        this.ft = ft; // Save reference to financial transaction object
    }

//        public void run ()
//        {
//            for (int count = 1, row = 1; row < 20; row++, count++)
//            {
//                for (int i = 0; i < count; i++)
//                    System.out.print (i  +" ");
//                System.out.print ('\n');
//            }
//        }

//    public void run ()
//    {
//        for (int i = 3; i < 100000; i += 2)
//        {
//            if (negative)
//                pi -= (1.0 / i);
//            else
//                pi += (1.0 / i);
//            negative = !negative;
//        }
//        pi += 1.0;
//        pi *= 4.0;
//        System.out.println ("Finished calculating PI");
//    }

    //needForSyncronization
//    public void run() {
//
//        for (int i = 0; i < 100; i++)
//            if (getName().equals("Deposit Thread"))
//                ft.update("Deposit", 2000.0);
//            else
//                ft.update("Withdrawal", 250.0);
//    }

    //Deadlock

    public void run ()
    {
        for (int i = 0; i < 100; i++)
        {
            if (getName ().equals ("Deposit Thread"))
            {
                synchronized (ft)
                {
                    synchronized (anotherSharedLock)
                    {
                        ft.transName = "Deposit";
                        try
                        {
                            Thread.sleep ((int) (Math.random () * 1000));
                        }
                        catch (InterruptedException e)
                        {
                        }
                        ft.amount = 2000.0;
                        System.out.println (ft.transName + " " + ft.amount);
                    }
                }
            }
            else
            {
                synchronized (anotherSharedLock)
                {
                    synchronized (ft)
                    {
                        ft.transName = "Withdrawal";
                        try
                        {
                            Thread.sleep ((int) (Math.random () * 1000));
                        }
                        catch (InterruptedException e)
                        {
                        }
                        ft.amount = 250.0;
                        System.out.println (ft.transName + " " + ft.amount);
                    }
                }
            }
        }
    }
}

