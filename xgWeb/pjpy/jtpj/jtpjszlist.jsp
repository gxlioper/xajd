<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/js/jtpjsz.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var sqzq=jQuery("#sqzq").val();
				var sqxn=sqzq.split(",")[0];
				var sqxq=sqzq.split(",")[1];
			     var gridSetting =  {
						pager:"pager",
						url:"jtpjsz.do?method=list&type=query",
						colList:[
						   {label:'��������id',name:'jxid', index: 'jxid',key:true,hidden:true},
						   {label:'�������� ',name:'jxmc', index: 'jxmc',formatter:jxmc},
						   {label:'������λ',name:'jtpjdw', index: 'jtpjdw',formatter:pjdwmc},
						   {label:'����ѧ��',name:'pdxn', index: 'pdxn',hidden:true},
						   {label:'����ѧ������',name:'pdxqmc', index: 'pdxqmc',hidden:true},
						   {label:'������������',name:'jxpdzq', index: 'pdxn,pdxq',formatter:jxpdzq},
						   {label:'��������',name:'tjsz', index: 'tjsz',formatter:setTjsz},
						   {label:'���뿪��',name:'sfkfsq', index: 'sfkfsq',formatter:sqkg},
						   {label:'���뿪��ʱ��',name:'sqqzsj', index: 'sqqzsj'},
						   {label:'�������',name:'lcxx', index: 'lcxx',hidden:true}
						],
						params:{sqxn:sqxn,sqxq:sqxq},
						sortname: "jxmc",
					 	sortorder: "asc"
					}
					jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function djbsz(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("��ѡ����Ҫ���õǼǱ����Ŀ��");
				} else {
					document.location.href="jtpjsz.do?method=bgylList&jxid="+rows[0]["jxid"];
				}
			}
		</script>
	</head>
	<body>
		<input id="xbmc" type="hidden" value='<bean:message key="lable.xb" />'/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/jtpjsz.do?method=list&type=query">
		<div class="toolbox">
			<logic:equal name="writeAble" value="yes">	
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="copy();return false;" class="btn_tj">����</a>
						</li>
						<logic:notEqual name="xxdm" value="11458">	
						<logic:notEqual name="xxdm" value="11067">	
						<li><a href="javascript:void(0);" onclick="djbsz();" class="btn_sz">�ǼǱ�����</a></li>	
						</logic:notEqual>
						</logic:notEqual>
					</ul>
				</div>
			</logic:equal>
		</div>
					<!-- �������� -->
			<div class="searchtab">
			<input type="hidden" id="iscanCopy" value="${iscanCopy}"/> 
				<table width="100%" border="0">
					<tr>
						<th width="10%">��������</th>
						<td>
							<html:select property="jxsm" styleId="sqzq"	value="${sqzq}">
								<html:options collection="sqzqList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<th width="10%">��������</th>
						<td>
							<input type="text" id="jxmc" name="jxmc" maxlength="20" />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">��������������Ϣ</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
