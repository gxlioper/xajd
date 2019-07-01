<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	<script>
	function saveorUpdate(type){
		var bynd = jQuery('#save_bynd').val();
		if(bynd==null||bynd==""){
			alert("������д��ҵ��ȣ�");
			return false;
			}
		
		if(type=="update"){
			saveUpdate('jyglTables.do?method=njjsJytjbUpdate&doType=update','')
		}else{
			saveUpdate('jyglTables.do?method=njjsJytjbUpdate&doType=save','')
		}
		}

	</script>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jyglTables" method="post">
			<input type="hidden" name="pkValue" value="${stu.xh }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notEmpty name="rs">
										<button id="buttonSave" onclick="saveorUpdate('update')">
											����
										</button>
									</logic:notEmpty>
									<logic:empty name="rs">
										<button id="buttonSave" onclick="saveorUpdate('save')">
											����
										</button>
									</logic:empty>
									<button id="buttonSave" onclick="window.open('jyglTables.do?method=printNjjsJytjb&xh=${stu.xh }')">
										��ӡ
									</button>
									<button id="buttonSave" onclick="refreshForm('jyglTables.do?method=showStudents&doType=query')">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<html:hidden property="save_xh" value="${stu.xh }"/>
								${stu.xh }
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${stu.xm }
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
								����
							</th>
							<td>
								${stu.mzmc }
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								${stu.csrq }
							</td>
							<th>
								������ò
							</th>
							<td>
								${stu.zzmmmc }
							</td>
						</tr>
						<tr>
							<th>
								��ͥ��ַ
							</th>
							<td colspan="3">
								${jtcy.jtszd}
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								${jtcy.jtyb }
							</td>
							<th>
								����״��
							</th>
							<td>
								${stu.jkzk }
							</td>
						</tr>
						<tr>
							<th>
								�س�
							</th>
							<td colspan="3">
								${stu.tc }
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td>
								${stu.lxdh }
							</td>
							<th>
								��ѧУ�κ�ְ��
							</th>
							<td>
								${zw }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��ҵ���
							</th>
							<td colspan="3">
								<html:select property="save_bynd" styleId="save_bynd" value="${rs.bynd }">
    							<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�������<br/>
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_jcqk" style="width:95%" rows="3" onblur="checkLen(this,200)" value="${rs.jcqk }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ҵ����<br/>
								<font color="red">(��400��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_byjd" style="width:95%" rows="6" onblur="checkLen(this,200)" value="${rs.byjd }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								ְҵ�����ȼ�
							</th>
							<td>
								<html:text property="save_zyjsdj" maxlength="20" value="${rs.zyjsdj }"></html:text>
							</td>
							<th>
								ְҵ�������۳ɼ�
							</th>
							<td>
								<html:text property="save_zjjdllcj" maxlength="20" value="${rs.zjjdllcj }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								ְҵ�������ܳɼ�
							</th>
							<td>
								<html:text property="save_zjjdjncj" maxlength="20" value="${rs.zjjdjncj }"></html:text>
							</td>
							<th>
								�����ȵ�
							</th>
							<td>
								<html:text property="save_cxdd" maxlength="20" value="${rs.cxdd }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />�Ƽ����<br/>
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_xytjyj" style="width:95%" rows="3" onblur="checkLen(this,200)" value="${rs.xytjyj }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								���յ�λ���<br/>
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_jsdwyj" style="width:95%" rows="3" onblur="checkLen(this,200)" value="${rs.jsdwyj }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ע<br/>
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_bz" style="width:95%" rows="3" onblur="checkLen(this,200)" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script defer="defer">
				alert('${message}');
			</script>
		</logic:present>
	</body>
</html>
