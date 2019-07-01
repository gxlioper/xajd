<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl//szdw/fdyjtff/js/fdyjtff.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			changbzlx();
		});
		</script>
	</head>
		
	<body>
		<html:form action="/szdw_fdyjtff.do" styleId="fdyjtffForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="id" styleId="id"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>辅导员信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">职工号</th>
							<td style="width:35%">
								<html:hidden property="zgh" styleId="zgh"/>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="zgh"/>
								</logic:present>
							</td>
							<th style="width:15%">姓名</th>
							<td style="width:35%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<th>性别</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xbmc"/>
								</logic:present>
							</td>
							<th>联系电话</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="lxdh"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>部门</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bmmc"/>
								</logic:present>
							</td>
							<th></th>
							<td>
								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>津贴发放信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>补助类型
							</th>
							<td width="34%">
								<html:select property="bzlx" styleId="bzlx" onchange="changbzlx()" style="width:70%">
									<html:option value=""></html:option>
									<html:option value="0">岗位补助</html:option>
									<html:option value="1">绩效考核补助</html:option>
								</html:select>
							</td>
							<th width="16%">
								考评等级
							</th>
							
							<td width="34%">
								<html:select property="kpdj" styleId="kpdj" disabled="true" style="width:70%">
									<html:option value=""></html:option>
									<html:option value="0">优秀</html:option>
									<html:option value="1">良好</html:option>
									<html:option value="2">合格</html:option>
									
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								学年
							</th>
							<td width="34%">
								<html:select property="xn"  styleId="xn" disabled="true" style="width:70%">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th width="16%">
								学期
							</th>
							
							<td width="34%">
								<html:select property="xq" styleId="xq" disabled="true" style="width:70%">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>补助金额
							</th>
							<td width="34%">
								<html:text onkeyup="checkInputNum(this)" maxlength="10" property="bzje" styleId="bzje" style="width:70%"></html:text>
							</td>
							<th width="16%">
								
							</th>
							
							<td width="34%">
								
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveUpdate();">
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
		</html:form>
	</body>
</html>