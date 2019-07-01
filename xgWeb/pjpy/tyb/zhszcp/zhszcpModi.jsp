<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>

<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript">
	function saveZcdata() {
		var array = {};
		var str = "";
		var pjstr = "";
		if (document.getElementsByName('btzd')) {
			array = document.getElementsByName('btzd');
			
			for (var i=0;i<array.length;i++) {
				str += array[i].value;
				str += "-";
				pjstr += "&"+array[i].value;
				pjstr += "=";
				pjstr += $(array[i].value).value;
			}
			
		}
		
		if($("sj")){
			$("sj").disabled=false;
		}
		if($("zcdm")){
			$("zcdm").disabled=false;
		}
		var url = 'pjpyTybZhszcp.do?method=zhszcpModi&act=save&shjb='+val('shjb');
		saveinfo(url + pjstr,str);
	}

	function disabledZd(){
		if($("sj")){
			$("sj").disabled="true";
		}
		if($("zcdm")){
			$("zcdm").disabled="true";
		}
	}
</script>
</head>
<body onload="disabledZd()">
	<html:form action="/pjpyTybZhszcp" method="post">
		<input type="hidden" name="userType" id="userType" value="${userType }" />
		<input type="hidden" name="bm" id="bm" value="${bm }"/>
		<input type="hidden" name="shjb" id="shjb" value="${shjb }"/>
		<input type="hidden" name="xxsh" id="xxsh" value="${rs.xxsh }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${rs.pkValue}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class='tab'>
		<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4" align="center">
						<span>单个修改</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>学号</th>
				<td>
					${rs.xh}
					<input type="hidden" id="xh" name="save_xh" value="${rs.xh}"/>
				</td>
				<th>姓名</th>
				<td>
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
						<input type="hidden" id="nd" name="save_nd" value="${rs.nd}"/>
					</td>
					<th></th>
					<td></td>
				</logic:equal>
				<logic:equal value="xn" name="pjzq">
					<th>学年</th>
					<td align="left">
						${rs.xn }
						<input type="hidden" id="xn" name="save_xn" value="${rs.xn}"/>
					</td>
					<th></th>
					<td></td>
				</logic:equal>
				<logic:equal value="xq" name="pjzq">
					<th>学年</th>
					<td>
						${rs.xn }
						<input type="hidden" id="xn" name="save_xn" value="${rs.xn}"/>
					</td>
					<th>学期</th>
					<td>
						${rs.xqmc }
						<input type="hidden" id="xq" name="save_xq" value="${rs.xq}"/>
					</td>
				</logic:equal>				
			</tr>
			<customTag:customTable  rsname="rs" nodeslist = "pjpyZhszcpwhActionForm" doType="update" scope="request"/>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4">
		          <div class="btn">
		            <logic:notEqual value="view" name="act">
						<button type="button" class="button2" id="btn_save"
							onclick="if(checkUpdateState()){saveZcdata()}"
							style="width:80px">
							保 存
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;"
						style="width:80px" id="buttonClose">
						关 闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		<!-- 保存提示信息 -->
		<jsp:include flush="true" page="/syscommon/shresprompt.jsp"></jsp:include>
		<script type="text/javascript">
			if (document.getElementsByName('btzd')) {
			var array = document.getElementsByName('btzd');			
			for (var i=0;i<array.length;i++) {
				if (array[i].value != 'fs') {
					$(array[i].value).disabled = true;
				}	
			}
			
		}
		</script>
	</html:form>
</body>

