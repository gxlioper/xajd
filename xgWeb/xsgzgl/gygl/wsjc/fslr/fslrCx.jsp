<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xgxt.action.Base"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function searchRs(){
			var url = "gyglnew_fslr_ajax.do?method=fslrCx";
			var ie = "10.0";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}
		
		//��ѯ�����
		function searchFslr(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
				
				var url="gyglnew_fslr.do?method=fslrCz";
				
				url+="&pkValue="+pkValue;
				
				refreshForm(url);
			}else{
				
				alertInfo("�빴ѡһ����Ҫ¼������ݣ�");
				
				return false;
			}
		}
		function Fsdr(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
				var url="gyglnew_fslr.do?method=fsdr";
				url+="&pkValue="+pkValue;
				var sty = "toolbar=no,location=no,directories=no,status=yes";
				sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=400,top=100";
				sty += ",left=200";
				//window.open(url,'',sty);
				showDialog('����', 600, 400, url);
			}else{
				alertInfo("�빴ѡһ����Ҫ��������ݣ�");
				return false;
			}
		}
		
		jQuery(function(){
			onShow();
		})
	</script>
	</head>
	<body >

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>


		<html:form action="/gyglnew_fslr" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					ϵͳֻ��ʾ��ǰѧ��ѧ�ڵļ���ճ���Ϣ��
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="searchFslr()" class="btn_ccg">¼��</a></li>
						<logic:notEqual name="xxdm" value="33333">
						  <logic:notEqual name="xxdm" value="12721">
							<li><a href="#" onclick="Fsdr()" class="btn_dr">����</a></li>
						  </logic:notEqual>
					  	</logic:notEqual>
					</ul>
				</div>
				</logic:equal>
				
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
					<span> ��ѯ���&nbsp;&nbsp;<font color="blue">��ʾ���ѱ��ύ�ļ���ճ̲��ܽ��в���</font> <logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=fslrForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
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
