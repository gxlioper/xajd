//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var gypyid = rowObject["gypyid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + gypyid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(gypyid) {
	var url = "gypy.do?method=detail&gypyid=" + gypyid;
	var title = "文明宿舍信息";
	showDialog(title,  600, 280,  url);
}
function add() {
	var url = "gypy.do?method=add";
	var title = "增加文明宿舍";
	showDialog(title,600, 280,url);
	jQuery("#dataTable").reloadGrid();
}
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'gypy.do?method=update&gypyid=' + rows[0]["gypyid"];
		var title = "修改文明宿舍";
		showDialog(title,600, 280, url);
		//jQuery("#dataTable").reloadGrid();
	}
}
//删除
function del() {
	var rows=jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("gypy.do?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function loadLdxx(){
	jQuery.post('gyglnew_qsgl.do?method=loadLdxx',{lddm:jQuery('#lddm').val()},function(data){
		var qsch = parseInt(data.qsch);
		var ldcs = parseInt(data.ldcs);
		var sfhlc = data.sfhlc;
		var ldxb = data.ldxb;
		var count = 0;
		jQuery('#ch').empty();
		jQuery('#ch').append("<option value=''>--请选择--</option>");
		while(count<ldcs){
			var chdm;
			var chmc;
			if('否' == sfhlc){
				
				if((qsch+count)>= 0){
					chdm = qsch>0 ? qsch+count:qsch+count+1;
					chmc = chdm + "层";
				}else{
					chdm = qsch+count;
					chmc = "B" + Math.abs(chdm) + "层";	
				}
				
			}else{
				chdm = qsch+count;
				chmc = chdm<0 ? "B" + Math.abs(chdm) + "层" : chdm + "层";
			}
			
			var option = "<option value=\"" + chdm + "\">" + chmc + "</option>"
			jQuery('#ch').append(option);
			
			count ++;
		}
		loadQs();
	},'json');
}
function loadQs(){
	jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm',{lddm:jQuery('#lddm').val(), ch:jQuery('#ch').val()},function(data){
		jQuery('#qsh').empty();
		jQuery('#qsh').append("<option value=''>--请选择--</option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].qsh + "\">" + data[i].qsh + "</option>";
				jQuery('#qsh').append(option);
			}
		}
	});
}
function loadQsdh(){
	var obj = new Object;
	obj.lddm = jQuery('#lddm').val();
	obj.ch = jQuery('#ch').val();
	obj.qsh = jQuery('#qsh').val();
	jQuery.getJSON('gypy.do?method=loadQsxxdh',obj,function(data){
			var qsdh = data.qsdh;
			jQuery('#qsdh').val(qsdh);
			jQuery('#xydm').val(data.xydm);
	});
}
function checkChar(obj){
	if (obj.value.match(/[\u4e00-\u9fa5]/g)) {
		alertInfo("只能输入字符,不能输入中文!",function (){obj.value="";});

		//return false;
	 }	
}
function save(url,checkId){
	if(!check(checkId)){
		return alertError("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if(!checkData("已经存在此文明宿舍！")){
		return false;
	}
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
}
//检测长度
function checkLength(obj,len){
	var str=jQuery("#"+obj).val();
	alert(str);
     	if(str.replace(/[^\u0000-\u00ff]/g, "**").length > len){	         
     		alertError("此项不能大于"+len+"个字符！");
     		jQuery("#"+obj).focus();
      		return false;
   		}
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
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

function exportConfig() {
	customExport("gypy.do", exportData);
}
	

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "gypy.do?method=export&pylx=1&dcclbh=" + "gypy.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
/**
 * 验证数据且提示
 * @param gypyid 验证数据id
 * @param mes	提示信息
 * @return
 */
function checkData(mes){
	var isCanDo=true;
	if(mes==""||null==mes){
		mes="已经存在数据！";
	}
 	jQuery("#form").ajaxSubmit({
		url:"gypy.do?method=checkData",
		type:"post",
		async:false,
		dataType:"json",
		success:function(data){
	 		if(data["message"]=="false"||data["message"]==false){
				showAlert(mes);
				isCanDo=false;
			}
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
 	return isCanDo;
}