package xsgzgl.jxgl.general.jxxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

/**
 * ��ѵ����-������Ϣά��-��ѵ��Ϣά��
 * @author yeyipin
 * @since 2012.10.10
 */
public class JxglJxxxwhService extends BasicService{
	private final String JXZT_START="start";
	JxglJxxxwhDao jxglJxxxwhDao = new JxglJxxxwhDao();
	/**
	 * ��ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		String[] en = new String[] { "", "jxmc", "cjnj", "kssj", "jssj", "cjrs", "hxrs","zt" };
		String[] cn = new String[] { "", "��ѵ����", "�μ��꼶", "��ʼʱ��", "����ʱ��", "��ѵ����", "��ѵ����","״̬"};
		if("jxmd".equalsIgnoreCase(type)){
			en = new String[]{"", "xh", "xm", "nj","xymc", "zymc","bjmc","cxqk"};
			cn = new String[] { "", "ѧ��", "����", "�꼶","Ժϵ", "רҵ","�༶","��ѵ���"};
		}
		return jxglJxxxwhDao.arrayToList(en, cn);
	}
	
	/**
	 * ��ѵ��Ϣ��ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> jxxxCx(JxglJxxxwhForm model) throws Exception {
		return jxglJxxxwhDao.jxxxCx(model);
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
					if(j==rs.length-1){
						String color = "����".equalsIgnoreCase(replaceHtml(rs[j]))?"blue":"red";
						html.append("<font color='"+color+"'>");
						html.append(replaceHtml(rs[j]));
						html.append("</font>");
					}else{
						html.append(replaceHtml(rs[j]));
					}
					html.append("</td>");
				}
				
				
				/*html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append("><a href='jxgl_jxxxwh.do?method=jxmdCx&pkValue="+replaceHtml(rs[0])+"&cxqk=cx'><font class='name'>");
				html.append(replaceHtml(rs[rs.length-3]));
				html.append("</font></a></td>");
				
				
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append("><a href='jxgl_jxxxwh.do?method=jxmdCx&pkValue="+replaceHtml(rs[0])+"&cxqk=hx'><font class='name'>");
				html.append(replaceHtml(rs[rs.length-2]));
				html.append("</font></a></td>");*/
				
				
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				String color = "����".equalsIgnoreCase(replaceHtml(rs[rs.length-1]))?"have":"non";
				html.append("<font class='"+color+"'>");
				html.append(replaceHtml(rs[rs.length-1]));
				html.append("</font></td>");
				
				html.append("</tr>");
			}
		}
		return html.toString();
	}


	
	
	/**
	 * ��òμ��꼶�б�
	 * @return
	 */
	public List<HashMap<String, String>> getCjnj() {
		return jxglJxxxwhDao.getCjnj();
	}
	
	/**
	 * ��ý��Ƶȼ��б�
	 * @return
	 */
	public List<HashMap<String, String>> getJzdj() {
		return jxglJxxxwhDao.getJzdj();
	}
	
	/**
	 * ���½��Ƶȼ� 
	 * @param sql
	 * @return
	 */
	public boolean serv_updateJzdj(String sql) {
		boolean result = false; 
		try {
			result = jxglJxxxwhDao.dao_updateJzdj(sql);
		} catch (Exception e) {
			
		}
		return result;
	}
	
	public int[] serv_batchUpdateJzdj(String[] sql) {
		try{
			return jxglJxxxwhDao.dao_batchUpdateJzdj(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��֤��ѵ��Ϣ true��ͨ�� false: ��ͨ��
	 * @param model
	 * @return
	 */
	public boolean checkJxxx(JxglJxxxwhForm model) {
		boolean flag = false;
		String num = jxglJxxxwhDao.getCount(model);
		if("0".equalsIgnoreCase(num)){
			flag = true;
		}
		return flag;
	}

	
	/**
	 * ��ѵ��Ϣ����(���ӣ�����)
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String jxxxSave(JxglJxxxwhForm model) throws Exception {
		boolean flag = false;
		//����
		if(model.getJxid()==null || "".equalsIgnoreCase(model.getJxid())){
			//���һ��GUID
			model.setJxid(jxglJxxxwhDao.getGuid());
			flag = jxglJxxxwhDao.insertJxxx(model);
		//����
		}else{
			flag = jxglJxxxwhDao.updateJxxx(model);
		}
		//�������ɹ����Ҵ˾�ѵ״̬Ϊ�����С�����������ѵ״̬��Ϊ��ֹͣ��
		if(flag && "start".equalsIgnoreCase(model.getJxzt())){
			flag = jxglJxxxwhDao.stopJxxx(model);
		}
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * ��þ�ѵ��Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxx(JxglJxxxwhForm model) {
		return jxglJxxxwhDao.getJxxx(model);
	}
	
	/**
	 * ��þ�ѵ��Ϣ�����ɸ�����
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxxView(JxglJxxxwhForm model) {
		return jxglJxxxwhDao.getJxxxView(model);
	}
	
	/**
	 * ��ѵ��Ϣɾ����֤
	 * @param model
	 * @return
	 */
	public boolean checkScJxxx(JxglJxxxwhForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0; i < pkValue.length; i++){
			model.setJxid(pkValue[i]);
			String num = jxglJxxxwhDao.getCountJz(model);
			if(!"0".equalsIgnoreCase(num)){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * ����ɾ����ѵ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String jxxxSc(JxglJxxxwhForm model) throws Exception {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{pkValue[i]});
		}
		return jxglJxxxwhDao.jxxxSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}

	
	/**
	 * ��ѵ��Ϣ����
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String jxxxCz(JxglJxxxwhForm model) throws Exception {
		boolean flag = jxglJxxxwhDao.jxxxCz(model);
		//��������ɹ����Ҵ˾�ѵ״̬Ϊ�����С�����������ѵ״̬��Ϊ��ֹͣ��
		if(flag && "start".equalsIgnoreCase(model.getJxzt())){
			flag = jxglJxxxwhDao.stopJxxx(model);
		}
		return flag?MessageInfo.MESSAGE_WORK_SUCCESS:MessageInfo.MESSAGE_WORK_FALSE;
	}

	
	/**
	 * ��ѵ������ѯ
	 * @param model
	 * @param request 
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> jxmdCx(JxglJxxxwhForm model, HttpServletRequest request) throws Exception {
		return jxglJxxxwhDao.jxmdCx(model,request);
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
	 * ��þ�ѵ����
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxrs(JxglJxxxwhForm model) {
		return jxglJxxxwhDao.getJxrs(model);
	}

	
	/**
	 * ���ɾ�ѵ����
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String scJxmd(JxglJxxxwhForm model) throws Exception {
		boolean flag = false;
		flag = jxglJxxxwhDao.insertJxmd(model);
		if(flag&&"yes".equalsIgnoreCase(model.getSfhx())){
			model.setCxqk("hx");
			flag = jxglJxxxwhDao.copyJxmd(model);
		}
		if(flag&&"yes".equalsIgnoreCase(model.getSfmx())){
			model.setCxqk("mx");
			flag = jxglJxxxwhDao.copyJxmd(model);
		}
		
		return flag?MessageInfo.MESSAGE_DO_SUCCESS:MessageInfo.MESSAGE_DO_FALSE;
	}
	
	
	
	/**
	 * ��ѵ����������֤
	 * @param model
	 * @return
	 */
	public boolean checkScJxmd(JxglJxxxwhForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0; i < pkValue.length; i++){
			model.setPkValue(pkValue[i]);
			String num = jxglJxxxwhDao.getCountMd(model);
			if(!"0".equalsIgnoreCase(num)){
				return false;
			}
		}
		return true;
	}
	
	
	
	/**
	 * ��ѵ����ɾ��
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String jxmdSc(JxglJxxxwhForm model) throws Exception {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{pkValue[i]});
		}
		return jxglJxxxwhDao.jxmdSc(params)?MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}

	
	/**
	 * ��ѵ�������(��ѵ����ѵ����ѵ)
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String cxqkCz(JxglJxxxwhForm model) throws Exception {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String cxqk = model.getCxqk();
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{cxqk,pkValue[i]});
		}
		return jxglJxxxwhDao.cxqkCz(params)?MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}

	/**
	 * ��ȡ��ǰ��ѵ��Ϣά��model   yjd
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxxwhModel(){
		JxglJxxxwhForm model=new JxglJxxxwhForm();
		model.setJxzt(JXZT_START);
		return jxglJxxxwhDao.getJxxxwhModel(model);
	}
	
	/**
	 * ��ȡ��ѵ��Ϣ   yjd
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxxxList(){
		return jxglJxxxwhDao.getJxxxList();
	}
	
	/**
	 * ��ȡ��ѵ��Ϣ   yjd
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxxwhByJxidModel(JxglJxxxwhForm model){
		if(model == null){
			return null;
		}
		return jxglJxxxwhDao.getJxxxwhByJxidModel(model);
	}
    
	/**
	 * ��ѵ������ѯ
	 * @param model
	 * @param request 
	 * @return
	 */
	public List<HashMap<String,String>> getAlljxmdCx(JxglJxxxwhForm model, HttpServletRequest request) throws Exception{
		return jxglJxxxwhDao.getAlljxmdCx(model, request);
	}
	/**
	 * 
	 * @����:��ȡ���õľ�ѵ��Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-8 ����11:17:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getQyJxxx() {
		return jxglJxxxwhDao.getQyJxxx();
	}

}
