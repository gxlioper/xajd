<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglZsjg.do?method=ydglManage&doType=query');
		}
		
		
		
		//ѧ����λ�춯
		function xscwyd(){
			//��¼����
			var num = document.getElementsByName("checkVal").length;
			
			var id = new Array();
			
			var blog=false;
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("checkVal")[i];
				if(obj.checked){
					blog=true;
				}
			}
			if(!blog){
				alert("����ѡ����Ҫ�춯��λ��ѧ��!");
				return false;
			}
			
			saveUpdate('/xgxt/gyglZsjg.do?method=xscwyd&doType=cwyd','');
			
		}
		
		function goSsydtj(){
			refreshForm("gygl_zsjg_ydtj.do");
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ס�޽�� - �����춯����</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/gyglZsjg">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ȷ�� -->
						<li>
							<a href="#" id="btn_down"
								onclick="xscwyd();return false;"
								class="btn_down">
								�춯
							</a>
						</li>
						<li>
							<a href="#" id="btn_fh"
								onclick="goSsydtj();return false;"
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
							<logic:iterate id="tit" name="topTr" offset="1">
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
										name="checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- ��ʾ��Ϣ -->
								<logic:iterate id="v" name="s" offset="1" >
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglZsjgForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->

			<logic:present name="result">
				<logic:equal name="result" value="true">
				<script>
					alert("�����ɹ�!");
				</script>
				</logic:equal>
				<logic:equal name="result" value="false">
				<script>
					alert("����ʧ��!");
				</script>
				</logic:equal>
			</logic:present>
			<!-- ��ʾ��Ϣend -->
		</html:form>
	</body>
</html>