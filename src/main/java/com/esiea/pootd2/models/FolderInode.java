package com.esiea.pootd2.models;

import java.util.LinkedList;

public class FolderInode extends Inode {
    private final LinkedList<Inode> childrens;

    public FolderInode(String name) {
        super(name);

        this.childrens = new LinkedList<>();
    }

    public void addInode(Inode child) {
        childrens.add(child);
        child.parent = this;
    }

    /**
     * Return the childrens of the folder.
     * @return The childrens of the folder.
     */
    public LinkedList<Inode> getChildrens() {
        return childrens;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (Inode child : childrens) {
            size += child.getSize();
        }
        return size;
    }

    public FolderInode getFolderInode(String name) {
        if (name.equals(".") || (name.equals("..") && this.getName().equals("/"))) {
            return this;
        }

        if (name.equals("..")) {
            return parent;
        }

        for (Inode child : childrens) {
            if (child instanceof FolderInode folderInode && folderInode.getName().equals(name)) {
                return folderInode;
            }
        }

        return null;
    }
}
