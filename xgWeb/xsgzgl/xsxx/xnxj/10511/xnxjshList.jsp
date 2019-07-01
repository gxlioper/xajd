<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xnxj/10511/js/xnxj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ��С������б�",
				pager:"pager",
				url:"xsxx_xnxj_xjtxgl.do?method=viewXnxjshList&type=query&shQryType=D",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'11%'  , formatter:xhLink1},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'13%'},
				   {label:'��дʱ��',name:'txsj', index: 'txsj',width:'9%'},
				   {label:'splid',name:'splid', index: 'splid',hidden:true},
				   {label:'shid',name:'shid', index: 'shid',hidden:true},
				   {label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
				   {label:'���״̬',name:'shztmc', index: 'shzt',width:'13%'}
				],
				sortname: "txsj",
			 	sortorder: "desc"
			}


			var gridSetting2 = {
				caption:"ѧ��С������б�",
				pager:"pager",
				url:"xsxx_xnxj_xjtxgl.do?method=viewXnxjshList&type=query&shQryType=Y",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'11%' , formatter:xhLink1},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'13%'},
				   {label:'��дʱ��',name:'txsj', index: 'txsj',width:'9%'},
				   {label:'���ʱ��',name:'shsj', index: 'shsj',width:'15%'},
				   {label:'splid',name:'splid', index: 'splid',hidden:true},
				   {label:'shid',name:'shid', index: 'shid',hidden:true},
				   {label:'xtgwid',name:'xtgwid', index: 'xtgwid',hidden:true},
				   {label:'���״̬',name:'shztmc', index: 'shzt',width:'13%'}
				   
				],
				sortname: "shsj",
			 	sortorder: "desc"
			}
			
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);	
			});

			function searchRs(){
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
				var onclickfn = "onclick=\"" + "showDialog('ѧ����ϸ��Ϣ' , 720 , 350 , 'xsxx_xnxj_xjtxgl.do?method=xnxjjdck&xh=" + cellValue + "&xn=" + rowObject['xn'] + "')" + "\"";
				
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
				
				return el;
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
		<html:form action="/xsxx_xnxj_xjtxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="xnxjSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelXnxjSh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_sr">����</a>
						</li>			
						</logic:equal>			
						<li><a href="javascript:void(0);" onclick="xnxjLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
							   
						<%--<li><a href="javascript:void(0);" onclick="knsrdShqk();return false;" 
							   title="����鿴����������ͳ�ơ�"
							   class="btn_tj">���ͳ��</a></li>--%>						
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
				<span>ѧ��С�������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
