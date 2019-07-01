/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:31:23 
 */  
package com.zfsoft.xgxt.xpjpy.cssz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013��-��������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����01:31:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszService extends SuperServiceImpl<CsszModel, CsszDao>  implements Constants{

	public static final String CPZ_CSH_NJZY = "1";//�������ʼ��-�꼶+רҵ
	public static final String CPZ_CSH_BJ = "2";//�������ʼ��-�༶
	public static final String ZCFLR_WYQ = "1";//�۲��¼��--��ҳǩ
	public static final String ZCFLR_YYQ = "0";//�۲��¼��--��ҳǩ
	public static final String PJFS_XQ = "1";//������ʽ  1:ѧ��	
	public static final String PJFS_XN = "2";//������ʽ  2��ѧ��
	public static final String PJFS_XNXQ = "3";//������ʽ 3��ѧ��ѧ��
	public static final String ZHCP_MRPM = "cpzpm";//�ۺϲ���Ĭ��������ʽ������������
	private static final String SplitChar = "#";
	public static final String XQKG="on"; //ѧ�ڿ��ر�ʶ
	
	//�Ƿ���Ҫ����ѧ���۲�
	public static final boolean getZczq(){
		
		CsszService cs=new CsszService();
		String zczq = cs.getCsz("zczq");
		
		if(!StringUtils.isBlank(zczq)&&!CsszService.PJFS_XN.equals(zczq)){
			return true;
		}
		return false;
	}
	
	
	//���ݲ����趨������������
	public static final Map<String, String> getPjzq() throws Exception{
		
		CsszService cs=new CsszService();
		String pjzq = cs.getCsz("pjzq");
		String xn = cs.getModel().getXn();
		String xq = cs.getModel().getXq();
		String cxxq = cs.getModel().getXq();
		
		if(PJFS_XN.equals(pjzq)){
			xq = "on";
			cxxq = "";
		}
		
		Map<String, String> pjzqMap = new HashMap<String, String>();
		
		pjzqMap.put("xn", xn);
		pjzqMap.put("xq", xq);
		//Ĭ�ϸ߼���ѯѧ��
		pjzqMap.put("cxxq", cxxq);
		
		//������������
		String xqmc = Base.getXqmcForXqdm(xq);
		pjzqMap.put("pjzqmc", xn+" "+xqmc);
		
		
		return pjzqMap;
	}
	
	
	private CsszDao csszDao = new CsszDao();
	
	public CsszService(){
		dao = csszDao;
	}
	
	
	/**
	 * 
	 * @����: �������������б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����09:09:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getPjzqList(){
		
		//ϵͳѧ���б�
		List<HashMap<String,String>> xqList = Base.getXqList(); 
		//���������б�
		List<HashMap<String,String>> pjzqList = new ArrayList<HashMap<String,String>>();
		
		String zczq = dao.getCsz("zczq");
		
		String xn = null;
		
		for (int i = 0 ; i < 2 ; i++){
			
			if (i == 0){
				xn = String.valueOf(Integer.parseInt(Base.currXn.substring(0,4))-1) + "-" + Base.currXn.substring(0,4);
			} else {
				xn = Base.currXn;
			}
			
			if(!StringUtils.isBlank(zczq) && PJFS_XN.equals(zczq)){
				
				HashMap<String,String> zqMap = new HashMap<String, String>();
				zqMap.put("zqdm", xn+SplitChar+XQKG);
				zqMap.put("zqmc", xn);
				pjzqList.add(zqMap);
				
			} else {
				for (HashMap<String,String> map : xqList){
					HashMap<String,String> zqMap = new HashMap<String, String>();
					zqMap.put("zqdm", xn+SplitChar+map.get("xqdm"));
					zqMap.put("zqmc", xn+" "+map.get("xqmc"));
					pjzqList.add(zqMap);
				}
			}
		}
		
		return pjzqList;
	}
	
	
	
	/**
	 * 
	 * @����: ���ڴ���ת����������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����04:23:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zqdm
	 * @return
	 * String ��������
	 */
	public String getZqmc(String zqdm){
		
		if (StringUtil.isNull(zqdm) || zqdm.indexOf(SplitChar) == -1){
			return null;
		}
		
		String[] zqInfo = zqdm.split(SplitChar);
		
		if (zqInfo.length != 2){
			return null;
		}
		
		List<HashMap<String,String>> xqList = Base.getXqList();
		
		String xqmc = null;
		
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(zqInfo[1])){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		return zqInfo[0]+" "+xqmc;
	}
	
	
	
	
	/**
	 * 
	 * @����: ��ѯ����������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����09:15:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * CsszModel ��������
	 * @throws Exception 
	 */
	public CsszModel getModel() throws Exception{
		
		CsszModel model = dao.getModel();
		
		if (model == null){
			//��ʼ����������
			model = new CsszModel();
			model.setPjkg(CLOSE);
			model.setCpzcsh(CPZ_CSH_NJZY);
			model.setZcflrfs(ZCFLR_WYQ);
			model.setPjfs(PJFS_XQ);
			model.setZcmrpm(ZHCP_MRPM);
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			
			boolean result = dao.runInsert(model);
			
			if (result){
				//������Ա��ʼ��
				CpmdDao cpmdDao = new CpmdDao();
				//��ɾ���
				if(cpmdDao.initDel()){
					cpmdDao.init();
				}
			}
			
		} else {
			String xn = model.getXn();
			String xq = model.getXq();
			
			//ƴװ��������
			if (!StringUtil.isNull(xn) && !StringUtil.isNull(xq)){
				model.setPjzq(xn+SplitChar+xq);
			}
		}
		
		boolean isOpen = isOpen(model);
		model.setKgzt(String.valueOf(isOpen));
		
		return model;
	}
	
	
	
	/**
	 * 
	 * @����: ���²�������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����11:16:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdKey
	 * @param zdValue
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateCssz(String zdKey,String zdValue) throws Exception{
		
		if ("pjzq".equals(zdKey)){
			String[] zqArray = zdValue.split(SplitChar);
			return dao.updatePjzq(zqArray);
		} else {
			return dao.updateCssz(zdKey, zdValue);
		}
		
	}
	
	
	
	/**
	 * 
	 * @����: �жϵ�ǰ��������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����01:48:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean isOpen(CsszModel model) throws Exception{
	
		if (model == null){
			model = dao.getModel();
		}
		
		//�޲����������� ���߿���״̬Ϊ�ر� ��Ϊ�ر�״̬
		if (model == null || CLOSE.equals(model.getPjkg())){
			return false;
		}
		
		//��ʽ����ʼʱ�䡢����ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date endTime = null;
		String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
		
		if (StringUtil.isNull(model.getKssj()) && StringUtil.isNull(model.getJssj())){
			return OPEN.equals(model.getPjkg());
		} else if (!StringUtil.isNull(model.getKssj()) && !StringUtil.isNull(model.getJssj())){
			startTime=sdf.parse(model.getKssj()+":00");
			endTime=sdf.parse(model.getJssj()+":59");
			//��ǰʱ�����ڽ���ʱ�� && ���ڿ�ʼʱ��  && ����״̬Ϊ����
			return now.before(endTime) && now.after(startTime) && OPEN.equals(model.getPjkg());
		} else if (!StringUtil.isNull(model.getKssj())){
			startTime=sdf.parse(model.getKssj()+":00");
			return now.after(startTime);
		} else if (!StringUtil.isNull(model.getJssj())){
			endTime=sdf.parse(model.getJssj()+":59");
			return now.before(endTime);
		}
		
		return false;
	}
	
	
	/**
	 * 
	 * @����:��ò�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-1 ����04:30:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCsz(String csdm){
		
		return dao.getCsz(csdm);
	}
	
}
