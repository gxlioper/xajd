//����ѧ����Ϣ
function getStuInfo(){
	jQuery.ajaxSetup({async:false});
	var parameter = {};
	parameter["xh"]=escape(jQuery("#xh").val());
	var url="xsxx_xqbdzcgl.do?method=dtXqbdzc";
	var message="";
	jQuery.getJSON(url,parameter,
			function(data){
				if(data!=null){
					jQuery("#xm").html(data.xm);
					jQuery("#nj").html(data.nj);
					jQuery("#xymc").html(data.xymc);
					jQuery("#zymc").html(data.zymc);
					jQuery("#bjmc").html(data.bjmc);
					jQuery("#zzmm").html(data.ldmc);
					jQuery("#mz").html(data.qsh);
					jQuery("#yhmc").html(data.cwh);
					jQuery("#yhkh").html(data.qsdh);
					changeTqs();
				}
			}
		);
	jQuery.ajaxSetup({async:true});
}

//����ע��
//zczt ע��״̬ 1 ע�� -1 δע��
function saveForm(zczt,bdZc){
	var api = frameElement.api;
	W = api.opener;
//	else if(zczt == '0'){   ����Ϊʲô��д����Ҳ�֪������ʱɾ��
//		message="��ѧ��"+bdZc+"�ѳ���,��ȷ��!";
//		showAlert(message);
//		return false;
//	}
	
	
	var xh = jQuery("#xh").val();
	var xn = jQuery("input[name='searchXn']").val();
	var xq = jQuery("input[name='searchXq']").val();
	var zcsj = jQuery("#zcsj").val();
	if("" == xh){
		showAlert("ѧ�Ų���Ϊ��");
		return false;
	}
	if("" == zcsj){
		showAlert("ע��ʱ�䲻��Ϊ�գ�");
		return false;
	}
	
	 var url = "xsxx_xqbdzcgl.do?method=doDtZc";
     
	 ajaxSubFormWithFun("xqbdzcForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  showAlert(bdZc+"�ɹ���");
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
        			 W.jQuery("#dataTable").reloadGrid();
        			 iFClose();
        			}
      		  }});
    	  }
    	  
      });
}


//����ע��
function dgzc(xh,bdZc,zczt){
	if("false"==jQuery("#zczt").val()){
		message = "��ǰ"+bdZc+"�ѹرգ�����ϵ����Ա��";
		showAlertDivLayer(message);
		return false;
	}
	if(zczt == '1' ){
		message="��ѧ���Ѿ�"+bdZc+",��ȷ��!";
		showAlert(message);
		return false;
	}
	var url = "xsxx_xqbdzcgl.do?method=dtXqbdzc&xh="+xh;
	var title = "ѧ��"+bdZc;
	showDialog(title,800,400,url);
}



//����ע��
function plzc(bdZc){
	if("false"==jQuery("#zckg").val()){
		message = "��ǰ"+bdZc+"�ѹرգ�����ϵ����Ա��";
		showAlertDivLayer(message);
		return false;
	}
	var xn2 = jQuery("#xn").val();
	var xq2 = jQuery("#xq").val();
	var search_xn = jQuery("a[name='a_name_xn']").attr("id").replace("a_id_","");
	var search_xq = jQuery("a[name='a_name_xq']").attr("id").replace("a_id_","");
	if(xn2 != search_xn || xq2 != search_xq){
		showAlertDivLayer("�ǵ�ǰѧ��ѧ�����ݲ��ܽ��в�����" );
		return false;
	}
	var rows = jQuery('#dataTable').getSeletRow();
	if(rows.length == 1){
		xh = rows[0].xh;
		zczt = rows[0].zczt;
		return dgzc(xh,bdZc,zczt);
	}
	if (rows.length == 0){
		if(jQuery("#dataTable").getRowCount() == '0'){
				message = "û��ѧ����"+bdZc+"�������²�ѯ��";
				showAlertDivLayer(message);
				return ;
			}
		var url = "xsxx_xqbdzcgl.do?method=plXqbdzc&type=zc_all&path=xsxx_xqbdzc.do";
		var map = getSuperSearch();
//		//�߼���ѯ
//		url +="&searchTj=";
//		url +=map["searchTj"];
//		url +="&searchTjz=";
//		url +=map["searchTjz"];
//		url +="&mhcx_lx=";
//		url +=map["mhcx_lx"];
//		url +="&searchLx=";
//		url +=map["searchLx"];
//
//		//ģ����ѯ
//		url +="&input_mhcx=";
//		url +=map["input_mhcx"];
//		url +="&mhcx_lx=";
//		url +=map["mhcx_lx"];
//		
//		showDialog("����"+bdZc,500,280,url);
		map["zcsj"] = jQuery('#zcsj').val();
		jQuery.post(url,map,
		function(data){
			url ="xsxx_xqbdzcgl.do?method=plXqbdzc&path=xsxx_xqbdzc.do&rownum_w=" + data["rownum_w"];
			url +="&rownum_x=0" +"+&rownum_wx="+ data["rownum_w"];
			showDialog("����"+bdZc,500,280,url);
		},'json');
		
	}else{
		
		var er = true;
		var rownum_x = 0;
		var rownum_w = 0;
		for(var i =0 ;i < rows.length ; i++){
			var zt = rows[i]['zczt'];
			if(zt == '1'){
				er	=  false;
				break;
			}if(zt == '0'){
				rownum_x++;
			}else{
				rownum_w++;
			}
		}
		if(!er){
			message = "ѡ�еļ�¼������"+bdZc+"����ȷ�ϣ�";
			showAlertDivLayer(message);
			return false;
		}
		
		var rownum_wx = rownum_x + rownum_w;
		var url ="xsxx_xqbdzcgl.do?method=plXqbdzc&type=zc_select&rownum_w=" + rownum_w +"&rownum_x="+ rownum_x +"&rownum_wx="+ rownum_wx;
		
		showDialog("����"+bdZc,500,280,url);
	}
	
}
function wbdyywh(bdZc){
	
	var xn2 = jQuery("#xn").val();
	var xq2 = jQuery("#xq").val();
	var search_xn = jQuery("a[name='a_name_xn']").attr("id").replace("a_id_","");
	var search_xq = jQuery("a[name='a_name_xq']").attr("id").replace("a_id_","");
	if(xn2 != search_xn || xq2 != search_xq){
		showAlertDivLayer("�ǵ�ǰѧ��ѧ�����ݲ��ܽ��в�����" );
		return false;
	}
	var rows = jQuery('#dataTable').getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ������ѧ����");
		return false;
	}else{
		var er = true;
		for(var i =0 ;i < rows.length ; i++){
			var zt = rows[i]['zczt'];
			if(zt == '1'){
				er	=  false;
				break;
			}
		}
		if(!er){
			message = "ѡ�еļ�¼������"+bdZc+"��ѧ������ȷ�ϣ�";
			showAlertDivLayer(message);
			return false;
		}
		var url = "xsxx_xqbdzcgl.do?method=wbdyywh";
		var title = "δ"+bdZc+"ԭ��ά��";
		showDialog(title,550,250,url);
		
	}
}

function saveWbdyy(wbdyy,wbdlbdm,yjbdsj){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = new Array();
	jQuery.each(rows,function(i,row){
		ids.push(row["xh"]);
	});
	jQuery.post(
		"xsxx_xqbdzcgl.do?method=saveWbdyy",
		{plIds:ids.join(","),wbdyy:wbdyy,wbdlbdm:wbdlbdm,yjbdsj:yjbdsj},
		function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	)
}

//����ע��
function plcx(bdZc){
	var xn2 = jQuery("#xn").val();
	var xq2 = jQuery("#xq").val();
	var search_xn = jQuery("a[name='a_name_xn']").attr("id").replace("a_id_","");
	var search_xq = jQuery("a[name='a_name_xq']").attr("id").replace("a_id_","");
	if(xn2 != search_xn || xq2 != search_xq){
		showAlertDivLayer("�ǵ�ǰѧ��ѧ�����ݲ��ܽ��в�����" );
		return false;
	}
	var rows = jQuery('#dataTable').getSeletRow();
	
	/*if(rows.length == 0){
		showAlertDivLayer("������ѡ��һ����Ҫ�����ļ�¼��");
		return false;
	}*/
	
	if (rows.length == 0){
		if(jQuery("#dataTable").getRowCount() == '0'){
				showAlertDivLayer("û��ѧ���ɳ����������²�ѯ��");
				return ;
			}
		var url = "xsxx_xqbdzcgl.do?method=cxXqbdzc&type=cx_all&path=xsxx_xqbdzc.do";
		var map = getSuperSearch();
//		//�߼���ѯ
//		url +="&searchTj=";
//		url +=map["searchTj"];
//		url +="&searchTjz=";
//		url +=map["searchTjz"];
//		url +="&mhcx_lx=";
//		url +=map["mhcx_lx"];
//		url +="&searchLx=";
//		url +=map["searchLx"];
//
//		//ģ����ѯ
//		url +="&input_mhcx=";
//		url +=map["input_mhcx"];
//		url +="&mhcx_lx=";
//		url +=map["mhcx_lx"];
//		
//		showDialog("��������",400,180,url);
		map["zcsj"] = jQuery('#zcsj').val();
		jQuery.post(url,map,
			function(data){
				url ="xsxx_xqbdzcgl.do?method=cxXqbdzc&path=xsxx_xqbdzc.do&rownum_t=" + data["rownum_t"];
				url +="&rownum_y="+ data["rownum_y"] +"+&rownum_w="+ data["rownum_w"]+"+&rownum_x="+ data["rownum_x"];
				showDialog("��������",400,180,url);
			},'json');
		
	}else{
		
		var er = true;
		for(var i =0 ;i < rows.length ; i++){
			var zt = rows[i]['zczt'];
			if(zt != '1'){
				er	=  false;
				break;
			}
		}
		if(!er){
			message = "ѡ�еļ�¼����δ"+bdZc+"����δ����ѧ������ȷ�ϣ�";
			showAlertDivLayer(message);
			return false;
		}
		var url = "xsxx_xqbdzcgl.do?method=cxXqbdzc&type=cx_select&rownum_t=" + rows.length + "&rownum_y="+ rows.length +"+&rownum_w=0" +"+&rownum_x=0";
		var title = "��������";
		showDialog(title,400,180,url);
	}

}

//�鿴
function ckzc(bdZc){
	
	var rows = jQuery('#dataTable').getSeletRow();
	
	var xh = '';
	var xn = jQuery("input[name='searchXn']").val();
	var xq = jQuery("input[name='searchXq']").val();
	
	if(rows.length !=1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
		return false;
	}
	xh = rows[0].xh;
	var url = "xsxx_xqbdzcgl.do?method=ckXqbdzc&xh=" + xh + "&xn=" + xn + "&xq=" + xq;
	var title = "ѧ��"+bdZc;
	showDialog(title,800,400,url);
}



//����
function exportConfig(){
	var DCCLBH='xsxx_xqbdzcgl.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='xsxx_xqbdzcgl.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxx_xqbdzcgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�߼���ѯ
function searchRs(){
	
	var search_xq = jQuery("a[name='a_name_xq']");
	
	var search_xn = jQuery("a[name='a_name_xn']");
	
	if(search_xq.length != 1 || search_xn.length != 1){
		showAlertDivLayer("��ѡ��һ��ѧ���һ��ѧ�ڣ�",{},{});
		return false;
	}
	
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
