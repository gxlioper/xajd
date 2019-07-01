
var gridSetting = {
	caption:"��������б�",
	pager:"pager",
	url:"xszz_zzxmjg.do?method=zzxmhzList&type=query",
	colList:[
	   {label:'ѧ��',name:'xn', index: 'xn',width:'6%'},
	   {label:'xq',name:'xq', index: 'xq',width:'7%',hidden:true},
	   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'7%'},
	   {label:'��Ŀ���',name:'lbmc', index: 'lbmc',width:'6%'},
	   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'13%'},
	   {label:'lbdm',name:'lbdm', index: 'lbdm',width:'13%',hidden:true},
	   {label:'������',name:'hjrs', index: 'hjrs',width:'19%',formatter:rsLink}
	],
	sortname: "xn,xq",
 	sortorder: "desc"
	};


jQuery(function(){
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function rsLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='zzxmView(\""+rowObject["xn"]+"\",\""+rowObject["xq"]+"\",\""+rowObject["lbdm"]+"\",\""+rowObject["xmmc"]+"\");'>"+rowObject["hjrs"]+"</a>";
}

function zzxmView(xn,xq,lbdm,xmmc){
	var url = 'xszz_zzxmjg.do?method=zzxmhzView&xn='+xn+'&xq='+xq+'&lbdm='+lbdm+'&xmmc='+xmmc;
	document.forms[0].action=url;
	document.forms[0].submit();
}


//��ӡexcel
function getExcel(){
	var rows = jQuery("#dataTable").getSeletRow();
	 if (rows.length ==0){
		showAlertDivLayer("������ѡ��һ����¼��");
		return false;
	 } else if (rows.length > 0){
		 if(rows.length>1){
	    	 for(var i=0;i<rows.length-1;i++){
	    		 if(rows[i+1]["xn"]!=rows[i]["xn"]&&rows[i+1]["xq"]!=rows[i]["xq"]){
	    			 showAlertDivLayer("��ѡ����ͬѧ��ѧ�ڵ�������Ŀ��");
	    			 return false;
	    		 }
	    		 if(rows[i+1]["xmmc"]!=rows[i]["xmmc"]){
	    			 showAlertDivLayer("��ѡ����ͬ���Ƶ�������Ŀ��");
	    			 return false;
	    		 }
	    	 }
		 }
		var rows=encodeURIComponent(encodeURIComponent(JSON.stringify(rows)));
    	var url="xszz_zzxmjg.do?method=downloadMultiExcel";
    	url += "&rows="+rows;
 		window.open(url);
     }
}

//��ӡexcel��ѧԺ��
function getExcelByXy(){
    var rows = jQuery("#dataTable").getSeletRow();
    var xyArr = getClickXy();
    if (rows.length ==0){
        showAlertDivLayer("������ѡ��һ����¼��");
        return false;
    }
    if (xyArr.length ==0){
        showAlertDivLayer("������ѡ��һ��ѧԺ��");
        return false;
    }
    if (rows.length > 0){
        if(rows.length>1){
            for(var i=0;i<rows.length-1;i++){
                if(rows[i+1]["xn"]!=rows[i]["xn"]&&rows[i+1]["xq"]!=rows[i]["xq"]){
                    showAlertDivLayer("��ѡ����ͬѧ��ѧ�ڵ�������Ŀ��");
                    return false;
                }
                if(rows[i+1]["xmmc"]!=rows[i]["xmmc"]){
                    showAlertDivLayer("��ѡ����ͬ���Ƶ�������Ŀ��");
                    return false;
                }
            }
        }

        var rows=encodeURIComponent(encodeURIComponent(JSON.stringify(rows)));
        var url="xszz_zzxmjg.do?method=downloadMultiExcelByXy";
        url += "&rows="+rows+"&xydms="+xyArr.toString();
        window.open(url);
    }
}

//ɽ��������ҽְҵѧԺ�������ѧ����ܱ�
function getshzxjHzexcel(){
	var xmlb = '';
	var xn = '';
	var xq = ''
	jQuery("a[name='tj_zzxmlb'].selectedValue").each(function(){
		xmlb = xmlb+jQuery(this).text()+',';
	})
	jQuery("a[name='tj_xn'].selectedValue").each(function(){
		xn = xn+jQuery(this).text()+',';
	})
	
	jQuery("a[name='tj_xq'].selectedValue").each(function(){
		xq = xq+jQuery(this).text()+',';
	})
	var value = xmlb.split(",");
	if(value.length != 2  ){
		showAlertDivLayer("��ѡ��һ�������ȷ�ϣ�");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	var url="xszz_zzxmjg.do?method=getSdxm_shzxjhzexcel&value="+xmlb+"&xn="+xn+"&xq="+xq;
	window.open(url);
}

/**
 * �Ϻ�����ѧԺ������Ŀ���ܱ�
 * ��������ѡ��һ����¼������������������ֱ�ӷ��ص�����ʾ��Ϣ
 * ���ù���input�ķ�ʽ,���ñ��ύ�������Է�����͵��ߴ���Ч��
 * @return
 */
function getZzxmHz_10277(){
	var rows = jQuery("#dataTable").getSeletRow();
	//������չ����
	jQuery("#shtyhzdcdiv").empty();
	if(rows.length == 0){
		showAlertDivLayer("�����ٹ�ѡһ����¼��");
		return false;
	}
	for ( var i = 0; i < rows.length; i++) {
		var row = rows[i];
		var xn = (row['xn'] == 'null' || row['xn'] == null) ? "":row['xn'];
		var xq = (row['xq'] == 'null' || row['xq'] == null) ? "":row['xq'];
		var lbdm = (row['lbdm'] == 'null' || row['lbdm'] == null) ? "":row['lbdm'];
		var paras = xn+xq+row['xmmc']+lbdm;
		var html = "<input value='"+paras+"' name='params' type='hidden'/>";
		jQuery("#shtyhzdcdiv").append(html);
	}
	var url = "xszz_zzxmjg.do?method=exportShty_hzdc";
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
