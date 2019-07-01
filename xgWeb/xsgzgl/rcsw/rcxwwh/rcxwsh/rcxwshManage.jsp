<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwh/rcxwsh/js/rcxwshManage.js"></script>
		<script type="text/javascript">
		function cancelShnew(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
			} else {				
				var splc = rows[0]["splc"];
				var shid = rows[0]["shid"];
				var xxwhid = rows[0]["id"];
				var shzt =  rows[0]["shzt"];
				showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
					jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post("rcsw_rcxwwh_rcxwshgl.do?method=cancelRcxwsh",{xxwhid:xxwhid,shzt:shzt},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
					
				},'json');
				}});
			}
		}
	
		function plsh(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length == 0){
				showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼��");
			} else {
				var title = "�ճ���Ϊ�������";
				if(${xxdm=="13815"}){
					title = "����ѧ���������";
				}
				if(${xxdm=="13431"}){
                    title = "�ӷ������������";
                }
				showDialog(title,500,300,"rcsw_rcxwwh_rcxwshgl.do?method=toPlsh");
			}
		}
		
		function tjsh(shzt,shyj){
			
			var rows = jQuery("#dataTable").getSeletRow();
			
			jQuery.post("rcsw_rcxwwh_rcxwshgl.do?method=plsh",{shzt:shzt,shyj:shyj,info:JSON.stringify(rows)},function(data){
				showAlertDivLayer(data.message);
				searchRs();
			});
		}
		
		</script>
	</head>

	<body style="min-height: 800px;">
		<input type="hidden" value="dsh" id="shzt"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwh_rcxwshgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="rcxwSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
						<logic:equal name="writeAble" value="yes">		   
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>				
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<logic:equal name="xxdm" value="13815">	
					<span>����ѧ������б�&nbsp;&nbsp; </span>
				</logic:equal>
				<logic:notEqual name="xxdm" value="13815">
					<logic:notEqual name="xxdm" value="13815">
						<span>�ճ���Ϊ����б�&nbsp;&nbsp; </span>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal name="xxdm" value="13431">
					<span>�ӷ���������б�&nbsp;&nbsp; </span>
				</logic:equal>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		
		
		<div id="plsh" style="display:none;">
			<table class="formlist">
				<thead>
					<tr>
						<th colspan="2">
							<span>�������</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="30%">
							������
						</th>
						<td>
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=rcxw&id=shyj" /><br/><br/>
							<textarea rows="5" style="width:95%;" id="shyj"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
