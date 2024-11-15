package com.esiea.pootd2;

public class FileInode extends Inode {
    private int size;

    public FileInode(String name) {
        super(name);
        // Nombre random entre 1 et 100000
        this.size = (int) (Math.random() * 100000) + 1;
    }

    @Override
    public int getSize() {
        return size;
    }
}
