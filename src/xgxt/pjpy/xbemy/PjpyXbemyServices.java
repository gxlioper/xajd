
package xgxt.pjpy.xbemy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.action.base.BaseServicesUtil;
import xgxt.utils.Arrays2;


public class PjpyXbemyServices {
	private PjpyXbemyDAO dao = null;
	private String jsName = "zf01";//����Ա
	/**
	 * @param pjpyModel ����ȷ�����������model
	 * @return
	 */
	public ArrayList<String[]>  getSbSearchResult(String userName, String userType, PjpyXbemyXysbjxjModel pjpyModel){
		ArrayList<String[]> result = new ArrayList<String[]>();
		dao = new PjpyXbemyDAO();
		if (userType.equalsIgnoreCase("xy")){//ѧԺ�û���ѯ���
			result = dao.getXysbjxjSearch(userName,pjpyModel);
		}else{//ѧ���������񴦣����񴦺͹���Ա��ѯ���
			result = dao.getShSearch(userName, userType, pjpyModel);
		}
		return result;
	}
	
	/**
	 * @param pjpyModel ����ȷ�����������model
	 * @return
	 */
	public ArrayList<HashMap<String, String>>  getSbSearchTitle(String userType, String userName){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		dao = new PjpyXbemyDAO();
		char js = "admin".equalsIgnoreCase(userType) && BaseServicesUtil.checkUserToGroup(userName, "ѧ����") && userName.equalsIgnoreCase(jsName)? 'a': 'o';
		switch(js){
		case 'a':{//����Ա��ѯ��ͷ
			result = dao.getAdminShTitle();
			break;
		}
		case 'o':{//�����û���ѯ��ͷ
			result = dao.getXysbjxjSearchTitle();
			break;
		}
		}
		return result;
	}
	
	/**
	 * �ύ��˽��
	 * @param res
	 * @param userName
	 * @param model
	 * @return
	 */
	public boolean submitShResult(String res,String userName,String userType,PjpyXbemyShModel model) throws Exception {
		PjpyXbemyDAO dao                                   = new PjpyXbemyDAO();
		boolean result                                     = false;
		char js = BaseServicesUtil.checkUserToGroup(userName, "����")? 'a'               //��ɫ�ǽ���
					:(BaseServicesUtil.checkUserToGroup(userName, "����")? 'b'           //��ɫ�ǲ���
							: (BaseServicesUtil.checkUserToGroup(userName, "ѧ����") && !userName.equalsIgnoreCase(jsName)? 'c'  //��ɫ��ѧ����
									: ("admin".equalsIgnoreCase(userType) && (BaseServicesUtil.checkUserToGroup(userName, "ѧ����")) && userName.equalsIgnoreCase(jsName)?'d'             //��ɫ�ǹ���Ա
											:'e')));									  //��ɫ��ѧԺ
		String[] keys                                      = model.getCbv();
		String   jxjdm                                     = model.getXmdm();
		String   xn                                        = model.getXn();
		Arrays2.trim(keys);																  //ȥ��keys��ÿ��ֵ�Ŀո�
		if("tg".equalsIgnoreCase(res)){//���ͨ��ʱ
			switch(js){
			case 'a':{//��ɫ�ǽ���
				dao.jwcshResult(xn, jxjdm, keys, res);
				break;
			}				      
			case 'b':{//��ɫ�ǲ���
				dao.cwcshResult(xn, jxjdm, keys, res);
				break;
			}
			case 'c':{//��ɫ��ѧ����
				dao.xscshResult(xn, jxjdm, keys, res);
				break;
			}
			case 'd':{//��ɫ�ǹ���Ա
				break;
			}
			case 'e':{//��ɫ��ѧԺ
				dao.xyshResult(xn, jxjdm, keys, res);
				break;
			}
			}
		} else if ("btg".equalsIgnoreCase(res)){//��˲�ͨ��ʱ
			switch(js){
			case 'a':{//��ɫ�ǽ���
				dao.jwcshResult(xn, jxjdm, keys, res);
				break;
			}				      
			case 'b':{//��ɫ�ǲ���
				dao.cwcshResult(xn, jxjdm, keys, res);
				break;
			}
			case 'c':{//��ɫ��ѧ����
				dao.xscshResult(xn, jxjdm, keys, res);
				break;
			}
			case 'd':{//��ɫ�ǹ���Ա
				break;
			}
			case 'e':{//��ɫ��ѧԺ
			    dao.xyshResult(xn, jxjdm, keys, res);	
				break;
			}
			}
		}
		return result;
	}
	
	/**
	 * ѧԺ��ѧ����˽����ѯ��ͷ
	 * @return
	 */
	public ArrayList<HashMap<String, String>>  getJxjShJgSearchTitle(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		dao = new PjpyXbemyDAO();
		result = dao.getAdminShTitle();//��ѯ��ͷ
		return result;
	}
	
	/**
	 * ѧԺ��ѧ����˽����ѯ
	 * @param pjpyModel
	 * @param jg
	 * @return
	 */
	public ArrayList<String[]>  getJxjShJgSearchResult(PjpyXbemyXysbjxjModel pjpyModel, String jg){
		ArrayList<String[]> result = new ArrayList<String[]>();
		dao = new PjpyXbemyDAO();
		result = dao.getJxjShJgSearch(pjpyModel, jg);//��ѯ���
		return result;
	}
	
	public ArrayList<String[]>  getJxjShJgExp(PjpyXbemyXysbjxjModel pjpyModel, String jg){
		ArrayList<String[]> result = new ArrayList<String[]>();
		dao = new PjpyXbemyDAO();
		result = dao.getJxjShJgExp(pjpyModel, jg);//��ѯ���
		return result;
	}
}
