package com.zfsoft.xgxt.rcsw.ylbx.ylbxjcsz;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 医疗保险基础设置
 */
public class YlbxjcszService extends SuperServiceImpl<YlbxjcszForm, YlbxjcszDao>{
	
	private YlbxjcszDao dao = new YlbxjcszDao();
	
	public YlbxjcszService() {
		super.setDao(dao);
	}
	
	/**
	 * 查询基础设置信息
	 */
	public YlbxjcszForm getModel(YlbxjcszForm t)throws Exception{
		return dao.getModel();
	}
	
	/**
	 * 查询基础设置信息(无参数)
	 */
    public YlbxjcszForm getModel()throws Exception{
		return getModel(new YlbxjcszForm());
	}
	
    /**
     * 保存参数设置信息
     * @param model
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
	public boolean saveJcsz(YlbxjcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
	}
}
