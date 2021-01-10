package com.mapper;
import com.util.Constants;
import com.util.EmptyUtils;
import com.util.Page;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import java.util.List;


/**
 * 通用的封装的查询solr的基础查询类
 * Created by XX on 17-5-5.
 */
public class BaseQuery<T> {
    private HttpSolrClient httpSolrClient;
    /***
     * 使用URL 初始化 httpSolrClient
     * @param
     */
    public BaseQuery(String url) {
        httpSolrClient = new HttpSolrClient(url);
        httpSolrClient.setParser(new XMLResponseParser()); // 设置响应解析器
        httpSolrClient.setConnectionTimeout(500); // 建立连接的最长时间
    }

    /***
     * 使用SolrQuery 查询分页数据
     */
    public Page<T> queryPage(SolrQuery query, Integer pageNo, Integer pageSize, Class clazz) throws Exception {
        //设置起始页数
                //结果集合
        Integer rows= EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE: pageSize;

        Integer currPage=(EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO-1: pageNo - 1);
        Integer start=currPage*rows;
        query.setStart(start);
        //一页显示多少条
        query.setRows(rows);
        System.out.println(httpSolrClient.toString());
        System.out.println(query.toString());
        QueryResponse queryResponse = httpSolrClient.query(query);
        SolrDocumentList docs = queryResponse.getResults();
        Page<T> page = new Page(currPage+1, query.getRows(), new Long(docs.getNumFound()).intValue());
        List<T> list = queryResponse.getBeans(clazz);
        page.setRows(list);
        return page;
    }

    /***
     * 使用SolrQuery 查询列表数据(即查询所有)
     */
    public List<T> queryList(SolrQuery query, Integer pageSize, Class clazz) throws Exception {
        //设置起始页数
        query.setStart(0);
        //一页显示多少条
        query.setRows(EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize);
        QueryResponse queryResponse = httpSolrClient.query(query);
        List<T> list = queryResponse.getBeans(clazz);
        return list;
    }
}
