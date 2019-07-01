/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-23 ����09:58:15 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfhz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ۺ����ʷֹ���
 * @�๦������: �ۺ���������ѧ�ֻ��ܱ� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-23 ����09:58:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfhzService extends SuperServiceImpl<ZhfhzForm, ZhfhzDao> {
	
	/**
	 * 
	 * @����: ѧ��������Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-23 ����10:33:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsxx(String xh){
		return dao.getXsxx(xh);		
	}
	
	public List<HashMap<String, String>> getMkxmList(String xh) {
		return dao.getMkxmList(xh);
	}
	
	public HashMap<String,String> getZfs(String xh){
		return dao.getZfs(xh);
	}

	/** 
	 * @����:ѧ�ţ�ģ�飺ģ���ܷ�
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-4-5 ����02:22:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param userName
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getmkzf(String userName) {
		// TODO �Զ����ɷ������
		return dao.getmkzf(userName);
	}

	/** 
	 * @����:����ģ�����Ŀ��Ϣ
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-4-5 ����04:24:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param userName
	 * @param object
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getsingleMkXmlist(String userName,
			String  mkmc) {
		// TODO �Զ����ɷ������
		return dao.getsingleMkXmlist(userName,mkmc );
	}

	/**
	 * @param userName 
	 * @param mkzflist  
	 * @����:ƴ������Ϣhtml
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-4-6 ����09:36:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getHzxxHtml(String userName, List<HashMap<String, String>> mkzflist) {
		String html ="";
		  for (int i = 0; i < mkzflist.size(); i++) {
			  if ("��������".equals(mkzflist.get(i).get("xmmkmc"))) {
				html+="<tr align='center'><td colspan='6'><font style='font-weight:bold;'>"+mkzflist.get(i).get("xmmkmc")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+mkzflist.get(i).get("mkzf")+"</font></td></tr>";
			}else {
				html+="<tr align='center'><td colspan='6'><font style='font-weight:bold;'>"+mkzflist.get(i).get("xmmkmc")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+mkzflist.get(i).get("mkzf")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+mkzflist.get(i).get("sfhg")+"</font></td></tr>";
			}
			  List<HashMap<String, String>> Xmlist = this.getsingleMkXmlist(userName,mkzflist.get(i).get("xmmkmc"));
			  html+="<tr align='center'>" +
			  		"<td width='15%' value='0'><font style='font-weight:bold;'>�Ʒ���Ŀ</font></td>" +
			  		"<td width='5%' value='0'><font style='font-weight:bold;'>�÷�</font></td>" +
			  		"<td width='15%'><font style='font-weight:bold;'>���</font></td>" +
					"<td width='20%'><font style='font-weight:bold;'>��������</font></td>" +
					"<td width='6%'><font style='font-weight:bold;'>�����</font></td>" +
					"<td width='12%'><font style='font-weight:bold;'>����/���ʱ��</font></td></tr>";
		         for (int j = 0; j < Xmlist.size(); j++) {
		        	html+="<tr align='center'>"+
		        	"<td width='15%' value='"+Xmlist.get(j).get("jfxmmc")+"' align='left'>"+Xmlist.get(j).get("jfxmmc")+"</td>"+
		        	"<td width='5%' value='"+Xmlist.get(j).get("jfxmdm")+Xmlist.get(j).get("xmzf")+"'>"+Xmlist.get(j).get("xmzf")+"</td>"+
						"<td width='15%'>"+Xmlist.get(j).get("lb")+"</td>"+
						"<td width='20%'>"+Xmlist.get(j).get("sxsm")+"</td>"+
						"<td width='6%'>"+Xmlist.get(j).get("sxf")+"</td>"+
						"<td width='12%'>"+Xmlist.get(j).get("cysj")+"</td></tr>";
		         }
		  }
		return html;
	}
	
}
