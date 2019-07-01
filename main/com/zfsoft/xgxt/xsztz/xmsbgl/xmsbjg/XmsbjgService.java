/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-13 ����11:40:07 
 */
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;



import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmjxForm;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-7-13 ����11:40:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XmsbjgService extends SuperServiceImpl<XmsbjgForm, XmsbjgDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	
	private XmsbjgDao dao = new XmsbjgDao();
	private XmsbDao xmsbDao = new XmsbDao();
	private XmsbService xmsbService = new XmsbService();

	public boolean isHaveSbjg(XmsbjgForm model) {
		return dao.isHaveSbjg(model);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:��Ŀ�걨������ӱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-13 ����09:54:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean ��������
	 * @throws
	 */
	public boolean editXmsbjg(XmsbjgForm model, List<XmjxForm> xmjxList,String[] cyxyArr) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String jgid = UniqID.getInstance().getUniqIDHash();
			model.setJgid(jgid);
			model.setXmdm(jgid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
			// ɾ��������Ϣ���ٲ���
			result = xmsbDao.delXmjx(model.getXmdm());
		}
		//�������ѧԺ��Ϣ
		xmsbService.cyxyPlbc(cyxyArr,model.getXmdm());
		if(null==xmjxList){
			return true;
		}
		
		List<String[]> jxxxList = new ArrayList<String[]>();
		String[] jxxx = null;
		String xmjxid = null;
		for (XmjxForm xmjxForm : xmjxList) {
			jxxx = new String[5];
			xmjxid = UniqID.getInstance().getUniqIDHash();
			jxxx[0] = xmjxid;
			jxxx[1] = model.getXmdm();
			jxxx[2] = xmjxForm.getFjxf();
			jxxx[3] = xmjxForm.getJxmc();
			jxxx[4] = xmjxForm.getXssx();
			jxxxList.add(jxxx);
		}
		return xmsbDao.jxxxPlbc(jxxxList);

	}

	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ��Ŀ�걨���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-13 ����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getXmsbjg(XmsbjgForm t) throws Exception {
		return dao.getXmsbjg(t);
	}

	/**
	 * 
	 * @����:ɾ����Ŀ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-13 ����11:32:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 *             XmsbjgForm ��������
	 * @throws
	 */
	public boolean delXmsbjg(String[] ids) throws Exception {
		List<String[]> xmjxList = new ArrayList<String[]>();
		String[] idArr = null;
		for (int i = 0; i < ids.length; i++) {
			idArr = new String[1];
			idArr[0] = ids[i];
			xmjxList.add(idArr);
		}
		return xmsbDao.delPlXmjx(xmjxList);
	}
	/**
	 * 
	 * @����:����������Ŀ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-20 ����08:55:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param sfrm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean setRmxm(String[] ids,String sfrm) throws Exception {
		List<String[]> rmszList = new ArrayList<String[]>();
		String[] idArr = null;
		for (int i = 0; i < ids.length; i++) {
			idArr = new String[2];
			idArr[0] = sfrm;
			idArr[1] = ids[i];
			rmszList.add(idArr);
		}
		return dao.setRmxm(rmszList);
	}
	
	/**
	 * ��ȡ��Ŀ����ѧ���б�
	 */
	public List<HashMap<String,String>> getSqxsList(String xmdm,String xh) throws Exception {
		return dao.getSqxsList(xmdm,xh);
	}
	
	public boolean isHaveSqJl(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		return dao.isHaveSqJl(values);
	}
	
	public List<HashMap<String, String>> getXsxxList(XmsbjgForm model, User user,String xhArr) throws Exception {
		if("".equals(xhArr)||null==xhArr){
			model.setXhArr(new String[]{});
		}
		else{
			model.setXhArr(xhArr.split(","));
		}
		return dao.getXsxxList(model, user);

	}
	/**
	 * 
	 * @����:��ȡ����ѧԺ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-18 ����04:06:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCyxyList(String xmdm){
		return dao.getCyxyList(xmdm); 
	}
	
	public String getXylb(String sbbmdm){
		return dao.getXylb(sbbmdm); 
	}
	
	public List<HashMap<String, String>> getXmForJcfrd(String xmdm){
		return xmsbDao.getXmForJcfrd(xmdm);
	}
	
	/**
	 * 
	 * @����:��ȡ��Ŀ�׶�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-21 ����11:39:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmjdSzList(String xmdm){
		return dao.getXmjdSzList(xmdm);
	}
	
	/**
	 * 
	 * @����:����׶�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-22 ����09:08:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param jdid
	 * @param jdmc
	 * @param jdf
	 * @param jdsx
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJdsz(String xmdm,String[] jdid,String[] jdmc,String[] jdf,String[] jdsx) throws Exception{
		return dao.saveJdsz(xmdm, jdid, jdmc, jdf, jdsx);
	}
	
	/**
	 * 
	 * @����:��ȡ�μ�������Ŀ����Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-8 ����01:59:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmcyrs(String xmdm){
		return dao.getTtxmcyrs(xmdm);
		
	}

}
