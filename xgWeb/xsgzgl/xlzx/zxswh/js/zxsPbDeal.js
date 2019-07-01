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
				// �������ݲ����޸�
				var xg_n = XLZX_ZXSPB_N == 'true' && date < sysDate;
				contentDiv.className = className;
				 if ("close" == className){
					 if(xg_n){
						 contentDiv.setAttribute("style", "background-color:rgb(220, 220, 220)!important");
					 }else{
						 contentDiv.innerHTML = "<button type='button' onclick='apZxs(\""+date+"\")'>����</button>";
					 }
				} else if ("arrangement" == className){
					//�������ʱ��ʾ�����Ű����ѯʦ��Ϣ
					var titles= getMSContent(date);
					if(xg_n){
						contentDiv.innerHTML = "<button type='button' title='"+titles+"' onclick='ckApyy(\""+id+"\")'>�鿴</button>";
						contentDiv.setAttribute("title", titles);
						contentDiv.setAttribute("style", "cursor:pointer");
					}else{
						contentDiv.innerHTML = "<button type='button' title='"+titles+"' onclick='xgApyy(\""+id+"\")'>�޸�</button>";
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
		
		//����ԤԼ
		function apZxs(date){
			var url="xlzx_zxspb.do?method=zxspbDetail&doType=add&date="+date;
			var sysDate=new Date().format("yyyy-MM-dd");
//			if(date <= sysDate){
//				showAlert("ԤԼ���ܰ����ڽ�������֮ǰ������!");
//				return false;
//			}
			showDialog("��ѯʦ�Ű�",780,550,url);
		}

		//�鿴ԤԼ
		function ckApyy(id){
			var url="xlzx_zxspb.do?method=zxspbDetail&doType=view&id="+id;
			showDialog("�鿴��ѯʦ�Ű�",780,550,url);
		}

		//�޸İ���ԤԼ
		function xgApyy(id){
			var url="xlzx_zxspb.do?method=zxspbDetail&doType=update&id="+id;
			showDialog("�޸���ѯʦ�Ű�",780,550,url);
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
		/* ��;�����ϳ���ѧԺ���Ի�����������
		 * ���ߣ�����
		 * ʱ�䣺2016.06.29 18:39
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
		