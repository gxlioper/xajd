<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/searchUtil.js"></script>
		<script language="javascript" defer="defer">
//ҳ���ʼ��
function onShow(){
	var userStatus = $("userStatus").value;
	//��½�û�Ϊѧ��
	if(userStatus == "stu"){
		if($("input_xh")){
			$("input_xh").readOnly = true;
		}
		if($("input_xm")){
			$("input_xm").focus();
		}
	}
}

//���ݹ�������ִ�в�ѯ����
function searchRs(){
	var win = window.dialogArguments.document;
	var node = win.getElementById("searchTjDiv");
	node.innerHTML = "";
	
	//ѧԺ
	var xy = getClickXy();
	for(var i=0;i<xy.length;i++){
		var tmp = win.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_xy";
			tmp.value = xy[i];
			node.appendChild(tmp);
	}
	
	//רҵ
	var zy = getClickZy();
	for(var i=0;i<zy.length;i++){
		var tmp = win.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_zy";
			tmp.value = zy[i];
			node.appendChild(tmp);
	}
	
	//�༶
	var bj = getClickBj();
	for(var i=0;i<bj.length;i++){
		var tmp = win.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_bj";
			tmp.value = bj[i];
			node.appendChild(tmp);
	}
	
	// [�Ա�,�꼶,ѧ��,ѧ��,���,�ļ�����]
	var all_tj = ["xb","nj","xn","xq","nd","filelx"];
	// [ѧ�ţ�����,�ļ�����]
	var all_like_tj = ["xh","xm","filemc"];
	
	for(var i=0;i<all_like_tj.length;i++){
	
		var tj = all_like_tj[i];
		var tjz = "";
		
		if($("input_"+tj)){
			tjz = $("input_"+tj).value
		}
		
		if(tjz != ""){
			var tmp = win.createElement("input");
				tmp.type = "hidden";
				tmp.name = "searchModel.input_"+tj;
				tmp.value = tjz;
				node.appendChild(tmp);
		}
	}
	
	for(var i=0;i<all_tj.length;i++){
	
		var tj = all_tj[i];
		var tjz = getClickTj(tj);
		
		for(var j=0;j<tjz.length;j++){
			var tmp = win.createElement("input");
				tmp.type = "hidden";
				tmp.name = "searchModel.search_tj_"+tj;
				tmp.value = tjz[j];
				node.appendChild(tmp);
		}
	}
	
	window.dialogArguments.document.getElementById("search_go").click();	
	window.close();
}
		</script> 
	</head>
	<body onload="setFocus('input_xh');onShow();" style="overflow:scroll;overflow-x:hidden">
		<html:form action="/seachTjManage">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="path" name="path" value="${path }" />
			<!-- ������ end-->
			<table class="formlist" border="0" align="center" style="width: 95%;" >
				<thead>
					<tr>
						<th colspan="2">
							<span>�߼���ѯ��ע����ɫ����Ϊ�Ѿ�ѡ�еĹ��������������������������ۣ�</span>
						</th>
					</tr>
				</thead>
				<tbody>	
				<!-- ѧ�š��������Ա� -->
				<%@ include file="/comm/search/tj/xhxmxb.jsp"%>
				<!-- ѧ�š��������Ա� end-->
				
				<!-- �꼶��ѧԺ��רҵ���༶ -->
				<%@ include file="/comm/search/tj/njxyzybj.jsp"%>
				<!-- �꼶��ѧԺ��רҵ���༶ end-->
				
				<!-- ģ����Ի� -->
				<%@ include file="/comm/search/tj/especial.jsp"%>
				<!-- ģ����Ի� end-->
				
				<!-- ���� -->
				<%@ include file="/comm/search/tj/other.jsp"%>
				<!-- ���� end-->
				
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<div class="btn">
								<button type="button" onclick="searchRs();return false;" id="buttonSave">
									��	ѯ
								</button>

								<button type="button" onclick="resetTj()" id="buttonSave">
									��	��
								</button>
								
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									��	��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>