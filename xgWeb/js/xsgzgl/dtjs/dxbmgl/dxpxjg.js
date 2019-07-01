function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
function dcmcLink(cellValue, rowObject) {
	var jgid = rowObject["jgid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + jgid + "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(jgid) {
	var url ="dtjs_dxbmgl_dxpxjgCk.do?&jgid=" + jgid;
	var title = "��ѵ�����Ϣ";
	showDialog(title, 800, 450, url);
}
//����
function add() {
		var url ="dtjs_dxbmgl_dxpxjgZj.do";
		var title = "������ѵ�����Ϣ";
		showDialog(title, 800, 460, url);
		jQuery("#dataTable").reloadGrid();
}
function formatStr(str){
	if(str==""||null==str){
		return "-1";
	}
	return str;
}
function save(checkId){
	if(!check(checkId)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	lock();
 	jQuery("#form").ajaxSubmit({
		url:'dtjs_dxbmgl_dxpxjgZj.do',
		type:"post",
		dataType:"json",
		data:{'type':'save'},
		success:function(data){
			if(data||data=='true'){
				showAlertDivLayer("����ɹ�!");
	 			var api = frameElement.api;
	 			api.opener.reload();
				api.close();
	    	}else{
	    		showAlertDivLayer("����ʧ��!");
		 		unlock();
	    	}
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}
function saveXg(){
	lock();
 	jQuery("#form").ajaxSubmit({
		url:'dtjs_dxbmgl_dxpxjgXg.do',
		type:"post",
		dataType:"json",
		data:{'type':'save'},
		success:function(data){
			if(data||data=='true'){
				showAlertDivLayer("����ɹ�!");
	 			var api = frameElement.api;
	 			api.opener.reload();
				api.close();
	    	}else{
	    		showAlertDivLayer("����ʧ��!");
		 		unlock();
	    	}
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}
/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}
function checkother(){
	var is=false;
	jQuery("li.current").each(function(){
		//��������
		is=true;
	});
	return is;
}
function checkNum(){
	var kssj=jQuery("#kssj").val();
	var str=kssj.substring(0,1);//��ȡ��һλ
	if(parseInt(str)<=0&&kssj.length>1){
		return true;
	}
	var jssj=jQuery("#jssj").val();
	str=jssj.substring(0,1);//��ȡ��һλ
	if(parseInt(str)<=0&&jssj.length>1){
		return true;
	}
	return false; 
}
//ɾ��
function del() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var ids='';
		var sjyls=0;
		var gxts=rows.length;
		for(var i=0;i<gxts;i++){
			if(rows[i]["sjly"]=='0'){
				ids+=rows[i]["jgid"];
			}else{
				sjyls++;
			}
		}
		if(sjyls==gxts){//��ѡ������ɾ��
			showAlertDivLayer("��ѡ��Ŀ�о�Ϊ�������Ϣ,����ɾ����");
			return false;
		}else if(sjyls>0&&sjyls<gxts){
			showConfirmDivLayer("��ѡ��Ŀ�а����������Ϣ,ȷ��ֻɾ�����ǽ������Ϣ�ļ�¼��", {
				"okFun" : function() {
				jQuery.post("dtjs_dxbmgl_dxpxjgSc.do", {values : ids}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data+"&nbsp;</font>������";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
			});
		}else{
			showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
				"okFun" : function() {
				jQuery.post("dtjs_dxbmgl_dxpxjgSc.do", {values : ids}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data+"&nbsp;</font>������";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				}
			});
		}
	}
}
//����
function pf(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {		
		var sjly=rows[0]["sjly"]
         if(sjly!="0"){//Ϊ���������
        	 showAlertDivLayer("�������Ϣ,�޷��޸ģ�");
        	 return false;
         }
		showDialog("����",800, 450,'dtjs_dxbmgl_dxpxjgXg.do?jgid='+rows[0]['jgid']);
	}
}
//�Զ��嵼�� ����
function exportConfig() {
	var xxdm=jQuery("#xxdm").val();
	if("13871"==xxdm){
		customExport("dtxxjg_13871.do",exportData);
	}else{
		customExport(action,exportData);
	}
}

// ��������
function exportData(dcclbh) {
	setSearchTj();//���ø߼���ѯ����
	var url = action+"?method=exportData&dcclbh=" + dcclbh;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function edit(obj,jdmc,jddm){
	jQuery(obj).parent().find("span[name='sj']").attr("class","edit");
	var grxj=jQuery(obj).parents("li").find("input[name='grxj']").val();
	var zd5=jQuery(obj).parents("li").find("input[name='zd5']").val();
	var kssj=jQuery(obj).parent().find("span[name='sj']").text();
	var jysj = jQuery(obj).parents("li").find("input[name='zd1']").val();
	var cjlr = jQuery(obj).parents("li").find("input[name='zd2']").val();
	var zd3 = jQuery(obj).parents("li").find("input[name='zd3']").val();
	var zd8 = jQuery(obj).parents("li").find("input[name='zd8']").val();
	var zd9 = jQuery(obj).parents("li").find("input[name='zd9']").val();
	var zd10 = jQuery(obj).parents("li").find("input[name='zd10']").val();
	var url="dtxxjg.do?method=editorView&type=edit&jdmc="+jdmc+"&jddm="+jddm+"&kssj="+kssj+"&grxj="+grxj+"&zd5="+zd5+"&zd1="+jysj+"&zd2="+cjlr
	        +"&zd8="+zd8+"&zd9="+zd9+"&zd10="+zd10+"&zd3="+zd3;
	showDialog("�༭", 600, 320, url);
}
function show(obj,jdmc,jddm){
	jQuery(obj).parent().find("span[name='sj']").attr("class","edit");
	var grxj=jQuery(obj).parents("li").find("input[name='grxj']").val();
	var zd5=jQuery(obj).parents("li").find("input[name='zd5']").val();
	var kssj=jQuery(obj).parent().find("span[name='sj']").text();
	var jysj = jQuery(obj).parents("li").find("input[name='zd1']").val();
	var cjlr = jQuery(obj).parents("li").find("input[name='zd2']").val();
	var zd3 = jQuery(obj).parents("li").find("input[name='zd3']").val();
	var zd8 = jQuery(obj).parents("li").find("input[name='zd8']").val();
	var zd9 = jQuery(obj).parents("li").find("input[name='zd9']").val();
	var zd10 = jQuery(obj).parents("li").find("input[name='zd10']").val();
	var url="dtxxjg.do?method=editorView&type=view&jdmc="+jdmc+"&jddm="+jddm+"&kssj="+kssj+"&grxj="+grxj+"&zd5="+zd5+"&zd1="+jysj+"&zd2="+cjlr
	+"&zd8="+zd8+"&zd9="+zd9+"&zd10="+zd10+"&zd3="+zd3;
	showDialog("�鿴", 600, 350, url);
}
function autoChange(){
	jQuery(".Join_party ul").css("float","right"); 
	jQuery(".Join_party ul .clearall").nextAll("li").css("float","right"); 

	var nowDate=new Date();
	var currentDate=nowDate.getFullYear()+"-"+nowDate.getMonth()+"-"+nowDate.getDate();
	jQuery("span[name='sj']").each(function(){
		var kssj=jQuery(this).text();
		var grxj=jQuery(this).parents("li").find("input[name='grxj']").val();
		var zd5=jQuery(this).parents("li").find("input[name='zd5']").val();
		//������ڶ�Ӧ��Ϣ�������ʾ��ʽ
		if((null!=kssj&&""!=kssj)||(null!=grxj&&grxj!="")){
			jQuery(this).parents("li").attr("class","current");
		}else{
			jQuery(this).parents("li").attr("class","dtxxglnewColorWZDX");
		}
		//���������Դ��ѧ�������򲻿����޸�
		var sjly=jQuery(this).parents("li").find("input[name='sjly']").val();
		if(sjly=="1"){//Ϊѧ������
			var jdmc=jQuery(this).parents("li").find("input[name='jdmc']").val();
			var jddm=jQuery(this).parents("li").find("input[name='jddm']").val();
			jQuery(this).prevAll("a").children("i.i2").attr("class","i1");
			jQuery(this).prevAll("a").removeAttr('onclick').click(function(){
				show(this,jdmc,jddm);
			});
		}
/*		//�����Ƿ���Ա༭
		var ksqkssj=jQuery(this).parents("li").find("input[name='ksqkssj']").val();
		var ksqjssj=jQuery(this).parents("li").find("input[name='ksqjssj']").val();
		//��ǰʱ��С���ڿ����뿪ʼʱ����ߴ��ڿ��������ʱ��
		//�򲻿ɱ༭��ֻ�ɲ鿴
		if(compareDate(currentDate,ksqkssj)==2||compareDate(currentDate,ksqjssj)==1){
			jQuery(this).prevAll("a>li").attr("class","i1");
		}*/
		//û�и���С�����ܲ鿴
		if(grxj==null||grxj==""){
			jQuery(this).prevAll("a").children(".i1").remove();
		}
	});
	//���¸����׶���ʾ״̬
	updateStyle();
}
function updateStyle(){
	var mm = "0"
	jQuery(".Join_party ul li.current").each(function(i){
		var jddm  = jQuery(this).find("input[name='jddm']").val();
		if(jddm>mm){
			mm = jddm;
		}
	});
	jQuery(".Join_party ul li").each(function(){
		var checkJddm=jQuery(this).find("input[name='jddm']").val();
		if(checkJddm<mm){
			
			jQuery(this).attr("class","current");
		}
	});
}
//�����ܷ�
function jszf(){
	var pxqc=jQuery('#pxqc').val();
	if(pxqc!=null&&pxqc!=''){
		var kqcjbfb=jQuery('#kqcjbfb').val()/100;
		var sjcjbfb=jQuery('#sjcjbfb').val()/100;
		var bjcjbfb=jQuery('#bjcjbfb').val()/100;
		var kscjbfb=jQuery('#kscjbfb').val()/100;
		var kqcj=jQuery('#kqcj').val()==''?0:jQuery('#kqcj').val();
		var sjcj=jQuery('#sjcj').val()==''?0:jQuery('#sjcj').val();
		var bjcj=jQuery('#bjcj').val()==''?0:jQuery('#bjcj').val();
		var kscj=jQuery('#kscj').val()==''?0:jQuery('#kscj').val();
		jQuery('#zpcj').val(kqcj*kqcjbfb+sjcj*sjcjbfb+bjcj*bjcjbfb+kscj*kscjbfb);
	}
}