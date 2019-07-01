package xsgzgl.gygl.gybxgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewService;

public class GybxglService extends GyglNewService{
	
	GybxglDao dao = new GybxglDao();
	/**
	 * ��ȡ������Ϣ���ݼ�
	 * @author zhanghui
	 */
	public ArrayList<String[]> getGybxglInfoList(GybxglForm myForm, String[] colList, HttpServletRequest request) 
		throws Exception{		
		return dao.getGybxglInfoList(myForm,colList,request);
	}
	
	public List<String[]> getGybxglSelfList(GybxglForm myForm, String[] colList, User user, String searchTjByGyfdy) 
	throws Exception{		
		return dao.getGybxglSelfList(myForm, colList, user, searchTjByGyfdy);
	}

	/**
	 * �鿴ѧ����Ϣ
	 * @author zhanghui
	 */
	public HashMap<String,String> viewXsxx(String xh){
		return dao.viewXsxx(xh);
	}
	
	public HashMap<String,String> viewXsxx(String pk, String xh){
		return dao.viewXsxx(pk,xh);
	}
	public List<HashMap<String, String>> getBxlbList(){
		return dao.getBxlbList();
	}
	
	public boolean gybxglAdd(GybxglForm myForm) throws Exception{
		return dao.gybxglAdd(myForm);		
	}
	
	/**
	 * ��Ԣ�����޸�
	 * @author sjf
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean gybxglModi(GybxglForm myForm) throws Exception{
		return dao.gybxglModi(myForm);		
	}
	
	/**
	 * ��Ԣ���޴���
	 * @author sjf
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean gybxglUpdate(GybxglForm myForm) throws Exception{
		return dao.gybxglUpdate(myForm);		
	}
	
	public boolean delGybx(GybxglForm model) throws Exception{
		List<String[]> params = new ArrayList<String[]>();
		String[] pks = model.getPrimarykey_checkVal();
		
		for(String pk : pks){
			params.add(new String[]{pk});
		}
		
		return dao.delGybx(params);
	}
	
	public boolean pjUpdate(GybxglForm model) throws SQLException{
		String[] pks = model.getPrimarykey_checkVal();
		String mycd = model.getMycd();
		String pj = model.getPj();
		
		List<String[]> list = new ArrayList<String[]>();
		
		for(String pk : pks){
			list.add(new String[]{mycd, pj, pk});
		}
		return dao.pjUpdate(list);
	}
	
	
	/**
	 * ��Ԣ���޹��� �Զ��嵼��
	 * @param myForm
	 * @param colList
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getGybxglInfoExportList(GybxglForm myForm, String[] colList, HttpServletRequest request) 
		throws Exception{		
		return dao.getGybxglInfoExportList(myForm,colList,request);
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-5 ����02:01:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bxlb
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBxlbzxList(String bxlb) 
	throws Exception{		
	return dao.getBxlbzxList(bxlb);
} 
	
	/**
	 * 
	 * @����:��ȡ���䲿��list
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-15 ����10:30:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getfpbmList(){
		return  dao.getfpbmList();
	}
	
	/**
	 * 
	 * @����:���䱣��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-15 ����01:54:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean gybxglfpUpdate(GybxglForm myForm) throws Exception{		
		return dao.gybxglfpUpdate(myForm);
	}
	
	/**
	 * �鿴ѧ����Ϣ�Լ���ر�����Ϣ
	 * @param pk
	 * @return
	 */
	public HashMap<String,String> viewfpXsxx(String pk,String xh){
		return dao.viewfpXsxx(pk, xh);
	}

	/**
	 * @return  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-14 ����02:20:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public  List<HashMap<String, String>> getfpxqList() {
		return dao.getfpxqList();
	}	
}
