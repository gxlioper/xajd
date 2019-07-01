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
			allNotEmpThenGo('/xgxt/zhcpJfsq.do?method=zcjfsh');
		}
		
		//�۲�ӷ����
		function zcjfsh(){
			var url = "/xgxt/zhcpJfsq.do?method=zcjfshDetail";
			var doType = "sh";
			showInfo(url,doType,800,600);
		}
		
		//�ύ�ӷ����
		function submitJfsh(){
			for(var i=0;i<document.getElementsByName("checkVal").length;i++){
				if(document.getElementsByName("checkVal")[i].checked==true&&document.getElementById("shr_"+i).value=="δ���"){
					alert("ѡ�е�ѡ���л�������δ��˵���Ŀ������˺����ύ��")
					return false;
				}
			}
			url = "/xgxt/zhcpJfsq.do?method=zcjfsh";
			var doType = "submit";
			sumitInfo(url,doType);
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
				ֻ��ִ�й�<font color="blue">�ύ</font>��������˷֣��Żᱻ��ʽ��¼��
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<html:form action="/zhcpJfsq">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��� -->
						<li>
							<a href="#" id="btn_sh"
								onclick="zcjfsh();return false;"
								class="btn_shtg">
								���
							</a>
						</li>
						<!-- �ύ -->
						<li>
							<a href="#" id="btn_tj"
								onclick="submitJfsh();return false;"
								class="btn_sh">
								�ύ
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
				
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr">
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
										name="checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
									<input type="hidden" id="shr_${index }" value="<logic:iterate id="v" name="s" offset="4" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- ��ʾ��Ϣ -->
								<logic:iterate id="v" name="s">
									<td align="left">
										${v }${xmNum }
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
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zhcpZcjfForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
	<div id="div_confirm"  style="display:none">
		<div class="open_prompt">
	    	<table width="100%" border="0" class="table01">
		        <tr>
		        	<td width="109"><div class="img img_why02"></div></td>
		            <th><p id="p_confirm"></p></th>
		        </tr>
		        <tr>
		        	<td colspan="2" align="center" class="btn01">
		        		<input  name="ȷ��" class="button" value="ȷ ��" onclick="bef();closeWindown();"/>
		        		<input  name="ȡ��" class="button" value="ȡ ��" onclick="closeWindown();return false;" />
		        		<input type="hidden" id="qr" value="no"/>
		        	</td>
		        </tr>
	        </table>
		</div>
	</div>

</html>
<script language="javascript" type="text/javascript"> 
<%--window.confirm = function(txt,ddd) {--%>
<%--	if($("p_confirm")){--%>
<%--		$("p_confirm").innerHTML = txt;--%>
<%--		tipsWindown('ϵͳ��ʾ','id:div_confirm','340','120','true','','true','id');--%>
<%--		return abc();--%>
<%--	}--%>
<%--} --%>
<%----%>
<%--function bef(){--%>
<%--	setTimeout("$('qr').value='yes'",300);--%>
<%--}--%>
<%----%>
<%--function abc(){--%>
<%----%>
<%--	var qr = $("qr").value;--%>
<%--	alert(qr);--%>
<%--	if(qr == "yes"){--%>
<%--		return true;--%>
<%--	}else {--%>
<%--		setTimeout("abc()",500);--%>
<%--	}--%>
<%--}--%>
</script> 