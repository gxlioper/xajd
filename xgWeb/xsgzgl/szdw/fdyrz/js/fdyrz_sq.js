//初始化查询
var gridSetting = {
		caption:"辅导员任职申请列表",
		pager:"pager",
		url:"szdw_fdyrz_sq.do?method=gjcxWdsq&type=query",
		colList:[
		   {label:'职工号',name:'zgh', index: 'zgh',width:'10%',hidden:true},//,formatter:xhLink
		   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'审核状态',name:'shzt', index: 'shzt',width:'15%'},
		   {label:'专兼职',name:'zjz', index: 'zjz',width:'7%'},
		   {label:'带班个数',name:'sqdbgs', index: 'sqdbgs',width:'7%'},
		   {label:'申请理由',name:'sqly', index: 'sqly',width:'60%'},
		   {label:'splc',name:'splc', index: 'splc',hidden:true},
		   {label:'主键',name:'sqid', index: 'sqid',key:true,hidden:true},
		   {label:'shztdm',name:'shztdm',index:'shztdm',hidden:true}
		],
		sortname: "sqsj",
	 	sortorder: "desc"
};

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='ckXsxxgl(\""+rowObject["xxgldm"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
//辅导员任职申请
function goFdyrzsq(){
	jQuery.post("szdw_fdyrz_sq.do?method=yzFdyrzsq",{},function(data){
		var message = data["message"];
		if(message=="true"){
			showDialog("我的任职申请",765,460,"szdw_fdyrz_sq.do?method=fdyrzsq");
		}else{
			alertInfo(message);
		}
	},'json');
}

//修改申请
/*function update(){
	jQuery.post("szdw_fdyrz_sq.do?method=yzFdyrzsq",{},function(data){
		var message = data["message"];
		if(message=="true"){
			showDialog("我的任职申请",765,530,"szdw_fdyrz_sq.do?method=fdyrzsq");
		}else{
			alertInfo(message);
		}
	},'json');
	
}*/

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
		var url = 'szdw_fdyrz_sq.do?method=fdyrzsqxg&sqid='+rows[0]["sqid"];
		var title = "修改任职申请";
		showDialog(title, 720, 470, url);
	}

}



var scrollHandler = function(){  myScroll = jQuery(window).scrollTop();  };
//取消申请
function fdyrz_qxsq(bjdm){
	var xzbj_text =jQuery("#xzbj_text").val();
	var reg = new RegExp("," + bjdm,"g");
	xzbj_text = xzbj_text.replace(reg,"");
	jQuery("#xzbj_text").val(xzbj_text);
	jQuery("#xzbj").load("szdw_fdyrz_sq.do?method=fdyrzsqbj&bjdm="+bjdm+"&type=qx");
}
/*//显示隐藏选择班级
function show_xzbj(){
	if(jQuery("#bjlist").is(":hidden")){
		jQuery("#bjlist").show();
		jQuery("#bj_flag").html("▽");
	}else{
		jQuery("#bjlist").hide();
		jQuery("#bj_flag").html("△");
	}
}*/
//申请保存
function save(obj){
	var zjz = jQuery("#zjz").val();
	var sqly = jQuery("#sqly").val();
	var splc = jQuery("#splc").val();
	var sqdbgs = jQuery("#sqdbgs").val();
	if(zjz==null || zjz ==""){
		alertInfo("请选择专兼职");
	}else if(sqly==null || sqly ==""){
		alertInfo("请填写申请理由！");
	}else if(sqdbgs==null || sqdbgs ==""){
		alertInfo("请输入申请班级数量");
	}else if(sqly.length>500){
		alertInfo("申请理由不能多于500字");
	}else if(zjz=="兼职" && sqdbgs>4){
		alertInfo("兼职最多可申请四个班级");
	}else{
		var url = "";
		if(obj == "submit"){
		  url="szdw_fdyrz_sq.do?method=fdyrzsq&type=submit";
		}else{
		  url="szdw_fdyrz_sq.do?method=fdyrzsq&type=save";
		}
		jQuery.ajaxSetup({
			 contentType:"application/x-www-form-urlencoded;charset=utf-8"
		});
		jQuery("#but_save").attr("disabled","true");
		jQuery.ajax({
			   type: "POST",
			   url: url,
			   data: { zjz:zjz,sqly:sqly,splc:splc,sqdbgs:sqdbgs },
			   dataType: "json",
			   success: function(data){
			     if(data.success =="false"){
			    	 jQuery("#but_save").attr("disabled",false);
			     }else{
			    	 //refreshForm("szdw_fdyrz_sq.do?method=gjcxWdsq");
			    	// iFClose();
			    	 showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
			    	
			     }
			   }
			});
	}
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//返回
function go_back(){
	refreshForm("szdw_fdyrz_sq.do?method=gjcxWdsq");
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
			if(shzt!='审核中'){
				alertInfo("您的申请已进入审批流程不能撤销");
				return false;
			}else{
				check = true;
			}
		});
		if(check){
			confirmInfo("您确定要取消"+rows.length +"条申请记录吗?",function(ty){
				if(ty=="ok"){
					sqids= sqids.replace(",", "");
					jQuery.post("szdw_fdyrz_sq.do?method=fdyrzqxsq",{sqids:sqids},function(data){
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