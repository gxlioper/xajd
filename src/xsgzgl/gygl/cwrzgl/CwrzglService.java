package xsgzgl.gygl.cwrzgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xsgzgl.gygl.comm.GyglNewService;

public class CwrzglService extends GyglNewService{
	
	CwrzglDao dao = new CwrzglDao();
	public ArrayList<String[]> getCwrzglInfoList(CwrzglForm myForm,HttpServletRequest request) 
		throws Exception{		
		return dao.getCwrzglInfoList(myForm,request);
	}	
	
	/**
	 * ͳ��¥���ķ�����Ϣ
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx(HttpServletRequest request,CwrzglForm myForm){
		dao.tjldFpxx(request, myForm);
	}
	
	/**
	 * ��ȡ¥���б�
	 * @return
	 */
	public List<HashMap<String,String>> getLdList(CwrzglForm myForm){
		return dao.getLdList(myForm);
	}
	
	/**
	 * ����ѧ����ס������Ϣ
	 * @param request
	 * @return
	 */
	public boolean saveXsrzfpxx(HttpServletRequest request,CwrzglForm myForm){
		return dao.saveXsrzfpxx(request,myForm);
	}
	
	/**
	 * ȡ��ѧ����ס��Ϣ
	 * @param request
	 * @return
	 */
	public boolean qxXsrzfpxx(HttpServletRequest request,CwrzglForm myForm){
		return dao.qxXsrzfpxx(request,myForm);
	}
	
	/**
	 * ��ȡָ��ѧԺ�꼶��ͳ������
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCwfpglInfo(CwrzglForm myForm){		
		return dao.getCwfpglInfo(myForm);
	}
	
	/**
	 * ��ȡѧԺ�꼶�Ա��¥���������
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx(CwrzglForm myForm){
		return dao.getXynjxbLdfpxx(myForm);
	}
	
	/**
	 * ��������
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		return dao.importData(request,response);
	}
	
	/**
	 * �Ƿ��ȡ����ס
	 * @param myForm
	 * @return
	 */
	public boolean isKqxrz(CwrzglForm myForm){
		return dao.isKqxrz(myForm);
	}
	
	//#############################################����ΪѧԺ����
	
	/**
	 * ��λ���������Ϣ�б�ѧԺ��
	 */
	public ArrayList<String[]> getCwfpglInfoList_xy(HttpServletRequest request,CwrzglForm myForm) 
	throws Exception{		
		return dao.getCwrzglInfoList_xy(request,myForm);
	}	
	
	/**
	 * ͳ��¥���ķ�����Ϣ��ѧԺ��
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx_xy(HttpServletRequest request,CwrzglForm myForm){
		dao.tjldFpxx_xy(request, myForm);
	}
	
	/**
	 * ��ȡ¥���б�ѧԺ��
	 * @return
	 */
	public List<HashMap<String,String>> getLdList_xy(CwrzglForm myForm){
		return dao.getLdList_xy(myForm);
	}
	
	/**
	 *  �����Ա𡢰༶���룬��ȡ���䵽רҵ��¥���б���רҵ���䷽ʽ��
	 * @return
	 */
	public List<HashMap<String,String>> getLdList_zy(CwrzglForm myForm){
		return dao.getLdList_zy(myForm);
	}
	
	/**
	 * ��ȡָ��ѧԺ�꼶��ͳ��������ѧԺ��
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCwfpglInfo_xy(CwrzglForm myForm){		
		return dao.getCwfpglInfo_xy(myForm);
	}
	
	/**
	 * ��ȡѧԺ�꼶�Ա��¥�����������ѧԺ��
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx_xy(CwrzglForm myForm){
		return dao.getXynjxbLdfpxx_xy(myForm);
	}
	
	/**
	 * ��ȡѧԺ�꼶�Ա��¥�����������רҵ���䷽ʽ��
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx_zy(CwrzglForm myForm){
		return dao.getXynjxbLdfpxx_zy(myForm);
	}

	/** 
	 * @����:(��λ��ס����-��ȡ��ǰרҵ�Ĵ�λ��Ϣ)��רҵ������䴲λ��ʽ
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-9-5 ����03:53:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param myForm
	 * void �������� 
	 * @throws 
	 */
	public void tjldFpxx_zy(HttpServletRequest request, CwrzglForm myForm) {
		dao.tjldFpxx_zy(request,myForm);
	}
	
	/** 
	 * @����:��ȡ�������(�½�ҽ�ƴ�ѧ��ѧԺ���Ի�ר��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-24 ����11:00:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYzlbList() {
		return dao.getYzlbList();
	}
}
