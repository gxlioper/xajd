<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zdzc/js/zcfs.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var djList = <%=request.getAttribute("djList")%>;
			
			var gridSetting = {
				caption:"����ѧ���б� ",
				pager:"pager",
				url:"zdzcwh.do?method=viewZdzcList&doType=query&editType=edit",
				params:getSuperSearch()
			};
				
			var zcxm = jQuery("font[name=zcxm]");
			var colList=[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'15%'},
				   {label:'����',name:'xm', index: 'xm',width:'15%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'15%'},
				   {label:'�ύ״̬',name:'tjzt', index: 'tjzt',hidden:true}
				];

			jQuery.each(zcxm,function(i,n){
				var json = {label:jQuery(n).attr("xmmc"),
							name:"fs"+i,
							index:"fs"+i
							
							};
				
				if (jQuery(n).attr("xmlx") != "3" && jQuery(n).attr("jktb") ==""){
					json["formatter"] = function(cell,rowObject){
						
						var html="";

						if(rowObject["tjzt"]=="1"){
							if(jQuery(n).attr("xmlx") == "4"){
								jQuery.each(djList,function(i,dj){
									if(cell == dj['dm']&&jQuery(n).attr("xmmc")==dj['xmmc']){
										html+=dj['mc'];
									}
								});
							}else{
								html+=cell==null ? "" : cell;						
							}
						}else if(jQuery(n).attr("xmlx") == "4"){
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
							html+=jQuery(n).attr("xmmc")+"' max='"+jQuery(n).attr("zdfs")+"' min='"+jQuery(n).attr("zxfs")+"'/>";
						}
						return html;
					};
				}
				
				colList.push(json);
			});
			colList.push({label:'�ύ״̬',name:'tjztmc', index: 'tjztmc',width:'7%'});
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
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }"  zdfs="${z.zdfs }" zxfs="${z.zxfs }"
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
					���ӣ�����Ӧ��Ϊ<font color="red">��ǰ����</font>�˰༶������Ա<font color="red">����</font>��ѧ��<br/>
					ɾ����ɾ��������Ϊ<font color="red">��ǰ����</font>�˰༶������Ա<font color="red">����</font>��ѧ��<br/>
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
			<html:form action="/zdzcwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="showDialog('���Ӳ���ѧ��',800,550,'xpj_cpmd.do?method=viewAddCpxsList&bjdm=${bjxxMap.bjdm }');" class="btn_zj"
						   title="����������Խ������б��еĲ���ѧ���һ�����"
						>������������</a></li>
					<li><a href="javascript:void(0);" onclick="cpxsDelete();" class="btn_sc"
						   title="�����������ȡ�����ڸð༶������ѧ����"
						>ɾ��</a></li>
					<li><a href="javascript:void(0);" onclick="toImport();" class="btn_dr"
						   title="����������Խ��Ѿ�����õ�ѧ���۲�������ٵ��뵽ϵͳ���С�"
						>����/����</a></li>
					<li><a href="javascript:void(0);" onclick="cpxsTj();" class="btn_up"
					   title="������������ύ��ȷ�ϵ�ѧ����"
					>�ύ</a></li>
					<logic:equal value="xx" name="userStatus">
						<li><a href="javascript:void(0);" onclick="cpxsQxtj();" class="btn_down"
						   title="������������ύ��ȷ�ϵ�ѧ����"
						>ȡ���ύ</a></li>	
					</logic:equal>			
					<logic:present name="zcxmList">
						<logic:iterate id="z" name="zcxmList">
							<logic:notEmpty name="z" property="jktb">
								<li><a href="javascript:void(0);" onclick="getIntefaceData('${z.xmdm}')" class="btn_sx">ͬ��${z.xmmc }</a></li>						
							</logic:notEmpty>
						</logic:iterate>
					</logic:present>
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			</html:form>
		</div>
		<div class="formbox" style="overflow: auto;">
			<!--����start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>����ѧ���б� </span>
			</h3>
			<div style="overflow: auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>
		</div>
	</body>
</html>
