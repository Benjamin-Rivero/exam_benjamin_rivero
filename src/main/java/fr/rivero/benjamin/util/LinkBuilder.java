package fr.rivero.benjamin.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class LinkBuilder {

    public static String build(int page, int size, Sort sort,HttpServletRequest request){
        StringBuilder builder = new StringBuilder();
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String route = request.getRequestURI();
        builder.append(baseUrl)
                .append(route)
                .append("?page=")
                .append(page)
                .append("&size=")
                .append(size);
        if(sort != null && !sort.isEmpty()) {
            sort.forEach(s -> {
                builder.append("&sort=");
                builder.append(s.getProperty()).append(",").append(s.getDirection());
            });
        }
        return builder.toString();
    }

}
