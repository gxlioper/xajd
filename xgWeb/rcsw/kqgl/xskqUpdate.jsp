<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
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
			<input type="hidden" name="save_lrr" value="${userName }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />


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
									<logic:notEqual name="doType" value="view">
										<button type="button" name="�ύ"
											onclick="saveUpdate('/xgxt/kqglManage.do?method=xskqUpdate&doType=save','xh-kqlx-kqsj')">
											����
										</button>
									</logic:notEqual>	
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
								ѧ��
							</th>
							<td width="34%">
								<html:text property="save_xh" maxlength="20" value="${rs.xh }"
									readonly="true"
								></html:text>
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
								<html:select property="save_xn" disabled="true" value="${rs.xn}">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							
								<html:hidden property="save_xn" value="${rs.xn}"/>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="save_xq" disabled="true" value="${rs.xq}">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							
								<html:hidden property="save_xq" value="${rs.xq}"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:select property="save_kqlx" styleId="kqlx" value="${rs.kqlx }">
									<html:options collection="kqlxdmList" property="dm" labelProperty="mc"/>
									<html:options collection="kqlxList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>����ʱ��
							</th>
							<td>
								<html:text property="save_kqsj" styleId="kqsj" value="${rs.kqsj }"
									readonly="true"
									onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm');"
								></html:text>
							</td>
						</tr>
						<tr style="display:none" id="xsTr">
							<th>����ѧʱ</th>
							<td>
								<html:text property="save_kkxs" maxlength="3" 
										   onkeyup="value=value.replace(/[^\d]/g,'')" value="${rs.kkxs }"/>
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
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_bz" style="width:98%" rows="5" onblur="checkLen(this,400)" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>����Ա</th>
							<td>
								${rs.fdy }
							</td>
							<th>������</th>
							<td>${rs.bzr }</td>
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
