	var closeNodeCode="Open";
	var openNodeCode="Close";
	var leafNodeCode="Child";
	//��ʼ����
	function initTree(){
		//��ʱ����
		var rootName=jQuery("#jxmc").val();
		var rootID=jQuery("#jxid").val();

		//��ȡ������
		var jqTreeContainer=jQuery("#gy-tree");
		//��ȡ���ڵ�
		var jqTable=rootNode(rootName,rootID);
		//�����ڵ�
		jqTreeContainer.append(jqTable);
	}
	//���ڵ�
	function rootNode(rootName,rootID){
		var jqTable=jQuery("<table></table>");
		var jqTr=jQuery("<tr></tr>");
		var jqTd1=jQuery("<td></td>");
		var jqTd2=jQuery("<td></td>");
		var jqSpan=jQuery("<span></span>");
		
		//����table����
		jqTable.attr("border","0");
		jqTable.attr("cellSpacing","0");
		jqTable.attr("cellPadding","0");
		//jqTable.attr("id",rootID);
		//����tr����
		jqTr.attr("id",rootID);
		//����td1����
		jqTd1.addClass("bj");
		//jqTd1.html("0");
		jqTd1.html(getClickStyleA(closeNodeCode));
		//���ýڵ�����
		jqSpan.html(rootName);
		jqSpan.bind("click",function(){
			//Ϊ�ڵ����õ���¼�
			clickNode(this);
		});
		//����td2����
		jqTd2.append(jqSpan);
		//��װ���ڵ�
		jqTr.append(jqTd1);
		jqTr.append(jqTd2);
		jqTable.append(jqTr);
		return jqTable;
	}

	//��ȡ�����ʽA����   ��������Ϊ���Ժ������ʽ
	function getClickStyleA(aStyle){
		
		var jqA=jQuery("<a></a>");
		var jqDiv=jQuery("<div></div>");
		jqA.addClass(aStyle);
		jqA.html(jqDiv);
		jqA.attr("href","#");
		jqA.bind("click",function(){
			//Ϊ�ڵ����õ���¼�
			var jqObj=jQuery(this);
			toggleNode(jqObj.parent().parent().next());
			
			if(aStyle==closeNodeCode || jqObj.attr("class")==openNodeCode){
				aStyle=jqObj.attr("class");
				clickNode(jQuery("span",jqObj.parent().next()));
			}
			return false;
		});
		return jqA;
	}
	//�����ڵ�
	/** data:δjson����
	*data[i].dm:�ڵ����(Ψһ)
	*data[i].mc:�ڵ�����
	*data[i].jb:�ڵ㼶��-- 1:�м�ڵ㣬2��Ҷ�ӽڵ�
	*length�����ɽڵ�ĳ���
	*/
	function createNode(data){
		var jqTable=jQuery("<table></table>");
		var jqTbody=jQuery("<tbody></tbody>");
		var jqTr=null;
		var jqTd1=null;
		var jqTd2=null;
		var jqSpan=null;
		//����table����
		jqTable.attr("border","0");
		jqTable.attr("cellSpacing","0");
		jqTable.attr("cellPadding","0");
		jqTable.attr("id","rootNode");
		for(var i=0;i<data.length; i++){
			jqTr=jQuery("<tr></tr>");
			jqTd1=jQuery("<td></td>");
			jqTd2=jQuery("<td></td>");
			jqSpan=jQuery("<span></span>");
			//����tr����
			jqTr.attr("id",data[i].dm);
			//����td1����
			jqTd1.addClass("bj");
			
			jqTd1.html(getClickStyleA(data[i].jbys));
			//����span
			jqSpan.html(data[i].mc);
			//���Ϊ����ΪҶ�ӽڵ�ͱ�ǵ�ǰ�ڵ�
			jqSpan.addClass(data[i].jb);//���ΪҶ�ڵ�

			//����ʾ����
			if(leafNodeCode == data[i].jbys){
				jqSpan.bind("click",function(){
					//Ϊ�ڵ����õ���¼�
					clickLeafageNode(this);
					
				});
			}else{
				jqSpan.bind("click",function(){
					//Ϊ�ڵ����õ���¼�
					clickNode(this);
				});
			}
			
			//����td2����
			jqTd2.append(jqSpan);
			//��װ�ڵ�����
			jqTr.append(jqTd1);
			jqTr.append(jqTd2);
			jqTbody.append(jqTr);
			
		}
		jqTable.append(jqTbody);
		//��װ�ڵ�
		jqTr=jQuery("<tr></tr>");
		jqTd1=jQuery("<td></td>");
		jqTd2=jQuery("<td></td>");
		//����tr����
		jqTr.attr("id","");
		jqTr.addClass("chlidNode");//�ӽڵ�tr���
		//����td1����
		jqTd1.addClass("bj");
		//����td2����
		jqTd2.append(jqTable);
		//��װ
		jqTr.append(jqTd1);
		jqTr.append(jqTd2);
		return jqTr;
		
	}

	//�����ӽڵ���
	function createNodeOption(data){
		var jqTr=null;
		var jqTd1=null;
		var jqTd2=null;
		var jqSpan=null;
		if(data.length != 0){
			jqTr=jQuery("<tr></tr>");
			jqTd1=jQuery("<td></td>");
			jqTd2=jQuery("<td></td>");
			jqSpan=jQuery("<span></span>");
			//����tr����
			jqTr.attr("id",data[0].dm);
			//����td1����
			jqTd1.addClass("bj");
			jqTd1.html(getClickStyleA(data[0].jbys));
			//����span
			jqSpan.html(data[0].mc);
			//���Ϊ����ΪҶ�ӽڵ�ͱ�ǵ�ǰ�ڵ�
			jqSpan.addClass(data[0].jb);//���ΪҶ�ڵ�

			//����ʽ����
			if(leafNodeCode == data[0].jbys){
				jqSpan.bind("click",function(){
					//Ϊ�ڵ����õ���¼�
					clickLeafageNode(this);
				});
			}else{
				jqSpan.bind("click",function(){
					//Ϊ�ڵ����õ���¼�
					clickNode(this);
					
				});
			}
			
			//����td2����
			jqTd2.append(jqSpan);
			//��װ�ڵ�����
			jqTr.append(jqTd1);
			jqTr.append(jqTd2);
		}
		return jqTr;
	}
	//��ʾ�ڵ�
	function showNode(jqNode){
		if(jqNode != null && jqNode.attr("class") == "chlidNode"){
			jqNode.show();
			//��ʾ��ʽ����
			//jqNode.prev().children().first().html(openNodeCode);
			jqNode.prev().children().children("a").attr("class",openNodeCode);
			nodeStyle(jqNode.prev().children().first().next());
		}
	}
	//���ؽڵ�
	function hideNode(jqNode){
		if(jqNode != null && jqNode.attr("class") == "chlidNode"){
			jqNode.hide();
			//��ʾ��ʽ����
			//jqNode.prev().children().first().html(closeNodeCode);
			jqNode.prev().children().children("a").attr("class",closeNodeCode);
			nodeStyle(jqNode.prev().children().first().next());
		}
	}
	//ѡ�нڵ���ʽ����
	function nodeStyle(jqNode){
		//�����ǰ��ѡ����ʽ
		jQuery(".hitTd").removeClass("hitTd");
		jqNode.addClass("hitTd");
	}
	
	//��ʾ�����ؽڵ�
	function toggleNode(jqNode) {
		if (jqNode.css("display") == "none") {
			showNode(jqNode);
		} else {
			hideNode(jqNode);
		}
	}
	
	// ��ҵ���йأ�ҵ���ϵ�ڵ�����
	/**
	*jqParentNode:���ڵ��jQuery����
	*/
	function createChildrenNode(jqParentNode){
		var url="jxjzgl_cxJxjz_ajax.do?method=cxXjjd";
		var nodeId=jqParentNode.parent().parent().attr("id");
		var map={"nodeId":nodeId};
		jQuery.post(url,map,function(data){
			if(data.length != 0){
				//��װ����
				var jzData=weaveData(data);
				//��װ�ڵ�
				var newNode=createNode(jzData);
				//���뵽ҳ��
				jqParentNode.parent().parent().after(newNode);
				//Ĭ����ʾ�ڵ�
				showNode(newNode);
			}
		},"json");
	}
	
	//����ڵ�
	function clickNode(obj){
		var jqParentNode=jQuery(obj);
		var nextNode=jqParentNode.parent().parent().next();
		//���ѡ����ʽ
		nodeStyle(jqParentNode.parent());
		//�����ѱ�����ѧ����Ϣ
		cxYjzxsmd(obj);
		//�жϽڵ��Ƿ���Ҷ�ӽڵ�
		if(checkIsChildrenNode(obj)){
			clickLeafageNode(jqParentNode);
			return false;
		}else{
			clickNotLeafageNode(obj);
		}

		//����ҳ������
		clickNodeLoadPage(obj);
		
		//�жϽڵ��Ƿ����ӽڵ�Ŀ¼
		if(nextNode.attr("class") == "chlidNode"){
			//�����ӽڵ�Ŀ¼����ʾ��������
			//toggleNode(nextNode);
			return false;
		}else{
			//ȥ��ѯ�Ƿ����ӽڵ�Ŀ¼
			createChildrenNode(jqParentNode);
		}
		
		//����Ƿ�Ҷ�ӽڵ�
		//�ж��Ƿ����ɹ��ӽڵ㣬���ɹ��ӽڵ�Ͳ�������

		//����ҳ������
		//clickNodeLoadPage(obj);
	}
	
	//���Ҷ�ӽڵ�
	function clickLeafageNode(obj){
		
		var jqParentNode=jQuery(obj);
		//�����ǰ��ѡ����ʽ
		//���ѡ����ʽ
		nodeStyle(jqParentNode.parent());
		//jQuery(".hitTd").removeClass("hitTd");
		//jqParentNode.addClass("hitTd");
		//alert("����Ҷ��");

		//����ҳ������
		clickNodeLoadPage(obj);
		//�����ѱ�����ѧ����Ϣ
		cxYjzxsmd(obj);
	}
	
	//����Ƿ�Ҷ�ӽڵ�
	function clickNotLeafageNode(obj){
		
	}

	//����ڵ����ҳ��
	function clickNodeLoadPage(obj){
		var jqParentNode=jQuery(obj);
		var url="jxjzgl_cxJxjz_ajax.do?method=cxJxjzxx";
		var jzid=jqParentNode.parent().parent().attr("id");
		var map={"jzid":jzid};
		jQuery.post(url,map,function(data){
			if(data != null){
				if(data.jzid == jQuery("#jxid").val()){
					setJxjzxxRoot(data);
				}else{
					setJxjzxx(data);
				}
			}
		},"json");
	}

	//��ʼ����ѵ����
	function initJxjz(){
		var rootID=jQuery("#jxid").val();
		var url="jxjzgl_cxJxjz.do?method=initYjJxjz";
		jQuery.post(url,{},function(data){
			if(data != null){
				var nodeData=weaveData(data);
				var jqNode=createNode(nodeData);
				var jqRootNode=jQuery("#"+rootID);
				jqRootNode.after(jqNode);
				showNode(jqNode);
			}
		},"json");
	}

	//��װ����
	function weaveData(jxjzData){
		var jddjdmZd= jQuery("#jddjdmZd").val();//��ѵ�ȼ���ʹ���
		var dataList=new Array();
		for ( var i = 0; i < jxjzData.length; i++) {
			var data={};
			data["dm"]=jxjzData[i].jzid;
			data["mc"]=jxjzData[i].jzmc;
			//������ʽ
			if(jddjdmZd == jxjzData[i].djdm){
				data["jbys"]=openNodeCode;
			}else{
				data["jbys"]=closeNodeCode;
			}
			data["jb"]=jxjzData[i].djdm;
			dataList[i]=data;
		}
		return dataList;
	}

	//����ҳ���ѵ������Ϣ
	function setJxjzxx(data){
		var jqShowJzmc=jQuery("#showJzmc");
		var jqShowJgmc=jQuery("#showJgmc");
		var jqShowJgdh=jQuery("#showJgdh");
		var jqShowJsmc=jQuery("#showJsmc");
		var jqShowJsdh=jQuery("#showJsdh");
		
		if(data.xxdm=="10704"){
			if(data.jzjb=="002"){//Ӫ
				jQuery("#showJgmc").parent().children().eq(0).html("Ӫ��");
				jQuery("#showJgmc").parent().children().eq(2).html("Ӫ���绰");
				jQuery("#showJsmc").parent().children().eq(0).html("�̵�Ա");
				jQuery("#showJsmc").parent().children().eq(2).html("�̵�Ա�绰");
			}else if(data.jzjb=="003"){//��
				jQuery("#showJgmc").parent().children().eq(0).html("����");
				jQuery("#showJgmc").parent().children().eq(2).html("�����绰");
				jQuery("#showJsmc").parent().children().eq(0).html("ָ��Ա");
				jQuery("#showJsmc").parent().children().eq(2).html("ָ��Ա�绰");
			}else{
				jQuery("#showJgmc").parent().children().eq(0).html("�̹�����");
				jQuery("#showJgmc").parent().children().eq(2).html("�̹ٵ绰");
				jQuery("#showJsmc").parent().children().eq(0).html("��ʦ����");
				jQuery("#showJsmc").parent().children().eq(2).html("��ʦ�绰");
			}
		}
		if(data.treeJzmc == null){
			jQuery("#showJzmc").html("");
		}else{
			jQuery("#showJzmc").html(data.treeJzmc);
		}
		if(data.jgmc == null){
			jQuery("#showJgmc").html("");
		}else{
			jQuery("#showJgmc").html(data.jgmc);
		}
		if(data.jgdh == null){
			jQuery("#showJgdh").html("");
		}else{
			jQuery("#showJgdh").html(data.jgdh);
		}
		if(data.jsmc == null){
			jQuery("#showJsmc").html("");
		}else{
			jQuery("#showJsmc").html(data.jsmc);
		}
		if(data.jsdh == null){
			jQuery("#showJsdh").html("");
		}else{
			jQuery("#showJsdh").html(data.jsdh);
		}
	}
	
	//����ҳ���ѵ���Ƹ�Ŀ¼
	function setJxjzxxRoot(data){
		var jqShowJzmc=jQuery("#showJzmc");
		var jqShowJgmc=jQuery("#showJgmc");
		var jqShowJgdh=jQuery("#showJgdh");
		var jqShowJsmc=jQuery("#showJsmc");
		var jqShowJsdh=jQuery("#showJsdh");
		if(data.jzmc == null){
			jQuery("#showJzmc").html("");
		}else{
			jQuery("#showJzmc").html(data.jzmc);
		}
		jQuery("#showJgmc").html("");
		jQuery("#showJgdh").html("");
		jQuery("#showJsmc").html("");
		jQuery("#showJsdh").html("");
	}

	//��֤��ǰ����ڵ��Ƿ����ӽڵ�
	function checkIsChildrenNode(obj){
		var jqParentNode=jQuery(obj);
		var leafNode=jQuery("#jddjdmZd").val();
		if(jqParentNode.attr("class") == leafNode){
			return true;
		}else{
			return false;
		}
	}