<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />

			<logic:equal value="xy" name="userType" scope="session">
				<html:hidden property="queryequals_xydm" value="${userDep }" />
			</logic:equal>


			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/jygl.do?method=xyslqUpdate','650','400')"
									class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/jygl.do?method=xyslqUpdate','update','650','400')"
									class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a href="#"
									onclick="deletedata('/xgxt/jygl.do?method=xyslqManage&doType=del');"
									class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a>
							</li>
							<li>
								<a href="#"
									onclick="expData('/xgxt/jygl.do?method=xyslqManage&doType=expData')"
									class="btn_dc"> ���� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=xyslqManage&doType=query')">
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
									<html:select property="queryequals_nj"
										onchange="initZyList();initBjList()" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<logic:equal value="stu" name="userType" scope="session">
										<html:text property="querylike_xh" maxlength="20"
											style="width:80px" value="${userName }" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
										<html:text property="querylike_xh" maxlength="20"
											style="width:80px"></html:text>
									</logic:notEqual>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:80px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:200px">
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
										style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</logic:notEmpty> 
					</span>
				</h3>

				
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" name="cb" onclick="selectAll()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:notEmpty name="rs">
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)"
											ondblclick="showInfo('/xgxt/jygl.do?method=xyslqUpdate','view','650','400')"
											style="cursor:hand;">
											<td align=center>
												<input type="checkbox" id="cbv" name="primarykey_cbv"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
												<input type="hidden" value="<bean:write name="v" />" />
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td align=center>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
							</tbody>
						</table>
					</div>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
					<!--��ҳ��ʾ-->
				
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("" + $('message').value);
				document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
