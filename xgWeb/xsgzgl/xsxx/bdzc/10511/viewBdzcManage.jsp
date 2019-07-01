<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bdzc/10511/js/bdzc.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ��<bean:message key="lable.bdzc"/>�б�",
				pager:"pager",
				url:"xsxx_xqbdzcgl.do?method=viewBdzcManage&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'ѧ��',name:'xn', index: 'xn',width:'12%'},
					{label:'ѧ��',name:'xqmc', index: 'xqmc',width:'3%'},
					{label:'ѧ��',name:'xh', index: 'xh',width:'12%' , formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'8%'},
					{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
					{label:'�꼶',name:'nj', index: 'nj',width:'7%'},
					{label:'�༶',name:'bjmc', index: 'bjdm',width:'18%'},
					{label:'<bean:message key="lable.bdzc"/>״̬',name:'zcztmc', index: 'zcztmc',width:'8%'},
					{label:'zczt',name:'zczt', index: 'zczt',width:'16%' , hidden:true},
					{label:'xq',name:'xq', index: 'xq',width:'16%' , hidden:true},
					{label:'δע�����',name:'wbdlbmc', index: 'wbdlbmc',width:'16%' },
					{label:'<bean:message key="lable.bdzc"/>ʱ��', name:'zcsj', index: 'zcsj',width:'15%'}
				],
				sortname: "xn,xq",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});


			/**
			 * ѧ������
			 * @param cellValue
			 * @param rowObject
			 * @return
			 */

			function xhLink(cellValue,rowObject){
				//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
				var onclickfn = "onclick=\"" + "showDialog('ѧ����ϸ��Ϣ' , 720 , 480 , 'xsxx_xqbdzcgl.do?method=ckXqbdzc&xh=" + cellValue + "&xq=" + rowObject['xq']+ "&xn=" + rowObject['xn'] + "')" + "\"";
				//var url = "xsxx_xqbdzcgl.do?method=ckXqbdzc&xh=" + xh + "&xn=" + xn + "&xq=" + xq;
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
				
				return el;
			}

<!--			function setWbdlbdm(cellValue,rowObject){-->
<!--				if(null!=cellValue){-->
<!--				return "<span title='"+rowObject["setWbdlbdm"]+"'>"+cellValue+"</span>";-->
<!--				}-->
<!--			}-->

			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjjg.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					var url="xpj_pjjg.do?method=getDjbWord&id="+rows[0]["id"];
					window.open(url);
			     }
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
					������ѡ�κ�ѧ��ʱ���Է���ѡ�в�ѯ������ѧ������ѧ������<bean:message key="lable.bdzc"/>,<br/>
					����ѡѧ��ʱ����ѡ�е�ѧ������ѧ������<bean:message key="lable.bdzc"/>��
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/xsxx_xqbdzcgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="searchXn" />
			<html:hidden property="searchXq" />
			<html:hidden property="xn" styleId="xn"/>
			<html:hidden property="xq" styleId="xq"/>
			<input type="hidden" name="zckg" id="zckg" value="${zckg}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul><%--
						<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="dgzc('<bean:message key="lable.bdzc"/>');" class="btn_zj">����<bean:message key="lable.bdzc"/></a></li>
						</logic:equal>
						--%>
						<logic:notEqual value="stu" scope="session" name="userType">
							<logic:equal name="writeAble" value="yes">	
								<li><a href="javascript:void(0);" onclick="plzc('<bean:message key="lable.bdzc"/>');" class="btn_plqy"><bean:message key="lable.bdzc"/></a></li>
								<li><a href="javascript:void(0);" onclick="plcx('<bean:message key="lable.bdzc"/>');" class="btn_sc">����<bean:message key="lable.bdzc"/></a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						</logic:notEqual>
						<li><a href="#" onclick="ckzc('<bean:message key="lable.bdzc"/>');return false;" id="btn_ck" class="btn_ck">�鿴</a></li>
						<logic:notEqual value="stu" scope="session" name="userType">
							<logic:equal name="writeAble" value="yes">	
								<li><a href="javascript:void(0);" onclick="wbdyywh('<bean:message key="lable.bdzc"/>');" class="btn_xg">δ<bean:message key="lable.bdzc"/>ԭ��ά��</a></li>
							</logic:equal>
						</logic:notEqual>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ��<bean:message key="lable.bdzc"/>�б�</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
