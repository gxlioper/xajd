<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/zhfsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
						
		</script>
	</head>
	<body>
		<html:form action="/zhf_sq" method="post" styleId="form">
			<html:hidden property="id"/>
			<html:hidden property="xh"/>		
			<div style='overflow-x:hidden;overflow-y:auto;height:410px;margin-bottom: 0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>计分项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>所属模块
							</th>
							<td>
								<html:select property="xmmkdm" styleId="xmmkdm" onchange="getJfxmListForUpdate(this);">
									<html:options collection="xmmkList" property="xmmkdm" labelProperty="xmmkmc"/>
								</html:select>
							</td>
							<th>
								<span class="red">*</span>计分项目
							</th>
							<td>
							<html:select property="jfxmdm" styleId="jfxmdm" onchange="getFsForUpdate(this);getKhyd(this);">
								<html:options collection="jfxmList" property="jfxmdm" labelProperty="jfxmmc"/>
							</html:select>		
							</td>
						</tr>
						<tr>
							<th>
								考核要点
							</th>
							<td id="khyd">
								${model.khyd}
							</td>
							<th>
								分数
							</th>
							<td id="fs">
								${model.fs}
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>参与或参加时间
							</th>
							<td>
								<html:text property="cysj" styleId="cysj" onfocus="showCalendar('cysj','y-mm-dd');"></html:text>
							</td>
							<th>
								<span class="red">*</span>事项说明
							</th>
							<td>
								<html:text property="sxsm" styleId="sxsm" maxlength="20" style="width:200px"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								佐证材料
							</th>
							<td colspan="3" id="fileTd" rowspan="5">
					    		<logic:notEmpty name="model" property="fj">
					    			<a href="javascript:delFile('${model.id}');" class="name">删除</a>
					    			<a href="javascript:void(0);" onclick="window.open('zhf_sq.do?method=downloadFile&id=${model.id }');return false;" class="name">预览</a>&nbsp;${model.fjmc }
					    		</logic:notEmpty>
					    		<logic:empty name="model" property="fj">
					    			<input type='file' name='lbfj' onchange="checkFileType(this)"/>
					    		</logic:empty>
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
								<div class="bz"><span class="red">*</span>为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveFormForUpdate();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
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

