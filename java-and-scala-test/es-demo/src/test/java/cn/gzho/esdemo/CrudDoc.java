package cn.gzho.esdemo;

import cn.gzho.esdemo.pojo.User;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CrudDoc {

    @Autowired
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
    }

    /**
     * add doc
     *
     * @throws IOException
     */
    @Test
    void addDoc() throws IOException {
        User user = new User("张张中", 30);
        IndexRequest request = new IndexRequest("user_index");

        request.id("1")
                .timeout(TimeValue.timeValueSeconds(1))
                .source(JSON.toJSONString(user), XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response.status());

    }

    /**
     * if exist a doc
     *
     * @throws IOException
     */
    @Test
    void ifExistDoc() throws IOException {
        GetRequest request = new GetRequest("user_index", "1");
        request.fetchSourceContext(new FetchSourceContext(false))//cus only get the exist,not get the context of _source
                .storedFields("_none");

        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * get doc
     *
     * @throws IOException
     */
    @Test
    void getDoc() throws IOException {
        GetRequest request = new GetRequest("user_index", "1");

        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response);
        System.out.println(response.getSourceAsString());
    }

    @Test
    void updateDoc() throws IOException {
        User user = new User("kkkkick", 19);
        UpdateRequest updateRequest = new UpdateRequest("user_index", "1");

        updateRequest
                .timeout("1s")
                .doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse response = client.update(updateRequest, RequestOptions.DEFAULT);

        System.out.println(response.status());
    }

    /**
     * delete index
     *
     * @throws IOException
     */
    @Test
    void deleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("user_index", "1");
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    @Test
    void bulkAdd() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("1s");
        for (int i = 0; i < 10; i++) {
            bulkRequest.add(new IndexRequest("user_index")
                    .id("" + (i + 1))
                    .source(JSON.toJSONString(new User("No." + i, i)), XContentType.JSON)
            );
        }

        BulkResponse responses = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        System.out.println(responses.hasFailures());

    }

    @Test
    void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user_index");
        //create a searchSource that describes the search action
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();



        //to build search condition that added to searchSource, we can use QueryBuilders tools
        //QueryBuilders.termQuery() => search exactly
        //QueryBuilders.matchAllQuery() => match all
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("age", "8");
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        searchSourceBuilder
//                .from(0)
//                .size(5)
                .query(termQueryBuilder)
                .timeout(new TimeValue(30, TimeUnit.SECONDS));

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(searchResponse);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));

        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }

    }

}
