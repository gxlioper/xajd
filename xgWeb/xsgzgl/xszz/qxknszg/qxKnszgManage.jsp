<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
						
		jQuery(function(){
			
			var gridSetting = {
					caption:"�����������ѯ",
					pager:"pager",
					url:"xszz_qxknszggl.do?method=qxKnszgManage&type=query",
					colList:[
						{label:'key',name:'guid', index: 'guid',key:true ,hidden:true},	
						{label:'sfqxrd',name:'sfqxrd', index: 'sfqxrd',key:true ,hidden:true},												 						 
						{label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
						{label:'���� ',name:'xm', index: 'xm',width:'7%'},
						{label:'�Ա� ',name:'xb', index: 'xb',width:'9%'},
						{label:'ѧԺ',name:'xymc', index: 'xymc',width:'21%'},
						{label:'�༶',name:'bjmc', index: 'bjmc',width:'21%'},
						{label:'�϶�����',name:'dcmc', index: 'dcmc',width:'13%'}
					],
					sortname: "sqsj",
				 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
						
		});

		function searchRs(){
			
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
			
		}


		function qxrd() {	
							
			var map = getSuperSearch();									
			var rows = jQuery("#dataTable").getSeletRow();							
			if(rows.length == 0){								
				jQuery.post("xszz_qxknszggl.do?method=getCountNum&qxtype=all",map,function(data){					
					if(data == "0"){
						showAlertDivLayer(" ��ѡ���ѧ���������ʸ���ȡ����");
					}
					if(data != "0"){						
						var url = 'xszz_qxknszggl.do?method=cancelKnsrdzg&countNum='+data;							
						var title = "����ȡ��ѧ���������ʸ�";
						showDialog(title, 368, 258, url);
					}
				});																														
			}			
			
			if(rows.length > 1){												
				var guids = new Array();						 			
			    for (var i=0;i<rows.length;i++){			    
				     if (jQuery.trim(rows[i]["sfqxrd"]) == "1"){
					     showAlertDivLayer(" ��ѡ���ѧ���������ʸ���ȡ����");
					     return false;
					     break; 
					  } 
				     guids.push(rows["guid"]);					    
				}
			    var countNum = rows.length;  
			   
				showDialog("����ȡ��ѧ���������ʸ�",368,258,"xszz_qxknszggl.do?method=cancelKnsrdzg&qxtype=more&countNum="+countNum);					
				return false;														
			}										
			 var countNum = rows.length;  				
			 var url = 'xszz_qxknszggl.do?method=cancelKnsrdzg&qxtype=one'+'&guid='+rows[0]["guid"]+'&countNum='+countNum;				    		
			 var title = "ȡ���϶�";
			 showDialog(title, 459,258, url);	
			 					
		}

		// ������˱���
		function saveMorePlqxrd(qxyy,qxtype){
												
			var rows = jQuery("#dataTable").getSeletRow();
			var guids = new Array();	
			jQuery.each(rows,function(i,row){
				guids.push(row["guid"]);			
			});						
			jQuery.post("xszz_qxknszggl.do?method=cancelKnsrdzg&type=save"+'&qxyy='+encodeURI(encodeURI(qxyy))+'&qxtype='+qxtype,{				
				guids:guids
				},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');
			
		}

		// �����������δ��У
		function saveAllPlqxrd(qxyy,qxtype){
										
			var map = getSuperSearch();		
			jQuery.post("xszz_qxknszggl.do?method=cancelKnsrdzg&type=save"+'&qxyy='+encodeURI(encodeURI(qxyy))+'&qxtype='+qxtype,map,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');	
															
		}	
		
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					ѧ������ʦ�ύ���������϶��������������ͨ���������������˲˵�<br/>
					�û�Ҳ���ڴ˴�ֱ��ά������������					
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/xszz_qxknszggl" >			
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_knsjgb"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="qxrd();return false;" class="btn_xg">ȡ���϶�</a></li>				
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
				<span> �����������ѯ </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
