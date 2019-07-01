/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:59:23 
 */  
package com.zfsoft.xgxt.xpjpy.cpxz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ComputeCxpm;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013-参评小组 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午01:59:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CpxzService extends SuperServiceImpl<CpxzModel, CpxzDao> {
	
	public static final String CPZ_CSH_NJZY = "1";//参评组初始化方式--年级、专业
	public static final String CPZ_CSH_BJ = "2";//参评组初始化方式--班级
	public static final String CPZ_CSH_SY = "3";//参评组初始化方式--书院
	
	private CpxzDao dao = new CpxzDao();

	public CpxzService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @描述: 初始化参评小组（供参数设置调用）
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午03:52:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型
	 * @throws Exception 
	 */
	public void initCpxz(User user) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		String cpzcsh = csszModel.getCpzcsh();
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		//当前周期是否已经有参评小组，有：return , 没有： 初始化
		boolean isHaveCpxz = dao.isHaveCpxz(xn, xq);
		
		if (isHaveCpxz){
			return ;
		}
		
		//删除本周期分数提交记录
		ZcfsDao zcfsDao = new ZcfsDao();
		zcfsDao.delTjjl(xn, xq, user);
		
		if (CPZ_CSH_BJ.equals(cpzcsh)){
			//初始化参评组代码库
			dao.createCpxzByBj();
			//初始化班级与参评组关联
			dao.initCpxzByBj(xn, xq,user);
		} else if(CPZ_CSH_NJZY.equals(cpzcsh)){
			//初始化参评组代码库
			dao.createCpxzByNjZy();
			//初始化班级与参评组关联
			dao.initCpxzByNjzy(xn, xq,user);
		} else if(CPZ_CSH_NJZY.equals(cpzcsh)){
			//初始化参评组代码库
			dao.createCpxzBySy();
			//初始化班级与参评组关联
			dao.initCpxzBySy(xn,xq,user);
		} else {
			dao.createCpxzBySy();
			dao.initCpxzBySy(xn,xq,user);
		}

		//如果学期评奖，添加学年班级与参评组关联
		if(CsszService.getZczq()){
			dao.initXnfsjt(xn);
		}
		
	}
	
	/**
	 * @描述：初始化参评组、参评名单
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月23日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @throws Exception
	 * void 返回类型
	 */
	public boolean initCpzCpmd(User user) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		String cpzcsh = csszModel.getCpzcsh();
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		
		ZcfsDao zcfsDao = new ZcfsDao();
		//将本周期已提交分数提交插入到临时表 
		if(!zcfsDao.bakTjjl(xn, xq, user)){
			logger.error("将本周期已提交分数提交插入到临时表  失败");
			return false;
		}
		//删除本周期分数提交记录
		if(!zcfsDao.delTjjl(xn, xq, user)){
			logger.error("删除本周期分数提交记录 失败");
			return false;
		}
		if (CPZ_CSH_BJ.equals(cpzcsh)){
			//初始化参评组代码库
			if(!dao.createCpxzByBj()){
				logger.error("初始化参评组代码库 失败");
				return false;
			}
			//初始化班级与参评组关联
			if(!dao.initCpxzByBj(xn, xq,user)){
				logger.error("初始化班级与参评组关联 失败");
				return false;
			}
		} else {
			//初始化参评组代码库
			if(!dao.createCpxzByNjZy()){
				logger.error("初始化参评组代码库 失败");
				return false;
			}
			//初始化班级与参评组关联
			if(!dao.initCpxzByNjzy(xn, xq,user)){
				logger.error("初始化班级与参评组关联 失败");
				return false;
			}
		}
		
		//如果学期评奖，添加学年班级与参评组关联
		if(CsszService.getZczq()){
			if(!dao.initXnfsjt(xn)){
				logger.error("添加学年班级与参评组关联 失败");
				return false;
			}
		}
		
		//更新本周期已提交分数记录为取消提交
		if(!zcfsDao.updTjjl()){
			logger.error("添加学年班级与参评组关联 失败");
			return false;
		}
		
		CpmdService cpmdService = new CpmdService();
		cpmdService.init();
		
		//非学年综测，初始化学年评奖人员
		if(CsszService.getZczq()){
			cpmdService.xnInit();
		}
		
		//删除多余分数
		if(!zcfsDao.delDyjl()){
			logger.error("删除多余分数 失败");
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @描述: 初始化参评小组（在参评小组菜单调用）
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午05:18:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param cpzcsh
	 * @throws Exception
	 * void 返回类型
	 */
	public boolean initCpxz(User user , String cpzcsh) throws Exception{
		
		String userType = user.getUserType();
		if (!("admin".equals(userType) || "xx".equals(userType) || "xy".equals(userType))){
			//参评小组初始化，用户权限不足。只能学校或学院用户初始化
			throw new SystemException(MessageKey.PJPY_CPXZCSH_QXBZ);
		}
		

		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		//当前周期是否已经有参评小组，有：更新 , 没有： 初始化
		boolean isHaveCpxz = dao.isHaveCpxz(xn, xq);
		
		if (CPZ_CSH_BJ.equals(cpzcsh)){
			//初始化参评组代码库
			dao.createCpxzByBj();
			
			if (isHaveCpxz){
				//更新提交记录表中班级所对应的参评小组
				dao.updateCpxzByBj(xn, xq, user);
			} else {
				dao.initCpxzByBj(xn, xq,user);
			}
		} else if(CPZ_CSH_NJZY.equals(cpzcsh)) {
			//初始化参评组代码库
			dao.createCpxzByNjZy();
			
			if (isHaveCpxz){
				//更新提交记录表中班级所对应的参评小组
				dao.updateCpxzByNjzy(xn, xq, user);
			} else {
				dao.initCpxzByNjzy(xn, xq,user);
			}
		} else {
			//初始化参评组代码库
			dao.createCpxzBySy();

			if (isHaveCpxz){
				dao.updateCpxzBySy(xn,xq,user);
			} else {
				//初始化班级与参评组关联
				dao.initCpxzBySy(xn,xq,user);
			}
		}

		//重新计算排名
		new Thread(new ComputeCxpm(xn,xq)).start();
		
		return isHaveCpxz;
		
	}


	/** 
	 * @描述:查询班级信息
	 * @作者：cq [工号：785]
	 * @日期：2013-8-7 下午04:38:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjInfo(String ids) {
		
		if (StringUtil.isNull(ids)){
			logger.error("选中班级为空");
			throw new NullPointerException();
		}
		
		String[] id = ids.split(",");
		
		return dao.getBjInfo(id);
	}


	/**
	 * @throws Exception  
	 * @描述:保存排名组设置
	 * @作者：cq [工号：785]
	 * @日期：2013-8-13 上午10:23:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param pmz
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean initCpzsz(String bjdm, String pmz) throws Exception {
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		String[] bjdms = bjdm.split(",");
		
		String cpzdm = dao.getIdByName(pmz);
		boolean haveCpxz = false;
		if(cpzdm == null || cpzdm.equals("")){//生成新的参评组
			cpzdm = UniqID.getInstance().getUniqIDHash();
			haveCpxz = dao.insertCpz(cpzdm,pmz);
		}
		
			//更新班级所对应的代码
		haveCpxz = dao.updateBj(xn,xq,bjdms,cpzdm);
		
		//重新计算排名
		new Thread(new ComputeCxpm(xn,xq)).start();
		
		// TODO 自动生成方法存根
		return haveCpxz;
	}

	public List<HashMap<String,String>> getCpzglyList(CpxzModel model, User user) throws Exception {
		return dao.getCpzglyList(model, user);
	}

	public List<HashMap<String,String>> getGlyList(CpxzModel model, User user)throws Exception {

		return dao.getGlyList(model,user);
	}

	public boolean saveFp(CpxzModel model)throws Exception{
		return dao.savefp(model).length>0;
	}

	public boolean cancelfp(CpxzModel model)throws Exception{
		return dao.cancelFp(model);
	}
}
