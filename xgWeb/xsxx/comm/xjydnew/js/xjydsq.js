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


// 查看学生异动信息
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='xjydXsInfoCk(\""+row["xjydsqid"]+"\")'>"+cellValue+"</a>";
}


//查看学生异动信息
function xjydXsInfoCk(xjydsqid){
	showDialog("查看学生学籍异动信息",800, 500,"xjydsq.do?method=xjydck&xjydsqid="+xjydsqid,null);
}

//增加
function add() {
		var url ="xjydsq.do?method=xjydsqAdd";
		var title = "学籍异动申请";
		showDialog(title, 830, 500, url);
}

//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("请选择一条您要修改的记录！");
	}  else{
		var shzt = rows[0]["shzt"];
		if(shzt!="0" && shzt!="3"){
			showAlert("请选择未提交或者已退回的记录！");
			return false;
		}
		showDialog("修改学籍异动信息",830, 500,'xjydsq.do?method=xjydsqUpd&xjydsqid='+rows[0]["xjydsqid"]);
	}
}

// 删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlert("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();

		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		
		confirmInfo("您确定要删除<font color='red'>"+ids.length +"</font>条记录吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("xjydsq.do?method=xjydsqDel",{values:ids.toString()},function(data){
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}

/**
 * 提交
 * @return
 */
function pltj(){

	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlert("请选择一条您要提交的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var shzt = rows[0]["shzt"];
		if(shzt!="0" && shzt!="3"){
			showAlertDivLayer("请选择未提交或退回的记录！");
			return false;
		}
		confirmInfo("您确定要提交<font color='red'>"+ids.length +"</font>条记录吗?",function(ty){
			if(ty=="ok"){
				
				if(shzt!="3"){
					// 验证是否可提交
					jQuery.post("xjydsq.do?method=checkSfktj", {
						ydlbdm:rows[0]["ydlbdm"]
					}, function(data) {
						if(data ==null || data=="false"){
							showAlertDivLayer("您申请的异动已关闭申请，无法提交，详情请联系管理员。");
							return false;
						}else{
							// 
							jQuery.post("xjydsq.do?method=xjydsqPltj&shzt=5",{values:ids.toString()},function(data){
								showAlert(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
						}
					});
					
				}else{

					// 提交
					jQuery.post("xjydsq.do?method=xjydsqPltj&shzt=5",{values:ids.toString()},function(data){
						showAlert(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			}
		});
	}
}

/**
 * 撤销
 * @return
 */
function plqxtj(){

	var ids = jQuery("#dataTable").getSeletIds();
	var shlcidnew = new Array();
	
	if (ids.length == 0){
		showAlert("请选择您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			var shzt = rows[i]["shzt"];
			if(shzt!="5"){
				showAlert("请选择审核中的记录！");
				return false;
			}
		}
		
		jQuery.each(rows,function(i,row){
			if(row["shzt"]=="3"){
				shlcidnew.push(row["shlcidnew"]);
			}else{
				shlcidnew.push(row["splcid"]);
			}
		})
		
		
		confirmInfo("您确定要撤销<font color='red'>"+ids.length +"</font>条记录吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("xjydsq.do?method=xjydsqPltj&shzt=0",{values:ids.toString()},function(data){
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}


//审批流查看
 function rcxwshLcinfo(){
  	var rows = jQuery("#dataTable").getSeletRow();
  	if (rows.length != 1){
  		showAlert("请选择一条记录！");
  	} else {

		var rows = jQuery("#dataTable").getSeletRow();
		var shzt = rows[0]["shzt"];
		if(shzt=="0"){
			showAlert("请选择已提交的记录！");
			return false;
		}
  		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['xjydsqid']+"&splc="+rows[0]['splcid']);
  	}
 }
	  

//查询
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function exportConfig() {
	customExport("xjyd_xjydsq.do", exportData,690,500);
}
// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "xjydsq.do?method=exportData&dcclbh=xjyd_xjydsq.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 显示学籍和班级信息
 * @return
 */
function initShow(){
	var xjlbdm = jQuery("#ydlbdm").val();
	if(xjlbdm == ""){
		jQuery("#xjlbmc").html("");
		jQuery("#sfyxj").html("");
		jQuery("#sfzx").html("");
		jQuery("#tzbj").hide();
		
	}else{
		jQuery.post("xjyd.do?method=xjydlbShpzGet",{values:xjlbdm},function(data){
			if(data != null){
				jQuery("#xjlbmc").html(data["xjlbmc"]);
				jQuery("#sfyxj").html(data["sfzxmc"]);
				jQuery("#sfzx").html(data["sfzxmc"]);
				if(data["sftjbj"]=='0'){
					jQuery("#tzbj").show();
				}else{
					jQuery("#tzbj").hide();
				}
			}
		},'json');
	}
}
