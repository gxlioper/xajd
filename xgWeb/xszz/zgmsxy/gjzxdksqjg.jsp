<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript">
     function result_query(){
      		var temp = document.getElementById("gnmkbz").value;
      		if(temp == null){
      			alert("����ѡ��Ҫ��ѯ����Ŀ!");
      		}else{
			document.forms[0].action = "zgmsxy_xszz.do?method=gjzxdksqjg&doType=query";
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
		showTopWin(url, 750, 550);
		return true;
	}
}
    </script>
</head>
	<body>
		
		<form action="#" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ���� - ��������ѯ</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th>
							<span>��ѡ��Ҫ��ѯ����Ŀ</span>
						</th>
					</tr>
				</thead>
				<tr>
					<td align="center">
						<html:select name="rs1" property="gnmkbz" styleId="gnmkbz">
							<html:option value="">---------��ѡ��--------</html:option>
							<html:option value="1">������ѧ����</html:option>
							<html:option value="2">��ҵ����Ϣ</html:option>
						</html:select>
					</td>
				</tr>
		
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			          	<button type="button" onclick="result_query()" value="ȷ��">ȷ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</form>
	</body>
</html>

