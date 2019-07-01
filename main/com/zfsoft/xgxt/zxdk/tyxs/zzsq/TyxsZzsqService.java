/**
 * @部门:学工产品事业部
 * @日期：2015-4-8 上午11:56:10 
 */
package com.zfsoft.xgxt.zxdk.tyxs.zzsq;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.axcs.wpjg.WpjgForm;
import com.zfsoft.xgxt.axcs.wpjg.WpjgService;
import com.zfsoft.xgxt.axcs.wpsh.WpshForm;
import com.zfsoft.xgxt.axcs.wpsq.WpsqDao;
import com.zfsoft.xgxt.axcs.wpsq.WpsqForm;
import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.xyddk.XyddkDao;
import com.zfsoft.xgxt.zxdk.xyddk.XyddkModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 退役学生资助管理模块
 * @类功能描述: 资助申请审核
 * @作者：冯兰英[工号：1177]
 * @时间： 2015-4-8 上午11:56:10
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TyxsZzsqService extends SuperAuditService<TyxsZzsq, TyxsZzsqDao>
		implements Constants {
	/**
	 * 
	 * @描述:修改申请
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-9 下午04:18:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateZzsq(TyxsZzsq t) {

		try {
			dao.runUpdate(t);
			return dao.runInsert(t);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 
	 * @描述:根据id删除
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午04:01:00
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	public boolean delZxsq(TyxsZzsq t) {
		try {
			return dao.runDelete(new String[] { t.getId() }) > 0;
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @描述:高级查询获取审核列表
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午04:00:23
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getAudingList(TyxsZzsq t, User user) {

		try {
			return dao.getShList(t, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return null;
		}
	}

	public HashMap<String, String> getSqxxByID(String id) {
		return dao.getSqxxByID(id);
	}
	/**
	 * 
	 * @描述:添加申请
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-21 上午11:52:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addSq(TyxsZzsq t) {
		try {
			return dao.runInsert(t);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @throws 保存申请	 *             
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-14 下午01:36:46
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	public boolean saveSqDraft(TyxsZzsq t) {

		// 查看该学生是否已经申请过
		HashMap<String, String> map = dao.getZzsqByXh(t);
		try {
			if (map == null) {
				return dao.runInsert(t);
			} else {
				return dao.runUpdate(t, map.get("id"));
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;

		}

	}

	/**
	 * @描述:根据学好和学年获取记录总数
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-14 下午04:46:02
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	public String getCountByXhAndXn(TyxsZzsq t) {
		return dao.getCountByXhAndXn(t);

	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com
	 * .zfsoft.xgxt.base.model.SuperAuditModel)
	 */

	@Override
	public boolean afterLastAudit(TyxsZzsq model) {
		// TODO 自动生成方法存根

		return false;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft
	 * .xgxt.base.model.SuperAuditModel)
	 */

	@Override
	public boolean deleteResult(TyxsZzsq model) {
		// TODO 自动生成方法存根
		return false;
	}
	
	/**
	 * 
	 * @描述:获取银行List
	 * @作者 ChenQ[工号：856]
	 * @日期：2015-9-6 下午02:32:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYhList(){
		return dao.getYhList();
	}
	/*华师大保存年度金额*/
	public boolean saveNdJeHsd(String[] nds,String[] jes,String id) throws Exception{
		return dao.saveNdJeHsd(nds, jes, id);
	}
	
	/*华师大年度金额删除*/
	public boolean delNdJe(String id) throws Exception{
		return dao.delNdJe(id);
	}
	
	/*年度金额list*/
	public List<HashMap<String, String>> getNdJe(String id){
		return dao.getNdJe(id);
	}
	
	public  String getYhListByYhdm(String yhdm){
		return dao.getYhListByYhdm(yhdm);
	}
	
	public boolean afterLastAuditself(TyxsZzsq model) throws Exception {
		// TODO 自动生成方法存根

		return dao.afterLastAudit(model.getXh(),model.getXn());
	}
}