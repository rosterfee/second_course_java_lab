package ru.itis.javaLab.args;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = "--ip")
    public String ip;

    @Parameter(names = "--port")
    public int port;
}
