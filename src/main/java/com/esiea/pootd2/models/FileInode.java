package com.esiea.pootd2.models;

/**
 * FileInode class representing a file in the filesystem.
 */
public class FileInode extends Inode {
    private int size;

    /**
     * Constructs a FileInode with the given name.
     *
     * @param name The name of the file.
     */
    public FileInode(String name) {
        super(name);

        // Random size between 1 and 100000
        this.size = (int) (Math.random() * 100000) + 1;
    }

    /**
     * Gets the size of the file.
     *
     * @return The size of the file.
     */
    @Override
    public int getSize() {
        return size;
    }
}
