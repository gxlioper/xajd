<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/Dth/xhtml1-transitional.dth">
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
			<div align="center" style="font-size:25px;font:'����' ">
				<b><br/>�㽭����ѧ${rs.xn }ѧ��${rs.xmmc }�����</b>
				<p style="height:20px"></p>
			</div>
			<table width="100%" id="rsT" class="printstyle">
				<tr valign="bottom"> 
			      <td style="width:7%"></td> 
			      <td style="width:12%"></td> 
			      <td style="width:7%"></td> 
			      <td style="width:5%"></td> 
			      <td style="width:7%"></td> 
			      <td style="width:8%"></td> 
			      <td style="width:8%"></td>
			      <td style="width:7%"></td>
			      <td style="width:1%"></td> 
			      <td style="width:5%"></td> 
			      
			      <td style="width:3%"></td>
			      <td style="width:3%"></td>
			      <td style="width:5%"></td>
				</tr>
				<tr> 
				  <td colspan='1' height="30px" align="center" >�� ��</td> 
				  <td colspan='1' >${rs.xm }</td>
				  <td colspan='1' align="center" >�� ��</td>
				  <td colspan='1' >${rs.xb }</td>
				  <td colspan='2' align="center" >��������</td>
				  <td colspan='3' >${rs.csrq }</td>
				  <td colspan='2' align="center" >������ò</td>
				  <td colspan='2' >${rs.zzmmmc }</td>
				</tr>
				<tr>
				  <td colspan='1' align="center" >ѧ ��</td>
				  <td colspan='1' >${rs.xh }</td>
				  <td colspan='1' align="center" >ѧ Ժ</td>
				  <td colspan='2' >${rs.xymc }</td>
				  <td colspan='1' align="center" >�� ��</td>
				  <td colspan='3' >${rs.bjmc }</td>
				  <td colspan='2' align="center" >����ְ��</td>
				  <td colspan='2' ></td>
				</tr>
				<tr>
				  <td colspan='2' align="center" >�ۺϲ����ɼ��༶����</td>
				  <td colspan='3' >${rs.cpzpm }</td>
				  <td colspan='1' align="center" >����</td>
				  <td colspan='1' >${rs.dyf }</td>
				  <td colspan='1' align="center" >����</td>
				  <td colspan='2' >${rs.zyf }</td>
				  <td colspan='2' align="center" >����</td>
				  <td colspan='1' >${rs.tyf }</td>
				</tr>
				<tr>
				  <td colspan='1' style="text-align:center" ><br/><br/><br/><br/>��<br/><br/>��<br/><br/>��<br/><br/>��<br/><br/><br/><br/></td>
				  <td colspan='12' ><p align="lift">�����˵���������ı��֣�����������л�����ķ������ʵ���ȷ��棩<br/><br/><br/><br/><br/><br/><br/><br/></p>
				  		<p align="right">&nbsp;</p>
					    <p align="right">&nbsp;</p>
					    <p align="right">&nbsp;</p>
					    <p align="right">&nbsp;</p>
					    <p align="right">&nbsp;</p>
					    <p align="right">������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/></p>
				     	<div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/></div></td>
				</tr>
				<tr>
				  <td colspan='' style="text-align:center" ><br/><br/><br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/><br/><br/></td>
				  <td colspan='12' ><p>&nbsp;</p>
				    <p align=center class="STYLE2"><br/><br/>������ǩ�֣����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><br/><br/></p>
		          <div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
				</tr>
				<tr>
				  <td colspan='' style="text-align:center" ><br/><br/><br/>ѧ<br/>Ժ<br/>��<br/>��<br/><br/><br/></td>
				  <td colspan='12' ><p>&nbsp;</p>
				  	<p align="center" class="STYLE2">ѧԺ�쵼ǩ�֣����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><br/><br/></p>
			      <div align="right" class="STYLE2">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </div></td>
				</tr>
				<tr>
				  <td colspan='' style="text-align:center" ><br/><br/><br/>ѧ<br/>У<br/>��<br/>��<br/><br/><br/></td>
				  <td colspan='12' ><p align="center" class="STYLE1">ͬ���${rs.xmmc }���ѧ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><br/><br/> </p>
				    <p align="right" class="STYLE2">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td>
				</tr>
				<tr>
				  <td colspan='' style="text-align:center" >��<br/>ע<br/></td>
				  <td colspan='12' >1.����һʽ���ݣ�������д����Ҫ��������벢��ӡ��<br>2.������������д����������Ϳ��</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
