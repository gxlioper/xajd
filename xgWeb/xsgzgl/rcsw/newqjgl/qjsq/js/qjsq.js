var action="qjsq.do";
var title="";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	//autoTitleForGrid();
	//setTitle();
}
function reload(){
	jQuery("#dataTable").reloadGrid();
	//autoTitleForGrid();
	//setTitle();
}
function autoTitleForGrid(){
	var num=0;
	var rows = jQuery("#dataTable").getSeletAllRow();
	jQuery.each(rows,function(i,e){
		//alert(rows[i]["qjzt"]);
		if(rows[i]["qjzt"]=="0"){
			num++;
		}
	});
	setTitleHtml(num);
}
//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var qjsqid = rowObject["qjsqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//用于列表更新后（增加/删除）更新提示
function setTitle(){
/*	var rows = jQuery("#dataTable").getSeletAllRow();
	var i=0;
	jQuery(rows).each(function(){
		var qjzt=jQuery(this)["qjzt"];
		if(qjzt=="0"){//草稿状态
			i++;
		}
	});*///无法获取列表加载完成 放弃
	/*jQuery.post("qjsq.do?method=getSwtj",function(data) {
		var wtjs=data["wtjs"];
		setTitleHtml(wtjs);
	},'json');*/
}


//设置列表头html
function setTitleHtml(num){
/*	var ts="";
	if(num!="0"){
		ts+="当前存在&nbsp;<font color='red'>["+num+"]&nbsp;</font>条草稿状态(未提交申请)的请假";
	}
	
	if(frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.jQuery("#title").html(ts);
	} else {
		jQuery("#title").html(ts);
	}*/
	
}
//查看信息
function ckxx(qjsqid) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&qjsqid=" + qjsqid;
	var title = "请假申请信息";
	showDialog(title, 800, 500, url);
}
//协议设置内容弹出
function qjxyCk(){
	var qjxy = jQuery.trim(jQuery("#qjxy").val());
	if(jQuery("#usertype").val() == 'stu' && qjxy != null && qjxy != '' && qjxy != 'undifined'){
		var url = "qjxysz.do?method=qjxyCk";
		var title = "请假承诺书";
		showDialog(title, 750, 500, url);
	}else{
		var url =action+"?method=add";
		var title = "请假申请";
		showDialog(title, 800, 500, url);
	}
	
}
//页面跳转
function  go_Forward(){
	add();
	iFClose();
}

//申请
function add() {
		var url =action+"?method=add";
		var title = "请假申请";
		showDialog(title, 800, 500, url);
		//reload();
		
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

function checkDate(kssj){
	var  kssjDate=Date.parse(kssj.substring(0,10).replace(/\-/g,"/"));
	var nowDate =getNowFormatDate();
    if (Date.parse(nowDate) <= kssjDate) {//时间戳对比  
           return false;  
    }   
    return true;
}
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "/";
//    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
//            + " " + date.getHours() + seperator2 + date.getMinutes();
    return currentdate;
}
function save(url,checkId){
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
	if("11998"==xxdm){
		if (checkDate(jQuery("#kssj").val())) {
			return showAlert('开始时间不能小于当天时间！');
		}
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
	if(!check(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if(!checkother()){
		return false;
	}
	var qjts=jQuery("#qjts").val();
	var qjlxid = jQuery("#qjlxid").val();
	lock();
	jQuery.post("qjsq.do?method=checkTs", {
		qjts:qjts,qjlxid:qjlxid,ssxydm:jQuery("#ssxydm").val()
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
			    	 }else if(data["success"]=="true"){
			    		 showAlert("提交成功!",{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
			    	 }
			 		 else{
			    		 showAlert(data["message"]);
			    	 }
			 		setTitle();
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
	if(chkNumInput("qjts")){
		showAlert("请假天数必须为数字!");
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
			showAlertDivLayer("只有修改未提交或已退回的记录！");
			return false;
		}
		var xh=rows[0]["xh"];
		jQuery.post("qjsq.do?method=isCanAdd", {
		}, function(data) {
			if(data["success"]=="true"){
				var url = action+'?method=update&xh='+xh+'&qjsqid=' + rows[0]["qjsqid"];
				var title = "修改请假类型";
				showDialog(title, 800, 500, url);
				reload();
			}else{
				showAlert("已经存在此请假类型!");
			}
		}, 'json');
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
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除"+data["num"]+"条！";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="</br>的申请已经提交不能删除！";
					}
					showAlertDivLayer(mes);
					reload();
					//showAlertDivLayer(mes);
				}, 'json');
			}
		});
	}
}
//批量提交
function pltj() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				if(shzt!="3"){
					// 验证是否可提交
					jQuery.post(action+"?method=checkTs", {
						qjts:rows[0]["qjts"],qjlxid:rows[0]["qjlxid"],ssxydm:rows[0]["xydm"]
					}, function(data) {
						if(data["success"]=="true"){

							jQuery.post(action+"?method=pltj", {
								values : ids.toString(),
								lcid : rows[0]['splcid'],
								xh : rows[0]['xh'],
                                ssxydm : rows[0]['xydm']
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}else{

							showAlertDivLayer("不存在申请天数对应请假规则!");
						}
					}, 'json');
					
				}else{

					jQuery.post(action+"?method=pltj", {
						values : ids.toString(),
						lcid : rows[0]['splcid'],
						xh : rows[0]['xh']
					}, function(data) {
						showAlert(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}
			}
		});
	}
}
////提交草稿
//function tj() {
//	var rows = jQuery("#dataTable").getSeletRow();
//	if (rows.length != 1) {
//		showAlert("请选择一条您要提交的记录！");
//	} else {
//		var qjzt=rows[0]["qjzt"];
//		
//		if(qjzt=="1"){
//			showAlertDivLayer("已经提交，不能重复提交！");
//			return false;
//		}
//		showConfirmDivLayer("您确定要提交选择的记录吗？", {
//			"okFun" : function() {
//			jQuery.post("qjsq.do?method=tj&type=tj"+'&qjsqid=' + rows[0]["qjsqid"], {
//			}, function(data) {
//			   	 if(data["success"]=="true"){
//			   		showAlertDivLayer("提交成功!",{},{"clkFun":function(){
//						 reload();
//						}});
//				 }else{
//					 showAlertDivLayer("提交失败!");
//				 }
//			   	setTitle();
//			}, 'json');
//		}
//		});
//	}
//}
function rcxwshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {	
		var shlc=rows[0]["splcid"];
		var shztmc=rows[0]["shztmc"];
		if(shlc=="无需审核"||shztmc=="无需审核"){
			showAlertDivLayer("此申请无需审核，无相关流程信息！");
			return false;
		}
		var shzt=rows[0]["shzt"];
		if(shzt=="0"){
			showAlertDivLayer("此申请尚未提交，无相关流程信息！");
			return false;
		}
		showDialog("请假审批流程跟踪",600,400,'comm_spl.do?method=lcgz&sqid='+rows[0]['qjsqid']+"&splc="+rows[0]['splcid']);
	}
}
	
//打印申请表
function printQjsqb(url){
	var qjsqid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				qjsqid +=rows[i]["qjsqid"];
			}else{
				qjsqid +=rows[i]["qjsqid"]+",";
			}
		}
		if("10511"==jQuery("#xxdm").val()){
			var url = "qjsq.do?method=printQjsqbOfHs&qjsqid="+qjsqid;
		}
		else{
			var url = url + "&qjsqid=" +qjsqid;
		}
		window.open(url);
	}
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

function selectQjkc(){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		showDialog("选择请假课程",800,550,"qjsq.do?method=selectQjkc&xh="+xh);
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

	
//浙江机电个性化脚本获取时间差的天数
	function dateDiffWithHours(sDate1, sDate2){ 
		  sDate1 = sDate1.replaceAll("-","/");
		  sDate2 = sDate2.replaceAll("-","/");
		  var aDate, oDate1, oDate2,iDays;
		  oDate1= new Date(sDate1.split(' ')[0]);
		  oDate2= new Date(sDate2.split(' ')[0]);
		  aDate = parseInt((oDate2 - oDate1) / 1000 / 60 / 60 /24);
		  iDays = accAdd(getCommonDate(sDate1,sDate2),(aDate-1)<=0?0:(aDate-1));
		  return iDays;
		}
	
//浙江机电个性化脚本获取日期差的天数
	function dateDiffNotWithHours(sDate1, sDate2){ 
		  sDate1 = sDate1.replaceAll("-","/");
		  sDate2 = sDate2.replaceAll("-","/");
		  var aDate, oDate1, oDate2,iDays;
		  oDate1= new Date(sDate1.split(' ')[0]);
		  oDate2= new Date(sDate2.split(' ')[0]);
		  aDate = parseInt((oDate2 - oDate1) / 1000 / 60 / 60 /24);
		  return aDate;
		}

	/*浙江机电个性化获取首尾天数的时间数*/
	function getCommonDate(sDate1,sDate2){
	 	sarys = sDate1.split(' ');
	 	sarys1 = sarys[1].split(':');
	 	if(sarys1[0]>17){
	 		sarys1[0]=17;
	 		sarys1[1]=00;
	 	}
	 	if(sarys1[0]< 8){//9
	 		sarys1[0] = 8;//9
	 		sarys1[1] = 00;
	 	}
	 	cDate1 = sarys[0]+" 17:00";
	 	darys = sDate2.split(' ');
	 	darys1 = darys[1].split(':');
	 	if(darys1[0]>17){
	 		darys1[0]=17;
	 		darys1[1]=00;
	 	}
	 	if(darys1[0]< 8){//9
	 		darys1[0] = 8;//9
	 		darys1[1] = 00;
	 	}
	 	dDate1 = darys[0]+" 08:00";
	 	var Date1 = new Date(sarys[0]+" "+sarys1[0]+":"+sarys1[1]);
	 	var Date2 = new Date(cDate1);
	 	var Date3 = new Date(darys[0]+" "+darys1[0]+":"+darys1[1]);
	 	var Date4 = new Date(dDate1);
	 	if(sarys[0] == darys[0]){  
		 	return  parseFloat((Date3 - Date1) / 1000 / 60 / 60 /9).toFixed(1);
	 	}else{
		 	var iDays1 = parseFloat((Date2 - Date1) / 1000 / 60 / 60 /9).toFixed(1);
		 	var iDays2 = parseFloat((Date3 - Date4) / 1000 / 60 / 60 /9).toFixed(1);
		 	return accAdd(iDays1,iDays2);
	 	}
	 }
	/*浙江机电个性化浮点数加法函数*/
	function accAdd(arg1, arg2) {
		  var r1, r2, m;
		  try {
		      r1 = arg1.toString().split(".")[1].length
		  } catch (e) {
		      r1 = 0
		  }
		  try {
		      r2 = arg2.toString().split(".")[1].length
		  } catch (e) {
		      r2 = 0
		  }
		  m = Math.pow(10, Math.max(r1, r2))
		  return (arg1 * m + arg2 * m) / m
		}
		//给Number类型增加一个add方法，调用起来更加方便。 
		Number.prototype.add = function (arg) {
		  return accAdd(arg, this);
		}

	//江苏省徐州医药高等职业学院
	function dateDiffFixed2(sDate1, sDate2){
        var  startDate=Date.parse(sDate1.replace(/\-/g,"/"));
        var  endDate=Date.parse(sDate2.replace(/\-/g,"/"));

        var oDate1, oDate2, iDays;
		oDate1 = new Date(startDate);
		oDate2 = new Date(endDate);
		iDays = parseFloat((oDate2 - oDate1) / 1000 / 60 / 60 /24).toFixed(2); //把相差的毫秒数转换为天数
		return iDays;
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
			
	function getNextDate(curDate,n){
		        //var uom = new Date(new Date(curDate)-0+n*86400000);
		        var uom = new Date(Date.parse(curDate.replace(/-/g,   "/")));
		        uom.setDate(uom.getDate() + n);
		        uom = uom.getFullYear() + "-" +  (uom.getMonth()+1) + "-" + uom.getDate();
		        return uom;
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
		var url = "qjsq.do?method=getXsqjspbOne&xh="+rows[0]["xh"]+"&qjsqid="+rows[0]["qjsqid"];
		/*也可以写成
		  var url = "qjsq.do?method=getXsqjspb";
		  url += "&xh=" + rows[0]["xh"] + "&qjsqid=" + rows[0]["qjsqid"];*/
		window.open(url);
	}else{
		var url = "qjsq.do?method=getXsqjspbZip&value="+ids;
		/*也可以写成
		  var url = "qjsq.do?method=getXsqjspbZip";
		  url += "&url=" + ids;*/
		window.open(url);
	}
}