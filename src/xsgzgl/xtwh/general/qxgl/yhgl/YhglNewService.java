package xsgzgl.xtwh.general.qxgl.yhgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.xgxt.xtwh.ksdh.KsdhService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicService;
import xsgzgl.xtwh.general.qxgl.GnmkModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_Ȩ�޹���_�û�����_service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */
public class YhglNewService extends BasicService{

	YhglNewDao dao = new YhglNewDao();
	
	/**
	 * ����û���Ϣ�������ͷ
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public List<HashMap<String,String>> getYhxxTop(User user,String checkbox){
		DAO dao = DAO.getInstance();
		String[] en = new String[]{};
	    String[] cn = new String[]{};
		if ("12898".equals(Base.xxdm)) {
			 en = new String[] { "pk", "yhm", "xm", "zmc", "bmmc", "sffz", "qyzt", "sffdy", "sfbzr","sfbl"	};
			 cn = new String[] { "", "�û���", "����", "������", "��������", "�Ƿ����", "����״̬","�Ƿ񸨵�Ա","�Ƿ������","�Ƿ�˼���ɼ�" };
		}else {
			 en = new String[] { "pk", "yhm", "xm", "zmc", "bmmc", "sffz", "qyzt", "sffdy", "sfbzr"	};
			 cn = new String[] { "", "�û���", "����", "������", "��������", "�Ƿ����", "����״̬","�Ƿ񸨵�Ա","�Ƿ������" };
		}

		if("no".equalsIgnoreCase(checkbox)){
			en = new String[] { "yhm", "xm", "zmc", "bmmc", "sffz", "qyzt", "sffdy", "sfbzr" };
			cn = new String[] { "�û���", "����", "������", "��������", "�Ƿ����", "����״̬","�Ƿ񸨵�Ա","�Ƿ������" };
		}
		
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * ����û���Ϣ�����
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public ArrayList<String[]> getYhxxList(YhglNewForm myForm,User user) 
		throws IllegalArgumentException, SecurityException, IllegalAccessException, 
		InvocationTargetException, NoSuchMethodException{
		
		return dao.getYhxxList(myForm, user);
		
	}
	/**
	 * ����û���Ϣ�����-���ݲ�ѯ�������
	 */
	public ArrayList<String[]> getYhxxAllList(YhglNewForm t,User user) 
	throws IllegalArgumentException, SecurityException, IllegalAccessException, 
	InvocationTargetException, NoSuchMethodException{
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getYhxxList(t, user);
		
	}
	
	/**
	 * ��֤�û����Ƿ����
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public Boolean checkYhm(String yhm){
		return dao.checkYhm(yhm);
	}
	
	/**
	 * �����û�Ȩ��
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public Boolean saveYhqx(String yhm){
		return dao.saveYhqx(yhm);
	}
	
	/**
	 * ɾ���û�
	 * 
	 * @date 2013-01-09
	 * @author zhanghui
	 */
	public Boolean deleteYhxx(YhglNewForm myForm){		
		return dao.deleteYhxx(myForm);		
	}
	
	/**
	 * ��ȡ�û���Ϣ
	 * 
	 * @date 2013-01-10
	 * @author zhanghui
	 */
	public HashMap<String,String> getYhxx(String yhm){
		return dao.getYhxx(yhm);		
	}
	
	/**
	 * 
	 * @����: ������ȡ�����˵��б�
	 * @���ߣ�zhangxiaobin [���ţ�1036]
	 * @���ڣ�2015-1-30 ����04:44:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @param zdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGnmkThrList(String yhm){
		// �����˵�
		List<HashMap<String, String>> gnmkListThr = dao.getGnmkList(null , yhm, null, 3);
		
		return gnmkListThr;
	}
	
	/**
	 * ��ȡ���еĹ���ģ��
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public List<GnmkModel> getAllGnmkList(String userName, String yhm, String zdm){
		List<GnmkModel> list = new ArrayList<GnmkModel>();
		
		// һ���˵�
		List<HashMap<String, String>> gnmkListOne = dao.getGnmkList(userName, yhm, zdm, 1);
		// �����˵�
		List<HashMap<String, String>> gnmkListTwo = dao.getGnmkList(userName, yhm, zdm, 2);
		// �����˵�
		List<HashMap<String, String>> gnmkListThr = dao.getGnmkList(userName, yhm, zdm, 3);
		
		// ��ȡ���еĹ���
		for(HashMap<String,String> mapOne : gnmkListOne){
			GnmkModel gnmkModelOne = new GnmkModel();
			String dmOne = mapOne.get("dm");
			gnmkModelOne.setGnmkdm(dmOne);
			gnmkModelOne.setGnmkmc(mapOne.get("mc"));
			gnmkModelOne.setChecked(mapOne.get("checked"));
			List<GnmkModel> childListOne = setChildList(gnmkModelOne, gnmkListTwo);
			
			for(GnmkModel gnmkModelTwo : childListOne){
				// �����˵�
				setChildList(gnmkModelTwo, gnmkListThr);
			}

			list.add(gnmkModelOne);
		}
		
		return list;
	}

	/**
	 * ��ȡ���еĹ���ģ��
	 *
	 * @date 2018-09-19
	 */
	public List<HashMap<String, String>> getAllDjGnmkList(String yhm){

		List<HashMap<String, String>> list = dao.getDjGnmkList(yhm);
		return list;
	}
	
	/**
	 * �����ӽڵ�
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	private List<GnmkModel> setChildList(GnmkModel model, List<HashMap<String, String>> list){
		List<GnmkModel> childList = new ArrayList<GnmkModel>();
		
		// ��ȡ��gnmk�µ��ӹ���ģ��
		for (HashMap<String, String> map : list){
			String dm = map.get("dm");
			if(dm.substring(0, dm.length()-2).equalsIgnoreCase(model.getGnmkdm())){
				GnmkModel gnmkModel = new GnmkModel();
				
				gnmkModel.setGnmkdm(dm);
				gnmkModel.setGnmkmc(map.get("mc"));
				gnmkModel.setDxq(map.get("dxq"));
				gnmkModel.setChecked(map.get("checked"));
	
				childList.add(gnmkModel);
			}
		}
		
		list.removeAll(childList);
		
		model.setChildList(childList);
		return childList;
	}
	
	/**
	 * �����û�Ȩ��
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public Boolean saveYhqx(String yhm,HttpServletRequest request) throws Exception{

		String[] gnmkdm=request.getParameterValues("gnmkdm");
		if(gnmkdm==null){
			gnmkdm=new String[0];
		}
		String[] sqls=new String[1+gnmkdm.length];
		
		sqls[0]="delete from yhqxb where yhm='"+yhm.replace("'", "")+"'";
		String dxq=null;
		for(int i=0;i<gnmkdm.length;i++){
			dxq=Base.chgNull(request.getParameter("dxq_"+gnmkdm[i]),"",0);
			sqls[1+i]="insert into yhqxb values('"+yhm+"','"+gnmkdm[i]+"','"+dxq+"')";
		}
		boolean flag =dao.saveArrDate(sqls);
		if(flag){
			try {
				KsdhService ksdh = new KsdhService();
				ksdh.del_Rubbish_data(yhm.replace("'", ""));
			} catch (Exception e) {
				System.out.println("���ٵ�����XG_XTWH_KSDHB��ɾ������");
			}
		}
		return flag;
	}

	/**
	 * �����û�Ȩ�� 20180921
	 */
	public Boolean yhGnsqSave(String yhm, JSONArray yhgnqxArray) throws Exception{

		DAO me = DAO.getInstance();
		String delSql = "delete from yhqxb where yhm = ? ";
		boolean result = me.runUpdate(delSql,new String[]{yhm});

		if(result){
			if(yhgnqxArray.length() > 0){
				String insertSql = "insert into yhqxb values (?,?,?)";
				List<String []> paramList = new ArrayList<String[]>();
				for(int i=0;i<yhgnqxArray.length();i++){
					JSONObject jobj = yhgnqxArray.getJSONObject(i);
					String gnmkdm = jobj.getString("gnmkdm");
					String dxq = jobj.getString("dxq").equals("null") ? null : jobj.getString("dxq");
					paramList.add(new String []{yhm,gnmkdm,dxq});
				}
				result = me.runBatchBoolean(insertSql,paramList);
			}
		}
		if(result){
			try {
				KsdhService ksdh = new KsdhService();
				ksdh.del_Rubbish_data(yhm.replace("'", ""));
			} catch (Exception e) {
				System.out.println("���ٵ�����XG_XTWH_KSDHB��ɾ������");
			}
		}
		return result;
	}
	
	/**
	 * ��ȡ�û�Ȩ�ޱ������ģ�飨���ӵģ����ٵģ�
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public HashMap<String,List<String>> getChgGnmk(String yhm,HttpServletRequest request) throws SQLException{

		//�޸ĺ���û�Ȩ��
		String[] gnmkdm=request.getParameterValues("gnmkdm");
		if(gnmkdm==null){
			gnmkdm=new String[0];
		}
		//��ǰ�û�Ȩ��
		String[] current = dao.getYhqx(yhm); 

		List<String> oldgnmk = dao.arrayToList(current);	
		List<String> newgnmk = dao.arrayToList(gnmkdm);
		
		//ȥ����ǰ���ܺ��޸ĺ�����ֵͬ��������Ĺ���ģ�飬ʣ�µ�Ϊ���ӵ�ģ��ͼ��ٵ�ģ��
		if(current != null && current.length != 0){
			for(int i=0;i<oldgnmk.size();i++){
				for(int j=0;j<newgnmk.size();j++){
					if(oldgnmk.get(i).equalsIgnoreCase(newgnmk.get(j))){
						oldgnmk.set(i, "");
						newgnmk.set(j, "");
					}
				}
			}
		}
		
		List<String> del = new ArrayList<String>();
		List<String> add = new ArrayList<String>();
		
		for(int i=0;i<oldgnmk.size();i++){
			if(!"".equalsIgnoreCase(oldgnmk.get(i))){
				del.add(oldgnmk.get(i));
			}
		}
		for(int i=0;i<newgnmk.size();i++){
			if(!"".equalsIgnoreCase(newgnmk.get(i))){
				add.add(newgnmk.get(i));
			}
		}
		
		HashMap<String,List<String>> map = new HashMap<String, List<String>>();
		map.put("add", add);	//�����Ĺ���ģ�����
		map.put("del", del);	//ɾ���Ĺ���ģ�����
				
		return map;
	}
	
	/**
	 * �����û�Ȩ��
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public Boolean saveYhqx(String yhm,HttpServletRequest request,HashMap<String,List<String>> map){
		
		List<String> add = map.get("add");		//�û�����������
		List<String> del = map.get("del");		//�û�����ٹ���
		String[] gnmkdm=request.getParameterValues("gnmkdm");//�û���дȨ�ޱ��� 2013-3-19
		String[] sqls=new String[add.size()*2+del.size()+gnmkdm.length];
		
		String dxq=null;
		int i=0;
		for(i=0;i<del.size();i++){
			sqls[i] = "delete from yhqxb where yhm ='"+yhm.replace("'", "")+"' and gnmkdm ='"+del.get(i)+"'";
		}
		for(int j=0;j<add.size();j++){	
			sqls[i+j]="delete from yhqxb where yhm ='"+yhm.replace("'", "")+"' and gnmkdm ='"+add.get(j)+"'";
		}
		i=i+add.size();
		for(int j=0;j<add.size();j++){	
			dxq=Base.chgNull(request.getParameter("dxq_"+add.get(j)),"",0);
			sqls[i+j]="insert into yhqxb (yhm,gnmkdm,dxq) values ('"+yhm.replace("'", "")+"','"+add.get(j)+"','"+dxq+"') ";
		}
		//�û���дȨ�ޱ��� 2013-3-19 
		for (int j = 0; j < gnmkdm.length; j++) {
			dxq=Base.chgNull(request.getParameter("dxq_"+gnmkdm[j]),"",0);
			sqls[i+j]=" update yhqxb b set b.dxq ='"+dxq+"' where b.gnmkdm = '"+gnmkdm[j]+"'";
		}
		boolean flag = true;
		try {
			flag = dao.saveArrDate(sqls);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ��ʼ���û�����
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public Boolean cshYhmm(YhglNewForm myForm){		
		return dao.cshYhmm(myForm);		
	}
	
	/**
	 * ��ȡ����Ա��Ϣ
	 * 
	 * @date 2013-01-14
	 * @author zhanghui
	 */
	public HashMap<String,String> getFdyxx(String yhm){
		return dao.getRsInfo("fdyxxb", "zgh", yhm, 
				new String[]{"zgh","xm","bmdm"});		
	}
	
	/**
	 * �����޸��û���Ϣ
	 * 
	 * @date 2013-01-18
	 * @author zhanghui
	 */
	public Boolean plUpdateYhxx(YhglNewForm myForm){		
		return dao.plUpdateYhxx(myForm);		
	}
	/**
	 * ����ȡ������
	 */
	public Boolean plQxYhxx(YhglNewForm myForm){		
		return dao.plQxYhxx(myForm);		
	}
	/**
	 * �û�����
	 */
	public Boolean yhfz(YhglNewForm myForm){		
		return dao.yhfz(myForm);		
	}
	/**
	 * �û�ͣ��
	 */
	public Boolean yhty(YhglNewForm myForm){		
		return dao.yhty(myForm);		
	}
	/**
	 * �û�˼���ɼ�
	 * @throws Exception 
	 */
	public int[] szkj(String[] zghs, String sfbl) throws Exception {
		// TODO Auto-generated method stub
		return dao.szkj(zghs,sfbl);
	}
}
