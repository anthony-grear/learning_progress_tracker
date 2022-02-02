package tracker;

import java.util.Comparator;

public class DbCompletionComparator implements Comparator<Student> {
    @Override
    public int compare(Student student, Student another) {
        if (another.dbCompletion.compareTo(student.dbCompletion) < 0) {
            return -1;
        } else if (another.dbCompletion.compareTo(student.dbCompletion) > 0) {
            return 1;
        } else {
            return student.userId.compareTo(another.userId);
        }
    }
}
