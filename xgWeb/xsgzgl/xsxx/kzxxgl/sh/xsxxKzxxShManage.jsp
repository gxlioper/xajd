<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ����չ��Ϣ����б�",
				pager:"pager",
				url:"xsxx_kzxxshgl.do?method=list&actionType=query&type=D",
				colList : [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '8%',formatter : xhLink },
							{ label : '����', name : 'xm', index : 'xm', width : '8%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
							{ label : '�꼶', name : 'nj', index : 'nj', width : '5%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xydm', width : '10%' },
							{ label : 'רҵ', name : 'zymc', index : 'zydm', width : '10%' },
							{ label : 'splc', name : 'splc', index : 'splc', hidden : true },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label:'shid',name:'shid', index: 'shid',hidden:true},
							{ label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
							{ label : '�༶', name : 'bjmc', index : 'bjdm', width : '10%' },
							{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '6%' },
							{ label : '���״̬', name : 'shztmc', index : 'shzt', width : '10%' } ],
				sortname: "sqsj",
			 	sortorder: "desc"
			}


			var gridSetting2 = {
				caption:"ѧ����չ��Ϣ����б�",
				pager:"pager",
				url:"xsxx_kzxxshgl.do?method=list&actionType=query&type=Y",
				colList : [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '8%',formatter : xhLink },
							{ label : '����', name : 'xm', index : 'xm', width : '8%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xydm', width : '10%' },
							{ label : 'רҵ', name : 'zymc', index : 'zydm', width : '10%' },
							{ label : 'splc', name : 'splc', index : 'splc', hidden : true },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label:'shid',name:'shid', index: 'shid',hidden:true},
							{ label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
							{ label : '�༶', name : 'bjmc', index : 'bjdm', width : '10%' },
							{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '6%' },
							{ label : '���״̬', name : 'shztmc', index : 'shzt', width : '10%' } ],
				sortname: "shsj",
			 	sortorder: "desc"
			}
			
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);	
			});

			/**
			 * �������Ѵ�����ǩ�л�
			 * @param obj
			 * @param shzt
			 * @return
			 */
			function selectTab(obj,shzt){
				jQuery("#shzt").val(shzt);

				if (shzt == "dsh"){
					jQuery("#li_sh").css("display","");
					jQuery("#li_qx").css("display","none");
					
					jQuery("#dataTable").initGrid(gridSetting);
				} else {
					jQuery("#li_sh").css("display","none");
					jQuery("#li_qx").css("display","");
					
					jQuery("#dataTable").initGrid(gridSetting2);
				}
				
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				
				searchRs();
			}
			/**
			 * ѧ������
			 * @param cellValue
			 * @param rowObject
			 * @return
			 */

			function xhLink(cellValue,rowObject){
				//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
				var onclickfn = "onclick=\"" + "showDialog('ѧ����չ��Ϣ' , 800 , 550 , 'xsxx_kzxxshgl.do?method=ck&sqid=" + rowObject['sqid'] + "')" + "\"";
				
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
				
				return el;
			}
			
			/**
			 * �߼���ѯ
			 * @return
			 */
			function searchRs() {
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			/**
			 * ���
			 */
			function sh(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				if(rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ��˼�¼��");
					return false;
				}
				
				showDialog("ѧ����չ��Ϣ���",750,550,"xsxx_kzxxshgl.do?method=sh&sqid="+rows[0]["sqid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&splc="+rows[0]["splc"]);
			}
			
			
			/**
			 * �������
			 * @return
			 */
			function cancelSh(){
				var rows = jQuery("#dataTable").getSeletRow();
			
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
				} else {
					
					//���һ��������˺���õ�·��
					var cancelPath = "xsxx_kzxxshgl.do?method=cancelShAction";
					confirmInfo("��ȷ��Ҫ����������?",function(ty){
						if(ty=="ok"){
							jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["splc"],shid:rows[0]["shid"]},function(data){
									// �ж��Ƿ����һ������(1:���һ�������ɹ���
									if("1" == data["cancelFlg"]){
										jQuery.post(cancelPath,{sqid:rows[0]["sqid"]},function(result){
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
						}
					});
			
				}
			}
			
			/**
			 * ���̸���
			 * @return
			 */
			function lcinfoSh(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
				}
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
		<html:form action="/xsxx_kzxxshgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="sh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_sr">����</a>
						</li>		
						</logic:equal>				
						<li><a href="javascript:void(0);" onclick="lcinfoSh();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>			
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
				<span>ѧ����չ��Ϣ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
