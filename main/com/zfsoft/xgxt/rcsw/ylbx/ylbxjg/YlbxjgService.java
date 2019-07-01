/**
 * @部门:学工产品事业部
 * @日期：2015-1-21 上午11:03:03 
 */  
package com.zfsoft.xgxt.rcsw.ylbx.ylbxjg;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
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
import xsgzgl.xsxx.general.xszpgl.XszpglForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxsh.YlbxshForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 医疗保险 
 * @作者： cq [工号:785]
 * @时间： 2015-1-21 上午11:03:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YlbxjgService extends SuperServiceImpl<YlbxjgForm, YlbxjgDao> {
	
	private YlbxjgDao dao = new YlbxjgDao();
	public static String _BCZSCID="-1";
	
	public YlbxjgService() {
		super.setDao(dao);
	}
	public List<HashMap<String, String>> getXjbxmdList(YlbxjgForm t, User user) throws Exception{
		return dao.getXjbxmdList(t,user);
		
	}
	
	/**
	 * 
	 * @描述:判断该学年结果表中是否已经存在该学生
	 * @作者：cq [工号：785]
	 * @日期：2015-1-22 下午04:45:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistBysqjg(YlbxjgForm model)
	throws Exception {
		boolean flag = false;
		// 
		String shzt = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(shzt)) {
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @描述:结果保存
	 * @作者：cq [工号：785]
	 * @日期：2015-1-22 下午04:46:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	
	public boolean saveSqjg(YlbxjgForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @描述:申请结果
	 * @作者：cq [工号：785]
	 * @日期：2015-1-22 下午04:49:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateSqjg(YlbxjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @描述:删除结果
	 * @作者：cq [工号：785]
	 * @日期：2015-1-22 下午04:49:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteSqjg(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getBbjg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?sqjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:删除结果
	 * @作者：cq [工号：785]
	 * @日期：2015-1-22 下午05:08:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int sqjgDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}


	/**
	 * 
	 * @描述:查看结果
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午11:51:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xszbbjgId
	 * @return
	 * @throws Exception
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> viewOneYlbxjgList(String jgId) throws Exception {
		return dao.viewOneYlbxjgList(jgId);
	}
	
	/** 
	 * 导出
	 */
	public List<HashMap<String, String>> getExportAllList(YlbxjgForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getExportAllList(t, user);
	}

	
	/**
	 * 
	 * @描述: 取医疗缴费档次以供资助报表打印
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-26 上午10:40:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJfdcRylbInfo(String xh) {
		
		return dao.getJfdcRylbInfo(xh); 
	}
	
	//照片导出
	public void zpdc(YlbxjgForm model,HttpServletResponse response,User user)throws Exception{
		List<HashMap<String, String>> list = dao.getXszpList(model,user);
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
					Blob blob = getXszp(list.get(i).get("xh"),model.getZpType(), dao);
					if(null!=blob){
						InputStream in2 = blob.getBinaryStream();
						//照片命名（若所选择的命名账号类型相应字段为空则以学号命名)
						String[] PhotoNameTypes =model.getPhotoNameType().split("-");
						String zpmc=null;
						if(PhotoNameTypes.length!=1){
							zpmc = Base.isNull(list.get(i).get(PhotoNameTypes[0])) ? list.get(i).get("xh") : list.get(i).get("xm")+list.get(i).get(PhotoNameTypes[0]);
						}else{
							zpmc = Base.isNull(list.get(i).get(model.getPhotoNameType())) ? list.get(i).get("xh") : list.get(i).get(model.getPhotoNameType());
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
	
	private Blob getXszp(String xh,String zpType,DAO dao){
		
		Blob blob = null;
		if("xs_zs".equals(zpType)){//若有招生照片则导出，否则导出学生照片
			blob=dao.getOneBlob("select xszp from xszpb where xh=? and xszp is not null and nvl(length(xszp),'0')>0", new String[]{xh}, "xszp");
			if(blob==null){
				blob=dao.getOneBlob("select zp from xszpb where xh=? and zp is not null and nvl(length(zp),'0')>0", new String[]{xh}, "zp");
			}
			return blob;
		}
		return blob;
		
	}
}
