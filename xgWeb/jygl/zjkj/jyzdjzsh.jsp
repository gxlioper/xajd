<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jygl" method="post">
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



			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/jygl.do?method=jyzdjzsb','sh','800','550')"
									class="btn_sh"> �������</a>
							</li>
							<li>
								<a href="#"
									onclick="shformdata('/xgxt/jygl.do?method=jyzdjzsh&shjg=ͨ��&doType=sh');"
									class="btn_shtg"> ����ͨ��</a>
							</li>
							<li>
								<a href="#"
									onclick="shformdata('/xgxt/jygl.do?method=jyzdjzsh&shjg=��ͨ��&doType=sh')"
									class="btn_shbtg">������ͨ��</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jyzdjzsh&doType=query')">
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
								<th>��������</th>
								<td>
									<html:select property="queryequals_bmdm" style="width:160px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
								</td>
								<th>ʱ��</th>
								<td colspan="3">
									<html:text property="querygreaterequal_sj"
											   styleId="querygreaterequal_sj"
											   onblur="dateFormatChg(this)"
										       onclick="return showCalendar(this.id,'y-mm-dd');"
									></html:text>
									-
									<html:text property="querylessequal_sj"
											   styleId="querylessequal_sj"
											   onblur="dateFormatChg(this)"
										       onclick="return showCalendar(this.id,'y-mm-dd');"
									></html:text>
								</td>
							</tr>
							<tr>
								<th>��������</th>
								<td><html:text property="querylike_jzzd" style="width:155px"></html:text></td>
								<th>������</th>
								<td><html:text property="querylike_zcr" style="width:155px"></html:text></td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>�������</th>
								<td><html:text property="querylike_mxdx" style="width:155px"></html:text></td>
								<th>���״̬</th>
								<td>
									<html:select property="queryequals_shzt" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>


			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
									
									</td>
									<logic:iterate id="tit" name="topTr" offset="2" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="showInfo('/xgxt/jygl.do?method=jyzdjzsb','view','730','530')"
										class="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
										<td align=center>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"
												/>
											<input type="hidden" value="<bean:write name="v" />" />
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         		alert("${message}");
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 	alert("${message}");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
