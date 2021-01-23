package ru.itis.javalab.app;

import com.beust.jcommander.IStringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UrlsConverter implements IStringConverter<List<String>> {
    @Override
    public List<String> convert(String s) {
        return new ArrayList<>(Arrays.asList(s.split(";")));
    }
}
