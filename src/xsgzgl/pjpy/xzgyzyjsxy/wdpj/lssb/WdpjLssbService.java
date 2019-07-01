package xsgzgl.pjpy.xzgyzyjsxy.wdpj.lssb;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.wdpj.WdpjLssbInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmDAO;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.wdpj.pjtj.WdpjPjtjDAO;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;
import xsgzgl.pjpy.xzgyzyjsxy.zhcp.PjpyZhcpService;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-11 ����10:27:12</p>
 */
public class WdpjLssbService extends xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbService {

	WdpjLssbDAO dao= new WdpjLssbDAO();
	
	// --------------------------��ʦ�ϱ�����������Ϣ begin--------------------
	/**
	 * ��ñ�ͷ�ļ�(�ҵ�����_��ʦ�ϱ�)
	 * @author qlj
	 */
	public List<HashMap<String, String>> getWdpjLssbTop(PjpyWdpjModel model,
			User user) {
		
		PjpyZhcpDAO zhcpDAO=new PjpyZhcpDAO();
		
		DAO dao = DAO.getInstance();
		
		// ----------------------��ʦ�ϱ� en begin-------------------------------------
		String[]en=new String[]{"xh","xm","nj","bjmc","zd3"};

		// ���������ֶ� ���ݻ����������۲����������ó�(1,2,3,4,5,6,7 �����)
		String[]zypmList=zhcpDAO.getZyPmArr("en");
		
		en=dao.unionArray(en, zypmList);
		
		String[]other=new String[]{"zd1","zd25"};
		
		en=dao.unionArray(en, other);
		
		// �۲������ֶ� ���ݻ����������۲����������ó�(1,2,3,4,5,6,7 �����)
		String[]zcpmList=zhcpDAO.getZcPmArr("en");
		
		en=dao.unionArray(en, zcpmList);
		
		other=new String[]{"cz"};
		
		en=dao.unionArray(en, other);
		// ----------------------��ʦ�ϱ� en end-------------------------------------
		
		
		//  ----------------------��ʦ�ϱ� en begin-------------------------------------
		String[]cn=new String[]{"ѧ��","����","�꼶","�༶","������"};

		// ���������ֶ� ���ݻ����������۲����������ó�(1,2,3,4,5,6,7 �����)
		String[]zypmcnList=zhcpDAO.getZyPmArr("cn");
		
		cn=dao.unionArray(cn, zypmcnList);
		
		String[]otherCn=new String[]{"�۲��","�۲�ȼ�"};
		
		cn=dao.unionArray(cn, otherCn);
		
		// �۲������ֶ� ���ݻ����������۲����������ó�(1,2,3,4,5,6,7 �����)
		String[]zcpmcnList=zhcpDAO.getZcPmArr("cn");
		
		cn=dao.unionArray(cn, zcpmcnList);
		
		other=new String[]{"����"};
		
		cn=dao.unionArray(cn, other);
		// ----------------------��ʦ�ϱ� en end-------------------------------------
		
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ѯ����(�ҵ�����_��ʦ�ϱ�)
	 * @author qlj
	 */
	public ArrayList<String[]> getWdpjLssbList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		// ָ��������Ŀ�������༶ѧ�������
		ArrayList<String[]> list = dao.getWdpjLssbList(myForm, model, user);
	
		return list;
	}

	// --------------------------��ʦ�ϱ�����������Ϣ end----------------------
	
	
	/**
	 * ѧ����Ϣ(�γ̳ɼ�)
	 * author qlj
	 */
	public List<HashMap<String,String>> getXscjList(String xh) throws IOException {
		return dao.getXscjList(xh);
	}
	
	/**
	 * ѧ����Ϣ(�γ̳ɼ����۲�ɼ�)
	 * author qlj
	 */
	public void showXsxxDiv(User user,String xmdm, String xh, HttpServletResponse response) throws IOException {
		
		XsxxglService stuService = new XsxxglService();
		
		response.setContentType("text/html;charset=gbk");
		
		PjpyZhcpService zhcpService = new PjpyZhcpService();
		
		HashMap<String,String>xsxxInfo=stuService.selectStuinfo(xh);

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\" style=\"overflow-x:hidden;overflow-y:auto;height:350px\" >");

		html.append("<table class=\"formlist\"  >");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span>ѧ����Ϣ</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		// ---------------------ѧ��������Ϣ begin -----------------
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("ѧ��");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:24%'>");
		html.append(xsxxInfo.get("xh"));
		html.append("</td>");
		html.append("<th width='25%'>");
		html.append("����");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:24%'>");
		html.append(xsxxInfo.get("xm"));
		html.append("</td>");
		html.append("</tr>");
		// ---------------------ѧ��������Ϣ end -----------------
		
		// ---------------------ѧ���ɼ���Ϣ begin ---------------
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span>ѧ���ɼ�</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<table width=\"100%\">");
		html.append("<tr>");
		html.append("<th>");
		html.append("ѧ��");
		html.append("</th>");
		html.append("<th>");
		html.append("ѧ��");
		html.append("</th>");
		html.append("<th>");
		html.append("�γ�����");
		html.append("</th>");
		html.append("<th>");
		html.append("�γ�����");
		html.append("</th>");
		html.append("<th>");
		html.append("�ɼ�");
		html.append("</th>");
		html.append("</tr>");
		
		List<HashMap<String,String>>xscjList=dao.getXscjList(xh);
		
		for(int i=0;i<xscjList.size();i++){
			
			HashMap<String,String>xscjMap=xscjList.get(i);
			html.append("<tr>");
			html.append("<td>");
			html.append(xscjMap.get("xn"));
			html.append("</td>");
			html.append("<td>");
			html.append(Base.isNull(xscjMap.get("xqmc"))?"":xscjMap.get("xqmc"));
			html.append("</td>");
			html.append("<td>");
			html.append(xscjMap.get("kcmc"));
			html.append("</td>");
			html.append("<td>");
			html.append(xscjMap.get("kcxz"));
			html.append("</td>");
			html.append("<td>");
			html.append(xscjMap.get("cj"));
			html.append("</td>");
			html.append("</tr>");
		}
		html.append("</table>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// ---------------------ѧ���ɼ���Ϣ end -----------------
		
		// ---------------------�����۲�ɼ� begin----------------
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span>�۲�ɼ�</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div style=\"overflow:auto;height:120px\">");
		html.append("<table width=\"100%\">");
		
		List<HashMap<String,Object>>bczcInfo=zhcpService.getBczcInfo(xh);
		for(int i=0;i<bczcInfo.size();i++){
			
			HashMap<String,Object>zccjMap=bczcInfo.get(i);
			
			HashMap<String,String>left=(HashMap<String,String>)zccjMap.get("left");
			
			HashMap<String,String>right=(HashMap<String,String>)zccjMap.get("right");
			html.append("<tr>");
			html.append("<th width='25%'>");
			html.append(Base.isNull(left.get("cn"))?"":left.get("cn"));
			html.append("</th>");
			html.append("<td width='25%'>");
			html.append(Base.isNull(left.get("zcinfo"))?"":left.get("zcinfo"));
			html.append("</td>");
			html.append("<th  width='25%'>");
			html.append(Base.isNull(right.get("cn"))?"":right.get("cn"));
			html.append("</th>");
			html.append("<td width='25%'>");
			html.append(Base.isNull(right.get("zcinfo"))?"":right.get("zcinfo"));
			html.append("</td>");
			html.append("</tr>");
			
		}
		html.append("</table>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// ---------------------�����۲�ɼ� end-------------------
		

		html.append("</table>");
		html.append("</div>");
		
		html.append("<div class=\"open_win01\">");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\" align=\"right\">");
		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		
		
		response.getWriter().print(html.toString());
	}

}
