package xgxt.fdygl.util;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.New_Random_ID;
import xgxt.DAO.*;
import xgxt.base.DealString;
import xgxt.base.Encrypt;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.xljk.lrh_Util.util.*;
import xgxt.form.FdyglForm;
public class fdygl_util {
	DAO dao=DAO.getInstance();
	public StandardOperation myop = new StandardOperation();
	public New_Random_ID newId= new New_Random_ID();
	public lrh_commen_util commen_util= new lrh_commen_util();
	DealString deal = new DealString();
//    HttpServletRequest request;
//	
//	public void rcgl_rcxw_util(HttpServletRequest request){
//		this.request = request;
//	}
	public List get_sfyqxList()
	{
		List li=commen_util.get_dmwhb_dmList2("fdydlqxgl");
		return li;
	}
	
	public List fdyqxgl_search(FdyglForm myform)
	{
		List li=null;
		String xydm=myform.getXydm();
		String xm=myform.getXm();
		String zgh=myform.getZgh();
		String sfyqx=myform.getSfyqx();
		boolean flag=commen_util.check_user_input(xm);
		if(true==flag)
		{
			flag=commen_util.check_user_input(zgh);
			if(true==flag)
			{
				sfyqx=commen_util.xljk_dmwhb_dmzh(sfyqx);
				String tableName="view_fdydlqxxx";
				String [] columes={"bmdm","sfyqx"};
				String [] values={xydm,sfyqx};
				String [] like_com={"zgh","xm"};
				String [] like_val={zgh,xm};
				String sql=commen_util.select_sql_yuju2(tableName, columes, values,like_com,like_val);
				li=dao.getList(sql, new String [] {}, new String [] {"zgh","xm","xb","bmmc","sfyqx"});
			}
		}
		return li;
	}
	
	public List get_TopTr()
	{
		String tableName="VIEW_FDYDLQXXX";
		String [] zdm={"zgh","xm","xb","bmmc","sfyqx"};
		List li=commen_util.Get_Table_Title(tableName, zdm);
		return  li;
	}
	
	public List get_fdyList()
	{
		List li=null;
		String sql="select zgh,bmdm xydm,xm from fdyxxb ";
		li=dao.getListNotOut(sql, new String []{});
		return li;
	}
	
	public List<HashMap<String, String>> get_haveQx_fdyList()
	{
		List<HashMap<String, String>> li=null;
		String sql="select distinct(yhm) zgh,bmdm xydm from view_yhzxxqxb ";
		li=dao.getListNotOut(sql, new String []{});
		return li;
	}
	
	public List<HashMap<String, String>> get_noQx_fdyList()
	{
		List<HashMap<String, String>> li=null;
		String sql="select zgh,bmdm xydm from fdyxxb a where not exists (select 1 from view_yhzxxqxb b where a.zgh=b.yhm)";
		li=dao.getListNotOut(sql, new String []{});
		return li;
	}
	
	public List get_fdyyhzQx_list()
	{
		List li=null;
		String sql="select gnmkdm,dxq from yhzqxb where zdm=( select zdm from yhzb where zmc = '辅导员') ";
		li=dao.getListNotOut(sql, new String [] {});
		return li;
	}
	
	public static boolean testRecordExist(String tableName, String primaryKey,
			String pkValue) {
		DAO dao = DAO.getInstance();
		String sql = "select count(1) cont from " + tableName + " where "
				+ primaryKey + "=?";
		String cont = dao.getOneRs(sql, new String[] { pkValue }, "cont");
		return (cont != null && !cont.equals("0")) ? true : false;
	}
	
	public boolean update_fdyqxxxb(HttpServletRequest request) throws Exception
	{
		boolean flag=false;
		List<HashMap<String, String>> haveQxFdy=this.get_haveQx_fdyList();
		List<HashMap<String, String>> noQxFdy=this.get_noQx_fdyList();
		String sql="delete from FDYDLQXXXB";
		String tableName="FDYDLQXXXB";
		flag=dao.runUpdate(sql, new String [] {});
//		int sss=haveQxFdy.size();
//		int aaa=noQxFdy.size();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] sqlT = new String[haveQxFdy.size()+noQxFdy.size()];
		int j = 0;
		for(int i=0;i<haveQxFdy.size();i++)
		{
			map=haveQxFdy.get(i);
			String newId=this.newId.new_xnid(tableName);
			sqlT[j] = "insert into FDYDLQXXXB(XNID,ZGH,SFYQX,XYDM) values('"+newId+"','"+map.get("zgh")+"','有权限','"+map.get("xydm")+"')";
			j++;
		}
		for(int i=0;i<noQxFdy.size();i++)
		{
			map=noQxFdy.get(i);
			String newId=this.newId.new_xnid(tableName);
			sqlT[j] = "insert into FDYDLQXXXB(XNID,ZGH,SFYQX,XYDM) values('"+newId+"','"+map.get("zgh")+"','无权限','"+map.get("xydm")+"')";
			j++;
		}
		dao.runBatch(sqlT);
		return flag;
	}
	
	public FdyglForm deal_gbk(FdyglForm myform)
	{
		myform.setXm(DealString.toGBK(myform.getXm()));
		myform.setZgh(DealString.toGBK(myform.getZgh()));
		return myform;
	}
	
	
	public boolean fpqx_fdyqxgl(String xzstr,HttpServletRequest request) throws Exception
	{
		String s1="!!SplitSignOne!!";
		String [] zghs=xzstr.split(s1);
		String mm="888888";
		Encrypt ept = new Encrypt();
		mm=ept.encrypt(mm);
		List fdyyhzQx=this.get_fdyyhzQx_list();
		String sql="select zdm from yhzb where zmc = '辅导员' ";
		HashMap map_fdyzdm=new HashMap();
		map_fdyzdm=dao.getMapNotOut(sql, new String []{});
		boolean flag=false;
		boolean record_exits=false;
		for(int i=0;i<zghs.length;i++)
		{
			String zgh=zghs[i];
			sql="select xm,bmdm from fdyxxb where zgh='"+zgh+"'";
			HashMap map_fdyxx=new HashMap();
			map_fdyxx=dao.getMapNotOut(sql, new String []{});
//			HashMap map_fdyxx2=new HashMap();
//			map_fdyxx2=dao.getMapNotOut("select xm,bmdm from fdyxxb where zgh='001000010'",  new String []{});
			record_exits=testRecordExist("yhb","yhm",zgh);
			if(false==record_exits)
			{
				String zdm=map_fdyzdm.get("zdm").toString();
				String xm=map_fdyxx.get("xm").toString();
				String bmdm=map_fdyxx.get("bmdm").toString();
				flag=StandardOperation.insert("yhb", new String []{"yhm","kl","zdm","xm","szbm","dwdm","qx"}, new String[] {zgh,mm,zdm,xm,bmdm,"01","1"},request);
				if(true==flag)
				{
					HashMap map_fdyyhzQx=new HashMap();
					String[] sqlT = new String[fdyyhzQx.size()];
					for(int j=0;j<fdyyhzQx.size();j++)
					{
						map_fdyyhzQx=(HashMap)fdyyhzQx.get(j);
						sql="select count(*) con from yhqxb where yhm=? and gnmkdm=? " ;
						String test_count=dao.getOneRs(sql, new String[]{zgh,map_fdyyhzQx.get("gnmkdm").toString()}, "con");
						if("0".equalsIgnoreCase(test_count))
						{
							sqlT[j] = "insert into yhqxb(yhm,gnmkdm,dxq) values('"+zgh+"','"+map_fdyyhzQx.get("gnmkdm").toString()+"','"+map_fdyyhzQx.get("dxq").toString()+"')";
						}
					}
					dao.runBatch(sqlT);
				}
			}
			else
			{
				
				flag=StandardOperation.update("yhb", new String[]{"kl"}, new String[]{mm}, "yhm", zgh,request);
				HashMap map_fdyyhzQx=new HashMap();
				String[] sqlT = new String[fdyyhzQx.size()];
				for(int j=0;j<fdyyhzQx.size();j++)
				{
					map_fdyyhzQx=(HashMap)fdyyhzQx.get(j);
					sql="select count(*) con from yhqxb where yhm=? and gnmkdm=? " ;
					String test_count=dao.getOneRs(sql, new String[]{zgh,map_fdyyhzQx.get("gnmkdm").toString()}, "con");
					if("0".equalsIgnoreCase(test_count))
					{
						sqlT[j] = "insert into yhqxb(yhm,gnmkdm) values('"+zgh+"','"+map_fdyyhzQx.get("gnmkdm").toString()+"')";
					}
				}
				dao.runBatch(sqlT);
			}
		}
		return flag;
	}
	
	
	public boolean shqx_fdyqlqxxxb(String xzstr,HttpServletRequest request) throws Exception
	{
		boolean flag=false;
		String s1="!!SplitSignOne!!";
		String [] zghs=xzstr.split(s1);
		String[] sqlT = new String[zghs.length*2];
		int j = 0;
		for(int i=0;i<zghs.length;i++)
		{
			sqlT[j] = "delete yhb where yhm='"+zghs[i]+"'";
			j++;
			sqlT[j] = "delete from yhqxb where yhm ='"+zghs[i]+"'";
			j++;
		}
		dao.runBatch(sqlT);
		return flag;
	}	
	
}
