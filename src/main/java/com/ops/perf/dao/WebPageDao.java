package com.ops.perf.dao;

import com.ops.perf.model.WebPage;

public interface WebPageDao {

	public void insert(WebPage webPage);
	public WebPage findWebPageBySK(int wp_web_page_sk);
	
}
