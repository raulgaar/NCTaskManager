/*
 * TaskImpl
 *
 * 1
 *
 * Raul Arturo Garcia Aranda
 *
 */
package mx.tc.j2se.tasks;
/*
*
* This is an implementation of the Task interface
*
*
* */
public class TaskImpl implements Task{

    private String title;           // Title of the task
    private int time;               // Time of execution of the task
    private int start;              // Start of execution of the task
    private int end;                // End of execution of the task
    private int interval;           // Interval of execution of the task
    private boolean active;         //Status of the task
    private boolean repeated;       //Status of repeatability of the task


    //Creates a new Task
    public TaskImpl() {

    }

    /*
    * Creates a new Task with the given title and time
    * @param title
    * @param time
    *  */
    public TaskImpl(String title, int time) {
        active = false;
        repeated = false;
        this.title = title;
        this.time = time;
    }

    /*
    * Creates a new Task with the given title, start end and interval
    * @param title
    * @param start
    * @param end
    * @param interval
    * */
    public TaskImpl(String title, int start, int end, int interval) {
        active = false;
        repeated = true;
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /*
    * Gets the title of the task
    * @return title
    * */
    @Override
    public String getTitle() {
        return title;
    }

    /*
    * Sets the title of the task
    * @param title
    * */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /*
    * Gets the status of the task
    * @return active
    * */
    @Override
    public boolean isActive() {
        return active;
    }

    /*
    * Sets the status of the task
    * @param active
    * */
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    /*
    * Gets the time of execution of the task
    * @return time
    * */
    @Override
    public int getTime() {
        int time;                          // Time of the task
        boolean r = isRepeated();
        if (r) {
            time = this.start;
        }else {
            time = this.time;
        }
        return time;
    }

    /*
    * Sets the time of execution for non-repetitive tasks
    * @param time
    * */
    @Override
    public void setTime(int time) {
        this.time = time;
        repeated = false;
    }

    /*
    * Gets the start time of a repetitive task
    * @return startTime
    * */
    @Override
    public int getStartTime() {
        int startTime;                     // Start time of the task

        if (this.interval != 0) {
            startTime = this.start;
        }else {
            startTime = this.time;
        }
        return startTime;
    }

    /*
     * Gets the start time of a repetitive task
     * @return endTime
     * */
    @Override
    public int getEndTime() {
        int endTime;                       //End time of the task

        if (this.interval != 0) {
            endTime = this.end;
        }else {
            endTime = this.time;
        }
        return endTime;
    }

    /*
    * Gets the interval of repetitive tasks
    * @return interval
    * */
    @Override
    public int getRepeatInterval() {
        return this.interval;
    }

    /*
    * Set the start time, end time and interval of the repetitive tasks
    * @param start
    * @param end
    * @param interval
    * */
    @Override
    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        if (!isRepeated()) {
            this.repeated = true;
        }
    }

    /*
    * Gets the repeatability of the tasks
    * @return repeated
    * */
    @Override
    public boolean isRepeated() {
        int interval = this.interval;       //Local variable for the interval value
        if(interval == 0){
            repeated = false;
        }else if(interval > 0) {
            repeated = true;
        }else{
            System.out.println("Interval must be greater than zero for" +
                    " repetitive tasks or 0 for non-repetitive tasks");
        }
        return repeated;
    }

    /*
    * Gets the next time of start time of a task given a time
    * @param current
    * */
    @Override
    public int nextTimeAfter(int current) {
        int startTime = getTime();         //Local variable for the start time of the task
        int endTime = getEndTime();        //Local variable for the end time of the task
        if(!isActive()) {
        return -1;
        }
        if (current < startTime) {
            return startTime;
        }else if(current >= startTime && current < endTime) {
            int elapsed = current - startTime;
            int intervalIndex = (elapsed/this.interval) + 1;
            int next = (this.interval * intervalIndex);
            return next;
        }else if(current >= startTime && current == endTime) {
            return current;
        }
        return -1;
    }
}


