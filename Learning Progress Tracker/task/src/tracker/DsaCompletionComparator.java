package tracker;

import java.util.Comparator;

public class DsaCompletionComparator implements Comparator<Student> {
    @Override
    public int compare(Student student, Student another) {
        if (another.dsaCompletion.compareTo(student.dsaCompletion) < 0) {
            return -1;
        } else if (another.dsaCompletion.compareTo(student.dsaCompletion) > 0) {
            return 1;
        } else {
            return student.userId.compareTo(another.userId);
        }
    }
}
