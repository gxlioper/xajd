/**
 * @部门:学工产品事业部
 * @日期：2013-12-16 上午10:02:13 
 */  
package com.zfsoft.xgxt.xsxx.bbzc;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学期报到注册
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-16 上午10:02:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqbdzcService extends SuperServiceImpl<XqbdzcForm, XqbdzcDao> {

	private XqbdzcDao dao = new XqbdzcDao();

	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param dao
	 */
	public XqbdzcService() {
		super.setDao(dao);
	}
	
	/**
	 * @throws Exception 
	 * 获取学期注册信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-16 下午07:24:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXqZcXx(String xh , String xn , String xq) throws Exception{
		return dao.getXqzcxx(xh, xn, xq);
	}
	
	/**
	 * 获取注册状态（查看页面判断所需要）
	 * 孟威
	 */
	public String getZczt(String xh , String xn , String xq) throws Exception{
		return dao.getZczt(xh, xn, xq);
	}
	/**
	 * 
	 * @描述:获取财务数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-17 上午09:35:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getCwsjList(String xh , String xn , String xq){
		return dao.getCwsjList(xh, xn, xq);
	}
	public boolean doDtZc(XqbdzcForm model) throws Exception{
		boolean flag = true;
		flag=dao.deleteZcxx(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:批量注册
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-17 下午01:09:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param plids
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean plXqzc(String[] plids, String xn , String xq , String zcr , String zcsj, XqbdzcForm t, User user) throws Exception{
		if(plids!= null && plids.length != 0){
//			wzcxsArr=plids;
			return dao.plXqzc(plids, xn, xq, zcr, zcsj, t, user, "ids");
		}
//		if(wzcxsArr == null || wzcxsArr.length == 0)
//			return false;
		
		return dao.plXqzc(new String[]{}, xn, xq, zcr, zcsj, t, user, "all");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:批量撤销
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-17 下午01:42:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param plids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean plCxzc(String[] plIds ,String xn ,String xq, XqbdzcForm t, User user) throws Exception{
//		String[] ids = null;
//		if(cxxsList == null || cxxsList.size()==0){
//			ids=plIds;
//		}
//		else{
//			ids= new String[cxxsList.size()];
//			for (int i = 0; i < cxxsList.size(); i++) {
//				ids[i]=cxxsList.get(i).get("id");
//			}
//		}
//		dao.runDelete(ids);
		
		if(null==t.getPlIds()){
			return dao.plCxzc(xn, xq, t,  user);
		}
		else{
			String[] ids=plIds;
			dao.runDelete(ids);
			
			return true;
		}
	}
	/**
	 * 
	 * @描述:未报到原因维护
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午03:01:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param plids
	 * @param xn
	 * @param xq
	 * @param zcr
	 * @param zcsj
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean wbdyywh(XqbdzcForm model) throws Exception{
		return dao.wbdyywh(model);
	}
	/**
	 * 
	 * @描述:获取用户常用意见列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午03:50:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCyyyList(User user) {
		return dao.getCyyyList(user);
	}
	/**
	 * 
	 * @描述:常用原因保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午04:16:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gnid
	 * @param shyj
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCyyy(User user,String[] cyyy){
		
		if (cyyy == null || cyyy.length == 0)
			return false;
		
		
		try {
			boolean b = dao.delCyyy(user);
			
			if (b){
				return dao.saveCyyy(user, cyyy);
			}
			
			return b;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:获取未注册学生数
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-10 上午11:21:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
    public String[] getWzcList(XqbdzcForm model,User user) throws Exception{
		return dao.getWzcArr(model,user);
	}
    
    public List<HashMap<String,String>> getBdzcList(XqbdzcForm model,User user) throws Exception{
		return dao.getBdzcList(model,user);
	}

	/**
	 * @throws Exception  
	 * @描述:获取未处理学生数目
	 * @作者：黄辰霁
	 * @日期：2015-12-15 下午03:36:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String[] 返回类型 
	 * @throws 
	 */
	public String getWzcListCount(XqbdzcForm model, User user) throws Exception {
		return dao.getBdzcListCount(model,user,"-1");
	}
	
	/**
	 * 
	 * @描述:获取未注册学生数目
	 * @作者：张昌路[工号：982]
	 * @日期：2016-1-12 下午07:19:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getWclListCount(XqbdzcForm model, User user) throws Exception {
		return dao.getBdzcListCount(model,user,"0");
	}

	/**
	 * @throws Exception  
	 * @描述:获取总人数
	 * @作者：黄辰霁
	 * @日期：2015-12-15 下午05:15:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getBdzcListCount(XqbdzcForm model, User user) throws Exception {
		return dao.getBdzcListCount(model,user,null);
	}
	public List<HashMap<String, String>> getWbdlb() {
		return dao.getWbdlb();
	}
	
	
}
