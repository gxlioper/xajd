package com.zfsoft.xgxt.xsxx.xjyd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XjydService extends SuperServiceImpl<XjydForm, XjydDao> {

	private XjydDao dao = new XjydDao();
	
	public XjydService(){
		super.setDao(dao);
	}

	
	/**
	 * @throws Exception  
	 * @描述:学籍异动类别列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 上午10:10:08
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXjlbPageList(XjydForm model) throws Exception {
		
		return dao.getXjlbPageList(model);
	}

	/**
	 * @throws Exception  
	 * @描述:学籍异动类别列表（全部/已设定）
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 上午10:10:08
	 * @param flg : 0:全部异动列表；1：可申请的异动
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXjlbList(String flg, String ydlbdm) throws Exception {

		return dao.getXjlbList(flg, ydlbdm);
	}
	
	/** 
	 * @描述:增加学籍异动类别
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 下午02:20:47
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveXjydlb(XjydForm model) {
		
		// 判断代码是否已存在
		if(!checkIsExist(model.getXjlbdm(), false)){
			return dao.saveXjydlb(model);
		}else{
			return false;
		}	
	}


	/**
	 * 
	 * @描述:判断代码是否已存在
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 下午02:35:13
	 * @param xjlbdm 学籍类别代码
	 * @param shpzFlg 是否审核配置判断 true:是，false：否
	 * @return
	 * boolean true:存在，false:不存在
	 * @throws
	 */
	private boolean checkIsExist(String xjlbdm , boolean shpzFlg){
		
		XjydForm model = new XjydForm();
		model.setXjlbdm(xjlbdm);
		try {
			
			List<HashMap<String,String>> resultList = new ArrayList<HashMap<String,String>>();
			
			if(shpzFlg){
				resultList = getXjlbShpzPageList(model);
			}else{
				resultList = getXjlbPageList(model);
			}
			if(resultList !=null && resultList.size()>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/** 
	 * @描述:修改学籍异动类别
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 下午02:20:47
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updXjydlb(XjydForm model) {
		String xjlbdmold = model.getXjlbdmold();
		String xjlbdm = model.getXjlbdm();
		boolean bolflg = false;
		// 同样的代码
		if(xjlbdmold.equals(xjlbdm)){
			bolflg = dao.delXjydlb(xjlbdm) > 0 ? true:false;
			if(bolflg){
				bolflg = dao.saveXjydlb(model);
			}
		}else{

			// 判断代码是否已存在
			if(!checkIsExist(xjlbdm, false)){
				
				bolflg = dao.delXjydlb(xjlbdmold) > 0 ? true:false;
				if(bolflg){
					bolflg = dao.saveXjydlb(model);
				}				
			}else{
				return false;
			}
		}
		
		return bolflg;
	}
	
	/** 
	 * @描述:修改学籍异动类别
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 下午02:20:47
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public int delXjydlb(String values) {
		
		return	dao.delXjydlb(values);
	}


	/**
	 * @描述:学籍异动类别审核配置列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 下午03:47:33
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception  
	 */
	public List<HashMap<String, String>> getXjlbShpzPageList(XjydForm model) throws Exception {
		return dao.getXjlbShpzPageList(model);
	}


	/** 
	 * @描述:修改学籍异动类别审核配置
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-29 上午08:32:34
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveXjydlbShpz(XjydForm model) {
		
		if(!checkIsExist(model.getXjlbdm(), true)){
			return dao.saveXjydlbShpz(model);
		}else{
			return false;
		}
	}


	/**
	 * @throws Exception  
	 * @描述:修改学籍异动类别审核配置
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-29 上午08:47:06
	 * @param myForm
	 * @return
	 * XjydForm 返回类型 
	 * @throws 
	 */
	public XjydForm getModelShpz(XjydForm myForm) throws Exception {
		return dao.getModelShpz(myForm);
	}


	/** 
	 * @描述:修改学籍异动类别审核配置
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-29 上午08:54:47
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updXjydlbShpz(XjydForm myForm) {
		return dao.updXjydlbShpz(myForm);
	}


	/** 
	 * @描述:删除学籍异动类别审核配置
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-29 上午08:58:37
	 * @param values
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int delXjydlbShpz(String values) {
		return dao.delXjydlbShpz(values);
	}
	
	
	
	/**
	 * 
	 * @描述:判断异动类别代码是否已被使用
	 * @作者：cq [工号：785]
	 * @日期：2014-8-29 下午05:32:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lbdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean lbdmExist(String lbdm){
		
		String sqNum = dao.lbdmExistXjydsqb(lbdm);
		String jgNum = dao.lbdmExistXjydjgb(lbdm);
		String xsxxNum = dao.lbdmExistXsxxb(lbdm);
		
		boolean lbdmExist = true;
		
		if("0".equals(sqNum)&&"0".equals(jgNum)&&"0".equals(xsxxNum)){
			
			lbdmExist = false; 
		}
		
		return lbdmExist;
		
	}
	
	
	/**
	 * 
	 * @描述:查询类别名称是否已使用
	 * @作者：cq [工号：785]
	 * @日期：2014-9-9 上午09:38:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lbmc
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean lbmcExist(String lbmc){
		
		String lbmcNum = dao.lbmcExistLbmc(lbmc);
		
		if("0".equals(lbmcNum)){
			return false;
		}
		
			return true;
	}
	
	/**
	 * 
	 * @描述:查询类别代码是否已使用
	 * @作者：cq [工号：785]
	 * @日期：2014-9-10 上午09:24:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lbdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean lbdmExists(String lbdm){
		
		String lbdmNum = dao.lbdmExists(lbdm);
		
		if("0".equals(lbdmNum)){
			return false;
		}
		
		return true;
		
	}
	

}
