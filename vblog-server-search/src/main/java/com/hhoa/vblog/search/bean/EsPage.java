package com.hhoa.vblog.search.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * EsPage.
 *
 * @author hhoa
 * @since 2022/8/5
 **/


@Schema(description = "单页信息")
public class EsPage extends PageRequest {
    public EsPage() {
        this(1, 0);
    }

    /**
     * Creates a new {@link AbstractPageRequest}.
     * Pages are zero indexed, thus providing 0 for {@code page} will return
     * the first page.
     *
     * @param pageNum must not be less than zero.
     * @param pageSize must not be less than one.
     */
    public EsPage(int pageNum, int pageSize) {
        super(pageNum, pageSize, Sort.unsorted());
    }
}
