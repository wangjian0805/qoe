package cn.bupt.qoe.util;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName:  PaginatorResult   
 * @Description:分页结果
 * @author: Gxx  
 * @date:   Nov 4, 2016 11:07:07 AM   
 *   
 * @param <E>
 */
public class PaginatorResult<E> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3479977685446943512L;
	
	private List<E> rows;  
    private long total;  

    public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}

	public long getTotal() {  
        return total;  
    }  
  
    public void setTotal(long total) {  
        this.total = total;  
    }  
  
}
