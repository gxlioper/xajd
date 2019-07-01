var dd=new DateGrid({
			style:'month',
			proxy:loadData,
			reader:{id:'id',title:'title',date:'date',content:'content'},
			operator:{add:function(id){}}
		});
		dd.render("tt");
		dd.viewable=true;
		jQuery('#curMonth').html(dd.virtualDay.getFullYear()+'��'+(dd.virtualDay.getMonth()+1)+'��');
		
		
		jQuery(function(){
			loadData();
		});
		
		function loadData(){
			var d = [];
			var today = dd.virtualDay.getFullYear()+'-'+(dd.virtualDay.getMonth()+1)+'-'+dd.virtualDay.getDate();
			var parame = {};
			parame["queryDate"]=today;
			jQuery.ajaxSetup({async : false});
				jQuery.post("xlzx_zxspb.do?method=zxsPbbForXs&doType=query",parame,function(data){
					for (var i = 0 ; i < data.length ; i++){
						d[i]={
							  id:data[i]["pbid"],
							  title:"",//��ʱû��ʹ��
							  date:data[i]["rq"],
							  sfyy:data[i]["sfyy"],
							  content:"",
							  className:data[i]["color"]
						};
					}
					
				},'json');
				loadDateContent(d);
			jQuery.ajaxSetup({async : true});
		}

		function addHoverEvent(){
			jQuery(".reserve, .arrangement").each(function(){
				var that = jQuery(this);
				that.hoverDelay({
					hoverEvent: function(){
						that.css("position","relative").children(".datetab_tsxx_right, .datetab_tsxx_left").show();
					},
					outEvent: function(){
						that.css("position","").children(".datetab_tsxx_right, .datetab_tsxx_left").hide();
					}
				});
			});
		}
		
		function loadDateContent(data){
			for ( var i = 0; i < data.length; i++) {
				var id = data[i][dd.reader.id];
				var date = data[i][dd.reader.date];
				var div = document.getElementById(date),
				contentDiv = document.createElement("div"),
				className = data[i]["className"];
				contentDiv.className = className;
				content = data[i]["content"];
				//�������ʱ��ʾ�����Ű����ѯʦ��Ϣ
				var titles= getMSContent(date);
				if ("open" == className){
					contentDiv.innerHTML = "<button type='button' title='"+titles+"' onclick='ckYy(\""+date+"\")'>�鿴</button>";
					contentDiv.setAttribute("title", titles);
					contentDiv.setAttribute("style", "cursor:pointer");
				}else if ("arrangement" == className ){
					if(getSfymFlag(date)=="Y"){
						contentDiv.innerHTML = "<button type='button' title='"+titles+"' >��Լ��</button>";
						contentDiv.setAttribute("title", titles);
						contentDiv.setAttribute("style", "cursor:pointer");
						className = "close";
					}else{
						if("Y"==data[i]["sfyy"]){
							contentDiv.innerHTML = "<button title='"+titles+"' type='button'>��ԤԼ</button>";
							contentDiv.setAttribute("title", titles);
							contentDiv.setAttribute("style", "cursor:pointer");
						}else{
							var sysDate=new Date().format("yyyy-MM-dd");
							if(date < sysDate){
								//contentDiv.innerHTML = "<button title='"+titles+"' type='button' >����ԤԼ</button>";
								contentDiv.setAttribute("title", titles);
								contentDiv.setAttribute("style", "cursor:pointer");
							}
							if(date >= sysDate){
								contentDiv.innerHTML = "<button type='button'  title='"+titles+"'  onclick='yyZxs(\""+date+"\")'>ԤԼ</button>";
								contentDiv.setAttribute("title", titles);
								contentDiv.setAttribute("style", "cursor:pointer");
							}
						}
					}
				}else if ("close" == className){
					//contentDiv.innerHTML = "<button type='button' >��Լ��</button>";
				} 
				div.appendChild(contentDiv);
			}
		}
		
		function getMSContent(date){
			var result='';
			var zxsInfoList = getZxspbInfo(date);
			if(zxsInfoList!=null && zxsInfoList!=''){
				result += "��ѯ��ʦ:";
				for(var i=0;i<zxsInfoList.length;i++){
					result +="\n";
					result += zxsInfoList[i]["xm"];
					if(zxsInfoList[i]["xb"]!=null && zxsInfoList[i]["xb"]!=''){
						result +="["+zxsInfoList[i]["xb"]+"]";
					}
					if(zxsInfoList[i]["bmmc"]!=null && zxsInfoList[i]["bmmc"]!=''){
						result +="["+zxsInfoList[i]["bmmc"]+"]";
					}
				}
			}
			return result;
		}
		
		
		//��ȡ��ǰ�������Ű����ѯʦ��Ϣ
		function getZxspbInfo(date){
			var zxsInfoList='';
			var zgh='';
			jQuery.ajaxSetup({async : false});
			jQuery.post("xlzx_zxspb.do?method=getPbZxsListByDate",{pbdate:date},function(data){
				zxsInfoList = data;
			},'json');
			jQuery.ajaxSetup({async : true});
			return zxsInfoList;
		}

		//��ȡ��ǰ�������Ű���A�s�f��
		function getYysmInfo(date){
			var zxspbInfo='';
			var zgh='';
			jQuery.ajaxSetup({async : false});
			jQuery.post("xlzx_zxspb.do?method=getYysmByDate",{pbdate:date},function(data){
				zxspbInfo = data;
			},'json');
			jQuery.ajaxSetup({async : true});
			return zxspbInfo;
		}
		
		//�Ƿ�Լ��
		function getSfymFlag(date){
			var sfym='';
			jQuery.ajaxSetup({async : false});
			jQuery.post("xlzx_yysqnew.do?method=getSfymFlag&date="+date,{},function(data){
				if(data=="Y"){
				sfym="Y";
				}
			},'');
			jQuery.ajaxSetup({async : true});
			return sfym;
		}
		
		//����ԤԼ
		function yyZxs(date){
			var sysDate=new Date().format("yyyy-MM-dd");
			if(date < sysDate){
				showAlert("����ԤԼ����֮ǰ������!");
				return false;
			}
			document.location.href="xlzx_yysqnew.do?method=yysqDetail&doType=add&date="+date;
		}

		//�鿴ԤԼ
		function ckYy(date){
			document.location.href="xlzx_yysqnew.do?method=yysqDetail&doType=view&date="+date;
		}


		//ˢ�µ�ǰҳ��
		function refreshPage(){
			dd.refresh();
			jQuery('#curMonth').html(dd.virtualDay.getFullYear()+'��'+(dd.virtualDay.getMonth()+1)+'��');
			loadData();
		}