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
    int totalJavaTasks;
    int totalDsaTasks;
    int totalDbTasks;
    int totalSpringTasks;



    public Student(String email, String first_name, String last_name) {
                    this.email = email;
                    this.first_name = first_name;
                    this.last_name = last_name;
                    this.userId = null;
                    this.javaPoints = 0;
                    this.dsaPoints = 0;
                    this.databasesPoints = 0;
                    this.springPoints = 0;
                    this.totalJavaTasks = 0;
                    this.totalDsaTasks = 0;
                    this.totalDbTasks = 0;
                    this.totalSpringTasks = 0;
    }

    double getJavaCompletionPercent() {
        return (double) this.javaPoints / 600;
    }

    double getDsaCompletionPercent() {
        return (double) this.dsaPoints / 400;
    }

    double getDbCompletionPercent() {
        return (double) this.databasesPoints / 480;
    }

    double getSpringCompletionPercent() {
        return (double) this.springPoints / 550;
    }
}
