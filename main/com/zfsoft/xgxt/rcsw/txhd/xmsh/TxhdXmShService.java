/**
 * @部门:学工产品事业部
 * @日期：2014-6-26 下午01:57:41 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;
import com.zfsoft.xgxt.rcsw.txhd.xmjg.TxhdXmjgDao;
import com.zfsoft.xgxt.rcsw.txhd.xmjg.TxhdXmjgForm;
import com.zfsoft.xgxt.rcsw.txhd.xmjg.TxhdXmjgService;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszDao;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-6-26 下午01:57:41
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdXmShService extends
		SuperServiceImplExtend<TxhdXmShForm, TxhdXmShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private BaseDbcz dbcz = new BaseDbcz();
	/**
	 * @throws Exception 
	 * 
	 * @描述:审核
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:47:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return boolean 返回类型
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(TxhdXmShForm form, User user) throws Exception {
		// 审核操作Model初始化
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_txhd_xmsh.do");
		model.setTzljsq("rcsw_txhd_xmsq_js.do");
		boolean reuslt = false;
		
		//如果为通过验证人数是否超过上限
		if(Constants.SH_TG.equals(form.getShzt())){
			checkRskz(form.getXmdm(),form.getGwid());
		}
		
		
			String zhzt = shlc.runAuditingNotCommit(model);
			TxhdXmShForm upForm = new TxhdXmShForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(upForm, form.getSqid());
			// 审核状态为通过的往日常行为结果表中保存该条数据
			if (zhzt.equalsIgnoreCase(Constants.SH_TG)) {
				// 获取数据库申请数据
				TxhdXmShForm data = getModel(form);
				TxhdXmjgForm qf = new TxhdXmjgForm();
				TxhdXmjgDao txs = new TxhdXmjgDao();
				// 对应属性复制到结果库
				BeanUtils.copyProperties(qf, StringUtils.formatData(data));
				HashMap<String, String> map=dao.getTxXmxx(data).get(0);
				qf.setXmmc(map.get("xmmc"));
				qf.setHddd(map.get("hddd"));
				qf.setLbdm(map.get("lbdm"));
				qf.setHdkssj(map.get("hdkssj"));
				qf.setHdjssj(map.get("hdjssj"));
				qf.setHdsm(map.get("hdsm"));
				qf.setSjly("1");
				BeanUtils.copyProperties(qf,dao.getTxXmxx(data));
				qf.setSqid(data.getSqid());
				qf.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				txs.runInsertNotCommit(qf);
			}
		
		return reuslt;
	}
	/**
	 * 
	 * @描述:获取团学项目信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-6-26 下午04:58:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTxXmxx(TxhdXmShForm t){
		return dao.getTxXmxx(t);
	}
	@Override
	public TxhdXmShForm getModel(TxhdXmShForm t) throws Exception {
		t = super.getModel(t);
		if(t!=null){
			// 把学期代码转换为学期名称
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		return t;
	}
	/**
	 * 
	 * @描述: 撤销
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-5 下午03:17:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shlc
	 * @param sqid
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean cancel(String shlc, String sqid) throws Exception {
		boolean result = false;
		// 请假申请状态更改
		TxhdXmShForm upForm = new TxhdXmShForm();
		upForm.setSqid(sqid);
		upForm.setShzt(Constants.YW_SHZ);
		TxhdXmjgService jjs = new TxhdXmjgService();
		result = dao.runUpdate(upForm, sqid)
				&& jjs.deleteForSh(sqid) ? true : false;
		upForm = dao.getModel(sqid);
		// 设置代办信息
		dbcz.cancel(upForm.getSqid(), upForm.getSplc());
		return result;
	}
	
	
	/**
	 * @throws Exception  
	 * @描述:检测人数
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 下午06:06:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws 
	 */
	private void checkRskz(String xmdm, String gwid) throws Exception {
		
		TxhdXmszDao txhdXmszDao = new TxhdXmszDao();
		Map<String,String> xmszMap = txhdXmszDao.getInfoById(xmdm);
		
		//审核人数上限、审批序号、审批流程
		String shrssx = xmszMap.get("shrssx");	//审核人数上限
		String xmszXh = xmszMap.get("xh");   // 项目设置审核序号
		String shlc = xmszMap.get("shlc");	//项目设置当中的审核流程
		
		//未设置就不控制
		if (StringUtil.isNull(shrssx)){
			return ;
		}
		
		//获取当前审核岗位序号
		ShlcDao shlcDao = new ShlcDao();
		String dqshXh = shlcDao.getGwxh(shlc,gwid); 
		
		//当前审核级别是否大于等于项目控制级别
		if(Integer.valueOf(dqshXh)>=Integer.valueOf(xmszXh)){
			
			//已通过人数
			String tgrs = dao.getTgrsByXmdm(xmdm, gwid);
			
			if (!(Integer.valueOf(tgrs) < Integer.valueOf(shrssx))){
				
				throw new SystemException(MessageKey.RSKZ_FAIL,tgrs);
			}
		}

	}
	
}
