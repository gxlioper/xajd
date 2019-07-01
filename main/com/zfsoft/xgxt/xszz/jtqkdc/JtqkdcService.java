package com.zfsoft.xgxt.xszz.jtqkdc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生资助2013版，家庭情况调查
 * @作者： Penghui.Qu 
 * @时间： 2013-4-18 下午05:35:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JtqkdcService extends SuperServiceImpl<JtqkdcForm, JtqkdcDao> {

	private JtqkdcDao dao = new JtqkdcDao();
	
	public JtqkdcService(){
		super.setDao(dao);
	}
	
	/**
	 * 根据学号查询家庭成员列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getJtcyList(String xh){
		
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		
		return dao.getJtcyList(xh);
	}
	

	
	/**
	 * 空的list
	 * @param size
	 * @return
	 */
	public List<HashMap<String,String>> getBlankList(int size){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		
		return list;
	}

	
	
	public boolean runInsert(JtqkdcForm t) throws Exception {
		
		JtqkdcForm model = dao.getModel(t);
		
		boolean isSuccess = true;
		
		t.setDcsj(GetTime.getTimeByFormat("YYYYMMDD"));
		
		//判断是否已经有填写过家庭情况调查
		if (model == null){
			isSuccess = super.runInsert(t);
		} else {
			isSuccess = super.runUpdate(t);
		}
		
		//保存家庭成员信息
		saveJtcy(t);
		
		return isSuccess;
	}
	public boolean runInsertOfYdxg(JtqkdcForm t) throws Exception {
		
		JtqkdcForm model = dao.getModel(t);
		
		boolean isSuccess = true;
		
		t.setDcsj(GetTime.getTimeByFormat("YYYYMMDD"));
		
		//判断是否已经有填写过家庭情况调查
		if (model == null){
			isSuccess = super.runInsert(t);
		} else {
			isSuccess = super.runUpdate(t);
		}
		return isSuccess;
	}
	
	//保存家庭成员信息
	private void saveJtcy(JtqkdcForm t) throws Exception{
		
		//先删后增
		String xh = t.getXh();
		//删除家庭成员
		dao.deleteJtcy(new String[]{xh});
		
		JtcyForm jtcy = t.getJtcy();
		
		Field[] fields = jtcy.getClass().getDeclaredFields();
		Map<String,String[]> map = new HashMap<String,String[]>();
		
		List<String> keys = new ArrayList<String>();
		
		//根据家庭成员对象属性值，获取全部家庭成员数据
		for (Field field : fields){
			String fieldName = field.getName();
			String methodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
			String[] values = (String[]) jtcy.getClass().getMethod(methodName).invoke(jtcy);
			
			if (null != values){
				keys.add(fieldName);
				map.put(fieldName, values);
			}
		}
		
		if (keys.size() == 0){
			logger.error("无家庭成员信息需要保存!");
			throw new NullPointerException();
		}
		

		//将家庭成员数据拼装成行对象
		int m = 0;
		String[] zdmc = new String[keys.size()];
		List<String[]> params = new ArrayList<String[]>();

		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			zdmc[m] = entry.getKey();
			String[] values = entry.getValue();
			
			for (int i = 0 , n = values.length ; i < n ; i++){
				String[] param = params.size() < i+1 ? new String[keys.size()] : params.get(i);
				param[m] = values[i];
				
				if (params.size() > i){
					params.remove(i);
				}
				
				params.add(i,param);
			}
			m++;
		}
		
		//检测家庭成员数据，全部非空的作为要保存的数据参数
		List<String[]> newParams = new ArrayList<String[]>();
		
		for (int i = 0 ; i < params.size() ; i++){
			String joinStr = Arrays.toString(params.get(i)).replaceAll(",", "").replace("[", "").replace("]", "");
			
			if (!StringUtil.isNull(joinStr.trim())){
				
				newParams.add(StringUtils.joinStrArr(new String[]{xh},params.get(i)));
			}
		}
		
		
		dao.saveJtcy(StringUtils.joinStrArr(new String[]{"xh"},zdmc), newParams);
	}

	
	
	
	
	/**
	 * 家庭情况调查信息删除
	 */
	public int runDelete(String[] values) throws Exception {
		
		//家庭情况调查删除
		int num = super.runDelete(values);
		
		if (num > 0){
			//家庭成员删除
			dao.deleteJtcy(values);
		}
		
		return num;
	}

	/** 
	 * @描述:获得父亲信息
	 * @作者：cq [工号：785]
	 * @日期：2013-9-22 下午01:44:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @return
	 * JtqkdcForm 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getfqInfo(String xh) {

		return dao.getfqInfo(xh);
	}

	/** 
	 * @描述:获得母亲信息
	 * @作者：cq [工号：785]
	 * @日期：2013-9-22 下午01:44:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @return
	 * JtqkdcForm 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getmqInfo(String xh) {

		return dao.getmqInfo(xh);
	}

	/** 
	 * @描述:上海海洋大学个性化查询家庭信息
	 * @作者：cq [工号：785]
	 * @日期：2014-10-29 下午04:50:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getJtqkInfo(String xh) {
		
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		
		return dao.getJtqkInfo(xh);
	}
	
	
	/**
	 * 根据学号查询家庭成员列表（默认前3条（上海海洋））
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getJtcyListSh(String xh){
		
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		
		return dao.getJtcyListSh(xh);
	}
	
	/**
	 * 
	 * @描述:填写调查表模块，浙江大学是否残疾与残疾证编号联动，页面加载完毕根据是否残疾值为是（1）的时候
	 * 取cjbh(ylzd4)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-10-30 下午04:05:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public String getCjbh_10335(String xh){
		return dao.getCjbh_10335(xh);
	}
	
	/**
	 * @描述：通用sql，取前n条家庭成员列表，不足n条填空
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月6日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getJtcyList(String xh,int n){
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		List<HashMap<String, String>> list = dao.getJtcyList(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
	/**
	 * @描述: 根据核实人的用户名查询出姓名
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-8-8 上午11:32:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yhm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String yhxm(String yhm){
		return dao.yhxm(yhm);
	}
	
	/**
	 * @描述: 根据学号获取家庭收入情况
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-9-11 下午05:10:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getJtsrqkByXh (String xh){
		return dao.getJtsrqkByXh(xh);
	}
}
