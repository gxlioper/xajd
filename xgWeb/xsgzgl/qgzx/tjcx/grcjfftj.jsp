<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�wujian -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
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
			var nd_num =  jQuery("a[name=a_name_nd]").length;
			if(nd_num != 1){
				alertInfo("������ֻ��ѡ��һ����ȣ�");
				return false;
			}
			
			var url = "qgzx_cjtjcx_ajax.do?method=grcjfftjCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}
		
		function expGrcjfftj(){
			var url = "qgzx_cjtjcx_ajax.do?method=expGrcjfftj";
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
				<em>���ĵ�ǰλ�ã�</em><a>�ڹ���ѧ-������-���ͳ�Ʋ�ѯ</a>
			</p>
		</div>
		<!-- ��ť -->
		<div class="toolbox" id="dgncz">
			<div class="buttonbox">
				<ul>
					<li><a href="#" class="btn_dc" onclick="expGrcjfftj();return false;">����</a></li>
				</ul>
			</div>
		</div>
		<html:form action="/qgzx_cjtjcx" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- ������ -->
			<input type="text" name="aa" value="" style="display:none;"/>
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->
			<div class="comp_title">
	      <ul>
	        <li><a href="javascript:$('go').value='';refreshForm('qgzx_cjtjcx.do?method=ydcjfftjCx');"><span>�¶ȳ�𷢷�ͳ��</span></a></li>
	        <li><a href="javascript:$('go').value='';refreshForm('qgzx_cjtjcx.do?method=ndcjfftjCx');"><span>��ȳ�𷢷�ͳ��</span></a></li>
	        <li><a href="javascript:$('go').value='';refreshForm('qgzx_cjtjcx.do?method=bmcjfftjCx');"><span>���ų�𷢷�ͳ��</span></a></li>
	        <li class="ha"><a href="javascript:$('go').value='';refreshForm('qgzx_cjtjcx.do?method=grcjfftjCx');"><span>���˳�𷢷�ͳ��</span></a></li>
	      </ul>
	    </div>
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
					<span> ��ѯ���&nbsp;&nbsp; <font color="blue">��ʾ��������ֻ��ѡ��һ�����</font><logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>