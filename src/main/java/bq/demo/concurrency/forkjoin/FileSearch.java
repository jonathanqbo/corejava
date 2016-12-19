/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.concurrency.forkjoin;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * <b> </b>
 *
 * <p> </p>
 *
 * @author Jonathan.q.bo@gmail.com
 *
 * @since Create at 11:39:06 PM Dec 18, 2016
 * 
 */

public class FileSearch extends RecursiveTask<List<String>>{

	private static final long serialVersionUID = -4298984888925785671L;

	private String path;
	
	private String namePattern;
	
	public FileSearch(String path, String namePattern) {
		this.path = path;
		this.namePattern = namePattern;
	}
	
	@Override
	protected List<String> compute() {
		File f = new File(path);
		
		List<FileSearch> subTasks = new LinkedList<>();
		List<String> results = new LinkedList<>();
		
		for (File file : f.listFiles()) {
			if ( file.isDirectory() ) {
				FileSearch subTask = new FileSearch(file.getAbsolutePath(), this.namePattern);
				// Fork
				subTask.fork();
				subTasks.add(subTask);
			}
			else {
				if ( file.getName().matches(this.namePattern)) 
					results.add(file.getAbsolutePath());
			}
		}
		
		subTasks.forEach( task -> {
			try {
				// Join, also can use get()
				results.addAll(task.join());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return results;
	}

	public static void main(String[] args) {
		String path = "/Users/qibo";
		FileSearch task = new FileSearch(path, "resume.*");
		
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		
		do {
			System.out.println("=====================");
			System.out.printf("Parallelism : %d\n", pool.getParallelism());
			System.out.printf("Steal : %d\n", pool.getStealCount());
			System.out.printf("ActiveThreadCount : %d\n", pool.getActiveThreadCount());
			System.out.printf("QueuedTaskCount : %d\n", pool.getQueuedTaskCount());
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {}
			
		} while ( !task.isDone() );
		
		pool.shutdown();
		
		try {
			task.get().forEach(System.out::println);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
}
