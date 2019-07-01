package xgxt.wjcf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.ShgcForm;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.New_Random_ID;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;

public class WjcfUtil {

	//DAO mydao= DAO.getInstance();
	StandardOperation myop = new StandardOperation();
	New_Random_ID newId= new New_Random_ID();
	lrh_commen_util commen_util= new lrh_commen_util();
	HttpServletRequest request;
	public void WjcfUtil1(HttpServletRequest request){
		this.request = request;
	}

	public String wjcf_xskqccb_add2(WjcfForm myform) throws Exception
	{
		String xn_id=myform.getXn_id();
		boolean flag=true;
		if(null!=xn_id && !"".equalsIgnoreCase(xn_id))
		{
			String tableName="wjcf_xskqccb";
			String primaryKey="xn_id";
			String value=xn_id;
			flag=StandardOperation.delete(tableName, primaryKey, value, request);
		}
		if(false==flag)
		{
			return "del_fail";
		}
		else
		{
			return this.wjcf_xskqccb_add(myform);
		}
	}
	
	public String wjcf_xskqccb_add(WjcfForm myform)
	{
		String tableName="wjcf_xskqccb";
		String xn_id=newId.new_xnid(tableName);
		String xn=myform.getXn();
		String xq=myform.getXq();
		String bjdm=myform.getBjdm();
//		String bjmc=myform.getBjmc();
		String bz=myform.getBz();
		String jssj=myform.getJssj();
		String kssj=myform.getKssj();
		String kcdm=myform.getKcdm();
//		String kcmc=myform.getKcmc();
		String qjrs=myform.getQjrs();
		String rq=myform.getRq();
		String sdrs=myform.getSdrs();
		String ydrs=myform.getYdrs();
		String sjd=kssj+"-"+jssj;		
		int ydrsInt=Integer.valueOf(ydrs);
		int sdrsInt=Integer.valueOf(sdrs);
//		int qjrsInt=Integer.valueOf(qjrs);
		String qq=String.valueOf(ydrsInt-sdrsInt);
		float temp=((float)(sdrsInt)/(float)ydrsInt)*100;
		String cql=String.valueOf(temp);
		int index=cql.indexOf(".");
		cql=cql.substring(0,index+3);
		StringBuffer col= new StringBuffer();
		StringBuffer val= new StringBuffer();
		String s1="!!SplitSignOne!!";
		
		col.append("XN_ID"+s1);
		val.append(xn_id+s1);
		
		col.append("XN"+s1);
		val.append(xn+s1);
		
		col.append("XQ"+s1);
		val.append(xq+s1);
		
		col.append("BJDM"+s1);
		val.append(bjdm+s1);
		
		col.append("KCDM"+s1);
		val.append(kcdm+s1);
		
		col.append("SDRS"+s1);
		val.append(sdrs+s1);
		
		col.append("QJRS"+s1);
		val.append(qjrs+s1);
		
		col.append("RQ"+s1);
		val.append(rq+s1);
		
		col.append("SJD"+s1);
		val.append(sjd+s1);
		
		col.append("YDRS"+s1);
		val.append(ydrs+s1);
		
		col.append("CQL"+s1);
		val.append(cql+s1);
		
		col.append("QQ"+s1);
		val.append(qq+s1);
		
		if(null!=bz&& !bz.equalsIgnoreCase(""))
		{
			col.append("BZ"+s1);
			val.append(bz+s1);
		}
		
		String []columns=col.toString().split(s1);
		String []values=val.toString().split(s1);
		
		boolean flag=StandardOperation.insert(tableName, columns, values, request);
		String message="";
		if(flag==true)
		{
			message="insert_success";
		}
		else
		{
			message="insert_fail";
		}
		return message;	
	}
	
	public List kqcc_search(WjcfForm myform)
	{
		List li=null;
		String tableName="VIEW_XSKQXX";
		String nj=myform.getNj();
		String xn=myform.getXn();
		String xq=myform.getXq();
		String rq=myform.getRq();
		String xydm=myform.getXydm();
		String zydm=myform.getZydm();
		String bjdm=myform.getBjdm();
		
		StringBuffer other=new StringBuffer();
		StringBuffer otherV=new StringBuffer();
		StringBuffer lik=new StringBuffer();
		StringBuffer likV=new StringBuffer();
		String s1="!!SplitSignOne!!";
		if(nj!=null&&!nj.equalsIgnoreCase(""))
		{
			other.append("NJ"+s1);
			otherV.append(nj+s1);
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
		if(zydm!=null&&!zydm.equalsIgnoreCase(""))
		{
			other.append("ZYDM"+s1);
			otherV.append(zydm+s1);
		}
		if(bjdm!=null&&!bjdm.equalsIgnoreCase(""))
		{
			other.append("BJDM"+s1);
			otherV.append(bjdm+s1);
		}
		
		String usersql="select a.XN,a.XQ,a.KCDM,a.KCMC,a.BJDM ,a.BJMC,a.RKJS,a.SKDD,a.ZYDM,a.ZYMC,a.BZR,a.XYMC,a.RS,a.SDRS,a.XYDM,a.NJ,a.CQL,a.QQ,a.QJRS,a.BZ,a.RQ,a.SJD,a.XN_ID from";
		String [] otherKeys={};
		String [] otherKeyValues={};
		String [] like={};
		String [] likeVal={};
		String []order={};
		String []and={"and a.XN_ID is not null"};
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
	    li=commen_util.Find_By_OtherKey5(tableName,usersql, otherKeys, otherKeyValues,like,likeVal,order,and);
	    return li;	
	}
	
	public WjcfForm kqcc_view(WjcfForm myform)
	{
		String xn_id=myform.getXn_id();
		List<HashMap<String, String>> li=null;
		String tableName="VIEW_XSKQXX";
		String usersql="select a.XN,a.XQ,a.KCDM,a.KCMC,a.BJDM ,a.BJMC,a.RKJS,a.SKDD,a.ZYDM,a.ZYMC,a.BZR,a.XYMC,a.RS,a.SDRS,a.XYDM,a.NJ,a.CQL,a.QQ,a.QJRS,a.BZ,a.RQ,a.SJD,a.XN_ID from";
		String []otherKeys={"XN_ID"};
		String []otherKeyValues={xn_id};
		li=commen_util.Find_By_OtherKey(tableName, usersql, otherKeys, otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
 		int tempLen=li.size();
 		for(int i=0;i<tempLen;i++)
 		{
 			map=li.get(i);
 			myform.setXn(map.get("XN"));
 			myform.setXq(map.get("XQ"));
 			myform.setKcmc(map.get("KCMC"));
 			myform.setBjmc(map.get("BJMC"));
 			myform.setRkjs(map.get("RKJS"));
 			myform.setSkdd(map.get("SKDD"));
 			myform.setZymc(map.get("ZYMC"));
 			myform.setYdrs(map.get("RS"));
 			myform.setSdrs(map.get("SDRS"));
 			myform.setNj(map.get("NJ"));
 			myform.setCql(map.get("CQL"));
 			myform.setQq(map.get("QQ"));
 			myform.setQjrs(map.get("QJRS"));
 			myform.setBz(map.get("BZ"));
 			myform.setRq(map.get("RQ"));
 			String []temp=map.get("SJD").split("-");
 			myform.setKssj(temp[0]);
 			myform.setJssj(temp[1]);
 		}
 		return myform;
	}
	
	public List<HashMap<String, String>> wjcf_getkqqkList()
	{
		String tableName="kqdmb";
		String usersql="select a.BH,a.MC from";
		List<HashMap<String, String>> li=null;
		String []otherKeys={};
		String []otherKeyValues={};
		li=commen_util.Find_By_OtherKey(tableName, usersql, otherKeys, otherKeyValues);
		return li;
	}
	
	public List xsxxkqxx_search(WjcfForm myform)
	{
		List li=null;
		String tableName="VIEW_XSXXKQXX";
//		String nj=myform.getNj();
		String xn=myform.getXn();
		String xq=myform.getXq();
		String rq=myform.getRq();
		String xydm=myform.getXydm();
		String zydm=myform.getZydm();
		String bjdm=myform.getBjdm();
		String kqqk=myform.getKqqk();
		String xh=myform.getXh();
		String xm=myform.getXm();
		
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
			boolean chk=Check_Input_Value.check2(xm);
			if(chk==false)
			{
				return li;
			}
			lik.append("XM"+s1);
			likV.append("%"+xm+"%"+s1);
		}
		
		if(rq!=null&&!rq.equalsIgnoreCase(""))
		{
			lik.append("KQSJ"+s1);
			likV.append("%"+"20"+rq+"%"+s1);
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
		
		if(xydm!=null&&!xydm.equalsIgnoreCase(""))
		{
			other.append("XYDM"+s1);
			otherV.append(xydm+s1);
		}
		if(zydm!=null&&!zydm.equalsIgnoreCase(""))
		{
			other.append("ZYDM"+s1);
			otherV.append(zydm+s1);
		}
		if(bjdm!=null&&!bjdm.equalsIgnoreCase(""))
		{
			other.append("BJDM"+s1);
			otherV.append(bjdm+s1);
		}
		if(kqqk!=null&&!kqqk.equalsIgnoreCase(""))
		{
			other.append("KQQK"+s1);
			otherV.append(kqqk+s1);
		}
		
		String usersql="select a.XN,a.XQ,a.XKKH,a.KCMC,a.XH,a.KQSJ,a.KQQK,a.MC,a.XM,a.XB,a.XYMC,a.ZYMC,a.BJMC,a.BJDM,a.ZYDM,a.XYDM from";
		String [] otherKeys={};
		String [] otherKeyValues={};
		String [] like={};
		String [] likeVal={};
		String []order={};
		String []and={};
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
	    li=commen_util.Find_By_OtherKey5(tableName,usersql, otherKeys, otherKeyValues,like,likeVal,order,and);
	    return li;	
	}
	
	public List<HashMap<String, String>> xsxxkqxx_xskqmx(WjcfForm myform)
	{
		DAO mydao= DAO.getInstance();
		List<HashMap<String, String>> li=null;
		String xh=myform.getXh();
//		boolean flag=false;
		if(xh!=null&&!xh.equalsIgnoreCase(""))
		{
			boolean chk=Check_Input_Value.check2(xh);
			if(false==chk)
			{
				return li;
			}
		}
		String rq=myform.getRq();
		String yf="20"+rq+"%";
		String sql="select a.xn_id,a.xh,a.kqqk,a.kqjg,a.yf,a.mc from VIEW_WJCF_XSCQMXB a where a.xh=? and yf like '"+yf+"'";
		String []inputValue={xh};
		String []outputValue={"xn_id","xh","kqqk","kqjg","yf","mc"};
		li=mydao.getList(sql, inputValue, outputValue);
		return li;
	}
	
	public String xsxxkqxx_xskqmx_add(WjcfForm myform) throws Exception
	{
		DAO mydao= DAO.getInstance();
//		List li=null;
		String tableName="VIEW_XSXXKQXX";
		String xh=myform.getXh();
		String rq=myform.getRq();
//		String s1="!!SplitSignOne!!";
		boolean flag=false;
		if(xh!=null&&!xh.equalsIgnoreCase(""))
		{
			boolean chk=Check_Input_Value.check2(xh);
			if(false==chk)
			{
				return "ilegal_input";
			}
		}
		String []otherKey={"XH"};
		String []otherKayValue={xh};
		String []like={"YF"};
		String []likeVal={"20"+rq+"%"};
		flag=StandardOperation.delete3("WJCF_XSCQMXB", otherKey, otherKayValue,like,likeVal,request);
		
		if(false==flag)
		{
			return "delete_fail";
		}
		
		List<HashMap<String, String>> kqqkList=this.wjcf_getkqqkList();
//		String temp="";
		StringBuffer strbuf=new StringBuffer();
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i=0;i<kqqkList.size();i++)
		{
			map=kqqkList.get(i);
			strbuf.append(map.get("BH")+(i==kqqkList.size()?"":"!!SplitSignOne!!"));
		}
		String [] Vector=strbuf.toString().split("!!SplitSignOne!!");
		
		for(int i=0;i<Vector.length;i++)
		{
			//select count(*) num from VIEW_XSXXKQXX where xh='0305030136' and KQSJ like '2007-08%' and kqqk='02';
			String sql="select count(*) num from "+tableName+" where xh=? and kqsj like '20"+rq+"%' and kqqk=? ";
			String []inputValue={xh,Vector[i]};
			String []outputValue={"num"};
			List<HashMap<String, String>> lii=mydao.getList(sql, inputValue, outputValue);
			HashMap<String, String> map2 = new HashMap<String, String>();
			String kqjg="";
			for(int j=0;j<lii.size();j++)
			{
				map2=lii.get(j);
				kqjg=map2.get("num");
			}
			String xn_id=newId.new_xnid("WJCF_XSCQMXB");
			String []columns={"XN_ID","XH","KQQK","KQJG","YF"};
			String []values={xn_id,xh,Vector[i],kqjg,"20"+rq};
			flag=StandardOperation.insert("WJCF_XSCQMXB", columns, values, request);
			if(false==flag)
			{
				return "insert_fail";
			}
		}
		return "insert_success";
	}
	
	public WjcfForm  xsxxkqxx_xskqmx_view(WjcfForm myform)
	{
		DAO mydao= DAO.getInstance();
		String xh=myform.getXh();
		if(xh!=null&&!xh.equalsIgnoreCase(""))
		{
			boolean chk=Check_Input_Value.check2(xh);
			if(false==chk)
			{
				return myform;
			}
		}
//		String tableName="VIEW_XSXXKQXX";
		String sql="select distinct a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm from VIEW_XSXXKQXX a where a.xh=? ";
		String []inputValue={xh};
		String []outputValue={"xm","xb","xymc","zymc","bjmc","xydm","zydm","bjdm"};
		List<HashMap<String, String>> li=mydao.getList(sql, inputValue, outputValue);
		HashMap<String, String> map = new HashMap<String, String>();
//		String kqjg="";
		for(int i=0;i<li.size();i++)
		{
			map=li.get(i);
			myform.setXm(map.get("xm"));
			myform.setXb(map.get("xb"));
			myform.setXydm(map.get("xydm"));
			myform.setXymc(map.get("xymc"));
			myform.setZydm(map.get("zydm"));
			myform.setZymc(map.get("zymc"));
			myform.setBjdm(map.get("bjdm"));
			myform.setBjmc(map.get("bjmc"));
		}
		return myform;
	}
	
	public WjcfForm xsxxkqxx_xskqmx_cqltj(WjcfForm myform) throws Exception
	{
//		DAO mydao= DAO.getInstance();
		List<HashMap<String, String>> li=null;
		li=this.xsxxkqxx_xskqmx(myform);
		String xh=myform.getXh();
		if(xh!=null&&!xh.equalsIgnoreCase(""))
		{
			boolean chk=Check_Input_Value.check2(xh);
			if(false==chk)
			{
				return myform;
			}
		}
		HashMap<String, String> map = new HashMap<String, String>();
		String kqjg="";
		String kqqk="";
		String qj="";//03
		String cd="";//01
		String kk="";//02
		String dk="";//04
		for(int i=0;i<li.size();i++)
		{
			map=li.get(i);
			kqjg=map.get("kqjg");
			kqqk=map.get("kqqk");
			if("01".equals(kqqk))
			{
				cd=kqjg;
			}
			if("02".equals(kqqk))
			{
				kk=kqjg;
			}
			if("03".equals(kqqk))
			{
				qj=kqjg;
			}
			if("04".equals(kqqk))
			{
				dk=kqjg;
			}
		}
		
		int qj_i=(!qj.equalsIgnoreCase(""))?Integer.valueOf(qj):0;		
		int cd_i=(!cd.equalsIgnoreCase(""))?Integer.valueOf(cd):0;
		int kk_i=(!kk.equalsIgnoreCase(""))?Integer.valueOf(kk):0;
		int dk_i=(!dk.equalsIgnoreCase(""))?Integer.valueOf(dk):0;
		float temp=(float)qj_i+cd_i+kk_i+dk_i;
		String cql="";
		if(0==temp)
		{
			cql="0";
		}
		else
		{
			cql=String.valueOf((float)dk_i/temp*100).substring(0,5);
		}
		myform.setCql(cql);
//		boolean flag=false;
		String []otherKey={"XH"};
		String []otherKayValue={xh};
		String []like={"YF"};
		String []likeVal={"20"+myform.getRq()};
		StandardOperation.delete3("WJCF_XSCQLB", otherKey, otherKayValue, like, likeVal,request);
		String xn_id=newId.new_xnid("WJCF_XSCQLB");
		String []columns={"XN_ID","XH","XYDM","CQL","YF"};
		String []values={xn_id,xh,myform.getXydm(),cql,"20"+myform.getRq()};
		StandardOperation.insert("WJCF_XSCQLB", columns, values, request);
		return myform;
	}
	
	public WjcfForm xsxxkqxx_xskqmx_xycqltj(WjcfForm myform) throws Exception
	{
		DAO mydao= DAO.getInstance();
		String xydm=myform.getXydm();
		String tableName="VIEW_XSXXKQXX";
		String rq=myform.getRq();
		String sql="select count(*) num from "+tableName+" a where xydm=? and KQSJ like '20"+rq+"%'";
		String []inputValue={xydm};
		String []outputValue={"num"};
		int fm=0;
		int fz=0;
		String temp="";
		List<HashMap<String, String>> li=mydao.getList(sql,inputValue,outputValue);
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i=0;i<li.size();i++)
		{
			map=li.get(i);
			temp=map.get("num");
		}
		fm=Integer.valueOf(temp);
		sql="select count(*) num from "+tableName+" a where xydm=? and KQSJ like '20"+rq+"%' and kqqk='04'";
		li=mydao.getList(sql,inputValue,outputValue);
		HashMap<String, String> map2 = new HashMap<String, String>();
		for(int i=0;i<li.size();i++)
		{
			map2=li.get(i);
			temp=map2.get("num");
		}
		fz=Integer.valueOf(temp);
		String cql="";
		if(0==fm)
		{
			cql="0";
		}
		else
		{
			cql=String.valueOf((float)fz/(float)fm*100);
			int index=String.valueOf(cql).indexOf(".");
			String temp1=String.valueOf(cql).substring(0,index);
			String temp2=String.valueOf(cql).substring(index,4);
			cql=temp1+temp2;
		}
		myform.setCql(cql);
		String xymc=commen_util.getxymc_byxydm(xydm);
		myform.setXymc(xymc);
		return myform;
	}

	
	public WjcfForm xsxxkqxx_xskqmx_qxcqltj(WjcfForm myform) throws Exception
	{
//		DAO mydao= DAO.getInstance();
		boolean flag=false;
		String rq=myform.getRq();
		String []otherKey={};
		String []otherKeyValue={};
		String []like={"YF"};
		String []likeVal={"20"+rq+"%"};
		flag=StandardOperation.delete3("WJCF_QXCQLMXB", otherKey, otherKeyValue, like, likeVal,request);
		if(false==flag)
		{
			return myform;
		}
		List<HashMap<String, String>> xyList=commen_util.get_xyList();
		HashMap<String, String> map = new HashMap<String, String>();
		String xydm="";
//		String xymc="";
		String cql="";
		int k=0;
		float qxcql=0;
		for(int i=0;i<xyList.size();i++)
		{
			map=xyList.get(i);
			xydm=map.get("xydm");
//			xymc=map.get("xymc");
			myform.setXydm(map.get("xydm"));
			cql=this.xsxxkqxx_xskqmx_xycqltj(myform).getCql();
			String xn_id=newId.new_xnid("WJCF_QXCQLMXB");
			String []columns={"XN_ID","YF","XYDM","CQL"};
			String []values={xn_id,"20"+rq,xydm,cql};
			flag=StandardOperation.insert("WJCF_QXCQLMXB", columns, values, request);
			if(false==flag)
			{
				return myform;
			}
			k++;
			
			qxcql=qxcql+new Float(cql);
		}
		qxcql=qxcql/k;
		if(0==qxcql)
		{
			myform.setCql(String.valueOf(qxcql));
		}
		else
		{
			int index=String.valueOf(qxcql).indexOf(".");
			String temp1=String.valueOf(qxcql).substring(0,index);
			String temp2=String.valueOf(qxcql).substring(index,4);
			myform.setCql(temp1+temp2);
		}
		return myform;
	}
	
	public List xsxxkqxx_xskqmx_qxcqltj_mx(WjcfForm myform) throws Exception
	{
		DAO mydao= DAO.getInstance();
		String rq=myform.getRq();
		String tableName="VIEW_WJCF_QXCQMXB";
		String sql="select a.xn_id,a.yf,a.xydm,a.cql,a.xymc from "+tableName+" a where a.yf like '20"+rq+"%'";
		List li=mydao.getListNotOut(sql,null);
		return li;
	}
	
	public WjcfForm xsxxkqxx_xskqmc_getsql(WjcfForm myform)
	{
		String usersql=myform.getUsersql();
		StringBuffer strbuf= new StringBuffer();
		String tableName="VIEW_XSXXKQXX";
		
//		String nj=myform.getNj();
		String xn=myform.getXn();
		String xq=myform.getXq();
		String rq=myform.getRq();
		String xydm=myform.getXydm();
		String zydm=myform.getZydm();
		String bjdm=myform.getBjdm();
		String kqqk=myform.getKqqk();
		String xh=myform.getXh();
		String xm=myform.getXm();
		
		if(usersql!=null&&!usersql.equalsIgnoreCase(""))
		{
			strbuf.append(usersql+" ");
		}
		else
		{
			strbuf.append("select a.* from ");
		}
		strbuf.append(tableName+" a where 1=1 ");
		
		if(xh!=null&&!xh.equalsIgnoreCase(""))
		{
			boolean chk=commen_util.check_user_input(xh);
			if(chk==false)
			{
				return myform;
			}
			strbuf.append(" and a.xh like '%"+xh+"%' ");
		}
		
		if(xm!=null&&!xm.equalsIgnoreCase(""))
		{
			boolean chk=commen_util.check_user_input(xh);
			if(chk==false)
			{
				return myform;
			}
			strbuf.append(" and a.xm like '%"+xm+"%' ");
		}
		
		if(rq!=null&&!rq.equalsIgnoreCase(""))
		{
			strbuf.append(" and a.kqsj like '20"+rq+"%' ");
		}
		
		if(xn!=null&&!xn.equalsIgnoreCase(""))
		{
			strbuf.append(" and a.xn ='"+xn+"' ");
		}
		if(xq!=null&&!xq.equalsIgnoreCase(""))
		{
			strbuf.append(" and a.xq ='"+xq+"' ");
		}
		
		if(xydm!=null&&!xydm.equalsIgnoreCase(""))
		{
			strbuf.append(" and a.xydm ='"+xydm+"' ");
		}
		if(zydm!=null&&!zydm.equalsIgnoreCase(""))
		{
			strbuf.append(" and a.zydm ='"+zydm+"' ");
		}
		if(bjdm!=null&&!bjdm.equalsIgnoreCase(""))
		{
			strbuf.append(" and a.bjdm ='"+bjdm+"' ");
		}
		if(kqqk!=null&&!kqqk.equalsIgnoreCase(""))
		{
			strbuf.append(" and a.kqqk ='"+kqqk+"' ");
		}
		myform.setSql(strbuf.toString());
		myform.setTableName(tableName);
		return myform;
	}
	
	/**
	 * 违纪处分原因
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>wjcfXxList(){
		DAO dao=DAO.getInstance();
		String sql="  select distinct(b.cfyydm) dm,cfyymc mc from wjcfb a,cfyydmb b where a.cfyy = b.cfyydm  ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 获取指定年度范围内的学年列表
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>wjcfXnList(ShgcForm shgcForm){
		String ksnd=shgcForm.getKsnf();
		String jsnd=shgcForm.getJsnf();
		int kssj=Integer.parseInt(ksnd);
		int jssj=Integer.parseInt(jsnd);
		HashMap<String,String>hashMap=new HashMap<String,String>();
		List<HashMap<String,String>>list=new ArrayList<HashMap<String,String>>();
		for(int i = kssj ; i<jssj ; i++){
			hashMap=new HashMap<String,String>();
			hashMap.put("xn", ""+i+"-"+(i+1));
			hashMap.put("xqone", "01");
			hashMap.put("xqtwo", "02");
			list.add(hashMap);
		}
		if(kssj==jssj){
			hashMap.put("xn", ""+kssj+"-"+(kssj+1));
			list.add(hashMap);
		}
		return list;
	}
	
	/**
	 * 获取学院List
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>wjcfXyList(){
		DAO dao=DAO.getInstance();
		String sql="  select distinct(xydm) dm,xymc mc from view_njxyzybj  ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 根据 学院、学年、学期 统计 总处分人数
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>wjzrsList(){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select  count(1)rs,b.xydm,a.xn,a.xq from view_xsjbxx b left join (select a.xn,a.xq,a.cflb,b.shbm,a.xysh,a.xxsh,a.xh from wjcfb a left join cflbdmb b on a.cflb=b.cflbdm)a on a.xh=b.xh ");
		sb.append(" where (a.shbm='校审' and a.xxsh='通过') or (shbm='院审' and xysh='通过') group by b.xydm,a.xn,a.xq ");
		return dao.getList(sb.toString(), new String[]{}, new String[]{"rs","xydm","xn","xq"});
	}
	
	/**
	 * 根据 学院、学年、学期、处分类别  处分原因 统计
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>wjrsList(){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select a.* from (select  count(1)rs,b.xydm,a.xn,a.xq,a.cfyy from view_xsjbxx b left join (select a.cfyy,a.xn,a.xq,a.cflb,b.shbm,a.xysh,a.xxsh,a.xh from wjcfb a left join cflbdmb b on a.cflb=b.cflbdm)a on a.xh=b.xh ");
		sb.append(" where (a.shbm='校审' and a.xxsh='通过') or (shbm='院审' and xysh='通过') group by b.xydm,a.xn,a.xq,a.cfyy)a,cfyydmb b where a.cfyy=b.cfyydm  ");
		return dao.getList(sb.toString(), new String[]{}, new String[]{"rs","xydm","xn","xq","cfyy"});
	}
	
	/**
	 * 获取学院学生人数
	 */
	public HashMap<String,String>xyrsList(){
		DAO dao=DAO.getInstance();
		String sql=" select count(1)rs,xydm from view_xsjbxx group by xydm ";
		List<HashMap<String,String>>xyrs= dao.getList(sql, new String[]{}, new String[]{"xydm","rs"});
		HashMap<String,String>xyrsMap=new HashMap<String,String>();
		for(int i=0;i<xyrs.size();i++){
			HashMap<String,String>hashMap=xyrs.get(i);
			xyrsMap.put(hashMap.get("xydm"), hashMap.get("rs"));
		}
		return xyrsMap;
	}
	
	/**
	 * 根据 学院 统计总人数
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>xywjrsList(ShgcForm shgcForm){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select a.* from (select  count(1)rs,b.xydm from view_xsjbxx b left join (select a.cfyy,a.xn,a.xq,a.cflb,b.shbm,a.xysh,a.xxsh,a.xh from wjcfb a left join cflbdmb b on a.cflb=b.cflbdm and xn>=? and xn<=?)a on a.xh=b.xh  ");
		sb.append(" where (a.shbm='校审' and a.xxsh='通过') or (shbm='院审' and xysh='通过') group by b.xydm)a  ");
		return dao.getList(sb.toString(), new String[]{shgcForm.getKsxn(),shgcForm.getJsxn()}, new String[]{"rs","xydm"});
	}
	
	/**
	 * 根据 学院、处分原因 统计总人数
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>cfrsList(ShgcForm shgcForm){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select a.* from (select  count(1)rs,b.xydm,a.cfyy from view_xsjbxx b left join (select a.cfyy,a.xn,a.xq,a.cflb,b.shbm,a.xysh,a.xxsh,a.xh from wjcfb a left join cflbdmb b on a.cflb=b.cflbdm and xn>=? and xn<=? )a on a.xh=b.xh   ");
		sb.append(" where (a.shbm='校审' and a.xxsh='通过') or (shbm='院审' and xysh='通过') group by b.xydm,a.cfyy)a  ");
		return dao.getList(sb.toString(), new String[]{shgcForm.getKsxn(),shgcForm.getJsxn()}, new String[]{"rs","xydm","cfyy"});
	}
	
	/**
	 * 全校违纪学生 总人数
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>xwjxsList(ShgcForm shgcForm){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select a.* from (select  count(1)rs,a.xn,a.xq from view_xsjbxx b left join (select a.cfyy,a.xn,a.xq,a.cflb,b.shbm,a.xysh,a.xxsh,a.xh from wjcfb a left join cflbdmb b on a.cflb=b.cflbdm and xn>=? and xn<=? )a on a.xh=b.xh    ");
		sb.append(" where (a.shbm='校审' and a.xxsh='通过') or (shbm='院审' and xysh='通过') group by a.xn,a.xq)a ");
		return dao.getList(sb.toString(), new String[]{shgcForm.getKsxn(),shgcForm.getJsxn()}, new String[]{"rs","xn","xq"});
	}
	
	/**
	 * 全校违纪学生 总人数
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>wjyyrsList(ShgcForm shgcForm){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select a.* from (select  count(1)rs,a.xn,a.xq,a.cfyy from view_xsjbxx b left join (select a.cfyy,a.xn,a.xq,a.cflb,b.shbm,a.xysh,a.xxsh,a.xh from wjcfb a left join cflbdmb b on a.cflb=b.cflbdm and xn>=? and xn<=? )a on a.xh=b.xh  ");
		sb.append(" where (a.shbm='校审' and a.xxsh='通过') or (shbm='院审' and xysh='通过') group by a.xn,a.xq,a.cfyy)a ");
		return dao.getList(sb.toString(), new String[]{shgcForm.getKsxn(),shgcForm.getJsxn()}, new String[]{"rs","xn","xq","cfyy"});
	}
	
	/**
	 * 学校总人数
	 */
	public HashMap<String,String>getZrs(){
		DAO dao=DAO.getInstance();
		String sql="  select count(1)rs from  view_xsjbxx ";
		return dao.getMap(sql, new String[]{}, new String[]{"rs"});
	}
}
