package com.zfsoft.xgxt.xszz.jtqkdc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������2013�棬��ͥ�������
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-18 ����05:35:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JtqkdcService extends SuperServiceImpl<JtqkdcForm, JtqkdcDao> {

	private JtqkdcDao dao = new JtqkdcDao();
	
	public JtqkdcService(){
		super.setDao(dao);
	}
	
	/**
	 * ����ѧ�Ų�ѯ��ͥ��Ա�б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getJtcyList(String xh){
		
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		
		return dao.getJtcyList(xh);
	}
	

	
	/**
	 * �յ�list
	 * @param size
	 * @return
	 */
	public List<HashMap<String,String>> getBlankList(int size){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		
		return list;
	}

	
	
	public boolean runInsert(JtqkdcForm t) throws Exception {
		
		JtqkdcForm model = dao.getModel(t);
		
		boolean isSuccess = true;
		
		t.setDcsj(GetTime.getTimeByFormat("YYYYMMDD"));
		
		//�ж��Ƿ��Ѿ�����д����ͥ�������
		if (model == null){
			isSuccess = super.runInsert(t);
		} else {
			isSuccess = super.runUpdate(t);
		}
		
		//�����ͥ��Ա��Ϣ
		saveJtcy(t);
		
		return isSuccess;
	}
	public boolean runInsertOfYdxg(JtqkdcForm t) throws Exception {
		
		JtqkdcForm model = dao.getModel(t);
		
		boolean isSuccess = true;
		
		t.setDcsj(GetTime.getTimeByFormat("YYYYMMDD"));
		
		//�ж��Ƿ��Ѿ�����д����ͥ�������
		if (model == null){
			isSuccess = super.runInsert(t);
		} else {
			isSuccess = super.runUpdate(t);
		}
		return isSuccess;
	}
	
	//�����ͥ��Ա��Ϣ
	private void saveJtcy(JtqkdcForm t) throws Exception{
		
		//��ɾ����
		String xh = t.getXh();
		//ɾ����ͥ��Ա
		dao.deleteJtcy(new String[]{xh});
		
		JtcyForm jtcy = t.getJtcy();
		
		Field[] fields = jtcy.getClass().getDeclaredFields();
		Map<String,String[]> map = new HashMap<String,String[]>();
		
		List<String> keys = new ArrayList<String>();
		
		//���ݼ�ͥ��Ա��������ֵ����ȡȫ����ͥ��Ա����
		for (Field field : fields){
			String fieldName = field.getName();
			String methodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
			String[] values = (String[]) jtcy.getClass().getMethod(methodName).invoke(jtcy);
			
			if (null != values){
				keys.add(fieldName);
				map.put(fieldName, values);
			}
		}
		
		if (keys.size() == 0){
			logger.error("�޼�ͥ��Ա��Ϣ��Ҫ����!");
			throw new NullPointerException();
		}
		

		//����ͥ��Ա����ƴװ���ж���
		int m = 0;
		String[] zdmc = new String[keys.size()];
		List<String[]> params = new ArrayList<String[]>();

		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			zdmc[m] = entry.getKey();
			String[] values = entry.getValue();
			
			for (int i = 0 , n = values.length ; i < n ; i++){
				String[] param = params.size() < i+1 ? new String[keys.size()] : params.get(i);
				param[m] = values[i];
				
				if (params.size() > i){
					params.remove(i);
				}
				
				params.add(i,param);
			}
			m++;
		}
		
		//����ͥ��Ա���ݣ�ȫ���ǿյ���ΪҪ��������ݲ���
		List<String[]> newParams = new ArrayList<String[]>();
		
		for (int i = 0 ; i < params.size() ; i++){
			String joinStr = Arrays.toString(params.get(i)).replaceAll(",", "").replace("[", "").replace("]", "");
			
			if (!StringUtil.isNull(joinStr.trim())){
				
				newParams.add(StringUtils.joinStrArr(new String[]{xh},params.get(i)));
			}
		}
		
		
		dao.saveJtcy(StringUtils.joinStrArr(new String[]{"xh"},zdmc), newParams);
	}

	
	
	
	
	/**
	 * ��ͥ���������Ϣɾ��
	 */
	public int runDelete(String[] values) throws Exception {
		
		//��ͥ�������ɾ��
		int num = super.runDelete(values);
		
		if (num > 0){
			//��ͥ��Աɾ��
			dao.deleteJtcy(values);
		}
		
		return num;
	}

	/** 
	 * @����:��ø�����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-9-22 ����01:44:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param string
	 * @return
	 * JtqkdcForm �������� 
	 * @throws 
	 */
	public HashMap<String, String> getfqInfo(String xh) {

		return dao.getfqInfo(xh);
	}

	/** 
	 * @����:���ĸ����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-9-22 ����01:44:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param string
	 * @return
	 * JtqkdcForm �������� 
	 * @throws 
	 */
	public HashMap<String, String> getmqInfo(String xh) {

		return dao.getmqInfo(xh);
	}

	/** 
	 * @����:�Ϻ������ѧ���Ի���ѯ��ͥ��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-10-29 ����04:50:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param string
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getJtqkInfo(String xh) {
		
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		
		return dao.getJtqkInfo(xh);
	}
	
	
	/**
	 * ����ѧ�Ų�ѯ��ͥ��Ա�б�Ĭ��ǰ3�����Ϻ����󣩣�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getJtcyListSh(String xh){
		
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		
		return dao.getJtcyListSh(xh);
	}
	
	/**
	 * 
	 * @����:��д�����ģ�飬�㽭��ѧ�Ƿ�м���м�֤���������ҳ�������ϸ����Ƿ�м�ֵΪ�ǣ�1����ʱ��
	 * ȡcjbh(ylzd4)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-10-30 ����04:05:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public String getCjbh_10335(String xh){
		return dao.getCjbh_10335(xh);
	}
	
	/**
	 * @������ͨ��sql��ȡǰn����ͥ��Ա�б�����n�����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��6�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getJtcyList(String xh,int n){
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		List<HashMap<String, String>> list = dao.getJtcyList(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
	/**
	 * @����: ���ݺ�ʵ�˵��û�����ѯ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-8-8 ����11:32:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String yhxm(String yhm){
		return dao.yhxm(yhm);
	}
	
	/**
	 * @����: ����ѧ�Ż�ȡ��ͥ�������
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-9-11 ����05:10:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getJtsrqkByXh (String xh){
		return dao.getJtsrqkByXh(xh);
	}
}
