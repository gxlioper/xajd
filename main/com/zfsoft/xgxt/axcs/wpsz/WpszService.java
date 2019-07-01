/**
 * @部门:学工产品事业部
 * @日期：2014-12-2 下午06:13:18 
 */
package com.zfsoft.xgxt.axcs.wpsz;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.PicDAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-2 下午06:13:18
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpszService extends SuperServiceImpl<WpszForm, WpszDao> {

	public List<HashMap<String, String>> getPageList(WpszForm model) throws Exception {
		return super.getPageList(model);
	}

	/**
	 * 
	 * @描述:获取物品类别列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-3 下午03:58:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getWplbList() throws Exception {
		return dao.getWplbList();
	}
	
	/**
	 * 
	 * @描述:获取物品名称列表
	 * @作者：cq [工号：785]
	 * @日期：2014-12-10 下午02:38:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getWpmcList(String xn) throws Exception {
		return dao.getWpmcList(xn);
	}
	
	public List<HashMap<String, String>> getTjpzList() throws Exception {
		return dao.getTjpzList();
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:添加物品
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-4 上午09:10:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return String 返回类型
	 * @throws
	 */
	public boolean addWp(WpszForm model) throws Exception {
		return dao.runInsert(model);
	}

	/**
	 * 
	 * @描述:物品基本设置保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-4 上午10:58:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean bcWpJbsz(WpszForm model) throws Exception {
		if("0".equals(model.getSqkg())&&"0".equals(model.getShkg())){
			model.setJbsz("0");
		}
		else{
		   model.setJbsz("1");	
		}
		return dao.runUpdate(model);
	}
	
	public boolean bcWpTjsz(WpszForm model,HttpServletRequest request) throws Exception {
		CommDAO commDao = new CommDAO();
		String[] dcdm=request.getParameterValues("tjsz");
		String tjzt = null;
		if(dcdm==null){
			dcdm=new String[0];
			tjzt="0";
			
		}else{
			tjzt="1";
		}
		dao.updateWpTjZt(model.getXmdm(), tjzt);
		int tjlen=0;
		for(int i=0;i<dcdm.length;i++){
			String[] tjzdArray = request.getParameterValues("tjz_"+dcdm[i]);
			if(tjzdArray==null){
				tjzdArray=new String[0];
			}
			tjlen+=tjzdArray.length;
		}
		String[] sqls=new String[1+tjlen];
		sqls[0]="delete from XG_AXJZ_AXCSXMTJB where xmdm='"+model.getXmdm()+"'";
		String tjzdm=null;
		String tjid=null;
		int k=0;
		for(int i=0;i<dcdm.length;i++){
			String[] tjzdArray = request.getParameterValues("tjz_"+dcdm[i]);
			if(tjzdArray==null){
				tjzdArray=new String[0];
			}
			
			for (int j = 0; j < tjzdArray.length; j++) {
			    tjid = StringUtils.getGuid();
				tjzdm=Base.chgNull(tjzdArray[j],"",0);
				sqls[1+k]="insert into XG_AXJZ_AXCSXMTJB values('"+tjid+"','"+model.getXmdm()+"','"+dcdm[i]+"','"+tjzdm+"')";
				k++;
			}
			
		}
		boolean flag =commDao.saveArrDate(sqls);
		return flag;
	}

	/**
	 * 
	 * @描述:物品图片显示
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-3 下午04:47:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return InputStream 返回类型
	 * @throws
	 */
	public InputStream getPhotoStream(String xmdm) {
		if (StringUtil.isNull(xmdm)) {
			logger.error("xmdm is null !");
			throw new NullPointerException();
		}
		InputStream is = dao.getPhotoStream(xmdm);
		return is;
	}

	public String saveWpPic(WpszForm model, User user) {
		PicDAO picDao = new PicDAO();
		String xmdm = model.getXmdm();
		FormFile file = model.getXmtp();
		String fileName = file.getFileName();

		boolean isAllowType = fileName.endsWith("jpg") || fileName.endsWith("gif") || fileName.endsWith("png") || fileName.endsWith("bmp") || fileName.endsWith("JPG") || fileName.endsWith("GIF")
				|| fileName.endsWith("PNG") || fileName.endsWith("BMP");

		if (!isAllowType) {
			return "上传失败，非法的文件格式！";
		}
		if (file.getFileSize() > 1024 * 1024) {
			return "上传失败，文件大小超过1M！";
		} else {
			try {
				picDao.saveWpPic(file.getInputStream(), xmdm);
				return "上传成功！";
			} catch (Exception e) {
				e.printStackTrace();
				return "上传失败，请重新上传！";
			}
		}
	}

	public String getCurrTime(String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(new Date());
	}
	/**
	 * 判断是否有申请记录
	 */
	public boolean isHaveSqJl(String values) throws Exception {
		return dao.isHaveSqJl(values);
	}
	/**
	 * 获取可复制学年
	 */
	public List<HashMap<String, String>> getFzXnList(String xn) {
		return dao.getFzXnList(xn);
	}
	/**
	 * 
	 * @描述:保存物品复制
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-5 下午01:44:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fzxn
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean wpFz(String fzxn) throws Exception {
		boolean flag = false;
		String currXn = Base.currXn; //当前学年
		List<HashMap<String, String>> wpList = dao.getKfzWpList(fzxn);
		if (wpList != null && wpList.size() > 0) {
			flag = dao.saveData(wpList, currXn);
		}
		return flag;
	}
	public HashMap<String,String> getWpxxByXmdm(String xmdm) throws Exception {
		return dao.getWpxxByXmdm(xmdm);
		
	}
	/**
	 * 
	 * @描述:获取物品条件List
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-11 下午01:02:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getWpTjList(String xmdm) throws Exception {
		return dao.getWpTjList(xmdm);
		
	}
}
