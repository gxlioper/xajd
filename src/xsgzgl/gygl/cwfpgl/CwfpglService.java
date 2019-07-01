package xsgzgl.gygl.cwfpgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mortbay.http.HttpRequest;

import xgxt.action.Base;
import xsgzgl.gygl.comm.GyglNewService;

public class CwfpglService extends GyglNewService{
	
	CwfpglDao dao = new CwfpglDao();
	/**
	 * 
	 * ȡ���꼶ѧԺͳ��
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> �������� 
	 * @throws
	 */
	public ArrayList<String[]> getCwfpglInfoList(CwfpglForm myForm,HttpServletRequest request) 
		throws Exception{
		
		return dao.getCwfpglInfoList(myForm,request);
	}

	/**
	 * ͳ��¥���ķ�����Ϣ
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx(HttpServletRequest request,CwfpglForm myForm){
		dao.tjldFpxx(request, myForm);
	}
	
	/**
	 * ��ȡ¥���б�
	 * @return
	 */
	public List<HashMap<String,String>> getLdList(String ldxb){
		return dao.getLdList(ldxb);
	}
	
	/**
	 * ��ȡ¥���б�(�û�����ǹ�Ԣ����Ա����Ԣ����Ա���ݷ�Χ����)
	 * @return
	 */
	public List<HashMap<String,String>> getLdListByGyfdy(HttpServletRequest request,String ldxb){
		String cwzt = request.getParameter("cwzt");
		String xydm = request.getParameter("xydm");
		return dao.getLdListByGyfdy(request,ldxb,cwzt,xydm);
	}
	
	/**
	 * ��ȡ��ѡѧԺ�ѷ���¥���б�(�û�����ǹ�Ԣ����Ա����Ԣ����Ա���ݷ�Χ����)
	 * @return
	 */
	public List<HashMap<String,String>> getCwfpLdListByGyfdy(HttpServletRequest request,String ldxb){
		String primarykey_checkVal= request.getParameter("primarykey_checkVal");
		String xy = request.getParameter("xydm");
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp = primarykey_checkVal.split("_");
			xy=temp[0];
		}
		return dao.getCwfpLdListByGyfdy(request,ldxb,xy);
	}
	
	/**
	 * �������Ҵ�λ������Ϣ
	 * @param request
	 * @return
	 */
	public boolean saveQscwfpxx(HttpServletRequest request,CwfpglForm myForm){
		return dao.saveQscwfpxx(request,myForm);
	}
	
	/**
	 * ȡ�����Ҵ�λ������Ϣ
	 * @param request
	 * @return
	 */
	public boolean qxQscwfpxx(HttpServletRequest request,CwfpglForm myForm){
		return dao.qxQscwfpxx(request,myForm);
	}
	
	/**
	 * ��ȡָ��ѧԺ�꼶��ͳ������
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCwfpglInfo(CwfpglForm myForm){		
		return dao.getCwfpglInfo(myForm);
	}
	
	/**
	 * ��ȡѧԺ�꼶�Ա��¥���������
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx(CwfpglForm myForm){
		return dao.getXynjxbLdfpxx(myForm);
	}

	public List<HashMap<String,String>> loadLdlist(CwfpglForm myForm){
		return dao.loadLdlist(myForm);
	}
	/**
	 * 
	 * @����:��������¥��
	 * @���ߣ�xiaxia
	 * @���ڣ�2014-9-12 ����03:23:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> cwfpglInitLdList(HttpServletRequest request ){
		String xydm = request.getParameter("xydm");
		String cwzt = request.getParameter("cwzt");
		String zydm = request.getParameter("zydm");
		String xbdm = request.getParameter("xbdm");
		return dao.cwfpglInitLdList(request,xydm, zydm, cwzt,xbdm);
		
	}
	/**
	 * ��λ���������Ϣ�б�
	 * ȡ���꼶ѧԺרҵͳ��
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCwfpglInfoList_zy(CwfpglForm myForm) 
	throws Exception{
	
		return dao.getCwfpglInfoList_zy(myForm);
	}
	
	/**
	 * ��λ���������Ϣ�б�ѧԺ��
	 * ȡ���꼶ѧԺרҵ�༶ͳ��
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCwfpglInfoList_xy(CwfpglForm myForm) 
	throws Exception{
	
		return dao.getCwfpglInfoList_xy(myForm);
	}
	
	/**
	 * ͳ��¥���ķ�����Ϣ(��רҵͳ��)
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx_zy(HttpServletRequest request,CwfpglForm myForm){
		dao.tjldFpxx_zy(request, myForm);
	}
	
	/**
	 * ͳ��¥���ķ�����Ϣ(ѧԺ)
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx_xy(HttpServletRequest request,CwfpglForm myForm){
		dao.tjldFpxx_xy(request, myForm);
	}
	
	/**
	 * ��ȡ¥���б�(ѧԺ)
	 * @return
	 */
	public List<HashMap<String,String>> getLdList_xy(CwfpglForm myForm){
		return dao.getLdList_xy(myForm);
	}
	/**
	 * �������Ҵ�λ������Ϣ(�����I����)
	 * @param request
	 * @return
	 */
	public boolean saveQscwfpxx_zy(HttpServletRequest request,CwfpglForm myForm){
		return dao.saveQscwfpxx_zy(request,myForm);
	}
	
	/**
	 * �������Ҵ�λ������Ϣ(ѧԺ)
	 * @param request
	 * @return
	 */
	public boolean saveQscwfpxx_xy(HttpServletRequest request,CwfpglForm myForm){
		return dao.saveQscwfpxx_xy(request,myForm);
	}
	
	/**
	 * ȡ�����Ҵ�λ������Ϣ(�����Iȡ������)
	 * @param request
	 * @return
	 */
	public boolean qxQscwfpxx_zy(HttpServletRequest request,CwfpglForm myForm){
		return dao.qxQscwfpxx_zy(request,myForm);
	}
	
	/**
	 * ȡ�����Ҵ�λ������Ϣ(ѧԺ)
	 * @param request
	 * @return
	 */
	public boolean qxQscwfpxx_xy(HttpServletRequest request,CwfpglForm myForm){
		return dao.qxQscwfpxx_xy(request,myForm);
	}
	/**
	 * ��ȡָ��ѧԺרҵ�꼶��ͳ������
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCwfpglInfo_zy(CwfpglForm myForm){		
		return dao.getCwfpglInfo_zy(myForm);
	}
	
	/**
	 * ��ȡָ��ѧԺ�꼶��ͳ��������ѧԺ��
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getCwfpglInfo_xy(CwfpglForm myForm){		
		return dao.getCwfpglInfo_xy(myForm);
	}
	/**
	 * ��ȡѧԺרҵ�꼶�Ա��¥���������
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx_zy(CwfpglForm myForm){
		return dao.getXynjxbLdfpxx_zy(myForm);
	}
	/**
	 * ��ȡѧԺ�꼶�Ա��¥���������
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String,String>> getXynjxbLdfpxx_xy(CwfpglForm myForm){
		return dao.getXynjxbLdfpxx_xy(myForm);
	}
	
	/**
	 * ����¥���б�ѧԺ��
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>> loadLdlist_xy(CwfpglForm myForm){
		return dao.loadLdlist_xy(myForm);
	}
}
