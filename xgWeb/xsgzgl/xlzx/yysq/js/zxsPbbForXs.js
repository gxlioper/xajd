var xxdm = jQuery("#xxdm").val();
var dd=new DateGrid({
			style:'month',
			proxy:loadData,
			reader:{id:'id',title:'title',date:'date',content:'content'},
			operator:{add:function(id){}}
		});
		dd.render("tt");
		dd.viewable=true;
		jQuery('#curMonth').html(dd.virtualDay.getFullYear()+'年'+(dd.virtualDay.getMonth()+1)+'月');
		
		
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
							  title:"",//暂时没有使用
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
				//鼠标移上时显示当天排班的咨询师信息
				var titles= getMSContent(date);
				if ("open" == className){
					contentDiv.innerHTML = "<button type='button' title='"+titles+"' onclick='ckYy(\""+date+"\")'>查看</button>";
					contentDiv.setAttribute("title", titles);
					contentDiv.setAttribute("style", "cursor:pointer");
				}else if ("arrangement" == className ){
					if(getSfymFlag(date)=="Y"){
						contentDiv.innerHTML = "<button type='button' title='"+titles+"' >已约满</button>";
						contentDiv.setAttribute("title", titles);
						contentDiv.setAttribute("style", "cursor:pointer");
						className = "close";
					}else{
						if("Y"==data[i]["sfyy"]){
							contentDiv.innerHTML = "<button title='"+titles+"' type='button'>已预约</button>";
							contentDiv.setAttribute("title", titles);
							contentDiv.setAttribute("style", "cursor:pointer");
						}else{
							var sysDate=new Date().format("yyyy-MM-dd");
							if(date < sysDate){
								//contentDiv.innerHTML = "<button title='"+titles+"' type='button' >不可预约</button>";
								contentDiv.setAttribute("title", titles);
								contentDiv.setAttribute("style", "cursor:pointer");
							}
							if(date >= sysDate){
									contentDiv.innerHTML = "<button type='button'  title='"+titles+"'  onclick='yyZxs(\""+date+"\")'>预约</button>";
									contentDiv.setAttribute("title", titles);
									contentDiv.setAttribute("style", "cursor:pointer");
							}
						}
					}
				}else if ("close" == className){
					//contentDiv.innerHTML = "<button type='button' >已约满</button>";
				} 
				div.appendChild(contentDiv);
			}
		}
		
		function getMSContent(date){
			var result='';
			var zxsInfoList = getZxspbInfo(date);
			if(zxsInfoList!=null && zxsInfoList!=''){
				result += "咨询老师:";
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
		
		
		//获取当前日期已排班的咨询师信息
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

		//获取当前日期已排班的預約說明
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
		
		//是否约满
		function getSfymFlag(date){
			var sfym='';
			jQuery.ajaxSetup({async : false});
			jQuery.post("xlzx_yysq.do?method=getSfymFlag&date="+date,{},function(data){
				if(data=="Y"){
				sfym="Y";
				}
			},'');
			jQuery.ajaxSetup({async : true});
			return sfym;
		}
		
		//安排预约
		function yyZxs(date){
			var sysDate=new Date().format("yyyy-MM-dd");
			if(date < sysDate){
				showAlert("不能预约今天之前的日期!");
				return false;
			}
			document.location.href="xlzx_yysq.do?method=yysqDetail&doType=add&date="+date;
		}

		//查看预约
		function ckYy(date){
			document.location.href="xlzx_yysq.do?method=yysqDetail&doType=view&date="+date;
		}


		//刷新当前页面
		function refreshPage(){
			dd.refresh();
			jQuery('#curMonth').html(dd.virtualDay.getFullYear()+'年'+(dd.virtualDay.getMonth()+1)+'月');
			loadData();
		}