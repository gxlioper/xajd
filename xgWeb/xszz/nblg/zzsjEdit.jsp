<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
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
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=zzsjEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
		
	<html:form action="/nblg_xszz.do?method=zzsjEdit" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ǰ����λ�ã�ѧ������ - ��������ά�� - ʱ��ά��</a></p>
		</div>
		<div class="tab">
			<table width="99%"  border="0" class="formlist">
			 <thead>
   				<tr>
       				<th colspan="4"><span>ʱ��ά��</span></th>
       			</tr>
  			</thead>
			<tbody>
			<tr>
				<th  width="50%">
					<span class="red">*</span>��Ŀ����
				</th>
				<td width="50%">
					<input type="text" id="xmmc" name="xmmc" readonly="readonly"
							style="width:150px;heigh:100%"
							value="<bean:write name="rs" property="xmmc"/>"/>
				</td>
			</tr>
			<tr>
				<th >
					<span class="red">*</span><bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td>
					<input type="hidden" id="xydm" name="xydm"
							value="<bean:write name="rs" property="xydm"/>" />
					<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:150px;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="red">*</span>���뿪ʼʱ��
				</th>
				<td>
					<input type="text" readonly style="cursor:hand;width:150px"
								onclick="return showCalendar('kssj','y-mm-dd');"
								value="<bean:write name='rs' property="kssj" />"
								name="kssj" id="kssj" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="red">*</span>�������ʱ��
				</th>
				<td>
					<input type="text" readonly style="cursor:hand;width:150px"
								onclick="return showCalendar('jssj','y-mm-dd');"
								value="<bean:write name='rs' property="jssj" />"
								name="jssj" id="jssj" />
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		          	<button type="button" id="buttonSave" onclick="yz();">
						��&nbsp;��
					</button>
					<button type="button" id="buttonClose"
						onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						��&nbsp;��
					</button>					           
		          </div>
		          </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
</body>
</html>
