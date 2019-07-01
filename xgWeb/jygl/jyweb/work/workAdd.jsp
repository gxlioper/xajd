<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function workAdd(){
				if ("" == $('gsmc').value){
					alert("����ѡ��λ!");
				} else {
					showTopWin('jywebUseCheckSession.do?method=gwwh','800','600');
				}
			}
		
		</script>
	</head>
	<body>
		<html:form action="/jyweb.do" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }" />
			<input type="button" style="display:none" onclick="window.refresh(document.URL)" id="search_go"/>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ƹ��λ����</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="if(window.dialogArguments){window.close();dialogArgumentsQueryChick();}">�ر�</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								��λ����
							</th>
							<td width="34%">
								<html:text property="save_gsmc" value="${companyInfo.dwmc}" styleId="gsmc" readonly="true"></html:text>
								<button onclick="showTopWin('/xgxt/jyweb.do?method=moreCompany',750,550);" class="btn_01">
									ѡ��
								</button>
							</td>
							<th width="16%">
								��λ��ַ
							</th>
							<td width="34%">
								${companyInfo.dz }
							</td>
						</tr>
						<tr>
							<th>
								��λ����
							</th>
							<td>
								${companyInfo.dwxzmc }
							</td>
							<th>
								��ҵ����
							</th>
							<td>
								${companyInfo.hyflmc }
							</td>
						</tr>
						<tr>
							<th>
								�� ��
							</th>
							<td>
								${companyInfo.cz }
							</td>
							<th>
								�� ַ
							</th>
							<td>
								${companyInfo.wz }
							</td>
						</tr>
						<tr>
							<th>
								��λ���
							</th>
							<td colspan="3" style="word-break:break-all;">
								${companyInfo.dwjj }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>��λ��Ϣ</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div class="toolbox">
									<div class="buttonbox" align="right">
										<ul>
											<li>
												<a
													href="javascript:workAdd();"
													class="btn_zj">����</a>
											</li>
											<li>
												<a href="#"
													onclick="showInfo('jywebUseCheckSession.do?method=workUpdate&doType=update','update','800','580')"
													class="btn_xg"> �޸� </a>
											</li>
											<li>
												<a
													href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=workAdd&doType=del','del')"
													class="btn_sc"> ɾ�� </a>
											</li>
										</ul>
									</div>
								</div>
							
							
								<logic:notEmpty name="rs">
									<table summary="" class="dateline" width="100%">
										<thead>
											<tr>
												<td>
												</td>
												<logic:iterate id="tit" name="topTr" offset="1" scope="request">
													<td id="${tit.en}" onclick="tableSort(this)">
														${tit.cn}
													</td>
												</logic:iterate>
											</tr>
										</thead>
										<tbody>
											<logic:iterate name="rs" id="s">
												<tr onclick="rowOnClick(this)">
													<td>
														<input type="checkbox" id="cbv" name="primarykey_cbv"
															value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
														<input type="hidden" value="${v }" />
													</td>
													<logic:iterate id="v" name="s" offset="1">
														<td>
															${v }
														</td>
													</logic:iterate>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
									<!--��ҳ��ʾ-->
									<jsp:include flush="true"
										page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
									<!--��ҳ��ʾ-->
								</logic:notEmpty>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<logic:present name="message">
				<script>
		 			alert("${message}");
		 		</script>
			</logic:present>
		</html:form>
	</body>
</html>
