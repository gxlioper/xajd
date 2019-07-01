<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>	
		--%><%@ include file="/syscommon/head.ini"%>	
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/xsgzgl/jxgl/general/jxxxwh/jxmd.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});


		//��ѯ�����
		function searchRs(){
			//��ť����

			//controlBtn();
			var url = "lsjzgl_cxLsjz_ajax.do?method=cxLsjzmdAjax";
		
			var ie = "ie";

			var otherValue = [ie,jQuery("#sjid").val()];
			
			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","2000");
		
			
		}

		//
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//���ؾ�ѵ����
		function fhJxjz(){
			var url = "jxjzgl_lsjzgl_cxLsjz.do";
			window.location.href=url;
		}

		//������ʷ��������
		function dcLsjzmd(){
			var url="lsjzgl_cxLsjz_ajax.do?method=dcLsjzmd";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		function lsjzglExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("lsjzgl_cxLsjz_ajax.do", lsjzglExportData);
			}
			
		
			
		// ��������
		function lsjzglExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "lsjzgl_cxLsjz_ajax.do?method=lsjzglExportData&dcclbh=" + "lsjzgl_cxLsjz_ajax.do";//dcclbh,�������ܱ��
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
				<span>
				��������Ϊ������ǰ��ѯ�����µı����Ƶ�ѧ����Ϣ��
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="jxjzgl_cxJxjz.do?method=cxJzmd" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="jzid" name="jzid" value="${model.jzid }"/>
			<input type="hidden" id="sjid" name="sjid" value="${model.sjid }"/>
			<input type="hidden" id="jxid" name="jxid" value="${model.sjid }"/>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="fhJxjz();return false;">����</a>
						</li>
						<li><a href="#" class="btn_qx" onclick="lsjzglExportConfig();return false;">�Զ��嵼��</a></li>
					</ul>
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
					<span> ��ѵ���ƣ� ${jxxxwhModel.jxmc} &nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxjzglFrom"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
	</body>
</html>