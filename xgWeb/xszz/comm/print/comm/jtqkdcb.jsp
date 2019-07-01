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
		<div align="center" style="font-size:28px;font:'����' "><b>�ߵ�ѧУѧ������ͥ��������</b></div>
		<br>
		<div align="left" style="font-size:15px;">
		<b>
		Ժ��ϵ����<u>${rs.xymc }</u>
		רҵ��<u>${rs.zymc }</u>
		ѧ�ƣ�<u>${rs.xz } ��</u>
		�꼶��<u>${rs.nj }</u>
		</b>
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
			<tr style="height:22px">
				<th width="">
					<div align="center">
						���֤����
					</div>
				</th>
				<th width="20%" colspan="2">
					<div align="center">
						${rs.sfzh }
					</div>
				</th>
				<th width="20%">
					<div align="center">
						������ò
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.zzmmmc }
					</div>
				</th>
				<th width="">
					<div align="center">
						��ѧǰ����	
					</div>
				</th>
				<th width="">
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
			<tr style="height:22px">
				<th width="">
					<div align="center">
						��ͥ�˿���		
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.jtrs }
					</div>
				</th>
				<th width="">
					<div align="center">
						��ҵѧУ					
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.rxqdw }
					</div>
				</th>
				<th width="">
					<div align="center">
						�����س�
					</div>
				</th>
				<th width="">
					<div align="center">
						${rs.tc }
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr style="height:22px">
				<th width="">
					<div align="center">
						�� ��
					</div>
				</th>
				<th width="">
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
				<th width="">
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
				<th width="">
					<div align="center">
						<logic:empty name="rs" property="lszn">
							����&nbsp;&nbsp;
							����
						</logic:empty>
						<logic:equal name="rs" property="lszn" value="��">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��&nbsp;&nbsp;
							����
						</logic:equal>
						<logic:equal name="rs" property="lszn" value="��">
							����&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��
						</logic:equal>			
					</div>
				</th>
				<th width="">
					<div align="center">
						�ͱ���
					</div>
				</th>
				<th width="">
					<div align="center">
						<logic:empty name="rs" property="sfdb">
							����&nbsp;&nbsp;
							����
						</logic:empty>
						<logic:equal name="rs" property="sfdb" value="��">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��&nbsp;&nbsp;
							����
						</logic:equal>
						<logic:equal name="rs" property="sfdb" value="��">
							����&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							��
						</logic:equal>		
					</div>
				</th>
			</tr>
			<!-- ��ͥͨѶ��Ϣ -->
			<!-- ��һ�� -->
			<tr style="height:22px">
				<th width="5%" rowspan="3">
					<div align="center">
						��ͥ<br>
						ͨѶ<br>
						��Ϣ
					</div>
				</th>
				<th width="10%">
					<div align="center">
						��ϸͨѶ��ַ
					</div>
				</th>
				<th width="" colspan="7">
					<div align="center">
						${rs.jtdz }
					</div>
				</th>
			</tr>
			<!-- �ڶ��� -->
			<tr style="height:22px">
				<th width="10%" rowspan="2">
					<div align="center">
						�ʱ�
					</div>
				</th>
				<th width="" rowspan="2">
					<div align="center">
						${rs.jtyb }
					</div>
				</th>
				<th width="10%" rowspan="2">
					<div align="center">
						�̶��绰
					</div>
				</th>
				<th width="" rowspan="2" colspan="2">
					<div align="center">
						${rs.jtdh }
					</div>
				</th>
				<th width="" rowspan="2">
					<div align="center">
						�ֻ�
					</div>
				</th>
				<th width="">
					<div align="center">
						��
					</div>
				</th>
				<th width="">
					<div align="center">
						<logic:iterate name="cyList" id="cy">
							<logic:equal name="cy" property="mc" value="����">
								${cy.cydh }
							</logic:equal>
						</logic:iterate>
					</div>
				</th>
			</tr>
			<!-- ������ -->
			<tr style="height:22px">
				<th width="">
					<div align="center">
						ĸ
					</div>
				</th>
				<th width="">
					<div align="center">
						<logic:iterate name="cyList" id="cy">
							<logic:equal name="cy" property="mc" value="ĸ��">
								${cy.cydh }
							</logic:equal>
						</logic:iterate>
					</div>
				</th>
			</tr>
			<!-- ��ͥ��Ա��� -->
			<tr style="height:22px">
				<th width="5%" rowspan="${cyNum+1 }">
					<div align="center">
						��<br>
						ͥ<br>
						��<br>
						Ա<br>
						��<br>
						��
					</div>
				</th>
				<th width="10%">
					<div align="center">
						����
					</div>
				</th>
				<th width="10%">
					<div align="center">
						����
					</div>
				</th>
				<th width="10%">
					<div align="center">
						��ѧ����ϵ
					</div>
				</th>
				<th width="10%">
					<div align="center">
						������ѧϰ����λ
					</div>
				</th>
				<th width="10%">
					<div align="center">
						ְҵ
					</div>
				</th>
				<th width="10%">
					<div align="center">
						����״��
					</div>
				</th>
				<th width="10%">
					<div align="center">
						������루Ԫ��
					</div>
				</th>
				<th width="10%">
					<div align="center">
						���֧����Ԫ��
					</div>
				</th>
				</tr>
				<logic:iterate name="cyList" id="cy">
				<tr>
					<th width="10%">
						<div align="center">
							${cy.cyxm }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.cynl }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.mc }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.cygzdw }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.cyzy }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.cyjkzk }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.cynsr }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.cynzc }&nbsp;&nbsp;
						</div>
					</th>
				</tr>
			</logic:iterate>
			<!-- �����Ϣ -->
			<tr style="height:100px">
				<th width="5%">
					Ӱ��<br>
					��ͥ<br>
					����<br>
					״��<br>
					�й�<br>
					��Ϣ
				</th>
				<th colspan="8" align="left">
				��ͥ�˾�������:<u>${rs.jtrjsr }</u>��Ԫ����ѧ������ѧ����ְר���ѻ��������:<u>${rs.yhzzqk}</u>��<br>
				��ͥ������Ȼ�ֺ������<u>${rs.jtszqk}</u>����ͥ����ͻ�������¼���<u>${rs.tfsjqk}</u> ��<br>
				��ͥ��Ա��м����������Ͷ������������<u>${rs.cjnmqk}</u> ��<br>
				��ͥ��Աʧҵ�����<u>${rs.jtsyqk}</u> ����ͥǷծ�����<u>${rs.jtqzqk}</u>��<br>
				���������<u>${rs.jtqtqk}</u>��		
				</th>
			</tr>
			<!-- �����Ϣ -->
			<tr style="height:100px">
				<th width="5%">
					ǩ<br>
					��<br>
				</th>
				<th width="">
					ѧ<br>
					��<br>
					��<br>
					��<br>
				</th>
				<th width="">

				</th>
				<th width="">
					ѧ��<br>
					�ҳ�<br>
					���<br>
					����
				</th>
				<th width="">

				</th>
				<th width="">
					ѧ����ͥ<br>
					���ڵ���<br>
					���ֵ�<br>
					��������
				</th>
				<th width="" colspan="3">	
					<div align="left">		
						������ǩ�֣�
						<br>
						<br>
						��λ���ƣ�
						<br>
					</div>
					<div align="right">
						���Ӹǹ��£�<br>
						         ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</div>
				</th>
			</tr>
			<!-- ����������Ϣ -->
			<tr style="height:22px">
				<th width="5%" rowspan="2">
					����<br>
					����<br>
					��Ϣ<br>
				</th>
				<th width="" colspan="2">
					��ϸͨѶ��ַ
				</th>
				<th width="" colspan="6">

				</th>
			</tr>
			<tr style="height:22px">
				<th width="" colspan="2">
					��������
				</th>
				<th width="" colspan="2">

				</th>
				<th width="" colspan="2">
					��ϵ�绰
				</th>
				<th width="" colspan="2">

				</th>
			</tr>
		</table>
		<div align="left">
		��1������ӡ��Ч�� 2������ʵ����������д���κ������Ϣһ����ʵ����������У�����������Ӱ�졣
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
