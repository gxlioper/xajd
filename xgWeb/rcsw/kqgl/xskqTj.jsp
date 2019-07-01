<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
			function setTj(tjlb){
				$('xh').disabled=false;
				$('xm').disabled=false;
				$('zy').disabled=false;
				$('bj').disabled=false;
			
				if (tjlb == "xy"){
					$('xh').disabled=true;
					$('xm').disabled=true;
					$('zy').disabled=true;
					$('bj').disabled=true;
				} else if (tjlb == "zy"){
					$('xh').disabled=true;
					$('xm').disabled=true;
					$('bj').disabled=true;
				} else if (tjlb == "bj"){
					$('xh').disabled=true;
					$('xm').disabled=true;
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');setTj('${tjlb };')">
		<input type="hidden" value="${realTable }" id="realTable" />
		<input type="hidden" value="${tableName }" id="tableName" />
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		<input type="hidden" id="cbVal" name="cbVal" value="" />

		<html:form action="/kqglManage" method="post">
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- �๦�ܲ�����һ -->
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
							   onclick="expData('/xgxt/kqglManage.do?method=xskqTj&doType=expData')"
							   class="btn_dc"> ���� </a>
						</li>
					</ul>
				</div>
			
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/kqglManage.do?method=xskqTj&doType=query')">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
									<html:select property="queryequals_nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="querylike_xh" maxlength="20"
										style="width:175px" styleId="xh"></html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:175px" styleId="xm"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<logic:equal value="xy" name="userType">
										<logic:equal value="false" name="isFdy">
											<html:hidden property="queryequals_xydm" value="${userDep}"/>
										</logic:equal>
									</logic:equal>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="queryequals_xn" style="width:180px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="queryequals_xq" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<html:text property="kqkssj" styleId="kqkssj" style="width:80px"
											onclick="return showCalendar(this.id,'y-MM-dd');"
											readonly="true"
									></html:text>
									-
									<html:text property="kqjssj" styleId="kqjssj" style="width:80px"
											onclick="return showCalendar(this.id,'y-MM-dd');"
											readonly="true" 
									></html:text>
								</td>
							</tr>
							<tr>
								<th>ͳ�Ʒ�ʽ</th>
								<td colspan="5">
									<html:radio property="tjlb" value="stu" onclick="setTj(this.value);">ѧ��</html:radio>
									<html:radio property="tjlb" value="xy" onclick="setTj(this.value);"><bean:message key="lable.xb" /></html:radio>
									<html:radio property="tjlb" value="zy" onclick="setTj(this.value);">רҵ</html:radio>
									<html:radio property="tjlb" value="bj" onclick="setTj(this.value);">�༶</html:radio>
									<logic:notEqual value="xy" name="userType">
										<html:radio property="tjlb" value="all" onclick="setTj(this.value);">ȫУ</html:radio>
									</logic:notEqual>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>

				<div class="con_overlfow">
					<table class="dateline" width="100%" id="rsTable">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate id="r" name="rs">
									<tr>
										<logic:iterate id="v" name="r">
											<td>${v }</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
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
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
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
				<!--��ҳ��ʾ-->
				<logic:notEqual value="nj" name="tjlb">
				<logic:notEqual value="xy" name="tjlb">
				<logic:notEqual value="all" name="tjlb">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=kqglForm"></jsp:include>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alert("${message}");
				$('search_go').click();
			</script>
		</logic:present>
		<script type="text/javascript" defer>
			
		</script>
	</body>
</html>
