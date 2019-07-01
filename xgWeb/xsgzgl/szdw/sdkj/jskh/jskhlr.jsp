<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"����Ա����¼���б�",
				pager:"pager",
				url:"sdkj_zdjskh.do?method=zdjskh&type=query",
				colList:[
				   {label:'���',name:'nf', index: 'nf',width:'30%'},
				   {label:'�·�',name:'yf', index: 'yf',width:'30%'},
				   {label:'������',name:'zrs', index: 'zrs',width:'20%'},
				   {label:'��¼������',name:'rs', index: 'rs',width:'20%'}
				],
				sortname: "yf",
			 	sortorder: "asc"
			}

			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["nf"] = jQuery("#nf").val();
				map["yf"] = jQuery("#yf").val();

				jQuery("#dataTable").reloadGrid(map);
			}
			function impAndChkDataa(){
				var realTable = "";
				var tableName = "";
				var nf="";
				var yf="";
				var sty = "toolbar=no,location=no,directories=no,status=yes";
				sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=400,top=100";
				sty += ",left=200";
				if($("realTable")) realTable = document.getElementById("realTable").value;
				if($("tableName")) tableName = document.getElementById("tableName").value;
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
					return false;
				} else {
					nf=rows[0]["nf"]
					yf=rows[0]["yf"];
				}
				url = 'impAndChkData.do?method=importDataSdkj';
				url += "&realTable=" + realTable;
				url += "&tableName=" + tableName;
				url += "&nf=" + nf;
				url += "&yf=" + yf;
				//showTopWin(url,600,500);
				//refreshForm(url);
				window.open(url,'',sty);
			}
			function ckxx(){
				var nf="";
				var yf="";
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
					return false;
				} else {
					nf=rows[0]["nf"]
					yf=rows[0]["yf"];
				}
				var url="sdkj_zdjskh.do?method=ckxx";
				url += "&nf=" + nf;
				url += "&yf=" + yf;
				refreshForm(url);
				
			}


			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/sdkj_zdjskh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="ckxx()" class="btn_ck">�鿴</a></li>
						<li><a href="#" onclick="impAndChkDataa();return false;" class="btn_dr">����</a></li>
					</ul>
				</div>
				<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
										�� ѯ
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset()">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>���</th>
							<td>
								<html:select name="map" property="nf" styleId="nf">
									<html:options collection="nfList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
							<th>�·�</th>
							<td>
								<html:select name="map" property="yf" styleId="yf">
									<html:option value=""></html:option>
									<html:options collection="yfList" property="yf" labelProperty="yf"/>
								</html:select>
							</td>
							<th></th>
							<td>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
				

			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ָ����ʦ����¼����Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
