/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-7 ����10:19:51 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-7 ����10:19:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
	 * @����:��ѯ����ѧ����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-7 ����02:03:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(BysXxForm model, User user)
	throws Exception{
		return dao.getXsxxList(model, user);
		
	}
	/**
	 * 
	 * @����:���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-7 ����03:24:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
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
	 * @����:�޸Ĳ���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����09:43:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param valueMap
	 * @param xsfzxxValueMap
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateRecord(BysXxForm myForm,
			HashMap<String, String> valueMap,
			HashMap<String, String> xsfzxxValueMap) throws Exception {
		valueMap.put("xy", myForm.getXymc());

		// ʡ���أ�����ֶα��档����ԭ�б��ֶ����������ԡ��¿���ѧ����Ϣ��ɾ�Ĳ�������˵ȹ��ܣ�����ֶ�δʹ��
		// ��Դ��
		String syd = valueMap.get("syd");
		HashMap<String, String> sydMap = getSsx(syd);
		valueMap.put("syds", sydMap.get("sheng"));
		valueMap.put("sydshi", sydMap.get("shi"));
		valueMap.put("sydx", sydMap.get("xian"));

		// ����
		String jg = valueMap.get("jg");
		HashMap<String, String> jgMap = getSsx(jg);
		valueMap.put("jgs", jgMap.get("sheng"));
		valueMap.put("jgshi", jgMap.get("shi"));
		valueMap.put("jgx", jgMap.get("xian"));

		// �������ڵ�
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
	 * @����:�޸ı�ҵ����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����10:07:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param bysXxvalueMap
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBysXx(BysXxForm myForm,
			HashMap<String, String> bysXxvalueMap) throws Exception {
		boolean result = dao.updateBysInfo(bysXxvalueMap);
		return result;
	}
	/**
	 * 
	 * @����:ͨ��ѧ�Ż�ȡ��ҵ����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����10:38:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBysXx(String xh) {
		HashMap<String, String> map = dao.getBysXx(xh);
		return map;
	}
	/**
	 * ͨ��ѧ�Ż�ȡ������Ϣ
	 */
	public HashMap<String, String> getSqXx(String xh) {
		HashMap<String, String> map = dao.getSqXx(xh);
		return map;
	}
	/**
	 * 
	 * @����:TODO��ȡ��Ƭ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����02:18:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @param column
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getZpMap(BysXxForm myForm,HttpServletRequest request,String column) throws Exception{
		// TODO �Զ����ɷ������
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
	 * ʡ���أ�����ֶα��档����ԭ�б��ֶ����������ԡ��¿���ѧ����Ϣ��ɾ�Ĳ�������˵ȹ��ܣ�����ֶ�δʹ��
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
	 * @����: ��������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-20 ����10:42:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @param bynd
	 * @return
	 * boolean �������� 
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
