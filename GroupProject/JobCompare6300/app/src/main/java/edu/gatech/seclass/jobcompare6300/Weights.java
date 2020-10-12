package edu.gatech.seclass.jobcompare6300;

import android.util.Log;

public class Weights {
    private static int commute_weight;
    private static int salary_weight;
    private static int bonus_weight;
    private static int retirement_weight;
    private static int leave_weight;

    public Weights() {
        this.commute_weight = 1;
        this.salary_weight = 1;
        this.bonus_weight = 1;
        this.retirement_weight = 1;
        this.leave_weight = 1;
    }

    public static int getCommute_weight() {
        return commute_weight;
    }

    public static void setCommute_weight(int commute_weight) {
        Weights.commute_weight = commute_weight;
    }

    public static int getSalary_weight() {
        return salary_weight;
    }

    public static void setSalary_weight(int salary_weight) {
        Weights.salary_weight = salary_weight;
    }

    public static int getBonus_weight() {
        return bonus_weight;
    }

    public static void setBonus_weight(int bonus_weight) {
        Weights.bonus_weight = bonus_weight;
    }

    public static int getRetirement_weight() {
        return retirement_weight;
    }

    public static void setRetirement_weight(int retirement_weight) {
        Weights.retirement_weight = retirement_weight;
    }

    public static int getLeave_weight() {
        return leave_weight;
    }

    public static void setLeave_weight(int leave_weight) {
        Weights.leave_weight = leave_weight;
    }

    public static String printWeights(){
        String string = "Commute : "+commute_weight+", Salary : "+salary_weight+", Bonus : "+bonus_weight+", Leave : "+leave_weight+", Retirement : "+retirement_weight;
        return string;
    }

    /*
        AYS = yearly salary adjusted for cost of living
        AYB = yearly bonus adjusted for cost of living
        RBP = retirement benefits percentage
        LT = leave time
        CT = commute time
     */
    public static double getJobScore(int AYS ,int AYB, int RBP, int LT, int CT){
        double jobScore = 0;
        int weightTotal = Weights.bonus_weight + Weights.retirement_weight + Weights.commute_weight + Weights.leave_weight + Weights.salary_weight;
        jobScore = ((Weights.salary_weight/weightTotal) * AYS) + ((Weights.bonus_weight/weightTotal) * AYB) + ( (Weights.retirement_weight/weightTotal) * (RBP * AYS))
                + ( (Weights.leave_weight/weightTotal) * (LT * AYS / 260) ) - ( (Weights.commute_weight/weightTotal) * (CT * AYS / 8) );
        return jobScore;
    }
}
