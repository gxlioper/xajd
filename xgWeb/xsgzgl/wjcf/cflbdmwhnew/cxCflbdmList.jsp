<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript"
			src="xsgzgl/wjcf/cflbdmwhnew/js/cflbdm.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"���������Ϣ�б�",
				pager:"pager",
				url:"wjcf_cflbdmwh.do?method=cxCflbdmList&type=query",
				colList:[
			       {label:'pk',name:'cflbdm', index: 'cflbdm',key:true,hidden:true},
				   {label:'����',name:'cflbmc', index: 'cflbmc',width:'10%'},
				   {label:'�������',name:'spl', index: 'spl',width:'12%'},
				   {label:'�Ƿ������',name:'sfkss', index: 'sfkss',width:'12%'},
				    <logic:equal name="xxdm" value="14073">
				    	{label:'��������ʱ��',name:'ssslgzr', index: 'ssslgzr',width:'8%'},
				    </logic:equal>
				    <logic:notEqual name="xxdm" value="14073">
				   		{label:'���߽�ֹ��',name:'ssslgzr', index: 'ssslgzr',width:'8%'},
				    </logic:notEqual>
				   	<logic:equal name="xxdm" value="14073">
				   		{label:'�Ƿ������<bean:message key="wjcf.text" />',name:'sfksqjc', index: 'sfksqjc',width:'18%',formatter:format},
				    </logic:equal>
				   	<logic:notEqual name="xxdm" value="14073">	    
				   		{label:'�Ƿ��<bean:message key="wjcf.text" />',name:'sfksqjc', index: 'sfksqjc',width:'18%',formatter:format},
				   	</logic:notEqual>
				    <logic:equal name="xxdm" value="14073">
				    	{label:'<bean:message key="wjcf.text" />����ʱ��',name:'jsslqsr', index: 'jsslqsr',width:'8%'},
				    </logic:equal>
				    <logic:notEqual name="xxdm" value="14073">
				    	{label:'<bean:message key="wjcf.text" />��ʼ��',name:'jsslqsr', index: 'jsslqsr',width:'8%'},
				    </logic:notEqual>
				   
				   {label:'�Ƿ��д�������',name:'cfqxflag', index: 'cfqxflag',width:'6%'},
                    {label:'����ʱ��',name:'cjsj', index: 'cjsj',width:'6%'},
				],
				sortname: "cjsj",
			 	sortorder: "asc"
			};
			function format(cellValue, rowObject){
				var sfksqjc = rowObject["sfksqjc"];
				if(!sfksqjc){
					return "";
				}
				var sfksq=sfksqjc.split(",");
				var text="";
				var i=0;

				if("14073" == jQuery("#xxdm").val()) {
					for(var str in sfksq){
						if(sfksq[str]=="no"){
							text+='��������';
						}else if(sfksq[str]=="xs"){
							text+='ѧ��������';
						}else if(sfksq[str]=="js"){
							text+='��ʦ������';
						}
						if(parseInt(str)+1!=sfksq.length){
							text+=",";
						}
					}
				} else {
					for(var str in sfksq){
						if(sfksq[str]=="no"){
							text+='����<bean:message key="wjcf.text" />';
						}else if(sfksq[str]=="xs"){
							text+='ѧ����<bean:message key="wjcf.text" />';
						}else if(sfksq[str]=="js"){
							text+='��ʦ��<bean:message key="wjcf.text" />';
						}
						if(parseInt(str)+1!=sfksq.length){
							text+=",";
						}
					}
				}
				return text;
			}
			function dcmcLink(cellValue, rowObject) {
				var qjsqid = rowObject["qjsqid"];
				return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
						+ "')\" class='name'>" + cellValue + "</a>";
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["cflbmc"] = jQuery("#cflbmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" id="text"
			value="<bean:message key="wjcf.text" />" />
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal value="yes" name="writeAble">
			<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);"
								onclick="showDialog('���Ӵ������',450,400,'wjcf_cflbdmwh.do?method=cflbdmAdd');"
								class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a>
						</li>
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">
								�����������
							</th>
							<td>
								<input type="text" id="cflbmc"
									onkeypress="if(event.keyCode==13){query();}" />
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>


		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ���������Ϣ�б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
