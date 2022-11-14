package models.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.PageItem;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
public class Company implements PageItem {
    private String title;
    private Boolean isAccredited;
    private Boolean isHabrMember;
    private Double rating;
    private String location;
    private String size;
    private Integer vacanciesCount;
    private String companyUrl;
    private String parsingUrl;
    private LocalDateTime dateTime;

    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put("title", title);
        attributes.put("accredited", isAccredited.toString());
        attributes.put("habr member", isHabrMember.toString());
        attributes.put("rating", rating.toString());
        attributes.put("location", location);
        attributes.put("size", size);
        attributes.put("vacancies count", vacanciesCount.toString());
        attributes.put("company url", companyUrl);
        attributes.put("parsing url", parsingUrl);
        attributes.put("timestamp", Timestamp.valueOf(dateTime).toString());

        return attributes;
    }
}
