/**
 * @部门:学工产品事业部
 * @日期：2017年2月14日 下午3:33:54 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.gprz;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.dtjs.zzgxzc.bysdzbwh.BysdzbwhDao;
import com.zfsoft.xgxt.dtjs.zzgxzc.xxjg.XxjgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-组织关系转出-改派日志模块
 * @类功能描述: Service
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月14日 下午3:33:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GprzService extends SuperServiceImpl<GprzForm, GprzDao>{

	/**
	 * @throws Exception  
	 * @描述:生成修改前后记录，只拼接有改动的字段
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月15日 下午2:03:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xf
	 * @param xxjgForm
	 * @return
	 * String [] 返回类型 
	 * @throws 
	 */
	public String[] getXgqhjl(XxjgForm xf, XxjgForm xxjgForm) throws Exception {
		StringBuilder xgqjl_sb = new StringBuilder();
		StringBuilder xghjl_sb = new StringBuilder();
		
		List<String[]> list = new ArrayList<String[]>();
		HashMap<String,String> xgqMap = getXgjlMap(xf);
		HashMap<String,String> xghMap = getXgjlMap(xxjgForm);
		
		list.add(new String[]{xgqMap.get("szdzbmc"),xghMap.get("szdzbmc"),"所在党支部："});
		list.add(new String[]{xgqMap.get("sfsn"),xghMap.get("sfsn"),"是否省内："});
		list.add(new String[]{xgqMap.get("jsdzz"),xghMap.get("jsdzz"),"接收党组织："});
		list.add(new String[]{xgqMap.get("sqdw"),xghMap.get("sqdw"),"所去单位："});
		list.add(new String[]{xgqMap.get("dfjzrq"),xghMap.get("dfjzrq"),"党费交至日期："});
		list.add(new String[]{xgqMap.get("sfkjhyzm"),xghMap.get("sfkjhyzm"),"是否开具婚姻证明："});
		list.add(new String[]{xgqMap.get("jsxbh"),xghMap.get("jsxbh"),"介绍信编号："});
		
		for(int i=0;i<list.size();i++){
			String [] sa = list.get(i);
			if(!sa[0].equals(sa[1])){
				xgqjl_sb.append(sa[2]);
				xgqjl_sb.append(sa[0]);
				
				xghjl_sb.append(sa[2]);
				xghjl_sb.append(sa[1]);
				
				xgqjl_sb.append(",");
				xghjl_sb.append(",");
			}
		}
		
		String xgqjl = xgqjl_sb.toString();
		String xghjl = xghjl_sb.toString();
		xgqjl = xgqjl.substring(0,xgqjl.length()-1<0?0:xgqjl.length()-1);
		xghjl = xghjl.substring(0,xghjl.length()-1<0?0:xghjl.length()-1);
		
		return new String[]{xgqjl,xghjl};
	}

	/**
	 * @throws Exception  
	 * @描述:生成修改前（后）记录，全部拼接
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月15日 下午3:04:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xxjgForm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getXgqhjl(XxjgForm xxjgForm) throws Exception {
		StringBuilder xgqhjl_sb = new StringBuilder();
		
		List<String[]> list = new ArrayList<String[]>();
		HashMap<String,String> xgqhMap = getXgjlMap(xxjgForm);
		
		list.add(new String[]{xgqhMap.get("xh"),"学号："});
		list.add(new String[]{xgqhMap.get("szdzbmc"),"所在党支部："});
		list.add(new String[]{xgqhMap.get("sfsn"),"是否省内："});
		list.add(new String[]{xgqhMap.get("jsdzz"),"接收党组织："});
		list.add(new String[]{xgqhMap.get("sqdw"),"所去单位："});
		list.add(new String[]{xgqhMap.get("dfjzrq"),"党费交至日期："});
		list.add(new String[]{xgqhMap.get("sfkjhyzm"),"是否开具婚姻证明："});
		list.add(new String[]{xgqhMap.get("jsxbh"),"介绍信编号："});
		
		for(int i=0;i<list.size();i++){
			String [] sa = list.get(i);
			xgqhjl_sb.append(sa[1]);
			xgqhjl_sb.append(sa[0]);
			
			if(i!=list.size()-1){
				xgqhjl_sb.append(",");
			}
				
		}
		
		
		return xgqhjl_sb.toString();
	}
		
	/**
	 * @throws Exception 
	 * @描述:生成修改记录map，将修改前或修改后的XxjgForm转为统一map
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月15日 下午3:59:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	private HashMap<String,String> getXgjlMap(XxjgForm xxjgForm) throws Exception{
		String xh = xxjgForm.getXh();
		xh = xh == null?"":xh;	//学号
		
		String szdzbmc = xxjgForm.getSzdzbmc();
		if(szdzbmc==null){
			szdzbmc = new BysdzbwhDao().getModel(xxjgForm.getSzdzb()).getDzbmc();
		}
		szdzbmc = szdzbmc == null?"":szdzbmc;	//所在党支部名称
		
		String sfsn = xxjgForm.getSfsn();
		sfsn = sfsn == null?"":sfsn;	//是否省内
		
		String jsdzz = xxjgForm.getJsdzz();
		jsdzz = jsdzz == null?"":jsdzz;	//接收党组织
		
		String sqdw = xxjgForm.getSqdw();
		sqdw = sqdw == null?"":sqdw;	//所去单位
		
		String dfjzrq = xxjgForm.getDfjzrq();
		dfjzrq = dfjzrq == null?"":dfjzrq;	//党费交至日期
		
		String sfkjhyzm = xxjgForm.getSfkjhyzm();
		sfkjhyzm = sfkjhyzm == null?"":sfkjhyzm;	//是否开具婚姻证明
		
		String jsxbh = xxjgForm.getJsxbh();
		jsxbh = jsxbh == null?"":jsxbh;	//介绍信编号
		
		HashMap<String,String> xgjlMap = new HashMap<String,String> ();
		xgjlMap.put("xh", xh);
		xgjlMap.put("szdzbmc", szdzbmc);
		xgjlMap.put("sfsn", sfsn);
		xgjlMap.put("jsdzz", jsdzz);
		xgjlMap.put("sqdw", sqdw);
		xgjlMap.put("dfjzrq", dfjzrq);
		xgjlMap.put("sfkjhyzm", sfkjhyzm);
		xgjlMap.put("jsxbh", jsxbh);
		
		return xgjlMap;
	}

	/**
	 * @throws SQLException  
	 * @描述:批量保存改派日志
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月15日 下午5:32:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gprzFormList
	 * void 返回类型 
	 * @throws 
	 */
	public boolean saveGprzFormList(List<GprzForm> gprzFormList) throws SQLException {
		return dao.saveGprzFormList(gprzFormList);
	}



}
