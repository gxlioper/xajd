<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<!-- 头 -->
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qtsjFunction.js"></script>	
	<script language="javascript">
	function xljk_xlxhhy_modi(){	
			var bz=document.all["bz"].value;
			if (bz.length>150){
				alert ("备注不能超过150个汉字");
				document.all["bz"].focus();
				return false;
			}
			document.all["modi_flag"].value="yes";
			var xn_id=document.all['xlxhhy_xnid'].value;
			saveData('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=Modi&xlxhhy_xnid='+xn_id,'xh-csrq-sjhm-qsdh-hybh');
	}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/xljk_xlxhhy" method="post">
			<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
			<input type="hidden" id="xlxhhy_xnid" name="xlxhhy_xnid"
				value="<bean:write name="xlxhhy_xnid" scope="request"/>" />
			<!-- 标题 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>心理健康 - 心理协会 - 会员基本信息 - 修改会员基本信息</a>
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
						<font color="red">*</font>学号
					</th>
					<td align="left">
						<html:text name="rs" property="hyxh" styleId="hyxh"
							readonly="true" />
					</td>
					<th align="right">
						姓名
					</th>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm"
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
						<font color="red">*</font>出生日期
					</th>
					<td align="left">
						<html:text name='rs' property="csrq" styleId="csrq"
							 style="cursor:hand;"
							onclick="return showCalendar('csrq','y-mm-dd');" />
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
						<font color="red">*</font>手机号码
					</th>
					<td align="left">
						<html:text name="rs" property="sjhm" styleId="zy" />
					</td>
					<th align="right" nowrap="nowrap">
						<font color="red">*</font>寝室电话
					</th>
					<td align="left">
						<html:text name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						<font color="red">*</font>会员编号
					</th>
					<td align="left">
						<html:text name="rs" property="hybh" readonly="true" />
					</td>

					<th align="right">
						职务
					</th>
					<td align="left">
						<html:text name="rs" property="zw" />
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						备注
					</th>
					<td colspan="4" align="left">
						<html:textarea rows="5" name="rs" style="width:98%" property="bz"
							styleId="a" />
					</td>
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button id="buttonSave" 
										onclick="xljk_xlxhhy_modi()"
										style="width: 80px">
										修 改
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="update_success">
					<script>
						alert("修改成功!");
						dialogArgumentsQueryChick();
						Close();	
					</script>
				</logic:equal>
				<logic:equal name="message" value="update_fail">
					<script>
						alert("修改失败!");
						document.getElementById("tmpdiv").innerHTML = "";
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<div id="tmpdiv"></div>
	</body>
</html>
