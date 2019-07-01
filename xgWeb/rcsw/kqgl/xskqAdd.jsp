<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function setXsStyle(obj){
				if ("����" == obj.value){
					$('xsTr').style.display="";
				} else {
					$('xsTr').style.display="none";
				}
			}
		</script>
	</head>
	<body onload="setXsStyle($('kqlx'))">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/kqglManage" method="post">
			<input type="hidden" id="userType" name="userType"value="${userType }" />
			<input type="hidden" id="userName" name="userName"value="${userName }" />
			<input type="hidden" id="url" name="url" value="/kqglManage.do?method=xskqAdd" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-nj-xymc-zymc-bjmc" />
			<input type="hidden" name="save_lrr" value="${userName }" />


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
									<button type="button" name="�ύ"
										onclick="saveUpdate('/xgxt/kqglManage.do?method=xskqAdd&doType=save','xh-kqlx-kqsj')">
										����
									</button>
									<button type="button" name="�ر�" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:text property="save_xh" maxlength="20"
									styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)"
									onblur="chkIsStu(this,'view_xsjbxx','xh')"
								></html:text>
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>�꼶</th>
							<td>${rs.nj }</td>
							<th><bean:message key="lable.xb" /></th>
							<td>${rs.xymc }</td>
						</tr>
						<tr>
							<th>רҵ</th>
							<td>${rs.zymc }</td>
							<th>�༶</th>
							<td>${rs.bjmc }</td>
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="save_xn" disabled="true" value="${xn}">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							
								<html:hidden property="save_xn" value="${xn}"/>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="save_xq" disabled="true" value="${xq}">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							
								<html:hidden property="save_xq" value="${xq}"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:select property="save_kqlx" styleId="kqlx" onchange="setXsStyle(this)">
									<html:options collection="kqlxdmList" property="dm" labelProperty="mc"/>
									<html:options collection="kqlxList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>����ʱ��
							</th>
							<td>
								<html:text property="save_kqsj" styleId="kqsj"
									readonly="true"
									onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm');"
								></html:text>
							</td>
						</tr>
						
						
						<tr style="display:none" id="xsTr">
							<th>����ѧʱ</th>
							<td>
								<html:text property="save_kkxs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</td>
							<th></th>
							<td></td>
						</tr>
						
						<tr>
							<th>
								��ע
								<br/>
								<font color="red">(��400��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_bz" style="width:98%" rows="5" onblur="checkLen(this,400)"></html:textarea>
							</td>
						</tr>
						
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script defer="defer">
				alert("${message}");
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
</html>
