<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body >
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/szdwfzjy" method="post">
			<input type="hidden" id="method" name="method"
				value="xsjlsbgrSq" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>发展教育-发展教育相关-学术奖励申报</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
			<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/szdwfzjy.do?method=xssbshOne&tableName=view_fzjyxsjlsbgr" />
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name='rs' property='xh' />" />
				<div class="tab">
			  	<table width="100%"  border="0" class="formlist">
				    <thead>
				    	<tr>
				        	<th colspan="4"><span>学术奖励申报</span></th>
				        </tr>
				    </thead>
				    <tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          		<button type="button" name="保存" id="buttonSave" onclick="szsxDataDoSave('szdwfzjy.do?method=xsjlsbgrSh','xh-sbjhmc-xn-nd');"
								 id="buttonSave">保 存</button>
				          </div></td>
				      </tr>
				    </tfoot>
				    <tbody>
						<tr>
							<th align="right">
								<font color="red">*</font>学年：
							</th>
							<td align="left">
								<html:select name = "rs" property="xn" style="width:120px" 
									styleId="xn">
								<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>年度：
							</th>
							<td align="left">
								<html:select name = "rs" property="nd" style="width:90px"
										styleId="nd">
								<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								申报学术科技成果名称:
							</th>
							<td colspan="3">
								<html:text name = "rs" property="sbxskjcgmc" style="width:99%"  styleId="sbxskjcgmc"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>学号：
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									选择
								</button>
							</td>
							<th align="right">
								姓名：
							</th>
							<td align="left">
								<bean:write name='rs' property="xm"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								性别：
							</th>
							<td align="left">
								<bean:write name='rs' property="xb"/>
							</td>
							<th align="right">
								年级：
							</th>
							<td align="left">
								<bean:write name='rs' property="nj"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<th align="right">
								专业：
							</th>
							<td align="left">
								<bean:write  name='rs' property="zymc"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								班级：
							</th>
							<td align="left">
								<bean:write name='rs' property="bjmc"/>
							</td>
							<th align="right">
								申报学术科技成果类型:
							</th>
							<td align="left">
									<html:select name = "rs" property="sbxskjcglxdm" styleId="sbxskjcglxdm">
										<html:option value=""></html:option>
										<html:options collection="sbxskjcglxList" property="sbxskjcglxdm"
										labelProperty="sbxskjcglxmc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								成果完成时间：
							</th>
							<td align="left">
								<html:text name= "rs"  property="cgwcsj" styleId="cgwcsj" readonly = "true"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cgwcsj','y-mm-dd');" />
							</td>
							<th align="right">
								成果级别:
							</th>
							<td align="left">
								<html:select name = "rs" property="cgjb" styleId="cgjb">
										<html:option value=""></html:option>
										<html:options collection="cgjbList" property="cgjbdm"
										labelProperty="cgjbmc" />
									</html:select>
							</td>
						</tr>
						<logic:equal  name="userType" value = "xy" scope = "session">
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核意见：
							</th>
							<td align="left" colspan="3">
								<html:textarea name = "rs" property="xyshyj" style="width:99%" rows="5"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								学工处审定意见：
							</th>
							<td align="left" colspan="3">
								<html:textarea name = "rs" property="xgcsdyj" style="width:99%"  rows="5" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核：
							</th>
							<td align="left">
								<html:select name = "rs" property="xysh" style="width:120px" styleId="xysh" >
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
							<th align="right">								
							`	学校审核：
							</th>
							<td align="left">
								<html:select name = "rs"  property="xxsh" style="width:120px" styleId="xxsh" disabled = "true">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
								<input type = "hidden" name = "xxsh" value="<bean:write name = "rs"  property="xxsh"/>" />
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name="userType" value = "xy" scope = "session">
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核意见：
							</th>
							<td align="left"  colspan="3">
								<html:textarea name = "rs" property="xyshyj" style="width:99%"  rows="5" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								学工处审定意见：
							</th>
							<td align="left" colspan="3">
								<html:textarea name = "rs" property="xgcsdyj" style="width:99%"  rows="5"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核：
							</th>
							<td align="left">
								<html:select  name = "rs"  property="xysh" style="width:120px" styleId="xysh" disabled = "true">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
								<input type = "hidden" name = "xysh" value="<bean:write name = "rs"  property="xysh"/>" />
							</td>
							<th align="right">
								学校审核：
							</th>
							<td align="left">
								<html:select  name = "rs"  property="xxsh" style="width:120px" styleId="xxsh">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
						</tr>
						</logic:notEqual>
						<tr>
							<th align="right">
								学生科研成果价值说明：
							</th>
							<td colspan="3">
								<html:textarea name = "rs" property="xskycgjzsm" style="width:99%"  rows="5"/>
							</td>
						</tr>
						</tbody>
					</table>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("保存成功！");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("保存失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
