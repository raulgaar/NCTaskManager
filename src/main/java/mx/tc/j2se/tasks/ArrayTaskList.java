package mx.tc.j2se.tasks;

import java.util.Iterator;
import java.util.stream.Stream;

public interface ArrayTaskList {
    void add(Task task);

    boolean remove(Task task);

    int size();

    Task getTask (int index);

    //ArrayTaskList incoming(int from, int to);

    Iterator<Task> iterator();

    boolean equals(Object o);

    int hashCode();

    String toString();

    Stream<Task> getStream();

}
