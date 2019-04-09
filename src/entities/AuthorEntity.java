package entities;

import java.util.Objects;

public class AuthorEntity {
    private int id_a;
    private String lname;
    private String fname;
    private String patr;
    private String mail;
    private String dob;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return id_a == that.id_a &&
                lname.equals(that.lname) &&
                fname.equals(that.fname) &&
                Objects.equals(patr, that.patr) &&
                Objects.equals(mail, that.mail) &&
                Objects.equals(dob, that.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_a, lname, fname, patr, mail, dob);
    }

    public AuthorEntity() {
    }

    public AuthorEntity(int id_a) {
        this.id_a = id_a;
    }

    public AuthorEntity(String lname, String fname, String patr, String mail, String dob) {
        this.lname = lname;
        this.fname = fname;
        this.patr = patr;
        this.mail = mail;
        this.dob = dob;
    }

    public AuthorEntity(int id_a, String lname, String fname, String patr, String mail, String dob) {
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
