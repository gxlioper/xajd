<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsxx/fbgl/bxh/js/bxh.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function($){
			});
		</script>
	</head>
	<body>
		<div>
			<html:form action="/fbglbbgl.do?method=add&type=query">
				<%@ include file="/comm/hiddenValue.jsp"%>
				<input id="pk" value="${pk}" type="hidden"/>
				<input id="bxhzt" value="${bxhzt}" type="hidden"/>
				<div style="" id="div_help" class="prompt">
					<h3>
						<span>提示：</span>
					</h3>
					<p>
						<span>	编学号时，若学生未分班，则不能编学号。
								<br />已编学号的学号将保留。
						</span>
					</p>
				</div>
				<table width="100%" border="0" class="formlist">
					<thead>
							<tr>
								<th colspan="2">
									<span>生成学号</span>
								</th>
							</tr>
					</thead>
					<tr>
						<th align="right">
							<font color="red">*</font>选择编学号规则
						</th>
						<td align="left">
							<html:select property="pzgzid" styleId="pzgzid" disabled="false"
								style="width:125px;">
								<html:options collection="pzgzList" property="pzgzid"
									labelProperty="pzgzmc" />
							</html:select>
							<span style="margin-left: 100px;">
								<a href="#" onclick="ckgzxx();return false;" style="text-decoration: underline;color: blue;">编学号规则查看</a>
							</span>
						</td>
					</tr><%--
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>已选择学生范围
						</th>
						<td align="left" style="color: red;">
							${zysl}
						</td>
					</tr>
					--%><tr>
						<th align="right">
							当前学生总数
						</th>
						<td align="left" id="xszs">
							${xszs}
						</td>
					</tr>
					<tr>
						<th align="right" >
							已编学号
						</th>
						<td align="left" id="ybxh">
							${ybxh}
						</td>
					</tr>
					<tr>
						<th align="right">
							未编学号
						</th>
						<td align="left" id="wbxh">
							${wbxh}
						</td>
					</tr>
				</table>
			</html:form>
			<%@include file="/xsgzgl/comm/browser/progressBar.jsp"%>
		</div>
		<table width="100%" border="0" class="formlist"
			style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">
							"
							<span class="red">*</span>"为必填项
						</div>
						<div class="btn">
							<button id="buttonSave"
								onclick="scxhSave();return false;"
								type="button">
								保存
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="buttonClose" onclick="iFClose();" type="button">
								关 闭
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>
