package com.esiea.pootd2.models;

public abstract class Inode {
    private String name;
    protected FolderInode parent;

    public Inode(String name) {
        this.name = name;
        this.parent = null;
    }

    public String getName() {
        return name;
    }

    public FolderInode getParent() {
        return parent;
    }

    public abstract int getSize();
}
