<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript">
		function Save(value){
		   var jtgznr = document.getElementById('jtgznr').value;
		   var ykzgzqk = document.getElementById('ykzgzqk').value;
		   var sqsj = document.getElementById('sqsj').value;
		   var scsqsj = document.getElementById('scsqsj').value;
		   if(jtgznr.length > 1000){
		   		alert('���幤�����ݲ��ܴ���1000�֣�');
		   	   	return false;
		   }
		   if(ykzgzqk.length > 1000){
		   		alert('�ѿ�չ����������ܴ���1000�֣�');
		   	   	return false;
		   }
		   if(sqsj == scsqsj){
		   		alert('����ס���ҽ����Ѿ�������ɫ����,�����ظ����룡');
		   	   	return false;
		   }
		   document.forms[0].action = "/xgxt/zjlg_gygl.do?method=tsqssq&doType=save&act="+value;
		   document.forms[0].submit();
		}		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ�� 
			document.forms[0].action = "/xgxt/zjlg_gygl.do?method=viewTsqsSqxx&doType=print";
			document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		}
	</script>
</head>
<body>
	<logic:equal value="yes" name="info">
		<br/>
		<br/>
		<br/>
		<p align="center"><font color="red" size="3">��ҳ��ֻ����ѧ�����ʣ�</font></p>
	</logic:equal>
	<logic:notEqual value="yes" name="info">
		<logic:equal value="no" name="rzxx">
			<br/>
			<br/>
			<br/>
			<p align="center"><font color="red" size="2">����û����ס����������A������</font></p>
		</logic:equal>
		<logic:notEqual value="no" name="rzxx">
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ԣ���� - A�����ҹ��� - ���� - ��ɫ����</a>
				</p>
			</div>
			<html:form action="/zjlg_gygl" method="post">
				<input type="hidden" name="ssbh" value="<bean:write name="rs" property="ssbh"/>"
					id="ssbh" />
				<input type="hidden" name="sqsj" value="<bean:write name="rs" property="sqsj"/>"
					id="sqsj" />
				<input type="hidden" name="scsqsj" value="<bean:write name="rs" property="scsqsj"/>"
					id="scsqsj" />
					
				<div class="tab">
				<table class="formlist" width="90%">
					<thead><tr><th colspan="4"><span>��Ϣά��</span></th></tr></thead>
					<tbody>
					<tr>
						<th>¥��</th>
						<td>
							<input type="hidden" name="lddm" value="<bean:write name="rs" property="lddm"/>" />
							<html:select name="rs" property="lddm" styleId="lddm" disabled="true">
								<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
							</html:select>
						</td>
						<th>
							¥��
						</th>
						<td>
							<input type="hidden" name="lc"
								value="<bean:write name="rs" property="lc"/>" />
							<html:select name="rs" property="lc" styleId="lc" disabled="true">
								<html:options collection="lcList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td>
							<input type="hidden" name="qsh"
								value="<bean:write name="rs" property="qsh"/>" />
							<html:select name="rs" property="qsh" styleId="qsh" disabled="true">
								<html:options collection="qshList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th>
							ѧ��
						</th>
						<td>
							<bean:write name="rs" property="xn" />
						</td>

					</tr>
					<tr>
						<th>
							����ʱ��
						</th>
						<td>
							<bean:write name="rs" property="sqsj" />
						</td>
						<th>
							��ϵ�绰
						</th>
						<td>
							<input type="text" id="lxdh" name="lxdh" maxlength="50"
								style="180px;heigh:100%"
								value="<bean:write name="rs" property="lxdh"/>" />
						</td>
					</tr>
					<tr>
						<th>
							���幤������
							<br/>
							<font color="red">(��1000����) </font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="jtgznr" rows="10" styleId="jtgznr"
								style="width: 95%;word-break:break-all;" />
						</td>
					</tr>
					<tr>
						<th>
							�ѿ�չ�������
							<br/>
							<font color="red">(��1000����)</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="ykzgzqk" rows="10" styleId="ykzgzqk"
								style="width: 95%;word-break:break-all;" />
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
							 <logic:equal name="commit" value="no">
								<button onclick="Save('apply')" id="buttonSave">
									����
								</button>
							</logic:equal>
							<logic:notEqual name="commit" value="no">
								<button onclick="Save('modify')" id="buttonSave">
									����
								</button>
							</logic:notEqual>
							<button class="button2" onclick="toPrintOut();">
									��ӡ
							</button>
				          </div></td>
				      </tr>
				    </tfoot>
				</table>
				</div>
			</html:form>
		</logic:notEqual>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
			    alert('�����ɹ���');
			  </script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
			    alert('����ʧ�ܣ�');
			  </script>
		</logic:equal>
		<logic:equal value="no" name="isajqs">
			<script type="text/javascript">
			    alert('���������ұ�ѧ��û��ù�A�����ң����ܽ������룡');
			</script>
		</logic:equal>
	</logic:notEqual>
</body>
</html>
