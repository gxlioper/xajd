<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xmsbjg/js/xmsbjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/jskpXmjg" method="post" styleId="jskpXmsbjgForm">
		<html:hidden property="xmid" styleId="xmid" value="53A33EB5335F3FB2E0538713470A2ABC"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>申报项目</th>
							<td>
								<input type="text" name="xmmc" id="xmmc" style="width:120px;"/>
								<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个项目',800,500,'jskpXmjg.do?method=showXmList');">选择</button>
							</td>
							<th >
							<font color="red">*</font>评分
							</th>
							<td colspan="3">
								<html:text style="width:100px" property="fs" styleId="fs"  value="${rs.fs}" maxlength="10" onkeyup="checkInputNum(this)"
			                          ></html:text>
			                          		评分区间${rs.fs}~${rs.fs}
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>获奖时间</th>
							<td colspan="3">
								<html:text  property="hjsj" styleId="hjsj" onfocus="showCalendar('hjsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>申报理由</th>
							<td colspan="3">
								<html:textarea property="sbly" styleId="sbly" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
							<html:hidden property="fjid" styleId="fjid" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'fjid'
												});
										});
									</script>
						</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:35px"></div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveXmsbjg('save');">
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