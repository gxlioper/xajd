/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-10 ����11:10:07 
 */  
package com.zfsoft.xgxt.xlzx.tsxsgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ѧ��ά��ģ��(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-9-10 ����11:10:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsService extends SuperServiceImpl<TsxsForm, TsxsDao> {
	
	private TsxsDao dao = new TsxsDao();
	
	public TsxsService() {
		super.setDao(dao);
	}
	
	
	/**
	 * ����Id��ѯ����ѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsInfoById(String id){
		
		return dao.getTsxsInfoById(id);
	}
	
	/**
	 * ����Id��ѯ����ѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsDetailByXh(String xh){
		
		HashMap<String,String> xsxx = dao.getTsxsDetailByXh(xh);
		if(xsxx !=null && xsxx.size()>0){

			//��ȡ������������
			if(xsxx.get("knlxdm")!=null && !"".equals(xsxx.get("knlxdm"))){
				String knlxmc = getKnlxMc(xsxx.get("knlxdm"));
				xsxx.put("knlxmc", knlxmc);
			}
		}
		return xsxx;
	}
	/**
	 * ����Id��ѯ����ѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsDetailByXhZc(TsxsForm myForm){
		
		HashMap<String,String> xsxx = dao.getTsxsDetailByXhZc(myForm);
		if(xsxx !=null && xsxx.size()>0){

			//��ȡ������������
			if(xsxx.get("knlxdm")!=null && !"".equals(xsxx.get("knlxdm"))){
				String knlxmc = getKnlxMc(xsxx.get("knlxdm"));
				xsxx.put("knlxmc", knlxmc);
			}
		}
		return xsxx;
	}
	
	/**
	 * ����������ѧ����Ϣ��
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveTsxsInfo(TsxsForm model)
			throws Exception {
		
		return dao.saveTsxsInfo(model);
	}
	

	/**
	 * 
	 * @����:ɾ������ѧ����Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-10 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delTsxsById(String[] id) throws Exception {
		
		return dao.delTsxsById(id);
	}
	/**
	 * 
	 * @����:ɾ������ѧ����ע״̬
	 * @���ߣ�1004
	 * @���ڣ�2013-9-10 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public boolean updateBatchGzStatus(String[] id,String gzzt) throws Exception{
		
		return dao.updateBatchGzStatus(id, gzzt);
	}
	
	/**
	 * 
	 * @����:�޸�����ѧ����Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-10 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public boolean updateTsxsInfo(TsxsForm model) throws Exception{
		
		return dao.updateTsxsInfo(model);
	}
	
	public List<HashMap<String, String>> getKnlxList(String[] knlxdms){
		
		return dao.getKnlxList(knlxdms);
	}
	
	public List<HashMap<String, String>> getKnlxList(){
		
		return dao.getKnlxList();
	}
	
	public String getKnlxMc(String knlxdms){
		String knlxmc = "";
		if(knlxdms!=null){
			String[] knlxdm = knlxdms.split(",");
			List<HashMap<String, String>> knlxInfoList = this.getKnlxList(knlxdm);
			if(knlxInfoList!=null && knlxInfoList.size()>0){
				for(int i=0;i<knlxInfoList.size();i++){
					if(i==knlxInfoList.size()-1){
						knlxmc += knlxInfoList.get(i).get("knlxmc");
					}else{
						knlxmc += knlxInfoList.get(i).get("knlxmc")+" ��";
					}
				}
			}
		}
		return knlxmc;
	}


	/** 
	 * @����:����ѧ�Ż�ȡ���µ�����ѧ����Ϣ(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-4-8 ����04:59:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getZcTsxsInfo(TsxsForm myForm) {
		
		return dao.getZcTsxsInfo(myForm);
	}
}
