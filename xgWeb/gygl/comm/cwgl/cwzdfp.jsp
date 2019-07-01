<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=cwzdfp&doType=query');
		}
		
		//��λ�Զ�����
		function zdfpcw(){
		
			var num = document.getElementsByName("pkvArr").length;
			var id = new Array();
			var flag = false;
			
			var n = 0;
			var blog=false;
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("pkvArr")[i];
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
				refreshForm('/xgxt/gyglCwgl.do?method=cwzdfp&doType=zdfp');
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
			//������ʾ��Ϣ
			$("prompt").innerHTML="���ڽ��д�λ�Զ�����,���Ժ�!";
		}
		
		function goCwxxtj(){
			refreshForm("gygl_cwgl_cwfp.do");
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/gyglCwgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox" >
					<ul>
						<!-- ȷ�� -->
						<li>
							<a href="#" id="btn_qd"
								onclick="zdfpcw();return false;"
								class="btn_qd" title="��ѡ��Ҫ���䴲λ�Ĳ��ţ������ȷ������ť�Դ�λ�Զ�����">
								<bean:message key="lable.btn_qd" />
							</a>
						</li>
						<li>
							<a href="#" id="btn_fh" onclick="goCwxxtj();return false;"
								class="btn_fh"> ���� </a>
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
					<span>
						��ѯ���
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue"></font> 
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
					<logic:notEmpty name="rsArrList" >
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="pkvArr"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- ��ʾ��Ϣ -->
								<logic:iterate id="v" name="s" offset="1" length="${showNum }">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
								
								<!-- ����䴲λ�� -->
								<logic:iterate id="v" name="s" offset="${xfprs }" length="1">
									<td style="display:none">
										<input type="hidden" id="xfprs_${index }" value="${v }"/>
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglCwglForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
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
											�Ѿ��ɹ�������${message }����λ����ȷ����һ������
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden"  name="next" id="next_cz" value="jg"/>
										<input type="radio" name="next" id="next_jg" value="jg" onclick="$('next_cz').value = this.value" checked="checked"/>
										�鿴�Ѿ�����Զ�����Ĵ�λ
										</br>
										<input type="radio" name="next" id="next_sd" value="sd" onclick="$('next_cz').value = this.value"/>
										ǰ��"��λ�ֶ�����"ģ��
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
					viewTempDiv("��ѡ����һ������","tsxxDiv",350,200);
					if($("message") && $("message").value != ""){
						$("message").value = "";
						$("doType").value = "";	
					}
				</script>
			</logic:present>
			<!-- ��ʾ��Ϣend -->
			
			<!-- ��ȴ� -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- ��ȴ� end-->
		</html:form>
	</body>
</html>