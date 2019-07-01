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
		<div><b><font size="4">����<bean:message key="lable.xb" />��${rs.xymc}</font></b></div><br/>
		<table width="100%" id="rsT" class="printstyle">
			<!-- ѧ�����˻������ -->
			<!-- ��һ�� -->
			<tr style="height:22px">
				<th width="5%" rowspan="4">
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
				<th width="10%">
					<div align="center">
						�� ��
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.xm }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						�Ա�
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.xb }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						��������
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.csrq }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						����
					</div>
				</th>
				<th width="">
					<div align="center">
						${rs.mzmc }
					</div>
				</th>
			</tr>
			<!-- �ڶ��� -->
			<tr>
				<th width="">
					<div align="center">
						���֤����
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.sfzh}
					</div>
				</th>
				<th width="10%">
					<div align="center">
						������ò
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.zzmmmc }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						��ͥ�˾�������
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.jtrjsr }Ԫ
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr>
				<th width="10%">
					<div align="center">
						����<bean:message key="lable.xb" />
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.xymc }
					</div>
				</th>
				
				<th width="">
					<div align="center">
						ר ҵ 
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.zymc }
					</div>
				</th>
				<th width="">
					<div align="center">
						�༶
					</div>
				</th>
				<th width="">
					<div align="center">
						${rs.bjmc }
					</div>
				</th>

			</tr>
			<!-- ������ -->
			<tr>
				<th width="">
					<div align="center">
						ѧ��
					</div>
				</th>
				<th width="">
					<div align="center">
						${rs.xh }
					</div>
				</th>
				<th width="">
					<div align="center">
						��У��ϵ�绰
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						${rs.lxdh }
					</div>
				</th>
				<th width="10%" >
					<div align="center">
						��ͥ�绰
					</div>
				</th>
				<th width="10%" colspan="">
					<div align="center">
						${rs.jtdh }
					</div>
				</th>
			</tr>
			<!-- ѧ�����������϶����� -->
			<tr style="height:200px">
				<th width="5%">
					ѧ<br>��<br>
					��<br>��<br>
					��<br>��<br>
					��<br>��<br>
					��<br>��
				</th>
				<th colspan="8" align="left" >
					<p style="height: 90px">
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
					��ע��������ϸ���˵������
					</div>
				</th>
			</tr>
			<!-- ѧ�����������϶����� -->
			<tr>
				<th width="5%" rowspan="${fjNum }">
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</th>
				<th width="" rowspan="${fjNum }">
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="left">
						<logic:iterate name="xmfjqkList" id="fj" indexId="num">
							<logic:equal name="num" value="0">
								A.${fj.fjmc }
								<logic:notEqual name="fj" property="fjmc" value="${rs.xmzzjb }">
									��
								</logic:notEqual>
								<logic:equal name="fj" property="fjmc" value="${rs.xmzzjb }">
									<img src="/xgxt/pictures/xszz/gou.jpg"></img>
								</logic:equal>
							</logic:equal>				
						</logic:iterate>
					</div>
				</th>
				<th width="10%" rowspan="${fjNum }">
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</th>
				<th width="" colspan="4" rowspan="${fjNum }">
					<div align="right">
					����С���鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
					<p align="right"><u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��
					</div>
				</th>
			</tr>
			<logic:iterate name="xmfjqkList" id="fj" indexId="num">
				<logic:notEqual name="num" value="0">
					<tr>
						<th width="10%" colspan="2">
							<div align="left">
								<logic:equal value="1" name="num">
								B.
								</logic:equal>
								<logic:equal value="2" name="num">
								C.
								</logic:equal>
								<logic:equal value="3" name="num">
								D.
								</logic:equal>
								<logic:equal value="4" name="num">
								E.
								</logic:equal>
								<logic:equal value="5" name="num">
								F.
								</logic:equal>
								${fj.fjmc }
								<logic:notEqual name="fj" property="fjmc" value="${rs.xmzzjb }">
									��
								</logic:notEqual>
								<logic:equal name="fj" property="fjmc" value="${rs.xmzzjb }">
									<img src="/xgxt/pictures/xszz/gou.jpg"></img>
								</logic:equal>
							</div>
						</th>
					</tr>
				</logic:notEqual>
			</logic:iterate>
			<!-- �϶����� -->
			<tr>
				<th width="5%">
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</th>
				<th width="10%">
					<div align="center">
						Ժ(ϵ)<br>
						���<br>
					</div>
				</th>
				<th width="" colspan="3">
					<p style="height: 120px;" align="left">
					������С���Ƽ�����Ժ��ϵ��������˺�<br>
					��  ͬ������С�������<br>
					��  ��ͬ������С�����������Ϊ:<br>
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</p>
					<div align="left">
					�������鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div align="left">
					����<bean:message key="lable.xb" />���£�&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div align="right"><u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��
					</div>					
				</th>
				<th width="10%">
					<div align="center">
						ѧ<br>У<br>ѧ<br>
						��<br>��<br>
						��<br>��<br>
						��<br>��<br>
						��<br>��<br>
						��
					</div>
				</th>
				<th width="" colspan="3">
					<p style="height: 120px;" align="left">
					��ѧ������Ժ��ϵ�����룬�����������ʵ��<br>
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
				</th>
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
