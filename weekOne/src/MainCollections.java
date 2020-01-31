import java.util.*;

public class MainCollections {

    public static void printCollection(Collection collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            String toPrint = (String) iterator.next();
            System.out.println(toPrint);
        }
    }

    public static void printQueue(Queue queue){
        while(queue.size()!=0)
        {
            System.out.println(queue.peek());
            queue.remove();
        }

    }

    public static void doTreeSetColletion() {
        System.out.println("TreeSet-------------");
        //String are Comparator
        SortedSet<String> sortedSet = new TreeSet<>();

        sortedSet.add("one");
        sortedSet.add("aabcd");
        sortedSet.add("bbcd");
      //  sortedSet.add("bbcd"); // il afiseaza numai o data
        sortedSet.add("xvgd");

        printCollection(sortedSet);
        SortedSet tailSet = sortedSet.tailSet("b");
        System.out.println("Tail of Sorted set--------------");
        printCollection(tailSet);
    }

    public static void doHashSetCollection() {
        System.out.println("HashSet-------------");
        Set hashSet = new HashSet(4);
        hashSet.add("one");
        hashSet.add("bbcd");
        hashSet.add("aabcd");
   //     hashSet.add("bbcd"); // il afiseaza in functie de ultima adaugare
        // one, aabcd, bbcd

        printCollection(hashSet);
    }

    public static void  doArrayList()
    {
        List arrayList= new ArrayList();

        long timeMilli = new Date().getTime();
        for(int i=0;i<2000000;i++){
            arrayList.add(1);
        }
        long milliToPrint= new Date().getTime()- timeMilli;
        System.out.println("fara sa initializam lungimea "+ milliToPrint);

        timeMilli = new Date().getTime();
        List arrayListWithKnownSize= new ArrayList(2000000);
        for(int i=0;i<2000000;i++){
            arrayListWithKnownSize.add(1);
        }

        milliToPrint= new Date().getTime()- timeMilli;
        System.out.println("cand am  initializam lungimea "+ milliToPrint);

    }
    public static void doPriorityQueueCollection() {
        System.out.println("Priority Queue-------------");
        Queue<String> queue = new PriorityQueue<>(6);
        queue.add("fsd");
        queue.add("avcs");
        queue.add("acdsac");
        queue.add("zadfc");
        queue.add("cxvds");

        //asa ia elementele in ordine alfabetica
         printQueue(queue);

        //aici le ia RANDOM! din cauza iteratorului
        printCollection(queue);
        System.out.println("Priority Queue- Persons-------------");
        Queue<Person> queuePersons= new PriorityQueue<>(3);
        queuePersons.add(new Person("dsfs",23));
        queuePersons.add(new Person("aaasfs",45));
        queuePersons.add(new Person("bbbb",12));

        printQueue(queuePersons);

    }




    public static void main(String[] args) {
//        doTreeSetColletion();
//        doHashSetCollection();
//        doPriorityQueueCollection();
          doArrayList();


    }


}
