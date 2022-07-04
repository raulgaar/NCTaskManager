package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedTaskListImplTest {

    @Test
    void add() {
        AbstractTaskList test = new LinkedTaskListImpl();
        Task task0 = new TaskImpl();
        Task task1 = new TaskImpl();
        Task task2 = new TaskImpl();
        Task task3 = new TaskImpl();
        Task task4 = new TaskImpl();
        Task task5 = new TaskImpl();
        Task task6 = new TaskImpl();
        Task task7 = new TaskImpl();
        Task task8 = new TaskImpl();
        Task task9 = new TaskImpl();
        Task task10 = new TaskImpl();
        task0.setTitle("test0");
        test.add(task0);
        assertEquals(task0, test.getTask(0));
        task0.setActive(true);

        task1.setTitle("test1");
        test.add(task1);
        task1.setActive(false);
        assertEquals(task1, test.getTask(1));

        task2.setTitle("test2");
        test.add(task2);
        task2.setActive(true);
        task3.setTitle("test3");
        test.add(task3);
        task3.setActive(true);
        task4.setTitle("test4");
        test.add(task4);
        task4.setActive(false);
        task5.setTitle("test5");
        test.add(task5);
        task5.setActive(true);
        task6.setTitle("test6");
        test.add(task6);
        task6.setActive(true);
        task7.setTitle("test7");
        test.add(task7);
        task7.setActive(false);
        task8.setTitle("test8");
        test.add(task8);
        task8.setActive(true);
        assertEquals(9, test.size());
        assertEquals(task2, test.getTask(2));
        test.remove(task3);
        test.remove(task4);
        test.remove(task7);
        assertEquals(6, test.size());
        assertEquals(task8, test.getTask(5));
        test.add(task4);
        test.add(task4);
        test.add(task2);
        assertEquals(9, test.size());
        test.remove(task4);
        test.remove(task2);
        assertEquals(5, test.size());
        test.remove(task9);
        assertEquals(5, test.size());
        test.remove(task10);
        assertEquals(5, test.size());
        test.remove(task2);
        assertEquals(5, test.size());
    }

    @Test
    void remove() {
    }

    @Test
    void size() {
    }

    @Test
    void getTask() {
    }

    @Test
    void incoming() {
        AbstractTaskList test = new LinkedTaskListImpl();
        AbstractTaskList eventOnLapse;
        Task task0 = new TaskImpl("Test 0", 0, 36, 1); //6
        Task task1 = new TaskImpl("Test 1", 3);
        Task task2 = new TaskImpl("Test 2", 50);
        Task task3 = new TaskImpl("Test 3", 3, 46, 2); //7
        Task task4 = new TaskImpl("Test 4", 2, 20, 4); //6
        Task task5 = new TaskImpl("Test 5", 10); //10
        Task task6 = new TaskImpl("Test 6", 25); //25
        Task task7 = new TaskImpl("Test 7", 15); //15
        Task task8 = new TaskImpl("Test 8", 10, 50, 10);//20
        //0- 5088
        Task lunch = new TaskImpl("Lunch", 4240);
        Task run = new TaskImpl("Morning run", 12, 4440,24);
        Task medication = new TaskImpl("Medication", 4152, 5088, 12);
        Task meetFriends = new TaskImpl("Meeting friends", 4446);

        task0.setActive(true);//si 0
        task1.setActive(false);
        task2.setActive(false);
        task3.setActive(true);//si 1
        task4.setActive(true);//si 2
        task5.setActive(true);//si 3
        task6.setActive(false);
        task7.setActive(true);//si 4
        task8.setActive(false);
        test.add(task0);
        test.add(task1);
        test.add(task2);
        test.add(task3);
        test.add(task4);
        test.add(task5);
        test.add(task6);
        test.add(task7);
        test.add(task8);
        eventOnLapse = test.incoming(5, 48);
        assertEquals(5, eventOnLapse.size());
        assertEquals(task0, eventOnLapse.getTask(0));
        assertEquals(task3, eventOnLapse.getTask(1));
        assertEquals(task4, eventOnLapse.getTask(2));
        assertEquals(task5, eventOnLapse.getTask(3));
        assertEquals(task7, eventOnLapse.getTask(4));
        test.remove(task0);
        test.remove(task1);
        test.remove(task2);
        test.remove(task3);
        test.remove(task4);
        test.remove(task5);
        test.remove(task6);
        test.remove(task7);
        test.remove(task8);
        test.add(lunch);
        test.add(run);
        test.add(medication);
        test.add(meetFriends);
        lunch.setActive(true);
        run.setActive(true);
        medication.setActive(true);
        meetFriends.setActive(true);
        System.out.println(run.nextTimeAfter(4256));
        System.out.println(medication.nextTimeAfter(4256));
        eventOnLapse = test.incoming(4256, 4280);
        System.out.println(eventOnLapse);
        assertEquals(2, eventOnLapse.size());
        assertEquals(run, eventOnLapse.getTask(0));
        assertEquals(medication, eventOnLapse.getTask(1));
        eventOnLapse = test.incoming(0, 1);
        assertEquals(0, eventOnLapse.size());
    }
}