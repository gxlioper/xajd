<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglCwgl.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=cwxxtj&doType=query');
		}
		
		//���ݽ������Ϣ�������ӽ��봲λ����
		function xscwfp(num){
			//��λ��Ϣ ����
			var pkValue=$('cwzj_'+num).value;
			
			refreshForm('/xgxt/gyglCwgl.do?method=sdfpcw&pkValue='+pkValue);
		}
		
		//��ѧ������
		function axsfpcw(){
			refreshForm('/xgxt/gyglCwgl.do?method=axsfpcw&doType=zdfp');
		}
				
		function cwzdfpxx(){
			
			var pkValue =new Array();
			var n=0;
			var blog=false;
			for(i=0;i<document.getElementsByName("primarykey_checkVal").length;i++){
				if(document.getElementsByName("primarykey_checkVal")[i].checked){
				pkValue[n]=document.getElementsByName("primarykey_checkVal")[i].value;
				blog=true;
				n++;
				}
			}
			if(!blog){
				alert("��ѡ����Ҫ�Զ����䴲λ�Ĳ�����Ϣ!");
				return false;
			}
			viewTempDiv("ѡ����䷶Χ","fpxx",600,320);
			var fpdx=$("fpdx").value;
<%--			$("fpdx").value;--%>
			var html="";
			html+="<table>"
			html+="<thead><tr>"
			if("xy"==fpdx){
				html+="<td width='80px'><bean:message key="lable.xb" /></td>";
			}else if("njxy"==fpdx){
				html+="<td>�꼶</td>";
			    html+="<td width='80px'><bean:message key="lable.xb" /></td>";
			}else if("njzy"==fpdx){
				html+="<td>�꼶</td>";
			    html+="<td width='80px'>רҵ</td>";
			}else if("bj"==fpdx){
				html+="<td>�꼶</td>";
			    html+="<td width='80px'>�༶</td>";
			}
			html+="<td>��������</td>";
		    html+="<td>����������</td>";
		    html+="<td>��ס�˴�λ��</td>";
			html+="<td>δס�˴�λ��</td>";
			html+="<td>��ס�˴�λ��</td>";
			html+="</tr></thead>"
			
			dwr.engine.setAsync(false);
			html+="<tbody>"
			gyglCwgl.getBmxxList(fpdx, pkValue,function(data){
				for(i=0;i<data.length;i++){
					var xymc="";
					var nj="";
					var zymc="";
					var bjmc="";
					html+="<tr>";
					if("xy"==fpdx){
						xymc=data[i].xymc;
						html+="<td width='80px' title='"+xymc+"'>";
						if(xymc.length>6){
							html+=xymc.substring(0,6)+"...";
						}else{
							html+=xymc;
						}
						html+="</td>";
					}else if("njxy"==fpdx){
						nj=data[i].nj;
						xymc=data[i].xymc;
						html+="<td >";
						html+=nj;
						html+="</td>";
						html+="<td width='80px' title='"+xymc+"'>";
						if(xymc.length>6){
							html+=xymc.substring(0,6)+"...";
						}else{
							html+=xymc;
						}
						html+="</td>";
					}else if("njzy"==fpdx){
						nj=data[i].nj;
						zymc=data[i].zymc;
						html+="<td>";
						html+=nj;
						html+="</td>";
						html+="<td width='80px' title='"+zymc+"'>";
						if(xymc.length>6){
							html+=zymc.substring(0,6)+"...";
						}else{
							html+=zymc;
						}
						html+="</td>";
					}else if("bj"==fpdx){
						
						nj=data[i].nj;
						bjmc=data[i].bjmc;
						html+="<td>";
						html+=nj;
						html+="</td>";
						html+="<td width='80px' title='"+bjmc+"'>"
						if(bjmc.length>6){
							html+=bjmc.substring(0,6)+"...";
						}else{
							html+=bjmc;
						}
						html+="</td>";
					}
					var bmrs=data[i].bmrs;
					var fpqss=data[i].fpqss;
					var kzrcws=data[i].kzrcws;
					var wzrcws=data[i].wzrcws;
					var yzrcws=data[i].yzrcws;
					html+="<td>";
					html+=bmrs;
					html+="</td>";
					html+="<td>";
					html+=fpqss;
					html+="</td>";
					html+="<td>";
					html+=kzrcws;
					html+="</td>";
					html+="<td>";
					html+=wzrcws;
					html+="</td>";
					html+="<td>";
					html+=yzrcws;
					html+="</td>";
					html+="</tr>";
				}
			})
			dwr.engine.setAsync(true);
			html+="</tbody></table>";
			$("Bmfpxx").innerHTML=html;
		}
		
		//��λ�Զ�����
		function zdfpcw(){
		
			var num = document.getElementsByName("primarykey_checkVal").length;
			var id = new Array();
			var flag = false;
			
			var n = 0;
			var blog=false;
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked){
					blog=true;
				}
			}
			if(!blog){
				alert("����ѡ����Ҫ�Զ����䴲λ�Ĳ���!");
				return false;
			}
			if(confirm("ȷ��Ҫ����ѡ���Ž��з��䴲λ��?")){
				showLoadPage();
				refreshForm('/xgxt/gyglCwgl.do?method=cwxxtj&doType=zdfp');
			}
		}
		
		//��һ������
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next == "gb"){//�ر�
				hiddenMessage(true,true);
			}else if(next == "jg"){//�����ѯ
				refreshForm("gyglCwgl.do?method=cwfpxxcx&go=go");
			}else if(next == "sd"){//�ֶ�����
				refreshForm("gyglCwgl.do?method=cwsdfp&go=go");
			}
		}
		
		//��ʾ����ҳ��
		function showLoadPage(){
			//�๦�ܲ���
			$("dgncz").style.display="none";
			//��ѯ���
			$("cxjg").style.display="none";
			//��ʾ
			$("page_loading").style.display="";
			//��ʾ��Ϣ
			$("xxPrompt").style.display="none";
			//������ʾ��Ϣ
			$("prompt").innerHTML="���ڽ��д�λ�Զ�����,���Ժ�!";
		}
		
		function goCwfpjg(){
			refreshForm("gygl_cwgl_fpjg.do");
		}
		
		function cwsdfp(){
		
			refreshForm('/xgxt/gyglCwgl.do?method=cwxxtj&doType=cwsdfp');
		}
		
		function getBmxss(){
			var fpdx=$("fpdx").value;
			var bm=new Array();
			var bmPk=document.getElementsByName("primarykey_checkVal");
			var n=0;
			for(i=0;i<bmPk.length;i++){
				if(bmPk[i].checked){
					bm[n]=bmPk[i].value;
					n++;
				}
			}
			
			if(bm.length>0){
				gyglCwgl.Bmxss(bm,fpdx,function(data){
					if(eval(data)>50){
						if(confirm("����ѡ��Ĳ���δ�����������࣬�Ƿ����ɸѡ?")){
							refreshForm("/xgxt/gyglCwgl.do?method=sxsdfpcwxs&go=go&doType=sxcx");
						}else{
							cwsdfp();
						}
					}else{
						cwsdfp();
					}
				})
			}else {
				alert("��ѡ����Ҫ����Ĳ���!");
				return false;
			}
		}
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<div id="xxPrompt" class="prompt">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				������ģ��չʾ���ŵĴ�λ�����������ѡϣ�����з���Ĳ��ţ��������λ�Զ����䡱����ʾ��λ����������ȷ������
				<br />
				ϵͳ�Զ��Դ�λ���з��䣬�������λ�ֶ����䡱����չ���ѷ������ѡ���ŵ����Ҵ�λ�б���������ֶ����䣬���
				<br />
				���������鿴�������Բ鿴�������Ѿ�����Ĵ�λ��������ִ�С�ȡ�����䡱������
				<br />
			</p>
			<a class="close" title="����"
				onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<!-- ���� end-->
		<html:form action="/gyglCwgl">
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx}" />
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ȷ�� -->
						<li>
							<a href="#" id="btn_shtg" onclick="zdfpcw();return false;"
								class="btn_shtg" > ��λ�Զ����� </a>
						</li>
						<!-- �ֶ����� -->
						<li>
							<a href="#" id="btn_xg"
								onclick="getBmxss();return false;"
								class="btn_xg"> ��λ�ֶ����� </a>
						</li>
						<!-- �ֶ����� -->
						<li>
							<a href="#" id="btn_sh"
								onclick="allNotEmpThenGo('/xgxt/gyglCwgl.do?method=cwsdfp&doType=query');return false;"
								class="btn_sh"> ��λ�ֶ����� </a>
						</li>
						<!-- ������ -->
						<li>
							<a href="#" id="btn_cx" onclick="goCwfpjg();return false;"
								class="btn_cx"> �������鿴 </a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->

				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->

			</div>
			<!-- �๦�ܲ����� end -->

			<!-- ��ѯ���-->
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> ��ѯ��� <logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue"></font>
						</logic:notEmpty> <%--						<font color="blue"></font>--%> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<!-- ��ͷ -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- ��ͷ end-->
						<!--���� -->
						<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td>
										<input type="checkbox" name="primarykey_checkVal" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
									</td>
									<!-- ��ʾ��Ϣ -->
									<logic:iterate id="v" name="s" offset="1">
										<td align="left" nowrap="nowrap">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<!--���� end-->
						<!-- ������ -->
						<%@ include file="/comm/noRows.jsp"%>
						<!-- ������ end-->
					</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyglCwglForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->


			<div id="fpxx" style="display: none" style="width:650px">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th>
									<span> ������Ϣ </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<p id="Bmfpxx" class="tab"
										style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
									</p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- ȷ�� -->
										<button onclick="zdfpcw();">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>

			<!-- ��ʾ��Ϣ -->
			<logic:present name="message">
				<!-- ���������ʾ�� -->
				<div id="tsxxDiv" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span> �Ѿ��ɹ�������${message }����λ����ȷ����һ������ </span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden" name="next" id="next_cz" value="jg" />
										<input type="radio" name="next" id="next_jg" value="jg"
											onclick="$('next_cz').value = this.value" checked="checked" />
										�鿴�Ѿ�����Զ�����Ĵ�λ
										</br>
										<input type="radio" name="next" id="next_sd" value="sd"
											onclick="$('next_cz').value = this.value" />
										ǰ��"��λ�ֶ�����"ģ��
										</br>
										<input type="radio" name="next" id="next_gb" value="gb"
											onclick="$('next_cz').value = this.value" />
										����Ϊ����������з���
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td>
										<div class="btn">
											<!-- ȷ�� -->
											<button onclick="nextCz()">
												<bean:message key="lable.btn_qd_space" />
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<script defer="defer">
					function showNextDiv(){
						viewTempDiv("��ѡ����һ������","tsxxDiv",350,200);
						if($("message") && $("message").value != ""){
							$("message").value = "";
							$("doType").value = "";	
						}
					}
					setTimeout("showNextDiv()",100);
				</script>
			</logic:present>
			<!-- ��ʾ��Ϣend -->

			<!-- ��ȴ� -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- ��ȴ� end-->
		</html:form>
	</body>
</html>
