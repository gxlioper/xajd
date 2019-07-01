<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/yy/js/wsbzyy.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			fdmcOnchange();
			zcOnChange();
			dayOnChange();
		})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/wsbz_yy" method="post" styleId="WsbzYyForm" onsubmit="return false;">
		<input type="hidden" name="flag" id="flag" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>预约信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>卫生保障分队</th>
							<td>
								<html:select property="fddm" styleId="fddm">
									<html:option value=""></html:option>
									<html:options collection="fdmclist" property="fddm" labelProperty="fdmc"/>
								</html:select>
							</td>
							<th>监督时间</th>
							<td>
								<label id="sj"></label>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>预约时间</th>
							<td>
								<div style="display: none" id="input">
								<%-- 
										${yyrqday}
									<html:hidden  property="yyrqday" styleId="yyrqday" value="${yyrqday}"/>--%>
									<html:select property="yyrqday" styleId="yyrqday">
									 	<html:options collection="yyrqList" property="yyrq" labelProperty="yyrq"/>
									</html:select>
								</div> 
								<div style="display: none" id="select">
								<%-- 
								<html:text property="yyrqzc" styleId="yyrqzc" value="${zclist}" readonly="true"/>--%>
									<html:select property="yyrqzc" styleId="yyrqzc">
										<html:options collection="yyzcList" property="yyzc" labelProperty="yyzc"/>
									</html:select>
								</div> 
								
							    <html:hidden property="yyrq" styleId="yyrq" />
							</td>
							<th>监督地点</th>
							<td>
								<label id="dd"></label>
							</td>
						</tr>
						<tr>
							<th>人数</th>
							<td>
								<label id="rs"></label>
							</td>
							<th>可预约人数</th>
							<td>
								<label id="syrs"></label>
							</td>
						</tr>
						<tr >
							<th>申请时间</th>
							<td>
								${sqsj}
								<html:hidden property="sqsj" styleId="sqsj" value="${sqsj}"/>
							</td>
							<th></th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>工作职责</th>
							<td colspan="3">
								<label id="gzzz"></label>
							</td>
						</tr>
						<tr>
							<th>服务要求</th>
							<td colspan="3">
								<label id="fwyq"></label>
							</td>
						</tr>
						<tr>
							<th>预约理由</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveData('add');">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
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