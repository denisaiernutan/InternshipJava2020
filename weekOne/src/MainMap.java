import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainMap {

    public static void printMap(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Key= " + entry.getKey() + " Value=" + entry.getValue());
        }
    }

    public static void doLinkedHashMap() {
        Map<Integer, String> hashMap = new LinkedHashMap<>();
        hashMap.put(23, "fdsfs");
        hashMap.put(12, "gfdsg");
        hashMap.put(10, "Fdsfg");
        hashMap.put(21,"fdsfs");

       System.out.println("in hashmap gasim valoarea fdsfs:" + hashMap.containsValue("fdsfs"));
        printMap(hashMap);
    }

    public static void doHashTable(){
        Map<Integer, String > hashTable= new Hashtable<>(4);
        hashTable.put(34,"gf");
        hashTable.put(12,"fdsf");
        hashTable.put(10,"fxdf");
        hashTable.put(67,"sbad");

        System.out.println( "key = 34 , value =" +hashTable.get(34));
        System.out.println("in hashTable gasim valoarea gf:" + hashTable.containsValue("gf"));
        printMap(hashTable);


    }
    public static void main(String[] args) {
//        doLinkedHashMap();
        doHashTable();
    }

}
