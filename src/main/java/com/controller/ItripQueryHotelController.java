package com.controller;

import com.po.Dto;
import com.service.ItripSearchHotelService;
import com.util.DtoUtil;
import com.util.EmptyUtils;
import com.util.Page;
import com.util.vo.SearchHotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/hotellist")
public class ItripQueryHotelController {
    @Autowired
    private ItripSearchHotelService itripSearchHotelService;

    @RequestMapping(value = "/searchItripHotelPage")
    public Dto searchItripHotelPage(@RequestBody SearchHotelVO searchHotelVO){
        System.out.println("酒店分页查询方法...");
        //System.out.println(searchHotelVO.toString());
        if(EmptyUtils.isEmpty(searchHotelVO)||EmptyUtils.isEmpty(searchHotelVO.getDestination())){
            return DtoUtil.returnFail("查询条件不能为空...","30001");
        }
            try {
                Page page=itripSearchHotelService.SearchHotelPageFromSolr(searchHotelVO);
                System.out.println(page.toString());
                if(page.getRows().size()==0){
                    return DtoUtil.returnFail("该条件下不存在酒店...","30000");
                }
                return DtoUtil.returnDataSuccess(page);
            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }
}
