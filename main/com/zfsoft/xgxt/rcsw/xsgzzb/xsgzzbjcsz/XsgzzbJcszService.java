package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjcsz;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import xgxt.utils.GetTime;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 周报基础设置
 */
public class XsgzzbJcszService extends SuperServiceImpl<XsgzzbJcszForm, XsgzzbJcszDao>{
	
	private XsgzzbJcszDao dao = new XsgzzbJcszDao();
	
	public XsgzzbJcszService() {
		super.setDao(dao);
	}
	
	/**
	 * 查询基础设置信息
	 */
	public XsgzzbJcszForm getModel(String gzzblx)throws Exception{
		return dao.getModel(gzzblx);
	}
	
    /**
     * 保存参数设置信息
     * @param model
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
	public boolean saveJcsz(XsgzzbJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			dao.setTableInfo(model);// 动态取表
			flag=dao.runInsert(model);
		}
		return flag;
	}
}
