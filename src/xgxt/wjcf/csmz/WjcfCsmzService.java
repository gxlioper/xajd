
package xgxt.wjcf.csmz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;


import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ɳ����ѧԺΥ�ʹ���service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-05</p>
 */
public class WjcfCsmzService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	WjcfCsmzDAO dao = null;//���ݲ���DAO

	/**
	 * �����û���������ѯ��ͬ�ı�ͷ
	 * @param userType
	 * @param userName
	 * @param isFdy
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle(String userType,
			String isFdy) throws Exception {
		dao = new WjcfCsmzDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ�û�
			topList = dao.getSearchTitle(3);
		} else if ((StringUtils.isEqual(userType, "xy"))
				&& (StringUtils.isEqual(isFdy, "false"))) {//ѧԺ�û�
			topList = dao.getSearchTitle(2);
		}//end if
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			topList = dao.getSearchTitle(1);
		}//end if
		return topList;
	}
	
	/**
	 * �����û���������ѯ��ͬ�Ľ��
	 * @param userType
	 * @param isFdy
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult (String userName, String userType, String isFdy, WjcfCsmzCxcfModel wjcfcsmzcxcfModel) throws Exception {
		dao = new WjcfCsmzDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			
			if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ�û�
				resList = dao.getSearchResult(userName, 3, wjcfcsmzcxcfModel);
			} else if ((StringUtils.isEqual(userType, "xy"))
					&& (StringUtils.isEqual(isFdy, "false"))) {//ѧԺ�û�
				resList = dao.getSearchResult(userName, 2, wjcfcsmzcxcfModel);
			}
			if (StringUtils.isEqual(isFdy, "true")) {//����Ա
				resList = dao.getSearchResult(userName, 1, wjcfcsmzcxcfModel);
			}//end if
		}else{
			if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ�û�
				resList = dao.getSearchResult(userName, 3, wjcfcsmzcxcfModel);
			} else if ((StringUtils.isEqual(userType, "xy"))
					&& (StringUtils.isEqual(isFdy, "false"))) {//ѧԺ�û�
				resList = dao.getSearchResult(userName, 2, wjcfcsmzcxcfModel);
			}//end if
			if (StringUtils.isEqual(isFdy, "true")) {//����Ա
				resList = dao.getSearchResult(userName, 1, wjcfcsmzcxcfModel);
			}//end if
		}
		return resList;
	}
	
	/**
	 * ���칤��ְҵ
	 * @param userType
	 * @param isFdy 
	 * @param isBzr
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult (User user, WjcfCsmzCxcfModel wjcfcsmzcxcfModel) throws Exception {
		dao = new WjcfCsmzDAO();
		List<String[]> resList = new ArrayList<String[]>();
		
		String userType=user.getUserType();
		
		String userName=user.getUserName();
		
		String isFdy=user.getFdyQx();
		
		String isBzr=user.getBzrQx();
		
		wjcfcsmzcxcfModel.setUser(user);
		
		//ѧУ�û�
		if (StringUtils.isEqual(isFdy, "true")|| StringUtils.isEqual(isBzr, "true")) {//����Ա
			resList = dao.getSearchResult(userName, 1, wjcfcsmzcxcfModel);
		} else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			resList = dao.getSearchResult(userName, 3, wjcfcsmzcxcfModel);
		} else if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�
			resList = dao.getSearchResult(userName, 2, wjcfcsmzcxcfModel);
		}
	
		return resList;
	}
	
	/**
	 * ��ȡ����б�
	 * @param userType
	 * @param isFdy
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getList() throws Exception {
		dao = new WjcfCsmzDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		list = dao.getList(4);
		return list;
	}
	
	/**
	 * ��ѯ����������Ϣ
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCxcfQryRestlt(String userType, String isFdy, String pkVal) throws Exception {
		dao = new WjcfCsmzDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ�û�
	
			resMap = dao.getCxcfQryResult(3, pkVal);
		} else if ((StringUtils.isEqual(userType, "xy"))
				&& (StringUtils.isEqual(isFdy, "false"))) {//ѧԺ�û�
			resMap = dao.getCxcfQryResult(2, pkVal);
		}//end if
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			resMap = dao.getCxcfQryResult(1, pkVal);
		}//end if
		
		return resMap;
	}
	
	/**
	 * ��ѯ����������Ϣ
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCxcfQryRestlt(User user, String pkVal) throws Exception {
		dao = new WjcfCsmzDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		
		String userType=user.getUserType();
		String isFdy=user.getFdyQx();
		String isBzr=user.getBzrQx();
		
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			if (StringUtils.isEqual(isFdy, "true") || StringUtils.isEqual(isBzr, "true")) {//����Ա
				
				resMap = dao.getCxcfQryResult(1, pkVal);
			
			}else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ�û�
				
				resMap = dao.getCxcfQryResult(3, pkVal);
			
			}else  if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�
				resMap = dao.getCxcfQryResult(2, pkVal);
			}//end if
			//end if
		}
		return resMap;
	}
	
	/**
	 * �������������Ϣ����
	 * @param userType
	 * @param isFdy
	 * @param pkVal
	 * @param wjcfsmzcxcfsaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfInfo(String userType, String isFdy, String pkVal,
			WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel,
			HttpServletRequest request) throws Exception {
		dao = new WjcfCsmzDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ�û�
			bFlag = dao.saveCxcfInfo(3, wjcfsmzcxcfsaveModel, request, pkVal);
		} else if ((StringUtils.isEqual(userType, "xy"))
				&& (StringUtils.isEqual(isFdy, "false"))) {//ѧԺ�û�
			bFlag = dao.saveCxcfInfo(2, wjcfsmzcxcfsaveModel, request, pkVal);
		}//end if
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			bFlag = dao.saveCxcfInfo(1, wjcfsmzcxcfsaveModel, request, pkVal);
		}//end if
		return bFlag;
	}
	
	/**
	 * �������������Ϣ����
	 * @param userType
	 * @param isFdy
	 * @param pkVal
	 * @param wjcfsmzcxcfsaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfInfo(User user, String pkVal,
			WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel,
			HttpServletRequest request) throws Exception {
		dao = new WjcfCsmzDAO();
		boolean bFlag = false;
		String userType=user.getUserType();
		String isFdy=user.getFdyQx();
		String isBzr=user.getBzrQx();
		
		if(StringUtils.isEqual(isFdy, "true") || StringUtils.isEqual(isBzr, "true")) {//����Ա
			
			bFlag = dao.saveCxcfInfo(1, wjcfsmzcxcfsaveModel, request, pkVal);
		
		}else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ�û�
			
			bFlag = dao.saveCxcfInfo(3, wjcfsmzcxcfsaveModel, request, pkVal);
		
		}else if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�
			
			bFlag = dao.saveCxcfInfo(2, wjcfsmzcxcfsaveModel, request, pkVal);
		
		}//end if
		return bFlag;
	}
	
	
	/**
	 * �������ѧ������������Ϣ
	 * @param userType
	 * @param isFdy
	 * @param pkVal
	 * @param wjcfsmzcxcfsaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfInfoPl(String userType, String isFdy, WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel) throws Exception {
		dao = new WjcfCsmzDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ�û�
			bFlag = dao.saveCxcfInfoPl(3, wjcfsmzcxcfsaveModel);
		} else if ((StringUtils.isEqual(userType, "xy"))
				&& (StringUtils.isEqual(isFdy, "false"))) {//ѧԺ�û�
			bFlag = dao.saveCxcfInfoPl(2, wjcfsmzcxcfsaveModel);
		}//end if
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			bFlag = dao.saveCxcfInfoPl(1, wjcfsmzcxcfsaveModel);
		}//end if
		return bFlag;
	}
	
	/**
	 * �������ѧ������������Ϣ
	 * @param userType
	 * @param isFdy
	 * @param pkVal
	 * @param wjcfsmzcxcfsaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfInfoByCqgc(String userType, String isFdy,String isBzr, WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel) throws Exception {
		dao = new WjcfCsmzDAO();
		boolean bFlag = false;
	
		if (StringUtils.isEqual(isFdy, "true") || StringUtils.isEqual(isBzr, "true")) {//����Ա
			
			bFlag = dao.saveCxcfInfoPl(1, wjcfsmzcxcfsaveModel);
		
		}else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ�û�
			
			bFlag = dao.saveCxcfInfoPl(3, wjcfsmzcxcfsaveModel);
		
		}else if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�
			bFlag = dao.saveCxcfInfoPl(2, wjcfsmzcxcfsaveModel);
		}
		
		return bFlag;
	}
	
	/**
	 * ͨ��ѧ�Ż�ȡѧ���������Ϣ���������༶��רҵ��ѧԺ���꼶���Ա�
	 * ͨ��Υ���ĺŻ�ȡѧ��Υ����Ϣ��ʱ�䣬������𣬴���ԭ��ѧ�꣬ѧ�ڣ�
	 * @param xh,pkval
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh, String pkVal) throws Exception {
		dao = new WjcfCsmzDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = dao.getStuInfo(xh, pkVal);
		return resMap;
 	}
	
	/**
	 * ���泷��������Ϣ
	 * @param cxcfSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfSqlInfo(CxcfSqSaveModel cxcfSaveModel, HttpServletRequest request) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.saveCxcfSqlInfo(cxcfSaveModel, request);
	}
	
	/**
	 * ����ǰ���ѧ������ʱ���Ƿ���һ��
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean chkStuTj(String cfsj) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.chkStuTj(cfsj);
	}
	
	/**
	 * �������ֲ�ѯ��ͷ
	 * cxcfqrytit -----�������ֲ�ѯ��ͷ
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfQryTit(WjcfCsmzCxcfModel cxcfModel, String xxdm) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.getCxcfQryTit(cxcfModel, xxdm);
	}
	
	/**
	 * �������ֲ�ѯ��ͷ
	 * cxcfqryres -----�������ֲ�ѯ�����
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfQryRes(WjcfCsmzCxcfModel cxcfModel, String xxdm) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.getCxcfQryRes(cxcfModel, xxdm);
	}
	
	/**
	 * ����ɾ������������Ϣ
	 * cxcfinfo----����������Ϣ
	 * @param cvb
	 * @return
	 * @throws Exception
	 */
	public String delCxcfInfo(String[] cvb) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.delCxcfInfo(cvb);
	}
	
	/**
	 * �������ֲ�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> stuCxcfTitle(String xxdm) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.stuCxcfTitle(xxdm);
	}
	
	/**
	 * �������ֲ�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stuCxcfResult(String xh, String xxdm) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.stuCxcfResult(xh, xxdm);
	}
	
	/**
	 * ͨ��������ȡ����������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> stuInfoByPk(String pkValue) throws Exception{
		dao = new WjcfCsmzDAO();
		return dao.stuInfoByPk(pkValue);
	}
	
	/**
	 * ��ӳ���������Ϣ   ���칤��ְҵ����ѧԺ
	 * @return
	 */
	public boolean updateCxcfInfo(WjcfCsmzCxcfSaveModel model) throws Exception{
		String sfcx="��";
		if(model!=null && "ͨ��".equals(model.getSh())){
			return dao.updateCxcfInfo(model, sfcx);
		}else{
			sfcx="��";
			model.setJcsj("");
			return dao.updateCxcfInfo(model, sfcx);
		}
	}
	
	public boolean update(String pkValue, String bz, HttpServletRequest request) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.update(pkValue, bz, request);
	}
	
	public HashMap<String, String> getcfInfo(String pkValue) throws Exception {
		dao = new WjcfCsmzDAO();
		return dao.getcfInfo(pkValue);
	}
}

