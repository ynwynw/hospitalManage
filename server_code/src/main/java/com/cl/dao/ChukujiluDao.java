package com.cl.dao;

import com.cl.entity.ChukujiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChukujiluView;


/**
 * 出库记录
 * 
 * @author 
 * @email 
 * @date 2024-12-27 22:23:16
 */
public interface ChukujiluDao extends BaseMapper<ChukujiluEntity> {
	
	List<ChukujiluView> selectListView(@Param("ew") Wrapper<ChukujiluEntity> wrapper);

	List<ChukujiluView> selectListView(Pagination page,@Param("ew") Wrapper<ChukujiluEntity> wrapper);
	
	ChukujiluView selectView(@Param("ew") Wrapper<ChukujiluEntity> wrapper);


}
