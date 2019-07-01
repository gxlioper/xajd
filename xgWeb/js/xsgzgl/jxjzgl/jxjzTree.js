	var closeNodeCode="Open";
	var openNodeCode="Close";
	var leafNodeCode="Child";
	//初始化树
	function initTree(){
		//暂时定死
		var rootName=jQuery("#jxmc").val();
		var rootID=jQuery("#jxid").val();

		//获取树容器
		var jqTreeContainer=jQuery("#gy-tree");
		//获取根节点
		var jqTable=rootNode(rootName,rootID);
		//填充根节点
		jqTreeContainer.append(jqTable);
	}
	//根节点
	function rootNode(rootName,rootID){
		var jqTable=jQuery("<table></table>");
		var jqTr=jQuery("<tr></tr>");
		var jqTd1=jQuery("<td></td>");
		var jqTd2=jQuery("<td></td>");
		var jqSpan=jQuery("<span></span>");
		
		//设置table属性
		jqTable.attr("border","0");
		jqTable.attr("cellSpacing","0");
		jqTable.attr("cellPadding","0");
		//jqTable.attr("id",rootID);
		//设置tr属性
		jqTr.attr("id",rootID);
		//设置td1属性
		jqTd1.addClass("bj");
		//jqTd1.html("0");
		jqTd1.html(getClickStyleA(closeNodeCode));
		//设置节点属性
		jqSpan.html(rootName);
		jqSpan.bind("click",function(){
			//为节点设置点击事件
			clickNode(this);
		});
		//设置td2属性
		jqTd2.append(jqSpan);
		//组装根节点
		jqTr.append(jqTd1);
		jqTr.append(jqTd2);
		jqTable.append(jqTr);
		return jqTable;
	}

	//获取点击样式A连接   独立开是为了以后更换样式
	function getClickStyleA(aStyle){
		
		var jqA=jQuery("<a></a>");
		var jqDiv=jQuery("<div></div>");
		jqA.addClass(aStyle);
		jqA.html(jqDiv);
		jqA.attr("href","#");
		jqA.bind("click",function(){
			//为节点设置点击事件
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
	//创建节点
	/** data:未json数据
	*data[i].dm:节点代码(唯一)
	*data[i].mc:节点名称
	*data[i].jb:节点级别-- 1:中间节点，2：叶子节点
	*length：生成节点的长度
	*/
	function createNode(data){
		var jqTable=jQuery("<table></table>");
		var jqTbody=jQuery("<tbody></tbody>");
		var jqTr=null;
		var jqTd1=null;
		var jqTd2=null;
		var jqSpan=null;
		//设置table属性
		jqTable.attr("border","0");
		jqTable.attr("cellSpacing","0");
		jqTable.attr("cellPadding","0");
		jqTable.attr("id","rootNode");
		for(var i=0;i<data.length; i++){
			jqTr=jQuery("<tr></tr>");
			jqTd1=jQuery("<td></td>");
			jqTd2=jQuery("<td></td>");
			jqSpan=jQuery("<span></span>");
			//设置tr属性
			jqTr.attr("id",data[i].dm);
			//设置td1属性
			jqTd1.addClass("bj");
			
			jqTd1.html(getClickStyleA(data[i].jbys));
			//设置span
			jqSpan.html(data[i].mc);
			//如果为级别为叶子节点就标记当前节点
			jqSpan.addClass(data[i].jb);//标记为叶节点

			//有演示控制
			if(leafNodeCode == data[i].jbys){
				jqSpan.bind("click",function(){
					//为节点设置点击事件
					clickLeafageNode(this);
					
				});
			}else{
				jqSpan.bind("click",function(){
					//为节点设置点击事件
					clickNode(this);
				});
			}
			
			//设置td2属性
			jqTd2.append(jqSpan);
			//组装节点内容
			jqTr.append(jqTd1);
			jqTr.append(jqTd2);
			jqTbody.append(jqTr);
			
		}
		jqTable.append(jqTbody);
		//组装节点
		jqTr=jQuery("<tr></tr>");
		jqTd1=jQuery("<td></td>");
		jqTd2=jQuery("<td></td>");
		//设置tr属性
		jqTr.attr("id","");
		jqTr.addClass("chlidNode");//子节点tr标记
		//设置td1属性
		jqTd1.addClass("bj");
		//设置td2属性
		jqTd2.append(jqTable);
		//组装
		jqTr.append(jqTd1);
		jqTr.append(jqTd2);
		return jqTr;
		
	}

	//创建子节点项
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
			//设置tr属性
			jqTr.attr("id",data[0].dm);
			//设置td1属性
			jqTd1.addClass("bj");
			jqTd1.html(getClickStyleA(data[0].jbys));
			//设置span
			jqSpan.html(data[0].mc);
			//如果为级别为叶子节点就标记当前节点
			jqSpan.addClass(data[0].jb);//标记为叶节点

			//有样式控制
			if(leafNodeCode == data[0].jbys){
				jqSpan.bind("click",function(){
					//为节点设置点击事件
					clickLeafageNode(this);
				});
			}else{
				jqSpan.bind("click",function(){
					//为节点设置点击事件
					clickNode(this);
					
				});
			}
			
			//设置td2属性
			jqTd2.append(jqSpan);
			//组装节点内容
			jqTr.append(jqTd1);
			jqTr.append(jqTd2);
		}
		return jqTr;
	}
	//显示节点
	function showNode(jqNode){
		if(jqNode != null && jqNode.attr("class") == "chlidNode"){
			jqNode.show();
			//显示样式控制
			//jqNode.prev().children().first().html(openNodeCode);
			jqNode.prev().children().children("a").attr("class",openNodeCode);
			nodeStyle(jqNode.prev().children().first().next());
		}
	}
	//隐藏节点
	function hideNode(jqNode){
		if(jqNode != null && jqNode.attr("class") == "chlidNode"){
			jqNode.hide();
			//显示样式控制
			//jqNode.prev().children().first().html(closeNodeCode);
			jqNode.prev().children().children("a").attr("class",closeNodeCode);
			nodeStyle(jqNode.prev().children().first().next());
		}
	}
	//选中节点样式控制
	function nodeStyle(jqNode){
		//清除以前被选中样式
		jQuery(".hitTd").removeClass("hitTd");
		jqNode.addClass("hitTd");
	}
	
	//显示后隐藏节点
	function toggleNode(jqNode) {
		if (jqNode.css("display") == "none") {
			showNode(jqNode);
		} else {
			hideNode(jqNode);
		}
	}
	
	// 根业务有关，业务关系节点数据
	/**
	*jqParentNode:父节点的jQuery对象
	*/
	function createChildrenNode(jqParentNode){
		var url="jxjzgl_cxJxjz_ajax.do?method=cxXjjd";
		var nodeId=jqParentNode.parent().parent().attr("id");
		var map={"nodeId":nodeId};
		jQuery.post(url,map,function(data){
			if(data.length != 0){
				//组装数据
				var jzData=weaveData(data);
				//组装节点
				var newNode=createNode(jzData);
				//加入到页面
				jqParentNode.parent().parent().after(newNode);
				//默认显示节点
				showNode(newNode);
			}
		},"json");
	}
	
	//点击节点
	function clickNode(obj){
		var jqParentNode=jQuery(obj);
		var nextNode=jqParentNode.parent().parent().next();
		//点击选中样式
		nodeStyle(jqParentNode.parent());
		//加载已被编制学生信息
		cxYjzxsmd(obj);
		//判断节点是否是叶子节点
		if(checkIsChildrenNode(obj)){
			clickLeafageNode(jqParentNode);
			return false;
		}else{
			clickNotLeafageNode(obj);
		}

		//加载页面数据
		clickNodeLoadPage(obj);
		
		//判断节点是否有子节点目录
		if(nextNode.attr("class") == "chlidNode"){
			//已有子节点目录，显示或隐藏它
			//toggleNode(nextNode);
			return false;
		}else{
			//去查询是否有子节点目录
			createChildrenNode(jqParentNode);
		}
		
		//点击是非叶子节点
		//判断是否生成过子节点，生成过子节点就不再生成

		//加载页面数据
		//clickNodeLoadPage(obj);
	}
	
	//点击叶子节点
	function clickLeafageNode(obj){
		
		var jqParentNode=jQuery(obj);
		//清除以前被选中样式
		//点击选中样式
		nodeStyle(jqParentNode.parent());
		//jQuery(".hitTd").removeClass("hitTd");
		//jqParentNode.addClass("hitTd");
		//alert("我是叶子");

		//加载页面数据
		clickNodeLoadPage(obj);
		//加载已被编制学生信息
		cxYjzxsmd(obj);
	}
	
	//点击是非叶子节点
	function clickNotLeafageNode(obj){
		
	}

	//点击节点加载页面
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

	//初始化军训建制
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

	//组装数据
	function weaveData(jxjzData){
		var jddjdmZd= jQuery("#jddjdmZd").val();//军训等级最低代码
		var dataList=new Array();
		for ( var i = 0; i < jxjzData.length; i++) {
			var data={};
			data["dm"]=jxjzData[i].jzid;
			data["mc"]=jxjzData[i].jzmc;
			//级别样式
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

	//设置页面军训建制信息
	function setJxjzxx(data){
		var jqShowJzmc=jQuery("#showJzmc");
		var jqShowJgmc=jQuery("#showJgmc");
		var jqShowJgdh=jQuery("#showJgdh");
		var jqShowJsmc=jQuery("#showJsmc");
		var jqShowJsdh=jQuery("#showJsdh");
		
		if(data.xxdm=="10704"){
			if(data.jzjb=="002"){//营
				jQuery("#showJgmc").parent().children().eq(0).html("营长");
				jQuery("#showJgmc").parent().children().eq(2).html("营长电话");
				jQuery("#showJsmc").parent().children().eq(0).html("教导员");
				jQuery("#showJsmc").parent().children().eq(2).html("教导员电话");
			}else if(data.jzjb=="003"){//连
				jQuery("#showJgmc").parent().children().eq(0).html("连长");
				jQuery("#showJgmc").parent().children().eq(2).html("连长电话");
				jQuery("#showJsmc").parent().children().eq(0).html("指导员");
				jQuery("#showJsmc").parent().children().eq(2).html("指导员电话");
			}else{
				jQuery("#showJgmc").parent().children().eq(0).html("教官名称");
				jQuery("#showJgmc").parent().children().eq(2).html("教官电话");
				jQuery("#showJsmc").parent().children().eq(0).html("教师名称");
				jQuery("#showJsmc").parent().children().eq(2).html("教师电话");
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
	
	//设置页面军训建制根目录
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

	//验证当前点击节点是否是子节点
	function checkIsChildrenNode(obj){
		var jqParentNode=jQuery(obj);
		var leafNode=jQuery("#jddjdmZd").val();
		if(jqParentNode.attr("class") == leafNode){
			return true;
		}else{
			return false;
		}
	}