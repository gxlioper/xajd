<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xnxj/10511/js/xnxj.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ��С�������",
				pager:"pager",
				url:"xsxx_xnxj_xjtxgl.do?method=viewXnxjList&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'11%' , formatter:xhLink1},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
				   {label:'splid',name:'splid', index: 'splid',hidden:true},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
				   {label:'��дʱ��',name:'txsj', index: 'txsj',width:'9%'},
				   {label:'txzt',name:'txzt', index: 'txzt',hidden:true},
				   {label:'��д״̬',name:'txztmc', index: 'txztmc',width:'8%'},
				   {label:'shjg',name:'shjg', index: 'shjg',hidden:true},
				   {label:'���״̬',name:'shztmc', index: 'shzt',width:'6%'}
				],
				sortname: "txsj",
			 	sortorder: "desc"
			}
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
				
			});

			function searchRs(){
				var search_xn = jQuery("a[name='a_name_xn']");
				
				if(search_xn.length != 1){
					if(jQuery('#userType').val() != 'stu'){
						showAlertDivLayer("��ѡ��һ��ѧ�꣡",{},{});
						return false;
						}
					
				}
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			/**
			 * ѧ������
			 * @param cellValue
			 * @param rowObject
			 * @return
			 */

			function xhLink1(cellValue,rowObject){
				//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
//				var onclickfn = "onclick=\"" + "showDialog('ѧ����ϸ��Ϣ' , 720 , 350 , 'xsxx_xnxj_xjtxgl.do?method=xnxjjdck&xh=" + cellValue + "')" + "\"";
				var onclickfn = "onclick=\"" + "showDialog('ѧ����ϸ��Ϣ' , 720 , 350 , 'xsxx_xnxj_xjtxgl.do?method=xnxjjdck&xh=" + cellValue + "&xn=" + rowObject['xn'] + "')" + "\"";
				
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
				
				return el;
			}
		</script>
	</head>

	<body>
		<input type="hidden" name="kgzt" id="kgzt" value="${jcszModel.kg }"/>
		
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
					ȡ�����룺����ȡ����δ��ˡ���ѧ��С����д
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/xszz_jtqkdc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="xnxjsq();return false;"  title="����ð�ť�����������дҳ�档">��дС��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xnxjUpdate();return false;" class="btn_xg" title="ѡ��һ����¼������ð�ť���޸������">�޸�С��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xnxjDelete();return false;" class="btn_sc" title="ֻ��ȡ��δ��˵ļ�¼���Ѿ�����˵Ĳ���ȡ����" >ɾ��С��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="xnxjLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a>
						</li>		
						<li><a href="javascript:void(0);" onclick="printXnxjSq('xsxx_xnxj_xjtxgl.do?method=doPrint');return false;" class="btn_down">����С���</a></li>						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ��С�������&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
