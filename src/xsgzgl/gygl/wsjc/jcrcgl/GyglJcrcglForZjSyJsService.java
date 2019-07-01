/**
 * @部门:学工产品事业部
 * @日期：2017-5-31 上午11:04:37 
 */  
package xsgzgl.gygl.wsjc.jcrcgl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 公寓管理检查日程service(浙江商业技师个性化) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-5-31 上午11:04:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyglJcrcglForZjSyJsService extends SuperServiceImpl<GyglJcrcglForm, GyglJcrcglForZjSyJsDao>{
	private GyglJcrcglForZjSyJsDao dao = new GyglJcrcglForZjSyJsDao();
	
	/** 
	 * @描述:插入检查日程(浙江商业技师学院)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-31 下午01:41:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws ParseException
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertJcrc(GyglJcrcglForm form) throws Exception{
		List<String> dates = getDates(form);
		return dao.plInsertJc(form, dates.toArray(new String[]{}));		
	}
	
	/** 
	 * @描述:获取起止时间区间(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-31 下午01:42:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws ParseException
	 * List<String> 返回类型 
	 * @throws 
	 */
	private List<String> getDates(GyglJcrcglForm form) throws ParseException{
		String kssj = form.getKssj();
		String jssj = form.getJssj();
		List<String> dates = new ArrayList<String>();
		dates.add(form.getKssj());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date begin = sdf.parse(kssj);
		Date end = sdf.parse(jssj);		
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(begin);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		while(endCalendar.after(beginCalendar)){
			beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
			dates.add(sdf.format(beginCalendar.getTime()));
		}
		return dates;
	}
}
