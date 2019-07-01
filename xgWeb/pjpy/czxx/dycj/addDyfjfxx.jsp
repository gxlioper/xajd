<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<base target="_self" />
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDyInfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript">
      function add(){
        var len = document.getElementById("tab").rows.length;
        //var num = $("numAdd").value;
        var count=len;  
        cellfu = [
             function(data){
	          return  count;
	         },
             function(data){
              return "<select name='pllb' id='lb" + count + "' ><option value='' ></option><option value='+' >�ӷ�</option><option value='-'>����</option></select>"
	         },
	         function(data){
	          return"<textarea name='plyy' rows=1  cols=32  onpropertychange='this.style.posHeight=this.scrollHeight' id='yy" + count + "' >"
	         },
	         function(data){
	         return "<textarea name='plbz' rows=1  cols=32  onpropertychange='this.style.posHeight=this.scrollHeight' id='bz" + count + "' >";
	         },
	         function(data){
	           return "<input type='text' name='plfs' id='fs"+count+ "' onkeyup='ckinpdata(this)' style='width:50px' maxlength='4'/>";
	         }
          ];
          DWRUtil.addRows("bxrow",[''],cellfu,{escapeHtml:false});         
          count++;
      }

      function delRow(){
        var tabobj = document.getElementById("tab");
    	var length = tabobj.rows.length;   
    	if(length==1){
      		return false;
    	}
   		if(confirm("ȷ��Ҫɾ���У�\n(������п�ʼ)")){      
       		tabobj.deleteRow(tabobj.rows.length-1);
    	}
      }
      function dataSave(){
         var xh = $("xh").value;
         var xn = $("xn").value;
         var act  = $("act").value;
         var len = document.getElementById("bxrow").rows.length;
         var lb ="";
         var yy ="";
         var bz ="";
         var num = 1; 
         for(var j=1;j<len;j++){
            lb = document.getElementById("bxrow").rows[j].cells[1].getElementsByTagName('select')[0].value;        
            yy = document.getElementById("bxrow").rows[j].cells[2].getElementsByTagName('textarea')[0].value;  
            bz = document.getElementById("bxrow").rows[j].cells[3].getElementsByTagName('textarea')[0].value;
            fs = document.getElementById("bxrow").rows[j].cells[4].getElementsByTagName('input')[0].value;
            if(lb==""||lb=='null'){
                alert("��"+num+"�С��ӣ��������Ϊ�գ�");
                return false;
            }       
            if(yy==""||yy=='null'){
               alert("��"+num+"�С��ӣ�����ԭ��Ϊ�գ�");
               return false;     
            } 
            if(fs==""||fs=='null'){
               alert("��"+num+"�С�������Ϊ�գ�");
               return false;     
            }  
            if(yy.length>1000){
               alert("��"+num+"�С��ӣ�����ԭ����������(��1000��)");
               return false;
            }                        
            if(bz.length>1000){
               alert("��"+num+"�С���ע����������(��1000��)");
               return false;
            }  
            num++; 
            }                                         
       	refreshForm('pjpy_czxx_addDyfjfxx.do?act=save');
       }

    </script>
	<body >
		<html:form action="/czxxPjpyDycj" method="post">
			
			<input type="hidden" id="strVal" name="strVal"
				value="${rsVal }" />
			<input type="hidden" id="xn" name="xn"
				value="${xn }" />	
			<input type="hidden" id="xq" name="xq"
				value="${xq }" />	
			<input type="hidden" id="xh" name="xh"
				value="${xh }" />	
			<input type="hidden" id="act" name="act"
				value="${act }" />	
			<input type="hidden" id="pkValue" name="pkValue"
				value="${pkValue }" />			
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - �۲���Ϣά�� - �������ӷ�ά��
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							�������ӷ�����
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td height="22" align="left">
						${rs.xh }
					</td>
					<td height="22" align="right">
						ѧ�꣺
					</td>
					<td height="22" align="left">
						${rs.xn }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						������
					</td>
					<td height="22" align="left">
						${rs.xm }
					</td>
					<td height="22" align="right">
						ѧ�ڣ�
					</td>
					<td height="22" align="left">
						${rs.xq }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�꼶��
					</td>
					<td height="22" align="left">
						${rs.nj }
					</td>
					<td height="22" align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td height="22" align="left">
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						רҵ��
					</td>
					<td height="22" align="left">
						${rs.zymc }
					</td>
					<td height="22" align="right">
						�༶��
					</td>
					<td height="22" align="left">
						${rs.bjmc}
					</td>
				</tr>
			</table>
			<fieldset>
				<legend>
					<font color="blue">�������ӷ�ά��</font>
					<input  value="+" title="������"
						style="height:18px;width:20px" onclick="add()">
					&nbsp;
					<input  value="-" title="ɾ����"
						style="height:18px;width:20px" onclick="delRow()">
				</legend>
				<table width="100%" class="tbstyle">
					<tbody>
						<tr>
							<td>
								<table width="100%" class="tbstyle" id="tab">
									<tbody width="100%" class="tbstyle" id="bxrow">									
										<tr>
										   <td>
												���
											</td>
											<td nowrap="nowrap">
												�ӣ��������
											</td>
											<td>
												�ӣ�����ԭ��
											</td>
											<td>
												��ע
											</td>
											<td>
												����
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<br />
			<div class="buttontool" id="button" align="center">
				<logic:notEqual value="view" name="act">
					<button type="button" class="button2" onclick="dataSave()"
						style="width:80px" id="buttonSave" >
						�� ��
					</button>
				</logic:notEqual>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="window.close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</body>
<script type="text/javascript">
 window.onload=function loadData(){
           var strVal = document.forms[0].strVal.value;
           if(strVal==""||strVal==null){
             return false;
           }
           var rowRecords = strVal.split("#");
           
           for(i=0;i<rowRecords.length;i++){
              var colRecords = rowRecords[i].split("!!");
              var count=i+1;  
              cellfu = [
                 function(data){
			          return  count;
			         },
		             function(data){
		              return "<select name='pllb' id='lb" + count + "' ><option value='' ></option><option value='+' >�ӷ�</option><option value='-'>����</option></select>"
			         },
			         function(data){
			          return"<textarea name='plyy' rows=1  cols=32  onpropertychange='this.style.posHeight=this.scrollHeight' id='yy" + count + "' >"
			         },
			         function(data){
			         return "<textarea name='plbz' rows=1  cols=32  onpropertychange='this.style.posHeight=this.scrollHeight' id='bz" + count + "' >";
			         },
			         function(data){
			           return "<input type='text' name='plfs' id='fs"+count+ "' onkeyup='ckinpdata(this)' style='width:50px' maxlength='4'/>";
			         }
              ];
              DWRUtil.addRows("bxrow",[''],cellfu,{escapeHtml:false});         
             
		      $("lb"+count).value=colRecords[0];
		      $("yy"+count).value=colRecords[1];
		      $("bz"+count).value=colRecords[2]; 
		      $("fs"+count).value=colRecords[3];       
              count++;
           }
      }
      </script>
</html>
