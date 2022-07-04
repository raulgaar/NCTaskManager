package mx.tc.j2se.tasks;


public class ArrayTaskListImpl extends AbstractTaskList{
    private int size = 0;
    private Task[] taskList = new Task[size];

    public ArrayTaskListImpl(){

    }

    @Override
    public void add(Task task) {
        int index;
        size++;
        index = size - 1;
        Task [] taskListCopy = new Task[size];
        for(int i = 0; i < taskListCopy.length; i++) {
            if(i == taskListCopy.length -1) {
                taskListCopy[index] = task;
            }else {
                taskListCopy[i] = taskList[i];
            }
        }
        taskList = taskListCopy;

    }

    @Override
    public boolean remove(Task task) {
        if (task == null) {
            return false;
        }
        int taskCount = 0;
        int difTask = 0;
        for(int i = 0; i < size; i++){
            if (taskList[i] == task) {
                taskCount++;
            }
        }
        if(taskCount == 0){
            System.out.println("Task not found");
            return false;
        }
        Task [] taskListCopy = new Task[size - taskCount];
        for (int i = 0, j = 0; i < size; i++) {
            if (taskList[i] == task) {
                continue;
            }
            taskListCopy[j] = taskList[i];
            j++;

        }
        taskList = taskListCopy;
        size = taskList.length;
        return true;
    }

    @Override
    public int size() {
        size = taskList.length;
        return size;
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < size) {
            return taskList[index];
        }else {
            throw new IllegalArgumentException ("Index exceeds the limit of the list");
        }
    }
/*
    @Override
    public ArrayTaskList incoming(int from, int to) {
        if(from < 0 && to < 0 && to < from) {
            return null;
        }
        ArrayTaskList incomingTasks = new ArrayTaskListImpl();
        for (int i = 0; i < size; i++) {
            int nextTaskEvent = taskList[i].nextTimeAfter(from);
            if ((nextTaskEvent <= from) || (nextTaskEvent >= to)){
                continue;
            }
            incomingTasks.add(taskList[i]);


        }
        return incomingTasks;


    }*/
}
