<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){
			//��ʼ���Ѷ�����������
			defaultCustomPjlc();
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
<%--			<p class="help">--%>
<%--				<a href="#" onclick="return false;" --%>
<%--					onmouseover ="showHelpDiv()"--%>
<%--					onmouseout="showHelpDiv()"--%>
<%--				>--%>
<%--				��������</a><img src="<%=stylePath%>/images/ico_new02.gif" />--%>
<%--			</p>--%>
		</div>
		<!-- ���� end-->
		
<%--		<!-- ��ʾ��Ϣ end-->--%>
<%--		<div class="prompt">--%>
<%--			<h3>--%>
<%--				<span>ϵͳ��ʾ��</span>--%>
<%--			</h3>--%>
<%--			<p>--%>
<%--				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>--%>
<%--				<span id="div_help" style="display: none">--%>
<%--				1.������Ĭ��չʾ���Ǳ�����ѧ��ѧ�ڵ����ݡ�</br>--%>
<%--				</span>--%>
<%--			</p>--%>
<%--		</div>--%>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input  id="btn_sx" onclick="onShow()" style="display:none"/>
			<input type="hidden" id="lcdj_submit"/>
			
			<table width="100%">
				<tr>
					<td>
						<!-- ������ʾ����ʼ -->
						<div class="main_box" id="div_custom_pjlc">
							
						</div>
						<!-- ������ʾ�� end-->
					</td>
				</tr>
			</table>	
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>