package zhongfucheng.domain;

public class Page {
	private Integer pagesize;//页面大小
	private Integer pageno;//当前页
	private Integer startrow;//起始行
	private Integer totalpage;//总页数
	private Integer totalcount;//总条数
	public Page(){
		
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getStartrow() {
		return startrow;
	}
	/**
	 * 计算起始行
	 * @param pageNo
	 * @param pageSize
	 */
	public void setStartrow(Integer pageNo,Integer pageSize) {
		this.startrow =(pageNo-1)*pageSize;
	}
	public Integer getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(Integer totalCount,Integer pageSize) {
		this.totalpage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
	}
	public Integer getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	@Override
	public String toString() {
		return "Page [pagesize=" + pagesize + ", pageno=" + pageno
				+ ", startrow=" + startrow + ", totalpage=" + totalpage
				+ ", totalcount=" + totalcount + "]";
	}

	
  

}
