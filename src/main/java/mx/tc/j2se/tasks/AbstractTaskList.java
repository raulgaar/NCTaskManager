package mx.tc.j2se.tasks;

abstract class AbstractTaskList {
    void add(Task task) {

    }

    boolean remove(Task task) {
        return false;
    }

    int size() {
        return 0;
    }

    Task getTask(int index) {
        return null;
    }

    AbstractTaskList incoming(int from, int to) {
        AbstractTaskList incomingTasks = new ArrayTaskListImpl();

        int nextTaskEvent;
        for(int i = 0; i < size(); i++) {
            nextTaskEvent = getTask(i).nextTimeAfter(from);
            Task task = getTask(i);
            if ((nextTaskEvent <= from) || (nextTaskEvent >= to)){
                continue;
            }
            incomingTasks.add(task);
        }
        return incomingTasks;
    }
}
