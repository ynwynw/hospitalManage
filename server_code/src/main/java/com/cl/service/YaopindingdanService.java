package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YaopindingdanEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YaopindingdanView;


/**
 * 药品订单
 *
 * @author 
 * @email 
 * @date 2024-12-27 22:23:16
 */
public interface YaopindingdanService extends IService<YaopindingdanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YaopindingdanView> selectListView(Wrapper<YaopindingdanEntity> wrapper);
   	
   	YaopindingdanView selectView(@Param("ew") Wrapper<YaopindingdanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YaopindingdanEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<YaopindingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<YaopindingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<YaopindingdanEntity> wrapper);



}

