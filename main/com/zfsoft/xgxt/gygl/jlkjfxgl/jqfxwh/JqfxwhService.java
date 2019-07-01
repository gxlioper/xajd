package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxwh;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.action.Base;
import xgxt.form.User;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz.JqfxjcszForm;
import com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz.JqfxjcszService;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 假期返校管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-14 下午02:39:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JqfxwhService extends SuperServiceImpl<JqfxwhForm, JqfxwhDao> {
	public static String JQFXWWHZT = "0";
	public static String JQFXWHZT = "1";
	
	/**
	 * 
	 * @描述:TODO(返校名称)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-1 上午09:43:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFxmc()throws Exception {
			return dao.getFxmc();
	}
		
	/**
	 * 
	 * @描述:TODO(未返校)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-2 上午11:09:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneFxgljgList(String  xh) {
		    return dao.getOneFxgljgList(xh);
	}
	
	/**
	 * 
	 * @描述:TODO(获取未返校信息记录)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-2 下午03:00:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneWfxxwhjgList(String  xh) {	
		 Map<String, String> newMap =  dao.getOneWfxxwhjgList(xh);
		 
		 //如果该学号的学生未处理
		 if(newMap.size() == 0){
			 newMap.put("fxztmc", "未处理");
		 }
		 if(newMap.get("wfxyy") == null){
			 newMap.put("wfxyy", "");		 	 
		 }
		  return newMap;
	}
	
	/**
	 * 
	 * @描述:TODO(保存返校维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-3 下午01:59:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJqFxwh(JqfxwhForm model) throws Exception {	
		boolean flag=true;
		model.setXq("春".equals(model.getXq())?"01":"02");
		
		model.setFxdm(getFxdm());		
		if(JQFXWWHZT.equals(model.getFxzt())||JQFXWHZT.equals(model.getFxzt())){
			//设置返校状态为返校
			model.setFxzt(JQFXWHZT);
			model.setWfxyy("");//置空未返校原因
			//修改假日未返校维护
			boolean updateResult = dao.runUpdate(model);
			flag = updateResult;
		}else {
			model.setFxzt(JQFXWHZT);	
			boolean insertResult = dao.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}
		 
		/*if(JQFXWWHZT.equals(model.getFxzt())){
			//设置返校状态为返校
			model.setFxzt(JQFXWHZT);
			model.setWfxyy("");//置空未返校原因
			//修改假日未返校维护
			boolean updateResult = dao.runUpdate(model);
			flag = updateResult;
		}else{
			model.setFxzt(JQFXWHZT);	
			boolean insertResult = dao.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}	*/
		return flag;
	}

	

	/**
	 * 
	 * @描述:TODO(保存假期未返校)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-14 下午12:30:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJqWfxwh(JqfxwhForm model) throws Exception {	
		model.setXq("春".equals(model.getXq())?"01":"02");	
		model.setFxdm(getFxdm());
		//假日返校
		boolean flag=true;		
		if(JQFXWWHZT.equals(model.getFxzt())||JQFXWHZT.equals(model.getFxzt())){			
			if(JQFXWHZT.equals(model.getFxzt())){
				model.setFxsj("");
				model.setFxzt(JQFXWWHZT);
			}
			//修改假日未返校维护
			boolean updateResult = super.runUpdate(model);
			flag = updateResult;
		}else{			
			//设置返校状态为返校
			model.setFxzt(JQFXWWHZT);	
			boolean insertResult = super.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}		
		return flag;
	}
		
	/** 
	 * @描述:TODO(获取假期返校基础设置中的返校代码)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-16 下午01:44:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * JqfxjcszForm 返回类型 
	 * @throws 
	 */
	public String getFxdm() throws Exception {
		JqfxjcszService jqfxjcszservice = new JqfxjcszService();
		JqfxjcszForm jcszModel = jqfxjcszservice.getModel();
		String fxdm = jcszModel.getFxdm();
		return fxdm;
	}
	
	/**
	 * 
	 * @描述:TODO(批量获取数据库里面的未返校记录)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-7 上午10:42:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getCountNum(JqfxwhForm model,User user)throws Exception {
		return dao.getCountNum(model,user);
	}
	
	/**
	 * 
	 * @描述:TODO(批量保存假期维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-11 上午09:59:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean plSaveJqwh(JqfxwhForm model,User user) throws Exception {					
		boolean flag = false;
		model.setFxdm(getFxdm());
		String currXn = Base.currXn; //当前学年	
		model.setXn(currXn);
		model.setXq("01".equals(Base.currXq)?"春":"秋");	
		model.setFxzt(JQFXWHZT);
		flag = dao.plSaveJqwh(model,user);		
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(批量保存多个假期返校)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-19 上午11:25:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean pldgSaveJqwh(JqfxwhForm model,User user) throws Exception{
		 
			boolean flag = false;
			model.setFxdm(getFxdm());
			String currXn = Base.currXn; //当前学年	
			model.setXn(currXn);
			model.setXq("01".equals(Base.currXq)?"春":"秋");	
			model.setFxzt(JQFXWHZT);			
				
			flag = dao.pldgSaveJqwh(model,user);		
			return flag;
	 }
	
	/**
	 * 
	 * @描述:TODO(获取常用意见)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-14 下午06:32:14
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
	 * @描述:TODO(保存未返校常用原因)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-15 上午11:20:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gnid
	 * @param shyj
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */  
	public boolean saveCyyy(User user,String[] cyyy){			
			if (cyyy == null || cyyy.length == 0 )
				return false;						
			try {
				boolean b = dao.delCyyy(user);				
				if (b){
					return dao.saveCyyy(user,cyyy);
				}				
				return b;
			} catch (Exception e) {
				return false;
			}
		}
	
	/**
	 * 
	 * @描述:TODO(批量学生假期未返校维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-18 下午05:09:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveplxsJqWfxwh(JqfxwhForm model) throws Exception {
		
		model.setXq("春".equals(model.getXq())?"01":"02");	
		model.setFxdm(getFxdm());		
		//假日返校
		boolean flag=true;
			
		model.setFxdm(getFxdm());
		String currXn = Base.currXn; //当前学年	
		model.setXn(currXn);
		model.setXq("01".equals(Base.currXq)?"春":"秋");	
		model.setFxzt(JQFXWWHZT);		
		dao.pldgSaveJqwfx(model);	
		return flag;
		
	}
}
