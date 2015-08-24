/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmquiz;

/**
 *
 * @author Christian
 */
import java.util.ArrayList;

public class Countdown extends Thread {
	private int current = 0;
	private int minimum = 0;
	private int maximum = 60;
	
	private boolean noMaximum = false;
	private boolean pause     = true;
	
	private ArrayList<CountdownListener> listeners = new ArrayList<CountdownListener>();
	
	@Override
	public void run() {
		while (true) {
			try { Thread.sleep(1000); } catch (Exception e) { }
			
			if (!isPause()) {
				if (current <= minimum) {
					current = minimum + 1;
				}

				// OnTick Event
				for (CountdownListener l : this.listeners) {
					l.onTick(current);
				}
				
				if (++current > maximum || noMaximum) {
					stopCountdown();
					
					// OnMaximumEvent
					for (CountdownListener l : this.listeners) {
						l.onMaximum(maximum);
					}
					
					pause = true;
				}
			}
		}
	}
	
	public void startCountdown() {
		setPause(false);
		super.start();
		
		// OnStart Event
		for (CountdownListener l : this.listeners) {
			l.onStart();
		}
	}
	
	public void stopCountdown() {
		setPause(true);
		reset();
		
		// OnStop Event
		for (CountdownListener l : this.listeners) {
			l.onStop();
		}
	}
	
	public void reset() {
		this.current = this.minimum;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public boolean isNoMaximum() {
		return noMaximum;
	}

	public void setNoMaximum(boolean noMaximum) {
		this.noMaximum = noMaximum;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
		
		// OnPause Event / OnUnPause Event
		for (CountdownListener l : this.listeners) {
			if (isPause()) {
				l.onPause();
			} else {
				l.onUnPause();
			}
		}
	}
	
	public void addListener(CountdownListener listener) {
		if (listener != null) {
			this.listeners.add(listener);
		} else {
			throw new NullPointerException("Listener could not be null!");
		}
	}
}
