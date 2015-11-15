package com.iiitd.ap.lab10;

/**
 * @author ambar14012
 * @author palash14072
 */

public interface Subject{
	void registerObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObservers();
	Object getUpdate();
}