package tracker;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
    String javaCompletion;
    String dsaCompletion;
    String dbCompletion;
    String springCompletion;
    boolean alreadyNotifiedJavaComplete;
    boolean alreadyNotifiedDsaComplete;
    boolean alreadyNotifiedDbComplete;
    boolean alreadyNotifiedSpringComplete;


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
                    this.javaCompletion = String.valueOf(0);
                    this.dsaCompletion = String.valueOf(0);
                    this.dbCompletion = String.valueOf(0);
                    this.springCompletion = String.valueOf(0);
                    this.alreadyNotifiedJavaComplete = false;
                    this.alreadyNotifiedDsaComplete = false;
                    this.alreadyNotifiedDbComplete = false;
                    this.alreadyNotifiedSpringComplete = false;
    }

    void putJavaCompletionPercent() {
        double bd1 = (double) this.javaPoints / 600 * 100;
        DecimalFormat df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        this.javaCompletion = df.format(bd1);
    }

    void putDsaCompletionPercent() {
        double bd2 = (double) this.dsaPoints / 400 * 100;
        DecimalFormat df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        this.dsaCompletion = df.format(bd2);
    }

    void putDbCompletionPercent() {
        double bd3 = (double) this.databasesPoints / 480 * 100;
        DecimalFormat df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        this.dbCompletion = df.format(bd3);
    }

    void putSpringCompletionPercent() {
        double bd4 = (double) this.springPoints / 550 * 100;
        DecimalFormat df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        this.springCompletion = df.format(bd4);
    }
}
