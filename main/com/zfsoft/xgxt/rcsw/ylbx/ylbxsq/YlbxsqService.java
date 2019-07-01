package com.zfsoft.xgxt.rcsw.ylbx.ylbxsq;

import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class YlbxsqService extends SuperServiceImpl<YlbxsqForm, YlbxsqDao> {

	private YlbxsqDao dao = new YlbxsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";

	public YlbxsqService() {
		super.setDao(dao);
	}
	
	public HashMap<String, String> viewOneYlbxsq(String sqid){
		return dao.viewOneYlbxsq(sqid);
	}
	
	/**
	 * @描述:增加医疗保险申请
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveYlbxsq(YlbxsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		// 获取审批流程
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if(SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(),splc,model.getXh(),"rcsw_ylbx_ylbxsh.do","rcsw_ylbx_ylbxsq.do");
		}
		return result;
	}
	
	/**
	 * @描述:修改医疗保险申请
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYlbxsq(YlbxsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取新审批流程
			model.setSplc(dao.getShlcID());
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = true;
		if (insertResult && SUBMIT.equals(model.getType())) {
			shlc.deleteShjl(model.getSqid());
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"rcsw_ylbx_ylbxsh.do","rcsw_ylbx_ylbxsq.do");
		}
		return insertResult && result;
	}
	
	/**
	 * @描述:提交医疗保险申请
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitYlbxsq(YlbxsqForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取新审批流程
			model.setSplc(dao.getShlcID());
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultYlbxsq = dao.updateYlbxsq(model);
		boolean result = false;
		if(resultYlbxsq){
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"rcsw_ylbx_ylbxsh.do","rcsw_ylbx_ylbxsq.do");
		}
		return result;
	}
	
	/**
	 * @描述:更新医疗保险申请
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelYlbxsq(YlbxsqForm model) throws Exception {
		boolean resultYlbxsq = dao.updateYlbxsq(model);
		return resultYlbxsq;
	}
	
	/**
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	/**
	 * @描述:是否已经存在
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(YlbxsqForm model) throws Exception {
		boolean flag = false;
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	/** 
	 * 导出
	 */
	public List<HashMap<String, String>> getExportAllList(YlbxsqForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getExportAllList(t, user);
	}
	
	/**
	 * 
	 * @描述: 取申请表中的医保号字段
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-5 下午03:08:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getYbh(String sqid) {		
		return dao.getYbh(sqid);
	}

	/** 
	 * @描述:获取保险类型list(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-14 下午04:00:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBxlxList() {
		return dao.getBxlxList();
	}

	/** 
	 * @描述:学生照片导出(苏州卫生)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-15 上午10:29:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param response
	 * @param user
	 * void 返回类型 
	 * @throws 
	 */
	public void zpdc(List<Map<String, String>> list, HttpServletResponse response) {
		ZipOutputStream zos = null;
		DAO dao=DAO.getInstance();
		try {
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition",
					"attachment; filename=image.zip");
			zos = new ZipOutputStream(response.getOutputStream());

			for (int i = 0; i < list.size(); i++) {
				if (!Base.isNull(list.get(i).get("xh"))) {
					//查询出学生相片BLOB
					Blob blob = getXszp(list.get(i).get("xh"), dao);
					if(null!=blob){
						InputStream in2 = blob.getBinaryStream();
						//照片命名
						String zpmc=list.get(i).get("sfzh");
						if(StringUtil.isNull(zpmc)){
							zpmc = list.get(i).get("xh")+"_该生无身份证号";
						}
						//输出相片流
						ZipEntry ze = new ZipEntry(zpmc + ".jpg");
						zos.putNextEntry(ze);
						zos.setEncoding("gbk"); //以中文命名时必须转码，否则乱码
						int blobsize = (int) blob.length();
						byte bu[] = new byte[blobsize];
						int bytesRead = 0;
						
						while ((bytesRead = in2.read(bu)) != -1) {
							zos.write(bu, 0, bytesRead);
						}
						in2.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Blob getXszp(String xh,DAO dao){
			Blob blob=dao.getOneBlob("select zp from xszpb where xh=? ", new String[]{xh}, "zp");
			return blob;
	}

	/** 
	 * @描述:获取照片打印时需要的信息(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-15 上午11:01:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getZpDyxx(String sqid) {
		return dao.getZpDyxx(sqid);
	}
}
