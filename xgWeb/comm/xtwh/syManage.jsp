<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
	</head>

	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXtwh">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<!-- 首页设置 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="2">
							<span>首页维护</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<th align="right" width="20%">
							提示语句：
						</th>
						<td align="left" width="">
							<html:text name="rs" property="tsyj" maxlength="50" style="width: 80%"/>
						</td>
					</tr>
<%--					<tr style="height: 23px">--%>
<%--						<th align="right" colspan="2">--%>
<%--							<font color="blue">友情提示：改完提示语句需要重启tomcat才起作用,请注意~^_^~</font>--%>
<%--						</th>--%>
<%--					</tr>--%>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="2">
							<div class="btn">
								<button type="button" id="buttonSave" onclick="saveUpdate('/xgxt/commXtwh.do?method=syManage&doType=save','')">
									保 存
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</br>
			</br>
			</br>
			</br></br>
			</br>
			</br>
			</br></br>
			</br></br>
			</br>
			</br>
			</br></br>
			</br>
			</br></br>
			</br>
			</br>
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		<!-- 提示信息 end-->
	</body>
</html>
