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
				if ("旷课" == obj.value){
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
				<em>您的当前位置:</em><a>${title }</a>
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
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="提交"
										onclick="saveUpdate('/xgxt/kqglManage.do?method=xskqAdd&doType=save','xh-kqlx-kqsj')">
										保存
									</button>
									<button type="button" name="关闭" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<html:text property="save_xh" maxlength="20"
									styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)"
									onblur="chkIsStu(this,'view_xsjbxx','xh')"
								></html:text>
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>年级</th>
							<td>${rs.nj }</td>
							<th><bean:message key="lable.xb" /></th>
							<td>${rs.xymc }</td>
						</tr>
						<tr>
							<th>专业</th>
							<td>${rs.zymc }</td>
							<th>班级</th>
							<td>${rs.bjmc }</td>
						</tr>
						<tr>
							<th>
								学年
							</th>
							<td>
								<html:select property="save_xn" disabled="true" value="${xn}">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							
								<html:hidden property="save_xn" value="${xn}"/>
							</td>
							<th>
								学期
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
								<font color="red">*</font>考勤类型
							</th>
							<td>
								<html:select property="save_kqlx" styleId="kqlx" onchange="setXsStyle(this)">
									<html:options collection="kqlxdmList" property="dm" labelProperty="mc"/>
									<html:options collection="kqlxList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>考勤时间
							</th>
							<td>
								<html:text property="save_kqsj" styleId="kqsj"
									readonly="true"
									onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm');"
								></html:text>
							</td>
						</tr>
						
						
						<tr style="display:none" id="xsTr">
							<th>旷课学时</th>
							<td>
								<html:text property="save_kkxs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</td>
							<th></th>
							<td></td>
						</tr>
						
						<tr>
							<th>
								备注
								<br/>
								<font color="red">(限400字)</font>
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
