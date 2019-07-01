package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjcsz;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import xgxt.utils.GetTime;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 大学生医疗保险参数设置模块
 * @类功能描述: TODO(大学生医疗保险基础设置) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-6 上午10:18:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxJcszService extends SuperServiceImpl<YlbxJcszForm, YlbxJcszDao>{
	
	private YlbxJcszDao dao = new YlbxJcszDao();
	
	public YlbxJcszService() {
		super.setDao(dao);
	}
	
	/**
	 * 查询基础设置信息
	 */
	public YlbxJcszForm getModel(YlbxJcszForm t)throws Exception{
		
		return dao.getModel();
		
	}
	
	/**
	 * 
	 * @描述:TODO(查询基础设置信息(无参数))
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 上午10:18:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * YlbxJcszForm 返回类型 
	 * @throws
	 */
    public YlbxJcszForm getModel()throws Exception{
		
		return getModel(new YlbxJcszForm());
	}
	
    /**
     * 
     * @描述:TODO(保存参数设置信息)
     * @作者：Dlq[工号：995]
     * @日期：2014-1-6 上午10:18:14
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param model
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
	public boolean saveJcsz(YlbxJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
		
	}
}
