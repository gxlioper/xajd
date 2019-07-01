<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
	</head>
	<body onload="checkWinType();dataManLoad();">
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
				<input type="hidden" id="url" name="url" value="/sjcz/sthdxxb.jsp" />
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="<bean:write name="userOnLine" scope="session"/>" />
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>社团活动信息维护</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" style="display:none">
									选择
								</button>
							</td>
							<td align="right">
								<font color="red">*</font>年度：
							</td>
							<td align="left">
								<html:select name="rs" property="nd" style="width:90px"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<td align="right">
								<font color="red">*</font>学年：
							</td>
							<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<td align="right">
								<font color="red">*</font>学期：
							</td>
							<td align="left">
								<html:select name="rs" property="xq" style="width:90px"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<td align="right">
								<font color="red">*</font>社团名称：
							</td>
							<td align="left">
								<html:select name="rs" property="stdm" style="width:90px"
									styleId="stdm">
									<html:option value=""></html:option>
									<html:options collection="stList" property="stdm"
										labelProperty="stmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<logic:present name="showhzy">
								<td align="right">
									任职情况：
								</td>
								<td align="left">
									<html:text name="rs" property="rzqk" styleId="rz"/>
								</td>
							</logic:present>
							<logic:notPresent name="showhzy">
								<td align="right">
									会员等级：
								</td>
								<td align="left">
									<html:select name="rs" property="hydj" style="width:90px">
										<html:option value=""></html:option>
										<html:option value="一般">一般</html:option>
										<html:option value="骨干">骨干</html:option>
									</html:select>
								</td>
							</logic:notPresent>
						</tr>
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<td align="right">
								<font color="red">*</font>加入时间：
							</td>
							<td align="left">
								<html:text name='rs' property="jrsj" styleId="jrsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jrsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<td align="right">
								退出时间：
							</td>
							<td align="left">
								<html:text name='rs' property="tcsj" styleId="tcsj" readonly="readonly"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('tcsj','y-mm-dd');" />
							</td>
						</tr>
						<logic:present name="showhzy">
							<tr>
								<td align="right">
									考核结果：
								</td>
								<td align="left">
									<html:text name="rs" property="khjg" styleId="kh"/>
								</td>
								<td align="right">
								</td>
								<td align="left">
								</td>
							</tr>
						</logic:present>
						<tr align="left">
							<td align="right">
								备注：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="dataCanModi(true)" id="buttonModi">
									修 改
								</button>
								<button type="button" onclick="if(checkXnNd('xn','nd'))dataDoSave('xh-stdm-jrsj');"
									id="buttonSave">
									保 存
								</button>
								<button type="button" onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
			</div>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
  			<logic:equal value="true" name="isSuccess">
			  <script type="text/javascript">
			    alert('操作成功！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="isSuccess">
			  <script type="text/javascript">
			    alert('操作失败！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
		</html:form>
	</body>
</html>
