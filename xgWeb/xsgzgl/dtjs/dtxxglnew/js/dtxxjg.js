var action="dtxxjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
function dcmcLink(cellValue, rowObject) {
	var dtxxjgid = rowObject["dtxxjgid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + dtxxjgid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(dtxxjgid) {
	var url = action+"?method=showView&dtxxjgid=" + dtxxjgid;
	var title = "����������Ϣ";
	showDialog(title, 800, 450, url);
}
//����
function add() {
		var url =action+"?method=add";
		var title = "���ӵ�����Ϣ";
		showDialog(title, 800, 460, url);
		jQuery("#dataTable").reloadGrid();
}
function formatStr(str){
	if(str==""||null==str){
		return "-1";
	}
	return str;
}
function save(url,checkId,keyid){
	if(!check(checkId)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(!checkother()){
		showAlert("ѧ�����ŷ�չ���̲��ܿգ�");
		return false;
	}
	var jddm="-1";
	var kssj="-1";
	var grxj="-1";
	var zd5="-1";
	var dtxxjgid="-1";
	var zd1 = "-1";
	var zd2 = "-1";
	var zd3 = "-1";
	var zd8 = "-1";
	var zd9 = "-1";
	var zd10 = "-1";
	jQuery(".finish").each(function(){
		//alert(jQuery(this).parents("li").html());
		//��ȡ�����޸Ĺ��Ľ׶δ���
		jddm+=",";
		jddm+=formatStr(jQuery(this).parents("li").find("input[name='jddm']").val());
		//��ȡ�����޸Ĺ��ĸ�����Ϣ
		grxj+=",";
		grxj+=formatStr(jQuery(this).parents("li").find("input[name='grxj']").val());
		//����
		zd5+=",";
		zd5+=formatStr(jQuery(this).parents("li").find("input[name='zd5']").val());
		//��ȡ�����޸Ĺ��Ŀ�ʼʱ��
		kssj+=",";
		kssj+=formatStr(jQuery(this).text());
		//��ȡ���Ž�ҵʱ��
		zd1+=",";
		zd1+=formatStr(jQuery(this).parents("li").find("input[name='zd1']").val());
		//��ȡ���Ž�ҵ�ɼ�
		zd2+=",";
		zd2+=formatStr(jQuery(this).parents("li").find("input[name='zd2']").val());
		
		zd3+=",";
		zd3+=formatStr(jQuery(this).parents("li").find("input[name='zd3']").val());
		
		zd8+=",";
		zd8+=formatStr(jQuery(this).parents("li").find("input[name='zd8']").val());
		
		zd9+=",";
		zd9+=formatStr(jQuery(this).parents("li").find("input[name='zd9']").val());
		
		zd10+=",";
		zd10+=formatStr(jQuery(this).parents("li").find("input[name='zd10']").val());
		
		//��ȡ�����޸Ĺ��ĵ��Ž��id
		dtxxjgid+=",";
		dtxxjgid+=formatStr(jQuery(this).parents("li").find("input[name='dtxxjgid']").val());
	});
	var data={'jddmstr':jddm,'kssjstr':kssj,'grxjstr':grxj,'zd5str':zd5,'dtxxjgidstr':dtxxjgid,'zd1str':zd1,'zd2str':zd2,'zd3str':zd3,'zd8str':zd8,'zd9str':zd9,'zd10str':zd10};
	//alert(jddm+":"+kssj+":"+grxj+":"+dtxxjgid);
	//return false;
	lock();
 	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		data:data,
		success:function(data){
	 		 if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }else{
	    		 showAlert(data["message"]);
	    	 }
	 		unlock();
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
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var sjly=rows[0]["sjly"];
		if(sjly=="1"){
			showAlertDivLayer("����������Ϣ��<font color='red'>���������</font>,�����޸ģ�");
			return false;
		}
		var url = action+'?method=update&dtxxjgid=' + rows[0]["dtxxjgid"];
		var title = "�޸ĵ�����Ϣ";
		showDialog(title, 800, 450, url);
		jQuery("#dataTable").reloadGrid();
	}
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
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="ѧ��<font color='red'>["+data["nodel"]+"]</font>";
						mes+="�����������������ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function rcxwshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		var sjly=rows[0]["sjly"]
         if(sjly!="1"){//Ϊ���������
        	 showAlertDivLayer("�������Ϣ,������������̣�");
        	 return false;
         }
		showDialog("���Ź����������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}
//�Զ��嵼�� ����
function exportConfig(value) {
	var xxdm=jQuery("#xxdm").val();
	if("13871"==xxdm){
		customExport("dtxxjg_13871.do",exportData);
	} else {
        customExport(action,exportData);
    }
}

function exportYbzzConfig() {
    customExport("dtxxjg_zsdy.do",exportYbzzData);
}

function exportYbzzData() {
    setSearchTj();//���ø߼���ѯ����
    var url = action+"?method=exportData&dcclbh=dtxxjg_zsdy.do";
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

function exportYbdyConfig() {
    customExport("dtxxjg_ybdy.do",exportYbdyData);
}

function exportYbdyData() {
    setSearchTj();//���ø߼���ѯ����
    var url = action+"?method=exportData&dcclbh=dtxxjg_ybdy.do";
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

function exportFzdxConfig() {
    customExport("dtxxjg_fzdx.do",exportFzdxData);
}

function exportFzdxData() {
    setSearchTj();//���ø߼���ѯ����
    var url = action+"?method=exportData&dcclbh=dtxxjg_fzdx.do";
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

function exportJjfzConfig() {
    customExport("dtxxjg_jjfz.do",exportJjfzData);
}

function exportJjfzData() {
    setSearchTj();//���ø߼���ѯ����
    var url = action+"?method=exportData&dcclbh=dtxxjg_jjfz.do";
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
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
/*
���ݵ���
*/	
function impData(){
	var realTable = "";
	var tableName = "";
	var sty = "toolbar=no,location=no,directories=no,status=yes";
	sty += ",menubar=no,scrollbars=yes,resizable=yes,width=650,height=420,top=100";
	sty += ",left=200";
	if($("realTable")) realTable = document.getElementById("realTable").value;
	if($("tableName")) tableName = document.getElementById("tableName").value;
	url = 'dtjs_dtxxgl.do?method=importData';
	url += "&realTable=" + realTable;
	url += "&tableName=" + tableName;
	//showTopWin(url,600,500);
	//refreshForm(url);
	window.open(url,'',sty);
}
//ͬ������������ò
function tbgxzzmm(){
	confirmInfo("ȷ��ͬ������������ò��Ϣ��",function(ok){
			if("ok"==ok){
				jQuery.post(action+"?method=tbdtxx",function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
}

//��������
function mcexport() {
	setSearchTj();//���ø߼���ѯ����
	var url = action+"?method=mcexport";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//������Ͽҽ��ר-Ԥ��չ����
function getYsqs() {
	var xy = '';
	jQuery("a[name='a_xy_mc'].selectedValue").each(function(){
		xy = xy+jQuery(this).text()+',';
	})
	var value = xy.split(",");
	if(value.length != 2  ){
		showAlertDivLayer("��ѡ��һ��ϵ������ȷ�ϣ�");
		return false;
	}
	var xymc = jQuery.trim(jQuery("a[name='a_xy_mc'].selectedValue").text());
	var url= action+"?method=getYsqs&value="+xymc;
	window.open(url);
}

//������Ͽҽ��ר-Ԥ����Ա
function getXsyb() {
	var xy = '';
	jQuery("a[name='a_xy_mc'].selectedValue").each(function(){
		xy = xy+jQuery(this).text()+',';
	})
	var value = xy.split(",");
	if(value.length != 2  ){
		showAlertDivLayer("��ѡ��һ��ϵ������ȷ�ϣ�");
		return false;
	}
	var xymc = jQuery.trim(jQuery("a[name='a_xy_mc'].selectedValue").text());
	var url= action+"?method=getXsyb&value="+xymc;
	window.open(url);
}

//������Ͽҽ��ר-Ԥ����Աת������ʽ��Ա��
function getYbzz() {
	var xy = '';
	jQuery("a[name='a_xy_mc'].selectedValue").each(function(){
		xy = xy+jQuery(this).text()+',';
	})
	var value = xy.split(",");
	if(value.length != 2  ){
		showAlertDivLayer("��ѡ��һ��ϵ������ȷ�ϣ�");
		return false;
	}
	var xymc = jQuery.trim(jQuery("a[name='a_xy_mc'].selectedValue").text());
	var url= action+"?method=getYbzz&value="+xymc;
	window.open(url);
}