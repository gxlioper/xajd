//ɾ����
function delRow(){
	var obj = jQuery("[name='chk']:checked").parent().parent();
	if(obj.length == 0){
		showAlert("����ѡ���¼���ٽ���ɾ��������");
		return false;
	}
	jQuery(obj).remove();
	jQuery("[name='chkall']").removeAttr("checked");
	calculateRs();
}

function getxhs(){
	var xhs = "";
	var xhobj = jQuery("[name='xh']");
	jQuery(xhobj).each(function(i){
		xhs +=this.value;
		if(xhobj.length-1 != i){
			xhs +=",";
		}
		
	});
	return xhs;
}

//��ʦ�������ѧ��
function addRowDialog(){
	if(jQuery("#bjdm").val() == ""){
		return showAlert("�༶����Ϊ�գ�");
	}
	var xhs = getxhs();
	var bjdm = jQuery("#bjdm").val();
    var url = "xlzxnew_zbsb.do?method=addStu&xhs="+xhs+"&bjdm="+bjdm;
    var title = "ѧ��ѡ��";
	showDialog(title, 770, 550, url);
}

function selectAll(obj){
	if(obj.checked){
		jQuery("[name='chk']").attr("checked","checked");
	}else{
		jQuery("[name='chk']").removeAttr("checked");
	}
}

