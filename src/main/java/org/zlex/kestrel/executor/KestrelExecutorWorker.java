/**
 * Jul 30, 2012
 */
package org.zlex.kestrel.executor;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Worker
 * 
 * @author zlex
 * @version 1.0
 * @since 1.0
 */
@Component
public class KestrelExecutorWorker {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(KestrelExecutorWorker.class);

	@Resource
	private TaskExecutor kestrelExecutor;

	/**
	 * 业务处理
	 * 
	 * @param value
	 */
	public void process(final Object value) {
		kestrelExecutor.execute(new Runnable() {
			public void run() {
				String finalStr = "finalString is "+value.toString();
				logger.debug(finalStr);
			}
		});
	}

}
