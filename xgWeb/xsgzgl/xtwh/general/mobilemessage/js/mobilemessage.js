var ids = "jsrxm-fsnr";
function textFormatter(cellValue,rowObject){
	
	if(null!=cellValue&&cellValue.length>20){
		cellValue=cellValue.substr(0,20)+"...";
	}
	return cellValue;
}

//消息发送
function saveForm(){
	if(!checkNotNull(ids)){
		showAlert("请将带"+"<font color='red'>*</font>"+"的内容填写完整！");
		return false;
	}
	if(jQuery("#fsnr").length > 200){
		showAlert("发送内容请控制在200字符之内！");
		return false;
	}
	var url = "xtwh_mobileMsg.do?method=saveSendMsg";
	ajaxSubFormWithFun("mobileMessageForm", url, function(data) {
		
		 if(data["message"]=="发送成功"){
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

function viewMsg(){
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length!=1){
		showAlert("请选择一条记录！");
		return false;
	}
	var url="xtwh_mobileMsg.do?method=mobileMsgView&id="+ids;
	showDialog('发送详情查看',500,350,url);
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}