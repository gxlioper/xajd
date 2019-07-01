<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/xszygl/js/xszygl.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/qsppgl/js/qsppgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "新生之友列表",
				pager : "pager",
				multiselect:false,
				url : "qsppgl.do?method=getSgppXszyList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : 'dwdm', name : 'dwdm', index : 'dwdm', hidden : true },
							{ label : 'nj', name : 'nj', index : 'nj', hidden : true },
							{ label : '职工号', name : 'zgh', index : 'zgh', width : '8%', formatter:zghLink},
							{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '所属部门', name : 'bmmc', index : 'bmmc', width : '12%' },
							{ label : '政治面貌', name : 'zzmmmc', index : 'zzmmmc', width : '10%' },
							{ label : '联系电话', name : 'lxdh', index : 'lxdh', width : '10%'},
							{ label : '职务职称', name : 'zwzc', index : 'zwzc', width : '5%' },
							{ label : '大类要求', name : 'dlyq', index : 'dlyq', width : '10%' },
							{ label : '已匹配寝室数', name : 'ppqss', index : 'ppqss', width : '5%' },
							{ label : '跨院系标记', name : 'kyxbj', index : 'kyxbj', width : '10%' ,formatter:kyxbjFormatter},
							{label:'操作',name:'', index: '',width:'10%',noSort:true,formatter:ppConfLink}
							],
					sortname : "ppqss", sortorder : "asc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function ppConfLink(cellValue, rowObject) {
			return "<label class='btn_01' onclick='saveSdpp(\""
					+ rowObject["zgh"] + "\",\"" + rowObject["nj"] + "\",\"" + rowObject["ppqss"] + "\");'>"
					+ "确定</label>";
		}

		//新生之友匹配确定
		function saveSdpp(zgh,nj,ppqss){ 
			var api = frameElement.api,W = api.opener;
			
			if(0!=ppqss){
				
				showConfirmDivLayer("该新生之友已匹配寝室，是否再次匹配？", {
					"okFun" : function() {
					W.saveSdpp(zgh,nj);
					closeDialog();
					}
				});
			}
			else{
				W.saveSdpp(zgh,nj);
				closeDialog();
			}
			
				
			
		}
		</script>
	</head>

	<body>
		<html:form action="/xszygl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="nj" styleId="nj"/>
			<html:hidden property="lddm" styleId="lddm"/>
			<html:hidden property="qsh" styleId="qsh"/>
			<html:hidden property="qsh" styleId="ldmc"/>
			<div>
				<h3 class=datetitle_01>
				<span>楼栋：${qsppForm.ldmc} &nbsp;寝室：${qsppForm.qsh}(${qsppForm.qsxb})&nbsp;入住人数：${qsppForm.rzrs}</span>
			   </h3>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span><font color="blue">${nj }级新生之友信息</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
