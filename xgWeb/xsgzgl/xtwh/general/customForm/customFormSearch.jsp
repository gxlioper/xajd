<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�������-->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/customForm.js"></script>
		<script language="javascript" defer="defer">
		jQuery(function(){
			//�����Զ��x��ԃ�Y��
			createCustomSearch();
			//�����Զ��x��ԃ�Y�������ֶ�
			createSearchCzzdTable();
		});
		
		//��ʾ�޸�Div
		function showEditDiv(zd,zdm){
		
		}
		</script>
	</head>
	<body ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
			
		<html:form action="/customForm" method="post">
			<!-- ������ begin-->
			
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ��ID -->
			<input type="hidden" name="str_form_id" id="hidden_form_id" value="${formInfo.form_id }"/>
			<!-- ��ԃҕ�D -->
			<input type="hidden" name="str_search_view" id="hidden_search_view" value="${formInfo.search_view}"/>
			
			<!-- ������ end-->
			
			<!-- �@ʾ�^��begin -->
			<div class="formbox"> 
				<!--����start-->
				<h3 class="datetitle_01"> <span>��ʾ�ֶ�����</span> </h3>
				<!--����end-->
				
				<!-- ����begin -->
				<table border="1" bordercolor="#000000" style="width:100%;height: 100%">
					<tr>
						<td width="20%" valign="top">
							<div id="div_czzd_${formInfo.search_view}"
								style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">
							
							</div>
						</td>
						<td valign="top">
							<div id="div_customSearch">
					
							</div>
						</td>
					</tr>
				</table>			
				<!-- ����end -->
				
			</div>
			<!-- �@ʾ�^��end-->
			
			<!-- ������Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>