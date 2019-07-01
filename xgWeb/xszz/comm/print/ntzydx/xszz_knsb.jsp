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
		</div>
		<br>
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
						����������
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
						${rs.sfzh }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						����Ա
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
						${rs.jtrjsr }
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr>
				<th width="">
					<div align="center">
						<bean:message key="lable.xb" />
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${xxmc }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						ϵ
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.xymc }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						רҵ
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.zymc }
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr>
				<th width="">
					<div align="center">
						�꼶
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.nj }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						�༶
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.bjmc }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						��У��ϵ�绰
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.lxdh } 
					</div>
				</th>
			</tr>
			<!-- ѧ�����������϶����� -->
			<tr style="height:200px">
				<th width="5%">
					ѧ��<br>
					����<br>
					����<br>
					�϶�<br>
					����
				</th>
				<th colspan="8" align="left">
					<p style="height: 190px">					
					${rs.rdly }
					</p>
					<div align="right">
					ѧ��ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
					</div>
					<div align="left">
					ע��������ϸ���˵����
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
				<th width="10%" rowspan="${fjNum }">
					<div align="center">
						��<br>
						��<br>
						��<br>
						��
					</div>
				</th>
				<th width="" colspan="4" rowspan="${fjNum }">
					<p style="height: 190px;" align="left">
					Ӧ�μ���������������____�ˣ�ʵ�ʲμ���������������____�ˡ����������飬������£�
					</p>
					<div align="right">
					����С���鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��
					</div>
				</th>
			</tr>
			<logic:iterate name="xmfjqkList" id="fj" indexId="num">
				<logic:notEqual name="num" value="0">
					<tr>
						<th width="10%" colspan="2">
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
				<th width="10%">
					<div align="center">
						Ժ<br>
						��ϵ��<br>
						��<br>
						��<br>
					</div>
				</th>
				<th width="" colspan="3">
					<p style="height: 190px;" align="left">
					������С���Ƽ�����Ժ��ϵ��������˺�<br>
					��  ͬ������С�������<br>
					��  ��ͬ������С�����������Ϊ:<br>
					</p>
					<div align="left">
					�������鳤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div align="right">
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��
					</div>					
				</th>
				<th width="10%">
					<div align="center">
						Уѧ<br>
						����<br>
						����<br>
						����<br>
						����<br>
						��
					</div>
				</th>
				<th width="" colspan="3">
					<p style="height: 190px;" align="left">
					��ѧ������Ժ��ϵ�����룬�����������ʵ��<br>
					��  ͬ�⹤���������С�������<br>
					��  ��ͬ�⹤���������С�����������Ϊ��<br>
					</p>
					<div align="left">
					������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div align="right">
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;
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
