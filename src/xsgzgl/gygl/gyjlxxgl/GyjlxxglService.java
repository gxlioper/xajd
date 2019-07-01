package xsgzgl.gygl.gyjlxxgl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.xgxt.base.util.DateTranCnDate;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

public class GyjlxxglService extends CommService{
	
	private GyjlxxglDao dao=new GyjlxxglDao();
	
	public List<HashMap<String, String>> getToplist(String[] colListCN ) throws Exception {
		String[] en = new String[colListCN.length];
		for(int i=0;i<en.length;i++){
			en[i]="tr_"+i;
		}
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.arrayToList(en,colListCN);
		return list;
	}
	
	/**
	 * ��ñ�ͷ
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		String[] str = null;
		if(type.equals("jlxx")){
			str = new String[]{"ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY,"�༶","¥������","���Һ�","��λ��"};
		}else if(type.equals("wjxx")){
			str = new String[]{"ѧ��","����","�Ա�","�༶","ס������","Υ��ʱ��","Υ�����", "������"};
		}else if(type.equals("wjcl")){
			str = new String[]{"ѧ��","����","�Ա�","ס������","Υ��ʱ��","Υ�����","������","���״̬"};
		}else if(type.equals("shxx")){
			str = new String[]{"","ѧ��","����","�Ա�","ס������","Υ��ʱ��","Υ�����","������","���״̬"};
		}
		return str;
	}
	
	/**
	 * ���ס��ѧ��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJlxxList(GyjlxxglForm model, HttpServletRequest request) throws Exception{
		return dao.getJlxxList(model,request);
	}
	/**
	 * ���������Ϣ
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String saveJlxx(GyjlxxglForm myForm,HttpServletRequest request) throws Exception{
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
			pk_xh = dao.getXhs(searchTjstr);
		}
		myForm.setPk_xh(pk_xh);
		return dao.saveJlxx(myForm, user);
	}
	
	/**
	 * ��ü��ɴ���list
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> getJldlList(HttpServletRequest request){
		return dao.getJldlList(request);
	}
	/**
	 * ��ȡ�������list
	 * @param jldldm
	 * @param request
	 * @return
	 */
	public List<HashMap<String,String>> getJllbList(String jldldm,HttpServletRequest request){
		return dao.getJllbList(jldldm,request);
	}
	
	/**
	 * ��Ԣ���ɴ�������
	 * @param jldldm
	 * @param request
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>> getCflbList(){
		return dao.getCflbList();
	}
	
	/**
	 * ���ѧ����Ϣ
	 * @param xh
	 * @param request
	 * @return
	 */
	public Map<String, String> getXsxx(String xh, HttpServletRequest request) {
		return dao.getXsxx(xh,request);
	}
	/**
	 * ���ѧ����Υ����Ϣ
	 * @param xh
	 * @param request
	 * @return
	 */
	public List<String[]> getOneWjxxList(String xh,String wjsj, HttpServletRequest request) {
		return dao.getOneWjxxList(xh,wjsj,request);
	}
	/**
	 * �����ʷΥ����Ϣ
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getWjxxList(GyjlxxglForm myForm, HttpServletRequest request,String type) throws Exception {
		return dao.getWjxxList(myForm,request,type);
	}
	/**
	 * ɾ��Υ����Ϣ
	 * @param myForm
	 * @param request
	 * @return
	 * @throws SQLException 
	 */
	public String delWjxx(GyjlxxglForm myForm, HttpServletRequest request) throws SQLException {
		return dao.delWjxx(myForm,request);
	}
	/**
	 * ���ѧ��Υ����Ϣ
	 * @param pkValue
	 * @param request
	 * @return
	 */
	public  Map<String, String> getXswjxx(String[] pkValue, HttpServletRequest request) {
		return dao.getXswjxx(pkValue,request);
	}

	/**
	 * ���ͬ����
	 * @param xh
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> getTqs(String xh,
			HttpServletRequest request) {
		return dao.getTqsxsxx(xh,request);
	}
	
	
	/**
	 * 
	 * @����:�㽭��ý���Ի�������Υ��ʱ���ȡѧ����Ӧ���ʱ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-6-13 ����05:47:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param wjsj
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjInfo(String xh,String wjsj){
		return dao.getQjInfo(xh,wjsj);
	}
	
	

	/**
	 * ���湫Ԣ������Ϣ
	 * @param myForm
	 * @param request
	 * @return
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public String saveGyjlxx(GyjlxxglForm myForm, HttpServletRequest request) throws SQLException, ParseException {
		String[] xh = myForm.getXh().split("!!@@!!");
		String gyjllbdldm = myForm.getJldldm();
		String gyjllbdm = myForm.getJllbdm();
		String wjsj = myForm.getWjsj();
		String bz = myForm.getBz();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String czr = getUser(request).getUserName();
		String fileid = myForm.getFileid();
		String ycxh = "";
		String gyjllbdlmc = dao.getJldlListByDm(gyjllbdldm);
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = sdf.parse(wjsj);
		int n = DateTranCnDate.getDay(d);
		if("13573".equalsIgnoreCase(Base.xxdm)) {
			if("ҹ������".equals(gyjllbdlmc)) {
				for(int i =0; i < xh.length;i++){				
					String num = dao.isExists2(xh[i],wjsj);
					if(!num.equals("0")) {
						return "��ʱ�����ѧ�����������������Ҫ�Ǽǣ�";
					}
					if(n == 7 || n == 1) {
						String num2 = dao.isExists3(xh[i],wjsj);
						if(!num2.equals("0")) {
							return "��ѧ���������˳��ڼ�������ĩ����Ҫ�Ǽǣ�";
						}
					}
				}
				
			}
		}

		List<String[]>param = new ArrayList<String[]>();
		String[] jllbdms=gyjllbdm.split(",");
		for(String jllbdm:jllbdms){
			if(StringUtils.isNull(jllbdm)){
				continue;
			}
			for(int i =0; i < xh.length;i++){
				String[] pkValue = new String[]{xh[i],wjsj,jllbdm};
				if(isExist(pkValue)){
					if(!ycxh.contains(pkValue[0])){
						ycxh = ycxh + pkValue[0]+",";
					}
					continue;
				}
				String[] el = new String[]{gyjllbdldm,jllbdm,wjsj,bz,czr,xn,xq,xh[i]};
				if("70002".equals(Base.xxdm)){
					 el = new String[]{gyjllbdldm,jllbdm,wjsj,bz,czr,xn,xq,fileid,xh[i]};
				}
				param.add(el);
			}
		}
		if(param.size() ==0){
			return "����ʧ�ܣ�<br>ѡ��ѧ����Υ�ͼ�¼��¼��,�����ظ�¼�룡";
		}else{
			boolean result = false;
			
			try {
				result = dao.saveGyjlxx(param);
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
			return result&&(ycxh.equals("")) ? "����ɹ���" : "����ɹ���<br>����,ѧ�� "+ycxh.substring(0,ycxh.length()-1)+" ��Υ�ͼ�¼��¼��,�����ظ�¼�룡";
		}
	}
	
	private boolean isExist(String[] pkValue) {
		return dao.isExists(pkValue);
	}

	/**
	 * �޸Ĺ�Ԣ������Ϣ
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public String updateGyjlxx(GyjlxxglForm myForm, HttpServletRequest request) throws Exception {
		String czr = getUser(request).getUserName();
		myForm.setCzr(czr);
		String xh = myForm.getXh();
		String gyjllbdldm = myForm.getJldldm();
		String wjsj = myForm.getWjsj();
		String gyjllbdlmc = dao.getJldlListByDm(gyjllbdldm);
		String gyjllbdm = myForm.getJllbdm();
		String yjllbdm = request.getParameter("yjllbdm");
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = sdf.parse(wjsj);
		int n = DateTranCnDate.getDay(d);
		if("13573".equalsIgnoreCase(Base.xxdm)) {
			if("ҹ������".equals(gyjllbdlmc)) {
				String num = dao.isExists2(xh,wjsj);
				if(!num.equals("0")) {
					return "��ʱ�����ѧ�����������������Ҫ�Ǽǣ�";
				}
				if(n == 7 || n == 1) {
					String num2 = dao.isExists3(xh,wjsj);
					if(!num2.equals("0")) {
						return "��ѧ���������˳��ڼ�������ĩ����Ҫ�Ǽǣ�";
					}
				}			
			}
		}
		// ��������޸�
		if(!gyjllbdm.equals(yjllbdm)){
			String[] pkValue = new String[]{xh,wjsj,gyjllbdm};
			if(isExist(pkValue)){
				return "��ѧ����Υ�ͼ�¼��¼��,�����ظ�¼�룡";
			}
		}
		return dao.updateGyjlxx(myForm,request);
	}
	
	/**
	 * ��ȡ�����Ϣ
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<String[]>getGyjlShList(GyjlxxglForm myForm,User user, HttpServletRequest request) throws Exception{
		
		return dao.getGyjlShList(myForm, user, request);
		
	}
	
	/**
	 * �޸����״̬
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean saveShzt(GyjlxxglForm myForm,User user) throws Exception{
		
		return dao.saveShzt(myForm, user);
	}
	
	/**
	 * �������洦�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean plclGyjlxx(GyjlxxglForm myForm) throws Exception{
		return dao.plclGyjlxx(myForm);
	}
	
	/**
	 * �޸Ĵ�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean saveCljg(GyjlxxglForm myForm,User user) throws Exception{
		
		return dao.saveCljg(myForm, user);
	}
}
