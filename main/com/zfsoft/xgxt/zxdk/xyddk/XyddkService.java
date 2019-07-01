/**
 * @部门:学工产品事业部
 * @日期：2014-9-25 下午03:28:22 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.SuperAuditService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款-国家助学贷款
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-25 下午03:28:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyddkService extends SuperAuditService<XyddkModel, XyddkDao> {

	private static final String sqsh = "sqsh";
	
	public boolean afterLastAudit(XyddkModel model) {
		
		DkjgDao dkjgDao = new DkjgDao();
		
		model.setSjly(sqsh);
		model.setHtbh(model.getZd3());
		model.setYhmc(model.getZd5());
		model.setLxdh(model.getZd6());
		model.setSplcid(null);
		
		//西安科技大学个性化
		if(Base.xxdm.equals("10704")){
			model.setBldkrq(model.getZd8());
			model.setHtdd(model.getZd10());
			model.setXyjbr(model.getZd2());
			if(!StringUtil.isNull(model.getZd7())){				
				model.setDkje(model.getZd7());
			}
			if(!StringUtil.isNull(model.getZd9())){				
				model.setDkqx(model.getZd9());
			}
		}
		
		try {
			dao.runUpdate(model);
			//插入结果库之前，先删除结果库中同学年同学号的数据
			dao.delBeforeShtg(model);
			if("10511".equals(Base.xxdm)){
				boolean flag = dkjgDao.runInsert(model);
				if(flag){
					/**
					 * 审核通过插入结果表
					 */
					flag = this.InsertIntoFdb(model.getId());
				}
				return flag;
			}else{
				return dkjgDao.runInsert(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean deleteResult(XyddkModel model) {
		
		DkjgDao dkjgDao = new DkjgDao();
		
		try {
			return dkjgDao.runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<HashMap<String, String>> getAudingList(XyddkModel t, User user)
		throws Exception {
		
		return dao.getAudingList(t, user);
	}


	/** 
	 * @描述:根据ID查询助学贷款信息
	 * @作者：cq [工号：785]
	 * @日期：2014-12-25 下午03:07:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getDkxxSq(String id) {
		
		return dao.getDkxxSq(id);
	}
	
	
	/**
	 * 
	 * @描述: 按学号、学年查询记录总数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-3-23 下午05:10:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCountByXhAndXn(XyddkModel t){
		
		return dao.getCountByXhAndXn(t);
	}
	
	/**
	 * 
	 * @描述:查询学生已贷款期限
	 * @作者：cq [工号：785]
	 * @日期：2015-11-26 下午03:48:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getYdkqx(String xh){
		return dao.getYdkqx(xh);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-15 上午11:42:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * booleanf 返回类型 
	 * @throws
	 */
	public boolean delBeforeShtg(XyddkModel model) throws Exception{
		return dao.delBeforeShtg(model);
	}
	
	/**
	 * 
	 * @描述: 查询上级流程信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-22 下午03:07:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @param gwid
	 * @param splc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getShlcInfo(String sqid,String gwid,String splc) {
		return dao.getShlcInfo(sqid, gwid, splc);
	}
	
	/**
	 * 
	 * @描述: 获取学生信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-8 下午04:36:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXsxxByHsd(String xh){
		return dao.getXsxxByHsd(xh);
	}
	
	/**
	 * 
	 * @描述: 是否存在
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 上午11:19:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public boolean  getWjmSameRs(String xh,String gid){
		return dao.getWjmSameRs(xh, gid);
	}
	
	/**
	 * 
	 * @描述: 检验是否存在相同学年的重复记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 下午02:10:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xns
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getXnXhSameRs(String[] xns,String xh,String id){
		return dao.getXnXhSameRs(xns, xh,id);
	}
	
	/**
	 * 
	 * @描述: 批量保存xg_hsd_zxdk_ndkb
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 下午03:26:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
    public boolean saveRsBatch(HashMap<String, String[]> para,String xh,String id){
    	String[] xns = para.get("xns");
    	String[] nzsfdks = para.get("nzsfdks");
    	String[] nxfdks = para.get("nxfdks");
    	String[] nshfdks = para.get("nshfdks");
    	String[] nzsfyjes = para.get("nzsfyjes");
    	String[] nxfyjes = para.get("nxfyjes");
    	List<String[]> parameter = new ArrayList<String[]>();
    	for (int i = 0; i < xns.length; i++) {
    		/**
    		 * 第一年默认为新贷，后几年默认为续贷
    		 */
    		String dkzt = "xud";
    		if(i == 0){
    			dkzt = "xind";
    		}
    		parameter.add(new String[]{id,xh,xns[i],nzsfdks[i],nxfdks[i],nshfdks[i],nzsfyjes[i],nxfyjes[i],dkzt});
		}
    	return dao.saveRsBatch(parameter);
	}
    
    /**
	 * @throws Exception 
	 * 
	 * @描述: 根据删除xg_hsd_zxdk_ndkb中的记录，修改时使用
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-11 上午10:13:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delRs(String id) throws Exception{
		return dao.delRs(id);
	}
	
	/**
	 * 
	 * @描述: 修改时判断是否存在
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 上午11:19:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public boolean  getWjmSameRsUpdate(String xh,String gid){
		return dao.getWjmSameRsUpdate(xh, gid);
	}
	
	/**
	 * 
	 * @描述: 根据id查找该记录对应的贷款金额记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-11 下午05:19:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getNdkbList(String id){
		return dao.getNdkbList(id);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 华师大根据id删除年贷款表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-14 上午09:51:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delNdkb(String[] ids) throws Exception{
		return dao.delNdkb(ids);
	}
	
	/**
	 * 
	 * @描述: 检测表单里面文件名是否有相同
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-14 下午03:04:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public boolean checkWjmIsSame(String gid){
		return dao.checkWjmIsSame(gid);
	}
	
	/**
	 * 
	 * @描述: 删除放贷表数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-15 下午06:59:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delFdb(String[] ids) throws Exception{
		return dao.delFdb(ids);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 插入放贷表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-15 下午07:09:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean InsertIntoFdb(String id) throws Exception{
		return dao.InsertIntoFdb(id);
	}
	
	/**
	 * 
	 * @描述: 验证本学年是否可申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-5-31 下午04:42:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIfKsqCurrXn(String xn,String xh){
		return dao.checkIfKsqCurrXn(xn,xh);
		
	}
	
}
