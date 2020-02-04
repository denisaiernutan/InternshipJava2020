public class MainThread {


//    public static void count() {
//        MyThread thread = new MyThread();
//        thread.start();
//        //se intercaleaza cu ceea ce face thread.run
//        for (int i = 0; i < 50; i++)
//            System.out.println("Din MainThread: i = " + i);
//    }
//
//    public static void calcPi() {
//        MyThread mt = new MyThread();
//        mt.start();
//        try {
//            //thread-ul lui main asteapta dupa thread-ul mt
//            mt.join();
//
//        } catch (InterruptedException e) {
//        }
//        System.out.println("pi = " + mt.pi);
//    }

//    public static void census() {
//        Thread[] threads = new Thread[Thread.activeCount()];
//        //pun threadurile in threads!
//        int n = Thread.enumerate(threads);
//        for (int i = 0; i < n; i++) {
//          //  threads[i].setName("thread_" + i);
//            System.out.println(threads[i].toString());
//        }
//    }

    public static void needForSyncronization(){
        FinTrans ft = new FinTrans ();
        MyThread tt1 = new MyThread (ft, "Deposit Thread");
        MyThread tt2 = new MyThread (ft, "Withdrawal Thread");
        tt1.start ();
        tt2.start ();
    }


    public static void main(String[] args) {
        //calcPi();
       // census();
        needForSyncronization();
    }
}
