<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/qgzx/gwgl/gwsq.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});
		
		function yrdwgwsqExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("qgzx_gwgl_ajax.do", yrdwgwsqExportData);
			}
			
		
			
		// ��������
		function yrdwgwsqExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "qgzx_gwgl_ajax.do?method=gwsqExportData&dcclbh=" + "qgzx_gwgl_ajax.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
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


		<html:form action="/qgzx_gwgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="kycz" id="kycz" value="${rs.kycz }" />
			
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="showAdd();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="#" onclick="showModi('update');return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="#" onclick="deleteGwsq();return false;" class="btn_sc">ɾ��</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_ck" onclick="showModi('view');return false;">�鿴</a>
						</li>

						<li><a href="#" onclick="yrdwgwsqExportConfig();return false;" class="btn_dc">����</a></li>

						<%--<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">��������</a></li>
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
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxGwglForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>