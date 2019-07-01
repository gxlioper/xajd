<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function modi(url){
			
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,500,300);
				return true;
			}else{
				alertInfo('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		function view(url){
			
			showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,500,300);

		}
		
		
		</script>
	</head>
	<body>

		<html:form action="/cdtyGfjy" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="realTable" id="realTable"
				value="xg_jxgl_gfjygl" />
			<input type="hidden" name="tableName" id="tableName"
				value="xg_jxgl_gfjygl" />
			<input type="hidden" name="viewName" id="viewName"
				value="xg_jxgl_gfjygl" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="showTopWin('/xgxt/cdtyGfjy.do?method=kcwhAdd',500,300)"
								class="btn_zj"> ���� </a>
						</li>
						<li>
							<a href="#"
								onclick="modi('cdtyGfjy.do?method=kcwhUpdate&doType=update')"
								class="btn_xg"> �޸� </a>
						</li>
						<li>
							<a href="#"
								onclick="dataBatch('/xgxt/cdtyGfjy.do?method=kcwhManage&doType=del')"
								class="btn_sc"> ɾ�� </a>
						</li>
<%--						<li>--%>
<%--							<a href="#" class="btn_dr"--%>
<%--								onclick="impAndChkData();return false;">����</a>--%>
<%--						</li>--%>
<%--						<li>--%>
<%--							<a href="#" class="btn_dc"--%>
<%--								onclick="dataExport();return false;">����</a>--%>
<%--						</li>--%>

					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/cdtyGfjy.do?method=kcwhManage')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
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
									���
								</th>
								<td>
									<html:select property="nd" styleId="nd" style="width:150px">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									�γ�����
								</th>
								<td>
									<html:text property="kcmc" styleId="kcmc" style="width:150px"/>
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							<font color="blue">��ʾ��������ͷ��������;</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td width="5%">
									&nbsp;
									<input type="checkbox" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										>
										<td>
											<div align="center">
											<input type="checkbox" name="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>" />
											</div>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!-- ������ -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- ������ end-->
						</tbody>
					</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=cdtyGfjyForm"></jsp:include>
				<%--					<script type="text/javascript">--%>
				<%--							$('choose').className="hide";--%>
				<%--					</script>--%>


			</div>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
