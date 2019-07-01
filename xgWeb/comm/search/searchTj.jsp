<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/searchUtil.js"></script>
		<script language="javascript" defer="defer">
//页面初始化
function onShow(){
	var userStatus = $("userStatus").value;
	//登陆用户为学生
	if(userStatus == "stu"){
		if($("input_xh")){
			$("input_xh").readOnly = true;
		}
		if($("input_xm")){
			$("input_xm").focus();
		}
	}
}

//根据过滤条件执行查询操作
function searchRs(){
	var win = window.dialogArguments.document;
	var node = win.getElementById("searchTjDiv");
	node.innerHTML = "";
	
	//学院
	var xy = getClickXy();
	for(var i=0;i<xy.length;i++){
		var tmp = win.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_xy";
			tmp.value = xy[i];
			node.appendChild(tmp);
	}
	
	//专业
	var zy = getClickZy();
	for(var i=0;i<zy.length;i++){
		var tmp = win.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_zy";
			tmp.value = zy[i];
			node.appendChild(tmp);
	}
	
	//班级
	var bj = getClickBj();
	for(var i=0;i<bj.length;i++){
		var tmp = win.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_bj";
			tmp.value = bj[i];
			node.appendChild(tmp);
	}
	
	// [性别,年级,学年,学期,年度,文件类型]
	var all_tj = ["xb","nj","xn","xq","nd","filelx"];
	// [学号，姓名,文件名称]
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
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="path" name="path" value="${path }" />
			<!-- 隐藏域 end-->
			<table class="formlist" border="0" align="center" style="width: 95%;" >
				<thead>
					<tr>
						<th colspan="2">
							<span>高级查询（注：红色字样为已经选中的过滤条件，具体描述可以再讨论）</span>
						</th>
					</tr>
				</thead>
				<tbody>	
				<!-- 学号、姓名、性别 -->
				<%@ include file="/comm/search/tj/xhxmxb.jsp"%>
				<!-- 学号、姓名、性别 end-->
				
				<!-- 年级、学院、专业、班级 -->
				<%@ include file="/comm/search/tj/njxyzybj.jsp"%>
				<!-- 年级、学院、专业、班级 end-->
				
				<!-- 模块个性化 -->
				<%@ include file="/comm/search/tj/especial.jsp"%>
				<!-- 模块个性化 end-->
				
				<!-- 其他 -->
				<%@ include file="/comm/search/tj/other.jsp"%>
				<!-- 其他 end-->
				
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<div class="btn">
								<button type="button" onclick="searchRs();return false;" id="buttonSave">
									查	询
								</button>

								<button type="button" onclick="resetTj()" id="buttonSave">
									重	置
								</button>
								
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									关	闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>