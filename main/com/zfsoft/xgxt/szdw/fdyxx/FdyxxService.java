/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午02:47:42 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.PicDAO;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.xgsz.CsszDao;
import com.zfsoft.xgxt.szdw.xgsz.CsszModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XgzdModel;

/** 
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-7-9 下午02:47:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyxxService extends SuperAuditService<FdyxxModel, FdyxxDao> {

	private ShlcInterface shlc = new CommShlcImpl();
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(FdyxxModel model) {
		return false;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	@Override
	public boolean deleteResult(FdyxxModel model) {
		return false;
	}
	
	
	public HashMap<String,String> getFdyInfo(String zgh){
		return dao.getFdyInfo(zgh);
	}
	public HashMap<String,String> getFdyInfo1(String zgh){
		return dao.getFdyInfo1(zgh);
	}
	/**
	 * @throws Exception  
	 * @描述:保存修改申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-7-16 上午09:20:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fdyxxForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public boolean saveXgsq(FdyxxModel fdyxxForm) throws Exception {
		
		boolean result = true;
		if(!Constants.SH_TH.equals(fdyxxForm.getShjg())){
			String guid = UniqID.getInstance().getUniqIDHash();
			guid = guid.toUpperCase();
			fdyxxForm.setSqid(guid);
			fdyxxForm.setShjg(Constants.YW_WTJ);
		}
		
//		dao.deleteShlc(fdyxxForm);
		dao.deleteXgsq(fdyxxForm);
		String xgzd = fdyxxForm.getXgzdJson();
		List<XgzdModel> xgzdList = null;
		if(xgzd != null && !xgzd.equals("")){
			xgzdList = JsonUtil.jsonToList(xgzd, XgzdModel.class);
		}
		
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.insertXgzd(xgzdList, fdyxxForm.getSqid());
		}
		//当审核状态不为【退回】时，才进行插入
		if(!Constants.SH_TH.equals(fdyxxForm.getShjg())){
			dao.insertXgsq(fdyxxForm);	
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public boolean saveTjsq(FdyxxModel model) throws Exception {
		boolean result = true;
		if(!Constants.SH_TH.equals(model.getShjg())){
			String guid = UniqID.getInstance().getUniqIDHash();
			guid = guid.toUpperCase();
			model.setSqid(guid);
			model.setShjg(Constants.YW_SHZ);
		}

		CsszDao csszDao = new CsszDao();
		CsszModel cssz = csszDao.getModel();
		String shlid = cssz.getSplc();
		String xgzd = model.getXgzdJson();
		
		dao.deleteXgsq(model);
		List<XgzdModel> xgzdList = null;
		if(xgzd != null && !xgzd.equals("")){
			xgzdList = JsonUtil.jsonToList(xgzd, XgzdModel.class);
		}
		 
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.insertXgzd(xgzdList, model.getSqid());
		}
//		if (StringUtil.isNull(shlid)  || "wxsh".equals(shlid)) {// 无需审核
//			model.setShjg(Constants.YW_TG);
//			dao.runUpdate(model);
//		} else {
			result = shlc.runSubmit(model.getSqid(), shlid, model.getXh(),"szdw_fdyxx_xgsh.do", "szdw_fdyxx_xgsq.do");
//		}
		if(!Constants.SH_TH.equals(model.getShjg())){		
			dao.insertXgsq(model);	//插入申请记录	
		} else {
			dao.updateShzt(model.getSqid(), Constants.YW_SHZ);  //更新申请表申请状态
		}
		return result;
	}
	
	

	/** 
	 * @描述:修改字段信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-7-16 上午10:24:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXgzdList(String sqid) {
		
		return dao.getXgzdList(sqid);
	}

	public HashMap<String,String> getBmdm(String bmdm){
		return dao.getBmdm(bmdm);
	}
	/** 
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-7-16 上午10:29:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getDshDataByZgh(String zgh) {
		HashMap<String, String> map = dao.getDshByZgh(zgh);
		String sqid = null;
		if (map != null) {
			sqid = map.get("sqid");
		}
		return sqid;
	}

	/** 
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-7-16 上午10:29:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getShzDataByZgh(String zgh) {
		HashMap<String, String> map = dao.getDataByZgh(zgh, Constants.YW_SHZ);
		String sqid = null;
		if (map != null) {
			sqid = map.get("sqid");
		}
		return sqid;
	}

	/** 
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-7-16 上午10:29:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getShxxByZgh(String zgh) {
		return dao.getShxxByXh(zgh);
	}
	
	
	public List<HashMap<String, String>> getWclPageList(FdyxxModel model, User user) throws Exception{
		return dao.getWclPageList(model, user);
	}
	
	
	/*****辅导员信息修改审核*********/
	@SuppressWarnings("rawtypes")
	@TransactionControl
	public boolean savePlsh(ShxxModel model, String dataJson) throws Exception{
		
		List list = JsonUtil.jsonToList("{data:" + dataJson + "}");
		
		for (Object object : list) {
			net.sf.ezmorph.bean.MorphDynaBean bean = (net.sf.ezmorph.bean.MorphDynaBean) object;
			String gwid = (String) bean.get("gwid");
			String ywid = (String) bean.get("ywid");
			String zgh = (String) bean.get("zgh");
			
			model.setGwid(gwid);
			model.setYwid(ywid);
			model.setSqrid(zgh);
			saveXgsh(model);
		}
		
		return true;
	}
	
	
	/*****辅导员信息修改审核*********/
	@SuppressWarnings("unchecked")
	@TransactionControl
	public boolean saveXgsh(ShxxModel model) throws Exception {
		boolean result = true;
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		String shlid = csszModel.getSplc();
		model.setShlc(shlid);
		model.setTzlj("szdw_fdyxx_xgsh.do");
		model.setTzljsq("szdw_fdyxx_xgsq.do");
		String shzt = shlc.runAuditingNotCommit(model);
		if (shzt != null) {
			result = dao.updateShztNotCommit(model.getYwid(), shzt);
			
			if (shzt.equals(Constants.SH_TG)) {
				String sqid = model.getYwid();
				List<HashMap<String,String>> infoList = dao.getXgzdList(sqid);
				FdyxxModel fdyxxModel = new FdyxxModel();
				String zgh = model.getSqrid();
				fdyxxModel.setZgh(zgh);
				boolean flag = false;
				for (HashMap<String,String> map : infoList){
					String property = map.get("zd");
					
					//家庭成员
					if ("jtcyList".equals(property)){
//						List<HjjlModel> hjxxList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",HjjlModel.class);
						List<JtcyModel> jtcyList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",JtcyModel.class);
						dao.updateJtcyxx(zgh,jtcyList);
					} else if ("gwydList".equals(property)){
						//岗位异动
						List<GwydModel> gwydList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",GwydModel.class);
						dao.updateGwydxx(zgh,gwydList);
					} else if ("jtjlList".equals(property)){
						//借调经历
						List<JtjlModel> jtjlList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",JtjlModel.class);
						dao.updateJtjlxx(zgh,jtjlList);
					} else if ("zyjszwList".equals(property)){
						//专业技术职务信息
						List<ZyjsgwModel> zyjszwList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZyjsgwModel.class);
						dao.updateZyjsgwxx(zgh,zyjszwList);
					} else if ("xxjlList".equals(property)){
						//学习经历信息
						List<XxjlModel> xxjlList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",XxjlModel.class);
						dao.updateXxjlxx(zgh,xxjlList);
					} else if ("gzjlList".equals(property)) {
						//工作经历信息
						List<GzjlModel> gzjlList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",GzjlModel.class);
						dao.updateGzjlxx(zgh,gzjlList);
					} else if ("zyzgzsList".equals(property)) {
						//职业资格证书
						List<ZyzgzsModel> zyzgzsList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZyzgzsModel.class);
						dao.deleteZyzgzs(zgh);
						dao.insertZyzgzs(zgh, zyzgzsList);	
					} else if ("zzjcqkList".equals(property)) {
						//著作、教材情况
						List<ZzjcqkModel> zzjcqkList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZzjcqkModel.class);
						dao.deleteZzjcqk(zgh);
						dao.insertZzjcqk(zgh, zzjcqkList);
					} else if ("fmczList".equals(property)) {
						//发明创造等情况
						List<FmczModel> fmczList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",FmczModel.class);
						dao.deleteFmcz(zgh);
						dao.insertFmcz(zgh, fmczList);
					} else if ("jxkyList".equals(property)) {
						//教学科研成果（含竞赛）获奖情况
						List<JxkyModel> jxkyList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",JxkyModel.class);
						dao.deleteJxky(zgh);
						dao.insertJxky(zgh, jxkyList);
					} else if ("fdyxzgzList".equals(property)) {
						//辅导员和行政工作
						List<FdyxzgzModel> fdyxzgzList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",FdyxzgzModel.class);
						dao.deleteFdyxzgz(zgh);
						dao.insertFdyxzgz(zgh, fdyxzgzList);
					} else if ("gzqkList".equals(property)) {
						//工作经历
						List<GzqkModel> gzqkList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",GzqkModel.class);
						dao.deleteGzjl(zgh);
						dao.insertGzjl(zgh, gzqkList);
					} else if ("zypxList".equals(property)) {
						//职业培训
						List<ZypxModel> zypxList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZypxModel.class);
						dao.deleteZypx(zgh);
						dao.insertZypx(zgh, zypxList);
					}else if ("xmxxList".equals(property)) {
						//项目学习
						List<XmxxModel> xmxxList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",XmxxModel.class);
						dao.deleteXmxx(zgh);
						dao.insertXmxx(zgh, xmxxList);
					}else if ("gxjlList".equals(property)) {
						//高校交流
						List<GxjlModel> gxjlList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",GxjlModel.class);
						dao.deleteGxjl(zgh);
						dao.insertGxjl(zgh, gxjlList);
					}else if ("jxqkList".equals(property)) {
						//教学情况
						List<JxqkModel> jxqkList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",JxqkModel.class);
						dao.deleteJxqk(zgh);
						dao.insertJxqk(zgh, jxqkList);
					}else if ("kylwfbList".equals(property)) {
						//科研情况论文发表
						List<KylwfbModel> kylwfbList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",KylwfbModel.class);
						dao.deleteKylwfb(zgh);
						dao.insertKylwfb(zgh, kylwfbList);
					}else if ("kyktfbList".equals(property)) {
						//科研情况论文发表
						List<KyktfbModel> kyktfbList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",KyktfbModel.class);
						dao.deleteKyktfb(zgh);
						dao.insertKyktfb(zgh, kyktfbList);
					}else if ("zgzsList".equals(property)) {
						//资格证书
						List<ZgzsModel> zgzsList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZgzsModel.class);
						dao.deleteZgzs(zgh);
						dao.insertZgzs(zgh, zgzsList);
					}else if ("gryyqkList".equals(property)) {
						//辅导员工作期间所获得的个人荣誉情况
						List<ZxhjqkModel> zxhjqkList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZxhjqkModel.class);
						dao.deleteZxhjqk(zgh);
						dao.insertZxhjqk(zgh, zxhjqkList);
					}else if ("xxryqkList".equals(property)) {
						//辅导员工作期间所获得的个人荣誉情况
						List<BjyyqkModel> bjyyqkList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",BjyyqkModel.class);
						dao.deleteBjyyqk(zgh);
						dao.insertBjyyqk(zgh, bjyyqkList);
					}else {
//						try{
							Method setMethod = fdyxxModel.getClass().getMethod("set"+property.substring(0, 1).toUpperCase()+property.substring(1),String.class);
							setMethod.invoke(fdyxxModel, map.get("zdz"));
							flag = true;
//						}catch(Exception e){
//							logger.error("model中无此字段——>"+map.get("zd"));
//						}
					}
				}
				if(flag){
					result = dao.runUpdateNotCommit(fdyxxModel);
				}
			}
		}
		return result;
	}
	
	
	/**获奖经历***/
	public List<HashMap<String,String>> getHjxxList(String zgh){
		return dao.getHjxxList(zgh);
	}
	
	/**培训经历***/
	public List<HashMap<String,String>> getPxjlList(String zgh){
		return dao.getPxjlList(zgh);
	}
	
	/**其它成果***/
	public List<HashMap<String,String>> getQtcgList(String zgh){
		return dao.getQtcgList(zgh);
	}
	
	/**科研项目***/
	public List<HashMap<String,String>> getKyxmList(String zgh){
		return dao.getKyxmList(zgh);
	}
	
	/**发布论文***/
	public List<HashMap<String,String>> getFblwList(String zgh){
		return dao.getFblwList(zgh);
	}
	
	/**教学工作***/
	public List<HashMap<String,String>> getJxgzList(String zgh){
		return dao.getJxgzList(zgh);
	}
	
	/**职业资格证书***/
	public List<HashMap<String,String>> getZyzgzsList(String zgh){
		return dao.getZyzgzsList(zgh);
	}
	
	/**著作、教材情况***/
	public List<HashMap<String,String>> getZzjcqkList(String zgh){
		return dao.getZzjcqkList(zgh);
	}
	
	/**发明创造等情况***/
	public List<HashMap<String,String>> getFmczList(String zgh){
		return dao.getFmczList(zgh);
	}
	
	/**教学科研成果（含竞赛）获奖情况***/
	public List<HashMap<String,String>> getJxkyList(String zgh){
		return dao.getJxkyList(zgh);
	}

	/**辅导员和行政工作***/
	public List<HashMap<String,String>> getFdyxzgzList(String zgh){
		return dao.getFdyxzgzList(zgh);
	}
	/**辅导员工作经历*/
	public List<HashMap<String,String>> getGzjlList(String zgh){
		return dao.getGzjlList(zgh);
	}
	/**辅导员职业培训*/
	public List<HashMap<String,String>> getZypxList(String zgh){
		return dao.getZypxList(zgh);
	}
	/**辅导员学习信息*/
	public List<HashMap<String,String>> getXmxxList(String zgh){
		return dao.getXmxxList(zgh);
	}
	/**辅导员高校交流*/
	public List<HashMap<String,String>> getGxjlList(String zgh){
		return dao.getGxjlList(zgh);
	}
	/**辅导员教学情况*/
	public List<HashMap<String,String>> getJxqkList(String zgh){
		return dao.getJxqkList(zgh);
	}
	/**辅导员科研论文发表*/
	public List<HashMap<String,String>> getKylwfbList(String zgh){
		return dao.getKylwfbList(zgh);
	}
	/**辅导员科研课题发表*/
	public List<HashMap<String,String>> getKyktfbList(String zgh){
		return dao.getKyktfbList(zgh);
	}
	/**辅导员资格证书*/
	public List<HashMap<String,String>> getZgzsList(String zgh){
		return dao.getZgzsList(zgh);
	}
	/**辅导员工作期间所获得的个人荣誉情况*/
	public List<HashMap<String,String>> getZxhjqkList(String zgh){
		return dao.getZxhjqkList(zgh);
	}
	/**辅导员工作期间所获得的班级荣誉情况*/
	public List<HashMap<String,String>> getBjyyqkList(String zgh){
		return dao.getBjyyqkList(zgh);
	}
	/**
	 * 
	 * @描述: 培训经历修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-16 上午10:17:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updatePxjl(String zgh, List<PxjlModel> list) throws Exception {
		return dao.updatePxjl(zgh, list);
	}
	
	/**
	 * 
	 * @描述: 教学工作修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-16 上午10:17:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateJxgz(String zgh, List<JxgzModel> list) throws Exception {
		return dao.updateJxgz(zgh, list);
	}
	
	/**
	 * 
	 * @描述: 职业资格证书修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-16 上午10:17:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateZyzgzs(String zgh, List<ZyzgzsModel> list) throws Exception {
		return dao.updateZyzgzs(zgh, list);
	}
	
	/**
	 * 
	 * @描述: 著作、教材情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-16 下午04:21:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateZzjcqk(String zgh, List<ZzjcqkModel> list) throws Exception {
		return dao.updateZzjcqk(zgh, list);
	}
	
	/**
	 * 
	 * @描述: 发明创造等情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-17 上午09:26:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateFmcz(String zgh, List<FmczModel> list) throws Exception {
		return dao.updateFmcz(zgh, list);
	}
	
	/**
	 * 
	 * @描述: 教学科研成果（含竞赛）获奖情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-17 下午02:01:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateJxky(String zgh, List<JxkyModel> list) throws Exception {
		return dao.updateJxky(zgh, list);
	}
	
	/**
	 * 
	 * @描述: 获奖情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-17 下午03:20:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateHjjl(String zgh, List<HjjlModel> list) throws Exception {
		return dao.updateHjjl(zgh, list);
	}
	
	/**
	 * 
	 * @描述: 论文发表情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-17 下午04:28:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateFblw(String zgh, List<FblwModel> list) throws Exception {
		return dao.updateFblw(zgh, list);
	}
	
	/**
	 * 
	 * @描述: 其它成果修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-17 下午04:28:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateQtcg(String zgh, List<QtcgModel> list) throws Exception {
		return dao.updateQtcg(zgh, list);
	}
	
	/**
	 * 
	 * @描述: 科研项目情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-18 上午09:03:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateKyxm(String zgh, List<KyxmModel> list) throws Exception {
		return dao.updateKyxm(zgh, list);
	}
	
	/**
	 * 
	 * @描述: 辅导员和行政工作修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-18 上午10:46:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateFdyxzgz(String zgh, List<FdyxzgzModel> list) throws Exception {
		return dao.updateFdyxzgz(zgh, list);
	}
	
	//fdyxxb取值关联代码表
	public HashMap<String, String> getFdyxxMap(String zgh){
		return dao.getFdyxxMap(zgh);
	}
	
	//通用word导出活动教师照片
	public String getPhoto(String zgh) throws Exception{
		PicDAO picDAO = new PicDAO();
	    InputStream is = picDAO.getPic(zgh, "fdy");
	    File photoFile = FileUtil.inputstreamToFile(is, "doc");
	    String photo = FileUtil.getBASE64(photoFile);
	    return photo;
	}
	
	/**
	 * 
	 * @描述:北京中医药大学教师身份保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-19 下午02:05:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zghs
	 * @param jssf
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savejssfPlwh(String[] zghs,String jssf) throws Exception{
		return dao.savejssfPlwh(zghs, jssf);
	}
	
	/** 
	 * @描述:获取带班期间学生荣誉(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-30 下午02:56:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsryList(String zgh) {
		
		return dao.getXsryList(zgh);
	}
	/** 
	 * @描述:辅导员期间个人荣誉(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-30 下午02:56:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGrryList(String zgh) {
		
		return dao.getGrryList(zgh);
	}

	/** 
	 * @描述：科研情况 论文(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-30 下午03:11:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKylwList(String zgh) {
		return dao.getKylwList(zgh);
	}
	/** 
	 * @描述：科研情况课题(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-30 下午03:11:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKyktList(String zgh) {
		return dao.getKyktList(zgh);
	}
	
	
	/** 
	 * @描述：工作经历(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-30 下午03:11:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGzjlList(String zgh,String num) {
		return dao.getGzjlList(zgh,num);
	}

	/**
	 * 空的list
	 */
	public void addKList(List<HashMap<String,String>> list, int size){
		if(list.size()<size){
			for (int i = 0 ; i <= size-list.size() ; i++){
				list.add(new HashMap<String, String>());
			}
		}
	}

	/** 
	 * @描述:带班总人数(这里用一句话描述这个方法的作用)
	 * @作者：linguoxia[工号：1553]
	 * @日期：2018-2-6 下午04:44:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getDbzrs(String zgh) {
		return dao.getDbzrs(zgh);
	}
	//1529 jz
	/**华东交通工作经历*/
	public boolean updateGzjl(String zgh, List<GzqkModel> list) throws Exception {
		return dao.updateGzjl(zgh, list);
	}
	/**华东交通职业培训*/
	public boolean updateZypx(String zgh, List<ZypxModel> list) throws Exception {
		return dao.updateZypx(zgh, list);
	}
	/**华东交通项目学习*/
	public boolean updateXmxx(String zgh, List<XmxxModel> list) throws Exception {
		return dao.updateXmxx(zgh, list);
	}
	/**华东交通高校交流*/
	public boolean updateGxjl(String zgh, List<GxjlModel> list) throws Exception {
		return dao.updateGxjl(zgh, list);
	}
	/**华东交通教学情况*/
	public boolean updateJxqk(String zgh, List<JxqkModel> list) throws Exception {
		return dao.updateJxqk(zgh, list);
	}
	/**华东交通科研论文发表*/
	public boolean updateKylwfb(String zgh, List<KylwfbModel> list) throws Exception {
		return dao.updateKylwfb(zgh, list);
	}
	/**华东交通科研课题发表*/
	public boolean updateKyktfb(String zgh, List<KyktfbModel> list) throws Exception {
		return dao.updateKyktfb(zgh, list);
	}
	/**华东交通资格证书*/
	public boolean updateZgzs(String zgh, List<ZgzsModel> list) throws Exception {
		return dao.updateZgzs(zgh, list);
	}
	/**华东交通辅导员工作期间所获得的个人荣誉情况*/
	public boolean updateZxhjqk(String zgh, List<ZxhjqkModel> list) throws Exception {
		return dao.updateZxhjqk(zgh, list);
	}
	/**华东交通辅导员工作期间所获得的班级同学荣誉情况*/
	public boolean updateBjyyqk(String zgh, List<BjyyqkModel> list) throws Exception {
		return dao.updateBjyyqk(zgh, list);
	}
	//华东交通，照片必填
	public String checkxszpSfcz(String zgh){
		String  zp =  dao.checkxszpSfcz(zgh);
		return "0".equalsIgnoreCase(zp)?"false":"true";
	}
	//查看带班总人数有多少
	public String fdydbrs(String zgh , String zgh1){
		return dao.fdydbrs(zgh, zgh1);
	}
	
	/**
	 * @描述: 判断带班人数是多少，判断是否带班
	 * @作者：  jz[工号：1529]
	 * @日期：2018-2-7 下午02:30:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param zgh1
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String sfdb(String zgh , String zgh1){
		String dbrs = dao.fdydbrs(zgh, zgh1);
		return "0".equalsIgnoreCase(dbrs)?"否":"是";
	}
	//带班名称和带班人数
	public List<HashMap<String, String>> bjmcAbjrs(String zgh , String zgh1){
		 List<HashMap<String, String>> bjmcabjrs = dao.bjmcAbjrs(zgh, zgh1);
		return dao.bjmcAbjrs(zgh, zgh1);
	}
/**
 * @description	： 获得证书
 * @author 		： CP（1352）
 * @date 		：2018-3-9 下午02:00:45
 * @param zgh
 * @return
 */
	public List<HashMap<String,String>> getHdzsList(String zgh) {
		return dao.getHdzsList(zgh);
	}
	public boolean updateHdzs(String zgh, List<HdzsModel> hdzsList) throws Exception {
		return dao.updateHdzs(zgh,hdzsList);
	}

	public boolean updateYjcg(String zgh, List<YjcgModel>  yjcgList) throws Exception {
		return dao.updateYjcg(zgh,yjcgList);
	}

	public List<HashMap<String,String>> getYjcgList(String zgh) {
		return dao.getYjcgList(zgh);
	}

	public boolean updateJtcyxx(String zgh,List<JtcyModel> list) throws Exception {
		return dao.updateJtcyxx(zgh, list);
	}

	public boolean updateGwydxx(String zgh,List<GwydModel> list) throws Exception {
		return dao.updateGwydxx(zgh, list);
	}

	public boolean updateJtjlxx(String zgh,List<JtjlModel> list) throws Exception {
		return dao.updateJtjlxx(zgh, list);
	}

	public boolean updateZyjsgwxx(String zgh,List<ZyjsgwModel> list) throws Exception {
		return dao.updateZyjsgwxx(zgh, list);
	}

	public boolean updateXxjlxx(String zgh,List<XxjlModel> list) throws Exception {
		return dao.updateXxjlxx(zgh, list);
	}

	public boolean updateGzjlxx(String zgh,List<GzjlModel> list) throws Exception {
		return dao.updateGzjlxx(zgh, list);
	}

	public List<HashMap<String,String>> getJtcyxx(String zgh){
		return dao.getJtcyxx(zgh);
	}

	public List<HashMap<String,String>> getGwydxx(String zgh){
		return dao.getGwydxx(zgh);
	}

	public List<HashMap<String,String>> getJtjlxx(String zgh){
		return dao.getJtjlxx(zgh);
	}

	public List<HashMap<String,String>> getZyjsgwxx(String zgh){
		return dao.getZyjsgwxx(zgh);
	}

	public List<HashMap<String,String>> getXxjlxx(String zgh){
		return dao.getXxjlxx(zgh);
	}

	public List<HashMap<String,String>> getGzjlxx(String zgh){
		return dao.getGzjlxx(zgh);
	}

	public List<HashMap<String,String>> getGwxx(String gwdjdm){
		return dao.getGwxx(gwdjdm);
	}
}
