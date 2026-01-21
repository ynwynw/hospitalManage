package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YaopinleixingEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YaopinleixingView;


/**
 * 药品类型
 *
 * @author 
 * @email 
 * @date 2024-12-27 22:23:16
 */
public interface YaopinleixingService extends IService<YaopinleixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YaopinleixingView> selectListView(Wrapper<YaopinleixingEntity> wrapper);
   	
   	YaopinleixingView selectView(@Param("ew") Wrapper<YaopinleixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YaopinleixingEntity> wrapper);
   	
   
}

