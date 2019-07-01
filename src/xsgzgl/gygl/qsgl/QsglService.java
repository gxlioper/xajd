package xsgzgl.gygl.qsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.NullStringException;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.gygl.cwgl.CwglForm;
import xsgzgl.gygl.cwgl.CwglModel;

public class QsglService extends GyglNewService{
	
	private QsglDAO qsglDao = new QsglDAO();
	
	/**
	 * ������������
	 * @param model
	 * @return
	 */
	public String saveQswh(QsglModel model){
		boolean flag1 = false;
		boolean flag2 = false;
		String message = "";
		
		flag1 =  qsglDao.saveQswh(model);
		
		if(flag1){
			List<String[]> params = new ArrayList<String[]>();
			String lddm = model.getLddm();
			String qsh = model.getQsh();
			String cws = model.getCws();
			if ("10698".equals(Base.xxdm)) {
				String[] ch2 = {"A","B","C","D","E","F","G","H","I","J","H","I","J","K","L","M"};
				for (int i=0; i<=Integer.parseInt(cws); i++){
					params.add(new String[]{lddm, qsh, ch2[i]});
				}
			}else {
				for (int i=1; i<=Integer.parseInt(cws); i++){
					params.add(new String[]{lddm, qsh, i+""});
				}
			}
			
			
			flag2 = qsglDao.saveCws(params);
		}
		
		if(flag1 && flag2){
			message = "����ɹ���";
		}else if(flag1 && !flag2){
			message = "��λ�Զ�����ʧ�ܣ�";
		}else{
			message = "����ʧ�ܣ�";
		}
		
		return message;
	}
	/**
	 * �޸�����
	 * @param model
	 * @return
	 * @throws  
	 */
	public String updateQswh(String pkValue, QsglModel model) throws Exception{
		//���ݴ�ѧ���Ի� �����޸����Һ� ��������ʱ���г�ͻ [1036]
		if(StringUtils.equals(Globals.XXDM_WZDX, Base.xxdm)){ 
			String yqsh = model.getYqsh(); //ԭ���Һ�
			String qsh = model.getQsh(); //�޸ĺ����Һ�
			//ֻ�����޸����Һŵ�����������ݳ�ͻ�ж�
			if(!StringUtils.isEqual(yqsh, qsh)){
				Map<String , String> queryQs = qsglDao.getQsForPk(model.getLddm() + model.getQsh());
				if(queryQs != null && queryQs.size() >= 1){
					return "��¥�������Һ��Ѵ��ڣ�";
				}
			}
		}
		
		//���ݴ�ѧ���Ի� �����޸����Һ� ��������ʱ���г�ͻ [1036]
		return qsglDao.updateQswh(pkValue, model) ? "����ɹ���" : "����ʧ�ܣ�";
	}
	
	/**
	 * ��ѯ����
	 * @param model
	 * @return
	 */
	public List<String[]> queryQs(QsglModel model) throws Exception{
		return qsglDao.queryQs(model);
	}
	
	/**
	 * ����ɾ��������Ϣ
	 * @param pk
	 * @return
	 */
	public boolean delQs(String[] pk){
		boolean flag = false;
		List<String[]> params = new ArrayList<String[]>();
		
		for(String str : pk){
			params.add(new String[]{str});
		}
		
		flag = qsglDao.delQs(params);
		
		// ɾ��������λ
		if(flag){
			flag = qsglDao.delCws(params);
		}
		
		return flag;
	}
	
	/**
	 * ��ȡָ��������Ϣ
	 * @param pk
	 * @return
	 */
	public Map<String, String> getQsForPk(String pk){
		return qsglDao.getQsForPk(pk);
	}
	
	/**
	 * ��ȡĳ¥�������е�����
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String, String>> getQshForLd(String lddm, String ch){
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("lddm", lddm);
		
		if(StringUtils.isNotNull(ch) && !"null".equalsIgnoreCase(ch)){
			queryMap.put("ch", ch);
		}
		queryMap.put("orderBy", " order by qsh ");
		return qsglDao.getRsList("xg_gygl_new_qsxxb", queryMap, new String[]{"qsh"});
	}
	
	/**
	 * ���ָ��¥��ָ���������������Һź����Ĵ�λ��
	 * @param lddm
	 * @param ch
	 * @return
	 */
	public Map<String, String> getMaxQsInfo(String lddm, String ch){
		return qsglDao.getMaxQsInfo(lddm, ch);
	}
	
	/**
	 * ��ȡ���ҵĴ�λ��Ϣ
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public List<String[]> getCwForQs(String pkValue){
		String[] inputValue = new String[]{pkValue};
		String[] outputValue = new String[]{"cwh",  "xh", "xm", "xsnj","xsxymc","xszymc","xsbjmc", "nj", "xymc", "sfbl","qsz"};
		
		return qsglDao.getCwForQs(inputValue, outputValue);
	}
	
	/**
	 * ��������
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		return qsglDao.importData(request,response);
	}
	
	/**
	 * ��ȡ���ҷ�����ס��Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getQsfprzInfo(String pkValue ){
		return qsglDao.getQsfprzInfo(pkValue);
	}
	
	/**
	 * ��ʼ����λ����
	 * @author zhanghui
	 */
	public String initQsss(QsglForm myForm,HttpServletRequest request)throws Exception{

		User user = getUser(request);
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =request.getParameter("searchTjstr");
		String cwStr = request.getParameter("qsStr");
		String[] primarykey_checkVal = null;
		if(cwStr !=null && !"".equalsIgnoreCase(cwStr)){//�������
			primarykey_checkVal = cwStr.split("!!splitOne!!");
		}else {//���ݼ�
			primarykey_checkVal = qsglDao.getQshs(searchTjstr);
		};
		myForm.setPrimarykey_checkVal(primarykey_checkVal);
		return qsglDao.initQsss(myForm,user);
	}
	
	/**
	 * ��ȡ�ɳ�ʼ��������
	 * @param model
	 * @return
	 */
	public String getKcshqss(QsglModel model) throws Exception{
		return qsglDao.getKcshqss(model);
	}
	
	/**
	 * ��ȡ��ѯ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String getSearchTjstr(QsglModel model) throws Exception{
		String[] sfhkcw=model.getSearchModel().getSearch_tj_sf();//��ʱ�Ƴ����Ƿ񺬿մ�λ
		model.getSearchModel().setSearch_tj_sf(null);
		//�߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		model.getSearchModel().setSearch_tj_sf(sfhkcw);
		
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
	 * ������Ϣ���� �Զ��� ���� 
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> qsxxglExportdata(QsglModel model) throws Exception{
		return qsglDao.qsxxglExportdata(model);
	}

	/** 
	 * @����:(��ȡ¥���Ա�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-20 ����03:33:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param ch
	 * @return
	 * Map<String,String> �������� 
	 * @throws 
	 */
	public Map<String, String> getLcXb(String lddm, String ch) {
		// TODO �Զ����ɷ������
		return qsglDao.getLcXb(lddm, ch);
	}
	
	/**
	 * 
	 * @����:�������ҹ������ά��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-27 ����10:11:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveplwhTypeOfQs(QsglForm para)
	throws Exception {
		boolean rs = qsglDao.saveplwhTypeOfQs(para, para.getPrimarykey_checkVal());
		if(rs){
			rs = qsglDao.delQsxx(para.getPrimarykey_checkVal());
		}
        int cws = Integer.parseInt(para.getCws());
        List<HashMap<String, String>> qsxxList = qsglDao.getQsxxList(para.getPrimarykey_checkVal());
        List<String[]> params = new ArrayList<String[]>();
        if(rs){
			if ("10698".equals(Base.xxdm)) {
				String[] ch2 = {"A","B","C","D","E","F","G","H","I","J","H","I","J","K","L","M"};
				for (int i = 0; i < qsxxList.size(); i++) {
					for (int j = 0; j < cws; j++) {
						String[] tempArr = new String[]{qsxxList.get(i).get("lddm"),qsxxList.get(i).get("qsh"),ch2[j]+""};
						params.add(tempArr);
					}
				}

			}else{
				for (int i = 0; i < qsxxList.size(); i++) {
					for (int j = 0; j < cws; j++) {
						String[] tempArr = new String[]{qsxxList.get(i).get("lddm"),qsxxList.get(i).get("qsh"),(j+1)+""};
						params.add(tempArr);
					}
				}
			}
        	 rs = qsglDao.saveCwxx(params);
        }
       
        return rs;
	}
}
