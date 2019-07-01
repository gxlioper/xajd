package xgxt.pjpy.zjsyzy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭��ҵְҵ����ѧԺ��������Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-16</p>
 */
public class PjpyZjsyzyService {
	PjpyZjsyzyDAO dao = new PjpyZjsyzyDAO();
	
	/**
	 * ��ȡѧ�����ۺ����ʲ���������Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZhszcpStuinfo(String xh){
		return dao.getZhszcpStuinfo(xh);
	}
	
	/**
	 * ��ȡѧ���б�
	 * @param List
	 * */
	public List getXnndList(){
		return dao.getXnndList();
	}
	
	/**
	 * ��ȡѧ���б�
	 * @return List
	 * */
	public List getXqList(){
		return dao.getXqList();
	}
	
	/**
	 * ��ȡ����ѧ�����뽱ѧ����Ϣ
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 **/
	public HashMap<String, String> getJxjshInfo(String pk, String pkValue,String userType){
		return dao.getJxjshInfo(pk,pkValue,userType);
	}
	
	/**
	 * ��ȡ��˽���б�
	 * @param i
	 * @return List
	 * */
	public List getChkList(int i){
		return dao.getChkList(i);
	}
	
	/**
	 * ��ȡѧ����ѧ����Ϣ
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuJxjInfo(String pk, String pkValue){
		return dao.getStuJxjInfo(pk, pkValue);
	}
	
	/**
	 * ��ѧ�����
	 * @param pk
	 * @param pkValue
	 * @param yesNo
	 * @return boolean
	 * @throws Exception 
	 * */
	public String saveCheck(PjpyZjsyzyForm model,HttpServletRequest request, String jmc) throws Exception{
//		boolean flag = false;
		String yesNo = model.getYesNo();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "xsjxjb";
		String pk = model.getPk();
		String pkValue = model.getPkValue();
		String xyshyj = model.getXyshyj();
		String xxshyj = model.getXxshyj();
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
//		String xq = model.getXq();
		String jxjdm = model.getJxjdm();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String mes = "";
		
		if("xy".equalsIgnoreCase(userType) && yesNo != null && yesNo.equalsIgnoreCase("ͨ��") && !yesNo.equalsIgnoreCase(model.getSh())){
			//���������
			if(!dao.checkIsHmd(xh, "��ѧ��")){
				mes +=  xh + ":" + "�����Ѿ������뽱ѧ��������������������ͨ����";
				return mes;
			}
//			���ɼ���ж�
			String hdjxjmc = dao.getHdjxjmc(xh,xn,nd);
			hdjxjmc = StringUtils.isNull(hdjxjmc) ? "" : hdjxjmc;
			
			//���������ж�
			int shtgrs = dao.getCountOfShtg(xydm,jxjdm,nj);//���ͨ������
			int xzrs = dao.getCountOfXz(jxjdm,xydm,nj);//��������
			
			if(xzrs!=0 && (shtgrs>=xzrs)){
				String dw = dao.getShdw(pk,pkValue,userType);//ѧԺ��רҵ
				mes += dw + "���ͨ�������Ѿ��������������" + xzrs + ";ͨ��������" + shtgrs;				
				return mes;
			}	
			
			
			if(hdjxjmc != null && !hdjxjmc.equalsIgnoreCase("") && !hdjxjmc.equalsIgnoreCase(jmc)){
				mes += xh + ":" + xn + "ѧ��" + nd + "��ȸ����Ѿ������" + hdjxjmc + ",�����ټ�ø��ѧ��";
				return mes;
			}
			//��������ж�
			//String jxjmc = dao.getJxjmcByJxjdm(jxjdm);
			/**��������**/
			//ѧϰ�ɼ����޿���һ�Ų�����		
//			if(dao.checkBxk(xh,xn,xq)){
//				mes +=  xh + ":" + "������һ�ű��޿γɼ�������,����ͨ����";
//				return mes;
//			}
			//����δ���������ɼ����������������߲��ܲ����صȽ���
//			if(dao.checkTycj(xh,xn,xq)){
//				mes += xh + ":" + "����δ���������ɼ�������,��ʱ����ͨ����";				
//				return mes;
//			}
			//���������ܵ����š���������
			
			//�ۺϽ�ѧ��
//			if(jxjmc != null && (jxjmc.equalsIgnoreCase("һ�Ƚ�") || jxjmc.equalsIgnoreCase("���Ƚ�") || jxjmc.equalsIgnoreCase("���Ƚ�"))){
//				String zhszcpcj = dao.getZhszcpcj(xh,xn,nd);
//				String pjcj = dao.getPjcj(xh,xn,xq);
//				zhszcpcj = zhszcpcj == null || "".equalsIgnoreCase(zhszcpcj) ? "0" : zhszcpcj;
//				//�ۺ����ʲ����ɼ�����80
//				if(Double.parseDouble(zhszcpcj) <80){
//					mes += xh + ":" + "�ۺ����ʲ����ɼ�Ϊ��" + zhszcpcj + "��; С��80��";
//					return mes;
//				}
//				//TODO �ɼ�ƽ��70������
//				if(Double.parseDouble(pjcj)<70){
//					request.setAttribute("mes", xh + ":" + xn + "ѧ��" +nd + "��ȳɼ�ƽ���֣�" + pjcj + ";С��70��");
//				}
//			}
			
			//�صȽ�ѧ��
//			if(jxjmc != null && jxjmc.equalsIgnoreCase("�صȽ�")){
//				//���ƿ��Գɼ�����85������
//				if(!dao.Zcjdb(xh,xn,xq)){
//					mes += xh + ":" + "���ƿ��Ծ�Ҫ��85�����ϣ�δ�ﵽҪ��";
//					return mes;
//				}
//				//�����ɼ�70������
//				String tycj = dao.getTycj(xh,xn,xq);
//				tycj = tycj == null || tycj.equalsIgnoreCase("") ? "0" : tycj;
//				if(Double.parseDouble(tycj) <70){
//					mes += xh + ":" + "�����ɼ�С��70��!";
//					
//					return mes;
//				}
//				
//				//�³ɼ�90������
//				String dcj = dao.getDycj(xh,xn,nd);
//				dcj = dcj == null || dcj.equalsIgnoreCase("") ? "0" : dcj;
//				if(Double.parseDouble(dcj)<90){
//					mes += xh + ":" + "˼��Ʒ�²��в���С��90�֣�";
//					return mes;
//				}
//			}
		}
		if(userType != null && userType.equalsIgnoreCase("xy")){
			StandardOperation.update(tableName, new String[]{"xysh","xyshyj","fdyyj"}, new String[]{yesNo,xyshyj,model.getFdyyj()}, pk, pkValue, request);
		}else if(userType != null && (userType.equalsIgnoreCase("xx") || userType.equalsIgnoreCase("admin"))){
			StandardOperation.update(tableName, new String[]{"xxsh","xxshyj"}, new String[]{yesNo,xxshyj}, pk, pkValue, request);
		}
		return mes;
	}
	
	/**
	 * ��ȡ�����ƺ������Ϣ
	 * @param PjpyZjsyzyForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getRychshInfo(PjpyZjsyzyForm model){
		return dao.getRychshInfo(model);
	}
	
	/**
	 * ���������ƺŵ���˽��
	 * @param PjpyZjsyzyForm model
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveCheckCredit(PjpyZjsyzyForm model, HttpServletRequest request) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		boolean flag = false;
		String userType = model.getUserType();
		String yesNo = model.getYesNo();
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String rychdm = model.getRychdm();
		String rychmc = model.getRychmc();
		String tableName = "xsrychb";
		String primaryKey = "xh||xn||nd||rychdm";
		String pkValue = xh+xn+nd+rychdm;
		String[] columns = {"xysh"};
		String[] values = {yesNo};
		
		String sql = "select xydm,zydm,bjdm from view_xsjbxx where xh=?";
		map = dao.getMap(sql, new String[]{xh}, new String[]{"xydm","zydm","bjdm"});
		
		//������������ж� 
		if(yesNo != null && yesNo.equalsIgnoreCase("ͨ��")){
			//���������
			if(!dao.checkIsHmd(xh, "�����ƺ�")){
				request.setAttribute("mes", "�����Ѿ������������ƺ�������������������ͨ����");
				return false;
			}
			if(rychmc != null && rychmc.equalsIgnoreCase("����ѧ���ɲ�")){//����ѧ���ɲ�
				//��������
				int xzrs = dao.getRychXzrs(map.get("xydm"));
				int shtgrs = dao.getRychShtgrs(map.get("xydm"),model);
				if(shtgrs>=xzrs){
					request.setAttribute("mes", "���ͨ����������,��˲飡");
					return false;
				}				
				//�����ж�
				//ѧϰ�ɼ��޲����� ѧϰ�޿���
				if(dao.isFail(xh,xn)){
					request.setAttribute("mes", xn + "ѧ��" + "�в�����ɼ���¼,��˲飡");
					return false;
				}
			}
			if(rychmc != null && rychmc.equalsIgnoreCase("����ѧ��")){  //����ѧ��
				String pjcj = dao.getPjcj(xh,xn,xq);
				//һѧ�����޳ɼ������񡢿������׺��ܴ��ֵȲ�����¼				
				//�Ѿ�����ͨ���ж�
				//ÿѧ�ھ�Ϊ��ѧ������	
				if(dao.priseEveryTerm(xh)){
					request.setAttribute("mes", "����ÿѧ�ھ�Ϊ��ѧ������,��˲飡");
					return false;
				}
				//���ſγ�ƽ���ɼ���80������
				if(Double.parseDouble(pjcj)<80){
					request.setAttribute("mes", xn + "ѧ��" + xq + "ѧ�ڿγ�ƽ���ɼ�����80��,��˲飡");
					return false;
				}
				//�ۺ����ʲ����ɼ���ȫ��ǰ15%
				if(dao.checkPm(xh,xn,xq)){
					request.setAttribute("mes", xn + "ѧ��" + xq + "ѧ���ۺ����ʲ����ɼ�������15%����,��˲飡");
					return false;
				}
				//ѧ���޿�������
			}
			if(rychmc != null 
					&& (rychmc.equalsIgnoreCase("ʡ�������ҵ��") 
					|| rychmc.equalsIgnoreCase("���������ҵ��") 
					|| rychmc.equalsIgnoreCase("Ժ�������ҵ��")) ){
				if(rychmc.equalsIgnoreCase("ʡ�������ҵ��")){
//					int xzrs = 0;
				}				
			}
		}
		if(userType != null && !userType.equalsIgnoreCase("xy")){//ѧУ�û�
			columns = new String[]{"xxsh"};	
		}
		flag = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		return flag;
	}
	
	//////////////////////////////////////////////////
	
	/**
	 * ��ȡ�����ӷ���Ŀ�б�
	 * @return list
	 * */
	public List getSsjfxmList(){
		return dao.getSsjfxmList();
	}
	
	/**
	 * ��ȡ���������б�
	 * @return List
	 * */
	public List getCzlxList(){
		return dao.getCzlxList();
	}
	
	/**
	 * ������Ŀģ����ѯ
	 * @param model
	 * @return List
	 * */
	public List querryBsxm(PjpyZjsyzyForm model){
		String[] columns = {"xmdm","xmmc","ssjfxm","czlx"};
		String userType = model.getUserType();
		String tableName = model.getTableName();
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			columns = new String[]{"xmdm","xmmc","ssjfxm","xmjf","czlx"};
		}
		return dao.querryBsxm(model,tableName,columns);
	}
	
	/**
	 * ������Ŀģ����ѯ��ͷ
	 * @return List
	 * */
	public List getBsxmToptr(String userType){
		String[] colEN = {"xmdm", "xmmc" ,"ssjfxm","czlx"};
		String[] colCN = dao.getColumnNameCN(colEN, "bsxmb");
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			colEN = new String[]{"xmdm", "xmmc" ,"ssjfxm","xmjf","czlx"};
			colCN = dao.getColumnNameCN(colEN, "view_xybsxmjfxxb");
		}
		return dao.arrayToList(colEN, colCN);
	}
	
	/**
	 * ���������Ŀ��Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveBsxm(PjpyZjsyzyForm model,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String xmdm = DealString.toGBK(model.getXmdm());
		String xmmc = DealString.toGBK(model.getXmmc());
		String ssjfxm = DealString.toGBK(model.getSsjfxm());
		String tableName = "bsxmb";
		String userType = model.getUserType();
		String userDep = model.getUserDep();
		String xmjf = DealString.toGBK(model.getXmjf());
		String czlx = DealString.toGBK(model.getCzlx());
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			tableName = "xybsxmjfxxb";
			if(dao.checkExists("xmdm||xydm", xmdm+userDep, tableName)){
				//update
				flag = StandardOperation.update(tableName, new String[]{"xmjf"},
						new String[]{xmjf}, "xmdm||xydm", xmdm+userDep, request);
			}else{
				//insert
				flag = StandardOperation.insert(tableName, new String[]{"xmdm","xmjf","xydm"}, 
						new String[]{xmdm,xmjf,userDep}, request);
			}
		}else{
			if(dao.checkExists("xmdm", xmdm, tableName)){
				//update
				flag = StandardOperation.update(tableName, new String[]{"xmmc","ssjfxm","czlx"},
						new String[]{xmmc,ssjfxm,czlx}, "xmdm", xmdm, request);
			}else{
				//insert
				flag = StandardOperation.insert(tableName, new String[]{"xmdm","xmmc","ssjfxm","czlx"}, 
						new String[]{xmdm,xmmc,ssjfxm,czlx}, request);
			}
		}
		return flag;
	}
	
	/**
	 * ɾ��������Ŀ��Ϣ
	 * @param String[] pk
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean delBsxm(String[] pk,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "bsxmb";
		for(int i=0; i<pk.length; i++){
			if(pk[i] != null && !pk[i].equalsIgnoreCase("0001") && !pk[i].equalsIgnoreCase("0002")){// 0001 0002ϵͳĬ�ϵ���Ŀ����ɾ��
				flag = StandardOperation.delete(tableName, "xmdm", pk[i], request);
			}else{
				request.setAttribute("mes", "0001��0002ΪϵͳĬ�ϵ���Ŀ������ɾ��!");
			}
			
		}
		return flag;
	}
	
	/**
	 * ����������ѯ������Ŀ��Ϣ
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> showBsxmInfo(String pk, String pkValue){
		return dao.querryBxsmInfo(pk,pkValue);
	}
	
	/**
	 * ����������ѯ������Ŀ��Ϣ
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> showXyBsxmInfo(String pk, String pkValue){
		return dao.querryXyBxsmInfo(pk,pkValue);
	}
	
	/**
	 * ��ȡ������Ϣ�б�
	 * @param tableName
	 * @param columns
	 * @return List
	 * */
	public List getBsxmList(String tableName, String[]columns,String tj1){
		if(tj1 != null && !"".equalsIgnoreCase(tj1)){
			tj1 = " where ssjfxm='" + tj1 + "'";
		}
		return dao.getDmList(tableName,columns,tj1);
	}
	
	
	public List getBxsmListByXy(String xydm){
		return dao.getBxsmListByXy(xydm);
	}
	/**
	 * ��ʼ��������Ŀ�ӷַ���
	 * @param userDep
	 * @param reqeust
	 * @return boolean
	 * */
	public boolean initMark(String userDep, HttpServletRequest request) throws Exception{
		return StandardOperation.update("xybsxmjfxxb", new String[]{"xmjf"}, new String[]{""}, "xydm", userDep, request);
	}
	
	/**
	 * ��ѯ���ʻ�����Ϣ
	 * @param model
	 * @return List
	 * @throws Exception 
	 * */
	public List querrySzjf(PjpyZjsyzyForm model) throws Exception{
		return dao.querrySzjf(model);
	}
	
	/**
	 * ��ѯ���ʻ�����Ϣ��ͷ
	 * @return List 
	 * */
	public List getSzjfToptr(){
		String tabName =  "view_xsszjf";
		String[] topColumn = {"����","xn","xq","xh","xm","bjmc","xmlbmc","ssjfxm","czlx","xmjf"};
		String[] topCN = dao.getColumnNameCN(topColumn, tabName);
		return dao.arrayToList(topColumn, topCN); 
	}
	
	/**
	 * ����ѧ�Ų�ѯѧ����Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuDetails(String xh){
		return dao.getStuDetails(xh);
	}
	
	/**
	 * ��ѯ����ѧ�����ʻ�����Ϣ
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getSzjfInfo(String pkValue){
		return dao.getSzjfInfo(pkValue);
	}
	
	/**
	 * ���ʻ�����Ϣ����
	 * @param model
	 * @param doType
	 * @param request
	 * @return boolean 
	 * @throws Exception
	 * */
	public boolean saveSzjf(PjpyZjsyzyForm model, String doType, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "xsszjfb";
		String pk = "xn||xq||xh||xmlbdm||sj";
		String xh = model.getXh();
		String xmlbdm = model.getXmlbdm();
		String sj = model.getSj();
		String bz = DealString.toGBK(model.getBz());
		String jfxm = DealString.toGBK(model.getJfxm());
		String xmmc = DealString.toGBK(model.getXmmc());
		String zgdw = DealString.toGBK(model.getZgdw());
		String xn = model.getXn();
		String xq = model.getXq();
		String xhO = model.getXhO();
		String xmlbdmO = model.getXmlbdmO();
		String sjO = model.getSjO();
		
		String pkValue = xn+xq+xh+xmlbdm+sj;
		if(doType != null && "modi".equalsIgnoreCase(doType)){//����
			pkValue = xn+xq+xhO+xmlbdmO+sjO;
			flag = StandardOperation.update(tableName, new String[]{"xh","xmlbdm","sj","xmmc","zgdw","jfxm","bz"},
					new String[]{xh,xmlbdm,sj,xmmc,zgdw,jfxm,bz}, pk,xn+xq+xhO+xmlbdmO+sjO, request);
		}else{//����
			if(dao.isExists(tableName, pk, pkValue)){//��¼�Ѿ�����
				//update
				flag = StandardOperation.update(tableName, new String[]{"jfxm","xmmc","zgdw","bz"}, new String[]{jfxm,xmmc,zgdw,bz}, pk, pkValue, request);
			}else{
				//insert
				flag = StandardOperation.insert(tableName, new String[]{"xh","xmlbdm","xn","xq","sj","bz","jfxm","xmmc","zgdw"}, new String[]{xh,xmlbdm,xn,xq,sj,bz,jfxm,xmmc,zgdw}, request);
			}
		}
		
		return flag;
	}
	
	/**
	 * ѧ�����ʻ�����Ϣɾ��
	 * 
	 * */
	public boolean delSzjf(String pk, HttpServletRequest request) throws Exception{
		boolean flag = false;
		pk = DealString.toGBK(pk);
		String[] pkValues = pk.split("!!");
		String tableName = "xsszjfb";//�������ݱ�
		String pkStr = "xn||xq||xh||xmlbdm||sj";
		String mes = "";
		
		for(int i=0; i<pkValues.length; i++){
			flag = StandardOperation.delete(tableName, pkStr, pkValues[i], request);
			if(flag==false){
				mes += "��" + i  + "������ɾ��ʧ��!\n";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * ��ѯ�������ź�������Ϣ
	 * @param model
	 * @return List
	 * @throws Exception 
	 * */
	public List querryPjpyhmd(PjpyZjsyzyForm model) throws Exception{		
		return dao.querryPjpyhmd(model); 
	}
	
	/**
	 * ��ȡ�������ź�������Ϣ��ͷ
	 * @return List
	 * */
	public List getPjpyhmdToptr(){
		String tabName = "view_pjpyhmd";
		String[] topColumn = {"����","xh","xm","bjmc","xmmc","kssj","jssj"};
		String[] topCN = dao.getColumnNameCN(topColumn, tabName);
		return dao.arrayToList(topColumn, topCN);
	}
	
	/**
	 * ��ȡ��������Ŀ�б�
	 * @return List
	 * */
	public List getXmList(){
		String[] tmpValue = {"��ѧ��","�����ƺ�"};		
		return dao.arrayToList(tmpValue, tmpValue);
	}
	
	/**
	 * ��ѯ����ѧ�����������ź�������¼��Ϣ
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getPjpyhmdInfo(String pkValue){
		return dao.getPjpyhmdInfo(pkValue);
	}
	
	/**
	 * �����������ź�������Ϣ
	 * @param model
	 * @param doType
	 * @param request
	 * @return booleans
	 * */
	public boolean savePjpyhmd(PjpyZjsyzyForm model,String doType,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "pjpyhmd";
		String pk = "xh||xmmc";
		String xh = model.getXh();
		String xmmc = DealString.toGBK(model.getXmmc());
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String yy = DealString.toGBK(model.getYy());
		String bz = DealString.toGBK(model.getBz());
		String pkValue = xh+xmmc;
		String xmmcO = DealString.toGBK(model.getXmmcO());
		
		if(doType != null && "modi".equalsIgnoreCase(doType)){//�޸Ĳ���
			if(!xmmc.equalsIgnoreCase(xmmcO)){
				if(dao.isExists(tableName, pk, pkValue)){
					flag = StandardOperation.delete(tableName, pk, pkValue, request);
					if(flag){
						flag = StandardOperation.update(tableName, new String[]{"xh","xmmc","kssj","jssj","yy","bz"}, 
								new String[]{xh,xmmc,kssj,jssj,yy,bz}, pk, xh+xmmcO, request);
					}
				}else{
					flag = StandardOperation.update(tableName, new String[]{"xh","xmmc","kssj","jssj","yy","bz"}, 
							new String[]{xh,xmmc,kssj,jssj,yy,bz}, pk, xh+xmmcO, request);
				}
			}
		}else{//���Ӳ���
			if(dao.isExists(tableName, pk, pkValue)){
				//update
				flag = StandardOperation.update(tableName, new String[]{"kssj","jssj","yy","bz"}, 
						new String[]{kssj,jssj,yy,bz}, pk, pkValue, request);
			}else{
				//insert
				flag = StandardOperation.insert(tableName, new String[]{"xh","xmmc","kssj","jssj","yy","bz"}, 
						new String[]{xh,xmmc,kssj,jssj,yy,bz}, request);
			}
		}
		return flag;
	}
	
	/**
	 * ɾ���������ź�������Ϣ
	 * @param pk
	 * @param request
	 * @return boolean
	 * */
	public boolean delPjpyhmd(String pk,HttpServletRequest request) throws Exception{
		boolean flag = false;
		pk = DealString.toGBK(pk);
		String[] pkValues = pk.split("!!");
		String tableName = "pjpyhmd";//�������ݱ�
		String pkStr = "xh||xmmc";
		String mes = "";
		
		for(int i=0; i<pkValues.length; i++){
			flag = StandardOperation.delete(tableName, pkStr, pkValues[i], request);
			if(flag==false){
				mes += "��" + (i+1)  + "������ɾ��ʧ��!\n";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * �Զ������ۺ����ʲ�������
	 * @param model
	 * @return boolean
	 * */
	public boolean autoAccount(PjpyZjsyzyForm model) throws Exception{
		String xn = model.getXn();
		String xq = model.getXq();
		String bjdm = model.getBjdm();
		
		return dao.runProcuder("{call autoAccountOfZjsyzy(?,?,?)}", new String[]{xn,xq,bjdm});
	}
	
	/**
	 * ��ѯ��ѧ��������ϸ��Ϣ
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * @throws Exception
	 * **/
	public HashMap<String, String> selectJxjsqOne(String pk, String pkValue) throws Exception{
		return dao.selectJxjsqOne(pk,pkValue);		
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param xh
	 * @return HashMap<String, String> 
	 * @throws Exception
	 * */
	public HashMap<String, String> selectStuBase(String xh) throws Exception{
		return dao.selectStuBase(xh);
	}
	
	/**
	 * ��ѯѧ��������ѧ���Υ�ʹ�����Ϣ ����ѧ��,ѧ��,�����������,����ԭ������
	 * 
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfxx(String xh, String xn, String xq)
			throws Exception {
		return dao.getStuWjcfxx(xh, xn, xq);
	}
	
	/**
	 * ��ѯѧ��������ѧ���Υ�ʹ�����Ϣ ����Υ�ͼ�¼��
	 * 
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public int getStuWjcfxxCount(String xh, String xn, String xq)
			throws Exception {
		return dao.getStuWjcfxxCount(xh, xn, xq);
	}
	
	/**
	 * ��ѯѧ��������ѧ���ѧ���ɼ� ����:��������ʾ����ɫ,�γ�����,�γ�����,�ɼ�
	 * 
	 * @param jwbb
	 *            is true ���ѯ ���ɰ�ɼ���֮ѧ�ְ�ɼ���
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuKccjxx(boolean jwbb, String xh, String xn,
			String xq) throws Exception {
		return dao.getStuKccjxx(jwbb, xh, xn, xq);
	}
	
	/**
	 * 	��ѯѧ�����ڰ༶�ۺ����ʳɼ���������Ϣ
	 * @param xh
	 * @return
	 */
	public List<String[]> getBjZhszcpinfo(String xh,String xn,String xq) {
		return dao.getBjZhszcpinfo(xh,xn,xq);
	}
	
	/**
	 * ��ȡѧ�����ڵİ༶����
	 * @param xh
	 * @return String
	 * **/
	public String getStubjmc(String xh) {
		return dao.getStubjmc(xh);
	}
	
	/**
	 * �ж��ܷ����صȽ�ѧ��
	 * @param xh
	 * @param xn
	 * @param nd
	 * @param xq
	 * @throws Exception 
	 * */
	public boolean nfhdtdjxj(String xh,String xn, String nd, String xq) throws Exception{
		return dao.nfhdtdjxj(xh, xn, nd, xq);
	}
	
	/**
	 * ��ȡ������������
	 * @param xh
	 * @param xn
	 * @param nd
	 * @param xq
	 * @return String
	 * @throws Exception 
	 * */
	public String getCpcppm(String xh,String xn,String nd,String xq) throws Exception{
		return dao.getCpcppm(xh, xn, nd, xq);
	}
}
