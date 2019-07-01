package xsgzgl.jxgl.general.jxcjgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;


/**
 * ��ѵ����-��ѵ���˹���-��ѵ�ɼ�����
 * @author yeyipin
 * @since 2012.10.13
 */
public class JxglJxcjglService extends BasicService{
	
	JxglJxcjglDao jxglJxcjglDao = new JxglJxcjglDao();
	
	/**
	 * ��þ�ѵ��Ϣ(һ����
	 * @return
	 */
	public HashMap<String,String> getJxxx(String jxid) {
		return jxglJxcjglDao.getJxxx(jxid);
	}
	
	
	/**
	 * ������о�ѵ��Ϣ
	 * @return
	 */
	public List<HashMap<String,String>> getJxxxList() {
		return jxglJxcjglDao.getJxxxList();
	}
	
	
	/**
	 * ��ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String jzid) {
		List<HashMap<String, String>> jxcjpzList = jxglJxcjglDao.getJxcjpz(jzid);
		String[] cjen = new String[jxcjpzList.size()];
		String[] cjcn = new String[jxcjpzList.size()];
		for(int i =0; i< jxcjpzList.size();i++){
			cjen[i] = jxcjpzList.get(i).get("zd");
			cjcn[i] = jxcjpzList.get(i).get("zdm");
		}
		String[] en = new String[] { "","xh", "xm", "nj", "bjmc", "szjz"};
		String[] cn = new String[] { "","ѧ��", "����", "�꼶", "�༶", "���ڽ���"};
		return jxglJxcjglDao.arrayToList(jxglJxcjglDao.unionArray(en, cjen), jxglJxcjglDao.unionArray(cn, cjcn));
	}

	
	/**
	 * ��ѵ�ɼ���ѯ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> jxcjCx(JxglJxcjglForm model, HttpServletRequest request) throws Exception {
		//��������еľ�ѵ��ϢID
		String jzid = model.getJzid();
		//���������еľ�ѵ��ϢID�����ؿ�
		if(Base.isNull(jzid)){
			return null;
		}
		//��þ�ѵ�ɼ�������Ϣ
		String cjzd = "";
		List<HashMap<String, String>> jxcjpzList = jxglJxcjglDao.getJxcjpz(jzid);
		for(int i =0; i< jxcjpzList.size();i++){
			cjzd+=jxcjpzList.get(i).get("zd")+"!!@@!!";
		}
		model.setZd(cjzd);
		List<HashMap<String, String>> jxjzdjList = jxglJxcjglDao.getJxdjList();
		return jxglJxcjglDao.jxcjCx(model,request,jxjzdjList);
	}
	
	/**
	 * ����ҳ���ѯ���
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(JxglJxcjglForm model,SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		//��������еľ�ѵ��ϢID
		String jzid = model.getJzid();
		List<HashMap<String,String>> jxcjpz = jxglJxcjglDao.getJxcjpz(jzid);
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
					if(j>=rs.length-jxcjpz.size()){
						int k = j+jxcjpz.size()-rs.length;
						HashMap<String,String> map = jxcjpz.get(k);
						String zdlx = map.get("zdlx");
						String dm = map.get("option_dm");
						String mc = map.get("option_mc");
						if("select".equalsIgnoreCase(zdlx)){
							List<HashMap<String,String>> cjlbList = jxglJxcjglDao.getCjlb(map);
							html.append("<select name=\"fz_" + i + "_" + j + "\" style=\"width: 80px\" onblur=\"checkIn(this)\">");
							html.append("<option value=\"\"></option>");
							for (HashMap<String,String> fslrdj : cjlbList) {
								if (replaceHtml(rs[j]).equals(fslrdj.get(dm))) {
									html.append("<option value=\"" + fslrdj.get(dm) + "\" selected=\"selected\">" + fslrdj.get(mc) + "</option>");
								} else {
									html.append("<option value=\"" + fslrdj.get(dm) + "\">" + fslrdj.get(mc) + "</option>");
								}
							}
							html.append("</select>");
						}else if("text".equalsIgnoreCase(zdlx)){
							html.append("<input id=\"fz_" + k + "\" name=\"fz_" + i + "_" + j + "\" value='"+replaceHtml(rs[j])+"' maxlength=\"10\" style=\"width:80px\" onkeyup=\"if(event.keyCode==13) {skipNext(this)}\" onblur=\"checkInputNum(this);checkIn(this)\"/");
						}else{
							html.append(replaceHtml(rs[j]));
						}
					}else{
						html.append(replaceHtml(rs[j]));
					}
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}


	/**
	 * ��ѵ�ɼ�����
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String jxcjBc(JxglJxcjglForm model) throws SQLException {
		//��þ�ѵ��ϢID
		String jzid = model.getJzid();
		//��ѵ�ɼ����ñ�
		List<HashMap<String,String>> jxcjpzList = jxglJxcjglDao.getJxcjpz(jzid);
		String[]pkValue = model.getPkValue().split("!!splitOne!!");
		List<String[]> delParams = new ArrayList<String[]>();
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length;i++){
			String[] pkV = pkValue[i].split("!!@@!!");
			delParams.add(new String[]{jzid,pkV[0]});
			String[] pkV2 = pczhyg(pkV);
			//����ɼ���Ϊ�գ�����
			if(notEmpty(pkV2)){
				params.add(jxglJxcjglDao.unionArray(new String[]{jzid}, pkV2));
			}
		}
		boolean flag = jxglJxcjglDao.jxcjDel(delParams);
		if(flag){
			flag = jxglJxcjglDao.jxcjBc(params,jxcjpzList);
		}
		
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}


	/**
	 * ȡ�����һλ��־����
	 * @param pkV
	 * @return
	 */
	private String[] pczhyg(String[] pkV) {
		String[] pkValue = new String[pkV.length-1];
		for(int i = 0; i < pkV.length -1;i ++){
			pkValue[i] = pkV[i];
		}
		return pkValue;
	}
	
	/**
	 * �ɼ��Ƿ�Ϊ��
	 * @param pkV2
	 * @return
	 */
	private boolean notEmpty(String[]pkV) {
		boolean flag = false;
		for(int i = 1; i < pkV.length;i ++){
			if(!Base.isNull(pkV[i])){
				flag = true; 
			}
		}
		return flag;
	}


	/**
	 * ��ѵ�ɼ�����
	 * @param outputStream
	 * @param model
	 * @throws Exception 
	 */
	public void jxcjDc(ServletOutputStream outputStream, JxglJxcjglForm model, HttpServletRequest request) throws Exception {
		//��������еľ�ѵ��ϢID
		String jxid = model.getJxid();
		model.setJzid(jxid);
		List<HashMap<String, String>> jxcjpzList = jxglJxcjglDao.getJxcjpz(jxid);
		
		String title = "��ѵ�ɼ�ͳ��";
		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopTr(jxid);
		// ��������Ĺ̶�����
		ArrayList<String[]> gdlist = jxcjCx(model,request);

		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		ws.addCell(new Label(0, 0, title, wcf2));
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 1; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.setColumnView(i-1, 25);
				ws.addCell(new Label(i-1, 1, map.get("cn"), wcf2));
			}
		}
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 1; j < info.length; j++) {
						if(j>=info.length-jxcjpzList.size()){
							int k = j+jxcjpzList.size()-info.length;
							HashMap<String,String> map = jxcjpzList.get(k);
							String zdlx = map.get("zdlx");
							String dm = map.get("option_dm");
							String mc = map.get("option_mc");
							boolean flag = false;
							if("select".equalsIgnoreCase(zdlx)){
								List<HashMap<String,String>> cjlbList = jxglJxcjglDao.getCjlb(map);
								for (HashMap<String,String> fslrdj : cjlbList) {
									if (info[j].equals(fslrdj.get(dm))) {
										ws.addCell(new Label(j-1, i + 2, fslrdj.get(mc), wcf2));
										flag = true;
									}
								}
							}
							if(!flag){
								ws.addCell(new Label(j-1, i + 2, info[j], wcf2));
							}
						}else{
							ws.addCell(new Label(j-1, i + 2, info[j], wcf2));
						}
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}

	/**
	 * ��֤�Ƿ��о�ѵ�ɼ�����ѧ��
	 * @param xhs   201210110!!@@!!201212121!!@@!!201212121 (ѧ����'!!@@!!'���Ÿ���)
	 * @param jxid	 CBDD274BE880EBC3E040007F01000647
	 * @return   ture:ѧ���гɼ� ,  false:ѧ��û�гɼ����߲�������
	 */
	public boolean checkIsJxcjByXs(String xhs,String jxid){
		boolean result=false;
		if(xhs == null || "".equals(xhs) || jxid==null || "".equals(jxid)){
			return result;
		}
		String[] xh=xhs.split("!!@@!!");
		List<HashMap<String, String>> list=jxglJxcjglDao.getJxcjByXhAndJxid(xh, jxid);
		if(list != null && list.size() > 0){
			result=true;
		}else{
			result= false;
		}
		return result;
			
	}
	
	/** 
	 * ��ȡ�Զ��嵼���ľ�ѵ����ɼ�
	 * @param jxcjpzList
	 * @param resultList
	 * void �������� 
	 * @throws 
	 */
	private void getCjmc(List<HashMap<String, String>> jxcjpzList,
			List<HashMap<String, String>> resultList) {
		for (int j = 0; j < resultList.size(); j++) {
			HashMap<String, String> resultMap = resultList.get(j);
			String[] jxcj = new String[]{"cj1","cj3"};
			
				for (int i = 0; i < jxcj.length; i++) {
					String cj=resultMap.get(jxcj[i]);
					if (cj != "" && cj != null) {
				for (int k = 0; k < jxcjpzList.size(); k++) {
					HashMap<String, String> map = jxcjpzList.get(k);
					String zdlx = map.get("zdlx");
					String dm = map.get("option_dm");
					String mc = map.get("option_mc");
					// boolean flag = false;
					if ("select".equalsIgnoreCase(zdlx)) {
						List<HashMap<String, String>> cjlbList = jxglJxcjglDao.getCjlb(map);
						for (HashMap<String, String> fslrdj : cjlbList) {
							if (cj.equals(fslrdj.get(dm))) {
								resultMap.put(jxcj[i], fslrdj.get(mc));
							}
						}
					}
				}
				}
			}
		}
	}


	/**
	 * ��ѵ����ɼ��Զ��嵼��
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> jxcjExportDataCx(JxglJxcjglForm model, HttpServletRequest request) throws Exception {
		
				
		//��������еľ�ѵ��ϢID
		String jzid = model.getJzid();
		//���������еľ�ѵ��ϢID�����ؿ�
		if(Base.isNull(jzid)){
			return null;
		}
		//��þ�ѵ�ɼ�������Ϣ
		String cjzd = "";
		List<HashMap<String, String>> jxcjpzList = jxglJxcjglDao.getJxcjpz(jzid);
		for(int i =0; i< jxcjpzList.size();i++){
			cjzd+=jxcjpzList.get(i).get("zd")+"!!@@!!";
		}
		model.setZd(cjzd);
		List<HashMap<String, String>> jxjzdjList = jxglJxcjglDao.getJxdjList();				
		List<HashMap<String,String>> resultList = jxglJxcjglDao.jxcjExportDataCx(model,request,jxjzdjList);				
		getCjmc(jxcjpzList, resultList);
		return resultList;
		
	}
	
	
}
