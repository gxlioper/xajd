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
				<br/><br/>
				<div align="left" style="font-size:15.75pt;font-family:'����';margin-left: 15px ">
					<b>����3��</b>
				</div>
				<br/><br/>
				<div align="center" style="font-size:15.75pt;font-family:'��������' ">
					<b>�㽭����ѧ�����ҵ���ǼǱ�</b>
				</div>
				<br/><br/>
				<div align="left" style="font-family: ����_GB2312;font-size:12pt;margin-left: 12px  ">
					ѧԺ��${rs.xymc}
					&nbsp;
					רҵ��${rs.zymc}
					&nbsp;
					�༶��${rs.bjmc}
					&nbsp;
					${rs.nowTime}
				</div>
				<table width="100%" id="rsT" class="printstyle" style="font-family: ����_GB2312;font-size:13.75pt">
					<tr>
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
					<tr> 
				      <td style="font-family: ����_GB2312;font-size:13.75pt" width="10%" colspan=3 height="30px" > �� ��</td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" width="15%" colspan=2>${rs.xm }&nbsp;</td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" width="10%" colspan=1 > �� ��</td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" width="15%" colspan=2 >${rs.xb }&nbsp;</td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" width="10%" colspan=3 > ��������</td>
				      <td style="font-family: ����_GB2312;font-size:13px" width="15%" colspan=2 ><%=Base.isNull(rs.get("csrq"))?"":rs.get("csrq").substring(0,7).replace("-","��")+"��" %></td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" width="10%" colspan=2> �� ��</td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" width="15%" colspan=2 >${rs.mzmc }&nbsp;</td>
				    </tr>
				    <tr> 
				      <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=3 height="30px" >��Դ��</td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=2>${rs.sydshi }</td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=3>��&nbsp;��&nbsp;��&nbsp;ò</td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=5>${rs.zzmmmc }&nbsp; </td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=2> ְ �� </td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=2>${rs.zw } </td>
				    </tr>
				    <tr> 
				      <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=4 height="30px">��&nbsp;ͥ&nbsp;��&nbsp;ַ&nbsp;</td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt"  colspan=6 >${rs.jtszd}&nbsp; </td>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=3 >��ϵ�绰&nbsp;</th>
				      <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=4 >${rs.sjhm }&nbsp; </th>
				    </tr> 
				    <tr style="height: 100px"> 
				      <td style="font-family: ����_GB2312;font-size:13.75pt"  width="7%"  style="text-align:center;line-height: 35px;">��<br>��<br>��<br>��</td>	      
				   	  <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=16  ><br>
				   	  <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 130px;_height: 180px;font-size:14px;font-weight:bold;" type="_moz">${rs.grjl }</textarea>
				   	   </td>
				    </tr> 
				    <tr> 
				      <td style="font-family: ����_GB2312;font-size:13.75pt"  width="7%" height="260px" style="text-align:center;line-height: 35px"> ��<br>��<br>��<br>��</td>	      
				   	  <td style="font-family: ����_GB2312;font-size:13.75pt"  colspan=16  ><br>
				   	  <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px;font-weight:bold;" type="_moz">${rs.zd2 }</textarea>
				   	  </td> 
				    </tr>
				    <tr> 
				      <td style="font-family: ����_GB2312;font-size:13.75pt" width="7%" height="70px" style="text-align:center;line-height: 35px">ѧ<br>Ժ<br>��<br>��</td>	      
				   	  <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=6 >&nbsp; </td>
				   	  <td style="font-family: ����_GB2312;font-size:13.75pt" width="7%" height="70px" style="text-align:center;line-height: 35px">ѧ<br>У<br>��<br>��</td>	      
				   	  <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=9 >&nbsp; </td>
				    </tr> 
				    <tr> 
				      <td style="font-family: ����_GB2312;font-size:13.75pt" width="7%" height="40px" style="text-align:center;line-height: 35px">��<br>ע</td>	      
				   	  <td style="font-family: ����_GB2312;font-size:13.75pt" colspan=16 >&nbsp; </td>
				    </tr> 
				</table>
				<br/>
				<div align="left" style="font-family: ����_GB2312;font-size:12pt;">
					<span style="margin-left:15px">ע��1���˱�һʽ���ݣ����˵�����ѧУ��һ�ݡ�<br></span>
					<span style="margin-left:44px">2����������Ҫ����Դ�ӡ����ѧԺ������Ч��</span>
				</div>
			</div>
		</html:form>
	</body>
</html>
