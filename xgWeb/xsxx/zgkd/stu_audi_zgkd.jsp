<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script>
			function batchOper(type){
				var RowsStr="!!";		
				for (i=0; i<document.getElementsByName("cbv").length; i++){
			    	if(document.getElementsByName("cbv")[i].checked){
			    		RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
			    	}
				}
				if (RowsStr=="!!"){
					alert("��ѡ��Ҫ���������ļ�¼��");
					return false;
				}else{
					refreshForm('xsxx_zgkd.do?method=saveAutiBatch&yesNo=' + type);
				}
			}
			
			/**ȫѡ*/
		function selectAll(){
			var checkBoxArr = document.getElementsByName("cbv");
			var selall = document.getElementById('cb');
			if(selall.checked==true){
				for(var i=0;i<checkBoxArr.length;i++){
					checkBoxArr[i].checked = true;
				}
			} else {
				for(var i=0;i<checkBoxArr.length;i++){
					checkBoxArr[i].checked = false;
				}
			}
		}
		
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('xsxx_zgkd.do?method=stuModiAuditing');
		}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/xsxx_zgkd.do" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" name="realTable" id="realTable"
				value="${realTable}" />
			<input type="hidden" name="tableName" id="tableName"
				value="${tableName}" />
			<input type="hidden" name="userName" id="userName"
				value="${userName}" />
			<input type="hidden" name="fdyQx" id="fdyQx" value="${fdyQx}" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />

			<!-- �߼���ѯ ���� -->
			<input type="hidden" name="userDep" id="userDep" value="${userDep }" />
			<input type="hidden" id="path" name="searchModel.path"
				value="stu_audi_zgkd.jsp" />

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!--��дȨ-->
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" onclick="batchOper('ͨ��')" class="btn_shtg">����ͨ��</a>
							</li>
							<li>
								<a href="#" onclick="batchOper('��ͨ��')" class="btn_shbtg">������ͨ��</a>
							</li>
						</logic:equal>
					</ul>
				</div>

				<!-- new �汾 -->
				<logic:equal name="superSearch" value="yes">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</logic:equal>

				<!-- old �汾 -->
				<logic:equal name="superSearch" value="no">
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<input type="hidden" name="go" value="a" />
											<button type="button" class="button2" id="search_go"
												onclick="allNotEmpThenGo('xsxx_zgkd.do?method=stuModiAuditing');">
												��ѯ
											</button>
											<button type="button" id="cz" onclick="czSearchCond('nj-xh-xm-xy-zy-bj');">
												����
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
										<html:select property="nj" styleId="nj" style="width:80px">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:text property="xh" styleId="xh" style="width:150px" />
									</td>
									<th>
										����
									</th>
									<td>
										<html:text property="xm" styleId="xm" style="width:150px" />
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<logic:notEqual value="true" name="fdyQx">
											<logic:equal value="xy" name="userType">
												<html:hidden property="xydm" value="${userDep}" />
											</logic:equal>
										</logic:notEqual>
										<html:select property="xydm" style="width:150px" styleId="xy"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										רҵ
									</th>
									<td>
										<html:select property="zydm" style="width:150px" styleId="zy"
											styleClass="select" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										�༶
									</th>
									<td>
										<html:select property="bjdm" style="width:150px" styleId="bj"
											styleClass="select">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</logic:equal>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ�����Խ��е�����ˣ�������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>

				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" width="100%"
						id="rsTable">
						<thead>
							<tr>
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<%
							int pageSize = (Integer) request.getAttribute("pageSize");
							%>
							<logic:empty name="rs">
								<%
								for (int i = 0; i < pageSize; i++) {
								%>
								<tr>
									<td align="center">
										<input type="checkbox" name="pk" value="" disabled="disabled"></input>
									</td>
									<logic:iterate id="tit" name="topTr">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>

								<%
								}
								%>
							</logic:empty>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand;" bgcolor=""
										ondblclick="showTopWin('xsxx_zgkd.do?method=showStuCheck&xh=' + curr_row.cells[1].innerText,870,600)">
										<td>
											<input type="hidden"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<input type="checkbox" id="cbv" name="cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="0" length="7">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										
										<!-- �����Ϣͼ����ʾ begin -->
										<logic:iterate id="v" name="s" offset="7" length="1">
											<td align="left" nowrap="nowrap">
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
											</td>
										</logic:iterate>
										<!-- �����Ϣͼ����ʾ end -->
									</tr>
								</logic:iterate>
								<logic:lessEqual value="${pageSize}" name="rsNum">
									<%
											int rsNum = ((Integer) request.getAttribute("rsNum"));
											for (int i = 0; i < (pageSize - rsNum); i++) {
									%>
									<tr>
										<td align="center">
											<input type="checkbox" name="pk" value="" disabled="disabled"></input>
										</td>
										<logic:iterate id="tit" name="topTr">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
									</tr>
									<%
									}
									%>
								</logic:lessEqual>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=zdwhqxfpForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<logic:present name="result">
				<script>
					alert('�����ɹ�!');
					document.getElementById('search_go').click();
				</script>
			</logic:present>
		</html:form>

	</body>
</html>
