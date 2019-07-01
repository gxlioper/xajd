<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
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

		function searchRs(){
			var url = "gygl_gypywh_ajax.do?method=pyqsCx";
			url+="&xn="+jQuery("#xn").val();
			url+="&xqdm="+jQuery("#xqdm").val();
			url+="&pylbdm="+jQuery("#pylbdm").val();
			url+="&pydx="+jQuery("#pydx").val();
			url+="&pysj="+jQuery("#pysj").val();
			var ie = "10.0";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		
		//����������Ϣ
		function gypyZj(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!splitOne!!";
				}
				var parameter={}
				var url="gygl_gypywh_ajax.do?method=gypyZj";
				parameter["pkValue"]=str;
				parameter["xn"]=escape(jQuery("#xn").val());
				parameter["xqdm"]=escape(jQuery("#xqdm").val());
				parameter["pylbdm"]=escape(jQuery("#pylbdm").val());
				parameter["pydx"]=escape(jQuery("#pydx").val());
				parameter["pysj"]=escape(jQuery("#pysj").val());
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
						alertInfo(result,function(tag){
				 			if(tag=="ok"){
								if (parent.window) {
									var api = frameElement.api,W = api.opener;
									jQuery(W.document).find('#gb').click();
									refershParent();
								}
				 				return false;
				 			}
				 		});
						//searchRs();
				 		return false;
					}
				);
				jQuery.ajaxSetup({async:true});
			}else{
				alertInfo("�빴ѡ��Ҫ��ӵ�������Ϣ��",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		</script>
	</head>
	<body onunload="window.dialogArguments.document.getElementById('search_go').onclick();">
		<html:form action="/gygl_gypywh" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="xn" id="xn" value="${rs.xn }" />
			<input type="hidden" name="xqdm" id="xqdm" value="${rs.xqdm }" />
			<input type="hidden" name="pylbdm" id="pylbdm" value="${rs.pylbdm }" />
			<input type="hidden" name="pydx" id="pydx" value="${rs.pydx }" />
			<input type="hidden" name="pysj" id="pysj" value="${rs.pysj }" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_zj" onclick="gypyZj();return false;">����</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="Close();window.dialogArguments.document.getElementById('search_go').onclick();">�ر�</a>
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
						ѧ��:<font class="blue">${rs.xn }</font>
						ѧ��:<font class="blue">${rs.xqmc }</font>
						�������:<font class="blue">${rs.pylbmc }</font>
						����ʱ��:<font class="blue">${rs.pysj }</font>
					</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gypywhForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>