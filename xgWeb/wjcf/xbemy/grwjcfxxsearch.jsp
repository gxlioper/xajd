<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script>
			function cfqr(obj) {
				if (confirm('ȷ��Ҫ�Ըô�����Ϣ����ȷ����,ȷ�Ϻ��ܽ��г�������������ϸ�˶ԣ�')) {
					var pk = obj.parentNode.parentNode.getElementsByTagName("td")[0].getElementsByTagName("input")[0].value;
					refreshForm("grwjcfxxsearch.do?act=save&pk="+pk);
				}
			}
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - �����걨���� - ѧ�����ֲ�ѯ</a>
			</p>
		</div>

		<html:form action="/grwjcfxxsearch.do" method="post">
			<button type="button" style="display:none" id="search_go" onclick="refreshForm('grwjcfxxsearch.do')" ></button>
			<div class="tab">
			
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<td align="left">
								ѧ�ţ� ${userName }
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								������ ${userNameReal }
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								��ʷΥ�ʹ���: ${num }
							</td>
						</tr>
					</thead>
				</table>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span>Υ�ʹ�����Ϣ&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue"> ��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��</font>
						</logic:notEmpty>
						 </span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="left" style="cursor:hand">
								<!-- ���ݴ�ѧ���������ͷ -->
								<logic:equal value="11078" name="xxdm">
									<logic:iterate id="tit" name="topTr" offset="1" length="8">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td onclick="tableSort(this)" nowrap>
										����
									</td>
								</logic:equal>
								<!-- ����ѧУ -->
								<logic:notEqual value="11078" name="xxdm">
									<logic:iterate id="tit" name="topTr" offset="1" length="8">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
<%--									<td>--%>
<%--										ѧ��ȷ��--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										ȷ��ʱ��--%>
<%--									</td>--%>
<%--									<td onclick="tableSort(this)" nowrap>--%>
<%--										����--%>
<%--									</td>--%>
								</logic:notEqual>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr align="left" onclick="rowOnClick(this);"
									style="cursor:hand"
									ondblclick="showTopWin('grwjcfxxview.do?pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500)">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<!-- ���ݴ�ѧ����������� ���� -->
									<logic:equal value="11078" name="xxdm">
										<logic:iterate id="v" name="s" offset="2" length="7">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</logic:equal>
									<!-- ������ѧ����������� ���� -->
									<logic:notEqual value="11078" name="xxdm">
										<logic:iterate id="v" name="s" offset="2" length="6">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</logic:notEqual>
									<!-- ����ѧУҪ��ʾ�����ļ��İ�ť ���ݴ�ѧ����ʾ -->
									<logic:notEqual value="11078" name="xxdm">
										<logic:iterate id="v" name="s" offset="9" length="1">
											<td nowrap>
												<logic:equal value="��" name="v">
													<logic:iterate id="v" name="s" offset="9" length="1">
														<bean:write name="v" />
													</logic:iterate>
												</logic:equal>
												<logic:notEqual value="��" name="v">
													<a
														href="downloadfilewj.do?len=&wjsclj=<logic:iterate id="v" name="s" offset="8" length="1"><bean:write name="v" /></logic:iterate>"
														target="_blank"><logic:iterate id="v" name="s"
															offset="9" length="1">
															<bean:write name="v" />
														</logic:iterate> </a>
												</logic:notEqual>
											</td>
										</logic:iterate>
<%--										<td>--%>
<%--											<logic:iterate id="v" name="s" offset="9" length="1">--%>
<%--												<bean:write name="v" />--%>
<%--											</logic:iterate>--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											<logic:iterate id="v" name="s" offset="10" length="1">--%>
<%--												<bean:write name="v" />--%>
<%--											</logic:iterate>--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											<button type="button" id="qr" class="button2"--%>
<%--												onclick="rowOnClick(this);cfqr(this)"--%>
<%--												<logic:iterate id="v" name="s" offset="11" length="1"><bean:write name="v" /></logic:iterate>>--%>
<%--												ȷ�ϴ���--%>
<%--											</button>--%>
<%--										</td>--%>
									</logic:notEqual>
									<logic:equal value="11078" name="xxdm">
										<td>
											<button type="button" id="qr" class="button2"
												onclick="rowOnClick(this);cfqr(this)"
												<logic:iterate id="v" name="s" offset="9" length="1"><bean:write name="v" /></logic:iterate>>
												����֪��
											</button>
										</td>
									</logic:equal>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
			</div>
			
			<logic:notEqual value="10653" name="xxdm">
				<logic:notEmpty name="rsData">
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 
					<logic:equal value="10653" name="xxdm">
					�ѳ���(���)�Ĵ�����Ϣ&nbsp;&nbsp; 
					</logic:equal>
					<logic:notEqual value="10653" name="xxdm">
					�����У�쿴��Ϣ&nbsp;&nbsp; 
					</logic:notEqual>
					<logic:empty name="rsData">
					
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> 
						<logic:notEmpty name="rsData">
						<font color="blue"> ��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��</font>
						</logic:notEmpty>
						</span>
				</h3>

				<div class="con_overlfow">
					<table summary="" class="dateline " align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td align="left">
									ѧ��
								</td>
								<td align="left">
									���
								</td>
								<td align="left">
									�������
								</td>
								<td align="left">
									����ԭ��
								</td>
								<td align="left">
									����ʱ��
								</td>
								<td align="left">
									�����ĺ�
								</td>
								<td align="left">
									���ʱ��
								</td>
								<td align="left">
									����ĺ�
								</td>
								<td align="center">
									������
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rsData" id="es">
								<tr align="left" onclick="rowOnClick(this);"
									style="cursor:hand"
									ondblclick="showTopWin('wjcf_zjcm_stulxckxx.do?pkValue='+curr_row.getElementsByTagName('input')[0].value,650,550)">
									<td>
										<logic:iterate id="v" name="es" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="es" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="es" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
			</div>
			</logic:notEqual>

			<script type="text/javascript">
					function chkPriseOne2(url, w, h) {
						if (w == null) {
							w = 700;
						}
						if (h == null) {
							h = 500;
						}	
						if (curr_row == null) {
							alert("��ѡ��Ҫ�������У�");
							return false;
						} else {		
							var val = curr_row.cells[0].getElementsByTagName("input")[0].value;
							url+=val;
							url+="&cfwh=";
							url+=curr_row.cells[8].innerText;
							url+="&cfsj=";
							url+=curr_row.cells[9].innerText;	
							showTopWin(url, w, h);
						}
					}
				</script>
			</div>
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!");
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>

