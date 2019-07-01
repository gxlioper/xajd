package xsgzgl.gygl.gyjldmgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;

public class GyjldmglDao extends DAO{
	
	/**
	 * ��ü��ɴ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJldlList(GyjldmglForm model) throws Exception{
		String searchTj="";
		List<String> inputV=new ArrayList<String>();
		String sql = "select * from (select a.*,rownum r from xg_gygl_new_gyjllbdlb a order by a.gyjllbdldm) ";
		String[] output = new String[] { "GYJLLBDLDM","GYJLLBDLDM","GYJLLBDLMC"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[]{}),
				output, model);
		
		
	}
	
	/**
	 * ������ɴ�����Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String saveJldlInfo(GyjldmglForm model,String type) throws Exception{
		String msg="��������";
		boolean b = false;
		String sql;
		if("add".equals(type)){
			sql="select count(1) num from xg_gygl_new_gyjllbdlb where GYJLLBDLDM=?";
			String num=this.getOneRs(sql, new String[]{model.getJldldm()}, "num");
			if("0".equals(num)){
				sql="insert into xg_gygl_new_gyjllbdlb(GYJLLBDLDM,GYJLLBDLMC) values(?,?)";
				b=this.runUpdate(sql,new String[]{model.getJldldm(),model.getJldlmc()});
				msg=(b?"�����ɹ���":"����ʧ�ܣ�");
			}else{
				msg="�ü��ɴ�������Ѵ��ڣ�";
			}
		}else if("update".equals(type)){
			sql="update xg_gygl_new_gyjllbdlb set GYJLLBDLMC=? where GYJLLBDLDM=?";
			b=this.runUpdate(sql,new String[]{model.getJldlmc(),model.getJldldm()});
			msg=(b?"�����ɹ���":"����ʧ�ܣ�");
		}
		return msg;
	}
	
	/**
	 * ɾ�����ɴ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String deleteJldlInfo(GyjldmglForm model) throws Exception{
		String b = "exists";
		String sql="select count(*) num from xg_gygl_new_gyjlb where GYJLLBDLDM=?";
		String num = this.getOneRs(sql, new String[]{model.getJldldm()}, "num");
		if(num.equals("0")){
				sql="delete from xg_gygl_new_gyjllbdlb where GYJLLBDLDM=?";
				boolean result = this.runUpdate(sql, new String[]{model.getJldldm()});
				b = result ? "yes" : "no";
		}
		return b;
	}
	/**
	 * ��ü��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJllbList(GyjldmglForm model) throws Exception{
		String searchTj="";
		List<String> inputV=new ArrayList<String>();
		String sql = "select a.*,rownum r from (select a.*,b.gyjllbdlmc  from xg_gygl_new_gyjllbdmb a " +
				"left join xg_gygl_new_gyjllbdlb b on a.gyjllbdldm = b.gyjllbdldm order by a.GYJLLBDM) a";
		String[] output = new String[] { "GYJLLBDM","GYJLLBDLDM","GYJLLBDM","GYJLLBMC","GYJLLBKF","GYJLLBDLMC"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[]{}),
				output, model);
		
		
	}
	
	/**
	 * ������������Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String saveJllbInfo(GyjldmglForm model,String type) throws Exception{
		String msg="��������";
		boolean b = false;
		String sql;
		if("add".equals(type)){
			sql="select count(1) num from xg_gygl_new_gyjllbdmb where GYJLLBDM=?";
			String num=this.getOneRs(sql, new String[]{model.getJllbdm()}, "num");
			if("0".equals(num)){
				sql="insert into xg_gygl_new_gyjllbdmb(GYJLLBDM,GYJLLBMC,GYJLLBDLDM,gyjllbkf) values(?,?,?,?)";
				b=this.runUpdate(sql,new String[]{model.getJllbdm(),model.getJllbmc(),model.getJldldm(),model.getJllbkf()});
				msg=(b?"�����ɹ���":"����ʧ�ܣ�");
			}else{
				msg="�ü����������Ѵ��ڣ�";
			}
		}else if("update".equals(type)){
			sql="update xg_gygl_new_gyjllbdmb set GYJLLBMC=?,GYJLLBDLDM=? ,gyjllbkf=? where GYJLLBDM=?";
			b=this.runUpdate(sql,new String[]{model.getJllbmc(),model.getJldldm(),model.getJllbkf(),model.getJllbdm()});
			msg=(b?"�����ɹ���":"����ʧ�ܣ�");
		}
		return msg;
	}
	
	/**
	 * ɾ�����������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String deleteJllbInfo(GyjldmglForm model) throws Exception{
		String b = "exists";
		String sql="select count(*) num from xg_gygl_new_gyjlb where GYJLLBDM=?";
		String num = this.getOneRs(sql, new String[]{model.getJllbdm()}, "num");
		if(num.equals("0")){
			sql="delete from xg_gygl_new_gyjllbdmb where GYJLLBDM=?";
			boolean result = this.runUpdate(sql, new String[]{model.getJllbdm()});
			b = result ?"yes":"no";
		}
		return b;
	}
	/**
	 * ��ü������List<HashMap<String,String>>
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJllbListMap(GyjldmglForm model) {
		String sql="select GYJLLBDLDM jldldm,GYJLLBDLMC jldlmc from xg_gygl_new_gyjllbdlb order by GYJLLBDLDM";
		return this.getList(sql, new String[]{}, new String[]{"jldldm","jldlmc"});
	}
	
	/**
	 * ���洦�������Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String saveCflbInfo(GyjldmglForm model,String type) throws Exception{
	
		String msg="��������";
		boolean b = false;
		String sql;
		if("add".equals(type)){
			sql="select count(1) num from xg_gygl_new_gyjlcflbb where GYJLCFDM=?";
			String num=this.getOneRs(sql, new String[]{model.getCflbdm()}, "num");
			if("0".equals(num)){
				sql="insert into xg_gygl_new_gyjlcflbb(GYJLCFDM,GYJLCFMC) values(?,?)";
				b=this.runUpdate(sql,new String[]{model.getCflbdm(),model.getCflbmc()});
				msg=(b?"�����ɹ���":"����ʧ�ܣ�");
			}else{
				msg="�ô����������Ѵ��ڣ�";
			}
		}else if("update".equals(type)){
			sql="update xg_gygl_new_gyjlcflbb  set GYJLCFMC=? where GYJLCFDM=? ";
			b=this.runUpdate(sql,new String[]{model.getCflbmc(),model.getCflbdm()});
			msg=(b?"�����ɹ���":"����ʧ�ܣ�");
		}else if("delete".equals(type)){
			
			StringBuilder check=new StringBuilder();
			check.append(" select cljg from xg_gygl_new_gyjlb where cljg=? ");
			String gyjlcfdm=this.getOneRs(check.toString(), new String[]{model.getCflbdm()},"cljg");
			
			if(Base.isNull(gyjlcfdm)){
				sql="delete from  xg_gygl_new_gyjlcflbb  where GYJLCFDM=? ";
				b=this.runUpdate(sql,new String[]{model.getCflbdm()});
				msg=(b?"�����ɹ���":"����ʧ�ܣ�");
			}else{
				msg="�������Ѿ�ʹ�ã�����ɾ����";
			}
		}
		return msg;
	}
	
	/**
	 * ��ô��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCflbList(GyjldmglForm model) throws Exception{
		String searchTj="";
		List<String> inputV=new ArrayList<String>();
		String sql = "select * from (select a.*,rownum r from xg_gygl_new_gyjlcflbb a order by a.GYJLCFDM) ";
		String[] output = new String[] { "GYJLCFDM","GYJLCFDM","GYJLCFMC"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[]{}),
				output, model);
		
		
	}
	
	
}
