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
			var xydm = document.getElementById('save_xydm').value;
			var kssj = document.getElementById('save_sqkssj').value;
			var jssj = document.getElementById('save_sqjssj').value;
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
			document.forms[0].action = "/xgxt/jsspZds.do?method=zxdksjEdit&doType=oneUpdate";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>�ճ�����-��������-����ʱ������</a>
		</p>
	</div>
	<html:form action="/zgmsxy_xszz.do?method=zxdksjEdit" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("����ɹ���");
	         	Close();window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:equal>
		</logic:present>
		<div class="tab">
		<table class="formlist" width="90%">
			<tr>
				<th>
						<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td>
						<input type="hidden" id="save_xydm" name="save_xydm"
							value="${rs.xydm }" />
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							value="${rs.xymc }"/>
				</td>
			</tr>
			<tr>
				<th>
						 ���뿪ʼʱ��
				</th>
				<td>
					<input type="text" readonly="readonly"
								onclick="return showCalendar('save_sqkssj','y-mm-dd');"
								value="${rs.sqkssj }"
								name="save_sqkssj" id="save_sqkssj" />
				</td>
			</tr>
			<tr>
				<th>
						�������ʱ��
				</th>
				<td>
					<input type="text" readonly="readonly"
								onclick="return showCalendar('save_sqjssj','y-mm-dd');"
								value="${rs.sqjssj }"
								name="save_sqjssj" id="save_sqjssj" />
				</td>
			</tr>
			
			<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			          	<button type="button" name="�ύ" onclick="yz();">����</button>
			            <button type="button" name="�ر�" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			   
		</table>
		</div>
	</html:form>
</body>
</html>
