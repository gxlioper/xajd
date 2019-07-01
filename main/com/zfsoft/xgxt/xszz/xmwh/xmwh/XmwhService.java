/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.xmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.xsxx.xsgl.XsxxDao;
import xgxt.action.Base;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀά��
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhService extends SuperServiceImpl<XmwhForm, XmwhDao> {

	private XmwhDao dao = new XmwhDao();
	private XsxxDao xsxxDao = new XsxxDao();

	public XmwhService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:��ȡ������Ŀ����������ǰ��Ŀ
	 * @���ߣ�Administrator
	 * @���ڣ�2013-4-24 ����04:12:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getOthers(String xmdm) throws Exception{
		return dao.getOthers(xmdm);
	}

	
	/**
	 * 
	 * @����:�ж��ظ����ݣ�������Ϊ׼
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRepeat(XmwhForm model) throws Exception {
		
		//CsszModel csszModel = new CsszDao().getModel();
		//String currXn = csszModel.getXn();// ��ǰѧ��
		//String currXq = csszModel.getXq();// ����ѧ��
		String currXn = Base.currXn;// ��ǰѧ��
		String currXq = Base.currXq;// ����ѧ��
		return dao.isRepeat(model,currXn,currXq);
	}
	
	/**
	 * 
	 * @����:�жϹ����ԣ����ݿɷ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRalate(XmwhForm model) throws Exception {
		XmwhForm modelOld = dao.getModel(model);
		boolean flag = false;
		if(!model.getXmmc().trim().equals(modelOld.getXmmc().trim()) || !model.getLbdm().equals(modelOld.getLbdm())){
			flag = dao.isRalate(model);
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:�жϹ����ԣ����ݿɷ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRalate(String values) throws Exception {
		return dao.isRalate(values);
	}
	
	/**
	 * 
	 * @ɾ��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-27 ����05:08:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delRalate(String values) throws Exception {
		return dao.delRalate(values);
	}	
	
	/**
	 * 
	 * @����:������Ŀ�����ѯ��¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String,String> getDataById(String xmdm) throws Exception {
		return dao.getDataById(xmdm);
	}
	
	/**
	 * 
	 * @����:������Ŀ���Ʋ�ѯ������¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String,String>  getDataByName(String xmmc, String xn, String xq) throws Exception {
		return dao.getDataByName(xmmc,xn,xq);
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
	 * @����:������Ŀ���Ʋ�ѯ���õı������
	 * @���ߣ�honglin
	 * @���ڣ�2013-5-4
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXszzdm (String xmmc, String xn, String xq){		
		if (StringUtil.isNull(xmmc)){
			logger.error("��Ŀ���Ʋ���Ϊ�գ�");
			throw new NullPointerException();
		}		
		return dao.getXszzdm(xmmc.trim(),xn,xq);
	}
	
	
	/**
	 * 
	 * @����:ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�
	 * @���ߣ�ligl
	 * @���ڣ�2013-7-5 ����09:14:42
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getRskznj(String xmdm) throws Exception {
		return dao.getRskznj(xmdm);
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
		if (xqmc == null) {
			xqmc = xq;
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
	 * @����:��ȡ��Ŀά�����е�����List
	 * @���ߣ���ǿ [���ţ�785]
	 * @���ڣ�2014-1-21 ����11:41:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmzqList(String Type) throws Exception {
		
		List<HashMap<String, String>> xmfzList = new ArrayList<HashMap<String, String>>();
		
		List<HashMap<String, String>> xnxqList = dao.getXmzqList();
		String xn = null;
		String xq = null;
		HashMap<String, String> xmfzMap = null;
		if (xnxqList != null) {
			for (HashMap<String, String> map : xnxqList) {
				xn = map.get("sqxn");
				xq = map.get("sqxq");
				if (Type.equals("Query")||(!xn.equals(Base.currXn) || !xq.equals(Base.currXq))) {
					xmfzMap = new HashMap<String, String>();
					xmfzMap.put("zqdm", xnXqGshDm(xn, xq));
					xmfzMap.put("zqmc", xnXqGshMc(xn, xq));
					xmfzList.add(xmfzMap);
				}
			}
		}
		return xmfzList;
	}

	/**
	 * 
	 * @����:������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-21 ����04:22:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxfznd
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runXmfz(String xmfznd) throws Exception {
		boolean flag = false;

		String currXn = Base.currXn; //��ǰѧ��
		String currXq = Base.currXq; //��ǰѧ��

		String fzXn = xmfznd.split(";")[0];
		String fzXq = xmfznd.split(";")[1];
		List<HashMap<String, String>> xmfzList = dao.getXmfz(fzXn, fzXq,
				currXn, currXq);
		if (xmfzList != null && xmfzList.size() > 0) {
			flag = dao.saveData(xmfzList, currXn, currXq);
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @����: ��ѯ��ͬһ����Ŀ���µ�������Ŀ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-21 ����03:53:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getSameGroupXmList(String xmdm){
		
		return dao.getSameGroupXmList(xmdm);
	}
	
	
	/**
	 * 
	 * @����: ���澭������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-22 ����09:22:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param groupXmdm
	 * @param xydm
	 * @param je
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean savJfsz(String xmdm,String[] groupXmdm , String[] xydm,String[] je) throws Exception{
		
		if (je == null || je.length == 0){
			return false;
		}
		
		/*����ɾ������*/
		dao.delXyjf(xmdm);
		dao.delXmfz(xmdm);
		
		/*1��������**/
		String uuid = UniqID.getInstance().getUniqIDHash();//��ID
		List<String[]> params = new ArrayList<String[]>();
		
		if (groupXmdm != null && groupXmdm.length > 0){
			for (String str : groupXmdm){
				String[] param = new String[]{uuid,str};
				params.add(param);
			}
		} 
		
		String[] param = new String[]{uuid,xmdm};
		params.add(param);
		
		boolean result = true;

		/*2��������ѧԺ���Ѷ��**/
		List<String[]> jfList = new ArrayList<String[]>();
		
		for (int i = 0 , j = je.length ; i < j ; i++){
			
			if (!StringUtil.isNull(je[i])){
				String[] xyjf = new String[]{uuid,xydm[i],je[i]};
				jfList.add(xyjf);
			}
		}
		
		//ѧԺ����������
		if(jfList.size() > 0){
			
			//������Ŀ���鱣��
			result = dao.saveXmfz(params);
			
			if (result){
				//��Ŀ���ѱ���
				result = dao.saveXyjf(jfList);
			}
		}
		
		
		return result;
	}
	
	
	public Map<String,String> getXyjf(String xmdm){
		
		List<HashMap<String,String>> xyjfList = dao.getXyjfList(xmdm);
		
		Map<String,String> result = new HashMap<String, String>();
		
		for (HashMap<String,String> map : xyjfList){
			result.put(map.get("xydm"), map.get("je"));
		}
		return result;
	}
	
	/**
	 * 
	 * @����:��ȡ��Ŀ���
	 * @���ߣ�Huang Chenji
	 * @���ڣ�2015-10-27 ����11:26:33
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		return dao.getXmlb();
	}
	
	/**
	 * �����Ի�����
	 */
	public HashMap<String, String> showXmxx_10335(String xmdm) throws Exception {
		return dao.showXmxx_10335(xmdm);
	}

	public List<HashMap<String,String>> getPyccList(){
		return xsxxDao.getPyccList();
	}
}
