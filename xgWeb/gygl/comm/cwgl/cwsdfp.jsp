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
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=cwsdfp&doType=query');
		}
		
		//���ݽ������Ϣ�������ӽ��봲λ����
		function xscwfp(num){
			//��λ��Ϣ ����
			var pkValue=$('cwzj_'+num).value;
			
			refreshForm('/xgxt/gyglCwgl.do?method=sdfpcw&pkValue='+pkValue);
		}
		
		//ȡ���������
		function qxfpcw(){
			
			var pkvArr = document.getElementsByName("pkvArr");
			
			var xh = new Array();
			
			var blog=false;
			for(var i=0;i<pkvArr.length;i++){
				var obj =pkvArr[i];
				if(obj.checked){
					blog=true;
				}
			}
			
			if(!blog){
				alert("��ѡ����Ҫȡ������Ĵ�λ��Ϣ!");
				return false;
			}
			
			saveUpdate('/xgxt/gyglCwgl.do?method=cwsdfp&doType=qxfp','');
			
		}
		
		//��ѧ������
		function axsfpcw(){
			refreshForm('/xgxt/gyglCwgl.do?method=axsfpcw&doType=zdfp');
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
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��λ���� - ��λ�ֶ�����</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/gyglCwgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
<%--						<li>--%>
<%--							<a href="#" id="btn_fp"--%>
<%--								onclick="axsfpcw();return false;"--%>
<%--								class="btn_sx" title="�������λ��������ť�����ֶ�������λ����">--%>
<%--								��λ����--%>
<%--							</a>--%>
<%--						</li>--%>
						<li>
							<a href="#" id="btn_qx"
								onclick="qxfpcw();return false;"
								class="btn_qx">
								ȡ������
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
			<div class="formbox">		
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
							<logic:iterate id="tit" name="topTr" offset="3">
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
									<!-- ��ŷ��䴲λʱ������ (lddm-cs-qsh-cwh) -->
									<input type="hidden" id="cwzj_${index }"
										 value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"/>
									<!-- ���ѧ��(xh) -->
									<input type="checkbox" id="xh_${index }"
										name="pkvArr" value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"
										 <logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>/>
								</td>
								
								<!-- ��ʾ��Ϣ -->
								<logic:iterate id="v" name="s" offset="3" length="7">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="10" length="1">
									<td align="left">
									<!-- �жϴ�λ�Ƿ����(δ����) -->
									<logic:equal name="v" value="�ɷ���"> 
										<a href="#" onclick="xscwfp('${index }');return false;" title="Ϊ�ô�λ������סѧ��">
											<font color="blue"><U>${v }</U></font>
										</a>
									</logic:equal>
									<!-- �жϴ�λ�Ƿ����(�ѷ���) -->
									<logic:notEqual name="v" value="�ɷ���"> 
											${v }
									</logic:notEqual>
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
						if($("doType")){
							$("doType").value="";	
						}
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("����ʧ�ܣ�");
						if($("doType")){
							$("doType").value="";	
						}
					</script>
				</logic:equal>
			</logic:present>
			<!-- ��ʾ��Ϣend -->
		</html:form>
	</body>
</html>