package xgxt.qgzx.zgdzdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class QgzxHgsqService {
	QgzxHgsqDAO qgzxhgsqdao = new QgzxHgsqDAO();
	/**��ȡ��λ��������ʱ��*/
	public String[] getStuTimeService(){
		return qgzxhgsqdao.getStuTime();
	}
	
	/**��ȡ��λ�����б�*/
	public List<HashMap<String, String>> getGwNameListService(String isLsgw){
		return qgzxhgsqdao.getGwNameList(isLsgw);
	}
	
	/**��ȡ��λ����*/
	public String[] getGwNameService(String tmp){
		return qgzxhgsqdao.getGwName(tmp);
	}
	
	/**��ȡѧ��ͨ��ѧԺ�Ƽ���¼*/
	public String getXytjcountService(String xh,String xn,String nd,String xq){
		String result = qgzxhgsqdao.getXytjcount(xh, xn, nd, xq);
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return result;
	}
	
	/**��ȡѧ����λ��˼�¼*/
	public boolean getGwshcountService(String xh,String xn,String nd,String xq){
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		String result = qgzxhgsqdao.getGwshcount(xh, xn, nd, xq);
		flag = Integer.parseInt(StringUtils.isNull(result) ? "0" : result)>0 ? true : false;
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			flag = qgzxhgsqdao.checkExists("xsgwxxb", "xh||xn||nd||xq||sfyx", xh+xn+nd+xq+"1");//sfyx='1'��Ч
		}
		return flag;
	}
	
	/**��ȡѧ���ڸڸ�λ*/
	public String getGwdmService(String xh,String xn,String nd,String xq){
		return qgzxhgsqdao.getGwdm(xh, xn, nd, xq);
	}
	
	/**ѧ���ڹ���ѧʱ�����
	 * @throws Exception */
	public void updateQgzxTime(String xh,HttpServletRequest request) throws Exception{
		String[][] kxf = new String[4][7];
		for(int i=0; i<4; i++){
			for(int j=0; j<7; j++){
				kxf[i][j] = request.getParameter(String.valueOf(i)+String.valueOf(j+1))==null?"0":"1";
			}
		}
		if(kxf!=null){
			qgzxhgsqdao.deleteQgzxTime(xh);
			for(int i=0; i<4; i++){
				for(int j=0; j<7; j++){
					qgzxhgsqdao.saveQgzxTime(xh, String.valueOf(i+1), String.valueOf(j+1), kxf[i][j]);
				}
			}
		}
	}
	
	/**����ѧ���ڹ���ѧ���ڵ�������Ϣ
	 * @throws SQLException */
	public boolean hgxx_saveService(String tmp[]) throws SQLException{
		return qgzxhgsqdao.hg_save(tmp);
	}
	
	/**����ѧԺ�����ȡ��λ�б�*/
	public List<HashMap<String, String>> getGwListForXydmService(String userName){
		return qgzxhgsqdao.getGwListForXydm(userName);
	}
	
	/**��ȡ��λ�б�*/
	public List<HashMap<String, String>> getGwListService(String userName){
		return qgzxhgsqdao.getGwList(userName);
	}
	
	/**��ȡ��λ�����б�*/
	public List<HashMap<String, String>> getGwXzListService(){
		return qgzxhgsqdao.getGwXzList();
	}
	
	/**��ȡ���뻻�ڵ�ѧ���б�*/
	public ArrayList<String[]> getHgXsService(StringBuffer querry,String[] colList,String userType){
		if("xx".equalsIgnoreCase(userType)){
			querry.append(" and xyyj='ͨ��'");
		}
		return qgzxhgsqdao.getHgXs(querry, colList);
	}
	
	/**��ȡѧ������������ϸ��Ϣ*/
	public HashMap<String, String> getQgzxOneService(String pkValue){
		return qgzxhgsqdao.getQgzxOne(pkValue);
	}
	
	/**ѧ��������Ϣ����
	 * @throws Exception */
	public boolean hgxx_updateService(String[] tmp,String pkValue) throws Exception{
		String[] tmp1 = pkValue.split("-");
		String[] tmp2 = new String[tmp.length+tmp1.length];
		for(int i=0;i <tmp.length;i++) 
		{ 
		  tmp2[i]=tmp[i]; 
		} 
		for(int j=0;j <tmp1.length;j++) 
		{ 
		  tmp2[tmp.length+j]=tmp1[j]; 
		}
		return qgzxhgsqdao.hgxx_update(tmp2);
	}
	
	/**ѧ���������
	 * @throws Exception */
	public boolean hgsqshService(String[] pkTmp,String userType,String type) throws Exception{
		boolean res = false;
		if("pass".equalsIgnoreCase(type)){
			type="ͨ��";
		}else{
			type="��ͨ��";
		}
		for(int i=0;i<pkTmp.length;i++){
			String[] tmp = pkTmp[i].split("-");
			res=qgzxhgsqdao.hgsqsh(tmp,userType,type);//���
			if(!res){
				break;
			}
			if("xx".equalsIgnoreCase(userType)&&"ͨ��".equalsIgnoreCase(type)){
				HashMap<String, String> rsMapGw = qgzxhgsqdao.getXsgwxx(tmp[0], tmp[1], tmp[2]);
				HashMap<String, String> rsMapHg = qgzxhgsqdao.getXshgxx(tmp);
				if(rsMapGw.size()!=0){
					//���¸�λ��Ϣ���е���ϸ��Ϣ������λ�仯�ļ�¼���뵽��λ������
					res = qgzxhgsqdao.updateXsgwxx(rsMapHg,tmp[0], tmp[1], tmp[2]);//���¸�λ��Ϣ
					if(!res){//���³ɹ�����Ϣ������ڼ�¼
						break;
					}
					//ɾ����λ��Ϣ����ѧ�������ڸڼ�¼
					res = qgzxhgsqdao.deleteXsgwxxb(tmp[0], tmp[1], tmp[2]);//���¸�λ��Ϣ
					qgzxhgsqdao.updateQgzxsqb(tmp[0]);//���´Ǹ�ѧ�����ڹ���ѧ���е����״̬
					if(!res){//���³ɹ�����Ϣ������ڼ�¼
						break;
					}
					qgzxhgsqdao.insertDgjl(Base.currXn,Base.currXq,rsMapGw);
					//ɾ��ѧ������ʱ����еĴǸ�ѧ����¼
					qgzxhgsqdao.deleteQgzxTime(tmp[0]);
					
					qgzxhgsqdao.hgsqshOther(tmp,userType,"��ͨ��");//���ͨ����������������Ϊ��ͨ��
					qgzxhgsqdao.insertDgjl(Base.currXn,Base.currXq,rsMapGw,rsMapHg);
				}
			}
		}
		return res;
	}
	
	/**����ѧ���Ǹ���Ϣ
	 * @throws Exception */
	public boolean cgxx_saveService(String[] tmp,String modi) throws Exception{
		if("yes".equalsIgnoreCase(modi)){			
			return qgzxhgsqdao.cgxx_update(tmp);
		}
		return qgzxhgsqdao.cgxx_save(tmp);
	}
	
	/**��ȡ����Ǹڵ�ѧ���б�*/
	public ArrayList<String[]> getCgXsService(StringBuffer querry,String[] colList,String userType){
		if("xx".equalsIgnoreCase(userType)){
			querry.append(" and xyyj='ͨ��'");
		}
		return qgzxhgsqdao.getCgXs(querry, colList);
	}
	
	/**ѧ���Ǹ����
	 * @throws Exception */
	public boolean cgsqshService(String[] pkTmp,String userType,String type) throws Exception{
		boolean res = false;
		boolean delFlag = true;
		String xxdm = StandardOperation.getXxdm();
		if("pass".equalsIgnoreCase(type)){
			type="ͨ��";
		}else{
			type="��ͨ��";
		}
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ 
			delFlag = false;
		}
		for(int i=0;i<pkTmp.length;i++){
			String[] tmp = pkTmp[i].split("-");
			res=qgzxhgsqdao.cgsqsh(tmp,userType,type);//���
			if(!res){
				break;
			}
			if("xx".equalsIgnoreCase(userType)&&"ͨ��".equalsIgnoreCase(type)){
				HashMap<String, String> rsMapGw = qgzxhgsqdao.getXsgwxx(tmp[0], tmp[1], tmp[2]);
				
				if(delFlag){
					//ɾ����λ��Ϣ���дǸ�ѧ����¼
					res = qgzxhgsqdao.deleteXsgwxxb(tmp[0], tmp[1], tmp[2]);//���¸�λ��Ϣ
					qgzxhgsqdao.updateQgzxsqb(tmp[0]);//���´Ǹ�ѧ�����ڹ���ѧ���е����״̬
					if(!res){//���³ɹ�����Ϣ������ڼ�¼
						break;
					}
				}else{
					qgzxhgsqdao.runUpdate("update xsgwxxb set sfyx='0' where xh=? and gwdm=? and gwsbsj=?", tmp);
				}
				qgzxhgsqdao.insertDgjl(Base.currXn,Base.currXq,rsMapGw);
				//ɾ��ѧ������ʱ����еĴǸ�ѧ����¼
				qgzxhgsqdao.deleteQgzxTime(tmp[0]);
			}
		}
		return res;
	}
	
	/**��ȡѧ�������Ǹ���ϸ��Ϣ*/
	public HashMap<String, String> getQgzxcgOneService(String pkValue){
		return qgzxhgsqdao.getQgzxcgOne(pkValue);
	}
	
	/**��ȡ���˵�λ��Ϣ*/
	public HashMap<String, String> getYrdwxxService(String userDep){
		return qgzxhgsqdao.getYrdwxx(userDep);
	}
	
	/**�й����ʴ�ѧ��ȡ���˵�λ����ѧ����ʱ��Ϣ*/
	public ArrayList<String[]> getYrdwctxslsxxserivce(String userName){
		return qgzxhgsqdao.getYrdwctxslsxx(userName);
	}
	/**�й����ʴ�ѧ��ȡ���˵�λ����ѧ����ʱ��Ϣ*/
	public ArrayList<String[]> getYrdwghxslsxxserivce(String userName){
		return qgzxhgsqdao.getYrdwghxslsxx(userName);
	}
	/**�й����ʴ�ѧ��ȡ���˵�λ����ѧ����ʱ��Ϣ*/
	public ArrayList<String[]> getYrdwsqxslsxxserivce(String userName){
		return qgzxhgsqdao.getYrdwsqxslsxx(userName);
	}
	
	/**�й����ʴ�ѧ���˵�λ����ѧ����ʽ���ݱ���
	 * @throws Exception */
	public boolean insertYrdwghXsService(String[] tmp,String xn,String nd,String xq,String sqly,String bz) throws Exception{
		String xh=tmp[0];
		String gwdm=tmp[1];
		String gwsbsj=tmp[2];
		String lxdh=tmp[3];
		String bj=tmp[4];
		String yhm=tmp[5];
		String num = qgzxhgsqdao.getYrdwghXs(xh,gwdm,gwsbsj,xn,nd,xq);
		if(!"0".equalsIgnoreCase(num)){
			return false;
		}
		return qgzxhgsqdao.insertYrdwghXs(xh,gwdm,gwsbsj,lxdh,bj,yhm,xn,nd,xq,sqly,bz);
	}
	
	/**������ʱ���е�ѧ����¼
	 * @throws Exception */
	public boolean deleteYrdwghxslsbSerivce(String userName) throws Exception{
		return qgzxhgsqdao.deleteYrdwghxslsb(userName);
	}
	
	
	/**��ȡ���˵�λ������ѧ���б�*/
	public ArrayList<String[]> getYrdwGhXsService(StringBuffer querry,String[] colList,String userType){
		return qgzxhgsqdao.getYrdwGhXs(querry, colList);
	}
	
	/**��ȡ���˵�λ������ѧ����ϸ��Ϣ*/
	public HashMap<String, String> getYrdwGhXsOneService(String pkValue){
		return qgzxhgsqdao.getYrdwGhXsOne(pkValue);
	}
	
	
	/**���˵�λ����ѧ�����
	 * @throws Exception */
	public boolean yrdwGhxsshService(String[] pkTmp,String userType,String type) throws Exception{
		boolean res = false;
		if("pass".equalsIgnoreCase(type)){
			type="ͨ��";
		}else{
			type="��ͨ��";
		}
		for(int i=0;i<pkTmp.length;i++){
			String[] tmp = pkTmp[i].split("-");
			res=qgzxhgsqdao.yrdwGhxssh(tmp,type);//���
			if(!res){
				break;
			}
			if("����".equalsIgnoreCase(tmp[3])){
				if("xx".equalsIgnoreCase(userType)&&"ͨ��".equalsIgnoreCase(type)){
					HashMap<String, String> rsMapGw = qgzxhgsqdao.getXsgwxx(tmp[0], tmp[1], tmp[2]);
				
					//ɾ����λ��Ϣ���дǸ�ѧ����¼
					res = qgzxhgsqdao.deleteXsgwxxb(tmp[0], tmp[1], tmp[2]);//���¸�λ��Ϣ
					qgzxhgsqdao.updateQgzxsqb(tmp[0]);//���´Ǹ�ѧ�����ڹ���ѧ���е����״̬
					if(!res){//���³ɹ�����Ϣ������ڼ�¼
						break;
					}
					qgzxhgsqdao.insertDgjl(Base.currXn,Base.currXq,rsMapGw);
					//ɾ��ѧ������ʱ����еĴǸ�ѧ����¼
					qgzxhgsqdao.deleteQgzxTime(tmp[0]);
				}
			}
			if("����".equalsIgnoreCase(tmp[3])){
				if("xx".equalsIgnoreCase(userType)&&"ͨ��".equalsIgnoreCase(type)){
					HashMap<String, String> rsMapGw = qgzxhgsqdao.getXsgwxx(tmp[0], tmp[1], tmp[2]);
					HashMap<String, String> rsMapHg = qgzxhgsqdao.getYrdwGhXsOne(tmp[0], tmp[1], tmp[2],tmp[3]);
					
					//���¸�λ��Ϣ���е���ϸ��Ϣ������λ�仯�ļ�¼���뵽��λ������
					res = qgzxhgsqdao.updateXsgwxxb(tmp[0], tmp[1], tmp[2]);//���¸�λ��Ϣ
					if(!res){//���³ɹ�����Ϣ������ڼ�¼
						break;
					}
					qgzxhgsqdao.yrdwHgsqshOther(tmp,userType,"��ͨ��");//���ͨ����������������Ϊ��ͨ��
					qgzxhgsqdao.insertYrdwDgjl2(Base.currXn,Base.currXq,rsMapGw,rsMapHg);
				}
			}
			if("����".equalsIgnoreCase(tmp[3])){
				if("xx".equalsIgnoreCase(userType)&&"ͨ��".equalsIgnoreCase(type)){
					HashMap<String, String> rsMapHg = qgzxhgsqdao.getYrdwGhXsOne(tmp[0], tmp[1], tmp[2],tmp[3]);
					if(!qgzxhgsqdao.checkExists("xsgwxxb", "xh||gwdm||gwsbsj", rsMapHg.get("xh")+rsMapHg.get("gwdm")+rsMapHg.get("gwsbsj"))){
						res = qgzxhgsqdao.insertXsgwxxb(rsMapHg);
					}					
					if(!res){
						break;
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * �ж��û��Ƿ������˵�λ�û�
	 * @param String userName
	 * @return boolean
	 * */
	public boolean checkIsYrdwUser(String userName){
		return qgzxhgsqdao.isYrdw(userName);
	}
	
	/**
	 * ��ѯѧ���ڵĸ�λ�б�
	 * @param String xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsgwList(String xh){
		return qgzxhgsqdao.selectXsgwList(xh);
	}
}
