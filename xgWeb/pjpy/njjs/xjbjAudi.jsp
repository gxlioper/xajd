<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/uicomm.js"></script>
		<script language="javascript">
			//��ʼ������������ǰ�������г���һ����˸�λ�����ṩ������ѡ��
			jQuery(function(){
				var shgw = jQuery('#shgw').val();
				var radio = jQuery('input[type="radio"]');
				
				if(radio.length>1 && ''==shgw){
					jQuery('#search_go').click(function(){
						tipsWindown("ϵͳ��ʾ","id:testID","350","150","true","","true","id");
					});
				} else{
					jQuery('#search_go').click(function(){
						allNotEmpThenGo('njjsXjbj.do?method=xjbjAudi&doType=query');
					});
				}
			})
			
			//��������ѡ������˸�λ��Ĳ���
			function searchGo(){
				var shgw = jQuery('input[type=radio]:checked').val();
				jQuery('#shgw').val(shgw);
				allNotEmpThenGo('njjsXjbj.do?method=xjbjAudi&doType=query');
			}
			
			function plsh(){
				tipsWindown("ϵͳ��ʾ","id:shDiv","350","150","true","","true","id");
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/njjsXjbj" method="post">
			<input type="hidden" name="userName" id="userName"value="${userName}" />
			<input type="hidden" name="userType" id="userType"value="${userType}" />
			<input type="hidden" name="userDep" id="userDep"value="${userDep}" />
		
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="realTable" id="realTable" value="xsbxb" />
			<input type="hidden" name="save_xxshsj" id="xxshsj" value="${shsj}" />

			<html:hidden property="shgw" styleId="shgw"/>

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" id="btn_shtg"
									onclick="showAuditingWindow('primarykey_cbv','njjsXjbj.do?method=xjbjDgsh&shgw='+$('shgw').value,700,500)"
									class="btn_sh"> ��� </a>
							</li>
							<li>
								<a href="#" id="btn_shtg"
									onclick="if(isChecked('primarykey_cbv') && confirm('��ȷ��Ҫȡ���������ѡ�ļ�¼��')){refreshForm('njjsXjbj.do?method=xjbjAudi&doType=qxsh')}"
									class="btn_qxsh"> ȡ����� </a>
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
										<%--<label>
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>��������</strong>
										</label>
									--%>
									</div>
									<div class="btn">
										<button class="btn_cx" id="search_go">
											�� ѯ
										</button>
										
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" styleId="zy"
										onchange="initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
						
							<tr>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<html:text property="sqkssj" style="width:80px"
										onclick="return showCalendar('sqkssj','yyyy-MM');"></html:text>
									-
									<html:text property="sqjssj" style="width:80px"
										onclick="return showCalendar('sqjssj','yyyy-MM');"></html:text>
								</td>
								<th>
									${xjbjForm.shgw }
								</th>
								<td>
									<logic:notEmpty name="xjbjForm" property="shgw">
										<html:select property="shzt" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="shjgList" property="en" labelProperty="cn"/>
										</html:select>
									</logic:notEmpty>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align=""
						width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td onclick="tableSort(this)">
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
												<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>
												alt="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"
												value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>" />
										</td>
										<td>
											<logic:iterate id="x" name="s" offset="3" length="1">
												<a
												href="javascript:showTopWin('njjsXjbj.do?method=xjbjDgsh&doType=view&pkValue=<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>',700,500)"
												class="pointer" style="color:#0A63BF"> 
													${x }
												</a>
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="4">
											<td>
												<logic:equal name="v" value="δ���">
													<p>
														<img src="<%=stylePath%>images/ico_dsh.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<logic:equal name="v" value="ͨ��">
													<p>
														<img src="<%=stylePath%>images/ico_shtg.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<logic:equal name="v" value="��ͨ��">
													<p>
														<img src="<%=stylePath%>images/ico_shbtg.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<logic:equal name="v" value="�˻�">
													<p>
														<img src="<%=stylePath%>images/ico_shth.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<logic:equal name="v" value="������">
													<p>
														<img src="<%=stylePath%>images/ico_shxcs.gif" width="52"
															height="18" />
													</p>
												</logic:equal>

												<logic:notEqual name="v" value="�˻�">
													<logic:notEqual name="v" value="������">
														<logic:notEqual name="v" value="��ͨ��">
															<logic:notEqual name="v" value="ͨ��">
																<logic:notEqual name="v" value="δ���">
																	${v }
																</logic:notEqual>
															</logic:notEqual>
														</logic:notEqual>
													</logic:notEqual>
												</logic:notEqual>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
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
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xjbjForm"></jsp:include>
			</div>
			<logic:present name="message">
				<script>
					alert("${message}");
				</script>
			</logic:present>
			
			
			
			
			<div id="testID" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��ѡ����Ҫ��˵ĸ�λ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									��˸�λ
								</th>
								<td>
									<logic:iterate id="g" name="yygw">
										<input type="radio" value="${g }" name="shgw" /> ${g }<br/>
									</logic:iterate>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button name="����" onclick="searchGo()">
											�� ��
										</button>
										<button name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		<div id="shDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>����д�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									���״̬
								</th>
								<td>
									<html:select property="shjg" style="width:120px" styleId="shjg">
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button name="����" onclick="refreshForm('njjsXjbj.do?method=xjbjAudi&doType=plsh&shjg='+$('shjg').value)">
											�� ��
										</button>
										<button name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		<%@ include file="/comm/other/exception.jsp"%>
	</body>
</html>
