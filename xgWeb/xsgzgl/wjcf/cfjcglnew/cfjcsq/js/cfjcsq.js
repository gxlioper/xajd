function add(){
	var rows=jQuery("#dataTable").getSeletRow();
	if(rows.length!=1){
		alertInfo("请选择一条您要"+jQuery("#xbmc").val()+"的记录！");
	}else{
		if(rows[0]["shztmc"]!="未提交申请"){
			alertInfo("不能重复申请"+jQuery("#xbmc").val()+"！");
			return false;
		}
		showDialog("处分"+jQuery("#xbmc").val()+"申请",760,500,'wjcf_cfjcsq.do?method=cfjcsqAdd&cfid='+rows[0]["cfid"]+'&xh='+rows[0]["xh"]);
	}
}
function save(url){

    if(!check("sqly-bjyj-qksm-filepath-filepath2")){
        return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
    }
	ajaxSubFormWithFun("cfjcsqForm",url,function(data){
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

function update(){
	var rows=jQuery("#dataTable").getSeletRow();
	if(rows.length!=1){
		alertInfo("请选择一条您要修改的记录！");
	}else{
		if(rows[0]["shztmc"]=="未提交申请"){
			alertInfo("违纪记录还没申请"+jQuery("#xbmc").val()+"，不能修改！");
			return false;
		}
		if(rows[0]["shzt"]!="0"&& rows[0]["shzt"]!="3"){
			alertInfo(""+jQuery("#xbmc").val()+"申请已审核，不能修改！");
			return false;
		}
		showDialog("处分"+jQuery("#xbmc").val()+"修改",760,500,'wjcf_cfjcsq.do?method=cfjcsqUpdate&cfid='+rows[0]["cfid"]+'&xh='+rows[0]["xh"]+'&jcid='+rows[0]["jcid"]);
	}
}

function updateSave(url){
    if(!check("sqly-bjyj-qksm-filepath-filepath2")){
        return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
    }
	ajaxSubFormWithFun("cfjcsqForm",url,function(data){
	  	 if(data["message"]=="保存成功！" ||data["message"]=="提交成功！"  ){
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

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length==0){
		alertInfo("请选择您要删除的记录！");
	}else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("wjcf_cfjcsq.do?method=cfjcsqDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
			}});
	}
}

//审批流查看
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条记录！");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids[0]==null){
		alertInfo("还未申请"+jQuery("#xbmc").val()+"，不能跟踪流程！");
		return false;
	}
	
	var shzt = rows[0]["shztmc"];

	if ("未提交" == shzt){
				showAlertDivLayer("该记录为未提交状态，请先提交！");
				return false;
	}
	
	showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['jcid']+"&splc="+rows[0]['splcid']);
}

function showCfqxFlag(cflbdm){
	//对于青岛酒店管理职业技术学院屏蔽该功能
	if(jQuery("#xxdm").val()=='13011') return false;
	
	if(cflbdm==""){return false;}
	var cfqx = "";
	jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
		jQuery("#showCfqx").html(data["message"]);
	},'json');
}

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