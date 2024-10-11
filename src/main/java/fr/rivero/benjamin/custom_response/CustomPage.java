package fr.rivero.benjamin.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.rivero.benjamin.json_views.JsonViews;
import fr.rivero.benjamin.util.LinkBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomPage<T> {

    @JsonView(JsonViews.Page.class)
    private CustomResponse<T> response;

    @JsonView(JsonViews.Page.class)
    private String first;

    @JsonView(JsonViews.Page.class)
    private String previous;

    @JsonView(JsonViews.Page.class)
    private String self;

    @JsonView(JsonViews.Page.class)
    private String next;

    @JsonView(JsonViews.Page.class)
    private String last;

    @JsonView(JsonViews.Page.class)
    private int pageSize;

    @JsonView(JsonViews.Page.class)
    private int pageNumber;

    @JsonView(JsonViews.Page.class)
    private long totalElements;

    @JsonView(JsonViews.Page.class)
    private int totalPages;

    @JsonView(JsonViews.Page.class)
    private String sort;

    public CustomPage(HttpStatus status, Page<T> page, HttpServletRequest request){
        this.setResponse(new CustomResponse<>(status.value(),page.getContent(),page.getContent().getFirst().getClass()));
        sort = page.getSort().toString();
        first = LinkBuilder.build(page.getPageable().first().getPageNumber()+1,page.getSize(),page.getSort(), request);
        if(page.getPageable().getPageNumber()-1 > 1) previous = LinkBuilder.build(page.getPageable().getPageNumber()-1,page.getSize(),page.getSort(), request);
        self = LinkBuilder.build(page.getPageable().getPageNumber(),page.getSize(),page.getSort(), request);;
        if(page.getPageable().getPageNumber()+1 < page.getTotalPages()) next = LinkBuilder.build(page.getPageable().getPageNumber()+1,page.getSize(),page.getSort(), request);
        last = LinkBuilder.build(page.getTotalPages(),page.getSize(),page.getSort(), request);
        pageNumber = page.getNumber()+1;
        pageSize = page.getSize();
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
    }
}
