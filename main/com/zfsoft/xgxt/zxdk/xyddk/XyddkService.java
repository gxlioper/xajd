/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-25 ����03:28:22 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.SuperAuditService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ����-������ѧ����
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-25 ����03:28:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyddkService extends SuperAuditService<XyddkModel, XyddkDao> {

	private static final String sqsh = "sqsh";
	
	public boolean afterLastAudit(XyddkModel model) {
		
		DkjgDao dkjgDao = new DkjgDao();
		
		model.setSjly(sqsh);
		model.setHtbh(model.getZd3());
		model.setYhmc(model.getZd5());
		model.setLxdh(model.getZd6());
		model.setSplcid(null);
		
		//�����Ƽ���ѧ���Ի�
		if(Base.xxdm.equals("10704")){
			model.setBldkrq(model.getZd8());
			model.setHtdd(model.getZd10());
			model.setXyjbr(model.getZd2());
			if(!StringUtil.isNull(model.getZd7())){				
				model.setDkje(model.getZd7());
			}
			if(!StringUtil.isNull(model.getZd9())){				
				model.setDkqx(model.getZd9());
			}
		}
		
		try {
			dao.runUpdate(model);
			//��������֮ǰ����ɾ���������ͬѧ��ͬѧ�ŵ�����
			dao.delBeforeShtg(model);
			if("10511".equals(Base.xxdm)){
				boolean flag = dkjgDao.runInsert(model);
				if(flag){
					/**
					 * ���ͨ����������
					 */
					flag = this.InsertIntoFdb(model.getId());
				}
				return flag;
			}else{
				return dkjgDao.runInsert(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean deleteResult(XyddkModel model) {
		
		DkjgDao dkjgDao = new DkjgDao();
		
		try {
			return dkjgDao.runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<HashMap<String, String>> getAudingList(XyddkModel t, User user)
		throws Exception {
		
		return dao.getAudingList(t, user);
	}


	/** 
	 * @����:����ID��ѯ��ѧ������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-25 ����03:07:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap �������� 
	 * @throws 
	 */
	public HashMap<String, String> getDkxxSq(String id) {
		
		return dao.getDkxxSq(id);
	}
	
	
	/**
	 * 
	 * @����: ��ѧ�š�ѧ���ѯ��¼����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-3-23 ����05:10:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCountByXhAndXn(XyddkModel t){
		
		return dao.getCountByXhAndXn(t);
	}
	
	/**
	 * 
	 * @����:��ѯѧ���Ѵ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-11-26 ����03:48:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYdkqx(String xh){
		return dao.getYdkqx(xh);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: 
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-15 ����11:42:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * booleanf �������� 
	 * @throws
	 */
	public boolean delBeforeShtg(XyddkModel model) throws Exception{
		return dao.delBeforeShtg(model);
	}
	
	/**
	 * 
	 * @����: ��ѯ�ϼ�������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-22 ����03:07:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param gwid
	 * @param splc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getShlcInfo(String sqid,String gwid,String splc) {
		return dao.getShlcInfo(sqid, gwid, splc);
	}
	
	/**
	 * 
	 * @����: ��ȡѧ����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-8 ����04:36:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsxxByHsd(String xh){
		return dao.getXsxxByHsd(xh);
	}
	
	/**
	 * 
	 * @����: �Ƿ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����11:19:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public boolean  getWjmSameRs(String xh,String gid){
		return dao.getWjmSameRs(xh, gid);
	}
	
	/**
	 * 
	 * @����: �����Ƿ������ͬѧ����ظ���¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����02:10:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xns
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean getXnXhSameRs(String[] xns,String xh,String id){
		return dao.getXnXhSameRs(xns, xh,id);
	}
	
	/**
	 * 
	 * @����: ��������xg_hsd_zxdk_ndkb
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����03:26:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
    public boolean saveRsBatch(HashMap<String, String[]> para,String xh,String id){
    	String[] xns = para.get("xns");
    	String[] nzsfdks = para.get("nzsfdks");
    	String[] nxfdks = para.get("nxfdks");
    	String[] nshfdks = para.get("nshfdks");
    	String[] nzsfyjes = para.get("nzsfyjes");
    	String[] nxfyjes = para.get("nxfyjes");
    	List<String[]> parameter = new ArrayList<String[]>();
    	for (int i = 0; i < xns.length; i++) {
    		/**
    		 * ��һ��Ĭ��Ϊ�´�������Ĭ��Ϊ����
    		 */
    		String dkzt = "xud";
    		if(i == 0){
    			dkzt = "xind";
    		}
    		parameter.add(new String[]{id,xh,xns[i],nzsfdks[i],nxfdks[i],nshfdks[i],nzsfyjes[i],nxfyjes[i],dkzt});
		}
    	return dao.saveRsBatch(parameter);
	}
    
    /**
	 * @throws Exception 
	 * 
	 * @����: ����ɾ��xg_hsd_zxdk_ndkb�еļ�¼���޸�ʱʹ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-11 ����10:13:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delRs(String id) throws Exception{
		return dao.delRs(id);
	}
	
	/**
	 * 
	 * @����: �޸�ʱ�ж��Ƿ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����11:19:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public boolean  getWjmSameRsUpdate(String xh,String gid){
		return dao.getWjmSameRsUpdate(xh, gid);
	}
	
	/**
	 * 
	 * @����: ����id���Ҹü�¼��Ӧ�Ĵ������¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-11 ����05:19:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getNdkbList(String id){
		return dao.getNdkbList(id);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ʦ�����idɾ��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-14 ����09:51:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delNdkb(String[] ids) throws Exception{
		return dao.delNdkb(ids);
	}
	
	/**
	 * 
	 * @����: ���������ļ����Ƿ�����ͬ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-14 ����03:04:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public boolean checkWjmIsSame(String gid){
		return dao.checkWjmIsSame(gid);
	}
	
	/**
	 * 
	 * @����: ɾ���Ŵ�������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-15 ����06:59:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delFdb(String[] ids) throws Exception{
		return dao.delFdb(ids);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����Ŵ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-15 ����07:09:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean InsertIntoFdb(String id) throws Exception{
		return dao.InsertIntoFdb(id);
	}
	
	/**
	 * 
	 * @����: ��֤��ѧ���Ƿ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-5-31 ����04:42:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfKsqCurrXn(String xn,String xh){
		return dao.checkIfKsqCurrXn(xn,xh);
		
	}
	
}
