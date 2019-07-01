<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<!-- 头 -->
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>

		<html:form action="/xljk_xlxhhy" method="post">
			<!-- 标题 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>心理健康 - 心理协会 - 会员基本信息 - 查看会员基本信息</a>
				</p>
			</div>
			<!-- 标题 end-->
			<!-- 会员基本信息 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="6">
							<span>会员基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" colspan="2">
						学号
					</th>
					<td align="left">
						<html:text name="rs" property="hyxh" styleId="hyxh"
							readonly="true" />
					</td>
					<th align="right">
						姓名
					</th>
					<td align="left">
						<html:text name="rs" property="xm" styleId="hyxm"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2">
						性别
					</th>
					<td align="left">
						<html:text name="rs" property="xb" styleId="xb" readonly="true" />
					</td>
					<th align="right">
						出生日期
					</th>
					<td align="left">
						<html:text name="rs" property="csrq" styleId="csrq"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2">
						<bean:message key="lable.xb" />
					</th>
					<td align="left">
						<html:text name="rs" property="xymc" styleId="xymc"
							readonly="true" />
					</td>
					<th align="right">
						班级
					</th>
					<td align="left">
						<html:text name="rs" property="bjmc" styleId="bjmc"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2" nowrap="nowrap">
						手机号码
					</th>
					<td align="left">
						<html:text name="rs" property="sjhm" styleId="zy" readonly="true" />
					</td>
					<th align="right" nowrap="nowrap">
						寝室电话
					</th>
					<td align="left">
						<html:text name="rs" property="qsdh" readonly="true" />
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						会员编号
					</th>
					<td align="left">
						<html:text name="rs" property="hybh" readonly="true" />
					</td>

					<th align="right">
						职务
					</th>
					<td align="left">
						<html:text name="rs" property="zw" readonly="true" />
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						备注
					</th>
					<td colspan="4" align="left">
						<html:textarea rows="5" name="rs" style="width:98%" property="bz"
							styleId="a" readonly="true" />
					</td>
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
								&nbsp;&nbsp;
								<div class="btn">
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
								</div>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<div id="tmpdiv"></div>
		</html:form>
	</body>
</html>
