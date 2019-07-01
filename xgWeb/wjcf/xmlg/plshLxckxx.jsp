<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>当前位置：违纪处分 - 解除留校察看 - 审核</a>
			</p>
		</div>

		<html:form action="/wjcfxmlgwh" method="post">
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="failinfo" id="failinfo"
				value="${failinfo}" />
			<input type="hidden" name="pkString" id="pkString"
				value="${pkString }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>批量审核</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="btn_save"
										onclick="saveinfo('wjcf_xmlg_plshLxckxx.do?operType=save','');">
										保 存
									</button>
									<button type="button" id="btn_close" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th style="width:30%">
								审核
							</th>
							<td align="left" width="70%">
								<html:select property="sh" styleId="sh">
									<html:options collection="shList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<logic:equal value="xy" name="userType">
							<tr>
								<th>
									班级评&nbsp;&nbsp;
									<br />
									议意见
								</th>
								<td align="left" colspan="">
									<html:textarea property="bjpyyj" styleId="bjpyyj" rows="4"
										style="width:95%"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									辅导员&nbsp;&nbsp;
									<br />
									签定意见
								</th>
								<td align="left" colspan="">
									<html:textarea property="fdyjdyj" styleId="fdyjdyj" rows="4"
										style="width:95%"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>
								审核意见
							</th>
							<td align="left">
								<html:textarea property="yj" styleId="yj" style="width:95%"
									rows="5"></html:textarea>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
					<!-- 保存后提示页面 -->
					<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
		</html:form>
	</body>
</html>