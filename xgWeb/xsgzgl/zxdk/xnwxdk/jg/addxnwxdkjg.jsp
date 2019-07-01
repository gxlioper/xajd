<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/jg/js/xnwxdkjg.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/js/util.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xnwxdk_jg" method="post" styleId="XnwxdkJgModel">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:520px;margin-bottom:0px;" >
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
								<span>申请人经济情况（本学年）</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${xn}
								<html:hidden property="xn" styleId="xn"/>
								
							</td>
							<th>学期</th>
							<td>
							    ${xqmc}
								<html:hidden property="xq" styleId="xq"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>家庭提供（元）</th>
							<td>
								<html:text property="jttg" styleId="jttg" onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							</td>
							<th><font color="red">*</font>助学金（元）</th>
							<td>
								<html:text property="zxj" styleId="zxj" onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>奖学金（元）</th>
							<td>
							    <html:text property="jxj" styleId="jxj" onkeyup="checkInput(this)" onblur="ismoney(this)"    maxlength="10"></html:text>
							</td>
							<th><font color="red">*</font>勤工助学收入（元）</th>
							<td>
								<html:text property="qgzxsr" styleId="qgzxsr" onkeyup="checkInput(this)" onblur="ismoney(this)"   maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>校内无息借款 （元） </th>
							<td>
							    <html:text property="xnwxjk" styleId="xnwxjk" onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							</td>
							<th><font color="red">*</font>其他收入（元） </th>
							<td>
								<html:text property="qtsr" styleId="qtsr" onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>助学贷款金额（元）</th>
							<td>
							    <html:text property="zxdksqje" styleId="zxdksqje" onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							</td>
							<th><font color="red">*</font>助学贷款时间 </th>
							<td>
								<html:text property="zxdksqsj" styleId="zxdksqsj" onclick="return showCalendar('zxdksqsj','y-mm-dd');"  maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>发放金额（元）</th>
							<td>
							    <html:text property="ffje" styleId="ffje" onkeyup="checkInput(this)" onblur="ismoney(this)" maxlength="10"></html:text>
							</td>
							<th><font color="red">*</font>发放时间 </th>
							<td>
								<html:text property="ffsj" styleId="ffsj" onclick="return showCalendar('ffsj','y-mm-dd');"  maxlength="10" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>本次申请金额（元）</th>
							<td>
							    <html:text property="sqje" styleId="sqje"  onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							    <html:hidden property="jesx" value="${jesx}" styleId="jesx"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>申请理由
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
											   onkeyup="checkzs();"
											   style="width:99%;" rows="5" ></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath',
			
											eid : 'filepath_f'
										});
									});
								</script>
							</td>
						</tr>					
					</tbody>
				</table>
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveDkjg('save');">
										保存
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