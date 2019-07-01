<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyyjx/js/gyyjx.js"></script>
		<script type="text/javascript" defer="defer">
			jQuery(function(){

				
				
			});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_gyyjxstu" method="post" styleId="gyyjxForm">
			<logic:notEmpty name="actionType" >
				<logic:equal value="update" name="actionType">
					<html:hidden property="gyyjid"/>
				</logic:equal>
			</logic:notEmpty>
			
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<logic:notEmpty name="actionType" >
						<logic:equal value="update" name="actionType">
							<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
						</logic:equal>
						<logic:equal value="add" name="actionType">
							<%@ include file="/xsgzgl/gygl/comm/selectStudent.jsp" %>				
						</logic:equal>
					</logic:notEmpty>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>住宿床位信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>楼栋名称</th>
							<td>${cwxx.ldmc}</td>
							<th>寝室号</th>
							<td>${cwxx.qsh}</td>
						</tr>
						<tr>
							<th>床位号</th>
							<td>${cwxx.cwh}</td>
							<th>寝室电话</th>
							<td>${cwxx.qsdh}</td>
						</tr>
						<tr>
							<th>收费标准</th>
							<td>${cwxx.sfbz}</td>
							<th>所属年级</th>
							<td>${cwxx.qsnj}</td>
						</tr>
						<tr>
							<th>所属<bean:message key="lable.xb" /></th>
							<td>${cwxx.qsxymc}</td>
							<th>所属班级</th>
							<td>${cwxx.bjmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>意见信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>意见分类
							</th>
							<td colspan="3">
								<html:select property="yjfldm" styleId="yjfldm"  >
									<html:option value="" >--请选择意见分类--</html:option>
									<html:options collection="yjflList" property="yjfldm" labelProperty="yjflmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							
							<th>
								<span class="red">*</span>意见描述
								<br/>
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="yjms" styleId="yjms" style="width:95%;" rows="5" ></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									
									<logic:notEmpty name="actionType" >
										<logic:equal value="add" name="actionType">
											<button id="save_button" type="button"  onclick="submitAction_stu('add');">保存</button>
										</logic:equal>
										<logic:equal value="update" name="actionType">
											<button id="save_button" type="button"  onclick="submitAction_stu('update');">保存</button>
										</logic:equal>
									</logic:notEmpty>
									<button type="button" name="关 闭" onclick="iFClose();">
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

