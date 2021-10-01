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
}
