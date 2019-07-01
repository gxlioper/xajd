/**
 * @部门:学工产品事业部
 * @日期：2013-7-31 上午08:27:26 
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.action.Base;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;



/**'
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办类型管理模块
 * @类功能描述: TODO(学生证补办类型) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-17 上午08:54:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YlbxwhService extends SuperServiceImpl<YlbxwhForm, YlbxwhDao>  {

	private YlbxwhDao dao = new YlbxwhDao();
	public static String _BCZSCID="-1";
	
	public YlbxwhService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(财政全额补助人员身份列表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午02:08:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCzqebzlxPageList(YlbxwhForm model) throws Exception{
		return dao.getCzqebzlxPageList(model);
	}
	
	/**
	 * 
	 * @描述:TODO(参保状况列表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午02:08:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCbzklxPageList(YlbxwhForm model) throws Exception{
		return dao.getCbzklxPageList(model);
	}
	
	/**
	 * 
	 * @描述:TODO(获得最大序号的学生证补办类型代码)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午08:57:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String changeCzqebzlxdm() {
		String max = dao.getMaxCzqelxdm();
		if(Base.isNull(max)){
			return "001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	
	/**
	 * 
	 * @描述:TODO(保存财政全额补助人员身份)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午04:13:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCzqebzlxInfo(YlbxwhForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			String czqebzdm = changeCzqebzlxdm();
			model.setCzqebzdm(czqebzdm);
			return dao.addCzqebzlxInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateCzqebzlxInfo(model);
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(获取财政全额补助人员身份)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午04:12:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param czqebzdm
	 * @return
	 * @throws Exception
	 * YlbxwhForm 返回类型 
	 * @throws
	 */
	public YlbxwhForm getCzqebzlxForm(YlbxwhForm t ,String czqebzdm) throws Exception{
		return dao.getCzqebzlxForm(t,czqebzdm);
	}
	
	/**
	 * 
	 * @描述:TODO(保存医疗保险维护)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午06:08:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCbzklxInfo(YlbxwhForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			String cbzkdm = changeCbzklxdm();
			model.setCbzkdm(cbzkdm);
			return dao.addCbzklxInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateCbzklxInfo(model);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @描述:TODO(获得最大序号的学生证补办类型代码)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午08:57:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String changeCbzklxdm() {
		String max = dao.getMaxCbzklxdm();
		if(Base.isNull(max)){
			return "001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	
	/**
	 * 
	 * @描述:TODO(获取参保状况)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-7 上午09:58:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param czqebzdm
	 * @return
	 * @throws Exception
	 * YlbxwhForm 返回类型 
	 * @throws
	 */
	public YlbxwhForm getCbzklxForm(YlbxwhForm t ,String cbzkdm) throws Exception{
		return dao.getCbzklxForm(t,cbzkdm);
	}
	
	
	/**
	 * 
	 * @描述:TODO(删除大学生医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:11:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteCzqebzlxwhInfo(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getCzqebzdm(str);
				noDel.append(hm.get("czqebzmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?czqebzlxwhDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:TODO(删除大学生医疗保险类型维护)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-20 下午02:04:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int czqebzlxwhDelete(String[] czqebzdm) throws Exception {
		return runDelete(czqebzdm);
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(删除大学生医疗保险参保状况类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:11:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] delCbzklxwhInfo(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.cbzkdmIsCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getCbzkdm(str);
				noDel.append(hm.get("cbzkmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?cbzklxwhDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:TODO(删除大学生医疗保险参保状况类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-20 下午02:04:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int cbzklxwhDelete(String[] czqebzdm) throws Exception {
		
		return dao.deleteCbzklxb(czqebzdm);
	}
	
}
