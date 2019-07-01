<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/mdqrDWR.js"></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript">
			function modi(url){
					var le=jQuery("input[name=primarykey_cbv]:checked").length;
				if(le>0){
					if(le>1){
						alertInfo('�빴ѡһ�����ݣ�');
						return false;
					}
					var value=jQuery("input[name=primarykey_cbv]:checked").val();
					//showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,500,500);
					showDialog('',500,310,url + '&pkValue='+value);
					return true;
				}else{
					alertInfo('��ѡ��Ҫ�޸ĵ������У�');
					return false;
				}
			}
			
			function saveSfqy(){
					refreshForm('/xgxt/xtwhSysz.do?method=xsxmManage&doType=save');
			}
			
			function changeSfxs(value,num){
				var id = "sfxsArr"+num;
				$(id).value = value;
			}
		</script>
	</head>
	<body>

		<html:form action="/xtwhSysz" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName"
				value="${userName}" />
			<input type="hidden" name="realTable" id="realTable"
				value="mdqr_xmnrb" />
			<input type="hidden" name="tabName" id="tabName" value="mdqr_xmnrb" />
			<input type="hidden" name="viewName" id="viewName"
				value="view_mdqr_xmnrb" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ��ʾ��Ϣ begin-->
<%--			<div id="xxPrompt" class="prompt">--%>
<%--				<h3>--%>
<%--					<span>ϵͳ��ʾ��</span>--%>
<%--				</h3>--%>
<%--				<p>--%>
<%--					��ʾ��Ϣģ�����"��������"��"��ϵ��ʽ"�����֡�"��������"��"��ϵ��ʽ"�ֱ���ʾ��"��������"���--%>
<%--					<br />--%>
<%--					����ʾ5����¼�������5����¼�������ʾ˳����ʾǰ5����¼��"��ϵ��ʽ"������ʾ4����¼�������--%>
<%--					<br />--%>
<%--					4����¼�������ʾ˳����ʾǰ4����¼��--%>
<%--				</p>--%>
<%--				<a class="close" title="����"--%>
<%--					onclick="this.parentNode.style.display='none';"></a>--%>
<%--			</div>--%>
			<!-- ��ʾ��Ϣ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="showDialog('',500,310,'/xgxt/xtwhSysz.do?method=xsxmUpdate&doType=add');"
								class="btn_zj"> ���� </a>
						</li>
						<li>
							<a href="#"
								onclick="modi('/xgxt/xtwhSysz.do?method=xsxmUpdate&doType=update')"
								class="btn_xg"> �޸� </a>
						</li>
						<li>
							<a href="#"
								onclick="dataBatch('/xgxt/xtwhSysz.do?method=xsxmManage&doType=del')"
								class="btn_sc"> ɾ�� </a>
						</li>
						<li>
							<a href="#" onclick="saveSfqy()" class="btn_ccg"> ���� </a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">

						<tbody>
							<tr>
								<th>
									��Ŀ����
								</th>
								<td>
									<html:text property="querylike_xmmc" />
								</td>
<%--								<th>--%>
<%--									��ʾ��ʽ--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<html:select property="queryequals_xsfs" styleId="xsfs"--%>
<%--										style="width:100px">--%>
<%--										<html:option value=""></html:option>--%>
<%--										<html:options collection="xsfsList" property="en"--%>
<%--											labelProperty="cn" />--%>
<%--									</html:select>--%>
<%--								</td>--%>
								<th>
									�Ƿ���ʾ
								</th>
								<td>
									<html:select property="queryequals_sfxs" styleId="sfxs"
										style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="sfxsList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<td colspan="2">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xtwhSysz.do?method=xsxmManage&doType=query')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
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
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<!-- �ٵ�,��ֹ��å��ʽ���ص�һ��CHECKBOX -->
									<input type="checkbox" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
										<td>
											<input type="checkbox" name="primarykey_cbv" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
											<input type="hidden" name="xmdmArr" id="xmdmArr"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1" length="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<td nowrap>
												<logic:iterate name="sfxsList" id="sfxss">
													<logic:equal name="sfxss" property="en" value="${v }">
														<input type="radio" value="${sfxss.en }"
															name="sfxss${index }" checked="checked"
															onclick="changeSfxs('${sfxss.en }','${index }')" />${sfxss.en }
										</logic:equal>
													<logic:notEqual name="sfxss" property="en" value="${v }">
														<input type="radio" value="${sfxss.en }"
															name="sfxss${index }"
															onclick="changeSfxs('${sfxss.en }','${index }')" />${sfxss.en }
										</logic:notEqual>
												</logic:iterate>
												<input type="hidden" name="sfxsArr" id="sfxsArr${index }"
													value="${v }" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="5" length="1">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<%
									ArrayList list = ((ArrayList) request.getAttribute("rs"));
									int rsNum = 0;
									if (list != null) {
										rsNum = list.size();
									}
									int pageSize = (Integer) request.getAttribute("pageSize");
									for (int i = 0; i < (pageSize - rsNum); i++) {
							%>
							<tr>
								<td>
									<input type="checkbox" value="" disabled="true" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xtwhSyszForm"></jsp:include>

				</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
				alertInfo('�����ɹ���');
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
				alertInfo('����ʧ�ܣ�');
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
