<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){
			//��ʼ����������
			defaultFreePjlc();
		}
		
		//��ʼ����������
		function defaultFreePjlc(){
			
			//·��
			var url = "general_pjpy_index_ajax.do?method=defaultFreePjlc";
			//����
		 	var parameter = {
				
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
				
			jQuery("#div_free_pjlc").load(
				url,
				parameter,
				function(){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				}
			);
		}
		
		//������������
		function addPjlc(){
			
			var maxPjlc = parseInt(jQuery("#maxPjlc").val())+1;
			var div_id = "div_"+maxPjlc;
			var table_id = "table_"+maxPjlc;
			var td_id = "td_"+maxPjlc;
			
			var divHtml = jQuery("#div_pjpy_pjlc").html();
				divHtml+= "<div id=\""+div_id+"\">";
				divHtml+= "<table id=\""+table_id+"\" width=\"100%\" style=\"cursor:hand\" border=\"1\">";
				divHtml+= "<thead>";
				divHtml+= "<tr>";
				divHtml+= "<td bgcolor=\"#CCFFFF\">";
				divHtml+= "��"+maxPjlc+"��";
				divHtml+= "</td>";
				divHtml+= "</tr>";
				divHtml+= "</thead>";
				
				divHtml+= "<tbody>";
				divHtml+= "<tr>";
				divHtml+= "<td style=\"height: 20px\" id=\""+td_id+"\" onclick=\"clickTd("+maxPjlc+")\">";
				divHtml+= "<div id=\"step_"+div_id+"\">";
				divHtml+= "</div>";
				divHtml+= "</td>";
				divHtml+= "</tr>";
				divHtml+= "</tbody>";
				
				divHtml+= "</table>";
				divHtml+= "</div>";
				
			jQuery("#div_pjpy_pjlc").html(divHtml);
			jQuery("#maxPjlc").val(maxPjlc);
		}
		
		//������������
		function addStep(lcdm,lcmc){
			var setp = jQuery("#step").val();
			if(setp == ""){
				alertError("����ѡ����Ҫ��ӵĲ���Ŷ^_^");
				return false;
			}
			
			var step = jQuery("#step").val();
			var div_id = "step_div_"+step;
			var divHtml = jQuery("#"+div_id).html();
				divHtml+= "<a href=\"#\" id=\"a_pjlc_"+lcdm+"\" onclick=\"editStep('"+lcdm+"','delete');return false;\">";
				divHtml+= "<font color=\"blue\">";
				divHtml+= lcmc;
				divHtml+= "</font>";
				divHtml+= "&nbsp;&nbsp;&nbsp;&nbsp";
				divHtml+= "</a>";
	
			jQuery("#"+div_id).html(divHtml);
			
			editStep(lcdm,"add");
		}
		
		//�ı䲽��
		function editStep(lcdm,lx){
		
			var a_id = "a_"+lcdm;
			var font_id = "font_"+lcdm;
		
			if(lx == "add"){//����
				$(a_id+"_view").style.display="none";
				$(a_id+"_none").style.display="";
			}else{//ɾ��
				var a_pjlc_id = "a_pjlc_"+lcdm;
				jQuery("#"+a_pjlc_id).remove();
				
				$(a_id+"_view").style.display="";
				$(a_id+"_none").style.display="none";
			}
		}
		
		//ѡ�е�Ԫ��
		function clickTd(maxPjlc){
			
			var td_id = "td_"+maxPjlc;
			var obj = $(td_id);	
			var bgc = "#ffcccc";
			
			if(obj.style.backgroundColor == bgc){
				obj.style.backgroundColor="";
				maxPjlc = "";
			}else{
				obj.style.backgroundColor="#ffcccc";
			}
			
			var num = jQuery("td",jQuery("#div_pjpy_pjlc")).length;
			for(var i=0;i<num;i++){
				var td_obj = jQuery("td",jQuery("#div_pjpy_pjlc"))[i];
				
				if(td_obj.id != td_id){
					td_obj.style.backgroundColor="";
				}
			}
			
			jQuery("#step").val(maxPjlc);
		}
		
		//��Ᵽ����������
		function checkSavePjlc(){
		
			//-----------------��֤�ɷ񱣴�-------------------
			var num = jQuery("div",jQuery("#div_pjpy_pjlc")).length;
			
			if(num == 0){
				alertError("�����ٶ���һ������^_^!!");
				return false;
			}
					
			for(var i=0;i<num;i++){
				var obj = jQuery("div",jQuery("#div_pjpy_pjlc")).eq(i);
				var obj_id = obj.attr("id");
				
				if("step" == obj_id.split("_")[0]){
					var step = obj_id.split("_")[2];
					var a_num = jQuery("a",jQuery("#"+obj_id)).length;
					
					if(a_num == 0){
						alertError("��"+step+"������Ϊ�գ���ȷ��^_^!!");
						return false;
					}
				}
			}
			//-----------------��֤�ɷ񱣴� end-------------------
			
			confirmInfo('����ȷ���������Ƶ�����',savePjlc);
		}
		
		//������������
		function savePjlc(tag){
			
			if(tag == "ok"){
				//-----------------������ֵ-------------------
				var num = jQuery("div",jQuery("#div_pjpy_pjlc")).length;
				var lcdm = new Array();//���̴���
				var lcdj = new Array();//���̵ȼ�
				var count = 0;//������
				
				for(var i=0;i<num;i++){
					var obj = jQuery("div",jQuery("#div_pjpy_pjlc")).eq(i);
					var obj_id = obj.attr("id");
					
					if("step" == obj_id.split("_")[0]){
						var step = obj_id.split("_")[2];
						var a_num = jQuery("a",jQuery("#"+obj_id)).length;
						
						for(var j=0;j<a_num;j++){
							var a_obj = jQuery("a",jQuery("#"+obj_id)).eq(j);
							var a_obj_id = a_obj.attr("id");
							
							lcdm[count] = a_obj_id.split("_")[2];
							lcdj[count] = step;
							count++;
						}	
					}
				}
				//-----------------������ֵ end-------------------
				
				var maxPjlc = jQuery("#maxPjlc").val();
				var url = "general_pjpy_index_ajax.do?method=savePjlc";
				
				//����
			 	var parameter = {
			 		"maxPjlc":maxPjlc,
			 		"lcdm":lcdm.join("!!@@!!"),
			 		"lcdj":lcdj.join("!!@@!!")
				};
				
				jQuery.post(url,
					parameter,
					function(result){
						alertInfo(result);
						if(window.dialogArguments.document.getElementById("btn_sx")){
							window.dialogArguments.document.getElementById("btn_sx").click();
						}
					}
				);
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
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
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt"  id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.������Ĭ��չʾ���Ǳ�����ѧ��ѧ�ڵ����ݡ�</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="maxPjlc" value="0"/>
			<input type="hidden" id="step" value=""/>
			
			<table border="1" style="width:100%" class="formlist">
				<tr>
					<td style="width:20%;vertical-align:top!important;">
						<div id="div_free_pjlc">
						
						</div>
					</td>
					<td style="vertical-align:top!important;">
						<div id="div_pjpy_pjlc">
						
						</div>
					</td>
				</tr>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<!-- ���� -->
								<button type="button"  onclick="checkSavePjlc();">
									�� ��
								</button>
								<!-- �ر� -->
								<button type="button"  onclick="Close();return false;" id="btn_gb">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>