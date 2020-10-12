package edu.gatech.seclass.jobcompare6300;

public class Job {
    private static String job;
    private static String company;
    private static String location;
    private static String col;
    private static String commute;
    private static String salary;
    private static String bonus;
    private static String retirement;
    private static String leave;

    public Job() {
        this.job = job;
        this.company = company;
        this.location = location;
        this.col = col;
        this.commute = commute;
        this.salary = salary;
        this.bonus = bonus;
        this.retirement = retirement;
        this.leave = leave;
    }

    public static void setJob(String job) {
        Job.job = job;
    }

    public static void setCompany(String company) {
        Job.company = company;
    }

    public static void setLocation(String location) {
        Job.location = location;
    }

    public static void setCol(String col) {
        Job.col = col;
    }

    public static void setCommute(String commute) {
        Job.commute = commute;
    }

    public static void setSalary(String salary) {
        Job.salary = salary;
    }


    public static void setBonus(String bonus) {
        Job.bonus = bonus;
    }

    public static void setRetirement(String retirement) {
        Job.retirement = retirement;
    }

    public static void setLeave(String leave) {
        Job.leave = leave;
    }
}