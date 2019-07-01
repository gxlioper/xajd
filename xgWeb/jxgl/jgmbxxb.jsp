<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<html:form action="/viewArmyStu" method="post">
			<div class="tab_cur" id="title_m">
				<p class="location">
					<em>您的当前位置:</em><a><span id="tipFollow"></span></a>
				</p>
			</div>
			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					无记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" name="userOnLine" id="userOnLine" value="${userOnLine}" />
				<input type="hidden" id="url" name="url" value="/viewCadremanInfo.do?zzlb=<bean:write name="rs" property="zzlb"/>" />
				<div class="tab">
				 <table width="100%"  border="0" class="formlist">
				    <thead>
				    	<tr>
				        	<th colspan="4"><span>基本信息</span></th>
				        </tr>
				    </thead>
					 <tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
							 <button type="button"
								onclick="Savedata('xh-zzlb','CadremanInfoSave.do');return false;"
								id="buttonSave">
								保 存
							</button>
							<button type="button" onclick="Close();return false;" 
								id="buttonClose">
								关 闭
							</button>
				          </div></td>
				      </tr>
				    </tfoot>
					<tbody>
						<tr>
							<th align="right">
								<font color="red">*</font>学号：
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<logic:equal name="doType" value="add">
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do?zzlb=<bean:write name="rs" property="zzlb"/>',750,550);"
										class="btn_01" id="buttonFindStu" >
										选择
									</button>
								</logic:equal>
							</td>
							<th align="right">
								姓名：
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<th align="right">
								性别：
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
						</tr>
						<tr>
							<th align="right">
								年级：
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<th align="right">
								加入组织时间：
							</th>
							<td align="left">
								<html:text name="rs" property="jrzzsj" styleId="jrzzsj" onclick="return showCalendar('jrzzsj','y-mm-dd');" readonly="true"></html:text>
							</td>
						</tr>
						
						<tr>
							<th align="right">
								专业：
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<th align="right">
								<font color="red">*</font>组织类别：
							</th>
							<td align="left">
								<html:text name="rs" property="zzlb" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								班级：
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<th align="right">
								组织职务：
							</th>
							<td align="left">
								<html:text name='rs' property="zzzw" styleId="zzzw" maxlength="10"/>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败!");
			</script>
		</logic:equal>
	</body>
</html>
