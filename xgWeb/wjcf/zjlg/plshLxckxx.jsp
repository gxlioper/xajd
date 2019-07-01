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
	<html:form action="/wjcfzjlgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="failinfo" id="failinfo" value="${failinfo}"/>
		<input type="hidden" name="pkString" id="pkString" value="${pkString }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 解除留校察看 - 审核</a>
			</p>
		</div>
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
									<button type="button" class="" id="btn_save" 
										onclick="saveinfo('wjcf_zjlg_plshLxckxx.do?operType=save','');">
										保 存
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="" id="btn_close" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
			<tr style="height:23px">
				<th align="right" style="width:35%">
					审核
				</th>
				<td align="left" width="65%">
					<html:select property="sh" styleId="sh">
						<html:options collection="shList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<logic:notEqual value="xy" name="userType">
				<tr style="height:23px">
				<th align="right">
					解除时间
				</th>
				<td align="left">
					<html:text property="jcsj" styleId="jcsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd');" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<th align="right">
					解除文号
				</th>
				<td align="left">
					<html:text property="jcwh" styleId="jcwh"></html:text>
				</td>
			</tr>
			<tr>
				<th align="right">
					解除结果
				</th>
				<td align="left">
					<html:select property="jcjg" styleId="jcjg">
						<html:option value=""></html:option>
						<html:options collection="jcList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			</logic:notEqual>
			<tr>
				<th align="right">
					审核意见
				</th>
				<td align="left">
					<html:textarea property="yj" styleId="yj" style="width:95%" rows="6"></html:textarea>
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
