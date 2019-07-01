var checkId="ystxmmc-ystlbdm-xmlbdm-lxdh-gkdwdm-fzrlb-fzr-zdls-ystclsj-splc";
var zdmcList='艺术团简介-艺术团获奖情况';
var zsnumList='500-500';

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='YstwhView(\""
			+ rowObject["ystid"] + "\");'>" + cellValue + "</a>";
}

function YstwhView(ystid) {
	showDialog("艺术团查看", 750, 400, "ystglYstwh.do?method=viewYstwh&ystid="
			+ ystid);
}
function showXsxxNotF5CallBack(rowData) {
	jQuery("#stfzrxm").val(rowData["xm"]);
	jQuery("#fzr").val(rowData["xh"]);
}
//选择教师回调函数
function showFdysNotF5CallBackAnother(rowData) {
	jQuery("#stfzrxm").val(rowData["xm"]);
	jQuery("#fzr").val(rowData["zgh"]);
}

function showFdysNotF5CallBack(rows){
	jQuery("#zdlsxm").val(rows['xm']);
	jQuery("#zdls").val(rows['zgh']);
	jQuery("#ssbm").val(rows['bmdm']);
	jQuery("#zdlslxfs").val(rows['lxdh']);
	jQuery("#zcmc").val(rows['zcmc']);
	jQuery("#zdlszc").val(rows['zc']);
	
}
//项目设置格式化
function setXmsz(cellValue, rowObject) {
	var ystid = rowObject.ystid;
	var splc = rowObject.splc;
	var value = "未设置";
	var status = '0';
	var color;
	var currDate = jQuery("#currDate").val();
	if ((null == rowObject.sqkssj || currDate >= rowObject.sqkssj)
			&& (null == rowObject.sqjssj || currDate <= rowObject.sqjssj)&&'1'==rowObject.sqkg) {
		value = "已设置";
		status = '1';
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return xmsz(\"" + ystid
			+ "\",\"" + splc+ "\");' >" + value + "</a>";
	return value;
}
function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}
/*
 * 项目设置详情
 */
function xmsz(ystid,splc) {
	if(splc == "null" || splc == null || jQuery.trim(splc) == ""){
		showAlertDivLayer("请现在艺术团修改中添加审核流程！");
		return false;
	}
	if (ystid == null) {// 点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		ystid = rows[0]["ystid"];
	}
	var url = 'ystglYstwh.do?method=xmsz&ystid=' + ystid;
	var title = "项目设置";
	showDialog(title, 520, 380, url);
}
function saveYstwh(type) {
	var flg = true;
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	if(!checkTextAreaLength("ystjj-ysthjqk",zdmcList,zsnumList)){
		flag=false;
	}
	jQuery("#ssbm").attr("disabled",false);; 
	var url = "ystglYstwh.do?method=saveYstwh&type="+type;
	ajaxSubFormWithFun("YstwhForm", url, function(data) {
		if (data["message"] == "保存成功！") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			flg = false;
			 showAlert(data["message"]);
		}
	});
}
function add() {
	var url = "ystglYstwh.do?method=addYstwh";
	var title = "艺术团增加";
	showDialog(title, 800, 550, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else if ('false' == rows[0]["sfkxg"]) {
		showAlertDivLayer("请选择用户本人申报的项目！");
	} 
	else {
		var url = 'ystglYstwh.do?method=editYstwh&ystid=' + rows[0]["ystid"];
		var title = "艺术团修改";
		showDialog(title, 800, 550, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("是否确定删除勾选的记录？", {
		"okFun" : function() {
		var url = "ystglYstwh.do?method=delYstwh";
		jQuery.post(url, {
			values : ids.toString()
		}, function(data) {
			if (data["success"] == false) {
				showAlertDivLayer(data["message"]);
			} else {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function(tag) {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}
		}, 'json');
	
}});
}
}

//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_N730203_YSTWH");
	return false;

}

var DCCLBH = "ystgl_ystgl_ystwh.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, YstwhExportData);
}

// 导出方法
function YstwhExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "ystglYstwh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//修改
function updatestcyxx(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		} 
		var ystid = rows[0]["ystid"];
		var rtid = "";
		if(getsqkg(ystid) != 1){
			showAlertDivLayer("当前艺术团项目未在申请状态，不允许修改成员信息！");
			return false;
		}
	var url = 'stglRtjg.do?method=RtjgWh&ystid=' + ystid+"&rtid="+rtid;
	var title = "艺术团成员明细修改";
	showDialog(title, 770, 550, url);
	
	
}

function getsqkg(ystid){
	var sqkg = 1;
	var url = "stglRtjg.do?method=getSqkg";
			jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:'ystid='+ystid,
			async: false,
			success:function(result){
				if(result==null||result=="null"){
					return false;
				}else{
					sqkg = result;
				}
			}
			
		});
	   return sqkg;
	
}

function addXs() {
	var xmdm=jQuery("#xmdm").val();
	var jcxf=jQuery("#jcxf").val();
	var xhArr = new Array();
	jQuery.each(jQuery("#tbody_xmsqxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhArr.push(xh);
	});
	var url = 'stglRtjg.do?method=getStu'+'&xhArr='+xhArr;
	showDialog('', 800, 550, url);
	return false;
}

function delXs() {
	var sjly="";
	var xhs="";
	var xh="";
	var rtids = "";
	var message = "删除成功！";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("请选择需要删除学生！");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		sjly=jQuery(checkbox[i]).parents("tr:eq(0)").find("input[name=sjly]").val();
		xh=jQuery(checkbox[i]).parents("tr:eq(0)").find("input[name=xh]").val();
		temprtid = jQuery(checkbox[i]).parents("tr:eq(0)").find("input[name=rtid]");
		if("1"==sjly){
			if(i!=0){
				xhs+=",";
			}
			xhs+=xh;
		}
		if(temprtid != null){
			if(jQuery(temprtid).val() != '' &&　jQuery(temprtid).val() != null){
				rtids+= jQuery(temprtid).val()+",";
			}
		}
	}
	if(""!=xhs){
		showAlertDivLayer("<font color='red'>["+xhs+"]</font>"+"为流程数据，不能删除！");
		return false;
	}
	if(rtids != "" && rtids != null){
		var url = "stglRtjg.do?method=delCyxx";
		jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:'values='+rtids.toString(),
		async: false,
		success:function(result){
			if(result==null||result=="null"){
				return false;
			}else{
				message = "删除成功！";;
			}
		 }
	    });
	}

	for ( var i = 0; i < checkbox.length; i++) {
	jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
	showAlert(message);
}

function setSqxs(rows,selobj) {

	var html = "";
	var jcxf=jQuery("#jcxf").val();
	var api = frameElement.api;
	var qqxsTrLen = api.get('parentDialog').jQuery("#tbody_xmsqxx tr").length;	
	for ( var int = 0; int < rows.length; int++) {
		var index=qqxsTrLen+int;
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/>"+"<input type='hidden' name='xh' id = 'xh_"+int+"' value='" +rows[int]["xh"]+"'/><input type='hidden' name='rtid' value=''/>"+"</td>";
		html += "<td>" + rows[int]["xh"] + "</td>";
		html += "<td>" + rows[int]["xm"] + "</td>";
		html += "<td>" + rows[int]["xb"] + "</td>";
		html += "<td>"+selobj+"</td>"
		html +="<td>"+"<input name = 'tc' id='tc_"+int  + "' maxlength='100'>";
		html +="<td>"+"<input name = 'sqly' id='sqly_"+int  + "' maxlength='100'>";
		

		jQuery("#tbody_xmsqxx").append(html);
	}
	var W;
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			W = api.get('parentDialog')
		} else {
			W = api.opener;
		}
	} else if (parent.window) {
		W = parent.window;
	}
	W.sqxsZj(html);

	iFClose();
}

function sqxsZj(html){
	jQuery("#tbody_xmsqxx").append(html);	
}

function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}

//维护信息保存
function saveJgwh(){
	//alert(parseInt(jQuery("#flagnum").val()));
	if(jQuery("#tbody_xmsqxx tr").length == 0){
		showAlert("请先填写成员信息！");
		return false;
	}
	var flag = true;
	if(jQuery("input[name='usertype']").val() != 'stu'){
		jQuery("#tbody_xmsqxx tr").each(function(b){
			var temptc=jQuery(this).find("td:eq(5) input[name='tc']").val();
			var tempsqly=jQuery(this).find("td:eq(6) input[name='sqly']").val();
			if(temptc == '' || tempsqly == ''){
				flag = false;
				showAlert("带"+"<font color='red'>*</font>"+"必填项不可为空！");
				return false;
			}
		});
	}else{
		var temptc=jQuery("input[name='tc']").val();
		var tempsqly=jQuery("input[name='sqly']").val();
		if(temptc == '' || tempsqly == ''){
			flag = false;
			showAlert("带"+"<font color='red'>*</font>"+"必填项不可为空！");
			return false;
		}
	}

	if(flag == false){
		return false;
	}
	if(jQuery("input[name='usertype']").val() != 'stu'){
		jQuery("select[name='rylbdm']").attr("disabled",false);
		var url = "stglRtjg.do?method=saveJgwh"
			ajaxSubFormWithFun("RtjgForm", url, function(data) {
				 if(data["message"]=="保存成功！"){
		   		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		   	 }else{
		   		 showAlert(data["message"]);
		   		}
				});
	}else{
		var url = "stglRtjg.do?method=saveJgwh_XS"
			ajaxSubFormWithFun("RtjgForm", url, function(data) {
				 if(data["message"]=="保存成功！"){
		   		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		   	 }else{
		   		 showAlert(data["message"]);
		   		}
				});
	}
	
}

//复制
function copyYstxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一个艺术团项目！");
		return false;
	}
	var url = "ystglYstwh.do?method=copyYstxx&ystxmmc="+rows[0]['ystxmmc']+"&ystid="+rows[0]['ystid'];
	var title = "艺术团复制";
	showDialog(title, 400, 180, url);
}


//保存艺术团
function saveCopyYstxx(){
	if(jQuery("#ystxmmc").val()=="" || jQuery("#xn").val()==""){
		showAlert("艺术团项目名称和有效学年不能为空！")
		return false;
	}
	var url = "ystglYstwh.do?method=saveCopyYstxx"
		ajaxSubFormWithFun("YstwhForm", url, function(data) {
			 if(data["message"]=="保存成功！"){
		   		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		   	 }else{
		   		 showAlert(data["message"]);
		   	 }
		});
	
}


//ajax动态获取项目类别list
function changeYstlb(){
	var ystlbdm=jQuery("#ystlbdm").val();
	var html="";
	jQuery.post('ystglYstwh.do?method=getXmlblist',{'ystlbdm':ystlbdm},function(data){
		if(data != null){
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				html+="<option value='"+o.xmlbdm+"'>"+o.xmlbmc+"</option>";
			}
		}
		jQuery("#xmlbdm").html(html);
	},'json');
};