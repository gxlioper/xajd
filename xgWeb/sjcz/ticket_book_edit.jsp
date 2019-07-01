<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>

	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>日常管理 - 票务管理 - 订票情况查询 - 单个维护</a>
				</p>
			</div>

			<input type="hidden" name="pkVal"
				value="<bean:write name="rs" property="dph"/>" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>订票记录维护</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="height:22px">
							<th>
								学号
							</th>
							<td>
								<bean:write name="rs" property="xh" />
							</td>
							<th>
								年度
							</th>
							<td>
								<bean:write name="rs" property="nd" />
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								<bean:write name="rs" property="xm" />
							</td>
							<th>
								学年
							</th>
							<td>
								<bean:write name="rs" property="xn" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								学期
							</th>
							<td>
								<bean:write name="rs" property="xqmc" />
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								订票号
							</th>
							<td>
								<bean:write name="rs" property="dph" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<bean:write name="rs" property="xymc" />
							</td>
							<th>
								车次
							</th>
							<td>
								<bean:write name="rs" property="cc" />
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								<bean:write name="rs" property="zymc" />
							</td>
							<th>
								到站
							</th>
							<td>
								<bean:write name="rs" property="dz" />
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td>
								<bean:write name="rs" property="bjmc" />
							</td>
							<th>
								票价
							</th>
							<td>
								<bean:write name="rs" property="pj" />
							</td>
						</tr>
						<tr>
							<th>
								不限车次
							</th>
							<td>
								<bean:write name="rs" property="bxcc" />
							</td>
							<th>
								可顺延
							</th>
							<td>
								<bean:write name="rs" property="ksy" />
							</td>
						</tr>
						<tr>
							<th>
								可联程
							</th>
							<td>
								<bean:write name="rs" property="klc" />
							</td>
							<th>
								可无座
							</th>
							<td>
								<bean:write name="rs" property="kwz" />
							</td>
						</tr>
						<tr>
							<th>
								其他
							</th>
							<td>
								<bean:write name="rs" property="qt" />
							</td>
							<th>
								预订结果
							</th>
							<td>
								<html:select name="rs" property="ydjg">
									<html:option value=""></html:option>
									<html:options collection="jgList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								是否领取
							</th>
							<td>
								<html:select name="rs" property="sflq">
									<html:option value=""></html:option>
									<html:options collection="lqList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								经办人
							</th>
							<td>
								<html:select name="rs" property="jbr" style="width:90px"
									styleId="jbr">
									<html:option value=""></html:option>
									<html:options collection="jbrList" property="jbrgh"
										labelProperty="jbrxm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								乘车日期
							</th>
							<td>
								<html:text name="rs" property="ccrq" styleId="ccrq"
									onclick="return showCalendar('ccrq','y-mm-dd');"></html:text>
							</td>
							<th>

							</th>
							<td>

							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3">
								<bean:write name="rs" property="bz" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="button2"
										onclick="refreshForm('/xgxt/ticket_book_edit.do?act=save');Close();window.dialogArguments.document.getElementById('search_go').click();"
										id="buttonSave">
										保 存
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="button2" onclick="Close();return false;" id="buttonClose">
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
