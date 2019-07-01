<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/rcpy/rcpysq/js/rcpysq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	</head>
	<body>
		<html:form method="post" styleId="rcpysqForm" action="/rcpy_rcpysq"
			enctype="multipart/form-data">
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>培养信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>培养项目名称
							</th>
							<td align="left">
								<html:text styleId="xmmc" property="xmmc" maxlength="50" />
							</td>
							<th align="right" width="10%">
								<span class="red">*</span>培养类别
							</th>
							<td align="left">
								<html:select property="pylb" styleId="pylb" disabled="false">
									<html:options collection="pylbList" property="pylb"
										labelProperty="pylbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>考核指标
							</th>
							<td align="left">
								<html:select property="khzb" styleId="khzb" disabled="false">
									<html:options collection="khzbList" property="khzb"
										labelProperty="khzbmc" />
								</html:select>
							</td>
							<th align="right" width="10%">
								<span class="red">*</span>限制条件
							</th>
							<td align="left">
								<html:select property="xztj" styleId="xztj" disabled="false">
									<html:options collection="xztjList" property="xztj"
										labelProperty="xztjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								日常作业&nbsp;
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="rczy" styleId="rczy"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								小结总结&nbsp;
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="xjzj" styleId="xjzj"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
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
												elementid : 'filepath'
												});
										});
									</script>
						</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('rcpy_rcpysq.do?method=zjRcpysq&type=save');return false;"
										id="buttonSave">
										保存草稿
									</button>
									<button type="button"
										onclick="save('rcpy_rcpysq.do?method=zjRcpysq&type=submit');return false;"
										id="buttonSave">
										提交申请
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
