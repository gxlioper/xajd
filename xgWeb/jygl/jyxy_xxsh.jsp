<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>	
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript">
	    function shgo(){
	    var fdysh = document.getElementById("fdysh").value;
	    var xxsh = document.getElementById("xxsh").value;
	    if(fdysh=="δͨ��X"&&xxsh=="δͨ��X"){
	    alert("�㲻���ظ������Э�飡");
	    return false;
	    }
			 	document.forms[0].action = "/xgxt/jyglJyxyXxsh.do?act=shenhe&doType=shenghe";
			 	document.forms[0].submit();
	    }
	    
	    function chekhid(){
			var fdyval = document.getElementById("xxsh").value;
			var hidval = document.getElementById("btgtr");
			if(fdyval == "δͨ��X"){
				hidval.style.display = "inline";
			}else{
				hidval.style.display = "none";
			}
	    }
		</script>
	</head>
	
	<body onload="">
		<script language="javascript" src="js/function.js"></script>

		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵЭ�鷽�� - ��ҵЭ��</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tbody>
					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>��һ����</b>
						</td>
					</tr>
					<tr bgcolor="DOEOEE">
						<td align="left" colspan="4">
							ѧ�����
							<html:text property="xslb" name="rs" readonly="true"
								style="width:45px" />
							��ҵ���
							<html:text property="bynd" name="rs" readonly="true"
								style="width:35px" />
							<bean:message key="lable.xsgzyxpzxy" />
							<html:text property="xymc" name="rs" readonly="true"
								style="width:120px" />
							�ύʱ��
						        <html:text name="rs" property="tjsj" readonly="true" style="width:140px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th width="19%">
							ѧ��
						</th>
						<td width="31%">
							<html:text name="rs" property="xsxh" readonly="true" />
						</td>

						<th width="18%">
							����
						</th>
						<td width="32%">
							<bean:write name="rs" property="name" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							�Ա����
						</th>
						<td >
							<bean:write name="rs" property="xbdm" />
						</td>
						<th>
							���֤��
						</th>
						<td>
							<bean:write name="rs" property="id" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							ѧУ���룺
						</th>
						<td>
							<bean:write name="rs" property="xxdm" />
						</td>
						<th>
							ѧУ����
						</th>
						<td>
							<bean:write name="rs" property="xxmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							רҵ����
						</th>
						<td align="left">
							<bean:write name="rs" property="zydm" />
						</td>
						<th>
							רҵ����
						</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							ѧ�ƴ��룺
						</th>
						<td>
							<bean:write name="rs" property="xzdm" />
						</td>
						<th>
							ѧ������
						</th>
						<td align="left">
							<bean:write name="rs" property="xldm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							������ʽ����
						</th>
						<td align="left">
							<bean:write name="rs" property="pyfsdm" />
						</td>
						<th>
							��Դ��������
						</th>
						<td>
							<bean:write name="rs" property="sydqdm" />
						</td>
					</tr>

					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>�ڶ�����</b>
						</td>
					</tr>


					<tr style="height:22px">
						<th>
							��ҵȥ�����
						</th>
						<td>
							<bean:write name="rs" property="byqxdm" />
						</td>
						<th>
							��Դ��������
						</th>
						<td>
							<bean:write name="rs" property="sydq" />
						</td>

					</tr>
					<tr style="height:22px">
						<th>
							��Ϣ�ǼǺ�
						</th>
						<td>
							<bean:write name="rs" property="xxdjh" />
						</td>
						<th>
							��֯��������
						</th>
						<td>
							<bean:write name="rs" property="zzjgdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��λ����
						</th>
						<td>
							<bean:write name="rs" property="dwmc" />
						</td>
						<th>
							������ò����
						</th>
						<td>
							<bean:write name="rs" property="zzmmdm" />
						</td>

					</tr>
					<tr style="height:22px">
						<th>
							��λ��������
						</th>
						<td>
							<bean:write name="rs" property="dwdq" />
						</td>
						<th>
							���ܲ�������
						</th>
						<td>
							<bean:write name="rs" property="zgbm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��λ��������
						</th>
						<td>
							<bean:write name="rs" property="dwdqdm" />
						</td>
						<th>
							���ܲ��Ŵ���
						</th>
						<td>
							<bean:write name="rs" property="zgbmdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							δ��ҵ��־
						</th>
						<td align="left">
							<bean:write name="rs" property="wjybz" />
						</td>
						<th>
							�Զ���1
						</th>
						<td>
							<bean:write name="rs" property="bz1" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							δ��ҵԭ��
						</th>
						<td align="left">
							<bean:write name="rs" property="wjyyy" />
						</td>
						<th>
							�Զ���2
						</th>
						<td>
							<bean:write name="rs" property="bz2" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��ϵ��ַ
						</th>
						<td align="left">
							<bean:write name="rs" property="lxdz" />
						</td>
						<th>
							�Զ���3
						</th>
						<td>
							<bean:write name="rs" property="bz3" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							�ʱ�
						</th>
						<td>
							<bean:write name="rs" property="yb" />
						</td>
						<th>
							�Զ���4
						</th>
						<td align="left">
							<bean:write name="rs" property="bz4" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							�绰
						</th>
						<td align="left">
							<bean:write name="rs" property="dh" />
						</td>
						<td>
							�Զ���5
						</td>
						<td>
							<bean:write name="rs" property="bz5" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��ס֤�������־λ
						</th>
						<td>
							<bean:write name="rs" property="jzzhlbbzwdm" />
						</td>
						<th>
							
						</th>
						<td align="left">
							
						</td>
						
					</tr>
					<tr style="height:22px">
						<th>
							��Դ�����ܵ�λ
						</th>
						<td>
							<bean:write name="rs" property="sydzgbm" />
						</td>
						<th>
							��λ���ʴ���
						</th>
						<td>
							<bean:write name="rs" property="dwxzdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							������׼�ĺ�
						</th>
						<td>
							<bean:write name="rs" property="blueno" />
						</td>
						<th>
							��ע
						</th>
						<td>
							<bean:write name="rs" property="memo" />
						</td>
					</tr>

					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>��������</b>
						</td>
					</tr>


					<tr style="height:22px">
						<th>
							��λ���ʴ���2
						</th>
						<td>
							<bean:write name="rs" property="dwxzdm2" />
						</td>
						<th>
							�������յ�
						</th>
						<td>
							<bean:write name="rs" property="dajsd" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��λ��ַ
						</th>
						<td >
							<bean:write name="rs" property="dwdz" />
						</td>
						<th>
							�������յص�ַ
						</th>
						<td>
							<bean:write name="rs" property="dajsddz" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��λ�绰
						</th>
						<td align="left">
							<bean:write name="rs" property="dwdh" />
						</td>
						<th>
							�������յ��ʱ�
						</th>
						<td>
							<bean:write name="rs" property="dajsdyb" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��λ�ʱ�
						</th>
						<td>
							<bean:write name="rs" property="dwyb" />
						</td>
						<th>
							��������
						</th>
						<td>
							<bean:write name="rs" property="dqlx" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							ΥԼ��
						</th>
						<td >
							<bean:write name="rs" property="wyj" />
						</td>
						<th>
							��ҵ����
						</th>
						<td align="left">
							<bean:write name="rs" property="hyfl" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							��һ����ƽ������
						</th>
						<td>
							<bean:write name="rs" property="dynypjgz" />
						</td>
						<th>
							רҵ�Կ�
						</th>
						<td>
							<bean:write name="rs" property="zydk" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							������ʼʱ��
						</th>
						<td align="left">
								<html:text name="rs" readonly="true" property="bdkssj" maxlength="60" style="width: 210px;"></html:text>
						</td>
						<th>
							��������ʱ��
						</th>
						<td align="left">
							<html:text name="rs" readonly="true" property="bdjssj" maxlength="60" style="width: 210px;"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							����֤��
						</th>
						<td align="left">
							<html:text name="rs" readonly="true" property="bdzh" style="width: 210px;" maxlength="60"></html:text>
						</td>
						<th>
						</th>
						<td>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
						
						<logic:equal value="12061" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />��˽��
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							����Ա��˽��
						</logic:notEqual>
						</th>
						<td>
							<html:text name="rs" property="fdysh" style="width=100%"
								readonly="true" />
						</td>
						<th align="right">
							ѧУ���
						</th>
						<td >
							<html:select name="rs" property="xxsh" onchange="">
								<html:option value="δ���">δ���</html:option>
								<html:option value="δͨ��X">δͨ��X</html:option>
								<html:option value="��ͨ����">��ͨ����</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
								<logic:equal value="12061" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />�����
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							����Ա���
							</logic:notEqual>
						</th>
						<td>
							<html:text name="rs" property="fdyshren" style="width=100%"
								readonly="true" />
						</td>
						<th>
							ѧУ�����
						</th>
						<td align="left">
							<html:text name="rs" property="xxshren" style="width=100%"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
						<logic:equal value="12061" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />���ʱ��
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							����Ա���ʱ��
						</logic:notEqual>
						</th>
						<td align="left">
							<html:text name="rs" property="fdyshsj" style="width=100%"
								readonly="true" />
						</td>
						<th>
							ѧУ���ʱ��
						</th>
						<td align="left">
							<html:text name="rs" property="xxshsj" style="width=100%"
								readonly="true" />
						</td>
					</tr>
					<tr id="btgtr">
						<th>
							��ͨ��ԭ��
							<br>
							���޸����
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="btgyy" rows="4"
								style="width:99%" />
						</td>
					</tr>
					<!-- �ж��Ƿ��и����ϴ� -->
					<logic:present name="youFj">
					<th align="right">
						������ϻ򸽼�����
					</th>
					<td colspan="3">
						<a href="downloadfilewj.do?wjsclj=${rs.wjdz }" target="_self">����</a>
					</td>
					</logic:present>	
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button name="����" onclick="shgo()" id="buttonSave">
										�� ��
									</button>
									<button name="�ر�" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</div>
		</html:form>
		<logic:notEmpty name="shenhe">
			<logic:equal name="shenhe" value="ok">
				<script>
                    alert("�����ɹ���");
					if(opener){
				 		opener.document.getElementById('query_go').onclick();
				 	}
					Close();
               </script>
			</logic:equal>
			<logic:equal name="shenhe" value="no">
				<script>
                    alert("����ʧ�ܣ�");
                    if(opener){
				 		opener.document.getElementById('query_go').onclick();
				 	}
					Close();
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

