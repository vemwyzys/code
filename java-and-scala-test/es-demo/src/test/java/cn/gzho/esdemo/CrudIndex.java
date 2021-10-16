package cn.gzho.esdemo;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class CrudIndex {

	@Autowired
	private RestHighLevelClient client;

	@Test
	void contextLoads() {
	}

	/**
	 * add index
	 * @throws IOException
	 */
	@Test
	void testCreatIndex() throws IOException {
		//create index request
		CreateIndexRequest request = new CreateIndexRequest("user_index");
		//client execute request, and get the response
		CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

		System.out.println(createIndexResponse);

	}

	/**
	 * get if index exists
	 * @throws IOException
	 */
	@Test
	void testExistIndex() throws IOException {
		//get index request
		GetIndexRequest getIndexRequest = new GetIndexRequest("test");
		boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
		System.out.println(exists);
	}

	/**
	 * delete index
	 * @throws IOException
	 */
	@Test
	void deleteIndex() throws IOException {
		//get index request
		DeleteIndexRequest delete = new DeleteIndexRequest("test");
		AcknowledgedResponse response = client.indices().delete(delete, RequestOptions.DEFAULT);
		System.out.println(response.isAcknowledged());
	}


}
