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
	  showAlert("该学生不存在该学年学期的资格库内或在该学年学期资格库中存在多条数据！");
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