package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.KaoqinjiluEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KaoqinjiluView;


/**
 * 考勤记录
 *
 * @author 
 * @email 
 * @date 2024-12-27 22:23:15
 */
public interface KaoqinjiluService extends IService<KaoqinjiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KaoqinjiluView> selectListView(Wrapper<KaoqinjiluEntity> wrapper);
   	
   	KaoqinjiluView selectView(@Param("ew") Wrapper<KaoqinjiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KaoqinjiluEntity> wrapper);
   	
   
}

