<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function searchRs(){
		
			allNotEmpThenGo('/xgxt/rcswSwbl.do?method=swblCx&xmdm='+$("xmdm").value);
		}
		
		function delSwbl(){
			var primaryKey=document.getElementsByName("primary_key");
			var flag=false;
			for(i=0;i<primaryKey.length;i++){
				if(primaryKey[i].checked){
					flag=true;
				}
			}
			if(!flag){
				alertError("���ȹ�ѡ��Ҫɾ��������!");
				return false;
			}
			var message="�˲�������ɾ���ѹ�ѡ��<br/>���ݣ��Ƿ����?";
			confirmInfo(message,del);
		}
		
		function del(tag){
			if(tag=="ok"){
				refreshForm("/xgxt/rcswSwbl.do?method=swblCx&doType=del");
			}
		}
		
		function modi(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alertError('��ѡ��Ҫ�޸ĵ������У�');
					return false;
				}
			}
			
		function showView(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alertError('��ѡ��Ҫ�鿴�������У�');
					return false;
				}
			}
		</script>
	</head>
	<body onload="">

		<html:form action="/rcswSwbl" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}"/>
			<!-- ������ -->
			
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="showTopWin('/xgxt/rcswSwbl.do?method=swblSq&xmdm=${xmdm}',800,600);return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="modi('/xgxt/rcswSwbl.do?method=swblXg&xmdm=${xmdm}&doType=update',800,600);return false;" class="btn_xg">�޸�</a></li>	
						<li><a href="#" onclick="delSwbl();return false;" class="btn_sc">ɾ��</a></li>
						<li><a href="#" onclick="showView('/xgxt/rcswSwbl.do?method=swblXg&xmdm=${xmdm}&doType=view',800,600);return false;" class="btn_cx">�鿴</a></li>		
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>  
						
					</span>
				</h3>

				
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr"  offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" 
								ondblclick="showView('/xgxt/rcswSwbl.do?method=swblXg&xmdm=${xmdm}&doType=view',800,600);return false;" style="cursor:hand">
									<td>
										<input type="checkbox" name="primary_key" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
											/>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						
						<!-- ������ -->
							<%@ include file="/comm/noRows.jsp"%>
						<!-- ������ end-->
						</tbody>
						
					</table>
					</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswSwblForm"></jsp:include>
				
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				$("doType").value="";
				alertError("${message}!");	
			</script>
		</logic:present>
	</body>
	<%@ include file="/comm/loading.jsp"%>
</html>
