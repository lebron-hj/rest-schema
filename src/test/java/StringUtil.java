public class StringUtil {

	public static String strLeft(final String text, String subtext) {
		if(!hasText(text) || !hasText(subtext)) {
			return "";
		}
		
		int find = text.indexOf(subtext);
		return (find!=-1) ? text.substring(0, find) : "";
	}
	
	public static String strRight(final String text, String subtext) {
		if(!hasText(text) || !hasText(subtext)) {
			return "";
		}
		
		int find = text.indexOf(subtext);
		return (find!=-1) ? text.substring(find+1) : "";
	}

	public static String strLeftBack(final String text, String subtext) {
		if(!hasText(text) || !hasText(subtext)) {
			return "";
		}
		
		int find = text.lastIndexOf(subtext);
		return (find!=-1) ? text.substring(0, find) : "";
	}
	
	public static String strRightBack(final String text, String subtext) {
		if(!hasText(text) || !hasText(subtext)) {
			return "";
		}
		
		int find = text.lastIndexOf(subtext);
		return (find!=-1) ? text.substring(find+1) : "";
	}
	
	private static boolean hasText(String text) {
		return (text!=null) && (!"".equals(text));
	}
	
	public static void main(String[] args) {
		String pPath = "/root/1/2/3/";
		if((!"/".equals(pPath)) && pPath.lastIndexOf("/")==pPath.length()-1){
			pPath = pPath.substring(0,pPath.length()-1);
		}
		System.out.println("pPath="+pPath);
		pPath = StringUtil.strLeftBack(pPath, "/");
		System.out.println("pPath1="+pPath);
//		String parentDir = new String(pPath);
		String parentDir = pPath;
		parentDir = StringUtil.strLeftBack(parentDir, "/");
		System.out.println("pPath2="+pPath);
		System.out.println("parentDir="+parentDir);
	}
}
