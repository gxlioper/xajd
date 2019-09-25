package xsgzgl.xtwh.general.qxgl.yhzgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.xgxt.xtwh.ksdh.KsdhService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.xtwh.general.qxgl.GnmkModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_Ȩ�޹���_�û������_service��
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
public class YhzglNewService extends BasicService{
	
	YhzglNewDao dao = new YhzglNewDao();
	
	/**
	 * ����û�����Ϣ�������ͷ
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public List<HashMap<String,String>> getYhzxxTop(User user){
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "zdm", "zmc", "yhsxs"	};
		String[] cn = new String[] { "", "������", "�û���" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * ����û�����Ϣ�����
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ArrayList<String[]> getYhzxxList(YhzglNewForm myForm,User user) 
		throws IllegalArgumentException, SecurityException, IllegalAccessException, 
		InvocationTargetException, NoSuchMethodException{
		
		return dao.getYhzxxList(myForm, user);
		
	}
	
	/**
	 * ��֤�û��������Ƿ����
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean checkZmc(String zmc){
		return dao.checkZmc(zmc);
	}
	
	/**
	 * �����û���
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean addYhzxx(YhzglNewForm myForm){		
		return dao.addYhzxx(myForm);		
	}
	
	/**
	 * �޸��û���
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean updateYhzxx(YhzglNewForm myForm){		
		return dao.updateYhzxx(myForm);		
	}
	/**
	 * �����û���
	 * 
	 * @date 2013-03-19
	 * @author zhangjw
	 */
	public Boolean copyYhzxx(YhzglNewForm myForm){		
		return dao.copyYhzxx(myForm);		
	}

	/**
	 * �ж�ѡ�е��û����Ƿ��û�
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean checkYhz(String[] yhz){
		return dao.checkYhz(yhz);
	}
	
	/**
	 * ɾ���û���
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean deleteYhzxx(YhzglNewForm myForm){		
		return dao.deleteYhzxx(myForm);		
	}
	
	/**
	 * ��ȡ���еĹ���ģ��
	 * 
	 * @date 2013-01-10
	 * @author zhanghui
	 */
	public List<GnmkModel> getAllGnmkList(String userName, String zdm){
		List<GnmkModel> list = new ArrayList<GnmkModel>();
		
		// һ���˵�
		List<HashMap<String, String>> gnmkListOne = dao.getGnmkList(zdm, 1);
		// �����˵�
		List<HashMap<String, String>> gnmkListTwo = dao.getGnmkList(zdm, 2);
		// �����˵�
		List<HashMap<String, String>> gnmkListThr = dao.getGnmkList(zdm, 3);
		
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
	public List<HashMap<String, String>> getAllDjGnmkList(String zdm){

		List<HashMap<String, String>> list = dao.getDjGnmkList(zdm);
		return list;
	}
	
	/**
	 * �����ӽڵ�
	 * 
	 * @date 2013-01-10
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
	 * ����û�����Ϣ
	 * 
	 * @date 2013-01-10
	 * @author zhanghui
	 */
	public HashMap<String,String> getYhzxx(String zdm){
		return dao.getYhzxx(zdm);
	}
	
	/**�����û���Ȩ��
	 * 
	 * @date 2013-03-19
	 * @author zhangjw
	 */
	public Boolean saveYhzqx(String zdm,HttpServletRequest request) throws Exception{
		String[] gnmkdm=request.getParameterValues("gnmkdm");
		if(gnmkdm==null){
			gnmkdm=new String[0];
		}
		String[] sqls=new String[2+gnmkdm.length];
		//�û���Ȩ�޲�����ʱ��
		sqls[0]="insert into yhzqxb_lsb select * from yhzqxb b where b.zdm ='"+zdm+"'";
		//ɾ���û���Ȩ�ޱ�
		sqls[1]="delete from yhzqxb where zdm='"+zdm.replace("'", "")+"'";
		String dxq=null;
		for(int i=0;i<gnmkdm.length;i++){
			dxq=Base.chgNull(request.getParameter("dxq_"+gnmkdm[i]),"",0);
			sqls[2+i]="insert into yhzqxb values('"+zdm+"','"+gnmkdm[i]+"','"+dxq+"')";
		}
		
		boolean flag = dao.saveArrDate(sqls);
		return flag;
	}


	/**�����û���Ȩ��
	 *
	 * @date 2018-09-19
	 */
	public Boolean yhzGnsqSave(String zdm,JSONArray yhzgnqxArray) throws Exception{

		DAO me = DAO.getInstance();

		String lsbSql = "insert into yhzqxb_lsb select * from yhzqxb b where b.zdm = ? ";
		boolean result = me.runUpdate(lsbSql,new String[]{zdm});

		String delSql = "delete from yhzqxb where zdm= ? ";
		result = me.runUpdate(delSql,new String[]{zdm});

		if(result){
			if(yhzgnqxArray.length() > 0){
				String insertSql = "insert into yhzqxb values (?,?,?)";
				List<String []> paramList = new ArrayList<String[]>();
				for(int i=0;i<yhzgnqxArray.length();i++){
					JSONObject jobj = yhzgnqxArray.getJSONObject(i);
					String gnmkdm = jobj.getString("gnmkdm");
					String dxq = jobj.getString("dxq").equals("null") ? null : jobj.getString("dxq");
//					paramList.add(new String []{zdm,gnmkdm,dxq});
					result = me.runUpdate(insertSql,new String[]{zdm,gnmkdm,dxq});
					if(!result){
						break;
					}
				}
//				result = me.runBatchBoolean(insertSql,paramList);
			}
		}

		if(result){
			saveYhqx(zdm);
		}

		return result;
	}
	
	/**
	 * ��ȡ�û���Ȩ�ޱ������ģ�飨���ӵģ����ٵģ�
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public HashMap<String,List<String>> getChgGnmk(String zdm,HttpServletRequest request) throws SQLException{

		//�޸ĺ���û���Ȩ��
		String[] gnmkdm=request.getParameterValues("gnmkdm");
		if(gnmkdm==null){
			gnmkdm=new String[0];
		}
		//��ǰ�û���Ȩ��
		String[] current = dao.getYhzqx(zdm); 

		List<String> oldgnmk = dao.arrayToList(current);	
		List<String> newgnmk = dao.arrayToList(gnmkdm);
				
		//��ǰ���ܺ��޸ĺ�����ֵͬ��������Ĺ���ģ�飬ʣ�µ�Ϊ���ӵ�ģ��ͼ��ٵ�ģ��
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
	 * �����û�Ȩ�ޣ��޸���Ȩ��ʱ��ͬʱ�޸��û�Ȩ�ޣ�
	 * 
	 * @date 2013-03-19
	 * @author zhangjw
	 */
	public Boolean saveYhqx(String zdm){
		CommDAO dao=new CommDAO();


		boolean flag = true;
		try {
			//�Ȳ�ѯ�����������û�
			String[] sqls=new String[3];
			//ɾ�������û�����Ȩ��
			String sql = "delete from yhqxb a where (a.yhm,a.gnmkdm) in (select b.yhm,a.gnmkdm from yhzqxb_lsb a left join view_yhz_yhxxb b on a.zdm=b.zdm)";
			sqls[0]=sql;
			
			StringBuffer sb = new StringBuffer();
			sb.append("merge into yhqxb t1 ");
			sb.append("using (select b.yhm,a.gnmkdm,a.dxq from yhzqxb a left join view_yhz_yhxxb b on a.zdm=b.zdm where b.zdm ='"+zdm+"') t2 ");
			sb.append("on (t1.yhm=t2.yhm and t1.gnmkdm=t2.gnmkdm) ");
			sb.append("when matched then ");
			sb.append("  update set t1.dxq = t2.dxq ");
			sb.append("when not matched then ");
			sb.append("insert values(t2.yhm,t2.gnmkdm,t2.dxq) ");
			
			//ɾ���û�����ʱ���¼
			sqls[1]=sb.toString();
			sqls[2]="truncate table yhzqxb_lsb";
			flag = dao.saveArrDate(sqls);
			if(flag){
				try {
					KsdhService ksdh = new KsdhService();
					ksdh.del_Rubbish_data_yhz(zdm.replace("'", ""));
				} catch (Exception e) {
					System.out.println("���ٵ�����XG_XTWH_KSDHB��ɾ������");
				}
			}
		} catch (SQLException e1) {
			System.out.println("������û�������ѯ�û��������û��쳣");
			e1.printStackTrace();
		} catch (Exception e) {
			System.out.println("�����û���Ȩ��ʱ�����û�Ȩ�޸���ʧ��");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public String getYhzmc(String keyValue) throws Exception{
		return dao.getYhzmc(keyValue)[0];
	}
}
