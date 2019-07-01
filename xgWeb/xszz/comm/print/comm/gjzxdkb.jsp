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
		<div align="center" style="font-size:24px;font:'����' ">
			<b>${xxmc }��ͥ��������ѧ�����������ѧ������˱�</b>
		</div>
		<br>
		<div align="left" style="font-size:15px;">
		</div>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<!-- ѧ�����˻������ -->
			<!-- ��һ�� -->
			<tr style="height:22px">
				<th width="10%">
					<div align="center">
						�� ��
					</div>
				</th>
				<th width="20%">
					<div align="center">
						${rs.xm }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						Ժ(ϵ)
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						${rs.xymc }
					</div>
				</th>
			</tr>
			<!-- �ڶ��� -->
			<tr>
				<th width="">
					<div align="center">
						�꼶
					</div>
				</th>
				<th width="">
					<div align="center">
						${rs.nj }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						רҵ
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						${rs.zymc }
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr>
				<th width="">
					<div align="center">
						��ͥסַ(ͨѶ��ַ)
					</div>
				</th>
				<th width="" colspan="5">
					<div align="center">
						${rs.jtdz }
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr>
				<th width="">
					<div align="center">
						�ҳ���ϵ��ַ(�̶��绰)
					</div>
				</th>
				<th width="" colspan="5">
					<div align="left">
						<logic:notEmpty name="cyList">
							<logic:iterate name="cyList" id="cy">
								<logic:notEmpty name="cy" property="mc">
								${cy.mc }:${cy.cygzdw }��${cy.cydh }��<br>
								</logic:notEmpty>
							</logic:iterate>
						</logic:notEmpty>
					</div>
				</th>
			</tr>
			<!-- ѧ�����������϶����� -->
			<tr style="height:150px">
				<th width="5%">
					ѧ����<br>
					������<br>
					������<br>
					ѧ����<br>
					���Լ�<br>
					ͥ����<br>
					������<br>
					������<br>
					˵��
				</th>
				<th colspan="5" align="left">
					<p style="height: 120px">
					�������ͥ�������ѡ��޷�������ѧ������÷ѡ������������������ѧ���<br>
					��ͥ�������Ѿ����������:${rs.sqsm }
					</p>
					<div align="right">
					ѧ������ǩ��������ӡ:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
					</div>
				</th>
			</tr>
			<!-- �ҳ��Ƿ�ͬ�⺢����У���������ѧ���� -->
			<tr style="height:150px">
				<th width="5%">
					�ҳ���<br>
					��ͬ��<br>
					������<br>
					У����<br>
					������<br>
					ѧ����
				</th>
				<th colspan="5" align="left">
					<p style="height: 100px">
					���ͥ�������ѣ���ͬ���ҵĺ������������ѧ��������ٺ������غ�ͬ�涨����ʱ���
					</p>
					<div align="right">
					�ҳ�ǩ��������ӡ:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
					</div>
				</th>
			</tr>
			<!-- �϶����� -->
			<tr>
				<th width="5%">
					<div align="center">
						����ί<br>
						Ա���<br>
						�ֵ���<br>
						ί��<br>
						���:
					</div>
				</th>
				<th width="" colspan="3">			
					<p style="height: 100px;" align="left">
					������ͥ��������Ƿ���ʵ?
					</p>
					<div align="right">
					�ǹ���:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
					</div>		
				</th>
				<th width="10%">
					<div align="center">
						��(��)��<br>
						������<br>
						��ؼ�<br>
						�нֵ�<br>
						���´�<br>
						���:
					</div>
				</th>
				<th width="" colspan="">
					<p style="height: 100px;" align="left">
					������ͥ��������Ƿ���ʵ?
					</p>
					<div align="right">
					�ǹ���:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
					</div>		
				</th>
			</tr>
			<!-- �϶����� -->
			<tr>
				<th width="5%">
					<div align="center">
						Ժ(ϵ) <br>
						�Ƽ����
					</div>
				</th>
				<th width="" colspan="5">			
					<p style="height: 120px;" align="left">
					������<u>${rs.rxrq }</u>����ѧ.
					��<u>${rs.xymc }</u>ϵԺ(ȫ��)
					<u>${rs.zymc }</u>רҵ(ȫ��)ѧϰ��<br>
					ѧ��<u>${rs.xz }</u>�꣬
					����ˣ�ͬ���Ƽ������������ѧ���<br>
					�ܽ��Ϊ<u>${rs.zje }</u> Ԫ(��д) ��<br>
					����ÿѧ���������<u>${rs.xnje }</u>Ԫ(��д)��<br>
					</p>
					<div align="right">
					Ժ (ϵ)�����������鳤ǩ�ֲ���ϵ(Ժ)����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
					</div>		
				</th>
			</tr>		
		</table>
		<div align="left">
		��ע:1��������Ϣ�ú�ɫ�ֱʻ�ˮ����д��<br>
		&nbsp;&nbsp;&nbsp;&nbsp;
		2���븽��ĸ���֤�����渴ӡ����û�����֤�����ڱ���ӡ�����л����µ�ҳ���Լ���ĸ��ҳ��Ҫ��ӡ����<br>
		&nbsp;&nbsp;&nbsp;&nbsp;
		3���������Ч��

		</div>
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
