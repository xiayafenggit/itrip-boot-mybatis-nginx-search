package com.service.Impl;

import com.mapper.BaseQuery;
import com.service.ItripSearchHotelService;
import com.util.EmptyUtils;
import com.util.Page;
import com.util.vo.ItripHotelVO;
import com.util.vo.SearchHotelVO;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

@Service
public class ItripSearchHotelServiceImpl implements ItripSearchHotelService {
    private BaseQuery<ItripHotelVO> baseQuery=new BaseQuery<>("http://127.0.0.1:8089/solr/hotel");
    @Override
    public Page<ItripHotelVO> SearchHotelPageFromSolr(SearchHotelVO searchHotelVO) throws Exception{
        SolrQuery solrQuery = new SolrQuery("*:*");    //SolrQuery表示查询组件
        StringBuffer str=new StringBuffer();
        int tempflag=0;   //定义标识符
        if(EmptyUtils.isNotEmpty(searchHotelVO)) {
            if (EmptyUtils.isNotEmpty(searchHotelVO.getDestination())) {
                str.append("destination:" + searchHotelVO.getDestination());
                tempflag=1;
            }
            if (EmptyUtils.isNotEmpty(searchHotelVO.getHotelLevel())) {
                if(tempflag==1) {
                    str.append("AND hotelLevel:" + searchHotelVO.getHotelLevel());
                }/*else{
                    str.append("hotelLevel:" + searchHotelVO.getHotelLevel());
                }*/
            }
           /* if (EmptyUtils.isNotEmpty(searchHotelVO.getKeywords())) {
                if(tempflag==1){
                    str.append("AND keywords:" + searchHotelVO.getKeywords());
                }else {
                    str.append("keywords:" + searchHotelVO.getKeywords());
                }
            }*/
        }
               if(EmptyUtils.isNotEmpty(str.toString())){
                   solrQuery.setQuery(str.toString());
               }
            try {
                Page<ItripHotelVO>  page = baseQuery.queryPage(solrQuery,searchHotelVO.getPageNo(),searchHotelVO.getPageSize(),ItripHotelVO.class);
                return page;
            } catch (Exception e) {
                e.printStackTrace();
            }
       return null;
    }
}
