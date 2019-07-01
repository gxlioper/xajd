package xsgzgl.wjcf.general.cfsbgl;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.inter.WjcfCfsbInterface;

/**
 * 
* 
* �����ƣ�WjcfCfsbAction 
* ��������Υ�ʹ��ִ����ϱ�Service
* �����ˣ�xucy 
* ����ʱ�䣺2012-6-20 ����01:18:00 
* �޸ı�ע��  
* @version 
*
 */
public class WjcfCfsbService extends CommService implements WjcfCfsbInterface{
	
	private WjcfCfsbDao dao = new WjcfCfsbDao();

	/**
	 *����html
	 * @param model
	 * @return
	 *
	 */
	public String createWjcfCfsbHTML(SearchRsModel rsModel,
			WjcfCfsbModel model, ArrayList<String[]> rsArrList, User user) {

		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" >");

				html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");

				
				html.append("<input type='checkbox' name='primarykey_checkVal' ");
				
				
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + rs[0] + "'/> ");

				html.append("</td>");

				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					if(j==1){
						html.append(" style=\"width:10%\"");
					}
					if(j==2){
						html.append(" style=\"width:5%\"");
					}
					if(j==3){
						html.append(" style=\"width:10%\"");
					}
					if(j==4){
						html.append(" style=\"width:10%\"");
					}
					if(j==5){
						html.append(" style=\"width:10%\"");
					}if(j==6){
						html.append(" style=\"width:10%\"");
					}
					if(j==7){
						html.append(" style=\"width:10%\"");
					}
					if(j==8){
						html.append(" style=\"width:10%\"");
					}
					html.append(">");
					
					html.append(rs[j]);
					
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 *������ͷ
	 * @param model
	 * @return
	 *
	 */
	public List<HashMap<String, String>> getWjcfCfsbTop(WjcfCfsbModel model, User user) {
		String[] en = new String[] {"pk" ,"xn","xq","xh", "xm", "cflb","cfyy","cfwh","cfsj","sbr" ,"shjg"  };
		String[] cn = new String[] { "","ѧ��", "ѧ��","ѧ��", "����", "�������","����ԭ��","�����ĺ�","����ʱ��","�ϱ���","��˽��" };
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		return topTr;
	}

	/**
	 *��ѯΥ�ʹ����б�
	 * @param model
	 * @return
	 *
	 */
	public ArrayList<String[]> getWjcfCfsbfList(WjcfGeneralForm myForm,
			WjcfCfsbModel model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getWjcfList(myForm, model,user);
	}
	
	/**
	 * ������   ��ʽ���ļ�·����s   
	 * @param fileName
	 * @return
	 */
	public String formatFileName(String url){
		if(url==null || "".equals(url)){
			return "";
		}
		String fileName=url;
		if(url.lastIndexOf("\\") > -1){
			fileName=url.substring(url.lastIndexOf("\\")+1, url.length());
		}
		return fileName;
	}
	
	/**
	 * ������  ȷ���ļ���ʽ��
	 * @param fileName
	 * @return
	 */
	public String isCriterionFile(String fileName){
		if(fileName==null || "".equals(fileName)){
			return "";
		}
		String[] suffix=new String[]{"doc","xls","rar","pdf","txt"};
		for (int i = 0; i < suffix.length; i++) {
			if(fileName.lastIndexOf(suffix[i]) == -1){
				//��ʽ���ϸ�
				return fileName;
			}
		}
		return "";
	}
	
	/**
	 *��������
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String saveCfsb(WjcfGeneralForm form,User user) throws Exception{
		form.setSbb(user.getUserName());
		form.setSbsj(GetTime.getNowTime2());
		form.setXn(Base.currXn);
		form.setXq(Base.currXq);
		InputStream instream = form.getFj().getInputStream();
		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc()
				.substring(form.getFjmc().lastIndexOf("\\")+1,
						form.getFjmc().length()) : "";
		form.setFjmc(fjmc);
		return dao.saveCfsb(form,instream);
	}
	
	/**
	 *�޸ı���
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean updateCfsb(WjcfGeneralForm form,User user,String cflbdmXt) throws Exception{
		form.setSbb(user.getUserName());
		form.setSbsj(GetTime.getNowTime2());
		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc()
				.substring(form.getFjmc().lastIndexOf("\\")+1,
						form.getFjmc().length()) : "";
		form.setFjmc(fjmc);
		boolean flag =  dao.updateCfsb(form,cflbdmXt);
		return flag;
	}

	/**
	 *��ѯһ����¼
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public HashMap<String, String> getCfsb(String cfid) throws Exception {
		return dao.getCfsb(cfid);
	}

	/**
	 * ��ѯѧ������Υ�ʹ����б�ȫ����¼��
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getYscfqk(String xh){
		return dao.getYscfqk(xh);
	}
	/**
	 * ��ѯѧ������Υ�ʹ����б�����������¼��
	 */
	public List<HashMap<String,String>> getYscfqkNotId(String xh, String cfid){
		return dao.getYscfqkNotId(xh, cfid);
	}
	
	/**
	 * ��ѯΥ�ʹ�������б�
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getCfsh(String xh){
		return dao.getCfsh(xh);
	}

	/**
	 *ɾ��Υ�ʹ����ϱ�
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean delWjsb(String[] pk) {
		List<String[]> params = new ArrayList<String[]>();		
		for(String str : pk){
			params.add(new String[]{str});
		}
		return dao.delWjsb(params);
	}
	

	public List<HashMap<String, String>> getCfshxxList(String cfid) {
		// TODO �Զ����ɷ������
		return dao.getCfshxx(cfid);
	}
	
	/**
	 * ��ѯ������Ϣ
	 * @param form
	 * @return
	 */
	public InputStream fjCx(WjcfGeneralForm form) throws Exception {
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfsbb where cfid = ?", new String[]{form.getCfId()}, "fj");
		return blob.getBinaryStream();
	}

	public String checkCfsb(WjcfGeneralForm model) {
		// TODO �Զ����ɷ������
		return dao.checkCfsb(model);
	}

	public String[] getSpgwByCflb(String cflbdm) {
		try {
			return dao.getSpgwByCflb(cflbdm);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// �Զ��嵼������
	public List<HashMap<String,String>> getWjcfCfsbfExportList(WjcfGeneralForm myForm, User user) throws IllegalArgumentException,
	SecurityException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException{
		
		return dao.getWjcfCfsbfExportList(myForm,user);
	}
}
