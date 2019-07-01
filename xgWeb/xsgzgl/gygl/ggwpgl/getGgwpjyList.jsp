<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ggwpgl/js/ggwpjy.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"xgygl_ggwpjydj.do?method=getGgwpjyList&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh', width:'10%', formatter:function(v,r){
					   return "<a class='name' href='javascript:view(\""+r["id"]+"\")'>"+v+"</a>";
				   }},
				   {label:'����',name:'xm', index: 'xm', width: '10%'},
				   {label:'ѧԺ',name:'xymc', index: 'xymc', width: '10%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc', width: '10%'},
				   {label:'������Ʒ����',name:'flmc', index: 'flmc', width: '10%'},
				   {label:'������Ʒ����',name:'sbmc', index: 'sbmc', width: '10%'},
				   {label:'����ʱ��',name:'jysj', index: 'jysj', width: '15%'},
				   {label:'�黹ʱ��',name:'ghsj', index: 'ghsj', width: '15%'},
				   {label:'�黹״̬',name:'ghztmc', index: 'ghztmc', width: '10%'},
				   {label:'ghzt',name:'ghzt', index: 'ghztmc',hidden:true}
				],
				sortname: "jysj",
			 	sortorder: "desc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/xgygl_ggwpjydj" method="post" styleId="form">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
					<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">�Ǽ�</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
					<li><a href="javascript:void(0);" onclick="ghsb();" class="btn_gx">�黹</a></li>						
					<li><a href="javascript:void(0);" onclick="dr();" class="btn_dr">����</a></li>	
					</logic:equal>					
					<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">����</a></li>						
				</ul>
			</div>
			<!-- �������� -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
		</div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ������Ʒ���ü�¼�б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
		
		<div id="sbgh" style="display:none;">
			<table class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�豸�黹</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="20%">
							�黹ʱ��
						</th>
						<td width="30%">
							<input type="text" value="${now }" readonly="readonly" name="ghsj" id="ghsj"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
							/>
						</td>
						<th align="right" width="20%">
							������
						</th>
						<td width="30%">
							${userNameReal }
							<input type="hidden" name="ghjbr" id="ghjbr" value="${userName }"/>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							�黹��ע <br/>
							<font color="red">����400�֣�</font>
						</th>
						<td colspan="3">
							<textarea rows="5" style="width:95%;" id="ghbz"
								onblur="checkLen(this,400);"
							></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
