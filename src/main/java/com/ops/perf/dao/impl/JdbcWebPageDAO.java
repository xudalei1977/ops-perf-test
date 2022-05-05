package com.ops.perf.dao.impl;

import com.ops.perf.dao.WebPageDao;
import com.ops.perf.model.WebPage;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.Map;

public class JdbcWebPageDAO extends JdbcDaoSupport implements WebPageDao {

	@Override
	public void insert(WebPage webPage) {
		String sql = "insert into web_page (wp_web_page_sk, " +
				" wp_web_page_id, " +
				" wp_rec_start_date, " +
				" wp_rec_end_date, " +
				" wp_creation_date_sk, " +
				" wp_access_date_sk, " +
				" wp_autogen_flag, " +
				" wp_customer_sk, " +
				" wp_url, " +
				" wp_type, " +
				" wp_char_count, " +
				" wp_link_count, " +
				" wp_image_count, " +
				" wp_max_ad_count, " +
				" logday, " +
				" hm) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		getJdbcTemplate().update(sql, new Object[] {
							webPage.getWp_web_page_sk(),
							webPage.getWp_web_page_id(),
							webPage.getWp_rec_start_date(),
							webPage.getWp_rec_end_date(),
							webPage.getWp_creation_date_sk(),
							webPage.getWp_access_date_sk(),
							webPage.getWp_autogen_flag(),
							webPage.getWp_customer_sk(),
							webPage.getWp_url(),
							webPage.getWp_type(),
							webPage.getWp_char_count(),
							webPage.getWp_link_count(),
							webPage.getWp_image_count(),
							webPage.getWp_max_ad_count(),
							webPage.getLogday(),
							webPage.getHm() });
	}

	@Override
	public WebPage findWebPageBySK(int sk) {
		String sql = "select * from web_page where wp_web_page_sk = ?";
		Map<String, Object> webPageMap =  getJdbcTemplate().queryForMap(sql, sk);

		WebPage webPage = new WebPage(
				Integer.parseInt(webPageMap.get("wp_web_page_sk").toString()),
				webPageMap.get("wp_web_page_id").toString(),
				webPageMap.get("wp_rec_start_date").toString(),
				webPageMap.get("wp_rec_end_date").toString(),
				Integer.parseInt(webPageMap.get("wp_creation_date_sk").toString()),
				Integer.parseInt(webPageMap.get("wp_access_date_sk").toString()),
				webPageMap.get("wp_autogen_flag").toString(),
				Integer.parseInt(webPageMap.get("wp_customer_sk").toString()),
				webPageMap.get("wp_url").toString(),
				webPageMap.get("wp_type").toString(),
				Integer.parseInt(webPageMap.get("wp_char_count").toString()),
				Integer.parseInt(webPageMap.get("wp_link_count").toString()),
				Integer.parseInt(webPageMap.get("wp_image_count").toString()),
				Integer.parseInt(webPageMap.get("wp_max_ad_count").toString()),
				webPageMap.get("logday").toString(),
				webPageMap.get("hm").toString());

		return webPage;
	}
}
