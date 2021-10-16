package cn.gzho.esdemo.service;

import cn.gzho.esdemo.pojo.JDGoodInfo;
import cn.gzho.esdemo.utils.HtmlParseUtil;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-06 10:55 AM
 */
@Service
public class GoodsService {

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private HtmlParseUtil htmlParseUtil;

    public Boolean putContentIntoES(String keyword) {
        List<JDGoodInfo> list = htmlParseUtil.pareJD(keyword);

        BulkRequest bulkRequest = new BulkRequest();

        bulkRequest.timeout(TimeValue.timeValueSeconds(5));

        list.forEach(jdGoodInfo -> bulkRequest.add(new IndexRequest("jd_goods")
                .source(JSON.toJSONString(jdGoodInfo), XContentType.JSON)));

        try {
            return client.bulk(bulkRequest, RequestOptions.DEFAULT).hasFailures();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String, Object>> searchPage(String keyword, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        SearchRequest searchRequest = new SearchRequest("jd_goods");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .timeout(TimeValue.timeValueSeconds(5))
                .from(pageNum)
                .size(pageSize)
                .query(QueryBuilders.matchQuery("title", keyword));

        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            ArrayList<Map<String, Object>> list = new ArrayList<>();
            hits.forEach(hit -> list.add(hit.getSourceAsMap()));
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
