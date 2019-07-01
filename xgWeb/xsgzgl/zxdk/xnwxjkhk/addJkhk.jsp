<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxjkhk/js/jkhk.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>	
	</head>
	<body>
		
		<body>
		
		<html:form action="/zxdk_jkhk" method="post" styleId="xnwxjkhkForm">
			
			
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/zxdk/xnwxjkhk/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${xn}
							</td>
							<th>学期</th>
							<td>
								${xq}
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>还款状态</th>
							<td>
								<html:select property="hkzt" styleId="hkzt" style="width:130px">
									<option value='已还款'>已还款</option>
									<option value='签订还款协议'>签订还款协议</option>
								</html:select>
							</td>
							<th><span class="red">*</span>还款时间</th>
							<td>
								<html:text property="hksj" styleId="hksj" onfocus="showCalendar('hksj','y-mm-dd');"></html:text>
							</td>
					    </tr>
					    <tr>
							<th>
								备注
								<br /><font color="red">&lt;限1000字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='8' onblur="checkLen(this,1000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="16%">
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
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveSqjg('save');">
										保存
									</button>
									<button type="button" type="button" onclick="iFClose();">
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

