/*
 * ZookeeperTest.java
 */


import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author http://blog.csdn.net/java2000_wl
 * @version <b>1.0</b>
 */
public class ZookeeperTest {
	
	private static final int SESSION_TIMEOUT = 30000;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperTest.class);
	
	private Watcher watcher =  new Watcher() {

		public void process(WatchedEvent event) {
			LOGGER.info("process : " + event.getType());
		}
	};
	
	private ZooKeeper zooKeeper;
	
	/**
	 *  连接zookeeper
	 * <br>------------------------------<br>
	 * @throws IOException
	 */
	@Before
	public void connect() throws IOException {
//		zooKeeper  = new ZooKeeper("localhost:2181,localhost:2182,localhost:2183", SESSION_TIMEOUT, watcher);
		zooKeeper  = new ZooKeeper("localhost:9983", SESSION_TIMEOUT, watcher);
	}
	
	/**
	 *  关闭连接
	 * <br>------------------------------<br>
	 */
	@After
	public void close() {
		try {
			zooKeeper.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建一个znode:只能一层层的创建，不支持父级目录创建
	 *  1.CreateMode 取值  
	 *  PERSISTENT：持久化，这个目录节点存储的数据不会丢失
	 *  PERSISTENT_SEQUENTIAL：顺序自动编号的目录节点，这种目录节点会根据当前已近存在的节点数自动加 1，然后返回给客户端已经成功创建的目录节点名；
	 *  EPHEMERAL：临时目录节点，一旦创建这个节点的客户端与服务器端口也就是 session过期超时，这种节点会被自动删除
	 *  EPHEMERAL_SEQUENTIAL：临时自动编号节点
	 * <br>------------------------------<br>
	 */
	@Test
	public void testCreate() {
		String result = null;
		 try {
			 result = zooKeeper.create("/SolrNew/ns1", "zk001data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 LOGGER.info("create result : {}", result);
	 }
	
	/**
	 * 删除节点  忽略版本
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDelete() {
		 try {
			zooKeeper.delete("/solrtest", -1);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
	}
	
	/**
	 *   获取数据
	 * <br>------------------------------<br>
	 */
	@Test
	public void testGetData() {
		String result = null;
		 try {
			 byte[] bytes = zooKeeper.getData("/zk001", null, null);
			 result = new String(bytes);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 LOGGER.info("getdata result : {}", result);
	}
	
	/**
	 *   获取数据  设置watch
	 * <br>------------------------------<br>
	 */
	@Test
	public void testGetDataWatch() {
		String result = null;
		 try {
			 byte[] bytes = zooKeeper.getData("/zk001", new Watcher() {
				public void process(WatchedEvent event) {
					LOGGER.info("testGetDataWatch  watch : {}", event.getType());
				}
			 }, null);
			 result = new String(bytes);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 LOGGER.info("getdata result : {}", result);
		 
		 // 触发wacth  NodeDataChanged
		 try {
			 zooKeeper.setData("/zk001", "testSetData".getBytes(), -1);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
	}
	
	/**
	 *    判断节点是否存在
	 *    设置是否监控这个目录节点，这里的 watcher 是在创建 ZooKeeper实例时指定的 watcher
	 * <br>------------------------------<br>
	 */
	@Test
	public void testExists() {
		Stat stat = null;
		 try {
			 stat = zooKeeper.exists("/configs", false);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 Assert.assertNotNull(stat);
		 LOGGER.info("exists result : {}", stat.getCzxid());
	}
	
	/**
	 *     设置对应znode下的数据  ,  -1表示匹配所有版本
	 * <br>------------------------------<br>
	 */
	@Test
	public void testSetData() {
		Stat stat = null;
		 try {
			 stat = zooKeeper.setData("/zk001", "testSetData".getBytes(), -1);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 Assert.assertNotNull(stat);
		 LOGGER.info("exists result : {}", stat.getVersion());	
	}
	
	/**
	 *    判断节点是否存在, 
	 *    设置是否监控这个目录节点，这里的 watcher 是在创建 ZooKeeper实例时指定的 watcher
	 * <br>------------------------------<br>
	 */
	@Test
	public void testExistsWatch1() {
		Stat stat = null;
		 try {
			 stat = zooKeeper.exists("/zk001", true);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 Assert.assertNotNull(stat);
		 
		 try {
			zooKeeper.delete("/zk001", -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *    判断节点是否存在, 
	 *    设置监控这个目录节点的 Watcher
	 * <br>------------------------------<br>
	 */
	@Test
	public void testExistsWatch2() {
		Stat stat = null;
		 try {
			 stat = zooKeeper.exists("/zk002", new Watcher() {
				public void process(WatchedEvent event) {
					LOGGER.info("testExistsWatch2  watch : {}", event.getType());
				}
			 });
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 Assert.assertNotNull(stat);
		 
		 // 触发watch 中的process方法   NodeDataChanged
		 try {
			zooKeeper.setData("/zk002", "testExistsWatch2".getBytes(), -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 // 不会触发watch 只会触发一次
		 try {
			zooKeeper.delete("/zk002", -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  获取指定节点下的子节点
	 * <br>------------------------------<br>
	 */
	@Test
	public void testGetChild() {
		 try {
			 zooKeeper.create("/zk/001", "001".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			 zooKeeper.create("/zk/002", "002".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			 
			 List<String> list = zooKeeper.getChildren("/zk", true);
			for (String node : list) {
				LOGGER.info("node {}", node);
			}
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
	}
	
	/**
	 * 删除zookeeper指定节点下的所有节点
	 * 执行之前要把solr服务（tomcat）关掉
	 * @throws Throwable
	 */
	@Test
	public void deleteNode() throws Throwable {
		// 创建一个Zookeeper实例，第一个参数为目标服务器地址和端口，第二个参数为Session超时时间，第三个为节点变化时的回调方法
//测试环境：172.20.8.5:2181,172.20.8.33:2181,172.20.8.36:2181
//		172.20.8.5 mcloud-db3
//		172.20.8.33 mcloud-db4
//		172.20.8.36 mcloud-db5
//生产环境：192.168.2.8:2181,192.168.2.9:2181,192.168.2.10:2181
//		192.168.2.8  dbcluster3
//		192.168.2.9  dbcluster4
//		192.168.2.10 dbcluster5

		ZooKeeper zk = new ZooKeeper("192.168.2.8:2181,192.168.2.9:2181,192.168.2.10:2181", 500000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("触发我："+event.getState());
			}
		});
		String pPath1 = "/clusterstate.json";
		String pPath2 = "/collections";
		String pPath3 = "/live_nodes";
		String pPath4 = "/overseer_elect";
		String pPath5 = "/configs";
		deleteNodeByPPath(zk,pPath1);
		deleteNodeByPPath(zk,pPath2);
		deleteNodeByPPath(zk,pPath3);
		deleteNodeByPPath(zk,pPath4);
		deleteNodeByPPath(zk,pPath5);
		// 关闭session
		zk.close();
	}
	
	private void deleteNodeByPPath(ZooKeeper zk,String pPath) throws Throwable {
		// 取得/root节点下的子节点名称,返回List<String>
				List<String> nodeList;
				try {
					nodeList = zk.getChildren(pPath, false);
				} catch (Exception e) {
					return;
				}
				if(nodeList.isEmpty()){
					// 删除/root/childone这个节点，第二个参数为版本，－1的话直接删除，无视版本
					zk.delete(pPath, -1);
				}else{
					for(String node : nodeList){
						String path = pPath + "/" + node;
						deleteNodeByPPath(zk,path);
					}
				}
	}

	/**
	 * 测试创建节点（父目录不存在可创建）
	 */
	@Test
	public void testMakeDir() {
		makeDir(zooKeeper, "/SolrNew1/ns3", ""); 
	}
	
		public static void makeDir(ZooKeeper zk, String dir, String value) {
			String parentDir = dir;
			Stat stat = null;
			 try {
				 if(!"/".equals(parentDir)){
						//如果以“/”结尾去掉
						if(parentDir.lastIndexOf("/")==parentDir.length()-1){
							parentDir = parentDir.substring(0,dir.length()-1);
						}
						
//						减少一层目录：/1/2/3->/1/2
						parentDir = StringUtil.strLeftBack(parentDir, "/");
					}
					 stat = zk.exists(dir, new Watcher() {
						public void process(WatchedEvent event) {
							LOGGER.info("zooKeeper.exists  watch : {}", event.getType());
						}
					 });
			} catch (Exception e) {
				e.printStackTrace();
				 LOGGER.error(e.getMessage());
				 return;
			}
			if (stat == null && dir.lastIndexOf("/") != 0) {//父级目录不存在，创建
				makeDir(zk,parentDir,null);
			}
			try {
				if (stat == null) {
					zk.create(dir, value==null?null:value.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				}
			} catch (KeeperException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			}
		}
		
	@Test
	public void test() throws Throwable {
		// 创建一个Zookeeper实例，第一个参数为目标服务器地址和端口，第二个参数为Session超时时间，第三个为节点变化时的回调方法
		ZooKeeper zk = new ZooKeeper("192.168.1.7:2181", 500000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				// dosomething
			}
		});
		// 创建一个节点root，数据是mydata,不进行ACL权限控制，节点为永久性的(即客户端shutdown了也不会消失)
		zk.create("/root", "mydata".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);

		// 在root下面创建一个childone znode,数据为childone,不进行ACL权限控制，节点为永久性的
		zk.create("/root/childone", "childone".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);

		// 取得/root节点下的子节点名称,返回List<String>
		zk.getChildren("/root", true);

		// 修改节点/root/childone下的数据，第三个参数为版本，如果是-1，那会无视被修改的数据版本，直接改掉
		zk.setData("/root/childone", "childonemodify".getBytes(), -1);

		// 取得/root/childone节点下的数据,返回byte[]
		byte[] bytes = zk.getData("/root/childone", true, null);
		System.out.println(new String(bytes));

		// 删除/root/childone这个节点，第二个参数为版本，－1的话直接删除，无视版本
		zk.delete("/root/childone", -1);

		// 关闭session
		zk.close();

	}

}
