<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
</script>
	</head>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><span id="tipFollow"></span></a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="<bean:write name="userOnLine" scope="session"/>" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/sxhbb.jsp" />
				<fieldset>
					<logic:notEmpty name="gwcxview">
					<div class="tab">
					<table width="100%" class="formlist" border="0">
							<thead>
								<tr>
									<th colspan="4"><span>思想汇报维护</span></th>
								</tr>
							</thead>
							<tfoot>
						      <tr>
						        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
						          <div class="btn">
						          <button type="button" onclick="dataCanModi(true)"
										id="buttonModi">
										修 改
									</button>
									<logic:notEmpty name="gwcxview"> 
									<button type="button"
										onclick="dataDoSave('xh-pxjssj');"
										id="buttonSave">
										保 存
									</button>
									</logic:notEmpty>
									<logic:empty name="gwcxview"> 
									<button type="button"
										onclick="if(checkXnNd('xn','nd')&&checkbz())dataDoSave('xn-xq-xh-pxjssj');"
										id="buttonSave">
										保 存
									</button>
									</logic:empty>
									<button type="button" onclick="Close();return false;" 
										id="buttonClose">
										关 闭
									</button>
						          </div></td>
						      </tr>
						    </tfoot>
						
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>学号
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									id="buttonFindStu" >
									选择
								</button>
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
								<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
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
								<font color="red">*</font>递交时间
							</th>
							<td align="left">
								<html:text name='rs' property="djsj" styleId="pxjssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('pxjssj','y-mm-dd');" />
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
								班级
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
						</tr>
						<tr align="left">
							<th>
								备注
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
					</div>
					</logic:notEmpty>
					<logic:empty name="gwcxview"> 
					<div class="tab">
					<table width="100%" class="formlist" border="0">
						<thead>
							<tr>
								<th colspan="4"><span>思想汇报维护</span></th>
							</tr>
						</thead>
						<tfoot>
						      <tr>
						        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
						          <div class="btn">
						          <button type="button" onclick="dataCanModi(true)"
										id="buttonModi">
										修 改
									</button>
									<logic:notEmpty name="gwcxview"> 
									<button type="button"
										onclick="dataDoSave('xh-pxjssj');"
										id="buttonSave">
										保 存
									</button>
									</logic:notEmpty>
									<logic:empty name="gwcxview"> 
									<button type="button"
										onclick="if(checkXnNd('xn','nd')&&checkbz())dataDoSave('xn-xq-xh-pxjssj');"
										id="buttonSave">
										保 存
									</button>
									</logic:empty>
									<button type="button" onclick="Close();return false;"
										id="buttonClose">
										关 闭
									</button>
						          </div></td>
						      </tr>
						    </tfoot>
						
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>学号
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" style="display:none">
									选择
								</button>
							</td>
							<th>
								<font color="red">*</font>年度
							</th>
							<td align="left">
								<html:select name="rs" property="nd" style="width:90px"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
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
								<font color="red">*</font>学年
							</th>
							<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
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
								<font color="red">*</font>学期
							</th>
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
							<th>
								年级
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<th>
								<font color="red">*</font>递交时间
							</th>
							<td align="left">
								<html:text name='rs' property="djsj" styleId="pxjssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('pxjssj','y-mm-dd');" />
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
							</th>
							<td align="left">
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
							</th>
							<td align="left">
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
						<tr align="left">
							<th>
								备注
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
					</div>
					</logic:empty>
				</fieldset>
				<div class="buttontool">
					
				</div>				
			</logic:notEmpty>
			<logic:present name="isSuccess">
				<logic:equal value="true" name="isSuccess">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="isSuccess">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
