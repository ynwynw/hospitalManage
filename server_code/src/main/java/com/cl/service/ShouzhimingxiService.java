package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ShouzhimingxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ShouzhimingxiView;


/**
 * 收支明细
 *
 * @author 
 * @email 
 * @date 2024-12-27 22:23:16
 */
public interface ShouzhimingxiService extends IService<ShouzhimingxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShouzhimingxiView> selectListView(Wrapper<ShouzhimingxiEntity> wrapper);
   	
   	ShouzhimingxiView selectView(@Param("ew") Wrapper<ShouzhimingxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShouzhimingxiEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<ShouzhimingxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<ShouzhimingxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<ShouzhimingxiEntity> wrapper);



}

