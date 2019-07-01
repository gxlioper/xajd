<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/jcrcgl/js/jcrcgl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" defer="defer">
			//��ʼ��
			function onShow(){ 
				searchRs();
			}
		</script>
	</head>
	<body >

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyglnew_jcrcgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="showDialog('���Ӽ���ճ�', 600, 380, 'gyglnew_jcrcgl.do?method=jcrcglZj');return false;" class="btn_zj">����</a>
						</li>
						<logic:notEqual value="33333" name="xxdm">						
							<li>
								<a href="#" onclick="showModi();return false;" class="btn_xg">�޸�</a>
							</li>
						</logic:notEqual>
						<li>
							<a href="#" onclick="deleteJcrcgl();return false;" class="btn_sc">ɾ��</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">�鿴</a>
						</li>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="tijiao();return false;" class="btn_shtg">�ύ</a>
						</li>
						<li>
							<a href="#" onclick="qxtj();return false;" class="btn_shbtg">����</a>
						</li>
						<%--<li>
							<a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a>
						</li>
						--%></logic:equal>
						<li><a href="#" class="btn_dc" onclick="jcrcglExportConfig();return false;">����</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
					--%></ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <font color="blue">��ʾ���ѱ�ά���ļ���ճ̲����ٽ��в���</font><logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyglJcrcglForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--				$('choose').className="hide";--%>
<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
