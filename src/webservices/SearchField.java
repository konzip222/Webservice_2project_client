/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

/**
 *
 * @author Jakub
 */
public class SearchField {
    // Search
    private int day = 0;
    private int month = 0;
    private int week = 0;
    private int year = 0;
    private int yearByWeek = 0;       

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYearByWeek() {
        return yearByWeek;
    }

    public void setYearByWeek(int yearByWeek) {
        this.yearByWeek = yearByWeek;
    }

    
}
