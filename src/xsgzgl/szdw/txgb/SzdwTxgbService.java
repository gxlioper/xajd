package xsgzgl.szdw.txgb;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.xszz.whtl.ybbx.XszzYbbxService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-14 ����03:45:48</p>
 */
public class SzdwTxgbService extends BasicService{
	
	SzdwTxgbDAO dao=new SzdwTxgbDAO();
	
	/**
	 * ���ý����ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String,String>>getTopTr(){
		
		DAO dao=DAO.getInstance();
		
		String[]en=new String[]{"pkValue","xn","xh","xm","nj","bjmc","zzmc","gbmc","zzjb"};
		
		String[]cn=new String[]{"","ѧ��","ѧ��","����","�꼶","�༶","��֯����","�ɲ�����","��֯����"};
		
		return  dao.arrayToList(en, cn);
	}
	
	/**
	 * ��ʼ�������ѯ��Ϣ
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initTxgbManage(RequestForm rForm,
			HttpServletRequest request) {
		
		DAO dao=DAO.getInstance();
		// ����ģ��
		String gnmk = "szdw";
		// ����·��
		String path ="szdw_xsgb_txgb.do";
		
		// ========================����ֶ� begin=========================
		
		// �ֶ���
		String[]en=new String[]{"pkValue","xn","xh","xm","nj","bjmc","zzmc","gbmc","zzjb"};
		// ������
		String[]cn=new String[]{"","ѧ��","ѧ��","����","�꼶","�༶","��֯����","�ɲ�����","��֯����"};
		// ��ͷ
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		// ========================����ֶ� end=========================

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ===============ͨ������ end ============
		
		rForm.setQtzdz(new String[]{});//������������

		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(en);
		rForm.setTopTr(topTr);
		rForm.setSearch("true");
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ȡ��������ճ̹�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getTxgbList(SzdwTxgbForm model) throws Exception{
		
		return dao.getTxgbList(model);
	}
	
	/**
	 * ���������
	 * 
	 * @author qlj
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[1]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-2)+"%\" ");
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
	 * ������Ϣ��ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initSave(BasicModel basicModel,
			HttpServletRequest request) {

		// �����ֶ�
		String []save={"xn","xh","zzmc","zzjb","gbmc","bz"};
		
		// --------------����------------
		basicModel.setTableName("xg_szdw_txgbb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * �޸���Ϣ��ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initModi(BasicModel basicModel,
			HttpServletRequest request) {

		String []save={"xn","xh","zzmc","zzjb","gbmc","bz"};
		String []pk={"xn","xh","zzmc","zzjb","gbmc"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_szdw_txgbb");
		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
	
	public HashMap<String,String>getTxgbMap(SzdwTxgbForm model){
		
		return dao.getTxgbMap(model);
		
	}
	
}
