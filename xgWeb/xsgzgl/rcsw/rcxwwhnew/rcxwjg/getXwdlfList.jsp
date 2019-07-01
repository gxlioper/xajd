<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		function getGridSettiong(){
			var gridSetting = {
				caption:"�ճ���Ϊ����ͳ�ƽ��",
				pager:"pager",
				url:"rcsw_rcxwwhnew_rcxwjggl.do?method=getXwdlfList&type=query",
				params:getSuperSearch(),
				multiselect:false
			};
			if("0"==jQuery("#zq").val()){
				var colList=[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'13%'},
				   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
                   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'12%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'12%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'}
				];
			}else{
				var colList=[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'13%'},
                   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'12%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'12%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'}
							];
				}
				
			var xwdl = jQuery("font[name=xwdl]");
			jQuery.each(xwdl,function(i,n){
				var xwdlJson = {
						label:jQuery(n).attr("xwmc"),
						name:"fz"+i,
						index:"fz"+i
				};
				colList.push(xwdlJson);
			});
			gridSetting["colList"] = colList;
			return gridSetting;				
		
			}

		function searchRs(){
			var xnLength=jQuery("a[name=a_name_xn]").length;
			if(xnLength != "1"){
				showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
				return false;
			}
			jQuery.ajaxSetup({async:false});
			var gridSetting = getGridSettiong();
			var map = getSuperSearch();
			gridSetting.params = map;
			//jQuery("#dataTable").reloadGrid(map);
			jQuery("#dataTable").initGrid(gridSetting);
			jQuery.ajaxSetup({async:true});
		}
		jQuery(function(){
			jQuery.ajaxSetup({async:false});
			var gridSetting = getGridSettiong();
			var map = getSuperSearch();
			gridSetting.params = map;
			jQuery("#dataTable").initGrid(gridSetting);
			jQuery.ajaxSetup({async:true});
		});
		//�ճ���Ϊ�����ֵܷ���
		function rcxwdlfDc() {
			var url ="rcsw_rcxwwhnew_rcxwjggl.do?method=rcxwdlfDc";
			var xnLength=jQuery("a[name=a_name_xn]").length;
			if(xnLength != "1"){
				showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
				return false;
			}
			setSearchTj();
			url = addSuperSearchParams(url);
			
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xwjgView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}
		function xwjgView(id, xh) {
			showDialog("�鿴�ճ���Ϊ���", 720, 520,
					"rcsw_rcxwwhnew_rcxwjggl.do?method=viewXwdljg&id=" + id + "&xh=" + xh);
		}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<logic:present name="xwlbList">
		<div id="xwdlDiv">
			<logic:iterate id="z" name="xwlbList">
				<font style="display:none;" xwdm="${z.rcxwlbdm }" xwmc="${z.rcxwlbmc }" name="xwdl"></font>
			</logic:iterate>
		</logic:present>
		<html:form action="/rcsw_rcxwwhnew_rcxwjggl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_rcxwjg"/>
			<input type="hidden" name="zq" id="zq" value="${zq}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="rcxwdlfDc();return false;">����</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�ճ���Ϊ����ͳ�ƽ��&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
