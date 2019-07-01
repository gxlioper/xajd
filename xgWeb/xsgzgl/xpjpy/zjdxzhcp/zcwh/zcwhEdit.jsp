<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxzhcp/zcwh/js/zcwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var djList = <%=request.getAttribute("djList")%>;
			var jsonStr = jQuery("#jsonStr").val();
			var map = JSON.parse(jsonStr);
			map["id"] = jQuery("#id").val();
			map["xn"] = jQuery("#xn").val();
			
			var gridSetting = {
				caption:"����ѧ���б� ",
				pager:"pager",
				url:"xpjpy_zcwh.do?method=zcwhEdit&doType=query&editType=edit&id="+jQuery("#id").val() + "&zcxmdmForTop=" + jQuery("#zcxmdmForTop").val(),
				params:map
			};
				
			var zcxm = jQuery("font[name=zcxm]");
			
			var colList=[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'11%'},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'12%'},
				   {label:'�ύ״̬',name:'tjzt', index: 'tjzt',hidden:true}
				];

			jQuery.each(zcxm,function(i,n){
				var json = {label:jQuery(n).attr("xmmc"),
							name:"fs"+i,
							index:"fs"+i
							};
				/*ԭ�������۲���Ŀ��Ŀ����  1���ӷ֡�2�����֡�3��Ĭ�Ϸ֡�4���ȼ�*/
				/*ԭ�������۲���Ŀ��Ŀ����  0���ȼ���1����ֵ*/
				/*������жϲ�ҪҲ�У�ʵ��ûʲô��*/
				if (jQuery(n).attr("xmlx") != "3" && jQuery(n).attr("jktb") ==""){
					
					json["formatter"] = function(cell,rowObject){
						var html="";
						
						if(rowObject["tjzt"]=="1"){
							if(jQuery(n).attr("xmlx") == "�ȼ�"){
								jQuery.each(djList,function(i,dj){
									if(cell == dj['dm']&&jQuery(n).attr("xmmc")==dj['xmmc']){
										html+=dj['mc'];
									}
								});
							}else{
								html+=cell==null ? "" : cell;						
							}
						}else if(jQuery(n).attr("xmlx") == "�ȼ�"){
							html+="<select onclick=\"saveZcfs(this,'"+jQuery(n).attr("xmdm")+"','"+rowObject["xh"]+"')\">";
							html+="<option value=''></option>";
							jQuery.each(djList,function(i,dj){
								if(jQuery(n).attr("xmmc")==dj['xmmc']){
									var option = "<option value='" + dj['dm'] + "'";
									if(cell == dj['dm']){
										option+=" selected ";
									}
									option+= ">" + dj['mc'] + "</option>";
									html+=option;
								}
							});
					   		html+="</select>";	
						}else{
							html+= "<input onblur=\"checkInputNum(this);saveZcfs(this,'";
							html+=jQuery(n).attr("xmdm");
							html+="','";
							html+=rowObject["xh"];
							html+="')\" type='text' onkeyup='toNext(this,event);checkInputNum(this);'";
							html+=" style='width:50px;' maxlength='10' value='";
							html+=cell==null ? "" : cell;
							html+="' name='";
							html+=jQuery(n).attr("xmmc")+"' max='"+jQuery(n).attr("zdfz")+"' min='"+jQuery(n).attr("zxfz")+"'/>";
						}
						return html;
					};
				}
				
				colList.push(json);
			});
			/*colList.push({label:'�ύ״̬',name:'tjztmc', index: 'tjztmc',width:'7%',hidden:true});*/
			gridSetting["colList"] = colList;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
		
		<style>
			/*���ղ���Ŀ����ʱ����ҳ�б����ʾ��ȫ�������б��������ʾ*/
			.formbox {overflow:scroll}
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		
	</head>

	<body>
		<form action="/xpjpy_zcwh" method="post" name="zcwhForm" id ="zcwhForm" enctype="multipart/form-data">
			<input type="hidden" value="${cssz.xn}" id="xn" name = "xn"/>
			<input type="hidden" value="${zcwhForm.id}" id="id" name = "id" />
			<input type="hidden" value='${jsonStr }' id='jsonStr' name ='params'/>
			<input type="hidden" value='${zcxmdmForTop}' id='zcxmdmForTop' name ='zcxmdmForTop'/>
		</form>
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }"  zdfz="${z.zdfz }" zxfz="${z.zxfz }"
				      xmmc="${z.xmmc }" xmlx="${z.xmlx }" jktb="${z.jktb }" name="zcxm"></font>
			</logic:iterate>
		</logic:present>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		
		<!-- ��ʾ��Ϣ end-->
		<div id="div_help" class="prompt" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					���ӣ�����Ӧ��Ϊ<font color="red">��ǰ����</font>��ѧԺ������Ա<font color="red">����</font>��ѧ��<br/>
					ɾ����ɾ��������Ϊ<font color="red">��ǰ����</font>��ѧԺ������Ա<font color="red">����</font>��ѧ��<br/>
					�ύ����Ա�������۲�ɼ�<font color="red">ȷ������</font>�󣬿��ύ���ύ��<font color="red">�����޸�</font><br/>
					ע���˴�ά��ѧ�����۲�ɼ���<font color="red">��ʱ������Ч</font>
				</span>
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<!-- ����������Խ������б��еĲ���ѧ���һ����� -->
					<li>
						<a href="javascript:void(0);" class="btn_zj" onclick="cpxsAdd();">������������</a>
					</li>
					
					<!-- �����������ȡ�����ڸð༶������ѧ���� -->
					<li>
						<a href="javascript:void(0);" class="btn_sc" onclick="cpxsDelete();">ɾ��</a>
					</li>
					
					<li>
						<a href="javascript:void(0);" class="btn_dr" onclick="cpxsImport();">����</a>
					</li>
					
					<li>
						<a href="javascript:void(0);" class="btn_dc" onclick="cpxsExport();">����</a>
					</li>
					
					<li>
						<a href="javascript:void(0);" class="btn_fh" onclick="document.location.href='xpjpy_zhcp_zcwh.do';">����</a>
					</li>
				</ul>
			</div>
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">ѧ�� / ����</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="doQuery();">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
		<div class="formbox" style="overflow: auto;">
			<!--����start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.xn}&nbsp;</font>����ѧ���б� </span>
			</h3>
			<div style="overflow: auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>
		</div>
	</body>
</html>