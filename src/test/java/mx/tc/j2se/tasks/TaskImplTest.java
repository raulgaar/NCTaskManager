package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskImplTest {

    @Test
    void getTitle() {
        Task task = new TaskImpl("This is a title", 1);
        assertEquals("Task", task.getTitle());
    }

    @Test
    void setTitle() {
        Task task = new TaskImpl();

        assertEquals("Task", task.getTitle());
    }

    @Test
    void isActive() {
        Task task = new TaskImpl();
        task.setActive(true);
        assertEquals(true, task.isActive());
    }

    @Test
    void setActive() {
    }

    @Test
    void getTime() {
    }

    @Test
    void setTime() {
        Task task = new TaskImpl();
        assertEquals(5, task.getTime());
        assertFalse(task.isRepeated());
    }
}