<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="checkWinType();">
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 奖学金申请 - 填写申请表</a>
				</p>
		</div>
		<html:form action="/data_search" method="post">
			<span id="tipFollow" style="display:none"></span>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
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
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="xh" />
				<input type="hidden" id="url" name="url" value="/sjcz/xsjxjb.jsp" />
				<input type="hidden" name="tab1" id="tab1" value="qtjxj"/>
				<input type="hidden" name="oldjxjdm" id="oldjxjdm" value="${oldjxjdm }"/>
				
				
				<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>填写申请表</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
<%--									<button type="button" onclick="dataCanModi(true)"  id="buttonModi">--%>
<%--										修 改--%>
<%--									</button>--%>
<%--									<button type="button" onclick="dataDoSaveApply1('/xgxt/applySave.do','jxjdm','jxj')"--%>
<%--										 id="buttonSave">--%>
<%--										保 存--%>
<%--									</button>--%>
								<logic:equal value="13108" name="xxdm">
									<button type="button"  onclick="showTopWin('/xgxt/stu_cj_details.do?xh=' + document.getElementById('xh').value, 800, 600)" 
										id="buttonQuery">
										查看学生成绩
									</button>
								</logic:equal>
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									关 闭
								</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<th>
									<font color="red">*</font>学号
								</th>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu" style="display:none">
										选择
									</button>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<th>
									<font color="red">*</font>学号
								</th>
								<td align="left">
									<bean:write name='rs' property="xh" />
								</td>
							</logic:equal>
							<th>
								年度
							</th>
							<td align="left">
								<bean:write name='rs' property="nd" />
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<th>
								学年
							</th>
							<td align="left">
								<bean:write name='rs' property="xn" />
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
								<font color="red">*</font>奖学金
							</th>
							<td align="left">
								<html:select name="rs" property="jxjdm" styleId="jxjdm">
									<option value=""></option>
									<html:options collection="jxjList" property="jxjdm"
										labelProperty="jxjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<th>
								奖学金类别
							</th>
							<td align="left">
								<bean:write name='rs' property='jxjlb' />
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
								奖励金额
							</th>
							<td align="left">
								<bean:write name='rs' property='jlje' />
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<th>
								担任职务
							</th>
							<td align="left">
								<html:text name='rs' property="drzw" styleId="a" />
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
							</th>
							<td align="left">
							</td>
						</tr>
						<tr>
							<th>
								科研项目
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='kyxm' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr>
							<th>
								申请理由
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='sqly' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
