//�鿴������Ϣ
 function ckgzxx() {
 	var id=jQuery("#pzgzid").val();
 	var url = "fbglgzpztj.do?method=showView&pzgzid=" + id;
 	var cktitle ="������Ϣ";
 	showDialog(cktitle, 800, 500, url);
 }
 /**
  * Ԥ��
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
 * ����Ԥ����Ϣ
 * @param pzgzid ���ù���id
 * @param tjgzid �ύ����id
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
 * ����Ԥ��html
 * @param pzgzid ���ù���id
 * @param tjgzid �ύ����id
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
 * �Ƿ��ж�Ӧgzdm������Ϣ
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
//���ӡ��޸�ҳ����ع���Ԥ��
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