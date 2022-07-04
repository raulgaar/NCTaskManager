package mx.tc.j2se.tasks;

public class TaskListFactory {
    private TaskListFactory(){

    }
    public static AbstractTaskList createTaskList(ListTypes.Types type) throws IllegalArgumentException{
        switch (type) {
            case ARRAY:
                return new ArrayTaskListImpl();
            case LINKED:
                return new LinkedTaskListImpl();
            default:
                throw new IllegalArgumentException("Not a valid type of task list");
        }
    }
}
