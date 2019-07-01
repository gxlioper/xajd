/**
 * @部门:学工产品事业部
 * @日期：2014-7-7 上午10:19:51 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.bysxx;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;
import xgxt.DAO.DAO;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-7 上午10:19:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BysXxService extends SuperServiceImpl<BysXxForm, BysXxDao> {
	public static String _BCZSCID = "-1";
	BysXxDao dao = new BysXxDao();
	
	XsxxglDao xsxxglDao = new XsxxglDao();
	
	@Override
	public List<HashMap<String, String>> getPageList(BysXxForm t, User user)
			throws Exception {
		return getBysXx(super.getPageList(t, user));
	}
	public List<HashMap<String, String>> getBysXx(
			List<HashMap<String, String>> list) {
		return list;
	}
	/**
	 * 
	 * @描述:查询所有学生信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-7 下午02:03:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(BysXxForm model, User user)
	throws Exception{
		return dao.getXsxxList(model, user);
		
	}
	/**
	 * 
	 * @描述:添加
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-7 下午03:24:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String save(BysXxForm myForm,String pkValues) throws Exception {
		List<String[]> params = new ArrayList<String[]>();
		DAO daos = DAO.getInstance();
		List<String> col = daos.getColumnsName("XG_BYSXX_BYSXXB");
		String[] pk = pkValues.split(",");
		for (int i = 0; i < pk.length; i++) {
			String[] formValue = new String[col.size()];
			formValue[0]=pk[i];
			formValue[1]=myForm.getBynd();
			for (int j = 0; j < col.size()-2; j++) {
				formValue[j+2]="";
			}
			params.add(formValue);
		}
		boolean flag = dao.save(params);
		return flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_SUCCESS;
	}
	/**
	 * 
	 * @描述:修改操作
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 上午09:43:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param valueMap
	 * @param xsfzxxValueMap
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateRecord(BysXxForm myForm,
			HashMap<String, String> valueMap,
			HashMap<String, String> xsfzxxValueMap) throws Exception {
		valueMap.put("xy", myForm.getXymc());

		// 省市县，拆分字段保存。保持原有表字段数据完整性。新开发学生信息增删改查申请审核等功能，拆分字段未使用
		// 生源地
		String syd = valueMap.get("syd");
		HashMap<String, String> sydMap = getSsx(syd);
		valueMap.put("syds", sydMap.get("sheng"));
		valueMap.put("sydshi", sydMap.get("shi"));
		valueMap.put("sydx", sydMap.get("xian"));

		// 籍贯
		String jg = valueMap.get("jg");
		HashMap<String, String> jgMap = getSsx(jg);
		valueMap.put("jgs", jgMap.get("sheng"));
		valueMap.put("jgshi", jgMap.get("shi"));
		valueMap.put("jgx", jgMap.get("xian"));

		// 户口所在地
		String hkszd = valueMap.get("hkszd");
		HashMap<String, String> hkszdMap = getSsx(hkszd);
		valueMap.put("hkshen", hkszdMap.get("sheng"));
		valueMap.put("hkshi", hkszdMap.get("shi"));
		valueMap.put("hkxian", hkszdMap.get("xian"));

		boolean result = xsxxglDao.updateInfo(valueMap);
		if (result) {
			result = xsxxglDao.updateInfoXsfzxx(xsfzxxValueMap);
		}

		return result;
	}
	/**
	 * 
	 * @描述:修改毕业生信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 上午10:07:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param bysXxvalueMap
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBysXx(BysXxForm myForm,
			HashMap<String, String> bysXxvalueMap) throws Exception {
		boolean result = dao.updateBysInfo(bysXxvalueMap);
		return result;
	}
	/**
	 * 
	 * @描述:通过学号获取毕业生信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 上午10:38:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBysXx(String xh) {
		HashMap<String, String> map = dao.getBysXx(xh);
		return map;
	}
	/**
	 * 通过学号获取申请信息
	 */
	public HashMap<String, String> getSqXx(String xh) {
		HashMap<String, String> map = dao.getSqXx(xh);
		return map;
	}
	/**
	 * 
	 * @描述:TODO获取照片
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午02:18:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @param column
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getZpMap(BysXxForm myForm,HttpServletRequest request,String column) throws Exception{
		// TODO 自动生成方法存根
		Blob blob= dao.getZpMap(myForm,column);
		BufferedInputStream bin = null;
		byte[] bytes = null;
		InputStream in = null;
		if(blob==null){
			ByteArrayOutputStream byStream = new ByteArrayOutputStream();
			int ch=0;
			ServletContext application = request.getSession().getServletContext();
			in =  new FileInputStream(new File(application.getRealPath("/images/type_pic.gif"))); 
			while((ch=in.read())!=-1){
				byStream.write(ch);
			}
			bytes = byStream.toByteArray();
			byStream.close();
		}else{
			try {
				bin = new BufferedInputStream(blob.getBinaryStream());
				bytes = new byte[(int)blob.length()];
				int len = bytes.length;
				int offset = 0;
				int read = 0;
				while(offset<len&&(read=bin.read(bytes,offset,len-offset))>0){
					offset+=read;
				}
			} catch(IOException e){
				e.printStackTrace();
			} finally{
				if (bin != null){
					bin.close();
				}
			}
		}
			BASE64Encoder encoder = new BASE64Encoder();
			String zp=bytes != null ? encoder.encode(bytes) : null;
			HashMap<String, String> map=new HashMap<String, String>();
			map.put(column, zp);
		return map;
	}
	
	
	/*
	 * 省市县，拆分字段保存。保持原有表字段数据完整性。新开发学生信息增删改查申请审核等功能，拆分字段未使用
	 */
	private HashMap<String, String> getSsx(String dm) {
		HashMap<String, String> ssx = new HashMap<String, String>();
		String sheng = "";
		String shi = "";
		String xian = "";
		if (dm != null && !dm.trim().equals("") && dm.length() >= 6) {
			String tmp0 = dm.substring(0, 2);
			sheng = tmp0 + "0000";
			String tmp1 = dm.substring(2, 4);
			if (!tmp1.equals("00")) {
				shi = tmp0 + tmp1 + "00";
			}
			String tmp2 = dm.substring(4, 6);
			if (!tmp2.equals("00")) {
				xian = dm;
			}
		}
		ssx.put("sheng", sheng);
		ssx.put("shi", shi);
		ssx.put("xian", xian);
		return ssx;
	}
	
	/**
	 * 
	 * @描述: 批量插入
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-20 上午10:42:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @param bynd
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean runInsertSelect(BysXxForm model, User user, String bynd) {
		boolean result = false;
		try {
			result = dao.insertSelect(model, user, bynd);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}
	
}
