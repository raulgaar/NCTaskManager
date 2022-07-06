package mx.tc.j2se.tasks;

public class LinkedTaskListImpl extends AbstractTaskList{

    private Node first;
    private Node last;
    private int size;

    public LinkedTaskListImpl() {
        first = null; // variable that sets the first node to null when the list is empty
        size = 0; // variable that sets the size to 0 when the list is empty
        last = null; // variable that sets the last node to null when the list is empty
    }
    /*
     * Adds a task at the end of the linked list
     * @param task
     * */

    @Override
    public void add(Task task) {
        Node newNode = new Node();
        newNode.setTask(task);
        if (first == null){
            first = newNode;
            last = newNode;
        }else{
            Node node = last;
            node.setNext(newNode);
            last = node.getNext();
        }
        size++;
    }
    /*
     * Removes a specific task from the linked list of tasks
     * @param task
     * */
    @Override
    public boolean remove(Task task) throws IllegalArgumentException{
        //conditional that checks that the task is not null
        if(task == null){
            throw new IllegalArgumentException("Task cannot be empty");
        }
        int count = 0; // local variable that indicates how many times the task is on the list

        Node prev = null; //Node that gives the previous task
        Node next; //Node that gives the next task
        // For loop to assign the next task to the node of the task that will be deleted and decrement the size of the
        //linked list
        for (Node node = first; node != null; node = next) {
            next = node.getNext();
            if (node.getTask() == task) {
                count++;
                size--;
                if (prev != null) {
                    prev.setNext(next);
                } else {
                    first = next;
                }
            } else {
                prev = node;
            }
        }

        System.out.print(count > 0 ? ("Task deleted: " + count + "\n") : "Not found\n");
        return count != 0;
    }
    /*
     * Gets the size of the LinkedTaskList
     *
     * */
    @Override
    public int size() {
        return size;
    }

    /*
     * Gets the task on the given index
     * @param index
     * */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (index >= 0 && index < size){
            if (index == 0) {
                return first.getTask();
            }else {
                Node node = first;
                for(int i = 0; i<index; i++){
                    node = node.getNext();
                }
                return node.getTask();
            }
        }else {
            throw new IndexOutOfBoundsException ("Index must be positive cannot be equal or greater than the size" +
                    " of the List");
        }
    }
/*
    @Override
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException{
        if (from < 0 || to < 0){
            throw new IllegalArgumentException ("Time cannot be negative");
        }
        Node node = first;
        LinkedTaskList incomingTasks = new LinkedTaskListImpl();
        while(node != null) {
            int nextTaskEvent = node.getTask().nextTimeAfter(from);
            if ((nextTaskEvent <= from) || (nextTaskEvent >= to)){
                node = node.getNext();
                continue;
            }
            incomingTasks.add(node.getTask());
            node = node.getNext();
        }
        return incomingTasks;
    }

 */
    protected static class Node{
        protected Node next;
        protected Task task;

        public void Node(){
            this.next = null;
            this.task = null;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node node) {
            this.next = node;
        }

        public Task getTask() {
            return this.task;
        }

        public void setTask(Task task) {
            this.task = task;
        }
    }
}

