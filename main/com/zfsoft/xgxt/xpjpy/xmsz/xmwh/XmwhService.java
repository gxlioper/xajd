/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����02:39:49 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.xmwh;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.xsxx.xsgl.XsxxDao;
import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.jdsz.JdszService;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszModel;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.xmsz.tzsz.TzszService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����02:39:49
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XmwhService extends SuperServiceImpl<XmwhModel, XmwhDao> {

	private XmwhDao dao = new XmwhDao();

	public XmwhService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����: ����������ϱ���������Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-30 ����02:59:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getKfsqPjxm() {
		return dao.getKfsqPjxm();
	}
	
	public List<HashMap<String, String>> getKfsqPjxm(String xnxq,String bjdms) {
		
		String newBjdms = "";
		String[] bjdmss = bjdms.split(",");
		for(int i=0;i<bjdmss.length;i++){
			newBjdms += "'"+bjdmss[i]+"',";
		}
		if(newBjdms.length()>0){
			newBjdms=newBjdms.substring(0, newBjdms.length()-1);
		}
		
		return dao.getKfsqPjxm(xnxq,newBjdms);
	}
	
	public List<HashMap<String, String>> getKfsqPjxm(String xnxq) {
		return dao.getKfsqPjxm(xnxq);
	}

	/**
	 * 
	 * @����:��ȡ������Ŀ����������ǰ��Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getOthers(String xmdm)
			throws Exception {
		String currXn = CsszService.getPjzq().get("xn");// ��ǰѧ��
		String currXq = CsszService.getPjzq().get("xq");// ����ѧ��

		return dao.getOthers(xmdm, currXn, currXq);
	}

	public List<HashMap<String, String>> getOthers(String xmdm,String xzdm)
			throws Exception {
		String currXn = CsszService.getPjzq().get("xn");// ��ǰѧ��
		String currXq = CsszService.getPjzq().get("xq");// ����ѧ��

		return dao.getOthers(xmdm, currXn, currXq,xzdm);
	}

	/**
	 * 
	 * @����:�ж��ظ����ݣ�������Ϊ׼
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRepeat(XmwhModel model) throws Exception {
		return dao.isRepeat(model);
	}

	/**
	 * 
	 * @����:�жϹ����ԣ����ݿɷ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRalate(XmwhModel model) throws Exception {
		XmwhModel modelOld = dao.getModel(model);
		boolean flag = false;
		if (!model.getXmmc().trim().equals(modelOld.getXmmc().trim())
				|| !model.getLxdm().equals(modelOld.getLxdm())) {
			// flag = dao.isRalate(model);
		}
		return flag;
	}

	/**
	 * 
	 * @����:�жϹ����ԣ����ݿɷ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRalate(String values) throws Exception {
		boolean flag = false;
		// �Ƿ���ѧ��������Ŀ
		SqshService sqshService = new SqshService();
		if (values != null) {
			String[] xmdms = values.split(",");
			for (int i = 0; i < xmdms.length; i++) {
				flag = sqshService.isExistsFlowData(xmdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * @ɾ��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean delRalate(String values) throws Exception {
		return dao.delRalate(values);
	}

	/**
	 * 
	 * @����:������Ŀ�����ѯ��¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getDataById(String xmdm) throws Exception {
		return dao.getDataById(xmdm);
	}

	/**
	 * 
	 * @����:������Ŀ���Ʋ�ѯ������¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getDataByName(String xmmc, String xn, String xq) throws Exception {
		return dao.getDataByName(xmmc, xn, xq);
	}

	/**
	 * 
	 * @����:������Ŀ�����ѯ��Ŀ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		return dao.getNameById(xmdm);
	}

	/**
	 * 
	 * @����:ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return String ��������
	 * @throws
	 */
	public String getRsfpnj(String xmdm) throws Exception {
		return dao.getRsfpnj(xmdm);
	}

	/**
	 * 
	 * @����:��ȡ��Ŀ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-7 ����11:26:33
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		return dao.getXmxz();
	}

	/**
	 * 
	 * @����:��ȡ��Ŀ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-7 ����11:26:33
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx(String xzdm) throws Exception {
		return dao.getXmlx(xzdm);
	}

	/**
	 * 
	 * @����:�õ���ǰѧ��ѧ�ڣ���ʽΪ:2012-2013 ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-8 ����11:05:27
	 * @�޸ļ�¼:
	 * @return String ��������
	 * @throws
	 */
	public String getCurrXnXqmc() throws Exception {
		CsszModel csszModel = new CsszDao().getModel();
		String currXn = csszModel.getXn();// ��ǰѧ��
		String currXq = csszModel.getXq();// ����ѧ��
		return xnXqGshMc(currXn, currXq);
	}

	/*
	 * ѧ��ѧ�ڸ�ʽ�������� 2012-2013 ��
	 */
	private String xnXqGshMc(String xn, String xq) throws Exception {
		String xqmc = null;
		List<HashMap<String, String>> xqList = Base.getXqList();
		for (HashMap<String, String> map : xqList) {
			if (xq != null && xq.equals(map.get("xqdm"))) {
				xqmc = map.get("xqmc");
			}
		}
		
//		����ѧ�굱�е�ѧ�ڴ����'on'  ȡ����ʾ
		if (xqmc == null) {
			xqmc = "";
		}
		
		
		return xn + " " + xqmc;
	}

	/*
	 * ѧ��ѧ�ڸ�ʽ�������� 2012-2013;01
	 */
	private String xnXqGshDm(String xn, String xq) throws Exception {
		return xn + ";" + xq;
	}

	/**
	 * 
	 * @����:��ѯ���Ը��ƵĽ�����Դ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-14 ����11:06:11
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getJxfz() throws Exception {
		List<HashMap<String, String>> jxfzList = new ArrayList<HashMap<String, String>>();
		CsszDao  csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		if (csszModel == null) {

		}
		String currXn = csszModel.getXn();// ��ǰѧ��
		String currXq = csszModel.getXq();// ����ѧ��
		String zczq = csszDao.getCsz("zczq");
		List<HashMap<String, String>> xnxqList = dao.getJxfzXnXq(zczq);
		String xn = null;
		String xq = null;
		HashMap<String, String> jxfzMap = null;
		if (xnxqList != null) {
			for (HashMap<String, String> map : xnxqList) {
				xn = map.get("xn");
				xq = map.get("xq");
				if (!xn.equals(currXn) || !xq.equals(currXq)) {
					jxfzMap = new HashMap<String, String>();
					jxfzMap.put("dm", xnXqGshDm(xn, xq));
					jxfzMap.put("mc", xnXqGshMc(xn, xq));
					jxfzList.add(jxfzMap);
				}
			}
		}
		return jxfzList;
	}

	/**
	 * @����:�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-14 ����01:48:30
	 * @�޸ļ�¼:
	 * @param jxfznd
	 * @return boolean ��������
	 * @throws
	 */
	public boolean runJxfz(String jxfznd) throws Exception {
		boolean flag = false;
		CsszModel csszModel = new CsszDao().getModel();
		String currXn = csszModel.getXn();// ��ǰѧ��
		String currXq = csszModel.getXq();// ����ѧ��
		TjszService tjszService = new TjszService();
		JdszService jdszService = new JdszService();
		TzszService tzszService = new TzszService();
		String fzXn = jxfznd.split(";")[0];
		String fzXq = jxfznd.split(";")[1];
		List<HashMap<String, String>> jxfzList = dao.getJxfz(fzXn, fzXq,
				currXn, currXq);
		if (jxfzList != null && jxfzList.size() > 0) {
			for (int i = 0; i < jxfzList.size(); i++) {
				String guid = UniqID.getInstance().getUniqIDHash();
				guid = guid.toUpperCase();
				jxfzList.get(i).put("id", guid);
			}
			flag = dao.saveData(jxfzList, currXn, currXq);
			//����ƣ������������á�������á������������
			List<TjszModel> list = null;
			for (HashMap<String, String> jxfzMap : jxfzList) {
				list=new ArrayList<TjszModel>();
				List<HashMap<String, String>> tjList = tjszService.getTjsz(jxfzMap.get("xmdm"));
				List<HashMap<String, String>> jdList = jdszService.getByXmdm(jxfzMap.get("xmdm"),currXn, currXq);
				List<HashMap<String, String>> tzList = tzszService.getByXmdm(jxfzMap.get("xmdm"),currXn, currXq);
				StringBuffer jdXmdms = new StringBuffer();
				StringBuffer tzXmdms = new StringBuffer();
				for (int i = 0; i < jdList.size(); i++) {
					if(i<jdList.size()-1){
						jdXmdms.append(jdList.get(i).get("fzbjdxmdm"));
						jdXmdms.append(",");
					}else{
						jdXmdms.append(jdList.get(i).get("fzbjdxmdm"));
					}
				}
				for (int i = 0; i < tzList.size(); i++) {
					if(i<tzList.size()-1){
						tzXmdms.append(tzList.get(i).get("fztzjxdm"));
						tzXmdms.append(",");
					}else{
						tzXmdms.append(tzList.get(i).get("fztzjxdm"));
					}
				}
				for (HashMap<String, String> tjMap : tjList) {
					TjszModel tjModel = new TjszModel();
					tjModel.setTjdm(tjMap.get("tjdm"));
					tjModel.setYyfw(tjMap.get("yyfw"));
					tjModel.setGxdm(tjMap.get("gxdm"));
					tjModel.setTjz(tjMap.get("tjz"));
					tjModel.setYlzq(tjMap.get("ylzq"));
					list.add(tjModel);
				}
				flag = tjszService.saveOrUpdate(jxfzMap.get("id"), list);
				flag = jdszService.jdsz(jxfzMap.get("id"), jdXmdms.toString());
				flag = tzszService.shsz(jxfzMap.get("id"), tzXmdms.toString());
			}
			
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-15 ����10:03:15
	 * @�޸ļ�¼: 
	 * @param dateFormat
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCurrTime(String dateFormat){
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(new Date());
	}
	
	/**
	 * 
	 * @����:������Ŀ����ظ���֤
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-11-17 ����02:45:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistXssx(XmwhModel model) throws Exception {
		String num = dao.checkExistXssx(model);
		return Integer.valueOf(num) > 0;
	}
	/**
	 * @����: �㽭��ýѧԺ����Ŀ���ʡ����ˡ����ҽ�ѧ�𡱲�ѡ�У�����ȫ��Ĭ��ѡ��
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ� 2015-12-31 ����01:03:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getXmxzmw() throws Exception {
		return dao.getXmxzmw();
	}
	
	/**
	 * 
	 * @����:������Ŀ�����ѯ��Ŀ���ƣ�����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public String getNameByIdJtpj(String xmdm) throws Exception {
		return dao.getNameByIdJtpj(xmdm);
	}
	
	/**
	 * @description	�� �Ƿ�����ɲ�
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-28 ����02:50:39
	 * @param xmdm
	 * @return
	 */
	public String getSfyxgb(String xmdm){
		return dao.getSfyxgb(xmdm);
	}

	public List<HashMap<String,String>> getnewXmlx(String xmxz) {
		return dao.getnewXmlx(xmxz);
	}

	/**
	 * @����:��ȡ��������б�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/9/29 20:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getPyccList(){
		XsxxDao xsxxDao = new XsxxDao();
		return xsxxDao.getPyccList();
	}

    public String getXsxh(String xzdm) {
		return dao.getXsxh(xzdm);
    }
}
