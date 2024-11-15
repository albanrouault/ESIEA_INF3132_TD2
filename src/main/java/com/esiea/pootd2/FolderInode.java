package com.esiea.pootd2;

import java.util.ArrayList;

public class FolderInode extends Inode {
    private ArrayList<Inode> children;

    public FolderInode(String name) {
        super(name);
        children = new ArrayList<Inode>();
    }

    public void addChild(Inode child) {
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
