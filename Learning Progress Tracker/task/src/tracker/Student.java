package tracker;

public class Student {
    String email;
    String first_name;
    String last_name;
    String userId;
    int javaPoints;
    int dsaPoints;
    int databasesPoints;
    int springPoints;

    public Student(String email, String first_name, String last_name) {
                    this.email = email;
                    this.first_name = first_name;
                    this.last_name = last_name;
                    this.userId = null;
                    this.javaPoints = 0;
                    this.dsaPoints = 0;
                    this.databasesPoints = 0;
                    this.springPoints = 0;
    }
}
