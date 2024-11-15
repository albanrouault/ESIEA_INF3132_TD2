package com.esiea.pootd2.models;

import java.util.LinkedList;

public class FolderInode extends Inode {
    private final LinkedList<Inode> children;

    public FolderInode(String name) {
        super(name);
        children = new LinkedList<Inode>();
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
