/**
 * @���ţ�ѧ����Ʒ��ҵ��
 * @���ڣ�2016��11��17��
 */  
package com.zfsoft.xgxt.xlzx.pxwh;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.date.DateUtils;

/**
 * @ϵͳ���ƣ�ѧ����������ϵͳ
 * @ģ�����ƣ���������ѵά�� ����ģ��
 * @�๦����������������ѵά�� ҵ���
 * @���ߣ�׿��[����:1391]
 * @ʱ�䣺2016��11��17��
 * @�汾��V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class PxwhService extends SuperServiceImpl<PxwhForm,PxwhDao> {
	private PxwhDao dao= new PxwhDao();
	
	public PxwhService(){
		super.setDao(dao);
	 }
	 
	/**
	 * @����������Ψһ��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return ture,У��ͨ����false,У�鲻ͨ��,�����ظ���
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean checkExist(PxwhForm form) throws Exception {
		return "0".equals(dao.checkExist(form));
	}

	/** 
	 * @��������ȡ��ѵ�����б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��18�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getPxbmList(PxwhForm form,User user) throws Exception {
		List<HashMap<String, String>> list=dao.getPxbmList(form,user);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		for(HashMap<String,String> map:list){
			if(DateUtils.betweenDate(format.parse(map.get("kssj")),format.parse(map.get("jssj")))  //�жϵ�ǰʱ���Ƿ���������
				&&"��".equals(map.get("bmkg"))  //�жϱ��������Ƿ��
				&&(Integer.valueOf(map.get("sxrs"))>(Integer.valueOf(map.get("ybmrs"))))){  //�ж��ѱ��������Ƿ�δ��������
				map.put("sfkfbm", "1");  //�Ƿ񿪹ر��� ��1
			}else{
				map.put("sfkfbm", "0");
			}
		}
		return list;
	}
	
	public boolean  bmcz(String pxid,String xh,String bmtype) throws Exception{
		return dao.bmcz(pxid, xh, bmtype);
	}

	/** 
	 * @�������ѱ���ѧ���б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��19�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getYbmxsList(PxwhForm model) throws Exception {
		return dao.getYbmxsList(model);
	}
	
	/**
	 * @����������ѧ�������б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��2��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getBmxsdcList(PxwhForm model) throws Exception {
		//����ҳ
		Pages pages = (Pages) model.getClass().getMethod("getPages").invoke(model);
		pages.setPageSize(Integer.MAX_VALUE);
		model.getClass().getMethod("setPages",Pages.class).invoke(model, pages);
		
		return dao.getBmxsdcList(model);
	}
	
} 


