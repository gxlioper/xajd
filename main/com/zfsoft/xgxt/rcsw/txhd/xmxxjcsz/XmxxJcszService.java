package com.zfsoft.xgxt.rcsw.txhd.xmxxjcsz;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 基础设置
 */
public class XmxxJcszService extends SuperServiceImpl<XmxxJcszForm, XmxxJcszDao>{
	
	private XmxxJcszDao dao = new XmxxJcszDao();
	
	public XmxxJcszService() {
		super.setDao(dao);
	}
	
	/**
	 * 查询基础设置信息
	 */
	public XmxxJcszForm getModel(XmxxJcszForm t)throws Exception{
		return dao.getModel();
	}
	
	/**
	 * 查询基础设置信息(无参数)
	 */
    public XmxxJcszForm getModel()throws Exception{
		return getModel(new XmxxJcszForm());
	}
	
    /**
     * 保存参数设置信息
     */
	public boolean saveJcsz(XmxxJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
	}
}
