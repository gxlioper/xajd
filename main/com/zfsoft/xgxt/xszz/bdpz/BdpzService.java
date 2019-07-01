package com.zfsoft.xgxt.xszz.bdpz;

import java.util.HashMap;
import java.util.List;


import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����������ģ��
 * @�๦������: ѧ������2013�棬��̬����ز���
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-18 ����04:39:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BdpzService extends SuperServiceImpl<BdpzForm, BdpzDao> {

	
	private BdpzDao dao = new BdpzDao();
	private static final String T = "t";
	private static final String M = "m";
	private static final String splitStr = "#";
	
	
	public BdpzService(){
		super.setDao(dao);
	}

	/**
	 * ������Ŀ�ֶ�������Ϣ
	 * @param gnmk
	 * @return
	 */
	public List<HashMap<String,String>> getBdpz(String gnmk){
		
		if (StringUtil.isNull(gnmk)){
			logger.error("gnmk can't null ! ");
			throw new NullPointerException();
		}
		
		return dao.getBdpz(gnmk);
	}
	
	
	/**
	 * ������Ŀѧ��������Ϣ����
	 * @param gnmk
	 * @return
	 */
	public List<HashMap<String,String>> getJbxxpz(String gnmk){
		
		if (StringUtil.isNull(gnmk)){
			logger.error("gnmk can't null ! ");
			throw new NullPointerException();
		}
		
		return dao.getJbxxpz(gnmk);
	}
	
	
	
	/**
	 * ���ݱ����ü�����������ѡ����ѡ��
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String,String>> getSjyList(BdpzForm model){
		
		String sjy = model.getSjy();
		
		if (StringUtil.isNull(sjy) || sjy.length() == 0){
			logger.error("sjy is null !");
			throw new NullPointerException();
		}
		
		List<HashMap<String,String>> dataList = null;
		//����Դ���÷�ʽ T(T#tableName#dm#mc) �� M��T ����table M ����Method
		String pzfs = sjy.substring(0,1);
		
		if (T.equalsIgnoreCase(pzfs)){
			String[] info = sjy.split(splitStr);
			
			if (info == null || info.length != 4){
				logger.error("������Ϣ�����Ϲ���");
				throw new NullPointerException();
			}
			
			String[] params = new String[]{info[2],info[3],info[1]};
			dataList = dao.getSjyList(params);
		} else if (M.equalsIgnoreCase(pzfs)){
			//������� sjy ���ø�ʽ (M#com.zfsoft.xgxt.**Service#methodName#param)
			String[] info = sjy.split(splitStr);
			//�������ǲ����������ĵ��Ǵ���������������Ĳ�֧��
			if (info == null || (info.length != 4 && info.length != 3)){
				logger.error("������Ϣ�����Ϲ���");
				throw new NullPointerException();
			}
			
			String className = info[1];
			String methodName = info[2];
			String param = info.length == 4 ? info[3] : null;
			
			try{
				//��ȡ��������
				Class<?> t = Class.forName(className);
				//��ȡ����ʵ��
				Object o =  t.getConstructor().newInstance();
				if (StringUtil.isNull(param)){
					dataList = (List<HashMap<String, String>>) t.getMethod(methodName).invoke(o);
				} else {
					dataList = (List<HashMap<String, String>>) t.getMethod(methodName,new Class[]{String.class}).invoke(o,param);
				}
			} catch(Exception e){
				e.printStackTrace();
				logger.error("����������������");
			}
		} else {
			logger.error("����Դ������Ϣ�����Ϲ���");
		}
		
		return dataList;
	}
}
