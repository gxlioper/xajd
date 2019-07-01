<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/general/cxpy/js/cxpy.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	</head>
	<body>
		<html:form action="/xsxx_gygl_cxcxpy.do?method=addCxpy" styleId="cxpyForm" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="xhs" id="xhs" value="${xhs}" />
			
			<div style="height:480px; width:100%; overflow-x:hidden;overflow-y:hidden;padding-right:0;">

				<%--<table width="100%" border="0" class="formlist">
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>学年
							</th>
							<td width="34%">
								<html:select property="xn" name="map" styleId="xn" onchange="removeXs()" style="width:100px">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>学期
							</th>
							
							<td width="34%">
								<html:select property="xq" name="map" styleId="xq" onchange="removeXs()" style="width:100px">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>操行等级
							</th>
							
							<td width="34%">
								<html:select property="cxdjdm" styleId="cxdjdm" style="width:100px">
									<html:options collection="cxdjList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>班主任
							</th>
							<td width="34%">
								<html:text property="bzr" styleId="bzr" maxlength="5" value="${name }"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>操行评语
								<br/><font color="red">(限制字数150)</font>
							</th>
							<td width="90%" colspan="3">
								<html:textarea onblur="chLengs(this,150);" rows="3" name="map" property="cxpy" style="width:99%" styleId="cxpy"></html:textarea>
							</td>
							
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>评定学生信息</span>
							</th>
						</tr>
					</thead>
				</table>--%>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>评定学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>学年
							</th>
							<td width="34%">
								<html:select property="xn" name="map" styleId="xn" onchange="removeXs()" style="width:100px">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<logic:notEqual name="xxdm" value="13943">
								<th width="16%">
									<font color="red">*</font>学期
								</th>
								
								<td width="34%">
									<html:select property="xq" name="map" styleId="xq" onchange="removeXs()" style="width:100px">
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
									</html:select>
								</td>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13943">
								<th><span class="red">*</span>评语日期</th>
								<td>
									<html:text property="sqsj" styleId="sqsj" onfocus="showCalendar('sqsj','yyyy-MM-dd HH:mm:ss');" value = "${nowTime}"></html:text>
								</td>
							</logic:equal>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>操行等级
							</th>
							
							<td width="34%">
								<html:select property="cxdjdm" styleId="cxdjdm" style="width:100px">
									<html:options collection="cxdjList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>班主任
							</th>
							<td width="34%">
								<html:text property="bzr" styleId="bzr" maxlength="5" value="${name }"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>操行评语
								<br/><font color="red">(限制字数150)</font>
							</th>
							<td width="90%" colspan="3">
								<html:textarea onblur="chLengs(this,150);" rows="3" name="map" property="cxpy" style="width:99%" styleId="cxpy"></html:textarea>
							</td>
							
						</tr>
						
					</tbody>
				</table>
				<div style="height:300px; width:98%; overflow-x:hidden;overflow-y:auto;padding-right:18px;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					
					<thead>
						<tr>
							<td colspan="7">
							<button type="button" id="btn_getXsxx" onclick="bcCxpy();" style="display: none;"></button>
							<button type="button" onclick="addTr();return false;" class="btn_01">增加学生</button>
							<button type="button" onclick="delTr();return false;" class="btn_01">删除学生</button>
							</td>
						</tr>
						<tr>
							<td width='10%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>学号</td>
							<td width='15%'>姓名</td>
							<td width='25%'><bean:message key="lable.xb" /></td>
							<td width='25%'>专业</td>
							<td width='10%'>班级</td>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						<logic:notEmpty name="xsList">
							<logic:iterate id="xs" name="xsList">
								<tr>
								<td width='10% '><input type="checkbox" name="xsxx"  /></td>
								<td>${xs.xh }</td>
								<td>${xs.xm }</td>
								<td>${xs.xymc }</td>
								<td>${xs.zymc }</td>
								<td>${xs.bjmc }</td>
								</tr>
							</logic:iterate>						
						</logic:notEmpty>
					</tbody>
				</table>
				</div>
			</div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="save();">
										保存
									</button>
									<button type="button" onclick="refreshParent2();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="xszgxxDiv" style="display: none;">
				<%@ include file="/xsgzgl/qgzx/gwgl/ryxxCk.jsp"%>
			</div>
		</html:form>
		<logic:notEmpty name="message">
		<script type="text/javascript">
			alertInfo("${message }",function(tag){
			if(tag=="ok"){
				refreshParent2();
			}
			});
		</script>
		</logic:notEmpty>
			
	</body>
</html>

