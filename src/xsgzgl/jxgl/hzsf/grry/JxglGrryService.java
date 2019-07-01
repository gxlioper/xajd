package xsgzgl.jxgl.hzsf.grry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
/**
 * ��ѵ����-��ѵ��-��������
 * @author yeyipin
 * @since 2012.7.27
 */
public class JxglGrryService extends BasicService{
	JxglGrryDAO dao = new JxglGrryDAO();
	/**
	 * ��ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xn", "xh","xm","tuan","ying","lian","bjmc","grrymc" };
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "����", "�ż�", "Ӫ��", "����", "�༶","������������" };
		if("xsmd".equalsIgnoreCase(type)){
			en = new String[]{"xh","xm","bjmc","tuanmc","yingmc","lianmc"};
			cn = new String[]{"ѧ��","����","�༶","�ż�","Ӫ��","����"};
		}
		return dao.arrayToList(en, cn);
	}
	/**
	 * ����������ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> GrryCx(JxglGrryForm model) throws Exception {
		StringBuilder SearchTj = new StringBuilder();
		String[] query = model.getQuery().split("!!splitOne!!");
		String xn = query[0];
		String nj = query[1];
		String grrydm = query[2];
		String xydm = query[3];
		String zydm = query[4];
		String bjdm = query[5];
		String tuandm = query[6];
		String yingdm = query[7];
		String liandm = query[8];
		//�����������
		String xh = unicode2Gbk(query[9]);
		String xm = unicode2Gbk(query[10]);
		if(xn!=null&&!"".equalsIgnoreCase(xn)){
			SearchTj.append(" and xn = '");
			SearchTj.append(xn);
			SearchTj.append("' ");
		}
		if(nj!=null&&!"".equalsIgnoreCase(nj)){
			SearchTj.append(" and nj = '");
			SearchTj.append(nj);
			SearchTj.append("' ");
		}
		if(grrydm!=null&&!"".equalsIgnoreCase(grrydm)){
			SearchTj.append(" and grrydm = '");
			SearchTj.append(grrydm);
			SearchTj.append("' ");
		}
		if(xydm!=null&&!"".equalsIgnoreCase(xydm)){
			SearchTj.append(" and xydm = '");
			SearchTj.append(xydm);
			SearchTj.append("' ");
		}
		if(zydm!=null&&!"".equalsIgnoreCase(zydm)){
			SearchTj.append(" and zydm = '");
			SearchTj.append(zydm);
			SearchTj.append("' ");
		}
		if(bjdm!=null&&!"".equalsIgnoreCase(bjdm)){
			SearchTj.append(" and bjdm = '");
			SearchTj.append(bjdm);
			SearchTj.append("' ");
		}
		if(tuandm!=null&&!"".equalsIgnoreCase(tuandm)){
			SearchTj.append(" and tuandm = '");
			SearchTj.append(tuandm);
			SearchTj.append("' ");
		}
		if(yingdm!=null&&!"".equalsIgnoreCase(yingdm)){
			SearchTj.append(" and yingdm = '");
			SearchTj.append(yingdm);
			SearchTj.append("' ");
		}
		if(liandm!=null&&!"".equalsIgnoreCase(liandm)){
			SearchTj.append(" and liandm = '");
			SearchTj.append(liandm);
			SearchTj.append("' ");
		}
		if(xh!=null&&!"".equalsIgnoreCase(xh)){
			SearchTj.append(" and xh like '%");
			SearchTj.append(xh);
			SearchTj.append("%' ");
		}
		if(xm!=null&&!"".equalsIgnoreCase(xm)){
			SearchTj.append(" and xm like '%");
			SearchTj.append(xm);
			SearchTj.append("%' ");
		}
		return dao.GrryCx(model,SearchTj.toString());
	}
	/**
	 * ��þ�ѵѧ����ϢList
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getJxxs(JxglGrryForm model) throws Exception {
		StringBuilder SearchTj = new StringBuilder();
		String[] query = model.getQuery().split("!!splitOne!!");
		String xn = query[0];
		String tuandm = query[1];
		String yingdm = query[2];
		String liandm = query[3];
		//�����������
		String xh = unicode2Gbk(query[4]);
		String xm = unicode2Gbk(query[5]);
		if(xn!=null&&!"".equalsIgnoreCase(xn)){
			SearchTj.append(" and xn = '");
			SearchTj.append(xn);
			SearchTj.append("' ");
		}
		if(tuandm!=null&&!"".equalsIgnoreCase(tuandm)){
			SearchTj.append(" and tuandm = '");
			SearchTj.append(tuandm);
			SearchTj.append("' ");
		}
		if(yingdm!=null&&!"".equalsIgnoreCase(yingdm)){
			SearchTj.append(" and yingdm = '");
			SearchTj.append(yingdm);
			SearchTj.append("' ");
		}
		if(liandm!=null&&!"".equalsIgnoreCase(liandm)){
			SearchTj.append(" and liandm = '");
			SearchTj.append(liandm);
			SearchTj.append("' ");
		}
		if(xh!=null&&!"".equalsIgnoreCase(xh)){
			SearchTj.append(" and xh like '%");
			SearchTj.append(xh);
			SearchTj.append("%' ");
		}
		if(xm!=null&&!"".equalsIgnoreCase(xm)){
			SearchTj.append(" and xm like '%");
			SearchTj.append(xm);
			SearchTj.append("%' ");
		}
		return dao.getJxxs(model,SearchTj.toString());
	}

	/**
	 * ��ø�������map
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getGrryMap(JxglGrryForm model) {
		return dao.getGrryMap(model);
	}
	/**
	 * ��þ�ѵѧ����Ϣmap
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxsMap(JxglGrryForm model) {
		return dao.getJxxsMap(model);
	}
	/**
	 * ������������
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String grryBc(JxglGrryForm model) throws Exception {
		return dao.grryBc(model)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * ���������޸ı���
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String grryXg(JxglGrryForm model) throws Exception {
		return dao.grryXg(model)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * ����������ɾ��
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String grrySc(JxglGrryForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!splitOne!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			String[] pkV = pkValue[i].split("!!@@!!");
			params.add(new String[]{pkV[0],pkV[1],pkV[2]});
		}
		return dao.grrySc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	/**
	 * ����HTML��ѯҳ��
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
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
	 * ������ѵ������ѯ��ϢHTML2
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
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"sendXh();\" style=\"cursor:hand\">");
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length) + "%\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append("<input type=\"hidden\" value=\""+rs[j]+"\"/>");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	/**
	 * �����Ӫ���б�
	 * @param type
	 * @param currXn
	 * @return
	 */
	public List<HashMap<String,String>> getTuanYingLianList(JxglGrryForm model) {
		return dao.getTuanYingLianList(model);
	}
	/**
	 * ��֤������Ϣ
	 * @param model
	 * @return
	 */
	public String checkSaveInfo(JxglGrryForm model) {
		return dao.isExist(model)?"��ѧ�����������Ѵ���":"true";
	}




}
