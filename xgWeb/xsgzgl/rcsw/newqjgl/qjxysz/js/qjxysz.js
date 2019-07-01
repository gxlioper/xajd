//保存协议内容
function save_xyszData(){
	editor.sync();
	//富文本编辑器内容是否为空调用html()方法获得内容
	var html=editor.html();
	if(html==null||html==""){
		showAlert("请将带"+"<font color='red'>*</font>"+"的内容填写完整！");
		return false;
	}
	var url = "qjxysz.do?method=save_xyszData";
	ajaxSubFormWithFun("QjXySzForm", url, function(data){
		
		    showAlertDivLayer(data["message"]);
	});
}

