//����˵�����ϲ�ָ����񣨱��idΪ_w_table_id��ָ���У�����Ϊ_w_table_colnum������ͬ�ı������ڵ�Ԫ�� 
//����˵����_w_table_id Ϊ��Ҫ���кϲ���Ԫ��ı���id������HTMl��ָ����� id="data" ���˲���ӦΪ #data  
//����˵����_w_table_colnum Ϊ��Ҫ�ϲ���Ԫ��������С�Ϊ���֣�������ߵ�һ��Ϊ1��ʼ���� 
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


//����˵�����ϲ�ָ����񣨱��idΪ_w_table_id�������У�����Ϊ_w_table_colnum���ϲ�ָ���У�����Ϊ_w_table_colnum����Ԫ�� 
//����˵����_w_table_id Ϊ��Ҫ���кϲ���Ԫ��ı���id������HTMl��ָ����� id="data" ���˲���ӦΪ #data  
//����˵����_w_table_colnum �ο��ϲ��ĵ�Ԫ��
//����˵����_w_table_mernum �ϲ���Ԫ�����ڵ��С�
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

//����˵�����ϲ�ָ����񣨱��idΪ_w_table_id�������У�����Ϊ_w_table_colnum���ϲ�ָ���У�����Ϊ_w_table_colnum����Ԫ�� ,�ϲ������
//����˵����_w_table_id Ϊ��Ҫ���кϲ���Ԫ��ı���id������HTMl��ָ����� id="data" ���˲���ӦΪ #data  
//����˵����_w_table_colnum �ο��ϲ��ĵ�Ԫ��
//����˵����_w_table_mernum �ϲ���Ԫ�����ڵ��С�
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