<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript">	
		function sendvalue(ssbh){
			showTopWin("/xgxt/zgdzdx_Gygl.do?method=getOneSsInfo&ssbh="+ssbh,600,600);
		}
		
		
		function printBb(){
			
			document.forms[0].action = "/xgxt/zgddGygl.do?method=rykTbtj&go=go&flg=printPage";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		</script>
	</head>

	<body onload="">

		<logic:notEqual value="printPage" name="flg">
			<!-- ���� -->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>��Ԣ����-��Դ����Ϣ-ͼ��ͳ��</a>
				</p>
			</div>
		</logic:notEqual>

		<!-- ���� end-->
		<html:form action="/zgddGygl" method="post">
			<%@ include file="/gygl/hiddenValue.jsp"%>

			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" />
					</FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<!-- �й����ʴ�ѧ -->
				<logic:notEqual value="printPage" name="flg">
					<logic:equal value="10491" name="xxdm">
						<div class="compTab" id="card">
							<div class="comp_title" id="div1">
								<ul>
									<li id="li-${s}">
										<a href="#"
											onclick="$('go').value='';refreshForm('ssxx_search.do?act=dormInfo')">
											<span>��ͨ��ѯ</span> </a>
									</li>
									<li id="li-${s}" class="ha">
										<a href="#"
											onclick="$('go').value='';refreshForm('zgddGygl.do?method=rykTbtj')">
											<span>ͼ��ͳ��</span> </a>
									</li>
								</ul>
							</div>
						</div>
					</logic:equal>
				</logic:notEqual>
				<!-- �й����ʴ�ѧ end-->
				<!-- ����ʦ�� -->
				<logic:equal value="10477" name="xxdm">
					<div class="compTab" id="card">
						<div class="comp_title" id="div1">
							<ul>
								<li id="li-${s}">
									<a href="#"
										onclick="$('go').value='';refreshForm('ssxx_search.do?act=dormInfo')">
										<span>��ͨ��ѯ</span> </a>
								</li>
								<li id="li-${s}" class="ha">
									<a href="#"
										onclick="$('go').value='';refreshForm('zgddGygl.do?method=rykTbtj')">
										<span>ͼ��ͳ��</span> </a>
								</li>
							</ul>
						</div>
					</div>
				</logic:equal>

				<logic:notEqual value="printPage" name="flg">
					<div class="toolbox">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" onclick="printBb();" class="btn_sh">��ӡ </a>
								</li>
							</ul>
						</div>
						<!--  ����ʦ��  end-->
						<div class="toolbox">
							<!-- �������� -->
							<div class="searchtab">
								<table width="100%" border="0">
									<tfoot>
										<tr>
											<td colspan="10">
												<div class="btn">
													<input type="hidden" name="go" value="a" />
													<button class="btn_cx" id="search_go"
														onclick="showTips('���ݲ�ѯ�У���ȴ�......');allNotEmpThenGo('/xgxt/zgddGygl.do?method=rykTbtj');">
														��ѯ
													</button>
												</div>
											</td>
										</tr>
									</tfoot>
									<tbody>
										<!-- ��һ�� -->
										<tr>
											<th>
												У��
											</th>
											<td>
												<html:select property="xqdm" style="" styleId="xqdm"
													onchange="setLdList()">
													<html:options collection="xqdmList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
											<th>
												¥��
											</th>
											<td>
												<html:select property="lddm" style="" styleId="lddm"
													onchange="setXqList();setCsList();setQsList();">
													<html:options collection="ldList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
											<th>
												��������
											</th>
											<td>
												<html:select property="cs" style="" styleId="cs"
													onchange="setQsList();">
													<html:options collection="csList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
										</tr>
										<!-- �ڶ��� -->
										<tr>
											<th>
												���Һ�
											</th>
											<td>
												<html:select property="qsh" style="" styleId="qsh"
													onchange="">
													<html:options collection="qsList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
											<th>
												�꼶
											</th>
											<td>
												<html:select property="nj" style="" onchange="">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
												</html:select>
											</td>
											<th>
												<bean:message key="lable.xsgzyxpzxy" />
											</th>
											<td>
												<html:select property="xydm" style="" styleId="xy"
													onchange="">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</logic:notEqual>

				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> <logic:notEqual value="printPage" name="flg">	
							��ѯ���&nbsp;&nbsp; 
							</logic:notEqual> <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> <logic:notEmpty name="rs">
							��ɫ״̬��
								<font color="#000000">���ģ�</font>
								<font color="red">�յģ�</font>
								<font color="orange">�еģ�</font>
								<font color="green">���ⷿ��(����)��</font>
								<font color="#800080">��У����</font>
								<font color="blue">�쳣�������ң���סѧ�����������Ҵ�λ������</font>
							</logic:notEmpty> </span>
					</h3>
					<logic:equal value="printPage" name="flg">	
					<div class="btn noPrin">
			            <button onclick="try{WebBrowser.ExecWB(8,1)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">ҳ������</button>
						<button onclick="try{WebBrowser.ExecWB(7,1)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">��ӡԤ��</button>
						<button onclick="try{WebBrowser.ExecWB(6,6)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">ֱ�Ӵ�ӡ</button>
			        </div>
			        </logic:equal>


					<logic:notEmpty name="rs">
						<logic:iterate name="rs" id="xqld">
							<table width="100%" class="table_01" style="">
								<tr>
									<td align="center" colspan="2">
										<font size="5" color="blue">${xqld.xqmc }/${xqld.ldmc }</font>
									</td>
								</tr>
								<logic:iterate id="cs" name="xqld" property="csList">
									<tr>
										<th align="center" width="10%" valign="middle">
											��${cs.ldcs.cs }����
											<br />
											��������${cs.ldcs.fjs }
											<br />
											��λ����${cs.ldcs.zcw }
											<br />
											�մ�λ����${cs.ldcs.kcw }
										</th>
										<td>
											<table width="100%">
												<tr>
													<logic:iterate id="qs" name="cs" property="qsList"
														indexId="index">
														<td height="20px" width="10%">
															<a href="javascript:sendvalue('${qs.ssbh}')"> <font
																color="${qs.color }">${qs.qsh }</font> </a>
															<br />
															<logic:equal name="qs" property="color" value="red">
																<strong>��</strong>
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																<br />
															</logic:equal>
															${qs.xymc }
															<br />
															${qs.sxnj }
															<br />
															<logic:equal name="qs" property="color" value="green">																
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																<br />
															</logic:equal>
														</td>
														<%
														if ((index.intValue() + 1) % 10 == 0) {
														%>
														<%
														out.print("</tr>");
														%>
														<%
														}
														%>
													</logic:iterate>
												</tr>
											</table>
										</td>
									</tr>
								</logic:iterate>
							</table>
						</logic:iterate>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
				</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>

		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{
				display:none;
			}
		</style>
	</body>
</html>
