<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/plugins/ringhtMenu/rightMenu.css" /> 
	       
	    <script type="text/javascript" src="js/jquery/plugins/ringhtMenu/contextMenu.js"></script>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��ѯ�����
		function searchRs(){

			jQuery.ajaxSetup({async: false});
		
			//��󼶱����
			creatMaxbzHtml();
			showDefauleMsg();
			defauleNextJxbz();
			
			//����Js
			creatJs();	
			jQuery.ajaxSetup({async: true});
		}	
		
		//��ʾ��ʼ�����
		function showDefauleMsg(){
		
			var lis = jQuery('li',jQuery('#div_maxBz')).length;
			var firstFw = $("firstFw").value;
			
			if(lis == 0 && firstFw==""){
				$("firstFw").value="no";
				$("div_nextBz").innerHTML = "";
				var xn = $("xn").value;
				var msg = xn+"ѧ���ѵ������δ��ʼ������ά��һ��߼������"
				confirmInfo(msg,defauleJxbz);
			}
		}
		
		function defauleNextJxbz(){
			var lis = jQuery('li',jQuery('#div_maxBz')).length;
			if(lis > 0){
				creatNextbzHtml();	
			}		
		}
		
		//��ʼ����ѵ����
		function defauleJxbz(tag){
		
			if(tag == "ok"){
				var url = "/xgxt/jxglJxbz.do?method=jxbzUpdate";
					url+= "&menuId=1_1";
					url+= "&czlx=same";
				showTopWin(url,500,350);
			}
		}
		
		//��󼶱����
		function creatMaxbzHtml(){
			
			var url = "jxglJxbz.do?method=getMaxbzHtml";
			var checkedBzdm = "";
			if($("checkedBzdm")){
				checkedBzdm = $("checkedBzdm").value;
			}
			
			//����
		 	var parameter = {
				"checkedBzdm":checkedBzdm
			};
			
			jQuery("#div_maxBz").load(url,parameter,function(){});
		}	
		
		//�μ������
		function creatNextbzHtml(){
			
			var url = "jxglJxbz.do?method=getNextbzHtml";
			var checkedBzdm = $("checkedBzdm").value;

			//����
		 	var parameter = {
				"checkedBzdm":checkedBzdm
			};
			
			jQuery("#div_nextBz").load(url,parameter,function(){});
		}	
		
		//����Js
		function creatJs(){
			
			var url = "jxglJxbz.do?method=getJxbzJs";
			//����
		 	var parameter = {

			};
			
			jQuery("#div_js").load(url,parameter,function(){});
		}	
		
		//չʾ��ѵ����ҳ��
		function showJxbzUpdate(id,czlx){
			
			var minBz = $("minBz").value;
			var bzdj = id.split("_")[0];
			var url = "";
			
			if(czlx == "next" && bzdj == (minBz-1)){
				url = "/xgxt/jxglJxbz.do?method=jxbzBjfp";
				url+= "&menuId="+id;
				url+= "&czlx="+czlx;
				showTopWin(url,800,600);
			}else{
				url = "/xgxt/jxglJxbz.do?method=jxbzUpdate";
				url+= "&menuId="+id;
				url+= "&czlx="+czlx;
				showTopWin(url,500,350);
			}
		}
		
		//ɾ����ѵ����
		function delJxbz(tag){
			if(tag == "ok"){
				var url = "/xgxt/jxglJxbz.do?method=delJxbz";
				
				var menuId = $("menuId").value;
				//����
			 	var parameter = {
			 		"menuId":menuId
				};
				
				jQuery.post(url,parameter,function(result){doSuccess(result);searchRs();});
			}
			searchRs();
		}	
		
		//�������
		function clickBz(obj){
			var id=obj.id;
			var bzdm = id.split("_")[1];
			var liId = "li_"+bzdm;
			
			$("checkedBzdm").value = bzdm;
			
			var as = getElementsByClass('current',document,'li');
			for(var i=0;i<as.length;i++){
				as[i].className = "";
			}
			
			obj.parentNode.className = "current";
			
			//�μ������
			setTimeout("creatNextbzHtml()",500);
			//����Js
			setTimeout("creatJs()",1000);
		}

		function init(){
			jQuery(function(){
				
				jQuery(this).not(jQuery("a[name=jxbz]")).mouseup(function(){
					
					jQuery("#smartMenu_b").attr("style","display:none");
					
				});
				
			})
		}
		setTimeout("init()",3000);
		</script>
	</head>
	<body onload="searchRs()" >
	
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
				1.����ƶ�������������ϣ�����<font color="blue">�Ҽ�</font>������ִ����ز�����</br>
				<span id="div_help" style="display: none">
				2.ִ��<font color="blue">����ͬ������</font>������������������ͬ���ı��ơ�</br>
				3.ִ��<font color="blue">�����Ӽ�����</font>���������Ӹü�����������ơ�</br>
				4.ִ��<font color="blue">ɾ��������</font>����ɾ���ñ��ƣ�����<font color="blue">��������</font>�Ѿ�<font color="blue">��ѵѧ��</font>��</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/jxglJxbz">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="menuId" value="" />	
			<input type="hidden" id="minBz" value="${minBz }" />
			<input type="hidden" id="xn" value="${xn }" />
			<input type="hidden" id="firstFw" value="${firstFw }" />
			<!-- ������ end-->
			
			<!-- �������� -->
			<div style="display:none">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �������� end-->
			
			<!-- ����DIV -->
			<div class="main_function">
				<!-- ��󼶱���� -->
				<div class="function_list01" id="div_maxBz">

  				</div>
  				<!-- ��󼶱���� end -->
  				
  				<!--  -->
  				<div class="function_list02" id="div_nextBz" style="overflow: scroll;overflow-x:hidden; height: 500px">
				  	
				</div>
  				<!--  -->
  				 <div class="function_list03"></div>
			</div>
			<!-- ����DIV end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		
		<!-- Js Div -->
		<div id="div_js">

		</div>
		<!-- Js Div end -->
	</body>
</html>