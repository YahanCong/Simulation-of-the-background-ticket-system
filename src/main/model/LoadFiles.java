package model;

import exception.EmptyFileException;

import java.io.FileNotFoundException;

public interface LoadFiles {
    void loadFromFile() throws FileNotFoundException, EmptyFileException;

}
