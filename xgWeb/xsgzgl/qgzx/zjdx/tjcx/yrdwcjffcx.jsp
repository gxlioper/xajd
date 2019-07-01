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
				url : "tjcx_zjdx.do?method=getYrdwFfTj&type=query",
				colList : [ {
					label : '���˵�λ',
					name : 'bmmc',
					index : 'bmmc',
					width : '10%'
				}, {
					label : '�˴�',
					name : 'janrc',
					index : 'janrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'janje',
					index : 'janje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'febrc',
					index : 'febrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'febje',
					index : 'febje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'marchrc',
					index : 'marchrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'marchje',
					index : 'marchje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'aprrc',
					index : 'aprrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'aprje',
					index : 'aprje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'mayrc',
					index : 'mayrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'mayje',
					index : 'mayje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'junrc',
					index : 'junrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'junje',
					index : 'junje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'julrc',
					index : 'julrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'julje',
					index : 'julje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'augrc',
					index : 'augrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'augje',
					index : 'augje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'seprc',
					index : 'seprc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'sepje',
					index : 'sepje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'octrc',
					index : 'octrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'octje',
					index : 'octje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'novrc',
					index : 'novrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'novje',
					index : 'novje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'decmrc',
					index : 'decmrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'decmje',
					index : 'decmje',
					width : '5%'
				}, {
					label : '�˴�',
					name : 'rowrc',
					index : 'rowrc',
					width : '5%'
				},{
					label : '���Ž��',
					name : 'rowje',
					index : 'rowje',
					width : '5%'
				}],
				multiselect:false,
				usedefined : true,
				uselastrow:true,
				uselastid:"dataTable1",
				usercols:27,
				useurl:"tjcx_zjdx.do?method=getHjSum"
			}
		   
			var map = {};
			map['nd']=jQuery('#nd').val();
			map['bmlb']=jQuery('#bmlb').val();
			map['yrdwdm']=jQuery('#yrdwdm').val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
			
			
		});
		function doQuery(){
			var map = {};
			map['nd']=jQuery('#nd').val();
			map['bmlb']=jQuery('#bmlb').val();
			map['yrdwdm']=jQuery('#yrdwdm').val();
			jQuery("#dataTable").reloadGrid(map);
			jQuery("#dataTable > tbody").append(jQuery("#test").html());
			
		}	

		/**
		 * ����
		 * @return
		 */
		function jgExportData() {
			var url = "tjcx_zjdx.do?method=exportDataYrdwFfTj";
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//��������л�		
		function bmlbchange(){
			var url = "tjcx_zjdx.do?method=bmlbChange";
			var data = null;
			jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:'bmlb='+jQuery("#bmlb").val(),
			async: false,
			success:function(result){
				data = result
			}
			
		  });
		   jQuery("#yrdwdm").empty();
		   var html = "<option value=''></option>";
		   for(var i=0;i < data.length;i++){
			   html += "<option value='"+data[i]["bmdm"]+"'>"+data[i]["bmmc"]+"</option>";
		   }
		   jQuery("#yrdwdm").append(html);
		}
				
		</script>
		<style type="text/css">
			.jz{text-align:center}
		</style>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<div class="prompt" id="div_help">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				���·ݡ���ѯ����ֻ��Ե�����Ч��
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/tjcx_zjdx">
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
						<td width="10%">
							<html:select property="nd" styleId="nd" onchange="doQuery()">
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
							</html:select>
						</td>
						<th width="5%">�·�</th>
						<td width="10%">
							<html:select property="yf" styleId="yf">
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
						</td>
						<th width="5%">�������</th>
						<td  width="10%">
							<select id="bmlb" name="bmlb" onchange="bmlbchange();doQuery()">
								<option value=""></option>
								<option value="1">У��</option>
								<option value="5">Ժ��</option>
							</select>
						</td>
						<th width="5%">���˵�λ</th>
						<td width="10%">
							<html:select styleId="yrdwdm" property="yrdwdm" style="width:98%" onchange="doQuery()">
								<html:option value=""></html:option>
								<html:options collection="yrbmList" property="bmdm" labelProperty="bmmc"/>
							</html:select>
						</td>
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
				<span>���˵�λ���귢��ͳ��&nbsp;&nbsp;<font color="blue">��λ(Ԫ)</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" >
				<thead>
					<tr class="nowrap">
						<th width="10%" style="text-align:center" sortname="bmmc" rowspan = "2" >���˵�λ</th>
						<th width="10%" style="text-align:center" colspan = "2">1��</th>
						<th width="10%" style="text-align:center" colspan = "2">2��</th>
						<th width="10%" style="text-align:center" colspan = "2">3��</th>
						<th width="10%" style="text-align:center" colspan = "2">4��</th>
						<th width="10%" style="text-align:center" colspan = "2">5��</th>
						<th width="10%" style="text-align:center" colspan = "2">6��</th>
						<th width="10%" style="text-align:center" colspan = "2">7��</th>
						<th width="10%" style="text-align:center" colspan = "2">8��</th>
						<th width="10%" style="text-align:center" colspan = "2">9��</th>
						<th width="10%" style="text-align:center" colspan = "2">10��</th>
						<th width="10%" style="text-align:center" colspan = "2">11��</th>
						<th width="10%" style="text-align:center" colspan = "2">12��</th>
						<th width="10%" style="text-align:center" colspan = "2">�ϼ�</th>
					</tr>
					<tr>
						<th width="5%" sortname="janrc" >�˴�</th>
						<th width="5%" sortname="janje" >���Ž��</th>
						<th width="5%" sortname="febrc" >�˴�</th>
						<th width="5%" sortname="febje" >���Ž��</th>
						<th width="5%" sortname="marchrc" >�˴�</th>
						<th width="5%" sortname="marchje" >���Ž��</th>
						<th width="5%" sortname="aprrc" >�˴�</th>
						<th width="5%" sortname="aprje" >���Ž��</th>
						<th width="5%" sortname="mayrc" >�˴�</th>
						<th width="5%" sortname="mayje" >���Ž��</th>
						<th width="5%" sortname="junrc" >�˴�</th>
						<th width="5%" sortname="junje" >���Ž��</th>
						<th width="5%" sortname="julrc" >�˴�</th>
						<th width="5%" sortname="julje" >���Ž��</th>
						<th width="5%" sortname="augrc" >�˴�</th>
						<th width="5%" sortname="augje" >���Ž��</th>
						<th width="5%" sortname="seprc" >�˴�</th>
						<th width="5%" sortname="sepje" >���Ž��</th>
						<th width="5%" sortname="octrc" >�˴�</th>
						<th width="5%" sortname="octje" >���Ž��</th>
						<th width="5%" sortname="novrc" >�˴�</th>
						<th width="5%" sortname="novje" >���Ž��</th>
						<th width="5%" sortname="decmrc" >�˴�</th>
						<th width="5%" sortname="decmje" >���Ž��</th>
						<th width="5%" sortname="rowrc" >�˴�</th>
						<th width="5%" sortname="rowje" >���Ž��</th>
					</tr>
				</thead>
				</table>
				<div style="display:none">
				<table  class="dateline" >
					<tbody id="dataTable1">
					
					<tr >
								<td style="word-break:break-all;" width="10%"><font color="red" style="font-weight:bold">�ܼ�</font></td>
								<td style="word-break:break-all;" width="5%" name="janrc">${hj.janrc}</td>
								<td style="word-break:break-all;" width="5%" name="janje">${hj.janje}</td>
								<td style="word-break:break-all;" width="5%" name="febrc">${hj.febrc}</td>
								<td style="word-break:break-all;" width="5%" name="febje">${hj.febje}</td>
								<td style="word-break:break-all;" width="5%" name="marchrc">${hj.marchrc}</td>
								<td style="word-break:break-all;" width="5%" name="marchje">${hj.marchje}</td>
								<td style="word-break:break-all;" width="5%" name="aprrc">${hj.aprrc}</td>
								<td style="word-break:break-all;" width="5%" name="aprje">${hj.aprje}</td>
								<td style="word-break:break-all;" width="5%" name="mayrc">${hj.mayrc}</td>
								<td style="word-break:break-all;" width="5%" name="mayje">${hj.mayje}</td>
								<td style="word-break:break-all;" width="5%" name="junrc">${hj.junrc}</td>
								<td style="word-break:break-all;" width="5%" name="junje">${hj.junje}</td>
								<td style="word-break:break-all;" width="5%" name="julrc">${hj.julrc}</td>
								<td style="word-break:break-all;" width="5%" name="julje">${hj.julje}</td>
								<td style="word-break:break-all;" width="5%" name="augrc">${hj.augrc}</td>
								<td style="word-break:break-all;" width="5%" name="augje">${hj.augje}</td>
								<td style="word-break:break-all;" width="5%" name="seprc">${hj.seprc}</td>
								<td style="word-break:break-all;" width="5%" name="sepje">${hj.sepje}</td>
								<td style="word-break:break-all;" width="5%" name="octrc">${hj.octrc}</td>
								<td style="word-break:break-all;" width="5%" name="octje">${hj.octje}</td>
								<td style="word-break:break-all;" width="5%" name="novrc">${hj.novrc}</td>
								<td style="word-break:break-all;" width="5%" name="novje">${hj.novje}</td>
								<td style="word-break:break-all;" width="5%" name="decmrc">${hj.decmrc}</td>
								<td style="word-break:break-all;" width="5%" name="decmje">${hj.decmje}</td>
								<td style="word-break:break-all;" width="5%" name="rowrc">${hj.rowrc}</td>
								<td style="word-break:break-all;" width="5%" name="rowje">${hj.rowje}</td>
							</tr>
						</tbody>
				</table>
					</div>
				</div>
				<div id="pager"></div>
			
		</div>
	</body>
</html>
