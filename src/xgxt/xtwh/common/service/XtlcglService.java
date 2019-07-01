package xgxt.xtwh.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.xtwh.common.dao.XtlcglDAO;

public class XtlcglService {
	XtlcglDAO dao = new XtlcglDAO();
	
	/**
	 * ��ȡ�������̼���
	 * @param gnid
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGnlcjb(String gnid){
		
		return new ArrayList<HashMap<String,String>>();
	}
	
	/**
	 * ��ȡ�û�����������е���Ϣ
	 * @param user
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getYhxxForGnlc(User user){
		
		return new HashMap<String, String>();
	}
	
	/**
	 * ��ȡ������˸�λ������Ϣ
	 * */
	public String[] getGnshgwmcxx(String cdid){
		return dao.getGnshgwmcxx(cdid);
	}
	
	/**
	 * ��ȡ������˸�λid��Ϣ
	 * */
	public String[] getGnshgwxx(String cdid){
		return dao.getGnshgwxx(cdid);
	}
	
	/**
	 * ��ȡĳһ���ܵ�ǰ�û����ڵĸ�λ��Ϣ
	 * */
	public List<HashMap<String, String>> getYhgwList(String cdid, String yhm){
		return dao.selectYhgwList(cdid, yhm);
	}
	
	/**
	 * ��ȡ��λ�����������Ϣ
	 * */
	public HashMap<String, String> getGwylcxgxx(String lcid, String gwid){
		return dao.selectGwylcxgxx(lcid, gwid);
	}
	
	
	/**
	 * �ж��Ƿ��������
	 * @param shb ���ҵ���
	 * */
	public boolean sfzhyjsh(String cdid, String xtgwid,String shb){	
		boolean result = false;
		HashMap<String, String> lcxxMap = dao.getGnlcxx(cdid);
		//ͬ������뷽ʽ
		String tjbcyfs = lcxxMap.get("tjbcyfs");
		//���뷽ʽΪ�ɲ����� �����ֲ��� ,���һ�������˸�λ�û�Ϊ�������
		if("�ɲ�����".equalsIgnoreCase(tjbcyfs) 
				|| "���ֲ���".equalsIgnoreCase(tjbcyfs)){
			result = dao.lastLevel(lcxxMap.get("lcid"), xtgwid);
		}else if("ȫ������".equalsIgnoreCase(tjbcyfs)){		
			//���뷽ʽΪ ȫ������ģ����һ�����Ϊ���һ��������һ���û����Ϊ�������
			result = dao.lastStation(lcxxMap.get("lcid"), xtgwid, shb);
		}
		return result;
	}
	
}
