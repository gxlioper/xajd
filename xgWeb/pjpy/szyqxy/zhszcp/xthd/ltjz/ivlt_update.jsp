<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
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
		return "<input type='text' style='width:80px'  name='jztm' id='jztm" + count + "' maxlength='30''>";
	    },	
		function(data){	    
		return "<input type='text' style='width:80px' name='xthdrq' id='xthdrq" + count + "' readonly='true' onblur='dateFormatChg(this)' style='cursor:hand;' onclick='time(this.id)'>";
	    },
	    function(data){
	    return "<input type='text' style='width:80px'  name='jcdj' id='jcdj" + count + "' >";
	    },
	    function(data){    
		return "<input type='text' style='width:60px' id='ccdj" + count + "' name='ccdj' maxlength='200'>";	 
	    },
	    function(data){    
	      	return "<select style='width:60px' id='jjf" + count + "' name='jjf'><option value='�ӷ�'>�ӷ�</option><option value='����'>����</option></select>";	 
	      },
	    function(data){    
	      	return "<input type='text' style='width:60px' id='pf" + count + "' name='pf' onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this)' maxlength='200'>";	 
	       },
	    function(data){    
	     	return "<input type='text' style='width:60px' id='xxsh" + count + "' readonly='true' name='xxsh' value='δ���' maxlength='20'>";	 
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
       if($("xxsh"+length).value!="δ���"){
           alert("�ü�¼�Ѿ�����ز�����ˣ����ܽ���ɾ��������");   
           return false;
         }else{
           tabobj.deleteRow(tabobj.rows.length-1);    
         }  
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
            if($("xxsh"+length).value!="δ���"){
              alert("���Ѿ�����ز�����˵ļ�¼�����ܽ���ɾ��������");   
              return false;
            }
            length--;
         } 
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
    if($("xh")){
        xh = $("xh").value; 
        if(xh==""){
            alert("��ȷ���������ѧ����");
            return false;
        }
     }
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
    if(rowLen == 0){
		alert("IVT��̳�������Ϊ�գ���");
		return false;
	}
    for(var i=1;i<=rowLen;i++){
   	 if($("jztm"+i)){
   			if($("jztm"+i).value == ""){
   				alert("��"+i+"��,������Ŀ����Ϊ�գ���ȷ��");
   				return false;
   				}
    		}
  			if($("xthdrq"+i)){
       			if($("xthdrq"+i).value == ""){
       				alert("��"+i+"��,���ڲ���Ϊ�գ���ȷ��");
       				return false;
       			}
   			}
		 }
        refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_dshdUpdate&xxk=ivlt&doType=update');
         $("buttonSave").disabled=true;
}
function onShow(){
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
	if(xh != ""){
		dwr.engine.setAsync(false);
		getSzPjpyDAO.dao_getXsXthdDwr(xh,xn,xq,"ivlt",function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');    
				for(var i=1;i<=data.length;i++){
					if($("jztm"+i)){
						$("jztm"+i).value = data[i-1].jztm;
					}
					if($("xthdrq"+i)){
						$("xthdrq"+i).value = data[i-1].xthdrq;
					}
					if($("jcdj"+i)){
						$("jcdj"+i).value = data[i-1].jcdj;
					}
					if($("ccdj"+i)){
						$("ccdj"+i).value = data[i-1].ccdj;
					}
					if($("xxsh"+i)){
						$("xxsh"+i).value = data[i-1].xxsh;
					}
					if($("jjf"+i)){
						$("jjf"+i).value = data[i-1].jjf;
					}
					if($("pf"+i)){
						$("pf"+i).value = data[i-1].pf;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
	}
}
		
jQuery(function(){
	onShow();
})
	</script>
	</head>
	<body >
		<html:form action="/pjpyszyqwh">
			<input id="url" name="url" type="hidden" value="/pjpy/szyqxy/zhszcp/zznl_Add.jsp"/>
			<input id="getStuInfo" name="getStuInfo" type="hidden" value="xh"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ۺ�����-ѧ���ۺ��������ɿ�-IVT��̳�޸�</a>
				</p>
			</div>
			
			<table class="formlist" border="0" id="rsTable" align="center"
				style="width: 100%">
				<tr style="height: 23px">
					<th>
						ѧ��
					</th>
					<td align="left">
						<logic:notEqual name="userType" value="stu" scope="session">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />

						</logic:notEqual>
						<logic:equal name="userType" value="stu" scope="session">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
						</logic:equal>
					</td>
					 <th>
						����
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
				    <th>
						ѧ��
					</th>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" readonly="true"/>
					</td>
					<th>
						ѧ��
					</th>
					<td align="left">
							<html:text name='rs' property="xq" styleId="xq" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
				    <th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<th>
						רҵ
					</th>
					<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						�༶
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
					<th>
					</th>
					<td align="left">
					</td>
				</tr>
			</table>
					<p>
						<!-- ��ѯ�õ�����������ʾ���� -->
						<input  value="+" onclick="trAdd('flag','add')" />
						<input  value="-" onclick="trDel('flag')" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd" style="width: 50px"
							onblur="trAdd('flag','madd')" />
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel" style="width: 50px"
							onblur="trDelAll('flag')" />
						&nbsp;��
					</p>
				
							<table align="center" style="width: 100%" id="rsT" class="formlist">
								<!-- ��ӡʱ��һ�в���ʾ- -->
								<thead style="height: 23px">
									<tr>
										<th>
											���
										</th>
										<th>
											������Ŀ
										</th>
										<th>
											����
										</th>
										<th>
											�����Ǽ�
										</th>
										<th>
											�����Ǽ�
										</th>
										<th>
											�Ӽ���
										</th>
										<th>
											����
										</th>
										<th>
											ѧУ���
										</th>
									</tr>
								</thead>
								<tbody class="formlist" id="flag">
								</tbody>
						</table>
			
			<div align="right">
					<button type="button" id="buttonSave" onclick="ztbhSave('flag');">
						�� ��
					</button>
					<button type="button" id="buttonClose" onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();" id="buttonClose">
						�� ��
					</button> 
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
