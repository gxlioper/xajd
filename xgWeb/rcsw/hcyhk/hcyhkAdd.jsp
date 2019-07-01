<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/hcyhk" method="post">
				<logic:present name="rs">
					<logic:equal name="rs" property="stuExists" value="no">
						<script>
					    	alert("您输入的学号无效!");
					    </script>
					</logic:equal>
				</logic:present>

				<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/hcyhk.do?method=hcyhkAdd" />

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
										<button type="button" class="button2" onclick="saveData('hcyhk.do?method=hcyhkAdd&doType=save','xh-sqly')">
											保 存
										</button>
										<button type="button" class="button2" onclick="window.close();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<thead>
							<tr>
								<th colspan="4">
									<span>补办申请信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="16%">
									<font color="red">*</font>学号
								</th>
								<td width="34%">
									<html:text property="save_xh" readonly="readonly"
										value="${rs.xh }" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this);" />
									<logic:notEqual value="student" name="userOnLine">
										<button type="button" onclick="showTopWin('stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:notEqual>
								</td>
								<th width="16%">
									姓名
								</th>
								<td width="34%">
									${rs.xm }
								</td>
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td>
									${rs.xb }
								</td>
								<th>
									年级
								</th>
								<td>
									${rs.nj }
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									${rs.xymc }
								</td>
								<th>
									专业
								</th>
								<td>
									${rs.zymc }
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td>
									${rs.bjmc }
								</td>
								<th>
									申请时间
								</th>
								<td>
									<html:text property="save_sqsj" readonly="true" value="${rs.sqsj }"></html:text>
								</td>
							</tr>
							<logic:equal name="xxdm" value="12703">
							<tr>
								<th>
									乘车区
								</th>
								<td colspan="3">
									<html:text property="save_qdz"  value="${rs.qdz }"/>
									-
									<html:text property="save_zdz"  value="${rs.zdz }"/>
								</td>
							</tr>
							</logic:equal>
							<tr>
								<th>
									<font color="red">*</font>申请理由
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:text value="${rs.sqly }" property="save_sqly" maxlength="50" style="width:90%" styleId="sqly"/>
								</td>
							</tr>
							<tr>
								<th>
									备注<br/>
									<font color="red">(限400字)</font>
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea property='save_bz' style="width:90%" 
												   rows='5' value="${rs.bz }" 
												   onblur="checkLen(this,400)"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}',function(t){
					if(t=="ok"){
						dialogArgumentsQueryChick();
			 			window.close();
					}
				});
				
			</script>
		</logic:present>
	</body>
</html>
