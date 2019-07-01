/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 增加
 * @return
 */
function sspyjgAdd(){
	var url = "sspyjg.do?method=sspyjgAdd";
	var title = "宿舍评优结果增加";
	showDialog(title,790,550,url);
}

/**
 * 选择楼栋onchange事件
 * @return
 */
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
	
	jQuery("#xsList").empty();
	var ldqsxx=jQuery('#lddm').val()+jQuery('#qsh').val();
	var html = "";
	jQuery.post("sspysq.do?method=getCwxx", {
		ldqsxx : ldqsxx
	}, function(data) {
		for(var i =0;i<data.length;i++){
			html+="<tr><td align='center'>"+data[i]["xh"]+"</td><td align='center'>"+data[i]["xm"]+"</td><td align='center'>"+data[i]["xsbjmc"]+"</td><td align='center'>"+data[i]["cwh"]+"</td></tr>";
		}
		jQuery("#xsList").append(html);
	}, 'json');
}


/**
 * 定义必填字段集合
 */
var ids = "lddm-qsh-ch-xn-xq-pyxmdm-sqly";

/**
 * 保存
 * @return
 */
function sspyjgSave(){
	
	/*验证必填字段*/
	if(!checkNotNull(ids)){
		showAlert("请将带<font class='red'>*</font>的项目填写完整！");
		return false;
	}
	
	var url = "sspyjg.do?method=sspyjgSave";
	ajaxSubFormWithFun("sspyjgForm", url, function(data){
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

/**
 * 修改
 * @return
 */
function sspyjgUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else{
		if ("1" == rows[0]["sjly"]){
			showAlertDivLayer("审核流程数据不能修改！");
			return false;
		}
		var title = "修改自主申请";
		var url = "sspyjg.do?method=sspyjgUpdate&guid="+rows[0]["guid"];
		showDialog(title, 680, 490, url);
	}
}

/**
 * 删除
 * @return
 */
function sspyjgDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("sspyjg.do?method=sspyjgDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * 导出功能编号
 */
var DCDLBH = "sspy_jg.do";

/**
 * 宿舍评优导出
 * @return
 */
function sspyjgExport(){
	customExport(DCDLBH, sspyjgExportData);
}

/**
 * 导出执行
 */
function sspyjgExportData() {
	/*设置高级查询条件*/
	setSearchTj();
	var url = "sspyjg.do?method=sspyjgExport&dcclbh=" + DCDLBH;
	/*设置高级查询参数*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 寝室号连接
 * @return
 */
function qshLink(cellValue, rowObject){
	return "<a hrer='javascript:void(0);' class='name' onclick='sspyjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

/**
 * 查看
 * @return
 */
function sspyjgView(guid){
	var url = "sspyjg.do?method=sspyjgView&guid="+guid;
	var title = "宿舍评优结果查看";
	showDialog(title,700,485,url);
}