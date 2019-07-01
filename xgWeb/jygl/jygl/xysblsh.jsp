<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/jygl/jygl.js"></script>
		<script type="text/javascript">
			function bysblPlsh(shjg){
				var shjb = $('shjb').value;
				var userType = $('userType').value;
				
				if (!isChecked("primarykey_cbv")){
					return false;
				}
				
				if (array.length > $('sybh').value && '通过'==shjg){
					alert("就业协议书编号不够分配，请先维护协议书编号!");
					return false;
				}
				
				refreshForm('/xgxt/jygl.do?method=xysblsh&doType=sh&shjg='+shjg);
			}
			
			function plsh(){
				viewTempDiv("协议书补领审核","shdiv",380,200);
			}
			
		</script>
	</head>
	<body onload="xyDisabled('xy');hiddenTr($('moreTerm'));purviewControl();">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="shjb" value="${cssz.xysbbshjb }"/>
			<input type="hidden" id=sybh value="${sybh }"/>

			<!-- 大师的权限用的 -->
			<logic:present name="purview">
				<input type="hidden" id="purview" name="purview" value="${ purview }" /> 
				<input type="hidden" id=" operateBound " name="operateBound" value="${ operateBound }" />
			</logic:present>

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									id="btn_sh"
									onclick="showAuditingWindow('primarykey_cbv','/xgxt/jygl.do?method=xysblsq&doType=sh',700,500);"
									class="btn_sh">审核 </a>
							</li>
							<li>
								<a href="#"
									id="btn_qxsh"
									onclick="if(isChecked('primarykey_cbv') && confirm('您确定要取消审核所勾选的记录吗？')){refreshForm('/xgxt/jygl.do?method=xysblsh&doType=qxsh')}"
									class="btn_qxsh">取消审核 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<label>
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>更多条件</strong>
										</label>
									</div>
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=xysblsh&doType=query')">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="queryequals_nj" style="width:160px"
										onchange="initZyList();initBjList()" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="querylike_xh" maxlength="20"
										style="width:155px"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:155px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									原就业
									<br />
									协议编号
								</th>
								<td>
									<html:text property="querylike_jyxybh" style="width:155px"></html:text>
								</td>
								<th>
									新就业
									<br />
									协议编号
								</th>
								<td>
									<html:text property="querylike_newjyxybh" style="width:155px"></html:text>
								</td>
								<th>
									补办类别
								</th>
								<td>
									<html:select property="queryequals_bblbdm" style="width:160px">
										<html:options collection="bblbList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<logic:equal value="2" name="cssz" property="xysbbshjb">
									<th>
										<bean:message key="lable.xsgzyxpzxy" />审核
									</th>
									<td>
										<html:select property="queryequals_xysh" style="width:160px">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />审核人
									</th>
									<td>
										<html:text property="querylike_xyshr" style="width:155px"></html:text>
									</td>
								</logic:equal>
								<th>
									学校审核
								</th>
								<td>
									<html:select property="queryequals_xxsh" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<logic:equal value="1" name="cssz" property="xysbbshjb">
									<th>
										学校审核人
									</th>
									<td>
										<html:text property="querylike_xxshr" style="width:155px"></html:text>
									</td>
									<th>
										届
									</th>
									<td>
										<html:select property="queryequals_je" styleId="je"
											style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="nfList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:equal>
							</tr>
							<logic:equal value="2" name="cssz" property="xysbbshjb">
							<tr style="display:none">
								<th>
									学校审核人
								</th>
								<td>
									<html:text property="querylike_xxshr" style="width:155px"></html:text>
								</td>
								<th>
									届
								</th>
								<td>
									<html:select property="queryequals_je" styleId="je"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="nfList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th></th>
								<td></td>
							</tr>
							</logic:equal>
						</tbody>
					</table>
				</div>
			</div>


			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序,剩余就业协议编号<font color="red">${sybh }</font>个</font>
						</logic:notEmpty>
					</span>
				</h3>

				
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" name="cb" onclick="selectAll()" disabled="disabled"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="3" scope="request">
										<td onclick="tableSort(this)" nowrap>
											${tit}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:notEmpty name="rs">
									<logic:iterate name="rs" id="s">
										<tr>
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv"
													value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"
													alt="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"
													<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate> />
											</td>
											<td>
												<a
													href="javascript:showTopWin('/xgxt/jygl.do?method=xysblsq&doType=view&pkValue=<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>','700','500');"
													class="pointer" style="color:#0A63BF"> 
													<logic:iterate id="v" name="s" offset="3" length="1">${v }</logic:iterate>
												</a>
											</td>
											<logic:iterate id="v" name="s" offset="4" >
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									<%
										for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") - ((List)request.getAttribute("rs")).size() ; i++){
										
									 %>
										<tr>
											<td>
												<input type="checkbox" disabled="disabled"/>
											</td>
											<logic:iterate id="t" name="topTr" offset="3" scope="request">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>
									<%
										}
									 %>
								</logic:notEmpty>
								<logic:empty name="rs">
									<%
										for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") ; i++){
									
									 %>
										<tr>
											<td>
												<input type="checkbox" disabled="disabled"/>
											</td>
											<logic:iterate id="t" name="topTr" offset="3" scope="request">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>
									<%
										}
									 %>
								</logic:empty>
							</tbody>
						</table>
					</div>
					<!--分页显示-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
					<!--分页显示-->
			</div>
			
			<div class="open_win01" style="display:none;" id="shdiv">
					<table width='80%' class='formlist'>
						<tbody>
							<tr>
								<th>
					      			审核意见<br/>
					      			<font color="red"><限500字></font>
					      		</th>
					      		<td colspan="3">
					      			<html:textarea property="save_shyj"  onblur="checkLen(this,500)" style="width:90%" rows="5"></html:textarea>
					      		</td>
							</tr>
							<tr>
					      		<th>审核人</th>
					      		<td>
					      			${userNameReal }
					      		</td>
					      		<th>审核时间</th>
					      		<td>
					      			${now }
					      		</td>
					      	</tr>
						<tbody>
						<tfoot>
							<tr>
								<td colspan='4'>
									<div class='btn'>
										<button onclick="if (confirm('您确定要审核所勾选的记录吗？')){bysblPlsh('通过')}">
											通过
										</button>
										<button onclick="if (confirm('您确定要审核所勾选的记录吗？')){bysblPlsh('不通过')}">
											不通过
										</button>
										<button onclick="if (confirm('您确定要退回所勾选的记录吗？')){bysblPlsh('退回')}">
											退回
										</button>
										<button onclick="hiddenMessage(true,true);return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			
		</html:form>
		<logic:present name="message">
			<script language="javascript">
	        	alert("${message}");
	        </script>
		</logic:present>
	</body>
</html>
