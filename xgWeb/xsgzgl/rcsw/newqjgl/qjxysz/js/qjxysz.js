//����Э������
function save_xyszData(){
	editor.sync();
	//���ı��༭�������Ƿ�Ϊ�յ���html()�����������
	var html=editor.html();
	if(html==null||html==""){
		showAlert("�뽫��"+"<font color='red'>*</font>"+"��������д������");
		return false;
	}
	var url = "qjxysz.do?method=save_xyszData";
	ajaxSubFormWithFun("QjXySzForm", url, function(data){
		
		    showAlertDivLayer(data["message"]);
	});
}

