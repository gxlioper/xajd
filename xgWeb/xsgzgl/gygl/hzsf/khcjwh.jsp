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
		
		//������һ��
		function skipNext(obj){
			var oldID = jQuery(obj).attr('id').split("_");
			//��
			if(event.keyCode==38){
				if(parseInt(oldID[1])>0){
					oldID[1] = parseInt(oldID[1])-1;
				}
				var newID = "#"+oldID[0]+"_"+oldID[1]+"_"+oldID[2]+"_"+oldID[3];
				jQuery(newID).focus().select();
			//��
			}else if(event.keyCode==40){
				if(parseInt(oldID[1])<parseInt(oldID[3])){
					oldID[1] = parseInt(oldID[1])+1;
				}
				var newID = "#"+oldID[0]+"_"+oldID[1]+"_"+oldID[2]+"_"+oldID[3];
				jQuery(newID).focus().select();
			//��
			}else if(event.keyCode==37){
				if(parseInt(oldID[2])>2){
					oldID[2] = parseInt(oldID[2])-1;
				}
				var newID = "#"+oldID[0]+"_"+oldID[1]+"_"+oldID[2]+"_"+oldID[3];
				jQuery(newID).focus().select();
			//��
			}else if(event.keyCode==39){
				if(parseInt(oldID[2])<9){
					oldID[2] = parseInt(oldID[2])+1;
				}
				var newID = "#"+oldID[0]+"_"+oldID[1]+"_"+oldID[2]+"_"+oldID[3];
				jQuery(newID).focus().select();
			}
		}

		//�Զ������ܷ�
		function checkIn(obj,max){
			var fs = obj.value;
			if(parseFloat(fs)>max){
				jQuery(obj).val("");
			}
			var sum = 0;
			var flag = false;
			for(var i=2;i<10;i++){
				var zd = jQuery(obj).parent().parent().find("td").eq(i).find("input[type='text']").val();
				if(""==zd){
					zd = 0;
				}else{
					flag = true;
				}
				sum+=parseFloat(zd);
			}
			if(flag){
				jQuery(obj).parent().parent().find("td").eq(10).text(sum);
			}else{
				jQuery(obj).parent().parent().find("td").eq(10).text("");
			}
		}

		//����
		function searchRs(){
			var nd_num =  jQuery("a[name=a_name_nd]").length;
			var yf_num =  jQuery("a[name=a_name_yf]").length;
			if(scjz==1){
				scjz++;
			}else{
				if(nd_num != 1 || yf_num != 1){
					alertInfo("������ֻ��ѡ��һ����Ⱥ��·ݣ�");
					return false;
				}
			}
			
			var url = "gygl_ldkhgl_ajax.do?method=khcjwh";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		setTimeout("c()","3000");
		
		function c(){
			
			jQuery("[name=tj_nd]").each(function(){
			
				jQuery(this).click( function (){
					
					var nd=(jQuery(this).attr("id")).split("tj_nd_")[1];
					
					jQuery("[name=a_name_nd]").each(
						function(){
							
							var other=(jQuery(this).attr("id")).split("a_id_")[1];
							
							if(nd!=other){
								removeYxtj("nd",other);
							}
						}
					)

				}); 
				
			})
			jQuery("[name=tj_yf]").each(function(){
					
					jQuery(this).click( function (){
						
						var yf=(jQuery(this).attr("id")).split("tj_yf_")[1];

						jQuery("[name=a_name_yf]").each(
							function(){
								
								var other=(jQuery(this).attr("id")).split("a_id_")[1];
								
								if(yf!=other){
									removeYxtj("yf",other);
								}
							}
						)

					}); 
			})
		
		}

		//����
		function khcjBc(){
			var nd_num =  jQuery("a[name=a_name_nd]").length;
			var yf_num =  jQuery("a[name=a_name_yf]").length;
			if(scjz==1){
				scjz++;
			}else{
				if(nd_num != 1 || yf_num != 1){
					alertInfo("������ֻ��ѡ��һ����Ⱥ��·ݣ�");
					return false;
				}
			}
			var len=jQuery("[name=div_pkValue]").length;
			if(len>=1){	
				var yf = (jQuery("a[name=a_name_nd]").attr("id")).split("a_id_")[1]+"-"+(jQuery("a[name=a_name_yf]").attr("id")).split("a_id_")[1];
				var array = jQuery("[name=div_pkValue]");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = yf+"!!@@!!"+jQuery(array[i]).val()+"!!@@!!";
					var flag = false;
					for(var j=2;j<10;j++){
						var zd = jQuery(array[i]).parent().parent().find("td").eq(j).find("input[type='text']").val();
						pkValue+=zd+"!!@@!!";
						if(zd!=null && ""!=zd){
							flag = true;
						}
					}
					if(flag){
						pkValue+=jQuery(array[i]).parent().parent().find("td").eq(10).text()+"!!@@!!";
						str += pkValue+"!!splitOne!!";
					}
				}
				if(""==str){
					alertInfo("û�пɱ�������ݣ�");
					return false;
				}
				var parameter={};
				var url="gygl_ldkhgl_ajax.do?method=khcjBc";
				parameter["pkValue"]=str;							
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
				alertInfo("û�����ݿ��Խ��б��棡");
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
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="refreshForm('gygl_ldkhgl_ldkhgl.do');return false;">����</a>
						</li>
						<li><a href="#" onclick="khcjBc();return false;" class="btn_dc">����</a></li>
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
					<span> ��ѯ���&nbsp;&nbsp; <font color="blue">��ʾ����ȼ��·�(��ѡ��ֻ��ѡһ��)</font></span>
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