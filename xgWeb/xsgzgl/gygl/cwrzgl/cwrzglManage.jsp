<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		
		<script type='text/javascript' src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/xgservice.js"></script>
	<script type='text/javascript' src="js/moveDiv.js"></script>
	<script type='text/javascript' src="js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	<script type='text/javascript' src="js/messageFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
	<script type="text/javascript" src="js/comm/ymPrompt.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('gyglnew_cwrzgl_cwrz.do');
		}
		
		//��λ�ֶ�����
		function goQssdfpByHand(){
			if(!curr_row){
				alertInfo('��ѡ��һ��');
				return false;
			}
			var pkValue = curr_row.getElementsByTagName('input')[0].value;
			$("primarykey_checkVal").value=pkValue;
			$("nj").value=pkValue.split("_")[1];
			$("xydm").value=pkValue.split("_")[0];
			
			var url="gyglnew_cwrzgl.do?method=cwrzglSdfp";
			refreshForm(url);
			BatAlert.showTips('���ڲ��������Ե�...');
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
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
				
		<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				��ѯ����еġ��ܴ�λ����ָ��������꼶<bean:message key="lable.xsgzyxpzxy" />���Ա�Ĵ�λ������<br/>
				������=����ס����+δ��ס�������ܴ�λ��=����ס��λ��+δ��ס��λ����
				��������ס�������͡�����ס��λ������һ��ʱ����˲�ѧ������<bean:message key="lable.xb" />�༶�봲λ����<bean:message key="lable.xb" />�༶�Ƿ�һ��
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/gyglnew_cwrzgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" id="nj" name="nj"/>
			<input type="hidden" id="xydm" name="xydm"/>
			<input type="hidden" id="primarykey_checkVal" name="primarykey_checkVal"/>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>					
						<logic:equal value="yes" name="writeAble">
						<!-- �ֶ����� -->
						<li>
							<a href="#" id="btn_xg"
								onclick="goQssdfpByHand();return false;"
								class="btn_sh">
								ѧ����ס
							</a>
						</li>
						</logic:equal>
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
							<td width="5px" style="display: none;">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort_hc(this,1)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:empty name="rsArrList">
					  	<%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
					 	</tr>
						<%}%>
					 </logic:empty>
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" style="cursor:hand">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center" width="5px" style="display: none;">
									<input type="checkbox" id="pk_${index }"
									 value="${v }"/>
								</td>
							</logic:iterate>
							<!-- ��ʾ��Ϣ -->
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<!--���� end-->
					<!-- ������ -->
					<%--<%@ include file="/comm/noRows.jsp"%>
					--%><!-- ������ end-->
				</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewCwrzglForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
		</html:form>
	</body>
</html>