<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xydm = document.getElementById('xydm').value;
			var kssj = document.getElementById('kssj').value;
			var jssj = document.getElementById('jssj').value;
			if((xydm == null) || (xydm == "")){
				alert("��ѡ��<bean:message key="lable.xsgzyxpzxy" />!");
				return false;
			}
			if((kssj == null) || (kssj == "")){
				alert("���뿪ʼʱ�䲻��Ϊ��!");
				return false;
			}
			if((jssj == null) || (jssj == "")){
				alert("�������ʱ�䲻��Ϊ��!");
				return false;
			}
			if (kssj > jssj){
				alert("���뿪ʼʱ�䲻�ܴ����������ʱ�䣡");
				return false;
			}
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=zxdksjEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>${title }</a>
		</p>
	</div>
	<html:form action="/zgmsxy_xszz.do?method=zxdksjEdit" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	Close();window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab">
		<table class="formlist" width="90%">
			<tr>
				<th width="30%">
					��Ŀ����
				</th>
				<td width="70%">
						<input type="text" id="xmmc" name="xmmc" readonly="readonly"
							value="<bean:write name="rs" property="xmmc"/>"/>
				</td>
			</tr>
			<tr>
				<th>
						<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td>
						<input type="hidden" id="xydm" name="xydm"
							value="<bean:write name="rs" property="xydm"/>" />
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							value="<bean:write name="rs" property="xymc"/>"/>
				</td>
			</tr>
			<tr>
				<th>
						 ���뿪ʼʱ��
				</th>
				<td>
					<input type="text" readonly="readonly"
								onclick="return showCalendar('kssj','y-mm-dd');"
								value="<bean:write name='rs' property="kssj" />"
								name="kssj" id="kssj" />
				</td>
			</tr>
			<tr>
				<th>
						�������ʱ��
				</th>
				<td>
					<input type="text" readonly="readonly"
								onclick="return showCalendar('jssj','y-mm-dd');"
								value="<bean:write name='rs' property="jssj" />"
								name="jssj" id="jssj" />
				</td>
			</tr>
			<logic:equal name="writeAble" value="yes">
			<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			          	<button type="button" name="�ύ" onclick="yz();">����</button>
			            <button type="button" name="�ر�" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    </logic:equal>
		</table>
		</div>
	</html:form>
</body>
</html>
