//查看规则信息
 function ckgzxx() {
 	var id=jQuery("#pzgzid").val();
 	var url = "fbglgzpztj.do?method=showView&pzgzid=" + id;
 	var cktitle ="规则信息";
 	showDialog(cktitle, 800, 500, url);
 }
 /**
  * 预览
  * @return
  */
function preview(){
	var pzgzid=jQuery("#pzgzid").val();
	jQuery.post("fbglgzpztj.do?method=getGzlx", {
		pzgzid:pzgzid
	}, function(data) {
		var html="";
		for(var i=0;i<data.length;i++){
			var obj=data[i];
			html+="<font color='blue'>"+obj.tjgzmc+":</font>&nbsp;";
			var gzstr=gzylHtml(pzgzid,obj.tjgzid);
			if(""!=gzstr){
				html+=gzylHtml(pzgzid,obj.tjgzid);
			}
		}
		jQuery("#tjpz").html(html);
	}, 'json');
}
/**
 * 加载预览信息
 * @param pzgzid 配置规则id
 * @param tjgzid 提交规则id
 * @return
 */
function loadPreview(pzgzid,tjgzid){
	var dataxx;
 	jQuery.ajax({
		url:"fbglgzpztj.do?method=getPreview",
		data:{pzgzid:pzgzid,tjgzid:tjgzid},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			dataxx=data;
		}
	});	
 	return dataxx;
}
/**
 * 生成预览html
 * @param pzgzid 配置规则id
 * @param tjgzid 提交规则id
 * @return
 */
function gzylHtml(pzgzid,tjgzid){
	var html="";
	html+="<font color='red'>";
	var dataxx=loadPreview(pzgzid,tjgzid);
	if(dataxx==""){
		return dataxx;
	}
	for(var j=0;j<dataxx.length;j++){
		html+=dataxx[j];
		html+="&nbsp;";
	}
	html+="</font>&nbsp;&nbsp;";
	return html;
}
/**
 * 是否有对应gzdm规则信息
 * @param gzdm
 * @return
 */
function isHaveGzxx(gzdm){
	var isok=false;
 	jQuery.ajax({
		url:"fbglgzpztj.do?method=getGzxx",
		data:{gzdm:gzdm},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data&&data!=""){
				isok=true;
			}
		}
	});	
 	return isok;
}
//增加、修改页面加载规则预览
function initGzView(obj){
	var spanName=jQuery(obj).parents(".gztjTbody").find("span").attr("name");
	if("BXHGZ_XHPX"==spanName){
		return;
	}
	var html="";
	var mrylz="";
	jQuery.each(jQuery(obj).parents(".gztjTbody").find("tr:visible").find("input:[name=mrylz]"),function(i,n){
		var mrylz=jQuery(n).val();
		if("null"==mrylz||null==mrylz){
			mrylz="";
		}
		html+=mrylz+" ";
	});
	jQuery(obj).parents(".formlist").find("font[name=gzyl]").html(html);
	
}