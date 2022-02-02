package tracker;

import java.util.Comparator;

public class JavaCompletionComparator implements Comparator<Student> {

    @Override
    public int compare(Student student, Student another) {
        if (another.javaPoints < (student.javaPoints)) {
            return -1;
        } else if (another.javaPoints > student.javaPoints ) {
            return 1;
        } else {
            return student.userId.compareTo(another.userId);
        }
    }
}
