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


import com.cl.dao.KaoqinjiluDao;
import com.cl.entity.KaoqinjiluEntity;
import com.cl.service.KaoqinjiluService;
import com.cl.entity.view.KaoqinjiluView;

@Service("kaoqinjiluService")
public class KaoqinjiluServiceImpl extends ServiceImpl<KaoqinjiluDao, KaoqinjiluEntity> implements KaoqinjiluService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KaoqinjiluEntity> page = this.selectPage(
                new Query<KaoqinjiluEntity>(params).getPage(),
                new EntityWrapper<KaoqinjiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<KaoqinjiluEntity> wrapper) {
		  Page<KaoqinjiluView> page =new Query<KaoqinjiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<KaoqinjiluView> selectListView(Wrapper<KaoqinjiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KaoqinjiluView selectView(Wrapper<KaoqinjiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
