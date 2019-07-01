package xsgzgl.gygl.xszsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewService;

public class XszsglService extends GyglNewService{
	
	XszsglDao dao = new XszsglDao();

	/**
	 * ��ȡ����סѧ����Ϣ���ݼ�
	 * @author zhanghui
	 */
	public ArrayList<String[]> getTsglZsxxList(XszsglForm myForm, HttpServletRequest request) 
		throws Exception{	
		return dao.getTsglZsxxList(myForm, request);
	}

	public String getBzxbz(String string) {
		// TODO �Զ����ɷ������
		return dao.getBzxbz(string);
	}
	/**
	 * �����߶���ע
	 * @throws Exception 
	 */
	public boolean setBz(XszsglForm myForm, String[] xhstr) throws Exception {
		// TODO �Զ����ɷ������
		boolean falg=false;
		//��ɾ��
		dao.deleteBz(xhstr);
		//�����
		
		return dao.insertBz(myForm,xhstr);
	}
	/**
	 * ��ȡѧ��ס����Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXszsxx(String xh) {
		// TODO �Զ����ɷ������
		
		return dao.getXszsxx(xh);
	}
	
	/**
	 * ѧ��ס����Ϣ �Զ��嵼��
	 * @param myForm
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getExportResultList(XszsglForm myForm,User user,HttpServletRequest request)
	throws Exception {
		// TODO �Զ����ɷ������
		return dao.getExportPageList(myForm, user,request);
	}
	
}
