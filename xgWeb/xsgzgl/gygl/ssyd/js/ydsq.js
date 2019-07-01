var action="ydsq.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//设置退宿还是调整信息显示
function setTstz(){
	var nowSelect=jQuery("#ssydlx").val();
	change(nowSelect);
	jQuery("#ssydlx").bind("click",function(){
		var select=jQuery(this).val();
		change(select);
	});
}
//查看页面设置当前显示
function setTstzFock(){
	var nowSelect=jQuery("#ssydlx").val();
	if(nowSelect=="00"){
		jQuery("#yy").text("退宿原因");
		jQuery("#sj").text("退宿时间");
		jQuery("#yxzcwxx").hide();
	}else{
		jQuery("#yy").text("宿舍调整原因");
		jQuery("#sj").text("宿舍调整时间");	
		alert(jQuery("#cwxx").val());
		if(jQuery("#cwxx").val()!=""){
			showCwxx(jQuery("#cwxx").val());
		}
	}
}
function change(select){
	if(select=="00"){
		jQuery("#ts").show();
		jQuery("#tz").hide();
		jQuery("#rz").hide();
		
		jQuery("#tssave").show();
		jQuery("#tssub").show();
		jQuery("#tzsave").hide();
		jQuery("#tzsub").hide();
		jQuery("#rzsave").hide();
		jQuery("#rzsub").hide();
		
		jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
		jQuery("input:radio[name='sfcwcsh']").removeAttr("disabled");
	}else if(select=="03"){
		jQuery("#ts").hide();
		jQuery("#tz").hide();
		jQuery("#rz").show();
		
		jQuery("#tssave").hide();
		jQuery("#tssub").hide();
		jQuery("#tzsave").hide();
		jQuery("#tzsub").hide();
		jQuery("#rzsave").show();
		jQuery("#rzsub").show();
		
		var rzcwxx = jQuery("#rzcwxx").val();
		if(rzcwxx!=""){
			showRzcwxx(rzcwxx);
		}
		
		jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
		jQuery("input:radio[name='sfcwcsh']").attr("disabled","disabled");
	}else{
		jQuery("#ts").hide();
		jQuery("#rz").hide();
		jQuery("#tz").show();
		
		jQuery("#tssave").hide();
		jQuery("#tssub").hide();
		jQuery("#rzsave").hide();
		jQuery("#rzsub").hide();
		jQuery("#tzsave").show();
		jQuery("#tzsub").show();
		
		var cwxx = jQuery("#cwxx").val();
		if(cwxx!=""){
			showCwxx(cwxx);
		}
		
		jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
		jQuery("input:radio[name='sfcwcsh']").removeAttr("disabled");
	}
}
//选择床位（调整）
function selectCw(){
	var xh = jQuery("#xh").val();
	if(xh===''){
		showAlertDivLayer('请先选择一个学生！');
	}else{
		showDialog('请选择一个床位',800,500,'ydsq.do?method=selectCwxx&goto=path&xh='+xh);
	}
}
//选择床位（入住）
function selectRzcw(){
	var xh = jQuery("#xh").val();
	if(xh===''){
		showAlertDivLayer('请先选择一个学生！');
	}else{
		showDialog('请选择一个床位',800,500,'ydsq.do?method=selectRzcwxx&goto=path&xh='+xh);
	}
}
//显示选中床位信息（调整）
function showCwxx(cwxx){
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
			   {label:'所属'+jQuery("#xbmc").val(),name:'xymc', index: 'xymc'},
			   {label:'学号',name:'xh', index: 'xh'},
			   {label:'姓名',name:'xm', index: 'xm'}
			],
			sortname: "sfrz",
		 	sortorder: "desc"
		}
 	jQuery("#cwxxTable").initGrid(cwxxSetting);
	jQuery("#yxzcwxx").show();
	jQuery("#cwxx").val(cwxx);
}
//显示选中床位信息（入住）
function showRzcwxx(rzcwxx){
	var rzcwxxSetting = {
			caption:"已选择床位信息",
			multiselect:false,
			rowNum:1,
			url:"ydsq.do?method=selectRzcwxx&type=query&rzcwxx="+rzcwxx,
			colList:[
			         {label:'床位信息id',name:'rzcwxx', index: 'rzcwxx',key:true,hidden:true},
			         {label:'楼栋名称',name:'ldmc', index: 'ldmc'},
			         {label:'寝室号',name:'qsh', index: 'qsh',width:'6%'},
			         {label:'床位号',name:'cwh', index: 'cwh',width:'6%'},
			         {label:'床位性别',name:'qsxb', index: 'qsxb'},
			         {label:'所属年级',name:'nj', index: 'nj'},
			         {label:'所属'+jQuery("#xbmc").val(),name:'xymc', index: 'xymc'}
			         //{label:'学号',name:'xh', index: 'xh'},
			         //{label:'姓名',name:'xm', index: 'xm'}
			         ],
			         sortname: "sfrz",
			         sortorder: "desc"
	}
	jQuery("#rzcwxxTable").initGrid(rzcwxxSetting);
	jQuery("#yxzrzcwxx").show();
	jQuery("#rzcwxx").val(rzcwxx);
}

//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var ssydsqid = rowObject["ssydsqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + ssydsqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(id) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&ssydsqid=" + id;
	var title = "宿舍异动申请信息";
	showDialog(title, 800, 500, url);
}
//申请
function add() {
	/*var userType = jQuery("#userType").val();
	if("stu" == userType){
		jQuery.post('gyglnew_gybxgl.do?method=viewXsxx',{},function(data){
			var lddm = data.lddm;
			
			if(!lddm){
				showAlertDivLayer("您尚未入住公寓寝室，无法提交宿舍异动申请！");
			}else{
				var url =action+"?method=add";
				var title = "宿舍异动申请";
				showDialog(title, 800, 500, url);
			}					
		},'json');
	}else{*/
		var url =action+"?method=add";
		var title = "宿舍异动申请";
		showDialog(title, 800, 500, url);		
	//}
		
}

function cancle(){
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
		
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
			
				jQuery.post(action+"?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splcid']
				}, function(data) {
					
					showAlertDivLayer(data["message"]);
					reload();
				}, 'json');
				
			}
		});
	}
	
}

function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=subBusi", {
					values : ids.toString(),
					ydlx : rows[0]['ssydlx'],
					xh : rows[0]['xh']
				}, function(data) {
					
					showAlertDivLayer(data["message"]);
					reload();
				}, 'json');
				
			}
		});
	}
	
}


function save(url,checkId){
	var ssydlx = jQuery("#ssydlx").val();
	var qsmcCk = jQuery("#qsmcCk").val();
	var sfysq = jQuery("#sfysq").val();
	var zsfje = jQuery.trim(jQuery("#zsfje").val());
	var jflx = jQuery("#jflx").val();
	var xxdm = jQuery("#xxdm").val();
	var rzcwxx = jQuery("#rzcwxx").val();
	
	if(sfysq=="0"){
		return showAlertDivLayer("该学生已有宿舍异动信息正在审核中，不能再申请。");
	}

	if(ssydlx == "03" && url == "ydsq.do?method=add&type=save" && qsmcCk == "0") {
		return showAlertDivLayer("该宿舍已经有学生提交了申请，正在审核中，不能再申请。");
	}
	if(xxdm == 10466 && ssydlx == "03" && rzcwxx == ''){
		showAlertDivLayer("请选择床位！");
		return false;
	}
	
	if("12303"==xxdm){
		checkId+="-bz";
	}
	
	if(!check(checkId)){
		return showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if(!checkother()){
		return false;
	}

	if (jflx != undefined && ((jflx == "" && zsfje != "" ) || (jflx != "" && zsfje == ""))) {
		showAlertDivLayer("请将住宿费信息填写完整！");
		return false;
	}
	
 	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
	 		 if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
	    		 showAlertDivLayer(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }
	 		 else{
	    		 showAlertDivLayer(data["message"]);
	    	 }
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}

function checkother(){
	var ssydlx = jQuery("#ssydlx").val();
	var oldSsydlx = jQuery("#oldSsydlx").val(); // 修改前保存的类型
	// 申请前学生有寝室号，但是类型选择的是“入住”，则不符合
	var td_cwh = jQuery.trim(jQuery("#td_cwh").html());
	if((!oldSsydlx && ssydlx != "03" && td_cwh == "") || (oldSsydlx && oldSsydlx == "03" && ssydlx != "03")){
		showAlertDivLayer("该学生尚未入住！");
		return false;
	}
	if((!oldSsydlx && ssydlx == "03" && td_cwh != "") || (oldSsydlx && oldSsydlx != "03" && ssydlx == "03")){
		showAlertDivLayer("该学生已入住！");
		return false;
	}
	return true;
}
/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			//alert(id[i]);
			return false;
		}
	}
	return true;
}
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
			showAlertDivLayer("只能修改未审核或者已退回的记录！");
			return false;
		}
		var url = action+'?method=update&xh='+rows[0]["xh"]+'&ssydsqid=' + rows[0]["ssydsqid"];
		var title = "修改宿舍异动类型";
		showDialog(title, 800, 490, url);
		reload();
	}
}
//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {			
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="宿舍异动</br>";
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+=" 的申请已进入结果库不能删除!";
					}
					showAlertDivLayer(mes);
					reload();
				}, 'json');
				
			}
		});
	}
}
function rcxwshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {	
		var shlc=rows[0]["splcid"];
		if(shlc==""){
			showAlertDivLayer("此申请无需审核，无相关流程信息！");
			return false;
		}
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("宿舍异动审批流程跟踪",600,400,'comm_spl.do?method=lcgz&sqid='+rows[0]['ssydsqid']+"&splc="+rows[0]['splcid']);
	}
}

function exportConfig() {
	customExport("ydsqbase.do", exportData,690,500);
}
// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "ydsq.do?method=exportData&dcclbh=ydsqbase.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//打印报表
function printTstzd() {
	var ssydsqid = jQuery("#dataTable").getSeletIds();
	if (ssydsqid.length <=0) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	
	var rows = jQuery("#dataTable").getSeletRow();
	
	for(var i=0; i<ssydsqid.length; i++){
		if("退宿"==rows[i]['ydlxmc'] || "入住"==rows[i]['ydlxmc']){
			showAlertDivLayer('"入住"/"退宿"不能打印"调宿通知单"');
			return false;
		}
	}
	
	var url = "ydsq.do?method=printTstzd&ssydsqid=" +ssydsqid.toString();
	
	window.open(url);
}
