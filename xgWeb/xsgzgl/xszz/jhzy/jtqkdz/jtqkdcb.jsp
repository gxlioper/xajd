<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>

		<html:form action="/stu_info_add" method="post">
		<div align="center" style="font-size:28px;font:'����' "><b>��ְҵ����ѧԺѧ������ͥ��������</b></div>
		<br>
		<div align="left" style="font-size:15px;">
		<b>
		<bean:message key="lable.xb" />��<u>${rs.xymc }</u>
		רҵ��<u>${rs.zymc }</u>
		�꼶��<u>${rs.nj }</u>
		</b>
		</div>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<!-- ѧ�����˻������ -->
			<!-- ��һ�� -->
			<tr style="height:40px">
				<th width="8%" rowspan="4">
					<div align="center">
						ѧ<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��<br>
						��
					</div>
				</th>
				<th width="8%">
					<div align="center">
						�� ��
					</div>
				</th>
				<th width="20%" colspan="2">
					<div align="center">
						${rs.xm }
					</div>
				</th>
				<th width="8%">
					<div align="center">
						�Ա�
					</div>
				</th>
				<th width="8%">
					<div align="center">
						${rs.xb }
					</div>
				</th>
				<th width="8%">
					<div align="center">
						����<br/>����
					</div>
				</th>
				<th width="12%">
					<div align="center">
						${rs.csrq }
					</div>
				</th>
				<th width="8%">
					<div align="center">
						����
					</div>
				</th>
				<th width="12%" colspan="2">
					<div align="center">
						${rs.mzmc }
					</div>
				</th>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:40px">
				<th width="">
					<div align="center">
						���֤<br/>����
					</div>
				</th>
				<th width="20%" colspan="3">
					<div align="center">
						${rs.sfzh }
					</div>
				</th>
				<th >
					<div align="center">
						����<br/>��ò
					</div>
				</th>
				<th width="" colspan="1">
					<div align="center">
						${rs.zzmmmc }
					</div>
				</th>
				<th width="">
					<div align="center">
						��ѧǰ<br/>����	
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						<logic:empty name="rs" property="jthk">
							������&nbsp;&nbsp;
							��ũ��
						</logic:empty>
						<logic:equal name="rs" property="jthk" value="����">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							����&nbsp;&nbsp;
							��ũ��
						</logic:equal>
						<logic:equal name="rs" property="jthk" value="ũ��">
							������&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							ũ��
						</logic:equal>	
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr style="height:40px">
				<th width="">
					<div align="center">
						��ͥ��<br/>����		
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						${rs.jtrks }
					</div>
				</th>
				<th width="">
					<div align="center">
						��ҵ<br/>ѧУ					
					</div>
				</th>
				<th width="" colspan="1">
					<div align="center">
						${rs.byxx }
					</div>
				</th>
				<th width="" >
					<div align="center">
						����<br/>�س�
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						${rs.tc }
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr style="height:40px">
				<th width="" >
					<div align="center">
						�� ��
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">					
						<logic:empty name="rs" property="sfgc">
							����&nbsp;&nbsp;
							����
						</logic:empty>
						<logic:equal name="rs" property="sfgc" value="��">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��&nbsp;&nbsp;
							����
						</logic:equal>
						<logic:equal name="rs" property="sfgc" value="��">
							����&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��
						</logic:equal>				
					</div>
				</th>
				<th width="">
					<div align="center">
						�� ��
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						<logic:empty name="rs" property="sfdq">
							����&nbsp;&nbsp;
							����
						</logic:empty>
						<logic:equal name="rs" property="sfdq" value="��">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��&nbsp;&nbsp;
							����
						</logic:equal>
						<logic:equal name="rs" property="sfdq" value="��">
							����&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��
						</logic:equal>		
					</div>
				</th>
				<th width="">
					<div align="center">
						��ʿ��Ů
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						<logic:empty name="rs" property="sflszn">
							����&nbsp;&nbsp;
							����
						</logic:empty>
						<logic:equal name="rs" property="sflszn" value="��">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��&nbsp;&nbsp;
							����
						</logic:equal>
						<logic:equal name="rs" property="sflszn" value="��">
							����&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��
						</logic:equal>			
					</div>
				</th>
			</tr>
			<!-- ��ͥͨѶ��Ϣ -->
			<!-- ��һ�� -->
			<tr style="height:40px">
				<th  rowspan="2">
					<div align="center">
						��ͥ<br>
						ͨѶ<br>
						��Ϣ
					</div>
				</th>
				<th width="20%" colspan="2">
					<div align="center">
						��ϸͨѶ��ַ
					</div>
				</th>
				<th width="" colspan="8">
					<div align="center">
						${rs.jtdz }
					</div>
				</th>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:40px">
				<th colspan="2">
					<div align="center">
						��������
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.jtyb }
					</div>
				</th>
				<th width="10%" >
					<div align="center">
						��ϵ�绰
					</div>
				</th>
				<th width="" colspan="5">
					<div align="center">
						<logic:notEmpty name="rs" property="jtdh" >
						${rs.jtdh }
						</logic:notEmpty>
						<logic:empty name="rs" property="jtdh" >
						�����ţ���
						</logic:empty>
					</div>
				</th>
			</tr>
			
			
			<tr style="height:40px">
				<td rowspan=7>
					<p align=center style='text-align:center'>
						<b><span style='font-family:������'>��<br/>ͥ<br/>��<br/>Ա<br/>��<br/>��</span> </b>
					</p>
				</td>
				<td align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>����</span>
					</p>
				</td>
				<td align=center width="12%">
					<p align=center style='text-align:center' >
						<span style='font-family:������'>����</span>
					</p>
				</td>
				<td  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>�뱾��<br/>��ϵ</span>
					</p>
				</td>
				<td colspan=3 align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>������ѧϰ����λ</span>
					</p>
				</td>
				<td width="6%" align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>ְҵ</span>
					</p>
				</td>
				<td colspan=2 width=16% align=center>
					<p >
						<span style='font-family:������'>�����루Ԫ��</span>
					</p>
				</td>
				<td  width=8%  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:������'>����<br/>״��</span>
					</p>
				</td>
			</tr>
			<%
				ArrayList<HashMap<String,String>>cyList=(ArrayList<HashMap<String,String>>)request.getAttribute("cyList");
				int len=0;
				if(cyList!=null && cyList.size()>0){
					len=cyList.size();
				}
				
				for(int i=0;i<len;i++){
					HashMap<String,String>cyMap=cyList.get(i);
				%>
				<tr style="height:40px">
				 <td align=center>
						<div align="center">
							<%=cyMap.get("cyxm")==null ? "" : cyMap.get("cyxm")%>
						</div>
					</td>
					<td align=center>
						<div align="center">
							<%=cyMap.get("cynl")==null ? "" : cyMap.get("cynl")%>
						</div>
					</td>
					<td  align=center>
						<div align="center">
							<%=cyMap.get("cygx")==null ? "" : cyMap.get("cygx")%>
						</div>
					</td>
					<td colspan=3 align=center>
						<div align="center">
							<%=cyMap.get("cygzxxdw")==null ? "" : cyMap.get("cygzxxdw")%>
						</div>
					</td>
					<td  align=center>
						<div align="center">
							<%=cyMap.get("cyzy")==null ? "" : cyMap.get("cyzy")%>
						</div>
					</td>
					<td  colspan=2 width="10%" align=center>
						<div align="center">
							<%=cyMap.get("cynsr")==null ? "" : cyMap.get("cynsr")%>&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=1  align=center>
						<div align="center">
							<%=cyMap.get("cyjkzk")==null ? "" : cyMap.get("cyjkzk")%>
						</div>
					</td>
				</tr>
				<%
				}
				%> 
				 
				 
				<%
				
				for(int i=0;i<6-len;i++){
				%>
				<tr style="height:40px">
					<td  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=3 align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td  colspan=2 width="10%" align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<%
				}
				%>
			<!-- �����Ϣ -->
			<tr >
				<th width="5%">
					Ӱ��<br>
					��ͥ<br>
					����<br>
					״��<br>
					�й�<br>
					��Ϣ
				</th>
				<th colspan="10" align="left" style="height:200px;line-height: 25px">
				��ͥ�˾�������<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��Ԫ����ѧ����ѧ���ѻ��������
				<U>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U><br/>                            
                <U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��<br/>
                ��ͥ������Ȼ�ֺ������<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> ��
				��ͥ����ͻ�������¼���<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��<br/>
				��ͥ��Ա��м����������Ͷ��������������<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��<br/>
				��ͥ��Աʧҵ�����<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>����ͥǷծ�����<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��<br/>
				���������<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> ��<br/>
				</th>
			</tr>
			<!-- �����Ϣ -->
			<tr style="height:120px">
				<th width="8%">
					ǩ<br>
					��<br>
				</th>
				<th width="">
					ѧ����<br>
					��ǩ��<br>
				</th>
				<th width="">

				</th>
				<th width="">
					ѧ����<br>
					�����<br>
					����ǩ<br>
					��&nbsp;&nbsp;&nbsp;&nbsp;
				</th>
				<th width="">

				</th>
				<th >
					ѧ����ͥ<br>
					���ڵ���<br>
					���ֵ�<br>
					��������<br>
					���&nbsp;&nbsp;&nbsp;&nbsp;
				</th>
				<th width="" colspan="5">	
					<div align="left">		
						���صͱ���׼��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ԫ/��.��
						<br>
						������ǩ�֣�
						<br>
						��λ���ƣ�
						<br>
					</div>
					<div align="left">
						���Ӹǹ��£�<br>
						 <U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��
					</div>
				</th>
			</tr>
			<!-- ����������Ϣ -->
			<tr style="height:40px">
				<th width="5%" rowspan="2">
					����<br>
					����<br>
					��Ϣ<br>
				</th>
				<th width="" colspan="2">
					��ϸͨѶ��ַ
				</th>
				<th width="" colspan="8">

				</th>
			</tr>
			<tr style="height:40px">
				<th width="" colspan="2">
					��������
				</th>
				<th width="" colspan="2">

				</th>
				<th width="" colspan="2">
					��ϵ�绰
				</th>
				<th width="" colspan="4">
				�����ţ���
				</th>
			</tr>
		</table>
		<div align="left">
		ע���ͱ�������ʿ��ͥ���屣�����м�ѧ���ȸ�֤���ļ���ӡ����
		</div>
		<br>
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>
		</html:form>
	</body>
</html>
