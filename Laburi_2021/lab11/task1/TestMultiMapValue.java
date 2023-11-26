package lab11.task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMultiMapValue {
    private MultiMapValue<String, String> map;

    @BeforeEach
    public void setup() {
        map = new MultiMapValue<>();
    }

    @AfterEach
    public void clean() {
        map = null;
    }

    @Test
    public void testAddElementWithOneValue() {
        map.add("key", "value");
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(1, map.getValues("key").size());
    }

    @Test
    public void testAddElementWithMoreValues() {
        map.add("key", "value1");
        map.add("key", "value2");
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(2, map.getValues("key").size());
    }

    @Test
    public void testAddTwoElements() {
        map.add("key1", "value1");
        map.add("key2", "value2");
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(2, map.size());
    }

    @Test
    public void testGetFirst() {
        List<String> listOfGirls = new ArrayList<>();
        listOfGirls.add("Ana");
        listOfGirls.add("Alexandra");
        listOfGirls.add("Maria");
        map.addAll("girls", listOfGirls);
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue("Ana".equals(map.getFirst("girls")));
    }

    @Test
    public void testContainsKey() {
        map.add("tehnologie", "TV");
        map.add("haine", "fusta");
        map.add("dulciuri", "ciocolata");
        map.add("rechizite", "ghiozdan");
        map.add("animale", "pisica");
        Assertions.assertTrue(map.containsKey("rechizite"));
        Assertions.assertFalse(map.containsKey("bautura"));
    }

    @Test
    public void testSize() {
        map.add("tehnologie", "TV");
        map.add("haine", "fusta");
        map.add("dulciuri", "ciocolata");
        map.add("rechizite", "ghiozdan");
        map.add("animale", "pisica");
        Assertions.assertEquals(5, map.size());
        Assertions.assertNotEquals(10, map.size());
    }

    @Test
    public void testRemoveKey() {
        map.add("tehnologie", "TV");
        map.add("haine", "fusta");
        map.add("dulciuri", "ciocolata");
        map.add("rechizite", "ghiozdan");
        map.add("animale", "pisica");

        List<String> listBeforeRemove = new ArrayList<>();
        for(String key : map.getHashMap().keySet()) {
            listBeforeRemove.add(key);
        }
        map.remove("dulciuri");
        map.remove("tehnologie");

        List<String> listAfterRemove = new ArrayList<>();
        for(String key : map.getHashMap().keySet()) {
            listAfterRemove.add(key);
        }
        Assertions.assertNotEquals(listAfterRemove, listBeforeRemove);
    }

    @Test
    public void testAddAllWithList() {
        List<String> listOfGirls = new ArrayList<>();
        listOfGirls.add("Ana");
        listOfGirls.add("Alexandra");
        listOfGirls.add("Maria");

        List<String> listOfBoys = new ArrayList<>();
        listOfBoys.add("Rares");
        listOfBoys.add("Mihai");
        listOfBoys.add("Stefan");

        map.addAll("girls", listOfGirls);
        map.addAll("boys", listOfBoys);

        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(2, map.size());
        Assertions.assertTrue("Rares".equals(map.getFirst("boys")));
    }

    @Test
    public void testAddAllWithMultiMapValue() {
        map.add("tehnologie", "TV");
        map.add("haine", "fusta");
        map.add("dulciuri", "ciocolata");
        map.add("rechizite", "ghiozdan");

        MultiMapValue<String, String> newMap = new MultiMapValue<>();
        newMap.add("animale", "caine");
        newMap.add("bautura", "cola");
        newMap.addAll(map);

        Assertions.assertEquals(6, newMap.size());
        Assertions.assertTrue(newMap.containsKey("haine"));
    }
}
