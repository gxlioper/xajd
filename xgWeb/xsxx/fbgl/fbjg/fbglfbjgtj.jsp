<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsxx/fbgl/fbjg/js/fbjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function($){
				$("#error").hide();
				$("#nj").bind("change",function(){
					jQuery("#error").hide();
					//自动设置具体学生信息
					autoSetXsxx();
				});
			});
		</script>
	</head>
	<body>
		<div>
		<html:form action="/fbglbbgl.do?method=add&type=query">
			<input type="hidden" id="pk" value="${pk}" />
			<table width="100%" border="0" class="formlist">
				<tr>
					<td>
					<div style="" id="div_help" class="prompt">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							如果班级、学生存在冲突则不会提交到正式库。
						</p>
					</div>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" class="formlist">
				<tr>
					<th width="40%"><font color='red'>*</font>选择提交年级</th>
					<td>
						<html:select property="nj" styleId="nj"
							style="width:125px;">
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th width="40%">已提交</th>
					<td id="ytj">
						${ytj}
					</td>
				</tr>
				<tr>
					<th width="40%">未提交</th>
					<td id="wtj">
						${wtj}
					</td>
				</tr>
				<tr id="error">
					<th style="color: red;">存在冲突的学号或班级：</th>
					<td><a class="name" id="download" href="fbglfbjg.do?method=downloadError">点击此处下载详细信息</a></td>
				</tr>
			</table>
		</html:form>
		</div>
		<%@include file="/xsgzgl/comm/browser/progressBar.jsp"%>
		<div style="height:35px;"></div>
		<div>
			<table width="100%" border="0" class="formlist" id="below" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="2">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="tjzsk();return false;" id="buttonSave">
									确  定
								</button>							
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关  闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
