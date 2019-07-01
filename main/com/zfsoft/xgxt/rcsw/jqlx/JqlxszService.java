/**
 * @部门:学工产品事业部
 * @日期：2013-12-30 下午03:55:28 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 假期留校设置
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-30 下午03:55:28
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JqlxszService extends SuperServiceImpl<JqlxszModel, JqlxszDao> {

	private JqlxszDao dao = new JqlxszDao();

	public JqlxszService() {
		super.setDao(dao);
	}

	/**
	 * 查询基础设置信息
	 */
	public JqlxszModel getModel(JqlxszModel t) throws Exception {

		return dao.getModel();
	}

	/**
	 * 
	 * @描述:得到实体
	 * @作者：945
	 * @日期：2013-12-26 上午09:20:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             XszbbJcszForm 返回类型
	 * @throws
	 */
	public JqlxszModel getModel() throws Exception {
		return getModel(new JqlxszModel());
	}
	
	/**
	 * 查询基础设置信息（申请、结果【专用】）
	 */
	public JqlxszModel getJqlxszModelSqJg() throws Exception {
		JqlxszModel jqlxszModel = dao.getModel();
		if(jqlxszModel == null){
			jqlxszModel = new JqlxszModel();
		}
		String lxkssj = jqlxszModel.getLxkssj();
		String lxjssj = jqlxszModel.getLxjssj();
		if(!StringUtil.isNull(lxkssj) && !lxkssj.contains("-")){
			lxkssj = lxkssj.substring(0, 4) + "-" + lxkssj.substring(4, 6) + "-" + lxkssj.substring(6, 8);
		}
		if(!StringUtil.isNull(lxjssj) && !lxjssj.contains("-")){
			lxjssj = lxjssj.substring(0, 4) + "-" + lxjssj.substring(4, 6) + "-" + lxjssj.substring(6, 8);
		}
		jqlxszModel.setLxkssj(lxkssj);
		jqlxszModel.setLxjssj(lxjssj);
		return jqlxszModel;
	}

	/**
	 * 
	 * @描述:保存参数设置信息
	 * @作者：945
	 * @日期：2013-12-26 上午09:21:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveJcsz(JqlxszModel model) throws Exception {
		boolean flag = false;
		flag = dao.deleteJqlxsz(model);
		if (flag) {
			flag = dao.runInsert(model);
		}
		return flag;
	}

}
