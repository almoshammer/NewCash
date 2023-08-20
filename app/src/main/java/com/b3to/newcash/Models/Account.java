package com.b3to.newcash.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = AccountType.class
        , parentColumns = "id", childColumns = "type_id", onDelete = ForeignKey.CASCADE))
public class Account extends Base {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long type_id;
    private String name;
    private String phone1;
    private String phone2;
    private String phone3;
    private String desc;

    public Account() {

    }

    public Account(long type_id, String name, String phone1, String phone2, String phone3, String desc, byte[] img) {
        this.type_id = type_id;
        this.name = name;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.desc = desc;
        this.img = img;
    }

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] img;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
