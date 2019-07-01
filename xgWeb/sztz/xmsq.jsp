<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script type="text/javascript">
			jQuery(function(){
				jQuery('#xmid').combogrid({
					panelWidth:255,
					value:'${rs.id}',
					idField:'id',
					textField:'xmmc',
					url:'sztzAjax.do?method=initXmList',
					columns:[[
						{field:'xmmc',title:'��Ŀ����',width:85},
						{field:'kmmc',title:'������Ŀ',width:85},
						{field:'hxnlmc',title:'��������',width:85}
					]],
					onChange:function(n,o){
						var xh = jQuery('#xh').val();
						var xmid = jQuery(this).combogrid('getValue');
						
						refreshForm('sztz.do?method=xmsq&doType=${sztzActionForm.doType}&xh='+xh+'&xmid='+xmid);
					}
				})
			});
		</script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<input type="hidden" id="url" name="url" value="/sztz.do?method=xmsq&doType=${sztzActionForm.doType }" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-nj-xymc-zymc-bjmc" />	
			<html:hidden property="shlcid" value="${rs.shlcid }"/>	
			<input type="hidden" name="temp_xmid" value="${rs.id }" id="temp_xmid"/>
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button onclick="saveUpdate('sztz.do?method=xmsqSave','xh-temp_xmid')">
										�� ��
									</button>
									<logic:notEqual value="add" name="sztzActionForm" property="doType">
										<button onclick="saveUpdate('sztz.do?method=xmsqSave&sftj=��','xh-temp_xmid')">
											�� �� 
										</button>
										<button type="reset">
											�� ��
										</button>
										<html:hidden property="shzt" value="δ���"/>
									</logic:notEqual>
									<logic:equal value="add" name="sztzActionForm" property="doType">
										<html:hidden property="shzt" value="ͨ��"/>
										<button onclick="window.close();return false;">
											�� ��
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									������Ϣ
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<logic:equal value="stu" name="userType">
									<html:text property="xh" styleId="xh" value="${stu.xh}" readonly="true"/>
								</logic:equal>
								<logic:notEqual value="stu" name="userType">
									<html:text property="xh" styleId="xh" value="${stu.xh}" 
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										onblur="chkIsStu(this,'view_xsjbxx','xh')"/>	
									<logic:notEqual value="modi" name="oper">
										<button onclick="showTopWin('stu_info.do',800,600);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:notEqual>						
								</logic:notEqual>		
			            	</td>
			            	<th width="16%">
			            		<font color="red">*</font>��Ŀ����
							</th>
							<td width="34%">
								<html:select property="xmid" styleId="xmid">
									
								</html:select>
			            	</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								${stu.xm }
							</td>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xn }
							</td>
						</tr>
						
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${stu.xb }
							</td>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xqmc }
							</td>
						</tr>
						
						<tr>
							<th>
								�꼶
							</th>
							<td>
								${stu.nj }
							</td>
							<th>
								������Ŀ
							</th>
							<td>
								${rs.kmmc }
							</td>
						</tr>
						
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								${stu.xymc }
							</td>
							<th>
								��������
							</th>
							<td>
								${rs.hxnlmc }
							</td>
						</tr>
						
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${stu.zymc }
							</td>
							<th>
								���췽
							</th>
							<td>
								${rs.zbf }
							</td>
						</tr>
						
						<tr>
							<th>
								�༶
							</th>
							<td>
								${stu.bjmc }
							</td>
							<th>
								������
							</th>
							<td>
								${rs.fzr }
							</td>
						</tr>
						<tr>
							<th>
								�ٰ�ʱ��
							</th>
							<td>
								${rs.jbkssj } �� ${rs.jbjssj }
							</td>
							<th>
								������
							</th>
							<td>
								${rs.jcf }
							</td>
						</tr>
						<tr>
							<th>
								�����ɫ
							</th>
							<td>
								<html:radio value="����" property="cyjs">����</html:radio>
								<html:radio value="��֯" property="cyjs">��֯</html:radio>
							</td>
							<th>
								�Ƿ�����
							</th>
							<td>
								<html:radio value="��" property="sfcx">��</html:radio>
								<html:radio value="��" property="sfcx">��</html:radio>
							</td>
						</tr>
						
						<tr>
							<th>
								�ɹ�����<br/>
								<font color="red">&lt;��400��&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="cgms" style="width:85%" rows="5" onblur="checkLen(this,400)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ע<br/>
								<font color="red">&lt;��400��&gt;</font>
							</th>
							<td colspan="3"  style="word-break:break-all;">
								<html:textarea property="bz" style="width:85%" rows="5" onblur="checkLen(this,400)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<logic:present name="message">
				<script defer>
					alert('${message}');
					if (window.dialogArguments) {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>
