var gridSetting = {
		caption:"辅导员培训项目申请",
		pager:"pager",
		url:"szdw_fdypxxmsq.do?method=fdypxxmsqList&type=query",
		colList:[
		   {label:'申请代码',name:'sqid', index: 'sqid',width:'1%',key:true,hidden:true},
		   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'20%',formatter:xhLink},
		   {label:'培训地点',name:'pxdd', index: 'pxdd',width:'20%'},
		   {label:'培训时间',name:'pxsj', index: 'pxsj',width:'20%'},
		   {label:'发布人',name:'fbr', index: 'fbr',width:'15%'},
		   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'审核状态',name:'shzt', index: 'shzt',width:'15%'},
		   {label:'项目代码',name:'xmdm', index: 'xmdm',width:'1%',hidden:true},
		   {label:'审批流程',name:'splc', index: 'splc',width:'1%',hidden:true},
		   {label:'shztdm', name:'shztdm', index:'shztdm',hidden:true} 
		],
		sortname: "sqsj",
	 	sortorder: "desc"
	}
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["xmdm"]+"\")'>"+cellValue+"</a>";
}
function add(){
	jQuery.post("szdw_fdypxxmsq.do?method=yzFdypxsq",{},function(data){
		var message = data["message"];
		if(message=="true"){
			showDialog("培训项目申请",700,505,"szdw_fdypxxmsq.do?method=fdypxxmSq");
		}else{
			alertInfo(message);
		}
	},'json');
	
}

function xzpxxm(){
	showDialog("培训项目选择",700,500,"szdw_fdypxxmwh.do?method=fdypxxmsqList");
}

function view(xmdm){
	showDialog("查看培训项目",700,300,'szdw_fdypxxmwh.do?method=fdypxxmView&xmdm='+xmdm);
}

function save(method){
	if(yanzheng()){
		jQuery.post("szdw_fdypxxmsq.do?method=yzFdypxsq",{xmdm:jQuery("#xmdm").val()},function(data){
			if(data.message!="true"){
				alertInfo(data.message);
			}else{
				//验证成功后才能进行保存
				var url = "szdw_fdypxxmsq.do?method="+method+"&type=save";
				ajaxSubFormWithFun("demoForm",url,function(data){
					 showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
								
							}
							//refreshForm("szdw_fdypxxmsq.do?method=fdypxxmsqList");
						}});
				});
			}
		},'json');
		
	}
}
function yanzheng(){
	var xmdm = jQuery("#xmdm").val();
	var sqly = jQuery("#sqly").val();
	if(xmdm=="" || xmdm == undefined){
		alertInfo("请选择培训项目");
	}else if(sqly.length>=2000){
		alertInfo("申请理由不能超过2000字");
	}else{
		return true;
	}
	return false;
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//审批流查看
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if (rows.length != 1){
		alertInfo("请选择一条记录！");
	} else {
		if ("未提交" == shzt){
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}
//取消申请
function qx_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length>=1){
		var sqids = "";
		//获取选择的申请编号并用 ,拼接
		var check = false;
		jQuery(rows).each(function(i){
			sqids = sqids+","+rows[i]["sqid"];
			var shzt = rows[i]['shzt'];
			if(shzt!='待审核'){
				alertInfo("您的申请已进入审批流程不能撤销");
				return false;
			}else{
				check = true;
			}
		});
		if(check){
			confirmInfo("您确定要取消"+rows.length +"条申请记录吗?",function(ty){
				//alert(ty);
				if(ty=="ok"){
					jQuery.post("szdw_fdypxxmsq.do?method=qxsq",{sqids:sqids},function(data){
						if(data["success"]=="true"){
							alertInfo("取消申请成功");
						}else{
							alertInfo("取消申请失败");
						}
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			});
		}
		
		
	}else{
		alertInfo("请选择一条需要取消的记录");
	}
}

//修改申请
function update() {
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt = rows[0]["shzt"];
		if ("审核中" == shzt){
			showAlertDivLayer("该信息记录正在审核中，不能修改！");
			return false;
		}
		if ("通过" == shzt){
			showAlertDivLayer("该信息记录审核已经通过，不能修改！");
			return false;
		}
		if ("不通过" == shzt){
			showAlertDivLayer("该信息记录审核已经不通过，不能修改！");
			return false;
		}
		var url = 'szdw_fdypxxmsq.do?method=fdypxxmsqXg&sqid='+rows[0]["sqid"]+'&xmdm='+rows[0]["xmdm"];
		var title = "修改辅导员培训申请";
		showDialog(title, 720, 505, url);
	}

}