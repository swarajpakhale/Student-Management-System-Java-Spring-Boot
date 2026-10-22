package com.sms.dto;

import com.sms.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for consolidated Dashboard statistics.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
public class DashboardStatsDto {

    private long totalStudents;
    private long computerTechCount;
    private long infoTechCount;
    private long electronicsCount;
    private long otherDeptCount;
    private double averageCgpa;
    private List<Student> recentStudents = new ArrayList<>();

    public DashboardStatsDto() {
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getComputerTechCount() {
        return computerTechCount;
    }

    public void setComputerTechCount(long computerTechCount) {
        this.computerTechCount = computerTechCount;
    }

    public long getInfoTechCount() {
        return infoTechCount;
    }

    public void setInfoTechCount(long infoTechCount) {
        this.infoTechCount = infoTechCount;
    }

    public long getElectronicsCount() {
        return electronicsCount;
    }

    public void setElectronicsCount(long electronicsCount) {
        this.electronicsCount = electronicsCount;
    }

    public long getOtherDeptCount() {
        return otherDeptCount;
    }

    public void setOtherDeptCount(long otherDeptCount) {
        this.otherDeptCount = otherDeptCount;
    }

    public double getAverageCgpa() {
        return averageCgpa;
    }

    public void setAverageCgpa(double averageCgpa) {
        this.averageCgpa = averageCgpa;
    }

    public List<Student> getRecentStudents() {
        return recentStudents;
    }

    public void setRecentStudents(List<Student> recentStudents) {
        this.recentStudents = recentStudents;
    }
}
