//查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//写信
function xx(){
	var url = "wdznx.do?method=xx";
	var title = "站内信新增";
	showDialog(title, 770, 500, url);
}

//学生消息发送
function saveXsXXForm(){
	//富文本编辑器内容同步到name为editorid的textarea中，用于后台获取
	editor.sync();
	var ids = "ztlb"+"-"+"xjzt";
	//富文本编辑器内容是否为空调用html()方法获得内容
	var html=editor.html();
	var htmlString = editor.text().length;
	if(checkNotNull(ids) == false ||html==null||html=="" || htmlString == 0){
		showAlert("请将带"+"<font color='red'>*</font>"+"的内容填写完整！");
		return false;
	}
	if(htmlString > 1000){
		showAlert("发送内容请控制在1000字符之内！");
		return false;
	}
	var url = "wdznx.do?method=saveXX";
	ajaxSubFormWithFun("WdznxForm", url, function(data) {
		 if(data["message"]=="信件发送成功！"){
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

//教师信息发送
function saveTeaXXForm(){
	//富文本编辑器内容同步到name为editorid的textarea中，用于后台获取
	editor.sync();
	var ids = "jsrxm"+"-"+"xjzt";
	//富文本编辑器内容是否为空调用html()方法获得内容
	var html=editor.html();
	var htmlString = editor.text().length;
	if(checkNotNull(ids) == false ||html==null||html=="" || htmlString == 0){
		showAlert("请将带"+"<font color='red'>*</font>"+"的内容填写完整！");
		return false;
	}
	if(htmlString > 1000){
		showAlert("发送内容请控制在1000字符之内！");
		return false;
	}
	var url = "wdznx.do?method=saveXX";
	ajaxSubFormWithFun("WdznxForm", url, function(data) {
		 if(data["message"]=="信件发送成功！"){
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

//收件箱标题查看链接，未查看信件标题的字体加黑
function sjxBtLink(cellValue, rowObject) {
	if(rowObject["jsrydbj"] == '0'){
		return "<a href='javascript:void(0);' style='font-weight:bold;' class='name' onclick='sjxBtView(\""
		+ rowObject["xjbh"] + "\",\"" + rowObject["jsrbh"] + "\",\"" + rowObject["fsrxm"] + "\",\"" + "wd" + "\");'>" + cellValue
		+ "</a>";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='sjxBtView(\""
		+ rowObject["xjbh"] + "\",\"" + rowObject["jsrbh"] + "\",\"" + rowObject["fsrxm"] + "\",\"" + "yd" + "\");'>" + cellValue
		+ "</a>";
	}
	
}

//查看函数
function sjxBtView(xjbh,jsrbh,fsrxm,type){
	showDialog("站内信查看", 770, 450, "wdznx.do?method=sjxCk&xjbh="
			+ xjbh + "&jsrbh=" + jsrbh+"&type="+type+"&fsrxm="+fsrxm);
}

//发件箱标题查看链接
function fjxBtLink(cellValue, rowObject) {
		return "<a href='javascript:void(0);' class='name' onclick='fjxBtView(\""
		+ rowObject["xjbh"] + "\",\"" + rowObject["jsrbh"] + "\",\"" + rowObject["jsrxm"] + "\");'>" + cellValue
		+ "</a>";
}

//查看函数
function fjxBtView(xjbh,jsrbh,jsrxm){
	showDialog("站内信查看", 770, 450, "wdznx.do?method=fjxCk&xjbh="
			+ xjbh + "&jsrbh=" + jsrbh+"&jsrxm="+jsrxm);
}

//收件箱删除
function sjxsc(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} 
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("wdznx.do?method=delScSjxjl",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}
//发件箱删除
function fjxsc(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} 
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("wdznx.do?method=delScFjxjl",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

//站内信回复
function znxhf(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要回复的记录！");
		return false;
	} 
	showDialog("站内信回复", 770, 450, "wdznx.do?method=xjHf&xjbh="
			+ rows[0]['xjbh']+"&jsrbh="+rows[0]['jsrbh']+"&fsrxm="+
			rows[0]['fsrxm']+"&jsrydbj="+rows[0]['jsrydbj']);
}

//站内信回复保存
function saveZnxhf(){
	//富文本编辑器内容同步到name为editorid的textarea中，用于后台获取
	editor.sync();
	var ids = "xjzt";
	var html=editor.html();
	var htmlString = editor.text().length;
	if(checkNotNull(ids) == false||html==null||jQuery.trim(html)=="" || htmlString == 0){
		showAlert("信件主题和发送内容不可为空！");
		return false;
	}
	if(htmlString > 1000){
		showAlert("发送内容请控制在1000字符之内！");
		return false;
	}
	var url = "wdznx.do?method=savexjHf";
	ajaxSubFormWithFun("ZnxglForm", url, function(data) {
		 if(data["message"]=="信件回复成功！"){
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

//查看页面回复按钮 
function zjhf(){
	var fsrxm = jQuery("#fsrxm").val();
	var jsrbh = jQuery("#jsrbh").val();
	var xjbh = jQuery("#xjbh").val();
	//先弹窗，在执行关闭
	showDialog("站内信回复", 770, 450, "wdznx.do?method=xjHf&xjbh="
			+ xjbh+"&jsrbh="+jsrbh+"&fsrxm="+
			fsrxm+"&jsrydbj=1");
	
	iFClose();
}
