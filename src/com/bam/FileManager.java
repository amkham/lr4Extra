package com.bam;

import rzp.oop.filesystem.FileSystem;
import rzp.oop.filesystem.FileSystemExtra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FileManager implements FileSystem, FileSystemExtra {

    private final HashMap<String, MyFile> fileSystem = new HashMap<>();


    @Override
    public void createFile(String fileName, String text) {

        MyFile file = new MyFile();
        file.setText(text);
        fileSystem.put(fileName, file);

    }

    @Override
    public String readFile(String fileName) {
        MyFile file = fileSystem.get(fileName);

        if (file.getReadable())
        {
            return fileSystem.getOrDefault(fileName, null).getText();
        }
        else return null;
    }

    @Override
    public void deleteFile(String fileName) {

        MyFile file = fileSystem.get(fileName);
        if (file.getWritable())
        {
            fileSystem.remove(fileName);
        }


    }

    @Override
    public void moveFile(String oldFileName, String newFileName) {


        MyFile file = fileSystem.get(oldFileName);
        if (file.getWritable() && file.getReadable())
        {
            createFile(newFileName, fileSystem.get(oldFileName).getText());
            deleteFile(oldFileName);
        }

    }

    @Override
    public List<String> searchFiles(String fileNamePart) {

        List<String> result = new ArrayList<>();
        Set<String> keys = fileSystem.keySet();

        for (String k: keys)
        {
            if (k.contains(fileNamePart))
            {
                result.add(k);
            }
        }

        return result;

    }

    @Override
    public void changeFile(String fileName, String newText) {

        MyFile file = fileSystem.get(fileName);
        if(file.getWritable())
        {
            file.setText(newText);
            fileSystem.put(fileName, file);
        }

    }

    @Override
    public void appendFile(String fileName, String appendText) {


        MyFile file = fileSystem.get(fileName);
        if (file.getWritable() && file.getReadable())
        {
            String oldText = file.getText();
            file.setText(oldText + appendText);
            fileSystem.put(fileName,file);
        }


    }


    @Override
    public void setWritable(String fileName, boolean writable) {

        MyFile file = fileSystem.get(fileName);
        file.setWritable(writable);
        fileSystem.put(fileName, file);

    }

    @Override
    public void setReadable(String fileName, boolean readable) {
        MyFile file = fileSystem.get(fileName);
        file.setReadable(readable);
        fileSystem.put(fileName, file);
    }
}

