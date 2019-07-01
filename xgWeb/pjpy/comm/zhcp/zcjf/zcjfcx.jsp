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
			allNotEmpThenGo('/xgxt/zhcpJfsq.do?method=zcjfcx');
		}
		
		//�۲�ӷ����
		function zcjfcx(){
			var url = "/xgxt/zhcpJfsq.do?method=zcjfshDetail";
			var doType = "jg";
			showInfo(url,doType,800,600);
		}
		
		//�����ӡ
		function printBb(){
			var url = "/xgxt/zhcpJfsq.do?method=zcjfcx&doType=print";
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
				�Բ�ѯ���ִ��<font color="blue">˫��</font>�����ɲ鿴��ϸ��Ϣ��
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
						<!-- ���� -->
						<li>
							<a href="#" id="btn_dc"
								onclick="setSearchTj();printBb();return false;"
								class="btn_dc">
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
							<tr onclick="rowOnClick(this);" ondblclick="zcjfcx()" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="checkVal"
										<logic:equal name="mklx" value="jg">disabled="disabled"</logic:equal>
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
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
</html>

<script language="javascript" type="text/javascript">  

</script> 
