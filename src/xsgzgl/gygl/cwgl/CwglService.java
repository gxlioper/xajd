package xsgzgl.gygl.cwgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewService;

public class CwglService extends GyglNewService{
	private CwglDAO cwglDao = new CwglDAO();
	
	/**
	 * ����������λ
	 * @param model
	 * @return
	 */
	public String saveCwwh(CwglModel model){
		
		return cwglDao.saveCwwh(model) ? "����ɹ���" : "����ʧ�ܣ�";
	}
	
	public boolean updateCwqsh(String qsh1 , String sqh2 , String lddm) throws Exception{
		return cwglDao.updateCwqsh(qsh1, sqh2 , lddm);
	}
	
	/**
	 * �޸Ĵ�λ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String updateCwwh(String pkValue, CwglModel model) throws Exception{
		boolean flag=false;
		flag=cwglDao.updateCwwh(pkValue, model);
		if(flag){
			//ɾ����סУ��ע
			cwglDao.deleteBzxbz(model.getXh());
		}
		return flag ? "����ɹ���" : "����ʧ�ܣ�";
	}
	
	/**
	 * @��������ס
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��2��21�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkValue
	 * @param model
	 * @return
	 * @throws Exception
	 * String ��������
	 */
	public String ruzhu(String pkValue, CwglModel model) throws Exception{
		boolean flag=false;
		flag=cwglDao.updateCwwh(pkValue, model);
		cwglDao.ruzhu(pkValue, model);
		if(flag){
			//ɾ����סУ��ע
			cwglDao.deleteBzxbz(model.getXh());
		}
		return flag ? "����ɹ���" : "����ʧ�ܣ�";
	}
	
	/**
	 * ��ѯ��λ
	 * @param model
	 * @return
	 */
	public List<String[]> queryCw(CwglModel model,HttpServletRequest request) throws Exception{
		
		return cwglDao.queryCw(model,request);
	}
	
	/**
	 * ����ɾ����λ��Ϣ
	 * @param pk
	 * @return
	 */
	public boolean delCw(String[] pk){
		List<String[]> params = new ArrayList<String[]>();
		
		for(String str : pk){
			params.add(new String[]{str});
		}
		return cwglDao.delCw(params);
	}
	
	/**
	 * ��ȡ������λ��Ϣ
	 * @param pk
	 * @return
	 */
	public Map<String, String> getCwForPk(String pk){
		return cwglDao.getCwForPk(pk);
	}
	
	/**
	 * ��ȡ���λ��
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public Map<String, String> getCwhForQs(String lddm, String qsh){
		return cwglDao.getCwhForQs(lddm, qsh);
	}
	
	/**
	 * �����޸��Ƿ���
	 * @param myForm
	 * @return
	 */
	public String updateCwbl(CwglForm myForm,User user) throws Exception{
		return cwglDao.updateCwbl(myForm,user);
	}
	/**
	 * Ԥ������б�
	 */
	public List<HashMap<String, String>> getYllbList() {
		return cwglDao.getYllbList();
	}

	/**
	 * ��λ�Ե�
	 * @param pk
	 * @param model
	 * @return
	 */
	public boolean cwdd(String[] pk,CwglModel model){
		return cwglDao.cwdd(pk,model);
	}
	
	
	/**
	 * ��ȡ��ǰ��ѯ���ݼ�����ס�Ĵ�λ����
	 * @return
	 */
	public String getYrzrs(CwglModel model,User user) throws Exception{
		return cwglDao.getYrzrs(model,user);
	}
	
	/**
	 * ��ȡ��ǰ��ѯ���ݼ�δ��ס�Ĵ�λ����
	 * @return
	 */
	public String getWrzrs(CwglModel model) throws Exception{
		return cwglDao.getWrzrs(model);
	}
	
	public String getSearchTjstr(CwglModel model) throws Exception{
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		String searchTjstr = "";
		if(searchTj!=null && !"".equalsIgnoreCase(searchTj)){
			String[] tj = searchTj.replace("?", "split").split("split");
			for(int i=0;i<inputV.length;i++){
				searchTjstr += tj[i]+"'"+inputV[i]+"'";
			}
			searchTjstr+=tj[inputV.length];
		}
		return searchTjstr;
	}
	
	/**
	 * ����������Ϣ
	 * @author zhanghui
	 */
	public String saveTsxx(CwglForm myForm,HttpServletRequest request)throws Exception{

		User user = getUser(request);
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =request.getParameter("searchTjstr");
		String xh = request.getParameter("xh");
		String xhstr = request.getParameter("xhstr");
		String[] pk_xh = null;
		if(xh !=null && !"".equalsIgnoreCase(xh)){//����ѧ��
			pk_xh = new String[]{xh};
		}else if(xhstr !=null && !"".equalsIgnoreCase(xhstr)){//���ѧ��
			pk_xh = xhstr.split("!!splitOne!!");
		}else {//���ݼ�
			pk_xh = cwglDao.getXhs(searchTjstr,user);
		}
		myForm.setPk_xh(pk_xh);
		return cwglDao.saveTsxx(myForm,user);
	}
	
	/**
	 * @��������ҵ����-���� ��ȡѧ������(�������ѧԺ���Ի�)
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��22�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param searchModel
	 * @param user
	 * @return
	 * @throws Exception
	 * int ��������
	 */
	public int getCounts(CwglForm myForm,SearchModel searchModel, User user) throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		return cwglDao.getCounts(myForm,searchModel,user);
		
	}
	
	/**
	 * @��������ҵ����-����(�������ѧԺ���Ի�)
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��22��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param searchModel
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 * String ��������
	 */
	public String saveTsxx2(CwglForm myForm,SearchModel searchModel,User user,HttpServletRequest request)throws Exception{
		
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =getSeachTj(myForm,searchModel,user);
		String xh = request.getParameter("xh");
		String xhstr = request.getParameter("xhstr");
		String[] pk_xh = null;
		if(xh !=null && !"".equalsIgnoreCase(xh)){//����ѧ��
			pk_xh = new String[]{xh};
		}else if(xhstr !=null && !"".equalsIgnoreCase(xhstr)){//���ѧ��
			pk_xh = xhstr.split(",");
		}else {//���ݼ�
			pk_xh = cwglDao.getXhs(searchTjstr,user);
		}
		myForm.setPk_xh(pk_xh);
		return cwglDao.saveTsxx(myForm,user);
	}
	
	/**
	 * @��������ҵ����-��������ƴ��(�������ѧԺ���Ի�)
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��22��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param searchModel
	 * @param user
	 * @return
	 * @throws Exception
	 * String ��������
	 */
	public String getSeachTj(CwglForm myForm,SearchModel searchModel,User user)throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");	
		sql.append(" and xh in (");
		sql.append(" select xh ");
		sql.append(" from VIEW_XSBFXX  where 1=1 ");
		sql.append("and (sfyby ='��' )  ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);			
		sql.append(") "); 
		String sqlstr=sql.toString();
		// �߼���ѯ����
		String searchTjstr = "";
		if(sqlstr!=null && !"".equalsIgnoreCase(sqlstr)){
			String[] tj = sqlstr.replace("?", "split").split("split");
			for(int i=0;i<inputV.length;i++){
				searchTjstr += tj[i]+"'"+inputV[i]+"'";
			}
			searchTjstr+=tj[inputV.length];
		}
		
		return searchTjstr;
		
	}
	
	
	/**
	 * ��ʼ����λ����
	 * @author zhanghui
	 */
	public String initCwss(CwglForm myForm,HttpServletRequest request)throws Exception{

		User user = getUser(request);
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =request.getParameter("searchTjstr");
		String cwStr = request.getParameter("cwStr");
		String[] primarykey_checkVal = null;
		if(cwStr !=null && !"".equalsIgnoreCase(cwStr)){//���Ŵ�λ
			primarykey_checkVal = cwStr.split("!!splitOne!!");
		}else {//���ݼ�
			primarykey_checkVal = cwglDao.getCwhs(searchTjstr);
		}
		myForm.setPrimarykey_checkVal(primarykey_checkVal);
		myForm.setBz(cwStr);
		return cwglDao.initCwss(myForm,user);
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXsxx(String xh, HttpServletRequest request){
		return cwglDao.getXsxx(xh, request);
	}
	
	/**
	 * ��ѯѧ��
	 * @param model
	 * @return
	 */
	public List<String[]> queryXs(CwglForm model, HttpServletRequest request){
		return cwglDao.queryXs(model,request);
	}

	public List<HashMap<String,String>> getFdyList(String pkValue) {
		// TODO �Զ����ɷ������
		
		return cwglDao.getFdyBzrList(pkValue);
	}
	
	/**
	 * ͨ��ѧ�Ż��ѧ��ס����Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getCwForXh(String pk){
		return cwglDao.getCwForXh(pk);
	}
	
	/**
	 * 
	 * @����:����ѧ�Ż�ȡͬ����������λ��Ϣ
	 * רҵ ѧ�� ���� ��λ��
	 * @���ߣ�zhangxiaobin [���ţ�1036]
	 * @���ڣ�2015-2-11 ����09:54:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getTqCwForXh(String xh){
		return cwglDao.getTqCw(xh);
	}
	
	/**
	 * ��λ��Ϣ�����Զ��嵼��
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCw(CwglModel model,HttpServletRequest request) throws Exception{
		return cwglDao.queryExportCw(model,request);
	}
}
