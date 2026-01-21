package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.YaopinleixingDao;
import com.cl.entity.YaopinleixingEntity;
import com.cl.service.YaopinleixingService;
import com.cl.entity.view.YaopinleixingView;

@Service("yaopinleixingService")
public class YaopinleixingServiceImpl extends ServiceImpl<YaopinleixingDao, YaopinleixingEntity> implements YaopinleixingService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YaopinleixingEntity> page = this.selectPage(
                new Query<YaopinleixingEntity>(params).getPage(),
                new EntityWrapper<YaopinleixingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YaopinleixingEntity> wrapper) {
		  Page<YaopinleixingView> page =new Query<YaopinleixingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YaopinleixingView> selectListView(Wrapper<YaopinleixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YaopinleixingView selectView(Wrapper<YaopinleixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
