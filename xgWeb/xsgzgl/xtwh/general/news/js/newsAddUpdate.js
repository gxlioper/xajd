var toWhoTemp = ""; // ������һ�εĽ��ն���
//�򿪸���
function open(index){
	//console.log("open(index):"+index + " " + jQuery(".selectTextClass").length);
	jQuery(".selectDivClass").hide();
	jQuery("#selectDiv_"+index).show();
	
	jQuery(".selectTextClass").each(function(){
		jQuery(this).removeClass("bor1");
	});
	jQuery("#selectText_"+index).addClass("bor1");
}
//�رո���
function closeAll(){
	jQuery(".selectDivClass").hide();
	jQuery(".selectTextClass").each(function(){
		jQuery(this).removeClass("bor1");
	});
}
//�򿪡��رո���
function selectTextClick(index){
	var selectDiv = "#selectDiv_"+index;
	if(jQuery(selectDiv).is(":hidden")){
		open(index);
	}else{
		closeAll();
	}
}
//���ý��ն�������
function autoSetText(toWho, index){
	var yxtj_dl=jQuery("#yxtj_dl").html();
	yxtj_dl=jQuery.trim(yxtj_dl);
	if(yxtj_dl!=""){
		jQuery("#sztj").show();
	}else{
		jQuery("#sztj").hide();
	}
	closeAll();
	jQuery("#" + toWho).attr("checked","checked");
	// ====== ���ý��ն������� begin ==========
	var select=jQuery("input[name='toWho']:checked");
	var text=jQuery(select).parent().text();
	jQuery(".selectTextClass").each(function(){
		var self = jQuery(this);
		self.text(self.attr("pName") + "�ɼ�");
	});
	//console.log("autoSetText toWho:" + toWho + " index:"+index + " text:"+text);
	jQuery("#selectText_" + index).html(text);
	// ====== ���ý��ն������� end ==========
	jQuery("input[name='selectRadio']").eq(index).attr("checked","checked");
}
// ���㣨ȫУ��
function selectDivAllClick(toWho, index){
	//console.log("selectDivAllClick toWho:" + toWho + " index:"+index);
	cleanTj();
	autoSetText(toWho, index);
}
// ���㣨ָ����
function selectDivSomeClick(toWho, index){
	if(toWhoTemp != toWho){ // �л����ն������
		cleanTj();
		toWhoTemp = toWho;
	}
	var searchTj=jQuery("#searchTj").val();
	var searchTjz=jQuery("#searchTjz").val();
	var mhcx_lx=jQuery("#mhcx_lx").val();
	var searchLx=jQuery("#searchLx").val();
	var search={searchTj:searchTj,searchTjz:searchTjz,mhcx_lx:mhcx_lx,searchLx:searchLx};
	var searchUrl="searchTj="+searchTj+"&searchTjz="+searchTjz+"&mhcx_lx="+mhcx_lx+"&searchLx="+searchLx;
	var url = "xtwh_news.do?method=selectQx&toWho="+toWho+"&toWhoIndex="+index+"&"+searchUrl;;
	closeAll();
	showDialog("ѡ�����", 800, 500, url, search);
}
// �������
function cleanTj(){
	jQuery("#sztj").hide();
	jQuery("#yxtj_dl").html("");
	jQuery("#searchTj").val("");
	jQuery("#searchTjz").val("");
	jQuery("#mhcx_lx").val("");
	jQuery("#searchLx").val("");
}
// ���ն���radio���
function selectRadioClick(index){
	index = index * 2;
	jQuery("input[name='toWho']")[index].click();
}


