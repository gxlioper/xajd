function checkXsInZgk(){
	var url = "dyxj_dyzgk.do?method=checkXsIsInZgk";
	var paras = {xh:jQuery("#xh").val(),xn:jQuery("#xn").val(),xq:jQuery("#xq").val()};
	var rs = null;
	jQuery.ajax({
	type:'post',
	url:url,
	dataType:'json',
	contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	data:paras,
	async: false,
	success:function(result){
		rs = result;
	}
  });
  if(!(rs["message"] == "true")){
	  showAlert("��ѧ�������ڸ�ѧ��ѧ�ڵ��ʸ���ڻ��ڸ�ѧ��ѧ���ʸ���д��ڶ������ݣ�");
	  jQuery("#btncg").hide();
	  jQuery("#btntj").hide();
	  jQuery("#btntj").hide();
	  return false;
  }else if(rs["message"] == "true"){
	  jQuery("#btncg").show();
	  jQuery("#btntj").show();
	  jQuery("#btntj").show();
  }else{
	 return false;
  }
  return true;
	
}