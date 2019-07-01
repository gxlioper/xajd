<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	</head>
	<body onload="checkWinType();">
		
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
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
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
				<input type="hidden" id="url" name="url" value="/sjcz/xsbxb.jsp" />
				<input type="hidden" id="tabName" name="tabName" value="xsbxb" />
				<input type="hidden" id="isSchool" name="isSchool" value="" />
				<input type="hidden" id="sqsj" name="sqsj" value="${rs.sqsj }" />
				
				
				<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生保险信息维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
									<button type="button" class="button2" onclick="dataDoSave('xh-nd-tbxzdm-tbsj-bxnx-tbgsdm')"
										 id="buttonSave">
										保 存
									</button>
									</logic:notEqual>
									<button type="button" class="button2" onclick="Close();return false;" 
										id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>学号
							</th>
							<td align="left">
								<logic:equal value="view" name="doType">
									${rs.xh }
								</logic:equal>
								<logic:notEqual value="view" name="doType">
								<html:text name='rs' property="xh"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this);" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									选择
								</button>
								</logic:notEqual>
							</td>
							<th>
								保险凭证号
							</th>
							<td align="left">
								<html:text name='rs' property="bxpzh" styleId="bxpzh" />
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
							</td>
							<th>
								<font color="red">*</font>年度
							</th>
							<td align="left">
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="nd" style="width:90px"
										styleId="nd" onchange="getInsureInfoExist();">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden name="rs" property="nd"/>
									<html:select name="rs" property="nd" style="width:90px" disabled="true"
										styleId="nd" onchange="getInsureInfoExist();">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
							</td>
							<th>
								<font color="red">*</font>保险公司
							</th>
							<td align="left">
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="tbgsdm" style="width:150px" 
										styleId="tbgsdm" onchange="refreshForm('newStuInsureApply.do?page=xsbxb');">
										<html:option value=""></html:option>
										<html:options collection="tbgsdmList" property="bxgsdm"
											labelProperty="bxgsmc" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden name="rs" property="tbgsdm"/>
									<html:select name="rs" property="tbgsdm" style="width:150px" disabled="true"
										styleId="tbgsdm" onchange="refreshForm('newStuInsureApply.do?page=xsbxb');">
										<html:option value=""></html:option>
										<html:options collection="tbgsdmList" property="bxgsdm"
											labelProperty="bxgsmc" />
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
							</td>
							<th>
								<font color="red">*</font>投保险种
							</th>
							<td align="left">
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="tbxzdm" style="width:150px" styleId="tbxzdm"
										onchange="refreshForm('newStuInsureApply.do?page=xsbxb')">
										<html:option value=""></html:option>
										<logic:notEmpty name="tbxzdmList">
											<html:options collection="tbxzdmList" property="bxxzdm"
												labelProperty="bxxzmc" />
										</logic:notEmpty>
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden name="rs" property="tbxzdm"/>
									<html:select name="rs" property="tbxzdm" style="width:150px" styleId="tbxzdm" disabled="true"
										onchange="refreshForm('newStuInsureApply.do?page=xsbxb')">
										<html:option value=""></html:option>
										<logic:notEmpty name="tbxzdmList">
											<html:options collection="tbxzdmList" property="bxxzdm"
												labelProperty="bxxzmc" />
										</logic:notEmpty>
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
							</td>
							<th>
								<font color="red">*</font>投保日期
							</th>
							<td align="left">
								<html:text name='rs' property="tbsj" styleId="tbsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('tbsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
							</td>
							<th>
								<font color="red">*</font>保险期限
							</th>
							<td align="left">
							<logic:notPresent name="showGdnz">
								<html:text name="rs" property="bxnx" styleId="bxnx"/>(年)
							</logic:notPresent>
							<logic:present name="showGdnz">
								<html:text name="rs" property="bxnx" styleId="bxnx" onchange=""/>(年)
							</logic:present>
							</td>	
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
							</td>
							<th>
								保费
							</th>
							<td align="left">
							<logic:notPresent name="showGdnz">
								<html:text name='rs' property="bf" styleId="bf" style="width:90px" />(元)
							</logic:notPresent>
							<logic:present name="showGdnz">
								<html:text name='rs' property="bf" styleId="bf" style="width:90px" />(元)
							</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								学制
							</th>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" readonly="true"/>
							</td>
						    <th>
								退保日期
							</th>
							<td align="left">
								<html:text name='rs' property="tuibsj" styleId="tuibsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('tuibsj','y-mm-dd');" />
							</td>	
						</tr>
						<tr>
							<th>
								身份证号
							</th>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh" readonly="true"/>
							</td>
							<th>
								退保标记
							</th>
							<td align="left">
								<html:select name="rs" property="tbbj" style="width:90px"
									styleId="tbbj">
									<html:option value=""></html:option>
									<html:option value="未退">未退</html:option>
									<html:option value="已退">已退</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								手机号码
							</th>
							<td align="left">
								<html:text name='rs' property="sjhm" styleId="sjhm" readonly="true"/>
							</td>
							 <th>
								缴费标记
							</th>
							<td align="left">
								<html:select name="rs" property="jfbj" style="width:90px" styleId="jfbj">
								    <html:option value="否">否</html:option>
								    <html:option value="是">是</html:option>
								</html:select>
							</td>
						</tr>
						<logic:present name="showGdnz">
						<tr>	
							<th>
							<logic:present name="showGdnz">
							保险档次
							</logic:present>
							</th>							
							<td align="left">
							<html:select name="rs" property="bxdc" onchange="">
								<html:option value="">---请选择---</html:option>
								<html:options collection="bxdcList" property="dcdm" labelProperty="dcmc"/>
							</html:select>
							
							</td>
							<td></td>
							<td></td>
						</tr>
						</logic:present>
						<tr align="left">
							<th>
								备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
					</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
