<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var sfzh = document.getElementById('sfzh').value;
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("δ���" != xxsh ) && (userType != "admin")){
				alert("ѧУ����ˣ��������޸���˽��!");
	          	return false;
			}
			
			
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=xssqSave";
			document.forms[0].submit();
		}
		function hm(){
			var hkczh = document.getElementById('hkczh').value;
			if(hkczh.length != 18){
				alert("�����ʺź���λ������!");
				return false;
			}
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ѧ������ - �鿴ѧ��������ϸ��Ϣҳ�� </a>
		</p>
	</div>
		<html:form action="zgmsxy_xszz.do?method=xssqSave" method="post">
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					Close();
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					Close();
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("��ͨ����ˣ�����������޸ģ�");
	         			return false;
	         		</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			<table class="formlist" width="90%">
				<thead>
					<tr>
						<th colspan="4">
							<span>ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="34%">
							<bean:write name='rs' property="xh" />
							<input type="hidden" id="xh" name="xh" value="<bean:write name="rs" property="xh"/>" />
							<input type="hidden" id="nd" name="nd" value="<bean:write name="rs" property="nd"/>" />
						</td>
					</logic:equal>
					<th width="16%">
						<div>
							����
						</div>
					</th>
					<td width="34%">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							�Ա�
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<th>
						<div>
							<bean:message key="lable.xb" />
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					
					<th>
						<div>
							רҵ
						</div>
					</th>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>
						<div>
							�༶
						</div>
					</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��ѧ����
						</div>
					</th>
					<td>
						<bean:write name="rs" property="rxrq"/>
					</td>
					<th>
						<div>
							��ҵʱ��
						</div>
					</th>
					<td>
						<bean:write name="rs" property="byny"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ѧ��
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xz"/>
					</td>
					<th>
						<div>
							��ͥ��ϵ�绰���̻���
						</div>
					</th>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
						�����ֻ�����
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sjhm"/>
					</td>
					<th>
						<div>
							���֤��
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sfzh"/>
						<input type="hidden" id="sfzh" name="sfzh" value="<bean:write name="rs" property="sfzh"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��ͥ�ʱ�
						</div>
					</th>
					<td>
						<bean:write name="rs" property="jtyb"/>
					</td>
					<th>
						<div>
							���֤��Ч��ֹ����
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sfzyxzzrq"/>
						<input type="hidden" id="sfzyxzzrq" name="sfzyxzzrq" value="<bean:write name="rs" property="sfzyxzzrq"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��ͥ��ϸ��ַ
						</div>
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtdz"/>
						<input type="hidden" id="jtdz" name="jtdz" value="<bean:write name="rs" property="jtdz"/>" />
					</td>
				</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>��ѧ����ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>
						<div>
							��ͥ��ϵ�绰2
						</div>
					</th>
					<td>
						<bean:write name="rs" property="lxdh2"/>
						<input type="hidden" id="lxdh2" name="lxdh2" value="<bean:write name="rs" property="lxdh2"/>" />
					</td>
					<th>
						<div>
							��ͥ��ϵ�绰3
						</div>
					</th>
					<td>
						<bean:write name="rs" property="lxdh3"/>
						<input type="hidden" id="lxdh3" name="lxdh3" value="<bean:write name="rs" property="lxdh3"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							QQ
						</div>
					</th>
					<td>
						<bean:write name="rs" property="qq"/>
						<input type="hidden" id="qq" name="qq" value="<bean:write name="rs" property="qq"/>" />
					</td>
					<th>
						<div>
							Email
						</div>
					</th>
					<td>
						<bean:write name="rs" property="email"/>
						<input type="hidden" id="email" name="email" value="<bean:write name="rs" property="email"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��������
						</div>
					</th>
					<td>
						<bean:write name="rs" property="fqxm"/>
						<input type="hidden" id="fqxm" name="fqxm" value="<bean:write name="rs" property="fqxm"/>" />
					</td>
					<th>
						<div>
							�������֤��
						</div>
					</th>
					<td>
							<bean:write name="rs" property="fqsfzh"/>
							<input type="hidden" id="fqsfzh" name="fqsfzh" value="<bean:write name="rs" property="fqsfzh"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ĸ������
						</div>
					</th>
					<td>
						<bean:write name="rs" property="mqxm"/>
						<input type="hidden" id="mqxm" name="mqxm" value="<bean:write name="rs" property="mqxm"/>" />
					</td>
					<th>
						<div>
							ĸ�����֤��
						</div>
					</th>
					<td>
							<bean:write name="rs" property="mqsfzh"/>
							<input type="hidden" id="mqsfzh" name="mqsfzh" value="<bean:write name="rs" property="mqsfzh"/>" />
					</td>
				</tr>
				<tr>
					<th>
							����ϼ�
					</th>
					<td>
						<bean:write name="rs" property="dkhj"/>
						<input type="hidden" id="dkhj" name="dkhj" value="<bean:write name="rs" property="dkhj"/>" />
					</td>
					<th>
						��һѧ��ѧϰ���úϼ�
					</th>
					<td>
						<bean:write name="rs" property="xq1"/>
						<input type="hidden" id="xq1" name="xq1" value="<bean:write name="rs" property="xq1"/>" />
					</td>
				</tr>
				<tr>
					<th>
						�ڶ�ѧ��ѧϰ���úϼ�
					</th>
					<td>
						<bean:write name="rs" property="xq2"/>
						<input type="hidden" id="xq2" name="xq2" value="<bean:write name="rs" property="xq2"/>" />
					</td>
					<th>
						����ѧ��ѧϰ���úϼ�
					</th>
					<td>
						<bean:write name="rs" property="xq3"/>
						<input type="hidden" id="xq3" name="xq3" value="<bean:write name="rs" property="xq3"/>" />
					</td>
				</tr>
				<tr>
					<th>
						����ѧ��ѧϰ���úϼ�
					</th>
					<td>
						<bean:write name="rs" property="xq4"/>
						<input type="hidden" id="xq4" name="xq4" value="<bean:write name="rs" property="xq4"/>" />
					</td>
					<th>
						����ѧ��ѧϰ���úϼ�
					</th>
					<td>
						<bean:write name="rs" property="xq5"/>
						<input type="hidden" id="xq5" name="xq5" value="<bean:write name="rs" property="xq5"/>" />
					</td>
				</tr>
				<tr>
					<th>
						ѧ�ѽ��
					</th>
					<td>
						<bean:write name="rs" property="xfje"/>
						<input type="hidden" id="xfje" name="xfje" value="<bean:write name="rs" property="xfje"/>" />
					</td>
					<th>
						ס�޷ѽ��
					</th>
					<td>
						<bean:write name="rs" property="zsfje"/>
						<input type="hidden" id="zsfje" name="zsfje" value="<bean:write name="rs" property="zsfje"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />���
						</div>
					</th>
					<td>
						<bean:write name='rs' property="yxsh" />
					</td>
					<th>
						<div>
						</div>
					</th>
					<td>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</th>
					<td colspan="3">
						<bean:write name="rs" property="yxshyj" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ѧУ���
						</div>
					</th>
					<td>
						<bean:write name='rs' property="xxsh" />
					</td>
					<th>
						<div>
						</div>
					</th>
					<td>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ѧУ������
						</div>
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xxshyj" />
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			        	<div class="bz">
							 <logic:equal name="sfksq" value="1">
					        	"<span class="red">*</span>"Ϊ������
					        	
					        	<logic:equal value="xy" name="userType">
			          				<logic:equal name="rs" property="xxsh" value="ͨ��"> 
			          					&nbsp;&nbsp;<span class="red"><bean:message key="lable.xb" />�û������޸�ѧУ�û����ͨ������Ϣ��</span>
			          				</logic:equal>
			          			</logic:equal>
					         </logic:equal>
						</div>
			          <div class="btn">
			          		<button type="button" name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
</body>
</html>
