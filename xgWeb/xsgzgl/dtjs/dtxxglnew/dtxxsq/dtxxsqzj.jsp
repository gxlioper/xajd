<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/dtxxsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			<logic:equal value="13871" name="xxdm">
				showRdzys();
				</logic:equal>
			});
		</script>
	</head>
	<body>
	<html:form method="post" styleId="form" action="/dtxxsq">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;height:375px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/dtjs/comm/selectStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>党团信息申请</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>申请阶段名称
						</th>
						<td align="left" colspan="3">
								<html:select property="jddm" styleId="jddm" disabled="false"  onchange="showRdzys();">
									<html:options collection="sqJdlist" property="jddm"
										labelProperty="jdmc" />
								</html:select>		
						</td>
						<th hidden align="right" width="10%" name="rdzys">
							入党志愿书编号
						</th>
						<td hidden align="left"  name="rdzys">
							<html:text property="zd3" maxlength="30" styleId="zd3" style="hidden:true"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							申请时间(可不填，默认取当前时间)
						</th>
						<td width="30%" colspan="3">
							<html:text property="sqsj" onclick="return showCalendar('sqsj','yyyy-MM-dd HH:mm:ss');" styleId="sqsj" ></html:text>
						</td>
					</tr>

					<tr>
						<th align="right" width="10%">
							<logic:notEqual name="xxdm" value="70002">
								<span class="red">*</span>个人小结&nbsp;
								<br />
								<font color="red">(限2000字)</font>	
							</logic:notEqual>
							<logic:equal name="xxdm" value="70002">	
								<span class="red">*</span>表现及奖惩情况&nbsp;
							</logic:equal>			
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="grxj" styleId="grxj" style="width:97%" onblur="checkLen(this,2000);"></html:textarea>
						</td>
					</tr>
						<tr>
							<th align="right">
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="zd5" styleId="zd5" />
								<input type="file" id="filepath_f" name="zd5" />
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
											elementid : 'zd5',

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
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="save('dtxxsq.do?method=add&type=save','xh-jddm-grxj');return false;" id="buttonSave">
									保存草稿
								</button>
								<button type="button"  onclick="save('dtxxsq.do?method=add&type=submit','xh-jddm-grxj');return false;" id="buttonSave">
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
		</div>
		</html:form>
	</body>
</html>
