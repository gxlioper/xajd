//函数说明：合并指定表格（表格id为_w_table_id）指定列（列数为_w_table_colnum）的相同文本的相邻单元格 
//参数说明：_w_table_id 为需要进行合并单元格的表格的id。如在HTMl中指定表格 id="data" ，此参数应为 #data  
//参数说明：_w_table_colnum 为需要合并单元格的所在列。为数字，从最左边第一列为1开始算起。 
function _w_table_rowspan(_w_table_id,_w_table_colnum){ 
    _w_table_firsttd = ""; 
    _w_table_currenttd = ""; 
    _w_table_SpanNum = 0; 
    _w_table_colnum = _w_table_colnum-1
    _w_table_Obj = jQuery(_w_table_id + " tr:gt(0)"); 
    _w_table_Obj.each(function(i){ 
        if(i==0){ 
            _w_table_firsttd = jQuery(this).children("td").eq(_w_table_colnum); 
            _w_table_SpanNum = 1; 
        }else{ 
            _w_table_currenttd = jQuery(this).children("td").eq(_w_table_colnum); 
            if(_w_table_firsttd.text()==_w_table_currenttd.text()){ 
                _w_table_SpanNum++; 
                _w_table_currenttd.hide(); //remove(); 
                _w_table_firsttd.attr("rowSpan",_w_table_SpanNum); 
            }else{ 
                _w_table_firsttd = jQuery(this).children("td").eq(_w_table_colnum); 
                _w_table_SpanNum = 1; 
            } 
        } 
     });  
  } 


//函数说明：合并指定表格（表格id为_w_table_id）依照列（列数为_w_table_colnum）合并指定列（列数为_w_table_colnum）单元格 
//参数说明：_w_table_id 为需要进行合并单元格的表格的id。如在HTMl中指定表格 id="data" ，此参数应为 #data  
//参数说明：_w_table_colnum 参考合并的单元格。
//参数说明：_w_table_mernum 合并单元格所在的列。
function _w_table_rowspan_merge(_w_table_id,_w_table_colnum, _w_table_mernum){ 
	_w_table_firsttd = ""; 
    _w_table_currenttd = ""; 
    _w_table_SpanNum = 0; 
    _w_table_colnum = _w_table_colnum-1
    _w_table_mernum = _w_table_mernum-1
    _w_table_Obj = jQuery(_w_table_id + " tr:gt(0)"); 
    _w_table_Obj.each(function(i){ 
        if(i==0){ 
            _w_table_firsttd = jQuery(this).children("td").eq(_w_table_colnum); 
            _w_table_firstmertd = jQuery(this).children("td").eq(_w_table_mernum); 
            _w_table_SpanNum = 1; 
        }else{ 
            _w_table_currenttd = jQuery(this).children("td").eq(_w_table_colnum); 
            _w_table_currentmertd = jQuery(this).children("td").eq(_w_table_mernum); 
            if(_w_table_firsttd.text()==_w_table_currenttd.text()){ 
                _w_table_SpanNum++; 
                _w_table_currentmertd.hide(); //remove(); 
                _w_table_firstmertd.attr("rowSpan",_w_table_SpanNum); 
            }else{ 
                _w_table_firsttd = jQuery(this).children("td").eq(_w_table_colnum); 
                _w_table_firstmertd = jQuery(this).children("td").eq(_w_table_mernum);
                _w_table_SpanNum = 1; 
            } 
        } 
     });  
  }

//函数说明：合并指定表格（表格id为_w_table_id）依照列（列数为_w_table_colnum）合并指定列（列数为_w_table_colnum）单元格 ,合并后求和
//参数说明：_w_table_id 为需要进行合并单元格的表格的id。如在HTMl中指定表格 id="data" ，此参数应为 #data  
//参数说明：_w_table_colnum 参考合并的单元格。
//参数说明：_w_table_mernum 合并单元格所在的列。
function _w_table_rowspan_merge_sum(_w_table_id,_w_table_colnum, _w_table_mernum){ 
  _w_table_firsttd = ""; 
  _w_table_currenttd = ""; 
  _w_table_SpanNum = 0; 
  _w_table_colnum = _w_table_colnum-1
  _w_table_mernum = _w_table_mernum-1
  _w_table_SpanSum = 0;
  _w_table_Obj = jQuery(_w_table_id + " tr:gt(0)"); 
  _w_table_Obj.each(function(i){ 
      if(i==0){ 
          _w_table_firsttd = jQuery(this).children("td").eq(_w_table_colnum); 
          _w_table_firstmertd = jQuery(this).children("td").eq(_w_table_mernum); 
          _w_table_SpanNum = 1; 
          _w_table_SpanSum = jQuery(this).children("td").eq(_w_table_mernum).text();
      }else{ 
          _w_table_currenttd = jQuery(this).children("td").eq(_w_table_colnum); 
          _w_table_currentmertd = jQuery(this).children("td").eq(_w_table_mernum); 
          if(_w_table_firsttd.text()==_w_table_currenttd.text()){ 
              _w_table_SpanNum++; 
              _w_table_SpanSum = parseInt(_w_table_SpanSum)+parseInt(jQuery(this).children("td").eq(_w_table_mernum).text());
              _w_table_currentmertd.hide(); //remove(); 
              _w_table_firstmertd.attr("rowSpan",_w_table_SpanNum);
              _w_table_firstmertd.text(_w_table_SpanSum);
          }else{ 
              _w_table_firsttd = jQuery(this).children("td").eq(_w_table_colnum); 
              _w_table_firstmertd = jQuery(this).children("td").eq(_w_table_mernum);
              _w_table_SpanNum = 1;
              _w_table_SpanSum = jQuery(this).children("td").eq(_w_table_mernum).text();
          } 
      } 
   });  
}