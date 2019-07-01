/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

import java.io.File;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsDao;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxDao;
import com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq.BysXxXgSqService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XxxgService;
import common.Globals;
import common.newp.StringUtil;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ����Ϣ����
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-23 ����11:12:32
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XsxxglService extends SuperServiceImpl<XsxxglModel, XsxxglDao> {

	private XsxxglDao dao = new XsxxglDao();

	public XsxxglService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:��ȡѧ����Ϣ�б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-23 ����11:46:35
	 * @�޸ļ�¼:
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(XsxxglModel model,
			User user) throws Exception {
		return dao.getPageList(model, user);
	}
	public List<HashMap<String, String>> getStuCjPageList(XsxxglModel model,
			User user) throws Exception {
		return dao.getStuCjPageList(model, user);
	}

	/**
	 * ��ȡѧ����Ϣ�б�
	 */
	public List<HashMap<String, String>> getXsxxAllList(XsxxglModel t,
			User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getPageList(t, user);
	}

	/**
	 * 
	 * @����:��ѯ����ֶ������б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-25 ����09:27:04
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getCxjgzdpzList() throws Exception {
		return dao.getCxjgzdpzList();
	}

	/**
	 * 
	 * ����:�����ʼ��
	 * 
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-25 ����01:57:00
	 * @�޸ļ�¼:
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean cshYhmm(String mm1, String values,String bz) throws Exception {
		return dao.cshYhmm(mm1, values,bz);
	}

	/**
	 * 
	 * @����:ɾ��ѧ����Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����11:10:18
	 * @�޸ļ�¼:
	 * @param keys
	 * @return
	 * @throws Exception int ��������
	 * @throws
	 */
	public boolean delData(String keys) throws Exception {
		return dao.delData(keys);
	}

	/**
	 * @throws NullStringException
	 * 
	 * @����:ͨ��ѧ�Ų�ѯѧ����ϸ��Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����02:27:17
	 * @�޸ļ�¼:
	 * @param xh
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getXsxxByXh(String xh) {
		HashMap<String, String> data = dao.getXsxxByXh(xh);
		/** ��ѯҳ����ʾzw�����ֶ� */
		String zwdm = data.get("zw");
		if (StringUtils.equals("1", zwdm)) {
			data.put("zwmc", "У��");
		} else if (StringUtils.equals("2", zwdm)) {
			data.put("zwmc", "�ּ�");
		} else if (StringUtils.equals("3", zwdm)) {
			data.put("zwmc", "����");
		}
		
		
		//�������д�����ʾ��������
		if(common.Globals.XXDM_JXKJSFXY.equals("11318")){
			data.put("yhmc2", dao.getYhmc(data.get("shgxzw2")));
			
		}
		
		if("10606".equals(Base.xxdm)){
			data.put("yhmc2", dao.getYhmc(data.get("shgxzw1")));
			data.put("yhmc3", dao.getYhmc(data.get("shgxgx2")));	
		}
		
		return data;

	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯѧ����ϸ��Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����02:27:17
	 * @�޸ļ�¼:
	 * @param xh
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getXsxxByXhForUpdate(String xh) {
		
		HashMap<String, String> map = null;
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			//�㽭��ѧ
			map = dao.getXsxxByXhForUpdate_zjdx(xh);
		}else{
			map = dao.getXsxxByXhForUpdate(xh);
		}
		return map;
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯѧ���ɼ��б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-28 ����08:51:21
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getStuCjList(String xh) {
		return dao.getStuCjList(xh);
	}
	/**
	 * 
	 * @����:��ȡѧ��ѧ��γ̳ɼ�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-16 ����07:34:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getStuCjOfXnList(String xh,String xn) {
		return dao.getStuCjOfXnList(xh,xn);
	}
	
	public List<HashMap<String, String>> getStuCjOfXnXqList(String xh,String xn,String xq) {
		return dao.getStuCjOfXnXqList(xh,xn,xq);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-9-6 ����05:16:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getStuXFJDOfXnList(String xh,String xn) {
		return dao.getStuXFJDOfXnList(xh,xn);
	}
	
	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ�ȼ����Գɼ�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-28 ����10:14:30
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getStuDjcjList(String xh) {
		return dao.getStuDjcjList(xh);
	}

	/**
	 * ��ȡѧ����λ��Ϣ�б�
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getStuQgzxXsgwxxList(String xh) {
		return dao.getStuQgzxXsgwxxList(xh);
	}

	/**
	 * ��ȡ��ѵ����
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getStuJxkhqk(String xh) {
		return dao.getStuJxkhqk(xh);
	}
	/**
	 * ��ȡѧ����𷢷��б�
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getStuQgzxCjffList(String xh) {
		return dao.getStuQgzxCjffList(xh);
	}

	/**
	 * 
	 * @����:��ȡ��Ԣ��Ϣ�б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-29 ����10:38:21
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getStuGyxxList(String xh) {
		return dao.getStuGyxxList(xh);
	}

	/**
	 * ��ȡ¥������Ա��Ϣ
	 */
	public List<HashMap<String,String>> getGyfdyxx(String xh){
		return dao.getGyfdyxx(xh);
	}
	
	/**
	 * ��ȡ¥������Ա��Ϣ
	 */
	public List<HashMap<String,String>> getGyglyxx(String xh){
		return dao.getGyglyxx(xh);
	}
	
	/**
	 * 
	 * @����:��ȡ������Ʒ�б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-29 ����10:38:21
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getQswpList(String xh) {
		return dao.getQswpList(xh);
	}

	/**
	 * 
	 * @����: ��ȡ��Ԣ���ɴ�����Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-12 ����02:40:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getGyjlclxxList(String xh) {
		return dao.getGyjlclxxList(xh);
	}
	
	public List<HashMap<String, String>> getGyjlclxxAllList(String xh) {
		return dao.getGyjlclxxAllList(xh);
	}

	/**
	 * 
	 * @����:��ȡ��Ԣ�춯��Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-12 ����03:05:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getGyydxxList(String xh) {
		return dao.getGyydxxList(xh);
	}

	/**
	 * 
	 * @����:��ȡ��Ԣ������Ϣ
	 * @���ߣ�1036
	 * @���ڣ�2014-3-14 ����03:24:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getGypyxxList(String xh) {
		return dao.getGypyxxList(xh);
	}

	/**
	 * 
	 * @����:����ѧ�Ų�ѯΥ�ʹ����б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		return dao.getWjcfList(xh);
	}

	/**
	 * ͨ��������ȡ��������ֶ�
	 * 
	 * @param tableName
	 * @return
	 */
	public String[] getColumnByTable(String tableName) {
		return dao.getColumnByTable(tableName);
	}

	/**
	 * @����:���ݱ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-29 ����02:13:20
	 * @�޸ļ�¼:
	 * @param myForm
	 * @return boolean ��������
	 * @throws
	 */
	public boolean saveRecord(XsxxglModel myForm,
			HashMap<String, String> valueMap) throws Exception {
		valueMap.put("xy", myForm.getXymc());

		// ʡ���أ�����ֶα��档����ԭ�б��ֶ����������ԡ��¿���ѧ����Ϣ��ɾ�Ĳ�������˵ȹ��ܣ�����ֶ�δʹ��
		// ��Դ��
		String syd = valueMap.get("syd");
		HashMap<String, String> sydMap = getSsx(syd);
		valueMap.put("syds", sydMap.get("sheng"));
		valueMap.put("sydshi", sydMap.get("shi"));
		valueMap.put("sydx", sydMap.get("xian"));

		boolean result = dao.saveInfo(valueMap);
		if (result) {
			result = dao.saveXsqtxx(myForm);
		}
		return result;
	}

	/**
	 * 
	 * @����:�޸Ĳ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-3 ����08:52:48
	 * @�޸ļ�¼:
	 * @param myForm
	 * @param valueMap
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateRecord(XsxxglModel myForm,
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
		
		// ������
		String csd = valueMap.get("csd");
		HashMap<String, String> csdMap = getSsx(csd);
		valueMap.put("csds", csdMap.get("sheng"));
		valueMap.put("csdshi", csdMap.get("shi"));
		valueMap.put("csdxian", csdMap.get("xian"));

		boolean result = dao.updateInfo(valueMap);
		if (result) {
			result = dao.updateInfoXsfzxx(xsfzxxValueMap);
		}

		return result;
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
	 * @����:ѧ�����޸���Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����04:30:36
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateRecordForStu(String sqid, boolean th) throws Exception {
		return updateRecordForStu(sqid, null, th);
	}
	public boolean updateRecordForStu(String sqid, String xh, boolean th) throws Exception {
		return updateRecordForStu(sqid, xh, th, false);
	}
	public boolean updateRecordForStu(String sqid, String xh, boolean th, boolean isBysxx) throws Exception {
		return updateRecordForStu(sqid, xh, th, isBysxx, null);
	}
	/**
	 * 
	 * @����:ѧ�����޸���Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����04:30:36
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateRecordForStu(String sqid, String xh, boolean th, boolean isBysxx,List<HashMap<String, String>> xsxgzdList)
			throws Exception {
		List<HashMap<String, String>> xgzdList = null;
		if(xsxgzdList != null){
			xgzdList = xsxgzdList;
		} else {
			xgzdList = new XxxgService().getXgzdList(sqid);
		}
		
		String[] xsxxZds = dao.getColumnByTable("xsxxb");
		String[] xsfzxxZds = dao.getColumnByTable("xsfzxxb");
		String[] bysxxZds = dao.getColumnByTable("XG_BYSXX_BYSXXB");

		HashMap<String, String> valueMap = new HashMap<String, String>();
		HashMap<String, String> xsfzxxValueMap = new HashMap<String, String>();
		HashMap<String, String> bysxxValueMap = new HashMap<String, String>();

		String zd = null;
		String zdz = null;
		//��ͥ��Ա��Ϣ
		String jtcyxxXgqzJson = null;
		String jtcyxxZdzJson = null;
		
		//ѧ����ᾭ����Ϣ
		String xlshjlXgqzJson = null;
		String xlshjlZdzJson = null;

		//��ѵ��Ϣ
		String pxxxXgqzJson = null;
		String pxxxZdzJson = null;

		//��ѧǰ�����
		String rxqhjqkXgqzJson = null;
		String rxqhjqkZdzJson = null;

		//��������£�
		String hjqkxxNewXgqzJson = null;
		String hjqkxxNewZdzJson = null;
		
		//ѧ��У��������������·ְҵ����ѧԺ��
		String xsxwhjqkXgqzJson = null;
		String xsxwhjqkZdzJson = null;
		
		//�ȼ����Գɼ��������Ƽ���ѧ��
		String djkscjXgqzJson = null;
		String djkscjZdzJson = null;
		
		//ѧ���ɲ������������Ƽ���ѧ��
		String xsgbjlXgqzJson = null;
		String xsgbjlZdzJson = null;
		
		//���ʵ������������Ƽ���ѧ��
		String shsjqkXgqzJson = null;
		String shsjqkZdzJson = null;
		
		//���ʵ�����������ũҵ��ѧ��
		String hnnyshsjXgqzJson = null;
		String hnnyshsjZdzJson = null;
		
		//��������������Ϣ�������Ƽ���ѧ��
		String cgcjjlXgqzJson = null;
		String cgcjjlZdzJson = null;
		
		//�������ģ������Ƽ���ѧ��
		String fblwXgqzJson = null;
		String fblwZdzJson = null;
		
		//������Ŀ�������Ƽ���ѧ��
		String kyxmXgqzJson = null;
		String kyxmZdzJson = null;
		
		//�����ɹ��������Ƽ���ѧ��
		String qtcgXgqzJson = null;
		String qtcgZdzJson = null;
		
		//������������Ƽ���ѧ��
		String hjqkXakjXgqzJson = null;
		String hjqkXakjZdzJson = null;

		//����ʵϰ��Ϣ(���ֹ�ҵְҵ)
		String ggsxjlXgqzJson = null;
		String ggsxjlZdzJson = null;

		//����ʵϰ��Ϣ(���ֹ�ҵְҵ)
		String dgsxjlXgqzJson = null;
		String dgsxjlZdzJson = null;
		
		//��ѧ�о�
		String kxyjXgqzJson=null;
		String kxyjZdzJson=null;
		//�����о�
		String ktyjXgqzJson=null;
		String ktyjZdzJson=null;
		//���´�ҵ��Ŀ
		String cxcyxmXgqzJson=null;
		String cxcyxmZdzJson=null;
		//ѧ�ƾ���
		String xkjsXgqzJson=null;
		String xkjsZdzJson=null;
		//����֤��
		String jnzsXgqzJson=null;
		String jnzsZdzJson=null;

		//���˼���(����ʦ����ѧ)
		String grjlXgqzJson = null;
		String grjlZdzJson = null;

		//��������(����ʦ����ѧ)
		String gzjlXgqzJson = null;
		String gzjlZdzJson = null;

		//ѧ��������Ϣ(����ѧԺ)
		String xsyhxxXgqzJson = null;
		String xsyhxxZdzJson = null;

		//���˽�����Ϣ(������ҽҩ��ѧ)
		String grhjxxXgqzJson = null;
		String grhjxxZdzJson = null;

		for (HashMap<String, String> xgzdMap : xgzdList) {
			zd = xgzdMap.get("zd");
			if (th) {
				zdz = xgzdMap.get("xgqz");
			} else {
				zdz = xgzdMap.get("zdz");
			}
			if (zd != null) {
				//��ͥ��Ա��Ϣ
				if (zd.equals(Constants.ZDYBD_JTCYXX)) {
					jtcyxxXgqzJson = xgzdMap.get("xgqz");
					jtcyxxZdzJson = xgzdMap.get("zdz");
				}
				//ѧ����ᾭ����Ϣ
				if (zd.equals(Constants.ZDYBD_XLSHJLXX)) {
					xlshjlXgqzJson = xgzdMap.get("xgqz");
					xlshjlZdzJson = xgzdMap.get("zdz");
				}
				//��ѵ��Ϣ
				if (zd.equals("pxxxList")) {
					pxxxXgqzJson = xgzdMap.get("xgqz");
					pxxxZdzJson = xgzdMap.get("zdz");
				}
				//�����
				if (zd.equals("rxqhjqkList")) {
					rxqhjqkXgqzJson = xgzdMap.get("xgqz");
					rxqhjqkZdzJson = xgzdMap.get("zdz");
				}
				//�����(��)
				if (zd.equals("hjqkxxNewList")) {
					hjqkxxNewXgqzJson = xgzdMap.get("xgqz");
					hjqkxxNewZdzJson = xgzdMap.get("zdz");
				}
				//ѧ��У��������������·ְҵ����ѧԺ��
				if (zd.equals("xsxwhjqkList")) {
					xsxwhjqkXgqzJson = xgzdMap.get("xgqz");
					xsxwhjqkZdzJson = xgzdMap.get("zdz");
				}
				//�ȼ����Գɼ��������Ƽ���ѧ��
				if (zd.equals("djkscjList_10704")) {
					djkscjXgqzJson = xgzdMap.get("xgqz");
					djkscjZdzJson = xgzdMap.get("zdz");
				}
				//ѧ���ɲ������������Ƽ���ѧ��
				if (zd.equals("xsgbjlList_10704")) {
					xsgbjlXgqzJson = xgzdMap.get("xgqz");
					xsgbjlZdzJson = xgzdMap.get("zdz");
				}
				//���ʵ������������Ƽ���ѧ��
				if (zd.equals("shsjqkList_10704")) {
					shsjqkXgqzJson = xgzdMap.get("xgqz");
					shsjqkZdzJson = xgzdMap.get("zdz");
				}
				//���ʵ�����������ũҵ��ѧ��
				if (zd.equals("shsjqkList_10466")) {
					hnnyshsjXgqzJson = xgzdMap.get("xgqz");
					hnnyshsjZdzJson = xgzdMap.get("zdz");
				}
				//��������������Ϣ�������Ƽ���ѧ��
				if (zd.equals("cgcjjlList_10704")) {
					cgcjjlXgqzJson = xgzdMap.get("xgqz");
					cgcjjlZdzJson = xgzdMap.get("zdz");
				}
				//�������ģ������Ƽ���ѧ��
				if (zd.equals("fblwList_10704")) {
					fblwXgqzJson = xgzdMap.get("xgqz");
					fblwZdzJson = xgzdMap.get("zdz");
				}
				//������Ŀ�������Ƽ���ѧ��
				if (zd.equals("kyxmList_10704")) {
					kyxmXgqzJson = xgzdMap.get("xgqz");
					kyxmZdzJson = xgzdMap.get("zdz");
				}
				//�����ɹ��������Ƽ���ѧ��
				if (zd.equals("qtcgList_10704")) {
					qtcgXgqzJson = xgzdMap.get("xgqz");
					qtcgZdzJson = xgzdMap.get("zdz");
				}
				if (zd.equals("hjqkList_10704")) {					
					hjqkXakjXgqzJson = xgzdMap.get("xgqz");
					hjqkXakjZdzJson = xgzdMap.get("zdz");
				}
				//����ʵϰ��Ϣ
				if (zd.equals("ggsxjlList")) {
					ggsxjlXgqzJson = xgzdMap.get("xgqz");
					ggsxjlZdzJson = xgzdMap.get("zdz");
				}
				//����ʵϰ��Ϣ
				if (zd.equals("dgsxjlList")) {
					dgsxjlXgqzJson = xgzdMap.get("xgqz");
					dgsxjlZdzJson = xgzdMap.get("zdz");
				}
				//��ѧ�о�
				if (zd.equals("kxyjList")) {
					kxyjXgqzJson= xgzdMap.get("xgqz");
					kxyjZdzJson = xgzdMap.get("zdz");
				}
				//�����о�
				if (zd.equals("ktyjList")) {
					ktyjXgqzJson= xgzdMap.get("xgqz");
					ktyjZdzJson = xgzdMap.get("zdz");
				}
				//���´�ҵ��Ŀ
				if (zd.equals("cxcyxmList")) {
					cxcyxmXgqzJson= xgzdMap.get("xgqz");
					cxcyxmZdzJson = xgzdMap.get("zdz");
				}
				//ѧ�ƾ���
				if (zd.equals("xkjsList")) {
					xkjsXgqzJson= xgzdMap.get("xgqz");
					xkjsZdzJson = xgzdMap.get("zdz");
				}
				//����֤��
				if (zd.equals("jnzsList")) {
					jnzsXgqzJson= xgzdMap.get("xgqz");
					jnzsZdzJson = xgzdMap.get("zdz");
				}
				
				//���˼���
				if (zd.equals("grjlList")) {
					grjlXgqzJson = xgzdMap.get("xgqz");
					grjlZdzJson = xgzdMap.get("zdz");
				}
				//��������
				if (zd.equals("gzjlList")) {
					gzjlXgqzJson = xgzdMap.get("xgqz");
					gzjlZdzJson = xgzdMap.get("zdz");
				}
				//ѧ��������Ϣ
				if (zd.equals("xsyhxxList")) {
					xsyhxxXgqzJson = xgzdMap.get("xgqz");
					xsyhxxZdzJson = xgzdMap.get("zdz");
				}
				//���˽�����Ϣ
				if (zd.equals("grhjxxList")) {
					grhjxxXgqzJson = xgzdMap.get("xgqz");
					grhjxxZdzJson = xgzdMap.get("zdz");
				}
			}

			for (int i = 0; i < xsxxZds.length; i++) {
				String xsxxZd = xsxxZds[i];
				if (zd != null && zd.toLowerCase().equals(xsxxZd.toLowerCase())) {
					valueMap.put(zd, zdz);
				}
			}
			for (int i = 0; i < bysxxZds.length; i++) {
				String bysxxZd = bysxxZds[i];
				if (zd != null
						&& zd.toLowerCase().equals(bysxxZd.toLowerCase())) {
					bysxxValueMap.put(zd, zdz);
				}
			}
			for (int i = 0; i < xsfzxxZds.length; i++) {
				String xsfzxxZd = xsfzxxZds[i];
				if (zd != null
						&& zd.toLowerCase().equals(xsfzxxZd.toLowerCase())) {
					xsfzxxValueMap.put(zd, zdz);
				}
			}
		}
		boolean result = true;

		if (xh == null) {
			xh = new XxxgService().getXhBySqid(sqid);
		}
		if (xh != null) {
			if (!valueMap.isEmpty()) {
				valueMap.put("xh", xh);
				result = dao.updateInfo(valueMap);
			}
			// ����ʦ����ѧ���±�ҵ����Ϣ
			if ("10511".equals(Base.xxdm) && result && isBysxx) {
				HashMap<String, String> bysxx = new BysXxXgSqService()
						.getXhBySqid(sqid);
				if (!bysxxValueMap.isEmpty() && !bysxx.isEmpty()) {
					bysxxValueMap.put("xh", bysxx.get("xh"));
					BysXxDao bysxxDao = new BysXxDao();
					result = bysxxDao.updateBysInfo(bysxxValueMap);
				}

			}
			if (result) {
				if (!xsfzxxValueMap.isEmpty()) {
					xsfzxxValueMap.put("xh", xh);
					result = dao.updateInfoXsfzxx(xsfzxxValueMap);
				}
			}

			List jtcyxxList = null;
			List xlshjlcxxList = null;
			List pxxxList = null;
			List rxqhjqkList = null;
			List hjqkList = null;
			List xsxwhjqkList = null;
			//����ũҵ��ѧ
			List hnnyshsjList = null;
			//�����Ƽ���ѧ
			List djkscjList = null;	
			List xsgbjlList = null;
			List shsjqkList = null;
			List cgcjjlList = null;
			List fblwList = null;
			List kyxmList = null;
			List qtcgList = null;
			//�����Ƽ���ѧ��end
			List ggsxjlList = null;
			List dgsxjlList = null;
			List kxyjList=null;
			List ktyjList=null;
			List cxcyxmList=null;
			List xkjsList=null;
			List jnzsList=null;
			List grjlList = null;
			List gzjlList = null;
			List xsyhxxList = null;
			List grhjxxList = null;
			List hjqkXakjList = null;
			if (th) {// �˻�
				if (jtcyxxXgqzJson != null) {
					jtcyxxXgqzJson = "{data:" + jtcyxxXgqzJson + "}";
					jtcyxxList = JsonUtil.jsonToList(jtcyxxXgqzJson,
							JtcyxxModel.class);
					result = updateJtcyxx(xh, jtcyxxList);// ��ͥ��Ա��Ϣ����
				}

				if (xlshjlXgqzJson != null) {
					xlshjlXgqzJson = "{data:" + xlshjlXgqzJson + "}";
					xlshjlcxxList = JsonUtil.jsonToList(xlshjlXgqzJson,
							XlshjlModel.class);
					result = updateXlshjlxx(xh, xlshjlcxxList);// ѧ����ᾭ����Ϣ����
				}

				if (pxxxXgqzJson != null) {
					pxxxXgqzJson = "{data:" + pxxxXgqzJson + "}";
					pxxxList = JsonUtil.jsonToList(pxxxXgqzJson,
							PxxxModel.class);
					result = updatePxxx(xh, pxxxList);// ��ѵ��Ϣ����
				}

				if (rxqhjqkXgqzJson != null) {
					rxqhjqkXgqzJson = "{data:" + rxqhjqkXgqzJson + "}";
					rxqhjqkList = JsonUtil.jsonToList(rxqhjqkXgqzJson,
							HjqkModel.class);
					result = updateHjqk(xh, rxqhjqkList);// ���������
				}

				if (hjqkxxNewXgqzJson != null) {
					hjqkxxNewXgqzJson = "{data:" + hjqkxxNewXgqzJson + "}";
					hjqkList = JsonUtil.jsonToList(hjqkxxNewXgqzJson,
							HjqkNewModel.class);
					result = updateHjqkNew(xh, hjqkList);// �����(��)����
				}
				
				if (xsxwhjqkXgqzJson != null) {
					xsxwhjqkXgqzJson = "{data:" + xsxwhjqkXgqzJson + "}";
					xsxwhjqkList = JsonUtil.jsonToList(xsxwhjqkXgqzJson,
							XsxwhjqkModel.class);
					result = updateXsxwhjqk(xh, xsxwhjqkList);// ѧ��У��������������·ְҵ����ѧԺ������
				}
				// ����ũҵ��ѧ
				if("10466".equals(Base.xxdm)){
					//���ʵ��
					if (hnnyshsjZdzJson != null) {
						hnnyshsjZdzJson = "{data:" + hnnyshsjZdzJson + "}";
						hnnyshsjList = JsonUtil.jsonToList(hnnyshsjZdzJson,
								ShsjqkModel.class);
						result = updateShsjqk(xh, hnnyshsjList);
					}
				}
				//�����Ƽ���ѧ
				if("10704".equals(Base.xxdm)){
					//�ȼ�������Ϣ����
					if (djkscjXgqzJson != null) {
						djkscjXgqzJson = "{data:" + xsxwhjqkXgqzJson + "}";
						djkscjList = JsonUtil.jsonToList(djkscjXgqzJson,
								DjkscjModel.class);
						result = updateDjkscj(xh, djkscjList);
					}
					//ѧ���ɲ���������
					if (xsgbjlXgqzJson != null) {
						xsgbjlXgqzJson = "{data:" + xsgbjlXgqzJson + "}";
						xsgbjlList = JsonUtil.jsonToList(xsgbjlXgqzJson,
								XsgbjlModel.class);
						result = updateXsgbjl(xh, xsgbjlList);
					}
					//���ʵ���������
					if (shsjqkXgqzJson != null) {
						shsjqkXgqzJson = "{data:" + shsjqkXgqzJson + "}";
						shsjqkList = JsonUtil.jsonToList(shsjqkXgqzJson,
								ShsjqkModel.class);
						result = updateShsjqk(xh, shsjqkList);
					}
					
					//��������������Ϣ����
					if (cgcjjlXgqzJson != null) {
						cgcjjlXgqzJson = "{data:" + cgcjjlXgqzJson + "}";
						cgcjjlList = JsonUtil.jsonToList(cgcjjlXgqzJson,
								CgcjjlModel.class);
						result = updateCgcjjl(xh, cgcjjlList);
					}
					//�������ı���
					if (fblwXgqzJson != null) {
						fblwXgqzJson = "{data:" + fblwXgqzJson + "}";
						fblwList = JsonUtil.jsonToList(fblwXgqzJson,
								FblwModel.class);
						result = updateFblw(xh, fblwList);
					}
					//������Ŀ����
					if (kyxmXgqzJson != null) {
						kyxmXgqzJson = "{data:" + kyxmXgqzJson + "}";
						kyxmList = JsonUtil.jsonToList(kyxmXgqzJson,
								KyxmModel.class);
						result = updateKyxm(xh, kyxmList);
					}
					//�����ɹ�����
					if (qtcgXgqzJson != null) {
						qtcgXgqzJson = "{data:" + qtcgXgqzJson + "}";
						qtcgList = JsonUtil.jsonToList(qtcgXgqzJson,
								QtcgModel.class);
						result = updateQtcg(xh, qtcgList);
					}
					//���������
					if (hjqkXakjXgqzJson != null) {
						hjqkXakjXgqzJson = "{data:" + hjqkXakjXgqzJson + "}";
						hjqkXakjList = JsonUtil.jsonToList(hjqkXakjXgqzJson,
								HjqkModel.class);
						result = updateHjqkXakj(xh, hjqkXakjList);
					}
					
				}

				// ���ֹ�ҵְҵ����ѧԺ
				if ("12903".equals(Base.xxdm)) {

					if (ggsxjlXgqzJson != null) {
						ggsxjlXgqzJson = "{data:" + ggsxjlXgqzJson + "}";
						ggsxjlList = JsonUtil.jsonToList(ggsxjlXgqzJson,
								GgsxjlModel.class);
						result = updateGgsxjl(xh, ggsxjlList);// ����ʵϰ��Ϣ����
					}

					if (dgsxjlXgqzJson != null) {
						dgsxjlXgqzJson = "{data:" + dgsxjlXgqzJson + "}";
						dgsxjlList = JsonUtil.jsonToList(dgsxjlXgqzJson,
								DgsxjlModel.class);
						result = updateDgsxjl(xh, dgsxjlList);// ����ʵϰ��Ϣ����
					}
				}
				//ɽ���ƾ�
				if("10125".equalsIgnoreCase(Base.xxdm)) {
					if (kxyjXgqzJson != null) {
						kxyjXgqzJson = "{data:" + kxyjXgqzJson + "}";
						kxyjList = JsonUtil.jsonToList(kxyjXgqzJson,KxyjModel.class);
						result = updateKxyj(xh, kxyjList);  //��ѧ�о�����
					}
					if (ktyjXgqzJson != null) {
						ktyjXgqzJson = "{data:" + ktyjXgqzJson + "}";
						ktyjList = JsonUtil.jsonToList(ktyjXgqzJson,KtyjModel.class);
						result = updateKtyj(xh, ktyjList);  //�����о�����
					}
					if (cxcyxmXgqzJson != null) {
						cxcyxmXgqzJson = "{data:" + cxcyxmXgqzJson + "}";
						cxcyxmList = JsonUtil.jsonToList(cxcyxmXgqzJson,CxcyxmModel.class);
						result = updateCxcyxm(xh, cxcyxmList);  //���´�ҵ��Ŀ����
					}
					if (xkjsXgqzJson != null) {
						xkjsXgqzJson = "{data:" + xkjsXgqzJson + "}";
						xkjsList = JsonUtil.jsonToList(xkjsXgqzJson,XkjsModel.class);
						result = updateXkjs(xh, xkjsList);  //ѧ�ƾ�������
					}
					if (jnzsXgqzJson != null) {
						jnzsXgqzJson = "{data:" + jnzsXgqzJson + "}";
						jnzsList = JsonUtil.jsonToList(jnzsXgqzJson,JnzsModel.class);
						result = updateJnzs(xh, jnzsList);  //����֤�鱣��
					}
				}
				
				// ����ʦ����ѧ
				if ("10511".equals(Base.xxdm)) {

					if (grjlXgqzJson != null) {
						grjlXgqzJson = "{data:" + grjlXgqzJson + "}";
						grjlList = JsonUtil.jsonToList(grjlXgqzJson,
								GrjlModel.class);
						result = updateXsGrjl(xh, grjlList);// ���˼�������
					}

					if (gzjlXgqzJson != null) {
						gzjlXgqzJson = "{data:" + gzjlXgqzJson + "}";
						gzjlList = JsonUtil.jsonToList(gzjlXgqzJson,
								GzjlModel.class);
						result = updateXsGzjl(xh, gzjlList);// ������������
					}
				}

				// ����ѧԺ
				if ("11354".equals(Base.xxdm)) {
					
					if (xsyhxxXgqzJson != null) {
						xsyhxxXgqzJson = "{data:" + xsyhxxXgqzJson + "}";
						xsyhxxList = JsonUtil.jsonToList(xsyhxxXgqzJson,
								XsyhxxModel.class);
						result = updateXsyhxx(xh, xsyhxxList);// ѧ��������Ϣ����
					}
				}

				// ������ҽҩ��ѧ
				if ("10026".equals(Base.xxdm)) {
					
					if (grhjxxXgqzJson != null) {
						grhjxxXgqzJson = "{data:" + grhjxxXgqzJson + "}";
						grhjxxList = JsonUtil.jsonToList(grhjxxXgqzJson,
								GrhjxxModel.class);
						result = updateGrhjxx(xh, grhjxxList);// ���˽�����Ϣ����
					}
				}
			} else {
				if (jtcyxxZdzJson != null) {
					jtcyxxZdzJson = "{data:" + jtcyxxZdzJson + "}";
					jtcyxxList = JsonUtil.jsonToList(jtcyxxZdzJson,
							JtcyxxModel.class);
					result = updateJtcyxx(xh, jtcyxxList);// ��ͥ��Ա��Ϣ����
				}

				if (xlshjlZdzJson != null) {
					xlshjlZdzJson = "{data:" + xlshjlZdzJson + "}";
					xlshjlcxxList = JsonUtil.jsonToList(xlshjlZdzJson,
							XlshjlModel.class);
					result = updateXlshjlxx(xh, xlshjlcxxList);// ѧ����ᾭ����Ϣ����
				}

				if (pxxxZdzJson != null) {
					pxxxZdzJson = "{data:" + pxxxZdzJson + "}";
					pxxxList = JsonUtil
							.jsonToList(pxxxZdzJson, PxxxModel.class);
					result = updatePxxx(xh, pxxxList);// ��ѵ��Ϣ����
				}

				if (rxqhjqkZdzJson != null) {
					rxqhjqkZdzJson = "{data:" + rxqhjqkZdzJson + "}";
					rxqhjqkList = JsonUtil.jsonToList(rxqhjqkZdzJson,
							HjqkModel.class);
					result = updateHjqk(xh, rxqhjqkList);// ���������
				}

				if (hjqkxxNewZdzJson != null) {
					hjqkxxNewZdzJson = "{data:" + hjqkxxNewZdzJson + "}";
					hjqkList = JsonUtil.jsonToList(hjqkxxNewZdzJson,
							HjqkNewModel.class);
					result = updateHjqkNew(xh, hjqkList);// �����(��)����
				}
				
				if (xsxwhjqkZdzJson != null) {
					xsxwhjqkZdzJson = "{data:" + xsxwhjqkZdzJson + "}";
					xsxwhjqkList = JsonUtil.jsonToList(xsxwhjqkZdzJson,
							XsxwhjqkModel.class);
					result = updateXsxwhjqk(xh, xsxwhjqkList);// ѧ��У��������������·ְҵ����ѧԺ������
				}
				
				if("10466".equals(Base.xxdm)){
					//���ʵ��
					if (hnnyshsjZdzJson != null) {
						hnnyshsjZdzJson = "{data:" + hnnyshsjZdzJson + "}";
						hnnyshsjList = JsonUtil.jsonToList(hnnyshsjZdzJson,
								ShsjqkModel.class);
						result = updateShsjqk(xh, hnnyshsjList);
					}
				}
				//�����Ƽ���ѧ
				if("10704".equals(Base.xxdm)){
					//�ȼ�������Ϣ����
					if (djkscjZdzJson != null) {
						djkscjZdzJson = "{data:" + djkscjZdzJson + "}";
						djkscjList = JsonUtil.jsonToList(djkscjZdzJson,
								DjkscjModel.class);
						result = updateDjkscj(xh, djkscjList);
					}
					//ѧ���ɲ���������
					if (xsgbjlZdzJson != null) {
						xsgbjlZdzJson = "{data:" + xsgbjlZdzJson + "}";
						xsgbjlList = JsonUtil.jsonToList(xsgbjlZdzJson,
								XsgbjlModel.class);
						result = updateXsgbjl(xh, xsgbjlList);
					}
					//���ʵ���������
					if (shsjqkZdzJson != null) {
						shsjqkZdzJson = "{data:" + shsjqkZdzJson + "}";
						shsjqkList = JsonUtil.jsonToList(shsjqkZdzJson,
								ShsjqkModel.class);
						result = updateShsjqk(xh, shsjqkList);
					}
					//��������������Ϣ����
					if (cgcjjlZdzJson != null) {
						cgcjjlZdzJson = "{data:" + cgcjjlZdzJson + "}";
						cgcjjlList = JsonUtil.jsonToList(cgcjjlZdzJson,
								CgcjjlModel.class);
						result = updateCgcjjl(xh, cgcjjlList);
					}
					//�������ı���
					if (fblwZdzJson != null) {
						fblwZdzJson = "{data:" + fblwZdzJson + "}";
						fblwList = JsonUtil.jsonToList(fblwZdzJson,
								FblwModel.class);
						result = updateFblw(xh, fblwList);
					}
					//������Ŀ����
					if (kyxmZdzJson != null) {
						kyxmZdzJson = "{data:" + kyxmZdzJson + "}";
						kyxmList = JsonUtil.jsonToList(kyxmZdzJson,
								KyxmModel.class);
						result = updateKyxm(xh, kyxmList);
					}
					//�����ɹ�����
					if (qtcgZdzJson != null) {
						qtcgZdzJson = "{data:" + qtcgZdzJson + "}";
						qtcgList = JsonUtil.jsonToList(qtcgZdzJson,
								QtcgModel.class);
						result = updateQtcg(xh, qtcgList);
					}
				}

				// ���ֹ�ҵְҵ����ѧԺ
				if ("12903".equals(Base.xxdm)) {

					if (ggsxjlZdzJson != null) {
						ggsxjlZdzJson = "{data:" + ggsxjlZdzJson + "}";
						ggsxjlList = JsonUtil.jsonToList(ggsxjlZdzJson,
								GgsxjlModel.class);
						result = updateGgsxjl(xh, ggsxjlList);// ����ʵϰ��Ϣ����
					}

					if (dgsxjlZdzJson != null) {
						dgsxjlZdzJson = "{data:" + dgsxjlZdzJson + "}";
						dgsxjlList = JsonUtil.jsonToList(dgsxjlZdzJson,
								DgsxjlModel.class);
						result = updateDgsxjl(xh, dgsxjlList);// ����ʵϰ��Ϣ����
					}
				}
				
				//ɽ���ƾ�
				if("10125".equalsIgnoreCase(Base.xxdm)) {
					if (kxyjZdzJson != null) {
						kxyjZdzJson = "{data:" + kxyjZdzJson + "}";
						kxyjList = JsonUtil.jsonToList(kxyjZdzJson,	KxyjModel.class);
						result = updateKxyj(xh, kxyjList);  //��ѧ�о�����
					}
					if (ktyjZdzJson != null) {
						ktyjZdzJson = "{data:" + ktyjZdzJson + "}";
						ktyjList = JsonUtil.jsonToList(ktyjZdzJson,	KtyjModel.class);
						result = updateKtyj(xh, ktyjList);  //�����о�����
					}
					if (cxcyxmZdzJson != null) {
						cxcyxmZdzJson = "{data:" + cxcyxmZdzJson + "}";
						cxcyxmList = JsonUtil.jsonToList(cxcyxmZdzJson,	CxcyxmModel.class);
						result = updateCxcyxm(xh, cxcyxmList);  //���´�ҵ��Ŀ����
					}
					if (xkjsZdzJson != null) {
						xkjsZdzJson = "{data:" + xkjsZdzJson + "}";
						xkjsList = JsonUtil.jsonToList(xkjsZdzJson,	XkjsModel.class);
						result = updateXkjs(xh, xkjsList);  //ѧ�ƾ�������
					}
					if (jnzsZdzJson != null) {
						jnzsZdzJson = "{data:" + jnzsZdzJson + "}";
						jnzsList = JsonUtil.jsonToList(jnzsZdzJson,	JnzsModel.class);
						result = updateJnzs(xh, jnzsList);  //����֤�鱣��
					}
				}

				// ����ʦ����ѧ
				if ("10511".equals(Base.xxdm)) {

					if (grjlZdzJson != null) {
						grjlZdzJson = "{data:" + grjlZdzJson + "}";
						grjlList = JsonUtil.jsonToList(grjlZdzJson,
								GrjlModel.class);
						result = updateXsGrjl(xh, grjlList);// ���˼�������
					}

					if (gzjlZdzJson != null) {
						gzjlZdzJson = "{data:" + gzjlZdzJson + "}";
						gzjlList = JsonUtil.jsonToList(gzjlZdzJson,
								GzjlModel.class);
						result = updateXsGzjl(xh, gzjlList);// ������������
					}
				}

				// ����ѧԺ
				if ("11354".equals(Base.xxdm)) {
					
					if (xsyhxxZdzJson != null) {
						xsyhxxZdzJson = "{data:" + xsyhxxZdzJson + "}";
						xsyhxxList = JsonUtil.jsonToList(xsyhxxZdzJson,
								XsyhxxModel.class);
						result = updateXsyhxx(xh, xsyhxxList);// ѧ��������Ϣ����
					}
				}

				// ������ҽҩ��ѧ
				if ("10026".equals(Base.xxdm)) {
					
					if (grhjxxZdzJson != null) {
						grhjxxZdzJson = "{data:" + grhjxxZdzJson + "}";
						grhjxxList = JsonUtil.jsonToList(grhjxxZdzJson,
								GrhjxxModel.class);
						result = updateGrhjxx(xh, grhjxxList);// ���˽�����Ϣ����
					}
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @����:����ѧ�Ų�ѯѧ���춯�б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXjydList(String xh) {
		return dao.getXjydList(xh);
	}


	/**
	 * @����:ѧ���޸ļ�ͥ��Աʱ��Ҫ������ʷ��¼
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/25 11:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh, list, user]
	 * @return: boolean
	 */
	public boolean updateJtcyxx(String xh, List<JtcyxxModel> list,User user)
			throws Exception {
		return dao.updateJtcyxx(xh, list,user);
	}

	/**
	 *
	 * @����:��ͥ��Ա��Ϣ�޸�
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-19 ����06:43:30
	 * @�޸ļ�¼:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateJtcyxx(String xh, List<JtcyxxModel> list)
			throws Exception {
		return dao.updateJtcyxx(xh, list);
	}


	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ��ͥ��Ա��Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-19 ����05:00:01
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxList(String xh)
			throws Exception {
		return dao.getJtcyxxList(xh);
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ��ͥ��Ա��Ϣ������ʾ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-19 ����05:00:01
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxXsList(String xh)
			throws Exception {
		return dao.getJtcyxxXsList(xh);
	}
	
	public List<HashMap<String, String>> getJtcyList(String xh,int n)throws Exception {
		List<HashMap<String, String>> list=dao.getJtcyList(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}

	/**
	 * 
	 * @����: ͨ��ѧ�Ż�ȡѧ��ѧ����ᾭ����Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-23 ����05:05:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXlshjlList(String xh) {
		return dao.getXlshjlList(xh);
	}

	/**
	 * @������ѧ�������� ѧ����ᾭ��ֻȡ4��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��13�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getXlshjlList4line(String xh) {
		return dao.getXlshjlList4line(xh);
	}
	
	public List<HashMap<String, String>> getXlshjlList(String xh,int n) {
		List<HashMap<String, String>> list=dao.getXlshjlList(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
	/**
	 * 
	 * @����:����ѧ��ѧ����ᾭ����Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-24 ����09:15:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateXlshjlxx(String xh, List<XlshjlModel> list)
			throws Exception {
		return dao.updateXlshjlxx(xh, list);
	}

	/**
	 * 
	 * @����:��ѵ��Ϣ�޸�
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-18 ����02:54:58
	 * @�޸ļ�¼:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updatePxxx(String xh, List<PxxxModel> list) throws Exception {
		return dao.updatePxxx(xh, list);
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ��ѵ��Ϣ��Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-18 ����02:55:33
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPxxxList(String xh)
			throws Exception {
		return dao.getPxxxList(xh);
	}

	/**
	 * 
	 * @����:������޸�
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-18 ����02:54:58
	 * @�޸ļ�¼:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateHjqk(String xh, List<HjqkModel> list) throws Exception {
		return dao.updateHjqk(xh, list);
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ����Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-18 ����02:55:33
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkList(String xh)
			throws Exception {
		return dao.getHjqkList(xh);
	}
	
	/**
	 * @����:������޸ģ��£�
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-3 ����01:53:07
	 * @�޸ļ�¼:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateHjqkNew(String xh, List<HjqkNewModel> list) throws Exception {
		return dao.updateHjqkNew(xh, list);
	}
	
	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ��������£�
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2017-10-23 ����10:24:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param n
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxHjqkNewList(String xh,int n)
	throws Exception {
		List<HashMap<String, String>> list = dao.getXsxxHjqkNewList(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
	/**
	 * @����:ͨ��ѧ�Ų�ѯ����Ϣ���£�
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-3 ����01:53:07
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkNewList(String xh)
	throws Exception {
		return dao.getHjqkNewList(xh);
	}
	
	/**
	 * 
	 * @����: ����ʵϰ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-17 ����10:11:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGgsxjlList(String xh) throws Exception {
		return dao.getGgsxjlList(xh);
	}
	
	/**
	 * 
	 * @����: ����ʵϰ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-17 ����11:07:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateGgsxjl(String xh, List<GgsxjlModel> list) throws Exception {
		return dao.updateGgsxjl(xh, list);
	}
	
	/**
	 * 
	 * @����: ����ʵϰ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-17 ����10:11:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDgsxjlList(String xh) throws Exception {
		return dao.getDgsxjlList(xh);
	}
	
	/**
	 * 
	 * @����: ����ʵϰ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-17 ����11:07:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateDgsxjl(String xh, List<DgsxjlModel> list) throws Exception {
		return dao.updateDgsxjl(xh, list);
	}
	
	/**
	 * 
	 * @����: ���˼������鿴��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-19 ����02:25:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsGrjlList(String xh) throws Exception {
		return dao.getXsGrjlList(xh);
	}

	
	/**
	 * 
	 * @����: ���˼������޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-19 ����02:28:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsGrjl(String xh, List<GrjlModel> list) throws Exception {
		return dao.updateXsGrjl(xh, list);
	}
	
	/**
	 * 
	 * @����: �����������鿴��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-19 ����02:25:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsGzjlList(String xh) throws Exception {
		return dao.getXsGzjlList(xh);
	}
	

	
	/**
	 * 
	 * @����: �����������޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-19 ����02:28:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsGzjl(String xh, List<GzjlModel> list) throws Exception {
		return dao.updateXsGzjl(xh, list);
	}
	
	/**
	 * 
	 * @����: ѧ��������Ϣ���鿴��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-16 ����03:40:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsyhxxList(String xh) throws Exception {
		return dao.getXsyhxxList(xh);
	}
	
	/**
	 * 
	 * @����: ѧ��������Ϣ���޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-16 ����03:41:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsyhxx(String xh, List<XsyhxxModel> list) throws Exception {
		return dao.updateXsyhxx(xh, list);
	}
	
	/**
	 * 
	 * @����: ���˽�����Ϣ���鿴��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-6 ����03:46:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrhjxxList(String xh) throws Exception {
		return dao.getGrhjxxList(xh);
	}
	
	/**
	 * 
	 * @����: ���ѧ���춯��ʾ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-27 ����04:49:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZdxjydList(String xh) throws Exception { 
		return dao.getZdxjydList(xh);
	}
	
	/**
	 * 
	 * @����: ���˽�����Ϣ���޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-6 ����03:46:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateGrhjxx(String xh, List<GrhjxxModel> list) throws Exception {
		return dao.updateGrhjxx(xh, list);
	}
	
	/**
	 * 
	 * @����:��ȡѧ���ɲ���Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����10:28:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXsgbxxList(String xh)
			throws Exception {
		return dao.getXsgbxxList(xh);
	}

	/**
	 * 
	 * @����:��ȡ������У��Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����10:28:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getJqlxxxList(String xh)
			throws Exception {
		return dao.getJqlxxxList(xh);
	}

	/**
	 * 
	 * @����:��ȡ�����Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����02:14:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getQjjgxxList(String xh)
			throws Exception {
		return dao.getQjjgxxList(xh);
	}

	/**
	 * 
	 * @����:��ȡ֤��������Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����02:28:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getZjbbxxList(String xh)
			throws Exception {
		return dao.getZjbbxxList(xh);
	}

	/**
	 * 
	 * @����:��ȡ���Żݿ���Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����02:28:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHcyhkxxList(String xh)
			throws Exception {
		return dao.getHcyhkxxList(xh);
	}

	/**
	 * 
	 * @����:��ȡѧ�ڱ���ע����Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����02:28:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXqbdzcxxList(String xh)
			throws Exception {
		return dao.getXqbdzcxxList(xh);
	}

	/**
	 * 
	 * @����:��ȡ��ԢΥ����Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����03:16:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getGywjxxList(String xh)
			throws Exception {
		return dao.getGywjxxList(xh);
	}

	/**
	 * 
	 * @����:��ȡ���һ�ܹ�Ԣ���Ų������ύ����ճ̵����һ�ܣ��㽭�����Ի���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-7-2 ����03:06:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getLgGypyxxList(String xh)
			throws Exception {
		return dao.getLgGypyxxList(xh);
	}

	/**
	 * 
	 * @����:��ȡ���÷�����Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����03:16:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getFyffxxList(String xh)
			throws Exception {
		return dao.getFyffxxList(xh);
	}

	/**
	 * 
	 * @����:��ȡ������Ϣ�б�
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-13 ����10:00:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getKqxxList(String xh)
			throws Exception {
		return dao.getKqxxList(xh);
	}
	
	public List<HashMap<String, String>> getZwzxKqxxList(String xh)
	throws Exception {
		return dao.getZwzxKqxxList(xh);
}
	/**
	 * 
	 * @����:���ĳ�����Ʒ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-4 ����03:39:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getWpsqjgList(String xh)
	throws Exception {
		return dao.getWpsqjgList(xh);
}
	/**
	 * 
	 * @����:��ɫͨ��������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-4 ����03:40:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLstdList(String xh)
	throws Exception {
		return dao.getWpsqjgList(xh);
}

	/**
	 * 
	 * @����:��ȡ��Ԣ�����֣������Ի�������ȡ���һ��¼�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-23 ����11:03:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getGywsfList(String xh)
			throws Exception {
		return dao.getGywsfList(xh);
	}
	
	/**
	 * ��ȡ��Ԣ�������б����м�¼��
	 */
	public List<HashMap<String, String>> getGywsfAllList(String xh) {
		return dao.getGywsfAllList(xh);
	}
	
	/**
	 * @����:����ģ�����ƣ���Ҫ������ ��ѧ���ɲ���ѯ����ϲ�
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-16 ����08:41:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String , String>> getPjXsgbxxList(String xh) throws Exception{
		return dao.getPjXsgbxxList(xh);
	}	
	
	/**
	 * 
	 * @����: �ж��Ƿ�ͱ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-10-22 ����05:04:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public String getSfdb(String xh) {
		
		return dao.getSfdb(xh);
	}
	
	
	/**
	 * 
	 * @����: ��ȡ��ǰϵͳ������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-10-23 ����10:33:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDqrq(String xh) {
		
		return dao.getDqrq(xh);
	}

	/**
	 * @throws Exception  
	 * @����:�㽭��ѧѧ԰��ѯ�����Ը�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-11 ����11:41:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXycx(XsxxglModel model, User user) throws Exception {
		return dao.getXycx(model,user);
	}
	
	public List<HashMap<String, String>> getTyqntjList(XsxxglModel exporModel,User user) throws Exception{
		return dao.getTyqntjList(exporModel,user);
	}
	/**
	 * 
	 * @����:��Ա������Ϣͳ�Ʊ�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-8 ����09:43:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param resultList
	 * @param os
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void tyqntjExport(List<HashMap<String,String>> resultList, OutputStream os, User user) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("��Ա������Ϣͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 9, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, "��Ա������Ϣͳ�Ʊ�", 0, 0, 9, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, "��λ��", 0, 1, 9, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ws.mergeCells(0, 0, 9, 0);
		ws.mergeCells(0, 1, 9, 1);
		ws.mergeCells(0, 2, 0, 3);
		ws.mergeCells(1, 2, 1, 3);
		ws.mergeCells(2, 2, 2, 3);
		ws.mergeCells(3, 2, 3, 3);
		ws.mergeCells(4, 2, 5, 2);
		ws.mergeCells(6, 2, 6, 3);
		ws.mergeCells(7, 2, 8, 2);
		ws.mergeCells(9, 2, 9, 3);
		ws.mergeCells(10, 2, 10, 3);
		ws.mergeCells(11, 2, 11, 3);
		
		ws.addCell(new Label(0, 2, "�꼶", wcf2));
		ws.addCell(new Label(1, 2, "��֧����", wcf2));
		ws.addCell(new Label(2, 2, "ѧ������", wcf2));
		ws.addCell(new Label(3, 2, "����ѧ������", wcf2));
		ws.addCell(new Label(4, 2, "��Ա��", wcf2));
		ws.addCell(new Label(4, 3, "��", wcf2));
		ws.addCell(new Label(5, 3, "Ů", wcf2));
		ws.addCell(new Label(6, 2, "ר��ѧ������", wcf2));
		ws.addCell(new Label(7, 2, "��Ա��", wcf2));
		ws.addCell(new Label(7, 3, "��", wcf2));
		ws.addCell(new Label(8, 3, "Ů", wcf2));
		ws.addCell(new Label(9, 2, "����Ա��", wcf2));
	    int sumTzbs=0;
	    int sumXszs=0;
	    int sumBks=0;
	    int sumZks=0;
	    int sumbkty_man=0;
	    int sumbkty_woman=0;
	    int sumzkty_man=0;
	    int sumzkty_woman=0;
	    int sumzkty_ftys=0;
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				sumTzbs = sumTzbs+Integer.parseInt(map.get("tzbs"));
				sumXszs = sumXszs+Integer.parseInt(map.get("xszs"));
				sumBks = sumBks+Integer.parseInt(map.get("bkxsrs"));
				sumZks=sumZks+Integer.parseInt(map.get("zkxsrs"));
				sumbkty_man = sumbkty_man+Integer.parseInt(map.get("bktys_man"));
				sumbkty_woman = sumbkty_woman+Integer.parseInt(map.get("bktys_woman"));
				sumzkty_man = sumzkty_man+Integer.parseInt(map.get("zktys_man"));
				sumzkty_woman = sumzkty_woman+Integer.parseInt(map.get("zktys_woman"));
				sumzkty_ftys = sumzkty_ftys+Integer.parseInt(map.get("ftys"));
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 15);
				ws.setColumnView(4, 15);
				ws.setColumnView(5, 20);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 15);
				ws.setColumnView(8, 15);
				ws.setColumnView(9, 10);
				ws.addCell(new Label(0, 4 + i, map.get("nj"), wcf2));
				ws.addCell(new Label(1, 4 + i, map.get("tzbs"), wcf2));
				ws.addCell(new Label(2, 4 + i, map.get("xszs"), wcf2));
				ws.addCell(new Label(3, 4 + i, map.get("bkxsrs"), wcf2));
				ws.addCell(new Label(4, 4 + i, map.get("bktys_man"), wcf2));
				ws.addCell(new Label(5, 4 + i, map.get("bktys_woman"), wcf2));
				ws.addCell(new Label(6, 4 + i, map.get("zkxsrs"), wcf2));
				ws.addCell(new Label(7, 4 + i, map.get("zktys_man"), wcf2));
				ws.addCell(new Label(8, 4 + i, map.get("zktys_woman"), wcf2));
				ws.addCell(new Label(9, 4 + i, map.get("ftys"), wcf2));
			}
		}
		ws.addCell(new Label(0, 4 + resultList.size(), "�ϼ�", wcf2));
		ws.addCell(new Label(1, 4 + resultList.size(), sumTzbs+"", wcf2));
		ws.addCell(new Label(2, 4 + resultList.size(), sumXszs+"", wcf2));
		ws.addCell(new Label(3, 4 + resultList.size(), sumBks+"", wcf2));
		ws.addCell(new Label(4, 4 + resultList.size(), sumbkty_man+"", wcf2));
		ws.addCell(new Label(5, 4 + resultList.size(), sumbkty_woman+"", wcf2));
		ws.addCell(new Label(6, 4 + resultList.size(), sumZks+"", wcf2));
		ws.addCell(new Label(7, 4 + resultList.size(), sumzkty_man+"", wcf2));
		ws.addCell(new Label(8, 4 + resultList.size(), sumzkty_woman+"", wcf2));
		ws.addCell(new Label(9, 4 + resultList.size(), sumzkty_ftys+"", wcf2));
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-8 ����06:12:14
	 * @����:�㽭����ѧԺѧ����У�������
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File getZjjcXsbxExcel(XsxxglModel model, User user) throws Exception{
		
		//����Excle�ļ�
		File tempxls = new File(System.getProperty("java.io.tmpdir")+"/"+System.currentTimeMillis()+".xls");
		WritableWorkbook wwb = Workbook.createWorkbook(tempxls);
		WritableSheet ws = wwb.createSheet("��ҵ����У�������һ����", 0);
		
	    //���ñ�ͷ��ʽ
		WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 9, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		//���ñ�ͷ
		ws.mergeCells(0, 0, 0, 1);
		ws.mergeCells(1, 0, 1, 1);
		ws.mergeCells(2, 0, 2, 1);
		ws.mergeCells(3, 0, 3, 1);
		ws.mergeCells(4, 0, 4, 1);
		ws.mergeCells(5, 0, 5, 1);
		ws.mergeCells(6, 0, 6, 1);
		ws.mergeCells(7, 0, 7, 1);
		ws.mergeCells(8, 0, 8, 1);
		ws.mergeCells(9, 0, 9, 1);
		ws.mergeCells(10, 0, 10, 1);
		ws.mergeCells(11, 0, 11, 1);
		ws.mergeCells(12, 0, 12, 1);
		ws.mergeCells(13, 0, 21, 0);
		ws.mergeCells(22, 0, 30, 0);
		ws.mergeCells(31, 0, 31, 1);
		ws.mergeCells(32, 0, 32, 1);
		ws.mergeCells(33, 0, 33, 1);
		ws.mergeCells(34, 0, 34, 1);
		
		ws.addCell(new Label(0, 0, "���", wcf));
		ws.addCell(new Label(1, 0, "��Դ��", wcf));
		ws.addCell(new Label(2, 0, "����", wcf));
		ws.addCell(new Label(3, 0, "����", wcf));
		ws.addCell(new Label(4, 0, "ѧ��", wcf));
		ws.addCell(new Label(5, 0, "����", wcf));
		ws.addCell(new Label(6, 0, "�Ա�", wcf));
		ws.addCell(new Label(7, 0, "����", wcf));
		ws.addCell(new Label(8, 0, "��������", wcf));
		ws.addCell(new Label(9, 0, "������ò", wcf));
		ws.addCell(new Label(10, 0, "רҵ", wcf));
		ws.addCell(new Label(11, 0, "���֤��", wcf));
		ws.addCell(new Label(12, 0, "���θɲ����", wcf));
		ws.addCell(new Label(13, 0, "ѧϰ�ܳɼ�", wcf));
		ws.addCell(new Label(13, 1, "һ", wcf));
		ws.addCell(new Label(14, 1, "��", wcf));
		ws.addCell(new Label(15, 1, "��", wcf));
		ws.addCell(new Label(16, 1, "��", wcf));
		ws.addCell(new Label(17, 1, "��", wcf));
		ws.addCell(new Label(18, 1, "��", wcf));
		ws.addCell(new Label(19, 1, "��", wcf));
		ws.addCell(new Label(20, 1, "ƽ����", wcf));
		ws.addCell(new Label(21, 1, "����", wcf));
		ws.addCell(new Label(22, 0, "�ۺϲ����ܳɼ�", wcf));
		ws.addCell(new Label(22, 1, "һ", wcf));
		ws.addCell(new Label(23, 1, "��", wcf));
		ws.addCell(new Label(24, 1, "��", wcf));
		ws.addCell(new Label(25, 1, "��", wcf));
		ws.addCell(new Label(26, 1, "��", wcf));
		ws.addCell(new Label(27, 1, "��", wcf));
		ws.addCell(new Label(28, 1, "��", wcf));
		ws.addCell(new Label(29, 1, "ƽ����", wcf));
		ws.addCell(new Label(30, 1, "����", wcf));
		ws.addCell(new Label(31, 0, "�������", wcf));
		ws.addCell(new Label(32, 0, "Ӣ��ȼ�", wcf));
		ws.addCell(new Label(33, 0, "������ȼ�", wcf));
		ws.addCell(new Label(34, 0, "��ע", wcf));

		
        //�������ݼ���ʽ
    	WritableCellFormat cellFormat=new WritableCellFormat();
    	cellFormat = ExcelMethods.getWcf(WritableFont.ARIAL,9, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		List<HashMap<String,String>> result = dao.getXszxData(model, user);
		
		int rindex = 2;  //��
		int cindex = 0;  //��
		for(int i=0;i<result.size();i++){
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("rn"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("sydqmc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("syd"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("bjmc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("xh"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("xm"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("xb"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("mzmc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("csrq"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zzmmmc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zymc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("sfzh"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zwmc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj1"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj2"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj3"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj4"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj5"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj6"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj7"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cjpjf"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cjpm"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj1"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj2"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj3"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj4"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj5"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj6"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj7"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcjpjf"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcjpm"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("jcqk"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("yydj"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("jsjdj"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get(""));
			rindex++;
			cindex=0;
		}
		wwb.write();
		wwb.close();
		return tempxls;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-8 ����06:12:14
	 * @����:�㽭����ѧԺѧ����У�������
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File getZjjcZcfExcel(String xh) throws Exception{
		
		//����Excle�ļ�
		File tempxls = new File(System.getProperty("java.io.tmpdir")+"/"+System.currentTimeMillis()+".xls");
		WritableWorkbook wwb = Workbook.createWorkbook(tempxls);
		WritableSheet ws = wwb.createSheet("ѧ���ۺ����ʲ������ܱ�", 0);
		ws.setColumnView(0, 5);
		ws.setColumnView(5, 14);
		ws.setColumnView(6, 14);
		ws.setColumnView(7, 16);
		ws.setRowView(0, 460); 
		ws.setRowView(1, 380); 
		ws.setRowView(2, 380); 
	    //���ñ�ͷ��ʽ
		WritableCellFormat title = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		title = ExcelMethods.getWcf(WritableFont.ARIAL, 18, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.NONE);
		ws.mergeCells(1, 0, 7, 0);
	
		addCell(ws,1,0,title,"�㽭����ѧԺѧ���ۺ����ʲ������ܱ�");
		
		//�����в���ʽ
		WritableCellFormat center = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		center = ExcelMethods.getWcf(WritableFont.ARIAL, 14, false, Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.NONE);
		
		
		//�����в���ʽ
		WritableCellFormat content = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		content = ExcelMethods.getWcf(WritableFont.ARIAL, 12, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		//��ȡѧ����Ϣ
		HashMap<String, String> xhs = dao.getXsxxByXh(xh);
		
		addCell(ws,1,1,center,"����:");
		addCell(ws,2,1,center,xhs.get("xm"));
		addCell(ws,3,1,center,"ѧ��:");
		addCell(ws,4,1,center,xhs.get("xh"));
		addCell(ws,5,1,center,"���:");
		addCell(ws,6,1,center,xhs.get("xymc"));
		addCell(ws,7,1,center,"����:"+xhs.get("bjmc"));
		addCell(ws,1,2,content,"ѧ��");
		addCell(ws,2,2,content,"��Ŀ");
		addCell(ws,3,2,content,"�ɼ�");
		addCell(ws,4,2,content,"�ȼ�");
		addCell(ws,5,2,content,"�������");
		addCell(ws,6,2,content,"������");
		addCell(ws,7,2,content,"����ǩ��");
		
		//ѧ�ڵı�ͷ
		ws.mergeCells(1, 3, 1, 6);
		addCell(ws,1,3,content,"��           һ          ѧ           ��");
		ws.mergeCells(1, 7, 1, 10);
		addCell(ws,1,7,content,"��           ��          ѧ           ��");
		ws.mergeCells(1, 11, 1, 14);
		addCell(ws,1,11,content,"��           ��          ѧ           ��");
		ws.mergeCells(1, 15, 1, 18);
		addCell(ws,1,15,content,"��           ��          ѧ           ��");
		ws.mergeCells(1, 19, 1, 22);
		addCell(ws,1,19,content,"��           ��          ѧ           ��");
		ws.mergeCells(1, 23, 1, 26);
		addCell(ws,1,23,content,"��           ��         ѧ           ��");
		ws.mergeCells(1, 27, 1, 30);
		addCell(ws,1,27,content,"��           ��         ѧ           ��");
		ws.mergeCells(1, 31, 1, 34);
		addCell(ws,1,31,content,"��           ��         ѧ           ��");
		

		
        for(int i=0;i<32;i++){
        	if((i+1)%4==1){
        	  addCell(ws,2,i+3,content,"����"); 
        	}else if((i+1)%4==2){
        	  addCell(ws,2,i+3,content,"����");	
        	}else if((i+1)%4==3){
        	  addCell(ws,2,i+3,content,"����");
        	}else{
        	  addCell(ws,2,i+3,content,"�ۺ�");	
        	}
        	addCell(ws,3,i+3,content,"");
        	addCell(ws,4,i+3,content,"");
        	addCell(ws,5,i+3,content,"");
        	addCell(ws,6,i+3,content,"");
        	addCell(ws,7,i+3,content,"");
        	ws.setRowView(i+3, 380); 
        }
        
        ws.mergeCells(1, 35, 2, 37);
        addCell(ws,1,35,content,"ѧ��������         ���");
        ws.setRowView(37, 860); 
        ws.mergeCells(3, 35, 7, 37);
        addCell(ws,3,35,content,"");
        
        
		int rindex = 0;  //��
		int cindex = 3;  //��
		
		String[] xnlist = getAllxn(xhs.get("nj"));
		String[] xqlist = new String[]{"01","02"};
		ZcfsDao zcfsDao = new  ZcfsDao();
		//�����
		List<HashMap<String,String>> zcf = zcfsDao.getAllZcfListByXh(xh);
		
		for(int i=0;i<xnlist.length;i++){
			for(int j=0;j<xqlist.length;j++){
				rindex =(rindex > 0) ? rindex+4:rindex+3;
				for(int m=0;m<zcf.size();m++){
					if(zcf.get(m).get("xn").equals(xnlist[i])&&
							zcf.get(m).get("xq").equals(xqlist[j])){
						if(zcf.get(m).get("xmmc").contains("����")){
							 addCell(ws,cindex,rindex,content,zcf.get(m).get("fs"));
						}else if(zcf.get(m).get("xmmc").contains("����")){
							addCell(ws,cindex,rindex+1,content,zcf.get(m).get("fs"));
						}else if(zcf.get(m).get("xmmc").contains("����")){
							addCell(ws,cindex,rindex+2,content,zcf.get(m).get("fs"));
						}else if(zcf.get(m).get("xmmc").contains("�۲��ܷ�")){
							addCell(ws,cindex,rindex+3,content,zcf.get(m).get("fs"));
						}
						
					}
				}
			}
		}
		wwb.write();
		wwb.close();
		return tempxls;
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-10 ����04:12:14
	 * @����:�㽭����ѧԺѧ����У�������
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File getZjjcZhqkExcel(XsxxglModel model, User user) throws Exception{
		
		//����Excle�ļ�
		File tempxls = new File(System.getProperty("java.io.tmpdir")+"/"+System.currentTimeMillis()+".xls");
		WritableWorkbook book = Workbook.createWorkbook(tempxls);
		WritableSheet sheet = book.createSheet("������Ϣ�Ӽ����������ְ���", 0);
		
	    //���ñ�ͷ��ʽ
		WritableCellFormat title = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		title = ExcelMethods.getWcf(WritableFont.ARIAL,10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);
		sheet.mergeCells(0, 0, 45, 0);
		addCell(sheet,0,0,title,"ѧ����У�����ۺ����һ����");
		sheet.setRowView(0, 460); 
		sheet.mergeCells(0, 1, 0, 2);
		addCell(sheet,0,1,title,"ѧ��");
		sheet.mergeCells(1, 1, 1, 2);
		addCell(sheet,1,1,title,"����");
		sheet.mergeCells(2, 1, 3, 1);
		sheet.setRowView(1, 500); 
		sheet.setRowView(2, 800); 
		addCell(sheet,2,1,title,"��Դ��");
		addCell(sheet,2,2,title,"����");
		addCell(sheet,3,2,title,"������");
		sheet.mergeCells(4, 1, 4, 2);
		addCell(sheet,4,1,title,"�Ա�");
		sheet.mergeCells(5, 1, 5, 2);
		addCell(sheet,5,1,title,"����");
		sheet.mergeCells(6, 1, 6, 2);
		addCell(sheet,6,1,title,"רҵ����");
		sheet.mergeCells(7, 1, 7, 2);
		addCell(sheet,7,1,title,"�༶");
		sheet.mergeCells(8, 1, 13, 1);
		addCell(sheet,8,1,title,"���θɲ�");
		addCell(sheet,8,2,title,"��һѧ��");
		addCell(sheet,9,2,title,"�ڶ�ѧ��");
		addCell(sheet,10,2,title,"����ѧ��");
		addCell(sheet,11,2,title,"����ѧ��");
		addCell(sheet,12,2,title,"����ѧ��");
		addCell(sheet,13,2,title,"����ѧ��");
		sheet.mergeCells(14, 1, 14, 2);
		addCell(sheet,14,1,title,"������ò");
		sheet.mergeCells(15, 1, 15, 2);
		addCell(sheet,15,1,title,"����ʱ��");
		sheet.setColumnView(15, 14); 
		sheet.mergeCells(16, 1, 16, 2);
		addCell(sheet,16,1,title,"��չʱ��");
		sheet.mergeCells(17, 1, 21, 1);
		addCell(sheet,17,1,title,"ʡͳ��");
		addCell(sheet,17,2,title,"��ʻ����");
		addCell(sheet,18,2,title,"�����һ��");
		addCell(sheet,19,2,title,"���������");
		addCell(sheet,20,2,title,"Ӣ���ļ�");
		addCell(sheet,21,2,title,"Ӣ������");
		sheet.mergeCells(22, 1, 26, 1);
		addCell(sheet,22,1,title,"��һѧ��");
		sheet.mergeCells(27, 1, 31, 1);
		addCell(sheet,27,1,title,"�ڶ�ѧ��");
		sheet.mergeCells(32, 1, 36, 1);
		addCell(sheet,32,1,title,"����ѧ��");
		sheet.mergeCells(37, 1, 41, 1);
		addCell(sheet,37,1,title,"����ѧ��");
		int cindex = 22;
		for(int i=0;i<4;i++){
			addCell(sheet,cindex++,2,title,"�����ɼ�");
			addCell(sheet,cindex++,2,title,"�ۺϲ���");
			addCell(sheet,cindex++,2,title,"��ѧ��");
			addCell(sheet,cindex++,2,title,"����");
			addCell(sheet,cindex++,2,title,"����");
		}
		sheet.mergeCells(42, 1, 42, 2);
		addCell(sheet,42,1,title,"���֤��");
		sheet.setColumnView(42, 20); 
		sheet.mergeCells(43, 1, 43, 2);
		addCell(sheet,43,1,title,"��ͥ��ַ");
		sheet.setColumnView(43, 48); 
		sheet.mergeCells(44, 1, 44, 2);
		addCell(sheet,44,1,title,"��������");
		sheet.mergeCells(45, 1, 45, 2);
		addCell(sheet,45,1,title,"ѧ����ϵ��ʽ");
		sheet.setColumnView(45, 14); 
		
		//��ȡ�����
		List<HashMap<String, String>> result = dao.getZhqkData(model, user);
		
		//����������ʽ
		WritableCellFormat content = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		content = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		int rindex = 3;
		    cindex = 0;
		for(int i=0;i<result.size();i++){
			addCell(sheet,cindex++,rindex,content,result.get(i).get("xh"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("xm"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("ds"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("hjd"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("xb"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("kslbmc"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zymc"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("bjmc"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc5"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc6"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jdmc"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("kssj"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("fzsj"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jstgqk"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jsj1j"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jsj2j"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("yysj"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("yylj"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("dyf1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zpcj1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("hjnum1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jcqk1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("rynum1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("dyf2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zpcj2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("hjnum2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jcqk2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("rynum2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("dyf3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zpcj3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("hjnum3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jcqk3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("rynum3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("dyf4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zpcj4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("hjnum4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jcqk4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("rynum4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("sfzh"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jtdz"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jtyb"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("sjhm"));
			rindex++;
			cindex = 0;
		}
		book.write();
		book.close();
		return tempxls;
	}
		
	public void addCell(WritableSheet sheet, int c, int r, WritableCellFormat cellFormat, String str) throws RowsExceededException, WriteException{
		Label label = new Label(c,r,str);
		label.setCellFormat(cellFormat);
		sheet.addCell(label);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-9����06:12:14
	 * @����:�㽭����ѧԺ��ȡѧ������ѧ��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param nj
	 * @return
	 * File �������� 
	 * @throws
	 */
	public String[] getAllxn(String nj) {
		if (StringUtil.isNull(nj)) {
			return new String[] {};
		}
		int num;
		try {
			num = Integer.parseInt(nj);
		} catch (NumberFormatException e) {
			return new String[] {};
		}
		String[] xnlist = new String[4];
		
		for (int i = 0; i < 4; i++) {
			xnlist[i] = (num + i) + "-" + (num + i + 1);
		}
		return xnlist;
	}
	
	
	/**
	 * 
	 * @����:��ȡ�ɶ���Ժ�ĳɼ���Ϣ���ڴ�ӡѧ����
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-30 ����10:51:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCdtyCjxxByxh(String xh){
		return dao.getCdtyCjxxByxh(xh);
	}
	
	
	/**
	 * 
	 * @����:Ч��������д������ȡѧ����Ϣ
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-23 ����10:51:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxListForYdxg(XsxxglModel model,
			User user) throws Exception {
		return dao.getXsxxListForYdxg(model, user);
	}
	public List<HashMap<String, String>> getXsxxListForYdxgOfAll(XsxxglModel model,
			User user) throws Exception {
		model.getPages().setPageSize(Integer.MAX_VALUE);
		return dao.getXsxxListForYdxg(model, user);
	}

	
	/**
	 * ��ѯѧ��֤��Ϣ
	 */
	public HashMap<String, String> cxXsz(String csdm, User user) throws Exception{
		return dao.cxXsz(csdm, user);
	}
	
	/**
	 * ����ѧ��֤��Ϣ
	 */
	public boolean bcXsz(String csdm, String csz, User user) throws Exception{
		return dao.bcXsz(csdm, csz, user);
	}
	
	/**
	 * 
	 * @����: ȡѧ�����������4�������ݴ�ѧ���Ի���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-29 ����11:10:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkNewFourList(String xh) throws Exception {
		return dao.getHjqkNewFourList(xh);
	}
	
	/**
	 * @����: ͨ��ѧ�Ż�ȡ���˼��������ڽ��зָ�--���������߼�����ѧУѧ����
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-9-29 ����09:40:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
		public List<HashMap<String, String>> getGrjlList(String xh) {
			return dao.getGrjlList(xh);
		}
		/**
		 * @throws Exception 
		 * @����: ͨ��ѧ�ų�ȡѧҵ�ɼ�1--3ѧ��
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2015-10-15 ����09:23:15
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xh
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getXycjoneList(String xh) throws Exception {
			return dao.getXycjoneList(xh);
		}
		/**
		 * @����: ͨ��ѧ�ų�ȡѧҵ�ɼ�4--6ѧ��
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2015-10-19 ����11:33:35
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xh
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getXycjtwoList(String xh) throws Exception {
			return dao.getXycjtwoList(xh);
		}
		/**
		 * @����: �㽭��ѧ������ѧ�ǼǱ�_���Ի������˼������½�ȡ
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2015-12-4 ����11:49:22
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xh
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
			public List<HashMap<String, String>> getXxhgzjlList(String xh) {
				return dao.getXxhgzjlList(xh);
			}
		/**
		 * @����:��ְͨҵ����ѧԺ���еȵ�
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2016-4-1 ����11:30:25
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xh
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
			public List<HashMap<String, String>> getCxdt(String xh) {
				return dao.getCxdt(xh);
			}
		/**
		 * @����: ��ͨ�Ƽ�ְҵѧԺ���Ի������
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2016-5-10 ����11:00:26
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xh
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
			public List<HashMap<String, String>> gethjqk(String xh) {
				return dao.gethjqk(xh);
			}
		/**
		 * @����: ��ȡѧ��ѧ��ѧ�ڳɼ�
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2016-5-17 ����09:28:12
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xn
		 * @param xn1
		 * @param xq
		 * @param xh
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getXnXqcj(String xn,String xn1,String xq,String xh) throws Exception {
			return dao.getXnXqcj(xn,xn1,xq,xh);
		}
		/**
		 * @����: ��ȡѧ���γ�ѧ�ں���ѧ��
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2016-5-17 ����09:29:13
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xh
		 * @return
		 * HashMap<String,String> �������� 
		 * @throws
		 */
		public HashMap<String, String> getKcxq(String xh){
			return dao.getKcxq(xh);
		}
		
		/**
		 * @����: �ȼ����Գɼ�[��ͨ�Ƽ�]
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2016-5-26 ����07:45:32
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xh
		 * @return
		 * HashMap<String,String> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getDjkscj(String xh){
			return dao.getDjkscj(xh);
		}

		/**
		 * @����: ��ҵ����������
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2016-5-25 ����07:45:32
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xh
		 * @return
		 * HashMap<String,String> �������� 
		 * @throws
		 */
		public HashMap<String, String> getBysj(String xh){
			return dao.getBysj(xh);
		}
		
	/**
	 * 	
	 * @����: ��ѧ��ƽ�����㣨���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-23 ����10:02:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXfjdList(String xh,String nj)
			throws Exception {
		if(xgxt.utils.String.StringUtils.isNull(nj)) {
			nj = "0";
		}
		return dao.getXfjdList(xh,nj);
	}	
	
	/*
	 * ȡ��Ӧѧ���꼶
	 */
	public String getXsnj(String xh) {
		return dao.getXsnj(xh);
	}
	
	/**
	 * 
	 * @����: �����໪ѧ��֤��ӡ��ȡѧ����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-24 ����07:01:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXszdyxxXaph(String xh){
		return dao.getXszdyxxXaph(xh);
	}
	
	/**
	 * 
	 * @����: �񽱸����ϴ�����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-27 ����03:01:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFileData(String xh){
		return dao.getFileData(xh);
	}
	
	/**
	 * 
	 * @����:���˽�����Ϣ���޸ģ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-10 ����07:09:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @param hjid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateGrhjxxByjg(String xh, List<GrhjxxModel> list,String[] hjid) throws Exception {
		
		return dao.updateGrhjxxByjg(xh, list, hjid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���������޸���־��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-19 ����04:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ip
	 * @param czr
	 * @param xgmmxh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean runInsertMmXgLog(String ip,String czr,String xgmmxh) throws Exception{
		return dao.runInsertMmXgLog(ip, czr, xgmmxh);
	}
	
	/**
	 * @����:��ʦ��Ѹ��˼����͹��������ŵ�һ��ȡֵ
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-10-21 ����10:08:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrjlShjlList(String xh) {
		return dao.getGrjlShjlList(xh);
	}

	/** 
	 * @��������ѧ�о�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Object �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getKxyjList(String xh) throws Exception {
		return dao.getKxyjList(xh);
	}
	
	/**
	 * @��������ѧ�о�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateKxyj(String xh, List<KxyjModel> list) throws Exception {
		return dao.updateKxyj(xh, list);
	}
	
	/** 
	 * @�����������о�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Object �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getKtyjList(String xh) throws Exception {
		return dao.getKtyjList(xh);
	}
	
	/**
	 * @�����������о�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateKtyj(String xh, List<KtyjModel> list) throws Exception {
		return dao.updateKtyj(xh, list);
	}
	
	/** 
	 * @���������´�ҵ��Ŀ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Object �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getCxcyxmList(String xh) throws Exception {
		return dao.getCxcyxmList(xh);
	}
	
	/**
	 * @���������´�ҵ��Ŀ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateCxcyxm(String xh, List<CxcyxmModel> list) throws Exception {
		return dao.updateCxcyxm(xh, list);
	}
	
	/** 
	 * @������ѧ�ƾ���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Object �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXkjsList(String xh) throws Exception {
		return dao.getXkjsList(xh);
	}
	
	/**
	 * @������ѧ�ƾ���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateXkjs(String xh, List<XkjsModel> list) throws Exception {
		return dao.updateXkjs(xh, list);
	}
	
	/** 
	 * @����������֤��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Object �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getJnzsList(String xh) throws Exception {
		return dao.getJnzsList(xh);
	}
	
	/**
	 * @����������֤��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateJnzs(String xh, List<JnzsModel> list) throws Exception {
		return dao.updateJnzs(xh, list);
	}
	
	public List<HashMap<String, String>> getXsjfList(String xh) throws Exception {
		return dao.getXsjfList(xh);
	}

	/**
	 * @����:����ѧ����У�⣩�������������·ְҵ����ѧԺ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��27�� ����5:27:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param hjqkList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateXsxwhjqk(String xh, List<XsxwhjqkModel> hjqkList) throws Exception {
		return dao.updateXsxwhjqk(xh,hjqkList);
	}

	/** 
	 * @����:��ȡѧ����У�⣩�������������·ְҵ����ѧԺ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��31�� ����9:31:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsxwhjqkList(String xh) {
		return dao.getXsxwhjqkList(xh);
	}
	
	/**
	 * @����:����ȼ����Գɼ��������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����10:57:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param djkscjList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateDjkscj(String xh, List<DjkscjModel> djkscjList) throws Exception {
		return dao.updateDjkscj(xh,djkscjList);
	}

	/** 
	 * @����:��ȡ�ȼ�������ϢList(�����Ƽ���ѧ)
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����10:25:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDjkscjList(String xh) {
		return dao.getDjkscjList(xh);
	}
	public List<HashMap<String, String>> getZcfsList(String xh) {
		return dao.getZcfsList(xh);
	}
	/** 
	 * @����:��ȡѧ���ɲ�����List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����7:58:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsgbjlList(String xh) {
		return dao.getXsgbjlList(xh);
	}

	/** 
	 * @����:��ȡ���ʵ�����List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����7:58:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getShsjqkList(String xh) {
		return dao.getShsjqkList(xh);
	}

	/** 
	 * @����:��ȡ��������������ϢList�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����7:58:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCgcjjlList(String xh) {
		return dao.getCgcjjlList(xh);
	}

	/**
	 * @����:����ѧ���ɲ�����List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����8:38:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xsgbjlList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateXsgbjl(String xh, List<XsgbjlModel> xsgbjlList) throws Exception {
		return dao.updateXsgbjl(xh,xsgbjlList);
	}

	/**
	 * @����:�������ʵ�����List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����8:38:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param shsjqkList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateShsjqk(String xh, List<ShsjqkModel> shsjqkList) throws Exception {
		return dao.updateShsjqk(xh,shsjqkList);
	}

	/**
	 * @����:�����������������ϢList�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����8:39:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param cgcjjlList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateCgcjjl(String xh, List<CgcjjlModel> cgcjjlList) throws Exception {
		return dao.updateCgcjjl(xh,cgcjjlList);
	}

	/** 
	 * @����:��ȡ��������List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:04:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFblwList(String xh) {
		return dao.getFblwList(xh);
	}

	/** 
	 * @����:��ȡ������ĿList�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:05:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKyxmList(String xh) {
		return dao.getKyxmList(xh);
	}

	/** 
	 * @����:��ȡ�����ɹ�List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:05:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getQtcgList(String xh) {
		return dao.getQtcgList(xh);
	}

	/**
	 * @����:���淢������List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:29:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param fblwList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateFblw(String xh, List<FblwModel> fblwList) throws Exception {
		return dao.updateFblw(xh,fblwList);
	}

	/**
	 * @����:���������ĿList�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:29:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param kyxmList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateKyxm(String xh, List<KyxmModel> kyxmList) throws Exception {
		return dao.updateKyxm(xh,kyxmList);
	}

	/**
	 * @����:���������ɹ�List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:29:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param qtcgList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateQtcg(String xh, List<QtcgModel> qtcgList) throws Exception {
		return dao.updateQtcg(xh,qtcgList);
	}
	
	public boolean updateHjqkXakj(String xh, List<HjqkModel> hjqkList) throws Exception {
		return dao.updateHjqkListForXakj(xh, hjqkList);
	}
	
	/** 
	 * @����:��ȡ��ƶ����Ϣ�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��23�� ����8:51:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getFpbxx(String xh) {
		return dao.getFpbxx(xh);
	}
	
	/**
	 * @������Ƿ�����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��7��6�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getQfqk(String xh){
		return dao.getQfqk(xh);
	}
	
	/**
	 * @������һ��ͨ������� ���ݹ���
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��8��18��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public List<HashMap<String, String>> getYktxfls(String xh){
		return dao.getYktxfls(xh);
	}
	//����ҽҩ һ��ͨ��ѯ
	public List<HashMap<String, String>> getXsyktcuList(String xh){
		return dao.getXsyktcuList(xh);
	}
	/**
	 * @������ͼ�������� ����ʦ����ѧ
	 * @���ߣ�tgj[����:1075]
	 * @���ڣ�2017��11��28��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public List<HashMap<String, String>> getTsjyList(String xh){
		return dao.getTsjyList(xh);
	}
	
	/** 
	 * @����:�޸�ʵϰ��Ϣ(�Ͼ��ߵ�ְҵ����ѧԺ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-26 ����02:26:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param sxxxList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateSxxx(String xh, List<SxxxModel> sxxxList) throws Exception {
		return dao.updateSxxx(xh,sxxxList);
	}
	
	/** 
	 * @����:�޸ķ�����Ϣ(�Ͼ��ߵ�ְҵ����ѧУ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-26 ����02:22:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param fwxxList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateFwxx(String xh, List<FwxxModel> fwxxList) throws Exception{
		return dao.updateFwxx(xh, fwxxList);
	}
	
	/** 
	 * @����:��ȡ������Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-26 ����05:04:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFwxxList(String xh){
		return dao.getFwxx(xh);
	}
	
	/** 
	 * @����:��ȡʵϰ��Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-26 ����05:05:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSxxxList(String xh){
		return dao.getSxxx(xh);
	}
	
	/**
	 * �鿴�ɼ���Ϣ
	 * @param xh
	 * @param xkkh
	 * @return
	 */
	public HashMap<String, String> getCjxx(String xh,String xkkh){
		return dao.getCjxx(xh, xkkh);
	}
	
	/**
	 * �������п���
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveYhkh(XsxxglModel model) throws Exception{
		return dao.saveYhkh(model);
	}

	public List<HashMap<String, String>> getycsjList() {
		return dao.getycsjList();
	}

	public int[] updateXsxxb(List<HashMap<String, String>> rsList) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateXsxxb(rsList);
	}

	public List<HashMap<String, String>> getYcXgzdList(String sqid) {
		return dao.getYcXgzdList(sqid);
		}

	public String getxhforsqid(String sqid) {
		// TODO Auto-generated method stub
		return dao.getxhforsqid(sqid);
	}

	public boolean ycsjTs(List<HashMap<String, String>> sqidlist) throws Exception {
		List<HashMap<String, String>> xgzdList = null;
		boolean result = true;
		for (int i = 0; i < sqidlist.size(); i++) {
			String sqid= sqidlist.get(i).get("sqid");
			xgzdList = getYcXgzdList(sqid);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			XsxxglDao dao = new XsxxglDao();
			String[] xsxxZds = dao.getColumnByTable("xsxxb");
			String zd =null;
			String zdz =null;
			//��ͥ��Ա��Ϣ
			String jtcyxxZdzJson = null;
			
			//ѧ����ᾭ����Ϣ
			String xlshjlZdzJson = null;
			for (HashMap<String, String> xgzdMap : xgzdList) {
				zd = xgzdMap.get("zd");
				zdz = xgzdMap.get("zdz");
				if (zd != null) {
					//��ͥ��Ա��Ϣ
					if (zd.equals(Constants.ZDYBD_JTCYXX)) {
						jtcyxxZdzJson = xgzdMap.get("zdz");
					}
					//ѧ����ᾭ����Ϣ
					if (zd.equals(Constants.ZDYBD_XLSHJLXX)) {
						xlshjlZdzJson = xgzdMap.get("zdz");
					}
				}
				for (int j = 0; j < xsxxZds.length; j++) {
					String xsxxZd = xsxxZds[j];
					if (zd != null && zd.toLowerCase().equals(xsxxZd.toLowerCase())) {
						valueMap.put(zd, zdz);
					}
				}
			}
		
			List jtcyxxList = null;
			List xlshjlcxxList = null;
			String xh = getxhforsqid(sqid);
			if (!valueMap.isEmpty()) {
				valueMap.put("xh", xh);
				result = dao.updateInfo(valueMap);
			}
			
			if (jtcyxxZdzJson != null) {
				jtcyxxZdzJson = "{data:" + jtcyxxZdzJson + "}";
				jtcyxxList = JsonUtil.jsonToList(jtcyxxZdzJson,
						JtcyxxModel.class);
				result = dao.updateJtcyxx(xh, jtcyxxList);// ��ͥ��Ա��Ϣ����
			}

			if (xlshjlZdzJson != null) {
				xlshjlZdzJson = "{data:" + xlshjlZdzJson + "}";
				xlshjlcxxList = JsonUtil.jsonToList(xlshjlZdzJson,
						XlshjlModel.class);
				result = dao.updateXlshjlxx(xh, xlshjlcxxList);// ѧ����ᾭ����Ϣ����
			}
		}
		return result;
		
	
	}
	
	/**
	 * @description	����ȡ����Ϣ�������Ƽ�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-29 ����11:18:08
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getHjqkListForXakj(String xh) throws Exception{
		return dao.getHjqkListForXakj(xh);
	}
	
	/**
	 * 
	 * @����: ����ʡ�����е�ר��ѧУ����ʵϰ״̬(zd1)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-5 ����04:12:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSxzt(String[] xhs,String zt) throws Exception{
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < xhs.length; i++) {
			params.add(new String[]{zt,xhs[i]});
		}
		return dao.updateSxzt(params);
	}

	public List<HashMap<String, String>> getxljkList(String xh) {
		// TODO Auto-generated method stub
		return dao.getxljkList(xh);
	}
	/**
	 * @throws Exception 
	 * @����: ͨ��ѧ�ų�ȡѧҵ�ɼ�ǰ39��
	 * @���ߣ�����[���ţ�1529]
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCjoneList(String xh) throws Exception {
		return dao.getCjoneList(xh);
	}
	/**
	 * @����: ͨ��ѧ�ų�ȡѧҵ�ɼ�40-78��
	 * @���ߣ�����[���ţ�1529]
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCjtwoList(String xh) throws Exception {
		return dao.getCjtwoList(xh);
	}

	public boolean updateHwjl(String xh, List<HwjlModel> hwjlList) throws Exception {
		return dao.updateHwjl(xh,hwjlList);
	}

	public List<HashMap<String, String>> getHwjlList(String xh) {
		return dao.getHwjlList(xh);
	}
	public String getSfqnzyz(String xh){
		return dao.getSfqnzyz(xh);
	}
	
	/** 
	 * @����:ͨ��ѧ�Ż�ȡ���������Ϣ(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-4-17 ����02:02:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCyktxxList(String xh) {
		return dao.getCyktxxList(xh);
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-4-17 ����02:13:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param cyktxxList
	 * @param id
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateCyktxxByjg(String xh, List<CyktxxModel> cyktxxList, String[] id) throws Exception {
		return dao.updateCyktxxByjg(xh, cyktxxList, id);
	}


	/**
	 * @����:ͬ��ͬѧ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 10:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
    public List<HashMap<String,String>> getTbtxList(String xh,String bjdm) {
    	return dao.getTbtxList(xh,bjdm);
    }

    /**
     * @����:ͬ����ͬѧ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2019/2/21 10:41
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [xh]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
	public List<HashMap<String,String>> getTsstxList(String xh) {
		return dao.getTsstxList(xh);
	}

	/**
	 * @����:�������Ŀ�ɼ�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 13:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getBjgkmList(String xh) {
		return dao.getBjgkmList(xh);
	}
	/**
	 * @����:������Ŀ�ɼ�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 13:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getQtkmList(String xh) {
		return dao.getQtkmList(xh);
	}

	/**
	 * @����:֪��̸����Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getZxthxxList(String xh) {
		return dao.getZxthxxList(xh);
	}

	/**
	 * @����:�ҷ���Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getJfxxList(String xh) {
		return dao.getJfxxList(xh);
	}

	/**
	 * @����:�ڶ�����-���Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getHdxxList(String xh) {
		return dao.getHdxxList(xh);
	}

	/**
	 * @����:�ĸ�һ����Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getSgybxxList(String xh) {
		return dao.getSgybxxList(xh);
	}

	/**
	 * @����:�μ�������Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getCjstxxList(String xh) {
		return dao.getCjstxxList(xh);
	}
	/**
	 * @����:��ҵ��Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getByxxList(String xh) {
		return dao.getByxxList(xh);
	}

	/**
	 * @����:���ý���
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 15:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getBzjlList(String xh) {
		return dao.getBzjlList(xh);
	}


	/**
	 * @����:ɾ���ļ�ͥ��Ա��Ϣ��������ʷ��¼��
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/25 13:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh, jtcyxxList, user]
	 * @return: boolean
	 */
	public boolean jtcyDelLsjl(String xh,  List<JtcyxxModel> jtcyxxList, User user) throws Exception {
			return dao.jtcyDelLsjl(xh,jtcyxxList,user);
	}

	/**
	 * @����:�ֶ��޸Ĳ�����ʷ��¼��
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/25 15:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [insLsjlList]
	 * @return: boolean
	 */
	public boolean insLsjl(List<HashMap<String, String>> list) throws SQLException {
		return dao.insLsjl(list);
	}

	/**
	 * @����:���ݵ�ַ�����ȡ��ַ����
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/25 16:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [list]
	 * @return: java.lang.String
	 */
	public String getSzdmc(String  dz) {
		return dao.getSzdmc(dz);
	}

	/**
	 * @����:�ֶ���ʷ��¼��Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/25 17:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getZdLsjList(XsxxglModel model) throws Exception {
		return dao.getZdLsjList(model);
	}

	/**
	 * @����:��ͥ��Ա��ʷ��¼��Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/25 17:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getJtcyLsjList(XsxxglModel model) throws Exception {
		return dao.getJtcyLsjList(model);
	}
}
