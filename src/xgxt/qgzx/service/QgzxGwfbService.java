package xgxt.qgzx.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.dao.XsgwglDAO;
import xgxt.qgzx.form.QgzxForm;
import xgxt.qgzx.hngydx.service.HngydxXsqgzxService.saveFreeTime;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧ��λ��������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2010-12-07</p>
 */
public class QgzxGwfbService {
	QgzxDao dao = new QgzxDao();
	
	/**
	 * ��ʼ�������б�����
	 * @param type
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getSelectData(String type){
		if("xiaoqu".equalsIgnoreCase(type)){//У��
			return dao.getWhList("dm_zju_xq", "dm", "xqmc", "", "", "");
		}else if("gwxz".equalsIgnoreCase(type)){//��λ����
			return dao.getWhList("gwxzdmb", "gwxzdm", "gwxzmc", "", "", "");
		}else if("jcfs".equalsIgnoreCase(type)){//�Ƴ귽ʽ
			return dao.getWhList("qgzx_jcfsdmb", "jcfsdm", "jcfsmc", "", "", "");
		}
		return new ArrayList<HashMap<String,String>>();
	}
	
	/**
	 * ���˵�λ����
	 * @param user
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYrdwList(User user){
		if(dao.isYrdwUser(user.getUserName())){//���˵�λ�û�
			return dao.getWhList("yrdwdmb", "yrdwdm", "yrdwmc", "", "dlm", user.getUserName());
		}else{
			return dao.getWhList("yrdwdmb", "yrdwdm", "yrdwmc", "", "", "");
		}
	}
	
	/**
	 * ���˵�λ����
	 * @param user
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYrdwListNotOption(User user){
		if(dao.isYrdwUser(user.getUserName())){//���˵�λ�û�
			return dao.getWhList("yrdwdmb", "yrdwdm", "yrdwmc", "", "dlm", user.getUserName(), false);
		}else{
			return dao.getWhList("yrdwdmb", "yrdwdm", "yrdwmc", "", "", "", false);
		}
	}
	
	/**
	 * ���ݸ�λ���ƻ�ȡ������ϱ�ʱ��
	 * @param gwdm
	 * @return String
	 * */
	public String getZjgwsbsj(String gwdm){
		return dao.queryZjgwsbsj(gwdm);
	}
}
