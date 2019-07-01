<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="java.util.HashMap"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/twgzFunction.js"></script>
		<script type="text/javascript" src="pjpy/nblg/nblgjs/nblgjs.js"></script>
		<script type="text/javascript" src="pjpy/shcbys/shcbys.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/systemFunction"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzDao.js'></script>
		<script language="javascript">
			function addyz(){
				var tName = document.getElementById('tname').value;
				if(tName == "gdnzzyxy_dknlldmb"){
					alertInfo("�����������������ݣ�");
					return false;
				}
				SelcodeConf('add');
			}
			function Delyz(){
				var tName = document.getElementById('tname').value;
				if(tName == "bjlydx_knssrly"){
					alertInfo("������Դ��Ŀ���ܱ�ɾ����");
					return false;
				}
				if(tName == "gdnzzyxy_dknlldmb"){
					alertInfo("�����ʲ��ܱ�ɾ����");
					return false;
				}
				codeConf('del');
			}
			
			function DelAllyz(){
				var tName = document.getElementById('tname').value;
				if(tName == "bjlydx_knssrly"){
					alertInfo("������Դ��Ŀ���ܱ�ɾ����");
					return false;
				}	
				if(tName == "gdnzzyxy_dknlldmb"){
					alertInfo("�����ʲ��ܱ�ɾ����");
					return false;
				}			
				Alldel();
			}
		</script>
		<script type="text/javascript">
			function modidm(){
				if (curr_row==null || curr_row =='') {
					alertInfo('��ѡ��Ҫ�����������ݣ�');
					return;
				} else {
					if($('KFSC_XG')){
					if(curr_row.cells[$('KFSC_XG').cellIndex].innerHTML=='�� '){
						alertInfo('������¼�����޸ģ�');
						return;
					}
					}
				    var url ='/xgxt/xtwhOther.do?method=xtDmwhOneNew&ssmk=';
				    url += $('ssmk').value;
				    url += '&tName=';
				    url += $('tname').value;
					url += '&pkValue=';
					url += curr_row.cells[0].getElementsByTagName('input')[0].value;
					//showTopWin(url,'600','480');
					showDialog('',370,190,url);
					return;
				}
			}
			function delDm() {
			    if (curr_row==null || curr_row =='') {
					alertInfo('��ѡ��Ҫ�����������ݣ�');
					return;
				} 
				if($('KFSC_XG')){
					if(curr_row.cells[$('KFSC_XG').cellIndex].innerHTML=='�� '){
						alertInfo('������¼����ɾ����');
						return;
					}
				}
				var url = '/xgxt/xtwhOther.do?method=xtDmwhDel';
				url += '&pkValue=';
				url += curr_row.cells[0].getElementsByTagName('input')[0].value;
				url += '&tName=';
				url += $('tname').value;
				confirmInfo('����������ɾ�����У�\nȷ��Ҫɾ��ѡ�е�������',function(tag){
					if(tag=="ok"){
						refreshForm(url);
					}
				});
			}
			
			function newChgCode(objTr) {
				
				if(objTr){
					$('tname').value= objTr.id;
				}
				document.forms[0].action = "xtwhOther.do?method=xtDmwhNew&ssmk="+$('ssmk').value;
				document.forms[0].submit();
			}
			
			jQuery(function(){
				if($($('tname').value)) $($('tname').value).parentNode.className = 'ha';
			})
			
			function xtwhdmwhExportConfig() {
				customExport("xtwhOther_"+$('tname').value+".do", xtwhdmwhExportData);
			}
			
		
			
			// ��������
			function xtwhdmwhExportData() {
				//setSearchTj();//���ø߼���ѯ����
				var url = "xtwhOther.do?method=xtwhdmwhExportData&dcclbh=" + "xtwhOther_"+$('tname').value+".do";//dcclbh,�������ܱ��
				//url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

			
		</script>
	</head>
	<body>

		<html:form action="/xtwhOther" method="post">
			<input type="hidden" id="tname" name="tName" value="${tName }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${tName }" />
			<input type="hidden" id="realTable" name="realTable"
				value="${tName }" />
			<input type="hidden" id="ssmk" name="ssmk" value="${ssmk }" />
			<input type="hidden" id="writeAble" name="writeAble"
				value="${writeAble }" />
			<input type="hidden" id="title" name="title" value="${title }" />

			<button type="button" class="button2" id="search_go" style="display: none;"
				onclick="newChgCode()">
				��ѯ
			</button>


			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showDialog('',370,190,'/xgxt/xtwhOther.do?method=xtDmwhOneNew&ssmk='+$('ssmk').value+'&tName='+$('tname').value)"
									class="btn_zj"> ����</a>
							</li>
							<li>
								<a href="#" onclick="modidm();return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#" onclick="delDm();return false;" class="btn_sc"> ɾ��</a>
							</li>
							<%--
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">����</a>
							</li>
							<li>
								<a href="#" onclick="xtwhdmwhExportConfig();return false;" class="btn_dc">����</a>
							</li>
							--%>
						</ul>
					</div>
				</logic:equal>
				<div class="compTab" id="card">
			
			<%
				List<HashMap<String,String>> cards = (List<HashMap<String,String>>)request.getAttribute("tableArray");
				
				int i = 0 ;
				while (null != cards && i < cards.size() ){
					
					if (i%5==0){
						out.print("<div class=\"comp_title\">");
						out.print("<ul>");
					} 
					out.print("<li><a href=\"#\" onclick=\"newChgCode(this);return false;\" id=\"");
					out.print(cards.get(i).get("whdmb"));
					out.print("\"><span>");
					out.print(cards.get(i).get("whdmbmc"));
					out.print("</span> </a>");
					
					i++;
					
					if (i%5==0){
						out.print("</ul></div>");
					}
				}
			
			 %>
			</div>


				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 
							��ѯ���&nbsp;&nbsp;
							<font color="red">(����:&nbsp;�Դ������ͳһ���룬ͬʱ��������ɾ�����¼�¼��)</font>
						</span>
					</h3>

					
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr id="titleTr">
									<logic:notEmpty name="topTr">
										<logic:iterate id="tit" name="topTr" scope="request">
											<td id="${tit.en}" onclick="tableSort(this)">
												${tit.cn}
											</td>
										</logic:iterate>
									</logic:notEmpty>
								</tr>
							</thead>
							<tbody>
								<logic:notEmpty name="rs">
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="hidden" value="<bean:write name="v" />" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="1" length="1">
												${v }
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="2">
												<td>
													${v }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									<%
										for (int n = 0; n < (Integer) request.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); n++) {
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
										for (int n = 0; n < (Integer) request.getAttribute("maxNum"); n++) {
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
						<!--��ҳ��ʾ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xtwhOtherForm"></jsp:include>
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
						<!--��ҳ��ʾ-->
					
				</div>
				<div id="temp"></div>
				<logic:present name="result">
					<logic:equal value="OK" name="result">
						<script>
					alertInfo('ɾ���ɹ�!');
				</script>
					</logic:equal>
				</logic:present>
				<logic:present name="result">
					<logic:equal value="NO" name="result">
						<script>
					alertInfo('ɾ��ʧ��!');
				</script>
					</logic:equal>
				</logic:present>
		</html:form>
	</body>
</html>
