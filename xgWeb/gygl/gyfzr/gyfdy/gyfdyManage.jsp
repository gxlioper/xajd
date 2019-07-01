<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyfdyDWR.js'></script>
		<script language="javascript">
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglGyfdy.do?method=gyfdyManage&doType=query');
		}
		
		//��ʾ��Ͻ¥����Ϣ
		function showGyld(yhm){
		
			var html="";
			html+="<div class='tab' style='width:98%;height:180px;overflow-y:auto;overflow-x:hidden'><table  class='formlist'><thead><tr><th colspan='3'><span>";
			html+="��Ͻ¥��";
			html+="</span></th></tr></thead>";
								
			dwr.engine.setAsync(false);
			gyfdyDWR.getGyfdyGxld(yhm,function(data){
				var len=data.length;
				html+="<tbody><tr>";
				var n=0;
				for(i=1;i<=data.length;i++){
					html+="<td width='33%'>"+data[i-1].ldmc+"</td>";
					if(i%3==0){
						html+="</tr><tr>";
						n++;
					}
				}
				
				if(len%3!=0){
					for(i=1;i<=3-(len%3);i++){
						html+="<td></td>"
					}
				}
				
				if(n<3){
					for(i=1;i<3-n;i++){
						html+="<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
					}
				}
				
				html+="</tr></tbody><tfoot>";
				html+="<tr>	<td colspan='3' align='right'>"
				html+="<button onclick='hiddenMessage(true,true);'>�ر�</button>"
				html+="</td></tr></tfoot></table></div>";
			});
			dwr.engine.setAsync(true);
			$("gxldDiv").innerHTML=html;
			viewTempDiv('��Ԣ����Ա��Ͻ¥��','gxldDiv',360,220);
		}
		
		function ldfp(){
			var url="/xgxt/gyglGyfdy.do?method=gyfdyManage";
			refreshForm('/xgxt/gyglGyfdy.do?method=gyldfdy');
		}
		</script>
	</head>
	<body>

		<html:form action="/gyglGyfdy" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
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
					���"¥������"��ť���ԶԹ�Ԣ����Ա���й�Ͻ¥����������������Ͻ¥�������Բ鿴��Ԣ����Ա��Ͻ¥����Ϣ��
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			
			<!-- ģ������ -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="ldfp();return false" class="btn_sx">
								¥������
							</a>
						</li>
					</ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>  
					</span>
				</h3>

				
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0"  indexId="index">
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
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									
									<logic:iterate id="v" name="s" offset="0" length="3">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									
									<td nowrap>
										<logic:iterate id="v" name="s" offset="0" length="1">
										<a href="#" onclick="showGyld('${v}');return false;">
										<U></logic:iterate>
										<font color="blue">
										<logic:iterate id="v" name="s" offset="3" length="1">
											<bean:write name="v" />
										</logic:iterate>	
										</font>
										</U>
										</a>
									</td>
									
								</tr>
							</logic:iterate>
						
						</logic:notEmpty>
						<%@ include file="/comm/noRows.jsp"%>
						</tbody>
					</table>
					</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglGyfdyForm"></jsp:include>

			</div>
			<div id="gxldDiv" style="display: none">
					
			</div>
			
			
		</html:form>
		
	</body>
</html>
