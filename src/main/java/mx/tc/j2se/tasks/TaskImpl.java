package mx.tc.j2se.tasks;

public class TaskImpl implements Task{

    private String title;
    private int time, start, end, interval;
    private boolean active, repeated;



    public TaskImpl() {

    }
    public TaskImpl(String title, int time) {
        active = false;
        repeated = false;
        this.title = title;
        this.time = time;
    }

    public TaskImpl(String title, int start, int end, int interval) {
        active = false;
        repeated = true;
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int getTime() {
        int t;
        boolean r = isRepeated();
        if (r) {
            t = this.start;
        }else {
            t = this.time;
        }
        return t;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
        repeated = false;
    }

    @Override
    public int getStartTime() {
        int t;

        if (this.interval != 1) {
            t = this.start;
        }else {
            t = this.time;
        }
        return t;
    }

    @Override
    public int getEndTime() {
        return this.end;
    }

    @Override
    public int getRepeatInterval() {
        int i;
        boolean r = isRepeated();
        if (r) {
            i = this.interval;
            setTime(this.start,this.end, i);
        }else {
            i = 0;
            setTime(this.time);
        }
        return i;
    }

    @Override
    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        boolean r = isRepeated();
        if (!r) {
            this.repeated = true;
        }
    }

    @Override
    public boolean isRepeated() {
        int interval = this.interval;
        if(interval == 1){
            repeated = false;
        }else if(interval > 1) {
            repeated = true;
        }else{
            System.out.println("Interval must be greater than zero for repetitive tasks or 0 for non-repetitive tasks");
        }
        return repeated;
    }

    @Override
    public int nextTimeAfter(int current) {
        int time = getTime();
        boolean active = isActive();
        if(active) {
            if (current <= time) {
                return time;
            }
        }
        return -1;
    }
}


