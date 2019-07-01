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
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=sdfpcw&doType=query');
		}
		
		//��λ�Զ�����
		function cwsdfp(){
		
			var num = document.getElementsByName("checkVal").length;
			var id = new Array();
			var flag = false;
			
			var n = 0;
			var blog=false;
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("checkVal")[i];
				if(obj.checked){
					blog=true;
				}
			}
			if(!blog){
				alert("����ѡ����Ҫ�Զ����䴲λ�Ĳ���!");
			}
			
			saveUpdate('/xgxt/gyglCwgl.do?method=cwzdfp&doType=zdfp','');
			
		}
		
		//����λ�����ѡ��ѧ��
		function xscwfp(){
			var url='/xgxt/gyglCwgl.do?method=sdfpcw&doType=cwfp';
			if(curr_row != null){
				var xh=curr_row.getElementsByTagName('input')[0].value;
				var xm=curr_row.getElementsByTagName('input')[1].value;
				if(confirm("ȷ��Ҫ��"+xm+"ͬѧ�����䵽"+$('ldmc').value+","+$('qsh').value+"����"+$('cwh').value+"�Ŵ�λ��")){
					refreshForm(url + '&xh='+xh,700,500);
					return true;
				}else {
					return false;
				}
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		
		//��һ������
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next == "gb"){//�ر�
				hiddenMessage(true,true);
			}else if(next == "jg"){//�����ѯ
				refreshForm("gyglQsgl.do?method=qszdfpjg&go=go");
			}else if(next == "sd"){//�ֶ�����
				refreshForm("gyglQsgl.do?method=qssdfp");
			}
		}
		
		function fhSdfp(){
			//��ѯ�����
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=cwsdfp&doType=query');
		}
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��λ���� - ѧ����λ����</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/gyglCwgl">
			<!-- ��ʾ��Ϣ start-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ѡ��һ����Ҫ���䴲λ��ѧ��,Ȼ����"ȷ�Ϸ���"��ť����ѡ��Ĵ�λ�����ѧ����
				</p>
				<a class="close" title="����"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
		<!-- ��ʾ��Ϣ end-->
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="ldmc" id="ldmc" value="${cwxxMap.ldmc }"/>
			<input type="hidden" name="cs" id="cs" value="${cwxxMap.cs }"/>
			<input type="hidden" name="qsh" id="qsh" value="${cwxxMap.qsh }"/>
			<input type="hidden" name="cwh" id="cwh" value="${cwxxMap.cwh }"/>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ȷ�� -->
						<li>
							<a href="#" id="btn_qd"
								onclick="xscwfp();return false;"
								class="btn_qd">
								ȷ�Ϸ���
							</a>
						</li>
						<li>
							<a href="#" id="btn_fh"
								onclick="fhSdfp();return false;"
								class="btn_fh">
								����
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
						<font color="blue">(���䴲λ 
						<bean:message key="lable.ld" />��${cwxxMap.ldmc}&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:message key="lable.cs" />��${cwxxMap.cs}&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:message key="lable.qs" />��${cwxxMap.qsh}&nbsp;&nbsp;&nbsp;&nbsp;
						��λ�ţ�${cwxxMap.cwh})</font> 
					</span>
				</h3>
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
<%--							<td width="5px">--%>
<%--								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />--%>
<%--							</td>--%>
							<logic:iterate id="tit" name="topTr" offset="1" length="5">
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
								<td align="center" style="display:none">
									<input type="hidden" id="xh_${index }"
										name="xh"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
									<input type="hidden" id="xm_${index }"
										name="xm"  
										value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"/>
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

			<logic:present name="result">
				<logic:equal name="result" value="true">
					<script>
						alert("�����ɹ���");
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
			</logic:present>
			<!-- ��ʾ��Ϣend -->
		</html:form>
	</body>
</html>