<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"xsxxDyxjZpjg.do?method=getZpjgList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'班级',name:'bjmc', index: 'bjdm'},
					   {label:'学年',name:'xn', index: 'xn'},
					   {label:'学期',name:'xqmc', index: 'xq'},
					   {label:'评定等级',name:'djmc', index: 'djmc'},
					   {label:'评议时间',name:'pysj',index:'pysj'},
					   {label:'数据来源',name:'sjly',index:'sjly',hidden:true}
					],
					sortname:"pysj",
					sortorder:"desc"
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog("查看申请表",600,320,"xsxxDyxjZpjg.do?method=zpjgView&id="+id);
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要修改的记录！");
				} else {
					if (rows[0]["sjly"] == "1"){
						alertInfo("流程数据不能修改！");
						return false;
					}
					showDialog('修改',700,450,'xsxxDyxjZpjg.do?method=zpjgEdit&id='+rows[0]["id"]);
				}
			}

			function delZpjg(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sjly'] == '1') {
							showAlertDivLayer("流程数据不能删除！");
							return false;
						}
					}
					showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
						jQuery.post("xsxxDyxjZpjg.do?method=zpjgDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addZpjg(){
				showDialog('增加德育自评',700,450,'xsxxDyxjZpjg.do?method=zpjgAdd');;
			}
			
			function importZpjg(){
				toImportData("xsxx_dyxj");
				return false;
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='xsxx_dyxj';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='xsxx_dyxj';
				setSearchTj();//设置高级查询条件
				//dcclbh,导出功能编号
				var url = "xsxxDyxjZpjg.do?method=export&dcclbh=" + DCCLBH;
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

			function exporDyxj(){
				var ids = jQuery("#dataTable").getSeletIds();
				var len = ids.length;
				if (len == 1) {
					var url = "xsxxDyxjZpjg.do?method=getZpjgxjb";
					url += "&id=" + ids+"&flag=jg";
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("请选择您要下载的记录！");
					return false;
				} else {
					var url = "xsxxDyxjZpjg.do?method=getZpjgTy";
					url += "&value=" + ids+"&flag=jg";
					window.open(url);
				}
			}

			function exporDyHz(){
				var xnobj = jQuery("a[name='tj_xn'][class='selectedValue']");
				var xqobj = jQuery("a[name='tj_xq'][class='selectedValue']");
				var bjobj = jQuery("a[name='a_bj_mc'][class='selectedValue']");
				var xn = "";
				var xq = "";
				var bjdms = "";
				if(xnobj.length != 1){
				  showAlertDivLayer("在高级查询条件中仅且只能选择一个学年！");
				  return false;
				}else{
				  xn = jQuery(xnobj).attr("id").replace("tj_xn_","");
				}

				if(xqobj.length != 1){
				  showAlertDivLayer("在高级查询条件中仅且只能选择一个学期！");
				  return false;
				}else{
				 xq = jQuery(xqobj).attr("id").replace("tj_xq_","");
				}

				if(bjobj.length == 0){
				  showAlertDivLayer("在高级查询条件中请选择一个或一个以上的班级！");
				  return false;
				}else{
			      if(bjobj.length == 1){
				     var str = jQuery(bjobj).attr("id");
				      if(str.indexOf("bj_mc_xs_") != -1){
				    		bjdms =str.replace("bj_mc_xs_","");
				    	}else{
				    		bjdms =str.replace("bj_mc_yc_","");
				      }
				      var url = "xsxxDyxjZpjg.do?method=getbjzpjghzb";
					  url += "&bjdm=" + encodeURI(encodeURI(bjdms))+"&xn="+xn+"&xq="+xq;
					  window.open(url);
			      }else{
			    	  for(var i=0;i<bjobj.length;i++){
				    	var str = jQuery(bjobj[i]).attr("id");
				    	if(str.indexOf("bj_mc_xs_") != -1){
				    		bjdms +=str.replace("bj_mc_xs_","")+",";
				    	}else{
				    		bjdms +=str.replace("bj_mc_yc_","")+",";
				    	}
					  }
			    		var url = "xsxxDyxjZpjg.do?method=getZpjghzTy";
						url += "&value=" + encodeURI(encodeURI(bjdms))+"&xn="+xn+"&xq="+xq;
						window.open(url);
			      }
				  
				}

			
			}

			function exporDyXyHz(){
				var xnobj = jQuery("a[name='tj_xn'][class='selectedValue']");
				var xqobj = jQuery("a[name='tj_xq'][class='selectedValue']");
				var xyobj = jQuery("a[name='a_xy_mc'][class='selectedValue']");
				var xn = "";
				var xq = "";
				var xydms = "";
				var xymcs ="";
				if(xnobj.length != 1){
				  showAlertDivLayer("在高级查询条件中仅且只能选择一个学年！");
				  return false;
				}else{
				  xn = jQuery(xnobj).attr("id").replace("tj_xn_","");
				}

				if(xqobj.length != 1){
				  showAlertDivLayer("在高级查询条件中仅且只能选择一个学期！");
				  return false;
				}else{
				 xq = jQuery(xqobj).attr("id").replace("tj_xq_","");
				}

				if(xyobj.length == 0){
				  showAlertDivLayer("在高级查询条件中请选择一个或一个以上的学院！");
				  return false;
				}else{
			      if(xyobj.length == 1){
				     var str = jQuery(xyobj).attr("id");
				      if(str.indexOf("xy_mc_xs_") != -1){
				    	  xydms =str.replace("xy_mc_xs_","");
				    	  xymcs = jQuery(xyobj).text();
				    	 
				    	}else{
				    		xydms =str.replace("xy_mc_yc_","");
				    		 xymcs = jQuery(xyobj).text();
				      }
				      var url = "xsxxDyxjZpjg.do?method=getxyzpjghzb";
					  url += "&xydm=" + xydms+"&xn="+xn+"&xq="+xq+"&xymc="+encodeURI(encodeURI(xymcs));
					  window.open(url);
			      }else{
			    	  for(var i=0;i<xyobj.length;i++){
				    	var str = jQuery(xyobj[i]).attr("id");
				    	var str1 = jQuery(xyobj[i]).text();
				    	if(str.indexOf("xy_mc_xs_") != -1){
				    		xydms +=str.replace("xy_mc_xs_","")+",";
				    		xymcs +=str1+","
				    	}else{
				    		xydms +=str.replace("xy_mc_yc_","")+",";
				    		xymcs +=str1+","
				    	}
					  }
			    		var url = "xsxxDyxjZpjg.do?method=getZpjgxyhzTy";
						url += "&value=" + xydms+"&xn="+xn+"&xq="+xq+"&value1="+encodeURI(encodeURI(xymcs));
						window.open(url);
			      }
				  
				}

			
			}

			function exporDyXxHz(){
				var xnobj = jQuery("a[name='tj_xn'][class='selectedValue']");
				var xqobj = jQuery("a[name='tj_xq'][class='selectedValue']");
				var xyobj = jQuery("a[name='a_xy_mc'][class='selectedValue']");
				var xn = "";
				var xq = "";
				var xydms = "";
				var xymcs ="";
				if(xnobj.length != 1){
				  showAlertDivLayer("在高级查询条件中仅且只能选择一个学年！");
				  return false;
				}else{
				  xn = jQuery(xnobj).attr("id").replace("tj_xn_","");
				}

				if(xqobj.length != 1){
				  showAlertDivLayer("在高级查询条件中仅且只能选择一个学期！");
				  return false;
				}else{
				 xq = jQuery(xqobj).attr("id").replace("tj_xq_","");
				}
			      var url = "xsxxDyxjZpjg.do?method=getxxzpjghzb";
				  url += "&xn="+xn+"&xq="+xq
				  window.open(url);
			}

			function exportXsDymx(){
				var ids = jQuery("#dataTable").getSeletIds();
				var len = ids.length;
			    if (len == 0) {
					showAlertDivLayer("请选择您要下载的记录！");
					return false;
				} else {
					var url = "xsxxDyxjZpjg.do?method=getZpjgMxb";
					url += "&value=" + ids;
					window.open(url);
				}
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/xsxxDyxjDyzp" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="addZpjg()" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" onclick="delZpjg();" class="btn_sc">删除</a></li>						
							<li><a href="javascript:void(0);" onclick="importZpjg();" class="btn_dr">导入</a></li>						
							<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>	
							<li><a href="javascript:void(0);" onclick="exporDyxj();" class="btn_dy">德育小结</a></li>
							<li><a href="javascript:void(0);" onclick="exportXsDymx();" class="btn_dy">德育小结汇总表</a></li>
							<li><a href="javascript:void(0);" onclick="exporDyHz();" class="btn_dy">班级汇总表</a></li>
							<li><a href="javascript:void(0);" onclick="exporDyXyHz();" class="btn_dy">学院汇总表</a></li>		
							<li><a href="javascript:void(0);" onclick="exporDyXxHz();" class="btn_dy">学校汇总表</a></li>		
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>德育自评结果列表  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
