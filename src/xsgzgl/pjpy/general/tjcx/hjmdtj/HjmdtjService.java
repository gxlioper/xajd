package xsgzgl.pjpy.general.tjcx.hjmdtj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-28 ����14:19:22
 * </p>
 */

public class HjmdtjService extends BasicService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	/**
	 * ������ͳ����ҳͷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] {"xh","xm","sfzh","nj","xymc","zymc","bjmc","xb","mzmc","rxrq","xmmc","xmje","yhkh"};
		String[] cn = new String[] {"ѧ��","����","���֤","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","�Ա�","����","��ѧ����","����Ŀ","���","����"};
		return dao.arrayToList(en, cn);
	}
	

	/**
	 * ������ͳ����ҳ���ݲ�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getHjmdtjCx(HjmdtjForm myForm) throws Exception {
		HjmdtjDAO dao = new HjmdtjDAO();
		return dao.getHjmdtjCx(myForm);
	}
	
	/**
	 * ������ͳ����ҳ���ݲ�ѯ(�ϼ�)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String getHjmdtjCxAll(HjmdtjForm myForm) throws Exception {
		HjmdtjDAO dao = new HjmdtjDAO();
		return dao.getHjmdtjCxAll(myForm);
	}

	/**
	 * ������ͳ����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTML(HjmdtjForm myForm, SearchRsModel rsModel,
			ArrayList<String[]> rsArrList,String title, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		//html.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"+title+"</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		if (rsArrList != null && rsArrList.size() > 0) {
			//html.append("<thead align=\"center\"><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">ѧ��</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">����</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���֤</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">רҵ</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">�Ա�</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">����</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">��ѧ����</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">����Ŀ</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���</td><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">���п���</td></thead></tr>");
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length) + "%\" ");
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
	 * ������ͳ����ҳ���ݵ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void expHjmdtj(ServletOutputStream os, HjmdtjForm myForm,String xmmcrs) throws Exception {
		HjmdtjDAO dao = new HjmdtjDAO();
		String title = xmmcrs;
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// �̶���
		String[] gdName = new String[] {"ѧ��","����","���֤","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","�Ա�","����","��ѧ����","����Ŀ","���","����"};
		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// ��������Ĺ̶�����
		
		//------2013-3-22 edit--qph--begin---------------
		//toԭ���ߣ��������ݵ���ѯ���ݽ�����ķ�ʽ���ޣ�����ҳ������Ĳ����ˡ�
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		//------2013-3-22 edit---qph--end--------------
		
		ArrayList<String[]> gdlist = dao.getHjmdtjCx(myForm);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat wcFormate=new WritableCellFormat();
		Alignment alignMent = Alignment.CENTRE;
		VerticalAlignment vag = VerticalAlignment.CENTRE;
		//��ʽ
		wcFormate.setVerticalAlignment(vag);
		wcFormate.setAlignment(alignMent);
		//����
		WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		wfTytle.setBoldStyle(WritableFont.BOLD);
		wfTytle.setPointSize(16);
		wcFormate.setFont(wfTytle);
		WritableSheet ws = wwb.createSheet(title, 0);
		ws.mergeCells(0, 0,gdName.length-1,0 );
		ws.addCell(new Label(0, 0, title, wcFormate));
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, map.get("cn"), wcf2));
			}
		}
		// �������
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 2, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ���������ʷ��Ϣ��Ĭ�ϵ�һ����Ϊ�״β�ѯ��������������Ŀ
	 * @return
	 */
	public String getPjlsxmMrtj(){
		HjmdtjDAO dao = new HjmdtjDAO();
		return dao.getPjlsxmMrtj();
	}
	
	/**
	 * ���������ʷ��Ϣ��Ĭ�ϵ�һ����Ϊ�״β�ѯ������������ʱ��
	 * @return
	 */
	public String getPjzqMrtj(){
		HjmdtjDAO dao = new HjmdtjDAO();
		return dao.getPjzqMrtj();
	}
}