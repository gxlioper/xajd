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
				jQuery.post("xlzx_zxspb.do?method=zxsPbDeal&doType=query",parame,function(data){
					for (var i = 0 ; i < data.length ; i++){
						d[i]={
							  id:data[i]["pbid"],
							  date:data[i]["rq"],
							  content:"",
							  className:data[i]["color"]
						};
					}
				},'json');
				dd.clearAllEventsInTable();
				dd.eventItem=d;
				loadDateContent(d);
			jQuery.ajaxSetup({async : true});
		}

		function loadDateContent(data){
			var XLZX_ZXSPB_N = jQuery("#XLZX_ZXSPB_N").val();
			for ( var i = 0; i < data.length; i++) {
				var id = data[i][dd.reader.id];
				var date = data[i][dd.reader.date];
				var div = document.getElementById(date),
					contentDiv = document.createElement("div"),
					className = data[i]["className"],
					content = data[i]["content"];
				var sysDate=new Date().format("yyyy-MM-dd");
				// 过期数据不能修改
				var xg_n = XLZX_ZXSPB_N == 'true' && date < sysDate;
				contentDiv.className = className;
				 if ("close" == className){
					 if(xg_n){
						 contentDiv.setAttribute("style", "background-color:rgb(220, 220, 220)!important");
					 }else{
						 contentDiv.innerHTML = "<button type='button' onclick='apZxs(\""+date+"\")'>安排</button>";
					 }
				} else if ("arrangement" == className){
					//鼠标移上时显示当天排班的咨询师信息
					var titles= getMSContent(date);
					if(xg_n){
						contentDiv.innerHTML = "<button type='button' title='"+titles+"' onclick='ckApyy(\""+id+"\")'>查看</button>";
						contentDiv.setAttribute("title", titles);
						contentDiv.setAttribute("style", "cursor:pointer");
					}else{
						contentDiv.innerHTML = "<button type='button' title='"+titles+"' onclick='xgApyy(\""+id+"\")'>修改</button>";
						contentDiv.setAttribute("title", titles);
						contentDiv.setAttribute("style", "cursor:pointer");

					}
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
		
		//安排预约
		function apZxs(date){
			var url="xlzx_zxspb.do?method=zxspbDetail&doType=add&date="+date;
			var sysDate=new Date().format("yyyy-MM-dd");
//			if(date <= sysDate){
//				showAlert("预约不能安排在今天或今天之前的日期!");
//				return false;
//			}
			showDialog("咨询师排班",780,550,url);
		}

		//查看预约
		function ckApyy(id){
			var url="xlzx_zxspb.do?method=zxspbDetail&doType=view&id="+id;
			showDialog("查看咨询师排班",780,550,url);
		}

		//修改安排预约
		function xgApyy(id){
			var url="xlzx_zxspb.do?method=zxspbDetail&doType=update&id="+id;
			showDialog("修改咨询师排班",780,550,url);
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
		/* 用途：湖南城市学院个性化导出方法！
		 * 作者：孟威
		 * 时间：2016.06.29 18:39
		 */
		function exportConfig(){
			var dqny = jQuery("#curMonth").html();
			var url = "xlzx_zxspb.do?method=exportConfig";
			url+= "&str_dqny="+dqny;
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		