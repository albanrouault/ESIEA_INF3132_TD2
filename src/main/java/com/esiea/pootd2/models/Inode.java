package com.esiea.pootd2.models;

/**
 * Inode class representing a node in the filesystem.
 */
public abstract class Inode {
    private String name;
    protected FolderInode parent;

    /**
     * Constructs an Inode with the given name.
     *
     * @param name The name of the inode.
     */
    public Inode(String name) {
        this.name = name;
        this.parent = null;
    }

    /**
     * Gets the name of the inode.
     *
     * @return The name of the inode.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the parent folder of the inode.
     *
     * @return The parent folder of the inode.
     */
    public FolderInode getParent() {
        return parent;
    }

    /**
     * Gets the size of the inode.
     *
     * @return The size of the inode.
     */
    public abstract int getSize();
}
