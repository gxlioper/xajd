/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:01:26 
 */
package com.zfsoft.xgxt.xsxx.xsxxcj;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(ѧ����Ϣ�ɼ�--���ְҵ��ѧ)
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-7-30 ����04:01:26
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XsxxcjService extends SuperServiceImpl<XsxxcjForm, XsxxcjDao> {
	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	private XsxxcjDao dao = new XsxxcjDao();

	public XsxxcjService() {
		super.setDao(dao);
	}

	/** 
	 * @����:TODO(��ȡѧ���б�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-1 ����05:55:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsPageList(XsxxcjForm model,
			User user) throws Exception{
		// TODO �Զ����ɷ������
		return dao.getXsPageList(model,user);
	}

	/** 
	 * @����:TODO(��ѯѧ��������Ϣͳ�ơ�̨�ˡ�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-2 ����10:35:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<String[]> getXsjbxxtzList(XsxxcjForm model,
			User user) throws Exception{
		// TODO �Զ����ɷ������
		return dao.getXsjbxxtzList(model,user);
	}

	/**
	 * @param title2 
	 * @param file  
	 * @����:TODO(����ѧ��������Ϣͳ�ơ�̨�ˡ�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-2 ����11:22:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param response
	 * @param resultList
	 * void �������� 
	 * @throws 
	 */
	public File exportXsjbxxtz(HttpServletResponse response,
			List<String[]> resultList, File file, String[] title) throws Exception{
		// TODO �Զ����ɷ������
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		response.reset();
		FileOutputStream stream = new FileOutputStream(file);
		WritableWorkbook wwb = Workbook.createWorkbook(stream);
		WritableSheet sheet = wwb.createSheet("��������", 0);
		sheet.mergeCells(0, 0, 0, 1);
		sheet.addCell(new Label(0,0,Base.YXPZXY_KEY));
		sheet.mergeCells(1, 0, 1, 1);
		sheet.addCell(new Label(1,0,"�꼶"));
		for(int i=0;i<title.length;i++){
			sheet.mergeCells(2+3*i, 0, 4+3*i, 0);
			sheet.addCell(new Label(2+3*i,0,title[i]));
		}
		for(int i=0;i<3*title.length;i++){
			sheet.addCell(new Label(2+i,1,"��"));
			sheet.addCell(new Label(3+i,1,"Ů"));
			sheet.addCell(new Label(4+i,1,"����"));
			i+=2;
		}
		for(int i=0;i<resultList.size();i++){
			for(int j=0;j<resultList.get(i).length;j++){
				sheet.addCell(new Label(j,i+2,resultList.get(i)[j]));
			}
		}
		wwb.write();
		wwb.close();
		return file;
		
	}

	/** 
	 * @����:TODO(��ѯѧ��������Ϣͳ�ơ�̨�ˡ�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-2 ����02:04:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<String[]> �������� 
	 * @throws 
	 */
	public List<String[]> exportXsknxxtz(XsxxcjForm model, User user) throws Exception{
		// TODO �Զ����ɷ������
		return dao.exportXsknxxtz(model,user);
	}
}
