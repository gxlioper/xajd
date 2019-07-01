/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:36:52 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdlxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 绿色通道
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:36:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdlxwhService extends SuperServiceImpl<LstdlxwhForm, LstdlxwhDao> {
	

		private LstdlxwhDao dao = new LstdlxwhDao();
		public static String _BCZSCID="-1";
		
		public LstdlxwhService() {
			super.setDao(dao);
		}
		
		/**
		 * 
		 * @描述:获取绿色通道类型list
		 * @作者：cq [工号：785]
		 * @日期：2014-11-25 下午05:26:56
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param model
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> 返回类型 
		 * @throws
		 */
		public List<HashMap<String, String>>  getPageList(LstdlxwhForm model) throws Exception{
			return dao.getPageList(model);
		}
		
		/**
		 * 
		 * @描述:获取最大类型代码
		 * @作者：cq [工号：785]
		 * @日期：2014-11-25 下午05:30:52
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @return
		 * String 返回类型 
		 * @throws
		 */
		public String changeLxdm() {
			String max = dao.getMaxLxdm();
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
		 * @描述:增加和修改类型
		 * @作者：cq [工号：785]
		 * @日期：2014-11-25 下午05:31:49
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param model
		 * @param type
		 * @return
		 * @throws Exception
		 * boolean 返回类型 
		 * @throws
		 */
		public boolean saveLxInfo(LstdlxwhForm model,String type) throws Exception{
			boolean result = false;
			if ("add".equals(type)) {
				String lxdm = changeLxdm();
				model.setLxdm(lxdm);
				return dao.addLxInfo(model);
			} else if ("update".equals(type)) {
				return dao.updateLxInfo(model);
			}
			return result;
		}

		/**
		 * 
		 * @描述:修改类型 单个查询
		 * @作者：cq [工号：785]
		 * @日期：2014-11-25 下午05:33:51
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param t
		 * @param xszbblxdm
		 * @return
		 * @throws Exception
		 * XszbblxwhForm 返回类型 
		 * @throws
		 */
		public LstdlxwhForm getLxwhForm(LstdlxwhForm t ,String lxdm) throws Exception{
			return dao.getLxwhForm(t,lxdm);
		}
		

		
		/**
		 * 
		 * @描述:判断类型是否已经被补办申请 和 补办结果应用
		 * @作者：cq [工号：785]
		 * @日期：2014-11-25 下午05:35:27
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param values
		 * @return
		 * @throws Exception
		 * String 返回类型 
		 * @throws
		 */
		public String checkLxdmForsqjg(String values)throws Exception{
	    	String resultLxmc="";
	    	String[] rcswLxmc=dao.checkLxdmForsqjg(values);
	    	for(int i=0;i<rcswLxmc.length;i++){
				if(i==rcswLxmc.length-1){
					resultLxmc+=rcswLxmc[i];
				}else{
					resultLxmc+=rcswLxmc[i]+",";
				}
				
			}
			return resultLxmc;
		}
		
		/**
		 * 
		 * @描述:删除类型
		 * @作者：cq [工号：785]
		 * @日期：2014-11-25 下午05:53:55
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param ids
		 * @return
		 * @throws Exception
		 * String[] 返回类型 
		 * @throws
		 */
		public String[] deleteLxwhInfo(String[] ids) throws Exception{
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
					HashMap<String, String> hm=dao.getLxsq(str);
					noDel.append(hm.get("lxmc"));
					noDel.append(",</br>");
					isHaveNoId=true;
				}
			}
			int i=delId.size()>0?lstdsqDelete(delId.toArray(new String[]{})):0;
			String str=noDel.toString();
			//去除最后多余逗号
			str=isHaveNoId?str:_BCZSCID;
			
			return new String[]{String.valueOf(i),str};
		}


		/**
		 * 
		 * @描述:删除绿色通道类型维护
		 * @作者：cq [工号：785]
		 * @日期：2014-11-26 上午08:42:45
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param lxdm
		 * @return
		 * @throws Exception
		 * int 返回类型 
		 * @throws
		 */
		private int lstdsqDelete(String[] lxdm) throws Exception {
			
			return runDelete(lxdm);
		}

}
