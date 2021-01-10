package com.service;

import com.util.Page;

import com.util.vo.ItripHotelVO;
import com.util.vo.SearchHotelVO;


import java.util.List;

public interface ItripSearchHotelService {
    public Page<ItripHotelVO> SearchHotelPageFromSolr(SearchHotelVO searchHotelVO) throws Exception;   //从solr中分页查询酒店信息方法
}
