<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xgxt.form.User" />
<jsp:directive.page import="xgxt.comm.search.SearchService" />
<jsp:directive.page import="xgxt.comm.search.SearchForm" />
<jsp:directive.page import="xgxt.form.RequestForm" />
<jsp:directive.page import="xgxt.action.Base" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />

<%
	//��ʼ����ض���
	SearchService search = new SearchService();
	SearchForm model = new SearchForm();
	RequestForm rForm = new RequestForm();
	User user = search.getUser(request);// �û�����
	
	String path = (String)request.getAttribute("path");
	String searchType =(String)request.getAttribute("searchType");
	
	if(Base.isNull(path)){
		//��ñ�ģ��·��
		path = request.getRequestURL().toString();
		String[] arrPath = path.split("/");
		path = arrPath[arrPath.length-1];	
	}
	
	if(!Base.isNull(searchType)){
		path += "&searchType="+searchType;
	}
	
	model.setPath(path);
	System.out.println("�߼���ѯ��Ҫ���õ�ҳ��:"+path);
	
	List<HashMap<String, String>> tjList = search.getSearchTjList(model,user);
	request.setAttribute("tjList",tjList);
	
	//����ģ��
	//String ssmk = "";
	
	//if(tjList!=null && tjList.size()>0){
	//	ssmk = tjList.get(0).get("ssmk");
	//	model.setSsmk(ssmk);
	//}

	//�����������
	//search.setSearchTj(model, rForm, user, request);
	//search.setRequestValue(rForm, user, request);
	
%>

<script language="javascript" src="js/comm/commFunction.js"></script>
<script language="javascript" src="js/comm/searchTj.js"></script>
<script language="javascript" src="js/comm/searchGxhTj.js"></script>
<script language="javascript" src="js/comm/searchTjByGygl.js"></script>
<script language="javascript" src="js/comm/searchTjByJxjz.js"></script>
<script language="javascript"
	src="<%=request.getContextPath()%>/xsgzgl/gygl/search/searchTjByGygl_Third.js"></script>
<script language="javascript" src="/xgxt/dwr/interface/searchUtil.js"></script>
<script language="javascript" defer="defer">

//var alert_info = if0.showModelessDialog("javascript:alert();window.close();","","status:no;resizable:no;help:no;dialogHeight:30px;dialogWidth:40px;");   
function setTsMsg(){
	//ģ��·����ҳ������и�������
	var path  = $("path").value;
	
	//------------���ģ����ѯ��Ϣ------------
	var tableName = "xg_search_szb";
	var colList = ["tj","mc"]; 
	var pk = "path||lx"; 
	var pkValue = path+"mhcx";
	var query = "";
	if($("ts_msg").value == ""){
	
		//��ѯ��ʾ��Ϣ
		var html_span = "����¼������ѯ��������";
			html_span+= "<font color='blue'>��ǿո�</font>";
			html_span+= "����(���";
		
<%--		dwr.engine.setAsync(false);--%>
<%--	--%>
<%--		getOtherData.getTableListInfo(tableName,colList,pk,pkValue,query,function(data){--%>
<%--			if(data != null && data.length > 0){--%>
<%--				for(var i=0;i<data.length;i++){--%>
<%--					--%>
<%--					var tj = data[i].tj;--%>
<%--					var mc = data[i].mc;--%>
<%--					if(i == 0){--%>
<%--						html_span += mc;--%>
<%--					}else{--%>
<%--						html_span += "��"+mc;--%>
<%--					}--%>
<%--				}--%>
<%--			}--%>
<%--		});--%>
<%--		--%>
<%--		dwr.engine.setAsync(true);--%>
		
		html_span += "��ѡ��Ĳ�ѯ��Χ����ģ����ѯ)";

		$("ts_msg").value = html_span;	
		$("tsxx_span").innerHTML = html_span;
	}else{
		$("tsxx_span").innerHTML = $("ts_msg").value;
	}
}

//���ݹ�������ִ�в�ѯ����
function setSearchTj(){	
	
	var node = $("searchTjDiv");
	
	var mhcxHtml = node.innerHTML;
	node.innerHTML = "";
	
	//ѧԺ
	var xy = getYxtj("xy");

	for(var i=0;i<xy.length;i++){
		var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_xy";
			tmp.value = xy[i];
			node.appendChild(tmp);
	}
	
	//רҵ
	var zy = getYxtj("zy");
	
	for(var i=0;i<zy.length;i++){
		var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_zy";
			tmp.value = zy[i];
			node.appendChild(tmp);
	}
	
	//�༶
	var bj = getYxtj("bj");
	
	for(var i=0;i<bj.length;i++){
		var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_bj";
			tmp.value = bj[i];
			node.appendChild(tmp);
	}
	
	//��������
	for(var i=0;i<jytj.length;i++){
	
		var tj = jytj[i];
		var tjz = getYxtj(tj);

		for(var j=0;j<tjz.length;j++){
			var tmp = document.createElement("input");
				tmp.type = "hidden";
				tmp.name = "searchModel.search_tj_"+tj;
				tmp.value = tjz[j];
				node.appendChild(tmp);
		}
	}
}

//�ر�More
function closeMore(){

	if($("yxtj_gxh_div")){
		
	}else{
		if($("a_id_more")){
			var obj = $("a_id_more");
			obj.className="down";
			obj.innerHTML="�� ��";
		}
		$("gjcx_div").style.display = "none";
	}
}
</script>

<div class="search_advanced">

	<!-- �û���� -->
	<input type="hidden" name="userStatus" id="userStatus" value="${userStatus }" />
	<input type="hidden" id="yhlx" value="${yhlx }" />
	<input type="hidden" name="searchPath" id="searchPath" value="<%=path%>" />
	<!-- ģ����ѯ -->
	<div class="adv_filter">
		<table border="0" width="100%">
			<tbody>
				<tr>
					<td style="padding-left:68px;">

						<!-- ѧ���û� -->
						<logic:equal name="userType" value="stu">
                			ѧ�ţ�${userName}&nbsp;&nbsp;
                			<input type="hidden" name="searchModel.input_xh"
								id="input_xh" value="${userName }" size="25" />&nbsp;&nbsp;
                			������${userNameReal }
                			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                		</logic:equal>

						<!-- ��ѧ���û� -->
						<logic:notEqual name="userType" value="stu">
							��ѯ������
					        <input type="text" name="searchModel.input_mhcx"
								id="input_mhcx" value="${searchTj.input_mhcx }" size="50"
								onkeypress="if(pressEnter(event)){searchRs();return false;}"
								onfocus="setTsMsg();displayMsgDiv('input_mhcx_msg');"
								onblur="hideMsgDiv('input_mhcx_msg')" />

							<div id="input_mhcx_msg" class="hide"
								style="left: 130px;top: 85px;">
								<div class="prompcon" style="width: 250px">
									<p id="tsxx_span"></p>
								</div>
							</div>
						</logic:notEqual>

						<input type="hidden" name="ts_msg" id="ts_msg" value="">
						<input type="hidden" name="go" value="a" />

						<logic:notEqual name="userType" value="stu">
							<button type="button" class="btn_cx" id="search_go"
								onclick="setSearchTj();searchRs();closeMore();">
								�� ѯ
							</button>

							<button type="button" class="btn_cz" onclick="czSuperSearch()">
								�� ��
							</button>
						</logic:notEqual>

						<logic:equal name="userType" value="stu">
							<button type="button" class="btn_cx" id="search_go" style="display: none"
								onclick="setSearchTj();searchRs();">
								�� ѯ
							</button>
						</logic:equal>

						<logic:notEqual name="userType" value="stu">
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio property="searchModel.mhcx_lx" value="all" onclick="$('mhcx_lx').value = this.value"/>
							ȫ��
							<!-- ģ��ѭ�� -->
							<logic:iterate name="tjList" id="tjMap">
								<logic:equal name="tjMap" property="lx" value="mhcx">
									<logic:empty name="tjMap" property="zd">
										<html:radio property="searchModel.mhcx_lx"
											value="${tjMap.tj }" onclick="$('mhcx_lx').value = this.value" />${tjMap.mc }
									</logic:empty>
									<logic:notEmpty name="tjMap" property="zd">
										<html:radio property="searchModel.mhcx_lx"
											value="${tjMap.zd }" onclick="$('mhcx_lx').value = this.value"/>${tjMap.mc }
									</logic:notEmpty>
								</logic:equal>
							</logic:iterate>
						</logic:notEqual>
						<input type="hidden" id="mhcx_lx" value="all"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- ģ����ѯ end-->

	<!-- ��ѡ���� -->
	<div class="selected-attr" id="yxtj_div" style="display: none">
		<h3>
			��ѡ������
		</h3>
		<dl id="yxtj_dl">

		</dl>
	</div>
	<!-- ��ѡ���� end-->

	<!-- �߼���ѯ-->
	<div class="prop-item" id="gjcx_div" style="display:none">

	</div>
	<!-- �߼���ѯ end-->
</div>

<!-- ������ť -->
<logic:notEmpty name="tjList">
	<div class="more--item_bottom">
		<p>
			<a href="#" class="down" id="a_id_more"
				onclick="showTbody(this,'up','down','�� ��','�� ��');return false">
				�� ��
			</a>
		</p>
	</div>
</logic:notEmpty>
<!-- ������ť end-->

<div id="searchTjDiv" style="display: none">
	<logic:present name="searchTj">

		<!-- ����ѭ�� -->
		<logic:iterate name="tjList" id="tjMap">
			<logic:equal name="tjMap" property="lx" value="djcx">
				<!-- ������� -->
				<logic:notEmpty name="searchTj" property="search_tj_${tjMap.tj }">
					<logic:iterate name="searchTj" property="search_tj_${tjMap.tj }"
						id="queryValue">
						<input type="hidden" title="${tjMap.tj }"
							name="searchModel.search_tj_${tjMap.tj }" value="${queryValue }" />
					</logic:iterate>
				</logic:notEmpty>
			</logic:equal>
		</logic:iterate>
		<!-- ����ѭ�� end -->

		<!-- ��ʼʱ�� -->
		<logic:notEmpty name="searchTj" property="search_tj_kssj">
			<logic:iterate name="searchTj" property="search_tj_kssj"
				id="queryValue" indexId="kssjNum">
				<input type="text" name="search_tj_kssj" value="${queryValue }" />
			</logic:iterate>
		</logic:notEmpty>

		<!-- ����ʱ�� -->
		<logic:notEmpty name="searchTj" property="search_tj_jssj">
			<logic:iterate name="searchTj" property="search_tj_jssj"
				id="queryValue" indexId="jssjNum">
				<input type="text" name="search_tj_jssj" value="${queryValue }" />
			</logic:iterate>
		</logic:notEmpty>

		<!-- ������ѡ���� -->
		<script language="javascript" defer="defer">
			setTimeout("setKssjOrJssj()",2000); 
			setTimeout("creatClickedTjBySearch()",3000);
		</script>
	</logic:present>
</div>

<script language="javascript" defer="defer">
	//�߼���ѯ�����õĵ�ַ
	var searchPath = $("searchPath").value;
	var yhlx = $("yhlx").value;;
	var v4Path = stylePath;
	var jxid = "";

	//���ؾ�ѵid
	if(document.getElementById("jxid")){
		jxid = document.getElementById("jxid").value;
	}
	
	//��������
 	var parameter = {
 		"searchPath":searchPath,
 		"stylePath":v4Path,
 		"yhlx":yhlx,
 		"jxid":jxid
	};
	
	var url="seachTjManage.do?method=createSearchTj";
	//jQuery.post(url,parameter,function(result){});
	jQuery("#gjcx_div").load(url,parameter,function(){});
	
	//��������
	function creatBmHtml(bmlx){
		
		var ul_id = bmlx+"_ul";
		
		var nj_click = getClickNj();
		var xy_click = getClickXy();
		var zy_click = getClickZy();
		
		var v4Path = stylePath;

		//����
	 	var parameter = {
	 		"nj":nj_click.join("!!@@!!"),
	 		"xy":xy_click.join("!!@@!!"),
	 		"zy":zy_click.join("!!@@!!"),
	 		"bmlx":bmlx,
	 		"stylePath":v4Path,
	 		"searchPath":searchPath
		};
	
		var url="seachTjManage.do?method=creatBmHtml";

		jQuery.ajaxSetup({async:false});
		
		if($(ul_id)){
			jQuery("#"+ul_id).load(url,parameter,function(){
			
				var bm_num_id = bmlx+"_num";
				var bm_more_id = bmlx+"_more";
				var num = $(bm_num_id).value;
				
				if(num < 12){
					if($(bm_more_id)){
						$(bm_more_id).style.display = "none";
					}
				}else{
					$(bm_more_id).style.display = "";
					$(bm_more_id).innerHTML = "����";
					$(bm_more_id).className = "more_down";
				}
			});
		}
		
		jQuery.ajaxSetup({async:true});	
	}
	
	//������Ԣ
	function creatGyHtml(bmlx){
		
		var ul_id = bmlx+"_ul";
		//У������
		var xqdm_click = getClickXqdm();
		//԰������
		var yqdm_click = getClickYqdm();
		//¥��
		var ld_click = getClickLd();
		//���
		var ch_click = getClickTj("ch");
		
		var v4Path = stylePath;

		//����
	 	var parameter = {
		 	"xqdm":xqdm_click.join("!!@@!!"),
		 	"yqdm":yqdm_click.join("!!@@!!"),
	 		"ld":ld_click.join("!!@@!!"),
	 		"ch":ch_click.join("!!@@!!"),
	 		"bmlx":bmlx,
	 		"stylePath":v4Path
		};
	
		var url="seachTjManage.do?method=creatGyHtml";

		jQuery.ajaxSetup({async:false});
		
		if($(ul_id)){
			jQuery("#"+ul_id).load(url,parameter,function(){
			
				var bm_num_id = bmlx+"_num";
				var bm_more_id = bmlx+"_more";
				var num = $(bm_num_id).value;

				if(num < 8){
					if($(bm_more_id)){
						$(bm_more_id).style.display = "none";
					}
				}else{
					$(bm_more_id).style.display = "";
					$(bm_more_id).innerHTML = "����";
					$(bm_more_id).className = "more_down";
				}
			});
		}
		
		jQuery.ajaxSetup({async:true});	
	}
</script>
