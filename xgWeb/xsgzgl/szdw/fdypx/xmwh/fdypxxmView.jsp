<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>查看辅导员培训项目</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								项目名称
							</th>
							<td width="34%">
								${model.xmmc}
							</td>
							<th width="16%">
								培训地点
							</th>
							<td width="34%" >
								${model.pxdd}
							</td>
						</tr>
						
						<tr>
						<logic:equal value="1"  name="view_type">
							<th width="16%">
								申请开关
							</th>
							<td width="34%">
								 <logic:notEmpty property="sqkg" name="model">
									  <logic:equal value="1" property="sqkg" name="model">
											开
										</logic:equal>
										<logic:equal value="0" property="sqkg" name="model">
											关
										</logic:equal>
								 </logic:notEmpty>
									
								<logic:empty property="sqkg" name="model">
									未设置
								</logic:empty>
							</td>
							<th width="16%">
								申请时间
							</th>
							<td width="34%" >
								${model.sqkssj}至
								${model.sqjssj}
							</td>
						</logic:equal>
						</tr>
						
						<tr>
							<th width="16%">
								培训时间
							</th>
							<td width="34%">
								${model.pxsj}
							</td>
							<th width="16%">
								<font color="red">*</font>培训学时
							</th>
							<td width="34%">
								${model.pxxs}
							</td>

						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>组织部门
							</th>
							<td width="34%" colspan="3">
								<html:select property="bmdm" styleId="bmdm" name="model" disabled="true">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc"></html:options>
								</html:select>
							</td>
						</tr>
						
						<tr>
							<th width="16%">
								培训简介
							</th>
							<td width="34%" colspan="3">
								${model.pxjj}
							</td>
						</tr>
						
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
										关闭
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

