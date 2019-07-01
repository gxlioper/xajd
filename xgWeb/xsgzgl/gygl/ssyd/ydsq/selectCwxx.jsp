<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"床位信息列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"ydsq.do?method=selectCwxx&type=query",
				colList:[
				   {label:'床位信息id',name:'cwxx', index: 'cwxx',key:true,hidden:true},
				   {label:'楼栋名称',name:'ldmc', index: 'ldmc'},
				   {label:'寝室号',name:'qsh', index: 'qsh',width:'6%'},
				   {label:'床位号',name:'cwh', index: 'cwh',width:'6%'},
				   {label:'床位性别',name:'qsxb', index: 'qsxb'},
				   {label:'所属年级',name:'nj', index: 'nj'},
				   {label:'所属<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
				   {label:'学号',name:'xh', index: 'xh'},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'操作',name:'cwxx', index: '',width:'58px',noSort:true,formatter:function(cell,rowObject){
					   return "<label class='btn_01' onclick=\"selectCw('"+cell+"','"+rowObject["xh"]+"');\">选择</label>";
				   }}
				],
				sortname: "sfrz",
			 	sortorder: "desc",
			 	params:{
				 	xh:"${xh}"
				}
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function selectCw(cwxx,xh){
				var api = frameElement.api;
				var cwxxSetting = {
						caption:"已选择床位信息",
						multiselect:false,
						rowNum:1,
						url:"ydsq.do?method=selectCwxx&type=query&cwxx="+cwxx,
						colList:[
						   {label:'床位信息id',name:'cwxx', index: 'cwxx',key:true,hidden:true},
						   {label:'楼栋名称',name:'ldmc', index: 'ldmc'},
						   {label:'寝室号',name:'qsh', index: 'qsh',width:'6%'},
						   {label:'床位号',name:'cwh', index: 'cwh',width:'6%'},
						   {label:'床位性别',name:'qsxb', index: 'qsxb'},
						   {label:'所属年级',name:'nj', index: 'nj'},
						   {label:'所属<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
						   {label:'学号',name:'xh', index: 'xh'},
						   {label:'姓名',name:'xm', index: 'xm'}
						],
						sortname: "sfrz",
					 	sortorder: "desc"
					}
				if (api){
					if (api.get('childDialog')){
						api.get('parentDialog').jQuery("#cwxxTable").initGrid(cwxxSetting);
						api.get('parentDialog').jQuery("#yxzcwxx").show();
						api.get('parentDialog').jQuery("#cwxx").val(cwxx);
						if(xh!=='null'){
							api.get('parentDialog').jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
							api.get('parentDialog').jQuery("input:radio[name='sfcwcsh']").attr("disabled","disabled");
							//api.get('parentDialog').jQuery("#showSfcwcsh").hide();
						}else{
							api.get('parentDialog').jQuery("input:radio[name='sfcwcsh']:eq(0)").attr("checked","checked");
							api.get('parentDialog').jQuery("input:radio[name='sfcwcsh']").removeAttr("disabled");

						}
						
					} else {
						var W = api.opener;
						W.showCwxx(cwxx);
						W.location="javascript:showCwxx("+cwxx+")";
					}
				} else if (parent.window){
					parent.window.document.location="javascript:showCwxx("+cwxx+")";
				}
				iFClose();
			}
			
			
			function searchRs(){
				var xh=jQuery("#xh").val();
				var map = getSuperSearch();
				map["xh"]=xh;
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
		<input type="hidden" value="${xh}" id="xh"/>
        <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<html:form action="/ydsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div>
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 床位信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
