package ru.itis.javaLab;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HtmlInput {

    private String type;
    private String name;
    private String placeholder;

}
