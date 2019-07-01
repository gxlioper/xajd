/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-22 ����08:54:47 
 */  
package com.zfsoft.xgxt.xlzx.yysq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ԤԼ����
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-8-22 ����08:54:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YysqService extends SuperServiceImpl<YysqForm, YysqDao> {
	
	private YysqDao dao = new YysqDao();

	public YysqService() {
		super.setDao(dao);
	}
	
	
	//��������ԤԼ��ѯ��Ϣ�б���ҳ��
	public List<HashMap<String, String>> queryYyzxInfoList(YysqForm model,User user)
	throws Exception {
		
		return dao.queryYyzxInfoList(model, user);
	}
	
	//����ԤԼ��Ų�ѯԤԼ��ѯ����
	public HashMap<String,String> getYyzxDetail(String id) throws Exception{
		
		return dao.getYyzxDetail(id);
		
	}
	//ԤԼ������Ϣ����
	public HashMap<String,String> getYysqInfoById(String id) throws Exception {
		
		return dao.getYysqInfoById(id);
	}
	
	public HashMap<String,String> getYysqById(String id){
		
		return  dao.getYysqById(id);
	}

	public HashMap<String,String> getYysqByCn(String xh,String zgh,String yyzxrq){
		
		return  dao.getYysqByCn(xh,zgh,yyzxrq);
	}
	
	public HashMap<String,String> getYysqByXh(String xh){
			
			return  dao.getYysqByXh(xh);
		}
	public HashMap<String,String> getYysqByXhAnddDate(String yyzxrq,String xh){
		
		return  dao.getYysqByXhAnddDate(yyzxrq,xh);
	}
	
	public List getYysqByZghAnddDate(String yyzxrq,String zgh){
		
		return  dao.getYysqByZghAnddDate(yyzxrq,zgh);
	}
	
	public boolean saveYysqInfo(YysqForm model)
	throws Exception {
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setId(guid);
		return dao.saveYysqInfo(model);
	}
	
	public boolean updateYysqInfo(YysqForm model)
	throws Exception {
		
		return dao.updateYysqInfo(model);
	}
	
	public boolean updateYysqStatus(YysqForm model)
	throws Exception {
		
		return dao.updateYysqStatus(model);
	}
	
	public int delYysqByZgh(String[] zgh) throws Exception {
		
		return dao.delYysqByZgh(zgh);
	}
	public HashMap<String,String> getStuInfoByXh(String xh){
		
		return dao.getStuInfoByXh(xh);
	}


	/** 
	 * @����:ɾ��ԤԼ����ʦɾ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-28 ����02:27:44
	 * @param split
	 * @return
	 * String[] �������� 
	 * @throws 
	 */
	public int delYyzxInfo(String[] ids) throws Exception{
		int count = dao.delYyzxInfo(ids);
		if(count > 0){
			dao.delXlzxInfo(ids);
		}
		return count;
	}
	
	/**
	 * 
	 * @����: ���㰴ʱ�����ԤԼ������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-27 ����03:02:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pbdate
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSjdYySx(String pbdate){
		return dao.getSjdYySx(pbdate);
	}
	
	/**
	 * 
	 * @����: ѧ��ԤԼʱ���(��ԤԼ��ԤԼ�ɹ��Ľ��й���)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-28 ����09:12:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yyzxrq
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsYySjd(String yyzxrq,String zgh,String xh){
		return dao.getXsYySjd(yyzxrq, zgh,xh);
	}


	/**
	 * @����:��֤������Ϣ�Ƿ����(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-12-26 ����04:14:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean ��������
	 * @throws
	 */
	public boolean isExists(String xh){
		String num = dao.getRecord(xh);
		return Integer.valueOf(num)>0;
	}

	/**
	 * @����:��ȡѧ����ѯ������е���������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/12/27 15:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.lang.String
	 */
	public String getCountJg(String xh) {
		return dao.getCountJg(xh);
	}
}
