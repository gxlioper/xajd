package xsgzgl.xtwh.general.cxjgpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_查询结果配置
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class CxjgpzService extends CommService{

	CxjgpzDao dao = new CxjgpzDao();
	
	//未配置字段
	public List<HashMap<String, String>> getWpzzdlist(CxjgpzForm model) throws Exception{		
		return dao.getWpzzdlist(model);
	}
	
	//已配置字段
	public List<HashMap<String, String>> getYpzzdlist(CxjgpzForm model) throws Exception{	
		return dao.getYpzzdlist(model);
	}
	
	//保存配置字段
	public boolean bcCxjgpz(CxjgpzForm model) throws Exception{
		//先把所有字段设置为未显示
		boolean flag = false;
		flag=dao.xgCxjgSfxs();
		flag = dao.bcCxjg(model);
		return flag;
	}
	
	//获取一条数据
	public HashMap<String, String> getZd(CxjgpzForm model){	
		return dao.getZd(model);
	}
	
	//保存配置字段
	public boolean xgZdmc(CxjgpzForm model) throws Exception{
		boolean flag = false;
		flag = dao.xgZdmc(model);
		return flag;
	}
	
	//已配置字段
	public ArrayList<String[]> getXsxxlist(CxjgpzForm model) throws Exception{	
		return dao.getXsxxlist(model);
	}
}
