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
	return "<textarea rows='3' cols='32' id='hdzt" + count + "' name='hdzt'></textarea>";
    },	
	function(data){	    
	return "<input type='text' style='width:80px'  name='hdrq' id='hdrq" + count + "' readonly='true' onblur='dateFormatChg(this)' style='cursor:hand;' onclick='time(this.id)'>";
    },
    function(data){
    return "<input type='text' style='width:100px'  name='hddj' id='hddj" + count + "' >";
    },
    function(data){    
    	return "<select style='width:60px' id='jjf" + count + "' name='jjf'><option value='�ӷ�'>�ӷ�</option><option value='����'>����</option></select>";	 
    },
    function(data){    
    	return "<input type='text' style='width:60px' onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this)' id='shfz" + count + "' name='shfz' maxlength='200'>";	 
     },
     function(data){    
     	return "<input type='text' style='width:60px' id='xxsh" + count + "' value='δ���' readonly='true' name='xxsh' maxlength='10'>";	 
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
    if(rowLen == 0){
		alert("��֯�����������Ϊ�գ���");
		return false;
	}
    for(var i=1;i<=rowLen;i++){
   	   if($("hdzt"+i)){
  			if($("hdzt"+i).value == ""){
  				alert("��"+i+"��,����ⲻ��Ϊ�գ���ȷ��");
  				return false;
  				}
  			if($("hdzt"+i).value.length>300){
  				alert("��"+i+"��,�������������ֻ����300������ȷ��");
  				return false;
  			}
   		}
  			if($("hdrq"+i)){
      			if($("hdrq"+i).value == ""){
      				alert("��"+i+"��,���ڲ���Ϊ�գ���ȷ��");
      				return false;
      			}
  			}
  			if($("hddj"+i)){
      			if($("hddj"+i).value == ""){
      				alert("��"+i+"��,��ȼ�����Ϊ�գ���ȷ��");
      				return false;
      			}
  			}
  			if($("shfz"+i)){
      			if($("shfz"+i).value == ""){
      				alert("��"+i+"��,ʵ�ʼӷֲ���Ϊ�գ���ȷ��");
      				return false;
      			}
  			}
   }
        refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_zznlUpdate&doType=update');
         $("buttonSave").disabled=true;
}
function onShow(){
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
	if(xh != ""){
		dwr.engine.setAsync(false);
		getSzPjpyDAO.dao_getZZnlDwr(xh,xn,xq,function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');    
				for(var i=1;i<=data.length;i++){
					if($("hdzt"+i)){
						$("hdzt"+i).value = data[i-1].hdzt;
					}
					if($("hdrq"+i)){
						$("hdrq"+i).value = data[i-1].hdrq;
					}
					if($("hddj"+i)){
						$("hddj"+i).value = data[i-1].hddj;
					}
					if($("jjf"+i)){
						$("jjf"+i).value = data[i-1].jjf;
					}
					if($("shfz"+i)){
						$("shfz"+i).value = data[i-1].shfz;
					}
					if($("xxsh"+i)){
						$("xxsh"+i).value = data[i-1].xxsh;
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
					<em>���ĵ�ǰλ��:</em><a>�ۺ�����-ѧ���ۺ��������ɿ�-��֯�����޸�</a>
				</p>
			</div>
			
			<table class="formlist" border="0" id="rsTable" align="center"
				style="width: 100%">

				<tr>
					<th>
						ѧ��
					</th>
					<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
					</td>
					<th>
						ѧ��
					</th>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" readonly="true" />
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						ѧ��
					</th>
					<td align="left">
							<html:text name='rs' property="xq" styleId="xq" readonly="true" />
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true" />
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						רҵ
					</th>
					<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true" />
					</td>
					<th>
						�༶
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true" />
					</td>
				</tr>
			</table>
			
			<!-- ��ѯ�õ�����������ʾ���� -->
			<input  value="+" onclick="trAdd('flag','add')" />
			<input  value="-" onclick="trDel('flag')" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
			<input type="text" name="numAdd" id="numAdd" style="width: 50px"
				onblur="trAdd('flag','madd')"/>
			&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
			<input type="text" name="numDel" id="numDel" style="width: 50px"
				onblur="trDelAll('flag')"/>
					&nbsp;��
			<table class="formlist" align="center" style="width: 90%" id="rsT">
				<!-- ��ӡʱ��һ�в���ʾ- -->
				<thead style="height: 23px">
					<tr>
						<th>
							���
						</th>
						<th nowrap="nowrap">
							�����
						</th>
						<th nowrap="nowrap">
							����
						</th>
						<th nowrap="nowrap">
							�ȼ�
						</th>
						<th nowrap="nowrap">
							�Ӽ���
						</th>
						<th nowrap="nowrap">
							����
						</th>
						<th nowrap="nowrap">
							ѧУ���
						</th>
					</tr>
				</thead>
				<tbody id="flag">
				</tbody>				
			</table>
	
			<table class="formlist" width="93%">
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						 <button type="button" class="button2" id="buttonSave" onclick="ztbhSave('flag');" style="width: 80px">
							�� ��
						</button>
						<button type="button" class="button2" id="buttonClose" onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();" style="width: 80px" id="buttonClose">
							�� ��
						</button> 
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			
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
