<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){
			//初始化自由流程
			defaultFreePjlc();
		}
		
		//初始化自由流程
		function defaultFreePjlc(){
			
			//路径
			var url = "general_pjpy_index_ajax.do?method=defaultFreePjlc";
			//参数
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
		
		//增加评奖流程
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
				divHtml+= "第"+maxPjlc+"步";
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
		
		//单击自由流程
		function addStep(lcdm,lcmc){
			var setp = jQuery("#step").val();
			if(setp == ""){
				alertError("请您选中需要添加的步骤哦^_^");
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
		
		//改变步骤
		function editStep(lcdm,lx){
		
			var a_id = "a_"+lcdm;
			var font_id = "font_"+lcdm;
		
			if(lx == "add"){//增加
				$(a_id+"_view").style.display="none";
				$(a_id+"_none").style.display="";
			}else{//删除
				var a_pjlc_id = "a_pjlc_"+lcdm;
				jQuery("#"+a_pjlc_id).remove();
				
				$(a_id+"_view").style.display="";
				$(a_id+"_none").style.display="none";
			}
		}
		
		//选中单元格
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
		
		//检测保存评奖流程
		function checkSavePjlc(){
		
			//-----------------验证可否保存-------------------
			var num = jQuery("div",jQuery("#div_pjpy_pjlc")).length;
			
			if(num == 0){
				alertError("请至少定制一个流程^_^!!");
				return false;
			}
					
			for(var i=0;i<num;i++){
				var obj = jQuery("div",jQuery("#div_pjpy_pjlc")).eq(i);
				var obj_id = obj.attr("id");
				
				if("step" == obj_id.split("_")[0]){
					var step = obj_id.split("_")[2];
					var a_num = jQuery("a",jQuery("#"+obj_id)).length;
					
					if(a_num == 0){
						alertError("第"+step+"步流程为空，请确认^_^!!");
						return false;
					}
				}
			}
			//-----------------验证可否保存 end-------------------
			
			confirmInfo('请您确认您所定制的流程',savePjlc);
		}
		
		//保存评奖流程
		function savePjlc(tag){
			
			if(tag == "ok"){
				//-----------------参数赋值-------------------
				var num = jQuery("div",jQuery("#div_pjpy_pjlc")).length;
				var lcdm = new Array();//流程代码
				var lcdj = new Array();//流程等级
				var count = 0;//计数器
				
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
				//-----------------参数赋值 end-------------------
				
				var maxPjlc = jQuery("#maxPjlc").val();
				var url = "general_pjpy_index_ajax.do?method=savePjlc";
				
				//参数
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
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt"  id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.本功能默认展示的是本评奖学年学期的数据。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
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
								<!-- 保存 -->
								<button type="button"  onclick="checkSavePjlc();">
									保 存
								</button>
								<!-- 关闭 -->
								<button type="button"  onclick="Close();return false;" id="btn_gb">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>