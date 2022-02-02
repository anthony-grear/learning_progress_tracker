package tracker;

import java.util.Comparator;

public class SpringCompletionComparator implements Comparator<Student> {

    @Override
    public int compare(Student student, Student another) {
        if (another.springCompletion.compareTo(student.springCompletion) < 0) {
            return -1;
        } else if (another.springCompletion.compareTo(student.springCompletion) > 0) {
            return 1;
        } else {
            return student.userId.compareTo(another.userId);
        }
    }
}
