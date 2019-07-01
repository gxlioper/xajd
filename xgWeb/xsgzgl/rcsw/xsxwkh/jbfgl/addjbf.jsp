<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xsxwkh/jbfgl/js/jbfgl.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
</head>
	<body style="width: 100%">
	<html:form action="/xsxwkhJbfgl.do" styleId="jbfglForm" method="post">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>基本分信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						 <tr>
							<th width="16%" >学年 </th>
							
							<td width="34%" colspan="3" >
							  ${xn}
							</td>
						</tr>
						 <tr>
							<th><span class="red">*</span>班主任辅导员测评等级 </th>
							<td>
								<html:select  property="bzrcpdj" styleId="bzrcpdj" onchange ="change1()" style="width:130px">
								<option value=''>请选择</option>
								<option value='优秀'>优秀(45)</option>
								<option value='良好'>良好(43)</option>
								<option value='合格'>合格(40)</option>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>班级学生测评等级
							</th>
								<td>
								<html:select  property="xscpdj" styleId="xscpdj" onchange ="change2()" style="width:130px">
								<option value=''>请选择</option>
								<option value='优秀'>优秀(45)</option>
								<option value='良好'>良好(43)</option>
								<option value='合格'>合格(40)</option>
								</html:select>
							</td>
						</tr>
						<tr style="display: none">
							<th><span class="red">*</span>班主任辅导员测评分值 </th>
							<td>
						<html:text property="bzrcpfz"  styleId="bzrcpfz" style="width:200px;" />
							</td>
							<th><span class="red">*</span>班级学生测评分值 </th>
							<td>							
							<html:text property="xscpfz" styleId="xscpfz" style="width:200px;" />						
							</td>
						</tr>
						<tr>
							<th width="16%" rowspan="4">
								备注
								<br><font color="red">限制字数（500）</font><br/>
							</th>
							<td width="34%" colspan="3" rowspan="4">
								<html:textarea property="bz" onblur="chLengs(this,500);" styleId="bz" rows="4" style="width:90%" ></html:textarea>
							</td>
						</tr>
				</tbody>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="save();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</table>	
		</html:form>
	</body>
</html>