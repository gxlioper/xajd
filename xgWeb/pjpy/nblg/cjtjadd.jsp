<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>

<script type="text/javascript">
	function savetj() {
		var tjmc = document.getElementById('tjmc').value;
		var tjxz = document.getElementById('tjxz').value;
		if (tjmc==''||tjxz=='') {
			alert('带*号为必填项!');
			return;
		}
		refreshForm('pjpy_nblg_cjtjadd.do?act=save');
		document.getElementById('btn_save').disabled=true;
	}
</script>
</head>
<body>
				<div class="tab_cur">
					<p class="location">
						<em>当前所在位置：</em><a>评奖评优 - 信息维护 - 课程成绩与等级考试成绩维护 - 成绩条件过滤设置(增加)</a>
					</p>
		</div>
	<html:form action="/pjpynblgwh" method="post">
		<table width="100%" class="formlist">
			<thead >
				<tr>
					<td  colspan="2">
						单个增加
					</td>
				</tr>
			</thead>
			<tfoot>
								<tr>
									<td colspan="4">
										<div class="btn">
													<button
														onclick="savetj()"
														id="btn_save">
														保 存
													</button>
											<button onclick="Close();return false;" id="buttonClose">
												关 闭
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
			<tr>
				<th  width="16%">
					<font color="red">*</font>名称:
				</th>
				<td width="34%">
					<html:text property="tjmc" styleId="tjmc"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>性质:
				</th>
				<td>
					<html:select property="tjxz" styleId="tjxz" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="tjxzList" property="tjxzdm" labelProperty="tjxzmc"/>
					</html:select>
				</td>
			</tr>
		</table>
		<!--  <div class="buttontool" align="center">
			<button id="btn_save" class="button2" onclick="savetj()" style="width:80px">保存</button>
			&nbsp;&nbsp;
			<button id="btn_close" class="button2" onclick="window.close();return false;" style="width:80px">关闭</button>
		</div>-->
	</html:form>
	<!-- 保存提示 -->
	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
</body>
</html>