package ru.itis.javaLab;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HtmlForm {

    private String method;
    private String action;
    private List<HtmlInput> inputs;

}
