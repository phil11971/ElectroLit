package entities;

import java.util.Date;

public class AuthorEntity {
    private int id_a;
    private String lname;
    private String fname;
    private String patr;
    private String mail;
    private Date dob;

    public AuthorEntity() {
    }

    public AuthorEntity(int id_a) {
        this.id_a = id_a;
    }

    public AuthorEntity(int id_a, String lname, String fname, String patr, String mail, Date dob) {
        this.id_a = id_a;
        this.lname = lname;
        this.fname = fname;
        this.patr = patr;
        this.mail = mail;
        this.dob = dob;
    }

    public int getId_a() {
        return id_a;
    }

    public void setId_a(int id_a) {
        this.id_a = id_a;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPatr() {
        return patr;
    }

    public void setPatr(String patr) {
        this.patr = patr;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
