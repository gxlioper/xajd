<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
    function result_query(){
       	var temp = document.getElementById("gnmklj").value;
       	if(temp == null){
       		alert("����ѡ��Ҫ��ѯ����Ŀ!");
       	}else{
			document.forms[0].action = "/xgxt/stu_result_query_common.do?doType=query&act=go&cdlb=result";
		 	document.forms[0].submit();
		}
	}
	
function chkAssisOne() {
	if (curr_row == null) {
		return false;
	} else {
		url = curr_row.getElementsByTagName("input")[0].value;
		if (url==""){
			return false;
		}
		url = url.replace("&amp;","&");
		url += "&jg=yes";
		showTopWin(url, 750, 550);
		return true;
	}
}
    </script>
	</head>
	<body>
		<html:form action="/stu_result_cdopen" method="post">
			<table width="99%"  border="0" class="formlist">
				<thead>
   				<tr>
       				<th colspan="4"><span>��ѡ��Ҫ��ѯ����Ŀ</span></th>
       			</tr>
  				</thead>
  				<tbody>
				<tr>
					<td align="center">
						<html:select name="rs1" property="gnmklj" styleId="gnmklj">
								<html:option value="">---------��ѡ��--------</html:option>
								<html:options collection="list" property="gnmklj"
									labelProperty="gnmkmc" />
							</html:select>
					</td>
				</tr>
				<tr>
					<td align="center">
						<button  onclick=result_query()
							style="width:80px" value="" >ȷ  ��</button>
					</td>
				</tr>
				</tbody>
			</table>
			
			<div class="formbox">
			<logic:equal name="userType" value="stu">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ���޸ģ�������ͷ��������;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			   </logic:equal>
			<logic:notEmpty name="rs">
				<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="chkAssisOne();">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>

