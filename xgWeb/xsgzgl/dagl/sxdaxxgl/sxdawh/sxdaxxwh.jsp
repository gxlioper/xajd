<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dagl/sxdaxxgl/js/sxdaxxgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "ѧ���б�",
				pager : "pager",
				url : "sxDaxxgl.do?method=sxdaxxwh&type=query",
				colList : [ {
					label : 'key',
					name : 'xsid',
					index : 'xsid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '10%'
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '6%'
				},{
					label : '��Դ��',
					name : 'syd',
					index : 'syd',
					width : '6%'
				},{
					label : '��ݵ���',
					name : 'kddh',
					index : 'kddh',
					width : '15%',
					formatter : dhLink
				},{
					label : '�ʼĵ�ַ',
					name : 'yjdz',
					index : 'yjdz',
					width : '15%',
					formatter : dzLink
				}, {
					label : '�ʼ���',
					name : 'yjr',
					index : 'yjr',
					width : '6%',
					formatter : yjrLink
				}, {
					label : 'ʱ��',
					name : 'sj',
					index : 'sj',
					width : '10%',
					formatter : sjLink
				}, {
					label : '��ע',
					name : 'bz',
					index : 'bz',
					width : '10%',
					formatter : bzLink
				},{
					label : '�༶',
					name : 'bjdm',
					index : 'bjdm',
					hidden : true
				}],
				sortname: "ywh,xh",
		 		sortorder: "asc",
				multiselect:false//������ѡ
			}
			var map = getSuperSearch();
			map['bjdms']=jQuery("#bjdms").val();
			map['xh']=jQuery('#xh').val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
			
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="bjdms" id="bjdms" value="${bjdms}"/>
		<input type="hidden" name="bjid" id="bjid" value="${bjid}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/sxDaxxgl" onsubmit="return false;" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
                             <a href="javascript:void(0);" onclick="document.location.href='sxdaxxgl.do';" class="btn_fh">����</a>	</li>
					</ul>
				
					
				
				</div>
				<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">ѧ�� / ����</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<th width="12%">ά��״̬</th>
						<td>
								<select id="ywh" name="ywh"  style="width:100px">
								<option value=''></option>
								<option value='0'>��ά��</option>
								<option value='1'>δά��</option>
								<select>
							</td>
						<td>
							<div class="btn">
								<button type="button"  class="btn_cx" id="search_go" onclick="doQuery();">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
				<!-- �������� -->	
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
					<html:select property="pj" styleId="pjhtml" >
						<html:option value=""></html:option>
<%--						<html:options collection="pylist" property="cxdjdm" labelProperty="cxdjmc"/>--%>
					</html:select>
				</div>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>
					<font color = "blue">${xymc}&nbsp;&nbsp;${zymc}&nbsp;&nbsp;${nj}&nbsp;&nbsp;${bjmc}</font> 
						ѧ���б�&nbsp;&nbsp;��ά����<font color = "blue" id="ywhs">${ywh}</font>��&nbsp;&nbsp;
						δά����<font color = "blue" id="wwhs">${wwh}</font>��
				</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
