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
    //private int time;               // Time of execution of the task
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
        this.setActive(false);
        //this.repeated = false;
        this.setTitle(title);
        this.setTime(time);
    }

    /*
    * Creates a new Task with the given title, start end and interval
    * @param title
    * @param start
    * @param end
    * @param interval
    * */
    public TaskImpl(String title, int start, int end, int interval) {
        this.active = false;
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
        return this.start;
    }

    /*
    * Sets the time of execution for non-repetitive tasks
    * @param time
    * */
    @Override
    public void setTime(int time) {
        this.start = time;
        this.end = time;
        this.interval = 0;
    }

    /*
    * Gets the start time of a repetitive task
    * @return startTime
    * */
    @Override
    public int getStartTime() {
        return this.start;
    }

    /*
     * Gets the start time of a repetitive task
     * @return endTime
     * */
    @Override
    public int getEndTime() {
        return this.end;
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
        if((start > end) || (interval > (end - start))) { //
            System.out.println("The start time and interval cannot be greater than the end time to be a repetitive task");
            return;
        }
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
        if(this.interval <= (this.end - this.start) && this.start < this.end) {
            if ((this.interval == 0 && this.repeated)) {
                System.out.println("This will run forever");
                return true; //endless loop
            } else if (this.interval > 0) {
                return true;
            } else {
                System.out.println("Interval must be greater than zero for " +
                                   "repetitive tasks");
                return false;
            }
        }else {
            return false;
        }
    }

    /*
    * Gets the next time of start time of a task given a time
    * @param current
    * */
    @Override
    public int nextTimeAfter(int current) {
        int startTime = getTime();         //Local variable for the start time of the task
        int endTime = getEndTime();        //Local variable for the end time of the task
        int next;
        if (!isActive()){
            return -1;
        }
        if (current < startTime) {
            return startTime;
        }
        if((current + this.interval) <= endTime) {
            if (this.interval != 0) {
                int elapsed = current - startTime;
                next = startTime + elapsed + this.interval;
                return next;


            }
        }
        return -1;
    }
}


