<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function checkzf(type){
		var xytbgxcs = document.getElementById("xytbgxcs").value;
		if(xytbgxcs.length>150){
			alert("特别关心措施不能超过150个汉字！！");
			return false;
		}
		var zxqjzysj = document.getElementById("zxqjzysj").value;
		if(zxqjzysj.length>300){
			alert("重要事件及结果不能超过300个汉字！！");
			return false;
		}
	if(type == "modify"){
		dataCanModi(true);
	}
	if(type == "save"){
		dataDoSave('xh-tbgxxslbdm');
	}
	}
</script>
</head>
	<body onload="checkWinType();">
		<html:form action="/data_search" method="post">
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
				<input type="hidden" id="url" name="url"
					value="/sjcz/xytbgxxsxxb.jsp" />
					
				<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 数据维护 - 特殊学生信息维护</a>
				</p>
			</div>	
					
					<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>心理测试结果维护</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
											<button type="button" class="" onclick="checkzf('save');"id="buttonSave">
											保 存
										</button>
										&nbsp;
										<button type="button" class="" onclick="Close();return false;" 
											id="buttonClose">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					<tbody>
						<tr>
							<th nowrap="nowrap">
								<font color="red">*</font>学号
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<logic:equal name="doType" value="add">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									选择
								</button>
								</logic:equal>
							</td>
							<th>
								姓名
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<th>
								年级
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<th>
								专业
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<th>
								<font color="red">*</font>特别关心类别
							</th>
							<td align="left">
								<html:select name="rs" property="tbgxxslbdm" style="width:90px"
									styleId="tbgxxslbdm">
									<html:option value=""></html:option>
									<html:options collection="tbgxxslbdmList" property="tbgxxslbdm"
										labelProperty="tbgxxslbmc" />
								</html:select>
							</td>
						</tr>
						
						<logic:equal name="xxdm" value="10827" scope="session">
						<tr>
							<th>
								寝室号
							</th>
							<td align="left">
								<html:text name='rs' property="ssbh" styleId="ssbh" />
							</td>
							<th>
								学生电话
							</th>
							<td align="left">
								<html:text name='rs' property="dhhm" styleId="dhhm" />
							</td>
						</tr>
						<tr>
							<th>
								出生年月
							</th>
							<td align="left">
								<html:text name='rs' property="csrq" styleId="csrq" onclick="return showCalendar('csrq','y-mm-dd');"/>
							</td>
							<th>
								家长姓名
							</th>
							<td align="left">
								<html:text name='rs' property="jzxm" styleId="jzxm" />
							</td>
						</tr>
						<tr>
							<th>
								家长电话
							</th>
							<td align="left">
								<html:text name='rs' property="jzdh" styleId="jzdh" />
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="10338" name="xxdm" scope="session">
						<tr align="left">
							<th>
								心理测试类别
							</th>
							<td>
								<html:text name='rs' property="xlcslb" maxlength="30"></html:text>
							</td>
							<th>
								心理问题类型
							</th>
							<td>
								<html:text name='rs' property="xlwtlx" maxlength="30"></html:text>
							</td>
						</tr>
						<tr >
							<th>
								是否困难生
							</th>
							<td>
								<html:text name='rs' property="sfkns" readonly="true"></html:text>
<%--								<bean:write name="rs" property="sfkns"/>--%>
							</td>
							<th>
								是否单亲
							</th>
							<td>
								<html:radio name='rs' property="sfdq" value="是"></html:radio>
								<html:radio name='rs' property="sfdq" value="否"></html:radio>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								特别学生来源
							</th>
							<td align="left">
								<html:select name="rs" property="lydm" style="width:90px"
									styleId="tbgxxslbdm">
									<html:option value=""></html:option>
									<html:options collection="tsxslyList" property="lydm"
										labelProperty="lymc" />
								</html:select>
							</td>
							<th>
								
							</th>
							<td align="left">
								
							</td>
						</tr>
						<tr align="left">
							<tr align="left">
							<th>
								心理状况
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zxqjzysj'  style="width:99%;word-break:break-all;"
									rows='5' />
							</td>
						</tr>
							<th>
								措施及建议
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xytbgxcs' style="width:99%;word-break:break-all;"
									rows='5' />
							</td>
						</tr>
						
						</tbody>
					</table>
					</div>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
