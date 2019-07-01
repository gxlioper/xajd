<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		jQuery(function(){
			if(!jQuery("#dspgw").val()){
				if(!jQuery("#query").val()){
					searchRs();
				}
			}
		});
		function searchRs(){
			allNotEmpThenGo('zxdk_xnmz.do?method=zxdkshManage');
		}
			function showSpgw(){
				var spgwidArr=document.getElementsByName('spgwidArr');
				var spgwArr=document.getElementsByName('spgwArr');
				var len=spgwArr.length;
				
				var html="<div style='width:100%;height:70px;overflow:auto;overflow-x:hidden'>";
				html+="<table width='100%'><tr>";
				for(i=1;i<=len;i++){
					html+="<td style='width:33%'>";
					html+="<input type='radio' name='shgwArr' value='"+spgwidArr[i-1].value+"' ";
					if (i==1) {
						html+= " checked='checked' ";
					}
					html+=">"+spgwArr[i-1].value;
					html+="</td>";
					
					if(i%3==0 ){
						html+="</tr><tr>";
					}
				}
				
				if(len%3!=0){
					
						for(i=1;i<=3-(len%3);i++){
							html+="<td></td>"
						}
				
				}
				html+="</tr></table></div>";
				$("div_spgw").innerHTML=html;
			}
			
			function chooseSpgw(){
				var shgwArr=document.getElementsByName('shgwArr');
				var shgw="";
				var flag=false;
				for(var i=0;i<shgwArr.length;i++){
					if(shgwArr[i].checked){
						flag=true;
						shgw=shgwArr[i].value;
					}
				}
				if(!flag){
					alertInfo("����ѡ��������λ!");
					return false;
				}
				if($("shgw")){
					$("shgw").value=shgw;
				}
				refreshForm("/xgxt/zxdk_xnmz.do?method=zxdkshManage&go=go");
			}
			
			function zxdkSh(){
				var primaryKey=document.getElementsByName("primary_key");
				
				var shgw=$("spgw").value;
				var n=0;
				var pkValue="";
				for(var i=0;i<primaryKey.length;i++){
					if(primaryKey[i].checked){
						pkValue=primaryKey[i].value;
						n++;
					}
				}
				
				if(n==0){//δѡ
					
					alertInfo("�빴ѡ��Ҫ��˵ļ�¼!");
					return false;
					
				}else if(n==1){//����
				
					showTopWin("/xgxt/zxdk_xnmz.do?method=zxdkDgsh&shgw="+shgw+"&xh="+pkValue,800,600);
					return false;
				}else {//������
					viewTempDiv('��ѡ����˸�λ','div_plsh',350,200)
				}
				
			}
			
			function plsh(shzt){
				refreshForm("/xgxt/zxdk_xnmz.do?method=zxdkshManage&doType=plsh&shzt="+shzt);
			}
			
			function show(){
				viewTempDiv('��ѡ����˸�λ','spgwChoose',350,200)
				$("windown-close").style.display="none";
				if($("message") && $("message").value != ""){
					$("message").value = "";
					$("doType").value = "";	
				}
				$('##backDiv##').style.backgroundColor = "rgb(242, 242, 242)";
			}
		</script>
	</head>
	<body onload="">

		<html:form action="/zxdk_xnmz" method="post">
			<input type="hidden" id="query" value="${searchTj}"/>
			<input type="hidden" id="dspgw" value="${dspgw}"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="spgw" id="spgw" value="${shgw}"/>
			<html:hidden property="shjb" styleId="shjb" />
			<!-- ��˸�λ������ -->
			<html:hidden property="shgw" styleId="shgw" />
			<!-- ��˸�λ���� end -->
			
			<!-- ������ -->

			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="zxdkSh();return false;" class="btn_sh">���</a>
						</li>
						<logic:present name="dspgw">
							<li>
								<a href="#" onclick="show();showSpgw();return false;"
									class="btn_sx">������˸�λ</a>
							</li>
						</logic:present>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);"
										style="cursor:hand">
										<!-- checkbox begin -->
										<td>
											<input type="checkbox" name="primary_key" id="pkV"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"
												<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate> />
										</td>
										<!-- checkbox end -->

										<!-- ����� begin -->
										<logic:iterate id="v" name="s" offset="2" length='7'>
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<!-- ����� end -->

										<!-- ��˽��ͼƬ��ʾ begin -->
										<logic:iterate id="v" name="s" offset="9" length="1">
											<td align="left" nowrap="nowrap">
												<!-- δ��� begin -->
												<logic:equal name="v" value="δ���">
													<p>
														<img src="<%=stylePath%>images/ico_dsh.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<!-- δ��� end -->
												<!-- ͨ�� begin -->
												<logic:equal name="v" value="ͨ��">
													<p>
														<img src="<%=stylePath%>images/ico_shtg.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<!-- ͨ�� end -->
												<!-- ��ͨ�� begin -->
												<logic:equal name="v" value="��ͨ��">
													<p>
														<img src="<%=stylePath%>images/ico_shbtg.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<!-- ��ͨ�� end -->
												<!-- �˻� begin -->
												<logic:equal name="v" value="�˻�">
													<p>
														<img src="<%=stylePath%>images/ico_shth.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<!-- �˻� end -->
												<!-- ������ begin -->
												<logic:equal name="v" value="������">
													<p>
														<img src="<%=stylePath%>images/ico_shxcs.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<!-- ������ begin -->
											</td>
										</logic:iterate>
										<!-- ��˽��ͼƬ��ʾ end -->
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!-- ������ -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- ������ end-->
						</tbody>

					</table>
				</div>
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zxdkForm"></jsp:include>
			</div>

			
		</html:form>

		<!-- ���������ʾ�� -->
		<div id='spgwChoose' style='display:none'>
			<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<th>
								<span> ����ѡ��ǰ��˸�λ! </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<td id="div_spgw">
						</td>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<!-- ȷ�� -->
									<button onclick="chooseSpgw()">
										<bean:message key="lable.btn_qd_space" />
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<!-- ��˸�λ���� begin -->
		<logic:notPresent name="spgwList">
		<logic:present name="spgwInfo">
			<logic:iterate name="spgwInfo" id="spgw">
				<input type="hidden" name="spgwidArr" value="${spgw.id}" />
				<input type="hidden" name="spgwArr" value="${spgw.mc}" />
			</logic:iterate>
		</logic:present>
		</logic:notPresent>
		<logic:present name="spgwList">
			<logic:iterate name="spgwList" id="spgw">
				<input type="hidden" name="spgwidArr" value="${spgw.id}" />
				<input type="hidden" name="spgwArr" value="${spgw.mc}" />
			</logic:iterate>

			<logic:present name="dspgw">
				<script defer="defer">
					setTimeout("show()","500");
					showSpgw();
				</script>
			</logic:present>
		</logic:present>
		<div id='div_plsh' style='display:none'>
			<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span> ������� </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>	
							<th style="width:10%">������</th>
							<td>
								<textarea name='shyj' id="shyj" rows='4'
									style="word-break:break-all;width:98%"
									onblur="chLeng(this,500);"></textarea>

							</td>
						</tr>
						<tr>	
							<th style="width:10%">��֤�����</th>
							<td>
								<textarea name='jzryj' id="jzryj" rows='4'
									style="word-break:break-all;width:98%"
									onblur="chLeng(this,500);"></textarea>

							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<!-- ȷ�� -->
									<button onclick="plsh('ͨ��')">
										ͨ  ��
									</button>
									<button onclick="plsh('��ͨ��')">
										��ͨ��
									</button>
									<button onclick="plsh('�˻�')">
										��  ��
									</button>
									<button onclick="hiddenMessage(true,true);">
										��  ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
