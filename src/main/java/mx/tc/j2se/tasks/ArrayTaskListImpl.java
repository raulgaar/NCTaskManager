package mx.tc.j2se.tasks;


public class ArrayTaskListImpl extends AbstractTaskList{
    private int size = 0; //local variable that indicates the size of the array
    private Task[] taskList = new Task[size]; //local array of tasks

    public ArrayTaskListImpl(){

    }
    /*
     * Adds a new task to the list of tasks
     * @param task
     * */
    @Override
    public void add(Task task) {
        int index;  //local variable that indicates the index of the task in the array
        size++;     //increment the size of the array
        index = size - 1; //index of the last task in the array
        Task [] taskListCopy = new Task[size]; //local array of task to copy an original array of tasks
        // for loop that will copy the original array of tasks to the taskListCopy to increment the size
        // of the original array of tasks
        for(int i = 0; i < taskListCopy.length; i++) {
            if(i == taskListCopy.length -1) {
                taskListCopy[index] = task;
            }else {
                taskListCopy[i] = taskList[i];
            }
        }
        taskList = taskListCopy;

    }
    /*
     * Removes a specific task from the array list of tasks
     * @param task
     * */
    @Override
    public boolean remove(Task task) {
        // Conditional to check that the given task is not null
        if (task == null) {
            return false;
        }
        int taskCount = 0; //local variable that indicates how many times the task is on the list
        // Loop that will increment the times counter
        for(int i = 0; i < size; i++){
            if (taskList[i] == task) {
                taskCount++;
            }
        }
        //Conditional that check that the task is on the list
        if(taskCount == 0){
            System.out.println("Task not found");
            return false;
        }
        Task [] taskListCopy = new Task[size - taskCount]; //local array of task to copy an original array of tasks
        // for loop that will copy the original array of tasks to the taskListCopy to decrement the size
        // of the original array of tasks
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
    /*
     * Gets the size of the taskList
     *
     * */
    @Override
    public int size() {
        size = taskList.length;
        return size;
    }
    /*
     * Gets the task on the given index
     * @param index
     * */
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
