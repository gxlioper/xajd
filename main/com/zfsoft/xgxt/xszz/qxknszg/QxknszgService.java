/**
 * @部门:学工产品事业部
 * @日期：2016-4-20 下午06:27:43 
 */  
package com.zfsoft.xgxt.xszz.qxknszg;


import xgxt.form.User;
import xgxt.utils.GetTime;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-4-26 上午10:13:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 * 
 */
public class QxknszgService extends SuperServiceImpl<QxknszgForm, QxknszgDao> {
	
	private QxknszgDao dao = new QxknszgDao();
	public static final String SAVE = "save";
	public static final String ONE = "one";
	public static final String MORE = "more";

	
	public QxknszgService(){
		super.setDao(dao);		
	}
	
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-26 上午10:24:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelKnsrdzg(QxknszgForm model,User user) throws Exception{
		 
		boolean flag = false;	
		model.setCzr(user.getUserName());
		model.setCzsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));	
		if(ONE.equals(model.getQxtype())){			
			flag = dao.cancelOneKnsrdzg(model);			
		}else if(MORE.equals(model.getQxtype())){			
			flag = dao.cancelMoreKnsrdzg(model);			
		}else{
			flag = dao.cancelAllKnsrdzg(model,user);			
		}
		return flag;
		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-26 上午10:24:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getCountNum(QxknszgForm model,User user)throws Exception {
		
		return dao.getCountNum(model,user);	
		
	}
		
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-27 上午09:25:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveKnsqxjlupdate(QxknszgForm model) throws Exception {	
		
		boolean flag=true;	
		if(SAVE.equals(model.getType())){			
			//设置返校状态为返校		
			boolean updateResult = super.runUpdate(model);
			flag = updateResult;
			
		}else{					
			boolean insertResult = super.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}				
		}		
		return flag;
		
	}
	
	

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-27 上午09:25:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByKnsqxjl(QxknszgForm model,String type)
	throws Exception {
		
		boolean flag = false;
		if("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForKnsqxjlSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.checkExistForQxknszgUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}	
		return flag;
		
	}

}
