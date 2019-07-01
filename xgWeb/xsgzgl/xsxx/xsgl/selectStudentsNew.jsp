<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"学生信息列表",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"xsxx_xsgl.do?method=selectStudentsNew&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'13%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'13%'},
				   {label:'性别',name:'xb', index: 'xb',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'15%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'18%'},
				   {label:'学籍类别',name:'xjlb', index: 'xjlb',width:'8%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#li_sc").hide();
				jQuery("#li_bc").hide();
				var map = {};
				map["xzxsKey"]= jQuery("#xzxsKey").val();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
				
			
			function searchRs(){
				var map = getSuperSearch();
				map["xzxs"] = jQuery("#xzxs").val();
				map["xzxsKey"]= jQuery("#xzxsKey").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			//页签切换
			function selectTab(obj,xzxs){
				jQuery("#xzxs").val(xzxs);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				if('dxzxs' == xzxs){
					jQuery("#li_xz").show();
					jQuery("#li_sc").hide();
					jQuery("#li_bc").hide();
				}else{
					jQuery("#li_sc").show();
					jQuery("#li_bc").show();
					jQuery("#li_xz").hide();
				}
				searchRs();
			}	

			function selStudent(){	
				var rows = jQuery("#dataTable").getSeletRow();
				var xzxsKey = jQuery("#xzxsKey").val();
				if (rows.length == 0){
					var map = getSuperSearch();
					var url = "xsxx_xsgl.do?method=selectStudentsNew&type=save&xzxsKey="+xzxsKey;
					//高级查询
					url +="&searchTj=";
					url +=map["searchTj"];
					url +="&searchTjz=";
					url +=map["searchTjz"];
					url +="&mhcx_lx=";
					url +=map["mhcx_lx"];
					url +="&searchLx=";
					url +=map["searchLx"];

					//模糊查询
					url +="&input_mhcx=";
					url +=map["input_mhcx"];
					url +="&mhcx_lx=";
					url +=map["mhcx_lx"];
					url +="&path=";
					url +=map["path"];
					
					confirmInfo("您确定要选择【全部】的记录吗?",function(ty){
						if(ty=="ok"){
							jQuery.post(url,{selected:'all'},function(data){
								alertInfo(data["message"]);
								jQuery("#xzxsKey").val(data["xzxsKey"]);
								jQuery("#yxzts").html(data["yxzxss"]);	
								jQuery("#dataTable").reloadGrid();
							},'json');
						}
					});					
					
				} else {
					var ids = jQuery("#dataTable").getSeletIds();
					confirmInfo("您确定要选择"+ids.length +"条记录吗?",function(ty){
						if(ty=="ok"){

							jQuery.post("xsxx_xsgl.do?method=selectStudentsNew&type=save&xzxsKey="+xzxsKey,{values:ids.toString()},function(data){
								alertInfo(data["message"]);
								jQuery("#xzxsKey").val(data["xzxsKey"]);
								jQuery("#yxzts").html(data["yxzxss"]);								
								jQuery("#dataTable").reloadGrid();
							},'json');
						}
					});					
				}
			}

			function delStudent(){
				var rows = jQuery("#dataTable").getSeletRow();
				var xzxsKey = jQuery("#xzxsKey").val();
				if (rows.length == 0){
					var map = getSuperSearch();
					var url = "xsxx_xsgl.do?method=selectStudentsNew&type=del&xzxsKey="+xzxsKey;
					//高级查询
					url +="&searchTj=";
					url +=map["searchTj"];
					url +="&searchTjz=";
					url +=map["searchTjz"];
					url +="&mhcx_lx=";
					url +=map["mhcx_lx"];
					url +="&searchLx=";
					url +=map["searchLx"];

					//模糊查询
					url +="&input_mhcx=";
					url +=map["input_mhcx"];
					url +="&mhcx_lx=";
					url +=map["mhcx_lx"];
					url +="&path=";
					url +=map["path"];
					
					confirmInfo("您确定要删除【全部】的记录吗?",function(ty){
						if(ty=="ok"){
							jQuery.post(url,{selected:'all'},function(data){
								alertInfo(data["message"]);
								jQuery("#xzxsKey").val(data["xzxsKey"]);
								jQuery("#yxzts").html(data["yxzxss"]);	
								jQuery("#dataTable").reloadGrid();
							},'json');
						}
					});					
					
				} else {
					var ids = jQuery("#dataTable").getSeletIds();
					confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
						if(ty=="ok"){

							jQuery.post("xsxx_xsgl.do?method=selectStudentsNew&type=del&xzxsKey="+xzxsKey,{values:ids.toString()},function(data){
								alertInfo(data["message"]);
								jQuery("#xzxsKey").val(data["xzxsKey"]);
								jQuery("#yxzts").html(data["yxzxss"]);	
								jQuery("#dataTable").reloadGrid();
							},'json');
						}
					});					
				}

			}
			
			function cfmStudent(){
				var yxzxss = jQuery("#yxzts").html();
				confirmInfo("您确定选择"+yxzxss +"个学生吗?",function(ty){
					if(ty=="ok"){
						var gotoPath = jQuery("#gotoPath").val();
						var xzxsKey = jQuery("#xzxsKey").val();
						if (gotoPath.split("?").length > 1){
							gotoPath = gotoPath+"&xzxsKey="+xzxsKey;
						} else {
							gotoPath = gotoPath+"?xzxsKey="+xzxsKey;
						}
						var api = frameElement.api;
						
						if (api){
							if (api.get('childDialog')){
								api.reload(api.get('parentDialog') ,gotoPath);
							} else {
								var W = api.opener;
								W.location=gotoPath;			
							}
						} else if (parent.window){
							parent.window.document.location=gotoPath;						
						}
						
						iFClose();
					}
				});		
			}
		</script>
	</head>

	<body>
		<!-- 提示信息 START-->
		<div class="prompt">
			<h3>
				<span>提示：</span>
			</h3>
			<p>检索后，不选择任何学生，即全选</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- 提示信息 END-->
		<html:form action="/xsxx_xsgl">
			<input type="hidden" value="${gotoPath}" id="gotoPath"/>
			<input type="hidden" id="xzxs" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<div class="buttonbox">
				  <ul>
					<li id="li_xz">
						<a href="javascript:void(0);" onclick="selStudent();return false;" class="btn_zj" id="xzButton">选择</a>
					</li>
					<li id="li_sc">
						<a href="javascript:void(0);" onclick="delStudent();return false;" class="btn_sc" id="scButton">删除</a>
					</li>
					<li id="li_bc">
						<a href="javascript:void(0);" onclick="cfmStudent();return false;" class="btn_sz">确定</a>
					</li>
				  </ul>
				</div>
				<!-- 按钮 -->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
				<div class="comp_title" id="comp_title">
			      <ul style="width:70%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dxzxs');"><span>待选择学生</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'yxzxs');"><span>已选择学生</span></a></li>
			      </ul>
			                        已选择学生总数：<span style="color:red;" id="yxzts">${yxzxss}</span>人
			    </div>
		</html:form>
		<div>
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
