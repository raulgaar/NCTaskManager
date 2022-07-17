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
        assertEquals(1, task.hashCode());
        assertEquals(2, task1.hashCode());
        assertEquals(3, task2.hashCode());
        assertEquals(1, task.hashCode());

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

        task.setActive(true);
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

        task.setActive(true);
        assertTrue(task.isActive());

        assertEquals(36, task.getStartTime());
        assertEquals(36, task.getEndTime());
        assertEquals(0, task.getRepeatInterval());
        assertFalse(task.isRepeated());

        task.setTime(50, 36, 1);
        assertFalse(task.isRepeated());
        task.setTime(0, 36, 50);
        assertFalse(task.isRepeated());

        task.setTime(0, 36, 0);
        assertTrue(task.isRepeated());
        task.setTime(0, 36, 1);
        assertTrue(task.isRepeated());
        task.setTime(0, 36, 15);
        assertTrue(task.isRepeated());

        task.setTime(1, 36, 1);
        assertTrue(task.isRepeated());
        task.setTime(20, 36, 1);
        assertTrue(task.isRepeated());


        //Test case given title and time
        Task task1 = new TaskImpl("test1", 7);
        assertEquals("test1", task1.getTitle());
        assertEquals(7, task1.getTime());
        assertEquals(7, task1.getStartTime());
        assertEquals(7, task1.getEndTime());
        assertFalse(task1.isRepeated());
        assertFalse(task1.isActive());

        task1.setActive(true);
        assertTrue(task1.isActive());

    }
    //testSetTime_Repetitive_Configuration: Check that after using setTime(int start, int end, int interval)
    // the result of isRepeated corresponds to the task type and the result of isActive is as expected.
    @Test
    void testSetTime_Repetitive_Configuration(){
        Task task = new TaskImpl();
        assertNull(task.getTitle());
        assertEquals(0, task.getTime());
        assertEquals(0, task.getStartTime());
        assertEquals(0, task.getEndTime());
        assertFalse(task.isRepeated());
        assertFalse(task.isActive());

        task.setTime(0, 36,0);
        assertEquals(0, task.getTime());
        assertEquals(0, task.getStartTime());
        assertEquals(36, task.getEndTime());
        assertEquals(0, task.getRepeatInterval());

        task.setTime(3, 36,2);
        assertEquals(3, task.getTime());
        assertEquals(3, task.getStartTime());
        assertEquals(36, task.getEndTime());
        assertEquals(2, task.getRepeatInterval());
        assertFalse(task.isActive());
        assertTrue(task.isRepeated());

        task.setActive(true);
        assertTrue(task.isActive());
        assertTrue(task.isRepeated());


    }

    //Test case testNextTimeAfter_NonRep: Check nextTimeAfter method for a non-repetitive active and inactive task.
    // Make sure it returns the expected result when checking before, on the time (exclusive),
    // and after the time of execution.
    @Test
    void testNextTimeAfter_NonRep() {
        Task task = new TaskImpl();
        assertNull(task.getTitle());
        assertEquals(0, task.getTime());
        assertEquals(0, task.getStartTime());
        assertEquals(0, task.getEndTime());
        assertEquals(0, task.getRepeatInterval());
        assertFalse(task.isActive());
        assertFalse(task.isRepeated());
        assertEquals(-1, task.nextTimeAfter(6));

        task.setTitle("test");
        assertEquals("test", task.getTitle());
        task.setTime(6);
        assertEquals(6, task.getTime());
        assertEquals(6, task.getStartTime());
        assertEquals(6, task.getEndTime());
        assertEquals(0, task.getRepeatInterval());
        assertFalse(task.isRepeated());
        assertFalse(task.isActive());
        assertEquals(-1, task.nextTimeAfter(6));

        task.setActive(true);
        assertTrue(task.isActive());
        assertEquals(-1, task.nextTimeAfter(6));

        task.setActive(false);
        assertFalse(task.isActive());

        /*task.setTime(6, 36, 0);
        assertEquals(6, task.getTime());
        assertEquals(6, task.getStartTime());
        assertEquals(36, task.getEndTime());
        assertTrue(task.isRepeated());
        assertEquals(0, task.getRepeatInterval());
        assertFalse(task.isActive());
        assertEquals(-1, task.nextTimeAfter(0));
        assertEquals(-1, task.nextTimeAfter(6));
        assertEquals(-1, task.nextTimeAfter(16));
        assertEquals(-1, task.nextTimeAfter(36));
        assertEquals(-1, task.nextTimeAfter(46));

        task.setActive(true);
        assertTrue(task.isActive());

        task.setTime(6, 36, 0);
        assertEquals(6, task.getTime());
        assertEquals(6, task.getStartTime());
        assertEquals(36, task.getEndTime());
        assertTrue(task.isRepeated());
        assertEquals(0, task.getRepeatInterval());
        assertTrue(task.isActive());
        assertEquals(6, task.nextTimeAfter(0));
        assertEquals(-1, task.nextTimeAfter(6));
        assertEquals(-1, task.nextTimeAfter(16));
        assertEquals(-1, task.nextTimeAfter(36));
        assertEquals(-1, task.nextTimeAfter(46));


         */
        task.setActive(false);
        assertFalse(task.isActive());

        task.setTime(6, 36, 1);
        assertEquals(6, task.getTime());
        assertEquals(6, task.getStartTime());
        assertEquals(36, task.getEndTime());
        assertTrue(task.isRepeated());
        assertEquals(1, task.getRepeatInterval());
        assertFalse(task.isActive());
        assertEquals(-1, task.nextTimeAfter(0));
        assertEquals(-1, task.nextTimeAfter(6));
        assertEquals(-1, task.nextTimeAfter(16));
        assertEquals(-1, task.nextTimeAfter(36));
        assertEquals(-1, task.nextTimeAfter(46));

        task.setActive(true);
        assertTrue(task.isActive());

        task.setTime(6, 36, 1);
        assertEquals(6, task.getTime());
        assertEquals(6, task.getStartTime());
        assertEquals(36, task.getEndTime());
        assertTrue(task.isRepeated());
        assertEquals(1, task.getRepeatInterval());
        assertTrue(task.isActive());
        assertEquals(6, task.nextTimeAfter(0));
        assertEquals(7, task.nextTimeAfter(6));
        assertEquals(17, task.nextTimeAfter(16));
        assertEquals(36, task.nextTimeAfter(35));
        assertEquals(-1, task.nextTimeAfter(36));
        assertEquals(-1, task.nextTimeAfter(46));

        task.setActive(false);
        assertFalse(task.isActive());

        task.setTime(6, 36, 2);
        assertEquals(6, task.getTime());
        assertEquals(6, task.getStartTime());
        assertEquals(36, task.getEndTime());
        assertTrue(task.isRepeated());
        assertEquals(2, task.getRepeatInterval());
        assertFalse(task.isActive());
        assertEquals(-1, task.nextTimeAfter(0));
        assertEquals(-1, task.nextTimeAfter(6));
        assertEquals(-1, task.nextTimeAfter(16));
        assertEquals(-1, task.nextTimeAfter(36));
        assertEquals(-1, task.nextTimeAfter(46));

        task.setActive(true);
        assertTrue(task.isActive());

        task.setTime(6, 36, 2);
        assertEquals(6, task.getTime());
        assertEquals(6, task.getStartTime());
        assertEquals(36, task.getEndTime());
        assertTrue(task.isRepeated());
        assertEquals(2, task.getRepeatInterval());
        assertTrue(task.isActive());
        assertEquals(6, task.nextTimeAfter(0));
        assertEquals(8, task.nextTimeAfter(6));
        assertEquals(14, task.nextTimeAfter(13));
        assertEquals(18, task.nextTimeAfter(17));
        assertEquals(36, task.nextTimeAfter(35));
        assertEquals(-1, task.nextTimeAfter(36));
        assertEquals(-1, task.nextTimeAfter(46));

    }

    //Test case

}