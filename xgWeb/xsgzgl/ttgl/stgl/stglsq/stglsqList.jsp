<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		var gridSetting = {
			caption:"社团申请列表",
			pager:"pager",
			url:"ttgl_stglsq.do?method=stglsqList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
			   {label:'社团类型',name:'stlx', index: 'stlx',width:'8%'},
			   {label:'社团全称',name:'stqc', index: 'stqc',width:'8%'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true}
			],
			sortname: "sqsj",
			sortorder: "desc"
		}
	gridSetting["params"]=getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		jQuery("#prompt_isopen").show();
		jQuery("#prompt_null_isopen").show();
		return false;
	}
	if ("false" == isopen){
		jQuery("#prompt_isopen").show();
		jQuery("#prompt_false_isopen").show();
		return false;
	}
});



function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}
function view(sqid) {
    var height = jQuery(window).height()-210;
	showDialog("查看社团信息", 790,height, "ttgl_stglsq.do?method=view&sqid=" + sqid
			);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["sqid"] + "\");'>" + cellValue
			+ "</a>";
}


function add(){
	var isopen = jQuery("#isopen").val();
    var height = jQuery(window).height()-210;
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var url = "ttgl_stglsq.do?method=add";
	var title = "增加社团";
	showDialog(title,790,height,url);
	
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
    var height = jQuery(window).height()-210;
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt = rows[0]["shzt"];
		if (shzt=='0'||shzt=='3'){
			var url = 'ttgl_stglsq.do?method=update&xh='+rows[0]["xh"]+'&sqid=' + rows[0]["sqid"];
			showDialog("修改社团信息", 790,height, url);
		}else{
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
	}

}


//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("ttgl_stglsq.do?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="的申请已经提交不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


function submit(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条您要提交的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		var url = "ttgl_stglsq.do?method=submit";
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
			jQuery.post(url,
				{
				 values:ids.toString(),
				 xh : rows[0]['xh'],
				 splc : rows[0]['splc'],
				 shzt : rows[0]['shzt']
				},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function cancel(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
			jQuery.post("ttgl_stglsq.do?method=cancel",
				{
				 values:ids.toString(),
				 splcid : rows[0]['splc'] 
				},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


function Lcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {	
		var shzt = rows[0]["shzt"];
		if ("0" == shzt){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}	
		showDialog("读书申请审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

var DCCLBH = "xg_ttgl_stglsq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

// 导出方法
function ExportData() {
	setSearchTj();//设置高级查询条件
	var url = "ttgl_stglsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
		

</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>提示：</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				<bean:message key="lable.jcszwcsh_prompt" />
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		
		<html:form action="/ttgl_stglsq"><!--
			<input type="hidden" name="tableName" id="tableName" value="xg_gfjy_gfjyqksqb"/>
			--><%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" 
							>申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="Lcinfo();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>				
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>国防教育情况列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
