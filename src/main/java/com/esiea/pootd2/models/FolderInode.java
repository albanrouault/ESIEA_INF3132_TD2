package com.esiea.pootd2.models;

import java.util.LinkedList;

/**
 * FolderInode class representing a folder in the filesystem.
 */
public class FolderInode extends Inode {
    private final LinkedList<Inode> childrens;

    /**
     * Constructs a FolderInode with the given name.
     *
     * @param name The name of the folder.
     */
    public FolderInode(String name) {
        super(name);

        this.childrens = new LinkedList<>();
    }

    /**
     * Adds an inode to the folder.
     *
     * @param child The inode to add.
     * @return True if the inode was added, false if it already exists.
     */
    public boolean addInode(Inode child) {
        // Check if the child (name + type) already exists
        for (Inode existingChild : childrens) {
            if (existingChild.getClass().equals(child.getClass()) && existingChild.getName().equals(child.getName())) {
                return false;
            }
        }
    
        childrens.add(child);
        child.parent = this;
        return true;
    }

    /**
     * Return the childrens of the folder.
     * @return The childrens of the folder.
     */
    public LinkedList<Inode> getChildrens() {
        return childrens;
    }

    /**
     * Gets the size of the folder.
     *
     * @return The size of the folder.
     */
    @Override
    public int getSize() {
        int size = 0;
        for (Inode child : childrens) {
            size += child.getSize();
        }
        return size;
    }

    /**
     * Gets the folder inode with the given name.
     *
     * @param name The name of the folder to get.
     * @return The folder inode with the given name.
     */
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
