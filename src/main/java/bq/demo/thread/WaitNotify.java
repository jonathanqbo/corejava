/** 
 * For personal learning, usage and backup purpose.
 */

package bq.demo.thread;

import java.util.LinkedList;
import java.util.List;

/**
 * <b> </b>
 *
 * <p> Run job one after one in sequence by using wait(), notify() </p>
 * 
 * <p> NOTE: the lock be synchronized need be same with lock to invoke wait()/notify()
 * otherwise will get {@link IllegalMonitorStateException} </p>
 * 
 * <pre>
    synchronized ( lock ) {
		try {
			lock.wait();
		} catch (InterruptedException e) {}					
	}
 * </pre>
 *
 * @author Jonathan.q.bo@gmail.com
 * 
 * Create at 3:25:00 PM Dec 8, 2016
 * 
 */

public class WaitNotify {

	public static void main(String[] args) {
		TeamPlayer[] team = new TeamPlayer[10];
		for(int i = 0 ; i < team.length; i++) {
			team[i] = new TeamPlayer();
		}
		
		List<TeamPlayerJob> jobs = new LinkedList<>();
		for(int i = 0; i < team.length; i++) {
			jobs.add(new TeamPlayerJob(team, i));
		}
		
		/* output==>
		
		job 0 done !
		job 1 done !
		job 2 done !
		job 3 done !
		job 4 done !
		job 5 done !
		job 6 done !
		job 7 done !
		job 8 done !
		job 9 done !
		*/
		jobs.forEach(t -> t.start());
	}
	
}

class TeamPlayerJob extends Thread {
	
	private int playerIndex;
	
	private  TeamPlayer[] team;
	
	public TeamPlayerJob(TeamPlayer[] team, int index) {
		this.team = team;
		this.playerIndex = index;
	}
	
	@Override
	public void run() {
		if(playerIndex > 0) {
			// wait notification for current
			synchronized ( team[playerIndex] ) {
				try {
					team[playerIndex].wait();
				} catch (InterruptedException e) {}					
			}
		}
		
		System.out.println("job " + playerIndex + " done !");
		
		if(playerIndex < (team.length - 1)) {
			// notify next
			synchronized (team[playerIndex + 1]) {
				team[playerIndex + 1].notify();					
			}
		}
	}
}

class TeamPlayer {}