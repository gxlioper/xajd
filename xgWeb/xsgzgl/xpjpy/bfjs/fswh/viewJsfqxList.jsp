<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/bfjs/fswh/js/jsfs.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"���ύ�����ְ༶�б�",
				pager:"pager",
				url:"xpjpyBfjsFswh.do?method=viewJsfqxList&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'19%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'�༶',name:'bjdm', index: 'bjdm',hidden:true},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'23%'},
				   {label:'�ύ��',name:'tjr', index: 'tjr',width:'8',hidden:true},
				   {label:'�ύ��',name:'tjrxm', index: 'tjrxm',width:'10%'},
				   {label:'�ύʱ��',name:'tjsj', index: 'tjsj',width:'18%'}
				],
				sortname: "nj,xydm,zydm,bjdm",
			 	sortorder: "desc",
			 	radioselect:false
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//ȡ���ύ������״̬
			function cancelTj(){
				var id = jQuery("#dataTable").getSeletIds();
				if (id.length == 0){
					showAlertDivLayer("��ѡ����Ҫȡ���İ༶��");
					return false;
				} 
				showDialog("ȡ���ύ",400,250,"xpjpyBfjsFswh.do?method=cancelTj&id="+id);
			}
		</script>
	</head>

	<body>
	
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >ʹ�ð���</a>
			</p>
		</div>
		<html:form action="/xpjpyBfjsFswh" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						�������ύ�������ݵİ༶������Ա���ڴ˴�����<font color="red">ȡ���ύ</font>������ȡ���ύ�İ༶�������ݿ�<font color="red">���µ����޸�</font>
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<logic:equal value="true" name="cssz" property="kgzt">
						<li><a href="javascript:void(0);" onclick="cancelTj();" class="btn_xg">ȡ���ύ</a></li>
						</logic:equal>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>���ύ�༶ </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
