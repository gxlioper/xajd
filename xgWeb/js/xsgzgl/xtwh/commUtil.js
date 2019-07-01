//ѡ��ѧ��
function searchXsxx(path,width,height,source_table,title,column,primary_key){
	
	//PATH
	if(path==""){
		path = "chooseStuInfo.jsp";
	}
	
	//��
	if(width==""){
		width = 800;
	}
	
	//��
	if(height==""){
		height = 600;
	}
	
	//���ݱ�
	if(source_table==""){
		source_table = "view_xsjbxx";
	}
	
	//title
	if(title==""){
		title = "ѧ����Ϣ";
	}
	
	//��
	if(column==""){
		var array_column = ["pk","xh","xm","nj","xymc","zymc","bjmc"]; 
		column = array_column.join("luojw");
	}
	
	//����
	if(primary_key==""){
		primary_key = "xh";
	}
	
	var url = "commUtil.do?method=xsxxSearch";
		url+= "&str_path="+path;
		url+= "&str_source_table="+source_table;
		url+= "&str_title="+title;
		url+= "&str_column="+column;
		url+= "&str_primary_key="+primary_key;
		
	showTopWin(url,width,height);
}

//����ѧ����Ϣ��ͨ�á�
function setXsxxComm(){
	var xh = "";
	var source_table = "view_xsxxb";
	
	if($("source_table")){
		source_table = jQuery("#source_table").val();
	}
	
	if($("text_xh")){
		xh = jQuery("#text_xh").val();
	
		jQuery.ajaxSetup({async:false});

		//·��
		var url = "commUtil.do?method=getXsxx";
			url+= "&str_xh="+xh;
			url+= "&str_source_table="+source_table;
	
		
	 	jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			async: false,
			success:function(result){
		 		for (var i = 0 ; i < result.length; i++){
					
					var column = result[i].column;
					var value = result[i].value;
				
					if($("text_"+column)){
						jQuery("#text_"+column).val(value);
					}else if($("textarea_"+column)){
						jQuery("#textarea_"+column).val(value);
					}else if($("select_"+column)){
						jQuery("#select_"+column).val(value);
					}else if($("class_"+column)){
						jQuery("#class_"+column).val(value);
					}else if($("calendar_"+column)){
						jQuery("#calendar_"+column).val(value);
					}
					
					var zd = column.substring(column.length-2,column.length);
					
					if(zd == "mc"){
						if($("display_"+column.substring(0,column.length-2)+"dm")){
							jQuery("#display_"+column.substring(0,column.length-2)+"dm").html(value);
						}else if($("display_"+column.substring(0,column.length-2))){
							jQuery("#display_"+column.substring(0,column.length-2)).html(value);
						}else if($("display_"+column.substring(0,column.length-2)+"mc")){
							jQuery("#display_"+column.substring(0,column.length-2)+"mc").html(value);
						}else if(column == "bjmc"){
							jQuery("#class_show_bjdm").val(value);
						}else if($("area_"+column)){
							alert(column);
						}
					}else{
						jQuery("#display_"+column).html(value);
					}
				}
			}
		});
	 	
		jQuery.ajaxSetup({async:true});
	}
}

//����ѧ����Ϣ�����⡿
function setXsxxSpecial(){

	var xh = "";
	var source_table = "";
	
	if($("hidden_detail_view")){
		source_table = jQuery("#hidden_detail_view").val();
	}
	
	if($("text_xh") && source_table!=""){
		xh = jQuery("#text_xh").val();
	
		jQuery.ajaxSetup({async:false});

		//·��
		var url = "commUtil.do?method=getXsxx";
			url+= "&str_xh="+xh;
			url+= "&str_source_table="+source_table;
	
		
	 	jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			async: false,
			success:function(result){
		 		for (var i = 0 ; i < result.length; i++){
					
					var column = result[i].column;
					var value = result[i].value;
					var zd = column.substring(column.length-2,column.length);
					
					if(zd == "mc"){
						if($("display_"+column.substring(0,column.length-2)+"dm")){
							jQuery("#display_"+column.substring(0,column.length-2)+"dm").html(value);
						}else if($("display_"+column.substring(0,column.length-2))){
							jQuery("#display_"+column.substring(0,column.length-2)).html(value);
						}else if($("display_"+column.substring(0,column.length-2)+"mc")){
							jQuery("#display_"+column.substring(0,column.length-2)+"mc").html(value);
						}else if(column == "bjmc"){
							jQuery("#class_show_bjdm").val(value);
						}
					}else{
						jQuery("#display_"+column).html(value);
					}	
				}
			}
		});
	 	
		jQuery.ajaxSetup({async:true});
	}
}

//��ʾѧ����Ϣ����ϸ��
function showXsxxDetail(xh){
	
	var url = "general_xsxx.do?method=xsxxDetailed";
		url+= "&xh="+xh;
		url+= "&doType=detail"
	var width=840;
	var height = 600;
	var left =(screen.width/2) - width/2;
	var top = (screen.height/2) - height/2;

	showTopWin(url,width,height);
}


