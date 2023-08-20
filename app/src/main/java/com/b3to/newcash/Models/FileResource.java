package com.b3to.newcash.Models;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = @ForeignKey(entity = Transaction.class, parentColumns = "id", childColumns = "transaction_id", onDelete = ForeignKey.CASCADE))
public class FileResource {
    private long id;
    private long transaction_id;
    private int type;
    private String path;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
