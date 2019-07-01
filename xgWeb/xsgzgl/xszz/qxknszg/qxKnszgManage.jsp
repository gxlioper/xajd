<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
						
		jQuery(function(){
			
			var gridSetting = {
					caption:"困难生结果查询",
					pager:"pager",
					url:"xszz_qxknszggl.do?method=qxKnszgManage&type=query",
					colList:[
						{label:'key',name:'guid', index: 'guid',key:true ,hidden:true},	
						{label:'sfqxrd',name:'sfqxrd', index: 'sfqxrd',key:true ,hidden:true},												 						 
						{label:'学号',name:'xh', index: 'xh',width:'10%'},
						{label:'姓名 ',name:'xm', index: 'xm',width:'7%'},
						{label:'性别 ',name:'xb', index: 'xb',width:'9%'},
						{label:'学院',name:'xymc', index: 'xymc',width:'21%'},
						{label:'班级',name:'bjmc', index: 'bjmc',width:'21%'},
						{label:'认定档次',name:'dcmc', index: 'dcmc',width:'13%'}
					],
					sortname: "sqsj",
				 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
						
		});

		function searchRs(){
			
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
			
		}


		function qxrd() {	
							
			var map = getSuperSearch();									
			var rows = jQuery("#dataTable").getSeletRow();							
			if(rows.length == 0){								
				jQuery.post("xszz_qxknszggl.do?method=getCountNum&qxtype=all",map,function(data){					
					if(data == "0"){
						showAlertDivLayer(" 您选择的学生困难生资格已取消！");
					}
					if(data != "0"){						
						var url = 'xszz_qxknszggl.do?method=cancelKnsrdzg&countNum='+data;							
						var title = "批量取消学生困难生资格";
						showDialog(title, 368, 258, url);
					}
				});																														
			}			
			
			if(rows.length > 1){												
				var guids = new Array();						 			
			    for (var i=0;i<rows.length;i++){			    
				     if (jQuery.trim(rows[i]["sfqxrd"]) == "1"){
					     showAlertDivLayer(" 您选择的学生困难生资格已取消！");
					     return false;
					     break; 
					  } 
				     guids.push(rows["guid"]);					    
				}
			    var countNum = rows.length;  
			   
				showDialog("批量取消学生困难生资格",368,258,"xszz_qxknszggl.do?method=cancelKnsrdzg&qxtype=more&countNum="+countNum);					
				return false;														
			}										
			 var countNum = rows.length;  				
			 var url = 'xszz_qxknszggl.do?method=cancelKnsrdzg&qxtype=one'+'&guid='+rows[0]["guid"]+'&countNum='+countNum;				    		
			 var title = "取消认定";
			 showDialog(title, 459,258, url);	
			 					
		}

		// 批量审核保存
		function saveMorePlqxrd(qxyy,qxtype){
												
			var rows = jQuery("#dataTable").getSeletRow();
			var guids = new Array();	
			jQuery.each(rows,function(i,row){
				guids.push(row["guid"]);			
			});						
			jQuery.post("xszz_qxknszggl.do?method=cancelKnsrdzg&type=save"+'&qxyy='+encodeURI(encodeURI(qxyy))+'&qxtype='+qxtype,{				
				guids:guids
				},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');
			
		}

		// 批量保存假期未返校
		function saveAllPlqxrd(qxyy,qxtype){
										
			var map = getSuperSearch();		
			jQuery.post("xszz_qxknszggl.do?method=cancelKnsrdzg&type=save"+'&qxyy='+encodeURI(encodeURI(qxyy))+'&qxtype='+qxtype,map,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');	
															
		}	
		
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					学生或老师提交的困难生认定，经过审核最终通过的申请结果会进入此菜单<br/>
					用户也可在此处直接维护困难生名单					
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/xszz_qxknszggl" >			
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_knsjgb"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="qxrd();return false;" class="btn_xg">取消认定</a></li>				
						</logic:equal>													
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 困难生结果查询 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
