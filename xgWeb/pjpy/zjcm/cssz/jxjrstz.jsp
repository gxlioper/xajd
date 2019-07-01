<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/zjcm/cssz/cssz.js"></script>
<script type="text/javascript">
function checkTzrsIsThanCprs() {
	var cprs = document.getElementById('cprs').value;
	var jxjrs = document.getElementById('jxjrs').value;
	cprs = parseFloat(cprs);
	jxjrs = parseFloat(jxjrs);
	if (jxjrs >= cprs) {
		alert("调整人数不能大于或等于该部门总人数!请重新调整.");
		return false;
	}
	return true;
}
function savedata() {
	if (checkTzrsIsThanCprs()) {
		saveinfo('pjpy_zjcm_jxjrstz.do?act=save','jxjrs');
	} 
}
function dispButton() {
	var jxjbl = document.getElementById('jxjbl').value;
	if (jxjbl ==null||jxjbl=='') {
		if ($('btn_save')) {
			document.getElementById('btn_save').disabled = true;
		}
		document.getElementById('msg').style.display="block";
	}
}
</script>
</head>
<body onload="dispButton()">
	<html:form action="/pjpyZjcmCssz" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${rs.pkValue }"/>
		<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 参数设置 - 参数设置</a>
			</p>
		</div>
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4" align="center">
						<span>奖学金比例人数单个调整</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>学年</th>
				<td>
					<html:text name="rs" property="xn" styleId="xn" style="color:#666666" readonly="true" />
				</td>
			</tr>
			<tr>
				<th>学期</th>
				<td>
					<html:text name="rs" property="xqmc" styleId="xq" style="color:#666666" readonly="true" />
				</td>
			</tr>
			<tr>
				<th>年度</th>
				<td>
					<html:text name="rs" property="nd" styleId="nd" style="color:#666666" readonly="true" />
				</td>
			</tr>
			<tr>
				<th>设置比例范围</th>
				<td>
					<html:select name="rs" property="cpfw" styleId="cpfw" style="width:120px" disabled="true">
						<html:options collection="cpfwList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>年级</th>
				<td>
					<html:select property="nj" styleId="nj" name="rs"
					 styleClass="select" style="width:90px" disabled="true">
						<html:option value=""></html:option>
						<html:options collection="njList" property="nj" labelProperty="nj"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>部门名称</th>
				<td>
					<html:text property="bmmc" name="rs" disabled="true"></html:text>
				</td>
			</tr>
			<tr>
				<th>参评人数</th>
				<td>
					<html:text property="cprs" name="rs" styleId="cprs" disabled="true"></html:text>
				</td>
			</tr>
			<tr>
				<th>奖学金类别</th>
				<td>
					<html:select property="jxjlb" styleId="jxjlb" name="rs" disabled="true">
						<html:options collection="lbList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>奖学金</th>
				<td>
					<html:select property="jxjdm" styleId="jxjdm" name="rs" disabled="true">
						<html:options collection="dmList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>建议人数比例</th>
				<td>
					<input type="text" style="width:60px" name="jxjbl" id="jxjbl" value="${rs.jxjbl }" disabled="disabled"/>
					<font color="red">(%)</font>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>调整人数</th>
				<td>
					<html:text property="jxjrs" styleId="jxjrs" maxlength="4" value="${rs.jxjrs }" onkeyup="chkIsNum(this);checkTzrsIsThanCprs();"></html:text>
					<font color="red">（请输入正整数）</font>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4">
				<div class="bz">"<span class="red">*</span>"为必填项
				<div align="center" style="display: none;" id="msg">
					<font color="red">(尚未设置奖学金比例，不能进行人数调整！)</font>
				</div>
				</div>
		          <div class="btn">
		            <logic:notEqual value="view"  name="writable">
						<button type="button" class="button2" id="btn_save"
						onclick="savedata()"
						style="width:80px">
						保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
						<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
				
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>