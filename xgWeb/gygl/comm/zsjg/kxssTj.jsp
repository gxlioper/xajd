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
			allNotEmpThenGo('/xgxt/gyglZsjg.do?method=kxssTj');
		}
		
		function kqsxx(lddm){
			var url="/xgxt/gyglZsjg.do?method=kqsxx&lddm="+lddm;
			showTopWin(url,800,600);
		}
		
		function xqsxx(lddm){
			var url="/xgxt/gyglZsjg.do?method=xqsxx&lddm="+lddm;
			showTopWin(url,800,600);
		}
		
		function printKxsstj(dclx){
			
			var url='/xgxt/gyglZsjg.do?method=printKxss&dclx='+dclx;
			if(dclx!="kxqs"){
				if(curr_row){
					var lddm= curr_row.getElementsByTagName('input')[0].value;
					url+="&lddm="+lddm;
					
				}else{
					alert("����ѡ����Ҫͳ�Ƶ�¥��!");
					return false;
				}
			}
			
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			
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
		
		<!-- ��ʾ��Ϣ end-->		
		<!-- ���� end-->
		<html:form action="/gyglZsjg">
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx}"/>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<div class="buttonbox">
					<ul>	
						<li><a href="#" class="btn_tj" onclick="printKxsstj('kxqs');return false;">��������ͳ��</a></li>
						
						<li><a href="#" class="btn_dc" onclick="printKxsstj('kqs');return false;">��������Ϣͳ��</a></li>
					
						<li><a href="#" class="btn_dy" onclick="printKxsstj('xqs');return false;">��������Ϣͳ��</a></li>
					</ul>
				</div>
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
						<logic:empty name="rs">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="rs">
							&nbsp;&nbsp;<font color="blue"></font> 
						</logic:notEmpty>
						
<%--						<font color="blue"></font>--%>
					</span>
				</h3>
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
						
							<logic:equal name="czxq" value="��">
								<logic:iterate id="tit" name="topTr" offset="1" length='1'>
								<td id="<bean:write name="tit" property="en"/>"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
							</logic:equal>
							<logic:equal name="czyq" value="��">
								<logic:iterate id="tit" name="topTr" offset="2"  length='1'>
								<td id="<bean:write name="tit" property="en"/>"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
							</logic:equal>
							
						
							<logic:iterate id="tit" name="topTr" offset="3">
								<td id="<bean:write name="tit" property="en"/>"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:notEmpty name="rs" >
						<logic:iterate name="rs" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<!-- ��ʾ��Ϣ -->
								<td style="display:none">
								<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="${v}"/>
								</logic:iterate>
								</td>
								
								<logic:equal name="czxq" value="��">
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="left" nowrap="nowrap">
										${v }
									</td>
								</logic:iterate>
								</logic:equal>
								
								<logic:equal name="czyq" value="��">
								<logic:iterate id="v" name="s" offset="2" length="1">
									<td align="left" nowrap="nowrap">
										${v }
									</td>
								</logic:iterate>
								</logic:equal>
								<logic:iterate id="v" name="s" offset="3" length="2">
									<td align="left" nowrap="nowrap">
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="0">
											0
										</logic:equal>
										<logic:notEqual name="v" value="0">
										<a href="#" onclick="xqsxx('<logic:iterate id="x" name="s" offset="0" length="1">${x}</logic:iterate>')"><font color="blue"><U>${v }</U></font></a>
										</logic:notEqual>
									</td>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="6" length="1">
									<td align="left" nowrap="nowrap">
									<logic:equal name="v" value="0">
											0
									</logic:equal>
									<logic:notEqual name="v" value="0">
									<a href="#" onclick="kqsxx('<logic:iterate id="x" name="s" offset="0" length="1">${x}</logic:iterate>')"><font color="blue"><U>${v }</U></font></a>
									</logic:notEqual>
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
			
			
			<div id="fpxx" style="display: none" style="width:650px">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
											������Ϣ
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<p id="Bmfpxx" class="tab" style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
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
		</html:form>
	</body>
</html>