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
		<div align="center" style="font-size:28px;font:'����' "><b>��ͥ��������ѧ���϶������</b></div>
		<br>
		<div align="left" style="font-size:15px;">
		<b>ѧУ��  �㽭�ʵ�ְҵ����ѧԺ</b> 
		</div>
		<table width="100%" id="rsT" class="printstyle">
			<!-- ѧ�����˻������ -->
			<!-- ��һ�� -->
			<tr style="height:45px">
				<th width="5%" rowspan="4">
					<div align="center">
						ѧ<br>��<br>
						��<br>��<br>
						��<br>��<br>
						��<br>��
					</div>
				</th>
				<th width="8%">
					<div align="center">
						�� ��
					</div>
				</th>
				<th width="12%">
					<div align="center">
						${rs.xm }
					</div>
				</th>
				<th width="6%">
					<div align="center">
						�Ա�
					</div>
				</th>
				<th width="7%" colspan="2">
					<div align="center">
						${rs.xb }
					</div>
				</th>
				<th width="6%">
					<div align="center">
						����<br/>
						����
					</div>
				</th>
				<th width="15%" colspan="2">
					<div align="center">
						${rs.csrq }
					</div>
				</th>
				<th width="8%">
					<div align="center">
						����
					</div>
				</th>
				<th width="8%">
					<div align="center">
						${rs.mzmc }
					</div>
				</th>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:45px">
				<th width="8%">
					<div align="center">
						���֤<br/>
						����
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.sfzh }
					</div>
				</th>
				<th  colspan="2">
					<div align="center">
						����<br/>
						��ò
					</div>
				</th>
				<th width="12%" colspan="2">
					<div align="center">
						${rs.zzmmmc }
					</div>
				</th>
				<th width="9%">
					<div align="center">
						��ͥ�˾�<br/>������
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						<logic:empty name="rs" property="jtrjsr">
						&nbsp;&nbsp;&nbsp;&nbsp;Ԫ
						</logic:empty>
						<logic:notEmpty name="rs" property="jtrjsr">
						${rs.jtrjsr }Ԫ
						</logic:notEmpty>
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr style="height:45px">
				<th width="8%">
					<div align="center">
						ϵ  ��
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${xymc }
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						�� ��
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.bjmc}
					</div>
				</th>
				<th width="10%">
					<div align="center">
						ר ҵ
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.zymc }
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr style="height:45px">
				<th width="8%">
					<div align="center">
						��У��<br/>ϵ�绰
					</div>
				</th>
				<th width="10%" colspan="9">
					<div align="center">
						${rs.lxdh } 
					</div>
				</th>
			</tr>
			<!-- ѧ�����������϶����� -->
			<tr style="height:180px">
				<th width="5%">
					ѧ<br/>��<br/>
					��<br/>��<br/>
					��<br/>��<br/>
					��<br/>��<br/>
					��<br/>��
				</th>
				<th colspan="10" align="left">
					<p style="height: 190px">
					
					${rs.sqly }
					</p>
					<div align="right">
					ѧ��ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
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
						��<br><br>
						��<br><br>
						��<br><br>
						��
					</div>
				</th>
				<th width="8%" rowspan="${fjNum }">
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</th>
				<th width="12%">
					<div align="center">
						<logic:iterate name="xmfjqkList" id="fj" indexId="num">
							<logic:equal name="num" value="0">
								${fj.fjmc }
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
				<th width="6%" rowspan="${fjNum }" >
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</th>
				<th width="" colspan="7" rowspan="${fjNum }">
					<p style="height: 170px;" align="left">
				
					</p>
					<div align="right">
					����С���鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��
					</div>
				</th>
			</tr>
			<logic:iterate name="xmfjqkList" id="fj" indexId="num">
				<logic:notEqual name="num" value="0">
					<tr>
						<th width="10%" colspan="1">
							<div align="center">
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
				<th width="8%">
					<div align="center">
						Ժ��ϵ��<br>
						<br>
						���<br>
					</div>
				</th>
				<th width="" colspan="2">
					<p style="height: 170;" align="left">
					������С���Ƽ�����Ժ��ϵ��������˺�<br>
					��  ͬ������С�������<br>
					��  ��ͬ������С�����������Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�� 
					<br>
					</p>
					<div align="left">
					�������鳤ǩ�֣�
					</div>
					<div align="right"><u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;</u>
					��
					</div>					
				</th>
				<th width="5%">
					<div align="center">
						ѧ<br/>
						У<br/>ѧ<br/>
						��<br/>��<br/>
						��<br/>��<br/>
						��<br/>��<br/>
						��<br/>��<br/>
						��<br/>
					</div>
				</th>
				<th width="" colspan="6">
					<p style="height: 100px;vertical-align: top" align="left" >
					��ѧ������Ժ��ϵ�����룬�����������ʵ��<br>
					��  ͬ�⹤���������С�������<br>
					��  ��ͬ�⹤���������С�����������Ϊ<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�� 
					</p>
					<div align="left">
					������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<br/>
					<div align="right"><u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					��
					</div>		        
					<div align="left">
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
