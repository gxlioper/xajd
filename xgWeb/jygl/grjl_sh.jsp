<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function grjlshgo(){
			var xxtj = document.getElementById("xxtj").value;
			var xxsh = document.getElementById("xxsh").value;
			
			if(xxsh=="��ͨ����"&&xxtj==""){
			    if(confirm("��ȷ����ʱ��Ϊ������дѧУ�Ƽ���")){
			        document.forms[0].action = "/xgxt/grjlshsave.do?act=shenhe&doType=shenghe";
				 	document.forms[0].submit();
				 	return true;
			    }else{
			    return false;
			    }
			}	 		
				 	document.forms[0].action = "/xgxt/grjlshsave.do?act=shenhe&doType=shenghe";
				 	document.forms[0].submit();
		    }
		</script>
	</head>
	<body>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>���˼�����ϸ��Ϣ</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" class="formlist" id="grjl">
					<thead>
						<tr><th colspan="5"><span></span></th></tr>
					</thead>
						<thead>
					<tr>
						<th colspan="5"><span>��������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							<th width="15%">���֤��</th>
							<td width="30%"><html:text name="rs" property="id" readonly="true"/>
								<html:checkbox name="rs" property="idsee" value="no" />
								(����) &nbsp;&nbsp; 
							</td>
						</logic:notEqual>
						<th width="15%">����ʱ��</th>
						<td width="25%"><html:text name="rs" property="fbsj" style="width:130px" readonly="true" /></td>
						<td rowspan="5" align="center" width="15%">
							��Ƭ
						</td>
					</tr>
					<tr>
						<th>����<bean:message key="lable.xsgzyxpzxy" /></th>
						<td><html:text name="rs" property="xymc" readonly="true" />
						</td>
						<th>ѧ��</th>
						<td><html:text name="rs" property="xsxh" readonly="true"/></td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td>
							<bean:write name="rs" property="name" />
						</td>
						<th>
							�Ա�
						</th>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
					</tr>
					<tr>
						<th>
							��������
						</th>
						<td>
							<bean:write name="rs" property="csny" />
						</td>
						<th>
							����
						</th>
						<td>
							<bean:write name="rs" property="mz"/>
						</td>
					</tr>
					<tr>
						<th>
							ѧ��
						</th>
						<td>
							<bean:write name="rs" property="xl" />
						</td>
						<th>
							������ò
						</th>
						<td>
							<bean:write name="rs" property="zzmm" />
						</td>
					</tr>
					<tr>
						<th>
							רҵ����
						</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<th>
							����רҵ
						</th>
						<td colspan="2">
							<bean:write name="rs" property="fxzymc" />
						</td>
					</tr>
				</tbody>
				<thead>
					<tr><th colspan="5"><span>��ϵ��ʽ</span></th></tr>
				</thead>	
				<tbody>
					<tr>
						<th>
							��ϵ��ַ
						</th>
						<td>
							<bean:write name="rs" property="lxdz" />
						</td>
						<th>
							��ϵ�绰
						</th>
						<td colspan="2">
							<bean:write name="rs" property="lxdh" />
						</td>
					</tr>
					<tr>
						<th>
							��������
						</th>
						<td>
							<bean:write name="rs" property="yzbm" />
						</td>
						<th>
							��������
						</th>
						<td colspan="2">
							<bean:write name="rs" property="email" />
						</td>
					</tr>
				</tbody>
					<thead>
					<tr>
						<th colspan="5"><span>ѧ���ۺ����</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							�����
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="hjqk" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							ѧϰ���
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="xxqk" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							У�����Ͻ���
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="xjysjl" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							���ʵ�����
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="shsjqk" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							��������
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="gzjl" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							�����س�
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="grtc" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							�����Ƽ�
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="zwtj" rows="6"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
					<tr>
						<th>
							ѧУ�Ƽ�
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="xxtj" rows="8"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					</logic:equal>
				</tbody>
				<thead><tr><th colspan="5"><span>���</span></th></tr></thead>
	
				<logic:equal value="xy" name="ldgxusertype">
					<tr id="fdysh1">
						<th>
							����Ա���
						</th>
						<td>
							<html:select name="rs" property="fdysh">
								<html:option value="δ���">δ���</html:option>
								<html:option value="δͨ��X">δͨ��X</html:option>
								<html:option value="��ͨ����">��ͨ����</html:option>
							</html:select>
						</td>				
						<th>
							�����
						</th>
						<td colspan="2">
							<html:text name="rs" property="fdyshr" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							���ʱ��
						</th>
						<td>
							<html:text name="rs" property="fdyshsj" readonly="true"/>
						</td>
						<th>
							��ͨ��ԭ���޸����
						</th>
						<td colspan="2" >
							<html:textarea name="rs" property="fdyshyj" rows="4"
								style="width: 95%;word-break:break-all;"/>
						</td>
					</tr>
					<tr id="xxsh1">
						<th>
							ѧУ���
						</th>
						<td>
							<html:text name="rs" property="xxsh" readonly="true"/>
						</td>				
						<th>
							�����
						</th>
						<td colspan="2">
							<html:text name="rs" property="shperson" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							���ʱ��
						</th>
						<td>
							<html:text name="rs" property="shsj" readonly="true"/>
						</td>
						<th>
							��ͨ��ԭ���޸����
						</th>
						<td colspan="2">
							<html:textarea name="rs" property="btgyy" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true"/>
						</td>
					</tr>
					</logic:equal>
					<logic:equal value="xx" name="ldgxusertype">
					<logic:notEqual value="10338" name="xxdm" scope="session">
					<tr>
						<th>
							����Ա���
						</th>
						<td>
							<html:text name="rs" property="fdysh" readonly="true"/>
						</td>				
						<th>
							�����
						</th>
						<td colspan="2">
							<html:text name="rs" property="fdyshr" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							���ʱ��
						</th>
						<td>
							<html:text name="rs" property="fdyshsj" readonly="true"/>
						</td>
						<th>
							��ͨ��ԭ���޸����
						</th>
						<td colspan="2" >
							<html:textarea name="rs" property="fdyshyj" rows="4" style="width: 95%;word-break:break-all;" readonly="true"/>
						</td>
					</tr>
					</logic:notEqual>
					<tr id="xxsh1">
						<th>
							ѧУ���
						</th>
						<td>
							<html:select name="rs" property="xxsh">
								<html:option value="δ���">δ���</html:option>
								<html:option value="δͨ��X">δͨ��X</html:option>
								<html:option value="��ͨ����">��ͨ����</html:option>
							</html:select>
						</td>				
						<th>
							�����
						</th>
						<td colspan="2">
							<html:text name="rs" property="shperson" readonly="true"
								style="width=100%" />
						</td>
					</tr>
					<tr>
						<th>
							���ʱ��
						</th>
						<td>
							<html:text name="rs" property="shsj" readonly="true"/>
						</td>
						<th>
							��ͨ��ԭ���޸����
						</th>
						<td colspan="2" >
							<html:textarea name="rs" property="btgyy" rows="4"
								style="width: 95%;word-break:break-all;"/>
						</td>
					</tr>
					</logic:equal>
				</table>
			</div>
			<div class="buttontool" align="center">
				<button class="button2" onclick="grjlshgo()" style="width:80px">
					�� ��
				</button>
				<button class="button2"
					onclick="Close();window.dialogArguments.document.getElementById('query_go').click();"
					style="width:80px">
					�� ��
				</button>
			</div>
			<logic:notEmpty name="shenhe">
				<logic:equal name="shenhe" value="ok">
					<script>
                       alert("�ύ�ɹ���");
                    </script>
				</logic:equal>
				<logic:equal name="shenhe" value="no">
					<script>
                      alert("�ύʧ�ܣ�");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
