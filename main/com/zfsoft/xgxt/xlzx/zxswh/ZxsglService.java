package com.zfsoft.xgxt.xlzx.zxswh;
/**
 * @����:ѧ����Ʒ��ҵ��
 * @ʱ�䣺 2013-7-23 ����05:36:33
 */  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @ϵͳ����: ѧ��ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ��ѯʦ��Ϣ��ѯ
 * @���ߣ� wanghj
 * @ʱ�䣺 2013-8-13 ����03:36:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxsglService extends SuperServiceImpl<ZxsglForm, ZxsglDao> {
	
	private ZxsglDao dao = new ZxsglDao();
	
	public ZxsglService() {
		super.setDao(dao);
	}

	/**
	 * ������ѯʦ��Ų�ѯ��ѯʦ��Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getZxsInfoByZgh(String zgh) throws Exception {
		return dao.getZxsInfoByZgh(zgh);
	}
	
	
	public List<HashMap<String,String>> getZxsInfoByZgh(String[] zgh,String xq) throws Exception {
		return dao.getZxsInfoByZgh(zgh,xq);
	}
	
	/**
	 * ������ѯʦ���������ѯ��ѯʦ��Ϣ
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getZxsInfoByZgh(String[] zgh) throws Exception {
		return dao.getZxsInfoByZgh(zgh,"");
	}
	
	/**
	 * ������ѯʦ״̬��ѯ��ѯʦ��Ϣ
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getZxsList(String status) throws Exception {
		return dao.getZxsList(status);
	}
	/**
	 * ������ѯʦ״̬��ѯ��ѯʦ��Ϣ�������Ƿ���ѧ��ԤԼ��
	 */
	public List<HashMap<String,String>> getZxsListXssq(String status, String pbdate) throws Exception {
		return dao.getZxsListXssq(status, pbdate);
	}
	
	/**
	 * ������ѯʦ��Ϣ
	 * @param model
	 * @return
	 */
	public boolean saveZxsInfo(ZxsglForm model) throws Exception{
		return dao.saveZxsInfo(model);
	}
	/**
	 * ����ְ����,���ڲ�ѯ��ѯʦ
	 */
	public List<HashMap<String, String>> getZxsListYyfk(String[] zghs, String zxrq){
		return dao.getZxsListYyfk(zghs, zxrq);
	}
	/**
	 * ɾ����ѯʦ��Ϣ
	 * @param model
	 * @return
	 */
	public int delZxsByzgh(String[] zgh) throws Exception{
		int a = dao.delZxsByZgh(zgh);
		return a;
	}
	
	/**
	 * ������ѯʦ��Ϣ
	 * @param model
	 * @return
	 */
	public boolean updateZxsInfo(ZxsglForm model) throws Exception{

		return dao.updateZxsInfo(model);
	}
	
	/**
	 * ����������ѯʦ״̬
	 * @param model
	 * @return
	 */
	public boolean updateBatchZgStatus(String[] zgh,String status) throws Exception{
		
		return dao.updateBatchZgStatus(zgh, status);
	}
	
	/**
	 * ����ְ���Ų�ѯ��ʦ��Ϣ
	 * @param model
	 * @return
	 */
	public Map<String,String> getFdyInfo(String zgh){
		
		return dao.getFdyInfo(zgh);
	}
	
	/**
	 * ���������ѯʦ
	 */
	public List<HashMap<String, String>> getZxsxxAllList(){
		return dao.getZxsxxAllList();
	}
	/**
	 * ���������ѯʦ  (����ԤԼʱ��)
	 */
	public List<HashMap<String, String>> getZxsxxAllByYysjList(String yysj){
		return dao.getZxsxxAllByYysjList(yysj);
	}
	
	/**
	 * У���б�
	 */
	public List<HashMap<String, String>> getXq() {
		return dao.getXq();	
	}
	
	/**
	 * 
	 * @����: ʱ���ԤԼ��ѯʦ�б��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-27 ����04:04:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param xq
	 * @param pbdate
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZxsInfoByZghForsjdYy(String[] zgh,String xq,String pbdate) throws Exception {
		return dao.getZxsInfoByZghForsjdYy(zgh, xq, pbdate);
	}
	
	/**
	 * 
	 * @����: ʱ���ԤԼ������ѯ������ԤԼѧ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-30 ����10:12:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zghs
	 * @param zxrq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxsListYyfkForSjd(String[] zghs, String zxrq){
		return dao.getZxsListYyfkForSjd(zghs, zxrq);
	}

	/** 
	 * @����:��ѯ�ó�����(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-3-5 ����09:38:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSclyList() {
		
		return dao.getSclyList();
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-3-5 ����10:04:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sclydms
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getSclyMc(String sclydms) {
		String sclymc = "";
		if(sclydms!=null){
			String[] sclydm = sclydms.split(",");
			List<HashMap<String, String>> sclyInfoList = this.getSclyList(sclydm);
			if(sclyInfoList!=null && sclyInfoList.size()>0){
				for(int i=0;i<sclyInfoList.size();i++){
					if(i == sclyInfoList.size()-1){
						sclymc += sclyInfoList.get(i).get("sclymc");
					}else{
						sclymc += sclyInfoList.get(i).get("sclymc")+" ��";
					}
				}
			}
		}
		return sclymc;
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-3-5 ����10:06:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sclydm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	private List<HashMap<String, String>> getSclyList(String[] sclydm) {
		return dao.getSclyList(sclydm);
	}

}
