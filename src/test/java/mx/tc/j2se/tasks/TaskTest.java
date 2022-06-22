package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void validateTitle(){
        Task task = new TaskImpl();
        Task task1 = new TaskImpl("test1", 5);
        Task task2 = new TaskImpl("test2", 0, 30, 2);
        //assertEquals("Empty", task.getTitle());
        task.setTitle("test0");
        assertEquals("test0", task.getTitle());
        assertEquals("test1", task1.getTitle());
        assertEquals("test2", task2.getTitle());
    //Page 1 paragraph 3
    }
    @Test
    void validateStatus(){
        Task task = new TaskImpl();
        Task task1 = new TaskImpl("test1", 5);
        Task task2 = new TaskImpl("test2", 0, 30, 2);
        assertFalse(task.isActive());
        assertFalse(task1.isActive());
        assertFalse(task2.isActive());
    //Page 1 paragraph 4
    }
    //testSetTime_NonRep_Configuration: Check that after using setTime(int time)
    // the result of isRepeated corresponds to the task type and the result of isActive is as expected.
    @Test
    void testSetTime_NonRep_Configuration(){
        Task task = new TaskImpl();
        assertNull(task.getTitle());
        task.setTitle("test1");
        assertEquals("test1", task.getTitle());
        //Test case NonRep inactive task
        assertFalse(task.isRepeated());
        assertFalse(task.isActive());
        task.setTime(36);
        //Test case NonRep active task
        assertFalse(task.isRepeated());
        assertTrue(task.isActive());
    }
    //testSetTime_NonRep_RepetitiveMethods: Check that the repetitive task related methods return
    // the correct information when you have a non-repetitive task.
    @Test
    void testSetTime_NonRep_RepetitiveMethods() {
        //Test case empty constructor
        Task task = new TaskImpl();

        assertNull(task.getTitle());
        task.setTitle("test");
        assertEquals("test", task.getTitle());

        assertEquals(0, task.getTime());
        assertFalse(task.isRepeated());
        assertFalse(task.isActive());

        task.setTime(36);
        assertFalse(task.isRepeated());
        assertEquals(36, task.getTime());
        assertTrue(task.isActive());

        assertEquals(36, task.getStartTime());
        assertEquals(36, task.getEndTime());
        assertEquals(0, task.getRepeatInterval());
        assertFalse(task.isRepeated());

        task.setTime(0, 36, 0);
        assertTrue(task.isRepeated());
        task.setTime(0, 36, 1);
        assertTrue(task.isRepeated());
        task.setTime(0, 36, 15);
        assertTrue(task.isRepeated());
        task.setTime(0, 36, 50);
        assertTrue(task.isRepeated());

        task.setTime(1, 36, 1);
        assertTrue(task.isRepeated());
        task.setTime(20, 36, 1);
        assertTrue(task.isRepeated());
        task.setTime(50, 36, 1);
        assertTrue(task.isRepeated());

        //Test case given title and time
        Task task1 = new TaskImpl("test1", 7);

    }
    @Test
    void emptyProperties(){
        Task task = new TaskImpl();
        assertNull(task.getTitle());
        assertFalse(task.isActive());
        assertEquals(0, task.getTime());
    }
    @Test
    void setTime() {
        Task task = new TaskImpl();

        task.setTime(0, 10, 1);
        task.setActive(true);
        System.out.println(task.isActive());
        assertEquals(-1, task.nextTimeAfter(10));
    }


}