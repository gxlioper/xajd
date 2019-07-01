/**
 * @部门:学工产品事业部
 * @日期：2014-6-24 上午09:40:22 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsq.xs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间：2014-6-25 下午13:08:17
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdXssqService extends
		SuperServiceImpl<TxhdXssqForm, TxhdXssqDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	public static String _BCZSCID = "-1";
	TxhdXssqDao dao = new TxhdXssqDao();

	@Override
	public List<HashMap<String, String>> getPageList(TxhdXssqForm t, User user)
			throws Exception {
		return getHdxx(super.getPageList(t, user));
	}

	public List<HashMap<String, String>> getHdxx(
			List<HashMap<String, String>> list) {
		return list;
	}

	/**
	 * 
	 * @描述:活动申请修改
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-6-25 下午13:08:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean update(TxhdXssqForm model) throws Exception {
			boolean updateResult = super.runUpdate(model);
			if(updateResult && Constants.YW_SHZ.equalsIgnoreCase(model.getShzt())) {
				updateResult = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "rcsw_txhd_xmsq_js.do", "rcsw_txhd_xmsh.do");
			}
			return updateResult;
		}

	/**
	 * 
	 * @描述:保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-06-24下午14:21:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean save(TxhdXssqForm model) throws Exception {
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		return super.runInsert(model);
	}

	/**
	 * 获取数据
	 */
	public List<HashMap<String, String>> getXmsqPageListXs(TxhdXssqForm model,
			User user) throws Exception {
		return dao.getXmsqPageListXs(model, user);
	}

	/**
	 * 
	 * @描述:已申请项目
	 * @作者：夏夏[工号：1104]
	 */
	public List<HashMap<String, String>> getXmsqPageListXsYsq(
			TxhdXssqForm model, User user) throws Exception {
		return dao.getXmsqPageListXsYsq(model, user);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:删除
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-6-27 下午13:08:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             String[] 返回类型 String数组 0为成功删除条数为不能删除的
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception {
		// StringBuffer del=new StringBuffer();
		List<String> delId = new ArrayList<String>();// 可删除的id集合
		List<String> delSqId = new ArrayList<String>();// 对应可删除的申请数据id
		if (null == ids || ids.length <= 0) {
			return null;
		}
		for (String str : ids) {
			delId.add(str);// 记录删除id
			delSqId.add(getModel(str).getSqid());
		}

		int i = delId.size() > 0 ? runDelete(delId.toArray(new String[] {}))
				: 0;
		return new String[] { String.valueOf(i) };
	}

	@Override
	public TxhdXssqForm getModel(TxhdXssqForm t) throws Exception {
		t = super.getModel(t);
		if (t != null) {
			// 把学期代码转换为学期名称
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		return t;
	}
	
	public HashMap<String, String> getCount(String xmdm, String xq, String xn) {
		return dao.getCount(xmdm, xq, xn);
	}

	/**
	 * 
	 * @描述:TODO 保存申请单
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-06-26 下午19:21:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveSq(TxhdXssqForm model) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_WTJ);
		// 获取审批流程
		String splc = dao.getSplcID(model.getXmdm());
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}

	public boolean submitRecord(String sqid, String splc, String xh)
			throws Exception {
		boolean result = false;
		result = shlc.runSubmit(sqid, splc, xh, "rcsw_txhd_xmsq_js.do",
				"rcsw_txhd_xmsh.do");
		return result;
	}

	/**
	 * 
	 * @描述:TODO(提交申请)
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-6-27 上午08:34:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */

	public boolean saveSq(TxhdXssqForm model, String type) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_WTJ);
		// 获取审批流程
		String splc = dao.getSplcID(model.getXmdm());
		model.setSplc(splc);
		// 有审批流的情况设定初始值
		if (splc != null && !"".equals(splc)) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		}
		boolean insertResult = super.runInsert(model);
		if (insertResult) {
			insertResult = shlc.runSubmit(sqid, model.getSplc(), model.getXh(),
					"rcsw_txhd_xmsq_js.do", "rcsw_txhd_xmsh.do");
		}
		return insertResult;
	}

	public boolean updateModel(TxhdXssqForm model) throws Exception {
		return super.runUpdate(model);
	}
	
	/** 
	 * @描述:撤销
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-6-27 下午01:28:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param lcid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}

}
