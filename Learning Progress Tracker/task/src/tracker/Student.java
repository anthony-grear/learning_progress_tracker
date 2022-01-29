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

    public Student(String email, String first_name, String last_name,
                   String userId, int javaPoints, int dsaPoints,
                   int databasesPoints, int springPoints) {
                    this.email = email;
                    this.first_name = first_name;
                    this.last_name = last_name;
                    this.userId = userId;
                    this.javaPoints = javaPoints;
                    this.dsaPoints = dsaPoints;
                    this.databasesPoints = databasesPoints;
                    this.springPoints = springPoints;
    }
}
