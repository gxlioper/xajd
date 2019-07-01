<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
			function to_save(){
			    var zbjl=$("zbjl").value;
			    var tfsjjcl=$("tfsjjcl").value;	    
			    if(zbjl.length>2000){
			       alert("值班记录字数过长！");
			       return false;
			    }
			    if(tfsjjcl.length>2000){
			       alert("突发事件及处理字数过长！");
			       return false;
			    }	    
			    dataDoSave('sj-zbrydm-zbrydm1')
			}
		</script>
	</head>

	<body onload="checkWinType();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 值班记录 - 每日记录</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/data_search" method="post">
			<logic:empty name="rs">
								<br />
								<br />
								<p align="center">
									<!--  有错误发生！-->
								</p>
							</logic:empty>
							<logic:notEmpty name="rs">
								<input type="hidden" id="doType" name="doType"
									value="${doType }" />
								<input type="hidden" id="pkValue" name="pkValue"
									value="${pkValue }" />
								<input type="hidden" id="disableEle" name="disableEle" value="" />
								<input type="hidden" id="readonlyEle" name="readonlyEle"
									value="" />
								<input type="hidden" id="url" name="url" value="/sjcz/mrzbjlb.jsp" />
								<table class="formlist" border="0" align="center" style="width: 100%">
								<thead>
									<tr>
										<th colspan="4">
											<span>每日值班记录维护</span>
										</th>
									</tr>
								</thead>
								<tbody>		
										<tr>
											<th align="right" width="20%">
												<font color="red">*</font>日期：
											</th>
											<td align="left">
												<html:text name='rs' property="sj" styleId="sj" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('sj','y-mm-dd');" />
											</td>
											<th align="right" width="15%">
												天气：
											</th>
											<td align="left">
												<html:text name='rs' property="tq" style="width: 120px" styleId="tq" />
											</td>
										</tr>
										<logic:present name="syzy">
										<tr>
											<th align=right>校区：</th>
											<td>
												<html:select name="rs" property="xqdm" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="xiaoqquList" property="dm"
														labelProperty="xqmc" />
												</html:select>
											</td>
											<th></th>
											<td></td>
										</tr>
										</logic:present>
										<tr>
											<logic:notPresent name="syzy">
											<th align="right">
												<font color="red">*</font>楼栋名称：
											</th>
											<td align="left">
												<html:select name="rs" property="lddm" style="width:150px"
													styleId="lddm">
													<html:option value=""></html:option>
													<html:options collection="ldList" property="lddm"
														labelProperty="ldmc" />
												</html:select>
											</td>											
											<th align="right">
												<font color="red">*</font>值班人员：
											</th>
											<logic:present name="showZjjj"><!-- 浙江经济职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
											  <td align="left">
											  <html:text name="rs" property="zbrydm"></html:text>
											  </td>											
											</logic:present>
											<logic:notPresent name="showZjjj">
											   <td align="left">
												<html:select name="rs" property="zbrydm" style="width:150px"
													styleId="zbrydm">
													<html:option value=""></html:option>
													<html:options collection="zbryList" property="zbrydm"
														labelProperty="zbrymc" />
												</html:select>
											   </td>
											</logic:notPresent>
											
											</logic:notPresent>
											<logic:present name="syzy">
											<th align="right">
												<font color="red">*</font>值班人员1：
											</th>
											<td align="left">
												<html:select name="rs" property="zbrydm" style="width:150px"
													styleId="zbrydm">
													<html:option value=""></html:option>
													<html:options collection="zbryList" property="zbrydm"
														labelProperty="zbrymc" />
												</html:select>
											</td>
											<th align="right">
												<font color="red">*</font>值班人员2：
											</th>
											<td align="left">
												<html:select name="rs" property="zbrydm1" style="width:150px"
													styleId="zbrydm1">
													<html:option value=""></html:option>
													<html:options collection="zbryList" property="zbrydm"
														labelProperty="zbrymc" />
												</html:select>
											</td>
											</logic:present>
										</tr>
										<logic:present name="showZjjj"><!-- 浙江经济职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
										<tr>
											<th align="right">
												职务：
											</th>
											<td align="left">
												<html:text name='rs' property="zw" style="width: 120px"  />
											</td>
											<th align="right">
											    部门： 												
											</th >
											<td align="left">	
												<html:select name="rs" property="bmdm" style="width:300px">
													<html:options collection="bmList" property="bmdm"
														labelProperty="bmmc" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th align="right">
												到岗时间：
											</th>
											<td align="left">
												<html:text name='rs' property="dgsj" style="width: 120px"  />
											</td>
											<th align="right">
											    离岗时间： 												
											</th >
											<td align="left">	
											    <html:text name='rs' property="lgsj" style="width: 120px" />											
											</td>
										</tr>
										</logic:present>
										<logic:present name="zbrLxList">
										<tr>
											<th align="right">
												到岗时间：
											</th>
											<td align="left">
												<html:text name='rs' property="dgsj" style="width: 120px"  />
											</td>
											<th align="right">
											    离岗时间： 												
											</th >
											<td align="left">	
											    <html:text name='rs' property="lgsj" style="width: 120px" />											
											</td>
										</tr>
										<tr>
											<th align="right">
												值班人员类型：
											</th>
											<td align="left">
												<html:select name="rs" property="zbrlx">
													<html:options collection="zbrLxList" property="en"
														labelProperty="cn" />
												</html:select>
											</td>
											<th align="right"> 												
											</th >
											<td align="left">												
											</td>
										</tr>
										</logic:present>
										<tr>
											<th align="right">
												值班记录：<br>
												<font color="red"><限2000字内></font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='zbjl' style="width:95%"
													rows='8' />
											</td>
										</tr>
										<tr>
											<th align="right">
												突发事件及处理：<br>
												<font color="red"><限2000字内></font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='tfsjjcl' style="width:95%"
													rows='8' />
											</td>
										</tr>
										</tbody>
										<tfoot>
											<tr bgcolor="EEF4F9" align="center">
												<td colspan="4">
													<div class="btn">
														<logic:notPresent name="syzy">
															<button type="button"
																onclick="to_save()"
																style="width:80px" id="buttonSave">
																保 存
															</button>
															</logic:notPresent>
															<logic:present name="syzy">
															<button type="button"
																onclick=""
																style="width:80px" id="buttonSave">
																保 存
															</button>
														</logic:present>
														&nbsp;&nbsp;
														<button type="button" id="buttonClose" onclick="Close();return false;"
															style="width: 80px">
															关	闭
														</button>
													</div>
												</td>
											</tr>
										</tfoot>
									</table>
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
