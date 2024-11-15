package com.esiea.pootd2.models;

import java.util.ArrayList;

public class FolderInode extends Inode {
    private final ArrayList<Inode> children;

    public FolderInode(String name) {
        super(name);

        this.children = new ArrayList<>();
    }

    public void addInode(Inode child) {
        children.add(child);
        child.parent = this;
    }

    @Override
    public int getSize() {
        int size = 0;
        if (children != null) {
            for (Inode child : children) {
                size += child.getSize();
            }
        }
        return size;
    }
}
