<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript">
	function InputNumValue(obj) {
	    if (((event.keyCode >= 48 && event.keyCode <= 57))) {						
		    return true;				
	    } else {
		    return false;
	    }	
    }
    </script>
	</head>
	<body onload="checkWinType();dataManLoad();">
		<div class="tab_cur">
			<p class="location" >
				<em>您的当前位置:</em><a id="tipFollow"></a>
			</p>
		</div>
		
		<html:form action="/data_search" method="post">
			<logic:equal value="true" name="isSuccess">
				<script language="javascript">
					alert("保存成功！");
					Close();
	         		window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="isSuccess">
				<script language="javascript">
					alert("保存失败！");
					Close();
	         		window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:notPresent name="showXbemy">
				<div class="title">
					<div class="title_img" id="title_m">
						<span id=""></span>
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
						value="xm-xb-xy-nj-zy-bj" />
					<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
					<input type="hidden" id="getStuInfo" name="getStuInfo"
						value="xm-xb-xymc-nj-zymc-bjmc-zzmmmc" />
					<input type="hidden" id="url" name="url" value="/sjcz/wjcflsb.jsp" />
					<input type="hidden" id="clwh" name="clwh"
						value="<bean:write name="clwh"/>" />
					<input type="hidden" id="userOnLine" name="userOnLine"
						value="<bean:write name="userOnLine" scope="session"/>" />



					<div class="tab">
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th colspan="4">
										<span>违纪处分信息维护</span>
									</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<td colspan="4">
										<div class="btn">
											<button type="button" onclick="dataCanModi(true)" id="buttonModi">
												修 改
											</button>
											<button type="button"
												onclick="if(checkXnNd('xn','nd'))dataDoSave('cfyy-cflb-cfsj-xm');return false;"
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
							<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<html:text name='rs' property="xh" styleId="xh"
											onkeypress="autoFillStuInfo(event.keyCode,this)" />
										<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu" style="display:none">
											选择
										</button>
							</td>
							<th width="16%">
								<font color="red">*</font>年度
							</th>
							<td width="34%">
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
							<td>
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<th>
								<font color="red">*</font>学年
							</th>
							<td>
								<html:select name="rs" property="xn" style="width:90px"
											styleId="xn">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
							</td>
						</tr>
								<tr>
									<th>
										政治面貌
									</th>
									<td align="left">
										<html:text name='rs' property="zzmmmc" readonly="true"
											styleId="zzmmmc" />
										<html:text name='rs' property="xb" styleId="xb"
											style="display:none" />
									</td>
									<th>
										学期
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
										<font color="red">*</font>处分类别
									</th>
									<td align="left">
										<html:select name="rs" property="cflb" style="width:90px"
											styleId="cflb">
											<html:option value=""></html:option>
											<html:options collection="cflbList" property="cflbdm"
												labelProperty="cflbmc" />
										</html:select>
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
										<font color="red">*</font>处分事由
									</th>
									<td align="left">
										<html:select name="rs" property="cfyy" style="width:90px"
											styleId="cfyy">
											<html:option value=""></html:option>
											<html:options collection="cfyyList" property="cfyydm"
												labelProperty="cfyymc" />
										</html:select>
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
										<font color="red">*</font>处分时间
									</th>
									<td align="left">
										<html:text name='rs' property="cfsj" styleId="cfsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('cfsj','y-mm-dd');" />
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
										处分文号
									</th>
									<td align="left">
										<html:text name='rs' property="cfwh" styleId="cfwh" onfocus="" maxlength="30"/>
									</td>
								</tr>
								<!-- 浙江理工GEGIN -->
								<logic:equal value="10338" name="xxdm">
									<logic:equal value="yes" name="zjlgLxck">
										<tr>
											<th>
												解除时间
											</th>
											<td align="left">
												${rs.cxsj }
											</td>
											<th>
												解除文号
											</th>
											<td align="left">
												${rs.cxwh }
											</td>
										</tr>
									</logic:equal>
								</logic:equal>
								<!-- END -->
								<!-- 其它学校 -->
								<logic:notEqual value="10338" name="xxdm">
									<tr>
										<th>
											申诉时间
										</th>
										<td align="left">
											<html:text name='rs' property="cxsj" styleId="cxsj"
												onblur="dateFormatChg(this)" style="cursor:hand;"
												onclick="return showCalendar('cxsj','y-mm-dd');" />
										</td>
										<th>
											申诉文号
										</th>
										<td align="left">
											<html:text name='rs' property="cxwh" styleId="cxwh" maxlength="30"/>
										</td>
									</tr>
								</logic:notEqual>
								<!-- END -->
								<!-- 浙江理工  begin-->
								<logic:equal value="10338" name="xxdm">
									<logic:equal value="yes" name="zjlgLxck">
										<tr>
											<th>
												留校察看解除结果
											</th>
											<td align="left">
												${rs.cxjg }
											</td>
											<th>

											</th>
											<td align="left">

											</td>
										</tr>
									</logic:equal>
								</logic:equal>
								<tr>
									<th>
										申诉结果
									</th>
									<td align="left">
										<%--							<html:text name='rs' property="ssjg" styleId="ssjg" />--%>
										<html:select name="rs" property="ssjg" style="width:100px"
											styleId="ssjg">
											<html:option value=""></html:option>
											<html:option value="解除处分"></html:option>
											<html:option value="更改处分"></html:option>
											<html:option value="维持原处分"></html:option>
											<html:option value="未决定"></html:option>
										</html:select>
									</td>
									<logic:equal value="11078" name="xxdm">
										<th>
											原(申诉更改前)
											<br />
											处分类别：
										</th>
										<td align="left">
											<html:select name="rs" property="ycflb" style="width:100px"
												styleId="ycflb">
												<html:option value=""></html:option>
												<html:options collection="cflbList" property="cflbdm"
													labelProperty="cflbmc" />
											</html:select>
										</td>
									</logic:equal>
									<logic:notEqual value="11078" name="xxdm">
										<th></th>
										<td align="left"></td>
									</logic:notEqual>
								</tr>
								<logic:equal value="11078" name="xxdm">
									<tr>
										<th>
											<font color="red">*</font>是否教务处数据
										</th>
										<td>
											<html:select property="sfjw" name="rs" style="width:90px">
												<html:option value="否">否</html:option>
												<html:option value="是">是</html:option>
											</html:select>
										</td>
										<th></th>
										<td></td>
									</tr>
								</logic:equal>
								</tbody>
								
							<thead>
								<tr>
									<td align="right" colspan="4">
										<span id="main2">教育跟踪记录 &nbsp;&nbsp;<a onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">查看</a>
										</span>
									</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="4">
										<div id="child2" style="display:none">
											<table width="100%" border="1" align="center" class="tbstyle">
												<tr>
													<th style="width:25%">
														第一季度教育情况记录
													</th>
													<td align="left" colspan="3" style="width:75%">
														<html:textarea name="rs" property="xsbx1" rows="3"
															readonly="true" style="width:400px"></html:textarea>
													</td>
												</tr>
												<tr>
													<th>
														第二季度教育情况记录
													</th>
													<td align="left" colspan="3">
														<html:textarea name="rs" property="xsbx2" rows="3"
															readonly="true" style="width:400px"></html:textarea>
													</td>
												</tr>
												<tr>
													<th>
														第三季度教育情况记录
													</th>
													<td align="left" colspan="3">
														<html:textarea name="rs" property="xsbx3" rows="3"
															readonly="true" style="width:400px"></html:textarea>
													</td>
												</tr>
												<tr>
													<th>
														第四季度教育情况记录
													</th>
													<td align="left" colspan="3">
														<html:textarea name="rs" property="xsbx4" rows="3"
															readonly="true" style="width:400px"></html:textarea>
													</td>
												</tr>
											</table>

										</div>
									</td>
								</tr>
								<tr align="left">
									<th>
										备注
										<br />
										<font color="red">(限制在800字以内)</font>
									</th>
									<td colspan="3">
										<html:textarea name='rs' property='bz' style="width:500px"
											rows='5' onkeyup="checkLen(this,800)" />
									</td>
								</tr>
								</tbody>
							</table>
							</div>
				</logic:notEmpty>
			</logic:notPresent>
			<logic:present name="showXbemy">
				<logic:equal value="showXbemy" name="showXbemy">
					<div class="title">
						<div class="title_img" id="title_m">
							<span id="tipFollow"></span>
						</div>
					</div>
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
						<input type="hidden" id="pkValue" name="pkValue"
							value="<bean:write name="pkValue" scope="request"/>" />
						<input type="hidden" id="disableEle" name="disableEle"
							value="xm-xb-xy-nj-zy-bj" />
						<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
						<input type="hidden" id="getStuInfo" name="getStuInfo"
							value="xm-xb-xymc-nj-zymc-bjmc" />
						<input type="hidden" id="url" name="url" value="/sjcz/wjcflsb.jsp" />
						<input type="hidden" id="clwh" name="clwh"
							value="<bean:write name="clwh"/>" />
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td colspan="5" align="center">
										违纪处分信息维护
									</td>
								</tr>
							</thead>
							<tr>
								<td align="right">
									<font color="red">*</font>学号：
								</td>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
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
								<td rowspan="5" width="15%">
									<img align="center" border="0"
										style="height:133px;width:100px;"
										src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg"
										id="pic"/>
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
									学期：
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
									<font color="red">*</font>处分类别：
								</td>
								<td align="left">
									<html:select name="rs" property="cflb" style="width:90px"
										styleId="cflb">
										<html:option value=""></html:option>
										<html:options collection="cflbList" property="cflbdm"
											labelProperty="cflbmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />
									：
								</td>
								<td align="left">
									<html:text name='rs' property="xymc" styleId="xy" />
								</td>
								<td align="right">
									<font color="red">*</font>处分事由：
								</td>
								<td align="left">
									<html:select name="rs" property="cfyy" style="width:90px"
										styleId="cfyy">
										<html:option value=""></html:option>
										<html:options collection="cfyyList" property="cfyydm"
											labelProperty="cfyymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									专业：
								</td>
								<td align="left">
									<html:text name='rs' property="zymc" styleId="zy" />
								</td>
								<td align="right">
									<font color="red">*</font>处分时间：
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="cfsj" styleId="cfsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('cfsj','y-mm-dd');" />
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
									处分文号：
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="cfwh" styleId="cfwh" />
								</td>
							</tr>
							<tr>
								<td align="right">
									解除时间：
								</td>
								<td align="left">
									<html:text name='rs' property="cxsj" styleId="cxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('cxsj','y-mm-dd');" />
								</td>
								<td align="right">
									解除文号：
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="cxwh" styleId="cxwh" />
								</td>
							</tr>
							<tr>
								<td align="right">
									申诉结果：
								</td>
								<td align="left">
									<html:text name='rs' property="ssjg" styleId="ssjg" />
								</td>
								<td align="right"></td>
								<td colspan="2" align="left">
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									违纪内容：
								</td>
								<td colspan="4">
									<html:textarea name='rs' property='bz' style="width:99%"
										rows='5' />
								</td>
							</tr>
						</table>
						<div class="buttontool" align="center">

						</div>
					</logic:notEmpty>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
