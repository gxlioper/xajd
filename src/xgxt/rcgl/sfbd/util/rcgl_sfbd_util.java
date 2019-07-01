package xgxt.rcgl.sfbd.util;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.Check_Input_Value;
import xgxt.utils.New_Random_ID;
import xgxt.DAO.*;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.xljk.lrh_Util.util.*;
import xgxt.rcgl.sfbd.form.*;
public class rcgl_sfbd_util {
	
	DAO mydao= DAO.getInstance();
	StandardOperation myop = new StandardOperation();
	New_Random_ID newId= new New_Random_ID();
	lrh_commen_util commen_util= new lrh_commen_util();
	HttpServletRequest request;
	
	public void rcgl_sfbd_util1(HttpServletRequest request){
		this.request = request;
	}
	
	public String sfbd_xnxq_add(rcgl_sfbd_form myform)
	{
		boolean flag=false;
		String xn_dm=myform.getXn_dm();
		String xq_dm=myform.getXq_dm();
		String xn=commen_util.get_rcgl_getMKMC(xn_dm);
		String xq=commen_util.get_rcgl_getMKMC(xq_dm);
		String sfbd=commen_util.get_rcgl_getMKMC("008rcgl_bdbj");
		String message="";
		
		String usql="select a.XN_ID from ";
		String []oth={"xn","xq"};
		String []othV={xn,xq};
		List testLi=commen_util.Find_By_OtherKey("rcgl_xssfbdb",usql, oth, othV);
		if(testLi.size()!=0)
		{
			message="date_exits";
			return message;
		}
		
		String tableName="VIEW_XSJBXX";
		String usersql="select XH from ";
		String []otherKeys={};
		String []otherKeyValues={};
		List<HashMap<String, String>> xsList=commen_util.Find_By_OtherKey(tableName, usersql, otherKeys, otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
 		int tempLen=xsList.size();
 		String xn_id="";
 		String xh="";
 		for(int i=0;i<tempLen;i++)
 		{
 			map=xsList.get(i);
 			xn_id=newId.new_xnid("rcgl_sfbd");
 			xh=map.get("XH");
 			String []columns={"XN_ID","XH","XQ","XN","SFBD"};
 			String []values={xn_id,xh,xq,xn,sfbd};
 			flag=StandardOperation.insert("rcgl_xssfbdb", columns, values, this.request);
 		}
 		if(flag==false)
 		{
 			message="insert_fail";
 		}
 		else
 		{
 			message="insert_success";
 		}
		return message;
	}
	
	public String sfbd_xs_add(rcgl_sfbd_form myform)
	{
		boolean flag=false;
		String xn_dm=myform.getXn_dm();
		String xq_dm=myform.getXq_dm();
		String xn=commen_util.get_rcgl_getMKMC(xn_dm);
		String xq=commen_util.get_rcgl_getMKMC(xq_dm);
		String sfbd=commen_util.get_rcgl_getMKMC("008rcgl_bdbj");
		String xh=myform.getXh();
		String message="";
		
		String usql="select a.XN_ID from ";
		String []oth={"xn","xq","xh"};
		String []othV={xn,xq,xh};
		List testLi=commen_util.Find_By_OtherKey("rcgl_xssfbdb",usql, oth, othV);
		if(testLi.size()!=0)
		{
			message="date_exits";
			return message;
		}
		String xn_id=newId.new_xnid("rcgl_sfbd");
		String []columns={"XN_ID","XH","XQ","XN","SFBD"};
		String []values={xn_id,xh,xq,xn,sfbd};
		flag=StandardOperation.insert("rcgl_xssfbdb", columns, values, this.request);
		
		if(flag==false)
 		{
 			message="insert_fail";
 		}
 		else
 		{
 			message="insert_success";
 		}
		return message;
	}
	
	public List sfbd_search(rcgl_sfbd_form myform)
	{
		String xn_dm=myform.getXn_dm();
		String xq_dm=myform.getXq_dm();
		String sfbd_dm=myform.getSfbd_dm();
		
		String xh=myform.getXh();
		String xm=myform.getXm();
		
		String xydm=myform.getXydm();
		String bjdm=myform.getBjdm();

		String sfbd=commen_util.get_rcgl_getMKMC(sfbd_dm);
		String xn=commen_util.get_rcgl_getMKMC(xn_dm);
		String xq=commen_util.get_rcgl_getMKMC(xq_dm);
		
		StringBuffer other=new StringBuffer();
		StringBuffer otherV=new StringBuffer();
		StringBuffer lik=new StringBuffer();
		StringBuffer likV=new StringBuffer();
		String s1="!!SplitSignOne!!";
		List li=null;
		
		if(xh!=null&&!xh.equalsIgnoreCase(""))
		{
			boolean chk=Check_Input_Value.check2(xh);
			if(chk==false)
			{
				return li;
			}
			lik.append("XH"+s1);
			likV.append("%"+xh+"%"+s1);
		}
		if(xm!=null&&!xm.equalsIgnoreCase(""))
		{
			boolean chk=Check_Input_Value.check2(xh);
			if(chk==false)
			{
				return li;
			}
			lik.append("XM"+s1);
			likV.append("%"+xm+"%"+s1);
		}
		
		if(sfbd!=null&&!sfbd.equalsIgnoreCase(""))
		{
			other.append("SFBD"+s1);
			otherV.append(sfbd+s1);
		}
		if(xn!=null&&!xn.equalsIgnoreCase(""))
		{
			other.append("XN"+s1);
			otherV.append(xn+s1);
		}
		if(xq!=null&&!xq.equalsIgnoreCase(""))
		{
			other.append("XQ"+s1);
			otherV.append(xq+s1);
		}
		
		if(bjdm!=null&&!bjdm.equalsIgnoreCase(""))
		{
			other.append("BJDM"+s1);
			otherV.append(bjdm+s1);
		}
		
		if(xydm!=null&&!xydm.equalsIgnoreCase(""))
		{
			other.append("XYDM"+s1);
			otherV.append(xydm+s1);
		}
		//view_njxyzybj  VIEW_RCGL_SFBD
		String tableName="VIEW_RCGL_SFBD";
		String usersql="select a.XN_ID,a.XH,a.XQ,a.XN,a.SFBD,a.MKMC,a.MKSS,a.XM,a.XB,a.NJ,a.XYDM,a.XYMC,a.ZYDM,a.ZYMC,a.BJDM,a.BJMC from";
		
		String [] otherKeys={};
		String [] otherKeyValues={};
		String [] like={};
		String [] likeVal={};
		String []order={};
		
		if(other.toString().equals(""))
		{
		}
		else {
			otherKeys=other.toString().split(s1);
			otherKeyValues=otherV.toString().split(s1);
		}
		
		if(lik.toString().equals(""))
		{
		}
		else
		{
			like=lik.toString().split(s1);
			likeVal=likV.toString().split(s1);
		}
	    li=commen_util.Find_By_OtherKey4(tableName,usersql, otherKeys, otherKeyValues,like,likeVal,order);
	    return li;	
	}
	
	public rcgl_sfbd_form sfbd_view(rcgl_sfbd_form myform)
	{
		String xn_id=myform.getRcgl_sfbd_xnid();
		String tableName="VIEW_RCGL_SFBD";
		String usersql="select a.XN_ID,a.XH,a.XQ,a.XN,a.SFBD,a.MKMC,a.MKSS,a.XM,a.XB,a.NJ,a.XYDM,a.XYMC,a.ZYDM,a.ZYMC,a.BJDM,a.BJMC from";
		String []otherKeys={"XN_ID"};
		String []otherKeyValues={xn_id};
		List<HashMap<String, String>> li=commen_util.Find_By_OtherKey(tableName, usersql, otherKeys, otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
 		int tempLen=li.size();
 		for(int i=0;i<tempLen;i++)
 		{
 			map=li.get(i);
 			myform.setXh(map.get("XH"));
 			myform.setXq(map.get("XQ"));
 			myform.setXn(map.get("XN"));
 			myform.setSfbd(map.get("SFBD"));
 			myform.setXb(map.get("XB"));
 			myform.setXm(map.get("XM"));
 			myform.setXymc(map.get("XYMC"));
 			myform.setBjmc(map.get("BJMC"));
 		}
 		return myform;
	}
	
	public String sfbd_qrbd(rcgl_sfbd_form myform) throws Exception
	{
		String s1="!!SplitSignOne!!";
		String []xzstr=myform.getXzstr().split(s1);
		String sfbd=commen_util.get_rcgl_getMKMC("007rcgl_bdbj");
		String primaryKey="XN_ID";
		boolean flag=false;
		for(int i=0;i<xzstr.length;i++)
		{
			String tableName="rcgl_xssfbdb";
			String []columns={"SFBD"};
			String []values={sfbd};
			flag=StandardOperation.update(tableName, columns, values, primaryKey, xzstr[i], this.request);
			if(false==flag)
			{
				return "update_fail";
			}
		}
		return "update_success";
	}
	
	public String sfbd_qxbd(rcgl_sfbd_form myform) throws Exception
	{
		String s1="!!SplitSignOne!!";
		String []xzstr=myform.getXzstr().split(s1);
		String sfbd=commen_util.get_rcgl_getMKMC("008rcgl_bdbj");
		String primaryKey="XN_ID";
		boolean flag=false;
		for(int i=0;i<xzstr.length;i++)
		{
			String tableName="rcgl_xssfbdb";
			String []columns={"SFBD"};
			String []values={sfbd};
			flag=StandardOperation.update(tableName, columns, values, primaryKey, xzstr[i], this.request);
			if(false==flag)
			{
				return "update_fail";
			}
		}
		return "update_success";
	}
	
	public String sfbd_del(rcgl_sfbd_form myform) throws Exception 
	{
		String primaryKey="XN_ID";
		boolean flag=false;
		String tableName="rcgl_xssfbdb";
		String value=myform.getXzstr();
		flag=StandardOperation.delete(tableName, primaryKey, value, this.request);
		if(false==flag)
		{
			return "del_fail";
		}
		else
		{
			return "del_success";
		}
	}
}
