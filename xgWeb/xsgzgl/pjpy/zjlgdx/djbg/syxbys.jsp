<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/Dth/xhtml1-transitional.dth">
<%@ page import="java.util.*" %>
<%@ page import="xgxt.action.Base" %>
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
	</head>

	<body>
		<html:form action="/stu_info_add" method="post">
			<%HashMap<String,String>rs=(HashMap<String,String>)request.getAttribute("rs"); %>
			<div style="width: 100%" align="center">
				<br><br><br><br><br>
				<div align="left">
					<b><font style="font-size:15.75pt;font-family:'����';margin-left:10px ">����1��</font></b>
				</div>
				<div align="center" style="font-size:15.75pt;font-family:'��������';" >
					<b>�㽭ʡ��ͨ�ߵ�ѧУ�����ҵ���ǼǱ�</b>
				</div>
				<br/>
				<div align="left" style="font-size:10.5pt;font-family:'����_GB2312';margin-left:10px ">
					ѧ У:<font style="font-size:8.5pt">�㽭����ѧ</font>
					Ժ(ϵ):<font style="font-size:8.5pt">&nbsp;${rs.xymc }</font>
					ר ҵ:<font style="font-size:8.5pt">&nbsp;${rs.zymc }</font>
					�� ��:<font style="font-size:8.5pt">&nbsp;${rs.bjmc }</font>
					<font style="font-size:8.5pt">&nbsp;${rs.nowTime }</font>
				</div>
				<table width="100%" id="rsT" class="printstyle">
					<tr valign="bottom"> 
				      <td style="width:4%"></td> 
				      <td style="width:4%" ></td> 
				      <td style="width:4%" ></td> 
				      <td style="width:6%"></td> 
				      <td style="width:6%"></td> 
				      <td style="width:12%"></td> 
				      <td style="width:3%"></td>
				      <td style="width:3%"></td>
				      <td style="width:5%"></td> 
				      <td style="width:5%"></td> 
				      <td style="width:5%"></td>
				      
				      <td style="width:6%"></td>
				      <td style="width:6%"></td>
				      <td style="width:6%"></td>
				      <td style="width:6%"></td>
				      <td style="width:6%"></td>
				      <td style="width:6%"></td> 
				    </tr>
					<tr valign="bottom"> 
				      <td colspan='3' height="30px"  style="font-family:'����_GB2312';font-size:15.75pt;">�� ��</td> 
				      <td colspan=2  style="font-family:'����_GB2312';font-size:15px;">${rs.xm }</td> 
				      <td colspan=1  style="font-family:'����_GB2312';font-size:15.75pt;"> �� ��</td> 
				      <td colspan=2  style="font-family:'����_GB2312';font-size:15px;">${rs.xb }</td> 
				      <td colspan=3  style="font-family:'����_GB2312';font-size:15.75pt;"> ��������</td> 
				      <td colspan=2  style="font-family:'����_GB2312';font-size:13px;"><%=Base.isNull(rs.get("csrq"))?"":rs.get("csrq").substring(0,7).replace("-","��")+"��" %></td> 
				      <td colspan=2  style="font-family:'����_GB2312';font-size:15.75pt;"> �� ��</td> 
				      <td colspan=2  style="font-family:'����_GB2312';font-size:15px;">${rs.mzmc }</td> 
				    </tr>
				    <tr valign="bottom"> 
				      <td colspan=3 height="30px" style="font-family:'����_GB2312';font-size:15.75pt;"> ��Դ��</td> 
				      <td colspan=2 style="font-family:'����_GB2312';font-size:14px;">${rs.sydshi } </td> 
				      <td colspan=3 style="font-family:'����_GB2312';font-size:15.75pt;"> &nbsp;��&nbsp;��&nbsp;��&nbsp;ò&nbsp;</td> 
				      <td colspan=5 style="font-family:'����_GB2312';font-size:15px;">${rs.zzmmmc } </td> 
				      <td colspan=2 style="font-family:'����_GB2312';font-size:15.75pt;"> ְ �� </td> 
				      <td colspan=2 style="font-family:'����_GB2312';font-size:15px;">${rs.zw } </td> 
				    </tr>
				    <tr valign="bottom"> 
				      <td colspan=3  style="font-family:'����_GB2312';font-size:15.75pt;" height="40px" >�� ͥ<br>�� ַ </td> 
				      <td colspan=7   style="font-family:'����_GB2312';font-size:15px;" >${rs.jtszd }${rs.jtdz } </td> 
				      <td colspan=3   style="font-family:'����_GB2312';font-size:15.75pt;" >�� ϵ<br>�� �� </td>
				      <td colspan=4 style="font-family:'����_GB2312';font-size:15px;"  >${rs.sjhm }&nbsp; </td>
				    </tr> 
				    <tr> 
				      <td width="7%" colspan="3" height="280px" style="font-family:'����_GB2312';text-align:center;font-size:15.75pt;" > ��<br><br>��<br><br>��<br><br>��</td> 	      
				   	  <td colspan=14  style="font-family:'����_GB2312';font-size:15.75pt;" >
				   	  <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px;font-weight:bold;" type="_moz">${rs.grjl }</textarea>
				   	   </td> 
				    </tr> 
				    <tr> 
				      <td width="7%" colspan="3" height="280px" style="font-family:'����_GB2312';text-align:center;font-size:15.75pt;"> ��<br><br>Ҫ<br><br>��<br><br>��</td> 
				      <td colspan=14 style="font-family:'����_GB2312';font-size:15.75pt;" >
				   	  <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px;font-weight:bold;" type="_moz">${rs.zd1 }</textarea>
				   	  </td> 
				    </tr>
				</table>
				<br/>
				<div align="left" style="font-family:'����_GB2312';font-size:'10.5pt';">
					<span style="margin-left: 10px">ע��1���˱�һʽ���ݣ�ѧ�����˵�����ѧУ��һ�ݡ�</span><br/>
					<span style="margin-left: 33px">2���������ݿɴ�ӡ���øֱ���д���ּ��������Ժ��ϵ����ѧУ���£��쵼ǩ�ַ���Ч��</span>
				</div>
			</div>
			
			<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
			<div style="width: 100%" align="center">
				<table width="100%" id="rsT" class="printstyle">
				    <tr> 
				      <td width="12%"height="280px" style="text-align:center;font-size:15.75pt;font-family:'����_GB2312';line-height: 35px">��<br/>У<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��</td>
				      <td colspan="3" >
				   	  <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px;font-weight:bold;" type="_moz">${rs.zd2 }</textarea>
				   	  </td> 
				    </tr>     
				    <tr> 
				      <td height="180px" style="text-align:center;font-size:15.75pt;font-family:'����_GB2312';line-height: 35px">Ժ<br/>ϵ<br/>��<br/>��</td> 	      
				   	  <td>&nbsp; </td> 
				   	  <td width="12%" height="160px" style="text-align:center;font-size:15.75pt;font-family:'����_GB2312';line-height: 35px">ѧ<br/>У<br/>��<br/>��</td> 	      
				   	  <td>&nbsp; </td> 
				    </tr> 
				     <tr> 
				      <td height="230px" style="text-align:center;font-size:15.75pt;font-family:'����_GB2312';line-height: 35px">ʡ<br/>��<br/>��<br/>��<br/>��<br/>��</td> 	      
				   	  <td>&nbsp; </td> 
				   	  <td style="text-align:center;font-size:15.75pt;font-family:'����_GB2312';line-height: 35px">��<br/>ҵ<br/>��<br/>ҵ<br/>ȥ<br/>��</td> 	      
				   	  <td>&nbsp; </td> 
				    </tr> 
				    <tr> 
				      <td height="85px" style="text-align:center;font-size:15.75pt;font-family:'����_GB2312';line-height: 35px">��<br>ע</td> 	      
				   	  <td colspan="3">&nbsp; </td> 
				    </tr> 
				</table>
				<br/>
				<div style="text-align:right;font-size:15.75pt;font:'����_GB2312';">
					�㽭ʡ�������Ʊ�&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</html:form>
	</body>
</html>
