<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		function pfxqLink(cellValue, rowObject){
			var pfr;
			if ("1"==${JgcxForm.pflx}){
				pfr = rowObject["xh"];
				
			}else{
				pfr = rowObject["zgh"];
			}
			return '<a href="javascript:void(0);" class="name" onclick="viewPf(\''+jQuery("#xmid").val()+'\',\''+rowObject["khbid"]+'\',\''+jQuery("#khdxr").val()+'\',\''+pfr+'\',\''+${JgcxForm.pflx}+'\')">'+cellValue+'</a>';
		}


		function viewPf(xmid,khbid,khdxr,pfr,pflx){
			showDialog2("查看",800,520,"khglKhpf.do?method=viewPf&xmid="+xmid+"&khbid="+khbid+"&khdxr="+khdxr+"&pfr="+pfr+"&pflx="+pflx);
		}
		
			var gridSetting = {
				caption : "列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"khgljgcx.do?method=dfrList&type=query",
				params:{
					pfzt:"${JgcxForm.pfzt}",
					pflx:"${JgcxForm.pflx}",
					xmid:"${JgcxForm.xmid}",
					xmszid:"${JgcxForm.xmszid}",
					khdxr:"${JgcxForm.khdxr}",
					bmdm:"${JgcxForm.bmdm}",
					khlx:"${JgcxForm.khlx}"
				}
			}


			var colList = [ 
				   {label:'学号',name:'xh', index: 'xh',width:'15%'},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'}
		   ];

			var colList1 = [ 
				{label:'学号',name:'xh', index: 'xh',width:'15%'},
				{label:'姓名',name:'xm', index: 'xm',width:'10%'},
				{label:'性别',name:'xb', index: 'xb',width:'8%'},
				{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
				{label:'专业',name:'zymc', index: 'zydm',width:'20%'},
				{label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
				{label:'khbid',name:'khbid', index: 'khbid',hidden:true}
				<logic:notEqual value="xy" name="userType">
				,{label : '总分',name : 'zf',index : 'zf',width : '20%', formatter:pfxqLink}
				</logic:notEqual>
		   ];

			var colList2 = [ 
			   {label : '职工号',name : 'zgh',index : 'zgh',width : '20%'}, 
			   {label : '姓名',name : 'xm',index : 'xm',width : '15%'}, 
			   {label : '性别',name : 'xbmc',index : 'xbmc',width : '15%'}, 
			   {label : '所在部门',name : 'bmmc',index : 'bmmc',width : '20%'}
		   ];

			var colList3 = [ 
 			   {label : '职工号',name : 'zgh',index : 'zgh',width : '20%'}, 
 			   {label : '姓名',name : 'xm',index : 'xm',width : '15%'}, 
 			   {label : '性别',name : 'xbmc',index : 'xbmc',width : '15%'}, 
 			   {label : '所在部门',name : 'bmmc',index : 'bmmc',width : '20%'},
 			   {label:'khbid',name:'khbid', index: 'khbid',hidden:true}
 			  <logic:notEqual value="xy" name="userType">
 			   ,{label : '总分',name : 'zf',index : 'zf',width : '20%', formatter:pfxqLink}
 			  </logic:notEqual>
 		   ];

			//下面代码比较恶心，在此解释下
			//第一个if判断的是学生还是老师，第二个if判断是"待打分"还是"已打分"
			if("1"==${JgcxForm.pflx}||("3"==${JgcxForm.pflx}&&"1"==${JgcxForm.khlx})){
				if("0"==${JgcxForm.pfzt}){
					gridSetting["colList"] = colList;
				}else{
					gridSetting["colList"] = colList1;
				}
				gridSetting["sortname"] = "xh";
			}else{
				if("0"==${JgcxForm.pfzt}){
					gridSetting["colList"] = colList2;
				}else{
					gridSetting["colList"] = colList3;
				}
				gridSetting["sortname"] = "zgh";
			}
			gridSetting["sortorder"] = "asc";


			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			
			function searchRs(){
				var map = getSuperSearch();
				map["pfzt"] = jQuery("#pfzt").val();
				map["pflx"] = jQuery("#pflx").val();
				map["xmid"] = jQuery("#xmid").val();
				map["xmszid"] = jQuery("#xmszid").val();
				map["khdxr"] = jQuery("#khdxr").val();
				map["bmdm"] = jQuery("#bmdm").val();
				map["khlx"] = jQuery("#khlx").val();
				jQuery("#dataTable").reloadGrid(map);
			}


			//导出
			function exportDfrData(){
				
				var pfzt = jQuery("#pfzt").val();
				var pflx = jQuery("#pflx").val();
				var xmid = jQuery("#xmid").val();
				var xmszid = jQuery("#xmszid").val();
				var khdxr = jQuery("#khdxr").val();
				var bmdm = jQuery("#bmdm").val();
				var khlx = jQuery("#khlx").val();

				var DCCLBH = jQuery("#path").val();
				
				setSearchTj();//设置高级查询条件
				var url = "khgljgcx.do?method=exportDfrData&dcclbh=" + DCCLBH 
							+"&pfzt="+pfzt+"&pflx="+pflx+"&xmid="+xmid+"&xmszid="+xmszid
							+"&khdxr="+khdxr+"&bmdm="+bmdm+"&khlx="+khlx;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
				
		</script>
	</head>

	<body>
		<html:form action="/khgljgcx">
			<html:hidden property="pfzt" styleId="pfzt" />
			<html:hidden property="pflx" styleId="pflx" />
			<html:hidden property="xmid" styleId="xmid" />
			<html:hidden property="xmszid" styleId="xmszid" />
			<html:hidden property="khdxr" styleId="khdxr" />
			<html:hidden property="bmdm" styleId="bmdm" />
			<html:hidden property="khlx" styleId="khlx" />
			<input type="hidden" id="khbid" value="${khbid}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="exportDfrData();return false;" class="btn_dc">导出</a>
						</li>
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
				<span> 结果列表
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
