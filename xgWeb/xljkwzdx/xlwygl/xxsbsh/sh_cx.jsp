<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				pager : "pager",
				url : "xljk_xlwygl_xxsbglwh.do?method=shquery&type=D",
				colList : [
							{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
							{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '15%',formatter : link},
							{ label : '����', name : 'xm', index : 'xm', width : '9%'},
							{ label : '�꼶', name : 'nj', index : 'nj', width : '6%'},
							{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%'},
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%'},
							{ label : '�ܴ�', name : 'zbzc', index : 'zbzc', width : '7%' },
							{ label : '�ϱ�����', name : 'sblxmc', index : 'sblxmc', width : '10%' },
							{ label : '�ϱ�ʱ��', name : 'sbsj', index : 'sbsj', width : '10%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label : 'shid', name : 'shid', index : 'shid', hidden : true },
							{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true },
							{ label : 'xtgwid', name : 'xtgwid', index : 'xtgwid', hidden : true }],
				sortname : "sbsj", sortorder : "desc",radioselect:false }

			var gridSetting2 = {
				pager:"pager",
				url:"xljk_xlwygl_xxsbglwh.do?method=shquery&type=Y",
				colList : [
							{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
							{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '15%',formatter : link},
							{ label : '����', name : 'xm', index : 'xm', width : '9%'},
							{ label : '�꼶', name : 'nj', index : 'nj', width : '6%'},
							{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%'},
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%'},
							{ label : '�ܴ�', name : 'zbzc', index : 'zbzc', width : '7%' },
							{ label : '�ϱ�����', name : 'sblxmc', index : 'sblxmc', width : '10%' },
							{ label : '�ϱ�ʱ��', name : 'sbsj', index : 'sbsj', width : '10%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label : 'shid', name : 'shid', index : 'shid', hidden : true },
							{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true },
							{ label : 'xtgwid', name : 'xtgwid', index : 'xtgwid', hidden : true }],
				sortname: "shsj",
			 	sortorder: "desc",
			 	radioselect:true
			}
			
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);	
			});

		/**
		 * �߼���ѯ
		 * @return
		 */
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		
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
			 * ����
			 * @param cellValue
			 * @param rowObject
			 * @return
			 */

			function link(cellValue,rowObject){
				//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
				var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 680,430 , 'xljk_xlwygl_xxsbglwh.do?method=shck&sbsqid=" + rowObject['sbsqid'] + "')" + "\"";
				
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
				
				return el;
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
					showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sbsqid']+"&splc="+rows[0]['splcid']);
				}
			}

			/**
			 * �����������
			 */
			function sh(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length == 0){
					showAlertDivLayer("��ѡ��һ����Ҫ��˼�¼��");
					return false;
				} else if(rows.length == 1){
					showDialog("�ϱ���Ϣ���",750,580,"xljk_xlwygl_xxsbglwh.do?method=sh&sbsqid="+rows[0]["sbsqid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&splid="+rows[0]["splcid"]);
				} else {
					showDialog("�ϱ���Ϣ���",500,300,"xljk_xlwygl_xxsbglwh.do?method=plsh");
				}
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
					var cancelPath = "xljk_xlwygl_xxsbglwh.do?method=cancelShAction";
					confirmInfo("��ȷ��Ҫ����������?",function(ty){
						if(ty=="ok"){
							jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["splcid"],shid:rows[0]["shid"]},function(data){
									// �ж��Ƿ����һ������(1:���һ�������ɹ���
									if("1" == data["cancelFlg"]){
										//alert(rows[0]["sbsqid"])
										jQuery.post(cancelPath,{sbsqid:rows[0]["sbsqid"],shzt:rows[0]["shzt"]},function(result){
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
			 * �������
			 * @param shzt
			 * @param shyj
			 * @return
			 */
			function savePlsh(shzt,shyj){
				
				var rows = jQuery("#dataTable").getSeletRow();
				var guid = new Array();
				var gwid = new Array();
				var xhs  = new Array();
				var splc = new Array();
				
				jQuery.each(rows, function(i,row){
					guid.push(row["sbsqid"]);
					gwid.push(row["xtgwid"]);
					xhs.push(row["xh"]);
					splc.push(row["splcid"]);
					
				});
				
				jQuery.post(
						"xljk_xlwygl_xxsbglwh.do?method=plsh&type=save",
						{
						 shzt:shzt,
						 id:guid,
						 gwids:gwid,
						 xhs:xhs,
						 shyj:shyj,
						 splcs:splc
						 
						},function(data){
							
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						},
						'json'
				);
				
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
		<html:form action="/xljk_xlwygl_xxsbglwh">
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
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
