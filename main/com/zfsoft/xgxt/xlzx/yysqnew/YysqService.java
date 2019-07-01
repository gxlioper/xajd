  
package com.zfsoft.xgxt.xlzx.yysqnew;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.xlzx.zxyyclnew.ZxyyclDao;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
/** 
 * ������ѯ����ģ��
 */

public class YysqService extends SuperServiceImpl<YysqForm, YysqDao> {
	
	private YysqDao dao = new YysqDao();

	public YysqService() {
		super.setDao(dao);
	}
	
	/** 
	 * ԤԼ��ѯ������ѯҳ��
	 */
	public List<HashMap<String, String>> queryYyfkList(YysqForm model,User user) throws Exception {
		return dao.queryYyfkList(model, user);
	}
	/**
	 * ����ԤԼ��Ų�ѯԤԼ��ѯ����
	 */
	public HashMap<String,String> getYyzxDetail(String id) throws Exception {
		return dao.getYyzxDetail(id);
	}
	/**
	 * ��ѯ��ʷ��Ϣ
	 */
	public List<HashMap<String, String>> getZxlsxxList(String xh, String id) throws Exception {
		return dao.getZxlsxxList(xh, id);
	}
	/**
	 * ������ѯʦ������ڲ�ѯԤԼ���������Ϣ
	 */
	public List<HashMap<String, String>> getYysqByZghAnddDate(String yyzxrq,String zgh){
		return dao.getYysqByZghAnddDate(yyzxrq,zgh);
	}
	
	//��������ԤԼ��ѯ��Ϣ�б���ҳ��
	public List<HashMap<String, String>> queryYyzxInfoList(YysqForm model,User user)
	throws Exception {
		
		return dao.queryYyzxInfoList(model, user);
	}
	//��������ԤԼ��ѯ��Ϣ�б�����ҳ��
	public List<HashMap<String, String>> queryYyzxInfoListAll(YysqForm t,User user)
	throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.queryYyzxInfoList(t, user);
	}
	//��������ԤԼ��ѯ��Ϣ�б���ҳ��
	public List<HashMap<String, String>> queryYyzxInfoListJg(YysqForm model,User user)
	throws Exception {
		
		return dao.queryYyzxInfoListJg(model, user);
	}
	//��������ԤԼ��ѯ��Ϣ�б�����ҳ��
	public List<HashMap<String, String>> queryYyzxInfoListAllJg(YysqForm t,User user)
	throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.queryYyzxInfoListJg(t, user);
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
	public HashMap<String,String> getYysqByXhAnddDateId(String yyzxrq,String xh,String id){
		
		return  dao.getYysqByXhAnddDateId(yyzxrq,xh,id);
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
	
	/**
	 * ����ѯʦ
	 */
	public boolean isZxs(String zgh){
		return dao.isZxs(zgh);
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
	 * ɾ��ԤԼ����ʦɾ����
	 */
	public int delYyzxInfo(String[] ids) throws Exception{
		int count = dao.delYyzxInfo(ids);
		if(count > 0){
			dao.delXlzxInfo(ids);
			new ZxyyclDao().deleteJzxx(ids);
		}
		return count;
	}
	
	/**
	 * ����ѧ�����ڲ�ѯԤԼ���������Ϣ[��ʱ�����֤]
	 */
	public HashMap<String,String> getYysqByXhAnddDateForSjd(String yyzxrq,String xh,String id){
		return dao.getYysqByXhAnddDateForSjd(yyzxrq, xh,id);
	}

	/**
	 *  ��ȡ��ѯ���������б�.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-27 15:28
	 * @param
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getZxwtlxList() {
		return dao.getZxwtlxList();
	}

	public boolean updateQdzt(YysqForm model) throws Exception {
		return dao.updateQdzt(model);
	}

	/**
	 * @����:�ҳ���Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/1/2 16:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [id]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getJzxx(String sqid) {
		return dao.getJzxx(sqid);
	}
}
