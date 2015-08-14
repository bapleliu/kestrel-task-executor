/**
 * Jul 30, 2012
 */
package org.zlex.kestrel.executor;

import static junit.framework.Assert.*;

import net.rubyeye.xmemcached.MemcachedClient;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * WorkerTest
 * 
 * @author zlex
 * @version 1.0
 * @since 1.0
 */
public class KestrelExecutorWorkerTest {

	private ApplicationContext app;
	private KestrelExecutorWorker kestrelExecutorWorker;
	private MemcachedClient memcachedClient;

	private final static String QUEUE_NAME = "KQ";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void before() throws Exception {
		app = new ClassPathXmlApplicationContext("applicationContext.xml");
		memcachedClient = (MemcachedClient) app.getBean("memcachedClient");
		kestrelExecutorWorker = (KestrelExecutorWorker) app
				.getBean("kestrelExecutorWorker");
	}

	@Test
	public void test() {
		while (true) {
			try {
				Object value = (Object) memcachedClient.get(QUEUE_NAME);
				if (value != null) {
					kestrelExecutorWorker.process(value);
				}
			} catch (Exception e) {
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
	}
}
