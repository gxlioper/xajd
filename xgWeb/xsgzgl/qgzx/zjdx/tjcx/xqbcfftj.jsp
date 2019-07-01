<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/zjdx/tjcx/js/tjcx.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "tjcx_zjdx.do?method=getXqbcFfTj&type=query",
				colList : [ {
					label : 'У��',
					name : 'xqmc',
					index : 'xqmc',
					width : '40%'
				}, {
					label : '�ڹ���ѧ�˴�',
					name : 'rc',
					index : 'rc',
					width : '30%'
				},{
					label : '����ܼ�(Ԫ)',
					name : 'bcje',
					index : 'bcje',
					width : '30%'
				}],
				multiselect:false, 
				uselastrow:true,
				usercols:3,
				uselastid:"dataTable1",
				useurl:"tjcx_zjdx.do?method=getXqSum"
			}
			var map = {};
			map['nd']=jQuery('#nd').val();
			map['yf']=jQuery('#yf').val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function doQuery(){
			var map = {};
			map['nd']=jQuery('#nd').val();
			map['yf']=jQuery('#yf').val();
			jQuery("#dataTable").reloadGrid(map);
		}	

		/**
		 * ����
		 * @return
		 */
		function jgExportData() {
			var url = "tjcx_zjdx.do?method=exportDataXqbcFfTj";//dcclbh,�������ܱ��
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
				
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/tjcx_zjdx" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<div class="toolbox">
				<logic:equal value="true" name="sfqggly">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="jgExportData();return false" class="btn_dc" >����</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="5%">���</th>
						<td width="15%">
							<html:select property="nd" styleId="nd" onchange="doQuery()">
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
							</html:select>
						</td>
						<th width="5%">�·�</th>
						<td width="15%">
							<html:select property="yf" styleId="yf" onchange="doQuery()">
								<html:option value="">ȫ��</html:option>
								<html:option value="01">01</html:option>
								<html:option value="02">02</html:option>
								<html:option value="03">03</html:option>
								<html:option value="04">04</html:option>
								<html:option value="05">05</html:option>
								<html:option value="06">06</html:option>
								<html:option value="07">07</html:option>
								<html:option value="08">08</html:option>
								<html:option value="09">09</html:option>
								<html:option value="10">10</html:option>
								<html:option value="11">11</html:option>
								<html:option value="12">12</html:option>
							</html:select>
						<td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go"  onclick="doQuery();">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��У�����귢��ͳ��&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div style="display:none">
				<table  class="dateline" >
					<tbody id="dataTable1">
					
					       <tr >
								<td style="word-break:break-all;" width="40%"><font color="red" style="font-weight:bold">�ܼ�</font></td>
								<td style="word-break:break-all;" width="30%" name="rc"></td>
								<td style="word-break:break-all;" width="30%" name="bcje"></td>
							</tr>
						</tbody>
				</table>
					</div>
			</div>
		</div>
	</body>
</html>
