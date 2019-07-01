package xsgzgl.jxgl.general.jxbxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

public class JxglJxbxglService extends BasicService{
	JxglJxbxglDao jxglJxbxglDao =new JxglJxbxglDao();
	
	
	/**
	 * ��ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		String[] en = new String[] { "","jxmc", "bxlbmc", "jtbx", "hdrys"};
		String[] cn = new String[] { "","��ѵ����", "�������", "�������", "�����Ա��"};
		if("bxmd".equalsIgnoreCase(type)){
			en = new String[] { "","xh", "xm", "xb", "nj","xymc","zymc","bjmc","szjz","sfhd"};
			cn = new String[] { "","ѧ��", "����", "�Ա�", "�꼶","Ժϵ","רҵ","�༶","���ڽ���","�Ƿ���"};
		}
		return jxglJxbxglDao.arrayToList(en,cn);
	}
	
	/**
	 * ��ѵ���ֲ�ѯ
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> jxbxCx(JxglJxbxglForm model) throws Exception{
		return jxglJxbxglDao.jxbxCx(model);
	}
	
	
	/**
	 * ����ҳ���ѯ���
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length-1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append("><a href='jxgl_jxbxgl.do?method=bxmdCx&pkValue="+replaceHtml(rs[0])+"&sfhd=yes'><font class='blue'>");
				html.append(replaceHtml(rs[rs.length-1]));
				html.append("</font></a></td>");
				html.append("</tr>");
			}
		}
		return html.toString();
	}


	/**
	 * ��þ�ѵ������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxbx(JxglJxbxglForm model) {
		return jxglJxbxglDao.getJxbx(model);
	}


	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> bxmdCx(JxglJxbxglForm model, HttpServletRequest request) throws Exception {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String jxid = pkValue[0];
		String bxlb = pkValue[1];
		String jtbx = pkValue[2];
		//��ѵ�ȼ��б���
		model.setLen(String.valueOf(jxglJxbxglDao.getJxdjList().size()));
		model.setJxid(jxid);
		model.setBxlb(bxlb);
		model.setJtbx(jtbx);
		List<HashMap<String, String>> jxjzdjList = jxglJxbxglDao.getJxdjList();
		return jxglJxbxglDao.bxmdCx(model,request,jxjzdjList);
	}
	
	
	/**
	 * ����ҳ���ѯ���
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML2(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}


	
	/**
	 * ��ѵ�������������ӣ�ɾ����
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String bxmdCz(JxglJxbxglForm model) throws Exception {
		String doType = model.getDoType();
		boolean flag = false;
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String jxid = pkValue[0];
		String bxlb = pkValue[1];
		String jtbx = pkValue[2];
		String[] xh = model.getXh().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < xh.length; i++){
			params.add(new String[]{jxid,bxlb,jtbx,xh[i]});
		}
		if("add".equalsIgnoreCase(doType)){
			flag = jxglJxbxglDao.bxmdSc(params);
			if(flag){
				flag = jxglJxbxglDao.bxmdZj(params);
			}
		}else if("del".equalsIgnoreCase(doType)){
			flag = jxglJxbxglDao.bxmdSc(params);
		}
		return flag?MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}
	
	/**
	 * ��֤�Ƿ��о�ѵ���� ����ѧ��
	 * @param xhs   201210110!!@@!!201212121!!@@!!201212121 (ѧ����'!!@@!!'���Ÿ���)
	 * @param jxid	 CBDD274BE880EBC3E040007F01000647
	 * @return   ture:ѧ���ٻ����,  false:ѧ��û���ٻ���ֻ��߲�������
	 */
	public boolean checkIsJxbxByXs(String xhs,String jxid){
		boolean result=false;
		if(xhs == null || "".equals(xhs) || jxid==null || "".equals(jxid)){
			return result;
		}
		String[] xh=xhs.split("!!@@!!");
		List<HashMap<String, String>> list=jxglJxbxglDao.getJxbxByXhAndJxid(xh, jxid);
		if(list != null && list.size() > 0){
			result=true;
		}else{
			result= false;
		}
		return result;
			
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getAllbxmd(JxglJxbxglForm model, HttpServletRequest request) throws Exception {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String jxid = pkValue[0];
		String bxlb = pkValue[1];
		String jtbx = pkValue[2];
		//��ѵ�ȼ��б���
		model.setLen(String.valueOf(jxglJxbxglDao.getJxdjList().size()));
		model.setJxid(jxid);
		model.setBxlb(bxlb);
		model.setJtbx(jtbx);
		List<HashMap<String, String>> jxjzdjList = jxglJxbxglDao.getJxdjList();
		return jxglJxbxglDao.getAllbxmd(model,request,jxjzdjList);
	}




}
