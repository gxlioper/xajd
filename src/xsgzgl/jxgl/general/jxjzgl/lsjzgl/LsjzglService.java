package xsgzgl.jxgl.general.jxjzgl.lsjzgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.struts.util.MessageResources;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;
import xsgzgl.jxgl.general.jxjzgl.jxjzgl.JxjzglFrom;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ʷ���ƹ���_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author �׽���
 * @version 1.0
 */

public class LsjzglService extends CommService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	LsjzglDAO dao=new LsjzglDAO();
	
	/**
	 * ��ѯ��ѵ����ͳ�Ʊ�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxjzTjb(LsjzglFrom model) throws Exception{
		List<HashMap<String, String>> jzdjList=dao.getJxJzdj(model);
		
		if(jzdjList == null || jzdjList.size() == 0){
			return null;
		}
		List<HashMap<String, String>> jxjzList=dao.getJxjzTjb(jzdjList, model);
		return jxjzList;
	}
	
	/**
	 * ������ѵ����ͳ�Ʊ�html
	 * @param jxjzList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String createJxjzTjxHtml(List<HashMap<String, String>> jxjzList,LsjzglFrom model) throws Exception{
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		List<HashMap<String, String>> jzdjList=dao.getJxJzdj(model);
		if(jxjzList == null){
			return "";
		}
		//��ȡ�ϲ�����ֵ����
		List<Object> jxjzNnion=setJxjzTjxNnionTable(jxjzList, jzdjList);
		HashMap<String, Integer> jzNnion=(HashMap<String, Integer>)jxjzNnion.get(0);
		HashMap<String, Integer> jzRs=(HashMap<String, Integer>)jxjzNnion.get(1);
		HashMap<String, String> jzXymc=(HashMap<String, String>)jxjzNnion.get(2);
		HashMap<String, Integer> jzBjNnion=(HashMap<String, Integer>)jxjzNnion.get(3);
		
		//�ϲ�ʹ�ò���
		StringBuffer html=new StringBuffer();
		HashMap<String, String> unionMapMc=new HashMap<String, String>();
		HashMap<String, String> unionMapDm=new HashMap<String, String>();
		HashMap<String, Integer> addUpMap=new HashMap<String, Integer>();
		HashMap<String, String> joinMap=new HashMap<String, String>();
		String rowspanNum=new String("");
		String jzmcBh="";
		String key="";
		String bjdm="";
		//HashMap<String, Integer> rowspanNums=new HashMap<String, Integer>();
		
		html.append("<span class=\"formbox\">");
		html.append("<table id=\"table_rs\" class=\"dateline\" width=\"100%\">");
		html.append("<thead><tr>");
		//����ͷͷ��
		for (int i = 0; i < jzdjList.size(); i++) {
			html.append("<td width=\"5\" noWrap>");
			html.append(jzdjList.get(i).get("djmc"));
			html.append("</td>");
			
			html.append("<td width=\"5\" noWrap>");
			html.append(jzdjList.get(i).get("djmc"));
			html.append("��");
			html.append("</td>");
			
			html.append("<td width=\"5\" noWrap>��ʦ</td>");
			
			html.append("<td width=\"5\" noWrap>����</td>");
			
			if(i==0){
				html.append("<td width=\"40px\" noWrap>"+Base.YXPZXY_KEY+"</td>");
			}
			
			if(i==(jzdjList.size()-1)){
				html.append("<td width=\"5\" noWrap>�༶</td>");
				html.append("<td width=\"5\" noWrap>����</td>");
			}
		}
		html.append("</tr></thead>");
		//�����б�
		html.append("<tbody>");
		for (int i = 0; i < jxjzList.size(); i++) {
			html.append("<tr>");
			for (int j = 0; j < jzdjList.size(); j++) {
				jzmcBh="jzmc"+jzdjList.get(j).get("djdm");
				key=jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm"));
				bjdm=jxjzList.get(i).get("bjdm");
				if((unionMapMc.get(jzmcBh) == null || !unionMapMc.get(jzmcBh).equals(jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm"))))
						|| (unionMapDm.get(jzmcBh) == null || !unionMapDm.get(jzmcBh).equals(key))){
					//rowspanNums.put(jzmcBh, Integer.valueOf(1));
					//��ʼ��
					//��������
					rowspanNum=new String();
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append(nullToString(jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm"))));
					html.append("</td>");
					
					//�̹�����
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append(nullToString(jxjzList.get(i).get("jgmc"+jzdjList.get(j).get("djdm"))));
					html.append("</td>");
					//��ʦ����
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append(nullToString(jxjzList.get(i).get("jsmc"+jzdjList.get(j).get("djdm"))));
					html.append("</td>");
					
					//����
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append("<a href=\"javascript:void(0);\" onclick=\"cxJzjzmdByJzid('");
					html.append(jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm")));
					html.append("')\" class=\"name\">");
					html.append(jzRs.get(key));
					html.append("</a>");
					html.append("��");
					html.append("</td>");
					
					//Ժϵ
					if(j == 0){
						html.append("<td height=\"28\" style=\"width:80px;\" noWrap align=\"left\"");
						html.append(" rowspan=\"");
						html.append(jzNnion.get(key));
						html.append("\">");
						html.append(nullToString(jzXymc.get(key)));
						html.append("</td>");
					}
				}
//				else{
//					rowspanNums.get(jzmcBh);
//					rowspanNums.put(jzmcBh, rowspanNums.put(jzmcBh, Integer.valueOf(1)).intValue()+1);
//				}
				//���һ������
				if(j == (jzdjList.size()-1)){
					//�༶
					if(jzBjNnion.get(key+bjdm) != null){
						html.append("<td height=\"28\" noWrap align=\"left\"");
						html.append(" rowspan=\"");
						html.append(jzBjNnion.get(key+bjdm));
						html.append("\">");
						html.append(nullToString(jxjzList.get(i).get("bjmc")));
						html.append("</td>");
						jzBjNnion.remove(key+bjdm);
					}
					
					//����
					html.append("<td height=\"28\" noWrap align=\"left\">");
					html.append("<a href=\"#\" class=\"name\"  onclick=\"cxJzjzmdByJzidAndBjdm('");
					html.append(jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm")));
					html.append("','");
					html.append(bjdm);
					html.append("')\" >");
					html.append(jxjzList.get(i).get("jzrs"));
					html.append("</a>");
					html.append("(");
					html.append(nullToString(jxjzList.get(i).get("xb")));
					html.append(")");
					html.append("</td>");
				}
				
				//
				unionMapMc.put(jzmcBh, jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm")));
				unionMapDm.put(jzmcBh, key);
			}
			html.append("</tr>");
		}
		html.append("</tbody>");
		html.append("</table>");
		html.append("</span>");
		return html.toString();
	}
	
	/**
	 * ��nullת���ַ�
	 * @param value
	 * @return
	 */
	private String nullToString(String value){
		if(value == null){
			return "";
		}
		return value;
	}
	
	/**
	 * ���þ�ѵͳ�Ʊ�
	 * @param jxjzList
	 * @param jzdjList
	 * @return
	 */
	private List<Object> setJxjzTjxNnionTable(List<HashMap<String, String>> jxjzList
			,List<HashMap<String, String>> jzdjList){
		HashMap<String, Integer> mapNnion=new HashMap<String, Integer>();//�ϲ�����
		HashMap<String, Integer> mapRs=new HashMap<String, Integer>();//�ϲ�����
		HashMap<String, String> mapXymc=new HashMap<String, String>();//�ϲ�ѧԺ����
		HashMap<String, Integer> mapBjNnion=new HashMap<String, Integer>();//�ϲ��༶����
		List<Object> jxjzNnion = new ArrayList<Object>();
		
		HashMap<String, String> unionMap=new HashMap<String, String>();
		Integer time=0;
		String key="";
		Integer rs=0;
		String xymc="";
		String bjdm="";
		for (int i = 0; i < jxjzList.size(); i++) {
			for (int j = 0; j < jzdjList.size(); j++) {
				key=jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm"));
				rs=Integer.valueOf(jxjzList.get(i).get("jzrs"));
				xymc=jxjzList.get(i).get("xymc");
				bjdm=jxjzList.get(i).get("bjdm");
				if(mapNnion.get(key) != null){
					time=mapNnion.get(key);
					mapNnion.put(key, 
							(time.intValue()+1));
					
				}else{
					//��һ�����ֵ�ǰ����
					if(key != null){
						mapNnion.put(key, 
								Integer.valueOf("1"));
					}
				}
					
					
				unionMap.put(key, 
						"true");
				//������¼
				if(mapRs.get(key) != null){
					mapRs.put(key, Integer.valueOf(mapRs.get(key))+rs);
				}else{
					mapRs.put(key,rs);
				}
				
				//ѧԺ��¼
				if(mapXymc.get(key) != null){
					if(xymc!=null && mapXymc.get(key).indexOf(xymc) <0){
						//mapXymc.put(key,mapXymc.get(key)+",<br/>"+xymc);
						mapXymc.put(key,mapXymc.get(key)+","+xymc);
					}
				}else{
					mapXymc.put(key,xymc);
				}
				//�༶��¼
				if(mapBjNnion.get(key+bjdm) != null){
					mapBjNnion.put(key+bjdm, mapBjNnion.get(key+bjdm)+1);
				}else{
					mapBjNnion.put(key+bjdm,1);
				}
				
			}
		}
		
		jxjzNnion.add(mapNnion);
		jxjzNnion.add(mapRs);
		jxjzNnion.add(mapXymc);
		jxjzNnion.add(mapBjNnion);
		return jxjzNnion;
	}
	
	/**
	 * ��ѯ��������ͳ�Ʊ�
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRszjTjb(LsjzglFrom model) throws Exception{
		
		return dao.getRszjTjb(model);
	}
	
	/**
	 * ��ѯ����ѧ����Ϣ�б� ����ҳ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjzmdList(LsjzglFrom model)
			throws Exception {
		List<HashMap<String, String>> jxjzdjList = dao.getJxdjList(new JxjzglFrom());
		return dao.getJxjzmdList(model,jxjzdjList);
	}
	
	/**
	 * ������������
	 * @param outputStream
	 * @param model
	 * @throws Exception
	 */
	public void dcJzTjb(ServletOutputStream os, LsjzglFrom model,HashMap<String, String> title,
			List<HashMap<String, String>> jxjzList) throws Exception {
		// ����excel����
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧ������ͳ��", 0);
		
		if(model == null || jxjzList == null){
			ExcelMethods.submitWritableWorkbookOperations(wwb);
			return ;
		}
		List<HashMap<String, String>> jzdjList=dao.getJxJzdj(model);
		//��ȡ�ϲ�����ֵ����
		List<Object> jxjzNnion=setJxjzTjxNnionTable(jxjzList, jzdjList);
		HashMap<String, Integer> jzNnion=(HashMap<String, Integer>)jxjzNnion.get(0);//�ϲ�����
		HashMap<String, Integer> jzRs=(HashMap<String, Integer>)jxjzNnion.get(1);//����ͳ��
		HashMap<String, String> jzXymc=(HashMap<String, String>)jxjzNnion.get(2);//ѧԺ����
		HashMap<String, Integer> jzBjNnion=(HashMap<String, Integer>)jxjzNnion.get(3);//�༶���ƺϲ�
		
		//�ϲ�ʹ�ò���
		HashMap<String, String> unionMapMc=new HashMap<String, String>(); //���ڼ�¼��ͬ������
		HashMap<String, String> unionMapDm=new HashMap<String, String>(); //���ڼ�¼��ͬ�����
		
		String tjxx="";
		//���õ�Ԫ�����
		ws.setColumnView(3, 35);
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			WritableCellFormat titleStyle = ExcelMB.getWritableCellFormat(16,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			WritableCellFormat leftTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			// ����
			ws.mergeCells(0, 0, (jzdjList.size()*4+2), 0); 
			ws.addCell(new Label(0, 0,title.get("jxmc")+"ͳ���", titleStyle));
			tjxx=title.get("jxmc")+"(��ѵ����:"+title.get("cxrs")+",�ѱ�������:"+title.get("ybzrs")+",δ��������:"+title.get("wbzrs")+")";
			ws.mergeCells(0, 1, (jzdjList.size()*4+2), 1); 
			ws.addCell(new Label(0, 1,tjxx, wcfTytle));
			
			//����ͷ��
			int index=0;
			for (int i = 0; i < jzdjList.size(); i++) {
				//�ȼ�����
				ws.addCell(new Label(index, 2,jzdjList.get(i).get("djmc"), wcfTytle));
				index++;
				//�ȼ��̹�����
				ws.addCell(new Label(index, 2,jzdjList.get(i).get("djmc")+"��", wcfTytle));
				index++;
				//��ʦ����
				ws.addCell(new Label(index, 2,"��ʦ", wcfTytle));
				index++;
				//����
				ws.addCell(new Label(index, 2,"����", wcfTytle));
				index++;
				
				if(i==0){
					//ѧԺ
					Base.YXPZXY_KEY = message.getMessage("lable.xb");
					ws.addCell(new Label(index, 2,Base.YXPZXY_KEY, wcfTytle));
					index++;
				}
				
				if(i==(jzdjList.size()-1)){
					//�༶
					ws.addCell(new Label(index, 2,"�༶", wcfTytle));
					index++;
					//����
					ws.addCell(new Label(index, 2,"����", wcfTytle));
					index++;
				}
			}
			
			String jzmcBh="";
			String key="";
			String bjdm="";
			//��������
			for (int i = 0; i < jxjzList.size(); i++) {
				index=0;
				for (int j = 0; j < jzdjList.size(); j++) {
					jzmcBh="jzmc"+jzdjList.get(j).get("djdm");
					key=jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm"));
					bjdm=jxjzList.get(i).get("bjdm");
					
					if((unionMapMc.get(jzmcBh) == null || !unionMapMc.get(jzmcBh).equals(jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm"))))
							|| (unionMapDm.get(jzmcBh) == null || !unionMapDm.get(jzmcBh).equals(key))){
						//��������
						//��������
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1); 
						//ws.setRowView(0, 400); // ����ָ���и�
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm")), wcfTytle));
						index++;
						
						//�̹�����
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("jgmc"+jzdjList.get(j).get("djdm")), wcfTytle));
						index++;
						
						//��ʦ����
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("lsmc"+jzdjList.get(j).get("djdm")), wcfTytle));
						index++;
						
						//����
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
						ws.addCell(new Label(index, 3+i,String.valueOf(jzRs.get(key)), wcfTytle));
						index++;
						
						//ѧԺ
						if(j == 0){
							ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
							ws.addCell(new Label(index, 3+i,jzXymc.get(key), wcfTytle));
							index++;
						}
					}else{
						index=index+4;
						if(j == 0){
							index++;
						}
					}
						
					
					//�༶������
					if(j == (jzdjList.size()-1)){
						//�༶
						if(jzBjNnion.get(key+bjdm) != null){
							ws.mergeCells(index, 3+i, index, 3+i+jzBjNnion.get(key+bjdm)-1);  
							ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("bjmc"), wcfTytle));
						}
						index++;
						jzBjNnion.remove(key+bjdm);
						
						
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("jzrs")+"("+jxjzList.get(i).get("xb")+")", wcfTytle));
						index++;
					}
					
					//
					unionMapMc.put(jzmcBh, jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm")));
					unionMapDm.put(jzmcBh, key);
				}
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * ��ѵ���Ʊ���
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type){
		DAO da = DAO.getInstance();
		String[] en = new String[]{};
		String[] cn = new String[]{};
		if("whjzmd".equalsIgnoreCase(type)){
			en = new String[]{"", "xh", "xm","xb", "nj","xymc", "zymc","bjmc","treejzmc"};
			cn = new String[] { "", "ѧ��", "����","�Ա�", "�꼶","Ժϵ", "רҵ","�༶","���ڽ���"};
		}else if("jxjzxsxx".equalsIgnoreCase(type)){
			en = new String[]{ "", "xh", "xm","xb", "bjmc","treejzmc"};
			cn = new String[] { "", "ѧ��", "����","�Ա�", "���ڰ༶","���ڽ���"};
		}
		return da.arrayToList(en, cn);
		
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
		BasicService basicService=new BasicService();
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + basicService.replaceHtml(rs[0]) + "'/> ");
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
					html.append(basicService.replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	/**
	 * ��ѯ����ѧ����Ϣ�б�  �޷�ҳ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjzmdNoPageList(LsjzglFrom model) throws Exception{
		List<HashMap<String, String>> jxjzdjList = dao.getJxdjList(new JxjzglFrom());
		return dao.getJxjzmdNoPageList(model,jxjzdjList);
	}
	
	/**
	 * ������������
	 * @param outputStream
	 * @param model
	 * @throws Exception
	 */
	public void dcLsjzmd(ServletOutputStream os,List<HashMap<String, String>> lsjzmdList) throws Exception {
		// ����excel����
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ѵ����", 0);
		
		//���õ�Ԫ�����
		ws.setColumnView(3, 35);
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			String[] trTop=new String[] { "ѧ��", "����","�Ա�", "�꼶","Ժϵ", "רҵ","�༶","���ڽ���"};
			String[] trTopDm=new String[] { "xh", "xm","xb", "nj","xymc", "zymc","bjmc","treejzmc"};
			// ����
			for (int i = 0; i < trTop.length; i++) {
				ws.addCell(new Label(i, 0,trTop[i], wcfTytle));
			}
			
			//��������
			for (int i = 0; i < lsjzmdList.size(); i++) {
				for (int j = 0; j < trTopDm.length; j++) {
					ws.addCell(new Label(j, (i+1),lsjzmdList.get(i).get(trTopDm[j]), wcfTytle));
				}
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ������ʷ��ѵ����Html
	 * @return
	 */
	public String createLsjxjzHtml(HashMap<String, String> jxxxwhModel,HashMap<String, String> rs){
		StringBuffer html=new StringBuffer();
		html.append(" ��ѵ���ƣ�");
		html.append(jxxxwhModel.get("jxmc"));
		html.append("&nbsp;&nbsp;");
		html.append("��ѵ������");
		html.append("<a href=\"javascript:void(0);\" style=\"color: blue;text-decoration:underline;\" class=\"name\" onclick=\"cxJzjzmdPage()\">");
		html.append(rs.get("cxrs"));
		html.append("</a>");
		html.append("��&nbsp;&nbsp;");
		html.append("�ѱ���������");
		html.append("<a href=\"javascript:void(0);\" style=\"color: blue;text-decoration:underline;\" class=\"name\" onclick=\"cxJzjzmdPageYbz()\">");
		html.append(rs.get("ybzrs"));
		html.append("</a>");
		html.append("��&nbsp;&nbsp;");
		html.append("��δ����������");
		html.append("<a href=\"javascript:void(0);\" style=\"color: blue;text-decoration:underline;\" class=\"name\" onclick=\"cxJzjzmdPageWbz()\">");
		html.append(rs.get("wbzrs"));
		html.append("</a>");
		html.append("��");
		return html.toString();
	}
	
	/**
	 * ����Ĭ�ϲ�ѯ����
	 * @return
	 */
	public SearchModel setDefaultSearchModel(String jzid,String bjdm) throws Exception{
		List<HashMap<String, String>> jxjzdjList = dao.getJxdjList(new JxjzglFrom());
		HashMap<String, String> jxjzModel= dao.getJxjzModelByJzid(jxjzdjList,jzid);
		SearchModel searchModel = new SearchModel();
		
		if(jxjzModel !=null){
			if(jxjzModel.get("tid") !=null && !"".equals(jxjzModel.get("tid"))){
				searchModel.setSearch_tj_tid(new String[]{jxjzModel.get("tid")});
			}
			
			if(jxjzModel.get("yid") !=null && !"".equals(jxjzModel.get("yid"))){
				searchModel.setSearch_tj_yid(new String[]{jxjzModel.get("yid")});
			}
			
			if(jxjzModel.get("lid") !=null && !"".equals(jxjzModel.get("lid"))){
				searchModel.setSearch_tj_lid(new String[]{jxjzModel.get("lid")});
			}
			
			if(jxjzModel.get("pid") !=null && !"".equals(jxjzModel.get("pid"))){
				searchModel.setSearch_tj_pid(new String[]{jxjzModel.get("pid")});
			}
			
			if(jxjzModel.get("bid") !=null && !"".equals(jxjzModel.get("bid"))){
				searchModel.setSearch_tj_bid(new String[]{jxjzModel.get("bid")});
			}
			
			if(jxjzModel.get("ssid") !=null && !"".equals(jxjzModel.get("ssid"))){
				searchModel.setSearch_tj_ssid(new String[]{jxjzModel.get("ssid")});
			}
		}
		
		if(bjdm !=null && !"".equals(bjdm)){
			searchModel.setSearch_tj_bj(new String[]{bjdm});
		}
		
		return searchModel;
	}
	
	/**
	 * ��ʷ���ƹ����Զ��嵼��
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJxjzmdExportDataList(LsjzglFrom model)
	throws Exception {
		
		List<HashMap<String, String>> jxjzdjList = dao.getJxdjList(new JxjzglFrom());
		return dao.getJxjzmdExportDataList(model,jxjzdjList);
		
	}
}