package com.zfsoft.xgxt.rcsw.xszbb.xszbbjcsz;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import xgxt.utils.GetTime;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办模块
 * @类功能描述: TODO(学生证补办基础设置) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-16 下午02:55:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbbJcszService extends SuperServiceImpl<XszbbJcszForm, XszbbJcszDao>{
	
	private XszbbJcszDao dao = new XszbbJcszDao();
	
	public XszbbJcszService() {
		super.setDao(dao);
	}
	
	/**
	 * 查询基础设置信息
	 */
	public XszbbJcszForm getModel(XszbbJcszForm t)throws Exception{
		
		XszbbJcszForm  model= dao.getModel();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
//		String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
//		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
//		
//		if (model != null) {
//			
//			if (Constants.CLOSE.equals(model.getSqkg())){
//				model.setIsopen("false");
//			} else if (!StringUtil.isNull(model.getSqkssj())&&!StringUtil.isNull(model.getSqjssj())){
//				
//				Date startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
//				Date endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
//				
//				if(now.before(startTime)||now.after(endTime)){
//					model.setIsopen("false");
//				} else{
//					model.setIsopen("true");
//				}
//					
//			} else if (StringUtil.isNull(model.getSqkssj())&&!StringUtil.isNull(model.getSqjssj())){
//				
//				Date endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
//				if(now.after(endTime)){
//					model.setIsopen("false");
//				} else{
//					model.setIsopen("true");
//				}
//				
//			} else if (!StringUtil.isNull(model.getSqkssj())&&StringUtil.isNull(model.getSqjssj())){
//				Date startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
//				if(now.before(startTime)){
//					model.setIsopen("false");
//				} else{
//					model.setIsopen("true");
//				}
//			}else {
//				model.setIsopen("true");
//			}
//		}
		return model;
	}
	

//	if (model != null) {
//		if (Constants.CLOSE.equals(model.getSqkg())){
//			model.setIsopen("false");
//		} else if (!StringUtil.isNull(model.getSqjssj())){
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
//			Date startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
//			Date endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
//			
//			String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
//			Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
//			
//			if (now.before(startTime) || now.after(endTime)){
//				model.setIsopen("false");
//			} else {
//				model.setIsopen("true");
//			}
//		} else {
//			model.setIsopen("true");
//		}
//	}
//	return model;
	
	/**
	 * 
	 * @描述:TODO(查询基础设置信息(无参数))
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:20:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * XszbbJcszForm 返回类型 
	 * @throws
	 */
    public XszbbJcszForm getModel()throws Exception{
		
		return getModel(new XszbbJcszForm());
	}
	
    /**
     * 
     * @描述:TODO(保存参数设置信息)
     * @作者：Dlq[工号：995]
     * @日期：2013-12-26 上午09:21:10
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param model
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
	public boolean saveJcsz(XszbbJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
		
	}
}
