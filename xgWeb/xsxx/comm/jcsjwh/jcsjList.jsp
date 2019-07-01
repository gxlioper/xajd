<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/comm/jcsjwh/js/jcsj.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
			var gridSettingXy = {
				caption:"部门/<bean:message key="lable.xb" />信息列表",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"jcsj.do?method=jcsjList&type=query",
				colList:[
				   {label:'<bean:message key="lable.bmxy" />代码',name:'xydm', index: 'xydm',width:'13%',key:true},
				   {label:'<bean:message key="lable.bmxy" />名称',name:'xymc', index: 'xymc',width:'13%'},
				   {label:'部门类别',name:'bmlbmc', index: 'bmlb',width:'6%'},
				   {label:'专业数',name:'zys', index: 'zys',width:'6%'},
				   {label:'班级数',name:'bjs', index: 'bjs',width:'6%'},
				   {label:'学生数',name:'xss', index: 'xss',width:'6%'}
				],
				sortname: "xydm",
			 	sortorder: "asc"
			}
			var gridSettingZy = {
					caption:"专业信息列表",
					pager:"pager",
					multiselect:true,
					rowNum:10,
					url:"jcsj.do?method=jcsjList&type=query",
					colList:[
					   {label:'<bean:message key="lable.xy" />名称',name:'xymc', index: 'xymc',width:'13%'},
					   {label:'专业代码',name:'zydm', index: 'zydm',width:'10%',key:true},
					   {label:'专业名称',name:'zymc', index: 'zymc',width:'13%'},
					   {label:'班级数',name:'bjs', index: 'bjs',width:'6%'},
					   {label:'学生数',name:'xss', index: 'xss',width:'6%'}
					],
					sortname: "zydm",
				 	sortorder: "asc"
				}
			var gridSettingBj = {
					caption:"班级信息列表",
					pager:"pager",
					multiselect:true,
					rowNum:10,
					url:"jcsj.do?method=jcsjList&type=query",
					colList:[
					   {label:'<bean:message key="lable.xy" />名称',name:'xymc', index: 'xymc',width:'13%'},
					   <logic:equal name="xxdm" value="8403">
					   {label:'托管<bean:message key="lable.xy" />名称',name:'tgxymc', index: 'tgxymc',width:'13%'},
					   </logic:equal>
					   <logic:equal name="xxdm" value="10426">
					   {label:'托管<bean:message key="lable.xy" />名称',name:'tgxymc', index: 'tgxymc',width:'13%'},
					   </logic:equal>
					   {label:'专业名称',name:'zymc', index: 'zymc',width:'13%'},
					   {label:'班级代码',name:'bjdm', index: 'bjdm',width:'10%',key:true},
					   {label:'班级名称',name:'bjmc', index: 'bjmc',width:'13%'},
					   {label:'年级',name:'nj', index: 'nj',width:'6%'},
					   {label:'学生数',name:'xss', index: 'xss',width:'6%'}
					],
					sortname: "bjdm",
				 	sortorder: "asc"
				}
			jQuery(function(){

				var xzflg = jQuery("#xzflg").val();
				jQuery(".ha").removeClass("ha");
				if("1" ==xzflg){					
					jQuery("#zyli").addClass("ha");
				}else if("2" ==xzflg){
					jQuery("#bjli").addClass("ha");
				}else{
					jQuery("#xyli").addClass("ha");
				}
				
				var map = getSuperSearch();
				map["xzflg"] = xzflg;

				if("1" ==xzflg){
					gridSettingZy["params"] = map;
					jQuery("#dataTable").initGrid(gridSettingZy);
				}else if("2" ==xzflg){
					gridSettingBj["params"] = map;
					jQuery("#dataTable").initGrid(gridSettingBj);
				}else{
					gridSettingXy["params"] = map;
					jQuery("#dataTable").initGrid(gridSettingXy);
				}
			});
			function searchRs(){

				var map = getSuperSearch();	
				var xzflg = jQuery("#xzflg").val();
				map["xzflg"] = xzflg;
				if("1" ==xzflg){
					gridSettingZy["params"] = map;
					jQuery("#dataTable").initGrid(gridSettingZy);
				}else if("2" ==xzflg){
					gridSettingBj["params"] = map;
					jQuery("#dataTable").initGrid(gridSettingBj);
				}else{
					gridSettingXy["params"] = map;
					jQuery("#dataTable").initGrid(gridSettingXy);
				}
			}

			//页签切换
			function selectTab(obj,xzflg){
				jQuery("#xzflg").val(xzflg);
				refreshForm("jcsj.do?method=jcsjList&xzflg="+xzflg);
			}
			function drxx(){
				var xzflg = jQuery("#xzflg").val();

				if("1" ==xzflg){
					toImportData("IMPORT_N710707X_ZY");
				}else if("2" ==xzflg){
					toImportData("IMPORT_N710707X_BJ");
				}else{
					toImportData("IMPORT_N710707X_XY");
				}				
				return false;
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jcsj">
			<html:hidden property="xzflg" styleId="xzflg" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<div class="buttonbox">
				  <ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="zjJcsj();return false;" class="btn_zj" id="zjButton">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xgJcsj();return false;" class="btn_xg" id="xgButton">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="scJcsj();return false;" class="btn_sc" id="scButton">删除</a>
						</li>
						<li><a href="#" onclick="drxx();return false;" class="btn_dr">导入</a></li>
					</logic:equal>
					<li>
						<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_sz">导出</a>
					</li>
				  </ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<!-- 按钮 -->
				<div class="comp_title" id="comp_title">
			      <ul style="width:70%">
			        <li class="ha"  id="xyli"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span><bean:message key="lable.bmxy" /></span></a></li>
			        <li id="zyli"><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>专业</span></a></li>
			        <li id="bjli"><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>班级</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div>
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 基础数据信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
