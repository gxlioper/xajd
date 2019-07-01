<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		var scjz = jQuery("#scjz").val();
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});

		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		function searchRs(){
			var xn_num =  jQuery("a[name=a_name_xn]").length;
			if(scjz==1){
				scjz++;
			}else{
				if(xn_num != 1){
					alertInfo("������ֻ��ѡ��һ��ѧ�꣡");
					return false;
				}
			}
			var url = "gygl_ldkhgl_ajax.do?method=ldkhgl";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		setTimeout("c()","3000");
		
		function c(){
			
			jQuery("[name=tj_xn]").each(function(){
			
				jQuery(this).click( function (){
					
					var xn=(jQuery(this).attr("id")).split("tj_xn_")[1];
					
					jQuery("[name=a_name_xn]").each(
						function(){
							
							var other=(jQuery(this).attr("id")).split("a_id_")[1];
							
							if(xn!=other){
								removeYxtj("xn",other);
							}
						}
					)

				}); 
			})
		
		}

		//ά���ɼ�
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url="gygl_ldkhgl.do?method=khcjwh";		
				url+="&pkValue="+pkValue;
				document.forms[0].action = url;
				document.forms[0].submit();
			}else{
				alertInfo("�빴ѡһ����¼����ά����");
				return false;
			}
		}

		//����
		function exp(){
			var url = "gygl_ldkhgl_ajax.do?method=exp";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}

		//�����ɼ�
		function expCj(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url = "gygl_ldkhgl_ajax.do?method=expCj";
				url+="&pkValue="+pkValue;
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}else{
				alertInfo("�빴ѡһ����¼���гɼ�������");
				return false;
			}
			
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


		<html:form action="/gygl_ldkhgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="scjz" id="scjz" value="${scjz }" />
			<input type="text" name="aa" id="aa" value="" style="display: none;" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_xg" onclick="showView();return false;">ά���ɼ�</a>
						</li>
						<%-- <li><a href="#" onclick="exp();return false;" class="btn_dc">������ǰҳ��</a></li> --%>
						<li><a href="#" onclick="expCj();return false;" class="btn_dc">�����ɼ�</a></li>
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
					<span> ��ѯ���&nbsp;&nbsp; <font color="blue">��ʾ��ѧ��(��ѡ��ֻ��ѡһ��)</font></span>
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