package com.astery.elegionapp.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

/** pojo class for a user */
@Entity
public class User {

    @NonNull @PrimaryKey private String id;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
    /*
    @NonNull private String name;
    @ColumnInfo(name = "lastname") private String lastName;
    @NonNull private String surname;
    private String email;
    @ColumnInfo(name = "vacation_name") private String vacationName;
    @ColumnInfo(name = "vacation_role") private Object vacationRole;
    private Object manager;
    @ColumnInfo(name = "pass_manager") private Object passManager;
    private String phone;
    @ColumnInfo(name  = "extra_phone") private String extraPhone;
    private String spentaimes;
    private Object role;
    private Boolean remote;
    private String skype;
    private String telegram;
    private Date birthday;
    private Object office;
    private Object division;
    private Object Entity;
    private Object status;
    private Date employment;
    private Date workStart;
    private Object lastChanged;
    private Calendar lastChangedTime;
    private int gender;
    private Boolean blockLogin;


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVacationName() {
        return vacationName;
    }

    public void setVacationName(String vacationName) {
        this.vacationName = vacationName;
    }

    public Object getVacationRole() {
        return vacationRole;
    }

    public void setVacationRole(Object vacationRole) {
        this.vacationRole = vacationRole;
    }

    public Object getManager() {
        return manager;
    }

    public void setManager(Object manager) {
        this.manager = manager;
    }

    public Object getPassManager() {
        return passManager;
    }

    public void setPassManager(Object passManager) {
        this.passManager = passManager;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExtraPhone() {
        return extraPhone;
    }

    public void setExtraPhone(String extraPhone) {
        this.extraPhone = extraPhone;
    }

    public String getSpentaimes() {
        return spentaimes;
    }

    public void setSpentaimes(String spentaimes) {
        this.spentaimes = spentaimes;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public Boolean getRemote() {
        return remote;
    }

    public void setRemote(Boolean remote) {
        this.remote = remote;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Object getOffice() {
        return office;
    }

    public void setOffice(Object office) {
        this.office = office;
    }

    public Object getDivision() {
        return division;
    }

    public void setDivision(Object division) {
        this.division = division;
    }

    public Object getEntity() {
        return Entity;
    }

    public void setEntity(Object entity) {
        Entity = entity;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Date getEmployment() {
        return employment;
    }

    public void setEmployment(Date employment) {
        this.employment = employment;
    }

    public Date getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Date workStart) {
        this.workStart = workStart;
    }

    public Object getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Object lastChanged) {
        this.lastChanged = lastChanged;
    }

    public Calendar getLastChangedTime() {
        return lastChangedTime;
    }

    public void setLastChangedTime(Calendar lastChangedTime) {
        this.lastChangedTime = lastChangedTime;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Boolean getBlockLogin() {
        return blockLogin;
    }

    public void setBlockLogin(Boolean blockLogin) {
        this.blockLogin = blockLogin;
    }

     */
}
