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
public interface CountdownListener {
	public void onTick(int current);
	public void onStart();
	public void onStop();
	public void onPause();
	public void onUnPause();
	public void onMaximum(int maximum);
}
