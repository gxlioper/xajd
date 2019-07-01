<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
<!--
	function saveData() {
		var doc = "";
		if (document.all){//IE
             doc = document.frames["zdyFrame"].document;
        }else{//Firefox    
             doc = document.getElementById("zdyFrame").contentDocument;
        }
		
		var len = doc.getElementById('flag').rows.length;
		
		if (len == 0) {
			alert("请输入要保存的数据！");
			return false;
		}
		
		var jsonStr = doc.getElementById('jsonStr').value;
		if (jsonStr != null && jsonStr != '') {
			var array = jsonStr.split("!@");

			for (var i=0;i<array.length;i++) {
				if (array[i] != null && array[i] != "") {
					
				var obj = eval('(' + array[i] + ')');

				//非空字段做非空检测	
				if (obj.sfnull == '0') {
					//字段名称对象数组
					var nameArr = doc.getElementsByName(obj.zdid);
					
					for (var j=0;j<nameArr.length;j++) {
						if (nameArr[j].value == null || nameArr[j].value == "") {
							alert("带*号字段为必填项!");
							return false;		
						}
					}
				}
				}
			}
		}
		
		
	}	

//-->
</script>
</head>
<body onload="">
	<html:form action="/pjpyTybZhszcp.do?method=zhszcpAdd" method="post">
		<!-- 保存提示信息 -->
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");
					//document.getElementById('zdyFrame').style.display = "none";
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败,请不要输入重复的数据!");
					//document.getElementById('zdyFrame').style.display = "none";
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
	
		<input type="hidden" name="errmsg" id="errmsg" value="${errmsg }"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }"/>
		<%--		<input type="hidden" name="act" id="act" value="save"/>--%>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url" value="/pjpy_tyb_zhszcpAdd.do?bm=${bm}&dmlb=${dmlb}&dm=${dm}" />
		<input type="hidden" name="bm" id="bm" value="${bm }"/>
		<input type="hidden" name="dmlb" id="dmlb" value="${dmlb}"/>
		<input type="hidden" name="dm" id="dm" value="${dm}" />
		
		<input type="hidden" name="content" id="content"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>单个增加</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%"><font color="red">*</font>学号</th>
				<td width="30%">
					<input type='text' name="hidstr" id="hidstr" style="text-align:left;ime-mode:disabled;width:0px;border:0;" readonly="readonly"/>
					<html:text name='rs' property="xh" styleId="xh" onblur="if (this.value=='') {return false;} else {refreshForm('pjpyTybZhszcp.do?method=zhszcpAdd');return false;}"/>
					<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						选择
					</button>
				</td>
				<th width="20%">姓名</th>
				<td width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>性别</th>
				<td>
					${rs.xb }
				</td>
				<th>年级</th>
				<td>
					${rs.nj }
				</td>

			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${rs.xymc }
				</td>
				<th>专业</th>
				<td>
					${rs.zymc }
				</td>
			</tr>
			<tr>
				<th>班级</th>
				<td>
					${rs.bjmc }
				</td>
				<th>学制</th>
				<td>
					${rs.xz }
				</td>
			</tr>

			<tr>
				<logic:equal value="nd" name="pjzq">
					<th>年度</th>
					<td>
						${rs.nd }
					</td>
					<td></td>
					<td></td>
				</logic:equal>
				<logic:equal value="xn" name="pjzq">
					<th>学年</th>
					<td>
						${rs.xn }
					</td>
					<td></td>
					<td></td>
				</logic:equal>
				<logic:equal value="xq" name="pjzq">
					<th>学年</th>
					<td>
						${rs.xn }
					</td>
					<th>学期</th>
					<td>
						${rs.xqmc }
					</td>
				</logic:equal>
			</tr>
			
			<tr>
				<td colspan="4" style="100%">
					<!-- 自定义字段展现 -->
					<IFRAME id="zdyFrame" name="zdyFrame" style="width:100%;height: 400px;"
					marginwidth='0' marginheight='0' framespacing='0' frameborder='0'
					src="pjpy_tyb_zhszcpZdzx.do?bm=${bm}&xh=${rs.xh }&dmlb=${dmlb }&dm=${dm }"></IFRAME>
				</td>
			</tr>
			</tbody>
		</table>
	</html:form>
</body>

