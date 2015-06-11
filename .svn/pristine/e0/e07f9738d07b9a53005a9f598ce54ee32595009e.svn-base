import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;


public class MoreLikeThisTest {
	
	private static HttpClient client = new DefaultHttpClient(new ThreadSafeClientConnManager());
	
	public static void main(String[] args) throws ParseException, ClientProtocolException, IOException {
		String uri = "http://192.168.61.89:8983/solr/core0/select?mlt.fl=contents&mlt.mintf=1&mlt=true&mlt.maxwl=9&distrib=false&mlt.maxqt=10&mlt.boost=true&wt=javabin&version=2&rows=1&df=title&mlt.count=10&fl=cid_iid,score&start=0&q=iid:d204381127&isShard=true&fsv=true";
		HttpGet get = new HttpGet(uri);
		System.out.println(EntityUtils.toString(client.execute(get).getEntity(),"utf-8"));
	}
	

}
