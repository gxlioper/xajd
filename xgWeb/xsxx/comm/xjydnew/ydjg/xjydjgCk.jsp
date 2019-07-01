<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsxx/comm/xjydnew/js/xjydjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">

			jQuery(function() {
				initShow();
			});
			</script>
	</head>
	<body>
		<html:form method="post" styleId="xjydjgForm" action="/xjydjg">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
			<html:hidden property="xjydjgid"/>
			<html:hidden property="xh"/>
			<html:hidden property="ydlbdm" styleId="ydlbdm"/>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>学籍异动申请信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							学籍异动类别
						</th>
						<td align="left">
							<span style="color:red;">${xjydjgForm.ydlbmc }</span>
						</td>
						<th align="right">
							学年/学期
						</th>
						<td align="left">
							
							${xjydjgForm.xn } ${xjydjgForm.xqmc } 
						</td>
					</tr>
					<tr>
						<th>学籍状态[异动]</th>
						<td colspan="3">
							<div >
								<table border="0" style="float:left">
									<tr>
										<th style="width:70px">原学籍类别</th>
										<td style="width:180px">${xjydjgForm.ydqxjlbmc }</td>
									</tr>
									<tr>
										<th>是否有学籍</th>
										<td>&nbsp;${xjydjgForm.ydqsfyxjmc }</td>
									</tr>
									<tr>
										<th>是否在校</th>
										<td>&nbsp;${xjydjgForm.ydqsfzxmc }</td>
									</tr>
								</table>
								<img style="float:left;margin:30px 10px" src='images/ssyd/to.gif' ></img>
								<table border="0"  style="float:left">
									<tr>
										<th style="width:90px">异动后学籍类别</th>
										<td style="width:180px">${xjydjgForm.ydlbmc }</td>
									</tr>
									<tr>
										<th>是否有学籍</th>
										<td>${xjydjgForm.ydhsfyxjmc }</td>
									</tr>
									<tr>
										<th>是否在校</th>
										<td>${xjydjgForm.ydhsfzxmc }</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr id="tzbj">
						<th>调整班级</th>
						<td colspan="3">
							<div >
								<table border="0"  style="float:left">
									<tr>
										<th style="width:70px;height:20px;">原年级</th>
										<td style="width:180px" id="ydqnj">&nbsp;${xjydjgForm.ydqnj }</td>
									</tr>
									<tr>
										<th style="height:20px;">原<bean:message key="lable.xb" /></th>
										<td id="ydqxy">&nbsp;${xjydjgForm.ydqxymc }</td>
									</tr>
									<tr>
										<th style="height:20px;">原专业</th>
										<td id="ydqzy">&nbsp;${xjydjgForm.ydqzymc }</td>
									</tr>
									<tr>
										<th style="height:20px;">原班级</th>
										<td id="ydqbj">&nbsp;${xjydjgForm.ydqbjmc }</td>
									</tr>
								</table>
								<img style="float:left;margin:55px 10px" src='images/ssyd/to.gif' ></img>
								<table border="0" style="float:left">
									<tr>
										<th style="width:90px">异动后年级</th>
										<td style="width:180px">${xjydjgForm.ydhnj }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">异动后<bean:message key="lable.xb" /></th>
										<td>${xjydjgForm.ydhxymc }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">异动后专业</th>
										<td>${xjydjgForm.ydhzymc }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">异动后班级</th>
										<td>${xjydjgForm.ydhbjmc }
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					
					<logic:equal name="xxdm" value="10511">					
						<tr id="xzpd">
							<th align="right" width="10%">
								学制
							</th>
							<td align="left" colspan="3">
								${xjydjgForm.xz }
							</td>
						</tr>
						<tr id="xxpd">
							<th align="right">
								来源学校/去向学校
							</th>
							<td align="left" colspan="3">
								${xjydjgForm.xxmc }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								异动原因
							</th>
							<td align="left" colspan="3">
								${xjydjgForm.ydyymc }
							</td>
						</tr>
					</logic:equal>
									
					<tr>
						<th align="right" width="10%">
							申请理由&nbsp;					
						</th>
						<td colspan="3">
						${xjydjgForm.sqly }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							学籍异动文号&nbsp;
						</th>
						<td>
							<span style="color:blue;">${xjydjgForm.xjydwh }</span>
						</td>
						
						<logic:notEqual name="xxdm" value="10511">
							<th align="right" width="10%">
								学籍异动时间&nbsp;
							</th>
							<td>
								${xjydjgForm.xjydsj }
							</td>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10511">
							<th align="right" width="10%">
								学籍异动审核时间&nbsp;
							</th>
							<td>
								${xjydjgForm.xjydsj }
							</td>
						</logic:equal>
					</tr>
					<tr id="lrqzsj">
						<th>异动起止时间</th>
						<td colspan="3">
							${xjydjgForm.sqkssj } &nbsp;至&nbsp; ${xjydjgForm.sqjssj }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							备注&nbsp;
						</th>
						<td colspan="3">
							${xjydjgForm.xjydbz }
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>
