package org.cubbs;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class DataStructuresTest
    extends TestCase
{

    public static final Key ONE = new Key("one", 1.0);
    public static final Key TWO = new Key("two", 2.0);
    public static final Key THREE = new Key("three", 3.0);

    public DataStructuresTest(String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( DataStructuresTest.class );
    }

    public void testStructs() {
        Key k = new Key("one", 1.0);

        assertEquals("one", k.name);
        assertEquals(1.0, k.cost,0.001);
        assertEquals(ONE, k);
    }


    public void testArrays()
    {
        Key[] keyArray = new Key[5];

        //set
        keyArray[0] = ONE;

        //length, not size() :(
        assertEquals(5, keyArray.length);

        //get
        assertEquals(ONE, keyArray[0]);

        //unassigned elements are null
        assertNull(keyArray[1]);
    }

    public void testMaps(){

        Value oneValue = new Value("The loneliest number", "that you'll ever do");

        Value twoValue = new Value("The loneliest number", "since the number one");

        Map<Key,Value> map = new HashMap<Key, Value>();

        //puts
        map.put(ONE, oneValue);
        map.put(TWO, twoValue);

        assertEquals(2, map.size());

        //gets
        assertEquals(oneValue, map.get(ONE));
        assertEquals(twoValue, map.get(TWO));
        assertEquals(null, map.get(THREE));

        //watch out for this case!
        assertEquals(null, map.get("garbage"));


        //foreach
        for (Key k:map.keySet()){
            System.out.println("K=" + k + " V=" + map.get(k));
        }


        //replace a key...
        map.put(ONE, twoValue);
        assertEquals(twoValue, map.get(ONE));

    }

    public void testSets(){
        Key redKey = new Key("red", 3.0);
        Key blueKey = new Key("blue", 4.0);

        Key repeatKey = new Key("red", 3.0);


        //put
        Set<Key> s = new HashSet<Key>();
        s.add(ONE);
        s.add(TWO);
        s.add(redKey);
        s.add(blueKey);
        s.add(repeatKey);

        //no duplicates stored
        assertEquals(4, s.size());

        //get
        assertTrue(s.contains(repeatKey));
        assertFalse(s.contains(new Key("notinhere",0.0)));

        System.out.println("Unsorted");
        //foreach, no order guaranteed
        for(Key k:s){
            System.out.println(k);
        }


        //sorted set, initialized with the contents of the last set
        SortedSet<Key> sortedSet = new TreeSet<Key>(s);
        //same get semantics
        assertTrue(sortedSet.contains(repeatKey));
        assertFalse(sortedSet.contains(new Key("notinhere",0.0)));

        System.out.println("Sorted");
        //foreach, increasing order is guaranteed this time
        Key lastKey = null;
        for(Key k:sortedSet){
            System.out.println(k);
            if (lastKey != null){
                assertTrue(lastKey.cost <= k.cost);
            }
            lastKey = k;
        }

    }

    public void testLists(){
        List<Key> list = new ArrayList<Key>();

        //append
        list.add(ONE);
        list.add(THREE);
        list.add(TWO);

        //head
        assertEquals(ONE,list.get(0));

        //random access? ArrayList makes it fast
        assertEquals(TWO,list.get(2));

        //sublisting in arrayList is slow
        List<Key> tail = list.subList(1,list.size());
        for (Key k:tail) System.out.println(k);
    }

    public void testStack(){
        Deque<Key> stack = new ArrayDeque<Key>();
        //push
        stack.push(ONE);
        stack.push(THREE);
        stack.push(TWO);

        //peek at the top
        assertEquals(TWO, stack.peek());

        //pop
        assertEquals(TWO, stack.pop());
        assertEquals(THREE, stack.pop());
        assertEquals(ONE, stack.pop());

        assertTrue(stack.isEmpty());
    }

    public void testQueue(){
        Deque<Key> stack = new ArrayDeque<Key>();
        //push
        stack.offer(ONE);
        stack.offer(THREE);
        stack.offer(TWO);

        //peek at the top
        assertEquals(ONE, stack.peek());

        //pop
        assertEquals(ONE, stack.poll());
        assertEquals(THREE, stack.poll());
        assertEquals(TWO, stack.poll());

        assertTrue(stack.isEmpty());
    }

    public void testPriorityQueue(){
        PriorityQueue<Key> pq = new PriorityQueue<Key>();
        //append
        pq.add(new Key("dying", 1.0));
        pq.add(new Key("cough", 3.0));
        pq.add(new Key("broken ribs", 2.0));

        //peek at the top
        assertEquals(new Key("dying", 1.0), pq.peek());

        //polls happen in order
        assertEquals(new Key("dying", 1.0), pq.poll());
        assertEquals(new Key("broken ribs", 2.0), pq.poll());
        assertEquals(new Key("cough", 3.0), pq.poll());

        assertTrue(pq.isEmpty());
    }



}
