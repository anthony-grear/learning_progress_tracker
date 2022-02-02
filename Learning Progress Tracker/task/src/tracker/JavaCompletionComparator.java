package tracker;

import java.util.Comparator;

public class JavaCompletionComparator implements Comparator<Student> {

    @Override
    public int compare(Student student, Student another) {
        if (another.javaCompletion.compareTo(student.javaCompletion) < 0) {
            return -1;
        } else if (student.javaCompletion.compareTo(another.javaCompletion) > 0) {
            return 1;
        } else {
            return student.userId.compareTo(another.userId);
        }
    }
}
