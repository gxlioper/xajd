<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function jyglByqxSh() {
			 	document.forms[0].action = "/xgxt/jyglByqxSh.do?act=shenhe&doType=shenghe";
			 	document.forms[0].submit();
	    }
	    
	    function chLengs(obj,leng){
			if(obj.value.length > leng){
				alert("����Ŀ�������Ϊ"+leng+",���Ѿ���������ȷ�ϣ�", function(){obj.focus();});
				//alert("����������"+leng+"�֣�");
			}
		}
		</script>
	</head>
	<body>
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ѧ����Ϣ - ��ҵȥ�����</a>
			</p>
		</div>
		<html:form action="/data_search" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<td align="left" colspan="4">
								ѧ�����
								<html:text property="xslb" name="rs" style="width:45px"
									readonly="true" />
								��ҵ���
								<html:text property="bynd" name="rs" style="width:35px"
									readonly="true" />
								<bean:message key="lable.xsgzyxpzxy" />
								<html:text property="xymc" name="rs" style="width:130px"
									readonly="true" />
								�ύʱ��
								<html:text name="rs" property="tjsj" style="width:140px"
									readonly="true" />
							</td>
						</tr>
					</thead>
					<tbody>
						<tr style="height:22px">
							<th width="15%">
								ѧ��
							</th>
							<td align="left" width="35%">
								<html:text name="rs" property="xsxh" style="width=100%"
									readonly="true" />
							</td>
							<th width="15%">
								����
							</th>
							<td align="left" width="35%">
								<bean:write name="rs" property="name" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								�Ա�
							</th>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								���֤��
							</th>
							<td align="left">
								<bean:write name="rs" property="id" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								ѧУ����
							</th>
							<td align="left">
								${xxmc }
							</td>
							<th align="right">
								<bean:message key="lable.xb" />����
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>

						</tr>
						<tr style="height:22px">
							<th align="right">
								רҵ����
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
							<th align="right">
								�༶����
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								��Դ����
							</th>
							<td align="left">
								<bean:write name="rs" property="sydq" />
							</td>
							<th align="right">
								������ʽ
							</th>
							<td align="left">
								<bean:write name="rs" property="pyfs" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								��ҵȥ��
							</th>
							<td align="left">
								<bean:write name="rs" property="byqx" />
							</td>
							<th align="right">
								��ϵ��ַ
							</th>
							<td align="left">
								<bean:write name="rs" property="lxdz" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								��ϵ�绰
							</th>
							<td>
								<bean:write name="rs" property="lxdh" />
							</td>
							<th>
								��������
							</th>
							<td>
								<bean:write name="rs" property="yzbm" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								�ƶ��绰
							</th>
							<td>
								<bean:write name="rs" property="yddh" />
							</td>
							<th>
								��������
							</th>
							<td>
								<bean:write name="rs" property="email" />
							</td>
						</tr>
						<logic:equal value="12453" name="xxdm" scope="session">

							<logic:equal value="xx" name="ldgxusertype" scope="request">
								<tr style="height:22px;">
									<th>
										ѧУ���
									</th>
									<td>
										<html:select name="rs" property="xxsh" style="width:100xp">
											<html:option value="δ���">δ���</html:option>
											<html:option value="δͨ��X">δͨ��X</html:option>
											<html:option value="��ͨ����">��ͨ����</html:option>
										</html:select>
									</td>
									<th>
										���ʱ��
									</th>
									<td>
										<html:text name="rs" property="shsj" style="width=100%"
											readonly="true" />
									</td>
								</tr>
								<tr style="height:55px">
									<th>
										�޸����
									</th>
									<td colspan="3">
										<html:textarea name="rs" property="xgyj" rows="3"
											style="width=100%" />
									</td>
								</tr>
								<tr style="height:22px">
									<td>
										�����
									</td>
									<td>
										<html:text name="rs" property="shperson" style="width=100%"
											readonly="true" />
									</td>
									<th>

									</th>
									<td>

									</td>
								</tr>
								<tr style="height:22px">
									<th>
										����Ա���
									</th>
									<td align="left">
										<html:text name="rs" property="fdysh" style="width=100%"
											readonly="true" />
									</td>
									<th>
										���ʱ��
									</th>
									<td>
										<html:text name="rs" property="fdyshsj" style="width=100%"
											readonly="true" />
									</td>
								</tr>
								<tr style="height:22px">
									<th>
										�����
									</th>
									<td align="left">
										<html:text name="rs" property="fdyshr" style="width=100%"
											readonly="true" />
									</td>
									<th>

									</th>
									<td align="center">

									</td>
								</tr>
							</logic:equal>
							<logic:equal value="xy" name="ldgxusertype" scope="request">
								<tr style="height:22px;">
									<th>
										ѧУ���
									</th>
									<td>
										<html:text name="rs" property="xxsh" style="width=100%"
											readonly="true" />
									</td>
									<th>
										���ʱ��
									</th>
									<td>
										<html:text name="rs" property="shsj" style="width=100%"
											readonly="true" />
									</td>
								</tr>
								<tr style="height:55px">
									<th>
										�޸����
									</th>
									<td colspan="3">
										<html:textarea name="rs" property="xgyj" rows="3"
											style="width=100%" readonly="true" />
									</td>
								</tr>
								<tr style="height:22px">
									<th>
										�����
									</th>
									<td>
										<html:text name="rs" property="shperson" style="width=100%"
											readonly="true" />
									</td>
									<th>

									</th>
									<td>

									</td>
								</tr>
								<tr style="height:22px">
									<th>
										����Ա���
									</th>
									<td>
										<html:select name="rs" property="fdysh" style="width:100xp">
											<html:option value="δ���">δ���</html:option>
											<html:option value="δͨ��X">δͨ��X</html:option>
											<html:option value="��ͨ����">��ͨ����</html:option>
										</html:select>
									</td>
									<th">
										���ʱ��
									</th>
									<td>
										<html:text name="rs" property="fdyshsj" style="width=100%"
											readonly="true" />
									</td>
								</tr>
								<tr style="height:22px">
									<th>
										�����
									</th>
									<td align="left">
										<html:text name="rs" property="fdyshr" style="width=100%"
											readonly="true" />
									</td>
									<th>

									</th>
									<td align="center">

									</td>
								</tr>
							</logic:equal>
						</logic:equal>
						<logic:notEqual value="12453" name="xxdm" scope="session">
							<logic:equal value="xx" name="ldgxusertype" scope="request">
								<tr style="height:22px;">
									<th>
										ѧУ���
									</th>
									<td>
										<html:select name="rs" property="xxsh" style="width:100xp">
											<html:option value="δ���">δ���</html:option>
											<html:option value="δͨ��X">δͨ��X</html:option>
											<html:option value="��ͨ����">��ͨ����</html:option>
										</html:select>
									</td>
									<th>
										��ҵ��λ
									</th>
									<td>
										<bean:write name="rs" property="jydw"/>
									</td>
								</tr>
								<tr style="height:55px">
									<th>
										�޸����
									</th>
									<td colspan="3">
										<html:textarea name="rs" property="xgyj" rows="3"
											style="word-break:break-all;width:95%"
											onblur="chLengs(this,50);" />
									</td>
								</tr>
								<tr style="height:22px;display: none">
									<th>
										�����
									</th>
									<td>
										<html:text name="rs" property="shperson" style="width=100%"
											readonly="true" />
									</td>
									<th>
										
									</th>
									<td align="center">
										
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="xy" name="ldgxusertype" scope="request">
								<tr style="height:22px">
									<th>
										ѧУ���
									</th>
									<td>
										<html:text name="rs" property="xxsh" style="width=100%"
											readonly="true" />
									</td>
									<th>
										���ʱ��
									</th>
									<td>
										<html:text name="rs" property="shsj" style="width=100%"
											readonly="true" />
									</td>
								</tr>
								<tr style="height:55px">
									<th>
										�޸����
									</th>
									<td colspan="3">
										<html:textarea name="rs" property="xgyj" rows="3"
											style="width=100%" />
									</td>
								</tr>
								<tr style="height:22px">
									<th>
										�����
									</th>
									<td>
										<html:text name="rs" property="shperson" style="width=100%"
											readonly="true" />
									</td>
									<th>

									</th>
									<td align="center">

									</td>
								</tr>
							</logic:equal>

						</logic:notEqual>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button name="�ر�" onclick="jyglByqxSh();return false;">
										�� ��
									</button>
									<button onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
		<logic:notEmpty name="shenhe">
			<logic:equal name="shenhe" value="ok">
				<script>
                     alert("�����ɹ���");
                     if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
               </script>
			</logic:equal>
			<logic:equal name="shenhe" value="no">
				<script>
                    alert("����ʧ�ܣ�");
                    if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

