/**
 * ��ʼ��
 */
jQuery(function(){
	searchRs();
});

/**
 * �б�չʾ
 * @return
 */
function getGridSettiong(){
	var gridSetting = {
		caption:"��ѧ���������һ����",
		pager:"pager",
		url:"xpjpy_jxjfp.do?method=getJxjfpListData",
		params:getSuperSearch(),
		multiselect:false
	};

	var colList=[
	   {label:'��������',name:'bmmc', index: 'bmmc',width:'15%'}
	];				
		
	var pjxm = jQuery("font[name=pjxm]");
	jQuery.each(pjxm,function(i,n){
		var pjxmJson = {
				label:"&nbsp;&nbsp;"+jQuery(n).attr("xmmc")+"<br/>(������/��������)",
				name:"jx"+i,
				index:"jx"+i
		};
		colList.push(pjxmJson);
	});

	var jjeList = {label:'�����',name:'jje', index: 'jje',width:'15%',key:true};
	var bmtzjeList = {label:'���ŵ������',name:'bmtzje', index: 'bmtzje',width:'15%',key:true};
	var zrsList = {label:'����ѧ������',name:'zrs', index: 'zrs',width:'15%',key:true};
	colList.push(jjeList);
	colList.push(bmtzjeList);
	colList.push(zrsList);
	
	gridSetting["colList"] = colList;
	
	return gridSetting;				
}

/**
 * ��ѯ
 * @return
 */
function searchRs(){
	var xn_num = document.getElementsByName("a_name_xn").length;
	var notFirst = jQuery("#notFirst").val();
	if("yes"==notFirst&&0==xn_num){
		showAlertDivLayer("����ѡ��һ��ѧ�꣡");
		return false;
	}
	jQuery.ajaxSetup({async:false});
	initPjxm();
	var gridSetting = getGridSettiong();
	jQuery("#dataTable").initGrid(gridSetting);
	jQuery.ajaxSetup({async:true});
}

/*��ʼ����ͳ��������Ŀ*/
function initPjxm(){
	var url="xpjpy_jxjfp.do?method=initPjxm";
	var xn = jQuery("[name=tj_xn][class=selectedValue]").attr("id");
	if(undefined!=xn){
		xn=xn.split("_")[2];
	}
	jQuery.post(url,{"xn":xn},function(data){
		dataObj=data;
		createPjxmDiv();
		},'json');
	
	}

/**
 * ������̬�Ľ�ѧ������(�б�)
 * @return
 */
function createPjxmDiv(){
	var pjDiv=jQuery("#pjxmDiv");
	jQuery("font",pjDiv).remove();
	var pjxmHtml = "";
	for ( var i = 0; i < dataObj.length; i++) {
		var o = dataObj[i];
		pjxmHtml+="<font style='display:none;' xmdm="+o.xmdm+" xmmc="+o.xmmc+" name='pjxm'></font>";
	}
	pjDiv.html(pjxmHtml);
	jQuery("#notFirst").val("yes")
}

/**
 * ��ѧ�����һ�������ݵ���
 * @return
 */
function jxjfpExport(){
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	if(xn_num != "1"){
		showAlertDivLayer("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
		return false;
	}
	
	setSearchTj();//���ø߼���ѯ����
	var url = "xpjpy_jxjfp.do?method=jxjfpExport"
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ���Ż��ܵ���(��̨��Ҫִ��һ���洢����)
 * @return
 */
function ffhzExport(){
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	if(xn_num != "1"){
		showAlertDivLayer("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
		return false;
	}
	/*���ø߼���ѯ����*/
	setSearchTj();//
	var url = "xpjpy_jxjfp.do?method=ffhzExport"
	/*���ø߼���ѯ����*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//���Ż��ܵ���
function gjjxjhzExport(){
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	if(xn_num != "1"){
		showAlertDivLayer("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
		return false;
	}
	setSearchTj();//���ø߼���ѯ����
	var url = "xpjpy_jxjfp.do?method=gjjxjhzExport"
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}