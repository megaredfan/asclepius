package buaa.bp.asclepius.utils;

public class PageOut {

	//条数
	//private final static int num = Integer.parseInt(Configuration.getProperty("num"));
	private final static int num = 100;
	/**
	 * 分页函数
	 * 
	 * @param pageNum
	 * @param count
	 * @return
	 */
	public static String outPageing(int pageNum, int count) {
		// 分页总数
		int pageCount = 0;
		if (count % num == 0) {
			pageCount = count / num;
		} else {
			pageCount = count / num + 1;
		}
		if (pageCount == 0) {
			pageCount = 1;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='pagination pagination-left'>");
		sb.append("<ul>");
		sb.append("<li>");
		sb.append("共"+count+"条纪录,当前第"+(pageNum+1)+"/"+pageCount+"页,每页"+num+"条记录");
		sb.append("</li>");
		sb.append("</ul>");
		sb.append("</div>");
		sb.append("<div class='pagination pagination-centered'>");
		sb.append("<ul>");
		if(pageNum < 1){
			sb.append("<li><a href='javascript:void(0);'><i class='icon-fast-backward'></i></a></li>");
			sb.append("<li><a href='javascript:void(0);'>← Previous</a></li>");
		}else{
			sb.append("<li><a href='javascript:void(0);' onclick='javascript:goPage(0);'><i class='icon-fast-backward'></i></a></li>");
			sb.append("<li><a href='javascript:void(0);' onclick='javascript:goPage("+ (pageNum - 1) +");'>← Previous</a></li>");
		}
		if(pageCount < 11){
			for (int i = 0; i < pageCount; i++) {
				if(pageNum == i){
					sb.append("<li class='active'><a href='javascript:void(0);'>"+ (i+1) +"</a></li>");
				}else{
					sb.append("<li><a href='javascript:void(0);' onclick='javascript:goPage("+ i +");'>"+ (i+1) +"</a></li>");
				}
			}
		}else{
			int start = (pageNum - 5);
			if(start < 0){
				start = 0;
			}
			int end = pageNum + 5;//分页信息显示到第几页
			if(end > pageCount){
				end = pageCount;
			}
			
			for (int i = start; i < end; i++) {
				if(pageNum == i){
					sb.append("<li class='active'><a href='javascript:void(0);'>"+ (i+1) +"</a></li>");
				}else{
					sb.append("<li><a href='javascript:void(0);' onclick='javascript:goPage("+ i +");'>"+ (i+1) +"</a></li>");
				}
			}
		}
		if(pageCount < 1 || pageCount == (pageNum + 1)){
			sb.append("<li><a href='javascript:void(0);'>Next →</a></li>");
			sb.append("<li><a href='javascript:void(0);'><i class='icon-fast-forward'></i></a></li>");
		}else{
			sb.append("<li><a href='javascript:void(0);' onclick='javascript:goPage("+ (pageNum + 1) +");'>Next →</a></li>");
			sb.append("<li><a href='javascript:void(0);' onclick='javascript:goPage("+ (pageCount - 1) +");'><i class='icon-fast-forward'></i></a></li>");
		}
		sb.append("</ul>");
		sb.append("</div>");
		return sb.toString();
	}
	

}
