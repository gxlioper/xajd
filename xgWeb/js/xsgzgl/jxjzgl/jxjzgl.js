//ǰ��ѧ������
function cxJzwh(){
	var url = "jxjzgl_cxJxjz.do?method=cxJzwh";
	window.location.href=url;
	//refreshForm(url);
}

//ǰ���ѽ�������
function cxJzmd(){
	var url = "jxjzgl_cxJxjz.do?method=cxJzmd";
	window.location.href=url;
	//refreshForm(url);
}

//���ӱ��潨��
function saveZjBcXjjz(){
	if(checkNotNullZjBcXjjz()){
		var url="jxjzgl_cxJxjz_ajax.do?method=zjBcJzwh";
		var map={};
		map["jzjb"]=jQuery("#djdm").val();
		map["jzmc"]=escape(jQuery("#jzmc").val());
		map["jgmc"]=escape(jQuery("#jgmc").val());
		map["jgdh"]=jQuery("#jgdh").val();
		map["jsmc"]=escape(jQuery("#jsmc").val());
		map["jsdh"]=jQuery("#jsdh").val();
		map["sjid"]=getSjid();
		jQuery.post(url,map,function(data){
			if(data != null){
				alertInfo("���ӳɹ�!");
				zjBcXjjxNode(data);
			}else{
				alertInfo("����ʧ��!");
			}
			//createNode
			closeWindown();
			refurbishJxjz();
		},"json");
		
	}
}

//�޸ı��潨��
function saveXgBcXjjz(){
	if(checkNotNullZjBcXjjz()){
		var url="jxjzgl_cxJxjz_ajax.do?method=xgBcJzwh";
		var map={};
		map["jzjb"]=jQuery("#djdm").val();
		map["jzmc"]=escape(jQuery("#jzmc").val());
		map["jgmc"]=escape(jQuery("#jgmc").val());
		map["jgdh"]=jQuery("#jgdh").val();
		map["jsmc"]=escape(jQuery("#jsmc").val());
		map["jsdh"]=jQuery("#jsdh").val();
		map["jzid"]=jQuery("#jzid").val();
		jQuery.post(url,map,function(data){
			if(data != null && data.jzid != null){
				alertInfo("�޸ĳɹ�!");
				xgBcXjjxNode(data);
			}else{
				alertInfo("�޸�ʧ��!");
			}
			//createNode
			closeWindown();
			refurbishJxjzInfo();
		},"json");
		
	}
}

//��ѯ�ѽ���ѧ������
function cxYjzxsmd(obj){
	var jqSearchJzid=jQuery("#searchJzid");
	var jzid=getSjid();
	jqSearchJzid.val(jzid);
	searchRs();
	
}



//�޸ı��潨�ƽڵ�
function xgBcXjjxNode(data){
	var xzId=getSjid();
	var jqXz=jQuery("#"+xzId);
	jqXz.children().next().children().html(data.jzmc);
}

//ɾ�����ƽڵ�
function scXjjxNode(){
	var jzid=getSjid();
	var jqJzNode=jQuery("#"+jzid);
	jqJzNode.next().remove();
	jqJzNode.remove();
}

//��ȡ�ϼ�ID
function getSjid(){
	var hitTd=jQuery(".hitTd");
	if(hitTd.length == 0){
		alertInfo("��ѡ��ڵ�!");
		return "";
	}
	var sjId=hitTd.parent().attr("id");
	return sjId;
}

//��ȡѡ�еĵ�ǰ�ڵ�jQuery����
function getClickNode(){
	var hitTd=jQuery(".hitTd");
	if(hitTd.length == 0){
		alertInfo("��ѡ��ڵ�!");
		return null;
	}
	var clickNode=hitTd.children();
	return clickNode;
}

//�����¼�����
function zjXjjz(){
	if(checkZjxjjz() && checkXjjz()){
		jQuery.ajaxSetup({async:false});
		loadZjXjpzPage();
		tipsWindown("�����¼�����","id:div_zjjz","340","300","true","","true","id");
		jQuery.ajaxSetup({async:true});
	}
}

//�޸ľ�ѵ����
function xgJxjz(){
	if(checkDjjz()){
		//��ǰ�ڵ�Ϊ��ѵ�ڵ㲻���޸�
		alertInfo("�����޸ľ�ѵ��Ϣ!");
		return false;
	}
	if(checkZjxjjz()){
		jQuery.ajaxSetup({async:false});
		loadXgXjpzPage();
		tipsWindown("�޸��¼�����","id:div_zjjz","340","300","true","","true","id");
		jQuery.ajaxSetup({async:true});
	}
	
}

//��֤���ӱ����¼�����
function checkNotNullZjBcXjjz(){
	var jzmc=jQuery("#jzmc").val();
	if(jzmc == ""){
		alertInfo("����д��������!");
		return false;
	}
	return true;
}

//��֤�����¼�����
function checkZjxjjz(){
	var jqHitTd = jQuery(".hitTd");
	if(jqHitTd.length >0){
		return true;
	}else{
		alertInfo("��ѡ����!");
		return false;
	}
	return false;
}

//��֤�¼�����
function checkXjjz(){
	var jddjdmZd=jQuery("#jddjdmZd").val();
	var jzjb=jQuery(".hitTd").children().attr("class");
	if(jzjb == jddjdmZd){
		alertInfo("��ǰ����С����,����������¼�����!");
		return false;
	}
	return true;
}

//��֤�Ƿ��Ƕ�������
function checkDjjz(){
	var jzid=getSjid();
	var jqJzid=jQuery("#"+jzid);
	if(jqJzid.children().next().children().attr("class") ==null){
		return true;
	}
	return false;
}

//���������¼�����  ҳ��
function loadZjXjpzPage(){
	var url="jxjzgl_cxJxjz_ajax.do?method=zjJzwh";
	var sjid=getSjid();
	var map={"sjid":sjid};
	map["jzjb"]=jQuery(".hitTd").children().attr("class");
	jQuery("#div_zjjz").load(url,map,function(data){
	});
}

function qhShow(obj){
	if(obj.value=='002'){
		jQuery("#th1").text("Ӫ��");
		jQuery("#th2").text("Ӫ���绰");
		jQuery("#th3").text("�̵�Ա");
		jQuery("#th4").text("�̵�Ա�绰");
	}else if(obj.value=='003'){
		jQuery("#th1").text("����");
		jQuery("#th2").text("�����绰");
		jQuery("#th3").text("ָ��Ա");
		jQuery("#th4").text("ָ��Ա�绰");
	}else{
		jQuery("#th1").text("�̹�����");
		jQuery("#th2").text("�̹ٵ绰");
		jQuery("#th3").text("��ʦ����");
		jQuery("#th4").text("��ʦ�绰");
	}
}

//���������¼�����  ҳ��
function loadXgXjpzPage(){
	var url="jxjzgl_cxJxjz_ajax.do?method=xgJzwh";
	var jzid=getSjid();
	var map={"jzid":jzid};
	jQuery("#div_zjjz").load(url,map,function(data){
		//alert(jQuery("#div_zjjz").html()+"--"+data);
	});
}

//��֤Ψһ�Ľ�������
function checkOnlyJzmc(obj){
	var sjid=getSjid();
	var jqObj=jQuery(obj);
	var url="jxjzgl_cxJxjz_ajax.do?method=checkJzmc";
	var map={};
	map["sjid"]=sjid;
	map["jzmc"]=escape(jqObj.val());
	jQuery.post(url,map,function(data){
		if(data == "false"){
			alertInfo("���������Ѿ�����!");
			jqObj.val("");
			return false;
		}
	});
}

//��ѯά����������
function cxWhjzmd(){
	var jqClickNode=getClickNode();
	var jzid=getSjid();
	if(jqClickNode != null){
		if(checkIsChildrenNode(jqClickNode[0])){
			var url="jxjzgl_cxJxjz.do?method=cxWhjzmd&jzid="+jzid;
			showTopWin(url,'900','600');
		}else{
			alertInfo("��ѡ�����һ������ά����������!");
		}
	}else{
		return false;
	}
}

//ˢ��ҳ��
function refurbishJxjz(){
	searchRs();
	//ˢ��ͳ����Ϣ
	loadDqjzxx();
}

//ˢ��ҳ����Ϣ
function refurbishJxjzInfo(){
	var jqNode=getClickNode();
	jqNode.click();
}

//��ѯ��ʷ��������
function loadDqjzxx(){
	var url="jxjzgl_cxJxjz_ajax.do?method=cxDqjxxxAjax";
	jQuery("#jxjzrsTj").load(url,parameter,function(data){
	});
}

//ȡ������
function qxbz(){
	var jqCheck = jQuery("[name=div_pkValue]:checked");
	var pkValue = "";
	var jzids = "";
	if(jqCheck.length > 0){
		jqCheck.each(function(){
			var xh=jQuery(this).parent().next().html();
			var jzid=jQuery(this).val();
			if(pkValue == ""){
				pkValue=xh;
				jzids=jzid;
			}else{
				pkValue=pkValue+"!!@@!!"+xh;
				jzids=jzids+"!!@@!!"+jzid;
			}
		});
	}else{
		alertInfo("��ѡ��ѧ��!");
		return false;
	}
	var jxid=jQuery("#jxid").val();
	var url="jxjzgl_cxJxjz_ajax.do?method=qxbz";
	var map={};
	map["pkValue"]=pkValue;
	map["jxid"]=jxid;
	map["jzids"]=jzids;
	jQuery.post(url,map,function(data){
		if(data != null){
			alertInfo(data);
			//ˢ��ѧ������
			if(data == "ȡ�����Ƴɹ�!"){
				refurbishJxjz();
			}
		}
	});
	
	
}

//ɾ����ѵ����
function scXjjz(){
	var jzid=getSjid();
	var parentJzid=jQuery("#"+jzid).parent().parent().parent().parent().prev().attr("id");
	if(checkDjjz()){
		//��ǰ�ڵ�Ϊ��ѵ�ڵ㲻��ɾ��
		alertInfo("����ɾ����ѵ!");
		return false;
	}
	if(jzid != ""){
		confirmInfo("ɾ���������ƺ󱾼����Ƶ��¼�����Ҳ�ᱻɾ��,�Ƿ�ȷ��ɾ��?",function(t){
			if(t=="ok"){
				var url="jxjzgl_cxJxjz_ajax.do?method=scJzwh";
				var map={};
				map["jzid"]=jzid;
				jQuery.post(url,map,function(data){
					if(data == "true"){
						scXjjxNode();
						gotoNode(parentJzid);
						alertInfo("ɾ���ɹ�!");
					}else{
						alertInfo("ɾ��ʧ��!");
					}
				});	
			}
		});
	}
}

//ɾ�����ƺ�λ���ϼ��ڵ�
function gotoNode(jzid){
	var jqJzNode=jQuery("#"+jzid);
	jqJzNode.children().next().children().click();
}

//��֤�����������ݾ�ѵ�ɼ��;�ѵ����
function checkJzmdByJxcjAndJxbx(){
	var url="jxjzgl_cxJxjz_ajax.do?method=checkXscjAndBx";
	var jxid=jQuery("#jxid").val();
	var jzid=getSjid();
	var map={"jxid":jxid,"jzid":jzid};
	jQuery.post(url,map,function(data){
		if(data !=null){
			if(data.isJxcj == "true" || data.isJxbx == "true"){
				alertInfo("ɾ�������µĲ���ѧ���ѱ�¼���ѵ�ɼ����ѵ����,����ɾ����ǰ����.");
			}else{
				scXjjz();
			}
		}
	},"json");	
}

//���ӱ��潨�ƽڵ�
function zjBcXjjxNode(data){
	var jzwhList=weaveData(data);
	var jqNodeHtml=null;
	var xzId=getSjid();
	var jqXz=jQuery("#"+xzId);
	var jqParentNode=getClickNode();

	//�жϽڵ��Ƿ����ӽڵ�Ŀ¼
	if(jqXz.next().attr("class") == "chlidNode"){
		//�����ӽڵ�
		jqNodeHtml=createNodeOption(jzwhList);
		jqXz.next().children().next().children().children().append(jqNodeHtml);
		return false;
	}else{
		//����ȥ��ѯ�Ƿ����ӽڵ�Ŀ¼
		createChildrenNode(jqParentNode);
	}
	
}

//��ֻ֤����������
function cehckInt(obj){
	obj.value=obj.value.replace(/[^\d]/g,'');
}

