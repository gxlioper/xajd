<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script type="text/javascript" src="xsgzgl/rcsw/jqfxgl/jqfxFxwh/js/jqfxwhManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">	

		function addfxgl() {

			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
				return false;
			}	

			var map = getSuperSearch();
			//var jsonStr = JSON.stringify(map);									
			var rows = jQuery("#dataTable").getSeletRow();							
			if(rows.length == 0 /*|| rows.length > 1*/){
				jQuery.post("rcsw_jqfxFxwh.do?method=getCountNum",map,function(data){
                if(data == "0"){
                    showAlertDivLayer("��ѡ���ѧ����¼Ϊ��У״̬��");
                }
                if(data != "0"){
						var url = 'rcsw_jqfxFxwh.do?method=plxsJqfx&countNum='+data;
						var title = "������У����";
						return showDialog(title, 400, 300, url);
					}
				});
			}
			/*����ҵ���жϣ�1529 jzע�͵�*/
			if(rows.length > 1){
				var xhs = new Array();						 			
			    for (var i=0;i<rows.length;i++){
				     if (jQuery.trim(rows[i]["fxzt"]) == "1"){
					     showAlertDivLayer("����ѧ����¼Ϊ��У״̬��");
					     return false;
					     break; 
					     } 
					     xhs.push(rows["xh"]);					    
				  }
			    var countNum = rows.length;  
				showDialog("������У����",420,300,"rcsw_jqfxFxwh.do?method=pldgxsJqfx&countNum="+countNum);
				return false;										
			}
			var fxzt = rows[0]["fxzt"];					
			
						
			/*var url = 'rcsw_jqfxFxwh.do?method=addxsJqfx&id='+rows[0]["id"]+'&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];*/
			/*	����ҵ���жϣ�1529 jzע�͵�
			if(jQuery.trim(fxzt) == "0"){				
		        var url = 'rcsw_jqfxFxwh.do?method=addxsJqfx&id='
				    + rows[0]["id"]+'&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    
		    }else{	
		    	// ������˱���				    
		    	var url = 'rcsw_jqfxFxwh.do?method=addxsJqfx&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    	
		    }
		    */
		    if(jQuery.trim(fxzt) != "-1"){			
				var url = 'rcsw_jqfxFxwh.do?method=addxsJqfx&id='+rows[0]["id"]+'&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];
			}else{
				var url = 'rcsw_jqfxFxwh.do?method=addxsJqfx&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];
			}
			var title = "��У����";
			showDialog(title, 720, 500, url);
			
			
					
		}

	
		</script>
	</head>
			
	<body>
	<input type="hidden" name="isopen" id="isopen" value="${jbszModel.isopen }"/>
			<!-- ������ -->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown="showHelpDiv()">ʹ�ð���</a>
				</p>
			</div>

			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					���ѡ�з�У״̬Ϊ���ѷ�У�ļ�¼������ʾ��ѧ���ѷ�У��������ѡ��;
				</p>
				<a class="close" title="����"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			
		<html:form action="/rcsw_jqfxFxwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="addfxgl();return false;" 
							>��У</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="addwfxgl();return false;" class="btn_sc">δ��У</a>
						</li>
						</logic:equal>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
							<li><a href="javascript:void(0);" onclick="bdqktj();return false;" class="btn_dy">�������ͳ��</a></li>
						</logic:equal>		
						<li><a href="#" class="btn_ck" onclick="viewfxgl();return false;">�鿴</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���ڷ�У�б� &nbsp; ѧ��/ѧ�ڣ� <font color="blue">${xn}/${xq}</font> &nbsp;&nbsp;��У��� <font color="blue">${fxmc}</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
