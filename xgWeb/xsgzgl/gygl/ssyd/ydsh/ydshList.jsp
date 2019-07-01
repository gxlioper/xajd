<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		//��ʼ����ѯ
		function getDclGird(){
			return {
				caption:"�����춯����б�",
				pager:"pager",
				url:"ydsh.do?method=list&type=query",
				colList:[
						   {label:'ѧ��',name:'xh', index: 'xh'},//,formatter:xhLink
						   {label:'����',name:'xm', index: 'xm'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
						   {label:'�Ա�',name:'xb', index: 'xb'},
						   {label:'����',name:'ssxx', index: 'ssxx'},
						   {label:'�춯����',name:'ssydlxmc', index: 'ssydlxmc'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
						   {label:'���״̬',name:'shzt', index: 'shzt'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},	
						   {label:'�춯���ʹ���',name:'ssydlx', index: 'ssydlx',hidden:true},
						   {label:'���´�λ��Ϣ',name:'sqshHideCwxx', index: 'sqshHideCwxx',hidden:true},
						   {label:'ssydsqid',name:'ssydsqid', index: 'ssydsqid',key:true,hidden:true}
				],
				params:{shlx:"dsh"},
				sortname: "sqsj",
			 	sortorder: "asc",
			 	radioselect:false
			}
		}
		function getYclGrid(){
			return {
				caption:"�����춯����б�",
				pager:"pager",
				url:"ydsh.do?method=list&type=query",
				colList:[
						   {label:'ѧ��',name:'xh', index: 'xh'},//,formatter:xhLink
						   {label:'����',name:'xm', index: 'xm'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
						   {label:'�Ա�',name:'xb', index: 'xb'},
						   {label:'����',name:'ssxx', index: 'ssxx'},
						   {label:'�춯����',name:'ssydlxmc', index: 'ssydlxmc'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
						   {label:'���״̬',name:'shzt', index: 'shzt'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},	
						   {label:'�춯���ʹ���',name:'ssydlx', index: 'ssydlx',hidden:true},
						   {label:'ssydsqid',name:'ssydsqid', index: 'ssydsqid',key:true,hidden:true}
				],
				params:{shlx:"ysh"},
				sortname: "shsj",
			 	sortorder: "desc",
			 	radioselect:true
			}
		}
			/*
			 * ����
			 */
			function cxshnew(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ������˵ļ�¼��");
				} else {
					splc_cx_new(rows[0]["splcid"],rows[0]["shid"]);
				}
			}
			/*
			 * �������̳���
			 * shid ���id
			 * splc ��������id 
			 */
			function splc_cx_new(splc,shid){
				//���һ��������˺���õ�·��
				var cancelPath = jQuery("#cancelPath").val();
				confirmInfo("��ȷ��Ҫ����������?",function(ty){
					if(ty=="ok"){
						jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
								// �ж��Ƿ����һ������(1:���һ�������ɹ���
								if("1" == data["cancelFlg"]){
									jQuery.post(cancelPath,{splcid:splc,shid:shid},function(result){
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
			
			/*
			 * �������̳���
			 * shid ���id
			 * splc ��������id 
			 */
			function splc_cx(splcid,shid){
				confirmInfo("��ȷ��Ҫ����������?",function(ty){
					if(ty=="ok"){
						jQuery.post("ydsh.do?method=cxsh",{splcid:splcid,shid:shid},function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						},'json');
					}
				});
			}
			/*
			 * ����[���һ�����ɳ���]
			 */
			function cxsh(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ��˵ļ�¼��");
				} else {
					splc_cx(rows[0]["splcid"],rows[0]["shid"]);
				}
			}
			jQuery(function(){
				var dclGrid = getDclGird();
				var map = getSuperSearch();
					map["shlx"] = "dsh";
					dclGrid["params"]=map;
				jQuery("#dataTable").initGrid(dclGrid);
				
				jQuery("#btn_sh").click(go_sh);
				jQuery("#btn_cs").click(lcgz);
				jQuery("#btn_qxsh").click(cxshnew);
				jQuery("#btn_qxsh").hide();
			});
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="cancelPath" id="cancelPath" value="${cancelPath}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li><a href="javascript:void(0);" id="btn_sh" class="btn_sh">���</a>
							<a href="javascript:void(0);" id="btn_qxsh" class="btn_sr">����</a>
							</li>
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">���̸���</a></li>
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
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>�����춯����б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
