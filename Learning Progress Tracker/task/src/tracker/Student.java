package tracker;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
    BigDecimal javaCompletion;
    BigDecimal dsaCompletion;
    BigDecimal dbCompletion;
    BigDecimal springCompletion;



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
                    this.javaCompletion = BigDecimal.valueOf(0);
                    this.dsaCompletion = BigDecimal.valueOf(0);
                    this.dbCompletion = BigDecimal.valueOf(0);
                    this.springCompletion = BigDecimal.valueOf(0);

    }

    void putJavaCompletionPercent() {
        MathContext rounding = new MathContext(3,RoundingMode.HALF_UP);
        BigDecimal bd1 = new BigDecimal((double) this.javaPoints / 600 * 100, rounding);
        this.javaCompletion = bd1;
    }

    void putDsaCompletionPercent() {
        MathContext rounding = new MathContext(3,RoundingMode.HALF_UP);
        BigDecimal bd2 = new BigDecimal((double) this.dsaPoints / 400 * 100, rounding);
        this.dsaCompletion = bd2;
    }

    void putDbCompletionPercent() {
        MathContext rounding = new MathContext(3,RoundingMode.HALF_UP);
        BigDecimal bd3 = new BigDecimal((double) this.databasesPoints / 480 * 100, rounding);
        this.dbCompletion = bd3;
    }

    void putSpringCompletionPercent() {
        MathContext rounding = new MathContext(3,RoundingMode.HALF_UP);
        BigDecimal bd4 = new BigDecimal((double) this.springPoints / 550 * 100, rounding);
        this.springCompletion = bd4;
    }
}
