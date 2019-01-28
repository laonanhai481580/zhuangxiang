package zhongfucheng.domain;

public class Page {
	private Integer pagesize;//ҳ���С
	private Integer pageno;//��ǰҳ
	private Integer startrow;//��ʼ��
	private Integer totalpage;//��ҳ��
	private Integer totalcount;//������
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
	 * ������ʼ��
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
