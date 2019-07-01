<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/xmsq/js/xmsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/xpjpy_xmsq" method="post" styleId="xmsqForm">
			<input type="hidden" id="xmdm" name="xmdm" value="${xmwhModel.xmdm}"/>
			<html:hidden styleId="xh" property="xh" />
			<html:hidden property="id" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<!-- 这个页面是有病的，等这段时间忙完回过头来要优化这个东西2018-07-10，Meng.Wei -->
					<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请奖项</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								评奖周期
							</th>
							<td colspan="3">
								<bean:write property="xn" name="xmsqForm"/>
							</td>
						</tr>
						<tr>
							<th>
								项目名称
							</th>
							<td>
								${xmwhModel.xmmc}
							</td>
							<th>
								项目金额
							</th>
							<td>
								${xmwhModel.xmje}
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>外语水平
							</th>
							<td>
								 <html:text property="wysp"  styleId="wysp" maxlength="16" style="width:150px"></html:text>
							</td>
							<th>
								宿舍电话
							</th>
							<td>
								 <html:text property="ssdh"  styleId="ssdh" maxlength="12" style="width:150px" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								担任社会工作职务
							</th>
							<td>
								 <html:text property="gzzw"  styleId="gzzw" maxlength="32" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								个人学习经历<br/>
								<font color="red">&lt;限200字&gt;</font>&nbsp;&nbsp;
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="grxxjl" styleId="grxxjl" style="width:100%;" rows="3" onblur="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								参加科研情况<br/>
								<font color="red">&lt;限200字&gt;</font>&nbsp;&nbsp;
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="cjkyqk" styleId="cjkyqk" style="width:100%;" rows="3" onblur="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								对设奖单位的认识<br/>
								<font color="red">&lt;限200字&gt;</font>&nbsp;&nbsp;&nbsp;
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="dwrs" styleId="dwrs" style="width:100%;" rows="3" onblur="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
						<tr id="fjxxid">
							<th>
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="fjxx" styleId="fjxx" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'fjxx'
										});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>申请理由
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveJxsqXg('save');return false;">
										保存草稿
									</button>
									<button type="button" onclick="saveJxsqXg('submit');return false;">
										提交申请
									</button>
									<button type="button"  onclick="iFClose();" id="buttonClose">
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