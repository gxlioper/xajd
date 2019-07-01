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
			allNotEmpThenGo('/xgxt/gyglZsjg.do?method=qsrzqkCx');
		}
		
		//��������Excel
		function expToExcel(){
			if (confirm("��Ҫ��������ѡ��Ĺ�����������������Excel����ȷ�ϲ���")) {
				var url = "/xgxt/gyglZsjg.do?method=qsrzqkCx&doType=exp";
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		}
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ס�޽�� - ������ס����鿴</a>
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
				������ģ�������ѧ����ס�����ҽ��в�ѯ��<br/>	
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/gyglZsjg">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- �Զ����� -->
						<li>
							<a href="#" id="btn_dc"
								onclick="expToExcel();return false;"
								class="btn_dc">
								����
							</a>
						</li>
						<!-- ���� -->
						<li>
							<a href="#" id="btn_fh"
								onclick="refreshForm('gyglZsjg.do?method=zsxxTj&mklx=fh');return false;"
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
								<td onclick="" width="20px" nowrap>
									<bean:message key="lable.ld" />
								</td>
								<td onclick="" width="10px" nowrap>
									<bean:message key="lable.qs" />
								</td>
								<td onclick="" width="10px" nowrap>
									�Ա�
								</td>
								<td onclick="" colspan="${maxCws }" nowrap>
									�༶������
								</td>
							</tr>
							<tr align="center" style="cursor:hand">
								<td onclick="" colspan="3" nowrap>
									��λ����
								</td>
								<logic:iterate name="cwInfoList" id="cwInfo">
									<td>
										${cwInfo.mc }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- ��ͷ end-->
						
						<!--���� -->
						<logic:iterate name="qsrzInfoList" id="qsInfo" indexId="index">
							<tr onclick="" style="cursor:hand">
								<td rowspan="3">
									${qsInfo.ldmc }
								</td>
								<td rowspan="3">
									${qsInfo.qsh }
								</td>
								<td rowspan="3">
									${qsInfo.xbxz }
								</td>
								<logic:iterate name="qsInfo" property="cwsList" id="rzInfo">
									<td>
										${rzInfo.xm }&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<tr onclick="" style="cursor:hand">
								<logic:iterate name="qsInfo" property="cwsList" id="rzInfo">
									<td>
										${rzInfo.xh }&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<tr onclick="" style="cursor:hand">
								<logic:iterate name="qsInfo" property="cwsList" id="rzInfo">
									<td>
										${rzInfo.bjmc }&nbsp;
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						<!--���� end-->
					</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglZsjgForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
		</html:form>
	</body>
</html>