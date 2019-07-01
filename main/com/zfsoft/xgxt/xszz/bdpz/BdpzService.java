package com.zfsoft.xgxt.xszz.bdpz;

import java.util.HashMap;
import java.util.List;


import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: 学生资助2013版，动态表单相关操作
 * @作者： Penghui.Qu 
 * @时间： 2013-4-18 下午04:39:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BdpzService extends SuperServiceImpl<BdpzForm, BdpzDao> {

	
	private BdpzDao dao = new BdpzDao();
	private static final String T = "t";
	private static final String M = "m";
	private static final String splitStr = "#";
	
	
	public BdpzService(){
		super.setDao(dao);
	}

	/**
	 * 资助项目字段配置信息
	 * @param gnmk
	 * @return
	 */
	public List<HashMap<String,String>> getBdpz(String gnmk){
		
		if (StringUtil.isNull(gnmk)){
			logger.error("gnmk can't null ! ");
			throw new NullPointerException();
		}
		
		return dao.getBdpz(gnmk);
	}
	
	
	/**
	 * 资助项目学生基本信息配置
	 * @param gnmk
	 * @return
	 */
	public List<HashMap<String,String>> getJbxxpz(String gnmk){
		
		if (StringUtil.isNull(gnmk)){
			logger.error("gnmk can't null ! ");
			throw new NullPointerException();
		}
		
		return dao.getJbxxpz(gnmk);
	}
	
	
	
	/**
	 * 根据表单配置加载下拉、多选、单选项
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String,String>> getSjyList(BdpzForm model){
		
		String sjy = model.getSjy();
		
		if (StringUtil.isNull(sjy) || sjy.length() == 0){
			logger.error("sjy is null !");
			throw new NullPointerException();
		}
		
		List<HashMap<String,String>> dataList = null;
		//数据源配置方式 T(T#tableName#dm#mc) 或 M，T 代表table M 代表Method
		String pzfs = sjy.substring(0,1);
		
		if (T.equalsIgnoreCase(pzfs)){
			String[] info = sjy.split(splitStr);
			
			if (info == null || info.length != 4){
				logger.error("配置信息不符合规则！");
				throw new NullPointerException();
			}
			
			String[] params = new String[]{info[2],info[3],info[1]};
			dataList = dao.getSjyList(params);
		} else if (M.equalsIgnoreCase(pzfs)){
			//反射调用 sjy 配置格式 (M#com.zfsoft.xgxt.**Service#methodName#param)
			String[] info = sjy.split(splitStr);
			//三个的是不带参数，四的是带单个参数，另外的不支持
			if (info == null || (info.length != 4 && info.length != 3)){
				logger.error("配置信息不符合规则！");
				throw new NullPointerException();
			}
			
			String className = info[1];
			String methodName = info[2];
			String param = info.length == 4 ? info[3] : null;
			
			try{
				//获取对象类型
				Class<?> t = Class.forName(className);
				//获取对象实例
				Object o =  t.getConstructor().newInstance();
				if (StringUtil.isNull(param)){
					dataList = (List<HashMap<String, String>>) t.getMethod(methodName).invoke(o);
				} else {
					dataList = (List<HashMap<String, String>>) t.getMethod(methodName,new Class[]{String.class}).invoke(o,param);
				}
			} catch(Exception e){
				e.printStackTrace();
				logger.error("反射调用下拉项出错！");
			}
		} else {
			logger.error("数据源配置信息不符合规则！");
		}
		
		return dataList;
	}
}
