package com.astery.elegionapp.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

/** pojo class for a user */
@Entity
public class User {


    @SerializedName("id")
    @Expose
    @NonNull @PrimaryKey private int id;

    @SerializedName("password")
    @Expose
    @NonNull private String password;

    @SerializedName("role")
    @Expose
    @NonNull private String access; // admin, employe

    @SerializedName("first_name")
    @Expose
    @NonNull private String name;

    @SerializedName("last_name")
    @Expose
    @ColumnInfo(name = "lastname") private String lastName;

    @SerializedName("father_name")
    @Expose
    @NonNull private String surname;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("vacationName")
    @Expose
    @ColumnInfo(name = "vacation_name") private String vacationName;

    @SerializedName("vacationRole")
    @Expose
    @ColumnInfo(name = "vacation_role") private String vacationRole;

    @SerializedName("manager")
    @Expose
    private String manager;

    @SerializedName("passManager")
    @Expose
    @ColumnInfo(name = "pass_manager") private String passManager;

    @SerializedName("phone")
    @Expose
    private int phone;

    @SerializedName("extraPhone")
    @Expose
    @ColumnInfo(name  = "extra_phone") private String extraPhone;

    @SerializedName("spentaimes")
    @Expose
    private String spentaimes;

    @SerializedName("position")
    @Expose
    private String role;

    @SerializedName("remote")
    @Expose
    private Boolean remote;

    @SerializedName("skype")
    @Expose
    private String skype;

    @SerializedName("telegram")
    @Expose
    private String telegram;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("office")
    @Expose
    private String office;

    @SerializedName("division")
    @Expose
    private String division;

    @SerializedName("entity")
    @Expose
    private String Entity;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("employment")
    @Expose
    private String employment;

    @SerializedName("workStart")
    @Expose
    private String workStart;

    @SerializedName("admChanged")
    @Expose
    private String lastChanged;

    @SerializedName("lastChanged")
    @Expose
    private String lastChangedTime;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("is_active")
    @Expose
    private Boolean blockLogin;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getAccess() {
        return access;
    }

    public void setAccess(@NonNull String access) {
        this.access = access;
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

    public String getVacationRole() {
        return vacationRole;
    }

    public void setVacationRole(String vacationRole) {
        this.vacationRole = vacationRole;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPassManager() {
        return passManager;
    }

    public void setPassManager(String passManager) {
        this.passManager = passManager;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getEntity() {
        return Entity;
    }

    public void setEntity(String entity) {
        Entity = entity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getWorkStart() {
        return workStart;
    }

    public void setWorkStart(String workStart) {
        this.workStart = workStart;
    }

    public String getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(String lastChanged) {
        this.lastChanged = lastChanged;
    }

    public String getLastChangedTime() {
        return lastChangedTime;
    }

    public void setLastChangedTime(String lastChangedTime) {
        this.lastChangedTime = lastChangedTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getBlockLogin() {
        return blockLogin;
    }

    public void setBlockLogin(Boolean blockLogin) {
        this.blockLogin = blockLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", access='" + access + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", vacationName='" + vacationName + '\'' +
                ", vacationRole='" + vacationRole + '\'' +
                ", manager='" + manager + '\'' +
                ", passManager='" + passManager + '\'' +
                ", phone=" + phone +
                ", extraPhone='" + extraPhone + '\'' +
                ", spentaimes='" + spentaimes + '\'' +
                ", role='" + role + '\'' +
                ", remote=" + remote +
                ", skype='" + skype + '\'' +
                ", telegram='" + telegram + '\'' +
                ", birthday='" + birthday + '\'' +
                ", office='" + office + '\'' +
                ", division='" + division + '\'' +
                ", Entity='" + Entity + '\'' +
                ", status='" + status + '\'' +
                ", employment='" + employment + '\'' +
                ", workStart='" + workStart + '\'' +
                ", lastChanged='" + lastChanged + '\'' +
                ", lastChangedTime='" + lastChangedTime + '\'' +
                ", gender='" + gender + '\'' +
                ", blockLogin=" + blockLogin +
                '}';
    }
}
