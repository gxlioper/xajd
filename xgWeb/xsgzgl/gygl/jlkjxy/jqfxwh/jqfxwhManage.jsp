<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script type="text/javascript" src="xsgzgl/gygl/jlkjxy/jqfxwh/js/jqfxwhManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">	

		function addfxgl() {
							
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('基础设置未初始化，请联系管理员！');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("当前未开放申请，请联系管理员！");
				return false;
			}	

			var map = getSuperSearch();
			//var jsonStr = JSON.stringify(map);									
			var rows = jQuery("#dataTable").getSeletRow();							
			if(rows.length == 0 /*|| rows.length > 1*/){								
				jQuery.post("jlkjxy_jqfxwh.do?method=getCountNum",map,function(data){					
					if(data == "0"){
						showAlertDivLayer("您选择的学生记录为返校状态！");
					}
					if(data != "0"){						
						var url = 'jlkjxy_jqfxwh.do?method=plxsJqfx&countNum='+data;							
						var title = "批量返校处理";
						return showDialog(title, 368, 258, url);
					}
				});																														
			}
			/*错误业务判断，1529 jz注释掉*/
			if(rows.length > 1){								
				var xhs = new Array();						 			
			    for (var i=0;i<rows.length;i++){
				     if (jQuery.trim(rows[i]["fxzt"]) == "1"){
					     showAlertDivLayer("已有学生记录为返校状态！");
					     return false;
					     break; 
					     } 
					     xhs.push(rows["xh"]);					    
				  }
			    var countNum = rows.length;  
				showDialog("批量返校处理",368,258,"jlkjxy_jqfxwh.do?method=pldgxsJqfx&countNum="+countNum);	
				return false;										
			}
			var fxzt = rows[0]["fxzt"];					
			
						
			/*var url = 'jlkjxy_jqfxwh.do?method=addxsJqfx&id='+rows[0]["id"]+'&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];*/
			/*	错误业务判断，1529 jz注释掉
			if(jQuery.trim(fxzt) == "0"){				
		        var url = 'jlkjxy_jqfxwh.do?method=addxsJqfx&id='
				    + rows[0]["id"]+'&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    
		    }else{	
		    	// 批量审核保存				    
		    	var url = 'jlkjxy_jqfxwh.do?method=addxsJqfx&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    	
		    }
		    */
		    if(jQuery.trim(fxzt) != "-1"){			
				var url = 'jlkjxy_jqfxwh.do?method=addxsJqfx&id='+rows[0]["id"]+'&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];
			}else{
				var url = 'jlkjxy_jqfxwh.do?method=addxsJqfx&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];
			}
			var title = "返校处理";
			showDialog(title, 720, 360, url);
			
			
					
		}

				
		// 批量保存假期未返校
		function savePlfxsh(fxsj){	

			var map = getSuperSearch();
			jQuery.post("jlkjxy_jqfxwh.do?method=plxsJqfx&type=save"+'&fxsj='+fxsj,map,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');				
			
			/**var url = "jlkjxy_jqfxwh.do?method=plxsJqfx&type=save";	
		    ajaxSubFormWithFun("jlkjxy_jqfxwhForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
		    	 }else{
		    		 showAlert(data["message"]);		    		 
		    	 }
			});	*/
			
		}	
		</script>
	</head>
			
	<body>
	<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
			<!-- 隐藏域 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown="showHelpDiv()">使用帮助</a>
				</p>
			</div>

			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					如果选中返校状态为：已返校的记录，则提示该学生已返校，请重新选择;
				</p>
				<a class="close" title="隐藏"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			
		<html:form action="/jlkjxy_jqfxwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="addfxgl();return false;" 
							>返校</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="addwfxgl();return false;" class="btn_sc">未返校</a>
						</li>
						</logic:equal>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:equal>		
						<li><a href="#" class="btn_ck" onclick="viewfxgl();return false;">查看</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>假期返校列表 &nbsp; 学年/学期： <font color="blue">${xn}/${xq}</font> &nbsp;&nbsp;返校类别： <font color="blue">${fxmc}</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
