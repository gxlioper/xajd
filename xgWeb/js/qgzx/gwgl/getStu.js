
function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


function searchRs(){
	var url = "qgzx_gwgl_ajax.do?method=getStu&pkValue="+jQuery("#pkValue").val();
	//�ڹ���ѧ���Ի�
	var xn = jQuery("#xn").val();
	url = url+"&xn="+xn+"&sfxyzgsc="+jQuery("#sfxyzgsc").val();
	
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}

//��ӱ���ѧ��
function zjBcStu(){
	var api = frameElement.api,W = api.get('parentDialog');
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		W.document.getElementById("xhs").value=str;
		api.close();
		W.document.getElementById("btn_getXsxx").onclick();
	}else{
		alertInfo("�빴ѡ��Ҫ��ӵ����ݣ�",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}