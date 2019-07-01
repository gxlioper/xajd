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
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_xsxx_dljc.do?method=searchDljc";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//重置登录检测
		function resetDljc(){
		
			var num=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(num == 0){//整体
			
				//年级
				var nj_num = jQuery("a[name=a_name_nj]").length; 			  		
				//学院
				var xy_num = jQuery("a[name=a_name_xy]").length; 		
				//专业
				var zy_num = jQuery("a[name=a_name_zy]").length; 
				//班级
				var bj_num = jQuery("a[name=a_name_bj]").length; 
		
				confirmInfo("您确定是否将<font color='blue'>过滤条件中所指定的学生</font>重置信息完善状态为否",resetDljcNoChecked);
				
			}else{//勾选
				confirmInfo("您确定是否将<font color='blue'>所勾选的学生</font>重置信息完善状态为否",resetDljcChecked);
			}
		}
		
		//重置登录检测【未勾选】
		function resetDljcNoChecked(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				//年级
				var nj = new Array();  
				var i = 0;				  
				jQuery("a[name=a_name_nj]").each(function(){
					var nj_id = jQuery(this).attr("id");
					nj[i] = nj_id.replace("a_id_","");
					i++;
				});
				
				//学院
				var xy = new Array(); 
				i = 0;			  
				jQuery("a[name=a_name_xy]").each(function(){
					var xy_id = jQuery(this).attr("id");
					xy[i] = xy_id.replace("a_id_","");
					i++;
				});
		
				//专业
				var zy = new Array(); 
				i = 0;					  
				jQuery("a[name=a_name_zy]").each(function(){
					var zy_id = jQuery(this).attr("id");
					zy[i] = zy_id.replace("a_id_","");
					i++;
				});
		
				//班级
				var bj = new Array();  				  
				jQuery("a[name=a_name_bj]").each(function(){
					var bj_id = jQuery(this).attr("id");
					bj[i] = bj_id.replace("a_id_","");
					i++;
				});
				
				var url = "general_xsxx_dljc.do?method=resetDljc";
				
				//参数
			 	var parameter = {
			 		"array_nj":nj.join("!!array!!"),
			 		"array_xy":xy.join("!!array!!"),
			 		"array_zy":zy.join("!!array!!"),
			 		"array_bj":bj.join("!!array!!"),
			 		"str_checked":"no"
				};
		
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						searchRs();
					}
				);
		
				jQuery.ajaxSetup({async:true});
			}
		}
		
		//重置登录检测【勾选】
		function resetDljcChecked(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				var xh = new Array();//学号
				var i = 0;			
				jQuery("input[name=primarykey_checkVal]:checked").each(function(){
					xh[i] = jQuery(this).val();
					i++;
				});
				
				var url = "general_xsxx_dljc.do?method=resetDljc";
				
				//参数
			 	var parameter = {
			 		"array_pkValue":xh.join("!!array!!"),
			 		"str_checked":"yes"
				};
		
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						searchRs();
					}
				);
		
				jQuery.ajaxSetup({async:true});
			}
		}
		
		//完善信息设置
		function showDljcSetting(){
			var url = "general_xsxx_dljc.do?method=dljcSetting";
			showTopWin(url,"800","600");
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/general_xsxx" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="resetDljc();return false;" id="btn_csh" class="btn_csh">
									重置检测
								</a>
							</li>
							<li>
								<a href="#" onclick="showDljcSetting();return false;" id="btn_sz" class="btn_sz">
									完善字段设置
								</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 流程跟踪弹出层 -->
			<div id="div_lcgz" style="display:none">
				<div class="open_win01">
					sss
				</div>
			</div>
			<!-- 流程跟踪弹出层 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>