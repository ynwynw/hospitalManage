package com.cl.dao;

import com.cl.entity.KaoqinjiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KaoqinjiluView;


/**
 * 考勤记录
 * 
 * @author 
 * @email 
 * @date 2024-12-27 22:23:15
 */
public interface KaoqinjiluDao extends BaseMapper<KaoqinjiluEntity> {
	
	List<KaoqinjiluView> selectListView(@Param("ew") Wrapper<KaoqinjiluEntity> wrapper);

	List<KaoqinjiluView> selectListView(Pagination page,@Param("ew") Wrapper<KaoqinjiluEntity> wrapper);
	
	KaoqinjiluView selectView(@Param("ew") Wrapper<KaoqinjiluEntity> wrapper);


}
