/**
 * @部门:学工产品事业部
 * @日期：2013-8-22 上午08:55:19 
 */  
package com.zfsoft.xgxt.xlzx.zxyycl;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xlzx.yysq.YysqDao;
import com.zfsoft.xgxt.xlzx.yysq.YysqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 咨询预约处理 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-8-22 上午08:55:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxyyclService extends SuperServiceImpl<ZxyyclForm, ZxyyclDao> {
	
	private ZxyyclDao dao = new ZxyyclDao();

	public ZxyyclService() {
		super.setDao(dao);
	}
	
	/** 
	 * @描述:查询咨询信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXlzxInfoByYyId(String id)
	throws Exception {
		
		return dao.getXlzxInfoByYyId(id);
	}
	
	/** 
	 * @描述:选择咨询信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public boolean saveXlzxInfo(ZxyyclForm model)
	throws Exception {
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setId(guid);
		return dao.saveXlzxInfo(model);
	}
	/**
	 * 
	 * @描述:删除咨询信息
	 * @作者：1004
	 * @日期：2013-9-26 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delZxInfoByYyid(String[] yyid) throws Exception {
		return dao.delZxInfoByYyid(yyid);
	}
	
	/** 
	 * @描述:咨询师或管理员新增预约咨询信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public boolean saveYyzxInfo(ZxyyclForm model) throws Exception {
		//创建成功的预约信息
		YysqDao yysqDao = new YysqDao();
		YysqForm yysqModel = new YysqForm();
		
		String guid = UniqID.getInstance().getUniqIDHash();
		yysqModel.setId(guid);
		yysqModel.setYyzxrq(model.getZxrq());
		yysqModel.setStatus("2");
		yysqModel.setXh(model.getXh());
		yysqModel.setZgh(model.getZgh());
		yysqModel.setXstell(model.getXstell());
		Boolean yyflag = yysqDao.saveYysqInfo(yysqModel);
		//创建咨询信息
		String newGuid = UniqID.getInstance().getUniqIDHash();
		model.setId(newGuid);
		model.setYyid(guid);
		Boolean zxflag = dao.saveXlzxInfo(model);
		return yyflag && zxflag;
	}
	/** 
	 * @描述:修改咨询信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public boolean updateXlzxInfo(ZxyyclForm model)
	throws Exception {
		return dao.updateXlzxInfo(model);
	}
	
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-22 上午08:55:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根

	}

}
