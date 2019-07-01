/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-17 ����04:14:42 
 */  
package com.zfsoft.xgxt.zjly.zhf.sq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ۺϷ�(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-17 ����04:14:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfService extends SuperServiceImpl<ZhfForm, ZhfDao>{
	private ZhfDao dao = new ZhfDao();
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/** 
	 * @����:�õ���Ŀģ���б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-20 ����09:54:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmmkList(){
		return dao.getXmmkList();
	}
	
	/** 
	 * @����:��֤����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-20 ����10:49:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(List<ZhfForm> list){
		return dao.countZhf(list)>0;
	}
	
	/** 
	 * @����:�õ��Ʒ���Ŀ�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-20 ����01:34:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mkid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJfxmListByMkid(String mkid){
		return dao.getJfxmListByMkid(mkid);
	}
	
	/** 
	 * @����:�õ�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�12282]
	 * @���ڣ�2016-6-20 ����03:12:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getFs(String id){
		return dao.getFs(id);
	}
	
	/** 
	 * @����:����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-21 ����03:18:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveZhf(ZhfForm model) throws Exception{
		List<ZhfForm> list = new ArrayList<ZhfForm>();
		//����
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		String[] jfxms = model.getJfxmdms();
		String[] xmdms = model.getXmmkdms();
		String[] sxsms = model.getSxsms();
		String[] cysjs = model.getCysjs();
		for(int i = 0;i<jfxms.length;i++){
			ZhfForm form = new ZhfForm();
			form.setXh(model.getXh());
			form.setXmmkdm(xmdms[i]);
			form.setJfxmdm(jfxms[i]);
			form.setSxsm(sxsms[i]);
			form.setCysj(cysjs[i]);
			form.setLrr(model.getXh());
			form.setLrsj(GetTime.getTimeByFormat(DATE_FORMAT));
			
			FormFile file = (FormFile) files.get("fj"+i);
			if (file != null && !StringUtil.isNull(file.getFileName())){
				String basePath = resource.getString("filesys.local.dir");
				//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
				if(StringUtils.isNull(basePath)){
					basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
				}
				String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
				
				if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
					File srcFile = FileUtil.conversionFormFile(file);
					File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
					FileUtils.copyFile(srcFile, destFile);
					form.setFj(destFile.getAbsolutePath());
					form.setFjmc(file.getFileName());
				}
			}
			else{
				form.setFj("");
				form.setFjmc("");
			}
			list.add(form);
		}
		if(isExist(list)){
			return false;
		}else{
			return dao.plSave(list);			
		}		
	}
	
	
	public ZhfForm getModel(ZhfForm t) throws Exception{
		ZhfForm form = super.getModel(t);
		HashMap<String,String> map = dao.getResult(t.getId());
		form.setJfxmmc(map.get("jfxmmc"));
		form.setXmmkmc(map.get("xmmkmc"));
		form.setFj(map.get("fj"));
		form.setFs(map.get("fs"));
		form.setKhyd(map.get("khyd"));
		return form;
	}
	
	/** 
	 * @����:��ȡ����Ҫ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-21 ����03:19:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getKhyd(String id){
		return dao.getKhyd(id);
	}
	
	/** 
	 * @����:�ж��Ƿ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-21 ����03:54:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExistForUpdate(ZhfForm t){
		return Integer.valueOf(dao.countZhfSq(t))>0;
	}
}
