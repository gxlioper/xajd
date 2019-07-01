<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/LodopFuncs.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"��������б�",
				pager:"pager",
				url:"xpj_pjjg.do?method=pjjghzList&type=query",
				colList:[
				   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
				   {label:'xq',name:'xq', index: 'xq',hidden:true},
				   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'10%'},
				   {label:'lxdm',name:'lxdm', index: 'lxdm',hidden:true},
				   {label:'��Ŀ����',name:'xmlxmc', index: 'xmlxmc',width:'10%'},
				   {label:'xzdm',name:'xzdm', index: 'xzdm',hidden:true},
				   {label:'��Ŀ����',name:'xmxzmc', index: 'xmxzmc',width:'10%'},
				   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'20%'},
				   {label:'������',name:'hjrs', index: 'hjrs',width:'5%',formatter:rsLink}
				],
				sortname: "xn,xq",
			 	sortorder: "desc"
				};


			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}


			function rsLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='pjjgmdView(\""+rowObject["xn"]+"\",\""+rowObject["xq"]+"\",\""
						+rowObject["xmmc"]+"\",\""+rowObject["lxdm"]+"\",\""+rowObject["xzdm"]+"\",\""+rowObject["xqmc"]+"\");'>"+rowObject["hjrs"]+"</a>";
			}

			function pjjgmdView(xn,xq,xmmc,lxdm,xzdm,xqmc){
				var url = 'xpj_pjjg.do?method=getpjjghzMd&xn='+xn+'&xq='+xq+'&xmmc='+xmmc+'&lxdm='+lxdm+'&xzdm='+xzdm+'&xqmc='+xqmc;
				showDialog("����ѧ���б�", 800, 540, url);
			}


			//��ӡexcel��ѧԺ��
			function getExcelXy(){
				var rows = jQuery("#dataTable").getSeletRow();
				var xyArr = getClickXy();
				 if (rows.length ==0){
					showAlertDivLayer("��ѡ��һ����¼��");
					return false;
				 }
				 if(xyArr.length == 0){
                     showAlertDivLayer("��ѡ������ѡ��һ��ѧԺ��");
                     return false;
				 }
				 if (rows.length == 1){
					 if(rows.length>1){
				    	 for(var i=0;i<rows.length-1;i++){
				    		 if(rows[i+1]["xn"]!=rows[i]["xn"]&&rows[i+1]["xq"]!=rows[i]["xq"]){
				    			 showAlertDivLayer("��ѡ����ͬѧ��ѧ�ڵ�������Ŀ��");
				    			 return false;
				    		 }
				    		 if(rows[i+1]["xmmc"]!=rows[i]["xmmc"]){
				    			 showAlertDivLayer("��ѡ����ͬ���Ƶ�������Ŀ��");
				    			 return false;
				    		 }
				    	 }
					 }
			     	var rows=encodeURIComponent(encodeURIComponent(JSON.stringify(rows)));
    				var url="xpj_pjjg.do?method=getHzbXy";
    				url += "&rows="+rows+"&xydms=" + xyArr.toString();
 					window.open(url);
			     }else{
			     	showAlertDivLayer("��ѡ��һ����¼��");
					return false;
			     }
			}

        //��ӡexcel
        function getExcel(){
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length ==0){
                showAlertDivLayer("��ѡ��һ����¼��");
                return false;
            } else if (rows.length == 1){
                if(rows.length>1){
                    for(var i=0;i<rows.length-1;i++){
                        if(rows[i+1]["xn"]!=rows[i]["xn"]&&rows[i+1]["xq"]!=rows[i]["xq"]){
                            showAlertDivLayer("��ѡ����ͬѧ��ѧ�ڵ�������Ŀ��");
                            return false;
                        }
                        if(rows[i+1]["xmmc"]!=rows[i]["xmmc"]){
                            showAlertDivLayer("��ѡ����ͬ���Ƶ�������Ŀ��");
                            return false;
                        }
                    }
                }
                var rows=encodeURIComponent(encodeURIComponent(JSON.stringify(rows)));
                var url="xpj_pjjg.do?method=getHzb";
                url += "&rows="+rows;
                window.open(url);
            }else{
                showAlertDivLayer("��ѡ��һ����¼��");
                return false;
            }
        }
		
			function post(URL, PARAMS) {        
			    var temp = document.createElement("form");        
			    temp.action = URL;        
			    temp.method = "post";        
			    temp.style.display = "none";        
			    for (var x in PARAMS) {        
			        var opt = document.createElement("textarea");        
			        opt.name = x;        
			        opt.value = PARAMS[x];        
			        // alert(opt.name)         
			        temp.appendChild(opt);        
			    }        
			    document.body.appendChild(temp);        
			    temp.submit();        
			    document.body.removeChild(temp);    
			}


			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >ʹ�ð���</a>
			</p>
		</div>
		<html:form action="/xszz_zzxmjg" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						����ѡ�� ͬѧ�ꡢͬ��Ŀ���Ƶ�������Ŀ��
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div id="shtyhzdcdiv" style="display:none">
				
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="getExcel();return false;" class="btn_down">�ϱ�����</a></li>
						<logic:equal name="xxdm" value="12036">
							<logic:equal value="xx" name="userStatus">
								<li><a href="javascript:void(0);" onclick="getExcelXy();return false;" class="btn_down">ѧԺ�ϱ�����</a></li>
							</logic:equal>
						</logic:equal>

					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ������Ŀ�����б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
