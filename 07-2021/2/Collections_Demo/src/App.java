import java.util.*;

class ArrayListImplementation {
    public void array_list() {
        System.out.println("Array List Implementation : \n");

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(list);
        System.out.println();

    }
}

class LinkedListImplementation {
    public void linked_list() {
        System.out.println("Linked List Implementation : \n");

        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.forEach(i -> System.out.println(i));
        System.out.println();

        list.add(2, 3);
        list.forEach(i -> System.out.println(i));
        System.out.println();

        List<Integer> list2 = new LinkedList<>();
        list2.add(11);
        list2.add(12);
        list2.add(13);
        ((LinkedList<Integer>) list2).addFirst(10);
        ((LinkedList<Integer>) list2).addLast(14);

        list.addAll(0, list2);
        System.out.println(list);
        System.out.println();

        list.removeAll(list2);
        System.out.println(list);
        System.out.println();

    }
}

class HashSetImplementation {
    public void hash_set() {
        System.out.println("Hash Set Implementation : \n");
        Set<String> set = new HashSet<>();
        set.add("One");
        set.add("Two");
        set.add("Three");
        set.add("Four");
        set.add("Five");

        set.forEach(i -> System.out.println(i));

        Set<String> set2 = new HashSet<>();
        set2.add("Six");
        set2.add("Seven");
        set2.add("Eight");

        set.addAll(set2);
        System.out.println(set);

        set.removeIf(i -> i.contains("Eight"));

        System.out.println(set);

        set.removeAll(set2);
        System.out.println(set);
        System.out.println();
    }
}

class LinkedHashSetImplementation {
    public void linked_hash_set() {
        System.out.println("Linked Hash Set Implementation : \n");
        Set<String> set = new LinkedHashSet<>();
        set.add("One");
        set.add("Two");
        set.add("Three");
        set.add("Four");
        set.add("Five");

        System.out.println(set);

        Set<String> set1 = new LinkedHashSet<>();
        set1.add("Six");
        set1.add("Seven");
        set1.add("Eight");

        set.addAll(set1);

        set.forEach(i -> System.out.println(i));

        System.out.println(set);

        set.removeAll(set1);
        System.out.println(set);

        System.out.println();
    }
}

class TreeSetImplementation {
    public void tree_set() {
        System.out.println("Tree Set Implementation : \n");
        TreeSet<String> set = new TreeSet<>();
        set.add("One");
        set.add("Two");
        set.add("Three");
        set.add("Four");
        set.add("Five");

        System.out.println("Original Set : " + set);
        System.out.println("Descending set : " + set.descendingSet());

        System.out.println();
    }
}

class StudentDetails implements Comparable<StudentDetails> {
    int mark;
    String name;

    public StudentDetails(int mark, String name) {
        this.mark = mark;
        this.name = name;
    }

    @Override
    public int compareTo(StudentDetails o) {
        return mark > o.mark ? 1 : -1;
    }

    @Override
    public String toString() {
        String result = "Student name : " + this.name + " " + "Student mark : " + this.mark + "\n";
        return result;
    }
}

class PriorityQueueImplementation {
    public void priorityQueueImplementation() {
        System.out.println("Priority Queue Implementation : \n");
        PriorityQueue<StudentDetails> pQueue = new PriorityQueue<>();
        pQueue.add(new StudentDetails(3, "Tom"));
        pQueue.add(new StudentDetails(1, "Dick"));
        pQueue.add(new StudentDetails(8, "Harry"));

        System.out.println("Size of the priority Queue is : " + pQueue.size());

        Iterator<StudentDetails> iterator = pQueue.iterator();
        System.out.println(pQueue.toString());
        System.out.println();
        System.out.println("To String");
        while (iterator.hasNext()) {
            System.out.println(pQueue.poll().toString());
        }
        System.out.println(pQueue.toString());
        pQueue.offer(new StudentDetails(3, "Barry"));
        pQueue.offer(new StudentDetails(2, "Wells"));

        System.out.println(pQueue.toString());

    }
}

class HashMapImplementation {
    public void hashMapImplementation() {
        System.out.println("Hash Map Implementation : \n");

        Random rand = new Random();
        int upperBound = 10;

        Map<Integer, String> map = new HashMap<>();
        map.put(3, "Dick");
        map.put(1, "Tom");
        map.put(2, "Harry");

        for (Map.Entry<Integer,String> mapEntry: map.entrySet()) {
            System.out.println("key : " + mapEntry.getKey() + "  " + "value : " + mapEntry.getValue());
        }
        System.out.println();
        System.out.println(map.entrySet());
        System.out.println();

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(4, "Harrison");
        map2.put(5, "Wells");
        map2.put(6, "Barry");
        map2.put(7, "Allen");

        Comparator<Integer> c = (i, j) -> i * rand.nextInt(upperBound) > j * rand.nextInt(upperBound) ? 1 : -1;

        map2.entrySet().stream().sorted(Map.Entry.comparingByKey(c)).forEach(System.out::println);
        System.out.println();

        map.putAll(map2);
        map.replace(2, "Harry", "Dick");
        map.replace(3, "Dick", "Harry");
        map.entrySet().stream().forEach(System.out::println);

        System.out.println();
    }
}

class LinkedHashMapImplementation{
    public void linkedHashMapImplementation(){
        System.out.println("Linked Hash Map Implementation : \n");

        Map<Integer,String> map = new LinkedHashMap<>();
        map.put(1, "Naveen");
        map.put(2, "Nithish");
        map.put(3, "Faiyaz");

        System.out.println(map);
        System.out.println();
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome");
        ArrayListImplementation a_list = new ArrayListImplementation();
        a_list.array_list();

        System.out.println("---------------------------------");

        LinkedListImplementation l_list = new LinkedListImplementation();
        l_list.linked_list();

        System.out.println("---------------------------------");

        HashSetImplementation h_set = new HashSetImplementation();
        h_set.hash_set();

        System.out.println("---------------------------------");

        LinkedHashSetImplementation l_hset = new LinkedHashSetImplementation();
        l_hset.linked_hash_set();

        System.out.println("---------------------------------");

        TreeSetImplementation t_set = new TreeSetImplementation();
        t_set.tree_set();

        System.out.println("---------------------------------");

        PriorityQueueImplementation pQueue = new PriorityQueueImplementation();
        pQueue.priorityQueueImplementation();

        System.out.println("---------------------------------");

        HashMapImplementation hMap = new HashMapImplementation();
        hMap.hashMapImplementation();

        System.out.println("---------------------------------");

        LinkedHashMapImplementation lMap = new LinkedHashMapImplementation();
        lMap.linkedHashMapImplementation();

    }
}
