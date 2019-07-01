<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/jygl/jygl.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript">
			function plsh(){
				viewTempDiv("毕业生审核","shdiv",380,200);
			}
			
			jQuery(function(){
				var syshen = jQuery('#syshen').val();
				var syshi = jQuery('#syshen').val();
				dwr.engine.setAsync(false);
				if (syshen != ''){
					loadShi('syshen','syshi','syxian');
					jQuery('#syshi').val('${jyglActionForm.sydshi}');
				}
				if (syshi != ''){
					loadXian('syshi','syxian');
					jQuery('#syxian').val('${jyglActionForm.sydxian}');
				}
				dwr.engine.setAsync(true);
			})
			
		</script>
	</head>
	<body
		onload="xyDisabled('xy');hiddenTr($('moreTerm'));purviewControl()">
		<html:form action="/jygl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			

			<logic:equal value="xy" name="userType">
				<logic:equal value="false" name="isFdy">
					<input type="hidden" name="xydm" value="${userDep }" />
				</logic:equal>
			</logic:equal>


			<!-- 大师的权限用的 -->
			<logic:present name="purview">
				<input type="hidden" id="purview" name="purview"
					value="${ purview }" />
				<input type="hidden" id=" operateBound " name="operateBound"
					value="${ operateBound }" />
			</logic:present>

			<logic:equal name="isByssh" value="false">
				<div class="prompt" id="div_help">
					<h3>
						<span>提示：</span>
					</h3>
					<p>
						<font color="red"> 现在不是毕业生信息审核时间,请于${cssz.bysshkssj
							}日&nbsp;至&nbsp;${cssz.bysshjssj }日内审核。</font>
					</p>
					<a class="close" title="隐藏"
						onclick="this.parentNode.style.display='none'"></a>
				</div>
			</logic:equal>

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<logic:equal name="isByssh" value="true">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" id="btn_sh"
										onclick="showAuditingWindow('primarykey_cbv','/xgxt/jygl.do?method=bysUpdate&doType=sh',900,580);"
										class="btn_sh">审核 </a>
								</li>
								<li>
									<a href="#" id="btn_qxsh"
										onclick="if(isChecked('primarykey_cbv') && confirm('您确定要取消审核所勾选的记录吗？')){refreshForm('/xgxt/jygl.do?method=byssh&doType=qxsh')}"
										class="btn_qxsh">取消审核 </a>
								</li>
							</ul>
						</div>
					</logic:equal>
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
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=bysshcx&doType=query')">
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
									届
								</th>
								<td>
									<html:select property="je" styleId="je"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="nfList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" maxlength="20"
										style="width:175px"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" maxlength="20"
										style="width:175px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<logic:notEqual value="10388" name="xxdm" scope="session">
								<tr style="display:none">
									<th>
										学历层次
									</th>
									<td>
										<html:select property="xlccdm" style="width:180px">
											<html:options collection="xlList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										培养方式
									</th>
									<td>
										<html:select property="pyfsdm" style="width:180px">
											<html:options collection="pyfsList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										招生类别
									</th>
									<td>
										<html:select property="zslbdm" style="width:180px">
											<html:options collection="zsList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr style="display:none">
									<th>
										生源地(省)
									</th>
									<td>
										<html:select property="sydshen" styleId="syshen"
											onchange="loadShi('syshen','syshi','syxian')"
											style="width:180px">
											<html:options collection="sydqList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										生源地(市)
									</th>
									<td>
										<html:select property="sydshi" styleId="syshi"
											onchange="loadXian('syshi','syxian')" style="width:180px">
											<html:option value="">----请选择-----</html:option>
										</html:select>
									</td>
									<th>
										生源地(县)
									</th>
									<td>
										<html:select property="sydxian" styleId="syxian"
											style="width:180px">
											<html:option value="">----请选择-----</html:option>
										</html:select>
									</td>
								</tr>
								<tr style="display:none">
									<th>
										是否在职
									</th>
									<td>
										<html:select property="sfzz" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										是否师范
									</th>
									<td>
										<html:select property="sfsf" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										是否独立
									</th>
									<td>
										<html:select property="sfdl" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr style="display:none">
									<th>
										是否确认
									</th>
									<td>
										<html:select property="sfqr" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										是否修改
									</th>
									<td>
										<html:select property="sfxg" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>生源地是否变动</th>
									<td>
										<html:select property="sybdzt" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr style="display:none">
									<th>
										<bean:message key="lable.xb" />审核
									</th>
									<td>
										<html:select property="xyshzt" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										学校审核
									</th>
									<td>
										<html:select property="shzt" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th></th>
									<td></td>
								</tr>
							</logic:notEqual>
							<!-- 福建工程 -->
							<logic:equal value="10388" name="xxdm" scope="session">
								<tr style="display:none">
									<th>
										离校待考
									</th>
									<td>
										<html:text property="lxdk" maxlength="25"
											style="width:175px"></html:text>
									</td>
									<th>
										是否学生干部
									</th>
									<td>
										<html:text property="xsgb" maxlength="25"
											style="width:175px"></html:text>
									</td>
									<th>
										学历层次
									</th>
									<td>
										<html:select property="xlccdm" style="width:180px">
											<html:options collection="xlList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr style="display:none">
									<th>
										毕业年份
									</th>
									<td>
										<html:select property="bynf" styleId="bynf"
											style="width:180px">
											<html:option value=""></html:option>
											<html:option value="未毕业">未毕业</html:option>
											<html:options collection="nfList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										是否结业生
									</th>
									<td>
										<html:select property="isjys" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="isNot" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										学校审核
									</th>
									<td>
										<html:select property="shzt" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
							</logic:equal>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align=""
						width="100%">
						
							<logic:notEmpty name="rs">
								<logic:equal value="13275" name="xxdm">
									<thead>
										<tr>
											<td>
												<input type="checkbox" disabled="true" />
											</td>
											<logic:iterate id="tit" name="topTr" offset="3" scope="request">
												<td onclick="tableSort(this)">
													${tit}
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s">
										<tr style="color:
											<logic:iterate id="v" name="s" offset="0" length="1">
												<logic:equal name="v" value="是">
													#004bd5
												</logic:equal>	
											</logic:iterate>
										">
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv"
													alt="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"
													<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>
													value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
											</td>
											<td>
												<a
													href="javascript:showTopWin('/xgxt/jygl.do?method=bysUpdate&doType=view&pk=<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>','900','600');"
													class="pointer" style="color:#0A63BF"> <logic:iterate
														id="v" name="s" offset="3" length="1">${v } </logic:iterate>
												</a>
											</td>
											<logic:iterate id="v" name="s" offset="4">
												<td>
													${v }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:equal>
								<logic:notEqual value="13275" name="xxdm">
								<thead>
									<tr>
										<td>
											<input type="checkbox" disabled="true" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2" scope="request">
											<td onclick="tableSort(this)">
												${tit}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr>
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv"
													alt="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"
													<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
													value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
											</td>
											<td>
												<a
													href="javascript:showTopWin('/xgxt/jygl.do?method=bysUpdate&doType=view&pk=<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>','900','600');"
													class="pointer" style="color:#0A63BF"> <logic:iterate
														id="v" name="s" offset="2" length="1">${v } </logic:iterate>
												</a>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td>
													${v }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:notEqual>
							
								
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="2" scope="request">
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
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="2" scope="request">
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
								审核意见
								<br />
								<font color="red"><限500字>
								</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_shyj" onblur="checkLen(this,500)"
									style="width:90%" rows="5"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								审核人
							</th>
							<td>
								${userNameReal }
							</td>
							<th>
								审核时间
							</th>
							<td>
								${now }
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan='4'>
								<div class='btn'>
									<button
										onclick="if (confirm('您确定要审核所勾选的记录吗？')){refreshForm('/xgxt/jygl.do?method=byssh&save_shzt=通过&doType=sh')}">
										通过
									</button>
									<button
										onclick="if (confirm('您确定要审核所勾选的记录吗？')){refreshForm('/xgxt/jygl.do?method=byssh&save_shzt=不通过&doType=sh')}">
										不通过
									</button>
									<button
										onclick="if (confirm('您确定要退回所勾选的记录吗？')){refreshForm('/xgxt/jygl.do?method=byssh&save_shzt=退回&doType=sh')}">
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
			<script defer="defer">
	       	 	 alert("${message}");
	         </script>
		</logic:present>
	</body>
</html>
