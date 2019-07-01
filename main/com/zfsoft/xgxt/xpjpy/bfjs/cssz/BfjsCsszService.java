/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:31:23 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.cssz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.bfjs.fswh.BfjsFswhDao;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��羺������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-3-31 ����04:05:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BfjsCsszService extends SuperServiceImpl<BfjsCsszModel, BfjsCsszDao>  implements Constants{

	public static final String CPZ_CSH_NJZY = "1";//�������ʼ��-�꼶+רҵ
	public static final String CPZ_CSH_BJ = "2";//�������ʼ��-�༶
	public static final String ZCFLR_WYQ = "1";//������¼��--��ҳǩ
	public static final String ZCFLR_YYQ = "0";//������¼��--��ҳǩ
	public static final String DFFS_XQ = "1";//��ַ�ʽ  1:ѧ��	
	public static final String DFFS_XN = "2";//��ַ�ʽ  2��ѧ��
	private static final String SplitChar = "#";
	public static final String XQKG="on"; //ѧ�ڿ��ر�ʶ
	
	//�Ƿ���Ҫ����ѧ�꾺��
	public static final boolean getDfzq(){
		
		BfjsCsszService cs=new BfjsCsszService();
		String bfjsdfzq = cs.getCsz("bfjsdfzq");
		
		if(!StringUtils.isBlank(bfjsdfzq)&&!BfjsCsszService.DFFS_XN.equals(bfjsdfzq)){
			return true;
		}
		return false;
	}
	
	
	//���ݲ����趨���þ�������
	public static final Map<String, String> getPdzq() throws Exception{
		
		BfjsCsszService cs=new BfjsCsszService();
		String Pdzq = cs.getCsz("pdzq");
		String xn = cs.getModel().getXn();
		String xq = cs.getModel().getXq();
		String cxxq = cs.getModel().getXq();
		
		if(DFFS_XN.equals(Pdzq)){
			xq = "on";
			cxxq = "";
		}
		
		Map<String, String> PdzqMap = new HashMap<String, String>();
		
		PdzqMap.put("xn", xn);
		PdzqMap.put("xq", xq);
		//Ĭ�ϸ߼���ѯѧ��
		PdzqMap.put("cxxq", cxxq);
		
		//������������
		String xqmc = Base.getXqmcForXqdm(xq);
		PdzqMap.put("Pdzqmc", xn+" "+xqmc);
		
		
		return PdzqMap;
	}
	
	
	private BfjsCsszDao BfjsCsszDao = new BfjsCsszDao();
	
	public BfjsCsszService(){
		dao = BfjsCsszDao;
	}
	
	
	/**
	 * 
	 * @����:�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-3-31 ����04:20:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getPdzqList(){
		
		//ϵͳѧ���б�
		List<HashMap<String,String>> xqList = Base.getXqList(); 
		//���������б�
		List<HashMap<String,String>> pdzqList = new ArrayList<HashMap<String,String>>();
		
		String zczq = dao.getCsz("bfjspdzq");
		
		String xn = null;
		
		for (int i = 0 ; i < 2 ; i++){
			
			if (i == 0){
				xn = String.valueOf(Integer.parseInt(Base.currXn.substring(0,4))-1) + "-" + Base.currXn.substring(0,4);
			} else {
				xn = Base.currXn;
			}
			
			if(!StringUtils.isBlank(zczq) && DFFS_XN.equals(zczq)){
				
				HashMap<String,String> zqMap = new HashMap<String, String>();
				zqMap.put("zqdm", xn+SplitChar+XQKG);
				zqMap.put("zqmc", xn);
				pdzqList.add(zqMap);
				
			} else {
				for (HashMap<String,String> map : xqList){
					HashMap<String,String> zqMap = new HashMap<String, String>();
					zqMap.put("zqdm", xn+SplitChar+map.get("xqdm"));
					zqMap.put("zqmc", xn+" "+map.get("xqmc"));
					pdzqList.add(zqMap);
				}
			}
		}
		
		return pdzqList;
	}
	
	
	
	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-3-31 ����04:20:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zqdm
	 * @return
	 * String �������� 
	 * @throws
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
	 * @����:��ѯ����������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-3-31 ����04:36:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * BfjsCsszModel �������� 
	 * @throws
	 */
	public BfjsCsszModel getModel() throws Exception{
		
		BfjsCsszModel model = dao.getModel();
		
		if (model == null){
			//��ʼ����������
			model = new BfjsCsszModel();
			model.setSqkg(CLOSE);
			model.setJsfs(DFFS_XQ);
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			boolean result = dao.runInsert(model);
			
		} else {
			String xn = model.getXn();
			String xq = model.getXq();
			
			//ƴװ��������
			if (!StringUtil.isNull(xn) && !StringUtil.isNull(xq)){
				model.setPdzq(xn+SplitChar+xq);
			}
		}
		
		boolean isOpen = isOpen(model);
		model.setKgzt(String.valueOf(isOpen));
		
		return model;
	}
	
	
	
	/**
	 * 
	 * @����: ���²�������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-22 ����11:16:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdKey
	 * @param zdValue
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateBfjsCssz(String zdKey,String zdValue) throws Exception{
		
		if ("pdzq".equals(zdKey)){
			String[] zqArray = zdValue.split(SplitChar);
			return dao.updatePdzq(zqArray);
		} else {
			return dao.updateBfjsCssz(zdKey, zdValue);
		}
		
	}
	
	
	
	/**
	 * 
	 * @����: �жϵ�ǰ��������
	 * @���ߣ����� [���ţ�1104]
	 * @���ڣ�2013-7-22 ����01:48:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean isOpen(BfjsCsszModel model) throws Exception{
	
		if (model == null){
			model = dao.getModel();
		}
		
		//�޲����������� ���߿���״̬Ϊ�ر� ��Ϊ�ر�״̬
		if (model == null || CLOSE.equals(model.getSqkg())){
			return false;
		}
		
		//��ʽ����ʼʱ�䡢����ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date endTime = null;
		String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
		
		if (StringUtil.isNull(model.getKssj()) && StringUtil.isNull(model.getJssj())){
			return OPEN.equals(model.getSqkg());
		} else if (!StringUtil.isNull(model.getKssj()) && !StringUtil.isNull(model.getJssj())){
			startTime=sdf.parse(model.getKssj()+":00");
			endTime=sdf.parse(model.getJssj()+":59");
			//��ǰʱ�����ڽ���ʱ�� && ���ڿ�ʼʱ��  && ����״̬Ϊ����
			return now.before(endTime) && now.after(startTime) && OPEN.equals(model.getSqkg());
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
	
	/**
	 * 
	 * @����:��ʼ�������༶
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-22 ����10:00:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void initJsbj(User user) throws Exception{
		BfjsCsszModel model = dao.getModel();
		//��ǰ�����Ƿ��Ѿ��о����༶���У�return , û�У� ��ʼ��
		boolean isHaveJsbj = dao.isHaveJsbj(model);
		dao.initMrf(model,user);
		if (isHaveJsbj){
			return ;
		}
		BfjsFswhDao fswhDao  = new BfjsFswhDao();
		fswhDao.delTjjl(model.getXn(),model.getXq(),user);
		//��ʼ�������༶
		dao.initJsbjByBj(model,user);
		
		
	}
	
}
