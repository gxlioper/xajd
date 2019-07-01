//查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//标题查看链接，未查看信件标题的字体加黑
function btLink(cellValue, rowObject) {
	if(rowObject["jsrydbj"] == '0'){
		return "<a href='javascript:void(0);' style='font-weight:bold;' class='name' onclick='btView(\""
		+ rowObject["xjbh"] + "\",\"" + rowObject["jsrbh"] + "\",\"" + "wd" + "\");'>" + cellValue
		+ "</a>";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='btView(\""
		+ rowObject["xjbh"] + "\",\"" + rowObject["jsrbh"] + "\",\"" + "yd" + "\");'>" + cellValue
		+ "</a>";
	}
	
}

//查看函数
function btView(xjbh,jsrbh,type){
	showDialog("站内信查看", 770, 450, "znxgl.do?method=glyxjCk&xjbh="
			+ xjbh + "&jsrbh=" + jsrbh+"&type="+type);
}

//信件删除
function znxsc(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} 
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("znxgl.do?method=delScSjxjl",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

//站内信分配页面
function znxfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要分配的记录！");
		return false;
	} 
	showDialog("站内信分配", 770, 450, "znxgl.do?method=XjFp&xjbh="
			+ rows[0]['xjbh'] );
	
}

//站内信分配页面保存
function saveXjForm(){
	var ids = "jsrxm"+"-"+"xjzt";
	if(checkNotNull(ids) == false){
		showAlert("请将带"+"<font color='red'>*</font>"+"的内容填写完整！");
		return false;
	}
	var url = "znxgl.do?method=saveXjFp";
	ajaxSubFormWithFun("ZnxglForm", url, function(data) {
		 if(data["message"]=="信件分配成功！"){
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

//站内信回复
function znxhf(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要回复的记录！");
		return false;
	} 
	showDialog("站内信回复", 770, 450, "znxgl.do?method=xjHf&xjbh="
			+ rows[0]['xjbh']+"&jsrydbj="+rows[0]['jsrydbj'] );
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
	var url = "znxgl.do?method=savexjHf";
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
	showDialog("站内信回复", 770, 450, "znxgl.do?method=xjHf&xjbh="
			+ xjbh+"&jsrydbj=1" );
	iFClose();
}
