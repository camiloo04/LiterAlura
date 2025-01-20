package com.literalura.model;

import lombok.Data;
import java.util.List;

@Data
public class GutendexResponse {
    private int count;
    private List<GutendexBook> results;

    @Data
    public static class GutendexBook {
        private String title;
        private List<GutendexAuthor> authors;
        private List<String> languages;
        private int download_count;
    }

    @Data
    public static class GutendexAuthor {
        private String name;
        private Integer birth_year;
        private Integer death_year;
    }
}