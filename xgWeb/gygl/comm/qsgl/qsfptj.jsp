<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/getCommGygl.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglQsgl.do?method=qsfptj');
		}
		
		//��ת�������Զ�����
		function goQszdfp(){
			var num = document.getElementsByName("primarykey_checkVal").length;
			var flag = false;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					flag = true;
					break;
				}
			}
			
			if(flag){
				$("div_nr").style.display="none";
				$("div_btn").style.display="none";
				$("div_tsxx").style.display="";
				
				viewTempDiv("ѡ����䷶Χ","zdscDiv",600,480);
				//��ʾ
				setBmtjInfo();
			}else{
				alert("��ѡ����Ҫ�Զ�����Ĳ��ţ�");
			}
		}
		
		//��ת�������ֶ�����
		function goQssdfp(){
			refreshForm("gyglQsgl.do?method=qssdfp");
		}
		
		//��ת�������ֶ�����
		function goQssdfpByHand(){
			var pkValue=document.getElementsByName("primarykey_checkVal");
			var bool=false;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					bool=true;
				}
			}
			if(!bool){
				alert("������ѡ��һ����Ҫ�������ҵĲ���!");
				return false;
			}
			
			refreshForm("gyglQsgl.do?method=qssdfpByHand");
		}
		
		//�����Զ�����
		function qszdfp(){
			if (confirm("��Ҫ���������õķ�Χ����ѡ��������Զ��������ң���ȷ�ϣ�")) {
				$("div_nr").style.display="none";
				$("div_btn").style.display="none";
				$("div_tsxx").style.display="";
				saveUpdate('/xgxt/gyglQsgl.do?method=qsfptj&doType=save','');
			}
		}
		
		//���ҷ�����
		function goQsfpjg(){
			refreshForm("gyglQsgl.do?method=qsfpjg");
		}
		
		//��һ������
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next == "gb"){//�ر�
				hiddenMessage(true,true);
			}else if(next == "jg"){//�����ѯ
				refreshForm("gyglQsgl.do?method=qsfpjg&go=go");
			}else if(next == "sd"){//�ֶ�����
				refreshForm("gyglQsgl.do?method=qssdfp");
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
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				������ģ��չʾ���ŵ����ҷ����������ѡϣ�����з���Ĳ��ţ�����������Զ����䡱���ڵ���ҳ��ȷ���������<br/>
				ϵͳ�Զ������ҽ��з��䣬����������ֶ�����������չ���ѷ������ѡ���ŵ������б���������ֶ��ķ��䣬���<br/>
				���������鿴�������Բ鿴�������Ѿ�����������ң�������ִ�С�ȡ�����䡱������ѡ��һ�����ϵĲ��ż�¼���<br/>
				���������ֶ����䡱��ť�ɶ�ѡ�в��Ž������ҷ��䡣
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ END-->
		
		<html:form action="/gyglQsgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������� -->
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx }"/>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- �Զ����� -->
						<li>
							<a href="#" id="btn_qd"
								onclick="goQszdfp();return false;"
								class="btn_shtg">
								�����Զ�����
							</a>
						</li>
						<!-- �ֶ����� -->
						<li>
							<a href="#" id="btn_xg"
								onclick="goQssdfpByHand();return false;"
								class="btn_sh">
								�����ֶ�����
							</a>
						</li>
						<logic:equal name="fpfs" value="1">
						<!-- �ֶ����� -->
							<li>
								<a href="#" id="btn_qd"
									onclick="goQssdfp();return false;"
									class="btn_xg">
									�����ֶ�����
								</a>
							</li>
						</logic:equal>
						<!-- ������ -->
						<li>
							<a href="#" id="btn_qd"
								onclick="goQsfpjg();return false;"
								class="btn_cx">
								�������鿴
							</a>
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
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;
							<font color="blue">
							
							</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1" length="9">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- ��ʾ��Ϣ -->
								<logic:iterate id="v" name="s" offset="1">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
					<!--���� end-->
					<!-- ������ -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- ������ end-->
				</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglQsglForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->

			<!-- �Զ�����Div-->
			<div id="zdscDiv" style="display: none">
				<div id="div_tsxx" align="center">
					</br></br></br></br></br></br>
					<img src="<%=stylePath%>images/ico_loading.gif"/>
					</br>
					<img src="<%=stylePath%>images/loadingli.gif"/>
					<p id="p_tsxx"><B>���ݴ����У����Ժ󡣡���</B></p>
					
					<!-- ������ѡ���� -->
					<script language="javascript" defer="defer">
						function showNr(){
							$("div_nr").style.display="";
							$("div_btn").style.display="";
							$("div_tsxx").style.display="none";
						}
						setTimeout("showNr()",2000);
					</script>
				</div>
				<div id="div_nr" class="tab" style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;display: none">
					<%@ include file="qsfpInfo.jsp"%>
					<%@ include file="qsfpRule.jsp"%>
				</div>
				<div id="div_btn" style="display: none">
					<table class="formlist">
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- ȷ�� -->
										<button onclick="qszdfp()">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- �Զ�����Div end-->
			
			<!-- ��ʾ��Ϣ -->
			<logic:present name="message">
				<!-- ���������ʾ�� -->
				<div id="tsxxDiv" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
											�Ѿ��ɹ�������${message }�����ң���ȷ����һ������
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden"  name="next" id="next_cz" value="jg"/>
										<input type="radio" name="next" id="next_jg" value="jg" onclick="$('next_cz').value = this.value" checked="checked"/>
										ǰ��"�������鿴"ģ��
										</br>
										<input type="radio" name="next" id="next_sd" value="sd" onclick="$('next_cz').value = this.value"/>
										ǰ��"�����ֶ�����"ģ��
										</br>
										<input type="radio" name="next" id="next_gb" value="gb" onclick="$('next_cz').value = this.value"/>
										�رձ�ҳ�棬����Ϊ����������з���
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
			
			<%@ include file="/comm/other/exception.jsp"%>
			
		</html:form>
	</body>
</html>