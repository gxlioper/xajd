package xgxt.rcgl.rcxwxx.util;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.Check_Input_Value;
import xgxt.utils.New_Random_ID;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.xljk.lrh_Util.util.*;
import xgxt.rcgl.rcxwxx.form.*;

public class rcgl_rcxw_util {
	
	StandardOperation myop = new StandardOperation();
	New_Random_ID newId= new New_Random_ID();
	lrh_commen_util commen_util= new lrh_commen_util();
	HttpServletRequest request;
	
	public void rcgl_rcxw_util1(HttpServletRequest request){
		this.request = request;
	}
	
	
	public String rcxwxx_add(rcgl_rcxw_from myform)
	{
		String message="";
		boolean flag=false;
		String tableName="RCGL_RCXW";
		String xh=myform.getXh();
		String rq=myform.getRq();
		String jlr=myform.getJlr();
		String jtnr=myform.getJtnr();
		String xwmkdm=myform.getXwmkdm();
		String xn = myform.getXn();
		String xq = myform.getXq();
		String xn_id=newId.new_xnid(tableName);
		StringBuffer col= new StringBuffer();
		StringBuffer val= new StringBuffer();
		String s1="!!SplitSignOne!!";
		col.append("XN_ID"+s1);
		val.append(xn_id+s1);
		col.append("XH"+s1);
		val.append(xh+s1);
		col.append("RQ"+s1);
		val.append(rq+s1);
		col.append("XWMKDM"+s1);
		val.append(xwmkdm+s1);
		col.append("JLR"+s1);
		val.append(jlr+s1);
		col.append("JTNR"+s1);
		val.append(jtnr+s1);
		col.append("XN"+s1);
		val.append(xn+s1);
		col.append("XQ"+s1);
		val.append(xq+s1);
		String []columns=col.toString().split(s1);
		String []values=val.toString().split(s1);
		flag=StandardOperation.insert(tableName, columns, values, this.request);
		
		if(flag==false)
		{
			message="inser_fail";
		}
		else
		{
			message="inser_success";
		}
		return message;
	}
	
	public List rcxwxx_search(rcgl_rcxw_from myform)
	{
		List li=null;
		String xh=myform.getXh();
		String xm=myform.getXm();
		
		String xwmkdm=myform.getXwmkdm();
		String rq=myform.getRq();
		String xydm=myform.getXydm();
		String bjdm=myform.getBjdm();
		String xn=myform.getXn();
		String xq=myform.getXq();
		
		StringBuffer other=new StringBuffer();
		StringBuffer otherV=new StringBuffer();
		StringBuffer lik=new StringBuffer();
		StringBuffer likV=new StringBuffer();
		String s1="!!SplitSignOne!!";
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
		
		if(xwmkdm!=null&&!xwmkdm.equalsIgnoreCase(""))
		{
			other.append("XWMKDM"+s1);
			otherV.append(xwmkdm+s1);
		}
		if(rq!=null&&!rq.equalsIgnoreCase(""))
		{
			other.append("RQ"+s1);
			otherV.append(rq+s1);
		}
		if(xydm!=null&&!xydm.equalsIgnoreCase(""))
		{
			other.append("XYDM"+s1);
			otherV.append(xydm+s1);
		}
		if(bjdm!=null&&!bjdm.equalsIgnoreCase(""))
		{
			other.append("BJDM"+s1);
			otherV.append(bjdm+s1);
		}
		
		if(xn!=null&&!xn.equalsIgnoreCase("")){
			other.append("XN"+s1);
			otherV.append(xn+s1);
		}
		
		if(xq!=null&&!xq.equalsIgnoreCase("")){
			other.append("XQ"+s1);
			otherV.append(xq+s1);
		}
		
		String tableName="VIEW_RCGL_RCXWXX";
		String usersql="select a.XN_ID,a.XWMKDM,a.JTNR,a.JLR,a.MKMC,a.MKSS,a.RQ,a.XH,a.XM,a.XB,a.XYMC,a.BJMC,a.XYDM,a.BJDM,a.XN,a.XQ from";
		
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
	public rcgl_rcxw_from rcxwxx_view(rcgl_rcxw_from myform)
	{
		String xn_id=myform.getRcgl_rcxw_xnid();
		String tableName="VIEW_RCGL_RCXWXX";
		String usersql="select a.XN_ID,a.XWMKDM,a.JTNR,a.JLR,a.MKMC,a.MKSS,a.RQ,a.XH,a.XM,a.XB,a.XYMC,a.BJMC,a.XYDM,a.BJDM,a.XN,a.XQ from";
			
		String []otherKeys={"XN_ID"};
		String []otherKeyValues={xn_id};
		List<HashMap<String, String>> li=commen_util.Find_By_OtherKey(tableName, usersql, otherKeys, otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
 		int tempLen=li.size();
 		for(int i=0;i<tempLen;i++)
 		{
 			map=li.get(i);
 			myform.setBjdm(map.get("BJDM"));
 			myform.setXwmkdm(map.get("XWMKDM"));
 			myform.setJtnr(map.get("JTNR"));
 			myform.setJlr(map.get("JLR"));
 			myform.setMkmc(map.get("MKMC"));
 			myform.setMkss(map.get("MKSS"));
 			myform.setRq(map.get("RQ"));
 			myform.setXh(map.get("XH"));
 			myform.setXm(map.get("XM"));
 			myform.setXb(map.get("XB"));
 			myform.setXymc(map.get("XYMC"));			
 			myform.setBjmc(map.get("BJMC"));
 			myform.setXydm(map.get("XYDM"));
 			
 			myform.setXn(map.get("XN"));
 			myform.setXq(map.get("XQ"));
 		}
 		return myform;
	}
	public String rcxwxx_modi(rcgl_rcxw_from myform) throws Exception
	{
		String xn_id=myform.getRcgl_rcxw_xnid();
		String message="";
		boolean flag=false;
		String tableName="RCGL_RCXW";
		String xh=myform.getXh();
		String rq=myform.getRq();
		String jlr=myform.getJlr();
		String jtnr=myform.getJtnr();
		String xwmkdm=myform.getXwmkdm();
		String xn = myform.getXn();
		String xq = myform.getXq();
		StringBuffer col= new StringBuffer();
		StringBuffer val= new StringBuffer();
		String s1="!!SplitSignOne!!";
		col.append("XH"+s1);
		val.append(xh+s1);
		col.append("RQ"+s1);
		val.append(rq+s1);
		col.append("XWMKDM"+s1);
		val.append(xwmkdm+s1);
		col.append("JLR"+s1);
		val.append(jlr+s1);
		col.append("JTNR"+s1);
		val.append(jtnr+s1);
        
		col.append("XN"+s1);
		val.append(xn+s1);
		col.append("XQ"+s1);
		val.append(xq+s1);

 		String []columns=col.toString().split(s1);
		String []values=val.toString().split(s1);
		String primaryKey="XN_ID";
		String pkValue=xn_id;
		flag=StandardOperation.update(tableName, columns, values, primaryKey, pkValue, this.request);
		if(flag==false)
		{
			message="update_fail";
		}
		else
		{
			message="update_success";
		}
		return message;
	}
	
	public String rcxwxx_del(rcgl_rcxw_from myform) throws Exception
	{
		String xn_id=myform.getRcgl_rcxw_xnid();
		String message="";
		String tableName="RCGL_RCXW";
		boolean flag=false;
		String primaryKey="XN_ID";
		String value=xn_id;
		flag=StandardOperation.delete(tableName, primaryKey, value, this.request);
		if(flag==false)
		{
			message="del_fail";
		}
		else
		{
			message="del_success";
		}
		return message;
	}
	
}
