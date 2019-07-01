var action="qjjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var qjjgid = rowObject["qjjgid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjjgid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(qjjgid) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&qjjgid=" + qjjgid;
	var title = "请假申请信息";
	showDialog(title, 800, 500, url);
}
//申请
function add() {
		var url =action+"?method=add";
		var title = "增加请假信息";
		showDialog(title, 800, 500, url);
		jQuery("#dataTable").reloadGrid();
}
//附件格式验证
function postfixCheck(){
	var wjm=jQuery("#formfile").val();
	if(wjm==""||wjm==null){
		return true;
	}
	var wjms=wjm.split(".");
	var hz=",bmp,jpg,jpeg,gif,png,pdf,doc,BMP,JPG,JPEG,GIF,PNG,PDF,DOC";
	if(hz.indexOf(wjms[wjms.length-1])<0){
		return false;
	}
	return true;
}
function save(url,checkId,sfjc){
	if(!postfixCheck()){
		return showAlert("请上传支持的附件格式！");
	}
	var xxdm=jQuery("#xxdm").val();
	if("12866"==xxdm){
		checkId+="-jzdh";
	}
	if("70002"==xxdm){
		checkId+="-xnxw";
	}
	if("12872"==xxdm){
		var  myselect=document.getElementById("qjlxid");
		var index=myselect.selectedIndex ;
		var store = myselect.options[index].text;
		if(store==("病假")){			
			if (jQuery(".MultiFile-label").length == 0){
				showAlert("请将带<font color='red'>*</font>的项目填写完整");
				return false;
			}
		}
	}
	if("12727"==xxdm||"12688"==xxdm){//请假节次字段
		var b = false;
		var qjjcstr="";
		var qjjc = document.getElementsByName("mdd");
		for(var i=0;i<qjjc.length;i++){
		    if(qjjc[i].checked){
		       b=true;
		       qjjcstr+=qjjc[i].value+",";
		    }
		}
		var qjts =jQuery("#qjts").val();
		if(qjts<1){
			if(b==false){
				return showAlert("请选择请假节次!");
			}
		}
		url+="&mdd="+ encodeURI(encodeURI(qjjcstr.substring(0,qjjcstr.length-1)));
	}
	if(!checkNotNull(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	
	if(!checkother()){
		return false;
	}
	lock();
	var qjts=jQuery("#qjts").val();
	jQuery.post("qjjg.do?method=checkTs", {
		qjts:qjts,sfjc:sfjc
	}, function(data) {
		if(data["success"]=="true"){
		 	jQuery("#form").ajaxSubmit({
				url:url,
				type:"post",
				dataType:"json",
				success:function(data){
			 		 if(data["message"]=="保存成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
			    	 }else{
			    		 showAlert(data["message"]);
			    	 }
				},
				contentType:"application/x-www-form-urlencoded;charset=utf-8"
			});	
		}else{
			showAlert("不存在申请天数对应请假规则!");
		}
		unlock();
	}, 'json');
}
function checkother(){
	if(chkNumInputForThis("qjts")){
		showAlert("请假天数必须为数字!");
		return false;
	}
	
	if(0 == jQuery("#qjts").val()) {
		showAlert("请假天数不能为0!");
		return false;
	}
	
	if(chkNumInputForThis("sjqjts")){
		showAlert("实际请假天数必须为数字!");
		return false;
	}
	var xxdm = jQuery("#xxdm").val();
	if(xxdm == "10351"){
		var qjjs = jQuery("#qjjs").val();
		if(jQuery.trim(qjjs) == ""){
			showAlert("请假节数不能为空!");
			return false;
		}
	}
	/*
	var sjqjts=jQuery("#sjqjts").val();
	if(null!=sjqjts&&sjqjts.indexOf(".")!=-1){
		showAlert("实际请假天数必须为整数数字!");
		return false;
	}*/
	return true;
}
function chkNumInputForThis(obj){
	//这里是共用js 处理不同页面 有些对象不存在的问题
	if(null==obj||null==$(obj)){
		return false;
	}
	return chkNumInput(obj);
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
		var xh=rows[0]["xh"];
		var qjzt=rows[0]["qjzt"];
		if(0==qjzt||"0"==qjzt){
			showAlertDivLayer("流程数据不能修改!");
		}else{
			var url = action+'?method=update&xh='+xh+'&qjjgid=' + rows[0]["qjjgid"];
			var title = "修改请假信息";
			showDialog(title, 800, 500, url);
			jQuery("#dataTable").reloadGrid();
		}
	}
}
//销假
function xjcl(){
	var userStatus = jQuery("#userStatus").val();
	var myDate = jQuery("#currTime").val();
	var xxdm=jQuery("#xxdm").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要销假的记录！");
		return false;
	} 
	var xh =rows[0]["xh"];
	var kssj =rows[0]["kssj"];
	var qjsqid =rows[0]["qjsqid"];
	jQuery.ajaxSetup({async:false});
	var haveNewSqjl = false;
	/*
	jQuery.post("qjjg.do?method=haveNewSqjl", {
		qjsqid:qjsqid,xh:xh,kssj:kssj
	}, function(data) {
	
		if("true"==data["message"]){
			haveNewSqjl=true;
		}
	},'json');*/
	
	var xh=rows[0]["xh"];
	var xjzt=rows[0]["xjzt"];
	var jssj = rows[0]["jssj"];
	if(xjzt=="1"){//1表示已经销假
		showAlertDivLayer("此信息已经销假，不能重复操作！");
		return false;
	}
	var days = dateDiff(myDate,jssj);
	//重庆三峡:未销假超过8天，提示必须续假
	if(days>jQuery("#xjts").val()&&"14008"==xxdm&&"xx"!=userStatus&&false==haveNewSqjl){
		showAlertDivLayer("请假未销假超过"+jQuery("#xjts").val()+"天，必须续假！");
		return false;
	}
	//重庆三峡医高专：销假个性化 
	//需求 XG_2016-0135 RA002 根据天数和请假审批流程销假提示取消 20161215
	//	var qjts = rows[0]["qjts"];
	//	var qjzt = rows[0]["qjzt"];	
	//	var nzcs = false;
	//	if("14008" == xxdm && "0" == qjzt && "xx"!=userStatus) {
	//		var xjUrl = "qjjg.do?method=xjrpdOneToN";
	//		if(qjts > 9){
	//			xjUrl = "qjjg.do?method=xjrpdTenD";
	//		}
	//		jQuery.post(xjUrl, {
	//			qjsqid:qjsqid,xh:xh
	//		}, function(data) {
	//			if("true"==data["message"]){
	//				nzcs=true;
	//			}
	//		},'json');
	//		if(true == nzcs) {
	//			showAlertDivLayer("您无权对该学生进行销假操作，请确认！");
	//			return false;
	//		}
	//	}

	var url = action+'?method=xjcl&xh='+xh+'&qjjgid=' + rows[0]["qjjgid"];
	var title = "销假管理";
	showDialog(title, 800,450, url);
	jQuery("#dataTable").reloadGrid();
	jQuery.ajaxSetup({async:true});
	
}
//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除"+data["num"]+"条！";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="为流程数据不能删除！";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
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
		showDialog("请假审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['qjsqid']+"&splc="+rows[0]['splcid']);
	}
}
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport('qjjgbase.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	setSearchTj();//设置高级查询条件
	var url = "qjjg.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//打印申请表
function printQjjgb(url){
	var qjjgid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				qjjgid +=rows[i]["qjjgid"];
			}else{
				qjjgid +=rows[i]["qjjgid"]+",";
			}
		}
		if("10511"==jQuery("#xxdm").val()){
			var url = "qjjg.do?method=printQjjgbOfHs&qjjgid="+qjjgid;
		}
		else{
			var url = url + "&qjjgid=" +qjjgid;
		}
		window.open(url);
	}
}

function selectQjkc(){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		showDialog("选择请假课程",800,500,"qjsq.do?method=selectQjkc&xh="+xh);
	} else {
		showAlertDivLayer("请先选择学生！");
	}
}

function addQjkc(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请至少选择一门课程！");
		return false;
	}
	var api = frameElement.api;
	var parentTbody = jQuery(api.get('parentDialog').document).find("#qjkc");
	jQuery("tr",parentTbody).remove();
	var kcbhs="";
	for ( var i = 0; i < rows.length; i++) {
		if(i!=0){
			kcbhs+=";"+rows[i]['kcbh'];
		}
		else{
			kcbhs+=rows[i]['kcbh'];
		}
		var tr = jQuery("<tr></tr>");
		var kcmcTd = jQuery("<td></td>");
		var rklsxmTd = jQuery("<td></td>");
		var rklslxfsTd = jQuery("<td></td>");
		var kcbh = jQuery("<input type='hidden' name='kcbh' id='kcbh"+i+"' value='"+rows[i]['kcbh']+"'/>");
		var kcmc = rows[i]['kcmc'];
		var rklsxm = rows[i]['rklsxm'];
		var rklslxfs = rows[i]['rklslxfs'];
		kcmcTd.append(kcmc);
		kcmcTd.append(kcbh);
		rklsxmTd.append(rklsxm);
		rklslxfsTd.append(rklslxfs);
		tr.append(kcmcTd).append(rklsxmTd).append(rklslxfsTd);
		parentTbody.append(tr);
	}
	parentTbody.append(jQuery("<input type='hidden' name='kcbhs' id='kcbhs' value='"+kcbhs+"'/>"));
	api.close();
	
}

function dateDiff(sDate1, sDate2){ 
	  var aDate, oDate1, oDate2, iDays;
	   // aDate = sDate1.split("-");
	  //  oDate1 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]); //转换为12-18-2002格式
	  oDate1= new Date(sDate1.substring(0,10).replaceAll("-","/"));
	   // aDate = sDate2.split("-");
	   // oDate2 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]);
	  oDate2= new Date(sDate2.substring(0,10).replaceAll("-","/"));
	  iDays = parseInt((oDate1 - oDate2) / 1000 / 60 / 60 /24); //把相差的毫秒数转换为天数
	  return iDays;
}

/**
 * 浙江警官职业学院——个性化请假审批表
 * @return
 */
function printXsqjspb(){
	/*选择的记录*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*多选记录*/
	var ids = jQuery("#dataTable").getSeletIds();
	/*选择记录的条数（长度）*/
	var len = ids.length;
	if(0 == len ){/*选择0条提示*/
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	}else if(1 == len){/*选择一条记录*/
		var url = "qjjg.do?method=getXsqjspbOne&xh="+rows[0]["xh"]+"&qjjgid="+rows[0]["qjjgid"];
		window.open(url);
	}else{
		var url = "qjjg.do?method=getXsqjspbZip&value="+ids;
		window.open(url);
	}
}