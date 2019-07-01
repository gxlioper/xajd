<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script type='text/javascript' src="js/comm/message.js"></script>	
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//��ѯ
		function searchRs(){
			var url = "jxgl_jxbxgl_ajax.do?method=bxmdCx&pkValue="+jQuery("#pkValue").val();
			var ie = "10.0";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		function bxmdCz(type){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
				var parameter={}
				var url="jxgl_jxbxgl_ajax.do?method=bxmdCz";	
				parameter["pkValue"]=escape(jQuery("#pkValue").val());
				parameter["xh"]=str;
				parameter["doType"]=type;
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
							alertInfo(result,function(tag){
								if(tag=="ok"){
									searchRs();
								}
							});
					}
				);
				jQuery.ajaxSetup({async:true});
			}else{
				alertInfo("�빴ѡ��Ҫ���������ݣ�",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}

		//����
		function exportConfig(){
			var DCCLBH='jxgl_jxbxgl.do';
			customExport(DCCLBH, exportData);
		}
		function exportData(){
			var DCCLBH='jxgl_jxbxgl.do';
			setSearchTj();//���ø߼���ѯ����
			var url = "jxgl_jxbxgl.do?method=exportData&dcclbh=" + DCCLBH+
			        "&pkValue="+jQuery("#pkValue").val();//dcclbh,�������ܱ��
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
				��ѡѧ����¼����<font color="blue">���</font>��<font color="blue">����</font>ά��ѧ����ѵ������Ϣ
				</span>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/jxgl_jxcjgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" id="jxid" name="jxid" value="${rs.jxid }"/>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="refreshForm('jxgl_jxkhgl_jxbxgl.do');return false;">����</a>
							<a href="#" class="btn_shtg" onclick="bxmdCz('add');return false;">���</a>
							<a href="#" class="btn_shbtg" onclick="bxmdCz('del');return false;">ȡ��</a>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
						</li>
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
					<span>
							��ѵ��Ϣ:<font class="blue">${rs.jxmc }</font>&nbsp;
							�������:<font class="blue">${rs.bxlbmc }</font>&nbsp;
							�������:<font class="blue">${rs.jtbxmc }</font>
					</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxglJxbxglForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>