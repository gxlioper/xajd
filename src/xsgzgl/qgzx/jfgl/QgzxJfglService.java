package xsgzgl.qgzx.jfgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.comm.BasicService;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;
/**
 * �ڹ���ѧ-�ڹ����ѹ���-������Ϣ����
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJfglService extends BasicService{
	QgzxJfglDAO myDao = new QgzxJfglDAO();
	/**
	 * �ṩĳ����ĳ���ʣ������ܾ��ѽӿڹ�����ģ��ʹ��
	 * @param nd
	 * @param yrdwdm
	 * @return
	 */
	public String getBmNdSyje(String nd, String yrdwdm){
		return myDao.getBmNdSyje(nd,yrdwdm);
	}
	/**
	 * ��ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[]{};
		String[] cn = new String[] {};
		if ("12309".equals(Base.xxdm)) {
			 en = new String[] { "", "r", "nd","yrdwmc","hbzje","jfhbje","wcsysyje" };
			 cn = new String[] { "", "�к�", "���", "���˲���", "�����ܽ��<Ԫ>", "�ѷ��Ž��<Ԫ>", "ʣ����<Ԫ>" };
		}else {
			en = new String[] { "", "r", "nd","yrdwmc","hbzje","yffje","syje" };
			cn = new String[] { "", "�к�", "���", "���˲���", "�����ܽ��<Ԫ>", "�ѷ��Ž��<Ԫ>", "ʣ����<Ԫ>" };
		}
		//�㽭��ְͨҵ����ѧԺ���Ի�
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			cn = new String[] { "", "�к�", "����", "���˲���", "�����ܽ��<Ԫ>", "�ѷ��Ž��<Ԫ>", "ʣ����<Ԫ>" };
		}
		return dao.arrayToList(en, cn);
	}
	/**
	 * ��þ�����ϢList
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJfxx(QgzxJfglForm myForm) throws Exception {
		return myDao.getJfxx(myForm);
	}
	/**
	 * 
	 * @����: �Ƿ���ƻ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-24 ����03:18:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 */
	public boolean isKzHbjs(){
		QgzxCsszService qcs=new QgzxCsszService();
		HashMap<String, String> hm=qcs.getCssz();
		String sfjfhb=hm.get("sfjfhb");
		//��𷢷Ų��ܾ��ѻ���Լ��
		if("no".equals(sfjfhb)){
			return false;
		}
		return true;
	}
	public Map<String, String> getJfxx(String yrdwdm, String nd) {
		return myDao.getJfxx(yrdwdm, nd);
	}
	/**
	 * ����HTML��ѯҳ��
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
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"10px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(replaceHtml(rs[1]));
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 2; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
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
	 * ������Ϣ����
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String jfxxBc(QgzxJfglForm myForm) throws Exception {
		boolean flag = false;
		String nd = myForm.getNd();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String[] bm= myForm.getBm().split("!!@@!!");
		String[] hbsj = myForm.getHbsj().split("!!@@!!");
		String[] hbje = myForm.getHbje().split("!!@@!!");
		String[] bz = myForm.getBz().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < bm.length;i++){
			String[] el = {xn,xq,nd,bm[i],hbsj[i],hbje[i],bz[i]};
			params.add(el);
		}
		flag = myDao.jfxxBc(params);
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	public String jfxxInit(QgzxJfglForm myForm) throws Exception {
		boolean flag = false;
		String nd = myForm.getNd();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String[] bm= myForm.getBm().split("!!@@!!");
		String[] hbsj = myForm.getHbsj().split("!!@@!!");
		String[] hbje = myForm.getHbje().split("!!@@!!");
		String[] bz = myForm.getBz().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < bm.length;i++){
			for(int j = 1; j <= 12; j++){
				String ny =nd+"-"+String.format("%02d", j);
				String[] el = {xn,xq,ny,bm[i],hbsj[i],hbje[i],bz[i]};
				params.add(el);
			}
		}
		flag = myDao.jfxxBc(params);
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * �޸ı���
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String jfxxXg(QgzxJfglForm myForm) throws Exception {
		boolean flag = false;
		String nd = myForm.getNd();
		String bm = myForm.getBm();
		String[] hbsj = myForm.getHbsj().split("!!@@!!");
		String[] hbje = myForm.getHbje().split("!!@@!!");
		String[] bz = myForm.getBz().split("!!@@!!");
		String[] xn = myForm.getXns();
		String[] xq = myForm.getXqs();
		List<String[]> params = new ArrayList<String[]>();
		if(!Base.isNull(myForm.getHbsj())&&!"".equals(myForm.getHbsj())){
			for(int i = 0; i < hbsj.length;i++){
				String forxn = "";
				String forxq = "";
				if(xn != null && xn.length == hbsj.length){
					forxn = xn[i];
				}
				if(xq != null && xq.length == hbsj.length){
					forxq = xq[i];
				}
				String[] el = {forxn,forxq,nd,bm,hbsj[i],hbje[i],bz[i]};
				params.add(el);
			}
			flag = myDao.jfxxDel(myForm);
			if(flag){
				flag = myDao.jfxxSave(params);
			}
		}else{
			flag = myDao.jfxxDel(myForm);
		}
		return flag?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * ��ò���List
	 * @return
	 */
	public List<HashMap<String, String>> getBm() {
		return myDao.getBm();
	}
	public List<HashMap<String, String>> getBms(String xn,String xq) {
		return myDao.getBms(xn,xq);
	}
	public List<HashMap<String, String>> getBm(String xn,String nd) {
		return myDao.getBm(xn,nd);
	}
	public HashMap<String, String> getGwxx(String xn,String yrdwdm,String xq) {
		return myDao.getGwxx(xn,yrdwdm,xq);
	}
	/**
	 * ��þ�����ϢMap
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getJfxxMap(QgzxJfglForm myForm) {
		HashMap<String,String> rs = new HashMap<String,String>();
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		myForm.setNd(pkValue[0]);
		myForm.setBm(pkValue[1]);
		//���
		rs.put("nd", pkValue[0]);
		rs.put("bm", pkValue[1]);
		rs.put("bmmc",getBmmc(pkValue[1]));
//		List<HashMap<String,String>> list = myDao.getJfxxList(myForm);
//		String rsList = getHtml(myForm,list);
//		rs.put("rsList", rsList);
		return rs;
	}
	/**
	 * ��ò�������
	 * @param bm
	 * @return
	 */
	private String getBmmc(String bm) {
		return myDao.getBmmc(bm);
	}
	/**
	 * ����������Ϣҳ��
	 * @param myForm
	 * @param list
	 * @return
	 */
	private String getHtml(QgzxJfglForm myForm,
			List<HashMap<String, String>> list) {
		String doType = myForm.getDoType();
		StringBuilder html = new StringBuilder();
		for(int i = 0;i < list.size();i++){
			if("update".equalsIgnoreCase(doType)){
				html.append("<tr><td width='5%'><input type='checkbox' /></td>");
				html.append("<td width='15%'><input type='hidden' id='hbsj' name='hbsj' value='");
				html.append(list.get(i).get("hbsj"));
				html.append("'/>");
				html.append(list.get(i).get("hbsj"));
				html.append("</td><td width='15%'><input type='text' id='hbje' name='hbje' size='10' maxlength='10' onblur='checkInputNum(this)' value='");
				html.append(list.get(i).get("hbje"));
				html.append("'/></td><td width='65%'><input type='text' id='bz' name='bz' style='width:280px' maxlength='1000' value='");
				if(list.get(i).get("bz")!=null){
					html.append(list.get(i).get("bz"));
				}
				html.append("'/></td></tr>");
			}else if("view".equalsIgnoreCase(doType)){
				html.append("<tr><td width='5%'>");
				html.append(i+1);
				html.append("</td><td width='15%'>");
				html.append(list.get(i).get("hbsj"));
				html.append("</td><td width='15%'>");
				html.append(list.get(i).get("hbje"));
				html.append("</td><td width='65%'>");
				if(list.get(i).get("bz")!=null){
					html.append(list.get(i).get("bz"));
				}
				html.append("</td></tr>");
			}
		}
		
		return html.toString();
	}
	/**
	 * ��֤������Ϣ�Ƿ���ȷ
	 * @param model
	 * @return
	 */
	public String checkBcInfo(QgzxJfglForm model) {
		String nd = model.getNd();
		String[] bm= model.getBm().split("!!@@!!");
		String[] hbsj = model.getHbsj().split("!!@@!!");
		for(int i = 0; i < bm.length;i++){
			String[] elSel = {nd,bm[i],hbsj[i]};
			if(myDao.isExist(elSel)){
				return "ͬһ���Ų��ܴ���������ͬ����ʱ��ľ�����Ŀ,��ȷ��";
			}
		}
		return "true";
	}
	/**
	 * ��֤�޸ı�����Ϣ�Ƿ���ȷ
	 * @param model
	 * @return
	 */
	public String checkXgBcInfo(QgzxJfglForm myForm) {
		String[] hbje = myForm.getHbje().split("!!@@!!");
		if(Base.isNull(myForm.getHbje())||"".equals(myForm.getHbje())){
			hbje = new String[]{"0"};
		}
		//�����ܽ��
		Double hbzje = 0.0;
		for(int i = 0; i < hbje.length;i++){
			hbzje+=Double.valueOf(hbje[i]);
		}
		if(hbzje<myDao.getHbzje(myForm)){
			return "�޸ĺ�Ļ����ܽ��С�ڵ�ǰ�ѻ����Ľ��,��ȷ��";
		}
		return "true";
	}
	
	/**
	 * ���Ĭ�ϲ���
	 * @param user 
	 * @return
	 */
	public HashMap<String, String> getMrcs(User user) {
		HashMap<String, String> rs = new HashMap<String,String>();
		rs.put("nd", Base.currNd);
		rs.put("bm", user.getUserDep());
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			rs.put("dis", "true");
		}
		return rs;
	}
	
	/**============================������ڹ�Begin============================*/
	/**
	 * ��ѯ�б�
	 */
	public List<HashMap<String,String>> getPageList(QgzxJfglForm t, User user) throws Exception {
		return myDao.getPageList(t, user);
	}
	/**
	 * ���ã��õ����˲���
	 * ʱ�䣺2016-12-20
	 */
	public List<HashMap<String, String>>getYrbm(){
		return myDao.getYrbm();
	}
	/**
	 * ���ã�������ȡ����Ŵ���
	 * ʱ�䣺2016-12-20
	 */
	public HashMap<String, String> getXycs(User user) {
		HashMap<String, String> rs = new HashMap<String,String>();
		rs.put("nd", Base.currNd);
		rs.put("bm", user.getUserDep());
		return rs;
	}
	/**
	 * ���ã�ȡ���ѻ�����Ϣ
	 * ʱ�䣺2016-12-20
	 */
	public HashMap<String, String> getJfhbMap(QgzxJfglForm myForm) {
		HashMap<String,String> rs = new HashMap<String,String>();
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		myForm.setNd(pkValue[0]);
		myForm.setBm(pkValue[1]);
		//���
		rs.put("nd", pkValue[0]);
		rs.put("bm", pkValue[1]);
		rs.put("bmmc",getBmmc(pkValue[1]));
		List<HashMap<String,String>> list = myDao.getJfxxList(myForm);
		String rsList = getJfhbym(myForm,list);
		rs.put("rsList", rsList);
		return rs;
	}
	/**
	 * ���ã����޸�ҳ��ľ��ѻ�����Ϣ
	 * ʱ�䣺2016-12-20
	 */
	private String getJfhbym(QgzxJfglForm myForm,List<HashMap<String, String>> list) {
		StringBuilder html = new StringBuilder();
		for(int i = 0;i < list.size();i++){
				html.append("<tr><td width='5%'>");
				html.append(i+1);
				html.append("</td><td width='15%'>");
				html.append(list.get(i).get("hbsj"));
				html.append("</td><td width='15%'>");
				html.append(list.get(i).get("hbje"));
				html.append("</td><td width='65%'>");
				if(list.get(i).get("bz")!=null){
					html.append(list.get(i).get("bz"));
				}
				html.append("</td></tr>");
		}
		return html.toString();
	}
	/**
	 * @����: �鿴ҳ�棬������ϸ�б�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-20 ����11:14:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFfmxList(QgzxJfglForm myForm) {
		return myDao.getFfmxList(myForm);
	}
	/**
	 * @����: �Ƿ����ڹ�����Ա
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-20 ����05:24:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean sfQggly(String yhm){
		return myDao.sfQggly(yhm);
	}
	
	/**
	 * �˷����������ˣ������Ժ�������
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getZdJfxxMap(QgzxJfglForm myForm) {
		HashMap<String,String> rs = new HashMap<String,String>();
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		myForm.setNd(pkValue[0]);
		myForm.setBm(pkValue[1]);
		//���
		rs.put("nd", pkValue[0]);
		rs.put("bm", pkValue[1]);
		rs.put("bmmc",getBmmc(pkValue[1]));
		List<HashMap<String,String>> list = myDao.getJfxxList(myForm);
		String rsList = getHtml(myForm,list);
		rs.put("rsList", rsList);
		return rs;
	}
	/**============================������ڹ�End============================*/
	
	/**
	 * ��ȡ����Դ�·�
	 */
	public List<HashMap<String,String>> getSourceMonth(){
		return new QgzxJfglDAO().getSourceMonth();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:���ƾ��ѻ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-24 ����11:37:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean copyJfhbData(String targetmonth,String sourcemonth) throws Exception{
		String hbsj = GetTime.getTimeByFormat("yyyymmdd");
		return new QgzxJfglDAO().copyJfhbData(targetmonth, hbsj, sourcemonth);
	}
	
	/**
	 * 
	 * @����: ��ǰ�·��Ƿ���ھ��ѻ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-24 ����03:24:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotJfhbDataExist(){
		return  new QgzxJfglDAO().checkIsNotJfhbDataExist();
	}
}
