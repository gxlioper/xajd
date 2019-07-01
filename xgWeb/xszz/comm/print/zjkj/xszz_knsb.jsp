<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

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
		<div align="center" style="font-size:28px;font:'����' "><b>�ߵ�ѧУ��ͥ��������ѧ���϶������</b></div>
		<br>
		<div align="left" style="font-size:15px;">
			<bean:message key="lable.xb" />��<u>${xxmc }</u>
		</div>
		<br>
		<table width="100%" id="rsT" class="tbstyle">
			<!-- ѧ�����˻������ -->
			<!-- ��һ�� -->
			<tr style="height:22px">
				<td width="5%" rowspan="4">
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
				</td>
				<td width="10%">
					<div align="center">
						�� ��
					</div>
				</td>
				<td width="10%">
					<div align="center">
						${rs.xm }
					</div>
				</td>
				<td width="10%">
					<div align="center">
						�Ա�
					</div>
				</td>
				<td width="10%">
					<div align="center">
						${rs.xb }
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����������
					</div>
				</td>
				<td width="10%">
					<div align="center">
						${rs.csrq }
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����
					</div>
				</td>
				<td width="">
					<div align="center">
						${rs.mzmc }
					</div>
				</td>
			</tr>
			<!-- �ڶ��� -->
			<tr>
				<td width="">
					<div align="center">
						���֤����
					</div>
				</td>
				<td width="" colspan="2">
					<div align="center">
						${rs.sfzh }
					</div>
				</td>
				<td width="10%">
					<div align="center">
						������ò
					</div>
				</td>
				<td width="10%" colspan="2">
					<div align="center">
						${rs.zzmmmc }
					</div>
				</td>
				<td width="10%">
					<div align="center">
						��ͥ�˾�������
					</div>
				</td>
				<td width="10%">
					<div align="center">
						${rs.jtrjsr }Ԫ
					</div>
				</td>
			</tr>
			<!-- ������ -->
			<tr>
				<td width="10%">
					<div align="center">
						����<bean:message key="lable.xb" />
					</div>
				</td>
				<td width="10%" colspan="2">
					<div align="center">
						${rs.xymc }
					</div>
				</td>
				
				<td width="">
					<div align="center">
						רҵ
					</div>
				</td>
				<td width="">
					<div align="center">
						${rs.zymc }
					</div>
				</td>
				
				<td width="" colspan="2">
					<div align="center">
						�༶
					</div>
				</td>
				<td width="">
					<div align="center">
						${rs.bjmc }
					</div>
				</td>
			</tr>
			<!-- ������ -->
			<tr>
				<td width="">
					<div align="center">
						ѧ��
					</div>
				</td>
				<td width="" colspan="1">
					<div align="center">
						${rs.xh}
					</div>
				</td>
				
				<td width="">
					<div align="center">
						��У��ϵ�绰
					</div>
				</td>
				<td width="" colspan="2">
					<div align="center">
						${rs.lxdh }
					</div>
				</td>
				<td width="10%"  colspan="1">
					<div align="center">
						��ͥ�绰
					</div>
				</td>
				<td width="10%" colspan="3">
					<div align="center">
						${rs.jtdh }
					</div>
				</td>
			</tr>
			<!-- ѧ�����������϶����� -->
			<tr style="height:200px">
				<td width="5%">
					ѧ��<br>
					����<br>
					����<br>
					�϶�<br>
					����
				</td>
				<td colspan="8" align="left">
					<p style="height: 190px">
					<br>
					${rs.rdly }
					</p>
					<div align="right">
					ѧ��ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��		
					</div>
					<div align="left">
					ע��������ϸ���˵����
					</div>
				</td>
			</tr>
			<!-- ѧ�����������϶����� -->
			<tr>
				<td width="5%" rowspan="${fjNum }">
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</td>
				<td width="" rowspan="${fjNum }">
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</td>
				<td width="10%" colspan="2">
					<div align="center">
						<logic:iterate name="xmfjqkList" id="fj" indexId="num">
							<logic:equal name="num" value="0">
								${fj.fjmc }��
							</logic:equal>				
						</logic:iterate>
					</div>
				</td>
				<td width="10%" rowspan="${fjNum }">
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</td>
				<td width="" colspan="4" rowspan="${fjNum }">
					<div align="right">
					����С���鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
					<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��
					</div>
				</td>
			</tr>
			<logic:iterate name="xmfjqkList" id="fj" indexId="num">
				<logic:notEqual name="num" value="0">
					<tr>
						<td width="10%" colspan="2">
							<div align="center">
								${fj.fjmc } 
								<logic:notEqual name="fj" property="fjmc" value="${rs.xmzzjb }">
									��
								</logic:notEqual>
								<logic:equal name="fj" property="fjmc" value="${rs.xmzzjb }">
									<img src="/xgxt/pictures/xszz/gou.jpg"></img>
								</logic:equal>
							</div>
						</td>
					</tr>
				</logic:notEqual>
			</logic:iterate>
			<!-- �϶����� -->
			<tr>
				<td width="5%">
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</td>
				<td width="10%">
					<div align="center">
						Ժ��ϵ��<br>
						���<br>
					</div>
				</td>
				<td width="" colspan="3">
					<p style="height: 190px;" align="left">
					������С���Ƽ�����<bean:message key="lable.xb" />������˺�<br>
					��  ͬ������С�������<br>
					��  ��ͬ������С�����������Ϊ:<br>
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</p>
					<div align="left">
					�������鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div align="right"><u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��
					</div>					
				</td>
				<td width="10%">
					<div align="center">
						ѧУ<br>
						ѧ��<br>
						����<br>
						����<br>
						����<br>
						���
					</div>
				</td>
				<td width="" colspan="3">
					<p style="height: 190px;" align="left">
					��ѧ������<bean:message key="lable.xb" />���룬�����������ʵ��<br>
					��  ͬ�⹤���������С�������<br>
					��  ��ͬ�⹤���������С�����������Ϊ��<br>
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</p>
					<div align="left">
					������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div align="right"><u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��
					</div>		        
					<div align="right">
						���Ӹǲ��Ź��£�
					</div>
				</td>
			</tr>
		</table>
		<br>
		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>
		</html:form>
	</body>
</html>
