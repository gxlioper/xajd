<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
	<script language="javascript" src="js/pjpy/pjpy_whlgdx.js"></script>	
</head>
	<body onload="checkWinType(); initMode()">				
		<html:form action="/chgPriseBat" method="post">
			<logic:present name="succ">
				<logic:equal value="OK" name="succ">
					<script language="javascript">
						alert('保存成功！');
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
					</script>
				</logic:equal>
				<logic:equal value="NO" name="succ">
					<script language="javascript">
						alert('保存失败！');
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
					</script>
				</logic:equal>
			</logic:present>
			<logic:notPresent name="succ">
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>评奖评优 - 参数设置 - 综合素质测评自定义字段维护</a>
					</p>
				</div>

				<div class="open_win">
					<table class="formlist" width="100%">
						<thead>
							<tr>
								<th colspan="4">
									<span>奖学金参数批量设置</span>
								</th>
							</tr>
						</thead>
						<tbody>
								<tr>
									<th align="right" width="35%">
										学年
									</th>
									<td align="left">
										<html:text name="commanForm" property="xn" styleId="xn" style="color:#666666" readonly="true" />
									</td>
								</tr>
								<tr>
									<th align="right">
										年度
									</th>
									<td align="left">
										<html:text name="commanForm" property="nd" styleId="nd"
											style="color:#666666" readonly="true" />
									</td>
								</tr>
								<tr>
									<th align="right">
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td align="left">
										<html:hidden name="commanForm" property="xydm" styleId="xy" />
										<input type="text" name="xymc" id="xymc" style="color:#666666"
											readonly value="<bean:write name="xymc" scope="request"/>" />
									</td>
								</tr>
								<tr>
									<th align="right">
										专业
									</th>
									<td align="left">
										<html:hidden name="commanForm" property="zydm" styleId="zy" />
										<input type="text" name="zymc" id="zymc" style="color:#666666"
											readonly value="<bean:write name="zymc" scope="request"/>" />
									</td>
								</tr>
								<tr>
									<th align="right">
										班级
									</th>
									<td align="left">
										<html:hidden name="commanForm" property="bjdm" styleId="bj" />
										<input type="text" name="bjmc" id="bjmc" style="color:#666666"
											readonly value="<bean:write name="bjmc" scope="request"/>" />
									</td>
								</tr>
								<tr>
									<th align="right">
										奖学金
									</th>
									<td align="left">
										<html:hidden name="commanForm" property="xmdm" styleId="jxj" />
										<input type="text" name="jxjmc" id="jxjmc" style="color:#666666"
											readonly value="<bean:write name="jxjmc"/>" />
									</td>
								</tr>
								<logic:equal value="yes" name="showAHJG">
								<tr>
									<th align="right">
										平均分设置
									</th>
									<td>
										<input type="text" id="pjf" name="pjf" value="${pjf }"
										onblur="ckdata(this)" />
									</td>
									</tr>
								</logic:equal>
								<%--武汉理工大学--%>
								<logic:equal value="10497" name="xxdm">
								<tr>
									<th align="right">
										设定方式
									</th>
									<td align="left">
										<select id="sdfs" onchange="initItem()" name="sdfs" >
										<option value="人数">人数</option>
										<option value="人数比例">人数比例</option>
										</select>
									</td>
								</tr>
								<tr style="display:none" id="jyrsbl">
									<th align="right">
										建议人数比例
									</th>
									<td align="left">
										<input type="text" style="width:60px" name="jyrs" id="jyrs"
											value="<bean:write name="jxjbl"/>"
											onkeypress="return numInputValue(this,5,event)" 
											maxlength="5" />
										<font color="red">% （请输入 0 ～ 100 之间的整数）</font>
									</td>
								</tr>
								<tr style="display:" id="rs">
									<th align="right">
										人数
									</th>
									<td align="left">
										<input type="text" style="width:60px" name="jxjrs" id="jxjrs"
										value="<bean:write name="jxjrs"/>"
										onkeypress="return numInputValue(this,5,event)"
											value="<bean:write name="jxjbl"/>" maxlength="5" />人
									</td>
								</tr>
								</logic:equal>
								<%--武汉理工大学end--%>
								<logic:notEqual value="10497" name="xxdm">
								<tr>
									<th align="right">
										建议人数比例
									</th>
									<td align="left">
										<div class="pos" style="z-index:2">
											<input type="text" name="jyrs" id="jyrs" value="<bean:write name="jxjbl"/>"
												class="text_nor" onkeypress="return numInputValue(this,5,event)" maxlength="5"
												onfocus="displayMsgDiv()" onblur="hideMsgDiv()"/>										
											<font color="red">%</font>
											<div id="msg_div" class="hide">
								              <div class="prompcon">
								                <p>请输入 0~100 之间的整数</p>
								              </div>
											</div>										
										</div>
									</td>
								</tr>
								</logic:notEqual>
								<logic:notEqual value="北京林业大学" name="xxmc" scope="session">
			<%--					<thead>--%>
			<%--						<tr>--%>
			<%--							<td height="25" align="center" colspan="2">--%>
			<%--								时间设置--%>
			<%--							</td>--%>
			<%--						</tr>--%>
			<%--					</thead>--%>
			<%--					--%>
			<%--					<tr>--%>
			<%--						<td align="right">--%>
			<%--							申请开始时间：--%>
			<%--						</td>--%>
			<%--						<td align="left">--%>
			<%--							<input type="hidden" name="xssqkssj" id="xssqkssj" value="" />--%>
			<%--							<input type="text" readonly style="cursor:hand;width:80px"--%>
			<%--								onclick="return showCalendar('xssqkssj1','y-mm-dd');"--%>
			<%--								value="<bean:write name="xssqkssj1" />" name="xssqkssj1"--%>
			<%--								id="xssqkssj1" />--%>
			<%--							－--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="xssqkssj2" />"--%>
			<%--								name="xssqkssj2" id="xssqkssj2" />--%>
			<%--							：--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="xssqkssj3" />"--%>
			<%--								name="xssqkssj3" id="xssqkssj3" />--%>
			<%--							：--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="xssqkssj4" />"--%>
			<%--								name="xssqkssj4" id="xssqkssj4" />--%>
			<%--						</td>--%>
			<%--					</tr>--%>
			<%--					<tr>--%>
			<%--						<td align="right">--%>
			<%--							申请截至时间：--%>
			<%--						</td>--%>
			<%--						<td align="left">--%>
			<%--							<input type="hidden" name="xssqjssj" id="xssqjssj" value="" />--%>
			<%--							<input type="text" readonly style="cursor:hand;width:80px"--%>
			<%--								onclick="return showCalendar('xssqjssj1','y-mm-dd');"--%>
			<%--								value="<bean:write name="xssqjssj1" />" name="xssqjssj1"--%>
			<%--								id="xssqjssj1" />--%>
			<%--							－--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="xssqjssj2" />"--%>
			<%--								name="xssqjssj2" id="xssqjssj2" />--%>
			<%--							：--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="xssqjssj3" />"--%>
			<%--								name="xssqjssj3" id="xssqjssj3" />--%>
			<%--							：--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="xssqjssj4" />"--%>
			<%--								name="xssqjssj4" id="xssqjssj4" />--%>
			<%--						</td>--%>
			<%--					</tr>--%>
			<%--					<tr>--%>
			<%--						<td align="right">--%>
			<%--							审核开始时间：--%>
			<%--						</td>--%>
			<%--						<td align="left">--%>
			<%--							<input type="hidden" name="shkssj" id="shkssj" value="" />--%>
			<%--							<input type="text" readonly style="cursor:hand;width:80px"--%>
			<%--								onclick="return showCalendar('shkssj1','y-mm-dd');"--%>
			<%--								value="<bean:write name="shkssj1" />" name="shkssj1"--%>
			<%--								id="shkssj1" />--%>
			<%--							－--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="shkssj2" />"--%>
			<%--								name="shkssj2" id="shkssj2" />--%>
			<%--							：--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="shkssj3" />"--%>
			<%--								name="shkssj3" id="shkssj3" />--%>
			<%--							：--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="shkssj4" />"--%>
			<%--								name="shkssj4" id="shkssj4" />--%>
			<%--						</td>--%>
			<%--					</tr>--%>
			<%--					<tr>--%>
			<%--						<td align="right">--%>
			<%--							审核截至时间：--%>
			<%--						</td>--%>
			<%--						<td align="left">--%>
			<%--							<input type="hidden" name="shjzdj" id="shjzdj" value="" />--%>
			<%--							<input type="text" readonly style="cursor:hand;width:80px"--%>
			<%--								onclick="return showCalendar('shjzdj1','y-mm-dd');"--%>
			<%--								value="<bean:write name="shjzdj1" />" name="shjzdj1"--%>
			<%--								id="shjzdj1" />--%>
			<%--							－--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="shjzdj2" />"--%>
			<%--								name="shjzdj2" id="shjzdj2" />--%>
			<%--							：--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="shjzdj3" />"--%>
			<%--								name="shjzdj3" id="shjzdj3" />--%>
			<%--							：--%>
			<%--							<input type="text" onkeypress="return numInputValue(this,2,event)"--%>
			<%--								style="width:20px" value="<bean:write name="shjzdj4" />"--%>
			<%--								name="shjzdj4" id="shjzdj4" />--%>
			<%--						</td>--%>
			<%--					</tr>--%>
								</logic:notEqual>
								</tbody>
								<tfoot>
							 <tr>
					        <td colspan="4">
					        
					          <div class="btn">
					            <logic:equal value="10497" name="xxdm">
							<button type="button" class=""
									onclick="timeSave()">
									保存
								</button>
							</logic:equal>
							<logic:notEqual value="10497" name="xxdm">
							<button type="button" class=""
									onclick="if($('jyrs').value > 100 || $('jyrs').value == ''){alert('建议人数比例不符合标准！');return false}else{if (confirm('确定要保存吗？')){document.forms[0].submit();return true;}return false;}">
									保存
								</button>
							</logic:notEqual>	
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="" onclick="Close();return false;">
									关闭
								</button>
					          </div>
					        </td>
					      </tr>
						</tfoot>
					</table>
					</div>
			</logic:notPresent>
		</html:form>
	</body>
</html>
