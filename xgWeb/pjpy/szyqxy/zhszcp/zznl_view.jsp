<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>

	<script type="text/javascript">
var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
	return count+ "<input type='hidden' style='width:80px'  name='_xuh' value='"+count+"'>";
	},
	function(data){	    
	return "<input type='text' style='width:200px'  name='hdzt' id='xh" + count + "' maxlength='20''>";
    },	
	function(data){	    
	return "<input type='text' style='width:80px'  name='hdrq' id='xm" + count + "' maxlength='20''>";
    },
    function(data){
    return "<input type='text' style='width:100px'  name='hddj' id='bj" + count + "' >";
    },
    function(data){    
	return "<input type='text' style='width:60px' id='hdpf' name='hdpf' maxlength='200'>";	 
    },
    function(data){    
    	return "<select style='width:60px' id='jjf' name='jjf'><option value='�ӷ�' label='�ӷ�'><option value='����' label='����'></select>";	 
    },
    function(data){    
    	return "<input type='text' style='width:60px' id='shfz' name='shfz' maxlength='200'>";	 
     }
	];
	
	if(type=='add'){
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
       }
       $("numAdd").value = "";
    }        
}
function time(id){
	return showCalendar(id,'y-mm-dd');
}
function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("ȷ��Ҫɾ����"+(length)+"�У�")){      
       tabobj.deleteRow(tabobj.rows.length-1);
    }
}
function trDelAll(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $("numDel").value; 
    if(length==0){
       $("numDel").value = "";
       return false;     
    }
    if(num==""||num==null){	
       return false;
    }
    if(num>length){
      num = length;
    }
    if(confirm("ȷ��Ҫɾ�����"+num+"�У�")){ 
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $("numDel").value = "";
}	
function fillValue(obj){
		var xm= "xm" + obj.id.replace("xh","");
		var bj= "bj" + obj.id.replace("xh","");
  		if(obj.value==""){
  		  return false;
  		}	
		getSztzData.getXsInfo(obj.value,function(data){
		if(data!=null){
			$(xm).value=data[0];
			$(bj).value=data[1];
		}else{
			alert("��ѧ�Ų����ڣ���������ȷѧ�ţ�");
			obj.value="";
			obj.focus();
			}
		});

}
function ztbhSave(the_tab){
   var tabobj = document.getElementById(the_tab);
    var rowLen = tabobj.rows.length;
    var xh ="";
    var xm ="";
    var bj = "";
    var bhzt ="";
    var bhnr = "";
    var bhbx = "";
    var fs = "";
    var bz = "";
    var xn = "";
    var xq = "";
    if($("xn")){
       xn = $("xn").value; 
       if(xn==""){
           alert("ѧ�겻��Ϊ�գ�");
           return false;
       }
    }
    if($("xq")){
       xq = $("xq").value;
       if(xq==""){
           alert("ѧ�ڲ���Ϊ�գ�");
           return false;
       }
    }
    var pkValue = xn+xq+xh;
    var nullCout = 0;
		
        for(j=0;j<rowLen;j++){
            var num = j+1;
            xh = document.getElementById("flag").rows[j].cells[1].getElementsByTagName('input')[0].value;
            //alert(xh);
            //alert(rowLen);
            if(xh==""){
                alert("��"+num+"�С�ѧ�š�Ϊ�գ�");
                return false;
            }
        }
        //alert(rowLen);
        //return false;
        refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_zznlUpdate&doType=update');
         $("buttonSave").disabled=true;
}
	</script>
	<body>
		<html:form action="/pjpyszyqwh">
			<input id="url" name="url" type="hidden" value="/pjpy/szyqxy/zhszcp/zznl_Add.jsp"/>
			<input id="getStuInfo" name="getStuInfo" type="hidden" value="xh"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ۺ�����-ѧ���ۺ��������ɿ�-��֯�����鿴
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
<%--				<thead>--%>
<%--					<tr style="height: 23px">--%>
<%--						<td colspan="4" align="center">--%>
<%--							˼��������������ʷ�����--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--				</thead>--%>
				<tr style="height: 23px">
					<td align="right">
						ѧ�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xh"/>
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
							<bean:write name="rs" property="xq"/>
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
							<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
			</table>
		<fieldset>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<tr>
						<td>
					<div class="mid_box">
							<table align="center" style="width: 100%" id="rsT" class="tbstyle">
								<!-- ��ӡʱ��һ�в���ʾ- -->
								<thead style="height: 23px">
									<tr>
										<td nowrap="nowrap">
											���
										</td>
										<td nowrap="nowrap">
											�����
										</td>
										<td nowrap="nowrap">
											����
										</td>
										<td nowrap="nowrap">
											�ȼ�
										</td>
										<td nowrap="nowrap">
											�Ӽ���
										</td>
										<td nowrap="nowrap">
											����
										</td>
									</tr>
								</thead>
								<tbody width="100%" class="tbstyle" id="flag">
									<logic:notEmpty name="rs1">
										<logic:iterate name="rs1" id="s">
											<tr>
												<td>
													<bean:write name="s" property="rnum" />
												</td>
												<td>
													<bean:write name="s" property="hdzt"/>
												</td>
												<td>
													<bean:write name="s" property="hdrq"/>
												</td>
												<td>
													<bean:write name="s" property="hddj"/>
												</td>
												<td>
													<bean:write name="s" property="jjf"/>
												</td>
												<td>
													<bean:write name="s" property="shfz"/>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</tbody>
					</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" class="button2" id="buttonClose" onclick="window.close();return false;" style="width: 80px" id="buttonClose">
						�� ��
					</button> </span>
			</div>
			<logic:present name="done">
				<logic:equal value="yes" name="done">
					<script>
	alert('�����ɹ���');
</script>
				</logic:equal>
				<logic:equal value="no" name="done">
					<script>
	alert('����ʧ�ܣ�');
</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
