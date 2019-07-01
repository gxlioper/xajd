/**
 * @部门:学工产品事业部
 * @日期：2016-5-5 上午09:59:33 
 */  
package xsgzgl.rcsw.wsbz.yy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-5-5 上午09:59:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsbzYyService extends SuperServiceImpl<WsbzYyForm, WsbzYyDao> {
	public boolean AddUpdateYyTimeCheck(){
		return dao.AddUpdateYyTimeCheck();
	}
	
	public boolean updateYyTimeCheck(String sqsj){
	    return dao.updateYyTimeCheck(sqsj);
	}
	
	public String produceHdplDay(){
		return dao.produceHdplDay();
	}
	
	public HashMap<String, String> produceHdplfWeek(){
		return dao.produceHdplfWeek();
	}
	
	public boolean isNowDateHaveYyjl(String xh){
	   return dao.isNowDateHaveYyjl(xh);
	}
	
	//每个学生在 表里最多只有两条记录
	public boolean isEveryXhTwoRecode(String xh){
	   return dao.isEveryXhTwoRecode(xh);
	}
	
	public String getWeekZc(){
		HashMap<String, String> map = this.produceHdplfWeek();
		int dyzc = Integer.parseInt(map.get("zcnum"));
		String dy = map.get("dy");
		String weekofmon  = map.get("weekofmon");
		//返回结果
		String zcstr = "";
		//判断是否在星期五之前
		boolean flag = this.checkPriviousFriday();
		int weeknum = Integer.parseInt(weekofmon);
		if(flag){
			if(weeknum+1>dyzc){
				
				zcstr= (Integer.parseInt(dy)+1 <= 12 ? (Integer.parseInt(dy)+1):1)+""+"月"+"第"+(weekofmon+1)+"周";
			}else{
				zcstr= Integer.parseInt(dy)+""+"月"+"第"+(weeknum+1)+"周";
			}
		}else{
			zcstr= Integer.parseInt(dy)+""+"月"+"第"+(weeknum)+"周";
		}
	    
	    return zcstr;
	}
	
	//是否超过人数上限
	public boolean isNotOverRssx(String fddm,String rs,String yyrq,String hdpl){
		return dao.isNotOverRssx(fddm, rs, yyrq,hdpl);
	}
	
	public List<HashMap<String, String>> getFdmcList(){
		return dao.getFdmcList();
	}
	
	
	public HashMap<String,String> getFdmcInfo(String fddm){
		return dao.getFdmcInfo(fddm);
	}
	
	public String getSyrs(String fddm,String yyrq) throws Exception{
		return dao.getSyrs(fddm, yyrq);
	}
	
	//同一个预约预约日期内，学生不可以申请两次
	public boolean isNotSameTwo(String fddm,String yyrq,String xh) throws Exception{
		return dao.isNotSameTwo(fddm, yyrq,xh);
	}
	
//	public boolean isNotOverRssx(String fddm,String rs,String yyrq,String xh){
//		return dao.isNotOverRssx(fddm, rs, yyrq, xh);
//	}
	
	/**
	 * @描述:判断是否在星期五之前
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-21 下午03:31:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkPriviousFriday(){
	    return dao.checkPriviousFriday();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:更新yyrq字段为空
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-29 下午04:09:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYyrqdaynull(String sqid) throws Exception{
		return dao.updateYyrqdaynull(sqid);
	}
	
	/**
	 * 
	 * @描述:更新yyzc字段为空
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-29 下午04:12:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYyzcnull(String sqid) throws Exception{
		return dao.updateYyzcnull(sqid);
	}
	
	/**
	 * 
	 * @描述: 获取预约周次表List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-27 下午06:47:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYyzcb(){
		List<HashMap<String, String>> yyzcList = dao.getYyzcb();
		for (int i = 0; i < yyzcList.size(); i++) {
			String yyrq = (yyzcList.get(i).get("yyzc").split("至"))[0];
			String compareDay = dao.getPriviousDay(yyrq);
			if(!dao.updateYyTimeCheck(compareDay)){
				yyzcList.remove(i);
				i = i-1;
			}

		}
		return yyzcList;
	}
	
	/**
	 * 
	 * @描述:获取预约日期表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-27 下午06:52:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getYyrqb(){
		List<HashMap<String, String>> yyrqList = dao.getYyrqb();
		for (int i = 0; i < yyrqList.size(); i++) {
			String yyrq = yyrqList.get(i).get("yyrq");
			String compareDay = yyrq;
			if(!dao.updateYyTimeCheck(compareDay)){
				yyrqList.remove(i);
				i = i-1;
			}

		}
		return yyrqList;
	}

	public boolean isExist(String yyrq) {
		// TODO Auto-generated method stub
		return dao.isExist(yyrq);
	}
}
