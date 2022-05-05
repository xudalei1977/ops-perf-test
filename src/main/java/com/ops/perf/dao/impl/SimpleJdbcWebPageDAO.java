package com.ops.perf.dao.impl;

import com.ops.perf.Test;
import com.ops.perf.dao.WebPageDao;
import com.ops.perf.model.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//if using hive, use the table.column as column name, e.g. WebPage.id instead of id
public class SimpleJdbcWebPageDAO extends NamedParameterJdbcDaoSupport implements WebPageDao{

	public static Logger log = LoggerFactory.getLogger(Test.class);

	@Override
	public void insert(WebPage webPage) {
//		String sql = "UPDATE WebPage SET NAME = :name WHERE ID = :id";
//		String sql = "insert into customer (id, name, age) values (:id, :name, :age)";
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
				" hm) values (:wp_web_page_sk, " +
				" :wp_web_page_id, " +
				" :wp_rec_start_date, " +
				" :wp_rec_end_date, " +
				" :wp_creation_date_sk, " +
				" :wp_access_date_sk, " +
				" :wp_autogen_flag, " +
				" :wp_customer_sk, " +
				" :wp_url, " +
				" :wp_type, " +
				" :wp_char_count, " +
				" :wp_link_count, " +
				" :wp_image_count, " +
				" :wp_max_ad_count, " +
				" :logday, " +
				" :hm)";

		SqlParameterSource ps = new BeanPropertySqlParameterSource(webPage);
		int i = getNamedParameterJdbcTemplate().update(sql, ps);
		log.info("***** affected row " + i);
	}
	
	@Override
	public WebPage findWebPageBySK(int sk) {
		String sql = "select * from web_page where wp_web_page_sk = :wp_web_page_sk";

		Map<String, Object> map = new HashMap();
		map.put("wp_web_page_sk", sk);
		
//		List<Map<String, Object>> list = getNamedParameterJdbcTemplate().queryForList("select * from default.WebPage", map);
//		System.out.println( list.toString() );
		
		Map<String, Object> webPageMap =  getNamedParameterJdbcTemplate().queryForMap(sql, map);

		Set<String> keys = webPageMap.keySet();
		for(String key:keys){
			log.info("****** key := " + key + " value := " + webPageMap.get(key));
		}

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
