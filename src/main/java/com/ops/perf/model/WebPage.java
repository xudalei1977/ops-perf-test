package com.ops.perf.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WebPage {

    private int wp_web_page_sk;
    private String wp_web_page_id;
    private String wp_rec_start_date;
    private String wp_rec_end_date;
    private int wp_creation_date_sk;
    private int wp_access_date_sk;
    private String wp_autogen_flag;
    private int wp_customer_sk;
    private String wp_url;
    private String wp_type;
    private int wp_char_count;
    private int wp_link_count;
    private int wp_image_count;
    private int wp_max_ad_count;
    private String logday;
    private String hm;


    public WebPage(int wp_web_page_sk,
                   String wp_web_page_id,
                   String wp_rec_start_date,
                   String wp_rec_end_date,
                   int wp_creation_date_sk,
                   int wp_access_date_sk,
                   String wp_autogen_flag,
                   int wp_customer_sk,
                   String wp_url,
                   String wp_type,
                   int wp_char_count,
                   int wp_link_count,
                   int wp_image_count,
                   int wp_max_ad_count,
                   String logday,
                   String hm) {
        this.wp_web_page_sk = wp_web_page_sk;
        this.wp_web_page_id = wp_web_page_id;
        this.wp_rec_start_date = wp_rec_start_date;
        this.wp_rec_end_date = wp_rec_end_date;
        this.wp_creation_date_sk = wp_creation_date_sk;
        this.wp_access_date_sk = wp_access_date_sk;
        this.wp_autogen_flag = wp_autogen_flag;
        this.wp_customer_sk = wp_customer_sk;
        this.wp_url = wp_url;
        this.wp_type = wp_type;
        this.wp_char_count = wp_char_count;
        this.wp_link_count = wp_link_count;
        this.wp_image_count = wp_image_count;
        this.wp_max_ad_count = wp_max_ad_count;
        this.logday = logday;
        this.hm = hm;
    }

}
